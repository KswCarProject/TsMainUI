package com.ts.can.bmw.mini;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemIcoVal;
import com.ts.canview.CanItemMsgBox;

public class CanBMWMiniCarCmpActivity extends CanBMWMiniBaseActivity {
    private static final int ITEM_CONSUMPTION = 1;
    private static final int ITEM_DISTANCE = 0;
    private static final int ITEM_SPEED = 2;
    private CanItemIcoVal mItemConsumption;
    private CanItemIcoVal mItemDistance;
    private CanItemIcoVal mItemSpeed;

    /* access modifiers changed from: protected */
    public void AddItemView() {
        this.mItemDistance = AddItemIcoVal(R.drawable.can_icon_kdlc, R.string.can_kd_range, 0);
        this.mItemConsumption = AddItemIcoVal(R.drawable.can_icon_consumption, R.string.can_consumption1, 1);
        this.mItemSpeed = AddItemIcoVal(R.drawable.can_icon_sudu, R.string.can_avg_speed, 2);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetMiniCarPC();
        GetMiniSetData();
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            this.mItemDistance.ShowGone(this.mSetData.fgRange);
            this.mItemConsumption.ShowGone(this.mSetData.fgConsumption);
            this.mItemSpeed.ShowGone(this.mSetData.fgAvgSpeed);
        }
        if (!i2b(this.mCarPC.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarPC.Update)) {
            this.mCarPC.Update = 0;
            setDistance(this.mCarPC.Range);
            setConsumption(this.mCarPC.Consumption);
            setSpeed(this.mCarPC.AvgSpeed);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(72);
        Sleep(1);
        Query(1);
    }

    private void setDistance(int distance) {
        this.mItemDistance.SetVal(String.format("%d KM", new Object[]{Integer.valueOf(distance)}));
    }

    private void setConsumption(int consumption) {
        String str;
        if (consumption == 65535) {
            str = "--.-";
        } else {
            str = String.format("%.1f L/100KM", new Object[]{Float.valueOf(((float) consumption) * 0.1f)});
        }
        this.mItemConsumption.SetVal(str);
    }

    private void setSpeed(int speed) {
        String str;
        if (speed == 65535) {
            str = "--.-";
        } else {
            str = String.format("%.1f KM/H", new Object[]{Float.valueOf(((float) speed) * 0.1f)});
        }
        this.mItemSpeed.SetVal(str);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                showResetNotice(1);
                return;
            case 2:
                showResetNotice(2);
                return;
            default:
                return;
        }
    }

    private void showResetNotice(int param) {
        new CanItemMsgBox(param, this, R.string.can_cmp_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
            public void onOK(int param) {
                CanBMWMiniCarCmpActivity.this.CarSet(129, param);
            }
        });
    }
}
