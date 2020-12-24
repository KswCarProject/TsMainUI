package com.ts.can.fiat.doblo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanFlatDobloSetDisplayActivity extends CanFlatDobloBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_DISTANCE = 3;
    public static final int ITEM_FUELS = 2;
    public static final int ITEM_LANG = 1;
    public static final int ITEM_TEMPERATURE = 4;
    public static final int ITEM_TRIP_B = 5;
    private static final int[] mDWDistance = {R.string.can_distance_km, R.string.can_distance_m};
    private static final int[] mDWFuels = {R.string.can_fuels_lkm, R.string.can_fuels_kml, R.string.can_fuels_mpg};
    private static final int[] mDWTemperature = {R.string.can_temperature_c, R.string.can_temperature_f};
    private static final int[] mLangArr = {R.string.lang_en_us, R.string.lang_francais, R.string.lang_deutsch, R.string.lang_espanol, R.string.lang_itanliano, R.string.lang_nederlands, R.string.lang_polski, R.string.lang_portugues, R.string.lang_turkce};
    protected CanItemPopupList mItemDistance;
    protected CanItemPopupList mItemFuels;
    protected CanItemPopupList mItemLang;
    protected CanItemPopupList mItemTemperature;
    protected CanItemSwitchList mItemTripB;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemLang = AddPopupItem(R.string.can_lang_set, mLangArr, 1);
        this.mItemFuels = AddPopupItem(R.string.can_consumption, mDWFuels, 2);
        this.mItemDistance = AddPopupItem(R.string.can_distance_l_c, mDWDistance, 3);
        this.mItemTemperature = AddPopupItem(R.string.can_temperature, mDWTemperature, 4);
        this.mItemTripB = AddCheckItem(R.string.can_display_t_b, 5);
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
    public void QueryData() {
        Query(64, 0);
        Sleep(1);
        Query(64, 1);
        Sleep(1);
        Query(64, 3);
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
        if (i2b(this.mSetData.UnitsUpdateOnce) && (!check || i2b(this.mSetData.UnitsUpdate))) {
            this.mSetData.UnitsUpdate = 0;
            this.mItemFuels.SetSel(this.mSetData.DWFuel);
            this.mItemDistance.SetSel(this.mSetData.DWDistance);
            this.mItemTemperature.SetSel(this.mSetData.DWTemp);
        }
        if (!i2b(this.mSetData.DspTripBUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.DspTripBUpdate)) {
            this.mSetData.DspTripBUpdate = 0;
            this.mItemTripB.SetCheck(this.mSetData.DspTripB);
        }
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CarSet(0, item + 1);
                return;
            case 2:
                CarSet(5, item);
                return;
            case 3:
                CarSet(3, item);
                return;
            case 4:
                CarSet(4, item);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 5:
                CarSWSet(6, this.mSetData.DspTripB);
                return;
            default:
                return;
        }
    }
}
