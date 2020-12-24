package com.txznet.comm.ui.Tn.T;

import android.view.View;
import android.view.ViewGroup;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.Tn.Ty;

/* compiled from: Proguard */
public class Tk extends Ty {
    private static final String T9 = Tk.class.getSimpleName();
    private static Tk Tr = new Tk();
    private View Tn;
    private TZ Ty;

    private Tk() {
    }

    public static Tk Tk() {
        return Tr;
    }

    private void TZ() {
        this.Ty = new TZ();
        this.Ty.T9();
    }

    private void TE() {
        if (this.Tn != null) {
            this.Ty.T(this.Tn);
        }
    }

    public void Tr() {
        Tn.T(T9 + " release");
        if (this.Ty != null) {
            this.Ty.T();
            this.Ty = null;
            this.Tn = null;
        }
    }

    public Object T(int targetView, View view, ViewGroup.LayoutParams layoutParams) {
        if (this.Ty == null) {
            TZ();
        }
        return this.Ty.T(targetView, view, layoutParams);
    }

    public void T9() {
        super.T9();
        TZ.Tk();
    }

    public void T() {
        this.Ty.T();
    }

    public void T(View recordView) {
        this.Tn = recordView;
        if (this.Ty == null) {
            TZ();
        }
        this.Ty.T(this.Tn);
    }

    public Object Ty() {
        if (this.Ty == null) {
            return null;
        }
        return this.Ty.Ty();
    }

    public View Tn() {
        if (this.Ty == null) {
            TZ();
            TE();
        }
        return this.Ty.Tn();
    }
}
