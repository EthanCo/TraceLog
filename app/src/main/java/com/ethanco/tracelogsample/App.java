package com.ethanco.tracelogsample;

import android.app.Application;

/**
 * Created by EthanCo on 2016/11/1.
 */

public class App extends Application {
    private static App sInstance;

    public static Application getInstance(){
        return sInstance;
    }

    @Override
    public void onCreate() {
        sInstance = this;
        TraceLogFactory.init(this);
    }
}
