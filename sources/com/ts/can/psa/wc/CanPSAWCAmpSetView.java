package com.ts.can.psa.wc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.can.MyApplication;

public class CanPSAWCAmpSetView extends CanScrollCarInfoView {
    private static int count = 0;
    private static long delayTime = 0;
    public static SharedPreferences mSharedPreferences = MyApplication.mContext.getSharedPreferences("can_psa_wc_amp_set", 0);

    public CanPSAWCAmpSetView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.PsaWcAmpSet(11, item);
                setPrefrences("0x0B", item);
                break;
            case 5:
                CanJni.PsaWcAmpSet(14, item);
                setPrefrences("0x0E", item);
                break;
        }
        ResetData(false);
    }

    public static void updateAmpSet() {
        long curTime = System.currentTimeMillis();
        if (curTime - delayTime > 700) {
            delayTime = curTime;
            switch (count) {
                case 0:
                    CanJni.PsaWcAmpSet(2, getPrefrences("0x02") + 7);
                    break;
                case 1:
                    CanJni.PsaWcAmpSet(3, getPrefrences("0x03") + 7);
                    break;
                case 2:
                    CanJni.PsaWcAmpSet(11, getPrefrences("0x0B"));
                    break;
                case 3:
                    CanJni.PsaWcAmpSet(12, getPrefrences("0x0C"));
                    break;
                case 4:
                    CanJni.PsaWcAmpSet(13, getPrefrences("0x0D"));
                    break;
                case 5:
                    CanJni.PsaWcAmpSet(14, getPrefrences("0x0E"));
                    break;
                case 6:
                    CanJni.PsaWcAmpSet(15, getPrefrences("0x0F") + 7);
                    break;
                case 7:
                    CanJni.PsaWcAmpSet(16, getPrefrences("0x10") + 7);
                    break;
            }
            count++;
            if (count >= 7) {
                count = 0;
            }
        }
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

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                int pos2 = updatePos("0x02", pos);
                CanJni.PsaWcAmpSet(2, pos2 + 7);
                updateItem(0, pos2);
                setPrefrences("0x02", pos2);
                break;
            case 1:
                int pos3 = updatePos("0x03", pos);
                CanJni.PsaWcAmpSet(3, pos3 + 7);
                updateItem(1, pos3);
                setPrefrences("0x03", pos3);
                break;
            case 6:
                int pos4 = updatePos("0x0F", pos);
                CanJni.PsaWcAmpSet(15, pos4 + 7);
                updateItem(6, pos4);
                setPrefrences("0x0F", pos4);
                break;
            case 7:
                int pos5 = updatePos("0x10", pos);
                CanJni.PsaWcAmpSet(16, pos5 + 7);
                updateItem(7, pos5);
                setPrefrences("0x10", pos5);
                break;
        }
        ResetData(false);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                int value = Neg(getPrefrences("0x0C"));
                CanJni.PsaWcAmpSet(12, value);
                setPrefrences("0x0C", value);
                break;
            case 4:
                int value1 = Neg(getPrefrences("0x0D"));
                CanJni.PsaWcAmpSet(13, value1);
                setPrefrences("0x0D", value1);
                break;
        }
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_psa_wc_balance_cmd, R.string.can_psa_wc_fade_cmd, R.string.can_psa_wc_syfb, R.string.can_psa_wc_xd, R.string.can_psa_wc_ylssdtj, R.string.can_psa_wc_yxxz, R.string.can_vol_high, R.string.can_vol_low};
        this.mPopValueIds[2] = new int[]{R.array.can_psa_wc_syfb_array};
        this.mPopValueIds[5] = new int[]{R.array.can_psa_wc_yxxz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = -7;
        iArr2[1] = 7;
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
        iArr5[6] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[0] = -7;
        iArr8[1] = 7;
        iArr8[2] = 1;
        iArr7[7] = iArr8;
    }

    public void ResetData(boolean check) {
        if (!check) {
            updateItem(0, getPrefrences("0x02"));
            updateItem(1, getPrefrences("0x03"));
            updateItem(2, getPrefrences("0x0B"));
            updateItem(3, getPrefrences("0x0C"));
            updateItem(4, getPrefrences("0x0D"));
            updateItem(5, getPrefrences("0x0E"));
            updateItem(6, getPrefrences("0x0F"));
            updateItem(7, getPrefrences("0x10"));
            if (getPrefrences("0x0E") == 5) {
                showItem(6, 1);
                showItem(7, 1);
                return;
            }
            showItem(6, 0);
            showItem(7, 0);
        }
    }

    public void QueryData() {
    }

    public void setPrefrences(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getPrefrences(String key) {
        return mSharedPreferences.getInt(key, 0);
    }
}
