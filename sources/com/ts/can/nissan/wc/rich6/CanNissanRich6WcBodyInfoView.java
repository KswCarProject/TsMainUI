package com.ts.can.nissan.wc.rich6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanNissanRich6WcBodyInfoView extends CanScrollCarInfoView {
    public static final String TAG = "CanNissanRich6WcCarInfoView";
    private CanDataInfo.NissanRich6Wc_CarData mCarData;

    public CanNissanRich6WcBodyInfoView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mCarData = new CanDataInfo.NissanRich6Wc_CarData();
        this.mItemTitleIds = new int[]{R.string.can_battery, R.string.can_dfqc_ac_a, R.string.can_soc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
    }

    public void ResetData(boolean check) {
        CanJni.NissanRiChWcGetCarData(this.mCarData);
        if (!i2b(this.mCarData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarData.Update)) {
            this.mCarData.Update = 0;
            updateItem(0, 0, String.valueOf(String.format("%.2f", new Object[]{Double.valueOf(((double) this.mCarData.BatV) * 0.1d)})) + "V");
            updateItem(1, 0, String.valueOf(String.format("%.2f", new Object[]{Double.valueOf(((double) this.mCarData.Current) * 0.1d)})) + "A");
            updateItem(2, 0, String.valueOf(this.mCarData.Soc) + "%");
        }
    }

    public void QueryData() {
        CanJni.NissanRiChWcQuery(5, 1, 50);
    }
}
