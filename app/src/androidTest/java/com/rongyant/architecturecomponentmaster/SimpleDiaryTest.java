package com.rongyant.architecturecomponentmaster;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.rongyant.architecturecomponentmaster.db.AppDataBase;
import com.rongyant.architecturecomponentmaster.db.dao.DairyDao;
import com.rongyant.architecturecomponentmaster.db.dao.UserDao;
import com.rongyant.architecturecomponentmaster.db.entity.DairyEntity;
import com.rongyant.architecturecomponentmaster.db.entity.UserEntity;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by XRY on 2017/7/20.
 */

@RunWith(AndroidJUnit4.class)
public class SimpleDiaryTest {
    private AppDataBase dataBase;
    private UserDao userDao;
    private DairyDao dairyDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext().getApplicationContext();
        dataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase.class).build();
        userDao = dataBase.userDao();
        dairyDao = dataBase.dairyDao();
    }

    @After
    public void closeDb() {
        dataBase.close();
    }

    @Test
    public void test() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setUserName("test");
        userEntity.setPsw("test");
        userDao.insertUser(userEntity);
        DairyEntity entity1 = new DairyEntity();
        entity1.setUserId(1);
        entity1.setDiaryId(1);
        entity1.setDairyContent("test test1");
        entity1.setDate(new Date(System.currentTimeMillis()));

        DairyEntity entity2 = new DairyEntity();
        entity2.setUserId(1);
        entity2.setDiaryId(2);
        entity2.setDairyContent("test test2");
        entity2.setDate(new Date(System.currentTimeMillis()));

        dairyDao.insert(entity1);

        List<DairyEntity> list = new ArrayList<>();
        list.add(entity1);
        list.add(entity2);

        dairyDao.insertAll(list);

        DairyEntity list1 = dairyDao.loadDairiesSync(1, 1);

        Assert.assertThat(list1, CoreMatchers.equalTo(entity1));

    }

    @Test
    public void testUpdate() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setUserName("test");
        userEntity.setPsw("test");
        userDao.insertUser(userEntity);
        DairyEntity entity1 = new DairyEntity();
        entity1.setUserId(1);
        entity1.setDiaryId(1);
        entity1.setDairyContent("test test1");
        entity1.setDate(new Date(System.currentTimeMillis()));

        DairyEntity entity2 = new DairyEntity();
        entity2.setUserId(1);
        entity2.setDiaryId(2);
        entity2.setDairyContent("test test2");
        entity2.setDate(new Date(System.currentTimeMillis()));

        dairyDao.insert(entity1);
        dairyDao.insert(entity2);

        DairyEntity entity3 = new DairyEntity();
        entity3.setUserId(1);
        entity3.setDiaryId(2);
        entity3.setDairyContent("test test666");
        entity3.setDate(new Date(System.currentTimeMillis()));
        List<DairyEntity> list2 = dairyDao.loadAll();
        for (int i = 0; i < list2.size(); i++) {
            Log.d("testDiary", list2.get(i).toString());
        }
        dairyDao.update(entity3);
        List<DairyEntity> list1 = dairyDao.loadAll();
        for (int i = 0; i < list1.size(); i++) {
            Log.d("testDiary", list1.get(i).toString());
        }
        DairyEntity list = dairyDao.loadDairiesSync(1, 2);
        Assert.assertThat(list, CoreMatchers.equalTo(entity3));
    }
}
