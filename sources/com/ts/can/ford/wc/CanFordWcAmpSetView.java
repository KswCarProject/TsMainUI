package com.ts.can.ford.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanFordWcAmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.FordWcAmpData mAmpData;

    public CanFordWcAmpSetView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 6:
                CanJni.FordWcCarAmpSet(7, item);
                return;
            case 7:
                CanJni.FordWcCarAmpSet(8, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        CanJni.FordWcCarAmpSet(id + 1, pos);
    }

    public void onClick(View v) {
        new CanItemMsgBox(((Integer) v.getTag()).intValue(), getActivity(), R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
            public void onOK(int param) {
                if (param == 8) {
                    CanJni.FordWcCarAmpSet(10, 1);
                } else if (param == 9) {
                    CanJni.FordWcCarAmpSet(11, 1);
                }
            }
        }, (CanItemMsgBox.onMsgBoxClick2) null);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_a_s_l, R.string.can_around, R.string.can_reset_balance, R.string.can_reset_all};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 30;
        iArr2[2] = 1;
        iArr[0] = iArr2;
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
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[1] = 14;
        iArr8[2] = 1;
        iArr7[3] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[1] = 14;
        iArr10[2] = 1;
        iArr9[4] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[1] = 14;
        iArr12[2] = 1;
        iArr11[5] = iArr12;
        this.mPopValueIds[6] = new int[]{R.string.can_off, R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[7] = new int[]{R.string.can_h6_coupe_lt, R.string.can_h6_coupe_hr};
        this.mAmpData = new CanDataInfo.FordWcAmpData();
    }

    public void ResetData(boolean check) {
        CanJni.FordWcGetAmpSet(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(new int[]{this.mAmpData.Vol, this.mAmpData.Bal, this.mAmpData.Fad, this.mAmpData.Bas, this.mAmpData.Mid, this.mAmpData.Tre, this.mAmpData.Scv, this.mAmpData.SoundMode});
        }
    }

    public void QueryData() {
        CanJni.FordWcQuery(5, 1, 166);
    }
}
