package com.ts.can.geely.boy;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

public class CanGeelyBoyCarACActivity extends CanCommonActivity {
    private static long AC_SHOW_TIME = 3000;
    private static final int AC_TOGGLE = 18;
    private static final int LT_TEMP_DECREASE = 1;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int MODE = 19;
    private static final int MODE_FOOT = 8;
    private static final int MODE_FOOT_FRONT = 9;
    private static final int MODE_HEAD = 6;
    private static final int MODE_HEAD_FOOT = 7;
    private static final int RT_TEMP_DECREASE = 3;
    private static final int RT_TEMP_INCREASE = 2;
    private static final int STATUS_AC = 10;
    private static final int STATUS_AC_MAX = 16;
    private static final int STATUS_AUTO = 12;
    private static final int STATUS_DUAL = 13;
    private static final int STATUS_FRONT = 14;
    private static final int STATUS_LOOPER = 11;
    private static final int STATUS_REAR = 15;
    private static final int WIND_DECREASE = 5;
    private static final int WIND_INCREASE = 4;
    private static int[] mWindArrays = {R.drawable.can_rh7_signal01_dn, R.drawable.can_rh7_signal02_dn, R.drawable.can_rh7_signal03_dn, R.drawable.can_rh7_signal04_dn, R.drawable.can_rh7_signal05_dn, R.drawable.can_rh7_signal06_dn, R.drawable.can_rh7_signal07_dn};
    private boolean isACJump = false;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAcMax;
    private ParamButton mBtnAcToggle;
    private ParamButton mBtnAuto;
    private ParamButton mBtnDual;
    private ParamButton mBtnFront;
    private ParamButton mBtnLooper;
    private ParamButton mBtnModeFoot;
    private ParamButton mBtnModeFootFront;
    private ParamButton mBtnModeHead;
    private ParamButton mBtnModeHeadFoot;
    private ParamButton mBtnRear;
    private ImageView mIvIon;
    private ImageView[] mIvWinds = new ImageView[7];
    private TextView mTvLeftTemp;
    private TextView mTvRightTemp;

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GeelyBoyCarAcSet(13, 1);
                return;
            case 1:
                CanJni.GeelyBoyCarAcSet(14, 1);
                return;
            case 2:
                CanJni.GeelyBoyCarAcSet(15, 1);
                return;
            case 3:
                CanJni.GeelyBoyCarAcSet(16, 1);
                return;
            case 4:
                CanJni.GeelyBoyCarAcSet(11, 1);
                return;
            case 5:
                CanJni.GeelyBoyCarAcSet(12, 1);
                return;
            case 6:
                CanJni.GeelyBoyCarAcSet(7, 1);
                return;
            case 7:
                CanJni.GeelyBoyCarAcSet(8, 1);
                return;
            case 8:
                CanJni.GeelyBoyCarAcSet(9, 1);
                return;
            case 9:
                CanJni.GeelyBoyCarAcSet(10, 1);
                return;
            case 10:
                CanJni.GeelyBoyCarAcSet(1, 1);
                return;
            case 11:
                if (i2b(this.mAcInfo.fgInnerLoop)) {
                    CanJni.GeelyBoyCarAcSet(4, 1);
                    return;
                } else {
                    CanJni.GeelyBoyCarAcSet(5, 1);
                    return;
                }
            case 12:
                CanJni.GeelyBoyCarAcSet(2, 1);
                return;
            case 13:
                CanJni.GeelyBoyCarAcSet(3, 1);
                return;
            case 14:
                CanJni.GeelyBoyCarAcSet(6, 1);
                return;
            case 15:
                CanJni.GeelyBoyCarAcSet(18, 1);
                return;
            case 16:
                CanJni.GeelyBoyCarAcSet(19, 1);
                return;
            case 18:
                CanJni.GeelyBoyCarAcSet(0, 1);
                return;
            case 19:
                CanJni.GeelyBoyCarAcSet(17, 1);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_relative;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.isACJump = CanFunc.IsCanActivityJumped(this);
        RelativeLayoutContainer container = new RelativeLayoutContainer(this);
        container.setBackgroundResource(R.drawable.can_rh7_bg);
        ParamButton leftTempIncrease = container.addButton(50, 87);
        ParamButton leftTempDecrease = container.addButton(50, 284);
        ParamButton rightTempIncrease = container.addButton(880, 87);
        ParamButton rightTempDecrease = container.addButton(880, 284);
        ParamButton windIncrease = container.addButton(188, 87);
        ParamButton windDecrease = container.addButton(188, 284);
        container.addImage(191, 198, R.drawable.can_rh7_signal_up);
        for (int i = 0; i < this.mIvWinds.length; i++) {
            this.mIvWinds[i] = container.addImage(191, 198, mWindArrays[i]);
        }
        this.mTvLeftTemp = container.addText(53, 198, 92, 61);
        this.mTvRightTemp = container.addText(883, 198, 92, 61);
        this.mBtnModeHead = container.addButton(KeyDef.RKEY_MEDIA_ANGLE, 78);
        this.mBtnModeHeadFoot = container.addButton(KeyDef.RKEY_MEDIA_ANGLE, Can.CAN_DFFG_S560);
        this.mBtnModeFoot = container.addButton(KeyDef.RKEY_MEDIA_ANGLE, Can.CAN_LIEBAO_WC);
        this.mBtnModeFootFront = container.addButton(KeyDef.RKEY_MEDIA_ANGLE, KeyDef.RKEY_MEDIA_OSD);
        this.mBtnAc = container.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 40);
        this.mBtnLooper = container.addButton(757, 40);
        this.mBtnAuto = container.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, Can.CAN_AUDI_ZMYT);
        this.mBtnDual = container.addButton(757, Can.CAN_AUDI_ZMYT);
        this.mBtnFront = container.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 265);
        this.mBtnRear = container.addButton(757, 265);
        this.mBtnAcMax = container.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 378);
        this.mBtnAcToggle = container.addButton(757, 373, 100, 100);
        this.mIvIon = container.addImage(470, 410, R.drawable.can_rh7_ion);
        TextView mode = container.addText(KeyDef.RKEY_MEDIA_ANGLE, 435);
        container.setDrawableUpDn(leftTempIncrease, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn).setDrawableUpDn(leftTempDecrease, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn).setDrawableUpDn(rightTempIncrease, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn).setDrawableUpDn(rightTempDecrease, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn).setDrawableUpDn(windIncrease, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn).setDrawableUpDn(windDecrease, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn).setDrawableUpDnSel(this.mBtnModeHead, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn).setDrawableUpDnSel(this.mBtnModeHeadFoot, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn).setDrawableUpDnSel(this.mBtnModeFoot, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn).setDrawableUpDnSel(this.mBtnModeFootFront, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn).setDrawableUpDnSel(this.mBtnAc, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn).setDrawableUpDnSel(this.mBtnLooper, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_nxh_dn).setDrawableUpDnSel(this.mBtnAuto, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn).setDrawableUpDnSel(this.mBtnDual, R.drawable.can_rh7_dual_up, R.drawable.can_rh7_dual_dn).setDrawableUpDnSel(this.mBtnFront, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn).setDrawableUpDnSel(this.mBtnRear, R.drawable.can_rh7_window01_up, R.drawable.can_rh7_window01_dn).setDrawableUpDnSel(this.mBtnAcMax, R.drawable.can_rh7_max_up, R.drawable.can_rh7_max_dn).setDrawableUpDnSel(this.mBtnAcToggle, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn).setTextStyle(this.mTvLeftTemp, 0, 17, Color.parseColor("#08D2D3"), 18).setTextStyle(this.mTvRightTemp, 0, 17, Color.parseColor("#08D2D3"), 18).setTextStyle(mode, "MODE", 17, -1, 24).setColorUpDnList(mode, -1, Color.parseColor("#0066FF")).setIdClickListener(leftTempIncrease, 0, this).setIdClickListener(leftTempDecrease, 1, this).setIdClickListener(rightTempIncrease, 2, this).setIdClickListener(rightTempDecrease, 3, this).setIdClickListener(windIncrease, 4, this).setIdClickListener(windDecrease, 5, this).setIdClickListener(this.mBtnModeHead, 6, this).setIdClickListener(this.mBtnModeHeadFoot, 7, this).setIdClickListener(this.mBtnModeFoot, 8, this).setIdClickListener(this.mBtnModeFootFront, 9, this).setIdClickListener(this.mBtnAc, 10, this).setIdClickListener(this.mBtnLooper, 11, this).setIdClickListener(this.mBtnAuto, 12, this).setIdClickListener(this.mBtnDual, 13, this).setIdClickListener(this.mBtnFront, 14, this).setIdClickListener(this.mBtnRear, 15, this).setIdClickListener(this.mBtnAcMax, 16, this).setIdClickListener(this.mBtnAcToggle, 18, this).setIdClickListener(mode, 19, this);
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
        this.mTvLeftTemp.setText(this.mAcInfo.szLtTemp);
        this.mTvRightTemp.setText(this.mAcInfo.szRtTemp);
        setViewSelected((View) this.mBtnAc, this.mAcInfo.fgAC);
        setViewSelected((View) this.mBtnLooper, this.mAcInfo.fgInnerLoop);
        setViewSelected((View) this.mBtnAuto, this.mAcInfo.nAutoLight);
        setViewSelected((View) this.mBtnDual, this.mAcInfo.fgDual);
        setViewSelected((View) this.mBtnFront, this.mAcInfo.fgDFBL);
        setViewSelected((View) this.mBtnRear, this.mAcInfo.fgRearLight);
        setViewSelected((View) this.mBtnAcMax, this.mAcInfo.fgACMax);
        showGoneView((View) this.mIvIon, this.mAcInfo.fgPTC);
        setViewSelected((View) this.mBtnAcToggle, this.mAcInfo.PWR);
        setWindValue(this.mAcInfo.nWindValue);
        setWindMode(this.mAcInfo.fgParallelWind, this.mAcInfo.fgDownWind, this.mAcInfo.fgForeWindMode);
    }

    private void setWindMode(int fgParallel, int fgDown, int fgForeWind) {
        setViewSelected((View) this.mBtnModeHead, false);
        setViewSelected((View) this.mBtnModeHeadFoot, false);
        setViewSelected((View) this.mBtnModeFoot, false);
        setViewSelected((View) this.mBtnModeFootFront, false);
        if (i2b(fgParallel) && !i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeHead, true);
        } else if (i2b(fgParallel) && i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeHeadFoot, true);
        } else if (!i2b(fgParallel) && i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeFoot, true);
        } else if (!i2b(fgParallel) && i2b(fgDown) && i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeFootFront, true);
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
        if (GetTickCount() > CanFunc.mLastACTick + AC_SHOW_TIME && this.isACJump) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GeelyBoyCarQuery(33, 0);
    }
}
