package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetMFDActivity extends CanBaseActivity implements View.OnClickListener, CanItemMsgBox.onMsgBoxClick, UserCallBack {
    public static final int ITEM_AVG_CONSUMPTION = 2;
    public static final int ITEM_AVG_SPEED = 7;
    public static final int ITEM_CONV_CONSUMERS = 3;
    public static final int ITEM_CUR_CONSUMPTION = 1;
    public static final int ITEM_DIG_DISPLAY = 8;
    public static final int ITEM_DIS_TRAVELLED = 6;
    public static final int ITEM_ECO_TIPS = 4;
    public static final int ITEM_OIL_TEMP = 10;
    public static final int ITEM_RESET_LONG_TERM = 12;
    public static final int ITEM_RESET_SINCE_START = 11;
    public static final int ITEM_SPEED_WARN = 9;
    public static final int ITEM_TRAVEL_TIME = 5;
    /* access modifiers changed from: private */
    public View mCurItem;
    private CanDataInfo.GolfWcDisplay mDisplayAdt = new CanDataInfo.GolfWcDisplay();
    private CanDataInfo.GolfWcDisplay mDisplayData = new CanDataInfo.GolfWcDisplay();
    private CanItemCheckList mItemAvgConsumption;
    private CanItemCheckList mItemAvgSpeed;
    private CanItemCheckList mItemConvConsumers;
    private CanItemCheckList mItemCurConsumption;
    private CanItemCheckList mItemDigDisplay;
    private CanItemCheckList mItemDisTravelled;
    private CanItemCheckList mItemEcoTips;
    private CanItemCheckList mItemOilTemp;
    private CanItemTextBtnList mItemResetLongTerm;
    private CanItemTextBtnList mItemResetSinceStart;
    private CanItemCheckList mItemSpeedWarn;
    private CanItemCheckList mItemTravelTime;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfWcGetDisplayData(1, this.mDisplayAdt);
        CanJni.GolfWcGetDisplayData(0, this.mDisplayData);
        if (i2b(this.mDisplayAdt.UpdateOnce) && (!check || i2b(this.mDisplayAdt.Update))) {
            this.mDisplayAdt.Update = 0;
            this.mItemCurConsumption.ShowGone(this.mDisplayAdt.CurrentConsumption);
            this.mItemAvgConsumption.ShowGone(this.mDisplayAdt.AverageConsumption);
            this.mItemConvConsumers.ShowGone(this.mDisplayAdt.ConvenuenceConsumer);
            this.mItemEcoTips.ShowGone(this.mDisplayAdt.EcoTips);
            this.mItemTravelTime.ShowGone(this.mDisplayAdt.TravelingTime);
            this.mItemDisTravelled.ShowGone(this.mDisplayAdt.DistanceTravelled);
            this.mItemAvgSpeed.ShowGone(this.mDisplayAdt.AverageSpeed);
            this.mItemDigDisplay.ShowGone(this.mDisplayAdt.DigitalSpeedDisplay);
            this.mItemSpeedWarn.ShowGone(this.mDisplayAdt.SpeedWarming);
            this.mItemOilTemp.ShowGone(this.mDisplayAdt.OilTemperature);
        }
        if (!i2b(this.mDisplayData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDisplayData.Update)) {
            this.mDisplayData.Update = 0;
            this.mItemCurConsumption.SetCheck(this.mDisplayData.CurrentConsumption);
            this.mItemAvgConsumption.SetCheck(this.mDisplayData.AverageConsumption);
            this.mItemConvConsumers.SetCheck(this.mDisplayData.ConvenuenceConsumer);
            this.mItemEcoTips.SetCheck(this.mDisplayData.EcoTips);
            this.mItemTravelTime.SetCheck(this.mDisplayData.TravelingTime);
            this.mItemDisTravelled.SetCheck(this.mDisplayData.DistanceTravelled);
            this.mItemAvgSpeed.SetCheck(this.mDisplayData.AverageSpeed);
            this.mItemDigDisplay.SetCheck(this.mDisplayData.DigitalSpeedDisplay);
            this.mItemSpeedWarn.SetCheck(this.mDisplayData.SpeedWarming);
            this.mItemOilTemp.SetCheck(this.mDisplayData.OilTemperature);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 118);
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
        this.mItemCurConsumption = AddCheckItem(R.string.can_cur_consump, 1);
        this.mItemAvgConsumption = AddCheckItem(R.string.can_avg_consump, 2);
        this.mItemConvConsumers = AddCheckItem(R.string.can_conv_consumers, 3);
        this.mItemEcoTips = AddCheckItem(R.string.can_eco_tips, 4);
        this.mItemTravelTime = AddCheckItem(R.string.can_trav_time, 5);
        this.mItemDisTravelled = AddCheckItem(R.string.can_dis_trav, 6);
        this.mItemAvgSpeed = AddCheckItem(R.string.can_avg_spped, 7);
        this.mItemDigDisplay = AddCheckItem(R.string.can_dig_speed_display, 8);
        this.mItemSpeedWarn = AddCheckItem(R.string.can_speed_warn, 9);
        this.mItemOilTemp = AddCheckItem(R.string.can_oil_temp, 10);
        this.mItemResetSinceStart = AddTextItem(R.string.can_reset_sin_start, 11);
        this.mItemResetLongTerm = AddTextItem(R.string.can_reset_l_term, 12);
    }

    /* access modifiers changed from: protected */
    public CanItemCheckList AddCheckItem(int resId, int Id) {
        CanItemCheckList item = new CanItemCheckList(this, resId);
        item.SetIdClickListener(this, Id);
        item.ShowGone(false);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemTextBtnList AddTextItem(int resId, int Id) {
        CanItemTextBtnList item = new CanItemTextBtnList((Context) this, resId);
        item.SetIdClickListener(this, Id);
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        int item = ((Integer) v.getTag()).intValue();
        switch (item) {
            case 1:
                CanJni.GolfWcDisplaySet(1, Neg(this.mDisplayData.CurrentConsumption));
                return;
            case 2:
                CanJni.GolfWcDisplaySet(2, Neg(this.mDisplayData.AverageConsumption));
                return;
            case 3:
                CanJni.GolfWcDisplaySet(3, Neg(this.mDisplayData.ConvenuenceConsumer));
                return;
            case 4:
                CanJni.GolfWcDisplaySet(4, Neg(this.mDisplayData.EcoTips));
                return;
            case 5:
                CanJni.GolfWcDisplaySet(5, Neg(this.mDisplayData.TravelingTime));
                return;
            case 6:
                CanJni.GolfWcDisplaySet(6, Neg(this.mDisplayData.DistanceTravelled));
                return;
            case 7:
                CanJni.GolfWcDisplaySet(7, Neg(this.mDisplayData.AverageSpeed));
                return;
            case 8:
                CanJni.GolfWcDisplaySet(8, Neg(this.mDisplayData.DigitalSpeedDisplay));
                return;
            case 9:
                CanJni.GolfWcDisplaySet(9, Neg(this.mDisplayData.SpeedWarming));
                return;
            case 10:
                CanJni.GolfWcDisplaySet(10, Neg(this.mDisplayData.OilTemperature));
                return;
            case 11:
            case 12:
                CanItemMsgBox msgbox = new CanItemMsgBox(item, this, R.string.can_sure_setup, this);
                v.setSelected(true);
                this.mCurItem = v;
                msgbox.getDlg().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface arg0) {
                        if (CanGolfWcSetMFDActivity.this.mCurItem != null) {
                            CanGolfWcSetMFDActivity.this.mCurItem.setSelected(false);
                        }
                    }
                });
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        switch (param) {
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
