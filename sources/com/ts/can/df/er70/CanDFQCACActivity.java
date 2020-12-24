package com.ts.can.df.er70;

import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.KeyDef;

public class CanDFQCACActivity extends CanDFQCBaseActivity {
    private String a;
    private String c;
    private TextView mTvA;
    private TextView mTvC;
    private TextView mTvMode;
    private TextView mTvP;
    private TextView mTvWind;
    private String mode;
    private String p;
    private String wind;

    /* access modifiers changed from: protected */
    public void InitLayout() {
        SetBackground(R.drawable.can_dfqc_ac_bg);
        this.mTvA = AddText(121, 173, 203, 25);
        this.mTvP = AddText(121, 212, KeyDef.RKEY_RADIO_6S, 25);
        this.mTvMode = AddText(121, Can.CAN_FLAT_RZC, 203, 25);
        this.mTvC = AddText(121, KeyDef.RKEY_AMS, 139, 25);
        this.mTvWind = AddText(121, KeyDef.RKEY_AVIN, 53, 25);
        initValue();
    }

    private void initValue() {
        this.a = getString(R.string.can_dfqc_ac_a);
        this.p = getString(R.string.can_dfqc_ac_p);
        this.mode = getString(R.string.can_dfqc_ac_mode);
        this.c = getString(R.string.can_dfqc_ac_c);
        this.wind = getString(R.string.can_dfqc_ac_wind);
        setValue();
    }

    private void setValue() {
        this.mTvA.setText(this.a);
        this.mTvP.setText(this.p);
        this.mTvMode.setText(this.mode);
        this.mTvC.setText(this.c);
        this.mTvWind.setText(this.wind);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void Query() {
    }
}
