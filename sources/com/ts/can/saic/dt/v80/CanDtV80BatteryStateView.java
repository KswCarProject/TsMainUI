package com.ts.can.saic.dt.v80;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanDtV80BatteryStateView extends CanScrollCarInfoView {
    private static String[] mErrorArray = {"正常", "隔离警告", "隔离错误"};
    private static String[] mGybjArray = {"正常", "过压", "欠压", "错误"};
    private static String[] mHotArray = {"正常", "过温", "过温1", "过温2"};
    private static String[] mWarnArray = {"正常", "警告", "错误"};
    private CanDataInfo.DT_V80_BMS_WARN mWarn;

    public CanDtV80BatteryStateView(Activity activity) {
        super(activity, 13);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mItemTitleIds = new int[]{R.string.can_dlxdcgz, R.string.can_gybjd, R.string.can_dczjysxgz, R.string.can_zcbjd, R.string.can_djgzzsd, R.string.can_djxtgr, R.string.can_igbt_wdzt, R.string.can_zkbgz, R.string.can_zbzt, R.string.can_cdszzt, R.string.can_dcdc, R.string.can_dlxdcqd, R.string.can_fzdcdl};
        this.mWarn = new CanDataInfo.DT_V80_BMS_WARN();
    }

    public void ResetData(boolean check) {
        CanJni.SaicDtV80GetBmsWarn(this.mWarn);
        if (!i2b(this.mWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarn.Update)) {
            this.mWarn.Update = 0;
            updateState(0, this.mWarn.Dlxdcgz, mWarnArray);
            updateState(1, this.mWarn.Gybjd, mGybjArray);
            updateState(2, this.mWarn.Dczjysx, mErrorArray);
            updateState(3, this.mWarn.Zcbjd, mWarnArray);
            updateState(4, this.mWarn.Djgzzsd, mWarnArray);
            updateState(5, this.mWarn.Djxtgr, mHotArray);
            updateState(6, this.mWarn.IGBT, mHotArray);
            updateState(7, this.mWarn.Zkbgz, new String[]{"正常", "故障"});
            updateState(8, this.mWarn.Ready, new String[]{"Not useable", "ON", "Enable running"});
            updateState(9, this.mWarn.Cdszzt, new String[]{"锁止", " ", " ", "未锁止"});
            updateState(10, this.mWarn.DCDC, new String[]{"OFF", "ON"});
            updateState(11, this.mWarn.Dlxdcqd, new String[]{"正常", "动力中断"});
            updateState(12, this.mWarn.Fzdcdl, new String[]{"正常", "电量低"});
        }
    }

    public void QueryData() {
    }

    private void updateState(int index, int val, String[] array) {
        String str;
        if (val < 0 || val >= array.length) {
            str = "- -";
        } else {
            str = array[val];
        }
        updateItem(index, 0, str);
    }

    public void doOnResume() {
        resetValues();
    }

    private void resetValues() {
        for (int i = 0; i < this.mItemTitleIds.length; i++) {
            updateItem(i, 0, "- -");
        }
    }
}
