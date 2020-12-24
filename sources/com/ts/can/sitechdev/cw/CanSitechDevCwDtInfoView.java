package com.ts.can.sitechdev.cw;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSitechDevCwDtInfoView extends CanScrollCarInfoView {
    public static final String TAG = "CanSitechDevCwDtInfoView";
    private CanDataInfo.SitechDev_Dt mDev_Dt;
    private String mExceptionStr;
    private String mInvalidStr;

    public CanSitechDevCwDtInfoView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mDev_Dt = new CanDataInfo.SitechDev_Dt();
        this.mInvalidStr = getActivity().getResources().getString(R.string.can_invalid);
        this.mExceptionStr = getActivity().getResources().getString(R.string.can_exception);
        this.mItemTitleIds = new int[]{R.string.can_dtzgdy, R.string.can_dtzgdyzh, R.string.can_dtzddy, R.string.can_dtzddyzh};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
    }

    public void ResetData(boolean check) {
        CanJni.SitechDevCwGetDtBatData(this.mDev_Dt);
        if (!i2b(this.mDev_Dt.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDev_Dt.Update)) {
            this.mDev_Dt.Update = 0;
            updateItem(0, 0, GetDtDy(this.mDev_Dt.Dtzgdy));
            updateItem(1, 0, GetDtDyZh(this.mDev_Dt.Dtzgdzzh));
            updateItem(2, 0, GetDtDy(this.mDev_Dt.Dtzddy));
            updateItem(3, 0, GetDtDyZh(this.mDev_Dt.Dtzddyzh));
        }
    }

    public String GetDtDy(int dy) {
        if (dy == 65535) {
            return this.mInvalidStr;
        }
        if (dy == 65534) {
            return this.mExceptionStr;
        }
        float value = (float) (((double) dy) * 0.001d);
        if (value < 0.0f || value > 15.0f) {
            return "-- V";
        }
        if (value == 0.0f) {
            return "0 V";
        }
        return String.valueOf(String.format("%.3f", new Object[]{Float.valueOf(value)})) + " V";
    }

    public String GetDtDyZh(int dyzh) {
        if (dyzh == 255) {
            return this.mInvalidStr;
        }
        if (dyzh == 254) {
            return this.mExceptionStr;
        }
        if (dyzh < 1 || dyzh > 250) {
            return "--";
        }
        return new StringBuilder(String.valueOf(dyzh)).toString();
    }

    public void QueryData() {
    }
}
