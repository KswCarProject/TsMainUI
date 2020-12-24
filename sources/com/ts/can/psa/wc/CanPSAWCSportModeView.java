package com.ts.can.psa.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanPSAWCSportModeView extends CanScrollCarInfoView {
    private CanDataInfo.PsaWcSportMode mPsaWcSportMode = new CanDataInfo.PsaWcSportMode();

    public CanPSAWCSportModeView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.PsaWcSportModeSet(1, 255);
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
        this.mItemTitleIds = new int[]{R.string.can_psa_wc_fdjqtgntysd};
        this.mPopValueIds[0] = new int[]{R.string.can_psa_wc_fdjqtgntysd_key1, R.string.can_psa_wc_fdjqtgntysd_key2};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mItemVisibles[0] = 0;
    }

    public void ResetData(boolean check) {
        CanJni.PsaWcGetSportMode(this.mPsaWcSportMode);
        if (!i2b(this.mPsaWcSportMode.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPsaWcSportMode.Update)) {
            this.mPsaWcSportMode.Update = 0;
            updateItem(0, this.mPsaWcSportMode.StartStop);
            this.mItemVisibles[0] = this.mPsaWcSportMode.fgStartStop;
            showItem();
        }
    }

    public void QueryData() {
    }
}
