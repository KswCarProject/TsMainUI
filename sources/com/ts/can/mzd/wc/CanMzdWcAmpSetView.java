package com.ts.can.mzd.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdWcAmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.MzdWcAmp mAmpData;

    public CanMzdWcAmpSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.MzdWcAmpSet(1, pos);
                return;
            case 1:
                CanJni.MzdWcAmpSet(2, pos);
                return;
            case 2:
                CanJni.MzdWcAmpSet(3, pos);
                return;
            case 3:
                CanJni.MzdWcAmpSet(4, pos);
                return;
            case 4:
                CanJni.MzdWcAmpSet(6, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 5) {
            CanJni.MzdWcAmpSet(7, Neg(this.mAmpData.Plt));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_high, R.string.can_audio_plt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 63;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        this.mProgressAttrs[1] = new int[]{8, 24, 1, 1};
        this.mProgressAttrs[2] = new int[]{8, 24, 1, 1};
        this.mProgressAttrs[3] = new int[]{10, 22, 1, 1};
        this.mProgressAttrs[4] = new int[]{10, 22, 1, 1};
        this.mAmpData = new CanDataInfo.MzdWcAmp();
    }

    public void ResetData(boolean check) {
        CanJni.MzdWcGetAmpInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vol);
            updateItem(1, this.mAmpData.Bal, getAmpStr(1, this.mAmpData.Bal));
            updateItem(2, this.mAmpData.Fad, getAmpStr(2, this.mAmpData.Fad));
            updateItem(3, this.mAmpData.Bas, getAmpStr(3, this.mAmpData.Bas));
            updateItem(4, this.mAmpData.Tre, getAmpStr(4, this.mAmpData.Tre));
            updateItem(5, this.mAmpData.Plt);
        }
    }

    private String getAmpStr(int index, int value) {
        int result = value - 16;
        if (result < 0) {
            if (index == 1) {
                return "L" + Math.abs(result);
            }
            if (index == 2) {
                return "F" + Math.abs(result);
            }
            return new StringBuilder().append(result).toString();
        } else if (result <= 0) {
            return new StringBuilder().append(result).toString();
        } else {
            if (index == 1 || index == 2) {
                return "R" + result;
            }
            return new StringBuilder().append(result).toString();
        }
    }

    public void QueryData() {
        CanJni.MzdWcQuery(5, 1, 166);
    }
}
