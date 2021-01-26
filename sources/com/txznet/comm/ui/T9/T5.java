package com.txznet.comm.ui.T9;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.txznet.comm.Tr.T;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.Ty.Ty;

/* compiled from: Proguard */
public abstract class T5 extends Dialog {
    public static int T5 = 260;

    /* renamed from: T  reason: collision with root package name */
    private boolean f505T;
    protected boolean TE;
    protected boolean TZ;
    protected View Tk;
    private boolean Tr;
    protected Ty Tv;

    /* access modifiers changed from: protected */
    public abstract View T(Object... objArr);

    public T5(Context context) {
        this(context, false);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T5() {
        /*
            r2 = this;
            com.txznet.comm.base.T r0 = com.txznet.comm.base.T.T()
            android.app.Activity r0 = r0.Ty()
            if (r0 != 0) goto L_0x0025
            android.content.Context r0 = com.txznet.comm.Tr.T.Tr()
        L_0x000e:
            r2.<init>(r0)
            com.txznet.comm.base.T r0 = com.txznet.comm.base.T.T()
            android.app.Activity r0 = r0.Ty()
            if (r0 != 0) goto L_0x0024
            android.view.Window r0 = r2.getWindow()
            r1 = 2002(0x7d2, float:2.805E-42)
            r0.setType(r1)
        L_0x0024:
            return
        L_0x0025:
            com.txznet.comm.base.T r0 = com.txznet.comm.base.T.T()
            android.app.Activity r0 = r0.Ty()
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.ui.T9.T5.<init>():void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T5(android.content.Context r5, boolean r6) {
        /*
            r4 = this;
            r3 = 0
            if (r6 == 0) goto L_0x004a
            int r2 = com.txznet.txz.comm.R.style.TXZ_Dialog_Style_Full
        L_0x0005:
            r4.<init>(r5, r2)
            r4.TE = r3
            r4.Tr = r3
            r4.TZ = r6
            r2 = 1
            r4.requestWindowFeature(r2)
            java.lang.Object[] r2 = new java.lang.Object[r3]
            android.view.View r2 = r4.T((java.lang.Object[]) r2)
            r4.Tk = r2
            android.view.View r2 = r4.Tk
            android.view.ViewParent r0 = r2.getParent()
            if (r0 == 0) goto L_0x002d
            boolean r2 = r0 instanceof android.view.ViewGroup
            if (r2 == 0) goto L_0x002d
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            android.view.View r2 = r4.Tk
            r0.removeView(r2)
        L_0x002d:
            if (r6 == 0) goto L_0x004d
            android.view.View r2 = r4.Tk
            int r3 = T5
            r2.setSystemUiVisibility(r3)
            android.view.Window r2 = r4.getWindow()
            android.view.WindowManager$LayoutParams r1 = r2.getAttributes()
            int r2 = r1.flags
            r2 = r2 | 1024(0x400, float:1.435E-42)
            r1.flags = r2
        L_0x0044:
            android.view.View r2 = r4.Tk
            r4.setContentView(r2)
            return
        L_0x004a:
            int r2 = com.txznet.txz.comm.R.style.TXZ_Dialog_Style
            goto L_0x0005
        L_0x004d:
            android.view.View r2 = r4.Tk
            r2.setSystemUiVisibility(r3)
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.ui.T9.T5.<init>(android.content.Context, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T5(android.content.Context r5, boolean r6, java.lang.Object... r7) {
        /*
            r4 = this;
            r3 = 0
            if (r6 == 0) goto L_0x0048
            int r2 = com.txznet.txz.comm.R.style.TXZ_Dialog_Style_Full
        L_0x0005:
            r4.<init>(r5, r2)
            r4.TE = r3
            r4.Tr = r3
            r4.TZ = r6
            r2 = 1
            r4.requestWindowFeature(r2)
            android.view.View r2 = r4.T((java.lang.Object[]) r7)
            r4.Tk = r2
            android.view.View r2 = r4.Tk
            android.view.ViewParent r0 = r2.getParent()
            if (r0 == 0) goto L_0x002b
            boolean r2 = r0 instanceof android.view.ViewGroup
            if (r2 == 0) goto L_0x002b
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            android.view.View r2 = r4.Tk
            r0.removeView(r2)
        L_0x002b:
            if (r6 == 0) goto L_0x004b
            android.view.View r2 = r4.Tk
            int r3 = T5
            r2.setSystemUiVisibility(r3)
            android.view.Window r2 = r4.getWindow()
            android.view.WindowManager$LayoutParams r1 = r2.getAttributes()
            int r2 = r1.flags
            r2 = r2 | 1024(0x400, float:1.435E-42)
            r1.flags = r2
        L_0x0042:
            android.view.View r2 = r4.Tk
            r4.setContentView(r2)
            return
        L_0x0048:
            int r2 = com.txznet.txz.comm.R.style.TXZ_Dialog_Style
            goto L_0x0005
        L_0x004b:
            android.view.View r2 = r4.Tk
            r2.setSystemUiVisibility(r3)
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.ui.T9.T5.<init>(android.content.Context, boolean, java.lang.Object[]):void");
    }

    public void T(boolean isFullScreen) {
        Tn.T("setIsFullScreenDialog:" + isFullScreen);
        this.TZ = isFullScreen;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public T5(boolean isSystem, boolean isFullScreen) {
        this(isSystem ? T.Tr() : com.txznet.comm.base.T.T().Ty(), isFullScreen);
        if (isSystem) {
            getWindow().setType(2002);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public T5(boolean isSystem, boolean isFullScreen, Object... objects) {
        this(isSystem ? T.Tr() : com.txznet.comm.base.T.T().Ty(), isFullScreen, objects);
        if (isSystem) {
            getWindow().setType(2002);
        }
    }

    public void show() {
        super.show();
        if (this.f505T && this.Tv != null) {
            this.Tv.T();
        }
        this.Tr = true;
        T9();
        getContext().sendBroadcast(new Intent("com.txznet.txz.action.FLOAT_WIN_SHOW"));
        if (this.TZ && (getWindow().getDecorView().getSystemUiVisibility() & 4) == 0) {
            this.Tk.setSystemUiVisibility(T5);
        }
    }

    public void dismiss() {
        this.Tr = false;
        Tn();
        super.dismiss();
        if (this.Tv != null) {
            this.Tv.Tr();
        }
        getContext().sendBroadcast(new Intent("com.txznet.txz.action.FLOAT_WIN_DISMISS"));
    }

    public void onWindowFocusChanged(boolean newFocus) {
        Tn.T(toString() + " onWindowFocusChanged: from " + this.Tr + " to " + newFocus);
        if (this.Tr != newFocus) {
            this.Tr = newFocus;
            if (this.Tr) {
                T9();
            } else {
                Tn();
            }
        }
        super.onWindowFocusChanged(newFocus);
    }

    /* access modifiers changed from: protected */
    public void Tn() {
    }

    /* access modifiers changed from: protected */
    public void T9() {
    }
}
