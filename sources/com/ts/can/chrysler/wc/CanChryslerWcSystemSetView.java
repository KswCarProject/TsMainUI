package com.ts.can.chrysler.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanChryslerWcSystemSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChrWcUnit mUnitData;

    public CanChryslerWcSystemSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.ChryslerWcLangSet(1, item + 1);
                return;
            case 1:
                CanJni.ChryslerWcUnitSet(7, item + 1);
                return;
            case 2:
                CanJni.ChryslerWcUnitSet(6, item);
                return;
            case 3:
                CanJni.ChryslerWcUnitSet(3, item + 1);
                return;
            case 4:
                CanJni.ChryslerWcUnitSet(5, item + 1);
                return;
            case 5:
                CanJni.ChryslerWcUnitSet(8, item);
                return;
            case 6:
                CanJni.ChryslerWcUnitSet(1, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_lang_set, R.string.can_units, R.string.can_TPMS_DW, R.string.can_temp_dw, R.string.can_YH_DW, R.string.can_YL_DW, R.string.can_lcdw};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.lang_en_uk, R.string.can_lang_cn, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_francais, R.string.can_reserved, R.string.lang_espanol, R.string.lang_nederlands, R.string.lang_portugues, R.string.can_reserved, R.string.can_reserved, R.string.can_reserved, R.string.can_reserved, R.string.can_reserved, R.string.can_reserved, R.string.lang_turkce, R.string.can_reserved, R.string.can_reserved, R.string.can_reserved, R.string.can_reserved, R.string.lang_polski};
        this.mPopValueIds[1] = new int[]{R.string.can_dw_gz, R.string.can_dw_mz, R.string.can_dw_zdy};
        this.mPopValueIds[2] = new int[]{R.string.can_pressure_psi, R.string.can_pressure_kpa, R.string.can_pressure_bar};
        this.mPopValueIds[3] = new int[]{R.string.can_temperature_c, R.string.can_temperature_f};
        this.mPopValueIds[4] = new int[]{R.string.can_fuels_lkm, R.string.can_fuels_kml, R.string.can_fuels_mpg_uk, R.string.can_fuels_mpg_us};
        this.mPopValueIds[5] = new int[]{R.string.can_pressure_psi, R.string.can_pressure_kpa, R.string.can_pressure_bar};
        this.mPopValueIds[6] = new int[]{R.string.can_distance_km, R.string.can_distance_mile};
        this.mUnitData = new CanDataInfo.ChrWcUnit();
    }

    public void ResetData(boolean check) {
        CanJni.ChryslerWcGetUnitInfo(this.mUnitData);
        if (!i2b(this.mUnitData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mUnitData.Update)) {
            this.mUnitData.Update = 0;
            updateItem(1, this.mUnitData.Dw - 1);
            updateItem(2, this.mUnitData.PrsDw);
            updateItem(3, this.mUnitData.TempDw - 1);
            updateItem(4, this.mUnitData.FuleDw - 1);
        }
    }

    public void QueryData() {
        CanJni.ChryslerWcQuery(1, 193);
    }
}
