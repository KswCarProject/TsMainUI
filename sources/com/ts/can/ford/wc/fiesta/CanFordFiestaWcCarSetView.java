package com.ts.can.ford.wc.fiesta;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.main.common.MainSet;

public class CanFordFiestaWcCarSetView extends CanScrollCarInfoView {
    public static SharedPreferences mSharedPreferences;
    int[] mValues;

    public CanFordFiestaWcCarSetView(Activity activity) {
        super(activity, 5);
    }

    /* access modifiers changed from: package-private */
    public int count(int[] values) {
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            count += values[i] << i;
        }
        return count;
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                this.mValues[0] = item;
                CanJni.FordOldFiestaWcCarSet(count(this.mValues), 0, 0, 0);
                setPrefrences("0", this.mValues[0]);
                break;
            case 3:
                this.mValues[3] = item;
                CanJni.FordOldFiestaWcCarSet(count(this.mValues), 0, 0, 0);
                setPrefrences("3", this.mValues[3]);
                break;
        }
        ResetData(false);
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                this.mValues[1] = Neg(this.mValues[1]);
                CanJni.FordOldFiestaWcCarSet(count(this.mValues), 0, 0, 0);
                setPrefrences("1", this.mValues[1]);
                break;
            case 2:
                this.mValues[2] = Neg(this.mValues[2]);
                CanJni.FordOldFiestaWcCarSet(count(this.mValues), 0, 0, 0);
                setPrefrences("2", this.mValues[2]);
                break;
            case 4:
                this.mValues[4] = Neg(this.mValues[4]);
                CanJni.FordOldFiestaWcCarSet(count(this.mValues), 0, 0, 0);
                setPrefrences(MainSet.SP_KS_QOROS, this.mValues[4]);
                break;
        }
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        Activity activity = getActivity();
        getActivity();
        mSharedPreferences = activity.getSharedPreferences("can_ford_fiesta_wc_car_set", 0);
        this.mValues = new int[5];
        this.mItemTitleIds = new int[]{R.string.can_zxdsszs, R.string.can_warn_tip, R.string.can_msg_tip, R.string.can_dlunits, R.string.can_environment_light};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK};
        this.mPopValueIds[0] = new int[]{R.array.can_fist_zxd};
        this.mPopValueIds[3] = new int[]{R.array.can_fist_l_c};
        for (int i = 0; i < this.mValues.length; i++) {
            this.mValues[i] = getPrefrences(new StringBuilder(String.valueOf(i)).toString());
        }
    }

    public void ResetData(boolean check) {
        if (!check) {
            for (int i = 0; i < this.mValues.length; i++) {
                updateItem(i, this.mValues[i]);
            }
        }
    }

    public void QueryData() {
    }

    public void setPrefrences(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getPrefrences(String key) {
        return mSharedPreferences.getInt(key, 0);
    }
}
