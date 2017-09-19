package com.ethanco.logbase;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/9/19
 */

public interface Trace {
    boolean isLoggable(String tag, int priority);

    void log(int priority, String tag, String message);
}