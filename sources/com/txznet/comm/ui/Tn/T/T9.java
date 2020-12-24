package com.txznet.comm.ui.Tn.T;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.TE.T;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.comm.ui.Tn.Ty;

/* compiled from: Proguard */
public class T9 extends Ty {
    public static Integer Tr = null;
    private RelativeLayout.LayoutParams T5;
    private LinearLayout.LayoutParams T9;
    /* access modifiers changed from: private */
    public Ty TE;
    private RelativeLayout.LayoutParams TZ;
    private LinearLayout.LayoutParams Th;
    /* access modifiers changed from: private */
    public T Tk;
    private RelativeLayout Tn;
    private FrameLayout Tv;
    private LinearLayout.LayoutParams Ty;

    public static void Tk() {
        Tn.T("initHeight:" + T.Th());
        if (T.Th() != null) {
            Tr = T.Th();
        }
    }

    public Object T(int targetView, View view, ViewGroup.LayoutParams layoutParams) {
        switch (targetView) {
            case 10:
                this.Tk.T().setVisibility(0);
                this.TE.T().setVisibility(8);
                this.Tk.T(view);
                return null;
            case 20:
                this.TE.Tr();
                this.TE.T(view);
                this.Tk.T().setVisibility(8);
                this.TE.T().setVisibility(0);
                return null;
            case 30:
                this.Tv.removeAllViews();
                this.Tv.addView(view);
                return null;
            default:
                return null;
        }
    }

    public void T9() {
        super.T9();
        if (this.f557T == null) {
            this.f557T = new LinearLayout(com.txznet.comm.Tr.T.Tr());
            this.Ty = new LinearLayout.LayoutParams(-1, -1);
            this.f557T.setLayoutParams(this.Ty);
            this.f557T.setOrientation(1);
            this.Tn = new RelativeLayout(com.txznet.comm.Tr.T.Tr());
            this.T9 = new LinearLayout.LayoutParams(-1, 0, 1.0f);
            this.Tn.setPadding((int) Tr.Tn("x24"), 0, (int) Tr.Tn("x24"), 0);
            this.Tk = new T(com.txznet.comm.Tr.T.Tr());
            this.TZ = new RelativeLayout.LayoutParams(-1, -1);
            this.Tn.addView(this.Tk.T(), this.TZ);
            this.TE = new Ty(com.txznet.comm.Tr.T.Tr());
            this.T5 = new RelativeLayout.LayoutParams(-1, -1);
            this.Tn.addView(this.TE.T(), this.T5);
            this.f557T.addView(this.Tn, this.T9);
            this.Tv = new FrameLayout(com.txznet.comm.Tr.T.Tr());
            if (Tr != null) {
                this.Th = new LinearLayout.LayoutParams(-1, Tr.intValue());
            } else {
                this.Th = new LinearLayout.LayoutParams(-1, (int) Tr.Tn("y80"));
            }
            this.f557T.addView(this.Tv, this.Th);
        } else if (this.Tn != null) {
            this.Tn.setPadding((int) Tr.Tn("x24"), 0, (int) Tr.Tn("x24"), 0);
        }
    }

    public void T() {
        if (this.TE != null && this.Tk != null) {
            com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                public void run() {
                    T9.this.TE.Tr();
                    T9.this.Tk.Tr();
                }
            }, 0);
        }
    }

    public void T(View recordView) {
        if (this.Tv != null) {
            this.Tv.removeAllViews();
            this.Tv.addView(recordView);
        }
    }

    public void Tr() {
    }

    public Object Ty() {
        this.Tk.Ty();
        return null;
    }
}
