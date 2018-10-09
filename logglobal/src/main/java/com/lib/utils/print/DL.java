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

public class DL extends BaseL {

    public static void init(Context context) {
        TraceLog traceLog = new TraceLog.Builder()
                .addTrace(new DefaultLog())
                .addTrace(new DiskLogTrace(context))
                .build();
        L.init(traceLog);
    }

    public static void init(String defTag, Context context) {
        TraceLog traceLog = new TraceLog.Builder()
                .addTrace(new DefaultLog())
                .addTrace(new DiskLogTrace(context))
                .setDefaultTag(defTag)
                .build();
        L.init(traceLog);
    }

    public static void init(String defTag, Context context, String path) {
        TraceLog traceLog = new TraceLog.Builder()
                .addTrace(new DefaultLog())
                .addTrace(new DiskLogTrace(context, path))
                .setDefaultTag(defTag)
                .build();
        L.init(traceLog);
    }
}
