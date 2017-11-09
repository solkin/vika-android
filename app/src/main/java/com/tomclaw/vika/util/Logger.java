package com.tomclaw.vika.util;

import android.util.Log;

import com.tomclaw.vika.core.Config;

/**
 * Created by Solkin on 07.02.2015.
 */
public class Logger {

    public static void log(String message) {
        Log.d(Config.LOG_TAG, message);
    }

    public static void logWithPrefix(String prefix, String message) {
        Log.d(Config.LOG_TAG, "[" + prefix + "] " + message);
    }

    public static void log(String message, Throwable ex) {
        Log.d(Config.LOG_TAG, message, ex);
    }
}
