package com.txznet.comm.T;

import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZResourceManager;
import java.util.List;

/* compiled from: Proguard */
public class Tr extends T {

    /* renamed from: T  reason: collision with root package name */
    private static Tr f356T = new Tr();
    private Object T9 = new Object();
    private List<T> Tn;
    private int Tr = 0;
    private int Ty = 0;

    /* compiled from: Proguard */
    public interface T {
        void T(int i);
    }

    private Tr() {
    }

    public static Tr T() {
        return f356T;
    }

    private void Tr() {
        if (com.txznet.comm.Tr.T.T()) {
            T("comm.configer.navControl.navMode", (TXZResourceManager.STYLE_DEFAULT + this.Tr).getBytes());
        }
        synchronized (this.T9) {
            if (this.Tn != null) {
                for (T listener : this.Tn) {
                    if (listener != null) {
                        listener.T(this.Tr);
                    }
                }
            }
        }
    }

    private void T(String packageName) {
        Tn.Tr().T(packageName, "comm.configer.navControl.navMode", (TXZResourceManager.STYLE_DEFAULT + this.Tr).getBytes(), (Tn.Tr) null);
    }

    public byte[] T(String packageName, String command, byte[] data) {
        com.txznet.comm.Tr.Tr.Tn.T("receive cmd:" + command + " from:" + packageName);
        if (com.txznet.comm.Tr.T.T()) {
            if ("sync".equals(command)) {
                T(packageName);
            }
        } else if ("navMode".equals(command)) {
            Integer mode = Integer.valueOf(Integer.parseInt(new String(data)));
            if (mode.intValue() != this.Tr) {
                this.Tr = mode.intValue();
                Tr();
            }
        }
        return null;
    }
}
