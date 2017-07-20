package com.rongyant.architecturecomponentmaster.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.rongyant.architecturecomponentmaster.db.AppDataBase.DATABASE_NAME;

/**
 * Created by XRY on 2017/7/20.
 */

public class DataBaseCreator {
    private static final String TAG = "DataBaseCreator";

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private AppDataBase mDb;

    private final AtomicBoolean mInitializing = new AtomicBoolean(true);


    public static DataBaseCreator getInstance() {
        return Singleton.sInstance;
    }

    private DataBaseCreator() {
    }

    public LiveData<Boolean> isDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    @Nullable
    public AppDataBase getDatabase() {
        return mDb;
    }

    /**
     * 创建数据库
     * @param context
     */
    public void createDb(Context context) {
        Log.d(TAG, "Creating db from " + Thread.currentThread().getName());

        if (!mInitializing.compareAndSet(true, false)) {
            return;
        }

        mIsDatabaseCreated.setValue(false);

        new AsyncTask<Context, Void, Void>() {

            @Override
            protected Void doInBackground(Context... params) {
                Context context = params[0].getApplicationContext();
                //重置数据库保证每次运行都有新数据
                context.deleteDatabase(DATABASE_NAME);
                AppDataBase appDataBase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDataBase.class, DATABASE_NAME)
                        .build();

               mDb = appDataBase;

                Log.d(TAG, "DB was populated in thread " + Thread.currentThread().getName());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                //在UI线程，通知观察者数据库已经建好
                mIsDatabaseCreated.setValue(true);
            }
        }.execute(context.getApplicationContext());
    }

    private static class Singleton {
        public static DataBaseCreator sInstance = new DataBaseCreator();
    }
}
