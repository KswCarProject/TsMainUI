package com.ts.can.sitechdev.cw;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSitechDevCwBatteryInfoView extends CanScrollCarInfoView {
    public static final String TAG = "CanSitechDevCwBatteryInfoView";
    private String[] mCdztStrs;
    private CanDataInfo.SitechDev_Battery mDev_Battery;
    private CanDataInfo.SitechDev_BmsWarn mDev_BmsWarn;
    private String mExceptionStr;
    private String mInvalidStr;

    public CanSitechDevCwBatteryInfoView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mDev_BmsWarn = new CanDataInfo.SitechDev_BmsWarn();
        this.mDev_Battery = new CanDataInfo.SitechDev_Battery();
        Resources resources = getActivity().getResources();
        this.mInvalidStr = resources.getString(R.string.can_invalid);
        this.mExceptionStr = resources.getString(R.string.can_exception);
        this.mCdztStrs = resources.getStringArray(R.array.can_cdzt_arrays);
        this.mItemTitleIds = new int[]{R.string.can_cdzt, R.string.can_dczzdy_v, R.string.can_dczzdl_a, R.string.can_dczsoc, R.string.can_dczzgwd, R.string.can_dczzgwdzh, R.string.can_dczzdwd, R.string.can_dczzdwdzh};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
    }

    public void ResetData(boolean check) {
        CanJni.SitechDevCwGetBatteryData(this.mDev_Battery);
        if (i2b(this.mDev_Battery.UpdateOnce) && (!check || i2b(this.mDev_Battery.Update))) {
            this.mDev_Battery.Update = 0;
            updateItem(1, 0, GetDczzdy(this.mDev_Battery.Dczzdy));
            updateItem(2, 0, GetDczzdl(this.mDev_Battery.Dczzdl));
            updateItem(3, 0, GetDczsoc(this.mDev_Battery.Soc));
            updateItem(4, 0, GetDczwd(this.mDev_Battery.Dczzgwd));
            updateItem(5, 0, GetDczzh(this.mDev_Battery.Dczzgwdzh));
            updateItem(6, 0, GetDczwd(this.mDev_Battery.Dczzdwd));
            updateItem(7, 0, GetDczzh(this.mDev_Battery.Dczzdwdzh));
        }
        CanJni.SitechDevCwGetBmsWarn(this.mDev_BmsWarn);
        if (!i2b(this.mDev_BmsWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDev_BmsWarn.Update)) {
            this.mDev_BmsWarn.Update = 0;
            updateItem(0, 0, GetWarnValue(this.mDev_BmsWarn.Cdzt));
        }
    }

    public String GetWarnValue(int value) {
        if (value < 0 || value >= this.mCdztStrs.length) {
            return "";
        }
        return this.mCdztStrs[value];
    }

    public String GetDczzdy(int value) {
        if (value == 65535) {
            return this.mInvalidStr;
        }
        if (value == 65534) {
            return this.mExceptionStr;
        }
        if (value == 0) {
            return "0 V";
        }
        return String.valueOf(String.format("%.1f", new Object[]{Float.valueOf((float) (((double) value) * 0.1d))})) + " V";
    }

    public String GetDczzdl(int value) {
        if (value == 65535) {
            return this.mInvalidStr;
        }
        if (value == 65534) {
            return this.mExceptionStr;
        }
        if (value == 0) {
            return String.valueOf(value - 3200) + " A";
        }
        return String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(((float) (0.1d * ((double) value))) - 3200.0f)})) + " A";
    }

    public String GetDczsoc(int value) {
        if (value == 255) {
            return this.mInvalidStr;
        }
        if (value == 254) {
            return this.mExceptionStr;
        }
        if (value == 0) {
            return String.valueOf(value) + " %";
        }
        return String.valueOf(String.format("%.1f", new Object[]{Float.valueOf((float) (((double) value) * 0.4d))})) + " %";
    }

    public String GetDczwd(int value) {
        if (value == 255) {
            return this.mInvalidStr;
        }
        if (value == 254) {
            return this.mExceptionStr;
        }
        return String.valueOf(value - 60) + " ℃";
    }

    public String GetDczzh(int value) {
        if (value == 255) {
            return this.mInvalidStr;
        }
        if (value == 254) {
            return this.mExceptionStr;
        }
        return new StringBuilder(String.valueOf(value)).toString();
    }

    public void QueryData() {
    }
}
