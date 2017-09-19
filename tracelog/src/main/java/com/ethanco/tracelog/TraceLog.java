package com.ethanco.tracelog;

import com.ethanco.logbase.Printer;
import com.ethanco.logbase.Trace;
import com.ethanco.tracelog.logs.DefaultLog;
import com.ethanco.tracelog.printer.TraceLogImpl;

/**
 * @Description TranceLog
 * Created by EthanCo on 2016/11/1.
 */

public class TraceLog implements Printer {
    private TraceLogImpl impl = new TraceLogImpl();

    private TraceLog() {
    }

    private static Trace defaultTraces;

    public static Trace defaultTrace() {
        if (defaultTraces == null) {
            defaultTraces = new DefaultLog();
        }
        return defaultTraces;
    }

    public static TraceLog create() {
        return new TraceLog();
    }

    @Override
    public Printer setDefaultTag(String tag) {
        impl.setDefaultTag(tag);
        return this;
    }

    @Override
    public TraceLog addTrace(Trace trace) {
        impl.addTrace(trace);
        return this;
    }

    @Override
    public Printer t(String tag) {
        return impl.t(tag);
    }

    @Override
    public void d(String message, Object... args) {
        impl.d(message, args);
    }

    @Override
    public void d(Object object) {
        impl.d(object);
    }

    @Override
    public void e(String message, Object... args) {
        impl.e(message, args);
    }

    @Override
    public void e(Throwable throwable, String message, Object... args) {
        impl.e(throwable, message, args);
    }

    @Override
    public void w(String message, Object... args) {
        impl.w(message, args);
    }

    @Override
    public void i(String message, Object... args) {
        impl.i(message, args);
    }

    @Override
    public void v(String message, Object... args) {
        impl.v(message, args);
    }

    @Override
    public void wtf(String message, Object... args) {
        impl.wtf(message, args);
    }

    @Override
    public void json(String json) {
        impl.json(json);
    }

    @Override
    public void xml(String xml) {
        impl.xml(xml);
    }

    @Override
    public void log(int priority, String tag, String message, Throwable throwable) {
        impl.log(priority, tag, message, throwable);
    }

    @Override
    public TraceLog clearTraces() {
        impl.clearTraces();
        return this;
    }
}
