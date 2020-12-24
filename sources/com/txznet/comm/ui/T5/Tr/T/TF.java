package com.txznet.comm.ui.T5.Tr.T;

import android.graphics.Bitmap;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.Tr.Tr.TE;
import com.txznet.comm.Tr.T;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.Tt;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.txz.util.Tk;

/* compiled from: Proguard */
public class TF extends Tt {
    private static TF TZ = new TF();
    LinearLayout T9;
    LinearLayout Tk;
    ImageView Tn;

    private TF() {
    }

    public static TF T9() {
        return TZ;
    }

    public Tn.T T(TM data) {
        com.txznet.comm.ui.T5.T.Tt viewData = (com.txznet.comm.ui.T5.T.Tt) data;
        this.T9 = new LinearLayout(T.Tr());
        this.T9.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        this.T9.setGravity(1);
        this.Tk = new LinearLayout(T.Tr());
        this.Tk.setLayoutParams(new AbsListView.LayoutParams(-2, -2));
        this.Tk.setBackgroundDrawable(Tr.Ty("shape_qrcode_bg"));
        this.Tk.setGravity(1);
        int paddingOut = (int) Tr.Tn("y15");
        this.Tk.setPadding(paddingOut, paddingOut, paddingOut, paddingOut);
        this.Tn = new ImageView(T.Tr());
        this.Tn.setLayoutParams(new AbsListView.LayoutParams(-2, -2));
        this.Tn.setBackgroundColor(-1);
        this.Tn.setScaleType(ImageView.ScaleType.CENTER);
        int paddingIn = (int) Tr.Tn("y5");
        this.Tn.setPadding(paddingIn, paddingIn, paddingIn, paddingIn);
        this.T9.addView(this.Tk);
        this.Tk.addView(this.Tn);
        try {
            final Bitmap bitmap = Tk.T(viewData.f456T, (int) Tr.Tn("y150"));
            com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                public void run() {
                    if (TF.this.Tn != null && bitmap != null) {
                        TF.this.Tn.setImageBitmap(bitmap);
                        int padding = (int) Tr.Tn("y5");
                        TF.this.Tn.setPadding(padding, padding, padding, padding);
                    }
                }
            }, 0);
        } catch (TE e) {
        }
        Tn.T adapter = new Tn.T();
        adapter.f462T = data.Ty();
        adapter.Tr = this.T9;
        adapter.Tn = T9();
        return adapter;
    }

    public void Tr() {
        super.Tr();
    }
}
