package com.txznet.comm.ui.Tn.T;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.Tn.T;

/* compiled from: Proguard */
public class Ty extends T {

    /* renamed from: T  reason: collision with root package name */
    boolean f557T = false;
    private Context Tn;
    private RelativeLayout Tr;
    private RelativeLayout.LayoutParams Ty;

    public Ty(Context context) {
        this.Tr = new RelativeLayout(context);
        this.Tn = context;
        this.Ty = new RelativeLayout.LayoutParams(-1, -1);
    }

    public void T(View view) {
        this.Tr.addView(view, this.Ty);
    }

    public View T() {
        return this.Tr;
    }

    public void Tr() {
        Tn.T("removeAllViews");
        this.Tr.removeAllViews();
        this.Tr.requestLayout();
    }
}
