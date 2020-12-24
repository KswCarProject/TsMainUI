package com.ts.can.chrysler;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanChrOthAmpSetView extends CanScrollCarInfoView {
    protected static final int ITEM_DTS = 6;
    protected static final int ITEM_SPEED_VOLUME_ADJUSTMENT = 7;
    protected static final int ITEM_VOL_BAL = 2;
    protected static final int ITEM_VOL_FAD = 1;
    protected static final int ITEM_VOL_HIGH = 5;
    protected static final int ITEM_VOL_LOW = 3;
    protected static final int ITEM_VOL_MID = 4;
    protected static final int ITEM_VOL_VALUE = 0;
    private CanDataInfo.ChrOthAMPInfo mAmpData;

    public CanChrOthAmpSetView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
        if (id == 7) {
            CanJni.ChrOthAmpCtrl(9, item);
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.ChrOthAmpCtrl(2, pos);
                return;
            case 1:
                CanJni.ChrOthAmpCtrl(3, pos);
                return;
            case 2:
                CanJni.ChrOthAmpCtrl(4, pos);
                return;
            case 3:
                CanJni.ChrOthAmpCtrl(5, pos);
                return;
            case 4:
                CanJni.ChrOthAmpCtrl(7, pos);
                return;
            case 5:
                CanJni.ChrOthAmpCtrl(6, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 6) {
            CanJni.ChrOthAmpCtrl(10, Neg(this.mAmpData.Dts));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_vol_around, R.string.can_apply_speed};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[7] = new int[]{R.string.can_trunk_close, R.string.can_headlightsens_1, R.string.can_headlightsens_2, R.string.can_headlightsens_3};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 38;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = 1;
        iArr4[1] = 19;
        iArr4[2] = 1;
        iArr3[5] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[0] = 1;
        iArr6[1] = 19;
        iArr6[2] = 1;
        iArr5[4] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[0] = 1;
        iArr8[1] = 19;
        iArr8[2] = 1;
        iArr7[3] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[0] = 1;
        iArr10[1] = 19;
        iArr10[2] = 1;
        iArr9[1] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[0] = 1;
        iArr12[1] = 19;
        iArr12[2] = 1;
        iArr11[2] = iArr12;
        this.mAmpData = new CanDataInfo.ChrOthAMPInfo();
    }

    public void ResetData(boolean check) {
        CanJni.CompassGetAmpInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vol);
            updateItem(1, this.mAmpData.Fad, setValText(this.mAmpData.Fad, 1));
            updateItem(2, this.mAmpData.Bal, setValText(this.mAmpData.Bal, 2));
            updateItem(3, this.mAmpData.Bas, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bas - 10)}));
            updateItem(4, this.mAmpData.Mid, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Mid - 10)}));
            updateItem(5, this.mAmpData.Tre, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Tre - 10)}));
            updateItem(6, this.mAmpData.Dts);
            updateItem(7, this.mAmpData.Csld);
        }
    }

    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    private String setValText(int val, int sta) {
        switch (sta) {
            case 1:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "F" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "R" + String.valueOf(val - 10);
                }
                break;
            case 2:
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
}
