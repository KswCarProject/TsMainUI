package com.txznet.txz.util;

import T.T.T.T;
import com.txznet.sdk.TXZResourceManager;
import java.util.Locale;

/* compiled from: Proguard */
public class Tr {
    public static String T(String str) {
        return T() ? str : Tr(str);
    }

    public static boolean T() {
        String country = Locale.getDefault().getCountry();
        if ("TW".equals(country) || "HK".equals(country)) {
            return false;
        }
        return true;
    }

    public static String Tr(String str) {
        try {
            return T.T().T(str);
        } catch (Exception e) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
    }
}
