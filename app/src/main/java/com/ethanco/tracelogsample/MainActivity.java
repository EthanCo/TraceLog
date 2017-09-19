package com.ethanco.tracelogsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelog.logs.DefaultLog;
import com.ethanco.tracelog.logs.LocalRecordLog;


public class MainActivity extends AppCompatActivity {


    TraceLog traceLog = new TraceLog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        traceLog.addTrace(new DefaultLog());
        traceLog.addTrace(new LocalRecordLog(this));
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
