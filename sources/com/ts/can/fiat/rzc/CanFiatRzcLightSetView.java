package com.ts.can.fiat.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFiatRzcLightSetView extends CanScrollCarInfoView {
    private static final int ITEM_CORNERING_LIGHTS = 1;
    private static final int ITEM_COURTESY_LIGHTS = 2;
    private static final int ITEM_DAYTIME_LIGHTS = 0;
    private static final int ITEM_FLASH_LIGHTS_WLOCK = 3;
    private static final int ITEM_MAX = 4;
    CanDataInfo.FlatRzcSetData mSetData;

    public CanFiatRzcLightSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.FiatRzcCarSet(18, item);
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
                CanJni.FiatRzcCarSet(16, Neg(this.mSetData.DaytimeLights));
                return;
            case 1:
                CanJni.FiatRzcCarSet(17, Neg(this.mSetData.CorneringLight));
                return;
            case 3:
                CanJni.FiatRzcCarSet(19, Neg(this.mSetData.FlashLightswLock));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_rjzmd, R.string.can_corneringLights, R.string.can_courtesy_lights, R.string.can_scszxdss};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[2] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
        this.mSetData = new CanDataInfo.FlatRzcSetData();
    }

    public void ResetData(boolean check) {
        CanJni.FiatRzcGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.DaytimeLights);
            updateItem(1, this.mSetData.CorneringLight);
            updateItem(2, this.mSetData.CourtesyLights);
            updateItem(3, this.mSetData.FlashLightswLock);
        }
    }

    public void QueryData() {
        CanJni.FiatRzcQuery(128, 16);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 17);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 18);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 19);
        Sleep(5);
    }
}
