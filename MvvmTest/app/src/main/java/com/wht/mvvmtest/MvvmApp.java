package com.wht.mvvmtest;


import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.wht.mvvmtest.data.PeopleFactory;
import com.wht.mvvmtest.data.PeopleService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class MvvmApp extends MultiDexApplication {
    private PeopleService peopleService;
    public static Application sApplication;
    private Scheduler scheduler;

    private static MvvmApp get(Context context) {
        return (MvvmApp) context.getApplicationContext();
    }

    public static MvvmApp create(Context context) {
        return MvvmApp.get(context);
    }

    public  PeopleService getPeopleService() {
        if (peopleService == null) {
            peopleService = PeopleFactory.create();
        }
        return peopleService;
    }

    /**
     *
     * @return 返回用于io线程的Scheduler
     */
    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
