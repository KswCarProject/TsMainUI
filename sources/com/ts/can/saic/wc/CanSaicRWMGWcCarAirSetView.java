package com.ts.can.saic.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSaicRWMGWcCarAirSetView extends CanScrollCarInfoView {
    private CanDataInfo.SailRwMg_SetData mSetData;

    public CanSaicRWMGWcCarAirSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.SaicRwMgCarSet(11, item, 255, 255);
                return;
            case 2:
                CanJni.SaicRwMgCarSet(12, item, 255, 255);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.SaicRwMgCarSet(13, Neg(this.mSetData.Hccscwld), 255, 255);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_hccscwld, R.string.can_ac_zdfl, R.string.can_fqwdsz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
        this.mPopValueIds[2] = new int[]{R.string.can_fqwdsz_1, R.string.can_fqwdsz_2, R.string.can_fqwdsz_3};
        this.mSetData = new CanDataInfo.SailRwMg_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.SaicRwMgGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Hccscwld);
            updateItem(1, this.mSetData.Zdmsfl);
            updateItem(2, this.mSetData.Fqwdsz);
        }
    }

    public void QueryData() {
        CanJni.SaicRwMgQuery(5, 1, 102);
    }
}
