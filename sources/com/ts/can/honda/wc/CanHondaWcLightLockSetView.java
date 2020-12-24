package com.ts.can.honda.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaWcLightLockSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaWcLightSet mLightData;

    public CanHondaWcLightLockSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HondaWcCarLightSet(1, item);
                return;
            case 1:
                CanJni.HondaWcCarLightSet(2, item);
                return;
            case 2:
                CanJni.HondaWcCarLightSet(3, item);
                return;
            case 3:
                CanJni.HondaWcCarLightSet(4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                CanJni.HondaWcCarLightSet(5, Neg(this.mLightData.Yshzdddldgxhsz));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_interiorlightdimmingtime, R.string.can_headlightautoofftime, R.string.can_zdddlmd, R.string.can_autointeriorsensitivity, R.string.can_autoheadlinghtonwitchwiper};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_headlightautoofftime_0s, R.string.can_headlightautoofftime_15s, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s};
        this.mPopValueIds[1] = new int[]{R.string.can_headlightautoofftime_0s, R.string.can_headlightautoofftime_15s, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s};
        this.mPopValueIds[2] = new int[]{R.string.can_cdzd, R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg, R.string.can_cdzg};
        this.mPopValueIds[3] = new int[]{R.string.can_cdzd, R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg, R.string.can_cdzg};
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
            updateItem(2, this.mLightData.Zdddlmd);
            updateItem(3, this.mLightData.Zdcnzmlmd);
            updateItem(4, this.mLightData.Yshzdddldgxhsz);
        }
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 103);
    }
}
