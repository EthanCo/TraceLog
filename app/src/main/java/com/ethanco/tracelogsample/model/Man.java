package com.ethanco.tracelogsample.model;


import com.ethanco.tracelogsample.DataHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pengwei on 16/3/8.
 */
public class Man extends Person {
    private static final int SEX = 1;
    private int a = 0;
    private List<String> list = new ArrayList<>();
    private Map<String, Person> map = DataHelper.getObjectMap();

    public Man(int a) {
        super();
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
