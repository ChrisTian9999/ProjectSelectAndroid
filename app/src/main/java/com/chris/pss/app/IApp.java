package com.chris.pss.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by noonecode on 2017/5/15.
 */

public class IApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }
}
