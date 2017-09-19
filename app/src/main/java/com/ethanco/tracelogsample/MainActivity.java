package com.ethanco.tracelogsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ethanco.loggerex.LoggerTrace;
import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelog.logs.DiskLogTrace;


public class MainActivity extends AppCompatActivity {
    TraceLog traceLog = new TraceLog.Builder()
            .addTrace(TraceLog.defaultTrace())
            .setDefaultTag("Zhk")
            .addTrace(new LoggerTrace())
            .addTrace(new DiskLogTrace(App.getInstance()))
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        traceLog.i("Z-MainActivity", "MainActivity onCreate1");

        Button btnPrintLog = (Button) findViewById(R.id.btn_print_log);
        btnPrintLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                traceLog.i("Life is a journey. What we should care about is not where it's headed but what we see and how we feel.");
            }
        });
    }
}
