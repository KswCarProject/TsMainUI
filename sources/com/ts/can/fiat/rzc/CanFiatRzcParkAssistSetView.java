package com.ts.can.fiat.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFiatRzcParkAssistSetView extends CanScrollCarInfoView {
    private static final int ITEM_MAX = 2;
    private static final int ITEM_PARK_SENSE = 0;
    private static final int ITEM_REAR_PARKSENSE_VOL = 1;
    CanDataInfo.FlatRzcSetData mSetData;

    public CanFiatRzcParkAssistSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.FiatRzcCarSet(48, item);
                return;
            case 1:
                CanJni.FiatRzcCarSet(49, item);
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
        this.mItemTitleIds = new int[]{R.string.can_psbcld, R.string.can_hpsyl};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_type_vol, R.string.can_vol_img};
        this.mPopValueIds[1] = new int[]{R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
        this.mSetData = new CanDataInfo.FlatRzcSetData();
    }

    public void ResetData(boolean check) {
        CanJni.FiatRzcGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.ParkSense);
            updateItem(1, this.mSetData.RearParkSenseVol);
        }
    }

    public void QueryData() {
        CanJni.FiatRzcQuery(128, 48);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 49);
        Sleep(5);
    }
}
