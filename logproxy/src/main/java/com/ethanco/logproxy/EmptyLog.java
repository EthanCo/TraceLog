package com.ethanco.logproxy;

import com.ethanco.logbase.IEntireLog;

/**
 * Log 空实现
 *
 * @author EthanCo
 * @since 2017/6/17
 */

public class EmptyLog implements IEntireLog {
    @Override
    public void v(String msg) {

    }

    @Override
    public void d(String msg) {

    }

    @Override
    public void i(String msg) {

    }

    @Override
    public void w(String msg) {

    }

    @Override
    public void e(String msg) {

    }

    @Override
    public void v(String tag, String message) {

    }

    @Override
    public void d(String tag, String message) {

    }

    @Override
    public void i(String tag, String message) {

    }

    @Override
    public void w(String tag, String message) {

    }

    @Override
    public void e(String tag, String message) {

    }

    @Override
    public void postCatchedException(Exception e) {

    }
}
