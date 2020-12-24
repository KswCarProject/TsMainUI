package com.ts.can.cc.dj.hf;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanHfDjAmpSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2 {
    private static final int ITEM_RESET_AMP = 8;
    private CanDataInfo.CcHfDj_AmpInfo mAmpInfo;

    public CanHfDjAmpSetView(Activity activity) {
        super(activity, 9);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.CcHfDjAmpSet(6, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.CcHfDjAmpSet(8, pos);
                return;
            case 1:
                CanJni.CcHfDjAmpSet(5, pos);
                return;
            case 2:
                CanJni.CcHfDjAmpSet(4, pos);
                return;
            case 3:
                CanJni.CcHfDjAmpSet(3, pos);
                return;
            case 6:
                CanJni.CcHfDjAmpSet(1, pos);
                return;
            case 7:
                CanJni.CcHfDjAmpSet(2, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 5:
                CanJni.CcHfDjAmpSet(7, Neg(this.mAmpInfo.Surround));
                return;
            case 8:
                new CanItemMsgBox(8, getActivity(), R.string.can_cmp_reset_notice, this, this);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_a_s_l, R.string.can_vol_around, R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_czgx};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.TITLE};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 39;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 20;
        iArr4[2] = 1;
        iArr4[3] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 20;
        iArr6[2] = 1;
        iArr6[3] = 1;
        iArr5[2] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[1] = 20;
        iArr8[2] = 1;
        iArr8[3] = 1;
        iArr7[3] = iArr8;
        this.mPopValueIds[4] = new int[]{R.string.can_gl8_2017_close, R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[1] = 20;
        iArr10[2] = 1;
        iArr10[3] = 1;
        iArr9[6] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[1] = 20;
        iArr12[2] = 1;
        iArr12[3] = 1;
        iArr11[7] = iArr12;
        this.mAmpInfo = new CanDataInfo.CcHfDj_AmpInfo();
    }

    public void ResetData(boolean check) {
        CanJni.CcHfDjGetAmpInfo(this.mAmpInfo);
        if (!i2b(this.mAmpInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpInfo.Update)) {
            this.mAmpInfo.Update = 0;
            updateItem(0, this.mAmpInfo.Vol);
            updateItem(1, this.mAmpInfo.Bas, new StringBuilder(String.valueOf(this.mAmpInfo.Bas - 10)).toString());
            updateItem(2, this.mAmpInfo.Mid, new StringBuilder(String.valueOf(this.mAmpInfo.Mid - 10)).toString());
            updateItem(3, this.mAmpInfo.Tre, new StringBuilder(String.valueOf(this.mAmpInfo.Tre - 10)).toString());
            updateItem(4, this.mAmpInfo.Ats);
            updateItem(5, this.mAmpInfo.Surround);
            updateItem(6, this.mAmpInfo.Fad, new StringBuilder(String.valueOf(this.mAmpInfo.Fad - 10)).toString());
            updateItem(7, this.mAmpInfo.Bal, new StringBuilder(String.valueOf(this.mAmpInfo.Bal - 10)).toString());
        }
    }

    public void QueryData() {
        CanJni.CcHfDjQuery(49);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    public void onCancel(int param) {
    }

    public void onOK(int param) {
        if (param == 8) {
            CanJni.CcHfDjAmpSet(255, 0);
        }
    }
}
