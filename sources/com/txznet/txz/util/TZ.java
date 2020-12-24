package com.txznet.txz.util;

import android.graphics.Color;
import android.text.TextUtils;

/* compiled from: Proguard */
public class TZ {
    public static int T(float alpha, int color) {
        String hex = Integer.toHexString(color);
        if (TextUtils.isEmpty(hex) || hex.length() < 6) {
            throw new NumberFormatException();
        }
        return Color.parseColor(("#" + Integer.toHexString((int) (256.0f * alpha))) + hex.substring(hex.length() - 6, hex.length()));
    }
}
