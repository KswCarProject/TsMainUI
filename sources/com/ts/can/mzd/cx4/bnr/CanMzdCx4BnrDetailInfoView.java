package com.ts.can.mzd.cx4.bnr;

import android.app.Activity;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.KeyDef;

public class CanMzdCx4BnrDetailInfoView extends CanRelativeCarInfoView {
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private static long mLastWarnTime = 0;
    private static boolean mWin;
    private static boolean mfgJump = false;
    private CustomImgView mBatteryIcon;
    private CanDataInfo.CAN_Msg mCanMsg;
    private TextView mDistanceItem;
    private CustomImgView[] mDoorIcons;
    private CanDataInfo.CAN_DoorInfo mDoorInfo;
    private TextView mElctricItem;
    private CustomImgView mMachineIcon;
    private RelativeLayoutManager mManager;
    private CustomImgView mParkingIcon;
    private TextView mParkingItem;
    private TextView mRPMItem;
    private int mScreenHeight;
    private CustomImgView mSeatBeltIcon;
    private TextView mSeatBeltItem;
    private CustomImgView mSideBeltIcon;
    private TextView mSideBeltItem;
    private TextView mSpeedItem;
    private TextView mTempItem;
    private CustomImgView mTrunkUpIcon;
    private TextView mTrunkUpItem;
    private CustomImgView mWaterIcon;
    private TextView mWaterItem;

    public CanMzdCx4BnrDetailInfoView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        this.mDoorIcons = new CustomImgView[6];
        this.mCanMsg = new CanDataInfo.CAN_Msg();
        this.mDoorInfo = new CanDataInfo.CAN_DoorInfo();
        if (MainSet.GetScreenType() == 5) {
            BaseUI_1280x480();
            return;
        }
        DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        this.mScreenHeight = outMetrics.heightPixels;
        if (this.mScreenHeight == 400) {
            BaseUI_1024x400();
            return;
        }
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
        lp.width = 1024;
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        lp.gravity = 17;
        this.mManager.GetLayout().setLayoutParams(lp);
        BaseUI();
        this.mManager.GetLayout().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
        this.mManager.GetLayout().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
        Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
    }

    private int getDimen(int resId) {
        return getActivity().getResources().getDimensionPixelSize(resId);
    }

    private void BaseUI_1024x400() {
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_vw_carinfo_bg_1024x400);
        this.mWaterIcon = addImageState(130, Can.CAN_BYD_M6_DJ, R.drawable.canvw_outtemd_up_1024x400, R.drawable.canvw_outtemd_up_1024x400);
        this.mMachineIcon = addImageState(Can.CAN_FORD_WC, Can.CAN_BENC_ZMYT, R.drawable.canvw_outtemd_up_1024x400, R.drawable.canvw_outtemd_dn_1024x400);
        this.mBatteryIcon = addImageState(Can.CAN_SGMW_WC, 63, R.drawable.canvw_battery_up_1024x400, R.drawable.canvw_battery_dn_1024x400);
        this.mSeatBeltIcon = addImageState(KeyDef.RKEY_AVIN, 35, R.drawable.canvw_seat_belt_up_1024x400, R.drawable.canvw_seat_belt_dn_1024x400);
        this.mSideBeltIcon = addImageState(439, 63, R.drawable.canvw_seat_belt_up_1024x400, R.drawable.canvw_seat_belt_dn_1024x400);
        this.mParkingIcon = addImageState(516, Can.CAN_BENC_ZMYT, R.drawable.canvw_parking_up_1024x400, R.drawable.canvw_parking_dn_1024x400);
        this.mTrunkUpIcon = addImageState(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6, Can.CAN_BYD_M6_DJ, R.drawable.canvw_trunk_up_1024x400, R.drawable.canvw_trunk_dn_1024x400);
        addImage(KeyDef.RKEY_MEDIA_STOP, 119, R.drawable.canvw_car3_up_1024x400);
        this.mDoorIcons[0] = addImage(310, 184, R.drawable.canvw_left_door_dn_1024x400);
        this.mDoorIcons[1] = addImage(416, 184, R.drawable.canvw_right_door_dn_1024x400);
        this.mDoorIcons[2] = addImage(KeyDef.RKEY_EJECT, Can.CAN_JIANGLING_MYX, R.drawable.canvw_left_door01_dn_1024x400);
        this.mDoorIcons[3] = addImage(410, Can.CAN_JIANGLING_MYX, R.drawable.canvw_right_door01_dn_1024x400);
        this.mDoorIcons[4] = addImage(345, 290, R.drawable.canvw_car3trunk_dn_1024x400);
        this.mDoorIcons[5] = addImage(KeyDef.RKEY_BT, 126, R.drawable.canvw_head_door_1024x400);
        showDoor(0, 0, 0, 0, 0, 0);
        addImage(732, 25, R.drawable.canvw_speed_up_1024x400);
        addImage(732, 129, R.drawable.canvw_instant_up_1024x400);
        addImage(732, Can.CAN_FLAT_WC, R.drawable.canvw_road_haul_up_1024x400);
        this.mWaterItem = addTextView(40, Can.CAN_NISSAN_XFY, R.string.can_car_water_temp, -1, false);
        this.mTempItem = addTextView(65, Can.CAN_BENC_ZMYT, R.string.can_out_temp, -1, false);
        this.mElctricItem = addTextView(148, 66, R.string.can_battery, -1, false);
        this.mSeatBeltItem = addTextView(KeyDef.RKEY_EJECT, 5, R.string.can_drive_safe_belt, -1, false);
        this.mSideBeltItem = addTextView(540, 66, R.string.can_passenger_safe_belt, -1, false);
        this.mParkingItem = addTextView(620, 146, R.string.can_brake, -1, false);
        this.mTrunkUpItem = addTextView(CanCameraUI.BTN_LANDWIND_2D3D, Can.CAN_NISSAN_XFY, R.string.can_trunk, -1, false);
        this.mRPMItem = addTextView(KeyDef.SKEY_RETURN_2, 32, R.string.can_rpm, ViewCompat.MEASURED_STATE_MASK, true);
        this.mSpeedItem = addTextView(KeyDef.SKEY_RETURN_2, 135, R.string.can_curspeed, ViewCompat.MEASURED_STATE_MASK, true);
        this.mDistanceItem = addTextView(KeyDef.SKEY_RETURN_2, Can.CAN_NISSAN_RICH6_WC, R.string.can_driving_mileage, ViewCompat.MEASURED_STATE_MASK, true);
    }

    private void BaseUI() {
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_vw_carinfo_bg);
        this.mWaterIcon = addImageState(181, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_up);
        this.mMachineIcon = addImageState(217, 173, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_dn);
        this.mBatteryIcon = addImageState(KeyDef.RKEY_ANGLEDN, 71, R.drawable.canvw_battery_up, R.drawable.canvw_battery_dn);
        this.mSeatBeltIcon = addImageState(446, 33, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mSideBeltIcon = addImageState(594, 71, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mParkingIcon = addImageState(696, 173, R.drawable.canvw_parking_up, R.drawable.canvw_parking_dn);
        this.mTrunkUpIcon = addImageState(734, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_trunk_up, R.drawable.canvw_trunk_dn);
        addImage(432, 145, R.drawable.canvw_car3_up);
        this.mDoorIcons[0] = addImage(422, Can.CAN_FLAT_WC, R.drawable.canvw_left_door_dn);
        this.mDoorIcons[1] = addImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, Can.CAN_FLAT_WC, R.drawable.canvw_right_door_dn);
        this.mDoorIcons[2] = addImage(428, 290, R.drawable.canvw_left_door01_dn);
        this.mDoorIcons[3] = addImage(555, 290, R.drawable.canvw_right_door01_dn);
        this.mDoorIcons[4] = addImage(469, 373, R.drawable.canvw_car3trunk_dn);
        this.mDoorIcons[5] = addImage(457, Can.CAN_FORD_WC, R.drawable.canvw_head_door);
        showDoor(0, 0, 0, 0, 0, 0);
        addImage(11, 429, R.drawable.canvw_speed_up);
        addImage(347, 429, R.drawable.canvw_instant_up);
        addImage(683, 429, R.drawable.canvw_road_haul_up);
        this.mWaterItem = addTextView(60, KeyDef.RKEY_MEDIA_SLOW, R.string.can_car_water_temp, -1, true);
        this.mTempItem = addTextView(77, 180, R.string.can_out_temp, -1, true);
        this.mElctricItem = addTextView(218, 78, R.string.can_battery, -1, false);
        this.mSeatBeltItem = addTextView(430, 5, R.string.can_drive_safe_belt, -1, false);
        this.mSideBeltItem = addTextView(720, 78, R.string.can_passenger_safe_belt, -1, false);
        this.mParkingItem = addTextView(KeyDef.SKEY_CALLDN_4, 180, R.string.can_brake, -1, false);
        this.mTrunkUpItem = addTextView(859, KeyDef.RKEY_MEDIA_SLOW, R.string.can_trunk, -1, false);
        this.mRPMItem = addTextView(161, 445, R.string.can_rpm, ViewCompat.MEASURED_STATE_MASK, true);
        this.mSpeedItem = addTextView(515, 445, R.string.can_curspeed, ViewCompat.MEASURED_STATE_MASK, true);
        this.mDistanceItem = addTextView(855, 445, R.string.can_driving_mileage, ViewCompat.MEASURED_STATE_MASK, true);
    }

    private void BaseUI_1280x480() {
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_vw_carinfo_bg_1280x480);
        this.mWaterIcon = addImageState(161, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_up);
        this.mMachineIcon = addImageState(197, 173, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_dn);
        this.mBatteryIcon = addImageState(298, 71, R.drawable.canvw_battery_up, R.drawable.canvw_battery_dn);
        this.mSeatBeltIcon = addImageState(436, 33, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mSideBeltIcon = addImageState(CanCameraUI.BTN_CHANA_CS75_MODE5, 71, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mParkingIcon = addImageState(676, 173, R.drawable.canvw_parking_up, R.drawable.canvw_parking_dn);
        this.mTrunkUpIcon = addImageState(714, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_trunk_up, R.drawable.canvw_trunk_dn);
        addImage(412, 145, R.drawable.canvw_car3_up);
        this.mDoorIcons[0] = addImage(CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, Can.CAN_FLAT_WC, R.drawable.canvw_left_door_dn);
        this.mDoorIcons[1] = addImage(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, Can.CAN_FLAT_WC, R.drawable.canvw_right_door_dn);
        this.mDoorIcons[2] = addImage(408, 290, R.drawable.canvw_left_door01_dn);
        this.mDoorIcons[3] = addImage(535, 290, R.drawable.canvw_right_door01_dn);
        this.mDoorIcons[4] = addImage(449, 373, R.drawable.canvw_car3trunk_dn);
        this.mDoorIcons[5] = addImage(436, Can.CAN_FORD_WC, R.drawable.canvw_head_door);
        showDoor(0, 0, 0, 0, 0, 0);
        addImage(922, 38, R.drawable.canvw_speed_up);
        addImage(922, 165, R.drawable.canvw_instant_up);
        addImage(922, 292, R.drawable.canvw_road_haul_up);
        this.mWaterItem = addTextView(60, KeyDef.RKEY_MEDIA_SLOW, R.string.can_car_water_temp, -1, false);
        this.mTempItem = addTextView(97, 180, R.string.can_out_temp, -1, false);
        this.mElctricItem = addTextView(198, 78, R.string.can_battery, -1, false);
        this.mSeatBeltItem = addTextView(410, 5, R.string.can_drive_safe_belt, -1, false);
        this.mSideBeltItem = addTextView(CanCameraUI.BTN_CC_WC_DIRECTION1, 78, R.string.can_passenger_safe_belt, -1, false);
        this.mParkingItem = addTextView(802, 180, R.string.can_brake, -1, false);
        this.mTrunkUpItem = addTextView(KeyDef.SKEY_RETURN_1, KeyDef.RKEY_MEDIA_SLOW, R.string.can_trunk, -1, false);
        this.mRPMItem = addTextView(1080, 57, R.string.can_rpm, ViewCompat.MEASURED_STATE_MASK, true);
        this.mSpeedItem = addTextView(1080, 184, R.string.can_curspeed, ViewCompat.MEASURED_STATE_MASK, true);
        this.mDistanceItem = addTextView(1080, 311, R.string.can_driving_mileage, ViewCompat.MEASURED_STATE_MASK, true);
    }

    private void showDoor(int lf, int rf, int lr, int rr, int back, int head) {
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
        if (head != 1) {
            i6 = 8;
        }
        customImgView6.setVisibility(i6);
    }

    private TextView addTextView(int x, int y, int textId, int textSize) {
        TextView tv = addText(x, y, textId);
        setTextStyle(tv, -1, textSize, 17);
        return tv;
    }

    private TextView addTextView(int x, int y, int textId, int color, boolean isCenterAlign) {
        RelativeLayout.LayoutParams layoutLp = new RelativeLayout.LayoutParams(160, -2);
        layoutLp.leftMargin = x;
        layoutLp.topMargin = y;
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(1);
        if (isCenterAlign) {
            layout.setGravity(1);
        }
        layout.setLayoutParams(layoutLp);
        this.mManager.GetLayout().addView(layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        TextView title = new TextView(getActivity());
        title.setMaxLines(1);
        title.setText(textId);
        title.setTextColor(color);
        int textSize = this.mScreenHeight == 400 ? 20 : 24;
        title.setTextSize(0, (float) textSize);
        title.setLayoutParams(lp);
        layout.addView(title);
        lp.topMargin = 5;
        TextView content = new TextView(getActivity());
        content.setTextSize(0, (float) textSize);
        content.setMaxLines(1);
        content.setTextColor(color);
        content.setLayoutParams(lp);
        layout.addView(content);
        return content;
    }

    public void ResetData(boolean check) {
        int i;
        CanJni.GetCarInfo(this.mCanMsg);
        CanJni.GetDoorInfo(this.mDoorInfo);
        if (i2b(this.mCanMsg.UpdateOnce) && (!check || i2b(this.mCanMsg.Update))) {
            this.mCanMsg.Update = 0;
            this.mTempItem.setText("-.-");
            this.mWaterItem.setText(String.format("%d℃", new Object[]{Integer.valueOf(this.mCanMsg.WaterTemp - 40)}));
            this.mElctricItem.setText(String.format("%.2fV", new Object[]{Double.valueOf(((double) ((float) this.mCanMsg.BatV)) * 0.01d)}));
            this.mSeatBeltIcon.SetSel(this.mCanMsg.DriveSafe);
            this.mSideBeltIcon.SetSel(this.mCanMsg.PassengerSafe);
            this.mParkingIcon.SetSel(this.mCanMsg.HandBrake);
            this.mParkingItem.setText(i2b(this.mCanMsg.HandBrake) ? R.string.can_brake_on : R.string.can_brake_off);
            if (this.mCanMsg.Distance == 16777215) {
                this.mDistanceItem.setText("-.-KM");
            } else {
                this.mDistanceItem.setText(String.format("%dKM", new Object[]{Integer.valueOf(this.mCanMsg.Distance)}));
            }
            this.mRPMItem.setText(String.format("%dRPM", new Object[]{Integer.valueOf(this.mCanMsg.Rpm)}));
            this.mSpeedItem.setText(String.format("%dKm/h", new Object[]{Integer.valueOf(this.mCanMsg.Speed)}));
        }
        this.mTrunkUpIcon.SetSel(this.mDoorInfo.RearOpen);
        TextView textView = this.mTrunkUpItem;
        if (i2b(this.mDoorInfo.RearOpen)) {
            i = R.string.can_teramont_open;
        } else {
            i = R.string.can_mzd_cx4_mode_off;
        }
        textView.setText(i);
    }

    private void updateDoor() {
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
        showDoor(this.mDoorInfo.LFOpen, this.mDoorInfo.RFOpen, this.mDoorInfo.LROpen, this.mDoorInfo.RROpen, this.mDoorInfo.RearOpen, this.mDoorInfo.HeadOpen);
    }

    public void QueryData() {
        CanJni.MzdCx4Query(2, 0);
        CanJni.MzdCx4BnrRpmInit(4);
        CanJni.MzdCx4BnrSpeedInit(4);
    }

    public void doOnPause() {
        super.doOnPause();
        CanJni.MzdCx4BnrRpmInit(0);
        CanJni.MzdCx4BnrSpeedInit(0);
    }

    public static void ShowWin() {
        if (!mWin) {
            mfgJump = true;
            mLastWarnTime = SystemClock.uptimeMillis();
            CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
        }
    }

    public void doOnFinish() {
        long time = SystemClock.uptimeMillis();
        if (mfgJump && time > mLastWarnTime + 3000) {
            mLastWarnTime = time;
            mfgJump = false;
            mWin = false;
            getActivity().finish();
        }
    }
}
