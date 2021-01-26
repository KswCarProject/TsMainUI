package com.txznet.comm.ui.T5.Tr.T;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GravityCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.txznet.comm.ui.T5.T.TB;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.Tj;
import com.txznet.comm.ui.T9.Tk;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.comm.ui.Tr.T;

/* compiled from: Proguard */
public class T6 extends Tj {
    private static T6 TP = new T6();

    /* renamed from: T  reason: collision with root package name */
    protected int f470T;
    private int T0;
    protected int T5;
    private float T6;
    protected String T9;
    private int TA;
    private int TB;
    private TextView TC;
    private int TD;
    protected String TE;
    private int TF;
    private float TG;
    private LinearLayout TI;
    private float TK;
    private TextView TL;
    private int TM;
    private float TN;
    private int TO;
    private TextView TQ;
    private TextView TT;
    private int TV;
    private int TX;
    protected String TZ;
    private TextView Ta;
    private int Tb;
    private int Te;
    private int Tf;
    protected View.OnTouchListener Th = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            Tk.Tr().T(2, 3, 0, 0);
            return false;
        }
    };
    private float Tj;
    protected String Tk;
    private TextView Tl;
    protected String Tn;
    private TextView To;
    private float Tq;
    protected int Tr;
    private int Ts;
    private int Tt;
    private int Tu;
    protected View.OnClickListener Tv = new View.OnClickListener() {
        public void onClick(View v) {
            Tk.Tr().T(0, 3, 0, ((Integer) v.getTag()).intValue());
        }
    };
    private TextView Tw;
    private int Tx;

    public static T6 T() {
        return TP;
    }

    private T6() {
        Tr();
    }

    public Tn.T T(TM data) {
        if (data instanceof TB) {
            T(((TB) data).Tn);
        }
        Tn.T adapter = new Tn.T();
        adapter.Tr = Tk();
        adapter.f466T = 16;
        return adapter;
    }

    public void Tr() {
        this.T6 = ((Float) T.Tr().T("poi_introSize1.list_introSize1.base_size4")).floatValue();
        this.Te = ((Integer) T.Tr().T("poi_introColor1.list_introColor1.base_color1")).intValue();
        this.Tq = ((Float) T.Tr().T("poi_introSize1.list_introSize1.base_size4")).floatValue();
        this.TF = ((Integer) T.Tr().T("poi_introColor1.list_introColor1.base_color1")).intValue();
        this.Tj = ((Float) T.Tr().T("poi_introSize2.list_introSize2.base_size3")).floatValue();
        this.TB = ((Integer) T.Tr().T("poi_introColor2.list_introColor2.base_color2")).intValue();
        this.TK = ((Float) T.Tr().T("poi_pageSize1.list_pageSize1.base_size4")).floatValue();
        this.TO = ((Integer) T.Tr().T("poi_pageColor1.list_pageColor1.base_color4")).intValue();
        this.TN = ((Float) T.Tr().T("poi_pageSize1.list_pageSize1.base_size4")).floatValue();
        this.Ts = ((Integer) T.Tr().T("poi_pageColor1.list_pageColor2.base_color1")).intValue();
        this.TG = ((Float) T.Tr().T("poi_pageSize1.list_pageSize1.base_size4")).floatValue();
        this.Tu = ((Integer) T.Tr().T("poi_pageColor1.list_pageColor2.base_color1")).intValue();
        this.T5 = (int) Tr.Tn("y64");
        this.Tt = (int) Tr.Tn("x10");
        this.Tf = (int) Tr.Tn("x10");
        this.TD = (int) Tr.Tn("x10");
        this.TA = (int) Tr.Tn("x10");
        this.Tx = (int) Tr.Tn("x14");
        this.T0 = (int) Tr.Tn("x14");
        this.Tb = (int) Tr.Tn("x5");
        this.TV = (int) Tr.Tn("x5");
        this.TM = (int) Tr.Tn("x10");
        this.TX = (int) Tr.Tn("x10");
    }

    /* access modifiers changed from: protected */
    public void T(TB.T titleInfo) {
        this.f470T = titleInfo.Tk;
        this.Tr = titleInfo.TZ;
        this.Tn = titleInfo.f435T;
        this.TZ = titleInfo.Tn;
        this.T9 = titleInfo.Tr;
        this.Tk = titleInfo.Ty;
        this.TE = titleInfo.T9;
    }

    public void T9() {
        this.TI = null;
        this.TL = null;
        this.Tw = null;
        this.Ta = null;
        this.TC = null;
        this.To = null;
        this.TQ = null;
        this.Tl = null;
        this.TT = null;
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public View Tk() {
        LinearLayout llTitleView = new LinearLayout(com.txznet.comm.Tr.T.Tr());
        llTitleView.setOrientation(0);
        llTitleView.setGravity(16);
        llTitleView.setPadding(this.Tt, 0, this.TD, 0);
        LinearLayout llTips = new LinearLayout(com.txznet.comm.Tr.T.Tr());
        llTips.setOrientation(0);
        llTips.setGravity(16);
        llTitleView.addView(llTips, new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.TC = new TextView(com.txznet.comm.Tr.T.Tr());
        this.TC.setSingleLine(true);
        this.TC.setEllipsize(TextUtils.TruncateAt.END);
        llTips.addView(this.TC, new LinearLayout.LayoutParams(-2, -2));
        this.To = new TextView(com.txznet.comm.Tr.T.Tr());
        this.To.setClickable(true);
        this.To.setVisibility(8);
        this.To.setBackground(Tr.Ty("white_range_layout"));
        this.To.setEllipsize(TextUtils.TruncateAt.END);
        this.To.setGravity(17);
        this.To.setMaxEms(7);
        this.To.setPadding(this.Tx, 0, this.T0, 0);
        this.To.setSingleLine(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = this.Tb;
        layoutParams.rightMargin = this.TV;
        llTips.addView(this.To, layoutParams);
        this.To.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Tk.Tr().T(0, 17, 0, 0);
            }
        });
        this.TQ = new TextView(com.txznet.comm.Tr.T.Tr());
        this.TQ.setSingleLine(true);
        this.TQ.setEllipsize(TextUtils.TruncateAt.END);
        llTips.addView(this.TQ, new LinearLayout.LayoutParams(-2, -2));
        this.Tl = new TextView(com.txznet.comm.Tr.T.Tr());
        this.Tl.setClickable(true);
        this.Tl.setVisibility(8);
        this.Tl.setBackground(Tr.Ty("white_range_layout"));
        this.Tl.setEllipsize(TextUtils.TruncateAt.END);
        this.Tl.setGravity(17);
        this.Tl.setPadding(this.Tx, 0, this.T0, 0);
        this.Tl.setSingleLine(true);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = this.Tb;
        layoutParams2.rightMargin = this.TV;
        llTips.addView(this.Tl, layoutParams2);
        this.Tl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Tk.Tr().T(0, 14, 0, 0);
            }
        });
        this.TT = new TextView(com.txznet.comm.Tr.T.Tr());
        this.TT.setSingleLine(true);
        llTips.addView(this.TT, new LinearLayout.LayoutParams(-2, -2));
        this.TI = new LinearLayout(com.txznet.comm.Tr.T.Tr());
        this.TI.setGravity(8388629);
        this.TI.setOrientation(0);
        this.TI.setVisibility(8);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -1);
        layoutParams3.gravity = GravityCompat.END;
        layoutParams3.leftMargin = (int) Tr.Tn("y20");
        llTitleView.addView(this.TI, layoutParams3);
        this.TL = new TextView(com.txznet.comm.Tr.T.Tr());
        this.TL.setSingleLine();
        this.TL.setText("上一页");
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams4.rightMargin = this.TM;
        this.TI.addView(this.TL, layoutParams4);
        this.Ta = new TextView(com.txznet.comm.Tr.T.Tr());
        this.Ta.setSingleLine();
        this.TI.addView(this.Ta, new LinearLayout.LayoutParams(-2, -2));
        this.Tw = new TextView(com.txznet.comm.Tr.T.Tr());
        this.Tw.setSingleLine();
        this.Tw.setText(com.txznet.txz.util.Tr.T("下一页"));
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.leftMargin = this.TX;
        this.TI.addView(this.Tw, layoutParams5);
        com.txznet.comm.Ty.Tk.T(this.TC, this.T6);
        com.txznet.comm.Ty.Tk.T(this.TC, this.Te);
        com.txznet.comm.Ty.Tk.T(this.TQ, this.T6);
        com.txznet.comm.Ty.Tk.T(this.TQ, this.Te);
        com.txznet.comm.Ty.Tk.T(this.TT, this.Tq);
        com.txznet.comm.Ty.Tk.T(this.TT, this.TF);
        com.txznet.comm.Ty.Tk.T(this.To, this.Tj);
        com.txznet.comm.Ty.Tk.T(this.To, this.TB);
        com.txznet.comm.Ty.Tk.T(this.Tl, this.Tj);
        com.txznet.comm.Ty.Tk.T(this.Tl, this.TB);
        com.txznet.comm.Ty.Tk.T(this.TL, this.TK);
        com.txznet.comm.Ty.Tk.T(this.TL, this.TO);
        com.txznet.comm.Ty.Tk.T(this.Ta, this.TN);
        com.txznet.comm.Ty.Tk.T(this.Ta, this.Ts);
        com.txznet.comm.Ty.Tk.T(this.Tw, this.TG);
        com.txznet.comm.Ty.Tk.T(this.Tw, this.Tu);
        this.TL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Tk.Tr().T(0, 2, 0, 0);
            }
        });
        this.Tw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Tk.Tr().T(0, 1, 0, 0);
            }
        });
        if (this.Tr == 0 || this.Tr == -1) {
            this.TI.setVisibility(8);
        } else {
            this.TI.setVisibility(0);
            if (this.f470T == 0) {
                com.txznet.comm.Ty.Tk.T(this.TL, this.TO);
            } else {
                com.txznet.comm.Ty.Tk.T(this.TL, this.Tu);
            }
            if (this.f470T == this.Tr - 1) {
                com.txznet.comm.Ty.Tk.T(this.Tw, this.TO);
            } else {
                com.txznet.comm.Ty.Tk.T(this.Tw, this.Tu);
            }
            if (this.Tr == 1) {
                com.txznet.comm.Ty.Tk.Tr(this.TL, "poi_pageColor1.list_pageColor1.base_color4");
                com.txznet.comm.Ty.Tk.Tr(this.Tw, "poi_pageColor1.list_pageColor1.base_color4");
            }
            this.Ta.setText((this.f470T + 1) + "/" + this.Tr);
        }
        if (!TextUtils.isEmpty(this.Tn)) {
            this.TC.setText(com.txznet.txz.util.Tr.T(this.Tn));
            this.TC.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.T9)) {
            this.To.setText(com.txznet.txz.util.Tr.T(this.T9));
            this.To.setVisibility(0);
            Drawable tvCityDrawableLeft = Tr.Ty("icon_arrow");
            if (tvCityDrawableLeft != null) {
                int height = tvCityDrawableLeft.getIntrinsicHeight();
                float scale = ((float) this.To.getLineHeight()) / ((float) height);
                tvCityDrawableLeft.setBounds(0, 0, (int) (((float) tvCityDrawableLeft.getIntrinsicWidth()) * scale), (int) (((float) height) * scale));
                this.To.setCompoundDrawables((Drawable) null, (Drawable) null, tvCityDrawableLeft, (Drawable) null);
                this.To.setCompoundDrawablePadding((int) Tr.Tn("x8"));
            }
        }
        if (!TextUtils.isEmpty(this.Tk)) {
            this.TQ.setText(com.txznet.txz.util.Tr.T(this.Tk));
            this.TQ.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.TZ)) {
            this.Tl.setText(com.txznet.txz.util.Tr.T(this.TZ));
            this.Tl.setVisibility(0);
            Drawable tvTitleDrawableLeft = Tr.Ty("icon_edit_new");
            if (tvTitleDrawableLeft != null) {
                int height2 = tvTitleDrawableLeft.getIntrinsicHeight();
                float scale2 = ((float) this.Tl.getLineHeight()) / ((float) height2);
                tvTitleDrawableLeft.setBounds(0, 0, (int) (((float) tvTitleDrawableLeft.getIntrinsicWidth()) * scale2), (int) (((float) height2) * scale2));
                this.Tl.setCompoundDrawables((Drawable) null, (Drawable) null, tvTitleDrawableLeft, (Drawable) null);
                this.Tl.setCompoundDrawablePadding((int) Tr.Tn("x8"));
            }
        }
        if (!TextUtils.isEmpty(this.TE)) {
            this.TT.setText(com.txznet.txz.util.Tr.T(this.TE));
            this.TT.setVisibility(0);
        }
        return llTitleView;
    }

    public View.OnClickListener TZ() {
        return this.Tv;
    }

    public View.OnTouchListener TE() {
        return this.Th;
    }

    public int T5() {
        return this.T5;
    }

    public void Ty(TM data) {
        if (data instanceof TB) {
            T(((TB) data).Tn);
        }
        if (this.Tr == 0 || this.Tr == -1) {
            this.TI.setVisibility(8);
        } else {
            this.TI.setVisibility(0);
            if (this.f470T == 0) {
                com.txznet.comm.Ty.Tk.T(this.TL, this.TO);
            } else {
                com.txznet.comm.Ty.Tk.T(this.TL, this.Tu);
            }
            if (this.f470T == this.Tr - 1) {
                com.txznet.comm.Ty.Tk.T(this.Tw, this.TO);
            } else {
                com.txznet.comm.Ty.Tk.T(this.Tw, this.Tu);
            }
            if (this.Tr == 1) {
                com.txznet.comm.Ty.Tk.Tr(this.TL, "poi_pageColor1.list_pageColor1.base_color4");
                com.txznet.comm.Ty.Tk.Tr(this.Tw, "poi_pageColor1.list_pageColor1.base_color4");
            }
            this.Ta.setText((this.f470T + 1) + "/" + this.Tr);
        }
        if (!TextUtils.isEmpty(this.Tn)) {
            this.TC.setText(com.txznet.txz.util.Tr.T(this.Tn));
            this.TC.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.T9)) {
            this.To.setText(com.txznet.txz.util.Tr.T(this.T9));
            this.To.setVisibility(0);
            Drawable tvCityDrawableLeft = Tr.Ty("icon_arrow");
            if (tvCityDrawableLeft != null) {
                int height = tvCityDrawableLeft.getIntrinsicHeight();
                float scale = ((float) this.To.getLineHeight()) / ((float) height);
                tvCityDrawableLeft.setBounds(0, 0, (int) (((float) tvCityDrawableLeft.getIntrinsicWidth()) * scale), (int) (((float) height) * scale));
                this.To.setCompoundDrawables((Drawable) null, (Drawable) null, tvCityDrawableLeft, (Drawable) null);
            }
        }
        if (!TextUtils.isEmpty(this.Tk)) {
            this.TQ.setText(com.txznet.txz.util.Tr.T(this.Tk));
            this.TQ.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.TZ)) {
            this.Tl.setText(com.txznet.txz.util.Tr.T(this.TZ));
            this.Tl.setVisibility(0);
            Drawable tvTitleDrawableLeft = Tr.Ty("icon_edit_new");
            if (tvTitleDrawableLeft != null) {
                int height2 = tvTitleDrawableLeft.getIntrinsicHeight();
                float scale2 = ((float) this.Tl.getLineHeight()) / ((float) height2);
                tvTitleDrawableLeft.setBounds(0, 0, (int) (((float) tvTitleDrawableLeft.getIntrinsicWidth()) * scale2), (int) (((float) height2) * scale2));
                this.Tl.setCompoundDrawables((Drawable) null, (Drawable) null, tvTitleDrawableLeft, (Drawable) null);
            }
        }
        if (!TextUtils.isEmpty(this.TE)) {
            this.TT.setText(com.txznet.txz.util.Tr.T(this.TE));
            this.TT.setVisibility(0);
        }
    }
}
