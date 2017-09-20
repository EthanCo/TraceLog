package com.ethanco.tracelog;

import android.text.TextUtils;

import com.ethanco.logbase.Config;
import com.ethanco.logbase.Printer;
import com.ethanco.logbase.Trace;
import com.ethanco.tracelog.logs.DefaultLog;
import com.ethanco.tracelog.printer.TraceLogImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TranceLog
 * Created by EthanCo on 2016/11/1.
 */

public class TraceLog implements Printer {
    private TraceLogImpl impl;

    private TraceLog(Builder builder) {
        impl = new TraceLogImpl();
        impl.setDefaultTag(builder.tag);
        for (Trace trace : builder.traces) {
            impl.addTrace(trace);
        }
    }

    private static Trace defaultTraces;

    public static Trace defaultTrace() {
        if (defaultTraces == null) {
            defaultTraces = new DefaultLog();
        }
        return defaultTraces;
    }

    @Override
    public TraceLog t(String tag) {
        impl.t(tag);
        return this;
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
    public void e(Object object) {
        impl.e(object);
    }

    @Override
    public void w(Object object) {
        impl.w(object);
    }

    @Override
    public void i(Object object) {
        impl.i(object);
    }

    @Override
    public void v(Object object) {
        impl.v(object);
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


    public static class Builder implements Config {
        private String tag;
        private List<Trace> traces = new ArrayList<>();

        public Builder() {
        }

        @Override
        public Builder setDefaultTag(String tag) {
            this.tag = tag;
            return this;
        }

        @Override
        public Builder addTrace(Trace trace) {
            traces.add(trace);
            return this;
        }

        public TraceLog build() {
            if (TextUtils.isEmpty(tag)) {
                tag = "TraceLog";
            }
            if (traces.size() == 0) {
                traces.add(new DefaultLog());
            }
            TraceLog traceLog = new TraceLog(this);
            return traceLog;
        }
    }
}
