package com.txznet.comm.ui.T5.Tr.T;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.TO;

/* compiled from: Proguard */
public class Tk extends TO {
    public static int T5 = 4;
    public static int TE = 3;
    private static Tk TF = new Tk();
    public static int TZ = 2;
    public static int Tk = 1;
    public static int Tv = 5;
    LinearLayout.LayoutParams T6 = null;
    LinearLayout Te = null;
    LinearLayout Th = null;
    /* access modifiers changed from: private */
    public T Tj = null;
    ImageView Tq = null;

    /* compiled from: Proguard */
    public interface T {
        Tn.T T(com.txznet.comm.ui.T5.T.TO to);

        void T();

        void T(int i);

        void T(TM tm);

        void T(Tr tr);
    }

    /* compiled from: Proguard */
    public interface Tr {
        void T(int i);
    }

    private Tk() {
        this.Ty = 0;
        this.Ty = Integer.valueOf(this.Ty.intValue() | 1);
    }

    public static Tk T9() {
        return TF;
    }

    public Tn.T T(TM data) {
        Tn.T adapter = new Tn.T();
        adapter.Tk = 0;
        adapter.Tn = T9();
        adapter.f462T = 19;
        adapter.Tr = Ty(data);
        return adapter;
    }

    private View Ty(TM data) {
        com.txznet.comm.ui.T5.T.TO mapPoiViewData = (com.txznet.comm.ui.T5.T.TO) data;
        Tn.T titleViewAdapter = T6.T().T((TM) mapPoiViewData);
        this.Tn = mapPoiViewData.Tn.Tk;
        this.T9 = mapPoiViewData.Tn.TZ;
        LinearLayout llLayout = new LinearLayout(com.txznet.comm.Tr.T.Tr());
        llLayout.setGravity(16);
        llLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, T6.T().T5());
        layoutParams.gravity = 16;
        llLayout.addView(titleViewAdapter.Tr, layoutParams);
        int contentHeight = com.txznet.comm.ui.TE.Tn.f535T;
        RelativeLayout rlLayout = new RelativeLayout(com.txznet.comm.Tr.T.Tr());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, contentHeight);
        llLayout.addView(rlLayout, layoutParams2);
        Tn.T mapViewAdapter = this.Tj.T(mapPoiViewData);
        rlLayout.addView(mapViewAdapter.Tr, new RelativeLayout.LayoutParams(-1, -1));
        if (com.txznet.comm.ui.TE.Tn.T()) {
            this.Th = new LinearLayout(com.txznet.comm.Tr.T.Tr());
            this.Th.setOrientation(1);
            new RelativeLayout.LayoutParams(-1, -1);
            rlLayout.addView(this.Th, layoutParams2);
            Tn.T listViewAdapter = Tq.T9().T(data);
            this.T6 = new LinearLayout.LayoutParams(-1, 0, 1.0f);
            this.Th.addView(listViewAdapter.Tr, this.T6);
            View view = new View(com.txznet.comm.Tr.T.Tr());
            this.T6 = new LinearLayout.LayoutParams(-1, 0, 1.0f);
            this.Th.addView(view, this.T6);
        } else {
            this.Th = new LinearLayout(com.txznet.comm.Tr.T.Tr());
            this.Th.setOrientation(0);
            new RelativeLayout.LayoutParams(-1, -1);
            rlLayout.addView(this.Th, layoutParams2);
            Tn.T listViewAdapter2 = Tq.T9().T(data);
            this.T6 = new LinearLayout.LayoutParams(0, -1, 1.0f);
            this.Th.addView(listViewAdapter2.Tr, this.T6);
            View view2 = new View(com.txznet.comm.Tr.T.Tr());
            this.T6 = new LinearLayout.LayoutParams(0, -1, 1.0f);
            this.Th.addView(view2, this.T6);
        }
        this.Te = new LinearLayout(com.txznet.comm.Tr.T.Tr());
        this.Te.setGravity(17);
        this.Te.setOrientation(1);
        this.Te.setVisibility(8);
        this.Te.setBackgroundColor(Color.argb(204, 0, 0, 0));
        new RelativeLayout.LayoutParams(-1, -1);
        rlLayout.addView(this.Te, layoutParams2);
        this.Tq = new ImageView(com.txznet.comm.Tr.T.Tr());
        this.Tq.setImageDrawable(com.txznet.comm.ui.TE.Tr.Ty("poimap_loading_anim"));
        this.Te.addView(this.Tq, new LinearLayout.LayoutParams(-2, -2));
        Tq.T9().T((Tr) new Tr() {
            public void T(int index) {
                Tk.this.Tj.T(index);
            }
        });
        this.Tj.T((Tr) new Tr() {
            public void T(int index) {
                Tq.T9().T(index);
            }
        });
        return llLayout;
    }

    public void Tr() {
        super.Tr();
        Tq.T9().Tr();
        T6.T().Tr();
    }

    public void Ty() {
        this.Tj.T();
        T6.T().T9();
        this.Th = null;
        this.T6 = null;
        this.Tq = null;
        this.Te = null;
        super.Ty();
    }

    public Object Tr(TM data) {
        this.Te.setVisibility(8);
        com.txznet.comm.ui.T5.T.TO viewData = (com.txznet.comm.ui.T5.T.TO) data;
        Log.d("zsbin", "updateView viewData.mMapAction=" + viewData.Tr);
        if (viewData.Tr != null && viewData.Tr.intValue() == Tk) {
            this.Te.setVisibility(0);
            ((AnimationDrawable) this.Tq.getDrawable()).start();
        }
        this.Tj.T(data);
        if (viewData.Tr == null) {
            T6.T().Ty(data);
            this.Th.removeAllViewsInLayout();
            this.Th.addView(Tq.T9().T(data).Tr, this.T6);
            this.Th.addView(new View(com.txznet.comm.Tr.T.Tr()), this.T6);
        }
        return super.Tr(data);
    }

    public Integer Tn() {
        return super.Tn();
    }

    public void T(int progress, int selection) {
    }

    public void T(boolean next) {
    }
}
