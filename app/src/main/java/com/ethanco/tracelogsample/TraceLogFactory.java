package com.ethanco.tracelogsample;

import android.app.Application;

import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelog.logs.LocalRecordLog;


/**
 * @Description TranceLog 工厂
 * Created by EthanCo on 2016/11/1.
 */

public class TraceLogFactory {

    enum Type {
        DEFAULT, //默认
        LOCAL,   //保存日志至本地
        BUGLY    //腾讯Bugly
    }

    private static Application context;
    private static TraceLog defaultLog;
    private static TraceLog localLog;
    private static TraceLog buglyLog;

    public static TraceLog create(Type type) {
        switch (type) {
            case LOCAL:
                return createLocalLog(context);
            case BUGLY:
                return createBuglyLog();
            case DEFAULT:
            default:
                return createDefaultLog();
        }
    }

    public static void init(Application _context) {
        context = _context;
    }

    private static TraceLog createDefaultLog() {
        if (defaultLog == null) {
            defaultLog = new TraceLog.Builder().addLog(TraceLog.defaultLog()).build();
        }
        return defaultLog;
    }

    private static TraceLog createLocalLog(Application context) {
        if (localLog == null) {
            LocalRecordLog recordLog = new LocalRecordLog(context,
                    1024 * 1024 * 10, "MyFolder", "MyFileName");
            localLog = new TraceLog.Builder() //对于某些实现了IInit接口的ILog实现类，需要传入Context
                    //.addLog(new DefaultLog())    //默认Log
                    .addLog(recordLog) //日志保存至本地文件
                    .setDefaultTag("MyTag")
                    .setEnable(BuildConfig.DEBUG)
                    .build();
        }
        return localLog;
    }

    private static TraceLog createBuglyLog() {
        if (buglyLog == null) {
            buglyLog = new TraceLog.Builder().addLog(new BuglyLog()).build(); //腾讯Bugly 日志上报封装
        }

        return buglyLog;
    }
}
