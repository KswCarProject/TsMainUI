package com.ts.main.benz;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.benc.withcd.CanBencWithCDCarFuncActivity;
import java.lang.ref.WeakReference;

public class DrivingComputeActivity extends Activity implements View.OnLongClickListener {
    private static final String SPEED_PAN = "speed_pan";
    private boolean is320SpeedPan;
    private boolean isLastSpeedPan;
    private CanDataInfo.CAN_Msg mCanMsg = new CanDataInfo.CAN_Msg();
    private String mDistanceStr;
    private WeakHandler mHandler;
    private ImageView mIvHandBreak;
    private ImageView mIvRatePointer;
    private ImageView mIvSafeSeat;
    private ImageView mIvSpeedPan;
    private ImageView mIvSpeedPointer;
    private int mLastRate = -1;
    private int mLastSpeed = -1;
    private ObjectAnimator mRateAnimator;
    private SharedPreferences mSp;
    private ObjectAnimator mSpeedAnimator;
    private String mSpeedStr;
    private TextView mTvDistance;
    private TextView mTvDistanceUnit;
    private TextView mTvSpeed;
    private TextView mTvSpeedUnit;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_compute);
        InitUI();
    }

    private void InitUI() {
        this.mIvSpeedPan = (ImageView) findViewById(R.id.iv_speed_pan);
        this.mIvSpeedPan.setOnLongClickListener(this);
        this.mIvSpeedPointer = (ImageView) findViewById(R.id.iv_speed_pointer);
        this.mIvRatePointer = (ImageView) findViewById(R.id.iv_rate_pointer);
        this.mIvSafeSeat = (ImageView) findViewById(R.id.iv_safe_seat);
        this.mIvHandBreak = (ImageView) findViewById(R.id.iv_hand_break);
        this.mTvDistance = (TextView) findViewById(R.id.tv_distance);
        this.mTvDistanceUnit = (TextView) findViewById(R.id.tv_distance_unit);
        this.mTvSpeed = (TextView) findViewById(R.id.tv_speed);
        this.mTvSpeedUnit = (TextView) findViewById(R.id.tv_speed_unit);
        this.mIvSpeedPointer.setRotation(-130.0f);
        this.mIvRatePointer.setRotation(-120.0f);
        this.mDistanceStr = String.valueOf(getString(R.string.can_range)) + " :";
        this.mSpeedStr = String.valueOf(getString(R.string.can_curspeed)) + " :";
        this.mTvDistance.setText(" ---");
        this.mTvSpeed.setText(" ---");
        this.mSp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public boolean onLongClick(View v) {
        boolean z;
        if (this.is320SpeedPan) {
            z = false;
        } else {
            z = true;
        }
        this.is320SpeedPan = z;
        UpdateData(false);
        this.mSp.edit().putBoolean(SPEED_PAN, this.is320SpeedPan).apply();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.is320SpeedPan = this.mSp.getBoolean(SPEED_PAN, false);
        UpdateData(false);
        this.mHandler = new WeakHandler(this);
        this.mHandler.sendEmptyMessageDelayed(0, 30);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: private */
    public void UpdateData(boolean check) {
        String disUnit;
        int distance;
        String unit;
        int speed;
        CanJni.GetCarInfo(this.mCanMsg);
        if (this.mCanMsg.Rpm <= 8000) {
            UpdateRate(this.mCanMsg.Rpm);
        }
        if (this.mCanMsg.DriveSafe == 1) {
            this.mIvSafeSeat.setImageResource(R.drawable.driving_anquandai_dn);
        } else {
            this.mIvSafeSeat.setImageResource(R.drawable.driving_anquandai_up);
        }
        if (this.mCanMsg.HandBrake == 1) {
            this.mIvHandBreak.setImageResource(R.drawable.driving_shousha_dn);
        } else {
            this.mIvHandBreak.setImageResource(R.drawable.driving_shousha_up);
        }
        if (CanBencWithCDCarFuncActivity.BencZmytSpeedDw() > 0) {
            disUnit = getResources().getString(R.string.dis_mile_unit);
            distance = (int) (((float) this.mCanMsg.Distance) * 0.62137f);
        } else {
            disUnit = getResources().getString(R.string.dis_km_unit);
            distance = this.mCanMsg.Distance;
        }
        this.mTvDistance.setText(String.format("%d", new Object[]{Integer.valueOf(distance)}));
        this.mTvDistanceUnit.setText(disUnit);
        if (this.mCanMsg.Speed == 255) {
            this.mTvSpeed.setText("  ---");
            return;
        }
        if (CanBencWithCDCarFuncActivity.BencZmytSpeedDw() > 0) {
            unit = getResources().getString(R.string.mph_unit);
            speed = (int) (((float) this.mCanMsg.Speed) * 0.62137f);
        } else {
            unit = getResources().getString(R.string.km_unit);
            speed = this.mCanMsg.Speed;
        }
        this.mTvSpeed.setText(String.format("%d", new Object[]{Integer.valueOf(speed)}));
        this.mTvSpeedUnit.setText(unit);
        UpdateSpeed(speed);
    }

    private void UpdateSpeed(int speed) {
        float startRotation;
        float endRotation;
        if (this.isLastSpeedPan != this.is320SpeedPan) {
            if (this.is320SpeedPan) {
                this.mIvSpeedPointer.setRotation(-129.0f);
                this.mIvSpeedPan.setImageResource(R.drawable.driving_pan02_new);
            } else {
                this.mIvSpeedPointer.setRotation(-130.0f);
                this.mIvSpeedPan.setImageResource(R.drawable.driving_pan01_new);
            }
        }
        if (this.mLastSpeed != speed || this.isLastSpeedPan != this.is320SpeedPan) {
            if (this.mSpeedAnimator != null) {
                this.mSpeedAnimator.end();
            }
            if (this.is320SpeedPan) {
                float degree = (speed > 160 ? 128.0f : 129.0f) / 160.0f;
                startRotation = -129.0f + (((float) this.mLastSpeed) * degree);
                endRotation = -129.0f + (((float) speed) * degree);
            } else {
                startRotation = -130.0f + (((float) this.mLastSpeed) * 1.0f);
                endRotation = -130.0f + (((float) speed) * 1.0f);
            }
            this.mSpeedAnimator = ObjectAnimator.ofFloat(this.mIvSpeedPointer, "rotation", new float[]{startRotation, endRotation});
            this.mSpeedAnimator.setInterpolator(new LinearInterpolator());
            this.mSpeedAnimator.setDuration(200);
            this.mSpeedAnimator.start();
            this.mLastSpeed = speed;
            this.isLastSpeedPan = this.is320SpeedPan;
        }
    }

    private void UpdateRate(int rate) {
        if (this.mLastRate != rate) {
            if (this.mRateAnimator != null) {
                this.mRateAnimator.end();
            }
            this.mRateAnimator = ObjectAnimator.ofFloat(this.mIvRatePointer, "rotation", new float[]{-120.0f + (30.0f * (((float) this.mLastRate) / 1000.0f)), -120.0f + (30.0f * (((float) rate) / 1000.0f))});
            this.mRateAnimator.setInterpolator(new LinearInterpolator());
            this.mRateAnimator.setDuration(200);
            this.mRateAnimator.start();
            this.mLastRate = rate;
        }
    }

    private static class WeakHandler extends Handler {
        private WeakReference<DrivingComputeActivity> mReference;

        public WeakHandler(DrivingComputeActivity activity) {
            this.mReference = new WeakReference<>(activity);
        }

        public void handleMessage(Message msg) {
            DrivingComputeActivity activity = (DrivingComputeActivity) this.mReference.get();
            if (activity != null) {
                activity.UpdateData(true);
                sendEmptyMessageDelayed(0, 30);
            }
        }
    }
}
