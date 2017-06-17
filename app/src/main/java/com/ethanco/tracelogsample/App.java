package com.ethanco.tracelogsample;

import android.app.Application;

import com.ethanco.logproxy.LogProxy;

/**
 * Created by EthanCo on 2016/11/1.
 */

public class App extends Application {
    private static App sInstance;
    private LogProxy logProxy;

    public LogProxy getLogProxy() {
        return logProxy;
    }

    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        TraceLogFactory.init(this);

        //设置Simple Log
        logProxy = new LogProxy();
        logProxy.setLog(TraceLogFactory.create(TraceLogFactory.Type.DEFAULT));
        logProxy.setDebug(BuildConfig.DEBUG);
    }
}
