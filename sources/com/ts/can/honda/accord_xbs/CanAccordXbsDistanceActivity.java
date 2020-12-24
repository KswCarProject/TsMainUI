package com.ts.can.honda.accord_xbs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanAccordXbsDistanceActivity extends CanAccordXbsBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    public static final int ITEM_ADJ_OUTTEMP = 3;
    public static final int ITEM_FUEL_BK_LIGHT = 4;
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_TRIPA_TIME = 1;
    public static final int ITEM_TRIPB_TIME = 2;
    public static final String TAG = "CanAccordXbsDistanceActivity";
    private static final int[] mTripTimingArr = {R.string.can_yjyld, R.string.can_gbsld, R.string.can_shoudong};
    private CanItemProgressList mItemAdjTemp;
    private CanItemSwitchList mItemFuelLight;
    private CanItemPopupList mItemTripA;
    private CanItemPopupList mItemTripB;
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
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemAdjTemp.SetCurVal(this.mSetData.AdjustOutTemp);
            if (this.mSetData.AdjustOutTemp >= 5) {
                this.mItemAdjTemp.SetValText("+" + (this.mSetData.AdjustOutTemp - 5));
            } else {
                this.mItemAdjTemp.SetValText(new StringBuilder(String.valueOf(this.mSetData.AdjustOutTemp - 5)).toString());
            }
            this.mItemFuelLight.SetCheck(this.mSetData.FuelBacklightSW);
            this.mItemTripA.SetSel(this.mSetData.TripAResetTiming);
            this.mItemTripB.SetSel(this.mSetData.TripBResetTiming);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(10, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemTripA = AddPopupItem(R.string.can_lcacs, mTripTimingArr, 1);
        this.mItemTripB = AddPopupItem(R.string.can_lcbcs, mTripTimingArr, 2);
        this.mItemFuelLight = AddCheckItem(R.string.can_rybjtm, 4);
        this.mItemAdjTemp = new CanItemProgressList((Context) this, R.string.can_tjww);
        this.mItemAdjTemp.SetIdCallBack(3, this);
        this.mManager.AddView(this.mItemAdjTemp.GetView());
        this.mItemAdjTemp.SetMinMax(0, 10);
        this.mItemAdjTemp.SetUserValText();
        this.mItemAdjTemp.ShowGone(false);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 4; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.TripAResetTiming;
                break;
            case 2:
                ret = this.mAdtData.TripBResetTiming;
                break;
            case 3:
                ret = this.mAdtData.AdjustOutTemp;
                break;
            case 4:
                ret = this.mAdtData.FuelBacklightSW;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemTripA.ShowGone(show);
                return;
            case 2:
                this.mItemTripB.ShowGone(show);
                return;
            case 3:
                this.mItemAdjTemp.ShowGone(show);
                return;
            case 4:
                this.mItemFuelLight.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                CarSet(1, Neg(this.mSetData.FuelBacklightSW));
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
                CarSet(2, item);
                return;
            case 2:
                CarSet(3, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 3:
                CarSet(0, pos);
                return;
            default:
                return;
        }
    }
}
