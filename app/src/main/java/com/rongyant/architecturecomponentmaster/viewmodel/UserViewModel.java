package com.rongyant.architecturecomponentmaster.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.rongyant.architecturecomponentmaster.db.DataBaseCreator;
import com.rongyant.architecturecomponentmaster.db.entity.UserEntity;

import javax.annotation.Nonnull;

/**
 * Created by XRY on 2017/7/21.
 */

public class UserViewModel extends AndroidViewModel {
    public static final MutableLiveData<UserEntity> ABSENT = new MutableLiveData<>();

    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    private LiveData<UserEntity> mObservableUser;
    private int mUserId;


    public UserViewModel(Application application, int userId) {
        super(application);

        mUserId = userId;

        final DataBaseCreator dataBaseCreator = DataBaseCreator.getInstance();

        LiveData<Boolean> databaseCreated = dataBaseCreator.isDatabaseCreated();

        mObservableUser = Transformations.switchMap(databaseCreated, new Function<Boolean, LiveData<UserEntity>>() {
            @Override
            public LiveData<UserEntity> apply(Boolean isDbCreated) {
                if (!Boolean.TRUE.equals(isDbCreated)) {
                    return ABSENT;
                } else {
                    return dataBaseCreator.getDatabase().userDao().loadUser(mUserId);
                }
            }
        });

        dataBaseCreator.createDb(this.getApplication());

    }

    public LiveData<UserEntity> getUser() {
        return mObservableUser;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @Nonnull
        private final Application application;

        private final int userId;


        public Factory(@Nonnull Application application, int userId) {
            this.application = application;
            this.userId = userId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new UserViewModel(application, userId);
        }
    }


}
