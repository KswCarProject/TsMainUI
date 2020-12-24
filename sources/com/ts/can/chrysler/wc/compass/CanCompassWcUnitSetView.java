package com.ts.can.chrysler.wc.compass;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanCompassWcUnitSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChrWcUnit mUnitData;

    public CanCompassWcUnitSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.ChryslerWcUnitSet(1, item + 1);
                return;
            case 1:
                CanJni.ChryslerWcUnitSet(6, item);
                return;
            case 2:
                CanJni.ChryslerWcUnitSet(3, item + 1);
                return;
            case 3:
                CanJni.ChryslerWcUnitSet(5, item + 1);
                return;
            case 4:
                CanJni.ChryslerWcUnitSet(7, item + 1);
                return;
            case 5:
                CanJni.ChryslerWcLangSet(1, item + 1);
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
        this.mItemTitleIds = new int[]{R.string.can_lcdw, R.string.can_TPMS_DW, R.string.can_temp_dw, R.string.can_fuel_d_w, R.string.can_units, R.string.can_lang_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_distance_km, R.string.can_distance_mile};
        this.mPopValueIds[1] = new int[]{R.string.can_pressure_psi, R.string.can_pressure_kpa, R.string.can_pressure_bar};
        this.mPopValueIds[2] = new int[]{R.string.can_temperature_c, R.string.can_temperature_f};
        this.mPopValueIds[3] = new int[]{R.string.can_fuels_lkm, R.string.can_fuels_kml, R.string.can_fuels_mpg_uk, R.string.can_fuels_mpg_us};
        this.mPopValueIds[4] = new int[]{R.string.can_dw_gz, R.string.can_dw_mz, R.string.can_individual};
        this.mPopValueIds[5] = new int[]{R.string.can_lang_en, R.string.can_lang_cn};
        this.mUnitData = new CanDataInfo.ChrWcUnit();
    }

    public void ResetData(boolean check) {
        CanJni.ChryslerWcGetUnitInfo(this.mUnitData);
        if (!i2b(this.mUnitData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mUnitData.Update)) {
            this.mUnitData.Update = 0;
            updateItem(new int[]{this.mUnitData.DisDw - 1, this.mUnitData.PrsDw, this.mUnitData.TempDw - 1, this.mUnitData.FuleDw - 1, this.mUnitData.Dw - 1});
        }
    }

    public void QueryData() {
        CanJni.ChryslerWcQuery(1, 193);
    }
}
