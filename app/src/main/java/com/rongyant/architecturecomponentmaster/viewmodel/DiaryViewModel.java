package com.rongyant.architecturecomponentmaster.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.rongyant.architecturecomponentmaster.db.DataBaseCreator;
import com.rongyant.architecturecomponentmaster.db.entity.DairyEntity;

/**
 * Created by XRY on 2017/7/21.
 */

public class DiaryViewModel extends AndroidViewModel{
    private final int userId;
    private final int diaryId;

    private static final MutableLiveData ABSENT = new MutableLiveData();

    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    private final LiveData<DairyEntity> mObservableDiary;

    public ObservableField<DairyEntity> diary = new ObservableField<>();


    public DiaryViewModel(Application application, final int userId, final int diaryId) {
        super(application);
        this.diaryId = diaryId;
        this.userId = userId;

        final DataBaseCreator dataBaseCreator = DataBaseCreator.getInstance();

        mObservableDiary = Transformations.switchMap(dataBaseCreator.isDatabaseCreated(), new Function<Boolean, LiveData<DairyEntity>>() {
            @Override
            public LiveData<DairyEntity> apply(Boolean isDbCreated) {
                if (!isDbCreated) {
                    return ABSENT;
                } else {
                    return dataBaseCreator.getDatabase().dairyDao().loadDairies(diaryId, userId);
                }
            }
        });

        dataBaseCreator.createDb(this.getApplication());
    }

    public LiveData<DairyEntity> getDiary() {
        return mObservableDiary;
    }

    public void setDiary(DairyEntity entity) {
        this.diary.set(entity);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application mApplication;

        private final int userId;

        private final int diaryId;


        public Factory(@NonNull Application mApplication, int userId, int diaryId) {
            this.mApplication = mApplication;
            this.userId = userId;
            this.diaryId = diaryId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return ((T) new DiaryViewModel(mApplication, userId, diaryId));
        }
    }


}
