package com.txznet.comm.ui.T5.Tr.T;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.txznet.comm.Tr.T;
import com.txznet.comm.Ty.Tk;
import com.txznet.comm.ui.T5.T.TD;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.TA;
import com.txznet.comm.ui.TE.T9;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.comm.ui.TE.Ty;
import com.txznet.comm.ui.TZ.Tn;
import com.txznet.comm.ui.view.RippleView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Proguard */
public class T5 extends TA {
    private static T5 Tk = new T5();
    private int T5;
    private int TE;
    private List<View> TZ;
    private int Th;
    private int Tv;

    public static T5 T9() {
        return Tk;
    }

    public void Tr() {
        super.Tr();
        this.TE = (int) Tn.Tr(Tn.Ty);
        this.T5 = (int) Tn.Tr(Tn.Tn);
        this.Tv = (int) Tn.T(Tn.Tr);
        this.Th = (int) Math.ceil((double) Tn.Tr(Tn.Tq));
    }

    @SuppressLint({"NewApi"})
    public Tn.T T(TM data) {
        boolean z;
        TD reminderListViewData = (TD) data;
        Tn.T titleViewAdapter = T6.T().T((TM) reminderListViewData);
        LinearLayout llLayout = new LinearLayout(T.Tr());
        llLayout.setGravity(16);
        llLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, T6.T().T5());
        layoutParams.gravity = 16;
        llLayout.addView(titleViewAdapter.Tr, layoutParams);
        LinearLayout llContent = new LinearLayout(T.Tr());
        llContent.setOrientation(1);
        llContent.setBackground(Tr.Ty("white_range_layout"));
        llLayout.addView(llContent, new LinearLayout.LayoutParams(-1, com.txznet.comm.ui.TE.T.T(false) * com.txznet.comm.ui.TE.T.TE()));
        llContent.setLayoutAnimation(Ty.T());
        llContent.setLayoutAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                if (T5.this.f463T != null) {
                    T5.this.f463T.T(animation, 1);
                }
            }

            public void onAnimationRepeat(Animation animation) {
                if (T5.this.f463T != null) {
                    T5.this.f463T.T(animation, 2);
                }
            }

            public void onAnimationEnd(Animation animation) {
                if (T5.this.f463T != null) {
                    T5.this.f463T.T(animation, 3);
                }
            }
        });
        this.TZ = new ArrayList();
        for (int i = 0; i < reminderListViewData.T9; i++) {
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, com.txznet.comm.ui.TE.T.T(false));
            TD.T t = reminderListViewData.T().get(i);
            if (i != com.txznet.comm.ui.TE.T.TE() - 1) {
                z = true;
            } else {
                z = false;
            }
            View itemView = T(i, t, z);
            llContent.addView(itemView, layoutParams2);
            this.TZ.add(itemView);
        }
        Tn.T viewAdapter = new Tn.T();
        viewAdapter.f462T = data.Ty();
        viewAdapter.Tr = llLayout;
        viewAdapter.Ty = true;
        viewAdapter.Tn = T9();
        return viewAdapter;
    }

    @SuppressLint({"NewApi"})
    private View T(int position, TD.T reminderBean, boolean showDivider) {
        RippleView itemView = new RippleView(T.Tr());
        itemView.setTag(Integer.valueOf(position));
        itemView.setOnClickListener(T6.T().TZ());
        itemView.setOnTouchListener(T6.T().TE());
        itemView.setMinimumHeight((int) Tr.Tn("m100"));
        FrameLayout flContent = new FrameLayout(T.Tr());
        itemView.addView(flContent, new RelativeLayout.LayoutParams(-1, -1));
        RelativeLayout rlItem = new RelativeLayout(T.Tr());
        flContent.addView(rlItem, new FrameLayout.LayoutParams(-1, -1));
        rlItem.setMinimumHeight((int) Tr.Tn("m80"));
        TextView tvNum = new TextView(T.Tr());
        tvNum.setId(T9.T());
        tvNum.setBackground(Tr.Ty("poi_item_circle_bg"));
        tvNum.setGravity(17);
        tvNum.setIncludeFontPadding(false);
        tvNum.setPadding(0, 0, 0, 0);
        RelativeLayout.LayoutParams mRLayoutParams = new RelativeLayout.LayoutParams(this.TE, this.T5);
        mRLayoutParams.leftMargin = this.Tv;
        mRLayoutParams.addRule(15);
        rlItem.addView(tvNum, mRLayoutParams);
        TextView tvContent = new TextView(T.Tr());
        tvContent.setTextColor(-1);
        tvContent.setTextSize(Tr.Tn("m26"));
        tvContent.setSingleLine();
        tvContent.setEllipsize(TextUtils.TruncateAt.END);
        tvContent.setId(T9.T());
        RelativeLayout.LayoutParams mRLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        mRLayoutParams2.leftMargin = (int) Tr.Tn("m16");
        mRLayoutParams2.topMargin = (int) Tr.Tn("m19");
        mRLayoutParams2.addRule(1, tvNum.getId());
        rlItem.addView(tvContent, mRLayoutParams2);
        ImageView ivTimeIcon = new ImageView(T.Tr());
        ivTimeIcon.setBackground(Tr.Ty("reminder_time_icon"));
        ivTimeIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ivTimeIcon.setId(T9.T());
        RelativeLayout.LayoutParams mRLayoutParams3 = new RelativeLayout.LayoutParams((int) Tr.Tn("m20"), (int) Tr.Tn("m24"));
        mRLayoutParams3.topMargin = (int) Tr.Tn("m5");
        mRLayoutParams3.addRule(5, tvContent.getId());
        mRLayoutParams3.addRule(3, tvContent.getId());
        rlItem.addView(ivTimeIcon, mRLayoutParams3);
        TextView tvTime = new TextView(T.Tr());
        tvTime.setTextColor(-1);
        tvTime.setTextSize(Tr.Tn("m20"));
        tvContent.setSingleLine();
        tvContent.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams mRLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        mRLayoutParams4.leftMargin = (int) Tr.Tn("m12");
        mRLayoutParams4.addRule(4, ivTimeIcon.getId());
        mRLayoutParams4.addRule(3, tvContent.getId());
        mRLayoutParams4.addRule(1, ivTimeIcon.getId());
        rlItem.addView(tvTime, mRLayoutParams4);
        TextView tvFullTime = new TextView(T.Tr());
        tvFullTime.setId(T9.T());
        tvFullTime.setVisibility(4);
        tvFullTime.setText("2017/12/19 12:00");
        tvFullTime.setTextSize(Tr.Tn("m20"));
        RelativeLayout.LayoutParams mRLayoutParams5 = new RelativeLayout.LayoutParams(-2, (int) Tr.Tn("m1"));
        mRLayoutParams5.leftMargin = (int) Tr.Tn("m12");
        mRLayoutParams5.addRule(3, tvContent.getId());
        mRLayoutParams5.addRule(1, ivTimeIcon.getId());
        rlItem.addView(tvFullTime, mRLayoutParams5);
        ImageView ivPositionIcon = new ImageView(T.Tr());
        ivPositionIcon.setBackground(Tr.Ty("reminder_position_icon"));
        ivPositionIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ivPositionIcon.setId(T9.T());
        RelativeLayout.LayoutParams mRLayoutParams6 = new RelativeLayout.LayoutParams((int) Tr.Tn("m20"), (int) Tr.Tn("m24"));
        mRLayoutParams6.topMargin = (int) Tr.Tn("m5");
        mRLayoutParams6.leftMargin = (int) Tr.Tn("m27");
        mRLayoutParams6.addRule(1, tvFullTime.getId());
        mRLayoutParams6.addRule(3, tvContent.getId());
        rlItem.addView(ivPositionIcon, mRLayoutParams6);
        TextView textView = new TextView(T.Tr());
        textView.setTextColor(-1);
        textView.setTextSize(Tr.Tn("m20"));
        tvContent.setSingleLine();
        tvContent.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams mRLayoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
        mRLayoutParams7.leftMargin = (int) Tr.Tn("m12");
        mRLayoutParams7.addRule(4, ivPositionIcon.getId());
        mRLayoutParams7.addRule(3, tvContent.getId());
        mRLayoutParams7.addRule(1, ivPositionIcon.getId());
        rlItem.addView(textView, mRLayoutParams7);
        View divider = new View(T.Tr());
        divider.setVisibility(8);
        divider.setBackgroundColor(Color.parseColor("#4c4c4c"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.Th);
        layoutParams.addRule(12);
        itemView.addView(divider, layoutParams);
        tvNum.setText(String.valueOf(position + 1));
        tvContent.setText(reminderBean.Tr);
        divider.setVisibility(showDivider ? 0 : 4);
        Tk.T(tvNum, "reminder_indexSize1.list_indexSize1");
        Tk.Tr(tvNum, "reminder_indexColor1.list_indexColor1.base_color1");
        Tk.T(tvContent, "reminder_itemSize1.list_itemSize1.base_size2");
        Tk.Tr(tvContent, "reminder_indexColor1.list_indexColor1.base_color1");
        Tk.T(tvTime, "reminder_itemSize2.list_itemSize1.base_size2");
        Tk.Tr(tvTime, "reminder_itemColor2.list_itemColor1.base_color1");
        Tk.T(tvFullTime, "reminder_itemSize2.list_itemSize1.base_size2");
        Tk.T(textView, "reminder_itemSize3.list_itemSize1.base_size2");
        Tk.Tr(textView, "reminder_itemColor3.list_itemColor1.base_color1");
        if (!TextUtils.isEmpty(reminderBean.Ty)) {
            tvTime.setText(reminderBean.Ty);
            ivTimeIcon.setVisibility(0);
            tvTime.setVisibility(0);
        } else {
            tvTime.setVisibility(4);
            ivTimeIcon.setVisibility(4);
        }
        if (!TextUtils.isEmpty(reminderBean.Tn)) {
            textView.setText(reminderBean.Tn);
            ivPositionIcon.setVisibility(0);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(4);
            ivPositionIcon.setVisibility(4);
        }
        itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                RippleView rippleView = (RippleView) v;
                if (hasFocus) {
                    rippleView.setBackgroundColor(Color.parseColor("#4AA5FA"));
                } else {
                    rippleView.setBackgroundColor(0);
                }
            }
        });
        return itemView;
    }

    public void T(int progress, int selection) {
    }

    public void T(boolean next) {
    }
}
