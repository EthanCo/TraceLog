package com.lib.utils.print;

import com.ethanco.tracelog.TraceLog;

/**
 * 全局Log
 */
public class L extends BaseL{

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
}
