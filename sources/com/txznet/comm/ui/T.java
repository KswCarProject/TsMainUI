package com.txznet.comm.ui;

/* compiled from: Proguard */
public class T {

    /* renamed from: T  reason: collision with root package name */
    private static Tr f424T;
    private static Ty Tr;
    private static Tk Ty;

    public static Tr T() {
        if (com.txznet.comm.Tr.T.Tr() != null && f424T == null) {
            synchronized (T.class) {
                if (f424T == null) {
                    f424T = new Tr(com.txznet.comm.Tr.T.Tr());
                }
            }
        }
        return f424T;
    }

    public static Ty Tr() {
        if (Tr == null) {
            synchronized (T.class) {
                if (Tr == null) {
                    Tr = new Ty();
                }
            }
        }
        return Tr;
    }

    public static Tk Ty() {
        if (Ty == null) {
            synchronized (T.class) {
                if (Ty == null) {
                    Ty = new Tk();
                }
            }
        }
        return Ty;
    }
}
