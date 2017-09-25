package com.lib.utils.print;

import com.ethanco.logbase.Printer;

/**
 * TraceLog 包装类 - 增加一层层级
 *
 * @author EthanCo
 * @since 2017/9/25
 */

public class PrinterWrap implements Printer {
    private Printer printer;

    public PrinterWrap(Printer printer) {
        this.printer = printer;
    }

    @Override
    public Printer t(String tag) {
        return printer.t(tag);
    }

    @Override
    public void d(String message, Object... args) {
        printer.d(message, args);
    }

    @Override
    public void e(String message, Object... args) {
        printer.e(message, args);
    }

    @Override
    public void e(Throwable throwable, String message, Object... args) {
        printer.e(throwable, message, args);
    }

    @Override
    public void w(String message, Object... args) {
        printer.w(message, args);
    }

    @Override
    public void i(String message, Object... args) {
        printer.i(message, args);
    }

    @Override
    public void v(String message, Object... args) {
        printer.v(message, args);
    }

    @Override
    public void wtf(String message, Object... args) {
        printer.wtf(message, args);
    }

    @Override
    public void d(Object object) {
        printer.d(object);
    }

    @Override
    public void e(Object object) {
        printer.e(object);
    }

    @Override
    public void w(Object object) {
        printer.w(object);
    }

    @Override
    public void i(Object object) {
        printer.i(object);
    }

    @Override
    public void v(Object object) {
        printer.v(object);
    }

    @Override
    public void json(String json) {
        printer.json(json);
    }

    @Override
    public void xml(String xml) {
        printer.xml(xml);
    }

    @Override
    public void log(int priority, String tag, String message, Throwable throwable) {
        printer.log(priority, tag, message, throwable);
    }
}
