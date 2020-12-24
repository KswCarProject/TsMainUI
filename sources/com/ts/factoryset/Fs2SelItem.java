package com.ts.factoryset;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.ParamButton;

/* compiled from: FsOtherActivity */
class Fs2SelItem {
    private ParamButton[] mBtnSW = null;
    private int[] mSWId = {R.id.fssw_sw0, R.id.fssw_sw1, R.id.fssw_sw2};
    protected View mView;
    private TextView tvValue = null;

    public Fs2SelItem(ViewGroup parent, int id, String strTitle, String strOff, String strOn, View.OnClickListener l) {
        this.mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fs_item_2sw, (ViewGroup) null);
        parent.addView(this.mView);
        this.mBtnSW = new ParamButton[2];
        ((TextView) this.mView.findViewById(R.id.fssw_title)).setText(strTitle);
        this.tvValue = (TextView) this.mView.findViewById(R.id.fssw_val);
        for (int i = 0; i < 2; i++) {
            this.mBtnSW[i] = (ParamButton) this.mView.findViewById(this.mSWId[i]);
            this.mBtnSW[i].setIntParam(id);
            this.mBtnSW[i].setIntParam2(i);
            this.mBtnSW[i].setOnClickListener(l);
        }
        this.mBtnSW[0].setText(strOff);
        this.mBtnSW[1].setText(strOn);
    }

    public Fs2SelItem(ViewGroup parent, int id, int title, int off, int on, View.OnClickListener l) {
        this.mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fs_item_2sw, (ViewGroup) null);
        parent.addView(this.mView);
        this.mBtnSW = new ParamButton[2];
        ((TextView) this.mView.findViewById(R.id.fssw_title)).setText(title);
        this.tvValue = (TextView) this.mView.findViewById(R.id.fssw_val);
        for (int i = 0; i < 2; i++) {
            this.mBtnSW[i] = (ParamButton) this.mView.findViewById(this.mSWId[i]);
            this.mBtnSW[i].setIntParam(id);
            this.mBtnSW[i].setIntParam2(i);
            this.mBtnSW[i].setOnClickListener(l);
        }
        this.mBtnSW[0].setText(off);
        this.mBtnSW[1].setText(on);
    }

    public void SetSel(int sel) {
        boolean z;
        boolean z2 = true;
        ParamButton paramButton = this.mBtnSW[0];
        if (sel == 0) {
            z = true;
        } else {
            z = false;
        }
        paramButton.setSelected(z);
        ParamButton paramButton2 = this.mBtnSW[1];
        if (sel == 0) {
            z2 = false;
        }
        paramButton2.setSelected(z2);
    }

    public void SetVal(int val) {
        boolean z = true;
        this.mBtnSW[0].setSelected(val == 0);
        ParamButton paramButton = this.mBtnSW[1];
        if (val == 0) {
            z = false;
        }
        paramButton.setSelected(z);
        if (this.tvValue != null) {
            this.tvValue.setText(" " + val);
        }
    }
}
