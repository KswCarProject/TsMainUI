package com.ts.can.baic.od.senova;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.canview.RelativeLayoutContainer;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanSenovaOdACView extends CanBaseACView {
    protected static final int AC_TOGGLE = 18;
    private static final int ID = -1;
    protected static final int LT_TEMP_DECREASE = 1;
    protected static final int LT_TEMP_INCREASE = 0;
    protected static final int MODE = 19;
    protected static final int MODE_FOOT = 8;
    protected static final int MODE_FOOT_FRONT = 9;
    protected static final int MODE_HEAD = 6;
    protected static final int MODE_HEAD_FOOT = 7;
    protected static final int RT_TEMP_DECREASE = 3;
    protected static final int RT_TEMP_INCREASE = 2;
    protected static final int STATUS_AC = 10;
    protected static final int STATUS_AC_MAX = 17;
    protected static final int STATUS_AUTO = 13;
    protected static final int STATUS_DUAL = 14;
    protected static final int STATUS_FRONT = 15;
    protected static final int STATUS_LOOPER_INNER = 11;
    protected static final int STATUS_LOOPER_OUTTER = 12;
    protected static final int STATUS_REAR = 16;
    protected static final int WIND_DECREASE = 5;
    protected static final int WIND_INCREASE = 4;
    protected static int[] mModeArrays = {R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn};
    protected static int[] mWindArrays = {R.drawable.can_rh7_signal01_dn, R.drawable.can_rh7_signal02_dn, R.drawable.can_rh7_signal03_dn, R.drawable.can_rh7_signal04_dn, R.drawable.can_rh7_signal05_dn, R.drawable.can_rh7_signal06_dn, R.drawable.can_rh7_signal07_dn};
    protected boolean isForeWindMode;
    protected CanDataInfo.CAN_ACInfo mAcInfo;
    protected ParamButton mBtnAc;
    protected ParamButton mBtnAcMax;
    protected ParamButton mBtnAcToggle;
    protected ParamButton mBtnAuto;
    protected ParamButton mBtnDual;
    protected ParamButton mBtnFront;
    protected ParamButton mBtnLooperInner;
    protected ParamButton mBtnLooperOutter;
    protected ParamButton mBtnMode;
    protected ParamButton[] mBtnModeArrays;
    protected ParamButton mBtnRear;
    protected RelativeLayoutContainer mContainer;
    protected ImageView[] mIvWinds;
    protected ParamButton mLeftTempDecrease;
    protected ParamButton mLeftTempIncrease;
    protected ParamButton mRightTempDecrease;
    protected ParamButton mRightTempIncrease;
    protected TextView mTvLeftTemp;
    protected TextView mTvRightTemp;
    protected ParamButton mWindDecrease;
    protected ParamButton mWindIncrease;

    public CanSenovaOdACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int cmd = -1;
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                cmd = 13;
                break;
            case 1:
                cmd = 14;
                break;
            case 4:
                cmd = 11;
                break;
            case 5:
                cmd = 12;
                break;
            case 6:
                cmd = 7;
                break;
            case 7:
                cmd = 8;
                break;
            case 8:
                cmd = 9;
                break;
            case 9:
                cmd = 10;
                break;
            case 10:
                cmd = 1;
                break;
            case 12:
                cmd = 4;
                break;
            case 15:
                cmd = 6;
                break;
            case 16:
                cmd = 18;
                break;
            case 18:
                cmd = 0;
                break;
        }
        Log.d("HAHA", "cmd = " + cmd);
        CanJni.SenovaOdAcSet(cmd, 1);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        this.mContainer = new RelativeLayoutContainer(getActivity());
        setBackgroundResource(R.drawable.can_rh7_bg02);
        InitTempButtons(false);
        InitWindButtons(false);
        InitModeButtons(false, true);
        InitClickableStatusButtons(false);
    }

    /* access modifiers changed from: protected */
    public void InitModeButtons(boolean isTouchable, boolean isClickable) {
        this.mBtnModeArrays = new ParamButton[4];
        for (int i = 0; i < this.mBtnModeArrays.length; i++) {
            this.mBtnModeArrays[i] = this.mContainer.addButton(305, (i * 80) + 108);
            ParamButton btnMode = this.mBtnModeArrays[i];
            this.mContainer.setDrawableUpDnSel(btnMode, mModeArrays[i * 2], mModeArrays[(i * 2) + 1]);
            if (isTouchable) {
                this.mContainer.setIdTouchListener(btnMode, i + 6, this);
            } else if (isClickable) {
                this.mContainer.setIdClickListener(btnMode, i + 6, this);
            } else {
                btnMode.setClickable(false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void InitWindButtons(boolean isTouchable) {
        this.mWindIncrease = this.mContainer.addButton(188, 117);
        this.mWindDecrease = this.mContainer.addButton(188, KeyDef.RKEY_POWER);
        this.mContainer.addImage(191, Can.CAN_TEANA_OLD_DJ, R.drawable.can_rh7_6signal_up);
        int[] mWindArrays2 = {R.drawable.can_rh7_6signal01_dn, R.drawable.can_rh7_6signal02_dn, R.drawable.can_rh7_6signal03_dn, R.drawable.can_rh7_6signal04_dn, R.drawable.can_rh7_6signal05_dn, R.drawable.can_rh7_6signal06_dn};
        this.mIvWinds = new ImageView[mWindArrays2.length];
        for (int i = 0; i < this.mIvWinds.length; i++) {
            this.mIvWinds[i] = this.mContainer.addImage(191, Can.CAN_TEANA_OLD_DJ, mWindArrays2[i]);
        }
        this.mContainer.setDrawableUpDnSel(this.mWindIncrease, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn).setDrawableUpDnSel(this.mWindDecrease, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn);
        if (isTouchable) {
            this.mContainer.setIdTouchListener(this.mWindIncrease, 4, this);
            this.mContainer.setIdTouchListener(this.mWindDecrease, 5, this);
            return;
        }
        this.mContainer.setIdClickListener(this.mWindIncrease, 4, this);
        this.mContainer.setIdClickListener(this.mWindDecrease, 5, this);
    }

    /* access modifiers changed from: protected */
    public void InitTempButtons(boolean isTouchable) {
        this.mLeftTempIncrease = this.mContainer.addButton(50, 117);
        this.mLeftTempDecrease = this.mContainer.addButton(50, KeyDef.RKEY_POWER);
        this.mTvLeftTemp = this.mContainer.addText(53, Can.CAN_TEANA_OLD_DJ, 92, 61);
        this.mContainer.setDrawableUpDnSel(this.mLeftTempIncrease, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn).setDrawableUpDnSel(this.mLeftTempDecrease, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn).setTextStyle(this.mTvLeftTemp, 0, 17, Color.parseColor("#08D2D3"), 18).setIdClickListener(this.mLeftTempIncrease, 0, this).setIdClickListener(this.mLeftTempDecrease, 1, this);
    }

    /* access modifiers changed from: protected */
    public void InitClickableStatusButtons(boolean isTouchable) {
        this.mBtnAc = this.mContainer.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 130);
        this.mBtnLooperOutter = this.mContainer.addButton(KeyDef.SKEY_POWEWR_2, 130);
        this.mBtnFront = this.mContainer.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 285);
        this.mBtnRear = this.mContainer.addButton(KeyDef.SKEY_POWEWR_2, 285);
        this.mBtnAcToggle = this.mContainer.addButton(462, 426);
        this.mContainer.setDrawableUpDnSel(this.mBtnAc, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn).setDrawableUpDnSel(this.mBtnLooperOutter, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_nxh_dn).setDrawableUpDnSel(this.mBtnFront, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn).setDrawableUpDnSel(this.mBtnRear, R.drawable.can_rh7_window02_up, R.drawable.can_rh7_window02_dn).setDrawableUpDnSel(this.mBtnAcToggle, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn).setIdClickListener(this.mBtnAc, 10, this).setIdClickListener(this.mBtnLooperOutter, 12, this).setIdClickListener(this.mBtnFront, 15, this).setIdClickListener(this.mBtnRear, 16, this).setIdClickListener(this.mBtnAcToggle, 18, this);
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        boolean z = false;
        this.mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        if (this.mTvLeftTemp != null) {
            Log.d("HAHA", "mAcInfo.szLtTemp = " + this.mAcInfo.szLtTemp);
            this.mTvLeftTemp.setText(this.mAcInfo.szLtTemp);
        }
        if (this.mTvRightTemp != null) {
            Log.d("HAHA", "mAcInfo.szRtTemp = " + this.mAcInfo.szRtTemp);
            this.mTvRightTemp.setText(this.mAcInfo.szRtTemp);
        }
        if (this.mBtnLooperOutter != null) {
            if (this.mBtnLooperInner == null) {
                setViewSelected((View) this.mBtnLooperOutter, this.mAcInfo.fgInnerLoop);
            } else {
                ParamButton paramButton = this.mBtnLooperOutter;
                if (!i2b(this.mAcInfo.fgInnerLoop)) {
                    z = true;
                }
                setViewSelected((View) paramButton, z);
                setViewSelected((View) this.mBtnLooperInner, this.mAcInfo.fgInnerLoop);
            }
        }
        if (this.mBtnAc != null) {
            setViewSelected((View) this.mBtnAc, this.mAcInfo.fgAC);
        }
        if (this.mBtnDual != null) {
            setViewSelected((View) this.mBtnDual, this.mAcInfo.fgDual);
        }
        if (this.mBtnAcMax != null) {
            setViewSelected((View) this.mBtnAcMax, this.mAcInfo.fgACMax);
        }
        if (this.mBtnRear != null) {
            setViewSelected((View) this.mBtnRear, this.mAcInfo.fgRearLight);
        }
        if (this.mBtnAcToggle != null) {
            setViewSelected((View) this.mBtnAcToggle, this.mAcInfo.PWR);
        }
        if (this.mBtnAuto != null) {
            setViewSelected((View) this.mBtnAuto, this.mAcInfo.nAutoLight);
        }
        if (this.mIvWinds != null) {
            setWindValue(this.mAcInfo.nWindValue);
        }
        if (this.mBtnModeArrays != null) {
            setWindMode(this.mAcInfo.fgParallelWind, this.mAcInfo.fgDownWind, this.mAcInfo.fgForeWindMode, this.mAcInfo.fgDFBL);
        }
    }

    public void showGoneView(View view, boolean show) {
        view.setVisibility(show ? 0 : 8);
    }

    public void setViewSelected(View view, int selected) {
        view.setSelected(i2b(selected));
    }

    public void setViewSelected(View view, boolean selected) {
        view.setSelected(selected);
    }

    /* access modifiers changed from: protected */
    public void setWindMode(int fgParallel, int fgDown, int fgForeWind, int fgDFBL) {
        for (ParamButton btnMode : this.mBtnModeArrays) {
            setViewSelected((View) btnMode, false);
        }
        if (this.isForeWindMode) {
            setViewSelected((View) this.mBtnFront, false);
        }
        if (i2b(fgParallel) && !i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeArrays[0], true);
        } else if (i2b(fgParallel) && i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeArrays[1], true);
        } else if (!i2b(fgParallel) && i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeArrays[2], true);
        } else if (!i2b(fgParallel) && i2b(fgDown) && i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeArrays[3], true);
        } else if (!i2b(fgParallel) && !i2b(fgDown) && i2b(fgForeWind) && this.isForeWindMode) {
            setViewSelected((View) this.mBtnFront, true);
        }
        if (!this.isForeWindMode && this.mBtnFront != null) {
            setViewSelected((View) this.mBtnFront, fgDFBL);
        }
    }

    /* access modifiers changed from: protected */
    public void setWindValue(int wind) {
        for (ImageView windIcon : this.mIvWinds) {
            showGoneView(windIcon, false);
        }
        for (int i = 0; i < wind; i++) {
            if (i < this.mIvWinds.length) {
                showGoneView(this.mIvWinds[i], true);
            }
        }
    }
}
