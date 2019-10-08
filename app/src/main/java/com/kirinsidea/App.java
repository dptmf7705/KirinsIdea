package com.kirinsidea;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static App INSTANCE;

    public static App instance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public Context getContext() {
        return getApplicationContext();
    }
}
