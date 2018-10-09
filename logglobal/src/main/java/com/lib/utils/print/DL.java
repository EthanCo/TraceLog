package com.lib.utils.print;

import android.content.Context;

import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelog.logs.DefaultLog;
import com.ethanco.tracelog.logs.DiskLogTrace;

/**
 * 日志存储到本地的全局Log
 *
 * @author EthanCo
 * @since 2018/10/9
 */

public class DL {
    protected static TraceLog traceLog;

    public static void init(Context context) {
        TraceLog traceLog = new TraceLog.Builder()
                .addTrace(new DefaultLog())
                .addTrace(new DiskLogTrace(context))
                .build();
        init(traceLog);
    }

    public static void init(String defTag, Context context) {
        TraceLog traceLog = new TraceLog.Builder()
                .addTrace(new DefaultLog())
                .addTrace(new DiskLogTrace(context))
                .setDefaultTag(defTag)
                .build();
        init(traceLog);
    }

    public static void init(String defTag, Context context, String path) {
        TraceLog traceLog = new TraceLog.Builder()
                .addTrace(new DefaultLog())
                .addTrace(new DiskLogTrace(context, path))
                .setDefaultTag(defTag)
                .build();
        init(traceLog);
    }

    /**
     * 初始化DL日志类，自定义配置
     *
     * @param _traceLog
     */
    public static void init(TraceLog _traceLog) {
        traceLog = _traceLog;
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
        traceLog.json("\r\n" + json);
    }

    public static void xml(String xml) {
        traceLog.xml("\r\n" + xml);
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

    public static PrinterWrap t(String tag) {
        return getPrintWrap(traceLog, tag);
    }

    private static PrinterWrap getPrintWrap(TraceLog traceLog, String tag) {
        return new PrinterWrap(traceLog.t(tag));
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
        traceLog.t(tag).json("\r\n" + json);
    }

    public static void xml(String tag, String xml) {
        traceLog.t(tag).xml("\r\n" + xml);
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
