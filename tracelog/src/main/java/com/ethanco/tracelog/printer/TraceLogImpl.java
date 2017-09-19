package com.ethanco.tracelog.printer;

import android.text.TextUtils;
import android.util.Log;

import com.ethanco.logbase.Config;
import com.ethanco.logbase.Printer;
import com.ethanco.logbase.Trace;
import com.ethanco.tracelog.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import static android.util.Log.ASSERT;
import static android.util.Log.DEBUG;
import static android.util.Log.VERBOSE;
import static com.ethanco.tracelog.parser.ObjectUtil.objectToString;

/**
 * TraceLogImpl
 *
 * @author EthanCo
 * @since 2017/9/19
 */

public class TraceLogImpl implements Printer, Config {

    /**
     * It is used for json pretty print
     */
    private static final int JSON_INDENT = 2;

    /**
     * Provides one-time used tag for the log message
     */
    private final ThreadLocal<String> localTag = new ThreadLocal<>();

    private final List<Trace> traces = new ArrayList<>();
    private volatile String defaultTag = "TraceLog";

    @Override
    public Printer t(String tag) {
        if (tag != null) {
            localTag.set(tag);
        }
        return this;
    }

    @Override
    public void d(String message, Object... args) {
        log(DEBUG, null, message, args);
    }

    @Override
    public void d(Object object) {
        //log(DEBUG, null, Util.toString(object));
        log(DEBUG, null, objectToString(object));
    }

    @Override
    public void e(String message, Object... args) {
        //e(null, message, args);
        log(Log.ERROR, null, message, args);
    }

    @Override
    public void e(Throwable throwable, String message, Object... args) {
        log(Log.ERROR, throwable, message, args);
    }

    @Override
    public void w(String message, Object... args) {
        log(Log.WARN, null, message, args);
    }

    @Override
    public void i(String message, Object... args) {
        log(Log.INFO, null, message, args);
    }

    @Override
    public void v(String message, Object... args) {
        log(VERBOSE, null, message, args);
    }

    @Override
    public void wtf(String message, Object... args) {
        log(ASSERT, null, message, args);
    }

    @Override
    public void json(String json) {
        if (TextUtils.isEmpty(json)) {
            d("Empty/Null json content");
            return;
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(JSON_INDENT);
                //d(message);
                log(DEBUG, null, objectToString(message));
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(JSON_INDENT);
                //d(message);
                log(DEBUG, null, objectToString(message));
                return;
            }
            e("Invalid Json");
        } catch (JSONException e) {
            e("Invalid Json");
        }
    }

    @Override
    public void xml(String xml) {
        if (TextUtils.isEmpty(xml)) {
            d("Empty/Null xml content");
            return;
        }
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            d(xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (TransformerException e) {
            e("Invalid xml");
        }
    }

    @Override
    public synchronized void log(int priority, String tag, String message, Throwable throwable) {
        if (throwable != null && message != null) {
            message += " : " + Util.getStackTraceString(throwable);
        }
        if (throwable != null && message == null) {
            message = Util.getStackTraceString(throwable);
        }
        if (Util.isEmpty(message)) {
            message = "Empty/NULL log message";
        }
        if (Util.isEmpty(tag)) {
            tag = defaultTag;
        }

        for (Trace adapter : traces) {
            if (adapter.isLoggable(tag, priority)) {
                adapter.log(priority, tag, message);
            }
        }
    }

    //@Override
    public TraceLogImpl clearTraces() {
        traces.clear();
        return this;
    }

    @Override
    public TraceLogImpl setDefaultTag(String tag) {
        defaultTag = tag;
        return this;
    }

    @Override
    public TraceLogImpl addTrace(Trace trace) {
        traces.add(trace);
        return this;
    }

    /**
     * This method is synchronized in order to avoid messy of logs' order.
     */
    private synchronized void log(int priority, Throwable throwable, String msg, Object... args) {
        String tag = getTag();
        String message = createMessage(msg, args);
        log(priority, tag, message, throwable);
    }

    /**
     * @return the appropriate tag based on local or global
     */
    private String getTag() {
        String tag = localTag.get();
        if (tag != null) {
            localTag.remove();
            return tag;
        }
        return null;
    }

    private String createMessage(String message, Object... args) {
        return args == null || args.length == 0 ? message : String.format(message, args);
    }
}