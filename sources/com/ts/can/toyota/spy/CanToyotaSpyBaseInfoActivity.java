package com.ts.can.toyota.spy;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
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
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.KeyDef;

public class CanToyotaSpyBaseInfoActivity extends CanBaseActivity implements UserCallBack {
    private ImageView mBatteryIcon;
    private TextView mDistanceItem;
    private ImageView[] mDoorIcons = new ImageView[5];
    private CanDataInfo.CAN_DoorInfo mDoorInfo = new CanDataInfo.CAN_DoorInfo();
    private TextView mElctricItem;
    private CanDataInfo.ToyotaSpyCarInfo1 mInfo1 = new CanDataInfo.ToyotaSpyCarInfo1();
    private CanDataInfo.ToyotaSpyCarInfo2 mInfo2 = new CanDataInfo.ToyotaSpyCarInfo2();
    private long mLastQueryTime = 0;
    private long mLastWarnTime = 0;
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
    private boolean mfgJump;
    private boolean mfgWarn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_vw_car_info);
        InitUI();
        if (getIntent().getExtras() != null) {
            this.mfgJump = true;
            Log.d(CanBaseActivity.TAG, "mfgJump");
            this.mLastWarnTime = GetTickCount();
            return;
        }
        this.mfgJump = false;
    }

    private void InitUI() {
        this.mManager = (RelativeLayout) findViewById(R.id.layout_vw_carinfo);
        this.mOilIcon = addImage(181, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_elctric_up, R.drawable.canvw_elctric_dn);
        addImage(217, 173, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_dn);
        this.mBatteryIcon = addImage(KeyDef.RKEY_ANGLEDN, 71, R.drawable.canvw_battery_up, R.drawable.canvw_battery_dn);
        this.mSeatBeltIcon = addImage(456, 33, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mTrunkUpIcon = addImage(594, 71, R.drawable.canvw_trunk_up, R.drawable.canvw_trunk_dn);
        this.mParkingIcon = addImage(696, 173, R.drawable.canvw_parking_up, R.drawable.canvw_parking_dn);
        this.mWashingIcon = addImage(734, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_washing_up, R.drawable.canvw_washing_dn);
        addImage(432, 145, R.drawable.canvw_car3_up);
        this.mDoorIcons[0] = addImage(422, Can.CAN_FLAT_WC, R.drawable.canvw_left_door_dn);
        this.mDoorIcons[1] = addImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, Can.CAN_FLAT_WC, R.drawable.canvw_right_door_dn);
        this.mDoorIcons[2] = addImage(428, 290, R.drawable.canvw_left_door01_dn);
        this.mDoorIcons[3] = addImage(555, 290, R.drawable.canvw_right_door01_dn);
        this.mDoorIcons[4] = addImage(469, 373, R.drawable.canvw_car3trunk_dn);
        showDoor(0, 0, 0, 0, 0);
        addImage(11, 429, R.drawable.canvw_speed_up);
        addImage(347, 429, R.drawable.canvw_instant_up);
        addImage(683, 429, R.drawable.canvw_road_haul_up);
        this.mOilItem = addText(80, KeyDef.RKEY_MEDIA_SLOW, R.string.can_rest_oil, -1, false);
        this.mTempItem = addText(117, 180, R.string.can_lqywd, -1, false);
        this.mDistanceItem = addText(855, 445, R.string.can_trav_time, -16777216, true);
        this.mElctricItem = addText(218, 78, R.string.can_battery, -1, false);
        this.mSeatBeltItem = addText(365, 5, R.string.can_belt);
        this.mTrunkUpItem = addText(720, 78, R.string.can_trunk, -1, false);
        this.mParkingItem = addText(KeyDef.SKEY_CALLDN_4, 180, R.string.can_brake, -1, false);
        this.mWashingItem = addText(859, KeyDef.RKEY_MEDIA_SLOW, R.string.can_wash, -1, false);
        this.mRPMItem = addText(161, 445, R.string.can_rpm, -16777216, true);
        this.mSpeedItem = addText(515, 445, R.string.can_curspeed, -16777216, true);
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
        CanJni.ToyotaSpyGetCarInfo1(this.mInfo1);
        CanJni.ToyotaSpyGetCarInfo2(this.mInfo2);
        CanJni.GetDoorInfo(this.mDoorInfo);
        ResetData();
        CanJni.ToyotaSpyQuery();
        super.onResume();
    }

    private void CheckData() {
        CanJni.ToyotaSpyGetCarInfo1(this.mInfo1);
        CanJni.ToyotaSpyGetCarInfo2(this.mInfo2);
        CanJni.GetDoorInfo(this.mDoorInfo);
        if (i2b(this.mInfo1.Update) || i2b(this.mInfo2.Update) || i2b(this.mDoorInfo.Update)) {
            ResetData();
        }
    }

    private void ResetData() {
        this.mInfo1.Update = 0;
        this.mInfo2.Update = 0;
        this.mDoorInfo.Update = 0;
        boolean fgWarnOil = i2b(this.mInfo1.fgWarnOil);
        boolean fgWarnBat = i2b(this.mInfo1.fgWarnBat);
        boolean fgQSY = i2b(this.mInfo1.fgQSY);
        boolean fgSS = i2b(this.mInfo1.fgSS) && i2b(this.mInfo1.fgDriving);
        boolean fgAQD = i2b(this.mInfo1.fgAQD) && i2b(this.mInfo1.fgDriving);
        this.mOilIcon.setSelected(fgWarnOil);
        this.mBatteryIcon.setSelected(fgWarnBat);
        if (fgQSY) {
            this.mWashingIcon.setSelected(true);
            this.mWashingItem.setText(R.string.can_wash_low);
        } else {
            this.mWashingIcon.setSelected(false);
            this.mWashingItem.setText(R.string.can_normal);
        }
        if (fgSS) {
            this.mParkingIcon.setSelected(true);
            this.mParkingItem.setText("");
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
        if (fgAQD || fgSS || fgQSY || fgWarnOil || fgWarnBat || CanFunc.IsDoorOpen(this.mDoorInfo)) {
            this.mfgWarn = true;
            this.mLastWarnTime = GetTickCount();
        } else {
            this.mfgWarn = false;
        }
        this.mOilItem.setText(String.format("%dL", new Object[]{Integer.valueOf(this.mInfo2.RemainOil)}));
        this.mTempItem.setText(String.format("%.2f℃", new Object[]{Double.valueOf(((double) ((float) this.mInfo2.OutTemp)) * 0.1d)}));
        this.mElctricItem.setText(String.format("%.2fV", new Object[]{Double.valueOf(((double) ((float) this.mInfo2.Voltage)) * 0.01d)}));
        this.mRPMItem.setText(String.format("%dRPM", new Object[]{Integer.valueOf(this.mInfo2.Rpm)}));
        this.mSpeedItem.setText(String.format("%.2fKm/h", new Object[]{Double.valueOf(((double) ((float) this.mInfo2.CurSpeed)) * 0.01d)}));
        this.mDistanceItem.setText(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(this.mInfo2.Range / 3600), Integer.valueOf((this.mInfo2.Range / 60) % 60), Integer.valueOf(this.mInfo2.Range % 60)}));
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
            return;
        }
        this.mTrunkUpIcon.setSelected(false);
        this.mTrunkUpItem.setText(R.string.can_trunk_close);
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
            CanJni.ToyotaSpyQuery();
        }
        CheckData();
        if (this.mfgJump && !this.mfgWarn && time > this.mLastWarnTime + 3000) {
            this.mLastWarnTime = time;
            finish();
        }
    }
}
