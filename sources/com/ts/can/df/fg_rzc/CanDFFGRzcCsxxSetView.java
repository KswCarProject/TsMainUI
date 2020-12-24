package com.ts.can.df.fg_rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanDFFGRzcCsxxSetView extends CanScrollCarInfoView {
    private static final int BMSCFDZT = 1;
    private static final int DCBCFDZT = 0;
    private int[] BMSCFDZT_VALUE;
    private int[] DCBCFDZT_VALUE;
    private CanDataInfo.Dffg_RzcCarInfo mSetData;

    public CanDFFGRzcCsxxSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_dcbcfdzt, R.string.can_bmscfdzt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.DCBCFDZT_VALUE = new int[]{R.string.can_discharging, R.string.can_quick_charging, R.string.can_low_charging};
        this.BMSCFDZT_VALUE = new int[]{R.string.can_tccd, R.string.can_xscd, R.string.can_wcd, R.string.can_cdwc};
        this.mSetData = new CanDataInfo.Dffg_RzcCarInfo();
    }

    public void ResetData(boolean check) {
        CanJni.DffgRzcGetCarInfo(this.mSetData);
        if (!i2b(this.mSetData.CsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.CsUpdate)) {
            this.mSetData.CsUpdate = 0;
            if (this.mSetData.Dcbcfdzt > 0) {
                updateItem(0, this.mSetData.Dcbcfdzt - 1, getString(this.DCBCFDZT_VALUE[this.mSetData.Dcbcfdzt - 1]));
            }
            if (this.mSetData.Bmscfdzt > 0) {
                updateItem(1, this.mSetData.Bmscfdzt - 1, getString(this.BMSCFDZT_VALUE[this.mSetData.Bmscfdzt - 1]));
            }
        }
    }

    public void QueryData() {
    }
}
