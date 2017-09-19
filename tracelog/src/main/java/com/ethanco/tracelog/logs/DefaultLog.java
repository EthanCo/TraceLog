package com.ethanco.tracelog.logs;

import android.util.Log;

import com.ethanco.logbase.Trace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基础日志
 *
 * @author EthanCo
 * @since 2017/9/19
 */

public class DefaultLog extends Trace {
    private static final int MAX_LOG_LENGTH = 4000;
    private static final int MAX_TAG_LENGTH = 23;
    private static final int CALL_STACK_INDEX = 5;
    private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");

    protected String createStackElementTag(StackTraceElement element) {
        String tag = element.getClassName();
        Matcher m = ANONYMOUS_CLASS.matcher(tag);
        if (m.find()) {
            tag = m.replaceAll("");
        }
        tag = tag.substring(tag.lastIndexOf('.') + 1);
        return tag.length() > MAX_TAG_LENGTH ? tag.substring(0, MAX_TAG_LENGTH) : tag;
    }

    @Override
    protected final String getTag() {
        String tag = super.getTag();
        if (tag != null) {
            return tag;
        }

        // DO NOT switch this to Thread.getCurrentThread().getStackTrace(). The test will pass
        // because Robolectric runs them on the JVM but on Android the elements are different.
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length <= CALL_STACK_INDEX) {
            throw new IllegalStateException(
                    "Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }
        return createStackElementTag(stackTrace[CALL_STACK_INDEX]);
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
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
