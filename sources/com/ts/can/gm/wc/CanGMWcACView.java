package com.ts.can.gm.wc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACActivity;
import com.ts.can.CanBaseACView;
import com.ts.can.MyApplication;

public class CanGMWcACView extends CanBaseACView {
    private static final int IMAGE_WIND = 201;
    private static final int ITEM_AC = 8;
    private static final int ITEM_AUTO = 10;
    private static final int ITEM_ENTER_FRONT_AC = 13;
    private static final int ITEM_ENTER_REAR_AC = 12;
    private static final int ITEM_LOOP = 9;
    private static final int ITEM_LT_TEMP_ADD = 0;
    private static final int ITEM_LT_TEMP_SUB = 1;
    private static final int ITEM_MODE_FOOT = 16;
    private static final int ITEM_MODE_FRONT = 14;
    private static final int ITEM_MODE_FRONT_FOOT = 18;
    private static final int ITEM_MODE_HEAD = 15;
    private static final int ITEM_MODE_HEAD_FOOT = 17;
    private static final int ITEM_RR_TEMP_ADD = 4;
    private static final int ITEM_RR_TEMP_SUB = 5;
    private static final int ITEM_RT_TEMP_ADD = 2;
    private static final int ITEM_RT_TEMP_SUB = 3;
    private static final int ITEM_SYNC = 11;
    private static final int ITEM_WIND_ADD = 6;
    private static final int ITEM_WIND_SUB = 7;
    private static final int TEXT_FRONT_SEAT = 104;
    private static final int TEXT_LT_TEMP = 100;
    private static final int TEXT_REAR_SEAT = 105;
    private static final int TEXT_RR_TEMP = 102;
    private static final int TEXT_RT_TEMP = 101;
    private static final int TEXT_WIND_VALUE = 103;
    public static boolean isRearAC;
    private static int[] mRearWindValues = {R.drawable.can_gl18_ac_fan_00, R.drawable.can_gl18_ac_fan_01, R.drawable.can_gl18_ac_fan_02, R.drawable.can_gl18_ac_fan_03, R.drawable.can_gl18_ac_fan_04, R.drawable.can_gl18_ac_fan_05, R.drawable.can_gl18_ac_fan_06};
    private static int[] mWindValues = {R.drawable.can_gl18_ac_fan_00, R.drawable.can_gl18_ac_fan_001, R.drawable.can_gl18_ac_fan_002, R.drawable.can_gl18_ac_fan_003, R.drawable.can_gl18_ac_fan_004, R.drawable.can_gl18_ac_fan_005, R.drawable.can_gl18_ac_fan_006, R.drawable.can_gl18_ac_fan_007, R.drawable.can_gl18_ac_fan_008};
    private CanDataInfo.CAN_ACInfo mACInfo;

    public CanGMWcACView(Activity activity) {
        super(activity);
    }

    public void doOnDestory() {
        super.doOnDestory();
        isRearAC = false;
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GmWcCarAirKey(12, 1);
                return;
            case 1:
                CanJni.GmWcCarAirKey(12, 2);
                return;
            case 2:
                CanJni.GmWcCarAirKey(13, 1);
                return;
            case 3:
                CanJni.GmWcCarAirKey(13, 2);
                return;
            case 4:
                CanJni.GmWcCarAirKey(22, 1);
                return;
            case 5:
                CanJni.GmWcCarAirKey(22, 2);
                return;
            case 6:
                if (isRearAC) {
                    CanJni.GmWcCarAirKey(21, 1);
                    return;
                } else {
                    CanJni.GmWcCarAirKey(11, 1);
                    return;
                }
            case 7:
                if (isRearAC) {
                    CanJni.GmWcCarAirKey(21, 2);
                    return;
                } else {
                    CanJni.GmWcCarAirKey(11, 2);
                    return;
                }
            case 8:
                CanJni.GmWcCarAirKey(2, Neg(this.mACInfo.fgAC));
                return;
            case 9:
                CanJni.GmWcCarAirKey(7, this.mACInfo.fgInnerLoop);
                return;
            case 10:
                if (isRearAC) {
                    CanJni.GmWcCarAirKey(17, 255);
                    return;
                } else {
                    CanJni.GmWcCarAirKey(7, 2);
                    return;
                }
            case 11:
                CanJni.GmWcCarAirKey(15, Neg(this.mACInfo.fgDual));
                return;
            case 12:
                isRearAC = true;
                EnterWin(CanBaseACActivity.class);
                return;
            case 13:
                isRearAC = false;
                EnterWin(CanBaseACActivity.class);
                return;
            case 14:
                CanJni.GmWcCarAirKey(5, 255);
                return;
            case 15:
                if (isRearAC) {
                    CanJni.GmWcCarAirKey(20, 255);
                    return;
                } else {
                    CanJni.GmWcCarAirKey(9, 255);
                    return;
                }
            case 16:
                if (isRearAC) {
                    CanJni.GmWcCarAirKey(18, 255);
                    return;
                } else {
                    CanJni.GmWcCarAirKey(10, 255);
                    return;
                }
            case 17:
                if (isRearAC) {
                    CanJni.GmWcCarAirKey(19, 255);
                    return;
                } else {
                    CanJni.GmWcCarAirKey(33, 255);
                    return;
                }
            case 18:
                CanJni.GmWcCarAirKey(34, 255);
                return;
            default:
                return;
        }
    }

    private void EnterWin(Class<?> clz) {
        Context context = MyApplication.mContext;
        Intent intent = new Intent();
        intent.setClass(context, clz);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 49);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        if (isRearAC) {
            InitRearAC();
        } else {
            InitFrontAC();
        }
    }

    private void InitFrontAC() {
        this.mTextAttrs = new int[][]{new int[]{49, Can.CAN_BJ20_WC, 110, 75, 100}, new int[]{862, Can.CAN_BJ20_WC, 110, 75, 101}, new int[]{372, 280, 103}, new int[]{527, 73, 111, 47, R.string.can_gl8_2017_auto, 10}, new int[]{655, 75, 111, 47, R.string.can_gl8_2017_rear_seat, 12}, new int[]{455, 183, 113, 44, R.string.can_gl8_2017_sync_already, 11}};
        this.mImageAttrs = new int[][]{new int[]{375, 294, R.drawable.can_gl18_ac_fan_00, 201}};
        int[] iArr = {261, 70, R.drawable.can_gl18_ac_icon_ac_up, R.drawable.can_gl18_ac_icon_ac_dn, 8};
        int[] iArr2 = new int[5];
        iArr2[0] = 49;
        iArr2[1] = 68;
        iArr2[2] = R.drawable.can_gl18_ac_icon_sh_up;
        iArr2[3] = R.drawable.can_gl18_ac_icon_sh_dn;
        this.mButtonAttrs = new int[][]{iArr, iArr2, new int[]{49, 294, R.drawable.can_gl18_ac_icon_xia_up, R.drawable.can_gl18_ac_icon_xia_dn, 1}, new int[]{858, 68, R.drawable.can_gl18_ac_icon_sh_up, R.drawable.can_gl18_ac_icon_sh_dn, 2}, new int[]{858, 294, R.drawable.can_gl18_ac_icon_xia_up, R.drawable.can_gl18_ac_icon_xia_dn, 3}, new int[]{252, 284, R.drawable.can_gl18_ac_prv_up, R.drawable.can_gl18_ac_prv_dn, 7}, new int[]{671, 284, R.drawable.can_gl18_ac_next_up, R.drawable.can_gl18_ac_next_dn, 6}, new int[]{389, 67, R.drawable.can_gl18_ac_icon_wxh_dn, R.drawable.can_gl18_ac_icon_nxh_dn, 9}, new int[]{86, 445, R.drawable.can_gl18_ac_icon01_up, R.drawable.can_gl18_ac_icon01_dn, 15}, new int[]{267, 445, R.drawable.can_gl18_ac_icon03_up, R.drawable.can_gl18_ac_icon03_dn, 16}, new int[]{448, 445, R.drawable.can_gl18_ac_icon02_up, R.drawable.can_gl18_ac_icon02_dn, 17}, new int[]{629, 445, R.drawable.can_gl18_ac_icon04_up, R.drawable.can_gl18_ac_icon04_dn, 18}, new int[]{810, 445, R.drawable.can_gl18_ac_icon05_up, R.drawable.can_gl18_ac_icon05_dn, 14}};
    }

    private void InitRearAC() {
        this.mTextAttrs = new int[][]{new int[]{80, 173, 40, 86, R.string.can_gl8_2017_front_seat, 104}, new int[]{858, 21, 110, 46, R.string.can_gl8_2017_rear_seat, 105}, new int[]{372, 165, 103}, new int[]{862, Can.CAN_BJ20_WC, 110, 75, 102}, new int[]{402, 74, 221, 46, R.string.can_gl8_2017_rear_seat_auto, 10}};
        this.mImageAttrs = new int[][]{new int[]{375, 178, R.drawable.can_gl18_ac_fan_00, 201}};
        this.mButtonAttrs = new int[][]{new int[]{858, 68, R.drawable.can_gl18_ac_icon_sh_up, R.drawable.can_gl18_ac_icon_sh_dn, 4}, new int[]{858, 294, R.drawable.can_gl18_ac_icon_xia_up, R.drawable.can_gl18_ac_icon_xia_dn, 5}, new int[]{252, 168, R.drawable.can_gl18_ac_prv_up, R.drawable.can_gl18_ac_prv_dn, 7}, new int[]{671, 168, R.drawable.can_gl18_ac_next_up, R.drawable.can_gl18_ac_next_dn, 6}, new int[]{266, 445, R.drawable.can_gl18_ac_icon01_up, R.drawable.can_gl18_ac_icon01_dn, 15}, new int[]{450, 445, R.drawable.can_gl18_ac_icon03_up, R.drawable.can_gl18_ac_icon03_dn, 16}, new int[]{631, 445, R.drawable.can_gl18_ac_icon02_up, R.drawable.can_gl18_ac_icon02_dn, 17}, new int[]{10, 173, R.drawable.can_gl18_ac_qz_up, R.drawable.can_gl18_ac_qz_dn, 13}};
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        if (isRearAC) {
            InitRearViews();
        } else {
            InitFrontViews();
        }
    }

    private void InitFrontViews() {
        setBackgroundResource(R.drawable.can_gl18_ac_bg01);
        setTextStyle(this.mTextObjects[0], -1, 24, 17);
        setTextStyle(this.mTextObjects[1], -1, 24, 17);
        setTextStyle(this.mTextObjects[2], -1, 18, 17);
        setTextStyle(this.mTextObjects[3], 0, 20, 17);
        setTextColorState(this.mTextObjects[3], -1, Color.parseColor("#FFCC00"));
        setIdClickListener(this.mTextObjects[3], 10);
        setTextStyle(this.mTextObjects[4], 0, 20, 17);
        setTextColorState(this.mTextObjects[4], -1, Color.parseColor("#FFCC00"));
        setIdClickListener(this.mTextObjects[4], 12);
        setTextStyle(this.mTextObjects[5], 0, 18, 17);
        setTextColorState(this.mTextObjects[5], -1, Color.parseColor("#06ebf9"));
        setIdClickListener(this.mTextObjects[5], 11);
    }

    private void InitRearViews() {
        setBackgroundResource(R.drawable.can_gl18_ac_bg02);
        setTextStyle(this.mTextObjects[0], -1, 20, 17);
        setTextStyle(this.mTextObjects[1], -1, 20, 17);
        setTextStyle(this.mTextObjects[2], -1, 18, 17);
        setTextStyle(this.mTextObjects[3], -1, 24, 17);
        setTextStyle(this.mTextObjects[4], 0, 20, 17);
        setTextColorState(this.mTextObjects[4], -1, Color.parseColor("#06ebf9"));
        setIdClickListener(this.mTextObjects[4], 10);
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mACInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        if (isRearAC) {
            updateText(102, this.mACInfo.szRearTemp);
            updateRearWindValue(this.mACInfo.nRearWindValue);
            updateWindMode(i2b(this.mACInfo.bRearUpWindFlg), i2b(this.mACInfo.bRearParallelWindFlg), i2b(this.mACInfo.bRearDownWindFlg));
            return;
        }
        updateFrontAC();
    }

    private void updateFrontAC() {
        updateButton(8, this.mACInfo.fgAC);
        updateButton(9, this.mACInfo.fgInnerLoop);
        updateText(10, this.mACInfo.fgAQS);
        updateText(11, this.mACInfo.fgDual);
        updateText(100, this.mACInfo.szLtTemp);
        updateText(101, this.mACInfo.szRtTemp);
        updateWindValue(this.mACInfo.nWindValue);
        updateWindMode(i2b(this.mACInfo.fgForeWindMode), i2b(this.mACInfo.fgParallelWind), i2b(this.mACInfo.fgDownWind));
    }

    private void updateWindMode(boolean fgUp, boolean fgParalle, boolean fgDown) {
        updateButton(14, 0);
        updateButton(18, 0);
        updateButton(16, 0);
        updateButton(15, 0);
        updateButton(17, 0);
        if (fgUp && fgDown && !fgParalle) {
            updateButton(18, 1);
        } else if (fgParalle && fgDown && !fgUp) {
            updateButton(17, 1);
        } else if (fgUp && !fgParalle && !fgDown) {
            updateButton(14, 1);
        } else if (!fgUp && fgParalle && !fgDown) {
            updateButton(15, 1);
        } else if (!fgUp && !fgParalle && fgDown) {
            updateButton(16, 1);
        }
    }

    private void updateWindValue(int wind) {
        Log.d("HAHA", "wind = " + wind);
        if (wind == 19) {
            updateText(103, getString(R.string.can_gl8_2017_auto_wind));
            updateImage(201, R.drawable.can_gl18_ac_fan_dn);
        } else if (wind >= 0 && wind < mWindValues.length) {
            updateText(103, String.valueOf(wind) + getString(R.string.can_gl8_2017_wind_unit));
            updateImage(201, mWindValues[wind]);
        }
    }

    private void updateRearWindValue(int wind) {
        if (wind == 5) {
            updateText(103, getString(R.string.can_gl8_2017_auto_wind));
            updateImage(201, R.drawable.can_gl18_ac_fan_dn);
        } else if (wind >= 0 && wind < 5) {
            updateText(103, String.valueOf(wind) + getString(R.string.can_gl8_2017_wind_unit));
            updateImage(201, mRearWindValues[wind]);
        }
    }
}
