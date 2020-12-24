package com.ts.can.vw.golf.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGolfWcJSMSSetView extends CanScrollCarInfoView {
    private static final int ITEM_EMODE = 0;
    private static final int ITEM_GTE_YDMS = 4;
    private static final int ITEM_HHDL = 1;
    private static final int ITEM_XDCCD = 3;
    private static final int ITEM_XDCWC = 2;
    private CanDataInfo.GolfWcDrivingMode3Selection mAdtData;
    private CanDataInfo.GolfWcDrivingMode3Selection mSetData;

    public CanGolfWcJSMSSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GolfWcDrivingModeSet(32, 0, 0);
                return;
            case 1:
                CanJni.GolfWcDrivingModeSet(33, 0, 0);
                return;
            case 2:
                CanJni.GolfWcDrivingModeSet(34, 0, 0);
                return;
            case 3:
                CanJni.GolfWcDrivingModeSet(35, 0, 0);
                return;
            case 4:
                CanJni.GolfWcDrivingModeSet(36, 0, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_emode, R.string.can_hhdl, R.string.can_xdcwc, R.string.can_xdccd, R.string.can_gteyd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mAdtData = new CanDataInfo.GolfWcDrivingMode3Selection();
        this.mSetData = new CanDataInfo.GolfWcDrivingMode3Selection();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        showItem(new int[5]);
    }

    public void ResetData(boolean check) {
        CanJni.GolfGetDrivingMode3Selection(this.mAdtData, 0);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(new int[]{this.mAdtData.E_MODE, this.mAdtData.Hhdl, this.mAdtData.Xdcwc, this.mAdtData.Xdccd, this.mAdtData.GTE});
        }
        CanJni.GolfGetDrivingMode3Selection(this.mSetData, 1);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.E_MODE, this.mSetData.Hhdl, this.mSetData.Xdcwc, this.mSetData.Xdccd, this.mSetData.GTE});
        }
    }

    public void QueryData() {
        CanJni.GolfWcQueryData(1, 136);
    }
}
