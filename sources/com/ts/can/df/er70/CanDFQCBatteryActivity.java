package com.ts.can.df.er70;

import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanDFQCBatteryActivity extends CanDFQCBaseActivity {
    private String a;
    private String c;
    private String e;
    private TextView mTvA;
    private TextView mTvC;
    private TextView mTvE;
    private TextView mTvEnotice;
    private TextView mTvEvalule;
    private TextView mTvR;
    private TextView mTvV;
    private String r;
    private String v;
    private String value = TXZResourceManager.STYLE_DEFAULT;

    /* access modifiers changed from: protected */
    public void InitLayout() {
        SetBackground(R.drawable.can_dfqc_dianc_bg);
        this.mTvV = AddText(121, 171, 203, 25);
        this.mTvA = AddText(121, 210, KeyDef.RKEY_RADIO_6S, 25);
        this.mTvC = AddText(121, Can.CAN_TOYOTA_SP_XP, 203, 25);
        this.mTvR = AddText(121, 293, 203, 25);
        this.mTvE = AddText(121, KeyDef.RKEY_EJECT_L, 203, 25);
        this.mTvEnotice = AddText(681, 483, 121, 26);
        this.mTvEnotice.setText(R.string.can_dfqc_battery_e);
        this.mTvEvalule = AddText(863, 376, 203, 56);
        this.mTvEvalule.setTextColor(-1);
        this.mTvEvalule.setTextSize(0, 41.0f);
        initValue(true);
    }

    private void initValue(boolean isSet) {
        this.v = getString(R.string.can_dfqc_battery_v);
        this.a = getString(R.string.can_dfqc_battery_a);
        this.c = getString(R.string.can_dfqc_battery_c);
        this.r = getString(R.string.can_dfqc_battery_r);
        this.e = getString(R.string.can_dfqc_battery_e);
        if (isSet) {
            this.mTvV.setText(this.v);
            this.mTvA.setText(this.a);
            this.mTvC.setText(this.c);
            this.mTvR.setText(this.r);
            this.mTvE.setText(this.e);
            this.mTvEvalule.setText(this.value);
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        VenuciaGetVcuSta2();
        VenuciaGetVcuSta1();
        VenuciaGetVcuSta3();
        VenuciaGetVcuLife();
        initValue(false);
        if (i2b(this.mVcuSta2.UpdateOnce) && (!check || i2b(this.mVcuSta2.Update))) {
            this.mVcuSta2.Update = 0;
            this.a = String.valueOf(this.a) + "  " + String.format("%.1f A", new Object[]{Double.valueOf((((double) this.mVcuSta2.VCU_Battery_Current) * 0.1d) - 500.0d)});
            this.v = String.valueOf(this.v) + "  " + String.format("%.1f V", new Object[]{Double.valueOf(((double) this.mVcuSta2.VCU_Battery_Voltage) * 0.1d)});
            this.mTvA.setText(this.a);
            this.mTvV.setText(this.v);
        }
        if (i2b(this.mVcuSta1.UpdateOnce) && (!check || i2b(this.mVcuSta1.Update))) {
            this.mVcuSta1.Update = 0;
            this.c = String.valueOf(this.c) + "  " + String.format("%d ℃", new Object[]{Integer.valueOf(this.mVcuSta1.VCU_SigTemp_Highest_NO - 40)});
            this.mTvC.setText(this.c);
        }
        if (i2b(this.mVcuLife.UpdateOnce) && (!check || i2b(this.mVcuLife.Update))) {
            this.mVcuLife.Update = 0;
            this.r = String.valueOf(this.r) + "  " + String.format("%d KΩ", new Object[]{Integer.valueOf(this.mVcuLife.jyzz)});
            this.mTvR.setText(this.r);
        }
        if (!i2b(this.mVcuSta3.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mVcuSta3.Update)) {
            this.mVcuSta3.Update = 0;
            this.value = String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(((float) this.mVcuSta3.VCU_SOC) * 0.4f)})) + "%";
            this.e = String.valueOf(this.e) + "  " + this.value;
            this.mTvE.setText(this.e);
            this.mTvEvalule.setText(this.value);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
    }
}
