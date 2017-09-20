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
        this(2, 5);
    }

    /**
     * @param methodCount  打印的方法层级
     * @param methodOffset 方法的位移
     */
    public LoggerTrace(int methodCount, int methodOffset) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)              // (Optional) Whether to show thread info or not. Default true
                .methodCount(methodCount)           // (Optional) How many method line to show. Default 2
                .methodOffset(methodOffset)         // (Optional) Hides internal method calls up to offset. Default 0
                //.logStrategy()                    // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("Logger")                      // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.clearLogAdapters();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public LoggerTrace(FormatStrategy formatStrategy) {
        Logger.clearLogAdapters();
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
