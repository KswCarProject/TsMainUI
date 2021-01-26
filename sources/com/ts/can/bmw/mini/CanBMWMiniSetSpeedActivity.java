package com.ts.can.bmw.mini;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanNumInuptDlg;
import com.txznet.sdk.TXZResourceManager;

public class CanBMWMiniSetSpeedActivity extends CanBMWMiniBaseActivity {
    public static final int ITEM_SPEED_SELECT_VALUE = 2;
    public static final int ITEM_SPEED_WARN = 0;
    public static final int ITEM_SPEED_WARN_VALUE = 1;
    private CanItemTitleValList mItemSelectVal;
    /* access modifiers changed from: private */
    public CanItemTitleValList mItemValue;
    private CanItemSwitchList mItemWarn;

    /* access modifiers changed from: protected */
    public void AddItemView() {
        this.mItemWarn = AddItemCheck(R.string.can_speed_warn, 0);
        this.mItemValue = AddItemTitleVal(R.string.can_speed_warn_value, 1);
        this.mItemSelectVal = AddItemTitleVal(R.string.can_speed_select_value, 2);
        this.mItemSelectVal.SetIconVisibility(8);
        this.mItemSelectVal.SetValVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetMiniSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            int warn = this.mSetData.fgSpeedWarn;
            this.mItemWarn.SetCheck(warn);
            if (i2b(warn)) {
                this.mItemValue.setEnabled(true);
                this.mItemSelectVal.setEnabled(true);
                setSpeed(this.mSetData.SpeedWarnVal);
                return;
            }
            this.mItemValue.setEnabled(false);
            this.mItemSelectVal.setEnabled(false);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(65);
        Sleep(1);
        Query(66);
    }

    private void setSpeed(int speed) {
        if (speed < 0 || speed > 400) {
            this.mItemValue.SetVal(TXZResourceManager.STYLE_DEFAULT);
        } else {
            this.mItemValue.SetVal(String.valueOf(String.valueOf(speed)) + " KM/H");
        }
    }

    public void onClick(View v) {
        int item = ((Integer) v.getTag()).intValue();
        switch (item) {
            case 0:
                int param = 130;
                if (this.mSetData.fgSpeedWarn == 1) {
                    param = 130 - 128;
                }
                CarSet(65, param);
                return;
            case 1:
                new CanNumInuptDlg(this, new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 400) {
                            CanBMWMiniSetSpeedActivity.this.mItemValue.SetVal(String.valueOf(val) + " KM/H");
                            CanBMWMiniSetSpeedActivity.this.CarSet(66, num / 256, num % 256, 0, 0);
                        }
                    }
                }, 3, item);
                return;
            case 2:
                CarSet(67, 255);
                return;
            default:
                return;
        }
    }
}
