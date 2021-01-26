package com.txznet.comm.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.txznet.comm.Tr.T;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.Ty.Ty;
import com.txznet.comm.ui.IKeepClass;

/* compiled from: Proguard */
public abstract class WinDialog extends Dialog implements IKeepClass {
    public static int T5 = -1;
    public static int TE = -1;
    public static int Tv = 260;

    /* renamed from: T  reason: collision with root package name */
    private Object f570T;
    protected Ty T6;
    protected boolean TZ;
    protected Runnable Th;
    protected View Tk;
    private boolean Tr;
    private boolean Ty;

    /* access modifiers changed from: protected */
    public abstract View T();

    public WinDialog(Context context) {
        this(context, false, false);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WinDialog() {
        /*
            r1 = this;
            com.txznet.comm.base.T r0 = com.txznet.comm.base.T.T()
            android.app.Activity r0 = r0.Ty()
            if (r0 != 0) goto L_0x0012
            android.content.Context r0 = com.txznet.comm.Tr.T.Ty()
        L_0x000e:
            r1.<init>((android.content.Context) r0)
            return
        L_0x0012:
            com.txznet.comm.base.T r0 = com.txznet.comm.base.T.T()
            android.app.Activity r0 = r0.Ty()
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.ui.dialog.WinDialog.<init>():void");
    }

    public WinDialog(Context context, boolean fullScreen) {
        this(context, fullScreen, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WinDialog(boolean isSystem) {
        this(isSystem ? T.Ty() : com.txznet.comm.base.T.T().Ty(), false, isSystem);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WinDialog(boolean isSystem, boolean isFullScreen) {
        this(isSystem ? T.Ty() : com.txznet.comm.base.T.T().Ty(), isFullScreen, isSystem);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WinDialog(android.content.Context r5, boolean r6, boolean r7) {
        /*
            r4 = this;
            r3 = 2007(0x7d7, float:2.812E-42)
            r2 = -1
            if (r6 == 0) goto L_0x0059
            int r0 = com.txznet.txz.comm.R.style.TXZ_Dialog_Style_Full
        L_0x0007:
            r4.<init>(r5, r0)
            com.txznet.comm.ui.dialog.WinDialog$1 r0 = new com.txznet.comm.ui.dialog.WinDialog$1
            r0.<init>()
            r4.Th = r0
            r0 = 0
            r4.Ty = r0
            android.view.View r0 = r4.T()
            r4.Tk = r0
            r4.TZ = r6
            if (r6 == 0) goto L_0x0025
            android.view.View r0 = r4.Tk
            int r1 = Tv
            r0.setSystemUiVisibility(r1)
        L_0x0025:
            android.view.View r0 = r4.Tk
            r4.setContentView(r0)
            com.txznet.comm.base.T r0 = com.txznet.comm.base.T.T()
            android.app.Activity r0 = r0.Ty()
            if (r0 != 0) goto L_0x003b
            android.view.Window r0 = r4.getWindow()
            r0.setType(r3)
        L_0x003b:
            if (r7 == 0) goto L_0x0044
            android.view.Window r0 = r4.getWindow()
            r0.setType(r3)
        L_0x0044:
            int r0 = TE
            if (r0 == r2) goto L_0x0051
            android.view.Window r0 = r4.getWindow()
            int r1 = TE
            r0.setType(r1)
        L_0x0051:
            android.view.Window r0 = r4.getWindow()
            r0.setLayout(r2, r2)
            return
        L_0x0059:
            int r0 = com.txznet.txz.comm.R.style.TXZ_Dialog_Style
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.ui.dialog.WinDialog.<init>(android.content.Context, boolean, boolean):void");
    }

    public void checkTimeout() {
        if (T5 != -1) {
            Tn.T("winDialog checkTimeout:" + T5);
            if (T5 > 0) {
                com.txznet.T.T.Tn(this.Th);
                com.txznet.T.T.Ty(this.Th, (long) T5);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void Tn() {
        if (isShowing()) {
            dismiss();
        }
    }

    public void setIsFullSreenDialog(boolean isFullScreen) {
        Tn.T("setIsFullScreenDialog:" + isFullScreen);
        this.TZ = isFullScreen;
    }

    public void updateDialogType(int type) {
        Tn.T("updateDialogType type:" + type);
        getWindow().setType(type);
    }

    public WinDialog setData(Object data) {
        this.f570T = data;
        return this;
    }

    public Object getData() {
        return this.f570T;
    }

    public void requestScreenLock() {
        if (this.T6 == null) {
            this.T6 = new Ty(getContext());
        }
        this.Tr = true;
    }

    public void cancelScreenLock() {
        if (this.T6 != null) {
            this.T6.Tr();
            this.T6 = null;
        }
    }

    public void show() {
        super.show();
        if (this.Tr && this.T6 != null) {
            this.T6.T();
        }
        this.Ty = true;
        Tr();
        getContext().sendBroadcast(new Intent("com.txznet.txz.action.FLOAT_WIN_SHOW"));
        if (this.TZ && (getWindow().getDecorView().getSystemUiVisibility() & 4) == 0) {
            this.Tk.setSystemUiVisibility(Tv);
        }
    }

    public void dismiss() {
        this.Ty = false;
        Ty();
        super.dismiss();
        if (this.T6 != null) {
            this.T6.Tr();
        }
        com.txznet.T.T.Tn(this.Th);
        getContext().sendBroadcast(new Intent("com.txznet.txz.action.FLOAT_WIN_DISMISS"));
    }

    public boolean hasFocus() {
        return this.Ty;
    }

    public void onWindowFocusChanged(boolean newFocus) {
        Tn.T(toString() + " onWindowFocusChanged: from " + this.Ty + " to " + newFocus);
        if (this.Ty != newFocus) {
            this.Ty = newFocus;
            if (this.Ty) {
                Tr();
            } else {
                Ty();
            }
        }
        super.onWindowFocusChanged(newFocus);
    }

    /* access modifiers changed from: protected */
    public void Ty() {
    }

    /* access modifiers changed from: protected */
    public void Tr() {
    }
}
