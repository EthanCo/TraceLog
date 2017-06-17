package com.ethanco.logproxy;

import com.ethanco.logbase.IEntireLog;

/**
 * 简易日志工具类
 */
public class L {
    private static IEntireLog log = new EmptyLog();
    private static boolean debug = true;

    public static void setLog(IEntireLog ICommonLog) {
        log = ICommonLog;
    }

    public static void setDebug(boolean _debug) {
        debug = _debug;
    }

    public static void v(String msg) {
        if (debug) log.v(msg);
    }

    public static void d(String msg) {
        if (debug) log.d(msg);
    }

    public static void i(String msg) {
        if (debug) log.i(msg);
    }

    public static void w(String msg) {
        if (debug) log.w(msg);
    }

    public static void e(String msg) {
        if (debug) log.e(msg);
    }

    public static void v(String tag, String message) {
        if (debug) log.v(tag, message);
    }

    public static void d(String tag, String message) {
        if (debug) log.d(tag, message);
    }

    public static void i(String tag, String message) {
        if (debug) log.i(tag, message);
    }

    public static void w(String tag, String message) {
        if (debug) log.w(tag, message);
    }

    public static void e(String tag, String message) {
        if (debug) log.e(tag, message);
    }

    public static void postCatchedException(Exception e) {
        if (debug) log.e("Exception>>>:" + e.getMessage());
    }
}

