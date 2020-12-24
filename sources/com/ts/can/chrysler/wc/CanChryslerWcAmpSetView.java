package com.ts.can.chrysler.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanChryslerWcAmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChrWcAMPInfo mAmpData;

    public CanChryslerWcAmpSetView(Activity activity) {
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
                int dPos = Math.abs(pos - this.mAmpData.Vol);
                if (dPos > 5) {
                    dPos = 5;
                }
                if (pos - this.mAmpData.Vol > 0) {
                    CanJni.ChryslerWcAmpSet(1, this.mAmpData.Vol + dPos);
                    return;
                } else {
                    CanJni.ChryslerWcAmpSet(1, this.mAmpData.Vol - dPos);
                    return;
                }
            case 1:
                CanJni.ChryslerWcAmpSet(2, checkPos(pos + 1, changeVal(this.mAmpData.Bal), this.mAmpData.Bal));
                return;
            case 2:
                CanJni.ChryslerWcAmpSet(3, checkPos(pos + 1, changeVal(this.mAmpData.Fad), this.mAmpData.Fad));
                return;
            case 3:
                CanJni.ChryslerWcAmpSet(4, checkPos(pos + 1, changeVal(this.mAmpData.Bas), this.mAmpData.Bas));
                return;
            case 4:
                CanJni.ChryslerWcAmpSet(5, checkPos(pos + 1, changeVal(this.mAmpData.Mid), this.mAmpData.Mid));
                return;
            case 5:
                CanJni.ChryslerWcAmpSet(6, checkPos(pos + 1, changeVal(this.mAmpData.Tre), this.mAmpData.Tre));
                return;
            default:
                return;
        }
    }

    private int checkPos(int pos, int value, int trueValue) {
        int result = Math.abs(pos - value);
        if (result > 1) {
            result = 1;
        }
        if (pos - value > 0) {
            if (trueValue == 255) {
                return 0;
            }
            return trueValue + result;
        } else if (trueValue != 0) {
            return trueValue - result;
        } else {
            return 255;
        }
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
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_psa_wc_ylssdtj, R.string.can_cch9_surround_sound};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 38;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[] attr = new int[4];
        attr[1] = 18;
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
            updateItem(1, changeVal(this.mAmpData.Bal) - 1, setValText(changeVal(this.mAmpData.Bal), true));
            updateItem(2, changeVal(this.mAmpData.Fad) - 1, setValText(changeVal(this.mAmpData.Fad), false));
            updateItem(3, changeVal(this.mAmpData.Bas) - 1);
            updateItem(4, changeVal(this.mAmpData.Mid) - 1);
            updateItem(5, changeVal(this.mAmpData.Tre) - 1);
            updateItem(6, this.mAmpData.Csld);
            updateItem(7, this.mAmpData.Dts);
        }
    }

    private int changeVal(int val) {
        switch (val) {
            case 0:
                return 10;
            case 1:
                return 11;
            case 2:
                return 12;
            case 3:
                return 13;
            case 4:
                return 14;
            case 5:
                return 15;
            case 6:
                return 16;
            case 7:
                return 17;
            case 8:
                return 18;
            case 9:
                return 19;
            case Can.CAN_FORD_EDGE_XFY:
                return 1;
            case Can.CAN_RENAUL_KOLEOS_XFY:
                return 2;
            case Can.CAN_LUXGEN_WC:
                return 3;
            case Can.CAN_NISSAN_XFY:
                return 4;
            case Can.CAN_MG_ZS_WC:
                return 5;
            case Can.CAN_TOYOTA_SP_XP:
                return 6;
            case Can.CAN_FORD_ESCORT_LY:
                return 7;
            case Can.CAN_FLAT_RZC:
                return 8;
            case 255:
                return 9;
            default:
                return 0;
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
        CanJni.ChryslerWcQuery(1, 166);
    }
}
