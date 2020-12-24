package com.ts.can.honda.wc.accord9;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanAccord9WcAmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaWcAmpInfo mAmpData;

    public CanAccord9WcAmpSetView(Activity activity) {
        super(activity, 9);
    }

    public void onItem(int id, int item) {
        if (id == 6) {
            CanJni.HondaWcCarAmpSet(7, item);
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
                    CanJni.HondaWcCarAmpSet(1, dPos);
                    return;
                } else {
                    CanJni.HondaWcCarAmpSet(1, (-dPos) + 1 + 255);
                    return;
                }
            case 1:
                CanJni.HondaWcCarAmpSet(2, checkPos(pos, this.mAmpData.Bal));
                return;
            case 2:
                CanJni.HondaWcCarAmpSet(3, checkPos(pos, this.mAmpData.Fad));
                return;
            case 3:
                CanJni.HondaWcCarAmpSet(4, checkPos(pos, this.mAmpData.Bas));
                return;
            case 4:
                CanJni.HondaWcCarAmpSet(5, checkPos(pos, this.mAmpData.Mid));
                return;
            case 5:
                CanJni.HondaWcCarAmpSet(6, checkPos(pos, this.mAmpData.Tre));
                return;
            case 8:
                CanJni.HondaWcCarAmpSet(10, checkPos(pos, this.mAmpData.Subwof));
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
        switch (((Integer) v.getTag()).intValue()) {
            case 7:
                CanJni.HondaWcCarAmpSet(8, Neg(this.mAmpData.Dts));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_psa_wc_ylssdtj, R.string.can_cch9_surround_sound, R.string.can_subwof};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 40;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[] attr = new int[4];
        attr[1] = 12;
        attr[2] = 1;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 18;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 18;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        this.mProgressAttrs[3] = attr;
        this.mProgressAttrs[4] = attr;
        this.mProgressAttrs[5] = attr;
        this.mPopValueIds[6] = new int[]{R.string.can_Scsfctsy_3, R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mProgressAttrs[8] = attr;
        this.mAmpData = new CanDataInfo.HondaWcAmpInfo();
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcGetAmpInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vol);
            updateItem(1, this.mAmpData.Bal, setValText(this.mAmpData.Bal, true));
            updateItem(2, this.mAmpData.Fad, setValText(this.mAmpData.Fad, false));
            updateItem(3, this.mAmpData.Bas);
            updateItem(4, this.mAmpData.Mid);
            updateItem(5, this.mAmpData.Tre);
            updateItem(6, this.mAmpData.Csld);
            updateItem(7, this.mAmpData.Dts);
            updateItem(8, this.mAmpData.Subwof);
        }
    }

    private String setValText(int val, boolean isLtRt) {
        if (isLtRt) {
            if (val == 10) {
                return "0";
            }
            if (val < 10) {
                return "L" + String.valueOf(10 - val);
            }
            if (val > 10) {
                return "R" + String.valueOf(val - 10);
            }
        } else if (val == 10) {
            return "0";
        } else {
            if (val < 10) {
                return "F" + String.valueOf(10 - val);
            }
            if (val > 10) {
                return "R" + String.valueOf(val - 10);
            }
        }
        return "0";
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 166);
    }
}
