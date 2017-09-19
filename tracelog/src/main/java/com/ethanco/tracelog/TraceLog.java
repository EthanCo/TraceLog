package com.ethanco.tracelog;

import com.ethanco.logbase.Trace;
import com.ethanco.tracelog.logs.DefaultLog;
import com.ethanco.tracelog.printer.LogPrinter;

import java.util.List;

/**
 * @Description TranceLog
 * Created by EthanCo on 2016/11/1.
 */

public class TraceLog {
    private List<Trace> logList;
    private String tag;

    LogPrinter logPrinter = new LogPrinter();

    public TraceLog() {
    }

    private static Trace defaultLog;

    public static Trace defaultLog() {
        if (defaultLog == null) {
            defaultLog = new DefaultLog();
        }
        return defaultLog;
    }

    public void addTrace(Trace trace) {
        logPrinter.addAdapter(trace);
    }


    public void v(String tag, String message) {
        logPrinter.t(tag).v(message);
    }


    public void d(String tag, String message) {
        logPrinter.t(tag).d(message);
    }


    public void i(String tag, String message) {
        logPrinter.t(tag).i(message);
    }


    public void w(String tag, String message) {
        logPrinter.t(tag).w(message);
    }


    public void e(String tag, String message) {
        logPrinter.t(tag).e(message);
    }


    public void json(String message) {
        logPrinter.json(message);
    }


    public void xml(String message) {
        logPrinter.xml(message);
    }

    public void v(String message) {
        logPrinter.v(message);
    }


    public void d(String message) {
        logPrinter.e(message);
    }


    public void i(String message) {
        logPrinter.i(message);
    }


    public void w(String message) {
        logPrinter.w(message);
    }


    public void e(String message) {
        logPrinter.e(message);
    }
}
