package com.ethanco.tracelog.parser;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengwei on 16/4/18.
 */
public class Constant {
    public static final String STRING_OBJECT_NULL = "Object[object is null]";

    // 解析属性最大层级
    public static final int MAX_CHILD_LEVEL = 2;

    private static List<Parser> defaultParsers;

    // 换行符
    public static final String BR = System.getProperty("line.separator");

    /**
     * 获取默认解析类
     *
     * @return
     */
    public static final List<Parser> getParsers() {
        checkParsers();
        return defaultParsers;
    }

    private static void checkParsers() {
        if (defaultParsers == null) {
            defaultParsers = new ArrayList<>();
            defaultParsers.add(new BundleParse());
            defaultParsers.add(new IntentParse());
            defaultParsers.add(new CollectionParse());
            defaultParsers.add(new MapParse());
            defaultParsers.add(new ThrowableParse());
            defaultParsers.add(new ReferenceParse());
            defaultParsers.add(new MessageParse());
        }
    }

    public static void addParser(Parser parser) {
        checkParsers();
        if (!defaultParsers.contains(parser)) {
            defaultParsers.add(parser);
        }
    }
}
