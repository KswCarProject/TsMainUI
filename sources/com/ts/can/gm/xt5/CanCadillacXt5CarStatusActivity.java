package com.ts.can.gm.xt5;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanCadillacXt5CarStatusActivity extends CanCommonActivity {
    private CanDataInfo.GM_BAT_MSG mBatteryData = new CanDataInfo.GM_BAT_MSG();
    private CanDataInfo.GM_FdjWaterTmp mFdjTempData = new CanDataInfo.GM_FdjWaterTmp();
    private CanDataInfo.GM_FdjZs mFdjZsData = new CanDataInfo.GM_FdjZs();
    private CanItemTitleValList mItemBatteryV;
    private CanItemTitleValList mItemFdjTemp;
    private CanItemTitleValList mItemFdjZs;
    private CanItemTitleValList mItemPjyh;
    private CanItemTitleValList mItemSpeed;
    private CanItemTitleValList mItemSsyh;
    private CanItemTitleValList mItemTotalMile;
    private CanItemTitleValList mItemYlxh;
    private CanScrollList mManager;
    private CanDataInfo.GM_Mile mMileData = new CanDataInfo.GM_Mile();
    private CanDataInfo.GM_OilMsg mOilData = new CanDataInfo.GM_OilMsg();
    private CanDataInfo.CAN_Speed mSpeedData = new CanDataInfo.CAN_Speed();

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemFdjZs = this.mManager.addItemTitleValList(R.string.can_rpm, 0, false, this);
        this.mItemFdjTemp = this.mManager.addItemTitleValList(R.string.can_rpm_temp, 0, false, this);
        this.mItemPjyh = this.mManager.addItemTitleValList(R.string.can_pjyh, 0, false, this);
        this.mItemPjyh.ShowGone(false);
        this.mItemSsyh = this.mManager.addItemTitleValList(R.string.can_ssyh, 0, false, this);
        this.mItemSsyh.ShowGone(false);
        this.mItemYlxh = this.mManager.addItemTitleValList(R.string.can_ylxh, 0, false, this);
        this.mItemTotalMile = this.mManager.addItemTitleValList(R.string.can_total_mile, 0, false, this);
        this.mItemBatteryV = this.mManager.addItemTitleValList(R.string.can_battery_v, 0, false, this);
        this.mItemSpeed = this.mManager.addItemTitleValList(R.string.can_speed, 0, false, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GmSbGetCarFdjzs(this.mFdjZsData);
        CanJni.GmSbGetCarFdjWatTmp(this.mFdjTempData);
        CanJni.GmSbGetCarOilMsg(this.mOilData);
        CanJni.GmSbGetCarMile(this.mMileData);
        CanJni.GmSbGetCarBatMsg(this.mBatteryData);
        CanJni.GetSpeed(this.mSpeedData);
        if (i2b(this.mFdjZsData.UpdateOnce) && (!check || i2b(this.mFdjZsData.Update))) {
            this.mFdjZsData.Update = 0;
            this.mItemFdjZs.SetVal(String.format("%d r/s", new Object[]{Integer.valueOf(this.mFdjZsData.Speed)}));
        }
        if (i2b(this.mFdjTempData.UpdateOnce) && (!check || i2b(this.mFdjTempData.Update))) {
            this.mFdjTempData.Update = 0;
            this.mItemFdjTemp.SetVal(String.format("%d °C", new Object[]{Integer.valueOf(this.mFdjTempData.Temp - 40)}));
        }
        if (i2b(this.mOilData.UpdateOnce) && (!check || i2b(this.mOilData.Update))) {
            this.mOilData.Update = 0;
            this.mItemPjyh.SetVal(String.format("%.1f L/H", new Object[]{Float.valueOf(((float) this.mOilData.Pjyh) / 10.0f)}));
            this.mItemSsyh.SetVal(String.format("%.1f L/H", new Object[]{Float.valueOf(((float) this.mOilData.Ssyh) / 10.0f)}));
            this.mItemYlxh.SetVal(String.format("%d KM", new Object[]{Integer.valueOf(this.mOilData.Ylxh)}));
        }
        if (i2b(this.mMileData.UpdateOnce) && (!check || i2b(this.mMileData.Update))) {
            this.mMileData.Update = 0;
            this.mItemTotalMile.SetVal(String.format("%d KM", new Object[]{Integer.valueOf(this.mMileData.TotalMile)}));
        }
        if (i2b(this.mBatteryData.UpdateOnce) && (!check || i2b(this.mBatteryData.Update))) {
            this.mBatteryData.Update = 0;
            this.mItemBatteryV.SetVal(String.format("%.1f V", new Object[]{Float.valueOf(((float) this.mBatteryData.BatV) / 10.0f)}));
        }
        if (!i2b(this.mSpeedData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSpeedData.Update)) {
            this.mSpeedData.Update = 0;
            this.mItemSpeed.SetVal(String.format("%d km/h", new Object[]{Integer.valueOf(this.mSpeedData.Speed)}));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
