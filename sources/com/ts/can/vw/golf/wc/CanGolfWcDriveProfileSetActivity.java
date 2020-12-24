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
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGolfWcDriveProfileSetActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick, UserCallBack, View.OnClickListener {
    private static final int ITEM_ACC = 102;
    private static final int ITEM_AIR = 104;
    private static final int ITEM_BIG_LIGHT = 103;
    private static final int ITEM_ENGINE = 101;
    private static final int ITEM_PDQB = 108;
    private static final int ITEM_RESET = 105;
    private static final int ITEM_STEERING = 100;
    private static final int ITEM_WHEEL = 106;
    private static final int ITEM_XPFZ = 107;
    private static final int ITEM_ZCFZ = 109;
    public static boolean isUICreated;
    private CanItemPopupList mACCItem;
    private CanItemPopupList mAirItem;
    private CanItemPopupList mBigLightItem;
    private int[] mEcoNormal = {R.string.can_eco, R.string.can_mode_normal};
    private CanItemPopupList mEngineItem;
    private int[] mNormalCross = {R.string.can_mode_normal, R.string.can_teramont_cross};
    private int[] mNormalSportEco = {R.string.can_mode_normal, R.string.can_sport, R.string.can_eco};
    private CanItemSwitchList mPdqbItem;
    private CanDataInfo.GolfWcDrivingMode2Selection mProfile2Adt = new CanDataInfo.GolfWcDrivingMode2Selection();
    private CanDataInfo.GolfWcDrivingMode2Selection mProfile2Data = new CanDataInfo.GolfWcDrivingMode2Selection();
    private int[] mSportNormal = {R.string.can_sport, R.string.can_mode_normal};
    private CanItemPopupList mSteeringItem;
    private long mTime;
    private CanItemPopupList mWheelItem;
    private CanItemSwitchList mXpfzItem;
    private CanItemSwitchList mZcfzItem;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        isUICreated = true;
        InitCrossInItem();
        this.mTime = GetTickCount();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        isUICreated = false;
    }

    private void InitCrossInItem() {
        setContentView(R.layout.activity_can_comm_list);
        CanScrollList manager = new CanScrollList(this);
        this.mBigLightItem = manager.addItemPopupList(R.string.can_dynamic_bend_light, this.mNormalSportEco, 103, (CanItemPopupList.onPopItemClick) this);
        this.mEngineItem = manager.addItemPopupList(R.string.can_teramont_engine, this.mNormalCross, 101, (CanItemPopupList.onPopItemClick) this);
        this.mACCItem = manager.addItemPopupList(R.string.can_acc_drive, this.mNormalSportEco, 102, (CanItemPopupList.onPopItemClick) this);
        this.mAirItem = manager.addItemPopupList(R.string.can_dfqc_ac, this.mEcoNormal, 104, (CanItemPopupList.onPopItemClick) this);
        this.mSteeringItem = manager.addItemPopupList(R.string.can_teramont_steering, this.mSportNormal, 100, (CanItemPopupList.onPopItemClick) this);
        this.mWheelItem = manager.addItemPopupList(R.string.can_teramont_wheelengine, this.mNormalCross, 106, (CanItemPopupList.onPopItemClick) this);
        this.mXpfzItem = manager.addItemCheckBox(R.string.can_teramont_xpfzxt, 107, this);
        this.mPdqbItem = manager.addItemCheckBox(R.string.can_teramont_pdqbfz, 108, this);
        this.mZcfzItem = manager.addItemCheckBox(R.string.can_teramont_zcfz, 109, this);
        manager.addItemIconList(R.drawable.can_icon_setup, R.string.can_teramont_model_reset, 105, this);
        this.mBigLightItem.ShowGone(false);
        this.mEngineItem.ShowGone(false);
        this.mACCItem.ShowGone(false);
        this.mAirItem.ShowGone(false);
        this.mSteeringItem.ShowGone(false);
        this.mWheelItem.ShowGone(false);
        this.mXpfzItem.ShowGone(false);
        this.mPdqbItem.ShowGone(false);
        this.mZcfzItem.ShowGone(false);
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
        CanJni.GolfWcGetDrivingMode2Selection(1, this.mProfile2Adt);
        CanJni.GolfWcGetDrivingMode2Selection(0, this.mProfile2Data);
        if (i2b(this.mProfile2Adt.UpdateOnce) && (!check || i2b(this.mProfile2Adt.Update))) {
            this.mProfile2Adt.Update = 0;
            this.mBigLightItem.ShowGone(this.mProfile2Adt.OffRoadInd_Dtddsd);
            this.mEngineItem.ShowGone(this.mProfile2Adt.OffRoadInd_Qdzz);
            this.mACCItem.ShowGone(this.mProfile2Adt.OffRoadInd_ACC);
            this.mAirItem.ShowGone(this.mProfile2Adt.OffRoadInd_Air);
            this.mSteeringItem.ShowGone(this.mProfile2Adt.OffRoadInd_Steering);
            this.mWheelItem.ShowGone(this.mProfile2Adt.OffRoadInd_FourWheelDrive);
            this.mXpfzItem.ShowGone(this.mProfile2Adt.OffRoadInd_Xpfzxt);
            this.mPdqbItem.ShowGone(this.mProfile2Adt.OffRoadInd_Pdqbfz);
            this.mZcfzItem.ShowGone(this.mProfile2Adt.OffRoadInd_Czfz);
            this.mTime = GetTickCount();
        }
        if (!i2b(this.mProfile2Data.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mProfile2Data.Update)) {
            this.mProfile2Data.Update = 0;
            this.mBigLightItem.SetSel(this.mProfile2Data.OffRoadInd_Dtddsd);
            this.mEngineItem.SetSel(this.mProfile2Data.OffRoadInd_Qdzz);
            this.mACCItem.SetSel(this.mProfile2Data.OffRoadInd_ACC);
            this.mAirItem.SetSel(this.mProfile2Data.OffRoadInd_Air);
            this.mSteeringItem.SetSel(this.mProfile2Data.OffRoadInd_Steering);
            this.mWheelItem.SetSel(this.mProfile2Data.OffRoadInd_FourWheelDrive);
            this.mXpfzItem.SetCheck(this.mProfile2Data.OffRoadInd_Xpfzxt);
            this.mPdqbItem.SetCheck(this.mProfile2Data.OffRoadInd_Pdqbfz);
            this.mZcfzItem.SetCheck(this.mProfile2Data.OffRoadInd_Czfz);
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
            case 100:
                CanJni.GolfWcDrivingModeSet(9, 1, item);
                return;
            case 101:
                CanJni.GolfWcDrivingModeSet(9, 2, item);
                return;
            case 102:
                CanJni.GolfWcDrivingModeSet(9, 4, item);
                return;
            case 103:
                CanJni.GolfWcDrivingModeSet(9, 5, item);
                return;
            case 104:
                CanJni.GolfWcDrivingModeSet(9, 6, item);
                return;
            case 106:
                CanJni.GolfWcDrivingModeSet(9, 3, item);
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
                        CanJni.GolfWcDrivingModeSet(9, 10, 0);
                    }
                });
                return;
            case 107:
                CanJni.GolfWcDrivingModeSet(9, 7, Neg(this.mProfile2Data.OffRoadInd_Xpfzxt));
                return;
            case 108:
                CanJni.GolfWcDrivingModeSet(9, 8, Neg(this.mProfile2Data.OffRoadInd_Pdqbfz));
                return;
            case 109:
                CanJni.GolfWcDrivingModeSet(9, 9, Neg(this.mProfile2Data.OffRoadInd_Czfz));
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
