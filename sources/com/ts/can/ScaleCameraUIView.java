package com.ts.can;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;

public class ScaleCameraUIView extends RelativeLayout implements View.OnClickListener {
    private boolean isAdjustable;
    private Button mBottomArrow;
    private Button mLeftArrow;
    private ImageView mLeftLine;
    private SharedPreferences mPreference;
    private Button mRightArrow;
    private ImageView mRightLine;
    private Button mScaleDown;
    private Button mScaleUp;
    private Button mTopArrow;
    private int translateValue;

    public ScaleCameraUIView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ScaleCameraUIView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.translateValue = 20;
        initViews();
    }

    private void initViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.scale_camera_ui, this, true);
        this.mLeftLine = (ImageView) findViewById(R.id.left_line);
        this.mRightLine = (ImageView) findViewById(R.id.right_line);
        this.mLeftArrow = (Button) findViewById(R.id.left_arrow);
        this.mRightArrow = (Button) findViewById(R.id.right_arrow);
        this.mTopArrow = (Button) findViewById(R.id.top_arrow);
        this.mBottomArrow = (Button) findViewById(R.id.bottom_arrow);
        this.mScaleDown = (Button) findViewById(R.id.scale_down);
        this.mScaleUp = (Button) findViewById(R.id.scale_up);
        this.mLeftArrow.setOnClickListener(this);
        this.mRightArrow.setOnClickListener(this);
        this.mTopArrow.setOnClickListener(this);
        this.mBottomArrow.setOnClickListener(this);
        this.mScaleDown.setOnClickListener(this);
        this.mScaleUp.setOnClickListener(this);
        this.mPreference = getContext().getSharedPreferences("back_assistant_line", 0);
    }

    private void setAdjustable(boolean isAdjustable2) {
        if (isAdjustable2) {
            this.mLeftArrow.setVisibility(0);
            this.mRightArrow.setVisibility(0);
            this.mTopArrow.setVisibility(0);
            this.mBottomArrow.setVisibility(0);
            this.mScaleDown.setVisibility(0);
            this.mScaleUp.setVisibility(0);
            return;
        }
        this.mLeftArrow.setVisibility(8);
        this.mRightArrow.setVisibility(8);
        this.mTopArrow.setVisibility(8);
        this.mBottomArrow.setVisibility(8);
        this.mScaleDown.setVisibility(8);
        this.mScaleUp.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAdjustable = this.mPreference.getBoolean("back_line_adjustable", false);
        setAdjustable(this.isAdjustable);
        Log.d("HOOCCHI", "onAttachedToWindow isAdjustable =" + this.isAdjustable);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mLeftLine.getLayoutParams();
        lp.leftMargin = this.mPreference.getInt("leftX", 61);
        lp.topMargin = this.mPreference.getInt("leftY", 145);
        this.mLeftLine.setLayoutParams(lp);
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) this.mRightLine.getLayoutParams();
        lp2.rightMargin = this.mPreference.getInt("rightX", 61);
        lp2.topMargin = this.mPreference.getInt("rightY", 145);
        this.mRightLine.setLayoutParams(lp2);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d("HOOCCHI", "onDetachedFromWindow isAdjustable = " + this.isAdjustable);
        if (this.isAdjustable) {
            SharedPreferences.Editor editor = this.mPreference.edit();
            editor.putBoolean("back_line_adjustable", false);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mLeftLine.getLayoutParams();
            editor.putInt("newLeftX", lp.leftMargin);
            editor.putInt("newLeftY", lp.topMargin);
            RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) this.mRightLine.getLayoutParams();
            editor.putInt("newRightX", lp2.rightMargin);
            editor.putInt("newRightY", lp2.topMargin);
            editor.commit();
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.left_arrow) {
            if (isCanLeftTranslate()) {
                translateLine(-this.translateValue, 0, this.translateValue, 0);
            }
        } else if (id == R.id.right_arrow) {
            if (isCanRightTranslate()) {
                translateLine(this.translateValue, 0, -this.translateValue, 0);
            }
        } else if (id == R.id.top_arrow) {
            translateLine(0, -this.translateValue, 0, -this.translateValue);
        } else if (id == R.id.bottom_arrow) {
            translateLine(0, this.translateValue, 0, this.translateValue);
        } else if (id == R.id.scale_down) {
            if (isCanScaleDown()) {
                translateLine(this.translateValue, 0, this.translateValue, 0);
            }
        } else if (id == R.id.scale_up && isCanLeftTranslate() && isCanRightTranslate()) {
            translateLine(-this.translateValue, 0, -this.translateValue, 0);
        }
    }

    private void translateLine(int leftDx, int leftDy, int rightDx, int rightDy) {
        RelativeLayout.LayoutParams leftParams = (RelativeLayout.LayoutParams) this.mLeftLine.getLayoutParams();
        RelativeLayout.LayoutParams rightParams = (RelativeLayout.LayoutParams) this.mRightLine.getLayoutParams();
        leftParams.leftMargin += leftDx;
        leftParams.topMargin += leftDy;
        rightParams.rightMargin += rightDx;
        rightParams.topMargin += rightDy;
        leftParams.topMargin = checkValue(leftParams.topMargin, 0, Can.CAN_FLAT_RZC);
        rightParams.topMargin = checkValue(rightParams.topMargin, 0, Can.CAN_FLAT_RZC);
        if (leftParams.leftMargin <= -130 && rightParams.rightMargin <= -130) {
            leftParams.leftMargin = -130;
            rightParams.rightMargin = -130;
        } else if (leftParams.leftMargin <= -130) {
            rightParams.rightMargin += leftParams.leftMargin + 130;
            leftParams.leftMargin = -130;
        } else if (rightParams.rightMargin <= -130) {
            leftParams.leftMargin += rightParams.rightMargin + 130;
            rightParams.rightMargin = -130;
        }
        int width = leftParams.width + leftParams.leftMargin + rightParams.rightMargin + rightParams.width;
        if (width > 1024) {
            int dx = (width - 1024) / 2;
            leftParams.leftMargin -= dx;
            rightParams.rightMargin -= dx;
        }
        this.mLeftLine.setLayoutParams(leftParams);
        this.mRightLine.setLayoutParams(rightParams);
    }

    private int checkValue(int value, int min, int max) {
        if (value <= min) {
            value = min;
        }
        if (value >= max) {
            return max;
        }
        return value;
    }

    private boolean isCanLeftTranslate() {
        return ((RelativeLayout.LayoutParams) this.mLeftLine.getLayoutParams()).leftMargin > -130;
    }

    private boolean isCanRightTranslate() {
        return ((RelativeLayout.LayoutParams) this.mRightLine.getLayoutParams()).rightMargin > -130;
    }

    private boolean isCanScaleDown() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mLeftLine.getLayoutParams();
        int leftMargin = lp.leftMargin;
        int leftWidth = lp.width;
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) this.mRightLine.getLayoutParams();
        return leftMargin < ((1024 - lp2.rightMargin) - lp2.width) - leftWidth;
    }
}
