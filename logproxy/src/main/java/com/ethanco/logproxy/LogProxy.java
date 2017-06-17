package com.ethanco.logproxy;

import com.ethanco.logbase.IEntireLog;

/**
 * 日志代理类
 */
public class LogProxy implements IEntireLog {
    private IEntireLog log = new EmptyLog();
    private boolean debug = true;

    public void setLog(IEntireLog entireLog) {
        log = entireLog;
    }

    public void setDebug(boolean _debug) {
        debug = _debug;
    }

    public void v(String msg) {
        if (debug) log.v(msg);
    }

    public void d(String msg) {
        if (debug) log.d(msg);
    }

    public void i(String msg) {
        if (debug) log.i(msg);
    }

    public void w(String msg) {
        if (debug) log.w(msg);
    }

    public void e(String msg) {
        if (debug) log.e(msg);
    }

    public void v(String tag, String message) {
        if (debug) log.v(tag, message);
    }

    public void d(String tag, String message) {
        if (debug) log.d(tag, message);
    }

    public void i(String tag, String message) {
        if (debug) log.i(tag, message);
    }

    public void w(String tag, String message) {
        if (debug) log.w(tag, message);
    }

    public void e(String tag, String message) {
        if (debug) log.e(tag, message);
    }

    public void postCatchedException(Exception e) {
        if (debug) log.postCatchedException(e);
    }
}

