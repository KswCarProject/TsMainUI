package com.ts.can.psa.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanPSAWCUnitSetInfoView extends CanScrollCarInfoView {
    private CanDataInfo.PsaWcUnit mPsaWcUnit = new CanDataInfo.PsaWcUnit();

    public CanPSAWCUnitSetInfoView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 0) {
                    CanJni.PsaWcUnitSet(3, 2);
                    return;
                } else {
                    CanJni.PsaWcUnitSet(3, 1);
                    return;
                }
            case 1:
                CanJni.PsaWcUnitSet(5, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_psa_wc_temperature, R.string.can_psa_wc_consumption};
        this.mPopValueIds[0] = new int[]{R.array.can_psa_wc_temperature_array};
        this.mPopValueIds[1] = new int[]{R.array.can_psa_wc_consumption_array};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
    }

    public void ResetData(boolean check) {
        CanJni.PsaWcGetUnit(this.mPsaWcUnit);
        if (!i2b(this.mPsaWcUnit.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPsaWcUnit.Update)) {
            this.mPsaWcUnit.Update = 0;
            updateItem(0, this.mPsaWcUnit.Temperature);
            updateItem(1, this.mPsaWcUnit.Consumption);
            ShowItems();
        }
    }

    /* access modifiers changed from: protected */
    public void ShowItems() {
        for (int i = 0; i < 2; i++) {
            showItem(i, IsHaveItem(i));
        }
    }

    /* access modifiers changed from: protected */
    public int IsHaveItem(int item) {
        switch (item) {
            case 0:
                if (this.mPsaWcUnit.fgTemperature == 1) {
                    return 1;
                }
                return 0;
            case 1:
                if (this.mPsaWcUnit.fgConsumption == 1) {
                    return 1;
                }
                return 0;
            default:
                return 0;
        }
    }

    public void QueryData() {
    }
}
