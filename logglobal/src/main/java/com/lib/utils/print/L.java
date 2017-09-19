package com.lib.utils.print;

import com.ethanco.tracelog.TraceLog;

/**
 * 全局Log
 */
public class L {
    private static TraceLog traceLog;

    public static void init(TraceLog _traceLog) {
        L.traceLog = _traceLog;
    }

    public static void e(String msg) {
        traceLog.e(msg);
    }

    public static void e(String msg, Throwable tr) {
        traceLog.e(msg, tr);
    }

    public static void w(String msg) {
        traceLog.w(msg);
    }

    public static void w(String msg, Throwable tr) {
        traceLog.w(msg, tr);
    }

    public static void i(String msg) {
        traceLog.i(msg);
    }

    public static void d(String msg) {
        traceLog.d(msg);
    }

    public static void v(String msg) {
        traceLog.v(msg);
    }

    public static void json(String json) {
        traceLog.json(json);
    }

    public static void xml(String xml) {
        traceLog.xml(xml);
    }

    public static void e(String tag, String msg) {
        traceLog.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        traceLog.e(tag, msg, tr);
    }

    public static void w(String tag, String msg) {
        traceLog.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable tr) {
        traceLog.w(tag, msg, tr);
    }

    public static void i(String tag, String msg) {
        traceLog.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        traceLog.d(tag, msg);
    }

    public static void v(String tag, String msg) {
        traceLog.v(tag, msg);
    }

    public static void json(String tag, String json) {
        traceLog.t(tag).json(json);
    }

    public static void xml(String tag, String xml) {
        traceLog.t(tag).xml(xml);
    }
}
