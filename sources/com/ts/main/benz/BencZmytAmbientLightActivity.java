package com.ts.main.benz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.can.benc.withcd.CanBencWithCDCarFuncActivity;
import com.ts.main.benz.ColorView;
import com.ts.main.benz.LightView;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class BencZmytAmbientLightActivity extends Activity implements View.OnClickListener, View.OnTouchListener {
    private static final String AMBIENT_LIGHT = "ambient_light";
    private static final String AMBIENT_RGB_DEGREE = "ambient_rgb_degree";
    private static final int BTN_LIGHT = 100;
    private static final int[] mCarDnIds = {R.drawable.can_car_benchi_dn, R.drawable.can_car_benchia_dn, R.drawable.can_car_benchie_dn, R.drawable.can_car_benchigla_dn, R.drawable.can_car_clc_dn};
    private static final int[] mCarUpIds = {R.drawable.can_car_benchi_up, R.drawable.can_car_benchia_up, R.drawable.can_car_benchie_up, R.drawable.can_car_benchigla_up, R.drawable.can_car_clc_up};
    private ParamButton[] mBtnLights = new ParamButton[4];
    private ColorView mColorView;
    private float mDownX;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            BencZmytAmbientLightActivity.this.ResetData(true);
        }
    };
    private int mIndex = 0;
    private CustomImgView mIvCar;
    /* access modifiers changed from: private */
    public ImageView mIvRgbPoint;
    private ImageView mIvToggleBg;
    /* access modifiers changed from: private */
    public ImageView mIvTogglePoint;
    private int mLastBright;
    private int mLastMode;
    private int mLastState;
    private LightView mLightView;
    private RelativeLayoutManager mManager;
    /* access modifiers changed from: private */
    public SharedPreferences mSp;
    /* access modifiers changed from: private */
    public TextView mTvToggleState;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_light_layout);
        this.mManager = new RelativeLayoutManager(this, R.id.root);
        this.mIvCar = this.mManager.AddImage(221, 93);
        this.mIvCar.setOnTouchListener(this);
        this.mIvRgbPoint = (ImageView) findViewById(R.id.iv_rgb_point);
        this.mColorView = (ColorView) findViewById(R.id.color_view);
        this.mColorView.setOnColorChangedListener(new ColorView.OnColorChangedListener() {
            public void onColorChanged(int color, float degree) {
                BencZmytAmbientLightActivity.this.mIvRgbPoint.setRotation(degree + 90.0f);
                CanBencWithCDCarFuncActivity.AmbientLightSet(3, Color.red(color), Color.green(color), Color.blue(color));
                BencZmytAmbientLightActivity.this.mSp.edit().putFloat(BencZmytAmbientLightActivity.AMBIENT_RGB_DEGREE, degree + 90.0f).apply();
            }
        });
        this.mLightView = (LightView) findViewById(R.id.light_view);
        this.mLightView.setOnLightChangedListener(new LightView.OnLightChangedListener() {
            public void onLightChanged(int progress) {
                CanBencWithCDCarFuncActivity.AmbientLightSet(2, progress, 0, 0);
            }
        });
        this.mIvTogglePoint = (ImageView) findViewById(R.id.iv_toggle_point);
        this.mIvToggleBg = (ImageView) findViewById(R.id.iv_toggle_bg);
        this.mIvToggleBg.setOnClickListener(this);
        this.mTvToggleState = (TextView) findViewById(R.id.tv_toggle_state);
        int[] strIds = {R.string.bright_jianbian, R.string.bright_changliang, R.string.bright_huxi, R.string.bright_baoshan};
        for (int i = 0; i < this.mBtnLights.length; i++) {
            this.mBtnLights[i] = addButton(766, (i * 79) + 148, R.drawable.can_btn_up, R.drawable.can_btn_dn, i + 100, strIds[i]);
        }
        this.mSp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mIndex = this.mSp.getInt(AMBIENT_LIGHT, 0);
        this.mIvCar.setStateDrawable(mCarUpIds[this.mIndex], mCarDnIds[this.mIndex]);
        this.mIvRgbPoint.setRotation(this.mSp.getFloat(AMBIENT_RGB_DEGREE, 0.0f));
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    /* access modifiers changed from: private */
    public void ResetData(boolean check) {
        boolean z;
        boolean z2;
        int state = CanBencWithCDCarFuncActivity.AmbientLightSta();
        if (this.mLastState != state || !check) {
            this.mIvCar.SetSel(state);
            if (state != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            updateTogglePoint(z2);
            this.mLastState = state;
        }
        int bright = CanBencWithCDCarFuncActivity.AmbientLightBri();
        if (this.mLastBright != bright || !check) {
            this.mLightView.setLight(bright);
            this.mLastBright = bright;
        }
        int mode = CanBencWithCDCarFuncActivity.AmbientLightMode();
        if (this.mLastMode != mode || !check) {
            for (int i = 0; i < this.mBtnLights.length; i++) {
                ParamButton paramButton = this.mBtnLights[i];
                if (mode == i) {
                    z = true;
                } else {
                    z = false;
                }
                paramButton.setSelected(z);
            }
            this.mLastMode = mode;
        }
        this.mHandler.sendEmptyMessageDelayed(0, 500);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.iv_toggle_bg) {
            CanBencWithCDCarFuncActivity.AmbientLightSet(0, CanBencWithCDCarFuncActivity.AmbientLightSta(), 0, 0);
            return;
        }
        int id = ((Integer) v.getTag()).intValue();
        if (id >= 100 && id < this.mBtnLights.length + 100) {
            CanBencWithCDCarFuncActivity.AmbientLightSet(1, id - 100, 0, 0);
        }
    }

    public boolean onTouch(View v, MotionEvent e) {
        int action = e.getAction();
        if (action == 0) {
            this.mDownX = e.getX();
        } else if (action == 1 && Math.abs(e.getX() - this.mDownX) > 100.0f) {
            if (e.getX() > this.mDownX) {
                this.mIndex = this.mIndex + -1 < 0 ? mCarUpIds.length - 1 : this.mIndex - 1;
            } else {
                this.mIndex = this.mIndex + 1 > mCarUpIds.length + -1 ? 0 : this.mIndex + 1;
            }
            this.mIvCar.setStateDrawable(mCarUpIds[this.mIndex], mCarDnIds[this.mIndex]);
            this.mSp.edit().putInt(AMBIENT_LIGHT, this.mIndex).apply();
        }
        return true;
    }

    private void updateTogglePoint(final boolean isON) {
        int i;
        int i2 = 94;
        if (isON) {
            i = 140;
        } else {
            i = 94;
        }
        float start = (float) i;
        if (!isON) {
            i2 = 140;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{start, (float) i2});
        animator.setDuration(100);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator anim) {
                float value = ((Float) anim.getAnimatedValue()).floatValue();
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) BencZmytAmbientLightActivity.this.mIvTogglePoint.getLayoutParams();
                lp.leftMargin = (int) value;
                BencZmytAmbientLightActivity.this.mIvTogglePoint.setLayoutParams(lp);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) BencZmytAmbientLightActivity.this.mTvToggleState.getLayoutParams();
                lp.leftMargin = isON ? Can.CAN_BENC_ZMYT : 110;
                BencZmytAmbientLightActivity.this.mTvToggleState.setLayoutParams(lp);
                BencZmytAmbientLightActivity.this.mTvToggleState.setText(isON ? "ON" : "OFF");
            }
        });
        animator.start();
    }

    private ParamButton addButton(int x, int y, int up, int dn, int id, int text) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setStateDrawable(up, dn, dn);
        btn.setPadding(0, 10, 0, 0);
        btn.setGravity(17);
        btn.setTextColor(-1);
        btn.setText(text);
        btn.setTextSize(0, 22.0f);
        btn.setTag(Integer.valueOf(id));
        btn.setAllCaps(false);
        btn.setOnClickListener(this);
        return btn;
    }
}
