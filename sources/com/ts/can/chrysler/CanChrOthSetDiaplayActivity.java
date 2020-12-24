package com.ts.can.chrysler;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanChrOthSetDiaplayActivity extends CanChrOthBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_DISTANCE = 3;
    public static final int ITEM_DWSPEED = 8;
    public static final int ITEM_FUELS = 5;
    public static final int ITEM_LANG = 1;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_PRESSURE = 6;
    public static final int ITEM_TEMPERATURE = 4;
    public static final int ITEM_TRIPB = 7;
    public static final int ITEM_UNITS = 2;
    public static final String TAG = "CanChrOthSetDiaplayActivity";
    private static final int[] mDWArr = {R.string.can_dw_mz, R.string.can_dw_gz};
    private static final int[] mDWDistance = {R.string.can_distance_km, R.string.can_service_distance_mi};
    private static final int[] mDWFuels = {R.string.can_fuels_lkm, R.string.can_fuels_kml, R.string.can_fuels_mpg};
    private static final int[] mDWPressure = {R.string.can_pressure_psi, R.string.can_pressure_kpa, R.string.can_pressure_bar};
    private static final int[] mDWSpeed = {R.string.can_speedunits_1, R.string.can_speedunits_2};
    private static final int[] mDWTemperature = {R.string.can_temperature_c, R.string.can_temperature_f};
    private static final int[] mLangArr = {R.string.lang_en_us, R.string.lang_francais, R.string.lang_deutsch, R.string.lang_espanol, R.string.lang_itanliano, R.string.lang_nederlands, R.string.lang_polski, R.string.lang_portugues, R.string.lang_turkce, R.string.lang_cn, R.string.lang_en_uk, R.string.lang_portugues_brasil, R.string.lang_pyccknn};
    protected CanItemPopupList mItemDistance;
    protected CanItemPopupList mItemDwSpeed;
    protected CanItemPopupList mItemFuels;
    protected CanItemPopupList mItemLang;
    protected CanItemPopupList mItemPressure;
    protected CanItemPopupList mItemTemperature;
    protected CanItemSwitchList mItemTripb;
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
                    this.mItemLang.SetSel(255);
                    break;
                default:
                    if (this.mSetData.Lang > 0 && this.mSetData.Lang < 14) {
                        this.mItemLang.SetSel(this.mSetData.Lang - 1);
                        break;
                    }
            }
        }
        if (!i2b(this.mSetData.UnitsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.UnitsUpdate)) {
            this.mSetData.UnitsUpdate = 0;
            this.mItemUnits.SetSel(this.mSetData.Units);
            this.mItemDistance.SetSel(this.mSetData.DWDistance);
            this.mItemFuels.SetSel(this.mSetData.DWFuel);
            this.mItemTemperature.SetSel(this.mSetData.DWTemp);
            this.mItemPressure.SetSel(this.mSetData.Pressure);
            this.mItemTripb.SetCheck(this.mSetData.DspTripB);
            this.mItemDwSpeed.SetSel(this.mSetData.Speed);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(64, 0);
        Sleep(1);
        Query(64, 1);
        Sleep(10);
        Query(64, 3);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        LayoutUI();
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemLang = AddPopupItem(R.string.can_lang_set, mLangArr, 1);
        this.mItemUnits = AddPopupItem(R.string.can_units, mDWArr, 2);
        this.mItemDistance = AddPopupItem(R.string.can_distance_l_c, mDWDistance, 3);
        this.mItemTemperature = AddPopupItem(R.string.can_temperature, mDWTemperature, 4);
        this.mItemFuels = AddPopupItem(R.string.can_consumption, mDWFuels, 5);
        this.mItemPressure = AddPopupItem(R.string.can_pressure, mDWPressure, 6);
        this.mItemTripb = AddCheckItem(R.string.can_tripb_disp, 7);
        this.mItemDwSpeed = AddPopupItem(R.string.can_speed_dw, mDWSpeed, 8);
        if (CanFunc.getInstance().IsCustomShow("Jeep")) {
            findViewById(R.id.can_comm_list_layout).setBackgroundResource(R.drawable.can_grand_cherokee_bg);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 8; i++) {
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
                ret = this.mAdtData.DWTemp;
                break;
            case 5:
                ret = this.mAdtData.DWFuel;
                break;
            case 6:
                ret = this.mAdtData.Pressure;
                break;
            case 7:
                ret = this.mAdtData.DspTripB;
                break;
            case 8:
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
            case 3:
                this.mItemDistance.ShowGone(show);
                return;
            case 4:
                this.mItemTemperature.ShowGone(show);
                return;
            case 5:
                this.mItemFuels.ShowGone(show);
                return;
            case 6:
                this.mItemPressure.ShowGone(show);
                return;
            case 7:
                this.mItemTripb.ShowGone(show);
                return;
            case 8:
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
            case 7:
                CarSWSet(6, this.mSetData.DspTripB);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                if (item < 13) {
                    CarSet(0, item + 1);
                    return;
                }
                return;
            case 2:
                CarSet(1, item);
                return;
            case 3:
                CarSet(3, item);
                return;
            case 4:
                CarSet(4, item);
                return;
            case 5:
                CarSet(5, item);
                return;
            case 6:
                CarSet(7, item);
                return;
            case 8:
                CarSet(8, item);
                return;
            default:
                return;
        }
    }
}
