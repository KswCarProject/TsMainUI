package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanHondaDaRzcAmpSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_AMP_BAL = 1;
    public static final int ITEM_AMP_BAS = 4;
    public static final int ITEM_AMP_CSLD = 6;
    public static final int ITEM_AMP_DEFAULT = 9;
    public static final int ITEM_AMP_DTS = 7;
    public static final int ITEM_AMP_FAD = 0;
    public static final int ITEM_AMP_MID = 3;
    public static final int ITEM_AMP_SUBWOF = 5;
    public static final int ITEM_AMP_TRE = 2;
    public static final int ITEM_AMP_VOL = 8;
    private CanDataInfo.HondaAmpData mAmpData;

    public CanHondaDaRzcAmpSetView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 6:
                CanJni.HondaDaSetAmp(7, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.HondaDaSetAmp(1, pos);
                return;
            case 1:
                CanJni.HondaDaSetAmp(2, pos);
                return;
            case 2:
                CanJni.HondaDaSetAmp(3, pos);
                return;
            case 3:
                CanJni.HondaDaSetAmp(4, pos);
                return;
            case 4:
                CanJni.HondaDaSetAmp(5, pos);
                return;
            case 5:
                CanJni.HondaDaSetAmp(6, pos);
                return;
            case 8:
                CanJni.HondaDaSetAmp(9, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 7:
                CanJni.HondaDaSetAmp(8, Neg(this.mAmpData.Dts));
                return;
            case 9:
                new CanItemMsgBox(9, getActivity(), R.string.can_cmp_reset_notice, this);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_vol_high, R.string.can_vol_middle, R.string.can_vol_low, R.string.can_subwof, R.string.can_apply_speed, R.string.can_dts, R.string.can_vol, R.string.can_rpa_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.TITLE};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 18;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 18;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 12;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[1] = 12;
        iArr8[2] = 1;
        iArr7[3] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[1] = 12;
        iArr10[2] = 1;
        iArr9[4] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[1] = 12;
        iArr12[2] = 1;
        iArr11[5] = iArr12;
        this.mPopValueIds[6] = new int[]{R.string.can_Scsfctsy_3, R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
        int[][] iArr13 = this.mProgressAttrs;
        int[] iArr14 = new int[4];
        iArr14[1] = 40;
        iArr14[2] = 1;
        iArr13[8] = iArr14;
        this.mAmpData = new CanDataInfo.HondaAmpData();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    public void ResetData(boolean check) {
        CanJni.HondaDaGetAmp(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vol);
            updateItem(1, this.mAmpData.Tre, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Tre - 9)}));
            updateItem(2, this.mAmpData.Mid, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Mid - 9)}));
            updateItem(3, this.mAmpData.Bas, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bas - 9)}));
            updateItem(0, this.mAmpData.Fad, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Fad - 9)}));
            updateItem(1, this.mAmpData.Bal, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bal - 9)}));
            updateItem(2, this.mAmpData.Tre, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Tre - 6)}));
            updateItem(3, this.mAmpData.Mid, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Mid - 6)}));
            updateItem(4, this.mAmpData.Bas, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bas - 6)}));
            updateItem(5, this.mAmpData.Subwof, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Subwof - 6)}));
            updateItem(6, this.mAmpData.Csld);
            updateItem(7, this.mAmpData.Dts);
            updateItem(8, this.mAmpData.Vol, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Vol)}));
        }
    }

    public void QueryData() {
        CanJni.HondaDAQuery(49, 0);
    }

    public void onOK(int param) {
        switch (param) {
            case 9:
                CanJni.HondaDaSetAmp(10, 0);
                return;
            default:
                return;
        }
    }
}
