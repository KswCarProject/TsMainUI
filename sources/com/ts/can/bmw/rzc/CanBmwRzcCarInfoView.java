package com.ts.can.bmw.rzc;

import android.app.Activity;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanBmwRzcCarInfoView extends CanRelativeCarInfoView {
    public static final String TAG = "CanBmwRzcCarInfoView";
    private CustomImgView mBatteryIcon;
    private TextView mDistanceItem;
    private TextView mDistanceItemTxt;
    private CustomImgView[] mDoorIcons;
    private TextView mElctricItem;
    private TextView mElctricItemTxt;
    private CustomImgView mLqywdIcon;
    private TextView mLqywdItemTxt;
    private CustomImgView mOilIcon;
    private TextView mOilItem;
    private TextView mOilItemTxt;
    private CustomImgView mParkingIcon;
    private TextView mParkingItem;
    private TextView mParkingItemTxt;
    private CustomImgView mQxyIcon;
    private TextView mQxyItemTxt;
    private TextView mRPMItem;
    private TextView mRPMItemTxt;
    private CustomImgView mSeatBeltIcon;
    private TextView mSeatBeltItem;
    private CanDataInfo.CAN_Msg mSetData;
    private CanDataInfo.BMW_Rzc_Info mSetData2;
    private TextView mSpeedItem;
    private TextView mSpeedItemTxt;
    private TextView mTempItem;
    private TextView mTempItemTxt;
    private CustomImgView mTrunkUpIcon;
    private TextView mTrunkUpItem;
    private TextView mTrunkUpItemTxt;
    private TextView mXhlcItem;

    public CanBmwRzcCarInfoView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mSetData = new CanDataInfo.CAN_Msg();
        this.mSetData2 = new CanDataInfo.BMW_Rzc_Info();
        setBackgroundResource(R.drawable.can_vw_carinfo_bg);
        this.mDoorIcons = new CustomImgView[6];
        if (MainSet.GetScreenType() == 5) {
            InitUI_1280x480();
        } else {
            InitUI_1024x600();
        }
        this.mOilItem.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTempItem.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mElctricItem.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTrunkUpItem.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mParkingItem.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mXhlcItem.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mRPMItem.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mSpeedItem.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mDistanceItem.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mLqywdItemTxt.setText(TXZResourceManager.STYLE_DEFAULT);
    }

    private void InitUI_1024x600() {
        this.mOilIcon = addImageState(181, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_elctric_up, R.drawable.canvw_elctric_dn);
        this.mLqywdIcon = addImageState(217, 173, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_dn);
        this.mBatteryIcon = addImageState(KeyDef.RKEY_ANGLEDN, 71, R.drawable.canvw_battery_up, R.drawable.canvw_battery_dn);
        this.mSeatBeltIcon = addImageState(456, 33, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mTrunkUpIcon = addImageState(594, 71, R.drawable.canvw_trunk_up, R.drawable.canvw_trunk_dn);
        this.mParkingIcon = addImageState(696, 173, R.drawable.canvw_parking_up, R.drawable.canvw_parking_dn);
        this.mQxyIcon = addImageState(734, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_washing_up, R.drawable.canvw_washing_dn);
        addImage(432, 145, R.drawable.canvw_car3_up);
        this.mDoorIcons[0] = addImage(422, Can.CAN_FLAT_WC, R.drawable.canvw_left_door_dn);
        this.mDoorIcons[1] = addImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, Can.CAN_FLAT_WC, R.drawable.canvw_right_door_dn);
        this.mDoorIcons[2] = addImage(428, 290, R.drawable.canvw_left_door01_dn);
        this.mDoorIcons[3] = addImage(555, 290, R.drawable.canvw_right_door01_dn);
        this.mDoorIcons[4] = addImage(469, 373, R.drawable.canvw_car3trunk_dn);
        this.mDoorIcons[5] = addImage(459, Can.CAN_HONDA_WC, R.drawable.canvw_head_door);
        showDoor(0, 0, 0, 0, 0, 0);
        addImage(11, 429, R.drawable.canvw_speed_up);
        addImage(347, 429, R.drawable.canvw_instant_up);
        addImage(683, 429, R.drawable.canvw_road_haul_up);
        this.mOilItem = addText(80, 350, R.string.can_rest_oil);
        this.mTempItem = addText(137, 210, R.string.can_out_temp);
        this.mLqywdItemTxt = addText(90, 210, R.string.can_lqywd);
        this.mElctricItem = addText(218, 108, R.string.can_battery);
        this.mSeatBeltItem = addText(365, 5, 300, 30, R.string.can_belt);
        this.mTrunkUpItem = addText(720, 108, R.string.can_trunk);
        this.mParkingItem = addText((int) KeyDef.SKEY_CALLDN_4, 210, R.string.can_brake);
        this.mXhlcItem = addText(859, 350, R.string.can_range_xhlc);
        this.mOilItemTxt = addText(80, (int) KeyDef.RKEY_MEDIA_SLOW, R.string.can_rest_oil);
        this.mTempItemTxt = addText(117, 180, R.string.can_out_temp);
        this.mElctricItemTxt = addText(218, 78, R.string.can_battery);
        this.mTrunkUpItemTxt = addText(720, 78, R.string.can_trunk);
        this.mParkingItemTxt = addText((int) KeyDef.SKEY_CALLDN_4, 180, R.string.can_brake);
        this.mQxyItemTxt = addText(859, (int) KeyDef.RKEY_MEDIA_SLOW, R.string.can_range_xhlc);
        this.mRPMItem = addText(161, 475, R.string.can_rpm);
        this.mSpeedItem = addText(515, 475, R.string.can_curspeed);
        this.mDistanceItem = addText(855, 475, R.string.can_curspeed);
        this.mRPMItemTxt = addText(161, 445, R.string.can_rpm);
        this.mSpeedItemTxt = addText(515, 445, R.string.can_curspeed);
        this.mDistanceItemTxt = addText(855, 445, R.string.can_curspeed);
        setTextStyle(this.mOilItemTxt, R.string.can_rest_oil, -1, 16, 0).setTextStyle(this.mTempItemTxt, R.string.can_out_temp, -1, 16, 0).setTextStyle(this.mElctricItemTxt, R.string.can_battery, -1, 16, 0).setTextStyle(this.mTrunkUpItemTxt, R.string.can_trunk, -1, 16, 0).setTextStyle(this.mParkingItemTxt, R.string.can_brake, -1, 16, 0).setTextStyle(this.mQxyItemTxt, R.string.can_wash, -1, 16, 0).setTextStyle(this.mOilItem, R.string.can_rest_oil, -1, 16, 0).setTextStyle(this.mTempItem, R.string.can_range_xhlc, -1, 16, 0).setTextStyle(this.mElctricItem, R.string.can_battery, -1, 16, 0).setTextStyle(this.mSeatBeltItem, R.string.can_belt, -1, 16, 17).setTextStyle(this.mTrunkUpItem, R.string.can_trunk, -1, 16, 0).setTextStyle(this.mParkingItem, R.string.can_brake, -1, 16, 0).setTextStyle(this.mXhlcItem, R.string.can_lqywd, -1, 16, 0).setTextStyle(this.mRPMItem, R.string.can_rpm, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mSpeedItem, R.string.can_curspeed, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mDistanceItem, R.string.can_driving_mileage, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mRPMItemTxt, R.string.can_rpm, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mSpeedItemTxt, R.string.can_curspeed, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mDistanceItemTxt, R.string.can_driving_mileage, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mLqywdItemTxt, R.string.can_lqywd, -1, 16, 0);
    }

    private void InitUI_1280x480() {
        setBackgroundResource(R.drawable.can_vw_carinfo_bg_1280x480);
        this.mOilIcon = addImageState(161, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_elctric_up, R.drawable.canvw_elctric_dn);
        this.mLqywdIcon = addImageState(197, 173, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_dn);
        this.mBatteryIcon = addImageState(298, 71, R.drawable.canvw_battery_up, R.drawable.canvw_battery_dn);
        this.mSeatBeltIcon = addImageState(436, 33, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mTrunkUpIcon = addImageState(CanCameraUI.BTN_CHANA_CS75_MODE5, 71, R.drawable.canvw_trunk_up, R.drawable.canvw_trunk_dn);
        this.mParkingIcon = addImageState(676, 173, R.drawable.canvw_parking_up, R.drawable.canvw_parking_dn);
        this.mQxyIcon = addImageState(714, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_washing_up, R.drawable.canvw_washing_dn);
        addImage(412, 145, R.drawable.canvw_car3_up);
        this.mDoorIcons[0] = addImage(CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, Can.CAN_FLAT_WC, R.drawable.canvw_left_door_dn);
        this.mDoorIcons[1] = addImage(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, Can.CAN_FLAT_WC, R.drawable.canvw_right_door_dn);
        this.mDoorIcons[2] = addImage(408, 290, R.drawable.canvw_left_door01_dn);
        this.mDoorIcons[3] = addImage(535, 290, R.drawable.canvw_right_door01_dn);
        this.mDoorIcons[4] = addImage(449, 373, R.drawable.canvw_car3trunk_dn);
        this.mDoorIcons[5] = addImage(439, Can.CAN_HONDA_WC, R.drawable.canvw_head_door);
        showDoor(0, 0, 0, 0, 0, 0);
        addImage(922, 38, R.drawable.canvw_speed_up);
        addImage(922, 165, R.drawable.canvw_instant_up);
        addImage(922, 292, R.drawable.canvw_road_haul_up);
        this.mOilItem = addText(60, 350, R.string.can_rest_oil);
        this.mTempItem = addText(117, 210, R.string.can_out_temp);
        this.mLqywdItemTxt = addText(70, 210, R.string.can_lqywd);
        this.mElctricItem = addText(198, 108, R.string.can_battery);
        this.mSeatBeltItem = addText(345, 5, R.string.can_belt);
        this.mTrunkUpItem = addText((int) CanCameraUI.BTN_CC_WC_DIRECTION1, 108, R.string.can_trunk);
        this.mParkingItem = addText(802, 210, R.string.can_brake);
        this.mXhlcItem = addText((int) KeyDef.SKEY_RETURN_1, 350, R.string.can_range_xhlc);
        this.mOilItemTxt = addText(60, (int) KeyDef.RKEY_MEDIA_SLOW, R.string.can_rest_oil);
        this.mTempItemTxt = addText(97, 180, R.string.can_out_temp);
        this.mElctricItemTxt = addText(198, 78, R.string.can_battery);
        this.mTrunkUpItemTxt = addText((int) CanCameraUI.BTN_CC_WC_DIRECTION1, 78, R.string.can_trunk);
        this.mParkingItemTxt = addText(802, 180, R.string.can_brake);
        this.mQxyItemTxt = addText((int) KeyDef.SKEY_RETURN_1, (int) KeyDef.RKEY_MEDIA_SLOW, R.string.can_range_xhlc);
        this.mRPMItem = addText(1080, 87, R.string.can_rpm);
        this.mSpeedItem = addText(1080, 214, R.string.can_curspeed);
        this.mDistanceItem = addText(1080, (int) KeyDef.RKEY_res4, R.string.can_driving_mileage);
        this.mRPMItemTxt = addText(1080, 57, R.string.can_rpm);
        this.mSpeedItemTxt = addText(1080, 184, R.string.can_curspeed);
        this.mDistanceItemTxt = addText(1080, 311, R.string.can_curspeed);
        setTextStyle(this.mOilItemTxt, R.string.can_rest_oil, -1, 16, 0).setTextStyle(this.mTempItemTxt, R.string.can_out_temp, -1, 16, 0).setTextStyle(this.mElctricItemTxt, R.string.can_battery, -1, 16, 0).setTextStyle(this.mTrunkUpItemTxt, R.string.can_trunk, -1, 16, 0).setTextStyle(this.mParkingItemTxt, R.string.can_brake, -1, 16, 0).setTextStyle(this.mQxyItemTxt, R.string.can_wash, -1, 16, 0).setTextStyle(this.mOilItem, R.string.can_rest_oil, -1, 16, 0).setTextStyle(this.mTempItem, R.string.can_range_xhlc, -1, 16, 0).setTextStyle(this.mElctricItem, R.string.can_battery, -1, 16, 0).setTextStyle(this.mSeatBeltItem, R.string.can_belt, -1, 16, 17).setTextStyle(this.mTrunkUpItem, R.string.can_trunk, -1, 16, 0).setTextStyle(this.mParkingItem, R.string.can_brake, -1, 16, 0).setTextStyle(this.mXhlcItem, R.string.can_lqywd, -1, 16, 0).setTextStyle(this.mRPMItem, R.string.can_rpm, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mDistanceItem, R.string.can_driving_mileage, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mSpeedItem, R.string.can_curspeed, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mRPMItemTxt, R.string.can_rpm, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mSpeedItemTxt, R.string.can_curspeed, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mDistanceItemTxt, R.string.can_driving_mileage, ViewCompat.MEASURED_STATE_MASK, 16, 1).setTextStyle(this.mLqywdItemTxt, R.string.can_lqywd, -1, 16, 0);
    }

    public void ResetData(boolean check) {
        CanJni.GetCarInfo(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            if (!(i2b(this.mSetData.DriveSafe) || i2b(this.mSetData.PassengerSafe))) {
                this.mSeatBeltIcon.setSelected(true);
                this.mSeatBeltItem.setText(getString(R.string.can_belt_on));
            } else {
                this.mSeatBeltIcon.setSelected(false);
                this.mSeatBeltItem.setText(String.valueOf(getString(R.string.can_belt)) + "  " + getString(R.string.can_off));
            }
            if (!i2b(this.mSetData.HandBrake)) {
                this.mParkingIcon.setSelected(true);
                this.mParkingItem.setText(TXZResourceManager.STYLE_DEFAULT);
            } else {
                this.mParkingIcon.setSelected(false);
                this.mParkingItem.setText(R.string.can_normal);
            }
            this.mRPMItem.setText(String.format("%dRPM", new Object[]{Integer.valueOf(this.mSetData.Rpm)}));
            this.mSpeedItem.setText(String.format("%dKm/h", new Object[]{Integer.valueOf(this.mSetData.Speed)}));
            this.mElctricItem.setText(String.format("%.2fV", new Object[]{Float.valueOf(((float) this.mSetData.BatV) * 0.01f)}));
            this.mTempItem.setText(String.format("%.1f℃", new Object[]{Float.valueOf(((float) this.mSetData.OutTemp) * 0.1f)}));
            this.mDistanceItem.setText(String.format("%dKm", new Object[]{Integer.valueOf(this.mSetData.Distance)}));
            this.mOilItem.setText(String.format("%dL", new Object[]{Integer.valueOf(this.mSetData.Syyl)}));
        }
        CanJni.BmwRzcGetInfo(this.mSetData2);
        if (!i2b(this.mSetData2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData2.Update)) {
            this.mSetData2.Update = 0;
            boolean fgWarnOil = i2b(this.mSetData2.fgWarnOil);
            boolean fgWarnBat = i2b(this.mSetData2.fgWarnBat);
            this.mOilIcon.setSelected(fgWarnOil);
            this.mBatteryIcon.setSelected(fgWarnBat);
        }
    }

    public void QueryData() {
        CanJni.BmwRzcQueryData(65, 1);
        Sleep(5);
        CanJni.BmwRzcQueryData(65, 2);
        Sleep(5);
        CanJni.BmwRzcQueryData(65, 3);
    }

    private void showDoor(int lf, int rf, int lr, int rr, int back, int Front) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        for (ImageView img : this.mDoorIcons) {
            img.setVisibility(8);
        }
        CustomImgView customImgView = this.mDoorIcons[0];
        if (lf == 1) {
            i = 0;
        } else {
            i = 8;
        }
        customImgView.setVisibility(i);
        CustomImgView customImgView2 = this.mDoorIcons[1];
        if (rf == 1) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        customImgView2.setVisibility(i2);
        CustomImgView customImgView3 = this.mDoorIcons[2];
        if (lr == 1) {
            i3 = 0;
        } else {
            i3 = 8;
        }
        customImgView3.setVisibility(i3);
        CustomImgView customImgView4 = this.mDoorIcons[3];
        if (rr == 1) {
            i4 = 0;
        } else {
            i4 = 8;
        }
        customImgView4.setVisibility(i4);
        CustomImgView customImgView5 = this.mDoorIcons[4];
        if (back == 1) {
            i5 = 0;
        } else {
            i5 = 8;
        }
        customImgView5.setVisibility(i5);
        CustomImgView customImgView6 = this.mDoorIcons[5];
        if (Front != 1) {
            i6 = 8;
        }
        customImgView6.setVisibility(i6);
    }
}
