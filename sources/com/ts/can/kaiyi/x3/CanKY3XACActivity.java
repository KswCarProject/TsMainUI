package com.ts.can.kaiyi.x3;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCommonActivity;
import com.ts.can.CanFunc;
import com.ts.canview.RelativeLayoutContainer;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanKY3XACActivity extends CanCommonActivity implements View.OnTouchListener {
    private static long AC_SHOW_TIME = 3000;
    private static final int ITEM_AC = 0;
    private static final int ITEM_AC_TOGGLE = 4;
    private static final int ITEM_FRONT_HEAT = 2;
    private static final int ITEM_LOOPER = 1;
    private static final int ITEM_MODE_FOOT = 7;
    private static final int ITEM_MODE_FOOT_FRONT = 8;
    private static final int ITEM_MODE_HEAD = 5;
    private static final int ITEM_MODE_HEAD_FOOT = 6;
    private static final int ITEM_REAR_HEAT = 3;
    private static final int ITEM_TEMP_VALUE = 9;
    private static final int ITEM_WIND_VALUE = 10;
    private boolean isACJump = false;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAcToggle;
    private ParamButton mBtnFrontHeat;
    private ParamButton mBtnLooper;
    private ParamButton mBtnModeFoot;
    private ParamButton mBtnModeFootFront;
    private ParamButton mBtnModeHead;
    private ParamButton mBtnModeHeadFoot;
    private ParamButton mBtnRearHeat;
    private RelativeLayoutContainer mContainer;
    private ImageView mIvWindCc;
    private ImageView mIvWindLc;
    private ImageView mIvWindLs;
    private ImageView mIvWindLx;
    private ImageView mIvWindRc;
    private ImageView mIvWindRs;
    private ImageView mIvWindRx;
    private LinearLayout mTempLayout;
    private LinearLayout mWindLayout;

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.KaiYi3xCarSet(8, Neg(this.mAcInfo.fgAC));
                return;
            case 1:
                CanJni.KaiYi3xCarSet(9, Neg(this.mAcInfo.fgInnerLoop));
                return;
            case 2:
                CanJni.KaiYi3xCarSet(10, 1);
                return;
            case 3:
                CanJni.KaiYi3xCarSet(11, Neg(this.mAcInfo.fgRearLight));
                return;
            case 4:
                CanJni.KaiYi3xCarSet(15, Neg(this.mAcInfo.PWR));
                return;
            case 5:
                CanJni.KaiYi3xCarSet(13, 0);
                return;
            case 6:
                CanJni.KaiYi3xCarSet(13, 1);
                return;
            case 7:
                CanJni.KaiYi3xCarSet(13, 2);
                return;
            case 8:
                CanJni.KaiYi3xCarSet(13, 3);
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        int windIndex = getWindIndex(event);
        int tempIndex = getTempIndex(event);
        switch (action) {
            case 0:
            case 1:
            case 2:
                if (id == 10) {
                    CanJni.KaiYi3xCarSet(12, windIndex + 1);
                    return true;
                } else if (id != 9) {
                    return true;
                } else {
                    CanJni.KaiYi3xCarSet(7, 13 - tempIndex);
                    return true;
                }
            default:
                return true;
        }
    }

    private int getTempIndex(MotionEvent event) {
        float f = 0.0f;
        if (event.getY() > 0.0f) {
            f = event.getY();
        }
        int scrollY = (int) f;
        if (scrollY >= 300) {
            scrollY = 300;
        }
        int tempIndex = scrollY / 25;
        if (((double) (scrollY % 25)) > 12.5d) {
            return tempIndex + 1;
        }
        return tempIndex;
    }

    private int getWindIndex(MotionEvent event) {
        float f = 0.0f;
        if (event.getX() > 0.0f) {
            f = event.getX();
        }
        int scrollX = (int) f;
        if (scrollX >= 271) {
            scrollX = 271;
        }
        int windIndex = scrollX / 45;
        if (((double) (scrollX % 45)) > 22.5d) {
            return windIndex + 1;
        }
        return windIndex;
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_relative;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.isACJump = CanFunc.IsCanActivityJumped(this);
        this.mContainer = new RelativeLayoutContainer(this);
        this.mContainer.setBackgroundResource(R.drawable.can_ky3x_bg);
        this.mBtnAc = this.mContainer.addButton(165, 30);
        this.mBtnLooper = this.mContainer.addButton(165, 130);
        this.mBtnFrontHeat = this.mContainer.addButton(165, Can.CAN_CC_HF_DJ);
        this.mBtnRearHeat = this.mContainer.addButton(165, KeyDef.RKEY_RDS_TA);
        this.mBtnAcToggle = this.mContainer.addButton(66, 430);
        this.mBtnModeHead = this.mContainer.addButton(568, 430);
        this.mBtnModeHeadFoot = this.mContainer.addButton(668, 430);
        this.mBtnModeFoot = this.mContainer.addButton(KeyDef.SKEY_POWEWR_1, 430);
        this.mBtnModeFootFront = this.mContainer.addButton(869, 430);
        ImageView tempPoint = new ImageView(this);
        tempPoint.setImageResource(R.drawable.can_ky3x_point);
        ImageView windPoint = new ImageView(this);
        windPoint.setImageResource(R.drawable.can_ky3x_point);
        this.mTempLayout = new LinearLayout(this);
        this.mTempLayout.setOrientation(1);
        this.mTempLayout.setGravity(80);
        this.mTempLayout.addView(tempPoint);
        this.mWindLayout = new LinearLayout(this);
        this.mWindLayout.setOrientation(0);
        this.mWindLayout.addView(windPoint);
        this.mContainer.addView(this.mTempLayout, 110, 60, 38, KeyDef.RKEY_res1);
        this.mContainer.addView(this.mWindLayout, 208, 473, KeyDef.RKEY_MEDIA_ZOOM, 38);
        this.mIvWindLs = this.mContainer.addImage(CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, 32, R.drawable.can_ky3x_shine_ls);
        this.mIvWindRs = this.mContainer.addImage(675, 32, R.drawable.can_ky3x_shine_rs);
        this.mIvWindLc = this.mContainer.addImage(281, 203, R.drawable.can_ky3x_shine_lc);
        this.mIvWindCc = this.mContainer.addImage(CanCameraUI.BTN_CHANA_ALSVINV7_MODE3, 191, R.drawable.can_ky3x_shine_cc);
        this.mIvWindRc = this.mContainer.addImage(KeyDef.SKEY_CALLUP_1, 186, R.drawable.can_ky3x_shine_rc);
        this.mIvWindLx = this.mContainer.addImage(279, 275, R.drawable.can_ky3x_shine_lx);
        this.mIvWindRx = this.mContainer.addImage(698, 275, R.drawable.can_ky3x_shine_rx);
        this.mContainer.setDrawableUpDnSel(this.mBtnAc, R.drawable.can_ky3x_ac_up, R.drawable.can_ky3x_ac_dn).setDrawableUpDnSel(this.mBtnLooper, R.drawable.can_ky3x_wxh_up, R.drawable.can_ky3x_nxh_dn).setDrawableUpDnSel(this.mBtnFrontHeat, R.drawable.can_ky3x_window_up, R.drawable.can_ky3x_window_dn).setDrawableUpDnSel(this.mBtnRearHeat, R.drawable.can_ky3x_window0_up, R.drawable.can_ky3x_window0_dn).setDrawableUpDnSel(this.mBtnAcToggle, R.drawable.can_ky3x_snow_up, R.drawable.can_ky3x_snow_dn).setDrawableUpDnSel(this.mBtnModeHead, R.drawable.can_ky3x_01_up, R.drawable.can_ky3x_01_dn).setDrawableUpDnSel(this.mBtnModeHeadFoot, R.drawable.can_ky3x_02_up, R.drawable.can_ky3x_02_dn).setDrawableUpDnSel(this.mBtnModeFoot, R.drawable.can_ky3x_03_up, R.drawable.can_ky3x_03_dn).setDrawableUpDnSel(this.mBtnModeFootFront, R.drawable.can_ky3x_04_up, R.drawable.can_ky3x_04_dn).setIdClickListener(this.mBtnAc, 0, this).setIdClickListener(this.mBtnLooper, 1, this).setIdClickListener(this.mBtnFrontHeat, 2, this).setIdClickListener(this.mBtnRearHeat, 3, this).setIdClickListener(this.mBtnAcToggle, 4, this).setIdClickListener(this.mBtnModeHead, 5, this).setIdClickListener(this.mBtnModeHeadFoot, 6, this).setIdClickListener(this.mBtnModeFoot, 7, this).setIdClickListener(this.mBtnModeFootFront, 8, this).setIdTouchListener(this.mTempLayout, 9, this).setIdTouchListener(this.mWindLayout, 10, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Can.updateAC();
        updateACUI();
    }

    private void updateACUI() {
        this.mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        setViewSelected((View) this.mBtnAc, this.mAcInfo.fgAC);
        if (i2b(this.mAcInfo.fgLoopDisable)) {
            this.mBtnLooper.setBackgroundResource(R.drawable.can_ky3x_wxh_up);
        } else {
            this.mBtnLooper.setDrawable(R.drawable.can_ky3x_wxh_up, R.drawable.can_ky3x_nxh_dn);
            setViewSelected((View) this.mBtnLooper, this.mAcInfo.fgInnerLoop);
        }
        setViewSelected((View) this.mBtnRearHeat, this.mAcInfo.fgRearLight);
        setViewSelected((View) this.mBtnAcToggle, this.mAcInfo.PWR);
        setWindMode(this.mAcInfo.fgDFBL, this.mAcInfo.fgParallelWind, this.mAcInfo.fgDownWind);
        setTempValue(this.mAcInfo.nLeftTemp);
        setWindValue(this.mAcInfo.nWindValue);
    }

    private void setTempValue(int tempValue) {
        if (tempValue > 0) {
            this.mTempLayout.scrollTo(0, (tempValue - 1) * 25);
        } else {
            this.mTempLayout.scrollTo(0, 0);
        }
    }

    private void setWindValue(int windValue) {
        boolean z = true;
        showGoneView(this.mWindLayout.getChildAt(0), windValue != 0);
        View childAt = this.mTempLayout.getChildAt(0);
        if (windValue == 0) {
            z = false;
        }
        showGoneView(childAt, z);
        if (windValue == 0) {
            setViewSelected((View) this.mBtnAc, false);
            setViewSelected((View) this.mBtnLooper, false);
            setWindMode(0, 0, 0);
        }
        if (windValue > 0) {
            this.mWindLayout.scrollTo(-((windValue - 1) * 45), 0);
        }
    }

    private void setWindMode(int fgDFBL, int fgParalle, int fgDown) {
        this.mBtnModeHead.setSelected(false);
        this.mBtnModeHeadFoot.setSelected(false);
        this.mBtnModeFoot.setSelected(false);
        this.mBtnModeFootFront.setSelected(false);
        this.mBtnFrontHeat.setSelected(false);
        showFrontWind(false);
        showParalleWind(false);
        showDownWind(false);
        if (i2b(fgParalle) && i2b(fgDown) && !i2b(fgDFBL)) {
            this.mBtnModeHeadFoot.setSelected(true);
            showParalleWind(true);
            showDownWind(true);
        } else if (i2b(fgDown) && i2b(fgDFBL) && !i2b(fgParalle)) {
            this.mBtnModeFootFront.setSelected(true);
            showFrontWind(true);
            showDownWind(true);
        } else if (i2b(fgParalle) && !i2b(fgDown) && !i2b(fgDFBL)) {
            this.mBtnModeHead.setSelected(true);
            showParalleWind(true);
        } else if (i2b(fgDown) && !i2b(fgParalle) && !i2b(fgDFBL)) {
            this.mBtnModeFoot.setSelected(true);
            showDownWind(true);
        } else if (!i2b(fgDown) && !i2b(fgParalle) && i2b(fgDFBL)) {
            this.mBtnFrontHeat.setSelected(true);
            showFrontWind(true);
        }
    }

    private void showFrontWind(boolean show) {
        showGoneView((View) this.mIvWindLs, show);
        showGoneView((View) this.mIvWindRs, show);
    }

    private void showParalleWind(boolean show) {
        showGoneView((View) this.mIvWindLc, show);
        showGoneView((View) this.mIvWindCc, show);
        showGoneView((View) this.mIvWindRc, show);
    }

    private void showDownWind(boolean show) {
        showGoneView((View) this.mIvWindLx, show);
        showGoneView((View) this.mIvWindRx, show);
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (GetTickCount() > CanFunc.mLastACTick + AC_SHOW_TIME && this.isACJump) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
