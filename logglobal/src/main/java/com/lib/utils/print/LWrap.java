package com.lib.utils.print;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Log
 */
class LWrap {
    public static boolean isDebug = true; //是否是调试状态
    public static String defaultTAG = "mLog";
    private static final int JSON_INDENT = 4; //缩进
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final int V = 0x1;
    private static final int D = 0x2;
    private static final int I = 0x3;
    private static final int W = 0x4;
    private static final int E = 0x5;
    private static final int A = 0x6;
    private static final int JSON = 0x7;

    public static void init(boolean _isDebug) {
        isDebug = _isDebug;
    }

    private static void warn(String tag, String msg, Throwable tr) {
        if (isDebug)
            Log.w(tag, msg, tr);
    }

    private static void error(String tag, String msg, Throwable tr) {
        if (isDebug)
            Log.e(tag, msg, tr);
    }

    private static void print(int type, String TAG, String msg) {
        if (!isDebug) {
            return;
        }

        if (TextUtils.isEmpty(msg)) {
            d(TAG, "Empty or Null json content");
            return;
        }

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int index = 10;
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodName).append(" ] ");
        if (type != JSON) {
            stringBuilder.append(msg);
        }
        String logStr = stringBuilder.toString();

        switch (type) {
            case V:
                Log.v(TAG, logStr);
                break;
            case D:
                Log.d(TAG, logStr);
                break;
            case I:
                Log.i(TAG, logStr);
                break;
            case W:
                Log.w(TAG, logStr);
                break;
            case E:
                Log.e(TAG, logStr);
                break;
            case A:
                Log.wtf(TAG, logStr);
                break;
            case JSON: {

                String message = null;
                try {
                    if (msg.startsWith("{")) {
                        JSONObject jsonObject = new JSONObject(msg);
                        message = jsonObject.toString(JSON_INDENT);
                    } else if (msg.startsWith("[")) {
                        JSONArray jsonArray = new JSONArray(msg);
                        message = jsonArray.toString(JSON_INDENT);
                    }
                } catch (JSONException e) {
                    e(TAG, e.getCause().getMessage() + "\n" + msg);
                    return;
                }

                printLine(TAG, true);
                message = logStr + LINE_SEPARATOR + message;
                String[] lines = message.split(LINE_SEPARATOR);
                StringBuilder jsonContent = new StringBuilder();
                for (String line : lines) {
                    jsonContent.append("║ ").append(line).append(LINE_SEPARATOR);
                }
                Log.d(TAG, jsonContent.toString());
                printLine(TAG, false);
            }
            break;
        }
    }

    //------------------default tag ---------------------------//

    public static void e(String msg) {
        print(E, defaultTAG, msg);
    }

    public static void e(String msg, Throwable tr) {
        error(defaultTAG, msg, tr);
    }

    public static void w(String msg) {
        print(W, defaultTAG, msg);
    }

    public static void w(String msg, Throwable tr) {
        warn(defaultTAG, msg, tr);
    }

    public static void i(String msg) {
        print(I, defaultTAG, msg);
    }

    public static void d(String msg) {
        print(D, defaultTAG, msg);
    }

    public static void v(String msg) {
        print(V, defaultTAG, msg);
    }

    public static void json(String msg) {
        print(JSON, defaultTAG, msg);
    }

    //-------------------- subTAG --------------------------------//

    private static String getFinalTAG(String defaultTAG, String subTAG) {
        return defaultTAG + "-" + subTAG;
    }

    public static void e(String subTAG, String msg) {
        print(E, getFinalTAG(defaultTAG, subTAG), msg);
    }

    public static void e(String subTAG, String msg, Throwable tr) {
        error(getFinalTAG(subTAG, subTAG), msg, tr);
    }

    public static void w(String subTAG, String msg) {
        print(W, getFinalTAG(defaultTAG, subTAG), msg);
    }

    public static void w(String subTAG, String msg, Throwable tr) {
        warn(getFinalTAG(defaultTAG, subTAG), msg, tr);
    }

    public static void i(String subTAG, String msg) {
        print(I, getFinalTAG(defaultTAG, subTAG), msg);
    }

    public static void d(String subTAG, String msg) {
        print(D, getFinalTAG(defaultTAG, subTAG), msg);
    }

    public static void v(String subTAG, String msg) {
        print(V, getFinalTAG(defaultTAG, subTAG), msg);
    }

    public static void json(String subTAG, String msg) {
        print(JSON, getFinalTAG(defaultTAG, subTAG), msg);
    }

    private static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}
