package com.txznet.comm.ui.T5.Tr.T;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.Tr.Tr.TE;
import com.txznet.comm.Tr.T;
import com.txznet.comm.Ty.T9;
import com.txznet.comm.ui.T5.T.T6;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.Th;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.txz.util.Tk;

/* compiled from: Proguard */
public class Tn extends Th {
    private static Tn Tk = new Tn();

    public static Tn T9() {
        return Tk;
    }

    public void Tr() {
        super.Tr();
    }

    public Tn.T T(TM data) {
        T6 helpDetailImageViewData = (T6) data;
        Tn.T titleViewAdapter = T6.T().T((TM) helpDetailImageViewData);
        LinearLayout llLayout = new LinearLayout(T.Tr());
        llLayout.setGravity(16);
        llLayout.setOrientation(1);
        int contentHeight = com.txznet.comm.ui.TE.Tn.T(false) * com.txznet.comm.ui.TE.Tn.Ty();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 16;
        llLayout.setLayoutParams(layoutParams);
        llLayout.setGravity(16);
        llLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, T6.T().T5());
        layoutParams2.gravity = 16;
        llLayout.addView(titleViewAdapter.Tr, layoutParams2);
        LinearLayout llContent = new LinearLayout(T.Tr());
        llContent.setOrientation(1);
        llContent.setBackgroundDrawable(Tr.Ty("white_range_layout"));
        llLayout.addView(llContent, new LinearLayout.LayoutParams(-1, contentHeight));
        llContent.addView(T(0, helpDetailImageViewData.Tr(), true), new LinearLayout.LayoutParams(-1, (int) (((float) contentHeight) / (((float) com.txznet.comm.ui.TE.Tn.Ty()) * 1.25f))));
        LinearLayout llDetail = new LinearLayout(T.Tr());
        llDetail.setOrientation(0);
        llDetail.setGravity(16);
        llContent.addView(llDetail, new LinearLayout.LayoutParams(-1, -1));
        llDetail.addView(new View(T.Tr()), new LinearLayout.LayoutParams(0, -2, 2.0f));
        T6.T helpDetailImg = helpDetailImageViewData.T().get(0);
        if (!TextUtils.isEmpty(helpDetailImg.Tn)) {
            ImageView iv = new ImageView(T.Tr());
            iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            if (helpDetailImg.Tn.startsWith("qrcode:")) {
                try {
                    iv.setImageBitmap(Tk.T(helpDetailImg.Tn.replace("qrcode:", ""), (int) Tr.Tn("y200")));
                } catch (TE e) {
                    e.printStackTrace();
                }
            } else if (helpDetailImageViewData.f427T) {
                com.Ty.T.Tr.Tn.T().T("file://" + helpDetailImg.Tn, new com.Ty.T.Tr.T9.Tr(iv));
            } else {
                iv.setImageDrawable(Tr.Ty(helpDetailImg.Tn));
            }
            llDetail.addView(iv, new LinearLayout.LayoutParams(0, -2, 5.0f));
        }
        if (!TextUtils.isEmpty(helpDetailImg.Tr) && !TextUtils.isEmpty(helpDetailImg.Tn)) {
            llDetail.addView(new View(T.Tr()), new LinearLayout.LayoutParams(0, -2, 1.0f));
        }
        if (!TextUtils.isEmpty(helpDetailImg.Tr)) {
            TextView textView = new TextView(T.Tr());
            textView.setGravity(16);
            com.txznet.comm.Ty.Tk.T(textView, ((Float) com.txznet.comm.ui.Tr.T.Tr().T("help_itemSize1.list_itemSize1.base_size2")).floatValue());
            com.txznet.comm.Ty.Tk.T(textView, ((Integer) com.txznet.comm.ui.Tr.T.Tr().T("help_itemColor1.list_itemColor1.base_color1")).intValue());
            textView.setText(helpDetailImg.Tr);
            llDetail.addView(textView, new LinearLayout.LayoutParams(0, -2, 10.0f));
        }
        llDetail.addView(new View(T.Tr()), new LinearLayout.LayoutParams(0, -2, 2.0f));
        Tn.T adapter = new Tn.T();
        adapter.f462T = data.Ty();
        adapter.Tr = llLayout;
        adapter.Tn = T9();
        return adapter;
    }

    private View T(int position, String name, boolean showDivider) {
        RelativeLayout itemView = new RelativeLayout(T.Tr());
        itemView.setTag(Integer.valueOf(position));
        FrameLayout flContent = new FrameLayout(T.Tr());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.topMargin = (int) Tr.Tn("y2");
        layoutParams.bottomMargin = (int) Tr.Tn("y2");
        itemView.addView(flContent, layoutParams);
        LinearLayout llContent = new LinearLayout(T.Tr());
        llContent.setOrientation(0);
        llContent.setGravity(16);
        FrameLayout.LayoutParams mFLayoutParams = new FrameLayout.LayoutParams(-1, -1);
        mFLayoutParams.gravity = 16;
        flContent.addView(llContent, mFLayoutParams);
        LinearLayout llDetail = new LinearLayout(T.Tr());
        llDetail.setGravity(16);
        llDetail.setOrientation(1);
        LinearLayout.LayoutParams mLLayoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
        mLLayoutParams.gravity = 16;
        mLLayoutParams.leftMargin = (int) Tr.Tn("y15");
        llContent.addView(llDetail, mLLayoutParams);
        TextView tvContent = new TextView(T.Tr());
        tvContent.setEllipsize(TextUtils.TruncateAt.END);
        tvContent.setSingleLine();
        tvContent.setGravity(80);
        LinearLayout.LayoutParams mLLayoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        mLLayoutParams2.gravity = 17;
        mLLayoutParams2.rightMargin = (int) Tr.Tn("y6");
        llDetail.addView(tvContent, mLLayoutParams2);
        View divider = new View(T.Tr());
        divider.setVisibility(8);
        divider.setBackgroundColor(Color.parseColor("#4c4c4c"));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) Math.ceil((double) Tr.Tn("y1")));
        layoutParams2.addRule(12);
        itemView.addView(divider, layoutParams2);
        if (showDivider) {
            com.txznet.comm.Ty.Tk.T(tvContent, ((Float) com.txznet.comm.ui.Tr.T.Tr().T("help_list_label_itemSize1")).floatValue());
            com.txznet.comm.Ty.Tk.T(tvContent, ((Integer) com.txznet.comm.ui.Tr.T.Tr().T("help_itemColor1.list_itemColor1.base_color1")).intValue());
        } else {
            com.txznet.comm.Ty.Tk.T(tvContent, ((Float) com.txznet.comm.ui.Tr.T.Tr().T("help_itemSize1.list_itemSize1.base_size2")).floatValue());
            com.txznet.comm.Ty.Tk.T(tvContent, ((Integer) com.txznet.comm.ui.Tr.T.Tr().T("help_itemColor1.list_itemColor1.base_color1")).intValue());
        }
        if (T9.T(name)) {
            name = "";
        }
        tvContent.setText(name);
        divider.setVisibility(showDivider ? 0 : 4);
        return itemView;
    }

    public void T(int progress, int selection) {
    }

    public void T(boolean next) {
    }
}
