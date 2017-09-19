package com.ethanco.logbase;

/**
 * Printer
 *
 * @author EthanCo
 * @since 2017/9/19
 */
public interface Config {
    Config setDefaultTag(String tag);

    Config addTrace(Trace trace);
}
