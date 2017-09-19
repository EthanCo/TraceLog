package com.ethanco.loggerex;

import com.ethanco.logbase.Trace;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Logger包装类
 *
 * @author EthanCo
 * @since 2017/9/18
 */

public class LoggerTrace implements Trace {

    public LoggerTrace() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    public boolean isLoggable(String tag, int priority) {
        return true;
    }

    @Override
    public void log(int priority, String tag, String message) {
        Logger.log(priority, tag, message, null);
    }
}
