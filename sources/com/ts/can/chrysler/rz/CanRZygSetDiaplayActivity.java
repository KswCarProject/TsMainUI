package com.ts.can.chrysler.rz;

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

public class CanRZygSetDiaplayActivity extends CanRZygBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_DISTANCE_UNIT = 6;
    public static final int ITEM_DWDISTANCE = 3;
    public static final int ITEM_DWFUEL = 7;
    public static final int ITEM_DWPRESENCE = 4;
    public static final int ITEM_DWSPEED = 9;
    public static final int ITEM_DWTEMP = 5;
    public static final int ITEM_LANG = 1;
    public static final int ITEM_LANG2 = 8;
    private static final int ITEM_MAX = 9;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_UNITS = 2;
    public static final String TAG = "CanRZygSetDiaplayActivity";
    private static final int[] mDWArr = {R.string.can_dw_gz, R.string.can_dw_mz, R.string.can_individual};
    private static final int[] mDWPressure = {R.string.can_pressure_bar, R.string.can_pressure_kpa, R.string.can_pressure_psi};
    private static final int[] mDWTemperature = {R.string.can_temperature_c, R.string.can_temperature_f};
    private static final int[] mDistanceUnits = {R.string.can_service_distance_mi, R.string.can_service_distance_km};
    private static final int[] mDwFuelUnis = {R.string.can_fuels_mpg_us, R.string.can_fuels_mpg_uk, R.string.can_fuels_lkm, R.string.can_fuels_kml};
    private static final int[] mLangArr = {R.string.lang_en_us, R.string.lang_cn};
    private static final int[] mLangArr2 = {R.string.lang_en_us, R.string.lang_francais, R.string.lang_portugues_br, R.string.lang_espanol, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_nederlands, R.string.lang_polski, R.string.lang_cn, R.string.lang_portugues, R.string.lang_turkce, R.string.lang_arab, R.string.lang_pyccknn, R.string.lang_en_uk, R.string.lang_france_can};
    private static final int[] mSpeedArr = {R.string.can_speedunits_2, R.string.can_speedunits_1};
    protected CanItemPopupList mItemDistanceUnit;
    protected CanItemPopupList mItemDwFuel;
    protected CanItemPopupList mItemDwSpeed;
    protected CanItemPopupList mItemLang;
    protected CanItemPopupList mItemLang2;
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
            if (this.mSetData.Lang2 > 0) {
                this.mItemLang2.SetSel(this.mSetData.Lang2 - 1);
            }
        }
        if (!i2b(this.mSetData.UnitsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.UnitsUpdate)) {
            this.mSetData.UnitsUpdate = 0;
            this.mItemUnits.SetSel(this.mSetData.Units);
            this.mItemPressure.SetSel(this.mSetData.Pressure);
            this.mItemTemperature.SetSel(this.mSetData.DWTemp);
            this.mItemDistanceUnit.SetSel(this.mSetData.DWDistance);
            this.mItemDwFuel.SetSel(this.mSetData.DWFuel);
            this.mItemDwSpeed.SetSel(this.mSetData.Speed);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(64, 0);
        Sleep(1);
        Query(64, 1);
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
        this.mItemLang2 = AddPopupItem(R.string.can_lang_set, mLangArr2, 8);
        this.mItemUnits = AddPopupItem(R.string.can_units, mDWArr, 2);
        this.mItemTemperature = AddPopupItem(R.string.can_temperature, mDWTemperature, 5);
        this.mItemPressure = AddPopupItem(R.string.can_pressure, mDWPressure, 4);
        this.mItemDistanceUnit = AddPopupItem(R.string.can_distance, mDistanceUnits, 6);
        this.mItemDwFuel = AddPopupItem(R.string.can_consumption, mDwFuelUnis, 7);
        this.mItemDwSpeed = AddPopupItem(R.string.can_speed_dw, mSpeedArr, 9);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 9; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.Lang;
                break;
            case 2:
                ret = this.mAdtData.Units;
                break;
            case 4:
                ret = this.mAdtData.Pressure;
                break;
            case 5:
                ret = this.mAdtData.DWTemp;
                break;
            case 6:
                ret = this.mAdtData.DWDistance;
                break;
            case 7:
                ret = this.mAdtData.DWFuel;
                break;
            case 8:
                ret = this.mAdtData.Lang2;
                break;
            case 9:
                ret = this.mAdtData.Speed;
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
            case 4:
                this.mItemPressure.ShowGone(show);
                return;
            case 5:
                this.mItemTemperature.ShowGone(show);
                return;
            case 6:
                this.mItemDistanceUnit.ShowGone(show);
                return;
            case 7:
                this.mItemDwFuel.ShowGone(show);
                return;
            case 8:
                this.mItemLang2.ShowGone(show);
                return;
            case 9:
                this.mItemDwSpeed.ShowGone(show);
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
                        CarSet(83, 1);
                        return;
                    case 1:
                        CarSet(83, 9);
                        return;
                    default:
                        return;
                }
            case 2:
                CarSet(82, item);
                return;
            case 4:
                CarSet(113, item);
                return;
            case 5:
                CarSet(114, item);
                return;
            case 6:
                CarSet(115, item);
                return;
            case 7:
                CarSet(116, item);
                return;
            case 8:
                CarSet(83, item + 1);
                return;
            case 9:
                CarSet(117, item + 1);
                return;
            default:
                return;
        }
    }
}
