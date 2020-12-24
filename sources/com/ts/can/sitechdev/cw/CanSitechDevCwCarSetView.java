package com.ts.can.sitechdev.cw;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.yyw.ts70xhw.KeyDef;

public class CanSitechDevCwCarSetView extends CanRelativeCarInfoView {
    public static final String TAG = "CanSitechDevCwCarSetView";
    private static long mLastWarnTime = 0;
    private static boolean mWin;
    private static boolean mfgJump = false;
    private String[] mAqdStrs;
    private ImageView mBatteryIcon;
    private CanDataInfo.SitechDev_CarInfo1 mDev_CarInfo1;
    private CanDataInfo.SitechDev_CarInfo2 mDev_CarInfo2;
    private CanDataInfo.SitechDev_CarInfo3 mDev_CarInfo3;
    private TextView mDistanceItem;
    private TextView mElctricItem;
    private long mLastQueryTime = 0;
    private RelativeLayout mManager;
    private ImageView mOilIcon;
    private TextView mOilItem;
    private ImageView mParkingIcon;
    private TextView mParkingItem;
    private String[] mQxyStrs;
    private TextView mRPMItem;
    private ImageView mSeatBeltIcon;
    private TextView mSeatBeltItem;
    private TextView mSpeedItem;
    private String[] mSsStrs;
    private TextView mTempItem;
    private ImageView mTrunkUpIcon;
    private TextView mTrunkUpItem;
    private ImageView mWashingIcon;
    private TextView mWashingItem;
    private boolean mfgWarn;

    public CanSitechDevCwCarSetView(Activity activity) {
        super(activity);
    }

    public void onClick(View v) {
    }

    public void ResetData(boolean check) {
        CanJni.SitechDevCwGetCarInfo1(this.mDev_CarInfo1);
        if (i2b(this.mDev_CarInfo1.UpdateOnce) && (!check || i2b(this.mDev_CarInfo1.Update))) {
            this.mDev_CarInfo1.Update = 0;
            boolean fgQSY = i2b(this.mDev_CarInfo1.Qxy);
            boolean fgAQD = i2b(this.mDev_CarInfo1.Aqd);
            boolean fgSS = i2b(this.mDev_CarInfo1.Ss);
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
            if (fgSS) {
                this.mParkingIcon.setSelected(true);
                this.mParkingItem.setText("");
            } else {
                this.mParkingIcon.setSelected(false);
                this.mParkingItem.setText(R.string.can_normal);
            }
        }
        CanJni.SitechDevCwGetCarInfo2(this.mDev_CarInfo2);
        if (i2b(this.mDev_CarInfo2.UpdateOnce) && (!check || i2b(this.mDev_CarInfo2.Update))) {
            this.mDev_CarInfo2.Update = 0;
            this.mRPMItem.setText(String.format("%d RPM", new Object[]{Integer.valueOf(this.mDev_CarInfo2.Rpm)}));
            this.mSpeedItem.setText(GetSpeed(this.mDev_CarInfo2.Speed));
            this.mElctricItem.setText(GetDcdy(this.mDev_CarInfo2.BatV));
            this.mDistanceItem.setText(String.format("%d Km", new Object[]{Integer.valueOf(this.mDev_CarInfo2.Xclc)}));
            this.mTempItem.setText(String.format("%.1f ℃", new Object[]{Double.valueOf(((double) this.mDev_CarInfo2.Cwwd) * 0.1d)}));
            this.mOilItem.setText(String.format("%d L", new Object[]{Integer.valueOf(this.mDev_CarInfo2.Syyl)}));
        }
        CanJni.SitechDevCwGetCarInfo3(this.mDev_CarInfo3);
        if (!i2b(this.mDev_CarInfo3.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDev_CarInfo3.Update)) {
            this.mDev_CarInfo3.Update = 0;
            boolean fgWarnOil = i2b(this.mDev_CarInfo3.Yljg);
            boolean fgWarnBat = i2b(this.mDev_CarInfo3.Dcdy);
            this.mOilIcon.setSelected(fgWarnOil);
            this.mBatteryIcon.setSelected(fgWarnBat);
        }
    }

    public String GetSpeed(int value) {
        if (value == 0) {
            return String.valueOf(value) + " km/h";
        }
        return String.valueOf(String.format("%.2f", new Object[]{Float.valueOf((float) (((double) value) * 0.01d))})) + " km/h";
    }

    public String GetDcdy(int value) {
        if (value == 0) {
            return String.valueOf(value) + " V";
        }
        return String.valueOf(String.format("%.2f", new Object[]{Float.valueOf((float) (((double) value) * 0.01d))})) + " V";
    }

    public String GetValue(int id, int value) {
        switch (id) {
            case 0:
                if (value >= 0 && value < this.mAqdStrs.length) {
                    return this.mAqdStrs[value];
                }
            case 1:
                if (value >= 0 && value < this.mQxyStrs.length) {
                    return this.mQxyStrs[value];
                }
            case 2:
                if (value >= 0 && value < this.mSsStrs.length) {
                    return this.mSsStrs[value];
                }
            case 8:
            case 9:
                if (value >= 0 && value < this.mQxyStrs.length) {
                    return this.mQxyStrs[value];
                }
        }
        return "";
    }

    public void QueryData() {
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mDev_CarInfo1 = new CanDataInfo.SitechDev_CarInfo1();
        this.mDev_CarInfo2 = new CanDataInfo.SitechDev_CarInfo2();
        this.mDev_CarInfo3 = new CanDataInfo.SitechDev_CarInfo3();
        this.mManager = getRelativeManager().GetLayout();
        this.mManager.setBackgroundResource(R.drawable.can_vw_carinfo_bg);
        this.mOilIcon = addImage(181, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_elctric_up, R.drawable.canvw_elctric_dn);
        addImage(217, 173, R.drawable.canvw_outtemd_up, R.drawable.canvw_outtemd_dn);
        this.mBatteryIcon = addImage(KeyDef.RKEY_ANGLEDN, 71, R.drawable.canvw_battery_up, R.drawable.canvw_battery_dn);
        this.mSeatBeltIcon = addImage(456, 33, R.drawable.canvw_seat_belt_up, R.drawable.canvw_seat_belt_dn);
        this.mTrunkUpIcon = addImage(594, 71, R.drawable.canvw_trunk_up, R.drawable.canvw_trunk_dn);
        this.mParkingIcon = addImage(696, 173, R.drawable.canvw_parking_up, R.drawable.canvw_parking_dn);
        this.mWashingIcon = addImage(734, KeyDef.RKEY_MEDIA_OSD, R.drawable.canvw_washing_up, R.drawable.canvw_washing_dn);
        addImage(432, 145, R.drawable.canvw_car3_up);
        addImage(11, 429, R.drawable.canvw_speed_up);
        addImage(347, 429, R.drawable.canvw_instant_up);
        addImage(683, 429, R.drawable.canvw_road_haul_up);
        this.mOilItem = addText(80, KeyDef.RKEY_MEDIA_SLOW, R.string.can_rest_oil, -1, false);
        this.mTempItem = addText(117, 180, R.string.can_out_temp, -1, false);
        this.mDistanceItem = addText(855, 445, R.string.can_driving_mileage, -16777216, true);
        this.mElctricItem = addText(218, 78, R.string.can_battery, -1, false);
        this.mSeatBeltItem = addText(365, 5, R.string.can_belt);
        this.mTrunkUpItem = addText(720, 78, R.string.can_trunk, -1, false);
        this.mParkingItem = addText(KeyDef.SKEY_CALLDN_4, 180, R.string.can_brake, -1, false);
        this.mWashingItem = addText(859, KeyDef.RKEY_MEDIA_SLOW, R.string.can_wash, -1, false);
        this.mRPMItem = addText(161, 445, R.string.can_rpm, -16777216, true);
        this.mSpeedItem = addText(515, 445, R.string.can_curspeed, -16777216, true);
    }

    public CustomImgView addImage(int x, int y, int iconId) {
        CustomImgView image = new CustomImgView(getActivity());
        image.setImageResource(iconId);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        lp.leftMargin = x;
        lp.topMargin = y;
        image.setLayoutParams(lp);
        this.mManager.addView(image);
        return image;
    }

    public CustomImgView addImage(int x, int y, int normal, int select) {
        Drawable normalDrawable;
        Drawable selectDrawable = null;
        StateListDrawable drawable = new StateListDrawable();
        if (normal <= 0) {
            normalDrawable = null;
        } else {
            normalDrawable = getActivity().getResources().getDrawable(normal);
        }
        if (select > 0) {
            selectDrawable = getActivity().getResources().getDrawable(select);
        }
        drawable.addState(new int[]{16842913}, selectDrawable);
        drawable.addState(new int[0], normalDrawable);
        CustomImgView image = new CustomImgView(getActivity());
        image.setImageDrawable(drawable);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        lp.leftMargin = x;
        lp.topMargin = y;
        image.setLayoutParams(lp);
        this.mManager.addView(image);
        return image;
    }

    public TextView addText(int x, int y, int textId) {
        TextView text = new TextView(getActivity());
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
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(1);
        if (isCenterAlign) {
            layout.setGravity(1);
        }
        layout.setLayoutParams(layoutLp);
        this.mManager.addView(layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        TextView title = new TextView(getActivity());
        title.setMaxLines(1);
        title.setText(textId);
        title.setTextColor(color);
        title.setTextSize(0, 24.0f);
        title.setLayoutParams(lp);
        layout.addView(title);
        lp.topMargin = 5;
        TextView content = new TextView(getActivity());
        content.setTextSize(0, 24.0f);
        content.setMaxLines(1);
        content.setTextColor(color);
        content.setLayoutParams(lp);
        layout.addView(content);
        return content;
    }
}
