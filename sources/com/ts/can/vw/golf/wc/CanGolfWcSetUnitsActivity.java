package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetUnitsActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick, UserCallBack {
    private static final int ITEM_HDL = 7;
    private static final int ITEM_OIL = 5;
    private static final int ITEM_RANGE = 1;
    private static final int ITEM_SPEED = 2;
    private static final int ITEM_TEMP = 3;
    private static final int ITEM_TYRES = 6;
    private static final int ITEM_VOLUME = 4;
    private static final String[] mHDLDW = {"KWh/100km", "km/KWh"};
    private static final String[] mOilDW = {"L/100km", "km/l", "mpg(US)", "mpg(UK)"};
    private static final String[] mRangeDW = {"mi", "km"};
    private static final String[] mSpeedDW = {"m/h", "km/h"};
    private static final String[] mTempDW = {"℉", "℃"};
    private static final String[] mTyresDW = {"kPa", "bar", "psi"};
    private static final String[] mVolumeDW = {"L", "gal(US)", "gal(UK)"};
    private CanDataInfo.GolfWcDw mDwAdt = new CanDataInfo.GolfWcDw();
    private CanDataInfo.GolfWcDw mDwInfo = new CanDataInfo.GolfWcDw();
    private CanItemPopupList mItemHdl;
    private CanItemPopupList mItemOil;
    private CanItemPopupList mItemRange;
    private CanItemPopupList mItemSpeed;
    private CanItemPopupList mItemTemp;
    private CanItemPopupList mItemTyres;
    private CanItemPopupList mItemVolume;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfWcGetDwData(1, this.mDwAdt);
        CanJni.GolfWcGetDwData(0, this.mDwInfo);
        if (i2b(this.mDwAdt.UpdateOnce) && (!check || i2b(this.mDwAdt.Update))) {
            this.mDwAdt.Update = 0;
            this.mItemRange.ShowGone(this.mDwAdt.Distance);
            this.mItemSpeed.ShowGone(this.mDwAdt.Speed);
            this.mItemTemp.ShowGone(this.mDwAdt.Temperature);
            this.mItemVolume.ShowGone(this.mDwAdt.Volume);
            this.mItemOil.ShowGone(this.mDwAdt.Consumption);
            this.mItemTyres.ShowGone(this.mDwAdt.Pressure);
            this.mItemHdl.ShowGone(this.mDwAdt.Hdl);
        }
        if (!i2b(this.mDwInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDwInfo.Update)) {
            this.mDwInfo.Update = 0;
            this.mItemRange.SetSel(this.mDwInfo.Distance);
            this.mItemSpeed.SetSel(this.mDwInfo.Speed);
            this.mItemTemp.SetSel(this.mDwInfo.Temperature);
            this.mItemVolume.SetSel(this.mDwInfo.Volume);
            this.mItemOil.SetSel(this.mDwInfo.Consumption);
            this.mItemTyres.SetSel(this.mDwInfo.Pressure);
            this.mItemHdl.SetSel(this.mDwInfo.Hdl);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 193);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemRange = new CanItemPopupList((Context) this, R.string.can_distance_l_c, mRangeDW, (CanItemPopupList.onPopItemClick) this);
        this.mItemRange.SetId(1);
        this.mItemSpeed = new CanItemPopupList((Context) this, R.string.can_speed, mSpeedDW, (CanItemPopupList.onPopItemClick) this);
        this.mItemSpeed.SetId(2);
        this.mItemTemp = new CanItemPopupList((Context) this, R.string.can_temperature, mTempDW, (CanItemPopupList.onPopItemClick) this);
        this.mItemTemp.SetId(3);
        this.mItemVolume = new CanItemPopupList((Context) this, R.string.can_volume, mVolumeDW, (CanItemPopupList.onPopItemClick) this);
        this.mItemVolume.SetId(4);
        this.mItemOil = new CanItemPopupList((Context) this, R.string.can_consumption, mOilDW, (CanItemPopupList.onPopItemClick) this);
        this.mItemOil.SetId(5);
        this.mItemTyres = new CanItemPopupList((Context) this, R.string.can_pressure, mTyresDW, (CanItemPopupList.onPopItemClick) this);
        this.mItemTyres.SetId(6);
        this.mItemHdl = new CanItemPopupList((Context) this, R.string.can_HDL_DW, mHDLDW, (CanItemPopupList.onPopItemClick) this);
        this.mItemHdl.SetId(7);
        this.mItemRange.ShowGone(false);
        this.mItemSpeed.ShowGone(false);
        this.mItemTemp.ShowGone(false);
        this.mItemVolume.ShowGone(false);
        this.mItemOil.ShowGone(false);
        this.mItemTyres.ShowGone(false);
        this.mItemHdl.ShowGone(false);
        this.mManager.AddView(this.mItemRange.GetView());
        this.mManager.AddView(this.mItemSpeed.GetView());
        this.mManager.AddView(this.mItemTemp.GetView());
        this.mManager.AddView(this.mItemVolume.GetView());
        this.mManager.AddView(this.mItemOil.GetView());
        this.mManager.AddView(this.mItemTyres.GetView());
        this.mManager.AddView(this.mItemHdl.GetView());
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 1:
                if (item == 1) {
                    CanJni.GolfWcDwSet(1, 1);
                    return;
                } else {
                    CanJni.GolfWcDwSet(1, 2);
                    return;
                }
            case 2:
                if (item == 1) {
                    CanJni.GolfWcDwSet(2, 1);
                    return;
                } else {
                    CanJni.GolfWcDwSet(2, 2);
                    return;
                }
            case 3:
                if (item == 1) {
                    CanJni.GolfWcDwSet(3, 1);
                    return;
                } else {
                    CanJni.GolfWcDwSet(3, 2);
                    return;
                }
            case 4:
                CanJni.GolfWcDwSet(4, item + 1);
                return;
            case 5:
                CanJni.GolfWcDwSet(5, item + 1);
                return;
            case 6:
                CanJni.GolfWcDwSet(6, item + 1);
                return;
            case 7:
                CanJni.GolfWcDwSet(7, item + 1);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
