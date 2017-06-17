package com.ethanco.logproxy.abs;

/**
 * @Description 常用Log
 * Created by EthanCo on 2016/10/12.
 */

public interface ICommonLog {
    void v(String tag, String message);

    void d(String tag, String message);

    void i(String tag, String message);

    void w(String tag, String message);

    void e(String tag, String message);

    void postCatchedException(Exception e);
}
