package com.neufmer.ygfstore.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.neufmer.ygfstore.db.converter.ContentConverter;
import com.neufmer.ygfstore.db.dao.ContentParamDao;
import com.neufmer.ygfstore.db.dao.SubmitParamDao;
import com.neufmer.ygfstore.db.entity.ContentParamEntity;
import com.neufmer.ygfstore.db.entity.SubmitParamEntity;
import com.wangxing.code.mvvm.utils.ContextUtils;

/**
 * Created by WangXing on 2019/7/12.
 */
@Database(entities = {SubmitParamEntity.class, ContentParamEntity.class}, version = 1)
@TypeConverters({ContentConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase sInstance;

    public abstract SubmitParamDao SubmitParamDao();

    public abstract ContentParamDao contentParamDao();

    public static AppDatabase getDatabase() {

        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(ContextUtils.getContext(),
                            AppDatabase.class, "YGFStore.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return sInstance;
    }


    public static void onDestroy() {
        sInstance = null;
    }

}
