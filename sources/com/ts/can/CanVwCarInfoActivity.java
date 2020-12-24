package com.ts.can;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.KeyDef;

public class CanVwCarInfoActivity extends CanBaseActivity implements UserCallBack {
    private View.OnClickListener VwBtnClick = new View.OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == R.id.can_btn_dw) {
                if (FtSet.Getyw1() > 0) {
                    FtSet.Setyw1(0);
                } else {
                    FtSet.Setyw1(1);
                }
                CanVwCarInfoActivity.this.ResetData();
            }
        }
    };
    private ImageView mBatteryIcon;
    private Button mBtnDw;
    private TextView mDistanceItem;
    private ImageView[] mDoorIcons = new ImageView[5];
    private CanDataInfo.CAN_DoorInfo mDoorInfo = new CanDataInfo.CAN_DoorInfo();
    private TextView mElctricItem;
    private CanDataInfo.VWCarInfo1 mInfo1 = new CanDataInfo.VWCarInfo1();
    private CanDataInfo.VWCarInfo2 mInfo2 = new CanDataInfo.VWCarInfo2();
    private long mLastQueryTime = 0;
    private long mLastWarnTime = 0;
    private RelativeLayout mManager;
    private ImageView mOilIcon;
    private TextView mOilItem;
    private int mOldDw = 255;
    private ImageView mParkingIcon;
    private TextView mParkingItem;
    private TextView mRPMItem;
    private int mScreenHeight;
    private ImageView mSeatBeltIcon;
    private TextView mSeatBeltItem;
    private TextView mSpeedItem;
    private TextView mTempItem;
    private ImageView mTrunkUpIcon;
    private TextView mTrunkUpItem;
    private ImageView mWashingIcon;
    private TextView mWashingItem;
    private boolean mfgJump;
    private boolean mfgWarn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_vw_car_info);
        if (MainSet.GetScreenType() == 5) {
            InitUI_1280x480();
        } else if (MainSet.GetScreenType() == 3) {
            InitUI_768X1024();
        } else {
            DisplayMetrics outMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
            this.mScreenHeight = outMetrics.heightPixels;
            if (this.mScreenHeight == 400) {
                InitUI_1024x400();
            } else {
                InitUI();
            }
        }
        this.mBtnDw = (Button) findViewById(R.id.can_btn_dw);
        this.mBtnDw.setOnClickListener(this.VwBtnClick);
        if (getIntent().getExtras() != null) {
            this.mfgJump = true;
            Log.d(CanBaseActivity.TAG, "mfgJump");
            this.mLastWarnTime = GetTickCount();
            return;
        }
        this.mfgJump = false;
    }

    private void InitUI_1024x400() {
        this.mManager = (RelativeLayout) findViewById(R.id.layout_vw_carinfo);
        this.mManager.setBackgroundResource(R.drawable.can_vw_carinfo_bg_1024x400);
        this.mOilIcon = addImage(130, Can.CAN_BYD_M6_DJ, R.drawable.canvw_elctric_up_1024x400, R.drawable.canvw_elctric_dn_1024x400);
        addImage(Can.CAN_FORD_WC, Can.CAN_BENC_ZMYT, R.drawable.canvw_outtemd_up_1024x400, R.drawable.canvw_outtemd_dn_1024x400);
        this.mBatteryIcon = addImage(Can.CAN_SGMW_WC, 63, R.drawable.canvw_battery_up_1024x400, R.drawable.canvw_battery_dn_1024x400);
        this.mSeatBeltIcon = addImage(KeyDef.RKEY_AVIN, 35, R.drawable.canvw_seat_belt_up_1024x400, R.drawable.canvw_seat_belt_dn_1024x400);
        this.mTrunkUpIcon = addImage(439, 63, R.drawable.canvw_trunk_up_1024x400, R.drawable.canvw_trunk_dn_1024x400);
        this.mParkingIcon = addImage(516, Can.CAN_BENC_ZMYT, R.drawable.canvw_parking_up_1024x400, R.drawable.canvw_parking_dn_1024x400);
        this.mWashingIcon = addImage(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6, Can.CAN_BYD_M6_DJ, R.drawable.canvw_washing_up_1024x400, R.drawable.canvw_washing_dn_1024x400);
        addImage(KeyDef.RKEY_MEDIA_STOP, 119, R.drawable.canvw_car3_up_1024x400);
        this.mDoorIcons[0] = addImage(KeyDef.RKEY_MEDIA_SUBT, 184, R.drawable.canvw_left_door_dn_1024x400);
        this.mDoorIcons[1] = addImage(416, 184, R.drawable.canvw_right_door_dn_1024x400);
        this.mDoorIcons[2] = addImage(KeyDef.RKEY_EJECT, Can.CAN_JIANGLING_MYX, R.drawable.canvw_left_door01_dn_1024x400);
        this.mDoorIcons[3] = addImage(410, Can.CAN_JIANGLING_MYX, R.drawable.canvw_right_door01_dn_1024x400);
        this.mDoorIcons[4] = addImage(345, 290, R.drawable.canvw_car3trunk_dn_1024x400);
        showDoor(0, 0, 0, 0, 0);
        addImage(732, 25, R.drawable.canvw_speed_up_1024x400);
        addImage(732, 129, R.drawable.canvw_instant_up_1024x400);
        addImage(732, Can.CAN_FLAT_WC, R.drawable.canvw_road_haul_up_1024x400);
        this.mOilItem = addText(40, Can.CAN_NISSAN_XFY, R.string.can_rest_oil, -1, false);
        if (CanFunc.mFsCanTp == 103) {
            this.mTempItem = addText(65, Can.CAN_BENC_ZMYT, R.string.can_lqywd, -1, false);
            this.mDistanceItem = addText(KeyDef.SKEY_RETURN_2, Can.CAN_NISSAN_RICH6_WC, R.string.can_trav_time, -16777216, true);
        } else if (CanFunc.mFsCanTp == 111) {
            this.mTempItem = addText(65, Can.CAN_BENC_ZMYT, R.string.can_car_water_temp, -1, false);
            this.mDistanceItem = addText(KeyDef.SKEY_RETURN_2, Can.CAN_NISSAN_RICH6_WC, R.string.can_driving_mileage, -16777216, true);
        } else {
            this.mTempItem = addText(65, Can.CAN_BENC_ZMYT, R.string.can_out_temp, -1, false);
            this.mDistanceItem = addText(KeyDef.SKEY_RETURN_2, Can.CAN_NISSAN_RICH6_WC, R.string.can_driving_mileage, -16777216, true);
        }
        this.mElctricItem = addText(148, 66, R.string.can_battery, -1, false);
        this.mSeatBeltItem = addText(Can.CAN_BENZ_SMART_OD, 5, R.string.can_belt);
        this.mTrunkUpItem = addText(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST1, 66, R.string.can_trunk, -1, false);
        this.mParkingItem = addText(620, 146, R.string.can_brake, -1, false);
        this.mWashingItem = addWashItemText(CanCameraUI.BTN_LANDWIND_2D3D, Can.CAN_NISSAN_XFY, -1, false);
        this.mRPMItem = addText(KeyDef.SKEY_RETURN_2, 32, R.string.can_rpm, -16777216, true);
        this.mSpeedItem = addText(KeyDef.SKEY_RETURN_2, 135, R.string.can_curspeed, -16777216, true);
    }

    private void InitUI() {
        this.mManager = (RelativeLayout) findViewById(R.id.layout_vw_carinfo);
        this.mOilIcon = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 181, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_elctric_up, R.drawable.canvw_elctric_dn);
        addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 217, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 173, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_dn);
        this.mBatteryIcon = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + KeyDef.RKEY_ANGLEDN, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 71, R.drawable.canvw_battery_up, R.drawable.canvw_battery_dn);
        this.mSeatBeltIcon = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 456, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 33, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mTrunkUpIcon = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 594, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 71, R.drawable.canvw_trunk_up, R.drawable.canvw_trunk_dn);
        this.mParkingIcon = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 696, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 173, R.drawable.canvw_parking_up, R.drawable.canvw_parking_dn);
        this.mWashingIcon = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 734, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_washing_up, R.drawable.canvw_washing_dn);
        addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 432, getResources().getDimensionPixelSize(R.dimen.y1_vw_carinfo_flg) + 145, R.drawable.canvw_car3_up);
        this.mDoorIcons[0] = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 422, getResources().getDimensionPixelSize(R.dimen.y1_vw_carinfo_flg) + Can.CAN_FLAT_WC, R.drawable.canvw_left_door_dn);
        this.mDoorIcons[1] = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, getResources().getDimensionPixelSize(R.dimen.y1_vw_carinfo_flg) + Can.CAN_FLAT_WC, R.drawable.canvw_right_door_dn);
        this.mDoorIcons[2] = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 428, getResources().getDimensionPixelSize(R.dimen.y1_vw_carinfo_flg) + 290, R.drawable.canvw_left_door01_dn);
        this.mDoorIcons[3] = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 555, getResources().getDimensionPixelSize(R.dimen.y1_vw_carinfo_flg) + 290, R.drawable.canvw_right_door01_dn);
        this.mDoorIcons[4] = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 469, getResources().getDimensionPixelSize(R.dimen.y1_vw_carinfo_flg) + 373, R.drawable.canvw_car3trunk_dn);
        showDoor(0, 0, 0, 0, 0);
        addImage(getResources().getDimensionPixelSize(R.dimen.x1_vw_carinfo_flg) + 11, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 429, R.drawable.canvw_speed_up);
        addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 347, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 429, R.drawable.canvw_instant_up);
        addImage(getResources().getDimensionPixelSize(R.dimen.x2_vw_carinfo_flg) + 683, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 429, R.drawable.canvw_road_haul_up);
        this.mOilItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 60, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + KeyDef.RKEY_MEDIA_SLOW, R.string.can_rest_oil, -1, true);
        if (CanFunc.mFsCanTp == 103) {
            this.mTempItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 77, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 180, R.string.can_lqywd, -1, true);
            this.mDistanceItem = addText(getResources().getDimensionPixelSize(R.dimen.x2_vw_carinfo_flg) + 850, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 445, R.string.can_trav_time, -16777216, true);
        } else if (CanFunc.mFsCanTp == 111) {
            this.mTempItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 77, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 180, R.string.can_car_water_temp, -1, true);
            this.mDistanceItem = addText(getResources().getDimensionPixelSize(R.dimen.x2_vw_carinfo_flg) + 850, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 445, R.string.can_driving_mileage, -16777216, true);
        } else {
            this.mTempItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 77, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 180, R.string.can_out_temp, -1, true);
            this.mDistanceItem = addText(getResources().getDimensionPixelSize(R.dimen.x2_vw_carinfo_flg) + 850, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 445, R.string.can_driving_mileage, -16777216, true);
        }
        this.mElctricItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 218, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 78, R.string.can_battery, -1, false);
        this.mSeatBeltItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 365, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 5, R.string.can_belt);
        this.mTrunkUpItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 720, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 78, R.string.can_trunk, -1, false);
        this.mParkingItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + KeyDef.SKEY_CALLDN_4, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 180, R.string.can_brake, -1, false);
        this.mWashingItem = addWashItemText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 859, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + KeyDef.RKEY_MEDIA_SLOW, -1, false);
        this.mRPMItem = addText(getResources().getDimensionPixelSize(R.dimen.x1_vw_carinfo_flg) + 161, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 445, R.string.can_rpm, -16777216, true);
        this.mSpeedItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 515, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 445, R.string.can_curspeed, -16777216, true);
    }

    private void InitUI_1280x480() {
        this.mManager = (RelativeLayout) findViewById(R.id.layout_vw_carinfo);
        this.mManager.setBackgroundResource(R.drawable.can_vw_carinfo_bg_1280x480);
        this.mOilIcon = addImage(161, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_elctric_up, R.drawable.canvw_elctric_dn);
        addImage(197, 173, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_dn);
        this.mBatteryIcon = addImage(KeyDef.RKEY_LOC, 71, R.drawable.canvw_battery_up, R.drawable.canvw_battery_dn);
        this.mSeatBeltIcon = addImage(436, 33, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mTrunkUpIcon = addImage(CanCameraUI.BTN_CHANA_CS75_MODE5, 71, R.drawable.canvw_trunk_up, R.drawable.canvw_trunk_dn);
        this.mParkingIcon = addImage(676, 173, R.drawable.canvw_parking_up, R.drawable.canvw_parking_dn);
        this.mWashingIcon = addImage(714, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_washing_up, R.drawable.canvw_washing_dn);
        addImage(412, 145, R.drawable.canvw_car3_up);
        this.mDoorIcons[0] = addImage(CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, Can.CAN_FLAT_WC, R.drawable.canvw_left_door_dn);
        this.mDoorIcons[1] = addImage(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, Can.CAN_FLAT_WC, R.drawable.canvw_right_door_dn);
        this.mDoorIcons[2] = addImage(408, 290, R.drawable.canvw_left_door01_dn);
        this.mDoorIcons[3] = addImage(535, 290, R.drawable.canvw_right_door01_dn);
        this.mDoorIcons[4] = addImage(449, 373, R.drawable.canvw_car3trunk_dn);
        showDoor(0, 0, 0, 0, 0);
        addImage(922, 38, R.drawable.canvw_speed_up);
        addImage(922, 165, R.drawable.canvw_instant_up);
        addImage(922, KeyDef.RKEY_PRE, R.drawable.canvw_road_haul_up);
        this.mOilItem = addText(60, KeyDef.RKEY_MEDIA_SLOW, R.string.can_rest_oil, -1, false);
        if (CanFunc.mFsCanTp == 103) {
            this.mTempItem = addText(97, 180, R.string.can_lqywd, -1, false);
            this.mDistanceItem = addText(1080, KeyDef.RKEY_MEDIA_SEL, R.string.can_trav_time, -16777216, true);
        } else if (CanFunc.mFsCanTp == 111) {
            this.mTempItem = addText(97, 180, R.string.can_car_water_temp, -1, false);
            this.mDistanceItem = addText(1080, KeyDef.RKEY_MEDIA_SEL, R.string.can_driving_mileage, -16777216, true);
        } else {
            this.mTempItem = addText(97, 180, R.string.can_out_temp, -1, false);
            this.mDistanceItem = addText(1080, KeyDef.RKEY_MEDIA_SEL, R.string.can_driving_mileage, -16777216, true);
        }
        this.mElctricItem = addText(198, 78, R.string.can_battery, -1, false);
        this.mSeatBeltItem = addText(345, 5, R.string.can_belt);
        this.mTrunkUpItem = addText(CanCameraUI.BTN_CC_WC_DIRECTION1, 78, R.string.can_trunk, -1, false);
        this.mParkingItem = addText(802, 180, R.string.can_brake, -1, false);
        this.mWashingItem = addWashItemWidthText(KeyDef.SKEY_RETURN_1, KeyDef.RKEY_MEDIA_SLOW, 90, -1, false);
        this.mRPMItem = addText(1080, 57, R.string.can_rpm, -16777216, true);
        this.mSpeedItem = addText(1080, 184, R.string.can_curspeed, -16777216, true);
    }

    private void InitUI_768X1024() {
        this.mManager = (RelativeLayout) findViewById(R.id.layout_vw_carinfo);
        this.mManager.setBackgroundResource(R.drawable.can_vw_carinfo_bg_768x432);
        this.mOilIcon = addImage(136, Can.CAN_NISSAN_XFY, 84, 84, R.drawable.canvw_elctric_up, R.drawable.canvw_elctric_dn);
        addImage(163, 148, 84, 84, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_dn);
        this.mBatteryIcon = addImage(Can.CAN_GM_CAPTIVA_OD, 71, 84, 84, R.drawable.canvw_battery_up, R.drawable.canvw_battery_dn);
        this.mSeatBeltIcon = addImage(KeyDef.RKEY_res5, 43, 84, 84, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mTrunkUpIcon = addImage(445, 71, 84, 84, R.drawable.canvw_trunk_up, R.drawable.canvw_trunk_dn);
        this.mParkingIcon = addImage(CanCameraUI.BTN_GEELY_YJX6_MODE3, 148, 84, 84, R.drawable.canvw_parking_up, R.drawable.canvw_parking_dn);
        this.mWashingIcon = addImage(551, Can.CAN_TOYOTA_SP_XP, 84, 84, R.drawable.canvw_washing_up, R.drawable.canvw_washing_dn);
        addImage(KeyDef.RKEY_RADIO_3S, 127, 120, 213, R.drawable.canvw_car3_up);
        this.mDoorIcons[0] = addImage(KeyDef.RKEY_OPEN, 192, 31, 52, R.drawable.canvw_left_door_dn);
        this.mDoorIcons[1] = addImage(422, 192, 31, 52, R.drawable.canvw_right_door_dn);
        this.mDoorIcons[2] = addImage(KeyDef.RKEY_MEDIA_PROG, Can.CAN_ZH_WC, 33, 50, R.drawable.canvw_left_door01_dn);
        this.mDoorIcons[3] = addImage(416, Can.CAN_ZH_WC, 33, 50, R.drawable.canvw_right_door01_dn);
        this.mDoorIcons[4] = addImage(351, KeyDef.RKEY_LOC, 65, 30, R.drawable.canvw_car3trunk_dn);
        showDoor(0, 0, 0, 0, 0);
        addImage(8, KeyDef.RKEY_res3, Can.CAN_LUXGEN_WC, 89, R.drawable.canvw_speed_up);
        addImage(260, KeyDef.RKEY_res3, Can.CAN_LUXGEN_WC, 89, R.drawable.canvw_instant_up);
        addImage(CanCameraUI.BTN_YG9_XBS_MODE2, KeyDef.RKEY_res3, Can.CAN_LUXGEN_WC, 89, R.drawable.canvw_road_haul_up);
        this.mOilItem = addText(35, 255, R.string.can_rest_oil, -1, false, 20);
        if (CanFunc.mFsCanTp == 103) {
            this.mTempItem = addText(77, Can.CAN_HYUNDAI_WC, R.string.can_lqywd, -1, false, 20);
            this.mDistanceItem = addText(CanCameraUI.BTN_CCH9_MODE9, 347, R.string.can_trav_time, -16777216, true, 20);
        } else if (CanFunc.mFsCanTp == 111) {
            this.mTempItem = addText(77, Can.CAN_HYUNDAI_WC, R.string.can_car_water_temp, -1, false, 20);
            this.mDistanceItem = addText(CanCameraUI.BTN_CCH9_MODE9, 347, R.string.can_driving_mileage, -16777216, true, 20);
        } else {
            this.mTempItem = addText(77, Can.CAN_HYUNDAI_WC, R.string.can_out_temp, -1, false, 20);
            this.mDistanceItem = addText(CanCameraUI.BTN_CCH9_MODE9, 347, R.string.can_driving_mileage, -16777216, true, 20);
        }
        this.mElctricItem = addText(128, 75, R.string.can_battery, -1, false, 20);
        this.mSeatBeltItem = addText(Can.CAN_ZH_WC, 15, R.string.can_belt, 20);
        this.mTrunkUpItem = addText(CanCameraUI.BTN_TRUMPCHI_GS7_MODE1, 75, R.string.can_trunk, -1, false, 20);
        this.mParkingItem = addText(622, Can.CAN_HYUNDAI_WC, R.string.can_brake, -1, false, 20);
        this.mWashingItem = addWashItemText(659, 258, -1, false, 20);
        this.mRPMItem = addText(108, 347, R.string.can_rpm, -16777216, true, 20);
        this.mSpeedItem = addText(368, 347, R.string.can_curspeed, -16777216, true, 20);
    }

    private ImageView addImage(int x, int y, int iconId) {
        ImageView image = new ImageView(this);
        image.setImageResource(iconId);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        lp.leftMargin = x;
        lp.topMargin = y;
        image.setLayoutParams(lp);
        this.mManager.addView(image);
        return image;
    }

    private ImageView addImage(int x, int y, int w, int h, int iconId) {
        ImageView image = new ImageView(this);
        image.setImageResource(iconId);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(w, h);
        lp.leftMargin = x;
        lp.topMargin = y;
        image.setLayoutParams(lp);
        this.mManager.addView(image);
        return image;
    }

    private ImageView addImage(int x, int y, int normal, int select) {
        Drawable normalDrawable;
        Drawable selectDrawable = null;
        StateListDrawable drawable = new StateListDrawable();
        if (normal <= 0) {
            normalDrawable = null;
        } else {
            normalDrawable = getResources().getDrawable(normal);
        }
        if (select > 0) {
            selectDrawable = getResources().getDrawable(select);
        }
        drawable.addState(new int[]{16842913}, selectDrawable);
        drawable.addState(new int[0], normalDrawable);
        ImageView image = new ImageView(this);
        image.setImageDrawable(drawable);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        lp.leftMargin = x;
        lp.topMargin = y;
        image.setLayoutParams(lp);
        this.mManager.addView(image);
        return image;
    }

    private ImageView addImage(int x, int y, int w, int h, int normal, int select) {
        Drawable normalDrawable;
        Drawable selectDrawable = null;
        StateListDrawable drawable = new StateListDrawable();
        if (normal <= 0) {
            normalDrawable = null;
        } else {
            normalDrawable = getResources().getDrawable(normal);
        }
        if (select > 0) {
            selectDrawable = getResources().getDrawable(select);
        }
        drawable.addState(new int[]{16842913}, selectDrawable);
        drawable.addState(new int[0], normalDrawable);
        ImageView image = new ImageView(this);
        image.setImageDrawable(drawable);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(w, h);
        lp.leftMargin = x;
        lp.topMargin = y;
        image.setLayoutParams(lp);
        this.mManager.addView(image);
        return image;
    }

    private TextView addText(int x, int y, int textId) {
        TextView text = new TextView(this);
        text.setText(textId);
        text.setTextColor(-1);
        text.setTextSize(0, 24.0f);
        text.setGravity(17);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(300, -2);
        lp.leftMargin = x;
        lp.topMargin = y;
        text.setLayoutParams(lp);
        this.mManager.addView(text);
        return text;
    }

    private TextView addText(int x, int y, int textId, int textSize) {
        TextView text = new TextView(this);
        text.setText(textId);
        text.setTextColor(-1);
        text.setTextSize(0, (float) textSize);
        text.setGravity(17);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(300, -2);
        lp.leftMargin = x;
        lp.topMargin = y;
        text.setLayoutParams(lp);
        this.mManager.addView(text);
        return text;
    }

    private TextView addText(int x, int y, int textId, int color, boolean isCenterAlign) {
        RelativeLayout.LayoutParams layoutLp = new RelativeLayout.LayoutParams(Can.CAN_CHANA_CS75_WC, -2);
        layoutLp.leftMargin = x;
        layoutLp.topMargin = y;
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);
        if (isCenterAlign) {
            layout.setGravity(1);
        }
        layout.setLayoutParams(layoutLp);
        this.mManager.addView(layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        TextView title = new TextView(this);
        title.setMaxLines(1);
        title.setText(textId);
        title.setTextColor(color);
        int textSize = this.mScreenHeight == 400 ? 20 : 24;
        title.setTextSize(0, (float) textSize);
        title.setLayoutParams(lp);
        layout.addView(title);
        lp.topMargin = 5;
        TextView content = new TextView(this);
        content.setTextSize(0, (float) textSize);
        content.setMaxLines(1);
        content.setTextColor(color);
        content.setLayoutParams(lp);
        layout.addView(content);
        return content;
    }

    private TextView addText(int x, int y, int textId, int color, boolean isCenterAlign, int textSize) {
        RelativeLayout.LayoutParams layoutLp = new RelativeLayout.LayoutParams(Can.CAN_CHANA_CS75_WC, -2);
        layoutLp.leftMargin = x;
        layoutLp.topMargin = y;
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);
        if (isCenterAlign) {
            layout.setGravity(1);
        }
        layout.setLayoutParams(layoutLp);
        this.mManager.addView(layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        TextView title = new TextView(this);
        title.setMaxLines(1);
        title.setText(textId);
        title.setTextColor(color);
        title.setTextSize(0, (float) textSize);
        title.setLayoutParams(lp);
        layout.addView(title);
        lp.topMargin = 5;
        TextView content = new TextView(this);
        content.setTextSize(0, (float) textSize);
        content.setMaxLines(1);
        content.setTextColor(color);
        content.setLayoutParams(lp);
        layout.addView(content);
        return content;
    }

    private TextView addWashItemWidthText(int x, int y, int width, int color, boolean isCenterAlign) {
        RelativeLayout.LayoutParams layoutLp = new RelativeLayout.LayoutParams(width, -2);
        layoutLp.leftMargin = x;
        layoutLp.topMargin = y;
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);
        if (isCenterAlign) {
            layout.setGravity(1);
        }
        layout.setLayoutParams(layoutLp);
        this.mManager.addView(layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        TextView content = new TextView(this);
        content.setTextSize(0, (float) (this.mScreenHeight == 400 ? 20 : 24));
        content.setMaxLines(2);
        content.setLineSpacing(5.0f, 1.0f);
        content.setTextColor(color);
        content.setLayoutParams(lp);
        layout.addView(content);
        return content;
    }

    private TextView addWashItemText(int x, int y, int color, boolean isCenterAlign) {
        RelativeLayout.LayoutParams layoutLp = new RelativeLayout.LayoutParams(Can.CAN_CHANA_CS75_WC, -2);
        layoutLp.leftMargin = x;
        layoutLp.topMargin = y;
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);
        if (isCenterAlign) {
            layout.setGravity(1);
        }
        layout.setLayoutParams(layoutLp);
        this.mManager.addView(layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        TextView content = new TextView(this);
        content.setTextSize(0, (float) (this.mScreenHeight == 400 ? 20 : 24));
        content.setMaxLines(2);
        content.setLineSpacing(5.0f, 1.0f);
        content.setTextColor(color);
        content.setLayoutParams(lp);
        layout.addView(content);
        return content;
    }

    private TextView addWashItemText(int x, int y, int color, boolean isCenterAlign, int textSize) {
        RelativeLayout.LayoutParams layoutLp = new RelativeLayout.LayoutParams(Can.CAN_CHANA_CS75_WC, -2);
        layoutLp.leftMargin = x;
        layoutLp.topMargin = y;
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);
        if (isCenterAlign) {
            layout.setGravity(1);
        }
        layout.setLayoutParams(layoutLp);
        this.mManager.addView(layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        TextView content = new TextView(this);
        content.setTextSize(0, (float) textSize);
        content.setMaxLines(2);
        content.setLineSpacing(5.0f, 1.0f);
        content.setTextColor(color);
        content.setLayoutParams(lp);
        layout.addView(content);
        return content;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.mOldDw = 255;
        MainTask.GetInstance().SetUserCallBack(this);
        CanJni.GetVwCarInfo1(this.mInfo1);
        CanJni.GetVwCarInfo2(this.mInfo2);
        CanJni.GetDoorInfo(this.mDoorInfo);
        ResetData();
        CanJni.QueryVwCarInfo();
        super.onResume();
    }

    private void CheckData() {
        CanJni.GetVwCarInfo1(this.mInfo1);
        CanJni.GetVwCarInfo2(this.mInfo2);
        CanJni.GetDoorInfo(this.mDoorInfo);
        if (i2b(this.mInfo1.Update) || i2b(this.mInfo2.Update) || i2b(this.mDoorInfo.Update)) {
            ResetData();
        }
    }

    /* access modifiers changed from: private */
    public void ResetData() {
        this.mInfo1.Update = 0;
        this.mInfo2.Update = 0;
        this.mDoorInfo.Update = 0;
        boolean fgWarnOil = i2b(this.mInfo1.fgWarnOil);
        boolean fgWarnBat = i2b(this.mInfo1.fgWarnBat);
        boolean fgQSY = i2b(this.mInfo1.fgQSY);
        boolean fgSS = i2b(this.mInfo1.fgSS);
        boolean fgAQD = i2b(this.mInfo1.fgAQD);
        this.mOilIcon.setSelected(fgWarnOil);
        this.mBatteryIcon.setSelected(fgWarnBat);
        if (fgQSY) {
            this.mWashingIcon.setSelected(true);
            this.mWashingItem.setText(R.string.can_wash_lower);
        } else {
            this.mWashingIcon.setSelected(false);
            this.mWashingItem.setText(R.string.can_wash_normal);
        }
        if (fgSS) {
            this.mParkingIcon.setSelected(true);
            this.mParkingItem.setText(R.string.can_normal_it);
        } else {
            this.mParkingIcon.setSelected(false);
            this.mParkingItem.setText(R.string.can_normal);
        }
        if (fgAQD) {
            this.mSeatBeltIcon.setSelected(true);
            this.mSeatBeltItem.setText(getString(R.string.can_belt_on));
        } else {
            this.mSeatBeltIcon.setSelected(false);
            this.mSeatBeltItem.setText(String.valueOf(getString(R.string.can_belt)) + "  " + getString(R.string.can_off));
        }
        if (fgQSY || fgWarnOil || fgWarnBat || (CanFunc.IsDoorOpen(this.mDoorInfo) && CanFunc.mFsCanTp != 1)) {
            this.mfgWarn = true;
            this.mLastWarnTime = GetTickCount();
        } else {
            this.mfgWarn = false;
        }
        if (FtSet.Getyw1() == 0) {
            this.mSpeedItem.setText(String.format("%.2fKm/h", new Object[]{Double.valueOf(((double) ((float) this.mInfo2.CurSpeed)) * 0.01d)}));
            if (CanFunc.mFsCanTp == 103) {
                this.mDistanceItem.setText(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(this.mInfo2.Range / 3600), Integer.valueOf((this.mInfo2.Range / 60) % 60), Integer.valueOf(this.mInfo2.Range % 60)}));
            } else {
                this.mDistanceItem.setText(String.format("%dKm", new Object[]{Integer.valueOf(this.mInfo2.Range)}));
            }
            this.mTempItem.setText(String.format("%.2f℃", new Object[]{Double.valueOf(((double) ((float) this.mInfo2.OutTemp)) * 0.1d)}));
        } else {
            this.mSpeedItem.setText(String.format("%.2fMile/h", new Object[]{Double.valueOf(((double) ((float) (((double) this.mInfo2.CurSpeed) * 0.01d))) / 1.6d)}));
            if (CanFunc.mFsCanTp == 103) {
                this.mDistanceItem.setText(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(this.mInfo2.Range / 3600), Integer.valueOf((this.mInfo2.Range / 60) % 60), Integer.valueOf(this.mInfo2.Range % 60)}));
            } else {
                this.mDistanceItem.setText(String.format("%.2fMile", new Object[]{Float.valueOf((float) (((double) this.mInfo2.Range) / 1.6d))}));
            }
            this.mTempItem.setText(String.format("%.2f℉", new Object[]{Float.valueOf((float) ((((double) this.mInfo2.OutTemp) * 0.1d * 1.8d) + 32.0d))}));
        }
        this.mOilItem.setText(String.format("%dL", new Object[]{Integer.valueOf(this.mInfo2.RemainOil)}));
        this.mElctricItem.setText(String.format("%.2fV", new Object[]{Double.valueOf(((double) ((float) this.mInfo2.Voltage)) * 0.01d)}));
        this.mRPMItem.setText(String.format("%dRPM", new Object[]{Integer.valueOf(this.mInfo2.Rpm)}));
        switch (FtSet.GetFdoor()) {
            case 1:
                int temp = this.mDoorInfo.LFOpen;
                this.mDoorInfo.LFOpen = this.mDoorInfo.RFOpen;
                this.mDoorInfo.RFOpen = temp;
                break;
            case 2:
                this.mDoorInfo.LFOpen = 0;
                this.mDoorInfo.RFOpen = 0;
                break;
        }
        switch (FtSet.GetBdoor()) {
            case 1:
                int temp2 = this.mDoorInfo.LROpen;
                this.mDoorInfo.LROpen = this.mDoorInfo.RROpen;
                this.mDoorInfo.RROpen = temp2;
                break;
            case 2:
                this.mDoorInfo.LROpen = 0;
                this.mDoorInfo.RROpen = 0;
                break;
        }
        showDoor(this.mDoorInfo.LFOpen, this.mDoorInfo.RFOpen, this.mDoorInfo.LROpen, this.mDoorInfo.RROpen, this.mDoorInfo.RearOpen);
        if (i2b(this.mDoorInfo.RearOpen)) {
            this.mTrunkUpIcon.setSelected(true);
            this.mTrunkUpItem.setText(R.string.can_trunk_open);
        } else {
            this.mTrunkUpIcon.setSelected(false);
            this.mTrunkUpItem.setText(R.string.can_trunk_close);
        }
        if (this.mOldDw != FtSet.Getyw1()) {
            this.mOldDw = FtSet.Getyw1();
            if (this.mOldDw > 0) {
                this.mBtnDw.setSelected(true);
            } else {
                this.mBtnDw.setSelected(false);
            }
        }
    }

    private void showDoor(int lf, int rf, int lr, int rr, int back) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        for (ImageView img : this.mDoorIcons) {
            img.setVisibility(8);
        }
        ImageView imageView = this.mDoorIcons[0];
        if (lf == 1) {
            i = 0;
        } else {
            i = 8;
        }
        imageView.setVisibility(i);
        ImageView imageView2 = this.mDoorIcons[1];
        if (rf == 1) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        imageView2.setVisibility(i2);
        ImageView imageView3 = this.mDoorIcons[2];
        if (lr == 1) {
            i3 = 0;
        } else {
            i3 = 8;
        }
        imageView3.setVisibility(i3);
        ImageView imageView4 = this.mDoorIcons[3];
        if (rr == 1) {
            i4 = 0;
        } else {
            i4 = 8;
        }
        imageView4.setVisibility(i4);
        ImageView imageView5 = this.mDoorIcons[4];
        if (back != 1) {
            i5 = 8;
        }
        imageView5.setVisibility(i5);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void UserAll() {
        long time = GetTickCount();
        if (time > this.mLastQueryTime + 1000) {
            this.mLastQueryTime = time;
            CanJni.QueryVwCarInfo();
        }
        CheckData();
        if (this.mfgJump && !this.mfgWarn && time > this.mLastWarnTime + 3000) {
            this.mLastWarnTime = time;
            finish();
        }
    }
}
