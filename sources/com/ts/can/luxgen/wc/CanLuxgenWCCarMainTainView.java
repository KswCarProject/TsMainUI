package com.ts.can.luxgen.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanLuxgenWCCarMainTainView extends CanScrollCarInfoView {
    private CanDataInfo.LuxgenWc_MainTain mMainTainData;

    public CanLuxgenWCCarMainTainView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_scdblc, R.string.can_xcdblc, R.string.can_jlxcdblc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mMainTainData = new CanDataInfo.LuxgenWc_MainTain();
    }

    public void ResetData(boolean check) {
        CanJni.LuxgenWcGetMainTainData(this.mMainTainData);
        if (!i2b(this.mMainTainData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mMainTainData.Update)) {
            this.mMainTainData.Update = 0;
            updateItem(0, this.mMainTainData.Scbyzlc, String.format("%d Km", new Object[]{Integer.valueOf(this.mMainTainData.Scbyzlc)}));
            updateItem(1, this.mMainTainData.Xcdbtz, String.format("%d Km", new Object[]{Integer.valueOf(this.mMainTainData.Xcdbtz)}));
            updateItem(2, this.mMainTainData.Jlxcdblc, String.format("%d Km", new Object[]{Integer.valueOf(this.mMainTainData.Jlxcdblc)}));
        }
    }

    public void QueryData() {
    }
}
