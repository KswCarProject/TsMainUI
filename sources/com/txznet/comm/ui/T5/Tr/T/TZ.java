package com.txznet.comm.ui.T5.Tr.T;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.txznet.comm.Tr.T;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.T.Ts;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.Ts;
import com.txznet.comm.ui.TE.Ty;
import com.txznet.comm.ui.TZ.Tn;
import com.txznet.comm.ui.view.GradientProgressBar;
import com.txznet.comm.ui.view.RippleView;
import com.txznet.comm.ui.view.RoundImageView;
import com.txznet.txz.comm.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Proguard */
public class TZ extends Ts {
    private static TZ Tk;
    private int T5;
    private int TE;
    private List<View> TZ = new ArrayList();

    public static TZ T9() {
        if (Tk == null) {
            synchronized (TZ.class) {
                if (Tk == null) {
                    Tk = new TZ();
                }
            }
        }
        return Tk;
    }

    public void Tr() {
        super.Tr();
        this.TE = (int) Tn.Tr(Tn.Ty);
        this.T5 = (int) Tn.Tr(Tn.Tn);
    }

    public void T(int progress, int selection) {
    }

    public void T(boolean next) {
    }

    public void Ty() {
        super.Ty();
        if (this.TZ != null) {
            this.TZ.clear();
        }
    }

    public Tn.T T(TM data) {
        Tn.T viewAdapter = new Tn.T();
        viewAdapter.Ty = true;
        viewAdapter.Tn = this;
        viewAdapter.Tr = Ty(data);
        viewAdapter.f466T = data.Ty();
        return viewAdapter;
    }

    private View Ty(TM viewData) {
        boolean z;
        com.txznet.comm.ui.T5.T.Ts data = (com.txznet.comm.ui.T5.T.Ts) viewData;
        LinearLayout llLayout = new LinearLayout(T.Tr());
        llLayout.setGravity(16);
        llLayout.setOrientation(1);
        Tn.T titleAdapter = T6.T().T((TM) data);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, T6.T().T5());
        lp.gravity = 16;
        llLayout.addView(titleAdapter.Tr, lp);
        LinearLayout llContent = new LinearLayout(T.Tr());
        llContent.setOrientation(1);
        int itemHeight = com.txznet.comm.ui.TE.T.T(false);
        llLayout.addView(llContent, new LinearLayout.LayoutParams(-1, com.txznet.comm.ui.TE.T.TE() * itemHeight));
        llContent.setLayoutAnimation(Ty.T());
        llContent.setLayoutAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                if (TZ.this.f467T != null) {
                    TZ.this.f467T.T(animation, 1);
                }
            }

            public void onAnimationRepeat(Animation animation) {
                if (TZ.this.f467T != null) {
                    TZ.this.f467T.T(animation, 2);
                }
            }

            public void onAnimationEnd(Animation animation) {
                if (TZ.this.f467T != null) {
                    TZ.this.f467T.T(animation, 3);
                }
            }
        });
        this.TZ = new ArrayList();
        for (int i = 0; i < data.T9; i++) {
            RippleView itemView = new RippleView(T.Tr());
            itemView.setRippleDuration(300);
            itemView.setRippleType(RippleView.Tr.RECTANGLE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, itemHeight);
            Ts.T t = data.T().get(i);
            if (i != com.txznet.comm.ui.TE.T.TE() - 1) {
                z = true;
            } else {
                z = false;
            }
            T(itemView, i, t, z);
            llContent.addView(itemView, layoutParams);
            this.TZ.add(itemView);
        }
        return llLayout;
    }

    private void T(RippleView itemView, int position, Ts.T poi, boolean showDivider) {
        itemView.setTag(Integer.valueOf(position));
        itemView.setOnClickListener(T6.T().TZ());
        itemView.setOnTouchListener(T6.T().TE());
        int y2 = (int) itemView.getContext().getResources().getDimension(R.dimen.y2);
        int x10 = (int) itemView.getContext().getResources().getDimension(R.dimen.x10);
        int x16 = (int) itemView.getContext().getResources().getDimension(R.dimen.x16);
        int y24 = (int) itemView.getContext().getResources().getDimension(R.dimen.y24);
        int y30 = (int) itemView.getContext().getResources().getDimension(R.dimen.y30);
        int y56 = (int) itemView.getContext().getResources().getDimension(R.dimen.y56);
        int y80 = (int) itemView.getContext().getResources().getDimension(R.dimen.y80);
        FrameLayout contentFrameLayout = new FrameLayout(itemView.getContext());
        RelativeLayout.LayoutParams frameParams = new RelativeLayout.LayoutParams(-1, -1);
        frameParams.bottomMargin = y2;
        frameParams.topMargin = y2;
        contentFrameLayout.setLayoutParams(frameParams);
        itemView.addView(contentFrameLayout);
        FrameLayout layout = new FrameLayout(itemView.getContext());
        layout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        contentFrameLayout.addView(layout);
        GradientProgressBar bar = new GradientProgressBar(itemView.getContext());
        bar.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        layout.addView(bar);
        LinearLayout linearLayout = new LinearLayout(itemView.getContext());
        linearLayout.setOrientation(0);
        linearLayout.setMinimumHeight(y80);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        layout.addView(linearLayout);
        TextView tvView = new TextView(itemView.getContext());
        tvView.setTextColor(-1);
        tvView.setTextSize((float) y30);
        tvView.setGravity(17);
        tvView.setIncludeFontPadding(false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(this.TE, this.T5);
        params.gravity = 17;
        params.leftMargin = x10;
        tvView.setLayoutParams(params);
        linearLayout.addView(tvView);
        RoundImageView roundImageView = new RoundImageView(itemView.getContext());
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(y56, y56);
        params2.leftMargin = x16;
        params2.gravity = 16;
        roundImageView.setLayoutParams(params2);
        linearLayout.addView(roundImageView);
        LinearLayout layout2 = new LinearLayout(itemView.getContext());
        layout2.setOrientation(0);
        layout2.setMinimumHeight(y80);
        layout2.setGravity(16);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(0, -1, 1.0f);
        params3.gravity = 16;
        params3.leftMargin = x16;
        layout2.setLayoutParams(params3);
        linearLayout.addView(layout2);
        TextView nameTv = new TextView(itemView.getContext());
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(-2, -1, 1.0f);
        params4.gravity = 16;
        nameTv.setEms(10);
        nameTv.setEllipsize(TextUtils.TruncateAt.END);
        nameTv.setSingleLine();
        nameTv.setGravity(16);
        nameTv.setLayoutParams(params4);
        nameTv.setTextColor(Color.parseColor("#adb6cc"));
        nameTv.setTextSize((float) y24);
        layout2.addView(nameTv);
        tvView.setText(String.valueOf(position + 1));
        roundImageView.setImageDrawable(T(itemView.getContext(), poi.f459T));
        nameTv.setText(poi.Tr);
        if (showDivider) {
            View view = new View(itemView.getContext());
            view.setBackgroundColor(Color.parseColor("#4c4c4c"));
            RelativeLayout.LayoutParams diviParams = new RelativeLayout.LayoutParams(-1, (int) Math.ceil((double) com.txznet.comm.ui.TZ.Tn.Tr(com.txznet.comm.ui.TZ.Tn.Tq)));
            diviParams.addRule(12);
            view.setLayoutParams(diviParams);
            itemView.addView(view);
        }
    }

    private Drawable T(Context mContext, String navPkn) {
        try {
            return mContext.getPackageManager().getApplicationIcon(navPkn);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
