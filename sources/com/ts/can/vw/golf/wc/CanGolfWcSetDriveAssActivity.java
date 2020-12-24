package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetDriveAssActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick, View.OnClickListener, UserCallBack {
    public static final int ITEM_ACC_DISTANCE = 4;
    public static final int ITEM_ACC_DRIVER_PROG = 2;
    public static final int ITEM_ACC_LAST_SELECT = 3;
    public static final int ITEM_ACC_TITLE = 1;
    public static final int ITEM_ADAPTIVE_LANE_ACTIVE = 11;
    public static final int ITEM_ADAPTIVE_LANE_TITLE = 10;
    public static final int ITEM_ADV_WARN_MENU = 9;
    public static final int ITEM_BLIND_SPOT = 16;
    public static final int ITEM_DAS_TITLE = 14;
    public static final int ITEM_DRIVER_ALERT_SYS = 15;
    public static final int ITEM_FA_ACTIVE = 6;
    public static final int ITEM_FA_ADVANCE_WARN = 7;
    public static final int ITEM_FA_DISPLAY_WARN = 8;
    public static final int ITEM_FA_TITLE = 5;
    public static final int ITEM_LANE_ACTIVE = 20;
    public static final int ITEM_LANE_TITLE = 19;
    private static final int ITEM_MAX = 20;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_PRO_PROTECTION_ACTIVE = 18;
    public static final int ITEM_PRO_PROTECTION_TITLE = 17;
    public static final int ITEM_SHOW_IN_MFD = 13;
    public static final int ITEM_TRAFFIC_SIGN_REC_TITLE = 12;
    private static final int[] mMenuAccDistance = {R.string.can_very_close, R.string.can_close, R.string.can_medium_z_d, R.string.can_far, R.string.can_very_far};
    private static final int[] mMenuAccProgram = {R.string.can_eco, R.string.can_mode_normal, R.string.can_sport};
    private static final int[] mMenuAdvWarning = {R.string.can_trunk_close, R.string.can_early, R.string.can_medium_s_s, R.string.can_late};
    private CanDataInfo.GolfWcAssist mAssistAdt = new CanDataInfo.GolfWcAssist();
    private CanDataInfo.GolfWcAssist mAssistInfo = new CanDataInfo.GolfWcAssist();
    private CanItemBlankTextList mItemACCTitle;
    private CanItemPopupList mItemAccDistance;
    private CanItemPopupList mItemAccDriverProg;
    private CanItemCheckList mItemAccLastSelect;
    private CanItemCheckList mItemAdaptiveLaneActive;
    private CanItemBlankTextList mItemAdaptiveLaneTitle;
    private CanItemPopupList mItemAdvanceWarnMenu;
    private CanItemCheckList mItemBlindSpot;
    private CanItemBlankTextList mItemDASTitle;
    private CanItemCheckList mItemDriverAlertSys;
    private CanItemCheckList mItemFAActive;
    private CanItemCheckList mItemFAAdvanceWarn;
    private CanItemCheckList mItemFADisplayWarn;
    private CanItemBlankTextList mItemFATitle;
    private CanItemCheckList mItemLaneKeepActive;
    private CanItemBlankTextList mItemLaneKeepTitle;
    private CanItemCheckList mItemPractiveProActive;
    private CanItemBlankTextList mItemPractiveProTitle;
    private CanItemCheckList mItemShowInMFD;
    private CanItemBlankTextList mItemTrafficSignTitle;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        boolean showSub;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        CanJni.GolfWcGetAssistData(1, this.mAssistAdt);
        CanJni.GolfWcGetAssistData(0, this.mAssistInfo);
        if (i2b(this.mAssistAdt.UpdateOnce) && (!check || i2b(this.mAssistAdt.Update))) {
            this.mAssistAdt.Update = 0;
            this.mItemAccDriverProg.ShowGone(this.mAssistAdt.ACC);
            this.mItemAccLastSelect.ShowGone(this.mAssistAdt.ACCLastDistanceSelected);
            this.mItemACCTitle.ShowGone((this.mAssistAdt.ACC == 0 && this.mAssistAdt.ACCLastDistanceSelected == 0) ? false : true);
            this.mItemFAActive.ShowGone(this.mAssistAdt.FrontAssistActive);
            this.mItemFAAdvanceWarn.ShowGone(this.mAssistAdt.FrontAssistAdvanceWarning);
            this.mItemFADisplayWarn.ShowGone(this.mAssistAdt.FrontAssistDisplayDistanceWarming);
            CanItemBlankTextList canItemBlankTextList = this.mItemFATitle;
            if (this.mAssistAdt.FrontAssistActive == 0 && this.mAssistAdt.FrontAssistAdvanceWarning == 0 && this.mAssistAdt.FrontAssistDisplayDistanceWarming == 0) {
                z3 = false;
            } else {
                z3 = true;
            }
            canItemBlankTextList.ShowGone(z3);
            this.mItemAdaptiveLaneTitle.ShowGone(this.mAssistAdt.AdaptiveLaneGuidance);
            this.mItemAdaptiveLaneActive.ShowGone(this.mAssistAdt.AdaptiveLaneGuidance);
            this.mItemTrafficSignTitle.ShowGone(this.mAssistAdt.ShowInMFD);
            this.mItemShowInMFD.ShowGone(this.mAssistAdt.ShowInMFD);
            this.mItemDriverAlertSys.ShowGone(this.mAssistAdt.DriverAlertSystemActive);
            this.mItemBlindSpot.ShowGone(this.mAssistAdt.DriverAlertSystemMqjk);
            CanItemBlankTextList canItemBlankTextList2 = this.mItemDASTitle;
            if (this.mAssistAdt.DriverAlertSystemActive == 0 && this.mAssistAdt.DriverAlertSystemMqjk == 0) {
                z4 = false;
            } else {
                z4 = true;
            }
            canItemBlankTextList2.ShowGone(z4);
            this.mItemPractiveProTitle.ShowGone(this.mAssistAdt.ProactiveOccupationActive);
            this.mItemPractiveProActive.ShowGone(this.mAssistAdt.ProactiveOccupationActive);
            this.mItemLaneKeepTitle.ShowGone(this.mAssistAdt.LoadKeepActive);
            this.mItemLaneKeepActive.ShowGone(this.mAssistAdt.LoadKeepActive);
        }
        if (!i2b(this.mAssistInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAssistInfo.Update)) {
            this.mAssistInfo.Update = 0;
            this.mItemAccDriverProg.SetSel(this.mAssistInfo.ACC);
            this.mItemAccLastSelect.SetCheck(this.mAssistInfo.ACCLastDistanceSelected);
            this.mItemAccDistance.SetSel(this.mAssistInfo.ACCDistanceVal);
            this.mItemAccDistance.ShowGone(this.mAssistAdt.ACCLastDistanceSelected == 1 && this.mAssistInfo.ACCLastDistanceSelected == 0);
            this.mItemFAActive.SetCheck(this.mAssistInfo.FrontAssistActive);
            if (!i2b(this.mAssistAdt.FrontAssistActive) || !i2b(this.mAssistInfo.FrontAssistActive)) {
                showSub = false;
            } else {
                showSub = true;
            }
            CanItemCheckList canItemCheckList = this.mItemFAAdvanceWarn;
            if (!i2b(this.mAssistAdt.FrontAssistAdvanceWarning) || !showSub) {
                z = false;
            } else {
                z = true;
            }
            canItemCheckList.ShowGone(z);
            CanItemCheckList canItemCheckList2 = this.mItemFADisplayWarn;
            if (!i2b(this.mAssistAdt.FrontAssistDisplayDistanceWarming) || !showSub) {
                z2 = false;
            } else {
                z2 = true;
            }
            canItemCheckList2.ShowGone(z2);
            this.mItemFAAdvanceWarn.SetCheck(this.mAssistInfo.FrontAssistAdvanceWarning);
            this.mItemFADisplayWarn.SetCheck(this.mAssistInfo.FrontAssistDisplayDistanceWarming);
            this.mItemAdvanceWarnMenu.SetSel(this.mAssistInfo.FrontAssistAdvanceWarmingVal);
            CanItemPopupList canItemPopupList = this.mItemAdvanceWarnMenu;
            if (!i2b(this.mAssistAdt.FrontAssistAdvanceWarning) || !showSub || this.mAssistInfo.FrontAssistAdvanceWarning != 1) {
                z5 = false;
            }
            canItemPopupList.ShowGone(z5);
            this.mItemAdaptiveLaneActive.SetCheck(this.mAssistInfo.AdaptiveLaneGuidance);
            this.mItemShowInMFD.SetCheck(this.mAssistInfo.ShowInMFD);
            this.mItemDriverAlertSys.SetCheck(this.mAssistInfo.DriverAlertSystemActive);
            this.mItemBlindSpot.SetCheck(this.mAssistInfo.DriverAlertSystemMqjk);
            this.mItemPractiveProActive.SetCheck(this.mAssistInfo.ProactiveOccupationActive);
            this.mItemLaneKeepActive.SetCheck(this.mAssistInfo.LoadKeepActive);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 71);
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

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        for (int i = 1; i <= 20; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void InitItem(int item) {
        switch (item) {
            case 1:
                this.mItemACCTitle = AddTitleItem(R.string.can_acc_drive);
                return;
            case 2:
                this.mItemAccDriverProg = AddPopupItem(R.string.can_drive_program, item, mMenuAccProgram);
                return;
            case 3:
                this.mItemAccLastSelect = AddCheckItem(R.string.can_last_dis_selected, item);
                return;
            case 4:
                this.mItemAccDistance = AddPopupItem(R.string.can_distance_c_j, item, mMenuAccDistance);
                return;
            case 5:
                this.mItemFATitle = AddTitleItem(R.string.can_front_assist);
                return;
            case 6:
                this.mItemFAActive = AddCheckItem(R.string.can_active, item);
                return;
            case 7:
                this.mItemFAAdvanceWarn = AddCheckItem(R.string.can_advance_warn, item);
                return;
            case 8:
                this.mItemFADisplayWarn = AddCheckItem(R.string.can_dis_warning, item);
                return;
            case 9:
                this.mItemAdvanceWarnMenu = AddPopupItem(R.string.can_advance_warn, item, mMenuAdvWarning);
                return;
            case 10:
                this.mItemAdaptiveLaneTitle = AddTitleItem(R.string.can_adaptive_lang);
                return;
            case 11:
                this.mItemAdaptiveLaneActive = AddCheckItem(R.string.can_active, item);
                return;
            case 12:
                this.mItemTrafficSignTitle = AddTitleItem(R.string.can_traffice_sign_rec);
                return;
            case 13:
                this.mItemShowInMFD = AddCheckItem(R.string.can_show_in_mfd, item);
                return;
            case 14:
                this.mItemDASTitle = AddTitleItem(R.string.can_drive_alert_sys);
                return;
            case 15:
                this.mItemDriverAlertSys = AddCheckItem(R.string.can_drive_alert_sys, item);
                return;
            case 16:
                this.mItemBlindSpot = AddCheckItem(R.string.can_blind_spot_monitoring, item);
                return;
            case 17:
                this.mItemPractiveProTitle = AddTitleItem(R.string.can_proactive_protection);
                return;
            case 18:
                this.mItemPractiveProActive = AddCheckItem(R.string.can_active, item);
                return;
            case 19:
                this.mItemLaneKeepTitle = AddTitleItem(R.string.can_lane_assist);
                return;
            case 20:
                this.mItemLaneKeepActive = AddCheckItem(R.string.can_active, item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemBlankTextList AddTitleItem(int resId) {
        CanItemBlankTextList item = new CanItemBlankTextList((Context) this, resId);
        item.ShowGone(false);
        this.mManager.AddView(item.GetView());
        return item;
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
    public CanItemPopupList AddPopupItem(int resId, int Id, int[] arry) {
        CanItemPopupList item = new CanItemPopupList((Context) this, resId, arry, (CanItemPopupList.onPopItemClick) this);
        item.SetId(Id);
        item.ShowGone(false);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                CanJni.GolfWcAssistSet(11, Neg(this.mAssistInfo.ACCLastDistanceSelected));
                return;
            case 6:
                CanJni.GolfWcAssistSet(1, Neg(this.mAssistInfo.FrontAssistActive));
                return;
            case 7:
                CanJni.GolfWcAssistSet(2, Neg(this.mAssistInfo.FrontAssistAdvanceWarning));
                return;
            case 8:
                CanJni.GolfWcAssistSet(3, Neg(this.mAssistInfo.FrontAssistDisplayDistanceWarming));
                return;
            case 11:
                CanJni.GolfWcAssistSet(4, Neg(this.mAssistInfo.AdaptiveLaneGuidance));
                return;
            case 13:
                CanJni.GolfWcAssistSet(5, Neg(this.mAssistInfo.ShowInMFD));
                return;
            case 15:
                CanJni.GolfWcAssistSet(6, Neg(this.mAssistInfo.DriverAlertSystemActive));
                return;
            case 16:
                CanJni.GolfWcAssistSet(13, Neg(this.mAssistInfo.DriverAlertSystemMqjk));
                return;
            case 18:
                CanJni.GolfWcAssistSet(7, Neg(this.mAssistInfo.ProactiveOccupationActive));
                return;
            case 20:
                CanJni.GolfWcAssistSet(10, Neg(this.mAssistInfo.LoadKeepActive));
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 2:
                CanJni.GolfWcAssistSet(9, item + 1);
                return;
            case 4:
                CanJni.GolfWcAssistSet(8, item + 1);
                return;
            case 9:
                CanJni.GolfWcAssistSet(12, item);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
