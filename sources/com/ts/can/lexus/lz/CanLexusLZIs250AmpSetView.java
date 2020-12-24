package com.ts.can.lexus.lz;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanLexusLZIs250AmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.LexusLz_Amp mAmpData;

    public CanLexusLZIs250AmpSetView(Activity activity) {
        super(activity, 11);
    }

    public void onItem(int id, int item) {
        if (id == 6) {
            CanJni.LexusLzCarsSet(12, item);
        } else if (id == 8) {
            CanJni.LexusLzCarsSet(8, item);
        } else if (id == 9) {
            CanJni.LexusLzCarsSet(9, item);
        } else if (id == 10) {
            CanJni.LexusLzCarsSet(10, item);
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.LexusLzCarsSet(7, pos);
                return;
            case 1:
                CanJni.LexusLzCarsSet(1, pos);
                return;
            case 2:
                CanJni.LexusLzCarsSet(2, pos);
                return;
            case 3:
                CanJni.LexusLzCarsSet(4, pos + 2);
                return;
            case 4:
                CanJni.LexusLzCarsSet(6, pos + 2);
                return;
            case 5:
                CanJni.LexusLzCarsSet(5, pos + 2);
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
                if (this.mAmpData.Asl == 1) {
                    CanJni.LexusLzCarsSet(3, 1);
                    return;
                } else {
                    CanJni.LexusLzCarsSet(3, 8);
                    return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_amp_psel, R.string.can_amp_asl, R.string.can_gf_toggle, R.string.can_switch_eq, R.string.can_switch_mute};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 63;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[] attr = new int[4];
        attr[1] = 10;
        attr[2] = 1;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 14;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 14;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        this.mProgressAttrs[3] = attr;
        this.mProgressAttrs[4] = attr;
        this.mProgressAttrs[5] = attr;
        this.mPopValueIds[6] = new int[]{R.array.can_lexus_lz_psel};
        this.mPopValueIds[8] = new int[]{R.string.can_cch9_seatmemory_key1, R.string.can_cch9_seatmemory_key2};
        this.mPopValueIds[9] = new int[]{R.string.can_cch9_seatmemory_key1, R.string.can_cch9_seatmemory_key2};
        this.mPopValueIds[10] = new int[]{R.string.can_cch9_seatmemory_key1, R.string.can_cch9_seatmemory_key2};
        this.mAmpData = new CanDataInfo.LexusLz_Amp();
    }

    public void ResetData(boolean check) {
        CanJni.LexusLzGetAmpInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            Log.e("lq2", "mAmpData.Asl:" + this.mAmpData.Asl);
            updateItem(0, this.mAmpData.Vol);
            updateItem(1, this.mAmpData.Fad, setValText(this.mAmpData.Fad, false));
            updateItem(2, this.mAmpData.Bal, setValText(this.mAmpData.Bal, true));
            updateItem(3, this.mAmpData.Bass - 2, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bass - 7)}));
            updateItem(4, this.mAmpData.Mid - 2, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Mid - 7)}));
            updateItem(5, this.mAmpData.Tre - 2, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Tre - 7)}));
            updateItem(6, this.mAmpData.Psel);
            updateItem(7, this.mAmpData.Asl);
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
        CanJni.LexusLzQuery(49, 0);
    }
}
