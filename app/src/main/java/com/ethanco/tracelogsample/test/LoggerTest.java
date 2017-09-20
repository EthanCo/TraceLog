package com.ethanco.tracelogsample.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelog.logs.DiskLogTrace;
import com.ethanco.tracelogsample.App;
import com.ethanco.tracelogsample.DataHelper;
import com.ethanco.tracelogsample.model.Child;
import com.ethanco.tracelogsample.model.FakeBounty;
import com.ethanco.tracelogsample.model.Man;
import com.ethanco.tracelogsample.model.MulObject;
import com.ethanco.tracelogsample.model.Person;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * LoggerTrace test
 *
 * @author EthanCo
 * @since 2017/9/20
 */

public class LoggerTest {
    public static void test(){
        Context context = App.getInstance();
        TraceLog traceLog = new TraceLog.Builder()
                //.addTrace(TraceLog.defaultTrace())
                .setDefaultTag("LoggerTest")
                .addTrace(App.getInstance().loggerTrace)
                .addTrace(new DiskLogTrace(context))
                .build();

        traceLog.v(DataHelper.getObject());
        traceLog.d(DataHelper.getObject());
        traceLog.i(DataHelper.getObject());
        traceLog.w(DataHelper.getObject());
        traceLog.e(DataHelper.getObject());

        traceLog.d("12345");
        traceLog.d("12%s3%s45", "a", "b");
        traceLog.d(new NullPointerException("12345"));
        traceLog.d(DataHelper.getIntArray());
        traceLog.d(DataHelper.getObject());
        traceLog.d(null);

        traceLog.json(DataHelper.getJson());

        // 打印List
        traceLog.d(DataHelper.getStringList());

        // 支持数据集合
        traceLog.d(DataHelper.getObjectList());

        // 支持map输出
        traceLog.d(DataHelper.getObjectMap());

        // Bundle支持
        Bundle bundle = new Bundle();
        bundle.putInt("abc", 1);
        bundle.putString("abc2", "text");
        bundle.putSerializable("abc3", DataHelper.getObject());
        bundle.putStringArray("abc4", DataHelper.getStringArray());
        bundle.putSerializable("abc7", DataHelper.getStringArray2());
        bundle.putSerializable("abc8", DataHelper.getStringArray3());
        bundle.putSerializable("abc5", DataHelper.getObject());
        bundle.putSerializable("abc6", DataHelper.getObjectArray());
        bundle.putSerializable("abc9", DataHelper.getStringMap());
        bundle.putSerializable("abc10", DataHelper.getBigString(context));
        traceLog.d(bundle);


        // 对象测试
        traceLog.d(DataHelper.getMan());
        traceLog.d(DataHelper.getObject());
        traceLog.d(DataHelper.getOldMan());


        // 大文本输出
        traceLog.d(DataHelper.getBigString(context));

        // Intent测试
        Intent it = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        it.putExtra("aaaa", "12345");
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        traceLog.d(it);

        Person p = DataHelper.getObject();
        WeakReference<Person> wp = new WeakReference<Person>(p);
        traceLog.d(wp);

        SoftReference<Person> sr = new SoftReference<Person>(p);
        traceLog.d(sr);

        List<WeakReference> l = new ArrayList<>();
        l.add(wp);
        l.add(wp);
        l.add(wp);
        traceLog.d(l);

        Child<Man> child = new Child<>("张三");
        child.setParent(DataHelper.getMan());
        //traceLog.d(child);

        traceLog.d(new MulObject(5));


        //traceLog.d(DataHelper.getNode("左结点", "右结点"));

        traceLog.d("12345678910");


        traceLog.t("lalal").xml(DataHelper.getXml());

        traceLog.t("lalal").d(new FakeBounty());

        traceLog.t("lalal").d("99999999");

        // 测试Message
        Message message = new Message();
        message.setData(new Bundle());
        message.obj = new Man(20);
        message.arg1 = 1;
        message.arg2 = 2;
        message.what = 1232;
        traceLog.d(message);

        Handler handler = new Handler() {

        };
        traceLog.d(handler.obtainMessage(1));

        traceLog.e("test 12345#");
    }
}
