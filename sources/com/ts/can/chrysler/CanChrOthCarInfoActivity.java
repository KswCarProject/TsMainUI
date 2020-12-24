package com.ts.can.chrysler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanChrOthCarInfoActivity extends CanChrOthBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_AMPSET = 13;
    public static final int ITEM_AUTO_RMT_BOOT = 6;
    public static final int ITEM_BRAKE = 9;
    public static final int ITEM_CAR_TYPE = 1;
    public static final int ITEM_COMPASS = 8;
    public static final int ITEM_DISPLAY = 2;
    public static final int ITEM_ENGINE_OFF = 5;
    public static final int ITEM_FUEL = 10;
    public static final int ITEM_LIGHT = 3;
    public static final int ITEM_LOCK = 4;
    private static final int ITEM_MAX = 13;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SAFE_ASSIST = 7;
    public static final int ITEM_SUSPENSION = 11;
    public static final int ITEM_WIDGET = 12;
    public static final String TAG = "CanChrOthCarInfoActivity";
    private CanItemIcoList mItemAmpSet;
    private CanItemIcoList mItemAutoRmtBoot;
    private CanItemIcoList mItemBrake;
    private CanItemIcoList mItemCarType;
    private CanItemIcoList mItemCompass;
    private CanItemIcoList mItemDisplay;
    private CanItemIcoList mItemEngineOff;
    private CanItemIcoList mItemFuel;
    private CanItemIcoList mItemLight;
    private CanItemIcoList mItemLock;
    private CanItemIcoList mItemSafeAssist;
    private CanItemIcoList mItemSuspension;
    private CanItemIcoList mItemWidget;
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
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
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
        if (CanFunc.getInstance().IsCustomShow("Jeep")) {
            findViewById(R.id.can_comm_list_layout).setBackgroundResource(R.drawable.can_grand_cherokee_bg);
        }
        this.mItemCarType = AddIcoItem(R.drawable.can_icon_esc, R.string.can_car_type_select, 1);
        this.mItemDisplay = AddIcoItem(R.drawable.can_icon_setup, R.string.can_display, 2);
        this.mItemLight = AddIcoItem(R.drawable.can_icon_light, R.string.can_light_setup, 3);
        this.mItemLock = AddIcoItem(R.drawable.can_icon_lock, R.string.can_door_lock_set, 4);
        this.mItemEngineOff = AddIcoItem(R.drawable.can_icon_driver_assist, R.string.can_fdjgbxx, 5);
        this.mItemAutoRmtBoot = AddIcoItem(R.drawable.can_icon_rmt_ctl, R.string.can_zdkqssxt, 6);
        this.mItemSafeAssist = AddIcoItem(R.drawable.can_icon_driver_assist, R.string.can_aqhjsfz, 7);
        this.mItemCompass = AddIcoItem(R.drawable.can_icon_compass, R.string.can_compass, 8);
        this.mItemBrake = AddIcoItem(R.drawable.can_icon_service, R.string.can_set_brake, 9);
        this.mItemFuel = AddIcoItem(R.drawable.can_icon_mfd, R.string.can_consumption, 10);
        this.mItemSuspension = AddIcoItem(R.drawable.can_icon_wm, R.string.can_xuanjia, 11);
        this.mItemWidget = AddIcoItem(R.drawable.can_icon_lock3, R.string.can_chrysler_control, 12);
        this.mItemAmpSet = AddIcoItem(R.drawable.can_icon_setup, R.string.can_amp_set, 13);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 13; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        GetAdtData();
        switch (item) {
            case 1:
                if (!CanFunc.getInstance().IsCustomShow("Jeep")) {
                    ret = 1;
                    break;
                }
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                ret = this.mAdtData.adtLight | this.mAdtData.adtLight2;
                break;
            case 4:
                ret = this.mAdtData.adtLock;
                break;
            case 5:
                ret = this.mAdtData.adtEnOff;
                break;
            case 6:
                ret = this.mAdtData.adtRmtBoot;
                break;
            case 7:
                ret = this.mAdtData.adtDrvAss;
                break;
            case 8:
                ret = this.mAdtData.adtCps;
                break;
            case 9:
                ret = this.mAdtData.AdtBrk;
                break;
            case 10:
                ret = this.mAdtData.adtFsp;
                break;
            case 11:
                ret = this.mAdtData.adtXJ;
                break;
            case 12:
                if (CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
            case 13:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemCarType.ShowGone(show);
                return;
            case 2:
                this.mItemDisplay.ShowGone(show);
                return;
            case 3:
                this.mItemLight.ShowGone(show);
                return;
            case 4:
                this.mItemLock.ShowGone(show);
                return;
            case 5:
                this.mItemEngineOff.ShowGone(show);
                return;
            case 6:
                this.mItemAutoRmtBoot.ShowGone(show);
                return;
            case 7:
                this.mItemSafeAssist.ShowGone(show);
                return;
            case 8:
                this.mItemCompass.ShowGone(show);
                return;
            case 9:
                this.mItemBrake.ShowGone(show);
                return;
            case 10:
                this.mItemFuel.ShowGone(show);
                return;
            case 11:
                this.mItemSuspension.ShowGone(show);
                return;
            case 12:
                this.mItemWidget.ShowGone(show);
                return;
            case 13:
                this.mItemAmpSet.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanChrOthCarTypeActivity.class);
                return;
            case 2:
                enterSubWin(CanChrOthSetDiaplayActivity.class);
                return;
            case 3:
                enterSubWin(CanChrOthSetLightActivity.class);
                return;
            case 4:
                enterSubWin(CanChrOthSetLockActivity.class);
                return;
            case 5:
                enterSubWin(CanChrOthSetEngOffActivity.class);
                return;
            case 6:
                enterSubWin(CanChrOthSetAutoRmtBootActivity.class);
                return;
            case 7:
                enterSubWin(CanChrOthSetSafeAssistActivity.class);
                return;
            case 8:
                enterSubWin(CanChrOthSetCompassActivity.class);
                return;
            case 9:
                enterSubWin(CanChrOthSetBrakeActivity.class);
                return;
            case 10:
                enterSubWin(CanChrOthDriveInfoActivity.class);
                return;
            case 11:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
                return;
            case 12:
                enterSubWin(CanJeepACWidgetActivity.class);
                return;
            case 13:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
