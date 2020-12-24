package com.ts.set.definition;

import android.util.Log;

public class Debug {
    public static final boolean DEBUG_MEDIA = true;
    public static String MEDIAVERSION = "v1.0.0_1506290915";
    public static boolean OUTPUT_DEBUG = true;
    public static boolean OUTPUT_ERROR = true;
    public static boolean OUTPUT_WARNING = true;

    public static void d(String strTag, String strMsg) {
        if (OUTPUT_DEBUG) {
            Log.d("[wcb]:" + strTag, strMsg);
        }
    }

    public static void w(String strTag, String strMsg) {
        if (OUTPUT_DEBUG || OUTPUT_WARNING) {
            Log.w("[wcb]:" + strTag, strMsg);
        }
    }

    public static void e(String strTag, String strMsg) {
        if (OUTPUT_DEBUG || OUTPUT_ERROR) {
            Log.e("[wcb]:" + strTag, strMsg);
        }
    }
}
