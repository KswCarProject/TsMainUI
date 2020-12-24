package com.ts.can.bmw.mini;

import com.ts.MainUI.R;
import com.ts.canview.CanItemPopupList;

public class CanBMWMiniSetUnitsActivity extends CanBMWMiniBaseActivity {
    public static final int ITEM_CONSUMPTION = 1;
    public static final int ITEM_LANGUAGE = 0;
    public static final int ITEM_TEMPERTURE = 2;
    private int[] mConsumptionArrays = {R.string.can_fuels_lkm, R.string.can_fuels_kml};
    private CanItemPopupList mItemConsumption;
    private CanItemPopupList mItemLanguage;
    private CanItemPopupList mItemTemperture;
    private int[] mLanguageArrays = {R.string.can_lang_en, R.string.can_lang_cn, R.string.can_lang_tw};
    private int[] mTempArrays = {R.string.can_temperature_c, R.string.can_temperature_f};

    /* access modifiers changed from: protected */
    public void AddItemView() {
        this.mItemLanguage = AddItemPopup(R.string.can_language, this.mLanguageArrays, 0);
        this.mItemConsumption = AddItemPopup(R.string.can_consumption, this.mConsumptionArrays, 1);
        this.mItemTemperture = AddItemPopup(R.string.can_temperature, this.mTempArrays, 2);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetMiniSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemLanguage.SetSel(this.mSetData.Lang - 1);
            this.mItemConsumption.SetSel(this.mSetData.ConsumpDW);
            this.mItemTemperture.SetSel(this.mSetData.TempDW);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(24);
        Sleep(1);
        Query(25);
        Sleep(1);
        Query(26);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CarSet(24, (item + 1) * 16);
                return;
            case 1:
                CarSet(25, item * 52);
                return;
            case 2:
                CarSet(26, item * 50);
                return;
            default:
                return;
        }
    }
}
