package com.ethanco.logproxy;

import com.ethanco.logbase.Trace;

/**
 * 日志代理类
 */
public class LogProxy implements Trace {
    private Trace log = new EmptyLogTrace();
    private boolean debug = true;

    public void setLog(Trace trace) {
        log = trace;
    }

    public void setDebug(boolean _debug) {
        debug = _debug;
    }

    @Override
    public boolean isLoggable(String tag, int priority) {
        return debug;
    }

    @Override
    public void log(int priority, String tag, String message) {
        log.log(priority, tag, message);
    }
}

