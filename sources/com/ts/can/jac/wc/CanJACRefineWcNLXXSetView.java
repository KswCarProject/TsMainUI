package com.ts.can.jac.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanJACRefineWcNLXXSetView extends CanScrollCarInfoView {
    private CanDataInfo.JacWc_BatInfo mBatData;

    public CanJACRefineWcNLXXSetView(Activity activity) {
        super(activity, 9);
    }

    public void onItem(int id, int item) {
        switch (id) {
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
        this.mItemTitleIds = new int[]{R.string.can_total_mile, R.string.can_sylc, R.string.can_dfqc_battery_e, R.string.can_ec_nll, R.string.can_jac_pjnh, R.string.can_jac_ssnh, R.string.can_jac_nlfp_kt, R.string.can_jac_nlfp_hs, R.string.can_jac_nlfp_qd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mPopValueIds[6] = new int[]{R.string.can_jac_nll_bxs, R.string.can_jac_nll_qd, R.string.can_jac_nll_nlhs};
        this.mBatData = new CanDataInfo.JacWc_BatInfo();
    }

    public void ResetData(boolean check) {
        CanJni.JacWcGetBatInfo(this.mBatData);
        if (!i2b(this.mBatData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mBatData.Update)) {
            this.mBatData.Update = 0;
            updateItem(0, this.mBatData.Zlc, String.format("%.1f km", new Object[]{Float.valueOf(((float) this.mBatData.Zlc) / 10.0f)}));
            updateItem(1, this.mBatData.Sylc, String.format("%.1f km", new Object[]{Float.valueOf(((float) this.mBatData.Sylc) / 1.0f)}));
            updateItem(2, this.mBatData.Sydl, String.valueOf(String.format("%d ", new Object[]{Integer.valueOf(this.mBatData.Sydl)})) + "%");
            updateItem(3, this.mBatData.Nll, getActivity().getResources().getString(this.mPopValueIds[6][this.mBatData.Nll]));
            updateItem(4, this.mBatData.Pjnh, String.format("%.1f km/kWh", new Object[]{Float.valueOf(((float) this.mBatData.Pjnh) / 10.0f)}));
            updateItem(5, this.mBatData.Ssnh, String.format("%.1f km/kWh", new Object[]{Float.valueOf(((float) this.mBatData.Ssnh) / 10.0f)}));
            updateItem(6, this.mBatData.Nlfp_Kt, String.format("%.1f kW", new Object[]{Float.valueOf(((float) this.mBatData.Nlfp_Kt) / 10.0f)}));
            updateItem(7, this.mBatData.Nlfp_Hs, String.format("%.1f kW", new Object[]{Float.valueOf(((float) this.mBatData.Nlfp_Hs) / 10.0f)}));
            updateItem(8, this.mBatData.Nlfp_djqd, String.format("%.1f kW", new Object[]{Float.valueOf(((float) this.mBatData.Nlfp_djqd) * 0.75f)}));
        }
    }

    public void QueryData() {
    }
}
