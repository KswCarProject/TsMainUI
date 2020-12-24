package com.ts.can.ford.f150;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordF150CarSetView extends CanScrollCarInfoView {
    private CanDataInfo.FordCarSet2 mCarSet;

    public CanFordF150CarSetView(Activity activity) {
        super(activity, 18);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.FordCarSet(177, item);
                return;
            case 1:
                CanJni.FordCarSet(178, item);
                return;
            case 2:
                CanJni.FordCarSet(179, item);
                return;
            case 6:
                CanJni.FordCarSet(183, item);
                return;
            case 7:
                CanJni.FordCarSet(184, item);
                return;
            case 16:
                CanJni.FordCarSet(197, item + 1);
                return;
            case 17:
                CanJni.FordCarSet(198, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 3:
                CanJni.FordCarSet(180, checkVal(pos, this.mCarSet.Jsczytj_sb));
                return;
            case 4:
                CanJni.FordCarSet(181, checkVal(pos, this.mCarSet.Jsczytj_zm));
                return;
            case 5:
                CanJni.FordCarSet(182, checkVal(pos, this.mCarSet.Jsczytj_xb));
                return;
            case 8:
                CanJni.FordCarSet(185, checkVal(pos, this.mCarSet.Ckczytj_sb));
                return;
            case 9:
                CanJni.FordCarSet(186, checkVal(pos, this.mCarSet.Ckczytj_zb));
                return;
            case 10:
                CanJni.FordCarSet(187, checkVal(pos, this.mCarSet.Ckczytj_xb));
                return;
            case 11:
                CanJni.FordCarSet(192, pos);
                return;
            case 12:
                CanJni.FordCarSet(193, pos);
                return;
            case 13:
                CanJni.FordCarSet(194, pos);
                return;
            case 14:
                CanJni.FordCarSet(195, pos);
                return;
            case 15:
                CanJni.FordCarSet(196, pos);
                return;
            default:
                return;
        }
    }

    private int checkVal(int pos, int val) {
        if (pos == val) {
            return 0;
        }
        if (pos - val > 0) {
            return 2;
        }
        return 1;
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_fwd_color, R.string.can_jscam_kb, R.string.can_jscam_zd, R.string.can_jsczytj_sb, R.string.can_jsczytj_zb, R.string.can_jsczytj_xb, R.string.can_ckcam_kb, R.string.can_ckcam_zd, R.string.can_ckczytj_sb, R.string.can_ckczytj_zb, R.string.can_ckczytj_xb, R.string.can_vol_high, R.string.can_vol_middle, R.string.can_vol_low, R.string.can_cch9_balance_before_and_after, R.string.can_cch9_balance_left_and_right, R.string.can_cch9_with_the_volume, R.string.can_psa_wc_yxxz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_cold_blue, R.string.can_orange_color, R.string.can_soft_blue, R.string.can_color_red, R.string.can_magoten_light_color_2, R.string.can_magoten_light_color_3, R.string.can_purple};
        this.mPopValueIds[1] = new int[]{R.string.can_off, R.string.can_ac_low, R.string.can_ac_high};
        this.mPopValueIds[2] = new int[]{R.string.can_off, R.string.can_ac_low, R.string.can_ac_high};
        this.mPopValueIds[6] = new int[]{R.string.can_off, R.string.can_ac_low, R.string.can_ac_high};
        this.mPopValueIds[7] = new int[]{R.string.can_off, R.string.can_ac_low, R.string.can_ac_high};
        this.mPopValueIds[16] = new int[]{R.string.can_off, R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[17] = new int[]{R.string.can_h6_coupe_lt, R.string.can_h6_coupe_hr};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 1;
        iArr2[1] = 10;
        iArr2[2] = 1;
        iArr[3] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = 1;
        iArr4[1] = 10;
        iArr4[2] = 1;
        iArr3[4] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[0] = 1;
        iArr6[1] = 10;
        iArr6[2] = 1;
        iArr5[5] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[0] = 1;
        iArr8[1] = 10;
        iArr8[2] = 1;
        iArr7[8] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[0] = 1;
        iArr10[1] = 10;
        iArr10[2] = 1;
        iArr9[9] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[0] = 1;
        iArr12[1] = 10;
        iArr12[2] = 1;
        iArr11[10] = iArr12;
        int[][] iArr13 = this.mProgressAttrs;
        int[] iArr14 = new int[4];
        iArr14[1] = 14;
        iArr14[2] = 1;
        iArr14[3] = 1;
        iArr13[11] = iArr14;
        int[][] iArr15 = this.mProgressAttrs;
        int[] iArr16 = new int[4];
        iArr16[1] = 14;
        iArr16[2] = 1;
        iArr16[3] = 1;
        iArr15[12] = iArr16;
        int[][] iArr17 = this.mProgressAttrs;
        int[] iArr18 = new int[4];
        iArr18[1] = 14;
        iArr18[2] = 1;
        iArr18[3] = 1;
        iArr17[13] = iArr18;
        int[][] iArr19 = this.mProgressAttrs;
        int[] iArr20 = new int[4];
        iArr20[1] = 14;
        iArr20[2] = 1;
        iArr20[3] = 1;
        iArr19[14] = iArr20;
        int[][] iArr21 = this.mProgressAttrs;
        int[] iArr22 = new int[4];
        iArr22[1] = 14;
        iArr22[2] = 1;
        iArr22[3] = 1;
        iArr21[15] = iArr22;
        this.mCarSet = new CanDataInfo.FordCarSet2();
    }

    public void ResetData(boolean check) {
        CanJni.FordGetCarSet(this.mCarSet);
        if (!i2b(this.mCarSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSet.Update)) {
            this.mCarSet.Update = 0;
            updateItem(0, this.mCarSet.Fwdzt);
            updateItem(1, this.mCarSet.Jscam_kb);
            updateItem(2, this.mCarSet.Jscam_zd);
            if (this.mCarSet.Jsczytj_sb > 0 && this.mCarSet.Jsczytj_sb < 11) {
                updateItem(3, this.mCarSet.Jsczytj_sb);
            }
            if (this.mCarSet.Jsczytj_zm > 0 && this.mCarSet.Jsczytj_zm < 11) {
                updateItem(4, this.mCarSet.Jsczytj_zm);
            }
            if (this.mCarSet.Jsczytj_xb > 0 && this.mCarSet.Jsczytj_xb < 11) {
                updateItem(5, this.mCarSet.Jsczytj_xb);
            }
            updateItem(6, this.mCarSet.Ckcam_kb);
            updateItem(7, this.mCarSet.Ckcam_zd);
            if (this.mCarSet.Ckczytj_sb > 0 && this.mCarSet.Ckczytj_sb < 11) {
                updateItem(8, this.mCarSet.Ckczytj_sb);
            }
            if (this.mCarSet.Ckczytj_zb > 0 && this.mCarSet.Ckczytj_zb < 11) {
                updateItem(9, this.mCarSet.Ckczytj_zb);
            }
            if (this.mCarSet.Ckczytj_xb > 0 && this.mCarSet.Ckczytj_xb < 11) {
                updateItem(10, this.mCarSet.Ckczytj_xb);
            }
            updateItem(11, this.mCarSet.Tre, String.valueOf(this.mCarSet.Tre - 7));
            updateItem(12, this.mCarSet.Mid, String.valueOf(this.mCarSet.Mid - 7));
            updateItem(13, this.mCarSet.Bass, String.valueOf(this.mCarSet.Bass - 7));
            String unit = "";
            if (this.mCarSet.Fade - 7 > 0) {
                unit = "R";
            } else if (this.mCarSet.Fade - 7 < 0) {
                unit = "F";
            }
            updateItem(14, this.mCarSet.Fade, String.valueOf(unit) + String.valueOf(this.mCarSet.Fade - 7));
            String unit2 = "";
            if (this.mCarSet.Bal - 7 > 0) {
                unit2 = "R";
            } else if (this.mCarSet.Bal - 7 < 0) {
                unit2 = "L";
            }
            updateItem(15, this.mCarSet.Bal, String.valueOf(unit2) + String.valueOf(this.mCarSet.Bal - 7));
            updateItem(16, this.mCarSet.Scv);
            updateItem(17, this.mCarSet.SoundMode);
        }
    }

    public void QueryData() {
    }
}
