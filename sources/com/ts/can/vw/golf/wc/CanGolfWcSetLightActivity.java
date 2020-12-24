package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetLightActivity extends CanBaseActivity implements View.OnClickListener, CanItemProgressList.onPosChange, CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_ALL_LIGHT = 18;
    public static final int ITEM_AUTO_HEADLIGHT = 4;
    public static final int ITEM_BACKGROUND_LIGHT_COLOR = 14;
    public static final int ITEM_CAMERA_BRI = 20;
    public static final int ITEM_CAMERA_COL = 22;
    public static final int ITEM_CAMERA_CON = 21;
    public static final int ITEM_CAMERA_TITLE = 19;
    public static final int ITEM_CAR_INNER_LIGHT = 15;
    public static final int ITEM_COMMING_FUNC = 11;
    public static final int ITEM_DAYTIME_LIGHT = 7;
    public static final int ITEM_DOOR_LIGHT = 9;
    public static final int ITEM_DYNAMIC_BEND_LIGHT = 2;
    public static final int ITEM_DYNAMIC_LIGHT = 1;
    public static final int ITEM_FOOTWELL_LIGHT = 10;
    public static final int ITEM_INSTRUMENT_LIGHT = 8;
    public static final int ITEM_LANE_ASS_SYS_LIGHT = 17;
    public static final int ITEM_LANE_FLASH = 5;
    public static final int ITEM_LEAVING_FUNC = 12;
    public static final int ITEM_LIGHT_DISTANCE = 13;
    private static final int ITEM_MAX = 22;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_RIGHT_FRONT_LIGHT = 16;
    public static final int ITEM_SWITCH_TIME = 3;
    public static final int ITEM_TRAVEL_MODE = 6;
    private static final int[] mMenuLightColor = {R.string.can_magoten_light_color_0, R.string.can_magoten_light_color_4, R.string.can_orange_color, R.string.can_magoten_light_color_3, R.string.can_color_asbh, R.string.can_color_hjl, R.string.can_color_stl, R.string.can_color_albs, R.string.can_color_ctjl, R.string.can_color_chuang, R.string.can_color_chong, R.string.can_color_zhs, R.string.can_color_zls, R.string.can_color_qhs};
    private static final int[] mMenuSwitchTime = {R.string.can_early, R.string.can_medium_s_s, R.string.can_late};
    private static final int[] mMenuTravelMode = {R.string.can_drive_left, R.string.can_drive_right};
    private CanDataInfo.GolfWcCameraSta mCameraData = new CanDataInfo.GolfWcCameraSta();
    private CanItemProgressList mItemAllLight;
    private CanItemCheckList mItemAutoHeadLight;
    private CanItemProgressList mItemCameraBri;
    private CanItemProgressList mItemCameraCol;
    private CanItemProgressList mItemCameraCon;
    private CanItemBlankTextList mItemCameraTitle;
    private CanItemProgressList mItemCommingFunc;
    private CanItemCheckList mItemDaytimeLight;
    private CanItemProgressList mItemDoorLight;
    private CanItemCheckList mItemDynamicBendLight;
    private CanItemCheckList mItemDynamicLight;
    private CanItemProgressList mItemFootwellLight;
    private CanItemProgressList mItemInnerLight;
    private CanItemProgressList mItemInstrumentLight;
    private CanItemProgressList mItemLaneAssSysLight;
    private CanItemCheckList mItemLaneFlash;
    private CanItemProgressList mItemLeavingFunc;
    private CanItemPopupList mItemLightColor;
    private CanItemProgressList mItemLightDistance;
    private CanItemProgressList mItemRightFrontLight;
    private CanItemPopupList mItemSwitchTime;
    private CanItemPopupList mItemTravelMode;
    private CanDataInfo.GolfWcLight mLight1Adt = new CanDataInfo.GolfWcLight();
    private CanDataInfo.GolfWcLight mLight1Data = new CanDataInfo.GolfWcLight();
    private CanDataInfo.GolfWcLight2 mLight2Adt = new CanDataInfo.GolfWcLight2();
    private CanDataInfo.GolfWcLight2 mLight2Data = new CanDataInfo.GolfWcLight2();
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfWcGetLightData(1, this.mLight1Adt);
        CanJni.GolfWcGetLightData(0, this.mLight1Data);
        CanJni.GolfWcGetLight2Data(1, this.mLight2Adt);
        CanJni.GolfWcGetLight2Data(0, this.mLight2Data);
        if (i2b(this.mLight1Adt.UpdateOnce) && (!check || i2b(this.mLight1Adt.Update))) {
            this.mLight1Adt.Update = 0;
            this.mItemDynamicLight.ShowGone(this.mLight1Adt.DynamicLightAssist);
            this.mItemDynamicBendLight.ShowGone(this.mLight1Adt.DynamicBendLight);
            this.mItemSwitchTime.ShowGone(this.mLight1Adt.SwithOnTime);
            this.mItemAutoHeadLight.ShowGone(this.mLight1Adt.AutoHeadlightControl);
            this.mItemLaneFlash.ShowGone(this.mLight1Adt.LaneChangeFlash);
            this.mItemTravelMode.ShowGone(this.mLight1Adt.TravelMode);
            this.mItemDaytimeLight.ShowGone(this.mLight1Adt.DaytimeLight);
            this.mItemInstrumentLight.ShowGone(this.mLight1Adt.Instrument);
            this.mItemDoorLight.ShowGone(this.mLight1Adt.DoorBackgroundlight);
            this.mItemFootwellLight.ShowGone(this.mLight1Adt.FootwellLighting);
            this.mItemCommingFunc.ShowGone(this.mLight1Adt.ComingHomeFunction);
            this.mItemLeavingFunc.ShowGone(this.mLight1Adt.LeavingHomeFunction);
        }
        if (i2b(this.mLight2Adt.UpdateOnce) && (!check || i2b(this.mLight2Adt.Update))) {
            this.mLight2Adt.Update = 0;
            this.mItemLightDistance.ShowGone(this.mLight2Adt.Ddzmjltj);
            this.mItemLightColor.ShowGone(this.mLight2Adt.Hjzmys);
            this.mItemInnerLight.ShowGone(this.mLight2Adt.Cnfwzm);
            this.mItemRightFrontLight.ShowGone(this.mLight2Adt.Yqstzm);
            this.mItemLaneAssSysLight.ShowGone(this.mLight2Adt.Bdfzxtld);
            this.mItemAllLight.ShowGone(this.mLight2Adt.Qbldtj);
        }
        if (i2b(this.mLight1Data.UpdateOnce) && (!check || i2b(this.mLight1Data.Update))) {
            this.mLight1Data.Update = 0;
            this.mItemDynamicLight.SetCheck(this.mLight1Data.DynamicLightAssist);
            this.mItemDynamicBendLight.SetCheck(this.mLight1Data.DynamicBendLight);
            this.mItemSwitchTime.SetSel(this.mLight1Data.SwithOnTime);
            this.mItemAutoHeadLight.SetCheck(this.mLight1Data.AutoHeadlightControl);
            this.mItemLaneFlash.SetCheck(this.mLight1Data.LaneChangeFlash);
            this.mItemTravelMode.SetSel(this.mLight1Data.TravelMode);
            this.mItemDaytimeLight.SetCheck(this.mLight1Data.DaytimeLight);
            this.mItemInstrumentLight.SetCurVal(this.mLight1Data.Instrument);
            if (this.mLight1Data.Instrument == 0) {
                this.mItemInstrumentLight.SetValText(R.string.can_min_value);
            } else {
                this.mItemInstrumentLight.SetValText(String.valueOf(this.mLight1Data.Instrument) + "%");
            }
            this.mItemDoorLight.SetCurVal(this.mLight1Data.DoorBackgroundlight);
            if (this.mLight1Data.DoorBackgroundlight == 0) {
                this.mItemDoorLight.SetValText(R.string.can_min_value);
            } else {
                this.mItemDoorLight.SetValText(String.valueOf(this.mLight1Data.DoorBackgroundlight) + "%");
            }
            this.mItemFootwellLight.SetCurVal(this.mLight1Data.FootwellLighting);
            if (this.mLight1Data.FootwellLighting == 0) {
                this.mItemFootwellLight.SetValText(R.string.can_min_value);
            } else {
                this.mItemFootwellLight.SetValText(String.valueOf(this.mLight1Data.FootwellLighting) + "%");
            }
            this.mItemCommingFunc.SetCurVal(this.mLight1Data.ComingHomeFunction);
            if (this.mLight1Data.ComingHomeFunction == 0) {
                this.mItemCommingFunc.SetValText(R.string.can_off);
            } else {
                this.mItemCommingFunc.SetValText(String.valueOf(this.mLight1Data.ComingHomeFunction) + "s");
            }
            this.mItemLeavingFunc.SetCurVal(this.mLight1Data.LeavingHomeFunction);
            if (this.mLight1Data.LeavingHomeFunction == 0) {
                this.mItemLeavingFunc.SetValText(R.string.can_off);
            } else {
                this.mItemLeavingFunc.SetValText(String.valueOf(this.mLight1Data.LeavingHomeFunction) + "s");
            }
        }
        if (i2b(this.mLight2Data.UpdateOnce) && (!check || i2b(this.mLight2Data.Update))) {
            this.mLight2Data.Update = 0;
            this.mItemLightDistance.SetCurVal(this.mLight2Data.Ddzmjltj);
            if (this.mLight2Data.Ddzmjltj == 0) {
                this.mItemLightDistance.SetValText(R.string.can_off);
            } else {
                this.mItemLightDistance.SetValText(new StringBuilder(String.valueOf(this.mLight2Data.Ddzmjltj)).toString());
            }
            this.mItemLightColor.SetSel(this.mLight2Data.Hjzmys);
            this.mItemInnerLight.SetCurVal(this.mLight2Data.Cnfwzm);
            if (this.mLight2Data.Cnfwzm == 0) {
                this.mItemInnerLight.SetValText(R.string.can_min_value);
            } else {
                this.mItemInnerLight.SetValText(String.valueOf(this.mLight2Data.Cnfwzm) + "%");
            }
            this.mItemRightFrontLight.SetCurVal(this.mLight2Data.Yqstzm);
            if (this.mLight2Data.Yqstzm == 0) {
                this.mItemRightFrontLight.SetValText(R.string.can_min_value);
            } else {
                this.mItemRightFrontLight.SetValText(String.valueOf(this.mLight2Data.Yqstzm) + "%");
            }
            this.mItemLaneAssSysLight.SetCurVal(this.mLight2Data.Bdfzxtld);
            this.mItemLaneAssSysLight.SetValText(String.valueOf(this.mLight2Data.Bdfzxtld));
            this.mItemAllLight.SetCurVal(this.mLight2Data.Qbldtj);
            this.mItemAllLight.SetValText(String.valueOf(this.mLight2Data.Qbldtj) + "%");
        }
        CanJni.GolfWcGetCameraSta(this.mCameraData);
        if (!i2b(this.mCameraData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCameraData.Update)) {
            this.mCameraData.Update = 0;
            this.mItemCameraBri.SetCurVal(this.mCameraData.Bright);
            this.mItemCameraBri.SetValText(new StringBuilder(String.valueOf(this.mCameraData.Bright)).toString());
            this.mItemCameraCon.SetCurVal(this.mCameraData.Contrast);
            this.mItemCameraCon.SetValText(new StringBuilder(String.valueOf(this.mCameraData.Contrast)).toString());
            this.mItemCameraCol.SetCurVal(this.mCameraData.Color);
            this.mItemCameraCol.SetValText(new StringBuilder(String.valueOf(this.mCameraData.Color)).toString());
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 103);
        Sleep(5);
        CanJni.GolfWcQueryData(1, 104);
        Sleep(5);
        CanJni.GolfWcQueryData(1, Can.CAN_FLAT_WC);
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
        for (int i = 1; i <= 22; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void InitItem(int item) {
        switch (item) {
            case 1:
                this.mItemDynamicLight = AddCheckItem(R.string.can_dynamic_light_assist, item);
                return;
            case 2:
                this.mItemDynamicBendLight = AddCheckItem(R.string.can_dynamic_bend_light, item);
                return;
            case 3:
                this.mItemSwitchTime = AddPopupItem(R.string.can_sw_on_time, item, mMenuSwitchTime);
                return;
            case 4:
                this.mItemAutoHeadLight = AddCheckItem(R.string.can_headlight_in_rain, item);
                return;
            case 5:
                this.mItemLaneFlash = AddCheckItem(R.string.can_lane_flash, item);
                return;
            case 6:
                this.mItemTravelMode = AddPopupItem(R.string.can_trip_mode, item, mMenuTravelMode);
                return;
            case 7:
                this.mItemDaytimeLight = AddCheckItem(R.string.can_rjxcd, item);
                return;
            case 8:
                this.mItemInstrumentLight = AddProgressItem(R.string.can_ins_sw_light, item, 0, 100, 10);
                return;
            case 9:
                this.mItemDoorLight = AddProgressItem(R.string.can_evt_light, item, 0, 100, 10);
                return;
            case 10:
                this.mItemFootwellLight = AddProgressItem(R.string.can_foot_light, item, 0, 100, 10);
                return;
            case 11:
                this.mItemCommingFunc = AddProgressItem(R.string.can_coming_func, item, 0, 30, 5);
                return;
            case 12:
                this.mItemLeavingFunc = AddProgressItem(R.string.can_leaving_func, item, 0, 30, 5);
                return;
            case 13:
                this.mItemLightDistance = AddProgressItem(R.string.can_light_distacne, item, 0, 6, 1);
                return;
            case 14:
                this.mItemLightColor = AddPopupItem(R.string.can_magoten_bg_light_color, item, mMenuLightColor);
                return;
            case 15:
                this.mItemInnerLight = AddProgressItem(R.string.can_car_inner_light, item, 0, 100, 10);
                return;
            case 16:
                this.mItemRightFrontLight = AddProgressItem(R.string.can_right_front_light, item, 0, 100, 10);
                return;
            case 17:
                this.mItemLaneAssSysLight = AddProgressItem(R.string.can_bdfzxtld, item, 1, 5, 1);
                return;
            case 18:
                this.mItemAllLight = AddProgressItem(R.string.can_all_light, item, 0, 100, 10);
                return;
            case 19:
                this.mItemCameraTitle = AddItemBlankTextList(R.string.can_camera_set);
                this.mItemCameraTitle.ShowGone(true);
                return;
            case 20:
                this.mItemCameraBri = AddProgressItem(R.string.can_mzd_cx4_drive_light, item, 30, 70, 1);
                this.mItemCameraBri.ShowGone(true);
                return;
            case 21:
                this.mItemCameraCon = AddProgressItem(R.string.can_con, item, 30, 70, 1);
                this.mItemCameraCon.ShowGone(true);
                return;
            case 22:
                this.mItemCameraCol = AddProgressItem(R.string.can_sat, item, 30, 70, 1);
                this.mItemCameraCol.ShowGone(true);
                return;
            default:
                return;
        }
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
    public CanItemProgressList AddProgressItem(int resId, int Id, int min, int max, int step) {
        CanItemProgressList item = new CanItemProgressList((Context) this, resId);
        item.SetIdCallBack(Id, this);
        item.SetMinMax(min, max);
        item.SetStep(step);
        item.SetUserValText();
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

    /* access modifiers changed from: protected */
    public CanItemBlankTextList AddItemBlankTextList(int resId) {
        CanItemBlankTextList item = new CanItemBlankTextList((Context) this, resId);
        item.ShowGone(false);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GolfWcLightSet(1, Neg(this.mLight1Data.DynamicLightAssist));
                return;
            case 2:
                CanJni.GolfWcLightSet(2, Neg(this.mLight1Data.DynamicBendLight));
                return;
            case 4:
                CanJni.GolfWcLightSet(4, Neg(this.mLight1Data.AutoHeadlightControl));
                return;
            case 5:
                CanJni.GolfWcLightSet(5, Neg(this.mLight1Data.LaneChangeFlash));
                return;
            case 7:
                CanJni.GolfWcLightSet(16, Neg(this.mLight1Data.DaytimeLight));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 8:
                CanJni.GolfWcLightSet(7, pos);
                return;
            case 9:
                CanJni.GolfWcLightSet(8, pos);
                return;
            case 10:
                CanJni.GolfWcLightSet(9, pos);
                return;
            case 11:
                CanJni.GolfWcLightSet(10, pos);
                return;
            case 12:
                CanJni.GolfWcLightSet(11, pos);
                return;
            case 13:
                CanJni.GolfWcLightSet(17, pos);
                return;
            case 15:
                CanJni.GolfWcLightSet(13, pos);
                return;
            case 16:
                CanJni.GolfWcLightSet(14, pos);
                return;
            case 17:
                CanJni.GolfWcLightSet(18, pos);
                return;
            case 18:
                CanJni.GolfWcLightSet(15, pos);
                return;
            case 20:
                CanJni.GolfWcCameraSet(10, pos);
                return;
            case 21:
                CanJni.GolfWcCameraSet(11, pos);
                return;
            case 22:
                CanJni.GolfWcCameraSet(12, pos);
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 3:
                CanJni.GolfWcLightSet(3, item);
                return;
            case 6:
                CanJni.GolfWcLightSet(6, item);
                return;
            case 14:
                CanJni.GolfWcLightSet(12, item);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
