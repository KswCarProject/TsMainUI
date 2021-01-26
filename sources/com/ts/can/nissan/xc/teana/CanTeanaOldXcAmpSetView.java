package com.ts.can.nissan.xc.teana;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanTeanaOldXcAmpSetView extends CanScrollCarInfoView {
    protected static final int ITEM_ASL = 7;
    protected static final int ITEM_BAL = 3;
    protected static final int ITEM_BOSE_CENTER = 6;
    protected static final int ITEM_FAD = 4;
    protected static final int ITEM_HIGH = 1;
    protected static final int ITEM_LOW = 2;
    protected static final int ITEM_SURROUND = 5;
    protected static final int ITEM_VOL = 0;
    private CanDataInfo.TeanaOldXc_Amp mAmpData;

    public CanTeanaOldXcAmpSetView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                if (pos - this.mAmpData.Vol > 0) {
                    CanJni.TeanOldXcAmpCmd(1, 0);
                    return;
                } else {
                    CanJni.TeanOldXcAmpCmd(2, 0);
                    return;
                }
            case 1:
                if (pos - changeVal(this.mAmpData.Tre) > 0) {
                    CanJni.TeanOldXcAmpCmd(5, 0);
                    return;
                } else {
                    CanJni.TeanOldXcAmpCmd(6, 0);
                    return;
                }
            case 2:
                if (pos - changeVal(this.mAmpData.Bass) > 0) {
                    CanJni.TeanOldXcAmpCmd(3, 0);
                    return;
                } else {
                    CanJni.TeanOldXcAmpCmd(4, 0);
                    return;
                }
            case 3:
                if (pos - changeVal(this.mAmpData.Bal) > 0) {
                    CanJni.TeanOldXcAmpCmd(7, 0);
                    return;
                } else {
                    CanJni.TeanOldXcAmpCmd(8, 0);
                    return;
                }
            case 4:
                if (pos - changeVal(this.mAmpData.Fad) > 0) {
                    CanJni.TeanOldXcAmpCmd(9, 0);
                    return;
                } else {
                    CanJni.TeanOldXcAmpCmd(10, 0);
                    return;
                }
            case 5:
                if (pos - changeVal(this.mAmpData.SourroundVol) > 0) {
                    CanJni.TeanOldXcAmpCmd(13, 0);
                    return;
                } else {
                    CanJni.TeanOldXcAmpCmd(14, 0);
                    return;
                }
            case 7:
                if (pos - this.mAmpData.SpeedSensitiveVol > 0) {
                    CanJni.TeanOldXcAmpCmd(11, 0);
                    return;
                } else {
                    CanJni.TeanOldXcAmpCmd(12, 0);
                    return;
                }
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
            case 6:
                CanJni.TeanOldXcAmpCmd(15, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_vol_high, R.string.can_vol_low, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_around, R.string.can_bose_centerpoint, R.string.can_apply_speed};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 16;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 10;
        iArr4[2] = 1;
        iArr3[2] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 10;
        iArr6[2] = 1;
        iArr5[1] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[1] = 10;
        iArr8[2] = 1;
        iArr7[3] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[1] = 10;
        iArr10[2] = 1;
        iArr9[4] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[1] = 10;
        iArr12[2] = 1;
        iArr11[5] = iArr12;
        int[][] iArr13 = this.mProgressAttrs;
        int[] iArr14 = new int[4];
        iArr14[1] = 5;
        iArr14[2] = 1;
        iArr13[7] = iArr14;
        this.mAmpData = new CanDataInfo.TeanaOldXc_Amp();
    }

    public void ResetData(boolean check) {
        CanJni.TeanOldXcGetAmpInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            Log.d("lq", "mAmpData.Vol:" + this.mAmpData.Vol);
            if (this.mAmpData.Vol > 16) {
                updateItem(0, 263);
            } else {
                updateItem(0, this.mAmpData.Vol);
            }
            updateItem(2, changeVal(this.mAmpData.Bass), String.format("%d", new Object[]{Integer.valueOf(changeVal(this.mAmpData.Bass) - 5)}));
            updateItem(1, changeVal(this.mAmpData.Tre), String.format("%d", new Object[]{Integer.valueOf(changeVal(this.mAmpData.Tre) - 5)}));
            updateItem(3, changeVal(this.mAmpData.Bal), setValText(changeVal(this.mAmpData.Bal), 3));
            updateItem(4, changeVal(this.mAmpData.Fad), setValText(changeVal(this.mAmpData.Fad), 4));
            updateItem(5, changeVal(this.mAmpData.SourroundVol), String.format("%d", new Object[]{Integer.valueOf(changeVal(this.mAmpData.SourroundVol) - 5)}));
            updateItem(7, this.mAmpData.SpeedSensitiveVol, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.SpeedSensitiveVol)}));
            updateItem(6, this.mAmpData.BOSE_Centerpoint);
        }
    }

    private String setValText(int val, int sta) {
        switch (sta) {
            case 3:
                if (val == 5) {
                    return "0";
                }
                if (val < 5) {
                    return "L" + String.valueOf(5 - val);
                }
                if (val > 5) {
                    return "R" + String.valueOf(val - 5);
                }
                break;
            case 4:
                if (val == 5) {
                    return "0";
                }
                if (val < 5) {
                    return "R" + String.valueOf(5 - val);
                }
                if (val > 5) {
                    return "F" + String.valueOf(val - 5);
                }
                break;
        }
        return "0";
    }

    private int changeVal(int val) {
        switch (val) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return val + 5;
            case Can.CAN_MG_ZS_WC:
                return 0;
            case Can.CAN_TOYOTA_SP_XP:
                return 1;
            case Can.CAN_FORD_ESCORT_LY:
                return 2;
            case Can.CAN_FLAT_RZC:
                return 3;
            case 255:
                return 4;
            default:
                return 0;
        }
    }

    public void QueryData() {
        CanJni.TeanOldXcQuery(19);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }
}
