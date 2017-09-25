package com.starwarsgo.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by jsantini on 25/09/17.
 */

public class StarWarsGOApplication extends Application {
    private static Context mContext;
    private static StarWarsGOApplication mInstance;

    public static Context getAppContext() {
        return mContext;
    }

    public static synchronized StarWarsGOApplication getInstance() {
        return mInstance;
    }

    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mInstance = this;
    }
}
