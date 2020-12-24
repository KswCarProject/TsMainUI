package com.txznet.comm.ui.T9;

import android.content.DialogInterface;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import com.ts.set.SettingSoundActivity;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.ui.TE.T;
import com.txznet.comm.ui.Tk;
import com.txznet.comm.ui.Tr;
import com.txznet.comm.ui.Ty;

/* compiled from: Proguard */
public class Tn extends TE implements T {

    /* renamed from: T  reason: collision with root package name */
    private static Tn f521T;
    private Tr.T T6 = new Tr.T() {
        public void T() {
            Tn.this.dismiss();
        }
    };
    private boolean T9 = false;
    private Ty.T Te = new Ty.T() {
    };
    private boolean Th;
    private Float Tn = null;
    private Tk.T Tq = null;
    private com.txznet.comm.ui.Tn.Ty Tr;
    private boolean Ty = false;

    private Tn() {
        Tk();
    }

    private Tn(boolean fullScreen) {
        super(true, fullScreen);
        Tk();
    }

    private void Tk() {
        getWindow().setType(2003);
        getWindow().addFlags(268435520);
        getWindow().setFlags(1024, 1024);
        setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.button.back", (byte[]) null, (Tn.Tr) null);
                Tn.this.dismiss();
            }
        });
        this.Tv = new com.txznet.comm.Ty.Ty(getContext());
    }

    public static Tn Ty() {
        if (f521T == null) {
            synchronized (Tn.class) {
                if (f521T == null) {
                    f521T = new Tn();
                }
            }
        }
        return f521T;
    }

    public void T(boolean isFullScreen) {
        if (this.TZ != isFullScreen) {
            com.txznet.comm.Tr.Tr.Tn.T("setIsFullScreenDialog:" + isFullScreen);
            f521T = new Tn(isFullScreen);
            f521T.T(com.txznet.comm.ui.Tn.Tn.T().T5());
            f521T.T();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        com.txznet.comm.Tr.Tr.Tn.T("onKeyDown:" + keyCode);
        return Tk.Tr().T(keyCode);
    }

    /* access modifiers changed from: protected */
    public View T(Object... objects) {
        if (this.Tr == null) {
            com.txznet.comm.ui.Tn.T.Tk.Tk().T9();
            this.Tr = com.txznet.comm.ui.Tn.T.Tk.Tk();
        }
        if (this.Tr != null) {
            return this.Tr.Tn();
        }
        return null;
    }

    public void T() {
        com.txznet.comm.Tr.Tr.Tn.T("[UI2.0] init recordwin2");
        T.T(getWindow().getDecorView());
        T.Tr(getWindow().getDecorView());
        getWindow().setLayout(-1, -1);
        if (this.Tn != null) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.alpha = this.Tn.floatValue();
            getWindow().setAttributes(layoutParams);
        }
        this.Ty = true;
    }

    public void T(com.txznet.comm.ui.Tn.Ty winLayout) {
        com.txznet.comm.Tr.Tr.Tn.T("[UI2.0] update winLayout");
        if (winLayout != null && !winLayout.equals(this.Tr)) {
            this.Tr = winLayout;
            this.Tk = this.Tr.Tn();
            ViewParent viewParent = this.Tk.getParent();
            if (viewParent != null && (viewParent instanceof ViewGroup)) {
                ((ViewGroup) viewParent).removeView(this.Tk);
            }
            if (this.TZ) {
                this.Tk.setSystemUiVisibility(SettingSoundActivity.id_jazz);
                getWindow().getAttributes().flags |= 1024;
            } else {
                this.Tk.setSystemUiVisibility(0);
            }
            setContentView(this.Tk);
            getWindow().setLayout(-1, -1);
            getWindow().setBackgroundDrawable(com.txznet.comm.ui.TE.Tr.Ty("dialog_bg"));
        }
    }

    public void show() {
        if (this.Ty) {
            if (!this.T9) {
                this.T9 = true;
                com.txznet.comm.ui.T.T().registerObserver(this.T6);
                com.txznet.comm.ui.T.Tr().registerObserver(this.Te);
                if (this.Tq != null) {
                    com.txznet.comm.ui.T.Ty().registerObserver(this.Tq);
                }
            }
            if (!this.Th) {
                this.Tv.T();
                this.Th = true;
            }
            if (this.Tk == null) {
                T.Tr(getWindow().getDecorView());
            } else {
                T.Tr(this.Tk);
                this.Tk.setBackgroundDrawable(com.txznet.comm.ui.TE.Tr.Ty("dialog_bg"));
            }
            super.show();
            TZ();
        }
    }

    private void TZ() {
    }

    public void dismiss() {
        com.txznet.comm.Tr.Tr.Tn.T("RecordWin2 dismiss");
        com.txznet.comm.Tr.Tr.T.T9();
        if (this.Th) {
            this.Tv.Tr();
            this.Th = false;
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.dismiss", (byte[]) null, (Tn.Tr) null);
        if (Looper.myLooper() != Looper.getMainLooper()) {
            com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                public void run() {
                    Tn.super.dismiss();
                    Tn.this.TE();
                }
            }, 0);
            return;
        }
        super.dismiss();
        TE();
    }

    /* access modifiers changed from: protected */
    public void Tn() {
        com.txznet.comm.ui.T.Ty().Tr();
    }

    /* access modifiers changed from: protected */
    public void T9() {
        com.txznet.comm.ui.T.Ty().T();
        if (this.Tk != null) {
            if (this.TZ) {
                this.Tk.setSystemUiVisibility(SettingSoundActivity.id_jazz);
                getWindow().getAttributes().flags |= 1024;
            } else {
                this.Tk.setSystemUiVisibility(0);
            }
        }
        getWindow().setLayout(-1, -1);
    }

    /* access modifiers changed from: private */
    public void TE() {
        this.Tr.T();
        if (this.T9) {
            com.txznet.comm.ui.T.Ty().Ty();
            com.txznet.comm.ui.T.T().unregisterObserver(this.T6);
            com.txznet.comm.ui.T.Tr().unregisterObserver(this.Te);
            if (this.Tq != null) {
                com.txznet.comm.ui.T.Ty().unregisterObserver(this.Tq);
            }
            this.T9 = false;
        }
        if (com.Ty.T.Tr.Tn.T().Tr()) {
            com.Ty.T.Tr.Tn.T().Tn();
            com.Ty.T.Tr.Tn.T().Ty();
        }
        com.txznet.comm.ui.TE.Tr.T();
        System.gc();
    }

    public void Tr() {
        if (isShowing()) {
            com.txznet.comm.Tr.Tr.Tn.Tn("current win is showing,can't new instance!");
            return;
        }
        TE();
        f521T = new Tn(this.TZ);
    }
}
