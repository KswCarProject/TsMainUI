package com.ts.can.chrysler.txb;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.can.MyApplication;
import com.ts.canview.CanItemMsgBox;

public class CanChryslerTxbAmpSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2 {
    private static final int ITEM_AMP_BAL = 2;
    private static final int ITEM_AMP_BASS = 3;
    private static final int ITEM_AMP_CHK = 254;
    private static final int ITEM_AMP_FADE = 1;
    private static final int ITEM_AMP_INIT = 6;
    private static final int ITEM_AMP_MAX = 7;
    private static final int ITEM_AMP_MID = 5;
    private static final int ITEM_AMP_TRE = 4;
    private static final int ITEM_AMP_VOL = 0;
    public static SharedPreferences mSharedPreferences = MyApplication.mContext.getSharedPreferences("can_chrysler_txb_amp_set", 0);

    public CanChryslerTxbAmpSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
    }

    public static void AmpInit() {
        if (getPrefrences("DATA254") != 85) {
            setPrefrences("DATA254", 85);
            setPrefrences("DATA0", 20);
            setPrefrences("DATA1", 10);
            setPrefrences("DATA2", 10);
            setPrefrences("DATA3", 10);
            setPrefrences("DATA4", 10);
            setPrefrences("DATA5", 10);
        }
        CanJni.ChryslerTxbCarAmpSet(2, getPrefrences("DATA0"));
        CanJni.ChryslerTxbCarAmpSet(3, getPrefrences("DATA1"));
        CanJni.ChryslerTxbCarAmpSet(4, getPrefrences("DATA2"));
        CanJni.ChryslerTxbCarAmpSet(5, getPrefrences("DATA3"));
        CanJni.ChryslerTxbCarAmpSet(6, getPrefrences("DATA4"));
        CanJni.ChryslerTxbCarAmpSet(7, getPrefrences("DATA5"));
    }

    /* access modifiers changed from: package-private */
    public int updatePos(String key, int pos) {
        int curPos = getPrefrences(key);
        if (pos > curPos) {
            return curPos + 1;
        }
        if (pos < curPos) {
            return curPos - 1;
        }
        return curPos;
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

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.ChryslerTxbCarAmpSet(2, pos);
                updateItem(0, pos);
                setPrefrences("DATA0", pos);
                break;
            case 1:
                CanJni.ChryslerTxbCarAmpSet(3, pos);
                updateItem(1, pos, setValText(pos, 1));
                setPrefrences("DATA1", pos);
                break;
            case 2:
                CanJni.ChryslerTxbCarAmpSet(4, pos);
                updateItem(2, pos, setValText(pos, 2));
                setPrefrences("DATA2", pos);
                break;
            case 3:
                CanJni.ChryslerTxbCarAmpSet(5, pos);
                updateItem(3, pos);
                setPrefrences("DATA3", pos);
                break;
            case 4:
                CanJni.ChryslerTxbCarAmpSet(6, pos);
                updateItem(4, pos);
                setPrefrences("DATA4", pos);
                break;
            case 5:
                CanJni.ChryslerTxbCarAmpSet(7, pos);
                updateItem(5, pos);
                setPrefrences("DATA5", pos);
                break;
        }
        ResetData(false);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 6:
                new CanItemMsgBox(6, getActivity(), R.string.can_sure_setup, this, this);
                break;
        }
        ResetData(false);
    }

    public void onOK(int param) {
        if (param == 6) {
            CanJni.ChryslerTxbCarAmpSet(8, 170);
        }
    }

    public void onCancel(int param) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_high, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_factory_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.TITLE};
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
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[0] = 1;
        iArr6[1] = 19;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
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
        iArr9[4] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[0] = 1;
        iArr12[1] = 19;
        iArr12[2] = 1;
        iArr11[5] = iArr12;
        this.mItemVisibles[6] = 0;
    }

    public void ResetData(boolean check) {
        if (!check) {
            updateItem(0, getPrefrences("DATA0"));
            updateItem(1, getPrefrences("DATA1"), setValText(getPrefrences("DATA1"), 1));
            updateItem(2, getPrefrences("DATA2"), setValText(getPrefrences("DATA2"), 2));
            updateItem(3, getPrefrences("DATA3"));
            updateItem(4, getPrefrences("DATA4"));
            updateItem(5, getPrefrences("DATA5"));
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
