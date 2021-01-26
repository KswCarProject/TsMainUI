package com.txznet.comm.Tr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import com.txznet.comm.Ty.Tn;

/* compiled from: Proguard */
public class T {

    /* renamed from: T  reason: collision with root package name */
    private static Context f358T;
    private static Context Tr;
    private static boolean Ty = false;

    public static boolean T() {
        return Ty;
    }

    public static void T(Context context) {
        f358T = context.getApplicationContext();
        Ty = "com.txznet.txz".equals(f358T.getApplicationInfo().packageName);
    }

    public static Context Tr() {
        if (f358T != null) {
            return f358T;
        }
        throw new IllegalStateException("you have not yet initialized the sdk context !");
    }

    @SuppressLint({"NewApi"})
    public static Context Ty() {
        if (Tr != null) {
            return Tr;
        }
        if (f358T == null) {
            throw new IllegalStateException("you have not yet initialized the sdk context !");
        } else if (Tn.T() == 0 || Tn.Tr() == 0) {
            return f358T;
        } else {
            Configuration configuration = f358T.getResources().getConfiguration();
            configuration.screenWidthDp = Tn.T();
            configuration.screenHeightDp = Tn.Tr();
            Tr = f358T.createConfigurationContext(configuration);
            return Tr;
        }
    }
}
