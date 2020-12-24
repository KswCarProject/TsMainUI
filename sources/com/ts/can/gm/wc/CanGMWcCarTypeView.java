package com.ts.can.gm.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMWcCarTypeView extends CanScrollCarInfoView {
    private CanDataInfo.GmWcPanKey mPanKey;

    public CanGMWcCarTypeView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.GmWcCarPanTypeSet(item, 0);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.array.can_gm_wc_pantype};
        this.mPanKey = new CanDataInfo.GmWcPanKey();
    }

    public void ResetData(boolean check) {
        CanJni.GmWcGetCarPanType(this.mPanKey);
        if (!i2b(this.mPanKey.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPanKey.Update)) {
            this.mPanKey.Update = 0;
            updateItem(0, this.mPanKey.Type);
        }
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 35);
    }
}
