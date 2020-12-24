package com.ts.can.honda.wc.accord9;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanAccord9WcLightLockSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaWcLightSet mLightData;

    public CanAccord9WcLightLockSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HondaWcCarLightSet(1, item);
                return;
            case 1:
                CanJni.HondaWcCarLightSet(2, item);
                return;
            default:
                return;
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
        this.mItemTitleIds = new int[]{R.string.can_interiorlightdimmingtime, R.string.can_headlightautoofftime};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_headlightautoofftime_0s, R.string.can_headlightautoofftime_15s, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s};
        this.mPopValueIds[1] = new int[]{R.string.can_headlightautoofftime_0s, R.string.can_headlightautoofftime_15s, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s};
        this.mLightData = new CanDataInfo.HondaWcLightSet();
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcGetLightSet(this.mLightData);
        if (!i2b(this.mLightData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLightData.Update)) {
            this.mLightData.Update = 0;
            updateItem(0, this.mLightData.Cndgjgsj);
            updateItem(1, this.mLightData.Qddzdxmsj);
        }
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 103);
    }
}
