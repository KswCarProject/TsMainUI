package com.ts.can.df.er70;

import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.KeyDef;

public class CanDFQCErrorActivity extends CanDFQCBaseActivity {
    private TextView mTvBattery;
    private TextView mTvControl;
    private TextView mTvMotor;

    /* access modifiers changed from: protected */
    public void InitLayout() {
        SetBackground(R.drawable.can_dfqc_guzhang_bg);
        this.mTvBattery = AddText(108, 188, 203, 25);
        this.mTvMotor = AddText(108, Can.CAN_MG_ZS_WC, 203, 25);
        this.mTvControl = AddText(108, KeyDef.RKEY_CMMB_PBC, 139, 25);
        this.mTvBattery.setText(R.string.can_dfqc_battery);
        this.mTvMotor.setText(R.string.can_dfqc_motor);
        this.mTvControl.setText(R.string.can_dfqc_control);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void Query() {
    }
}
