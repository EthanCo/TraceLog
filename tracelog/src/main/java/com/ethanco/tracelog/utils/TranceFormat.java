package com.ethanco.tracelog.utils;

import android.text.TextUtils;

/**
 * Created by Heiko on 2020/2/14.
 */

public class TranceFormat {

    /**
     * 使日志可以定位
     *
     * @param msg
     * @return
     */
    public static String getIndexMessage(int stackIndex,String msg) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = stackTrace[stackIndex].getFileName();
        String methodName = stackTrace[stackIndex].getMethodName();
        int lineNumber = stackTrace[stackIndex].getLineNumber();
        //methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ (").append(className).append(":")
                .append(lineNumber).append(")#")
                .append(methodName).append(" ] ")
                .append(msg);

        return stringBuilder.toString();
    }

    private static final String LINE_FEED = "\n    ";
    private static final String AT = "at ";

    /**
     * 获取堆栈信息
     *
     * @param prefix
     * @return
     */
    public static String getStackInfo(String prefix) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(prefix)) {
            sb.append(prefix);
        }
        sb.append(LINE_FEED);
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 4; i < stackTrace.length; i++) {
            sb.append(AT).append(stackTrace[i].toString()).append(LINE_FEED);
        }
        return sb.toString();
    }
}
