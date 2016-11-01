package com.ethanco.tracelogsample;

import com.ethanco.tracelog.abs.ILog;

/**
 * @Description 腾讯Bugly 日志上报
 * Created by EthanCo on 2016/11/1.
 */

public class BuglyLog implements ILog {
    @Override
    public void v(String tag, String message) {
        //需引用Bugly后，取消注释
        //BuglyLog.v(tag, message);
    }

    @Override
    public void d(String tag, String message) {
        //BuglyLog.d(tag, message);
    }

    @Override
    public void i(String tag, String message) {
        //BuglyLog.i(tag, message);
    }

    @Override
    public void w(String tag, String message) {
        //BuglyLog.w(tag, message);
    }

    @Override
    public void e(String tag, String message) {
        //BuglyLog.e(tag, message);
    }

    @Override
    public void postCatchedException(Exception e) {
        //CrashReport.postCatchedException(e);
    }
}
