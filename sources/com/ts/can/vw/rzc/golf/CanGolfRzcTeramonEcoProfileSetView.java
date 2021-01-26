package com.ts.can.vw.rzc.golf;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanGolfRzcTeramonEcoProfileSetView extends CanRelativeCarInfoView implements CanItemPopupList.onPopItemClick {
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
    private int[] mAirArray;
    private CanItemPopupList mAirItem;
    private CanDataInfo.GolfSeatDriveProfile mDriveProfile;
    private int mEcoProfile;
    private int[] mEngineArray;
    private CanItemPopupList mEngineItem;
    private int[] mFrontLightArray;
    private CanItemPopupList mFrontLightItem;
    private CanScrollList mManager;
    private int[] mSteeringArray;
    private CanItemPopupList mSteeringItem;

    public CanGolfRzcTeramonEcoProfileSetView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        getActivity().setContentView(R.layout.activity_can_comm_list);
        this.mEngineArray = new int[]{R.string.can_mode_normal, R.string.can_sport, R.string.can_eco};
        this.mFrontLightArray = new int[]{R.string.can_mode_normal, R.string.can_sport, R.string.can_eco};
        this.mSteeringArray = new int[]{R.string.can_mode_normal, R.string.can_sport};
        this.mAirArray = new int[]{R.string.can_mode_normal, R.string.can_eco};
        this.mDriveProfile = new CanDataInfo.GolfSeatDriveProfile();
        this.mManager = new CanScrollList(getActivity());
        this.mEcoProfile = getActivity().getIntent().getIntExtra("eco_profile", 0);
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

    public void doOnResume() {
        super.doOnResume();
        ResetData(false);
        QueryData();
    }

    public void doOnPause() {
        super.doOnPause();
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                new CanItemMsgBox(1, getActivity(), R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.GolfSendCmd(212, 1);
                    }
                });
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

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

    public void QueryData() {
        if (this.mEcoProfile == 3) {
            CanJni.GolfQuery(64, 160);
        }
    }

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
}
