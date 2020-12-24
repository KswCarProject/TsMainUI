package com.Ty.T.Ty;

import com.Ty.T.Tr.T.T9;
import java.util.Comparator;

/* compiled from: Proguard */
public final class Tn {
    public static String T(String imageUri, T9 targetSize) {
        return imageUri + "_" + targetSize.T() + "x" + targetSize.Tr();
    }

    public static Comparator<String> T() {
        return new Comparator<String>() {
            /* renamed from: T */
            public int compare(String key1, String key2) {
                return key1.substring(0, key1.lastIndexOf("_")).compareTo(key2.substring(0, key2.lastIndexOf("_")));
            }
        };
    }
}
