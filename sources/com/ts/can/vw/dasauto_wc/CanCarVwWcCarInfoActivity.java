package com.ts.can.vw.dasauto_wc;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
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
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.main.common.MainSet;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.KeyDef;

public class CanCarVwWcCarInfoActivity extends CanBaseActivity implements UserCallBack {
    public static final String TAG = "CanCarVwWcCarInfoActivity";
    private static long mLastWarnTime = 0;
    private static boolean mWin;
    private static boolean mfgJump = false;
    private ImageView mBatteryIcon;
    private CanDataInfo.CAN_Msg mCarInfo = new CanDataInfo.CAN_Msg();
    private CanDataInfo.VwWcCarOutTemp mCarOutTemp = new CanDataInfo.VwWcCarOutTemp();
    private TextView mDistanceItem;
    private ImageView[] mDoorIcons = new ImageView[6];
    private CanDataInfo.CAN_DoorInfo mDoorInfo = new CanDataInfo.CAN_DoorInfo();
    private int mDoorTick = 0;
    private TextView mElctricItem;
    private CanDataInfo.VwWcCarInfo1 mInfo1 = new CanDataInfo.VwWcCarInfo1();
    private CanDataInfo.VwWcCarInfo2 mInfo2 = new CanDataInfo.VwWcCarInfo2();
    private long mLastQueryTime = 0;
    private RelativeLayout mManager;
    private ImageView mOilIcon;
    private TextView mOilItem;
    private ImageView mParkingIcon;
    private TextView mParkingItem;
    private TextView mRPMItem;
    private ImageView mSeatBeltIcon;
    private TextView mSeatBeltItem;
    private TextView mSpeedItem;
    private TextView mTempItem;
    private ImageView mTrunkUpIcon;
    private TextView mTrunkUpItem;
    private ImageView mWashingIcon;
    private TextView mWashingItem;
    private boolean mfgWarn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_vw_car_info);
        if (MainSet.GetScreenType() == 5) {
            InitUI_1280x480();
        } else {
            InitUI();
        }
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
        this.mDoorIcons[5] = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 459, getResources().getDimensionPixelSize(R.dimen.y1_vw_carinfo_flg) + Can.CAN_HONDA_WC, R.drawable.canvw_head_door);
        showDoor(0, 0, 0, 0, 0, 0);
        addImage(getResources().getDimensionPixelSize(R.dimen.x1_vw_carinfo_flg) + 11, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 429, R.drawable.canvw_speed_up);
        addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 347, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 429, R.drawable.canvw_instant_up);
        addImage(getResources().getDimensionPixelSize(R.dimen.x2_vw_carinfo_flg) + 683, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 429, R.drawable.canvw_road_haul_up);
        this.mOilItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 80, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + KeyDef.RKEY_MEDIA_SLOW, R.string.can_rest_oil, -1, false);
        this.mTempItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 117, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 180, R.string.can_out_temp, -1, false);
        this.mDistanceItem = addText(getResources().getDimensionPixelSize(R.dimen.x2_vw_carinfo_flg) + 855, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 445, R.string.can_driving_mileage, ViewCompat.MEASURED_STATE_MASK, true);
        this.mElctricItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 218, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 78, R.string.can_battery, -1, false);
        this.mSeatBeltItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 365, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 5, R.string.can_belt);
        this.mTrunkUpItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 720, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 78, R.string.can_trunk, -1, false);
        this.mParkingItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + KeyDef.SKEY_CALLDN_4, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 180, R.string.can_brake, -1, false);
        this.mWashingItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 859, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + KeyDef.RKEY_MEDIA_SLOW, R.string.can_wash, -1, false);
        this.mRPMItem = addText(getResources().getDimensionPixelSize(R.dimen.x1_vw_carinfo_flg) + 161, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 445, R.string.can_rpm, ViewCompat.MEASURED_STATE_MASK, true);
        this.mSpeedItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 515, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 445, R.string.can_curspeed, ViewCompat.MEASURED_STATE_MASK, true);
    }

    private void InitUI_1280x480() {
        this.mManager = (RelativeLayout) findViewById(R.id.layout_vw_carinfo);
        this.mManager.setBackgroundResource(R.drawable.can_vw_carinfo_bg_1280x480);
        this.mOilIcon = addImage(161, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_elctric_up, R.drawable.canvw_elctric_dn);
        addImage(197, 173, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_dn);
        this.mBatteryIcon = addImage(298, 71, R.drawable.canvw_battery_up, R.drawable.canvw_battery_dn);
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
        showDoor(0, 0, 0, 0, 0, 0);
        addImage(922, 38, R.drawable.canvw_speed_up);
        addImage(922, 165, R.drawable.canvw_instant_up);
        addImage(922, 292, R.drawable.canvw_road_haul_up);
        this.mOilItem = addText(60, KeyDef.RKEY_MEDIA_SLOW, R.string.can_rest_oil, -1, false);
        this.mTempItem = addText(97, 180, R.string.can_out_temp, -1, false);
        this.mDistanceItem = addText(1080, 311, R.string.can_driving_mileage, ViewCompat.MEASURED_STATE_MASK, true);
        this.mElctricItem = addText(198, 78, R.string.can_battery, -1, false);
        this.mSeatBeltItem = addText(345, 5, R.string.can_belt);
        this.mTrunkUpItem = addText(CanCameraUI.BTN_CC_WC_DIRECTION1, 78, R.string.can_trunk, -1, false);
        this.mParkingItem = addText(802, 180, R.string.can_brake, -1, false);
        this.mWashingItem = addText(KeyDef.SKEY_RETURN_1, KeyDef.RKEY_MEDIA_SLOW, R.string.can_wash, -1, false);
        this.mRPMItem = addText(1080, 57, R.string.can_rpm, ViewCompat.MEASURED_STATE_MASK, true);
        this.mSpeedItem = addText(1080, 184, R.string.can_curspeed, ViewCompat.MEASURED_STATE_MASK, true);
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

    private TextView addText(int x, int y, int textId, int color, boolean isCenterAlign) {
        RelativeLayout.LayoutParams layoutLp = new RelativeLayout.LayoutParams(130, -2);
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
        title.setTextSize(0, 24.0f);
        title.setLayoutParams(lp);
        layout.addView(title);
        lp.topMargin = 5;
        TextView content = new TextView(this);
        content.setTextSize(0, 24.0f);
        content.setMaxLines(1);
        content.setTextColor(color);
        content.setLayoutParams(lp);
        layout.addView(content);
        return content;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        CheckData(false);
        super.onResume();
        Log.d(TAG, "-----onResume-----");
        mWin = true;
    }

    public static void ShowWin() {
        if (!mWin) {
            mfgJump = true;
            mLastWarnTime = GetTickCount();
            CanFunc.showCanActivity(CanCarVwWcCarInfoActivity.class);
        }
    }

    private void CheckData(boolean check) {
        CanJni.VwWcGetCarInfo1(this.mInfo1);
        if (i2b(this.mInfo1.UpdateOnce) && (!check || i2b(this.mInfo1.Update))) {
            this.mInfo1.Update = 0;
            boolean fgWarnOil = i2b(this.mInfo1.fgWarnOil);
            boolean fgWarnBat = i2b(this.mInfo1.fgWarnBat);
            boolean fgQSY = i2b(this.mInfo1.fgQSY);
            boolean fgAQD = i2b(this.mInfo1.fgAQD);
            this.mOilIcon.setSelected(fgWarnOil);
            this.mBatteryIcon.setSelected(fgWarnBat);
            if (fgQSY) {
                this.mWashingIcon.setSelected(true);
                this.mWashingItem.setText(R.string.can_wash_low);
            } else {
                this.mWashingIcon.setSelected(false);
                this.mWashingItem.setText(R.string.can_normal);
            }
            if (fgAQD) {
                this.mSeatBeltIcon.setSelected(true);
                this.mSeatBeltItem.setText(getString(R.string.can_belt_on));
            } else {
                this.mSeatBeltIcon.setSelected(false);
                this.mSeatBeltItem.setText(String.valueOf(getString(R.string.can_belt)) + "  " + getString(R.string.can_off));
            }
            if (fgAQD || fgQSY || fgWarnOil || fgWarnBat || CanFunc.IsDoorOpen(this.mDoorInfo)) {
                mLastWarnTime = GetTickCount();
            }
            this.mOilItem.setText(String.format("%dL", new Object[]{Integer.valueOf(this.mInfo1.RemainOil)}));
            this.mElctricItem.setText(String.format("%d.%dV", new Object[]{Integer.valueOf(this.mInfo1.VoltageH), Integer.valueOf(this.mInfo1.VoltageL)}));
        }
        CanJni.GetCarInfo(this.mCarInfo);
        if (i2b(this.mCarInfo.UpdateOnce) && (!check || i2b(this.mCarInfo.Update))) {
            boolean fgSS = i2b(this.mCarInfo.HandBrake);
            this.mCarInfo.Update = 0;
            this.mSpeedItem.setText(String.format("%d Km/h", new Object[]{Integer.valueOf(this.mCarInfo.Speed)}));
            if (fgSS) {
                mLastWarnTime = GetTickCount();
            }
            if (fgSS) {
                this.mParkingIcon.setSelected(true);
                this.mParkingItem.setText(TXZResourceManager.STYLE_DEFAULT);
            } else {
                this.mParkingIcon.setSelected(false);
                this.mParkingItem.setText(R.string.can_normal);
            }
        }
        CanJni.VwWcGetCarInfo2(this.mInfo2);
        if (i2b(this.mInfo2.UpdateOnce) && (!check || i2b(this.mInfo2.Update))) {
            this.mInfo2.Update = 0;
            this.mRPMItem.setText(String.format("%dRPM", new Object[]{Integer.valueOf(this.mInfo2.Rpm)}));
            this.mDistanceItem.setText(String.format("%dKm", new Object[]{Integer.valueOf(this.mInfo2.Range)}));
        }
        CanJni.VwWcGetOutTemp(this.mCarOutTemp);
        if (i2b(this.mCarOutTemp.UpdateOnce) && (!check || i2b(this.mCarOutTemp.Update))) {
            this.mCarOutTemp.Update = 0;
            this.mTempItem.setText(String.format("%.1f℃", new Object[]{Double.valueOf((((double) ((float) this.mCarOutTemp.Temp)) * 0.5d) - 40.0d)}));
        }
        this.mDoorTick++;
        if (this.mDoorTick > 20) {
            this.mDoorTick = 0;
        }
        CanJni.GetDoorInfo(this.mDoorInfo);
        if (!i2b(this.mDoorInfo.UpdateOnce)) {
            return;
        }
        if (!check || this.mDoorTick == 0) {
            this.mDoorInfo.Update = 0;
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
            if (i2b(this.mDoorInfo.RearOpen)) {
                this.mTrunkUpIcon.setSelected(true);
                this.mTrunkUpItem.setText(R.string.can_trunk_open);
                return;
            }
            this.mTrunkUpIcon.setSelected(false);
            this.mTrunkUpItem.setText(R.string.can_trunk_close);
        }
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
        if (back == 1) {
            i5 = 0;
        } else {
            i5 = 8;
        }
        imageView5.setVisibility(i5);
        ImageView imageView6 = this.mDoorIcons[5];
        if (Front != 1) {
            i6 = 8;
        }
        imageView6.setVisibility(i6);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
        mfgJump = false;
        mWin = false;
    }

    public void UserAll() {
        long time = GetTickCount();
        if (time > this.mLastQueryTime + 1000) {
            this.mLastQueryTime = time;
        }
        CheckData(true);
        if (mfgJump) {
            Log.d(TAG, "mfgJump=" + mfgJump);
            if (time > mLastWarnTime + 3000) {
                finish();
            }
        }
    }
}
