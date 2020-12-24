package com.ts.can.cc.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanCCWcAmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.CcWcAMPInfo mAmpData;

    public CanCCWcAmpSetView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
        if (id == 6) {
            CanJni.CcWcGetCarAmpSet(7, item);
        } else if (id == 7) {
            CanJni.CcWcGetCarAmpSet(8, item);
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                int dPos = Math.abs(pos - this.mAmpData.Vol);
                if (dPos > 5) {
                    dPos = 5;
                }
                if (pos - this.mAmpData.Vol > 0) {
                    CanJni.CcWcGetCarAmpSet(1, dPos);
                    return;
                } else {
                    CanJni.CcWcGetCarAmpSet(1, (-dPos) + 1 + 255);
                    return;
                }
            case 1:
                CanJni.CcWcGetCarAmpSet(2, checkPos(pos, this.mAmpData.Bal));
                return;
            case 2:
                CanJni.CcWcGetCarAmpSet(3, checkPos(pos, this.mAmpData.Fad));
                return;
            case 3:
                CanJni.CcWcGetCarAmpSet(4, checkPos(pos, this.mAmpData.Bas));
                return;
            case 4:
                CanJni.CcWcGetCarAmpSet(5, checkPos(pos, this.mAmpData.Mid));
                return;
            case 5:
                CanJni.CcWcGetCarAmpSet(6, checkPos(pos, this.mAmpData.Tre));
                return;
            default:
                return;
        }
    }

    private int checkPos(int pos, int value) {
        int result = Math.abs(pos - value);
        if (result > 1) {
            result = 1;
        }
        if (pos - value > 0) {
            return value + result;
        }
        return value - result;
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_cch9_with_the_volume, R.string.can_cch9_surround_sound};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 39;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[] attr = new int[4];
        attr[1] = 20;
        attr[2] = 1;
        this.mProgressAttrs[1] = attr;
        this.mProgressAttrs[2] = attr;
        this.mProgressAttrs[3] = attr;
        this.mProgressAttrs[4] = attr;
        this.mProgressAttrs[5] = attr;
        this.mPopValueIds[6] = new int[]{R.string.can_Scsfctsy_3, R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[7] = new int[]{R.string.can_h6_coupe_hr, R.string.can_h6_coupe_lt};
        this.mAmpData = new CanDataInfo.CcWcAMPInfo();
    }

    public void ResetData(boolean check) {
        CanJni.CcWcGetAmpInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vol);
            updateItem(1, this.mAmpData.Bal);
            updateItem(2, this.mAmpData.Fad);
            updateItem(3, this.mAmpData.Bas);
            updateItem(4, this.mAmpData.Mid);
            updateItem(5, this.mAmpData.Tre);
            updateItem(6, this.mAmpData.VolLinkSpeed);
            updateItem(7, this.mAmpData.Surround);
        }
    }

    public void QueryData() {
        CanJni.CcWcGetCarQuery(5, 1, 166);
    }
}
