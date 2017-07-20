package com.rongyant.architecturecomponentmaster.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rongyant.architecturecomponentmaster.db.entity.DairyEntity;

import java.util.List;

/**
 * Created by XRY on 2017/7/19.
 */

@Dao
public interface DairyDao {
    @Query("SELECT * FROM dairies where userId = :userId AND diaryId = :diaryId")
    LiveData<List<DairyEntity>> loadDairies(int userId, int diaryId);

    @Query("SELECT * FROM dairies where userId = :userId AND diaryId = :diaryId")
    DairyEntity loadDairiesSync(int userId, int diaryId);

    @Query("SELECT * FROM dairies")
    List<DairyEntity> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DairyEntity> dairyEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DairyEntity dairyEntity);

    @Update()
    void update(DairyEntity dairyEntity);

    @Update
    void updateAll(DairyEntity...dairyEntities);

}
