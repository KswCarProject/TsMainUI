package com.ts.can.faw.t3.b30;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFawB30T3BatteryStatusView extends CanScrollCarInfoView {
    private CanDataInfo.FawB30T3_BatInfo mBatInfo;

    public CanFawB30T3BatteryStatusView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_dfqc_battery_v, R.string.can_dfqc_battery_a, R.string.can_gzxx, R.string.can_pjwd, R.string.can_soc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mBatInfo = new CanDataInfo.FawB30T3_BatInfo();
    }

    public void ResetData(boolean check) {
        CanJni.FawB30T3GetBatInfo(this.mBatInfo);
        if (!i2b(this.mBatInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mBatInfo.Update)) {
            this.mBatInfo.Update = 0;
            if (this.mBatInfo.Dcdy < 0 || this.mBatInfo.Dcdy > 500) {
                updateItem(0, 0, "-.-");
            } else {
                updateItem(0, this.mBatInfo.Dcdy, String.valueOf(this.mBatInfo.Dcdy) + " V");
            }
            if (this.mBatInfo.Dcdl < 0 || this.mBatInfo.Dcdl > 1000) {
                updateItem(1, 0, "-.-");
            } else {
                updateItem(1, this.mBatInfo.Dcdl, String.valueOf(this.mBatInfo.Dcdl - 500) + " A");
            }
            if (this.mBatInfo.Gzxx < 0 || this.mBatInfo.Gzxx > 1) {
                updateItem(2, 0, "-.-");
            } else {
                updateItem(2, this.mBatInfo.Gzxx, getString(this.mBatInfo.Gzxx == 0 ? R.string.can_normal : R.string.can_dfqc_error));
            }
            if (this.mBatInfo.Pjwd < 0 || this.mBatInfo.Pjwd > 250) {
                updateItem(3, 0, "-.-");
            } else {
                updateItem(3, this.mBatInfo.Pjwd, String.valueOf(this.mBatInfo.Pjwd - 40) + " ℃");
            }
            if (this.mBatInfo.Soc < 0 || this.mBatInfo.Soc > 100) {
                updateItem(4, 0, "-.-");
            } else {
                updateItem(4, this.mBatInfo.Soc, String.valueOf(this.mBatInfo.Soc) + " %");
            }
        }
    }

    public void QueryData() {
        CanJni.FawB30T3Query(4);
    }
}
