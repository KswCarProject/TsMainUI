package com.txznet.comm.ui.T5.Tr.T;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.txznet.comm.Tr.T;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.T.Tj;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.TF;
import com.txznet.comm.ui.TE.Tr;
import com.txznet.txz.comm.R;

/* compiled from: Proguard */
public class T9 extends TF {
    private static T9 Tn = new T9();
    private View T9;

    public static T9 T9() {
        return Tn;
    }

    public void Tr() {
        this.Ty = 1;
        super.Tr();
    }

    public Tn.T T(TM data) {
        LinearLayout.LayoutParams params;
        Tj helpTipsViewData = (Tj) data;
        this.T9 = LayoutInflater.from(T.Tr()).inflate(R.layout.help_tip_view, (ViewGroup) null);
        LinearLayout llContent = (LinearLayout) this.T9.findViewById(R.id.llContent);
        ((TextView) this.T9.findViewById(R.id.tvTitle)).setText(helpTipsViewData.f449T);
        llContent.removeAllViews();
        llContent.setGravity(1);
        int itemHeight = (int) (((float) com.txznet.comm.ui.TE.T.T(false)) * 0.8f);
        for (int i = 0; i < helpTipsViewData.T().size(); i++) {
            Tj.T bean = helpTipsViewData.T().get(i);
            View view = LayoutInflater.from(T.Tr()).inflate(R.layout.help_tip_view_item, (ViewGroup) null);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            icon.setImageDrawable(Tr.Ty(bean.f450T));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) icon.getLayoutParams();
            layoutParams.width = (int) com.txznet.comm.ui.TZ.Tn.Tr(com.txznet.comm.ui.TZ.Tn.TB);
            layoutParams.height = (int) com.txznet.comm.ui.TZ.Tn.Tr(com.txznet.comm.ui.TZ.Tn.TK);
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(bean.Tr);
            if (i == 1) {
                icon.setScaleX(1.2f);
                icon.setScaleY(1.2f);
                title.setTextSize(title.getTextSize() * 1.2f);
                params = new LinearLayout.LayoutParams(-2, itemHeight);
            } else {
                icon.setAlpha(0.5f);
                title.setAlpha(0.5f);
                layoutParams.leftMargin = (int) (((float) layoutParams.leftMargin) * 2.5f);
                params = new LinearLayout.LayoutParams(-2, itemHeight);
            }
            llContent.addView(view, params);
        }
        Tn.T adapter = new Tn.T();
        adapter.f466T = data.Ty();
        adapter.Tr = this.T9;
        adapter.Tn = T9();
        return adapter;
    }

    public Object Tr(TM data) {
        LinearLayout.LayoutParams params;
        Tj helpTipsViewData = (Tj) data;
        LinearLayout llContent = (LinearLayout) this.T9.findViewById(R.id.llContent);
        ((TextView) this.T9.findViewById(R.id.tvTitle)).setText(helpTipsViewData.f449T);
        llContent.removeAllViews();
        llContent.setGravity(1);
        int itemHeight = (int) (((float) com.txznet.comm.ui.TE.T.T(false)) * 0.8f);
        for (int i = 0; i < helpTipsViewData.T().size(); i++) {
            Tj.T bean = helpTipsViewData.T().get(i);
            View view = LayoutInflater.from(T.Tr()).inflate(R.layout.help_tip_view_item, (ViewGroup) null);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            icon.setImageDrawable(Tr.Ty(bean.f450T));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) icon.getLayoutParams();
            layoutParams.width = (int) com.txznet.comm.ui.TZ.Tn.Tr(com.txznet.comm.ui.TZ.Tn.TB);
            layoutParams.height = (int) com.txznet.comm.ui.TZ.Tn.Tr(com.txznet.comm.ui.TZ.Tn.TK);
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(bean.Tr);
            if (i == 1) {
                icon.setScaleX(1.2f);
                icon.setScaleY(1.2f);
                title.setTextSize(title.getTextSize() * 1.2f);
                params = new LinearLayout.LayoutParams(-2, itemHeight);
            } else {
                icon.setAlpha(0.5f);
                title.setAlpha(0.5f);
                layoutParams.leftMargin = (int) (((float) layoutParams.leftMargin) * 2.5f);
                params = new LinearLayout.LayoutParams(-2, itemHeight);
            }
            llContent.addView(view, params);
        }
        return super.Tr(data);
    }
}
