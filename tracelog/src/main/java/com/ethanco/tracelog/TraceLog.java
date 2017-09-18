package com.ethanco.tracelog;

import com.ethanco.logbase.ICommonLog;
import com.ethanco.logbase.IEntireLog;
import com.ethanco.tracelog.abs.IInit;
import com.ethanco.tracelog.logs.DefaultLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TranceLog
 * Created by EthanCo on 2016/11/1.
 */

public class TraceLog implements IEntireLog {
    private Builder builder;
    private List<ICommonLog> logList;
    private String tag;
    private boolean enable;

    public TraceLog() {
        this(new Builder());
    }

    public TraceLog(Builder builder) {
        setBuilder(builder);
    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
        this.logList = builder.logList;
        this.enable = builder.enable;
        this.tag = builder.tag;

        for (ICommonLog log : logList) {
            if (log instanceof IInit) {
                IInit initLog = (IInit) log;
                initLog.init();
            }
        }
    }

    private static ICommonLog defaultLog;

    public static ICommonLog defaultLog() {
        if (defaultLog == null) {
            defaultLog = new DefaultLog();
        }
        return defaultLog;
    }

    @Override
    public void v(String tag, String message) {
        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.v(tag, message);
        }
    }

    @Override
    public void d(String tag, String message) {
        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.d(tag, message);
        }
    }

    @Override
    public void i(String tag, String message) {
        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.i(tag, message);
        }
    }

    @Override
    public void w(String tag, String message) {
        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.w(tag, message);
        }
    }

    @Override
    public void e(String tag, String message) {
        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.e(tag, message);
        }
    }

    @Override
    public void postCatchedException(Exception e) {
        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.postCatchedException(e);
        }
    }

    @Override
    public void v(String message) {
        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.v(tag, message);
        }
    }

    @Override
    public void d(String message) {
        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.d(tag, message);
        }
    }

    @Override
    public void i(String message) {
        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.i(tag, message);
        }
    }

    @Override
    public void w(String message) {
        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.w(tag, message);
        }
    }

    @Override
    public void e(String message) {
        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.e(tag, message);
        }
    }

    public static class Builder {
        List<ICommonLog> logList = new ArrayList<>();
        String tag;
        boolean enable;

        public Builder() {
            //default config
            this.tag = "TranceLog";
            this.enable = true;
        }

        //添加ILog实现类
        public Builder addLog(ICommonLog log) {
            if (!logList.contains(log)) {
                logList.add(log);
            }
            return this;
        }

        //设置默认Tag
        public Builder setDefaultTag(String tag) {
            this.tag = tag;
            return this;
        }

        //是否启用日志打印
        public Builder setEnable(boolean enable) {
            this.enable = enable;
            return this;
        }

        public TraceLog build() {
            return new TraceLog(this);
        }
    }
}
