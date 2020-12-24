package com.ts.can.luxgen.od;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanLuxgenOdCarSetView extends CanScrollCarInfoView {
    public static final int ITEM_CCAQYXFZ = 1;
    public static final int ITEM_DCFZYX = 0;
    public static final int ITEM_MAX = 2;
    private CanDataInfo.LuxgenOd_SetData mCarData;

    public CanLuxgenOdCarSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.LuxgenOdCarSet(146, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.LuxgenOdCarSet(147, Neg(this.mCarData.Ccaqyxfzxt));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_rvs_camera, R.string.can_ccaqyxfz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_hjyxfzxt, R.string.can_dccsyxfzxt};
        this.mCarData = new CanDataInfo.LuxgenOd_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.LuxgenOdGetCarSetData(this.mCarData);
        if (!i2b(this.mCarData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarData.Update)) {
            this.mCarData.Update = 0;
            updateItem(new int[]{this.mCarData.Dcfzyx, this.mCarData.Ccaqyxfzxt});
        }
    }

    public void QueryData() {
    }
}
