package com.ts.can.df.er70;

import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanDFQCCarInfoActivity extends CanDFQCBaseActivity implements View.OnClickListener {
    private static final int ITEM_AC = 1;
    private static final int ITEM_BATTERY = 0;
    private static final int ITEM_CONTROL = 4;
    private static final int ITEM_ERROR = 3;
    private static final int ITEM_MOTOR = 2;
    private static final int ITEM_VERSION = 5;
    private ParamButton[] mBatteryArray = new ParamButton[6];
    private ParamButton mBtnAC;
    private ParamButton mBtnBattery;
    private ParamButton mBtnControl;
    private ParamButton mBtnError;
    private ParamButton mBtnMotor;
    private ParamButton mBtnVersion;
    private View.OnTouchListener mListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case 0:
                    CanDFQCCarInfoActivity.this.setBatterySelected(true);
                    break;
                case 1:
                case 3:
                case 4:
                    CanDFQCCarInfoActivity.this.setBatterySelected(false);
                    break;
                case 2:
                    CanDFQCCarInfoActivity.this.setBatterySelected(v.isPressed());
                    break;
            }
            return false;
        }
    };

    /* access modifiers changed from: protected */
    public void InitLayout() {
        SetBackground(R.drawable.can_dfqc_main_bg);
        this.mBtnBattery = this.mManager.AddButton(141, 51);
        this.mBtnBattery.setTag(0);
        this.mBtnBattery.setDrawable(R.drawable.can_dfqc_main_dc02_up, R.drawable.can_dfqc_main_dc02_dn);
        this.mBtnBattery.setOnClickListener(this);
        this.mBtnBattery.setOnTouchListener(this.mListener);
        this.mBtnAC = this.mManager.AddButton(802, 27);
        this.mBtnAC.setTag(1);
        this.mBtnAC.setDrawable(R.drawable.can_dfqc_main_ac_up, R.drawable.can_dfqc_main_ac_dn);
        this.mBtnAC.setOnClickListener(this);
        this.mBtnMotor = this.mManager.AddButton(91, Can.CAN_X80_RZC);
        this.mBtnMotor.setTag(2);
        this.mBtnMotor.setDrawable(R.drawable.can_dfqc_main_dianji_up, R.drawable.can_dfqc_main_dianji_dn);
        this.mBtnMotor.setOnClickListener(this);
        this.mBtnError = this.mManager.AddButton(802, 219);
        this.mBtnError.setTag(3);
        this.mBtnError.setDrawable(R.drawable.can_dfqc_main_guzhang_up, R.drawable.can_dfqc_main_guzhang_dn);
        this.mBtnError.setOnClickListener(this);
        this.mBtnControl = this.mManager.AddButton(132, CanCameraUI.BTN_TRUMPCHI_GS4_MODE4);
        this.mBtnControl.setTag(4);
        this.mBtnControl.setDrawable(R.drawable.can_dfqc_main_diankong_up, R.drawable.can_dfqc_main_diankong_dn);
        this.mBtnControl.setOnClickListener(this);
        this.mBtnVersion = this.mManager.AddButton(719, 406);
        this.mBtnVersion.setTag(5);
        this.mBtnVersion.setDrawable(R.drawable.can_dfqc_main_banben_up, R.drawable.can_dfqc_main_banben_dn);
        this.mBtnVersion.setOnClickListener(this);
        for (int i = 0; i < this.mBatteryArray.length; i++) {
            this.mBatteryArray[i] = this.mManager.AddButton((i * 11) + Can.CAN_DF_WC, 62, 9, 33);
            this.mBatteryArray[i].setClickable(false);
            this.mBatteryArray[i].setDrawable(R.drawable.can_dfqc_main_dc01_up, R.drawable.can_dfqc_main_dc01_dn);
        }
        AddText(162, 115, R.string.can_dfqc_battery);
        AddText(129, 310, R.string.can_dfqc_motor);
        AddText(162, 490, R.string.can_dfqc_control);
        AddText(KeyDef.SKEY_HOME_3, 114, R.string.can_dfqc_ac);
        AddText(KeyDef.SKEY_HOME_3, 306, R.string.can_dfqc_error);
        AddText(752, 492, R.string.can_dfqc_version);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanDFQCBatteryActivity.class);
                return;
            case 1:
                enterSubWin(CanDFQCACActivity.class);
                return;
            case 2:
                enterSubWin(CanDFQCMotorActivity.class);
                return;
            case 3:
                enterSubWin(CanDFQCErrorActivity.class);
                return;
            case 4:
                enterSubWin(CanDFQCControlActivity.class);
                return;
            case 5:
                enterSubWin(CanDFQCVersionActivity.class);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        VenuciaGetVcuSta3();
        if (!i2b(this.mVcuSta3.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mVcuSta3.Update)) {
            this.mVcuSta3.Update = 0;
            setBatteryVisible(Math.round((((float) this.mVcuSta3.VCU_SOC) * 0.4f) / (100.0f / ((float) this.mBatteryArray.length))));
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
    }

    /* access modifiers changed from: private */
    public void setBatterySelected(boolean isSelected) {
        for (ParamButton selected : this.mBatteryArray) {
            selected.setSelected(isSelected);
        }
    }

    private void setBatteryVisible(int startPosition) {
        if (startPosition <= this.mBatteryArray.length) {
            for (int i = 0; i < this.mBatteryArray.length; i++) {
                if (i < startPosition) {
                    this.mBatteryArray[(this.mBatteryArray.length - 1) - i].setVisibility(0);
                } else {
                    this.mBatteryArray[(this.mBatteryArray.length - 1) - i].setVisibility(4);
                }
            }
        }
    }
}
