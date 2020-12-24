package com.ts.can.vw.golf.wc;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanGolfWcIndividualDriveProfileSetActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick, UserCallBack, View.OnClickListener {
    private static final int ITEM_ACC = 102;
    private static final int ITEM_AIR = 104;
    private static final int ITEM_BIG_LIGHT = 103;
    private static final int ITEM_DCC = 99;
    private static final int ITEM_ENGINE = 101;
    private static final int ITEM_RESET = 105;
    private static final int ITEM_STEERING = 100;
    public static boolean isUICreated;
    private CanItemPopupList mACCItem;
    private CanItemPopupList mAirItem;
    private CanItemPopupList mBigLightItem;
    private int[] mComNormalSport = {R.string.can_mode_ss, R.string.can_mode_normal, R.string.can_sport};
    private CanItemPopupList mDCCItem;
    private int[] mEcoNormal = {R.string.can_eco, R.string.can_mode_normal};
    private CanItemPopupList mEngineItem;
    private int[] mNormalSportEco = {R.string.can_mode_normal, R.string.can_sport, R.string.can_eco};
    private CanDataInfo.GolfWcDrivingModeSelection mProfile1Data = new CanDataInfo.GolfWcDrivingModeSelection();
    private CanDataInfo.GolfWcDrivingMode2Selection mProfile2Adt = new CanDataInfo.GolfWcDrivingMode2Selection();
    private int[] mSportNormal = {R.string.can_sport, R.string.can_mode_normal};
    private CanItemPopupList mSteeringItem;
    private long mTime;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        isUICreated = true;
        InitIndividualItem();
        this.mTime = GetTickCount();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        isUICreated = false;
    }

    private void InitIndividualItem() {
        setContentView(R.layout.activity_can_comm_list);
        CanScrollList manager = new CanScrollList(this);
        this.mDCCItem = manager.addItemPopupList(0, this.mComNormalSport, 99, (CanItemPopupList.onPopItemClick) this);
        this.mDCCItem.GetBtn().setText("DCC");
        this.mSteeringItem = manager.addItemPopupList(R.string.can_teramont_steering, this.mSportNormal, 100, (CanItemPopupList.onPopItemClick) this);
        this.mEngineItem = manager.addItemPopupList(R.string.can_teramont_engine, this.mNormalSportEco, 101, (CanItemPopupList.onPopItemClick) this);
        this.mACCItem = manager.addItemPopupList(R.string.can_acc_drive, this.mNormalSportEco, 102, (CanItemPopupList.onPopItemClick) this);
        this.mBigLightItem = manager.addItemPopupList(R.string.can_dynamic_bend_light, this.mNormalSportEco, 103, (CanItemPopupList.onPopItemClick) this);
        this.mAirItem = manager.addItemPopupList(R.string.can_dfqc_ac, this.mEcoNormal, 104, (CanItemPopupList.onPopItemClick) this);
        manager.addItemIconList(R.drawable.can_icon_setup, R.string.can_teramont_model_reset, 105, this);
        this.mDCCItem.ShowGone(false);
        this.mSteeringItem.ShowGone(false);
        this.mEngineItem.ShowGone(false);
        this.mACCItem.ShowGone(false);
        this.mBigLightItem.ShowGone(false);
        this.mAirItem.ShowGone(false);
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
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfWcGetDrivingModeSelection(0, this.mProfile1Data);
        CanJni.GolfWcGetDrivingMode2Selection(1, this.mProfile2Adt);
        if (i2b(this.mProfile2Adt.UpdateOnce) && (!check || i2b(this.mProfile2Adt.Update))) {
            this.mProfile2Adt.Update = 0;
            this.mDCCItem.ShowGone(this.mProfile2Adt.Ind_DCC);
            this.mSteeringItem.ShowGone(this.mProfile2Adt.Ind_Steering);
            this.mEngineItem.ShowGone(this.mProfile2Adt.Ind_Qdzz);
            this.mACCItem.ShowGone(this.mProfile2Adt.Ind_ACC);
            this.mBigLightItem.ShowGone(this.mProfile2Adt.Ind_Dtddsd);
            this.mAirItem.ShowGone(this.mProfile2Adt.Ind_Air);
            this.mTime = GetTickCount();
        }
        if (!i2b(this.mProfile1Data.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mProfile1Data.Update)) {
            this.mProfile1Data.Update = 0;
            this.mDCCItem.SetSel(this.mProfile1Data.Ind_DCC);
            this.mSteeringItem.SetSel(this.mProfile1Data.Ind_Steering);
            this.mEngineItem.SetSel(this.mProfile1Data.Ind_Engine);
            this.mACCItem.SetSel(this.mProfile1Data.Ind_ACC);
            this.mBigLightItem.SetSel(this.mProfile1Data.Ind_DynamicBendLighting);
            this.mAirItem.SetSel(this.mProfile1Data.Ind_AirCondition);
            this.mTime = GetTickCount();
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 134);
        Sleep(5);
        CanJni.GolfWcQueryData(1, 135);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 99:
                CanJni.GolfWcDrivingModeSet(5, 1, item);
                return;
            case 100:
                CanJni.GolfWcDrivingModeSet(5, 6, item);
                return;
            case 101:
                CanJni.GolfWcDrivingModeSet(5, 3, item);
                return;
            case 102:
                CanJni.GolfWcDrivingModeSet(5, 4, item);
                return;
            case 103:
                CanJni.GolfWcDrivingModeSet(5, 2, item);
                return;
            case 104:
                CanJni.GolfWcDrivingModeSet(5, 5, item);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 105:
                new CanItemMsgBox(0, this, R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.GolfWcDrivingModeSet(5, 7, 0);
                    }
                });
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
        if (GetTickCount() > this.mTime + 6000) {
            CanGolfWcSeatDriveProfileActivity.isEnd = true;
            finish();
        }
    }
}
