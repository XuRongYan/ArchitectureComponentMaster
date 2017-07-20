package com.rongyant.architecturecomponentmaster;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.rongyant.architecturecomponentmaster.db.AppDataBase;
import com.rongyant.architecturecomponentmaster.db.dao.UserDao;
import com.rongyant.architecturecomponentmaster.db.entity.UserEntity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;


/**
 * Created by XRY on 2017/7/20.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleEntityTest {
    private AppDataBase database;
    private UserDao userDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDataBase.class).build();
        userDao = database.userDao();

    }

    @After
    public void closeDb() throws IOException{
        database.close();
    }

    @Test
    public void writeUserAndRead() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setUserName("test");
        userEntity.setPsw("psw");
        userDao.insertUser(userEntity);
        UserEntity userEntity1 = userDao.loadUserSync(1);
        Assert.assertThat(userEntity1, equalTo(userEntity));
    }
}
