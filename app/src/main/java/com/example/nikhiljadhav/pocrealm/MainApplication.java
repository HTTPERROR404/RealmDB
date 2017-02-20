package com.example.nikhiljadhav.pocrealm;

import android.app.Application;
import android.content.Context;

/**
 * Created by nikhil.jadhav on 2/7/2017.
 */

public class MainApplication extends Application {

    private static Context mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext(){
        return mContext;
    }
}
