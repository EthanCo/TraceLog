package com.ethanco.tracelogsample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ethanco.tracelog.TraceLog;
import com.ethanco.tracelog.logs.DiskLogTrace;
import com.ethanco.tracelogsample.databinding.ActivityMainBinding;
import com.ethanco.tracelogsample.test.LTest;
import com.ethanco.tracelogsample.test.LoggerTest;


public class MainActivity extends AppCompatActivity {
    TraceLog traceLog = new TraceLog.Builder()
            //.addTrace(TraceLog.defaultTrace())
            .setDefaultTag("DefaultTag")
            .addTrace(App.getInstance().loggerTrace)
            .addTrace(new DiskLogTrace(App.getInstance()))
            .build();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        traceLog.i("onCreate");

        binding.btnPrintLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                traceLog.i("Life is a journey.");
            }
        });

        binding.btnLoggerTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoggerTest.test();
            }
        });
        binding.btnLTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LTest.test();
            }
        });

    }
}
