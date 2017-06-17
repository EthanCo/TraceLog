package com.ethanco.tracelogsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ethanco.logproxy.LogProxy;
import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelog.logs.LocalRecordLog;


public class MainActivity extends AppCompatActivity {


    TraceLog defaultLog = new TraceLog.Builder().build();

    TraceLog localLog = new TraceLog.Builder(App.getInstance()) //对于某些实现了IInit接口的ILog实现类，需要传入Context
            //.addLog(new DefaultLog())     //默认日志 和设置setBaseEnable(true)的作用相同
            .addLog(new LocalRecordLog()) //日志保存至本地文件
            .setMaxFileCacheSize(1024 * 1024 * 10) //日志文件最大缓存，以B为单位
            .setFolder("MyFolder") //日志保存文件夹，如果不设置，默认为TraceLog
            .setFileName("MyFileName") //日志文件文件名，如果不设置，默认为TraceLog
            .setDefaultTag("MyTag") //默认tag
            .setEnable(BuildConfig.DEBUG) //是否启用
            .setBaseEnable(true)
            .build();

    TraceLog buglyLog = new TraceLog.Builder().addLog(new BuglyLog()).build(); //腾讯Bugly 日志上报封装
    private Button btnSetNotPrint;
    private Button btnSimpleLog;
    private LogProxy logProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logProxy = App.getInstance().getLogProxy();
        btnSetNotPrint = (Button) findViewById(R.id.btn_set_not_print_log);
        btnSimpleLog = (Button) findViewById(R.id.btn_simple_log);

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

        Button btnPrintLog = (Button) findViewById(R.id.btn_print_log);
        btnPrintLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localLog.i("log log log log log log log log log log log log log log log log log log " +
                        "log log log log log log log log log log log log log log log log log log log log " +
                        "log log log log log log log log log log log log log log log log log log log log " +
                        "log log log log log log log log log log log log log log log log log log log log " +
                        "log log log log log log log log log log log log log log log log log log log log " +
                        "log log log log log log log log log log log log log log log log log log log log ");
            }
        });

        btnSetNotPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TraceLog.Builder builder = localLog.getBuilder();
                builder.setEnable(false);  //附加日志打印
                builder.setBaseEnable(false);  //基础日志打印
                localLog.setBuilder(builder);
            }
        });

        btnSimpleLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logProxy.i("hello world ! this is simple log");
            }
        });
    }
}
