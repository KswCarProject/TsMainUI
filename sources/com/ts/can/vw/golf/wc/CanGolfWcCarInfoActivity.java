package com.ts.can.vw.golf.wc;

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
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.KeyDef;

public class CanGolfWcCarInfoActivity extends CanBaseActivity implements UserCallBack {
    private ImageView mBatteryIcon;
    private ImageView[] mDoorIcons = new ImageView[6];
    private CanDataInfo.CAN_DoorInfo mDoorInfo = new CanDataInfo.CAN_DoorInfo();
    private TextView mElctricItem;
    private CanDataInfo.GolfWcInfo1 mInfo1 = new CanDataInfo.GolfWcInfo1();
    private CanDataInfo.GolfWcInfo2 mInfo2 = new CanDataInfo.GolfWcInfo2();
    private long mLastQueryTime = 0;
    private long mLastWarnTime = 0;
    private RelativeLayout mManager;
    private ImageView mOilIcon;
    private TextView mOilItem;
    private TextView mOilTempItem;
    private TextView mOilValueItem;
    private TextView mQuickOilItem;
    private TextView mRPMItem;
    private ImageView mSeatBeltIcon;
    private TextView mSeatBeltItem;
    private ImageView mTrunkUpIcon;
    private TextView mTrunkUpItem;
    private ImageView mWashingIcon;
    private TextView mWashingItem;
    private TextView mWaterTempItem;
    private boolean mfgJump;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_vw_car_info);
        InitUI();
        if (getIntent().getExtras() != null) {
            Log.d(CanBaseActivity.TAG, "mfgJump");
            this.mfgJump = true;
            this.mLastWarnTime = GetTickCount();
            return;
        }
        this.mfgJump = false;
    }

    private void InitUI() {
        this.mManager = (RelativeLayout) findViewById(R.id.layout_vw_carinfo);
        this.mOilIcon = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 181, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_elctric_up, R.drawable.canvw_elctric_dn);
        addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 217, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 173, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_dn);
        this.mBatteryIcon = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + KeyDef.RKEY_ANGLEDN, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 71, R.drawable.canvw_battery_up, R.drawable.canvw_battery_dn);
        this.mSeatBeltIcon = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 456, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 33, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mTrunkUpIcon = addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 594, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 71, R.drawable.canvw_trunk_up, R.drawable.canvw_trunk_dn);
        addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 696, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 173, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_up);
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
        addImage(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 347, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 429, R.drawable.canvw_oil_up);
        addImage(getResources().getDimensionPixelSize(R.dimen.x2_vw_carinfo_flg) + 683, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 429, R.drawable.canvw_oil_value_icon);
        this.mOilItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 80, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + KeyDef.RKEY_MEDIA_SLOW, R.string.can_oil_warn, -1, false);
        this.mOilTempItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 117, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 180, R.string.can_oil_temp, -1, false);
        this.mElctricItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 218, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 78, R.string.can_battery, -1, false);
        this.mSeatBeltItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 365, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 5, R.string.can_belt);
        this.mTrunkUpItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 720, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 78, R.string.can_trunk, -1, false);
        this.mWaterTempItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + KeyDef.SKEY_CALLDN_4, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + 180, R.string.can_car_water_temp, -1, false);
        this.mWashingItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 859, getResources().getDimensionPixelSize(R.dimen.y_vw_carinfo_flg) + KeyDef.RKEY_MEDIA_SLOW, R.string.can_wash, -1, false);
        this.mRPMItem = addText(getResources().getDimensionPixelSize(R.dimen.x1_vw_carinfo_flg) + 150, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 445, R.string.can_rpm, ViewCompat.MEASURED_STATE_MASK, true);
        this.mQuickOilItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + 500, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 445, R.string.can_ssyh, ViewCompat.MEASURED_STATE_MASK, true);
        this.mOilValueItem = addText(getResources().getDimensionPixelSize(R.dimen.x_vw_carinfo_flg) + KeyDef.SKEY_NAVI_2, getResources().getDimensionPixelSize(R.dimen.y2_vw_carinfo_flg) + 445, R.string.can_rest_oil, ViewCompat.MEASURED_STATE_MASK, true);
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
        RelativeLayout.LayoutParams layoutLp = new RelativeLayout.LayoutParams(160, -2);
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
        if (textId != 0) {
            title.setText(textId);
        }
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
        CanJni.GolfWcGetInfo1(this.mInfo1);
        CanJni.GolfWcGetInfo2(this.mInfo2);
        CanJni.GetDoorInfo(this.mDoorInfo);
        ResetData();
        QueryData();
        super.onResume();
    }

    private void QueryData() {
        CanJni.GolfWcQueryData(1, 18);
        Sleep(5);
        CanJni.GolfWcQueryData(1, 25);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void CheckData() {
        CanJni.GolfWcGetInfo1(this.mInfo1);
        CanJni.GolfWcGetInfo2(this.mInfo2);
        CanJni.GetDoorInfo(this.mDoorInfo);
        if (i2b(this.mInfo1.Update) || i2b(this.mInfo2.Update) || i2b(this.mDoorInfo.Update)) {
            ResetData();
        }
    }

    private void ResetData() {
        this.mInfo1.Update = 0;
        this.mInfo2.Update = 0;
        this.mDoorInfo.Update = 0;
        this.mOilIcon.setSelected(i2b(this.mInfo1.OilLow));
        if (i2b(this.mInfo1.OilLow)) {
            this.mOilItem.setText(R.string.can_wash_low);
        } else {
            this.mOilItem.setText(R.string.can_normal);
        }
        this.mOilTempItem.setText(String.format("%d℃", new Object[]{Integer.valueOf(this.mInfo2.OilTemp - 40)}));
        this.mWaterTempItem.setText(String.format("%d℃", new Object[]{Integer.valueOf(this.mInfo2.WaterTemp - 40)}));
        this.mBatteryIcon.setSelected(i2b(this.mInfo1.BatLow));
        this.mElctricItem.setText(String.format("%d.%dV", new Object[]{Integer.valueOf(this.mInfo1.BatVh), Integer.valueOf(this.mInfo1.BatVl)}));
        if (i2b(this.mInfo1.SafeWarn)) {
            this.mSeatBeltIcon.setSelected(true);
            this.mSeatBeltItem.setText(getString(R.string.can_belt_on));
        } else {
            this.mSeatBeltIcon.setSelected(false);
            this.mSeatBeltItem.setText(String.valueOf(getString(R.string.can_belt)) + "  " + getString(R.string.can_off));
        }
        if (i2b(this.mInfo1.LeanerWarm)) {
            this.mWashingIcon.setSelected(true);
            this.mWashingItem.setText(R.string.can_wash_low);
        } else {
            this.mWashingIcon.setSelected(false);
            this.mWashingItem.setText(R.string.can_normal);
        }
        this.mRPMItem.setText(String.format("%dRPM", new Object[]{Integer.valueOf(this.mInfo2.Rmp)}));
        this.mQuickOilItem.setText(String.format("%d.%dL/100KM", new Object[]{Integer.valueOf(this.mInfo1.Ssyhh), Integer.valueOf(this.mInfo1.Ssyhl)}));
        this.mOilValueItem.setText(String.format("%dL", new Object[]{Integer.valueOf(this.mInfo1.Syyl)}));
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
        if (head != 1) {
            i6 = 8;
        }
        imageView6.setVisibility(i6);
    }

    public void UserAll() {
        long time = GetTickCount();
        if (time > this.mLastQueryTime + 1000) {
            this.mLastQueryTime = time;
            QueryData();
        }
        CheckData();
        if (this.mfgJump && time > this.mLastWarnTime + 3000) {
            this.mLastWarnTime = time;
            finish();
        }
    }
}
