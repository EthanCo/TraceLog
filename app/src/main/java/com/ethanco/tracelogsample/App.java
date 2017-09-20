package com.ethanco.tracelogsample;

import android.app.Application;

import com.ethanco.loggerex.LoggerTrace;

/**
 * Created by EthanCo on 2016/11/1.
 */

public class App extends Application {
    private static App sInstance;
    public LoggerTrace loggerTrace;

    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        loggerTrace = (new LoggerTrace(3, 5));
    }
}
