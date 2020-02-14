package com.ethanco.tracelog.logs;

import com.ethanco.logbase.Trace;
import com.ethanco.tracelog.utils.TranceFormat;

/**
 * 可以定位到第几行的日志
 * Created by Heiko on 2020/2/14.
 */

public class IndexLog implements Trace {
    private DefaultLog defaultLog = new DefaultLog();
    private int stackIndex = 10;

    public IndexLog() {
    }

    public IndexLog(int stackIndex) {
        this.stackIndex = stackIndex;
    }

    @Override
    public boolean isLoggable(String tag, int priority) {
        return true;
    }

    @Override
    public void log(int priority, String tag, String message) {
        String result = TranceFormat.getIndexMessage(stackIndex, message);
        defaultLog.log(priority, tag, result);
    }
}
