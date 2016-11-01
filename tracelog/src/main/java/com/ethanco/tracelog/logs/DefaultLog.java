package com.ethanco.tracelog.logs;

import android.util.Log;

import com.ethanco.tracelog.abs.ILog;


/**
 * @Description 默认Log
 * Created by EthanCo on 2016/11/1.
 */

public class DefaultLog implements ILog {

    @Override
    public void v(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    public void d(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void i(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    public void w(String tag, String message) {
        Log.w(tag, message);
    }

    @Override
    public void e(String tag, String message) {
        Log.e(tag, message);
    }

    @Override
    public void postCatchedException(Exception e) {
        e.printStackTrace();
    }
}
