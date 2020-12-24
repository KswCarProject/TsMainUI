package com.txznet.comm.ui.T9;

import com.ts.bt.BtExe;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.Tk;

/* compiled from: Proguard */
public class Ty implements T {
    private static Ty Ty = new Ty();

    /* renamed from: T  reason: collision with root package name */
    Runnable f530T = new Runnable() {
        public void run() {
            if (Ty.this.Tr != null && !Ty.this.Tr.isShowing()) {
                Ty.this.T9();
            }
        }
    };
    private Integer T5 = null;
    private Boolean T9 = null;
    private Tk.T TE = null;
    private com.txznet.comm.ui.Tn.Ty TZ = null;
    private Integer Th = null;
    private Float Tk = null;
    private Object Tn = new Object();
    /* access modifiers changed from: private */
    public TZ Tr;
    private Integer Tv = null;

    private Ty() {
    }

    public static Ty Ty() {
        return Ty;
    }

    private void T(String log) {
        Tn.T("RecordWin2Impl1:" + log);
    }

    /* access modifiers changed from: private */
    public void Tn() {
        boolean z = false;
        if (this.TZ != null) {
            if (this.T9 != null) {
                z = this.T9.booleanValue();
            }
            this.Tr = new TZ(z, this.TZ);
        } else {
            com.txznet.comm.ui.Tn.T.Tk.Tk().T9();
            com.txznet.comm.ui.Tn.Ty winLayout = com.txznet.comm.ui.Tn.T.Tk.Tk();
            if (this.T9 != null) {
                z = this.T9.booleanValue();
            }
            this.Tr = new TZ(z, winLayout);
        }
        if (this.TE != null) {
            this.Tr.T(this.TE);
        }
        if (this.Tk != null) {
            this.Tr.T(this.Tk);
        }
        if (this.T5 != null) {
            this.Tr.T(this.T5.intValue());
        }
        if (this.Th != null) {
            this.Tr.Tr(this.Th.intValue());
        }
    }

    /* access modifiers changed from: private */
    public void T9() {
        T("releaseWinRecord");
        com.txznet.comm.ui.Tn.T(this.f530T);
        if (this.Tr != null) {
            if (this.Tr.isShowing()) {
                this.Tr.dismiss();
            }
            this.Tr.Ty();
            com.txznet.comm.ui.Tn.Tn.T().Tk();
            this.Tr = null;
        }
        System.gc();
    }

    public void T() {
        synchronized (this.Tn) {
            if (this.Tr == null) {
                Tn();
            }
        }
        this.Tr.Tr();
        com.txznet.comm.ui.Tn.T(this.f530T);
        com.txznet.comm.ui.Tn.T(this.f530T, (int) BtExe.AUTO_ANSWER_CHK_TIME);
    }

    public boolean isShowing() {
        if (this.Tr == null) {
            return false;
        }
        return this.Tr.isShowing();
    }

    public void T(boolean isFullScreen) {
        this.T9 = Boolean.valueOf(isFullScreen);
        if (this.Tr != null) {
            com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                public void run() {
                    Ty.this.T9();
                    Ty.this.Tn();
                }
            }, 0);
        }
    }

    public void T(com.txznet.comm.ui.Tn.Ty winLayout) {
        this.TZ = winLayout;
        if (this.Tr != null) {
            this.Tr.T(this.TZ);
        }
    }

    public void show() {
        com.txznet.comm.ui.Tn.T(this.f530T);
        synchronized (this.Tn) {
            if (this.Tr == null) {
                Tn();
            }
            com.txznet.comm.ui.Tn.Tn.T().Tn();
            this.Tr.show();
        }
    }

    public void dismiss() {
        synchronized (this.Tn) {
            if (this.Tr != null) {
                this.Tr.dismiss();
                com.txznet.comm.ui.Tn.T(this.f530T);
                com.txznet.comm.ui.Tn.T(this.f530T, (int) BtExe.AUTO_ANSWER_CHK_TIME);
            }
        }
    }

    public void Tr() {
        if (isShowing()) {
            Tn.Tn("current win is showing,can't new instance!");
        } else {
            T9();
        }
    }
}
