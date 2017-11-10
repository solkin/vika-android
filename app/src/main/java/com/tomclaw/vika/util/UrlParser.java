package com.tomclaw.vika.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by solkin on 05/11/2017.
 */
public class UrlParser {

    private static final String PARAMETER_SEPARATOR = "&";
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    private static final String UTF8_ENCODING = "UTF-8";

    public static Map<String, String> parse(final URI uri) {
        return parse(uri, UTF8_ENCODING);
    }

    public static Map<String, String> parse(final URI uri, final String encoding) {
        Map<String, String> result = Collections.emptyMap();
        String params = uri.getRawQuery();
        if (params == null) {
            params = uri.getRawFragment();
        }
        if (params != null && params.length() > 0) {
            result = new HashMap<>();
            parse(result, new Scanner(params), encoding);
        }
        return result;
    }

    private static void parse(
            final Map<String, String> parameters,
            final Scanner scanner,
            final String encoding) {
        scanner.useDelimiter(PARAMETER_SEPARATOR);
        while (scanner.hasNext()) {
            final String[] nameValue = scanner.next().split(NAME_VALUE_SEPARATOR);
            if (nameValue.length == 0 || nameValue.length > 2) {
                throw new IllegalArgumentException("bad parameter");
            }
            final String name = decode(nameValue[0], encoding);
            String value = null;
            if (nameValue.length == 2) {
                value = decode(nameValue[1], encoding);
            }
            parameters.put(name, value);
        }
    }

    private static String decode(final String content, final String encoding) {
        try {
            return URLDecoder.decode(content,
                    encoding != null ? encoding : DEFAULT_CONTENT_CHARSET);
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }
}
