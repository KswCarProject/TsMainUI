package com.ts.can.bmw.lz.bmw2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanItemPopupList;
import com.ts.main.common.MainSet;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.Mcu;

public class CanBMW2LzCarcomputerView extends CanRelativeCarInfoView implements CanItemPopupList.onPopItemClick {
    public static final String TAG = "CanBMW2LzCarcomputerView";
    private CanDataInfo.CAN_Msg mCanMsg;
    private int[] mDayNumIds;
    private int mIll = -1;
    private ImageView mIvDrivingIcon;
    private ImageView mIvFootBreak;
    private ImageView mIvHandBreak;
    private ImageView mIvRateCircle;
    private ImageView mIvRatePointer;
    private ImageView mIvSafeSeat;
    private ImageView mIvSpeedCircle;
    private ImageView mIvSpeedPointer;
    private ImageView mIvSpeedUnit;
    /* access modifiers changed from: private */
    public ImageView mIvSpeedValue1;
    /* access modifiers changed from: private */
    public ImageView mIvSpeedValue2;
    /* access modifiers changed from: private */
    public ImageView mIvSpeedValue3;
    private int mLastRate = -1;
    private int mLastSpeed = -1;
    private int[] mNightNumIds;
    private ObjectAnimator mRateAnimator;
    private RelativeLayoutManager mRelativeManager;
    private ObjectAnimator mSpeedAnimator;
    private int[] mStallIds;

    public CanBMW2LzCarcomputerView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        getActivity().setContentView(R.layout.activity_bmw_driving_compute);
        this.mRelativeManager = new RelativeLayoutManager(getActivity(), R.id.can_drive_layout);
        if (isScreenType1280()) {
            InitBaseUI();
            return;
        }
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mRelativeManager.GetLayout().getLayoutParams();
        lp.width = 1280;
        lp.height = 480;
        lp.gravity = 17;
        this.mRelativeManager.GetLayout().setLayoutParams(lp);
        InitBaseUI();
        this.mRelativeManager.GetLayout().setScaleX(0.8f);
        this.mRelativeManager.GetLayout().setScaleY(1.25f);
    }

    private boolean isScreenType1280() {
        return MainSet.GetScreenType() == 5;
    }

    private void InitBaseUI() {
        this.mCanMsg = new CanDataInfo.CAN_Msg();
        this.mDayNumIds = new int[]{R.drawable.can_driving_numb_00, R.drawable.can_driving_numb_01, R.drawable.can_driving_numb_02, R.drawable.can_driving_numb_03, R.drawable.can_driving_numb_04, R.drawable.can_driving_numb_05, R.drawable.can_driving_numb_06, R.drawable.can_driving_numb_07, R.drawable.can_driving_numb_08, R.drawable.can_driving_numb_09};
        this.mStallIds = new int[]{R.drawable.can_driving_p, R.drawable.can_driving_r, R.drawable.can_driving_n, R.drawable.can_driving_n, R.drawable.can_driving_d, R.drawable.can_driving_s, R.drawable.can_driving_m};
        this.mNightNumIds = new int[]{R.drawable.can_driving_numb2_00, R.drawable.can_driving_numb2_01, R.drawable.can_driving_numb2_02, R.drawable.can_driving_numb2_03, R.drawable.can_driving_numb2_04, R.drawable.can_driving_numb2_05, R.drawable.can_driving_numb2_06, R.drawable.can_driving_numb2_07, R.drawable.can_driving_numb2_08, R.drawable.can_driving_numb2_09};
        this.mIvDrivingIcon = (ImageView) getActivity().findViewById(R.id.iv_driving_icon);
        this.mIvSpeedCircle = (ImageView) getActivity().findViewById(R.id.iv_speed_circle);
        this.mIvSpeedPointer = (ImageView) getActivity().findViewById(R.id.iv_speed_pointer);
        this.mIvRateCircle = (ImageView) getActivity().findViewById(R.id.iv_rate_circle);
        this.mIvRatePointer = (ImageView) getActivity().findViewById(R.id.iv_rate_pointer);
        this.mIvSpeedValue1 = (ImageView) getActivity().findViewById(R.id.iv_speed_value1);
        this.mIvSpeedValue2 = (ImageView) getActivity().findViewById(R.id.iv_speed_value2);
        this.mIvSpeedValue3 = (ImageView) getActivity().findViewById(R.id.iv_speed_value3);
        this.mIvSpeedUnit = (ImageView) getActivity().findViewById(R.id.iv_speed_unit);
        this.mIvSafeSeat = (ImageView) getActivity().findViewById(R.id.iv_safe_seat);
        this.mIvFootBreak = (ImageView) getActivity().findViewById(R.id.iv_foot_break);
        this.mIvHandBreak = (ImageView) getActivity().findViewById(R.id.iv_hand_break);
        getActivity().findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                CanBMW2LzCarcomputerView.this.getActivity().finish();
            }
        });
        this.mIvSpeedValue1.setTag(0);
        this.mIvSpeedValue2.setTag(0);
        this.mIvSpeedValue3.setTag(0);
        this.mIvSpeedPointer.setRotation(-130.0f);
        this.mIvRatePointer.setRotation(-144.0f);
        this.mIvSpeedValue1.setVisibility(4);
        this.mIvSpeedValue2.setVisibility(4);
        this.mIvSpeedValue3.setVisibility(4);
        this.mIvFootBreak.setVisibility(4);
        UpdateSpeed(0);
    }

    /* access modifiers changed from: private */
    public void UpdateMode(boolean isDay) {
        int[] numIds;
        if (isDay) {
            this.mIvDrivingIcon.setImageResource(R.drawable.can_driving_di1);
            this.mIvSpeedCircle.setImageResource(R.drawable.can_driving_sudu_pan_01);
            this.mIvRateCircle.setImageResource(R.drawable.can_driving_zhuansu_pan_01);
            this.mIvSpeedUnit.setImageResource(R.drawable.can_driving_kmh_01);
            numIds = this.mDayNumIds;
        } else {
            this.mIvDrivingIcon.setImageResource(R.drawable.can_driving_di2);
            this.mIvSpeedCircle.setImageResource(R.drawable.can_driving_sudu_pan_02);
            this.mIvRateCircle.setImageResource(R.drawable.can_driving_zhuansu_pan_02);
            this.mIvSpeedUnit.setImageResource(R.drawable.can_driving_kmh_02);
            numIds = this.mNightNumIds;
        }
        int index = ((Integer) this.mIvSpeedValue1.getTag()).intValue();
        if (index >= 0 && index < numIds.length) {
            this.mIvSpeedValue1.setImageResource(numIds[index]);
        }
        int index2 = ((Integer) this.mIvSpeedValue2.getTag()).intValue();
        if (index2 >= 0 && index2 < numIds.length) {
            this.mIvSpeedValue2.setImageResource(numIds[index2]);
        }
        int index3 = ((Integer) this.mIvSpeedValue3.getTag()).intValue();
        if (index3 >= 0 && index3 < numIds.length) {
            this.mIvSpeedValue3.setImageResource(numIds[index3]);
        }
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
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    private void UpdateSpeed(final int speed) {
        if (this.mLastSpeed != speed) {
            if (this.mSpeedAnimator != null) {
                this.mSpeedAnimator.end();
            }
            this.mSpeedAnimator = ObjectAnimator.ofFloat(this.mIvSpeedPointer, "rotation", new float[]{-130.0f + ((float) this.mLastSpeed), -130.0f + ((float) speed)});
            this.mSpeedAnimator.setInterpolator(new LinearInterpolator());
            this.mSpeedAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    int i;
                    int i2 = 4;
                    boolean z = false;
                    int value1 = speed / 100;
                    int value2 = (speed % 100) / 10;
                    int value3 = speed % 10;
                    CanBMW2LzCarcomputerView.this.mIvSpeedValue1.setTag(Integer.valueOf(value1));
                    CanBMW2LzCarcomputerView.this.mIvSpeedValue2.setTag(Integer.valueOf(value2));
                    CanBMW2LzCarcomputerView.this.mIvSpeedValue3.setTag(Integer.valueOf(value3));
                    ImageView access$1 = CanBMW2LzCarcomputerView.this.mIvSpeedValue1;
                    if (value1 == 0) {
                        i = 4;
                    } else {
                        i = 0;
                    }
                    access$1.setVisibility(i);
                    ImageView access$2 = CanBMW2LzCarcomputerView.this.mIvSpeedValue2;
                    if (!(value1 == 0 && value2 == 0)) {
                        i2 = 0;
                    }
                    access$2.setVisibility(i2);
                    CanBMW2LzCarcomputerView.this.mIvSpeedValue3.setVisibility(0);
                    RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) CanBMW2LzCarcomputerView.this.mIvSpeedValue2.getLayoutParams();
                    RelativeLayout.LayoutParams lp3 = (RelativeLayout.LayoutParams) CanBMW2LzCarcomputerView.this.mIvSpeedValue3.getLayoutParams();
                    if (speed < 10) {
                        lp3.leftMargin = 667;
                    } else if (speed < 100) {
                        lp2.leftMargin = CanCameraUI.BTN_LANDWIND_2D3D;
                        lp3.leftMargin = CanCameraUI.BTN_CC_WC_DIRECTION1;
                    } else {
                        lp2.leftMargin = 667;
                        lp3.leftMargin = 728;
                    }
                    CanBMW2LzCarcomputerView.this.mIvSpeedValue2.setLayoutParams(lp2);
                    CanBMW2LzCarcomputerView.this.mIvSpeedValue3.setLayoutParams(lp3);
                    CanBMW2LzCarcomputerView canBMW2LzCarcomputerView = CanBMW2LzCarcomputerView.this;
                    if (Mcu.GetIll() != 1) {
                        z = true;
                    }
                    canBMW2LzCarcomputerView.UpdateMode(z);
                }
            });
            this.mSpeedAnimator.setDuration(200);
            this.mSpeedAnimator.start();
            this.mLastSpeed = speed;
        }
    }

    private void UpdateRate(int rate) {
        if (this.mLastRate != rate) {
            if (this.mRateAnimator != null) {
                this.mRateAnimator.end();
            }
            this.mRateAnimator = ObjectAnimator.ofFloat(this.mIvRatePointer, "rotation", new float[]{-144.0f + (36.0f * (((float) this.mLastRate) / 1000.0f)), -144.0f + (36.0f * (((float) rate) / 1000.0f))});
            this.mRateAnimator.setInterpolator(new LinearInterpolator());
            this.mRateAnimator.setDuration(200);
            this.mRateAnimator.start();
            this.mLastRate = rate;
        }
    }

    public void ResetData(boolean check) {
        boolean z = false;
        CanJni.GetCarInfo(this.mCanMsg);
        if (this.mCanMsg.UpdateOnce == 1 && (!check || this.mCanMsg.Update == 1)) {
            this.mCanMsg.Update = 0;
            Log.d("CanMsg", "Speed ==== " + this.mCanMsg.Speed);
            UpdateSpeed(this.mCanMsg.Speed);
            UpdateRate(this.mCanMsg.Rpm);
            UpdateMode(Mcu.GetIll() != 1);
            if (this.mCanMsg.DriveSafe == 1) {
                this.mIvSafeSeat.setImageResource(R.drawable.can_driving_anquandai_up);
            } else {
                this.mIvSafeSeat.setImageResource(R.drawable.can_driving_anquandai_dn);
            }
            if (this.mCanMsg.HandBrake == 1) {
                this.mIvHandBreak.setImageResource(R.drawable.can_driving_shousha_dn);
            } else {
                this.mIvHandBreak.setImageResource(R.drawable.can_driving_shousha_up);
            }
            if (this.mCanMsg.Stalls >= 0 && this.mCanMsg.Stalls < this.mStallIds.length) {
                this.mIvFootBreak.setImageResource(this.mStallIds[this.mCanMsg.Stalls]);
            }
        }
        if (this.mIll != Mcu.GetIll()) {
            this.mIll = Mcu.GetIll();
            if (Mcu.GetIll() != 1) {
                z = true;
            }
            UpdateMode(z);
        }
    }

    public void QueryData() {
        CanJni.Bmw2LzQuery(18);
        Sleep(5);
        CanJni.Bmw2LzQuery(24);
    }

    public void onItem(int id, int item) {
    }
}
