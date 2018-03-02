package com.nuaskent.myscheduler;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by nasa on 2018/02/24.
 */

public class MySchedulerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // ApplicationクラスのonCreateメソッドはアプリ実効開始時にActivityよりも早く呼ばれる為ここで初期化
        Realm.init(this);
        // realmのDB各種設定を実施するインスタンスを生成
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
