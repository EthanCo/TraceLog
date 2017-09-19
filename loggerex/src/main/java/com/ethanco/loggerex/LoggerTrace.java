package com.ethanco.loggerex;

import com.ethanco.logbase.Trace;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Logger包装类
 *
 * @author EthanCo
 * @since 2017/9/18
 */

public class LoggerTrace implements Trace {

    public LoggerTrace() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)   // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)          // (Optional) How many method line to show. Default 2
                .methodOffset(5)         // (Optional) Hides internal method calls up to offset. Default 0
                //.logStrategy()         // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("Logger")           // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
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
