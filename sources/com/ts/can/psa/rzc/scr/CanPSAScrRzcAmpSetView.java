package com.ts.can.psa.rzc.scr;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemProgressList;

public class CanPSAScrRzcAmpSetView extends CanScrollCarInfoView {
    private static final String AMP_DATA = "amp_data";
    private int[] mData;
    private SharedPreferences mSp;

    public CanPSAScrRzcAmpSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (getValue(this.mData[0] & 128) != item) {
                    for (int i = 0; i < this.mData.length - 1; i++) {
                        this.mData[i] = item == 0 ? 7 : 137;
                    }
                    updateProgress(item);
                    updateData();
                    return;
                }
                return;
            case 6:
                this.mData[4] = (this.mData[4] & 128) + (item * 64);
                AmpSet();
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
            case 2:
            case 3:
            case 4:
                this.mData[id - 1] = (this.mData[id - 1] & 128) + pos;
                AmpSet();
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 5) {
            this.mData[4] = (this.mData[4] & 128) > 0 ? this.mData[4] - 128 : this.mData[4] + 128;
            AmpSet();
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_vol_low, R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_vol_high, R.string.can_psa_wc_ylssdtj, R.string.can_psa_wc_syfb};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.array.can_psa_scr_car_type};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 14;
        iArr2[2] = 1;
        iArr2[3] = 1;
        iArr[1] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 14;
        iArr4[2] = 1;
        iArr4[3] = 1;
        iArr3[2] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 14;
        iArr6[2] = 1;
        iArr6[3] = 1;
        iArr5[3] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[1] = 14;
        iArr8[2] = 1;
        iArr8[3] = 1;
        iArr7[4] = iArr8;
        this.mPopValueIds[6] = new int[]{R.string.can_driving_position, R.string.can_all_passengers};
        this.mData = new int[5];
        this.mSp = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    public void doOnResume() {
        for (int i = 0; i < this.mData.length; i++) {
            this.mData[i] = this.mSp.getInt(AMP_DATA + i, 0);
        }
        updateProgress(getValue(this.mData[0] & 128));
    }

    public void doOnPause() {
        SharedPreferences.Editor editor = this.mSp.edit();
        for (int i = 0; i < this.mData.length; i++) {
            editor.putInt(AMP_DATA + i, this.mData[i]);
        }
        editor.apply();
    }

    public void ResetData(boolean check) {
        if (!check) {
            updateData();
        }
    }

    public void QueryData() {
    }

    private void updateData() {
        updateItem(0, getValue(this.mData[0] & 128));
        updateItem(1, getCheckValue(this.mData[0]), getCheckValueStr(this.mData[0]));
        updateItem(2, getCheckValue(this.mData[1]), getCheckValueStr(this.mData[1]));
        updateItem(3, getCheckValue(this.mData[2]), getCheckValueStr(this.mData[2]));
        updateItem(4, getCheckValue(this.mData[3]), getCheckValueStr(this.mData[3]));
        updateItem(5, getValue(this.mData[4] & 128));
        updateItem(6, getValue(this.mData[4] & 64));
    }

    private int getValue(int val) {
        if (val > 0) {
            return 1;
        }
        return 0;
    }

    private int getCheckValue(int val) {
        if ((val & 128) > 0) {
            return val - 128;
        }
        return val;
    }

    private String getCheckValueStr(int val) {
        if ((val & 128) > 0) {
            return String.valueOf((val - 128) - 9);
        }
        return String.valueOf(val - 7);
    }

    private void updateProgress(int item) {
        int max = item == 0 ? 14 : 18;
        ((CanItemProgressList) this.mItemObjects[1]).SetMinMax(0, max);
        ((CanItemProgressList) this.mItemObjects[2]).SetMinMax(0, max);
        ((CanItemProgressList) this.mItemObjects[3]).SetMinMax(0, max);
        ((CanItemProgressList) this.mItemObjects[4]).SetMinMax(0, max);
    }

    private void AmpSet() {
        updateData();
        CanJni.PsaSrcRzcArmCtrl(19, this.mData[0], this.mData[1], this.mData[2], this.mData[3], this.mData[4]);
    }
}
