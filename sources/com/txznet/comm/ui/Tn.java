package com.txznet.comm.ui;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.txznet.comm.Tr.Tr.T9;
import com.txznet.comm.ui.T9.Tk;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.comm.ui.Tk.T;
import com.txznet.txz.util.T5;

/* compiled from: Proguard */
public class Tn {

    /* renamed from: T  reason: collision with root package name */
    protected static HandlerThread f545T;
    private static Handler Tn = new Handler(Looper.getMainLooper());
    protected static T5 Tr;
    private static Tn Ty = new Tn();
    /* access modifiers changed from: private */
    public T T9;

    /* compiled from: Proguard */
    public interface T {
        void T();

        void Tr();
    }

    private Tn() {
    }

    public static Tn T() {
        return Ty;
    }

    public static void T(Runnable r, int delay) {
        if (delay > 0) {
            Tn.postDelayed(r, (long) delay);
        } else {
            Tn.post(r);
        }
    }

    public static void T(Runnable runnable) {
        if (Tn != null) {
            Tn.removeCallbacks(runnable);
        }
    }

    public static void T(Runnable r, long delay) {
        if (delay > 0) {
            Tr.T(r, delay);
        } else {
            Tr.T(r);
        }
    }

    public void T(T listener) {
        this.T9 = listener;
        f545T = new HandlerThread("UI2Back");
        f545T.start();
        Tr = new T5(f545T.getLooper());
        com.txznet.comm.Ty.T.T.T(com.txznet.comm.Tr.T.Tr());
        Tr();
    }

    public void Tr(T listener) {
        com.txznet.comm.Tr.Tr.Tn.T("initBySDK");
        T(listener);
    }

    public void Tr() {
        com.txznet.comm.Tr.Tr.Tn.T("#####UI2.0####### initNormal");
        com.txznet.comm.ui.Tk.T.Tr().T((T.C0020T) new T.C0020T() {
            public void T() {
                com.txznet.comm.Tr.Tr.Tn.T("onResLoaded");
                Tn.T((Runnable) new Runnable() {
                    public void run() {
                        try {
                            com.txznet.comm.ui.TE.T.T(Integer.valueOf(Tr.T9("theme_code")).intValue());
                            com.txznet.comm.ui.T9.Tr.T().Tr();
                            Tk.Tr().T(com.txznet.comm.ui.Tk.T.Tr().f543T);
                            com.txznet.comm.ui.Tr.T.Tr().T();
                            com.txznet.comm.ui.TZ.Tn.Tr().T();
                            Tk.Tr().T();
                            T9.T().Tr();
                            Tn.T((Runnable) new Runnable() {
                                public void run() {
                                    try {
                                        com.txznet.comm.ui.Tn.Tn.T().Ty();
                                        com.txznet.comm.ui.Ty.Tr.T().Tr();
                                        Tn.T((Runnable) new Runnable() {
                                            public void run() {
                                                com.txznet.comm.Tr.Tr.Tn.T("UI2.0 init success");
                                                com.txznet.comm.ui.T9.Tr.T().T(com.txznet.comm.ui.Tn.Tn.T().T5());
                                                if (Tn.this.T9 != null) {
                                                    Tn.this.T9.T();
                                                }
                                            }
                                        }, 0);
                                    } catch (Exception e) {
                                        com.txznet.comm.Tr.Tr.Tn.T("UI2.0:", (Throwable) e);
                                        com.txznet.comm.Tr.Tr.Tn.Tn("UI2.0 normal init error!");
                                        T9.T("ui.init.error.E.view");
                                        if (Tn.this.T9 != null) {
                                            Tn.this.T9.Tr();
                                        }
                                    }
                                }
                            }, 0);
                        } catch (Exception e) {
                            com.txznet.comm.Tr.Tr.Tn.T("UI2.0:", (Throwable) e);
                            com.txznet.comm.Tr.Tr.Tn.Tn("UI2.0 normal init error!");
                            if (Tn.this.T9 != null) {
                                Tn.this.T9.Tr();
                            }
                        }
                    }
                }, 0);
            }

            public void T(String errorDsp) {
                com.txznet.comm.Tr.Tr.Tn.Tn("load skin apk error:" + errorDsp);
                if (Tn.this.T9 != null) {
                    Tn.this.T9.Tr();
                }
            }
        }, false);
    }
}
