package com.lib.utils.print;

import android.content.Context;
import android.text.TextUtils;

import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelog.logs.DiskLogTrace;

/**
 * 全局Log
 */
public class L {
    private static TraceLog traceLog;


    public static void init() {
        TraceLog traceLog = new TraceLog.Builder()
                .addTrace(new LTrace())
                .build();
        L.init(traceLog);
    }

    public static void init(String defTag) {
        TraceLog traceLog = new TraceLog.Builder()
                .addTrace(new LTrace())
                .setDefaultTag(defTag)
                .build();
        L.init(traceLog);
    }

    /**
     * 初始化L日志类
     *
     * @param context
     * @param saveToDisk 是否保存到本地
     */
    public static void init(Context context, boolean saveToDisk) {
        TraceLog.Builder builder = new TraceLog.Builder()
                .addTrace(new LTrace());
        if (saveToDisk) {
            builder.addTrace(new DiskLogTrace(context));
        }
        TraceLog traceLog = builder.build();
        L.init(traceLog);
    }

    /**
     * 初始化L日志类
     *
     * @param context
     * @param defTag 默认tag
     * @param saveToDisk 是否保存到本地
     */
    public static void init(Context context, String defTag, boolean saveToDisk) {
        TraceLog.Builder builder = new TraceLog.Builder()
                .addTrace(new LTrace());
        if (!TextUtils.isEmpty(defTag)) {
            builder.setDefaultTag(defTag);
        }
        if (saveToDisk) {
            builder.addTrace(new DiskLogTrace(context));
        }
        TraceLog traceLog = builder.build();
        L.init(traceLog);
    }

    /**
     * 初始化L日志类，自定义配置
     *
     * @param _traceLog
     */
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
