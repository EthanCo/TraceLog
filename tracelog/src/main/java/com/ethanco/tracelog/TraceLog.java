package com.ethanco.tracelog;

import android.app.Application;

import com.ethanco.logproxy.abs.IEntireLog;
import com.ethanco.logproxy.abs.ICommonLog;
import com.ethanco.tracelog.abs.IInit;
import com.ethanco.tracelog.abs.IRecord;
import com.ethanco.tracelog.logs.DefaultLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TranceLog
 * Created by EthanCo on 2016/11/1.
 */

public class TraceLog implements IEntireLog {
    private Builder builder;
    private ICommonLog baseLog;
    private List<ICommonLog> logList;
    private String tag;
    private boolean enable;

    public TraceLog() {
        this(new Builder());
    }

    public TraceLog(Application context) {
        this(new Builder(context));
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
        if (builder.baseEnable) {
            this.baseLog = builder.baseLog;
        } else {
            this.baseLog = null;
        }

        for (ICommonLog log : logList) {
            if (log instanceof IRecord) {
                IRecord record = (IRecord) log;
                record.setFileName(builder.fileName);
                record.setFolder(builder.folder);
                record.setMaxFileCacheSize(builder.maxFileCacheSize);
            }

            if (log instanceof IInit) {
                IInit initLog = (IInit) log;
                if (builder.application == null) {
                    throw new IllegalStateException("Please set Context");
                }
                initLog.init(builder.application);
            }
        }
    }

    @Override
    public void v(String tag, String message) {
        if (baseLog != null) {
            baseLog.v(tag, message);
        }

        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.v(tag, message);
        }
    }

    @Override
    public void d(String tag, String message) {
        if (baseLog != null) {
            baseLog.d(tag, message);
        }

        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.d(tag, message);
        }
    }

    @Override
    public void i(String tag, String message) {
        if (baseLog != null) {
            baseLog.i(tag, message);
        }

        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.i(tag, message);
        }
    }

    @Override
    public void w(String tag, String message) {
        if (baseLog != null) {
            baseLog.w(tag, message);
        }

        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.w(tag, message);
        }
    }

    @Override
    public void e(String tag, String message) {
        if (baseLog != null) {
            baseLog.e(tag, message);
        }

        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.e(tag, message);
        }
    }

    @Override
    public void postCatchedException(Exception e) {
        if (baseLog != null) {
            baseLog.postCatchedException(e);
        }

        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.postCatchedException(e);
        }
    }

    @Override
    public void v(String message) {
        if (baseLog != null) {
            baseLog.v(tag, message);
        }

        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.v(tag, message);
        }
    }

    @Override
    public void d(String message) {
        if (baseLog != null) {
            baseLog.d(tag, message);
        }

        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.d(tag, message);
        }
    }

    @Override
    public void i(String message) {
        if (baseLog != null) {
            baseLog.i(tag, message);
        }

        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.i(tag, message);
        }
    }

    @Override
    public void w(String message) {
        if (baseLog != null) {
            baseLog.w(tag, message);
        }

        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.w(tag, message);
        }
    }

    @Override
    public void e(String message) {
        if (baseLog != null) {
            baseLog.e(tag, message);
        }

        if (!enable) {
            return;
        }
        for (ICommonLog log : logList) {
            log.e(tag, message);
        }
    }

    public static class Builder {
        Application application;
        List<ICommonLog> logList = new ArrayList<>();
        String tag;
        boolean enable;
        int maxFileCacheSize;
        String fileName;
        String folder;
        boolean baseEnable;
        ICommonLog baseLog;

        public Builder() {
            //default config
            this.tag = "TranceLog";
            this.enable = true;
            this.maxFileCacheSize = 1014 * 1024 * 10;
            this.fileName = "TranceLog";
            this.folder = "TranceLog";
            this.baseLog = new DefaultLog();
            this.baseEnable = true;
        }

        public Builder(Application context) {
            this();
            setContext(context);
        }

        //添加ILog实现类
        public Builder addLog(ICommonLog log) {
            if (!logList.contains(log)) {
                logList.add(log);
            }
            return this;
        }

        //设置基础Log
        public Builder setBaseLog(ICommonLog log) {
            this.baseLog = log;
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

        //设置基础日志是否打印
        public Builder setBaseEnable(boolean enable) {
            this.baseEnable = enable;
            return this;
        }

        //设置最大日志文件最大缓存大小
        public Builder setMaxFileCacheSize(int maxCacheSize) {
            this.maxFileCacheSize = maxCacheSize;
            return this;
        }

        //设置日志文件名
        public Builder setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        //设置日志文件夹
        public Builder setFolder(String folder) {
            this.folder = folder;
            return this;
        }

        //设置Context
        public Builder setContext(Application context) {
            this.application = context;
            return this;
        }

        public TraceLog build() {
            return new TraceLog(this);
        }
    }
}
