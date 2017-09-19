package com.ethanco.logproxy;

import com.ethanco.logbase.Trace;

/**
 * Log 空实现
 *
 * @author EthanCo
 * @since 2017/6/17
 */

public class EmptyLogTrace implements Trace {

    @Override
    public boolean isLoggable(String tag, int priority) {
        return false;
    }

    @Override
    public void log(int priority, String tag, String message) {

    }
}
