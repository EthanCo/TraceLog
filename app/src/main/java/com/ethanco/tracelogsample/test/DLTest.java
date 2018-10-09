package com.ethanco.tracelogsample.test;

import android.content.Context;

import com.ethanco.loggerex.LoggerTrace;
import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelogsample.App;
import com.ethanco.tracelogsample.DataHelper;
import com.lib.utils.print.DL;

/**
 * DL.java 测试
 *
 * @author EthanCo
 * @since 2017/9/20
 */

public class DLTest {
    public static final String TAG = "Z-LTest";

    public static void test(Context context) {
        /*LoggerTrace loggerTrace = App.getInstance().loggerTrace;
        TraceLog traceLog = new TraceLog.Builder()
                //.addTrace(TraceLog.defaultTrace()) 默认日志
                .addTrace(loggerTrace) //Logger日志，正式使用methodOffset应为6，这里为兼容其他测试，设置的是5
                //.addTrace(new LTrace())
                .build();
        DL.init(traceLog);*/

        DL.init(context);

        DL.v("vvvvvvvv");
        DL.d("dddddddd");
        DL.i("iiiiiiii");
        DL.w("wwwwwwww");
        DL.e("eeeeeeee");
        DL.json(DataHelper.getJson());
        DL.xml(DataHelper.getXml());
        DL.v(DataHelper.getObject());
        DL.d(DataHelper.getObject());
        DL.i(DataHelper.getObject());
        DL.w(DataHelper.getObject());
        DL.e(DataHelper.getObject());

        DL.v(TAG, "vvvvvvvv");
        DL.d(TAG, "dddddddd");
        DL.i(TAG, "iiiiiiii");
        DL.w(TAG, "wwwwwwww");
        DL.e(TAG, "eeeeeeee");
        DL.json(TAG, DataHelper.getJson());
        DL.xml(TAG, DataHelper.getXml());

        DL.t(TAG).v("2vvvvvvvv");
        DL.t(TAG).d("2dddddddd");
        DL.t(TAG).i("2iiiiiiii");
        DL.t(TAG).w("2wwwwwwww");
        DL.t(TAG).e("2eeeeeeee");

        DL.t(TAG).v(DataHelper.getObject());
        DL.t(TAG).d(DataHelper.getObject());
        DL.t(TAG).i(DataHelper.getObject());
        DL.t(TAG).w(DataHelper.getObject());
        DL.t(TAG).e(DataHelper.getObject());
    }

    public static void test2Hierarchy() {
        LoggerTrace loggerTrace = App.getInstance().loggerTrace;
        TraceLog traceLog = new TraceLog.Builder()
                //.addTrace(TraceLog.defaultTrace()) 默认日志
                .addTrace(loggerTrace) //Logger日志，正式使用methodOffset应为6，这里为兼容其他测试，设置的是5
                .build();
        DL.init(traceLog);

        DL.i("test2Hierarchy-->1");
        DL.i(TAG, "test2Hierarchy-->2");
        DL.t(TAG).i("test2Hierarchy-->3");
        DL.t(TAG).i(DataHelper.getObject());
        DL.i(DataHelper.getObject());
    }
}
