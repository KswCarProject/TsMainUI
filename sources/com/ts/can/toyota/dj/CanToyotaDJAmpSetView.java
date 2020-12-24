package com.ts.can.toyota.dj;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanToyotaDJAmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.ToyotaDj_AMPInfo mAmpData;

    public CanToyotaDJAmpSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.ToyotaDjAmpSet(7, pos);
                return;
            case 1:
                CanJni.ToyotaDjAmpSet(2, pos);
                return;
            case 2:
                CanJni.ToyotaDjAmpSet(1, pos);
                return;
            case 3:
                CanJni.ToyotaDjAmpSet(4, pos);
                return;
            case 4:
                CanJni.ToyotaDjAmpSet(5, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_high};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 63;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 14;
        iArr4[2] = 1;
        iArr4[3] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 14;
        iArr6[2] = 1;
        iArr6[3] = 1;
        iArr5[2] = iArr6;
        this.mProgressAttrs[3] = new int[]{2, 12, 1, 1};
        this.mProgressAttrs[4] = new int[]{2, 12, 1, 1};
        this.mAmpData = new CanDataInfo.ToyotaDj_AMPInfo();
    }

    public void ResetData(boolean check) {
        CanJni.ToyotaDjGetAMPInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vol);
            updateItem(1, this.mAmpData.Bal, setValText(this.mAmpData.Bal, true));
            updateItem(2, this.mAmpData.Fad, setValText(this.mAmpData.Fad, false));
            updateItem(3, this.mAmpData.Bas, new StringBuilder().append(this.mAmpData.Bas - 7).toString());
            updateItem(4, this.mAmpData.Tre, new StringBuilder().append(this.mAmpData.Tre - 7).toString());
        }
    }

    private String setValText(int val, boolean isLtRt) {
        if (isLtRt) {
            if (val == 7) {
                return "0";
            }
            if (val < 7) {
                return "L" + String.valueOf(7 - val);
            }
            if (val > 7) {
                return "R" + String.valueOf(val - 7);
            }
        } else if (val == 7) {
            return "0";
        } else {
            if (val < 7) {
                return "F" + String.valueOf(7 - val);
            }
            if (val > 7) {
                return "R" + String.valueOf(val - 7);
            }
        }
        return "0";
    }

    public void QueryData() {
        CanJni.ToyotaDjQuery(49, 0);
    }
}
