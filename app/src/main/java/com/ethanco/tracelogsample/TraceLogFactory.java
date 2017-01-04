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
            defaultLog = new TraceLog.Builder().build();
        }
        return defaultLog;
    }

    private static TraceLog createLocalLog(Application context) {
        if (localLog == null) {
            localLog = new TraceLog.Builder(context) //对于某些实现了IInit接口的ILog实现类，需要传入Context
                    //.addLog(new DefaultLog())    //默认Log
                    .addLog(new LocalRecordLog()) //日志保存至本地文件
                    .setMaxFileCacheSize(1024 * 1024 * 10) //日志文件最大缓存，以B为单位
                    .setFolder("MyFolder") //日志保存文件夹，如果不设置，默认为TraceLog
                    .setFileName("MyFileName") //日志文件文件名，如果不设置，默认为TraceLog
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
