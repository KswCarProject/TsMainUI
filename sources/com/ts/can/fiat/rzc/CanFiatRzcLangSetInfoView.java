package com.ts.can.fiat.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFiatRzcLangSetInfoView extends CanScrollCarInfoView {
    private CanDataInfo.FlatRzcSetData mSetData;

    public CanFiatRzcLangSetInfoView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.FiatRzcCarSet(0, item);
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
        this.mItemTitleIds = new int[]{R.string.can_psa_wc_lang_setup};
        this.mPopValueIds[0] = new int[]{R.string.lang_itanliano, R.string.lang_deutsch, R.string.lang_en_us, R.string.lang_espanol, R.string.lang_francais, R.string.lang_portugues, R.string.lang_polski, R.string.lang_nederlands, R.string.can_car_null, R.string.lang_turkce};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mSetData = new CanDataInfo.FlatRzcSetData();
    }

    public void ResetData(boolean check) {
        CanJni.FiatRzcGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Lang);
        }
    }

    public void QueryData() {
        CanJni.FiatRzcQuery(128, 0);
    }
}
