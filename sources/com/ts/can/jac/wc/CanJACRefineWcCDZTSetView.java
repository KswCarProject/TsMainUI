package com.ts.can.jac.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanJACRefineWcCDZTSetView extends CanScrollCarInfoView {
    private CanDataInfo.JacWc_ChargInfo mChargData;

    public CanJACRefineWcCDZTSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_dfqc_battery_a, R.string.can_dfqc_battery_v};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mChargData = new CanDataInfo.JacWc_ChargInfo();
    }

    public void ResetData(boolean check) {
        CanJni.JacWcGetChargInfo(this.mChargData);
        if (!i2b(this.mChargData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mChargData.Update)) {
            this.mChargData.Update = 0;
            updateItem(0, this.mChargData.Dl, String.format("%.1fA", new Object[]{Float.valueOf(((float) this.mChargData.Dl) / 10.0f)}));
            updateItem(1, this.mChargData.Dy, String.format("%.1fV", new Object[]{Float.valueOf(((float) this.mChargData.Dy) / 10.0f)}));
        }
    }

    public void QueryData() {
    }
}
