package com.ts.can.jac.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanJACRefineWcDrivAssistSetView extends CanScrollCarInfoView {
    CanDataInfo.JacWc_BaseInfo mBaseData;

    public CanJACRefineWcDrivAssistSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_psa_wc_jsms, R.string.can_jac_dw};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mPopValueIds[0] = new int[]{R.string.can_tripbresettiming_wc_1, R.string.can_jac_jsms_1, R.string.can_jac_jsms_2, R.string.can_jac_jsms_3};
        this.mPopValueIds[1] = new int[]{R.string.can_tripbresettiming_wc_1, R.string.can_jac_dw_1, R.string.can_jac_dw_2, R.string.can_jac_dw_3, R.string.can_jac_dw_4};
        this.mBaseData = new CanDataInfo.JacWc_BaseInfo();
    }

    public void ResetData(boolean check) {
        CanJni.JacWcGetBaseInfo(this.mBaseData);
        if (!i2b(this.mBaseData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mBaseData.Update)) {
            this.mBaseData.Update = 0;
            updateItem(0, this.mBaseData.Jsms, getActivity().getResources().getString(this.mPopValueIds[0][this.mBaseData.Jsms]));
            updateItem(1, this.mBaseData.Dw, getActivity().getResources().getString(this.mPopValueIds[1][this.mBaseData.Dw]));
        }
    }

    public void QueryData() {
    }
}
