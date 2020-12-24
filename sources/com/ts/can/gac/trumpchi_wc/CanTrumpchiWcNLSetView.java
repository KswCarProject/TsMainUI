package com.ts.can.gac.trumpchi_wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanTrumpchiWcNLSetView extends CanScrollCarInfoView {
    private CanDataInfo.GCWcEnergy mEnergySet;

    public CanTrumpchiWcNLSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.TrumpchiWcEnergySet(1, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ec_nll, R.string.can_jac_nll_nlhs};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_jac_nll_bxs, R.string.can_jac_nll_qd};
        this.mPopValueIds[0] = new int[]{R.string.can_jac_nll_bxs, R.string.can_jac_nll_qd};
        this.mPopValueIds[1] = new int[]{R.string.can_trunk_close, R.string.can_cdjd, R.string.can_cdjg};
        this.mEnergySet = new CanDataInfo.GCWcEnergy();
    }

    public void ResetData(boolean check) {
        CanJni.TrumpchiWcGetEnergyData(this.mEnergySet);
        if (!i2b(this.mEnergySet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mEnergySet.Update)) {
            this.mEnergySet.Update = 0;
            updateItem(0, this.mEnergySet.Nll);
        }
    }

    public void QueryData() {
    }
}
