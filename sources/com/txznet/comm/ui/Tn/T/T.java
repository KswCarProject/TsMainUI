package com.txznet.comm.ui.Tn.T;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/* compiled from: Proguard */
public class T extends com.txznet.comm.ui.Tn.T {

    /* renamed from: T  reason: collision with root package name */
    private LinearLayout.LayoutParams f550T = new LinearLayout.LayoutParams(-1, -1);
    private LinearLayout Tr;
    private Tr Ty;

    public T(Context context) {
        this.Ty = new Tr(context);
        this.Tr = new LinearLayout(context);
        this.Tr.setBackgroundColor(0);
        this.Tr.addView(this.Ty.T(), this.f550T);
    }

    public void T(View view) {
        this.Ty.T(view);
        this.Ty.Tr();
    }

    public View T() {
        return this.Tr;
    }

    public void Tr() {
        this.Ty.Ty();
    }

    public void Ty() {
        this.Ty.Tn();
    }
}
