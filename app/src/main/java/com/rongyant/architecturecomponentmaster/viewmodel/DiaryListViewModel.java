package com.rongyant.architecturecomponentmaster.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.rongyant.architecturecomponentmaster.db.DataBaseCreator;
import com.rongyant.architecturecomponentmaster.db.entity.DairyEntity;

import java.util.List;

/**
 * Created by XRY on 2017/7/21.
 */
@SuppressWarnings("unchecked")
public class DiaryListViewModel extends AndroidViewModel {
    public static final MutableLiveData ABSENT = new MutableLiveData();
    {
        ABSENT.setValue(null);
    }

    private int userId;

    private LiveData<List<DairyEntity>> mObservableDiaries;

    public DiaryListViewModel(Application application, int userId) {
        super(application);
        this.userId = userId;

        final DataBaseCreator dataBaseCreator = DataBaseCreator.getInstance();

        mObservableDiaries = Transformations.switchMap(dataBaseCreator.isDatabaseCreated(), new Function<Boolean, LiveData<List<DairyEntity>>>() {
            @Override
            public LiveData<List<DairyEntity>> apply(Boolean isDbCreated) {
                if (!isDbCreated) {
                    return ABSENT;
                } else {
                    return dataBaseCreator.getDatabase().dairyDao().loadAll(DiaryListViewModel.this.userId);
                }
            }
        });

        dataBaseCreator.createDb(this.getApplication());
    }

    LiveData<List<DairyEntity>> getDiaries() {
        return mObservableDiaries;
    }
}
