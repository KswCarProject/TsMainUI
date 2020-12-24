package com.txznet.comm.Ty;

import java.util.regex.Pattern;

/* compiled from: Proguard */
public class T9 {
    public static boolean T(String value) {
        return value == null || value.isEmpty();
    }

    public static String Tr(String str) {
        if (str != null) {
            return Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("");
        }
        return "";
    }
}
