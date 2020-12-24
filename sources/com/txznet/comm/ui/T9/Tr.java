package com.txznet.comm.ui.T9;

import android.text.TextUtils;
import com.txznet.comm.Ty.Tn;
import com.txznet.comm.ui.TE.T;
import com.txznet.comm.ui.Tn.Ty;

/* compiled from: Proguard */
public class Tr {

    /* renamed from: T  reason: collision with root package name */
    private static Tr f526T = new Tr();
    private Boolean T9;
    private Tn.T Tn = new Tn.T() {
        public void T(int width, int height) {
            if (Tr.this.Tr.isShowing()) {
                boolean unused = Tr.this.Ty = true;
            } else {
                com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                    public void run() {
                        Tr.this.Tk();
                    }
                }, 0);
            }
        }
    };
    /* access modifiers changed from: private */
    public T Tr;
    /* access modifiers changed from: private */
    public boolean Ty = false;

    private Tr() {
    }

    public static Tr T() {
        return f526T;
    }

    /* access modifiers changed from: private */
    public void Tk() {
        this.Tr.Tr();
        if (this.Tr instanceof Tn) {
            this.Tr = Tn.Ty();
        }
        if (this.Tr instanceof T9) {
            this.Tr = T9.Ty();
        }
    }

    public void Tr() {
        com.txznet.comm.Tr.Tr.Tn.T("init:" + T.Ty());
        if (!TextUtils.isEmpty(T.Ty())) {
            try {
                this.Tr = (T) com.txznet.comm.ui.Tk.T.Tr().T(T.Ty());
                this.Tr.T();
            } catch (Exception e) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init WinRecordImpl error!");
                this.Tr = null;
            }
        }
        if (this.Tr == null) {
            this.Tr = Ty.Ty();
            this.Tr.T();
        }
        if (this.T9 != null) {
            this.Tr.T(this.T9.booleanValue());
        }
        Tn.T(this.Tn);
    }

    public boolean Ty() {
        return this.Tr.isShowing();
    }

    public void T(boolean isFullScreen) {
        if (this.Tr == null) {
            this.T9 = Boolean.valueOf(isFullScreen);
            return;
        }
        this.Tr.T(isFullScreen);
        if (this.Tr instanceof Tn) {
            this.Tr = Tn.Ty();
        }
    }

    public void T(Ty winLayout) {
        this.Tr.T(winLayout);
    }

    public void Tn() {
        this.Tr.show();
    }

    public void T9() {
        this.Tr.dismiss();
        if (this.Ty) {
            com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                public void run() {
                    if (!Tr.this.Ty()) {
                        Tr.this.Tk();
                        boolean unused = Tr.this.Ty = false;
                    }
                }
            }, 0);
        }
    }
}
