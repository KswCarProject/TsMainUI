package com.txznet.comm.Ty;

import android.widget.TextView;
import com.txznet.comm.ui.Tr.T;

/* compiled from: Proguard */
public class Tk {
    public static void T(TextView tv, float size) {
        tv.setTextSize(0, size);
    }

    public static void T(TextView tv, String key) {
        T(tv, ((Float) T.Tr().T(key)).floatValue());
    }

    public static void T(TextView tv, int color) {
        tv.setTextColor(color);
    }

    public static void Tr(TextView tv, String key) {
        T(tv, ((Integer) T.Tr().T(key)).intValue());
    }
}
