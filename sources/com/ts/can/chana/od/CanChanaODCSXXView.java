package com.ts.can.chana.od;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.txznet.sdk.TXZResourceManager;

public class CanChanaODCSXXView extends CanScrollCarInfoView {
    private CanDataInfo.ChanAOd_MaintenData mSetData;

    public CanChanaODCSXXView(Activity activity) {
        super(activity, 1);
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
        this.mItemTitleIds = new int[]{R.string.can_sybylc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE};
        this.mSetData = new CanDataInfo.ChanAOd_MaintenData();
    }

    public void ResetData(boolean check) {
        CanJni.ChanAOdGetMaintenData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            if (this.mSetData.Range > 8190) {
                updateItem(0, this.mSetData.Range, TXZResourceManager.STYLE_DEFAULT);
                return;
            }
            updateItem(0, this.mSetData.Range, String.format("%d KM", new Object[]{Integer.valueOf(this.mSetData.Range)}));
        }
    }

    public void QueryData() {
        CanJni.ChanAOdCarQuery(58, 0);
    }
}
