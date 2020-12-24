package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordRzcAmpSetView extends CanScrollCarInfoView {
    protected static final int ITEM_EQ = 7;
    protected static final int ITEM_SPEED_VOLUME_ADJUSTMENT = 6;
    protected static final int ITEM_VOL_ATTENUATION = 4;
    protected static final int ITEM_VOL_BALANCE = 5;
    protected static final int ITEM_VOL_HIGH = 1;
    protected static final int ITEM_VOL_LOW = 3;
    protected static final int ITEM_VOL_MID = 2;
    protected static final int ITEM_VOL_VALUE = 0;
    private CanDataInfo.FordRzcAmpData mAmpData;

    public CanFordRzcAmpSetView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
        if (id == 6) {
            CanJni.FordRzcAmpSet(5, item);
        } else if (id == 7) {
            CanJni.FordRzcAmpSet(6, item);
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.FordRzcAmpSet(0, pos + 7);
                return;
            case 2:
                CanJni.FordRzcAmpSet(1, pos + 7);
                return;
            case 3:
                CanJni.FordRzcAmpSet(2, pos + 7);
                return;
            case 4:
                CanJni.FordRzcAmpSet(3, pos + 7);
                return;
            case 5:
                CanJni.FordRzcAmpSet(4, pos + 7);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_vol_high, R.string.can_vol_middle, R.string.can_vol_low, R.string.can_vol_attenuation, R.string.can_vol_balance, R.string.can_a_s_l, R.string.can_eq};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[6] = new int[]{R.string.can_trunk_close, R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
        this.mPopValueIds[7] = new int[]{R.string.can_cch9_surround_sound_key1, R.string.can_cch9_surround_sound_key2};
        this.mAmpData = new CanDataInfo.FordRzcAmpData();
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 30;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = -7;
        iArr4[1] = 7;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[0] = -7;
        iArr6[1] = 7;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[0] = -7;
        iArr8[1] = 7;
        iArr8[2] = 1;
        iArr7[3] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[0] = -7;
        iArr10[1] = 7;
        iArr10[2] = 1;
        iArr9[4] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[0] = -7;
        iArr12[1] = 7;
        iArr12[2] = 1;
        iArr11[5] = iArr12;
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetAmpSet(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vol);
            updateItem(1, this.mAmpData.Tre - 7);
            updateItem(2, this.mAmpData.Mid - 7);
            updateItem(3, this.mAmpData.Bass - 7);
            updateItem(4, this.mAmpData.Atten - 7);
            updateItem(5, this.mAmpData.Bal - 7);
            updateItem(6, this.mAmpData.VolWithSpeed);
            updateItem(7, this.mAmpData.EQ);
        }
    }

    public void QueryData() {
        CanJni.FordQuery(98, 0);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }
}
