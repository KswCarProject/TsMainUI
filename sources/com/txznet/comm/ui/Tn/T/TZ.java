package com.txznet.comm.ui.Tn.T;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.ts.bt.ContactInfo;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.TE.T;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.comm.ui.Tn.Ty;

/* compiled from: Proguard */
public class TZ extends Ty {
    public static int Tr;
    public static int Ty;
    private Ty T5;
    private LinearLayout.LayoutParams T6;
    private FrameLayout T9;
    private FrameLayout.LayoutParams TE;
    private T TZ;
    private FrameLayout Th;
    private LinearLayout.LayoutParams Tk;
    private LinearLayout.LayoutParams Tn;
    private FrameLayout.LayoutParams Tv;

    public static void Tk() {
        Tr = T.T5().intValue();
        Ty = T.Tv().intValue();
        Tn.T("initWeight:" + Tr + ContactInfo.COMBINATION_SEPERATOR + Ty);
    }

    public Object T(int targetView, View view, ViewGroup.LayoutParams layoutParams) {
        switch (targetView) {
            case 10:
                this.TZ.T().setVisibility(0);
                this.T5.T().setVisibility(8);
                this.T5.Tr();
                this.TZ.T(view);
                return null;
            case 20:
                this.T5.Tr();
                this.T5.T(view);
                this.TZ.T().setVisibility(8);
                this.T5.T().setVisibility(0);
                return null;
            case 30:
                this.Th.removeAllViews();
                this.Th.addView(view);
                return null;
            default:
                return null;
        }
    }

    public void T9() {
        super.T9();
        if (this.f561T == null) {
            Tn.T("init weightRecord:" + Tr + ",weightContent:" + Ty);
            this.f561T = new LinearLayout(com.txznet.comm.Tr.T.Tr());
            this.Tn = new LinearLayout.LayoutParams(-1, -1);
            this.f561T.setLayoutParams(this.Tn);
            this.f561T.setOrientation(0);
            this.f561T.setWeightSum((float) (Tr + Ty));
            this.Th = new FrameLayout(com.txznet.comm.Tr.T.Tr());
            this.T6 = new LinearLayout.LayoutParams(0, -1, (float) Tr);
            this.f561T.addView(this.Th, this.T6);
            this.T9 = new FrameLayout(com.txznet.comm.Tr.T.Tr());
            this.Tk = new LinearLayout.LayoutParams(0, -1, (float) Ty);
            this.T9.setPadding((int) Tr.Tn("x24"), 0, (int) Tr.Tn("x24"), 0);
            this.TZ = new T(com.txznet.comm.Tr.T.Tr());
            this.TE = new FrameLayout.LayoutParams(-1, -1);
            this.T9.addView(this.TZ.T(), this.TE);
            this.T5 = new Ty(com.txznet.comm.Tr.T.Tr());
            this.Tv = new FrameLayout.LayoutParams(-1, -1);
            this.T9.addView(this.T5.T(), this.Tv);
            this.f561T.addView(this.T9, this.Tk);
        } else if (this.T9 != null) {
            this.T9.setPadding((int) Tr.Tn("x24"), 0, (int) Tr.Tn("x24"), 0);
        }
    }

    public void T() {
        if (this.T5 != null && this.TZ != null) {
            this.T5.Tr();
            this.TZ.Tr();
        }
    }

    public void T(View recordView) {
        if (this.Th != null) {
            this.Th.removeAllViews();
            this.Th.addView(recordView);
        }
    }

    public Object Ty() {
        this.TZ.Ty();
        return null;
    }

    public void Tr() {
    }
}
