package com.ts.can.df.er70;

import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.KeyDef;

public class CanDFQCControlActivity extends CanDFQCBaseActivity {
    private String a;
    private TextView mTvA;
    private TextView mTvP;
    private TextView mTvV;
    private TextView mTvX;
    private String p;
    private String v;
    private String x;

    /* access modifiers changed from: protected */
    public void InitLayout() {
        SetBackground(R.drawable.can_dfqc_diankong_bg);
        this.mTvV = AddText(121, 188, 203, 25);
        this.mTvA = AddText(121, Can.CAN_JIANGLING_MYX, KeyDef.RKEY_RADIO_6S, 25);
        this.mTvX = AddText(121, 269, 203, 25);
        this.mTvP = AddText(121, KeyDef.RKEY_MEDIA_SUBT, 139, 25);
        initValue(true);
    }

    private void initValue(boolean isSet) {
        this.v = getString(R.string.can_dfqc_control_v);
        this.a = getString(R.string.can_dfqc_control_a);
        this.x = getString(R.string.can_dfqc_control_x);
        this.p = getString(R.string.can_dfqc_control_p);
        if (isSet) {
            this.mTvV.setText(this.v);
            this.mTvA.setText(this.a);
            this.mTvX.setText(this.x);
            this.mTvP.setText(this.p);
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        VenuciaGetMotorSta3();
        initValue(false);
        if (!i2b(this.mMotorSta3.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mMotorSta3.Update)) {
            this.mMotorSta3.Update = 0;
            this.v = String.valueOf(this.v) + "  " + String.format("%.1f V", new Object[]{Float.valueOf(((float) this.mMotorSta3.MOTORC_Motor_Vol) * 0.1f)});
            this.a = String.valueOf(this.a) + "  " + String.format("%.1f A", new Object[]{Float.valueOf((((float) this.mMotorSta3.MOTORC_Motor_Crt) * 0.1f) - 1500.0f)});
            this.mTvV.setText(this.v);
            this.mTvA.setText(this.a);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
    }
}
