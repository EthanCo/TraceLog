package com.ethanco.trancelog;

import android.app.Application;
import android.content.Context;

import com.ethanco.trancelog.abs.IInit;
import com.ethanco.trancelog.abs.ILog;
import com.ethanco.trancelog.abs.IRecord;
import com.ethanco.trancelog.abs.ISimpleLog;
import com.ethanco.trancelog.logs.DefaultLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TranceLog
 * Created by EthanCo on 2016/11/1.
 */

public class TraceLog implements ILog, ISimpleLog {
    private List<ILog> logList;
    private String tag;

    public TraceLog() {
        this(new Builder());
    }

    public TraceLog(Context context) {
        this(new Builder(context));
    }

    public TraceLog(Builder builder) {
        if (builder.enable) {
            this.logList = builder.logList;
        } else {
            this.logList = new ArrayList<>();
        }
        this.tag = builder.tag;

        for (ILog log : logList) {
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
        for (ILog log : logList) {
            log.v(tag, message);
        }
    }

    @Override
    public void d(String tag, String message) {
        for (ILog log : logList) {
            log.d(tag, message);
        }
    }

    @Override
    public void i(String tag, String message) {
        for (ILog log : logList) {
            log.i(tag, message);
        }
    }

    @Override
    public void w(String tag, String message) {
        for (ILog log : logList) {
            log.w(tag, message);
        }
    }

    @Override
    public void e(String tag, String message) {
        for (ILog log : logList) {
            log.e(tag, message);
        }
    }

    @Override
    public void postCatchedException(Exception e) {
        for (ILog log : logList) {
            log.postCatchedException(e);
        }
    }

    @Override
    public void v(String message) {
        for (ILog log : logList) {
            log.v(tag, message);
        }
    }

    @Override
    public void d(String message) {
        for (ILog log : logList) {
            log.d(tag, message);
        }
    }

    @Override
    public void i(String message) {
        for (ILog log : logList) {
            log.i(tag, message);
        }
    }

    @Override
    public void w(String message) {
        for (ILog log : logList) {
            log.w(tag, message);
        }
    }

    @Override
    public void e(String message) {
        for (ILog log : logList) {
            log.e(tag, message);
        }
    }

    public static class Builder {
        Application application;
        List<ILog> logList = new ArrayList<>();
        String tag;
        boolean enable;
        int maxFileCacheSize;
        String fileName;
        String folder;

        public Builder() {
            //default config
            this.tag = "TranceLog";
            this.enable = true;
            this.maxFileCacheSize = 1014 * 1024 * 10;
            this.fileName = "TranceLog";
            this.folder = "TranceLog";
        }

        public Builder(Context context) {
            this();
            setContext(context);
        }

        //添加ILog实现类
        public Builder addLog(ILog log) {
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
        public Builder setContext(Context context) {
            this.application = (Application) context.getApplicationContext();
            return this;
        }

        public TraceLog build() {
            if (logList.size() == 0) {
                logList.add(new DefaultLog());
            }
            return new TraceLog(this);
        }
    }
}
