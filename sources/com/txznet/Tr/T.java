package com.txznet.Tr;

import com.txznet.comm.Tr.Tn;
import com.txznet.txz.util.T.Tr;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Proguard */
public class T {

    /* renamed from: T  reason: collision with root package name */
    public static Map<String, C0013T> f355T = new ConcurrentHashMap();

    /* renamed from: com.txznet.Tr.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public interface C0013T {
        byte[] T(String str, String str2, byte[] bArr);
    }

    public static void T(String prefix, C0013T processor) {
        Tn.Tr().T((Runnable) new Tr<String, C0013T>(prefix, processor) {
            public void run() {
                if (this.Tn == null) {
                    T.f355T.remove(this.Ty);
                } else {
                    T.f355T.put(this.Ty, this.Tn);
                }
            }
        }, 0);
    }
}
