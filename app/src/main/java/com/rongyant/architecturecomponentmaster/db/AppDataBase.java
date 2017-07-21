package com.rongyant.architecturecomponentmaster.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.rongyant.architecturecomponentmaster.db.converter.DateConverter;
import com.rongyant.architecturecomponentmaster.db.dao.DairyDao;
import com.rongyant.architecturecomponentmaster.db.dao.UserDao;
import com.rongyant.architecturecomponentmaster.db.entity.DairyEntity;
import com.rongyant.architecturecomponentmaster.db.entity.UserEntity;

/**
 * Created by XRY on 2017/7/19.
 */
@Database(entities = {UserEntity.class, DairyEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)

public abstract class AppDataBase extends RoomDatabase {
    static final String DATABASE_NAME = "architecture-component-master-db";

    public abstract UserDao userDao();

    public abstract DairyDao dairyDao();

}
