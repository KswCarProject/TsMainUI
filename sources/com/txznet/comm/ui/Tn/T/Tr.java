package com.txznet.comm.ui.Tn.T;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;
import com.txznet.comm.ui.Tn.T;

/* compiled from: Proguard */
public class Tr extends T {

    /* renamed from: T  reason: collision with root package name */
    private ListView f556T;
    private com.txznet.comm.ui.T.T Tr = new com.txznet.comm.ui.T.T();
    private Context Ty;

    public Tr(Context context) {
        this.Ty = context;
        this.f556T = new ListView(context);
        this.f556T.setDivider(new ColorDrawable(-7829368));
        this.f556T.setSelector(new ColorDrawable(0));
        this.f556T.setVerticalScrollBarEnabled(false);
        this.f556T.setAdapter(this.Tr);
    }

    public void T(View view) {
        this.Tr.T(view);
    }

    public View T() {
        return this.f556T;
    }

    public void Tr() {
        this.f556T.setSelection(this.Tr.getCount() - 1);
        this.f556T.requestLayout();
    }

    public void Ty() {
        this.Tr.T();
        this.Tr = new com.txznet.comm.ui.T.T();
        this.f556T.setAdapter(this.Tr);
    }

    public void Tn() {
        this.Tr.Tr();
    }
}
