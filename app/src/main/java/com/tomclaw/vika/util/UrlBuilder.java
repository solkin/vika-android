package com.tomclaw.vika.util;

import android.util.Pair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by solkin on 05/11/2017.
 */
public class UrlBuilder extends ArrayList<Pair<String, String>> {

    private static final String UTF8_ENCODING = "UTF-8";

    private String url;

    private UrlBuilder(String url) {
        this.url = url;
    }

    public UrlBuilder appendParam(String key, long value) {
        add(new Pair<>(key, String.valueOf(value)));
        return this;
    }

    public UrlBuilder appendParam(String key, int value) {
        add(new Pair<>(key, String.valueOf(value)));
        return this;
    }

    public UrlBuilder appendParam(String key, String value) {
        add(new Pair<>(key, value));
        return this;
    }

    public void sortParams() {
        Collections.sort(this, new Comparator<Pair<String, String>>() {
            @Override
            public int compare(Pair<String, String> lhs, Pair<String, String> rhs) {
                return lhs.first.compareTo(rhs.first);
            }
        });
    }

    public String build() {
        String params = "";
        try {
            params = prepareParameters(this);
        } catch (UnsupportedEncodingException ignored) {
        }
        return url + params;
    }

    public void reset() {
        clear();
    }

    public static UrlBuilder create(String url) {
        return new UrlBuilder(url);
    }

    private static String prepareParameters(List<Pair<String, String>> pairs)
            throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        // Perform pair concatenation.
        for (Pair<String, String> pair : pairs) {
            if (builder.length() > 0) {
                builder.append('&');
            }
            builder.append(pair.first)
                    .append('=')
                    .append(urlEncode(pair.second));
        }
        return builder.toString();
    }

    private static String urlEncode(String string) throws UnsupportedEncodingException {
        return URLEncoder.encode(string, UTF8_ENCODING).replace("+", "%20");
    }

}
