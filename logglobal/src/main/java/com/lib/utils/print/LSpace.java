package com.lib.utils.print;

import android.content.Context;
import android.text.TextUtils;

import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelog.logs.DiskLogTrace;

/**
 * 全局Log 统一控制蓝色日志可点击行数
 */
class LSpace {
    private static TraceLog traceLog;

    public static TraceLog getTraceLog() {
        return traceLog;
    }

    public void init() {
        TraceLog traceLog = new TraceLog.Builder()
                .addTrace(new LTrace())
                .build();
        init(traceLog);
    }

    public void init(String defTag) {
        TraceLog traceLog = new TraceLog.Builder()
                .addTrace(new LTrace())
                .setDefaultTag(defTag)
                .build();
        init(traceLog);
    }

    /**
     * 初始化L日志类
     *
     * @param context
     * @param defTag 默认tag
     * @param saveToDisk 是否保存到本地
     */
    public void init(Context context, String defTag, boolean saveToDisk) {
        TraceLog.Builder builder = new TraceLog.Builder()
                .addTrace(new LTrace());
        if (!TextUtils.isEmpty(defTag)) {
            builder.setDefaultTag(defTag);
        }
        if (saveToDisk) {
            builder.addTrace(new DiskLogTrace(context));
        }
        TraceLog traceLog = builder.build();
        init(traceLog);
    }

    /**
     * 初始化L日志类，自定义配置
     *
     * @param _traceLog
     */
    public void init(TraceLog _traceLog) {
        LSpace.traceLog = _traceLog;
    }

    public void e(String msg) {
        traceLog.e(msg);
    }

    public void e(String msg, Throwable tr) {
        traceLog.e(tr, msg);
    }

    public void w(String msg) {
        traceLog.w(msg);
    }

    public void i(String msg) {
        traceLog.i(msg);
    }

    public void d(String msg) {
        traceLog.d(msg);
    }

    public void v(String msg) {
        traceLog.v(msg);
    }

    public void json(String json) {
        traceLog.json(json);
    }

    public void xml(String xml) {
        traceLog.xml(xml);
    }

    public void v(Object object) {
        traceLog.v(object);
    }

    public void i(Object object) {
        traceLog.i(object);
    }

    public void d(Object object) {
        traceLog.d(object);
    }

    public void w(Object object) {
        traceLog.w(object);
    }

    public void e(Object object) {
        traceLog.e(object);
    }

    public PrinterWrap t(String tag) {
        return getPrintWrap(traceLog, tag);
    }

    private static PrinterWrap getPrintWrap(TraceLog traceLog, String tag) {
        return new PrinterWrap(traceLog.t(tag));
    }


    //---------------------------- 其他(兼容原先) ---------------------------------------

    public void e(String tag, String msg) {
        traceLog.t(tag).e(msg);
    }

    public void e(String tag, String msg, Throwable tr) {
        traceLog.t(tag).e(tr, msg);
    }

    public void w(String tag, String msg) {
        traceLog.t(tag).w(msg);
    }

    public void i(String tag, String msg) {
        traceLog.t(tag).i(msg);
    }

    public void d(String tag, String msg) {
        traceLog.t(tag).d(msg);
    }

    public void v(String tag, String msg) {
        traceLog.t(tag).v(msg);
    }

    public void json(String tag, String json) {
        traceLog.t(tag).json(json);
    }

    public void xml(String tag, String xml) {
        traceLog.t(tag).xml(xml);
    }

    public void v(String tag, Object object) {
        traceLog.t(tag).v(object);
    }

    public void i(String tag, Object object) {
        traceLog.t(tag).i(object);
    }

    public void d(String tag, Object object) {
        traceLog.t(tag).d(object);
    }

    public void w(String tag, Object object) {
        traceLog.t(tag).w(object);
    }

    public void e(String tag, Object object) {
        traceLog.t(tag).e(object);
    }
}
