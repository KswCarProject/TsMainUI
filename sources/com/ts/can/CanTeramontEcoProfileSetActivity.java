package com.ts.can;

import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanTeramontEcoProfileSetActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    public static final String ECO_PROFILE = "eco_profile";
    private static final int ITEM_AIR = 2;
    private static final int ITEM_ENGINE = 1;
    private static final int ITEM_FRONT_LIGHT = 4;
    private static final int ITEM_RESET = 3;
    private static final int ITEM_STEERING = 0;
    public static final int PROFILE_ECO = 0;
    public static final int PROFILE_INDIVIDUAL = 3;
    public static final int PROFILE_NORMAL = 1;
    public static final int PROFILE_SPORT = 2;
    private int[] mAirArray = {R.string.can_mode_normal, R.string.can_eco};
    private CanItemPopupList mAirItem;
    private CanDataInfo.GolfSeatDriveProfile mDriveProfile = new CanDataInfo.GolfSeatDriveProfile();
    private int mEcoProfile;
    private int[] mEngineArray = {R.string.can_mode_normal, R.string.can_sport, R.string.can_eco};
    private CanItemPopupList mEngineItem;
    private int[] mFrontLightArray = {R.string.can_mode_normal, R.string.can_sport, R.string.can_eco};
    private CanItemPopupList mFrontLightItem;
    private CanScrollList mManager;
    private int[] mSteeringArray = {R.string.can_mode_normal, R.string.can_sport};
    private CanItemPopupList mSteeringItem;

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.GolfSendCmd(209, item);
                return;
            case 1:
                CanJni.GolfSendCmd(210, item);
                return;
            case 2:
                CanJni.GolfSendCmd(211, item == 0 ? 0 : 2);
                return;
            case 4:
                CanJni.GolfSendCmd(213, item);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                new CanItemMsgBox(1, this, R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.GolfSendCmd(212, 1);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mEcoProfile = getIntent().getIntExtra("eco_profile", 0);
        if (this.mEcoProfile == 0) {
            initCommonItems(R.string.can_mode_normal, R.string.can_eco, R.string.can_eco);
        } else if (this.mEcoProfile == 1) {
            initCommonItems(R.string.can_mode_normal, R.string.can_mode_normal, R.string.can_mode_normal);
        } else if (this.mEcoProfile == 2) {
            initCommonItems(R.string.can_sport, R.string.can_sport, R.string.can_mode_normal);
        } else if (this.mEcoProfile == 3) {
            initEcoIndividual();
        }
    }

    private void initEcoIndividual() {
        this.mSteeringItem = this.mManager.addItemPopupList(R.string.can_teramont_steering, this.mSteeringArray, 0, (CanItemPopupList.onPopItemClick) this);
        this.mEngineItem = this.mManager.addItemPopupList(R.string.can_teramont_engine, this.mEngineArray, 1, (CanItemPopupList.onPopItemClick) this);
        this.mAirItem = this.mManager.addItemPopupList(R.string.can_dfqc_ac, this.mAirArray, 2, (CanItemPopupList.onPopItemClick) this);
        this.mFrontLightItem = this.mManager.addItemPopupList(R.string.can_teramont_front_light, this.mFrontLightArray, 4, (CanItemPopupList.onPopItemClick) this);
        this.mManager.addItemFsSetList(R.string.can_teramont_model_reset, 3, this);
    }

    private void initCommonItems(int steering, int engine, int air) {
        this.mManager.addItemTitleValList(R.string.can_teramont_steering, 0, false, (View.OnClickListener) null).SetVal(steering);
        this.mManager.addItemTitleValList(R.string.can_teramont_engine, 0, false, (View.OnClickListener) null).SetVal(engine);
        this.mManager.addItemTitleValList(R.string.can_dfqc_ac, 0, false, (View.OnClickListener) null).SetVal(air);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        int i = 0;
        if (this.mEcoProfile == 3) {
            CanJni.GolfGetSeatDriveProfile(this.mDriveProfile);
            if (!i2b(this.mDriveProfile.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mDriveProfile.Update)) {
                this.mDriveProfile.Update = 0;
                this.mSteeringItem.SetSel(this.mDriveProfile.IndSteering);
                this.mEngineItem.SetSel(this.mDriveProfile.IndEngine);
                CanItemPopupList canItemPopupList = this.mAirItem;
                if (this.mDriveProfile.IndClimate != 0) {
                    i = 1;
                }
                canItemPopupList.SetSel(i);
                this.mFrontLightItem.SetSel(this.mDriveProfile.FrontLight);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (this.mEcoProfile == 3) {
            CanJni.GolfQuery(64, Can.CAN_CHANA_CS75_WC);
        }
    }
}
