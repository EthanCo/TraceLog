package com.ethanco.tracelogsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelog.logs.DefaultLog;
import com.ethanco.tracelog.logs.LocalRecordLog;


public class MainActivity extends AppCompatActivity {


    TraceLog defaultLog = new TraceLog.Builder().build();

    TraceLog localLog = new TraceLog.Builder(App.getInstance()) //对于某些实现了IInit接口的ILog实现类，需要传入Context
            .addLog(new DefaultLog())     //默认日志
            .addLog(new LocalRecordLog()) //日志保存至本地文件
            .setMaxFileCacheSize(1024 * 1024 * 10) //日志文件最大缓存，以B为单位
            .setFolder("MyFolder") //日志保存文件夹，如果不设置，默认为TraceLog
            .setFileName("MyFileName") //日志文件文件名，如果不设置，默认为TraceLog
            .setDefaultTag("MyTag") //默认tag
            .setEnable(BuildConfig.DEBUG) //是否启用
            .build();

    TraceLog buglyLog = new TraceLog.Builder().addLog(new BuglyLog()).build(); //腾讯Bugly 日志上报封装

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultLog.i("Z-MainActivity", "MainActivity onCreate1");
        defaultLog.i("MainActivity onCreate2");
        localLog.i("Z-MainActivity", "MainActivity onCreate3");
        localLog.i("MainActivity onCreate4");

        //由于Bugly需要应用第三方库，故此Demo中为模拟，若真实使用，引用Bugly第三方简易库后，取消BuglyLog里的注释即可正常使用
        buglyLog.i("Z-MainActivity", "MainActivity onCreate5");
        buglyLog.i("MainActivity onCreate6");

        //对于defaultLog、customLog、buglyLog 等有多个TraceLog的情况，建议采用一个Factory进行封装
        //TraceLog newLog = TraceLogFactory.create(TraceLogFactory.Type.BUGLY);
        TraceLog newLog = TraceLogFactory.create(TraceLogFactory.Type.LOCAL);
        newLog.i("xxxxxxxxx");
    }
}
