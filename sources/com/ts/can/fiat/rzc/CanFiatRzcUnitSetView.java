package com.ts.can.fiat.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFiatRzcUnitSetView extends CanScrollCarInfoView {
    CanDataInfo.FlatRzcSetData mSetData;

    public CanFiatRzcUnitSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.FiatRzcCarSet(2, item);
                return;
            case 1:
                CanJni.FiatRzcCarSet(3, item);
                return;
            case 2:
                CanJni.FiatRzcCarSet(1, item);
                return;
            case 3:
                CanJni.FiatRzcCarSet(4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_lcdw, R.string.can_temp_dw, R.string.can_fuel_d_w, R.string.can_dlunits};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_service_distance_km, R.string.can_service_distance_mi};
        this.mPopValueIds[1] = new int[]{R.string.can_temperature_c, R.string.can_temperature_f};
        this.mPopValueIds[2] = new int[]{R.string.can_fuels_kml, R.string.can_fuels_lkm, R.string.can_fuels_mpg_uk, R.string.can_fuels_mpg_us};
        this.mPopValueIds[3] = new int[]{R.string.can_dw_yz, R.string.can_dw_zdy};
        this.mSetData = new CanDataInfo.FlatRzcSetData();
    }

    public void ResetData(boolean check) {
        CanJni.FiatRzcGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Jl);
            updateItem(1, this.mSetData.Wd);
            updateItem(2, this.mSetData.Yh);
            updateItem(3, this.mSetData.Metric);
        }
    }

    public void QueryData() {
        CanJni.FiatRzcQuery(128, 1);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 2);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 3);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 4);
        Sleep(5);
    }
}
