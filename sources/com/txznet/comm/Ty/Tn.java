package com.txznet.comm.Ty;

import android.content.res.Configuration;
import android.content.res.Resources;
import com.ts.bt.ContactInfo;
import java.util.HashSet;
import java.util.Set;

/* compiled from: Proguard */
public class Tn {

    /* renamed from: T  reason: collision with root package name */
    private static int f410T = 0;
    private static int T9;
    private static Set<T> TZ = new HashSet();
    private static boolean Tk = false;
    private static int Tn;
    private static int Tr = 0;
    private static int Ty = 100000;

    /* compiled from: Proguard */
    public interface T {
        void T(int i, int i2);
    }

    public static int T(int value) {
        return value > Ty ? value : value + Ty;
    }

    public static int Tr(int value) {
        return value < Ty ? value : value - Ty;
    }

    public static void T(int width, int height, boolean manual) {
        com.txznet.comm.Tr.Tr.Tn.T("SceenSize change:" + width + ContactInfo.COMBINATION_SEPERATOR + height + ",mannual:" + manual + ",sFitScreenChange:" + Tk);
        if (!manual && (Tn == 0 || T9 == 0 || (width == Tn && height == T9))) {
            Tn = width;
            T9 = height;
        } else if (manual || Tk) {
            Tn = width;
            T9 = height;
            int width2 = T(width);
            int height2 = T(height);
            Resources resources = com.txznet.comm.Tr.T.Tr().getResources();
            Configuration configuration = resources.getConfiguration();
            configuration.screenWidthDp = width2;
            configuration.screenHeightDp = height2;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            synchronized (Tn.class) {
                for (T listener : TZ) {
                    listener.T(Tr(width2), Tr(height2));
                }
            }
        }
    }

    public static void T(T listener) {
        synchronized (Tn.class) {
            TZ.add(listener);
        }
    }

    public static int T() {
        return f410T;
    }

    public static int Tr() {
        return Tr;
    }
}
