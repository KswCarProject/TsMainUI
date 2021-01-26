package com.txznet.comm.Ty;

import com.txznet.sdk.TXZResourceManager;
import java.util.regex.Pattern;

/* compiled from: Proguard */
public class T9 {
    public static boolean T(String value) {
        return value == null || value.isEmpty();
    }

    public static String Tr(String str) {
        if (str != null) {
            return Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll(TXZResourceManager.STYLE_DEFAULT);
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }
}
