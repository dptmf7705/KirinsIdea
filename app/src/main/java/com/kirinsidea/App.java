package com.kirinsidea;

import android.app.Application;

public class App extends Application {
    private static App INSTANCE;

    public static App instance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("this application does not extends Application.class");
        }
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    @Override
    public void onTerminate() {
        INSTANCE = null;
        super.onTerminate();
    }
}
