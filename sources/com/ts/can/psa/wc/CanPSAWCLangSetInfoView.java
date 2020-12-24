package com.ts.can.psa.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanPSAWCLangSetInfoView extends CanScrollCarInfoView {
    private CanDataInfo.PsaWcLang mPsaWcLang = new CanDataInfo.PsaWcLang();

    public CanPSAWCLangSetInfoView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.PsaWcLangSet(item + 1);
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
        this.mPopValueIds[0] = new int[]{R.array.can_psa_wc_lang};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
    }

    public void ResetData(boolean check) {
        CanJni.PsaWcGetLang(this.mPsaWcLang);
        if (!i2b(this.mPsaWcLang.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPsaWcLang.Update)) {
            this.mPsaWcLang.Update = 0;
            updateItem(0, this.mPsaWcLang.Lang - 1);
        }
    }

    public void QueryData() {
    }
}
