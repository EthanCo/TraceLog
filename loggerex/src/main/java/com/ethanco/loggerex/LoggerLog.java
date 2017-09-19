package com.ethanco.loggerex;

/**
 * Logger包装类
 *
 * @author EthanCo
 * @since 2017/9/18
 */

//public class LoggerLog implements ITree {
//
//    public LoggerLog() {
//        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                .showThreadInfo(true)     // (Optional) Whether to show thread info or not. Default true
//                .methodCount(2)           // (Optional) How many method line to show. Default 2
//                .methodOffset(3)          // (Optional) Hides internal method calls up to offset. Default 0
//                //.logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
//                .tag("Logger")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
//                .build();
//        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
//    }
//
//    @Override
//    public void v(String tag, String message) {
//        Logger.t(tag).v(message);
//    }
//
//    @Override
//    public void d(String tag, String message) {
//        Logger.t(tag).d(message);
//    }
//
//    @Override
//    public void i(String tag, String message) {
//        Logger.t(tag).i(message);
//    }
//
//    @Override
//    public void w(String tag, String message) {
//        Logger.t(tag).w(message);
//    }
//
//    @Override
//    public void e(String tag, String message) {
//        Logger.t(tag).e(message);
//    }
//
//    @Override
//    public void json(String tag, String message) {
//        Logger.t(tag).json(message);
//    }
//
//    @Override
//    public void xml(String tag, String message) {
//        Logger.t(tag).xml(message);
//    }
//
//    @Override
//    public void postCatchedException(Exception e) {
//        Logger.e("postCatchedException>>:" + e.getMessage());
//    }
//}
