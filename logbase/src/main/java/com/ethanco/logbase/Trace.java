package com.ethanco.logbase;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/9/19
 */

public abstract class Trace {
    final ThreadLocal<String> explicitTag = new ThreadLocal<>();

    protected String getTag() {
        String tag = explicitTag.get();
        if (tag != null) {
            explicitTag.remove();
        }
        return tag;
    }

    /**
     * Log a verbose message with optional format args.
     */
    public void v(String message, Object... args) {
        prepareLog(Log.VERBOSE, null, message, args);
    }

    /**
     * Log a verbose exception and a message with optional format args.
     */
    public void v(Throwable t, String message, Object... args) {
        prepareLog(Log.VERBOSE, t, message, args);
    }

    /**
     * Log a verbose exception.
     */
    public void v(Throwable t) {
        prepareLog(Log.VERBOSE, t, null);
    }

    /**
     * Log a debug message with optional format args.
     */
    public void d(String message, Object... args) {
        prepareLog(Log.DEBUG, null, message, args);
    }

    /**
     * Log a debug exception and a message with optional format args.
     */
    public void d(Throwable t, String message, Object... args) {
        prepareLog(Log.DEBUG, t, message, args);
    }

    /**
     * Log a debug exception.
     */
    public void d(Throwable t) {
        prepareLog(Log.DEBUG, t, null);
    }

    /**
     * Log an info message with optional format args.
     */
    public void i(String message, Object... args) {
        prepareLog(Log.INFO, null, message, args);
    }

    /**
     * Log an info exception and a message with optional format args.
     */
    public void i(Throwable t, String message, Object... args) {
        prepareLog(Log.INFO, t, message, args);
    }

    /**
     * Log an info exception.
     */
    public void i(Throwable t) {
        prepareLog(Log.INFO, t, null);
    }

    /**
     * Log a warning message with optional format args.
     */
    public void w(String message, Object... args) {
        prepareLog(Log.WARN, null, message, args);
    }

    /**
     * Log a warning exception and a message with optional format args.
     */
    public void w(Throwable t, String message, Object... args) {
        prepareLog(Log.WARN, t, message, args);
    }

    /**
     * Log a warning exception.
     */
    public void w(Throwable t) {
        prepareLog(Log.WARN, t, null);
    }

    /**
     * Log an error message with optional format args.
     */
    public void e(String message, Object... args) {
        prepareLog(Log.ERROR, null, message, args);
    }

    /**
     * Log an error exception and a message with optional format args.
     */
    public void e(Throwable t, String message, Object... args) {
        prepareLog(Log.ERROR, t, message, args);
    }

    /**
     * Log an error exception.
     */
    public void e(Throwable t) {
        prepareLog(Log.ERROR, t, null);
    }

    /**
     * Log an assert message with optional format args.
     */
    public void wtf(String message, Object... args) {
        prepareLog(Log.ASSERT, null, message, args);
    }

    /**
     * Log an assert exception and a message with optional format args.
     */
    public void wtf(Throwable t, String message, Object... args) {
        prepareLog(Log.ASSERT, t, message, args);
    }

    /**
     * Log an assert exception.
     */
    public void wtf(Throwable t) {
        prepareLog(Log.ASSERT, t, null);
    }

    /**
     * Log at {@code priority} a message with optional format args.
     */
    public void log(int priority, String message, Object... args) {
        prepareLog(priority, null, message, args);
    }

    /**
     * Log at {@code priority} an exception and a message with optional format args.
     */
    public void log(int priority, Throwable t, String message, Object... args) {
        prepareLog(priority, t, message, args);
    }

    /**
     * Log at {@code priority} an exception.
     */
    public void log(int priority, Throwable t) {
        prepareLog(priority, t, null);
    }

    /**
     * Return whether a message at {@code priority} should be logged.
     *
     * @deprecated use {@link #isLoggable(String, int)} instead.
     */
    @Deprecated
    public boolean isLoggable(int priority) {
        return true;
    }

    /**
     * Return whether a message at {@code priority} or {@code tag} should be logged.
     */
    public boolean isLoggable(String tag, int priority) {
        return isLoggable(priority);
    }

    private void prepareLog(int priority, Throwable t, String message, Object... args) {
        // Consume tag even when message is not loggable so that next message is correctly tagged.
        String tag = getTag();

        if (!isLoggable(tag, priority)) {
            return;
        }
        if (message != null && message.length() == 0) {
            message = null;
        }
        if (message == null) {
            if (t == null) {
                return; // Swallow message if it's null and there's no throwable.
            }
            message = getStackTraceString(t);
        } else {
            if (args.length > 0) {
                message = formatMessage(message, args);
            }
            if (t != null) {
                message += "\n" + getStackTraceString(t);
            }
        }

        log(priority, tag, message, t);
    }

    /**
     * Formats a log message with optional arguments.
     */
    protected String formatMessage(String message, Object[] args) {
        return String.format(message, args);
    }

    private String getStackTraceString(Throwable t) {
        // Don't replace this with Log.getStackTraceString() - it hides
        // UnknownHostException, which is not what we want.
        StringWriter sw = new StringWriter(256);
        PrintWriter pw = new PrintWriter(sw, false);
        t.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    /**
     * Write a log message to its destination. Called for all level-specific methods by default.
     *
     * @param priority Log level. See {@link Log} for constants.
     * @param tag      Explicit or inferred tag. May be {@code null}.
     * @param message  Formatted log message. May be {@code null}, but then {@code t} will not be.
     * @param t        Accompanying exceptions. May be {@code null}, but then {@code message} will not be.
     */
    protected abstract void log(int priority, String tag, String message, Throwable t);
}