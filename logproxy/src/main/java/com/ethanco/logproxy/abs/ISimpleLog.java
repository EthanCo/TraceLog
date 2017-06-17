package com.ethanco.logproxy.abs;

/**
 * @Description 没有Tag Log
 * Created by EthanCo on 2016/11/1.
 */

public interface ISimpleLog {
    void v(String message);

    void d(String message);

    void i(String message);

    void w(String message);

    void e(String message);
}
