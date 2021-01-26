package com.txznet.comm.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.txznet.comm.Ty.Tk;
import com.txznet.comm.ui.IKeepClass;
import com.txznet.comm.ui.TE.T;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.comm.ui.TZ.Tn;

/* compiled from: Proguard */
public class IconTextView extends RelativeLayout implements IKeepClass {

    /* renamed from: T  reason: collision with root package name */
    private ImageView f656T;
    private float T5;
    private int T9;
    private Context TE;
    private String TZ;
    private String Tk;
    private int Tn;
    private TextView Tr;
    private float Tv;
    private TextView Ty;

    public IconTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.T5 = 24.0f;
        this.Tv = 24.0f;
        this.TE = context;
        this.Tn = Color.parseColor("#adb6cc");
        this.T9 = -1;
        this.T5 = 24.0f;
        this.Tv = 24.0f;
    }

    public void init() {
        boolean z;
        String mLayoutId = "icon_textview_layout";
        switch (T.T()) {
            case 1:
                mLayoutId = "icon_textview_layout";
                break;
            case 2:
                mLayoutId = "icon_textview_layout_normal";
                break;
            case 3:
                mLayoutId = "icon_textview_layout_large";
                break;
            case 4:
                mLayoutId = "icon_textview_layout_car";
                break;
        }
        int tvHeadTopMargin = (int) Tn.Tr(Tn.TA);
        int tvHeadBottomMargin = (int) Tn.Tr(Tn.Tx);
        int tvTitleTopMargin = (int) Tn.Tr(Tn.T0);
        int tvTitleBottomMargin = (int) Tn.Tr(Tn.TV);
        int tvTitleLeftMargin = (int) Tn.T(Tn.Tb);
        int ivIconWidth = (int) Tn.Tr(Tn.TD);
        int ivIconHeight = (int) Tn.Tr(Tn.Tf);
        View view = Tr.Tr(mLayoutId);
        this.f656T = (ImageView) Tr.T("itv_icon_iv", view);
        this.Tr = (TextView) Tr.T("itv_title_tv", view);
        this.Ty = (TextView) Tr.T("itv_head_tv", view);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.Ty.getLayoutParams();
        layoutParams.topMargin = tvHeadTopMargin;
        layoutParams.bottomMargin = tvHeadBottomMargin;
        this.Ty.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.Tr.getLayoutParams();
        layoutParams2.topMargin = tvTitleTopMargin;
        layoutParams2.bottomMargin = tvTitleBottomMargin;
        layoutParams2.leftMargin = tvTitleLeftMargin;
        this.Tr.setLayoutParams(layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.f656T.getLayoutParams();
        layoutParams3.width = ivIconWidth;
        layoutParams3.height = ivIconHeight;
        this.f656T.setLayoutParams(layoutParams3);
        boolean z2 = T.T() == 2;
        if (T.T() == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z2 || z) {
            View mIconImageViewParent = (View) this.f656T.getParent();
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) mIconImageViewParent.getLayoutParams();
            layoutParams4.topMargin = (int) Tr.Tn("y16");
            mIconImageViewParent.setLayoutParams(layoutParams4);
        }
        this.Tr.setText(this.Tk);
        this.Tr.setTextColor(this.Tn);
        this.Tr.setTextSize(0, this.T5);
        this.Ty.setText(this.TZ);
        this.Ty.setTextColor(this.Tn);
        this.Ty.setTextSize(0, this.Tv);
        addView(view, new RelativeLayout.LayoutParams(-1, -1));
    }

    public void setDrawable(Drawable d) {
        this.f656T.setImageDrawable(d);
    }

    public void setTitle(String t) {
        this.Tk = t;
        this.Tr.setText(this.Tk);
    }

    public void setHead(String t) {
        this.TZ = t;
        this.Ty.setText(this.TZ);
    }

    public void setTitleSize(float size) {
        this.T5 = size;
        Tk.T(this.Tr, this.T5);
    }

    public void setHeadSize(float size) {
        this.Tv = size;
        Tk.T(this.Ty, this.Tv);
    }

    public void setTitleColor(int color) {
        this.Tr.setTextColor(color);
    }

    public void setHeadColor(int color) {
        this.Ty.setTextColor(color);
    }
}
