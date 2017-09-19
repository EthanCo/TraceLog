package com.ethanco.tracelog.logs;

import android.util.Log;

import com.ethanco.logbase.Trace;

/**
 * 基础日志
 *
 * @author EthanCo
 * @since 2017/9/19
 */

public class DefaultLog implements Trace {
    private static final int MAX_LOG_LENGTH = 4000;

    @Override
    public boolean isLoggable(String tag, int priority) {
        return true;
    }

    @Override
    public void log(int priority, String tag, String message) {
        if (message.length() < MAX_LOG_LENGTH) {
            if (priority == Log.ASSERT) {
                Log.wtf(tag, message);
            } else {
                Log.println(priority, tag, message);
            }
            return;
        }

        // Split by line, then ensure each line can fit into Log's maximum length.
        for (int i = 0, length = message.length(); i < length; i++) {
            int newline = message.indexOf('\n', i);
            newline = newline != -1 ? newline : length;
            do {
                int end = Math.min(newline, i + MAX_LOG_LENGTH);
                String part = message.substring(i, end);
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, part);
                } else {
                    Log.println(priority, tag, part);
                }
                i = end;
            } while (i < newline);
        }
    }
}
