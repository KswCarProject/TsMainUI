package com.ts.can.df.er70;

import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.KeyDef;

public class CanDFQCMotorActivity extends CanDFQCBaseActivity {
    private String a;
    private String c;
    private TextView mTvA;
    private TextView mTvC;
    private TextView mTvOut;
    private TextView mTvP;
    private TextView mTvR;
    private TextView mTvV;
    private String out;
    private String p;
    private String r;
    private String v;

    /* access modifiers changed from: protected */
    public void InitLayout() {
        SetBackground(R.drawable.can_dfqc_dianji_bg);
        this.mTvR = AddText(121, 168, KeyDef.RKEY_RADIO_6S, 25);
        this.mTvC = AddText(121, Can.CAN_LEXUS_ZMYT, KeyDef.RKEY_RADIO_6S, 25);
        this.mTvA = AddText(121, Can.CAN_LUXGEN_WC, 203, 25);
        this.mTvV = AddText(121, 290, 203, 25);
        this.mTvP = AddText(121, KeyDef.RKEY_RDS_PTY, 139, 25);
        this.mTvOut = AddText(121, 374, 203, 25);
        initValue(true);
    }

    private void initValue(boolean isSet) {
        this.r = getString(R.string.can_dfqc_motor_r);
        this.c = getString(R.string.can_dfqc_motor_c);
        this.a = getString(R.string.can_dfqc_motor_a);
        this.v = getString(R.string.can_dfqc_motor_v);
        this.p = getString(R.string.can_dfqc_motor_p);
        this.out = getString(R.string.can_dfqc_motor_out);
        if (isSet) {
            this.mTvR.setText(this.r);
            this.mTvC.setText(this.c);
            this.mTvA.setText(this.a);
            this.mTvV.setText(this.v);
            this.mTvP.setText(this.p);
            this.mTvOut.setText(this.out);
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        VenuciaGetMotorSpd();
        VenuciaGetMotorSta3();
        VenuciaGetChargerSta1();
        VenuciaGetVcuVp();
        initValue(false);
        if (i2b(this.mMotorSpd.UpdateOnce) && (!check || i2b(this.mMotorSpd.Update))) {
            this.mMotorSpd.Update = 0;
            this.r = String.valueOf(this.r) + "  " + String.format("%d r/min", new Object[]{Integer.valueOf(this.mMotorSpd.MOTORC_PwerSpd_A - 32000)});
            this.mTvR.setText(this.r);
        }
        if (i2b(this.mMotorSta3.UpdateOnce) && (!check || i2b(this.mMotorSta3.Update))) {
            this.mMotorSta3.Update = 0;
            this.c = String.valueOf(this.c) + "  " + String.format("%d ℃", new Object[]{Integer.valueOf(this.mMotorSta3.MOTORC_Motor_Temp - 40)});
            this.mTvC.setText(this.c);
        }
        if (i2b(this.mChargerSta1.UpdateOnce) && (!check || i2b(this.mChargerSta1.Update))) {
            this.mChargerSta1.Update = 0;
            this.a = String.valueOf(this.a) + "  " + String.format("%.1f A", new Object[]{Float.valueOf((((float) this.mChargerSta1.CHARGER_Output_Crt) * 0.1f) - 600.0f)});
            this.v = String.valueOf(this.v) + "  " + String.format("%.1f V", new Object[]{Float.valueOf(((float) this.mChargerSta1.CHARGER_Output_Vol) * 0.1f)});
            this.mTvA.setText(this.a);
            this.mTvV.setText(this.v);
        }
        if (!i2b(this.mVcuVp.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mVcuVp.Update)) {
            this.mVcuVp.Update = 0;
            this.out = String.valueOf(this.out) + "  " + String.format("%d KW", new Object[]{Integer.valueOf(this.mVcuVp.VCU_Motor_LimtPower)});
            this.mTvOut.setText(this.out);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
    }
}
