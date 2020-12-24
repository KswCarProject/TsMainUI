package com.ts.can.hyundai.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiWcSetAmpView extends CanScrollCarInfoView {
    private CanDataInfo.HyundaiWcAmpData mAmpData;

    public CanHyundaiWcSetAmpView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
    }

    public int onVal(int pos, int val) {
        int temp = 16;
        if (pos > val) {
            temp = val + 1;
        } else if (val > 0) {
            temp = val - 1;
        }
        if (temp < 6 || temp > 26) {
            return 16;
        }
        return temp;
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.HyundaiWcAmpSet(1, pos);
                return;
            case 1:
                CanJni.HyundaiWcAmpSet(2, onVal(pos, this.mAmpData.Bal));
                return;
            case 2:
                CanJni.HyundaiWcAmpSet(3, onVal(pos, this.mAmpData.Fad));
                return;
            case 3:
                CanJni.HyundaiWcAmpSet(4, onVal(pos, this.mAmpData.Bas));
                return;
            case 4:
                CanJni.HyundaiWcAmpSet(5, onVal(pos, this.mAmpData.Mid));
                return;
            case 5:
                CanJni.HyundaiWcAmpSet(6, onVal(pos, this.mAmpData.Tre));
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 6) {
            CanJni.HyundaiWcAmpSet(7, Neg(this.mAmpData.Mute));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_vol_mute};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 45;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = 6;
        iArr4[1] = 26;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[0] = 6;
        iArr6[1] = 26;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[0] = 6;
        iArr8[1] = 26;
        iArr8[2] = 1;
        iArr7[3] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[0] = 6;
        iArr10[1] = 26;
        iArr10[2] = 1;
        iArr9[4] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[0] = 6;
        iArr12[1] = 26;
        iArr12[2] = 1;
        iArr11[5] = iArr12;
        this.mAmpData = new CanDataInfo.HyundaiWcAmpData();
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiWcGetAmpSet(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(new int[]{this.mAmpData.Vol, this.mAmpData.Bal, this.mAmpData.Fad, this.mAmpData.Bas, this.mAmpData.Mid, this.mAmpData.Tre, this.mAmpData.Mute});
        }
    }

    public void QueryData() {
    }
}
