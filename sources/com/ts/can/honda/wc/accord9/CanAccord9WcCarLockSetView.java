package com.ts.can.honda.wc.accord9;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanAccord9WcCarLockSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaWcDoorSet mDoorData;

    public CanAccord9WcCarLockSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.HondaWcCarDoorSet(3, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.HondaWcCarDoorSet(4, Neg(this.mDoorData.Yklsts));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_yklsts, R.string.can_zdcssj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_Scsfctsy_3, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s, R.string.can_headlightautoofftime_90s};
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
        }
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 101);
    }
}
