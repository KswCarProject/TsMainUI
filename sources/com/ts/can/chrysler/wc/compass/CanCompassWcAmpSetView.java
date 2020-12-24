package com.ts.can.chrysler.wc.compass;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanCompassWcAmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChrWcAMPInfo mAmpData;

    public CanCompassWcAmpSetView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
        if (id == 6) {
            CanJni.ChryslerWcAmpSet(7, item);
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.ChryslerWcAmpSet(1, pos);
                return;
            case 1:
                CanJni.ChryslerWcAmpSet(2, checkPos(pos));
                return;
            case 2:
                CanJni.ChryslerWcAmpSet(3, checkPos(pos));
                return;
            case 3:
                CanJni.ChryslerWcAmpSet(4, checkPos(pos));
                return;
            case 4:
                CanJni.ChryslerWcAmpSet(5, checkPos(pos));
                return;
            case 5:
                CanJni.ChryslerWcAmpSet(6, checkPos(pos));
                return;
            default:
                return;
        }
    }

    private int checkPos(int pos) {
        if (pos < 0) {
            return pos + 255 + 1;
        }
        return pos;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 7:
                CanJni.ChryslerWcAmpSet(8, Neg(this.mAmpData.Dts));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_a_s_l, R.string.can_around};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 38;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[] attr = new int[4];
        attr[0] = -9;
        attr[1] = 9;
        attr[2] = 1;
        this.mProgressAttrs[1] = attr;
        this.mProgressAttrs[2] = attr;
        this.mProgressAttrs[3] = attr;
        this.mProgressAttrs[4] = attr;
        this.mProgressAttrs[5] = attr;
        this.mPopValueIds[6] = new int[]{R.array.can_wc_a_s_l_arrays};
        this.mAmpData = new CanDataInfo.ChrWcAMPInfo();
    }

    public void ResetData(boolean check) {
        CanJni.ChryslerWcGetAmpInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vol);
            updateItem(1, checkValue(this.mAmpData.Bal));
            updateItem(2, checkValue(this.mAmpData.Fad));
            updateItem(3, checkValue(this.mAmpData.Bas));
            updateItem(4, checkValue(this.mAmpData.Mid));
            updateItem(5, checkValue(this.mAmpData.Tre));
            updateItem(6, this.mAmpData.Csld);
            updateItem(7, this.mAmpData.Dts);
        }
    }

    private int checkValue(int value) {
        if (value > 9) {
            return (value - 255) - 1;
        }
        return value;
    }

    public void QueryData() {
        CanJni.ChryslerWcQuery(1, 166);
    }
}
