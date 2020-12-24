package com.txznet.comm.ui.Tn.T;

import android.view.View;
import android.view.ViewGroup;
import com.txznet.comm.ui.Tn.Ty;

/* compiled from: Proguard */
public class Tn extends Ty {
    private static Tn Tr = new Tn();
    private View Tn;
    private T9 Ty;

    private Tn() {
    }

    public static Tn Tk() {
        return Tr;
    }

    private void TZ() {
        this.Ty = new T9();
        this.Ty.T9();
        if (this.Tn != null) {
            this.Ty.T(this.Tn);
        }
    }

    private void TE() {
        if (this.Tn != null) {
            this.Ty.T(this.Tn);
        }
    }

    public void Tr() {
        if (this.Ty != null) {
            this.Ty.T();
            this.Ty.Tr();
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

    public void T() {
        this.Ty.T();
    }

    public void T9() {
        super.T9();
        T9.Tk();
    }

    public void T(View recordView) {
        this.Tn = recordView;
        if (this.Ty == null) {
            TZ();
        }
        this.Ty.T(this.Tn);
    }

    public View Tn() {
        if (this.Ty == null) {
            TZ();
            TE();
        }
        return this.Ty.Tn();
    }

    public Object Ty() {
        if (this.Ty == null) {
            return null;
        }
        return this.Ty.Ty();
    }
}
