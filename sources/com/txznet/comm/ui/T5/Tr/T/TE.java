package com.txznet.comm.ui.T5.Tr.T;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.can.bmw.mini.CanBMWMiniServiceDetailActivity;
import com.txznet.comm.Tr.T;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.T.Tk;
import com.txznet.comm.ui.T5.Tr.Tu;
import com.txznet.comm.ui.TE.T9;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.comm.ui.TE.Ty;
import com.txznet.comm.ui.view.GradientProgressBar;
import com.txznet.comm.ui.view.RippleView;
import com.txznet.sdk.bean.BusinessPoiDetail;
import com.txznet.sdk.bean.Poi;
import com.txznet.txz.util.TZ;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"NewApi"})
/* compiled from: Proguard */
public class TE extends Tu {
    private static TE TZ = null;
    private int T0;
    private ArrayList<GradientProgressBar> T2 = new ArrayList<>(4);
    private int T5;
    private int T6;
    private float T8;
    private int TA;
    private int TB;
    private int TC;
    private int TD;
    private List<View> TE;
    private int TF;
    private int TG;
    private int TI;
    private int TK;
    private float TL;
    private int TM;
    private int TN;
    private int TO;
    private int TP;
    private int TQ;
    private int TR;
    private int TT;
    private boolean TU = false;
    private int TV;
    private int TX;
    private float Ta;
    private int Tb;
    private int Td;
    private int Te;
    private int Tf;
    private boolean Tg = false;
    private int Th;
    private boolean Ti = false;
    private int Tj;
    List<View> Tk = new ArrayList();
    private float Tl;
    private int Tm;
    private float To;
    private int Tq;
    private int Ts;
    private int Tt;
    private int Tu;
    private int Tv;
    private int Tw;
    private int Tx;
    /* access modifiers changed from: private */
    public Tk.Tr Tz;

    protected TE() {
        Tr();
    }

    public static TE T9() {
        if (TZ == null) {
            synchronized (TE.class) {
                if (TZ == null) {
                    TZ = new TE();
                }
            }
        }
        return TZ;
    }

    public void T(int progress, int selection) {
        if (this.T2.size() > selection) {
            GradientProgressBar progressBar = this.T2.get(selection);
            if (progress >= 0) {
                if (progressBar.getVisibility() == 8) {
                    progressBar.setVisibility(0);
                }
                progressBar.setProgress(progress);
            } else if (progressBar.getVisibility() == 0) {
                progressBar.setVisibility(8);
            }
        }
    }

    public void Ty() {
        super.Ty();
        if (this.TE != null) {
            this.TE.clear();
        }
        if (this.Tk != null) {
            this.Tk.clear();
        }
        if (this.T2 != null) {
            this.T2.clear();
        }
    }

    public Tn.T T(TM data) {
        if (this.Tk != null) {
            this.Tk.clear();
        }
        com.txznet.comm.ui.T5.T.Tu poiListViewData = (com.txznet.comm.ui.T5.T.Tu) data;
        this.Tg = !this.Ti && poiListViewData.f457T;
        this.TU = poiListViewData.Tk.equals(Poi.PoiAction.ACTION_NAV_HISTORY);
        Tn.T titleViewAdapter = T6.T().T((TM) poiListViewData);
        this.Tn = poiListViewData.Tn.Tk;
        this.T9 = poiListViewData.Tn.TZ;
        LinearLayout llLayout = new LinearLayout(T.Tr());
        llLayout.setGravity(16);
        llLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, T6.T().T5());
        layoutParams.gravity = 16;
        llLayout.addView(titleViewAdapter.Tr, layoutParams);
        int itemHeight = com.txznet.comm.ui.TE.T.T(false);
        LinearLayout llContent = new LinearLayout(T.Tr());
        llContent.setOrientation(1);
        llContent.setBackgroundColor(this.Td);
        llLayout.addView(llContent, new LinearLayout.LayoutParams(-1, com.txznet.comm.ui.TE.T.TE() * itemHeight));
        llContent.setLayoutAnimation(Ty.T());
        llContent.setLayoutAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                if (TE.this.f463T != null) {
                    TE.this.f463T.T(animation, 1);
                }
            }

            public void onAnimationRepeat(Animation animation) {
                if (TE.this.f463T != null) {
                    TE.this.f463T.T(animation, 2);
                }
            }

            public void onAnimationEnd(Animation animation) {
                if (TE.this.f463T != null) {
                    TE.this.f463T.T(animation, 3);
                }
            }
        });
        this.T2.clear();
        this.TE = new ArrayList();
        int i = 0;
        while (i < poiListViewData.T9) {
            RippleView itemView = new RippleView(T.Tr());
            llContent.addView(itemView, new LinearLayout.LayoutParams(-1, itemHeight));
            if (Poi.PoiAction.ACTION_NAV_HISTORY.equals(poiListViewData.Tk)) {
                Tr(itemView, i, poiListViewData.T().get(i), i != com.txznet.comm.ui.TE.T.TE() + -1);
            } else {
                T(itemView, i, poiListViewData.T().get(i), i != com.txznet.comm.ui.TE.T.TE() + -1);
            }
            this.TE.add(itemView);
            i++;
        }
        Tn.T viewAdapter = new Tn.T();
        viewAdapter.f462T = data.Ty();
        viewAdapter.Tr = llLayout;
        viewAdapter.Ty = true;
        viewAdapter.Tn = T9();
        return viewAdapter;
    }

    public void Tr() {
        if (this.Ty == null) {
            this.Ty = 16;
        } else {
            this.Ty = Integer.valueOf(this.Ty.intValue() | 16);
        }
        this.T5 = (int) com.txznet.comm.ui.TZ.Tn.Tr(com.txznet.comm.ui.TZ.Tn.Ty);
        this.Tv = (int) com.txznet.comm.ui.TZ.Tn.Tr(com.txznet.comm.ui.TZ.Tn.Tn);
        this.Th = (int) com.txznet.comm.ui.TZ.Tn.T(com.txznet.comm.ui.TZ.Tn.Tr);
        this.T6 = (int) com.txznet.comm.ui.TZ.Tn.T(com.txznet.comm.ui.TZ.Tn.TF);
        this.Te = (int) com.txznet.comm.ui.TZ.Tn.T(com.txznet.comm.ui.TZ.Tn.Tj);
        this.Tq = (int) Tr.Tn("x16");
        this.TF = (int) Tr.Tn("x5");
        this.Tj = (int) Tr.Tn("x124");
        this.TB = (int) Tr.Tn("y20");
        this.TK = (int) Tr.Tn("x4");
        this.TO = (int) Tr.Tn("x20");
        this.TN = (int) Tr.Tn("y20");
        this.Ts = (int) Tr.Tn("x2");
        this.TG = (int) Tr.Tn("x20");
        this.Tu = (int) Tr.Tn("y20");
        this.Tt = (int) Tr.Tn("x2");
        this.TD = (int) Tr.Tn("x20");
        this.Tf = (int) Tr.Tn("y20");
        this.TA = (int) Tr.Tn("x2");
        this.Tx = (int) Tr.Tn("x2");
        this.T0 = (int) Tr.Tn("x4");
        this.TV = (int) Tr.Tn("x7");
        this.Tb = (int) Tr.Tn("x16");
        this.TM = (int) Tr.Tn("x40");
        this.TX = (int) Tr.Tn("x16");
        this.TP = (int) Tr.Tn("x16");
        this.TI = (int) Math.ceil((double) com.txznet.comm.ui.TZ.Tn.Tr(com.txznet.comm.ui.TZ.Tn.Tq));
        this.TL = ((Float) com.txznet.comm.ui.Tr.T.Tr().T("poi_indexSize1.list_indexSize1")).floatValue();
        this.Tw = ((Integer) com.txznet.comm.ui.Tr.T.Tr().T("poi_indexColor1.list_indexColor1.base_color1")).intValue();
        this.Ta = ((Float) com.txznet.comm.ui.Tr.T.Tr().T("poi_itemSize1.list_itemSize1.base_size2")).floatValue();
        this.TC = ((Integer) com.txznet.comm.ui.Tr.T.Tr().T("poi_itemColor1.list_itemColor1.base_color1")).intValue();
        this.To = ((Float) com.txznet.comm.ui.Tr.T.Tr().T("poi_itemSize2.list_itemSize2.base_size4")).floatValue();
        this.TQ = ((Integer) com.txznet.comm.ui.Tr.T.Tr().T("poi_itemColor2.list_itemColor2.base_color4")).intValue();
        this.Tl = ((Float) com.txznet.comm.ui.Tr.T.Tr().T("poi_itemSize2.list_itemSize2.base_size4")).floatValue();
        this.TT = ((Integer) com.txznet.comm.ui.Tr.T.Tr().T("poi_itemColor2.list_itemColor2.base_color4")).intValue();
        this.T8 = ((Float) com.txznet.comm.ui.Tr.T.Tr().T("poi_itemSize2.list_itemSize2.base_size4")).floatValue();
        this.TR = ((Integer) com.txznet.comm.ui.Tr.T.Tr().T("poi_itemColor2.list_itemColor2.base_color4")).intValue();
        this.Tm = Integer.valueOf(com.txznet.comm.ui.Tr.T.Tr().Tr("theme_color1")).intValue();
        this.Td = com.txznet.comm.ui.Tr.T.Tr().Tr("theme_color2");
        this.Tm = TZ.T(0.8f, this.Tm);
        this.Td = TZ.T(0.8f, this.Td);
    }

    public void T(boolean next) {
        com.txznet.comm.Tr.Tr.Tn.T("update snap " + next);
    }

    private View T(RippleView itemView, int position, Poi poi, boolean showDivider) {
        String mDistance;
        int i;
        itemView.setTag(Integer.valueOf(position));
        itemView.setOnClickListener(T6.T().TZ());
        itemView.setOnTouchListener(T6.T().TE());
        GradientProgressBar gradientProgressBar = new GradientProgressBar(T.Tr());
        gradientProgressBar.setVisibility(8);
        itemView.addView(gradientProgressBar, new RelativeLayout.LayoutParams(-1, -1));
        this.T2.add(gradientProgressBar);
        TextView textView = new TextView(T.Tr());
        textView.setId(T9.T());
        textView.setBackground(Tr.Ty("poi_item_circle_bg"));
        textView.setGravity(17);
        textView.setIncludeFontPadding(false);
        textView.setPadding(0, 0, 0, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.T5, this.Tv);
        layoutParams.leftMargin = this.Th;
        layoutParams.addRule(15);
        this.Tk.add(textView);
        itemView.addView(textView, layoutParams);
        View view = new View(T.Tr());
        view.setId(T9.T());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(0, 0);
        layoutParams2.addRule(1, textView.getId());
        layoutParams2.addRule(15);
        itemView.addView(view, layoutParams2);
        RelativeLayout relativeLayout = new RelativeLayout(T.Tr());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        if (!TextUtils.isEmpty(poi.getGeoinfo())) {
            layoutParams3.addRule(2, view.getId());
        }
        layoutParams3.addRule(15);
        layoutParams3.addRule(1, textView.getId());
        itemView.addView(relativeLayout, layoutParams3);
        ImageView ivStarGrade = new ImageView(T.Tr());
        ivStarGrade.setVisibility(8);
        ivStarGrade.setId(T9.T());
        ivStarGrade.setScaleType(ImageView.ScaleType.FIT_END);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, this.TB);
        layoutParams4.rightMargin = this.TP;
        layoutParams4.addRule(11);
        layoutParams4.addRule(15);
        relativeLayout.addView(ivStarGrade, layoutParams4);
        LinearLayout linearLayout = new LinearLayout(T.Tr());
        linearLayout.setId(T9.T());
        linearLayout.setGravity(16);
        linearLayout.setOrientation(0);
        linearLayout.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(0, ivStarGrade.getId());
        layoutParams5.addRule(15);
        layoutParams5.rightMargin = this.TF;
        relativeLayout.addView(linearLayout, layoutParams5);
        ImageView ivJuan = new ImageView(T.Tr());
        ivJuan.setScaleType(ImageView.ScaleType.FIT_END);
        ivJuan.setVisibility(8);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(this.TO, this.TN);
        layoutParams6.rightMargin = this.Ts;
        ivJuan.setImageDrawable(Tr.Ty("dz_juan"));
        linearLayout.addView(ivJuan, layoutParams6);
        ImageView ivHui = new ImageView(T.Tr());
        ivHui.setScaleType(ImageView.ScaleType.FIT_END);
        ivHui.setVisibility(8);
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(this.TG, this.Tu);
        layoutParams7.rightMargin = this.Tt;
        ivHui.setImageDrawable(Tr.Ty("dz_hui"));
        linearLayout.addView(ivHui, layoutParams7);
        ImageView imageView = new ImageView(T.Tr());
        imageView.setScaleType(ImageView.ScaleType.FIT_END);
        imageView.setVisibility(8);
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(this.TD, this.Tf);
        layoutParams8.rightMargin = this.TA;
        imageView.setImageDrawable(Tr.Ty("dz_tuan"));
        linearLayout.addView(imageView, layoutParams8);
        TextView textView2 = new TextView(T.Tr());
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setSingleLine();
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams9.addRule(0, linearLayout.getId());
        layoutParams9.addRule(15);
        layoutParams9.addRule(9);
        layoutParams9.rightMargin = this.TP;
        layoutParams9.leftMargin = this.T6;
        relativeLayout.addView(textView2, layoutParams9);
        FrameLayout flDistance = new FrameLayout(T.Tr());
        flDistance.setId(T9.T());
        RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams10.addRule(11);
        if (!TextUtils.isEmpty(poi.getGeoinfo())) {
            layoutParams10.addRule(3, view.getId());
        } else {
            layoutParams10.addRule(15);
        }
        layoutParams10.rightMargin = this.TP;
        itemView.addView(flDistance, layoutParams10);
        if (TextUtils.isEmpty(poi.getGeoinfo())) {
            RelativeLayout.LayoutParams layoutParams11 = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams11.addRule(0, flDistance.getId());
            relativeLayout.setLayoutParams(layoutParams11);
        }
        ImageView imageView2 = new ImageView(T.Tr());
        imageView2.setVisibility(8);
        imageView2.setId(T9.T());
        imageView2.setScaleType(ImageView.ScaleType.FIT_END);
        flDistance.addView(imageView2, new FrameLayout.LayoutParams(-2, this.TB));
        TextView textView3 = new TextView(T.Tr());
        textView3.setId(T9.T());
        FrameLayout.LayoutParams flayoutParams = new FrameLayout.LayoutParams(-2, -2);
        flayoutParams.gravity = 5;
        flayoutParams.leftMargin = (int) this.Tl;
        flDistance.addView(textView3, flayoutParams);
        TextView textView4 = new TextView(T.Tr());
        textView4.setId(T9.T());
        textView4.setGravity(8388629);
        textView4.setSingleLine();
        RelativeLayout.LayoutParams layoutParams12 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams12.addRule(0, flDistance.getId());
        layoutParams12.addRule(3, view.getId());
        layoutParams12.rightMargin = this.TV;
        itemView.addView(textView4, layoutParams12);
        TextView textView5 = new TextView(T.Tr());
        textView5.setId(T9.T());
        textView5.setSingleLine();
        textView5.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams13 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams13.addRule(0, textView4.getId());
        layoutParams13.addRule(1, textView.getId());
        layoutParams13.addRule(3, view.getId());
        textView5.setPadding(this.T6, 0, this.Te, 0);
        itemView.addView(textView5, layoutParams13);
        View view2 = new View(T.Tr());
        view2.setId(T9.T());
        RelativeLayout.LayoutParams layoutParams14 = new RelativeLayout.LayoutParams(0, 0);
        layoutParams14.addRule(1, textView.getId());
        layoutParams14.addRule(3, textView5.getId());
        itemView.addView(view2, layoutParams14);
        RelativeLayout relativeLayout2 = new RelativeLayout(T.Tr());
        relativeLayout2.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams15 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams15.addRule(3, view2.getId());
        layoutParams15.addRule(1, textView.getId());
        itemView.addView(relativeLayout2, layoutParams15);
        ImageView imageView3 = new ImageView(T.Tr());
        imageView3.setVisibility(8);
        imageView3.setId(T9.T());
        imageView3.setScaleType(ImageView.ScaleType.FIT_START);
        RelativeLayout.LayoutParams layoutParams16 = new RelativeLayout.LayoutParams(-2, this.TB);
        layoutParams16.leftMargin = this.T6;
        layoutParams16.addRule(9);
        layoutParams16.addRule(15);
        relativeLayout2.addView(imageView3, layoutParams16);
        LinearLayout linearLayout2 = new LinearLayout(T.Tr());
        linearLayout2.setId(T9.T());
        linearLayout2.setGravity(16);
        linearLayout2.setOrientation(0);
        linearLayout2.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams17 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams17.addRule(1, imageView3.getId());
        layoutParams17.addRule(15);
        layoutParams17.rightMargin = this.TF;
        relativeLayout2.addView(linearLayout2, layoutParams17);
        ImageView ivJuanNew = new ImageView(T.Tr());
        ivJuanNew.setScaleType(ImageView.ScaleType.FIT_END);
        ivJuanNew.setVisibility(8);
        LinearLayout.LayoutParams layoutParams18 = new LinearLayout.LayoutParams(this.TO, this.TN);
        layoutParams18.rightMargin = this.Ts;
        ivJuanNew.setImageDrawable(Tr.Ty("dz_juan"));
        linearLayout2.addView(ivJuanNew, layoutParams18);
        ImageView ivHuiNew = new ImageView(T.Tr());
        ivHuiNew.setScaleType(ImageView.ScaleType.FIT_END);
        ivHuiNew.setVisibility(8);
        LinearLayout.LayoutParams layoutParams19 = new LinearLayout.LayoutParams(this.TG, this.Tu);
        layoutParams19.rightMargin = this.Tt;
        ivHuiNew.setImageDrawable(Tr.Ty("dz_hui"));
        linearLayout2.addView(ivHuiNew, layoutParams19);
        ImageView imageView4 = new ImageView(T.Tr());
        imageView4.setScaleType(ImageView.ScaleType.FIT_END);
        imageView4.setVisibility(8);
        LinearLayout.LayoutParams layoutParams20 = new LinearLayout.LayoutParams(this.TD, this.Tf);
        layoutParams20.rightMargin = this.TA;
        imageView4.setImageDrawable(Tr.Ty("dz_tuan"));
        linearLayout2.addView(imageView4, layoutParams20);
        TextView textView6 = new TextView(T.Tr());
        textView6.setId(T9.T());
        textView6.setGravity(8388629);
        textView6.setSingleLine();
        RelativeLayout.LayoutParams layoutParams21 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams21.addRule(11);
        layoutParams21.rightMargin = this.TP;
        relativeLayout2.addView(textView6, layoutParams21);
        View divider = new View(T.Tr());
        divider.setVisibility(8);
        divider.setBackgroundColor(Color.parseColor("#4c4c4c"));
        RelativeLayout.LayoutParams layoutParams22 = new RelativeLayout.LayoutParams(-1, this.TI);
        layoutParams22.addRule(12);
        itemView.addView(divider, layoutParams22);
        com.txznet.comm.Ty.Tk.T(textView, this.TL);
        com.txznet.comm.Ty.Tk.T(textView, this.Tw);
        com.txznet.comm.Ty.Tk.T(textView2, this.Ta);
        com.txznet.comm.Ty.Tk.T(textView2, this.TC);
        com.txznet.comm.Ty.Tk.T(textView5, this.To);
        com.txznet.comm.Ty.Tk.T(textView5, this.TQ);
        com.txznet.comm.Ty.Tk.T(textView3, this.Tl);
        com.txznet.comm.Ty.Tk.T(textView3, this.TT);
        com.txznet.comm.Ty.Tk.T(textView4, this.T8);
        com.txznet.comm.Ty.Tk.T(textView4, this.TR);
        com.txznet.comm.Ty.Tk.T(textView6, this.T8);
        com.txznet.comm.Ty.Tk.T(textView6, this.TR);
        if (this.Tg && (poi instanceof BusinessPoiDetail)) {
            linearLayout.setVisibility(8);
            textView4.setVisibility(8);
            ivStarGrade.setVisibility(8);
            imageView2.setVisibility(8);
            relativeLayout2.setVisibility(0);
            linearLayout2.setVisibility(0);
            textView6.setVisibility(0);
            imageView3.setVisibility(0);
            BusinessPoiDetail bpd = (BusinessPoiDetail) poi;
            imageView3.setImageDrawable(T(bpd.getScore()));
            final ImageView imageView5 = imageView3;
            imageView3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    imageView5.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    Rect rect = imageView5.getDrawable().getBounds();
                    float[] values = new float[10];
                    imageView5.getImageMatrix().getValues(values);
                    float sx = values[0];
                    float f = values[4];
                    int cw = (int) (((float) rect.width()) * sx);
                    ((RelativeLayout.LayoutParams) imageView5.getLayoutParams()).width = cw;
                    com.txznet.comm.Tr.Tr.Tn.Tn("currentWidth:" + cw + "realWidth:" + imageView5.getWidth());
                }
            });
            if (bpd.isHasCoupon()) {
                ivHuiNew.setVisibility(0);
            } else {
                ivHuiNew.setVisibility(8);
            }
            if (bpd.isHasDeal()) {
                imageView4.setVisibility(0);
            } else {
                imageView4.setVisibility(8);
            }
            int price = (int) bpd.getAvgPrice();
            if (price > 0) {
                textView6.setText(String.format("￥%d/人", new Object[]{Integer.valueOf(price)}));
            } else {
                textView6.setVisibility(8);
            }
        } else if (poi instanceof BusinessPoiDetail) {
            linearLayout.setVisibility(0);
            textView4.setVisibility(0);
            ivStarGrade.setVisibility(0);
            imageView2.setVisibility(4);
            BusinessPoiDetail bpd2 = (BusinessPoiDetail) poi;
            double score = bpd2.getScore();
            if (score < 1.0d) {
                ivStarGrade.setVisibility(8);
            } else {
                ivStarGrade.setImageDrawable(T(score));
                imageView2.setImageDrawable(T(score));
            }
            if (bpd2.isHasCoupon()) {
                ivHui.setVisibility(0);
            } else {
                ivHui.setVisibility(8);
            }
            if (bpd2.isHasDeal()) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
            int price2 = (int) bpd2.getAvgPrice();
            if (price2 > 0) {
                textView4.setText(com.txznet.txz.util.Tr.T(String.format("￥%d/人", new Object[]{Integer.valueOf(price2)})));
            } else {
                textView4.setVisibility(8);
            }
        } else {
            linearLayout.setVisibility(8);
            textView4.setVisibility(8);
            ivStarGrade.setVisibility(8);
            imageView2.setVisibility(8);
        }
        double d = ((double) poi.getDistance()) / 1000.0d;
        if (d < 1.0d) {
            mDistance = (1000.0d * d) + "米";
        } else {
            mDistance = String.format("%.1f", new Object[]{Double.valueOf(d)}) + "公里";
        }
        textView3.setText(com.txznet.txz.util.Tr.T(mDistance));
        textView.setText(String.valueOf(position + 1));
        textView.setClickable(true);
        final int i2 = position;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TE.this.Tz != null) {
                    TE.this.Tz.T(i2);
                }
                TE.this.T(i2);
            }
        });
        textView2.setText(com.txznet.txz.util.Tr.T(poi.getName()));
        textView5.setText(com.txznet.txz.util.Tr.T(poi.getGeoinfo()));
        if (showDivider) {
            i = 0;
        } else {
            i = 4;
        }
        divider.setVisibility(i);
        return itemView;
    }

    private View Tr(RippleView itemView, int position, Poi poi, boolean showDivider) {
        String mDistance;
        itemView.setTag(Integer.valueOf(position));
        itemView.setOnClickListener(T6.T().TZ());
        itemView.setOnTouchListener(T6.T().TE());
        GradientProgressBar gradientProgressBar = new GradientProgressBar(T.Tr());
        gradientProgressBar.setVisibility(8);
        itemView.addView(gradientProgressBar, new RelativeLayout.LayoutParams(-1, -1));
        this.T2.add(gradientProgressBar);
        TextView textView = new TextView(T.Tr());
        textView.setId(T9.T());
        textView.setBackground(Tr.Ty("poi_item_circle_bg"));
        textView.setGravity(17);
        textView.setIncludeFontPadding(false);
        textView.setPadding(0, 0, 0, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.T5, this.Tv);
        layoutParams.leftMargin = this.Th;
        layoutParams.addRule(15);
        this.Tk.add(textView);
        itemView.addView(textView, layoutParams);
        View view = new View(T.Tr());
        view.setId(T9.T());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(0, 0);
        layoutParams2.addRule(1, textView.getId());
        layoutParams2.addRule(15);
        itemView.addView(view, layoutParams2);
        RelativeLayout relativeLayout = new RelativeLayout(T.Tr());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(2, view.getId());
        layoutParams3.addRule(1, textView.getId());
        itemView.addView(relativeLayout, layoutParams3);
        ImageView ivStarGrade = new ImageView(T.Tr());
        ivStarGrade.setVisibility(8);
        ivStarGrade.setId(T9.T());
        ivStarGrade.setScaleType(ImageView.ScaleType.FIT_END);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, this.TB);
        layoutParams4.rightMargin = this.TP;
        layoutParams4.addRule(11);
        layoutParams4.addRule(15);
        relativeLayout.addView(ivStarGrade, layoutParams4);
        LinearLayout linearLayout = new LinearLayout(T.Tr());
        linearLayout.setId(T9.T());
        linearLayout.setGravity(16);
        linearLayout.setOrientation(0);
        linearLayout.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(0, ivStarGrade.getId());
        layoutParams5.addRule(15);
        layoutParams5.rightMargin = this.TF;
        relativeLayout.addView(linearLayout, layoutParams5);
        ImageView ivJuan = new ImageView(T.Tr());
        ivJuan.setScaleType(ImageView.ScaleType.FIT_END);
        ivJuan.setVisibility(8);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(this.TO, this.TN);
        layoutParams6.rightMargin = this.Ts;
        ivJuan.setImageDrawable(Tr.Ty("dz_juan"));
        linearLayout.addView(ivJuan, layoutParams6);
        ImageView ivHui = new ImageView(T.Tr());
        ivHui.setScaleType(ImageView.ScaleType.FIT_END);
        ivHui.setVisibility(8);
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(this.TG, this.Tu);
        layoutParams7.rightMargin = this.Tt;
        ivHui.setImageDrawable(Tr.Ty("dz_hui"));
        linearLayout.addView(ivHui, layoutParams7);
        ImageView imageView = new ImageView(T.Tr());
        imageView.setScaleType(ImageView.ScaleType.FIT_END);
        imageView.setVisibility(8);
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(this.TD, this.Tf);
        layoutParams8.rightMargin = this.TA;
        imageView.setImageDrawable(Tr.Ty("dz_tuan"));
        linearLayout.addView(imageView, layoutParams8);
        TextView textView2 = new TextView(T.Tr());
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setSingleLine();
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams9.addRule(0, linearLayout.getId());
        layoutParams9.addRule(15);
        layoutParams9.addRule(9);
        layoutParams9.leftMargin = this.T6;
        relativeLayout.addView(textView2, layoutParams9);
        RelativeLayout relativeLayout2 = new RelativeLayout(T.Tr());
        relativeLayout2.setVisibility(0);
        relativeLayout2.setId(T9.T());
        RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams((int) Tr.Tn("x100"), -1);
        layoutParams10.addRule(11);
        itemView.addView(relativeLayout2, layoutParams10);
        final RelativeLayout relativeLayout3 = relativeLayout2;
        relativeLayout2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                relativeLayout3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams2 = relativeLayout3.getLayoutParams();
                layoutParams2.width = relativeLayout3.getBottom() - relativeLayout3.getTop();
                relativeLayout3.setLayoutParams(layoutParams2);
            }
        });
        final int i = position;
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                com.txznet.comm.Ty.Tr jb = new com.txznet.comm.Ty.Tr();
                jb.T(CanBMWMiniServiceDetailActivity.KEY_INDEX, (Object) Integer.valueOf(i));
                jb.T("action", (Object) "delete");
                com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.item.selected", jb.Ty(), (Tn.Tr) null);
            }
        });
        View historyLine = new View(T.Tr());
        historyLine.setBackgroundColor(Color.parseColor("#7F4f4f4f"));
        RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(this.TI, -1);
        layoutParams11.addRule(9);
        int Tn = (int) Tr.Tn("y20");
        layoutParams11.bottomMargin = Tn;
        layoutParams11.topMargin = Tn;
        relativeLayout2.addView(historyLine, layoutParams11);
        ImageView historyDel = new ImageView(T.Tr());
        historyDel.setVisibility(0);
        RelativeLayout.LayoutParams layoutParams12 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams12.addRule(13);
        historyDel.setImageDrawable(Tr.Ty("poi_history_del"));
        relativeLayout2.addView(historyDel, layoutParams12);
        FrameLayout flDistance = new FrameLayout(T.Tr());
        flDistance.setId(T9.T());
        RelativeLayout.LayoutParams layoutParams13 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams13.addRule(0, relativeLayout2.getId());
        layoutParams13.addRule(3, view.getId());
        layoutParams13.rightMargin = this.TP;
        itemView.addView(flDistance, layoutParams13);
        ImageView imageView2 = new ImageView(T.Tr());
        imageView2.setVisibility(8);
        imageView2.setId(T9.T());
        imageView2.setScaleType(ImageView.ScaleType.FIT_END);
        flDistance.addView(imageView2, new FrameLayout.LayoutParams(-2, this.TB));
        TextView textView3 = new TextView(T.Tr());
        textView3.setVisibility(8);
        textView3.setId(T9.T());
        FrameLayout.LayoutParams flayoutParams = new FrameLayout.LayoutParams(-2, -2);
        flayoutParams.gravity = 5;
        flDistance.addView(textView3, flayoutParams);
        TextView textView4 = new TextView(T.Tr());
        textView4.setId(T9.T());
        textView4.setGravity(8388629);
        textView4.setSingleLine();
        RelativeLayout.LayoutParams layoutParams14 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams14.addRule(0, flDistance.getId());
        layoutParams14.addRule(3, view.getId());
        layoutParams14.rightMargin = this.TV;
        itemView.addView(textView4, layoutParams14);
        TextView textView5 = new TextView(T.Tr());
        textView5.setSingleLine();
        textView5.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams15 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams15.addRule(0, textView4.getId());
        layoutParams15.addRule(1, textView.getId());
        if (!TextUtils.isEmpty(poi.getName())) {
            layoutParams15.addRule(3, view.getId());
        } else {
            layoutParams15.addRule(15);
        }
        textView5.setPadding(this.T6, 0, this.Te, 0);
        itemView.addView(textView5, layoutParams15);
        View divider = new View(T.Tr());
        divider.setVisibility(8);
        divider.setBackgroundColor(Color.parseColor("#4c4c4c"));
        RelativeLayout.LayoutParams layoutParams16 = new RelativeLayout.LayoutParams(-1, this.TI);
        layoutParams16.addRule(12);
        itemView.addView(divider, layoutParams16);
        com.txznet.comm.Ty.Tk.T(textView, this.TL);
        com.txznet.comm.Ty.Tk.T(textView, this.Tw);
        com.txznet.comm.Ty.Tk.T(textView2, this.Ta);
        com.txznet.comm.Ty.Tk.T(textView2, this.TC);
        com.txznet.comm.Ty.Tk.T(textView5, this.To);
        com.txznet.comm.Ty.Tk.T(textView5, this.TQ);
        com.txznet.comm.Ty.Tk.T(textView3, this.Tl);
        com.txznet.comm.Ty.Tk.T(textView3, this.TT);
        com.txznet.comm.Ty.Tk.T(textView4, this.T8);
        com.txznet.comm.Ty.Tk.T(textView4, this.TR);
        if (poi instanceof BusinessPoiDetail) {
            linearLayout.setVisibility(0);
            textView4.setVisibility(0);
            ivStarGrade.setVisibility(0);
            imageView2.setVisibility(4);
            BusinessPoiDetail bpd = (BusinessPoiDetail) poi;
            double score = bpd.getScore();
            if (score < 1.0d) {
                ivStarGrade.setVisibility(8);
            } else {
                ivStarGrade.setImageDrawable(T(score));
                imageView2.setImageDrawable(T(score));
            }
            if (bpd.isHasCoupon()) {
                ivHui.setVisibility(0);
            } else {
                ivHui.setVisibility(8);
            }
            if (bpd.isHasDeal()) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
            int price = (int) bpd.getAvgPrice();
            if (price > 0) {
                textView4.setText(com.txznet.txz.util.Tr.T(String.format("￥%d/人", new Object[]{Integer.valueOf(price)})));
            } else {
                textView4.setVisibility(8);
            }
        } else {
            linearLayout.setVisibility(8);
            textView4.setVisibility(8);
            ivStarGrade.setVisibility(8);
            imageView2.setVisibility(8);
        }
        double d = ((double) poi.getDistance()) / 1000.0d;
        if (d < 1.0d) {
            mDistance = (1000.0d * d) + "米";
        } else {
            mDistance = String.format("%.1f", new Object[]{Double.valueOf(d)}) + "公里";
        }
        textView3.setText(com.txznet.txz.util.Tr.T(mDistance));
        textView.setText(String.valueOf(position + 1));
        textView2.setText(com.txznet.txz.util.Tr.T(poi.getName()));
        textView5.setText(com.txznet.txz.util.Tr.T(poi.getGeoinfo()));
        divider.setVisibility(showDivider ? 0 : 4);
        return itemView;
    }

    private Drawable T(double score) {
        if (score < 1.0d) {
            return Tr.Ty("dz_icon_star0");
        }
        if (score < 2.0d) {
            return Tr.Ty("dz_icon_star1");
        }
        if (score < 3.0d) {
            return Tr.Ty("dz_icon_star2");
        }
        if (score < 4.0d) {
            return Tr.Ty("dz_icon_star3");
        }
        if (score < 5.0d) {
            return Tr.Ty("dz_icon_star4");
        }
        if (score < 6.0d) {
            return Tr.Ty("dz_icon_star5");
        }
        if (score < 7.0d) {
            return Tr.Ty("dz_icon_star6");
        }
        if (score < 8.0d) {
            return Tr.Ty("dz_icon_star7");
        }
        if (score < 9.0d) {
            return Tr.Ty("dz_icon_star8");
        }
        if (score < 10.0d) {
            return Tr.Ty("dz_icon_star9");
        }
        return Tr.Ty("dz_icon_star10");
    }

    public void T(int index) {
        for (View view : this.Tk) {
            view.setBackground(Tr.Ty("poi_item_circle_bg"));
        }
        if (index < this.Tk.size()) {
            this.Tk.get(index).setBackground(Tr.Ty("poi_item_circle_seleted_bg"));
        }
    }
}
