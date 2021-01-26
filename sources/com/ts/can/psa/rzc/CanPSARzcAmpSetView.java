package com.ts.can.psa.rzc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.can.MyApplication;

public class CanPSARzcAmpSetView extends CanScrollCarInfoView {
    private static final int ITEM_AMP_ASL = 12;
    private static final int ITEM_AMP_BAL1 = 3;
    private static final int ITEM_AMP_BAL2 = 4;
    private static final int ITEM_AMP_BASS1 = 5;
    private static final int ITEM_AMP_BASS2 = 6;
    private static final int ITEM_AMP_CENTRIK = 14;
    private static final int ITEM_AMP_CHK = 254;
    private static final int ITEM_AMP_FAD1 = 1;
    private static final int ITEM_AMP_FAD2 = 2;
    private static final int ITEM_AMP_LOUDNESS = 11;
    private static final int ITEM_AMP_MID1 = 9;
    private static final int ITEM_AMP_MID2 = 10;
    private static final int ITEM_AMP_SET = 0;
    private static final int ITEM_AMP_STYLE = 13;
    private static final int ITEM_AMP_SUBWOOFER = 15;
    private static final int ITEM_AMP_TRE1 = 7;
    private static final int ITEM_AMP_TRE2 = 8;
    private static String[] mAmpStr;
    public static SharedPreferences mSharedPreferences = MyApplication.mContext.getSharedPreferences("can_psa_rzc_amp_set", 0);

    public CanPSARzcAmpSetView(Activity activity) {
        super(activity, 16);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                setPrefrences("DATA0", item);
                break;
            case 13:
                setPrefrences("DATA13", item);
                sendCmd1();
                break;
        }
        ResetData(false);
    }

    public static void AmpInit() {
        if (getPrefrences("DATA254") != 85) {
            setPrefrences("DATA254", 85);
            setPrefrences("DATA0", 0);
            setPrefrences("DATA1", 7);
            setPrefrences("DATA2", 9);
            setPrefrences("DATA3", 7);
            setPrefrences("DATA4", 9);
            setPrefrences("DATA5", 7);
            setPrefrences("DATA6", 9);
            setPrefrences("DATA7", 7);
            setPrefrences("DATA8", 9);
            setPrefrences("DATA9", 7);
            setPrefrences("DATA10", 9);
            setPrefrences("DATA11", 0);
            setPrefrences("DATA12", 0);
            setPrefrences("DATA13", 0);
            setPrefrences("DATA14", 0);
            setPrefrences("DATA15", 3);
        }
        sendCmd1();
        sendCmd2();
    }

    private static void sendCmd1() {
        CanJni.PsaRzcAmpCmd(getPrefrences("DATA1"), getPrefrences("DATA3"), getPrefrences("DATA5"), getPrefrences("DATA7"), getPrefrences("DATA9"), (getPrefrences("DATA11") & 128) + (getPrefrences("DATA12") & 64) + (getPrefrences("DATA13") & 15), (getPrefrences("DATA14") & 128) + (getPrefrences("DATA15") & 6), 1);
    }

    private static void sendCmd2() {
        CanJni.PsaRzcAmpCmd(getPrefrences("DATA2"), getPrefrences("DATA4"), getPrefrences("DATA6"), getPrefrences("DATA8"), getPrefrences("DATA10"), (getPrefrences("DATA11") & 128) + (getPrefrences("DATA12") & 64) + (getPrefrences("DATA13") & 15), (getPrefrences("DATA14") & 128) + (getPrefrences("DATA15") & 6), 2);
    }

    private String setValFadBalText(int val, int sta) {
        switch (sta) {
            case 1:
                if (val == 7) {
                    return "0";
                }
                if (val < 7) {
                    return "F" + String.valueOf(7 - val);
                }
                if (val > 7) {
                    return "R" + String.valueOf(val - 7);
                }
                break;
            case 2:
                if (val == 9) {
                    return "0";
                }
                if (val < 9) {
                    return "F" + String.valueOf(9 - val);
                }
                if (val > 9) {
                    return "R" + String.valueOf(val - 9);
                }
                break;
            case 3:
                if (val == 7) {
                    return "0";
                }
                if (val < 7) {
                    return "L" + String.valueOf(7 - val);
                }
                if (val > 7) {
                    return "R" + String.valueOf(val - 7);
                }
                break;
            case 4:
                if (val == 9) {
                    return "0";
                }
                if (val < 9) {
                    return "L" + String.valueOf(9 - val);
                }
                if (val > 9) {
                    return "R" + String.valueOf(val - 9);
                }
                break;
        }
        return "0";
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                setPrefrences("DATA1", pos);
                sendCmd1();
                break;
            case 2:
                setPrefrences("DATA2", pos);
                sendCmd2();
                break;
            case 3:
                setPrefrences("DATA3", pos);
                sendCmd1();
                break;
            case 4:
                setPrefrences("DATA4", pos);
                sendCmd2();
                break;
            case 5:
                setPrefrences("DATA5", pos);
                sendCmd1();
                break;
            case 6:
                setPrefrences("DATA6", pos);
                sendCmd2();
                break;
            case 7:
                setPrefrences("DATA7", pos);
                sendCmd1();
                break;
            case 8:
                setPrefrences("DATA8", pos);
                sendCmd2();
                break;
            case 9:
                setPrefrences("DATA9", pos);
                sendCmd1();
                break;
            case 10:
                setPrefrences("DATA10", pos);
                sendCmd2();
                break;
            case 15:
                setPrefrences("DATA15", pos);
                sendCmd1();
                break;
        }
        ResetData(false);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 11:
                setPrefrences("DATA11", Neg(getPrefrences("DATA11")));
                sendCmd1();
                break;
            case 12:
                setPrefrences("DATA12", Neg(getPrefrences("DATA12")));
                sendCmd1();
                break;
            case 14:
                setPrefrences("DATA14", Neg(getPrefrences("DATA14")));
                sendCmd1();
                break;
        }
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_gf_toggle, R.string.can_balance_left_right, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_low, R.string.can_vol_high, R.string.can_vol_high, R.string.can_vol_middle, R.string.can_vol_middle, R.string.can_amp_dxd, R.string.can_apply_speed, R.string.can_amp_musicstyle, R.string.can_zyysq, R.string.can_subwoofer};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 14;
        iArr2[2] = 1;
        iArr[1] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 14;
        iArr4[2] = 1;
        iArr3[3] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 14;
        iArr6[2] = 1;
        iArr5[5] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[1] = 14;
        iArr8[2] = 1;
        iArr7[7] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[1] = 14;
        iArr10[2] = 1;
        iArr9[9] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[1] = 18;
        iArr12[2] = 1;
        iArr11[2] = iArr12;
        int[][] iArr13 = this.mProgressAttrs;
        int[] iArr14 = new int[4];
        iArr14[1] = 18;
        iArr14[2] = 1;
        iArr13[4] = iArr14;
        int[][] iArr15 = this.mProgressAttrs;
        int[] iArr16 = new int[4];
        iArr16[1] = 18;
        iArr16[2] = 1;
        iArr15[6] = iArr16;
        int[][] iArr17 = this.mProgressAttrs;
        int[] iArr18 = new int[4];
        iArr18[1] = 18;
        iArr18[2] = 1;
        iArr17[8] = iArr18;
        int[][] iArr19 = this.mProgressAttrs;
        int[] iArr20 = new int[4];
        iArr20[1] = 18;
        iArr20[2] = 1;
        iArr19[10] = iArr20;
        int[][] iArr21 = this.mProgressAttrs;
        int[] iArr22 = new int[4];
        iArr22[1] = 6;
        iArr22[2] = 1;
        iArr21[15] = iArr22;
        this.mPopValueIds[13] = new int[]{R.array.can_psarzc_musicstyle};
        this.mPopValueIds[0] = new int[]{R.array.can_psarzc_musicstyle};
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        mAmpStr = new String[]{String.valueOf(getString(R.string.can_amp_set)) + "1", String.valueOf(getString(R.string.can_amp_set)) + "2"};
        getScrollManager().RemoveView(0);
        this.mItemObjects[0] = getScrollManager().addItemPopupList(this.mItemTitleIds[0], mAmpStr, 0, this, 0);
        AmpInit();
    }

    public void ResetData(boolean check) {
        if (!check) {
            updateItem(0, getPrefrences("DATA0"));
            updateItem(1, getPrefrences("DATA1"), setValFadBalText(getPrefrences("DATA1"), 1));
            updateItem(3, getPrefrences("DATA3"), setValFadBalText(getPrefrences("DATA3"), 3));
            updateItem(2, getPrefrences("DATA2"), setValFadBalText(getPrefrences("DATA2"), 2));
            updateItem(4, getPrefrences("DATA4"), setValFadBalText(getPrefrences("DATA4"), 4));
            updateItem(5, getPrefrences("DATA5"), String.format("%d", new Object[]{Integer.valueOf(getPrefrences("DATA5") - 7)}));
            updateItem(6, getPrefrences("DATA6"), String.format("%d", new Object[]{Integer.valueOf(getPrefrences("DATA6") - 9)}));
            updateItem(7, getPrefrences("DATA7"), String.format("%d", new Object[]{Integer.valueOf(getPrefrences("DATA7") - 7)}));
            updateItem(8, getPrefrences("DATA8"), String.format("%d", new Object[]{Integer.valueOf(getPrefrences("DATA8") - 9)}));
            updateItem(9, getPrefrences("DATA9"), String.format("%d", new Object[]{Integer.valueOf(getPrefrences("DATA9") - 7)}));
            updateItem(10, getPrefrences("DATA10"), String.format("%d", new Object[]{Integer.valueOf(getPrefrences("DATA10") - 9)}));
            updateItem(11, getPrefrences("DATA11"));
            updateItem(12, getPrefrences("DATA12"));
            updateItem(13, getPrefrences("DATA13"));
            updateItem(14, getPrefrences("DATA14"));
            updateItem(15, getPrefrences("DATA15"), String.format("%d", new Object[]{Integer.valueOf(getPrefrences("DATA15") - 3)}));
            if (i2b(getPrefrences("DATA0"))) {
                showItem(1, 0);
                showItem(3, 0);
                showItem(5, 0);
                showItem(7, 0);
                showItem(9, 0);
                showItem(2, 1);
                showItem(4, 1);
                showItem(6, 1);
                showItem(8, 1);
                showItem(10, 1);
                return;
            }
            showItem(1, 1);
            showItem(3, 1);
            showItem(5, 1);
            showItem(7, 1);
            showItem(9, 1);
            showItem(2, 0);
            showItem(4, 0);
            showItem(6, 0);
            showItem(8, 0);
            showItem(10, 0);
        }
    }

    public void QueryData() {
    }

    public static void setPrefrences(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getPrefrences(String key) {
        return mSharedPreferences.getInt(key, 0);
    }
}
