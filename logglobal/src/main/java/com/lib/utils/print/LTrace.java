package com.lib.utils.print;

import android.util.Log;

import com.ethanco.logbase.Trace;

/**
 * LTrace
 *
 * @author EthanCo
 * @since 2018/5/23
 */

public class LTrace implements Trace {
    @Override
    public boolean isLoggable(String tag, int priority) {
        return true;
    }

    @Override
    public void log(int priority, String tag, String message) {
        switch (priority) {
            case Log.VERBOSE:
                LWrap.v(tag,message);
                break;
            case Log.DEBUG:
                LWrap.d(tag,message);
                break;
            case Log.INFO:
                LWrap.i(tag,message);
                break;
            case Log.WARN:
                LWrap.w(tag,message);
                break;
            case Log.ERROR:
                LWrap.e(tag,message);
                break;
            case Log.ASSERT:
                LWrap.e(tag,message);
                break;
            default:
        }
    }
}
