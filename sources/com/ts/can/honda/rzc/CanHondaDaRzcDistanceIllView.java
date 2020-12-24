package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaDaRzcDistanceIllView extends CanScrollCarInfoView {
    public static final int ITEM_AUTOLIGHTSENS = 0;
    public static final int ITEM_HEADLIGHTAUTOOFTIME = 1;
    public static final int ITEM_INTERIORLIGHT = 2;
    protected CanDataInfo.HondaSetData mSetData;

    public CanHondaDaRzcDistanceIllView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HondaDACarSet(6, item);
                return;
            case 1:
                CanJni.HondaDACarSet(5, item);
                return;
            case 2:
                CanJni.HondaDACarSet(4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_autolightsensitivity, R.string.can_headlightautoofftime, R.string.can_interiorlightdimmingtime};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_sensitivity_min, R.string.can_sensitivity_low, R.string.can_sensitivity_mid, R.string.can_sensitivity_high, R.string.can_sensitivity_max};
        this.mPopValueIds[1] = new int[]{R.string.can_headlightautoofftime_0s, R.string.can_headlightautoofftime_15s, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s};
        this.mPopValueIds[2] = new int[]{R.string.can_headlightautoofftime_15s, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s};
        this.mSetData = new CanDataInfo.HondaSetData();
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.DisIllUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.DisIllUpdate)) {
            this.mSetData.DisIllUpdate = 0;
            updateItem(0, this.mSetData.AutoLightSens);
            updateItem(1, this.mSetData.lightofftime);
            updateItem(2, this.mSetData.lightdimmingtime);
        }
    }

    public void QueryData() {
    }
}
