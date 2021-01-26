package com.txznet.comm.Tr.Tr;

import com.txznet.comm.Tr.T;
import com.txznet.comm.Tr.Tn;

/* compiled from: Proguard */
public class TE {

    /* renamed from: T  reason: collision with root package name */
    public static long f392T = 0;
    public static boolean Tr = true;

    public static int T(int type, byte[] jsonData) {
        if (T.T()) {
            try {
                Class<?> cls = Class.forName("com.txznet.txz.jni.JNIHelper");
                cls.getMethod("doReport", new Class[]{Integer.TYPE, byte[].class}).invoke(cls, new Object[]{Integer.valueOf(type), jsonData});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!com.txznet.comm.Tr.T.T.T().Ty()) {
            Tn.Tr().T("com.txznet.txz", "comm.report.type." + type, jsonData, (Tn.Tr) null);
        } else {
            com.txznet.comm.Tr.T.T.T().T(12, com.txznet.comm.Tr.T.Tn.T(type, jsonData));
        }
        return 0;
    }

    public static int Tr(int type, byte[] jsonData) {
        if (T.T()) {
            try {
                Class<?> cls = Class.forName("com.txznet.txz.jni.JNIHelper");
                cls.getMethod("doReportImmediate", new Class[]{Integer.TYPE, byte[].class}).invoke(cls, new Object[]{Integer.valueOf(type), jsonData});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!com.txznet.comm.Tr.T.T.T().Ty()) {
            Tn.Tr().T("com.txznet.txz", "comm.report.imme." + type, jsonData, (Tn.Tr) null);
        } else {
            com.txznet.comm.Tr.T.T.T().T(13, com.txznet.comm.Tr.T.Tn.T(type, jsonData));
        }
        return 0;
    }

    public static int T(int type, String json) {
        return T(type, json.getBytes());
    }
}
