package com.ethanco.tracelog.parser;

import android.os.Bundle;

/**
 * Created by pengwei on 16/3/8.
 */
public class BundleParse implements Parser<Bundle> {

    @Override
    public Class<Bundle> parseClassType() {
        return Bundle.class;
    }

    @Override
    public String parseString(Bundle bundle) {
        if (bundle != null) {
            StringBuilder builder = new StringBuilder(bundle.getClass().getName() + " [" + LINE_SEPARATOR);
            for (String key : bundle.keySet()) {
                builder.append(String.format("'%s' => %s " + LINE_SEPARATOR,
                        key, ObjectUtil.objectToString(bundle.get(key))));
            }
            builder.append("]");
            return builder.toString();
        }
        return null;
    }
}
