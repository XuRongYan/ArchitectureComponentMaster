package com.rongyant.architecturecomponentmaster.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.rongyant.architecturecomponentmaster.db.entity.UserEntity;

import java.util.List;

/**
 * Created by XRY on 2017/7/19.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM users where userId = :userId")
    LiveData<UserEntity> loadUser(int userId);

    @Query("SELECT * FROM users where userId = :userId")
    UserEntity loadUserSync(int userId);

    @Query("SELECT * FROM users")
    List<UserEntity> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity user);

    @Delete
    void deleteUser(UserEntity user);
}
