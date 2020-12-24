package com.ts.can.chrysler.xbs;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanXbsygCarInfoActivity extends CanXbsygBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_AMP_INFO = 10;
    public static final int ITEM_AUTO_RMT_BOOT = 6;
    public static final int ITEM_BRAKE = 9;
    public static final int ITEM_CAR_TYPE = 1;
    public static final int ITEM_COMPASS = 8;
    public static final int ITEM_DISPLAY = 2;
    public static final int ITEM_ENGINE_OFF = 5;
    public static final int ITEM_LIGHT = 3;
    public static final int ITEM_LOCK = 4;
    private static final int ITEM_MAX = 11;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SAFE_ASSIST = 7;
    public static final int ITEM_XJ_INFO = 11;
    public static final String TAG = "CanRZygCarInfoActivity";
    private int mFirstV = -1;
    private CanItemIcoList mItemAmpInfo;
    private CanItemIcoList mItemAutoRmtBoot;
    private CanItemIcoList mItemBrake;
    private CanItemIcoList mItemCarType;
    private CanItemIcoList mItemCompass;
    private CanItemIcoList mItemDisplay;
    private CanItemIcoList mItemEngineOff;
    private CanItemIcoList mItemLight;
    private CanItemIcoList mItemLock;
    private CanItemIcoList mItemSafeAssist;
    private CanItemIcoList mItemXjInfo;
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
        GetAdtData();
        if (!i2b(this.mAdtData.UpdateOnce)) {
            return;
        }
        if (!check || this.mAdtData.Update != 0) {
            this.mAdtData.Update = 0;
            LayoutUI();
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query2(11);
        Sleep(5);
        Query2(12);
        Sleep(5);
        Query2(2);
        Sleep(5);
        Query2(3);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        Log.d("CanRZygCarInfoActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanRZygCarInfoActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCarType = AddIcoItem(R.drawable.can_icon_esc, R.string.can_car_type_select, 1);
        this.mItemDisplay = AddIcoItem(R.drawable.can_icon_setup, R.string.can_display, 2);
        this.mItemLight = AddIcoItem(R.drawable.can_icon_light, R.string.can_light_setup, 3);
        this.mItemLock = AddIcoItem(R.drawable.can_icon_lock, R.string.can_door_lock_set, 4);
        this.mItemEngineOff = AddIcoItem(R.drawable.can_icon_driver_assist, R.string.can_fdjgbxx, 5);
        this.mItemAutoRmtBoot = AddIcoItem(R.drawable.can_icon_rmt_ctl, R.string.can_zdkqssxt, 6);
        this.mItemSafeAssist = AddIcoItem(R.drawable.can_icon_driver_assist, R.string.can_aqhjsfz, 7);
        this.mItemCompass = AddIcoItem(R.drawable.can_icon_compass, R.string.can_compass, 8);
        this.mItemBrake = AddIcoItem(R.drawable.can_icon_service, R.string.can_set_brake, 9);
        this.mItemAmpInfo = AddIcoItem(R.drawable.can_icon_setup, R.string.can_eq, 10);
        this.mItemXjInfo = AddIcoItem(R.drawable.can_icon_tyres, R.string.can_xuanjia, 11);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 11; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 2:
                ret = this.mAdtData.adtDW;
                break;
            case 3:
                ret = this.mAdtData.adtLight;
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
                ret = this.mAdtData.AdtBrk | this.mAdtData.AutoPark;
                break;
            case 10:
                ret = this.mAdtData.AdtAmp;
                break;
            case 11:
                ret = this.mAdtData.adtXJ;
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
                this.mItemAmpInfo.ShowGone(show);
                return;
            case 11:
                this.mItemXjInfo.ShowGone(show);
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
                enterSubWin(CanXbsygCarTypeActivity.class);
                return;
            case 2:
                enterSubWin(CanXbsygSetDiaplayActivity.class);
                return;
            case 3:
                enterSubWin(CanXbsygSetLightActivity.class);
                return;
            case 4:
                enterSubWin(CanXbsygSetLockActivity.class);
                return;
            case 5:
                enterSubWin(CanXbsygSetEngOffActivity.class);
                return;
            case 6:
                enterSubWin(CanXbsygSetAutoRmtBootActivity.class);
                return;
            case 7:
                enterSubWin(CanXbsygSetSafeAssistActivity.class);
                return;
            case 8:
                enterSubWin(CanXbsygSetCompassActivity.class);
                return;
            case 9:
                enterSubWin(CanXbsygSetBrakeActivity.class);
                return;
            case 10:
                enterSubWin(CanXbsygSetAmpActivity.class);
                return;
            case 11:
                enterSubWin(CanXbsygSetXjActivity.class);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public int GetFirstView() {
        Rect scrollBounds = new Rect();
        ((ScrollView) findViewById(R.id.can_comm_scrview)).getHitRect(scrollBounds);
        LinearLayout mLayout = (LinearLayout) findViewById(R.id.can_comm_lineview);
        int total = mLayout.getChildCount();
        for (int i = 0; i < total; i++) {
            View v = mLayout.getChildAt(i);
            if (v.getLocalVisibleRect(scrollBounds)) {
                if (-1 == this.mFirstV) {
                    Log.d("CanRZygCarInfoActivity", "total = " + total + ", first visible = " + ((Integer) v.getTag()));
                }
                return ((Integer) v.getTag()).intValue();
            }
        }
        return -1;
    }
}
