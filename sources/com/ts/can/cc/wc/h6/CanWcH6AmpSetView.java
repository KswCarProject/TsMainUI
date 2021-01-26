package com.ts.can.cc.wc.h6;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanWcH6AmpSetView extends CanScrollCarInfoView {
    protected static final int ITEM_ASL = 6;
    protected static final int ITEM_BAL = 5;
    protected static final int ITEM_FAD = 4;
    protected static final int ITEM_HIGH = 1;
    protected static final int ITEM_LOW = 3;
    protected static final int ITEM_MID = 2;
    protected static final int ITEM_SC = 7;
    protected static final int ITEM_VOL = 0;
    private CanDataInfo.CcH6WcAmpData mAmpData;

    public CanWcH6AmpSetView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 6:
                CanJni.CcH6WcAmpSet(7, item);
                return;
            case 7:
                CanJni.CcH6WcAmpSet(8, item);
                return;
            default:
                return;
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
                    CanJni.CcH6WcAmpSet(1, dPos);
                    return;
                } else {
                    CanJni.CcH6WcAmpSet(1, (-dPos) + 1 + 255);
                    return;
                }
            case 1:
                CanJni.CcH6WcAmpSet(6, checkPos(pos, this.mAmpData.Tre));
                return;
            case 2:
                CanJni.CcH6WcAmpSet(5, checkPos(pos, this.mAmpData.Mid));
                return;
            case 3:
                CanJni.CcH6WcAmpSet(4, checkPos(pos, this.mAmpData.Bas));
                return;
            case 4:
                CanJni.CcH6WcAmpSet(3, checkPos(pos, this.mAmpData.Fad));
                return;
            case 5:
                CanJni.CcH6WcAmpSet(2, checkPos(pos, this.mAmpData.Bal));
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
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_vol_high, R.string.can_vol_middle, R.string.can_vol_low, R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_apply_speed, R.string.can_h6_coupe_hrlts};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 39;
        iArr2[2] = 5;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 20;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 20;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[1] = 20;
        iArr8[2] = 1;
        iArr7[3] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[1] = 20;
        iArr10[2] = 1;
        iArr9[4] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[1] = 20;
        iArr12[2] = 1;
        iArr11[5] = iArr12;
        this.mPopValueIds[6] = new int[]{R.string.can_trunk_close, R.string.can_sensitivity_low, R.string.can_sensitivity_mid, R.string.can_sensitivity_high};
        this.mPopValueIds[7] = new int[]{R.string.can_h6_coupe_hr, R.string.can_h6_coupe_lt};
        this.mAmpData = new CanDataInfo.CcH6WcAmpData();
    }

    public void ResetData(boolean check) {
        CanJni.CcH6WcGetAmpSet(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            Log.d("lq", "mAmpData.Vol:" + this.mAmpData.Vol);
            if (this.mAmpData.Vol > 39) {
                updateItem(0, 39);
            } else {
                updateItem(0, this.mAmpData.Vol);
            }
            if (this.mAmpData.Tre > 20) {
                updateItem(1, 20, String.format("%d", new Object[]{10}));
            } else {
                updateItem(1, this.mAmpData.Tre, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Tre - 10)}));
            }
            if (this.mAmpData.Mid > 20) {
                updateItem(2, 20, String.format("%d", new Object[]{10}));
            } else {
                updateItem(2, this.mAmpData.Mid, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Mid - 10)}));
            }
            if (this.mAmpData.Bas > 20) {
                updateItem(3, 20, String.format("%d", new Object[]{10}));
            } else {
                updateItem(3, this.mAmpData.Bas, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bas - 10)}));
            }
            if (this.mAmpData.Fad > 20) {
                updateItem(4, 20, String.format("%d", new Object[]{10}));
            } else {
                updateItem(4, this.mAmpData.Fad, setValText(this.mAmpData.Fad, 4));
            }
            if (this.mAmpData.Bal > 20) {
                updateItem(5, 20, String.format("%d", new Object[]{10}));
            } else {
                updateItem(5, this.mAmpData.Bal, setValText(this.mAmpData.Bal, 5));
            }
            updateItem(6, this.mAmpData.Asl);
            updateItem(7, this.mAmpData.Sc);
        }
    }

    private String setValText(int val, int sta) {
        switch (sta) {
            case 4:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "R" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "F" + String.valueOf(val - 10);
                }
                break;
            case 5:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "L" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "R" + String.valueOf(val - 10);
                }
                break;
        }
        return "0";
    }

    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }
}
