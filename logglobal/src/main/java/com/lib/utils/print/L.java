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
        traceLog.e(tr, msg);
    }

    public static void w(String msg) {
        traceLog.w(msg);
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

    public static void v(Object object) {
        traceLog.v(object);
    }

    public static void i(Object object) {
        traceLog.i(object);
    }

    public static void d(Object object) {
        traceLog.d(object);
    }

    public static void w(Object object) {
        traceLog.w(object);
    }

    public static void e(Object object) {
        traceLog.e(object);
    }

    public static TraceLog t(String tag) {
        return traceLog.t(tag);
    }


    //---------------------------- 其他(兼容原先) ---------------------------------------

    public static void e(String tag, String msg) {
        traceLog.t(tag).e(msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        traceLog.t(tag).e(tr, msg);
    }

    public static void w(String tag, String msg) {
        traceLog.t(tag).w(msg);
    }

    public static void i(String tag, String msg) {
        traceLog.t(tag).i(msg);
    }

    public static void d(String tag, String msg) {
        traceLog.t(tag).d(msg);
    }

    public static void v(String tag, String msg) {
        traceLog.t(tag).v(msg);
    }

    public static void json(String tag, String json) {
        traceLog.t(tag).json(json);
    }

    public static void xml(String tag, String xml) {
        traceLog.t(tag).xml(xml);
    }

    public static void v(String tag, Object object) {
        traceLog.t(tag).v(object);
    }

    public static void i(String tag, Object object) {
        traceLog.t(tag).i(object);
    }

    public static void d(String tag, Object object) {
        traceLog.t(tag).d(object);
    }

    public static void w(String tag, Object object) {
        traceLog.t(tag).w(object);
    }

    public static void e(String tag, Object object) {
        traceLog.t(tag).e(object);
    }
}
