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

    public static void test() {
        LoggerTrace loggerTrace = App.getInstance().loggerTrace;
        TraceLog traceLog = new TraceLog.Builder()
                //.addTrace(TraceLog.defaultTrace()) 默认日志
                .addTrace(loggerTrace) //Logger日志，正式使用methodOffset应为6，这里为兼容其他测试，设置的是5
                .build();
        L.init(traceLog);

        L.v("vvvvvvvv");
        L.d("dddddddd");
        L.i("iiiiiiii");
        L.w("wwwwwwww");
        L.e("eeeeeeee");
        L.json(DataHelper.getJson());
        L.xml(DataHelper.getXml());

        L.v(TAG, "vvvvvvvv");
        L.d(TAG, "dddddddd");
        L.i(TAG, "iiiiiiii");
        L.w(TAG, "wwwwwwww");
        L.e(TAG, "eeeeeeee");
        L.json(TAG, DataHelper.getJson());
        L.xml(TAG, DataHelper.getXml());
    }
}
