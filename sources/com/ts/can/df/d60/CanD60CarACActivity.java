package com.ts.can.df.d60;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCommonActivity;
import com.ts.can.CanFunc;
import com.ts.canview.RelativeLayoutContainer;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanD60CarACActivity extends CanCommonActivity implements View.OnTouchListener {
    private static final int AC_TOGGLE = 18;
    private static final int AUTO = 20;
    private static final int LT_TEMP_DECREASE = 1;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int MODE = 19;
    private static final int RT_TEMP_DECREASE = 3;
    private static final int RT_TEMP_INCREASE = 2;
    private static final int STATUS_AC = 10;
    private static final int STATUS_LOOPER_INNER = 11;
    private static final int STATUS_LOOPER_OUTTER = 12;
    public static final String TAG = "CanD60CarACActivity";
    private static final int WIND_DECREASE = 5;
    private static final int WIND_INCREASE = 4;
    protected static boolean mIsAC;
    private static int[] mWindArrays = {R.drawable.can_rh7_signal01_dn, R.drawable.can_rh7_signal02_dn, R.drawable.can_rh7_signal03_dn, R.drawable.can_rh7_signal04_dn, R.drawable.can_rh7_signal05_dn, R.drawable.can_rh7_signal06_dn, R.drawable.can_rh7_signal07_dn};
    protected static boolean mfgJump = false;
    private int cmd;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAcMax;
    private ParamButton mBtnAcToggle;
    private ParamButton mBtnAuto;
    private ParamButton mBtnDual;
    private ParamButton mBtnFront;
    private ParamButton mBtnLooperInner;
    private ParamButton mBtnLooperOutter;
    private ParamButton mBtnMode;
    private ParamButton mBtnModeFoot;
    private ParamButton mBtnModeFootFront;
    private ParamButton mBtnModeHead;
    private ParamButton mBtnModeHeadFoot;
    private ParamButton mBtnRear;
    private ImageView[] mIvWinds = new ImageView[7];
    private TextView mTvLeftTemp;
    private TextView mTvRightTemp;

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                if (this.mAcInfo.nLeftTemp < 15) {
                    CanJni.CanVenuciaD60AcSet(19, this.mAcInfo.nLeftTemp + 1);
                    return;
                }
                return;
            case 1:
                if (this.mAcInfo.nLeftTemp > 1) {
                    CanJni.CanVenuciaD60AcSet(19, this.mAcInfo.nLeftTemp - 1);
                    return;
                }
                return;
            case 2:
                if (this.mAcInfo.nRightTemp < 15) {
                    CanJni.CanVenuciaD60AcSet(20, this.mAcInfo.nRightTemp + 1);
                    return;
                }
                return;
            case 3:
                if (this.mAcInfo.nRightTemp > 1) {
                    CanJni.CanVenuciaD60AcSet(20, this.mAcInfo.nRightTemp - 1);
                    return;
                }
                return;
            case 4:
                if (this.mAcInfo.nWindValue < 7) {
                    CanJni.CanVenuciaD60AcSet(18, this.mAcInfo.nWindValue + 1);
                    return;
                }
                return;
            case 5:
                if (this.mAcInfo.nWindValue > 1) {
                    CanJni.CanVenuciaD60AcSet(18, this.mAcInfo.nWindValue - 1);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        if (action == 0) {
            switch (id) {
                case 10:
                    this.cmd = 1;
                    break;
                case 11:
                    this.cmd = 4;
                    break;
                case 12:
                    this.cmd = 5;
                    break;
                case 18:
                    this.cmd = 0;
                    break;
                case 19:
                    this.cmd = 17;
                    break;
                case 20:
                    this.cmd = 2;
                    break;
            }
            v.setSelected(true);
            CanJni.CanVenuciaD60AcSet(this.cmd, 1);
        } else if (action == 1) {
            v.setSelected(false);
            CanJni.CanVenuciaD60AcSet(this.cmd, 0);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_relative;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        mfgJump = CanFunc.IsCanActivityJumped(this);
        Log.d(TAG, "jump = " + mfgJump);
        RelativeLayoutContainer container = new RelativeLayoutContainer(this);
        container.setBackgroundResource(R.drawable.can_rh7_bg_01);
        ParamButton leftTempIncrease = container.addButton(50, 117);
        ParamButton leftTempDecrease = container.addButton(50, KeyDef.RKEY_POWER);
        ParamButton rightTempIncrease = container.addButton(880, 117);
        ParamButton rightTempDecrease = container.addButton(880, KeyDef.RKEY_POWER);
        ParamButton windIncrease = container.addButton(188, 117);
        ParamButton windDecrease = container.addButton(188, KeyDef.RKEY_POWER);
        container.addImage(191, Can.CAN_TEANA_OLD_DJ, R.drawable.can_rh7_signal_up);
        for (int i = 0; i < this.mIvWinds.length; i++) {
            this.mIvWinds[i] = container.addImage(191, Can.CAN_TEANA_OLD_DJ, mWindArrays[i]);
        }
        this.mTvLeftTemp = container.addText(53, Can.CAN_TEANA_OLD_DJ, 95, 61);
        this.mTvRightTemp = container.addText(883, Can.CAN_TEANA_OLD_DJ, 95, 61);
        this.mBtnModeHead = container.addButton(KeyDef.RKEY_MEDIA_ANGLE, 108);
        this.mBtnModeHeadFoot = container.addButton(KeyDef.RKEY_MEDIA_ANGLE, 185);
        this.mBtnModeFoot = container.addButton(KeyDef.RKEY_MEDIA_ANGLE, 264);
        this.mBtnModeFootFront = container.addButton(KeyDef.RKEY_MEDIA_ANGLE, KeyDef.RKEY_res5);
        this.mBtnDual = container.addButton(200, 20);
        this.mBtnAcMax = container.addButton(KeyDef.RKEY_MEDIA_SLOW, 20);
        this.mBtnFront = container.addButton(450, 17);
        this.mBtnRear = container.addButton(CanCameraUI.BTN_TRUMPCHI_GS7_MODE1, 17);
        this.mBtnAc = container.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 110);
        this.mBtnLooperOutter = container.addButton(757, 110);
        this.mBtnLooperInner = container.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 222);
        this.mBtnMode = container.addButton(757, 222);
        this.mBtnAcToggle = container.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_res5);
        this.mBtnAuto = container.addButton(757, KeyDef.RKEY_res5);
        container.setDrawableUpDn(leftTempIncrease, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn).setDrawableUpDn(leftTempDecrease, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn).setDrawableUpDn(rightTempIncrease, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn).setDrawableUpDn(rightTempDecrease, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn).setDrawableUpDn(windIncrease, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn).setDrawableUpDn(windDecrease, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn).setDrawableUpSel(this.mBtnModeHead, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn).setDrawableUpSel(this.mBtnModeHeadFoot, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn).setDrawableUpSel(this.mBtnModeFoot, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn).setDrawableUpSel(this.mBtnModeFootFront, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn).setDrawableUpDnSel(this.mBtnAc, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn).setDrawableUpDnSel(this.mBtnLooperOutter, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn).setDrawableUpDnSel(this.mBtnLooperInner, R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_nxh_dn).setDrawableUpDnSel(this.mBtnMode, R.drawable.can_rh7_mode_up, R.drawable.can_rh7_mode_dn).setDrawableUpDnSel(this.mBtnAcToggle, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn).setDrawableUpDnSel(this.mBtnAuto, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn).setDrawableUpSel(this.mBtnDual, R.drawable.conditioning_dual_up, R.drawable.conditioning_dual_dn).setDrawableUpSel(this.mBtnAcMax, R.drawable.conditioning_acmax_up, R.drawable.conditioning_acmax_dn).setDrawableUpSel(this.mBtnFront, R.drawable.conditioning_wind_up, R.drawable.conditioning_wind_dn).setDrawableUpSel(this.mBtnRear, R.drawable.conditioning_heat_up, R.drawable.conditioning_heat_dn).setTextStyle(this.mTvLeftTemp, 0, 17, Color.parseColor("#08D2D3"), 16).setTextStyle(this.mTvRightTemp, 0, 17, Color.parseColor("#08D2D3"), 16).setIdClickListener(leftTempIncrease, 0, this).setIdClickListener(leftTempDecrease, 1, this).setIdClickListener(rightTempIncrease, 2, this).setIdClickListener(rightTempDecrease, 3, this).setIdClickListener(windIncrease, 4, this).setIdClickListener(windDecrease, 5, this).setIdTouchListener(this.mBtnAc, 10, this).setIdTouchListener(this.mBtnLooperInner, 11, this).setIdTouchListener(this.mBtnLooperOutter, 12, this).setIdTouchListener(this.mBtnMode, 19, this).setIdTouchListener(this.mBtnAcToggle, 18, this).setIdTouchListener(this.mBtnAuto, 20, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        Can.updateAC();
        updateACUI();
        Log.d(TAG, "-----onResume-----");
        mIsAC = true;
    }

    public static void ShowAC() {
        if (!mIsAC) {
            mfgJump = true;
            CanFunc.showCanActivity(CanD60CarACActivity.class);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
        mIsAC = false;
        mfgJump = false;
    }

    private void updateACUI() {
        boolean z = false;
        this.mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mTvLeftTemp.setText(this.mAcInfo.szLtTemp);
        this.mTvRightTemp.setText(this.mAcInfo.szRtTemp);
        Log.d(TAG, "mAcInfo.szLtTemp=" + this.mAcInfo.szLtTemp);
        setViewSelected((View) this.mBtnAc, this.mAcInfo.fgAC);
        setViewSelected((View) this.mBtnLooperInner, this.mAcInfo.fgInnerLoop);
        ParamButton paramButton = this.mBtnLooperOutter;
        if (!i2b(this.mAcInfo.fgInnerLoop)) {
            z = true;
        }
        setViewSelected((View) paramButton, z);
        setViewSelected((View) this.mBtnDual, this.mAcInfo.fgDual);
        setViewSelected((View) this.mBtnAcMax, this.mAcInfo.fgACMax);
        setViewSelected((View) this.mBtnRear, this.mAcInfo.fgRearLight);
        setWindValue(this.mAcInfo.nWindValue);
        setWindMode(this.mAcInfo.fgParallelWind, this.mAcInfo.fgDownWind, this.mAcInfo.fgForeWindMode);
        setViewSelected((View) this.mBtnAuto, this.mAcInfo.nAutoLight);
    }

    private void setWindMode(int fgParallel, int fgDown, int fgForeWind) {
        setViewSelected((View) this.mBtnModeHead, false);
        setViewSelected((View) this.mBtnModeHeadFoot, false);
        setViewSelected((View) this.mBtnModeFoot, false);
        setViewSelected((View) this.mBtnModeFootFront, false);
        setViewSelected((View) this.mBtnFront, false);
        if (i2b(fgParallel) && !i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeHead, true);
        } else if (i2b(fgParallel) && i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeHeadFoot, true);
        } else if (!i2b(fgParallel) && i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeFoot, true);
        } else if (!i2b(fgParallel) && i2b(fgDown) && i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeFootFront, true);
        } else if (!i2b(fgParallel) && !i2b(fgDown) && i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnFront, true);
        }
    }

    private void setWindValue(int wind) {
        for (ImageView windIcon : this.mIvWinds) {
            showGoneView((View) windIcon, false);
        }
        for (int i = 0; i < wind; i++) {
            if (i < this.mIvWinds.length) {
                showGoneView((View) this.mIvWinds[i], true);
            }
        }
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (mfgJump && GetTickCount() > CanFunc.mLastACTick + 6000) {
            finish();
            Log.d(TAG, "UserAll Exit Ac");
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
