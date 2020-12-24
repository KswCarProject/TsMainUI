package com.txznet.comm.Tr.Tr;

import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.tongting.IConstantData;
import com.txznet.txz.T.Tn;
import java.lang.reflect.Method;

/* compiled from: Proguard */
public class T9 {

    /* renamed from: T  reason: collision with root package name */
    private static Class<?> f387T = null;
    private static Method Tr = null;
    private static T Ty = new T();

    private static int T(int type, int val, String[] attrs) {
        if (com.txznet.comm.Tr.T.T()) {
            try {
                if (f387T == null || Tr == null) {
                    f387T = Class.forName("com.txznet.txz.jni.JNIHelper");
                    Tr = f387T.getMethod("monitor", new Class[]{Integer.TYPE, Integer.TYPE, String[].class});
                }
                Tr.invoke(f387T, new Object[]{Integer.valueOf(type), Integer.valueOf(val), attrs});
            } catch (Exception e) {
            }
        } else {
            try {
                Tr json = new Tr();
                json.T("attrs", (Object) attrs);
                json.T(IConstantData.KEY_TYPE, (Object) Integer.valueOf(type));
                json.T("val", (Object) Integer.valueOf(val));
                Tn.Tr().T("com.txznet.txz", "comm.monitor", json.toString().getBytes(), (Tn.Tr) null);
            } catch (Exception e2) {
            }
        }
        return 0;
    }

    public static int T(String... attrs) {
        return T(1, 1, attrs);
    }

    public static void T() {
        com.txznet.txz.T.Tn.T("comm.monitor.", Ty);
    }

    /* compiled from: Proguard */
    private static class T implements Tn.T {
        private T() {
        }
    }
}
