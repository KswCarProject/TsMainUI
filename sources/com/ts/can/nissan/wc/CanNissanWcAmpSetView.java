package com.ts.can.nissan.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanNissanWcAmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.NissanWcAMPInfo mAmpData;

    public CanNissanWcAmpSetView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        if (id == 9) {
            CanJni.NissanWcAmpSet(7, item);
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
                    CanJni.NissanWcAmpSet(1, dPos);
                    return;
                } else {
                    CanJni.NissanWcAmpSet(1, (-dPos) + 1 + 255);
                    return;
                }
            case 1:
                CanJni.NissanWcAmpSet(2, checkValue(this.mAmpData.Bal, pos, 1));
                return;
            case 2:
                CanJni.NissanWcAmpSet(3, checkValue(this.mAmpData.Fad, pos, 1));
                return;
            case 3:
                CanJni.NissanWcAmpSet(4, checkValue(this.mAmpData.Bas, pos, 1));
                return;
            case 4:
                CanJni.NissanWcAmpSet(5, checkValue(this.mAmpData.Mid, pos, 1));
                return;
            case 5:
                CanJni.NissanWcAmpSet(6, checkValue(this.mAmpData.Tre, pos, 1));
                return;
            case 6:
                CanJni.NissanWcAmpSet(8, checkValue(this.mAmpData.Surround, pos, 1));
                return;
            default:
                return;
        }
    }

    private int checkValue(int preValue, int curValue, int maxStep) {
        if (preValue >= 251 && preValue <= 255) {
            preValue = (preValue - 1) - 255;
        }
        int dPos = curValue - preValue;
        if (dPos >= maxStep) {
            return 1;
        }
        if (dPos > (-maxStep)) {
            return 0;
        }
        return 255;
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 7) {
            CanJni.NissanWcAmpSet(10, Neg(this.mAmpData.DriveSound));
        } else if (id == 8) {
            CanJni.NissanWcAmpSet(9, Neg(this.mAmpData.BoseCenter));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_around, R.string.can_jszyczt, R.string.can_bose_centerpoint, R.string.can_a_s_l};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[9] = new int[]{R.array.can_a_s_l_arrays};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 40;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[] attr = new int[4];
        attr[0] = -5;
        attr[1] = 5;
        attr[2] = 1;
        this.mProgressAttrs[1] = attr;
        this.mProgressAttrs[2] = attr;
        this.mProgressAttrs[3] = attr;
        this.mProgressAttrs[4] = attr;
        this.mProgressAttrs[5] = attr;
        this.mProgressAttrs[6] = attr;
        this.mAmpData = new CanDataInfo.NissanWcAMPInfo();
    }

    public void ResetData(boolean check) {
        CanJni.NissanWcGetAmpInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vol);
            updateValue(1, this.mAmpData.Bal);
            updateValue(2, this.mAmpData.Fad);
            updateValue(3, this.mAmpData.Bas);
            updateValue(4, this.mAmpData.Mid);
            updateValue(5, this.mAmpData.Tre);
            updateValue(6, this.mAmpData.Surround);
            updateItem(7, this.mAmpData.DriveSound);
            updateItem(8, this.mAmpData.BoseCenter);
            updateItem(9, this.mAmpData.VolLinkSpeed);
        }
    }

    private void updateValue(int item, int value) {
        if (value >= 251) {
            updateItem(item, (value - 255) - 1);
        } else if (value >= 0 && value <= 5) {
            updateItem(item, value);
        }
    }

    public void QueryData() {
    }
}
