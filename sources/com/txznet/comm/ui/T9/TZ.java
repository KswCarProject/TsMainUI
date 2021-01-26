package com.txznet.comm.ui.T9;

import android.content.DialogInterface;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.set.SettingSoundActivity;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.ui.TE.T;
import com.txznet.comm.ui.Tk;
import com.txznet.comm.ui.Tr;
import com.txznet.comm.ui.Ty;

/* compiled from: Proguard */
public class TZ extends TE {

    /* renamed from: T  reason: collision with root package name */
    public static int f507T;
    private static int T6 = 0;
    public static Boolean T9 = null;
    private static boolean Te = false;
    public static int Tn;
    private static Float Tq = null;
    public static int Tr;
    public static int Ty;
    private Tr.T TB = new Tr.T() {
        public void T() {
            TZ.this.dismiss();
        }
    };
    private boolean TF = false;
    private Ty.T TK = new Ty.T() {
    };
    private Tk.T TO = null;
    /* access modifiers changed from: private */
    public com.txznet.comm.ui.Tn.Ty Th;
    private boolean Tj;

    public TZ() {
        Tk();
    }

    public TZ(boolean fullScreen, com.txznet.comm.ui.Tn.Ty winLayout) {
        super(true, fullScreen, winLayout);
        Tk();
    }

    private void Tk() {
        getWindow().setType(2003);
        getWindow().setFlags(1024, 1024);
        getWindow().addFlags(285212736);
        T();
        setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.button.back", (byte[]) null, (Tn.Tr) null);
                TZ.this.dismiss();
            }
        });
        this.Tv = new com.txznet.comm.Ty.Ty(getContext());
    }

    public void T(int type) {
        getWindow().setType(type);
    }

    public void Tr(int flags) {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags = flags;
        getWindow().setAttributes(attrs);
    }

    public void T() {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        if (!(Ty > 0 || Ty == -1 || Ty == -2)) {
            Ty = -1;
        }
        if (!(Tn > 0 || Tn == -1 || Tn == -2)) {
            Tn = -1;
        }
        layoutParams.x = f507T;
        layoutParams.y = Tr;
        layoutParams.width = Ty;
        layoutParams.height = Tn;
        if (!(Ty == -1 && Tn == -1)) {
            layoutParams.flags = 32;
            getWindow().setGravity(51);
        }
        if (T9 != null) {
            setCancelable(T9.booleanValue());
        }
        getWindow().setAttributes(layoutParams);
    }

    public void T(boolean isFullScreen) {
        if (this.TZ != isFullScreen) {
            com.txznet.comm.Tr.Tr.Tn.T("setIsFullScreenDialog:" + isFullScreen);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        com.txznet.comm.Tr.Tr.Tn.T("onKeyDown:" + keyCode);
        return Tk.Tr().T(keyCode);
    }

    /* access modifiers changed from: protected */
    public View T(Object... objects) {
        int width;
        if (objects.length > 0) {
            this.Th = objects[0];
        }
        if (this.Th == null) {
            com.txznet.comm.ui.Tn.T.Tk.Tk().T9();
            this.Th = com.txznet.comm.ui.Tn.T.Tk.Tk();
        }
        View windowView = null;
        if (this.Th != null) {
            windowView = this.Th.Tn();
        }
        int layoutLeft = T.Te().intValue();
        int layoutTop = T.Tq().intValue();
        int layoutRight = T.TF().intValue();
        int layoutBottom = T.Tj().intValue();
        if (T6 <= 0 && layoutLeft == 0 && layoutTop == 0 && layoutRight == 0 && layoutBottom == 0) {
            return windowView;
        }
        RelativeLayout llLayout = new RelativeLayout(getContext());
        llLayout.setPadding(layoutLeft, layoutTop, layoutRight, layoutBottom);
        if (T6 > 0) {
            width = T6;
        } else {
            width = -1;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, -1);
        layoutParams.addRule(13);
        llLayout.addView(windowView, layoutParams);
        com.txznet.comm.Tr.Tr.Tn.T("layout padding left::" + layoutLeft + " top::" + layoutTop + " right::" + layoutRight + " bottom::" + layoutBottom + " contentWidth::" + T6);
        return llLayout;
    }

    public void T(Float winBgAlpha) {
        if (winBgAlpha.floatValue() >= 0.0f && winBgAlpha.floatValue() <= 1.0f) {
            Tq = winBgAlpha;
            com.txznet.comm.Tr.Tr.Tn.Tr("setWinBgAlpha:" + winBgAlpha);
        }
    }

    public void Tr() {
        com.txznet.comm.Tr.Tr.Tn.T("[UI2.0] init RecordWin2True");
        T();
        T.T(getWindow().getDecorView());
        T.Tr(getWindow().getDecorView());
    }

    public void T(com.txznet.comm.ui.Tn.Ty winLayout) {
        com.txznet.comm.Tr.Tr.Tn.T("[UI2.0] update winLayout");
        if (winLayout != null && !winLayout.equals(this.Th)) {
            this.Th = winLayout;
            this.Tk = this.Th.Tn();
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
            T();
        }
    }

    public void show() {
        com.txznet.comm.Tr.Tr.Tn.T("RecordWin2True show");
        if (!this.TF) {
            this.TF = true;
            com.txznet.comm.ui.T.T().registerObserver(this.TB);
            com.txznet.comm.ui.T.Tr().registerObserver(this.TK);
            if (this.TO != null) {
                try {
                    com.txznet.comm.ui.T.Ty().registerObserver(this.TO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (!this.Tj) {
            if (this.Tv == null) {
                this.Tv = new com.txznet.comm.Ty.Ty(getContext());
            }
            this.Tv.T();
            this.Tj = true;
        }
        String background = T.T6();
        if (TextUtils.isEmpty(background)) {
            background = "dialog_bg";
        }
        com.txznet.comm.Tr.Tr.Tn.T("dialog_background_name::" + background);
        if (Te || this.Tk == null) {
            getWindow().setBackgroundDrawable(com.txznet.comm.ui.TE.Tr.Ty(background));
        } else {
            this.Tk.setBackgroundDrawable(com.txznet.comm.ui.TE.Tr.Ty(background));
        }
        if (this.Tk == null) {
            T.Tr(getWindow().getDecorView());
        } else {
            T.Tr(this.Tk);
        }
        if (Tq != null) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.alpha = Tq.floatValue();
            getWindow().setAttributes(layoutParams);
        }
        com.txznet.comm.ui.Tn.Tn.T().T(0);
        super.show();
        TZ();
    }

    private void TZ() {
    }

    public void dismiss() {
        com.txznet.comm.Tr.Tr.Tn.T("RecordWin2True dismiss");
        com.txznet.comm.Tr.Tr.T.T9();
        if (this.Tj) {
            this.Tv.Tr();
            this.Tj = false;
        }
        Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.dismiss", (byte[]) null, (Tn.Tr) null);
        if (Looper.myLooper() != Looper.getMainLooper()) {
            com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                public void run() {
                    TZ.super.dismiss();
                    TZ.this.TE();
                    if (TZ.this.Th != null) {
                        TZ.this.Th.T();
                    }
                }
            }, 0);
            return;
        }
        super.dismiss();
        TE();
        if (this.Th != null) {
            this.Th.T();
        }
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
            } else {
                this.Tk.setSystemUiVisibility(0);
            }
        }
        T();
    }

    public void T(Tk.T observer) {
        this.TO = observer;
    }

    /* access modifiers changed from: private */
    public void TE() {
        if (this.TF) {
            com.txznet.comm.ui.T.Ty().Ty();
            com.txznet.comm.ui.T.T().unregisterObserver(this.TB);
            com.txznet.comm.ui.T.Tr().unregisterObserver(this.TK);
            if (this.TO != null) {
                try {
                    com.txznet.comm.ui.T.Ty().unregisterObserver(this.TO);
                } catch (Exception e) {
                }
            }
            this.TF = false;
        }
    }

    public void Ty() {
        this.Th.Tr();
        this.Tk = null;
        setContentView(new TextView(getContext()));
        if (this.TF) {
            com.txznet.comm.ui.T.T().unregisterObserver(this.TB);
            com.txznet.comm.ui.T.Tr().unregisterObserver(this.TK);
            if (this.TO != null) {
                try {
                    com.txznet.comm.ui.T.Ty().unregisterObserver(this.TO);
                } catch (Exception e) {
                }
            }
            this.TF = false;
        }
        if (com.Ty.T.Tr.Tn.T().Tr()) {
            com.Ty.T.Tr.Tn.T().Tn();
            com.Ty.T.Tr.Tn.T().Ty();
        }
        com.txznet.comm.ui.TE.Tr.T();
        System.gc();
    }
}
