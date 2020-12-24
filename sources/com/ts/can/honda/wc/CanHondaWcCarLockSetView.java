package com.ts.can.honda.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaWcCarLockSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaWcDoorSet mDoorData;

    public CanHondaWcCarLockSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.HondaWcCarDoorSet(2, item);
        } else if (id == 3) {
            CanJni.HondaWcCarDoorSet(4, item);
        } else if (id == 4) {
            CanJni.HondaWcCarDoorSet(5, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.HondaWcCarDoorSet(1, Neg(this.mDoorData.Yklsts));
                return;
            case 2:
                CanJni.HondaWcCarDoorSet(3, Neg(this.mDoorData.Lkszgxhsz));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_yklsts, R.string.can_zdcssj, R.string.can_walkawayautolock, R.string.can_cmzdls, R.string.can_cmzdks};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_Scsfctsy_3, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s, R.string.can_headlightautoofftime_90s};
        this.mPopValueIds[3] = new int[]{R.string.can_Scsfctsy_3, R.string.can_cmzdls_1, R.string.can_cmzdls_2};
        this.mPopValueIds[4] = new int[]{R.string.can_Scsfctsy_3, R.string.can_cmzdks_1, R.string.can_cmzdks_2};
        this.mDoorData = new CanDataInfo.HondaWcDoorSet();
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcGetDoorSet(this.mDoorData);
        if (!i2b(this.mDoorData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDoorData.Update)) {
            this.mDoorData.Update = 0;
            updateItem(0, this.mDoorData.Yklsts);
            updateItem(1, this.mDoorData.Zdcssj);
            updateItem(2, this.mDoorData.Lkszgxhsz);
            updateItem(3, this.mDoorData.Cmzdls);
            updateItem(4, this.mDoorData.Cmzdks);
        }
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 101);
    }
}
