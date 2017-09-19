package com.ethanco.tracelogsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ethanco.loggerex.LoggerLog;
import com.ethanco.logproxy.LogProxy;
import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelog.logs.LocalRecordLog;


public class MainActivity extends AppCompatActivity {


    TraceLog defaultLog = new TraceLog.Builder().addLog(TraceLog.defaultLog()).build();

    TraceLog localLog = new TraceLog.Builder() //对于某些实现了IInit接口的ILog实现类，需要传入Context
            //.addLog(new DefaultLog())     //默认日志 和设置setBaseEnable(true)的作用相同
            .addLog(new LoggerLog())
            .addLog(new LocalRecordLog(App.getInstance(), 1024 * 1024 * 10, "MyFolder", "MyFileName")) //日志保存至本地文件
            .setDefaultTag("MyTag") //默认tag
            .build();

    TraceLog buglyLog = new TraceLog.Builder().addLog(new BuglyLog()).build(); //腾讯Bugly 日志上报封装
    private Button btnSetNotPrint;
    private Button btnSimpleLog;
    private LogProxy logProxy;
    private Button btnSetPrintLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logProxy = App.getInstance().getLogProxy();
        btnSetNotPrint = (Button) findViewById(R.id.btn_set_not_print_log);
        btnSimpleLog = (Button) findViewById(R.id.btn_simple_log);
        btnSetPrintLog = (Button) findViewById(R.id.btn_set_print_log);

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
                localLog.setEnable(false);
            }
        });

        btnSetPrintLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localLog.setEnable(true);
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
