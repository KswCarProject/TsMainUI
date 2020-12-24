package com.ts.can.chrysler.xbs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanXbsygSetDiaplayActivity extends CanXbsygBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_DISTANCE_UNIT = 6;
    public static final int ITEM_DWDISTANCE = 3;
    public static final int ITEM_DWFUEL = 7;
    public static final int ITEM_DWPRESENCE = 4;
    public static final int ITEM_DWTEMP = 5;
    public static final int ITEM_LANG = 1;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_UNITS = 2;
    public static final String TAG = "CanRZygSetDiaplayActivity";
    private static final int[] mDWArr = {R.string.can_dw_mz, R.string.can_dw_gz};
    private static final int[] mDWDistanceArr = {R.string.can_service_distance_km, R.string.can_service_distance_mi};
    private static final int[] mDWPressure = {R.string.can_pressure_bar, R.string.can_pressure_kpa, R.string.can_pressure_psi};
    private static final int[] mDWTemperature = {R.string.can_temperature_c, R.string.can_temperature_f};
    private static final int[] mDistanceUnits = {R.string.can_service_distance_mi, R.string.can_service_distance_km};
    private static final int[] mDwFuelUnis = {R.string.can_fuels_lkm, R.string.can_fuels_kml};
    private static final int[] mLangArr = {R.string.lang_en_us, R.string.lang_cn};
    protected CanItemPopupList mItemDistance;
    protected CanItemPopupList mItemDistanceUnit;
    protected CanItemPopupList mItemDwFuel;
    protected CanItemPopupList mItemLang;
    protected CanItemPopupList mItemPressure;
    protected CanItemPopupList mItemTemperature;
    protected CanItemPopupList mItemUnits;
    private CanScrollList mManager;
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (i2b(this.mSetData.LangUpdateOnce) && (!check || i2b(this.mSetData.LangUpdate))) {
            this.mSetData.LangUpdate = 0;
            switch (this.mSetData.Lang) {
                case 0:
                    this.mItemLang.SetSel(0);
                    break;
                case 1:
                    this.mItemLang.SetSel(1);
                    break;
            }
        }
        if (!i2b(this.mSetData.UnitsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.UnitsUpdate)) {
            this.mSetData.UnitsUpdate = 0;
            this.mItemUnits.SetSel(this.mSetData.Units);
            this.mItemDistance.SetSel(this.mSetData.DWDistance);
            this.mItemPressure.SetSel(this.mSetData.Pressure);
            this.mItemTemperature.SetSel(this.mSetData.DWTemp);
            if (this.mSetData.DWDistance == 1 && this.mSetData.DWFuel == 0) {
                this.mItemDwFuel.SetVal(R.string.can_fuels_mpg);
            } else {
                this.mItemDwFuel.SetSel(this.mSetData.DWFuel);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query2(12);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        LayoutUI();
        Log.d("CanRZygSetDiaplayActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanRZygSetDiaplayActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemLang = AddPopupItem(R.string.can_lang_set, mLangArr, 1);
        this.mItemUnits = AddPopupItem(R.string.can_units, mDWArr, 2);
        this.mItemDistance = AddPopupItem(R.string.can_distance, mDWDistanceArr, 3);
        this.mItemTemperature = AddPopupItem(R.string.can_temperature, mDWTemperature, 5);
        this.mItemPressure = AddPopupItem(R.string.can_pressure, mDWPressure, 4);
        this.mItemDwFuel = AddPopupItem(R.string.can_consumption, mDwFuelUnis, 7);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 7; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.adtLang;
                break;
            case 2:
                ret = this.mAdtData.Units;
                break;
            case 3:
                ret = this.mAdtData.DWDistance;
                break;
            case 4:
                ret = this.mAdtData.Pressure;
                break;
            case 5:
                ret = this.mAdtData.DWTemp;
                break;
            case 7:
                ret = this.mAdtData.DWFuel;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemLang.ShowGone(show);
                return;
            case 2:
                this.mItemUnits.ShowGone(show);
                return;
            case 3:
                this.mItemDistance.ShowGone(show);
                return;
            case 4:
                this.mItemPressure.ShowGone(show);
                return;
            case 5:
                this.mItemTemperature.ShowGone(show);
                return;
            case 7:
                this.mItemDwFuel.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                switch (item) {
                    case 0:
                        CarSet(97, 0);
                        return;
                    case 1:
                        CarSet(97, 9);
                        return;
                    default:
                        return;
                }
            case 2:
                CarSet(81, item);
                return;
            case 3:
                CarSet(82, item);
                return;
            case 4:
                CarSet(84, item);
                return;
            case 5:
                CarSet(85, item);
                return;
            case 7:
                CarSet(83, item);
                return;
            default:
                return;
        }
    }
}
