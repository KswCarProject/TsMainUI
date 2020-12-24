package com.ts.can.bmw.mini;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemSwitchList;

public class CanBMWMiniSetDisplayActivity extends CanBMWMiniBaseActivity {
    public static final int ITEM_AVG_CONSUMPTION = 1;
    public static final int ITEM_AVG_SPEED = 3;
    public static final int ITEM_CONSUMPTION = 2;
    public static final int ITEM_DATE = 4;
    public static final int ITEM_KD_DISTANCE = 0;
    private CanItemSwitchList mItemAvgConsumption;
    private CanItemSwitchList mItemAvgSpeed;
    private CanItemSwitchList mItemConsumption;
    private CanItemSwitchList mItemDate;
    private CanItemSwitchList mItemDistance;

    /* access modifiers changed from: protected */
    public void AddItemView() {
        this.mItemDistance = AddItemCheck(R.string.can_kd_range, 0);
        this.mItemAvgConsumption = AddItemCheck(R.string.can_avg_consump, 1);
        this.mItemConsumption = AddItemCheck(R.string.can_consumption, 2);
        this.mItemAvgSpeed = AddItemCheck(R.string.can_avg_spped, 3);
        this.mItemDate = AddItemCheck(R.string.can_date, 4);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetMiniSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemDistance.SetCheck(this.mSetData.fgRange);
            this.mItemAvgConsumption.SetCheck(this.mSetData.fgAvgConsumption);
            this.mItemConsumption.SetCheck(this.mSetData.fgConsumption);
            this.mItemAvgSpeed.SetCheck(this.mSetData.fgAvgSpeed);
            this.mItemDate.SetCheck(this.mSetData.fgDate);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(1);
    }

    public void onClick(View v) {
        int item = ((Integer) v.getTag()).intValue();
        int param = getCarSetParam();
        switch (item) {
            case 0:
                if (this.mSetData.fgRange != 1) {
                    param += 128;
                    break;
                } else {
                    param -= 128;
                    break;
                }
            case 1:
                if (this.mSetData.fgAvgConsumption != 1) {
                    param += 64;
                    break;
                } else {
                    param -= 64;
                    break;
                }
            case 2:
                if (this.mSetData.fgConsumption != 1) {
                    param += 32;
                    break;
                } else {
                    param -= 32;
                    break;
                }
            case 3:
                if (this.mSetData.fgAvgSpeed != 1) {
                    param += 16;
                    break;
                } else {
                    param -= 16;
                    break;
                }
            case 4:
                if (this.mSetData.fgDate != 1) {
                    param += 8;
                    break;
                } else {
                    param -= 8;
                    break;
                }
        }
        CarSet(1, param);
    }

    private int getCarSetParam() {
        int param = 0;
        if (this.mSetData.fgDate == 1) {
            param = 0 + 8;
        }
        if (this.mSetData.fgAvgSpeed == 1) {
            param += 16;
        }
        if (this.mSetData.fgConsumption == 1) {
            param += 32;
        }
        if (this.mSetData.fgAvgConsumption == 1) {
            param += 64;
        }
        if (this.mSetData.fgRange == 1) {
            return param + 128;
        }
        return param;
    }
}
