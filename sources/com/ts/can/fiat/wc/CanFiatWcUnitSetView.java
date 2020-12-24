package com.ts.can.fiat.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFiatWcUnitSetView extends CanScrollCarInfoView {
    CanDataInfo.FiatAllWcUnit mAdtData;
    CanDataInfo.FiatAllWcUnit mSetData;

    public CanFiatWcUnitSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.FiatAllWcUnitSet(1, item + 1);
                return;
            case 1:
                CanJni.FiatAllWcUnitSet(3, item + 1);
                return;
            case 2:
                CanJni.FiatAllWcUnitSet(5, item + 1);
                return;
            case 3:
                CanJni.FiatAllWcUnitSet(7, item);
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
        this.mPopValueIds[2] = new int[]{R.string.can_fuels_lkm, R.string.can_fuels_kml, R.string.can_fuels_mpg_us, R.string.can_fuels_mpg_uk};
        this.mPopValueIds[3] = new int[]{R.string.can_dw_yz, R.string.can_dw_gz, R.string.can_dw_zdy};
        this.mSetData = new CanDataInfo.FiatAllWcUnit();
        this.mAdtData = new CanDataInfo.FiatAllWcUnit();
    }

    public void ResetData(boolean check) {
        CanJni.FiatAllWcGetUnit(this.mAdtData, 0);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(new int[]{this.mAdtData.Dis, this.mAdtData.Temp, this.mAdtData.Fule, this.mAdtData.Dlsz});
        }
        CanJni.FiatAllWcGetUnit(this.mSetData, 1);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            if (this.mSetData.Dis == 0) {
                updateItem(0, 1);
            } else if (this.mSetData.Dis == 1) {
                updateItem(0, 0);
            }
            if (this.mSetData.Temp == 0) {
                updateItem(1, 1);
            } else if (this.mSetData.Temp == 1) {
                updateItem(1, 0);
            }
            updateItem(2, this.mSetData.Fule);
            updateItem(3, this.mSetData.Dlsz);
        }
    }

    public void QueryData() {
    }
}
