package com.ethanco.tracelogsample.test;

import com.ethanco.loggerex.LoggerTrace;
import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelogsample.App;
import com.ethanco.tracelogsample.DataHelper;
import com.lib.utils.print.L;

/**
 * L.java 测试
 *
 * @author EthanCo
 * @since 2017/9/20
 */

public class LTest {
    public static final String TAG = "Z-LTest";
    private static boolean isInited = false;

    public static void test() {
        /*LoggerTrace loggerTrace = App.getInstance().loggerTrace;
        TraceLog traceLog = new TraceLog.Builder()
                //.addTrace(TraceLog.defaultTrace()) 默认日志
                .addTrace(loggerTrace) //Logger日志，正式使用methodOffset应为6，这里为兼容其他测试，设置的是5
                //.addTrace(new LTrace())
                .build();
        L.init(traceLog);*/

        if (!isInited) {
            L.init();
            isInited = true;
        }

        L.v("vvvvvvvv");
        L.d("dddddddd");
        L.i("iiiiiiii");
        L.w("wwwwwwww");
        L.e("eeeeeeee");
        L.json(DataHelper.getJson());
        L.xml(DataHelper.getXml());
        L.v(DataHelper.getObject());
        L.d(DataHelper.getObject());
        L.i(DataHelper.getObject());
        L.w(DataHelper.getObject());
        L.e(DataHelper.getObject());

        L.v(TAG, "vvvvvvvv");
        L.d(TAG, "dddddddd");
        L.i(TAG, "iiiiiiii");
        L.w(TAG, "wwwwwwww");
        L.e(TAG, "eeeeeeee");
        L.json(TAG, DataHelper.getJson());
        L.xml(TAG, DataHelper.getXml());

        L.t(TAG).v("2vvvvvvvv");
        L.t(TAG).d("2dddddddd");
        L.t(TAG).i("2iiiiiiii");
        L.t(TAG).w("2wwwwwwww");
        L.t(TAG).e("2eeeeeeee");

        L.t(TAG).v(DataHelper.getObject());
        L.t(TAG).d(DataHelper.getObject());
        L.t(TAG).i(DataHelper.getObject());
        L.t(TAG).w(DataHelper.getObject());
        L.t(TAG).e(DataHelper.getObject());
    }

    public static void test2Hierarchy() {
        LoggerTrace loggerTrace = App.getInstance().loggerTrace;
        TraceLog traceLog = new TraceLog.Builder()
                //.addTrace(TraceLog.defaultTrace()) 默认日志
                .addTrace(loggerTrace) //Logger日志，正式使用methodOffset应为6，这里为兼容其他测试，设置的是5
                .build();
        L.init(traceLog);

        L.i("test2Hierarchy-->1");
        L.i(TAG, "test2Hierarchy-->2");
        L.t(TAG).i("test2Hierarchy-->3");
        L.t(TAG).i(DataHelper.getObject());
        L.i(DataHelper.getObject());
    }
}
