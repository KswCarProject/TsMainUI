package com.ts.can.vw.golf.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGolfWcCDSZSetView extends CanScrollCarInfoView {
    private static final int ITEM_CNWD = 1;
    private static final int ITEM_KTSYXDCGD = 2;
    private static final int ITEM_XDCCDXX = 3;
    private static final int ITEM_ZDCDDL = 0;
    private CanDataInfo.GolfWcMixDrivingSet mAdtData;
    private CanDataInfo.GolfWcMixDrivingSet mSetData;

    public CanGolfWcCDSZSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.GolfMixDrivingSetCmd(1, sendCDDL(item));
                return;
            default:
                return;
        }
    }

    private int sendCDDL(int item) {
        switch (item) {
            case 0:
                return 5;
            case 1:
                return 10;
            case 2:
                return 13;
            case 3:
                return 255;
            default:
                return 0;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.GolfMixDrivingSetCmd(2, sendCNWD(pos));
                return;
            case 3:
                CanJni.GolfMixDrivingSetCmd(4, pos);
                return;
            default:
                return;
        }
    }

    private int sendCNWD(int pos) {
        if (pos == 0) {
            return Can.CAN_FLAT_RZC;
        }
        if (pos == 14) {
            return 255;
        }
        return pos + 16;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.GolfMixDrivingSetCmd(3, Neg(this.mSetData.Ktsyxdcgd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_zdcddl, R.string.can_hant_cnwd, R.string.can_ktsyxdcgd, R.string.can_xdccdxx};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS};
        this.mPopValueIds[0] = new int[]{R.string.can_5a, R.string.can_10a, R.string.can_13a, R.string.can_max_a};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 14;
        iArr2[2] = 1;
        iArr[1] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 100;
        iArr4[2] = 1;
        iArr3[3] = iArr4;
        this.mAdtData = new CanDataInfo.GolfWcMixDrivingSet();
        this.mSetData = new CanDataInfo.GolfWcMixDrivingSet();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        showItem(0, 0);
        showItem(1, 0);
        showItem(2, 0);
        showItem(3, 0);
    }

    public void ResetData(boolean check) {
        CanJni.GolfGetMixDrivingSet(this.mAdtData, 0);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(new int[]{this.mAdtData.Zdcddl, this.mAdtData.Cnwd, this.mAdtData.Ktsyxdcgd, this.mAdtData.Xdccdxx});
        }
        CanJni.GolfGetMixDrivingSet(this.mSetData, 1);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            int[] values = new int[4];
            values[0] = changedCDDL(this.mSetData.Zdcddl);
            values[2] = this.mSetData.Ktsyxdcgd;
            values[3] = this.mSetData.Xdccdxx;
            updateItem(values);
            updateItem(3, this.mSetData.Xdccdxx, String.valueOf(String.format("%d", new Object[]{Integer.valueOf(this.mSetData.Xdccdxx)})) + " %");
            if (this.mSetData.Cnwd == 254) {
                updateItem(1, 0, "Low");
            } else if (this.mSetData.Cnwd == 255) {
                updateItem(1, 14, "Hi");
            } else if (this.mSetData.Cnwd < 32 || this.mSetData.Cnwd > 59) {
                updateItem(1, 0, "--");
            } else {
                updateItem(1, ((int) (((double) this.mSetData.Cnwd) * 0.5d)) - 16, String.format("%.1f", new Object[]{Double.valueOf(((double) this.mSetData.Cnwd) * 0.5d)}));
            }
        }
    }

    private int changedCDDL(int cddl) {
        switch (cddl) {
            case 5:
                return 0;
            case 10:
                return 1;
            case 13:
                return 2;
            case 255:
                return 3;
            default:
                return 0;
        }
    }

    public void QueryData() {
        CanJni.GolfWcQueryData(1, 73);
    }
}
