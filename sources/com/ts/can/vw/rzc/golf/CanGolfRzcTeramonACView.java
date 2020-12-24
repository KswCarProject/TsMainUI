package com.ts.can.vw.rzc.golf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.MyApplication;
import com.ts.canview.MyProgressBar;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanGolfRzcTeramonACView extends CanBaseACView {
    private static final int BTN_AC = 14;
    private static final int BTN_AUTO = 15;
    private static final int BTN_DECREASE_WIND = 12;
    private static final int BTN_DOWN_WIND = 18;
    private static final int BTN_INCREASE_WIND = 13;
    private static final int BTN_MAX = 16;
    private static final int BTN_PALLAX_WIND = 17;
    private static final int BTN_TEMP_DECREASE_1 = 20;
    private static final int BTN_TEMP_DECREASE_2 = 22;
    private static final int BTN_TEMP_DECREASE_3 = 24;
    private static final int BTN_TEMP_INCREASE_1 = 19;
    private static final int BTN_TEMP_INCREASE_2 = 21;
    private static final int BTN_TEMP_INCREASE_3 = 23;
    private static final int ITEM_CLEAR_AIR = 4;
    private static final int ITEM_FRONT_AIR = 8;
    private static final int ITEM_LT_HOT = 1;
    private static final int ITEM_OFF = 5;
    private static final int ITEM_REAR = 7;
    private static final int ITEM_REAR_AIR = 9;
    private static final int ITEM_RT_HOT = 2;
    private static final int ITEM_SETTINGS = 11;
    private static final int ITEM_SW_HOT = 3;
    private static final int ITEM_SYNC = 6;
    private static final int ITEM_WIND = 10;
    public static final String TAG = "CanGolfRzcTeramonACView";
    private CustomImgView acStatus;
    private TextView[] arrayViews;
    private CustomImgView autoStatus;
    private CustomImgView downWind;
    private String[] mAQSArray;
    private CustomImgView mAc;
    private CanDataInfo.GolfACData mAcData;
    private CustomImgView mAuto;
    protected boolean mAutoFinish = false;
    private String[] mAutoWind;
    private ParamButton mBtnCleanAir;
    private ParamButton mBtnFrontAir;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnOff;
    private ParamButton mBtnRear;
    private ParamButton mBtnRearAir;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnSettings;
    private ParamButton mBtnSwHot;
    private ParamButton mBtnSync;
    private ParamButton mBtnWind;
    private CustomImgView mDual;
    private CustomImgView mLtFire;
    private CustomImgView[] mLtHot;
    private TextView mLtTemp;
    private RelativeLayoutManager mManager;
    private CustomImgView mMaxFront;
    private CanDataInfo.GolfRearAir mRearAir;
    private CustomImgView mRearLight;
    private TextView mRearTemp;
    private CustomImgView mRtFire;
    private CustomImgView[] mRtHot;
    private TextView mRtTemp;
    private CustomImgView mWindLtDn;
    private CustomImgView mWindLtPxLt;
    private CustomImgView mWindLtPxRt;
    private CustomImgView mWindLtUp;
    private MyProgressBar mWindProgress;
    private CustomImgView mWindRtDn;
    private CustomImgView mWindRtPxLt;
    private CustomImgView mWindRtPxRt;
    private CustomImgView mWindRtUp;
    private TextView mWindVal;
    private CustomImgView maxStatus;
    private CustomImgView pallaxWind;
    private MyProgressBar windProgress;
    private TextView windVal;
    /* access modifiers changed from: private */
    public PopupWindow window;
    /* access modifiers changed from: private */
    public int windowId;

    public CanGolfRzcTeramonACView(Activity activity) {
        super(activity);
    }

    public void doOnResume() {
        super.doOnResume();
        CanFunc.mfgShowAC = false;
    }

    public void ResetData(boolean check) {
        CanJni.TeramontGetRearAir(this.mRearAir);
        if (!i2b(this.mRearAir.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mRearAir.Update)) {
            this.mRearAir.Update = 0;
            this.mBtnOff.SetSel(Neg(this.mRearAir.bPowerFlg));
            if (this.window != null && this.window.isShowing() && this.windowId == 9) {
                this.windProgress.SetCurPos(this.mRearAir.nWindValue);
                this.windVal.setText(String.valueOf(this.mRearAir.nWindValue));
                this.autoStatus.SetSel(this.mRearAir.nAutoLight);
                this.pallaxWind.SetSel(this.mRearAir.bParallelWindFlg);
                this.downWind.SetSel(this.mRearAir.bDownWindFlg);
            }
        }
    }

    /* access modifiers changed from: private */
    public long GetTickCount() {
        return SystemClock.uptimeMillis();
    }

    public void doOnPause() {
        super.doOnPause();
    }

    public void doOnDestory() {
        super.doOnDestory();
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int i = 7;
        int i2 = 1;
        int i3 = 0;
        CanDataInfo.CAN_ACInfo mACInfo = Can.mACInfo;
        CanFunc.mLastACTick = GetTickCount();
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                int ltHot = (mACInfo.nLtChairHot & 3) + 1;
                if (ltHot > 3) {
                    ltHot = 0;
                }
                CanJni.GolfSendCmd(173, ltHot);
                return;
            case 2:
                int RtHot = (mACInfo.nRtChairHot & 3) + 1;
                if (RtHot > 3) {
                    RtHot = 0;
                }
                CanJni.GolfSendCmd(174, RtHot);
                return;
            case 3:
                CanJni.GolfSendCmd(172, 1 - (this.mAcData.SwHot & 1));
                return;
            case 4:
                CanJni.GolfSendCmd(175, 1 - (this.mAcData.ClearAir & 1));
                return;
            case 5:
                CanJni.GolfSendParaCmd(162, Neg(this.mRearAir.bPowerFlg));
                return;
            case 6:
                CanJni.GolfSendCmd(179, Neg(mACInfo.fgDual));
                return;
            case 7:
                CanJni.GolfSendCmd(188, Neg(mACInfo.fgRearLock));
                return;
            case 8:
                showWindow(v, CanCameraUI.BTN_GOLF_WC_MODE1, 120);
                return;
            case 9:
                showWindow(v, 621, 190);
                return;
            case 10:
                showWindow(v, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, this.mAutoWind.length * 80);
                return;
            case 11:
                showWindow(v, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, this.mAQSArray.length * 80);
                return;
            case 12:
                if (this.window == null || !this.window.isShowing()) {
                    CanJni.GolfSendCmd(183, mACInfo.nWindValue + -1 < 0 ? 0 : mACInfo.nWindValue - 1);
                    return;
                }
                if (this.mRearAir.nWindValue - 1 >= 0) {
                    i3 = this.mRearAir.nWindValue - 1;
                }
                CanJni.GolfSendParaCmd(166, i3);
                return;
            case 13:
                if (this.window == null || !this.window.isShowing()) {
                    CanJni.GolfSendCmd(183, mACInfo.nWindValue + 1 > 7 ? 7 : mACInfo.nWindValue + 1);
                    return;
                }
                if (this.mRearAir.nWindValue + 1 <= 7) {
                    i = this.mRearAir.nWindValue + 1;
                }
                CanJni.GolfSendParaCmd(166, i);
                return;
            case 14:
                CanJni.GolfSendCmd(187, 2);
                return;
            case 15:
                if (this.windowId == 8) {
                    if (mACInfo.nAutoLight == 0) {
                        i3 = 1;
                    }
                    CanJni.GolfSendCmd(187, i3);
                    return;
                }
                if (this.mRearAir.nAutoLight != 0) {
                    i2 = 0;
                }
                CanJni.GolfSendParaCmd(163, i2);
                return;
            case 16:
                CanJni.GolfSendCmd(187, 3);
                return;
            case 17:
                if (this.windowId == 8) {
                    CanJni.GolfSendCmd(180, Neg(mACInfo.fgParallelWind));
                    return;
                } else {
                    CanJni.GolfSendParaCmd(164, Neg(this.mRearAir.bParallelWindFlg));
                    return;
                }
            case 18:
                if (this.windowId == 8) {
                    CanJni.GolfSendCmd(181, Neg(mACInfo.fgDownWind));
                    return;
                }
                CanJni.GolfSendParaCmd(165, Neg(this.mRearAir.bDownWindFlg));
                CanJni.GolfSendCmd(181, Neg(mACInfo.fgDownWind));
                return;
            case 19:
                CanJni.GolfSendCmd(184, 1);
                return;
            case 20:
                CanJni.GolfSendCmd(184, 0);
                return;
            case 21:
                CanJni.GolfSendCmd(185, 1);
                return;
            case 22:
                CanJni.GolfSendCmd(185, 0);
                return;
            case 23:
                CanJni.GolfSendCmd(186, 1);
                return;
            case 24:
                CanJni.GolfSendCmd(186, 0);
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
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mAutoWind = new String[]{"自动模式风量：轻", "自动模式风量：中", "自动模式风量：强"};
        this.mAQSArray = new String[]{"非自动空气循环", "自动空气循环"};
        this.mLtHot = new CustomImgView[3];
        this.mRtHot = new CustomImgView[3];
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        this.mManager = getRelativeManager();
        if (MainSet.GetScreenType() == 3) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            initCommon();
            this.mManager.GetLayout().setScaleX(0.78f);
            this.mManager.GetLayout().setScaleY(0.79f);
        } else {
            initCommon();
        }
        this.mAcData = new CanDataInfo.GolfACData();
        this.mRearAir = new CanDataInfo.GolfRearAir();
    }

    private void initCommon() {
        ParamButton LtTempDecrease = this.mManager.AddButton(30, 20, 90, 50);
        LtTempDecrease.setStateUpDn(R.drawable.can_ac_blue, R.drawable.can_ac_blue);
        LtTempDecrease.setTag(20);
        LtTempDecrease.setOnClickListener(this);
        ParamButton LtTempIncrease = this.mManager.AddButton(100, 20, 90, 50);
        LtTempIncrease.setStateUpDn(R.drawable.can_ac_red, R.drawable.can_ac_red);
        LtTempIncrease.setTag(19);
        LtTempIncrease.setOnClickListener(this);
        ParamButton RtTempDecrease = this.mManager.AddButton(KeyDef.SKEY_NAVI_2, 20, 90, 50);
        RtTempDecrease.setStateUpDn(R.drawable.can_ac_blue, R.drawable.can_ac_blue);
        RtTempDecrease.setTag(22);
        RtTempDecrease.setOnClickListener(this);
        ParamButton RtTempIncrease = this.mManager.AddButton(906, 20, 90, 50);
        RtTempIncrease.setStateUpDn(R.drawable.can_ac_red, R.drawable.can_ac_red);
        RtTempIncrease.setTag(21);
        RtTempIncrease.setOnClickListener(this);
        this.mLtTemp = this.mManager.AddText(50, 13, 170, 50);
        this.mLtTemp.setTextSize(0, 40.0f);
        this.mLtTemp.setTextColor(-1);
        this.mLtTemp.setText("13℃");
        this.mLtTemp.setGravity(19);
        this.mRtTemp = this.mManager.AddText(KeyDef.SKEY_MUTE_1, 13, 170, 50);
        this.mRtTemp.setTextSize(0, 40.0f);
        this.mRtTemp.setTextColor(-1);
        this.mRtTemp.setGravity(21);
        this.mRtTemp.setText("13℃");
        this.mWindProgress = new MyProgressBar(getActivity(), R.drawable.conditioning_progress_bar_up, R.drawable.conditioning_progress_bar_dn);
        this.mManager.AddViewWrapContent(this.mWindProgress, 267, 32);
        this.mWindProgress.SetMinMax(0, 7);
        this.mWindProgress.SetCurPos(0);
        this.mWindVal = this.mManager.AddText(746, 22, 60, 40);
        this.mWindVal.setTextSize(0, 30.0f);
        this.mWindVal.setTextColor(-1);
        this.mWindVal.setText("0");
        this.mWindVal.setGravity(19);
        ParamButton decreaseWind = this.mManager.AddButton(190, 0, 98, 70);
        decreaseWind.setStateDrawable(R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, R.drawable.can_rh7_fss_dn);
        decreaseWind.setTag(12);
        decreaseWind.setOnClickListener(this);
        ParamButton increaseWind = this.mManager.AddButton(660, 0);
        increaseWind.setStateDrawable(R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, R.drawable.can_rh7_fsb_dn);
        increaseWind.setTag(13);
        increaseWind.setOnClickListener(this);
        this.mManager.AddImageEx(12, 76, 999, 1, R.drawable.conditioning_line_up);
        this.mManager.AddImageEx(481, 94, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, Can.CAN_CHRYSLER_ONE_HC, R.drawable.can_golf_bg_new);
        this.mManager.AddImageEx(411, 104, 2, 267, R.drawable.can_golf_sline);
        ParamButton RearTempDecrease = this.mManager.AddButton(30, 350, 90, 50);
        RearTempDecrease.setStateUpDn(R.drawable.can_ac_blue, R.drawable.can_ac_blue);
        RearTempDecrease.setTag(24);
        RearTempDecrease.setOnClickListener(this);
        ParamButton RearTempIncrease = this.mManager.AddButton(106, 350, 90, 50);
        RearTempIncrease.setStateUpDn(R.drawable.can_ac_red, R.drawable.can_ac_red);
        RearTempIncrease.setTag(23);
        RearTempIncrease.setOnClickListener(this);
        this.mRearTemp = this.mManager.AddText(5, 348, 170, 50);
        this.mRearTemp.setTextSize(0, 40.0f);
        this.mRearTemp.setTextColor(-1);
        this.mRearTemp.setGravity(21);
        this.mRearTemp.setText("13℃");
        this.mBtnLtHot = this.mManager.AddButton(30, Can.CAN_JAC_REFINE_OD);
        this.mBtnLtHot.setStateDrawable(R.drawable.conditioning_leftseat_up, R.drawable.conditioning_leftseat_dn, R.drawable.conditioning_leftseat_dn);
        this.mBtnLtHot.setTag(1);
        this.mBtnLtHot.setOnClickListener(this);
        this.mBtnRtHot = this.mManager.AddButton(217, Can.CAN_JAC_REFINE_OD);
        this.mBtnRtHot.setStateDrawable(R.drawable.conditioning_rightseat_up, R.drawable.conditioning_rightseat_dn, R.drawable.conditioning_rightseat_dn);
        this.mBtnRtHot.setTag(2);
        this.mBtnRtHot.setOnClickListener(this);
        this.mBtnSwHot = this.mManager.AddButton(Can.CAN_TEANA_OLD_DJ, 348, 84, 47);
        this.mBtnSwHot.setStateDrawable(R.drawable.can_golf_wheel_hot_up, R.drawable.can_golf_wheel_hot_dn, R.drawable.can_golf_wheel_hot_dn);
        this.mBtnSwHot.setTag(3);
        this.mBtnSwHot.setOnClickListener(this);
        this.mLtFire = this.mManager.AddImageEx(102, 279, 41, 44, R.drawable.conditioning_direction);
        this.mRtFire = this.mManager.AddImageEx(Can.CAN_RENAUL_KOLEOS_XFY, 279, 41, 44, R.drawable.conditioning_direction);
        for (int i = 0; i < 3; i++) {
            this.mLtHot[i] = this.mManager.AddImage((i * 39) + 62, KeyDef.RKEY_POWER_ON, 36, 16);
            this.mLtHot[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
            this.mRtHot[i] = this.mManager.AddImage((i * 39) + Can.CAN_ZH_H530, KeyDef.RKEY_POWER_ON, 36, 16);
            this.mRtHot[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
        }
        this.mWindLtUp = this.mManager.AddImage(CanCameraUI.BTN_GEELY_YJX6_ESC, Can.CAN_BENC_ZMYT);
        this.mWindLtUp.setStateDrawable(R.drawable.can_golf_cold02, R.drawable.can_golf_hot02);
        this.mWindLtPxLt = this.mManager.AddImage(CanCameraUI.BTN_YG9_XBS_MODE2, Can.CAN_SGMW_WC);
        this.mWindLtPxLt.setStateDrawable(R.drawable.can_golf_cold07, R.drawable.can_golf_hot07);
        this.mWindLtPxRt = this.mManager.AddImage(CanCameraUI.BTN_LANDWIND_2D_RIGHT, 179);
        this.mWindLtPxRt.setStateDrawable(R.drawable.can_golf_cold05, R.drawable.can_golf_hot05);
        this.mWindLtDn = this.mManager.AddImage(669, 276);
        this.mWindLtDn.setStateDrawable(R.drawable.can_golf_cold08, R.drawable.can_golf_hot08);
        this.mWindRtUp = this.mManager.AddImage(737, 93);
        this.mWindRtUp.setStateDrawable(R.drawable.can_golf_cold01, R.drawable.can_golf_hot01);
        this.mWindRtPxLt = this.mManager.AddImage(673, 165);
        this.mWindRtPxLt.setStateDrawable(R.drawable.can_golf_cold04, R.drawable.can_golf_hot04);
        this.mWindRtPxRt = this.mManager.AddImage(KeyDef.SKEY_SEEKDN_4, 125);
        this.mWindRtPxRt.setStateDrawable(R.drawable.can_golf_cold03, R.drawable.can_golf_hot03);
        this.mWindRtDn = this.mManager.AddImage(KeyDef.SKEY_SEEKUP_3, 201);
        this.mWindRtDn.setStateDrawable(R.drawable.can_golf_cold06, R.drawable.can_golf_hot06);
        this.mRearLight = this.mManager.AddImage(436, KeyDef.RKEY_res1);
        this.mRearLight.setStateDrawable(R.drawable.conditioning_heat_up, R.drawable.conditioning_heat_dn);
        this.mRearLight.Show(false);
        this.mDual = this.mManager.AddImage(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST1, KeyDef.RKEY_res1);
        this.mDual.setStateDrawable(R.drawable.conditioning_dual_up, R.drawable.conditioning_dual_dn);
        this.mAc = this.mManager.AddImage(CanCameraUI.BTN_LANDWIND_2D_RIGHT, KeyDef.RKEY_res1);
        this.mAc.setStateDrawable(R.drawable.conditioning_ac_up, R.drawable.conditioning_ac_dn);
        this.mAuto = this.mManager.AddImage(748, KeyDef.RKEY_res1);
        this.mAuto.setStateDrawable(R.drawable.conditioning_auto_up, R.drawable.conditioning_auto01_dn);
        this.mMaxFront = this.mManager.AddImage(852, KeyDef.RKEY_res1);
        this.mMaxFront.setStateDrawable(R.drawable.conditioning_max_up, R.drawable.conditioning_max_dn);
        this.mManager.AddImage(0, 428, 1024, 127).setBackgroundResource(R.drawable.can_golf_bel_bg);
        this.mBtnOff = this.mManager.AddButton(4, 437, 121, 100);
        this.mBtnOff.setStateDrawable(R.drawable.can_golf_bel_off_up, R.drawable.can_golf_bel_off_dn, R.drawable.can_golf_bel_off_dn);
        this.mBtnOff.setTag(5);
        this.mBtnOff.setOnClickListener(this);
        this.mBtnSync = this.mManager.AddButton(132, 437, 121, 100);
        this.mBtnSync.setStateDrawable(R.drawable.can_golf_bel_sync_up, R.drawable.can_golf_bel_sync_dn, R.drawable.can_golf_bel_sync_dn);
        this.mBtnSync.setTag(6);
        this.mBtnSync.setOnClickListener(this);
        this.mBtnRear = this.mManager.AddButton(259, 437, 121, 100);
        this.mBtnRear.setStateDrawable(R.drawable.can_golf_bel_rear_up, R.drawable.can_golf_bel_rear_dn, R.drawable.can_golf_bel_rear_dn);
        this.mBtnRear.setTag(7);
        this.mBtnRear.setOnClickListener(this);
        this.mBtnCleanAir = this.mManager.AddButton(387, 437, 121, 100);
        this.mBtnCleanAir.setStateDrawable(R.drawable.can_golf_bel_cleanair_up, R.drawable.can_golf_bel_cleanair_dn, R.drawable.can_golf_bel_cleanair_dn);
        this.mBtnCleanAir.setTag(4);
        this.mBtnCleanAir.setOnClickListener(this);
        this.mBtnFrontAir = this.mManager.AddButton(515, 437, 121, 100);
        this.mBtnFrontAir.setStateDrawable(R.drawable.can_golf_bel_frontair_up, R.drawable.can_golf_bel_frontair_dn, R.drawable.can_golf_bel_frontair_dn);
        this.mBtnFrontAir.setTag(8);
        this.mBtnFrontAir.setOnClickListener(this);
        this.mBtnRearAir = this.mManager.AddButton(CanCameraUI.BTN_LANDWIND_2D_LEFT, 437, 121, 100);
        this.mBtnRearAir.setStateDrawable(R.drawable.can_golf_bel_rearair_up, R.drawable.can_golf_bel_rearair_dn, R.drawable.can_golf_bel_rearair_dn);
        this.mBtnRearAir.setTag(9);
        this.mBtnRearAir.setOnClickListener(this);
        this.mBtnWind = this.mManager.AddButton(KeyDef.SKEY_POWEWR_4, 437, 121, 100);
        this.mBtnWind.setStateDrawable(R.drawable.can_golf_bel_fan_up, R.drawable.can_golf_bel_fan_dn, R.drawable.can_golf_bel_fan_dn);
        this.mBtnWind.setTag(10);
        this.mBtnWind.setOnClickListener(this);
        this.mBtnSettings = this.mManager.AddButton(899, 437, 121, 100);
        this.mBtnSettings.setStateDrawable(R.drawable.can_golf_bel_set_up, R.drawable.can_golf_bel_set_dn, R.drawable.can_golf_bel_set_dn);
        this.mBtnSettings.setTag(11);
        this.mBtnSettings.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        CanDataInfo.CAN_ACInfo mACInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mRearLight.SetSel(mACInfo.fgRearLight);
        this.mDual.SetSel(mACInfo.fgDual);
        this.mAc.SetSel(mACInfo.fgAC);
        this.mAuto.SetSel(mACInfo.nAutoLight);
        this.mMaxFront.SetSel(mACInfo.fgMaxFornt);
        int ltHot = mACInfo.nLtChairHot & 3;
        int rtHot = mACInfo.nRtChairHot & 3;
        for (int i = 0; i < ltHot; i++) {
            this.mLtHot[i].setSelected(true);
        }
        for (int i2 = ltHot; i2 < 3; i2++) {
            this.mLtHot[i2].setSelected(false);
        }
        for (int i3 = 0; i3 < rtHot; i3++) {
            this.mRtHot[i3].setSelected(true);
        }
        for (int i4 = rtHot; i4 < 3; i4++) {
            this.mRtHot[i4].setSelected(false);
        }
        this.mLtFire.Show(ltHot);
        this.mRtFire.Show(rtHot);
        this.mWindVal.setText(Integer.toString(mACInfo.nWindValue));
        this.mWindProgress.SetMinMax(0, mACInfo.nWindMax);
        this.mWindProgress.SetCurPos(mACInfo.nWindValue);
        boolean bLtHot = false;
        boolean bRtHot = false;
        if (mACInfo.nLeftTemp > 13) {
            bLtHot = true;
        }
        if (mACInfo.nRightTemp > 13) {
            bRtHot = true;
        }
        this.mWindLtUp.Show(mACInfo.fgUpWind);
        this.mWindLtUp.setSelected(bLtHot);
        this.mWindRtUp.Show(mACInfo.fgUpWind);
        this.mWindRtUp.setSelected(bRtHot);
        this.mWindLtPxLt.Show(mACInfo.fgParallelWind);
        this.mWindLtPxLt.setSelected(bLtHot);
        this.mWindRtPxLt.Show(mACInfo.fgParallelWind);
        this.mWindRtPxLt.setSelected(bRtHot);
        this.mWindLtPxRt.Show(mACInfo.fgParallelWind);
        this.mWindLtPxRt.setSelected(bLtHot);
        this.mWindRtPxRt.Show(mACInfo.fgParallelWind);
        this.mWindRtPxRt.setSelected(bRtHot);
        this.mWindLtDn.Show(mACInfo.fgDownWind);
        this.mWindLtDn.setSelected(bLtHot);
        this.mWindRtDn.Show(mACInfo.fgDownWind);
        this.mWindRtDn.setSelected(bRtHot);
        try {
            this.mLtTemp.setText(mACInfo.szLtTemp);
            this.mRtTemp.setText(mACInfo.szRtTemp);
        } catch (Exception e) {
            Log.e(TAG, "set Temp text Exception!");
        }
        CanJni.GolfGetACData(this.mAcData);
        try {
            this.mRearTemp.setText(mACInfo.szRearTemp);
        } catch (Exception e2) {
        }
        this.mBtnSwHot.SetSel(this.mAcData.SwHot);
        this.mBtnCleanAir.SetSel(this.mAcData.ClearAir);
        this.mBtnRear.SetSel(mACInfo.fgRearLock);
        if (this.window != null && this.window.isShowing()) {
            if (this.windowId == 8) {
                this.acStatus.SetSel(mACInfo.fgAC);
                this.autoStatus.SetSel(mACInfo.nAutoLight);
                this.maxStatus.SetSel(mACInfo.fgMaxFornt);
                this.pallaxWind.SetSel(mACInfo.fgParallelWind);
                this.downWind.SetSel(mACInfo.fgDownWind);
            } else if (this.windowId != 9) {
                for (int i5 = 0; i5 < this.arrayViews.length; i5++) {
                    if (this.windowId == 11) {
                        if (i5 == mACInfo.fgAQS) {
                            this.arrayViews[i5].setTextColor(SupportMenu.CATEGORY_MASK);
                        } else {
                            this.arrayViews[i5].setTextColor(-1);
                        }
                    } else if (this.windowId == 10) {
                        if (i5 == this.mAcData.Profile) {
                            this.arrayViews[i5].setTextColor(SupportMenu.CATEGORY_MASK);
                        } else {
                            this.arrayViews[i5].setTextColor(-1);
                        }
                    }
                }
            }
        }
    }

    private void showWindow(View anchor, int width, int height) {
        this.windowId = ((Integer) anchor.getTag()).intValue();
        this.window = new PopupWindow(width, height);
        this.window.setBackgroundDrawable(new ColorDrawable());
        this.window.setFocusable(true);
        this.window.setContentView(getContentView(width, height));
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        this.window.showAtLocation(anchor, 0, location[0] - ((this.window.getWidth() - anchor.getWidth()) / 2), (location[1] - this.window.getHeight()) - 20);
        updateACUI();
        ResetData(false);
    }

    private View getContentView(int width, int height) {
        RelativeLayout contentView = new RelativeLayout(getActivity());
        contentView.setBackgroundResource(R.drawable.can_popup_bg);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        if (this.windowId == 8 || this.windowId == 9) {
            addACViews(contentView);
        } else if (this.windowId == 10) {
            addArrayView(contentView, this.mAutoWind);
        } else if (this.windowId == 11) {
            addArrayView(contentView, this.mAQSArray);
        }
        return contentView;
    }

    private void addACViews(RelativeLayout contentView) {
        RelativeLayoutManager manager = new RelativeLayoutManager(contentView);
        int y = 0;
        if (this.windowId == 9) {
            this.windProgress = new MyProgressBar(getActivity(), R.drawable.conditioning_progress_bar_up, R.drawable.conditioning_progress_bar_dn);
            manager.AddViewWrapContent(this.windProgress, 80, 40);
            this.windProgress.SetMinMax(0, 7);
            this.windProgress.SetCurPos(0);
            this.windVal = manager.AddText(CanCameraUI.BTN_YG9_XBS_MODE1, 27, 60, 40);
            this.windVal.setTextSize(0, 30.0f);
            this.windVal.setTextColor(-1);
            this.windVal.setText("0");
            this.windVal.setGravity(19);
            ParamButton decreaseWind = manager.AddButton(0, 0);
            decreaseWind.setStateDrawable(R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, R.drawable.can_rh7_fss_dn);
            decreaseWind.setTag(12);
            decreaseWind.setOnClickListener(this);
            ParamButton increaseWind = manager.AddButton(CanCameraUI.BTN_GEELY_YJX6_MODE1, 5);
            increaseWind.setStateDrawable(R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, R.drawable.can_rh7_fsb_dn);
            increaseWind.setTag(13);
            increaseWind.setOnClickListener(this);
            y = 80;
        } else if (this.windowId == 8) {
            y = 25;
            this.acStatus = manager.AddImage(40, 25);
            this.acStatus.setStateDrawable(R.drawable.conditioning_ac_up, R.drawable.conditioning_ac_dn, R.drawable.conditioning_ac_dn);
            this.acStatus.setTag(14);
            this.acStatus.setOnClickListener(this);
            this.maxStatus = manager.AddImage(258, 25);
            this.maxStatus.setStateDrawable(R.drawable.conditioning_max_up, R.drawable.conditioning_max_dn, R.drawable.conditioning_max_dn);
            this.maxStatus.setTag(16);
            this.maxStatus.setOnClickListener(this);
        }
        this.autoStatus = manager.AddImage(144, y);
        this.autoStatus.setStateDrawable(R.drawable.conditioning_auto_up, R.drawable.conditioning_auto01_dn, R.drawable.conditioning_auto01_dn);
        this.autoStatus.setTag(15);
        this.autoStatus.setOnClickListener(this);
        this.pallaxWind = manager.AddImage(352, y - 5);
        this.pallaxWind.setStateDrawable(R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, R.drawable.can_rh7_icon01_dn);
        this.pallaxWind.setTag(17);
        this.pallaxWind.setOnClickListener(this);
        this.downWind = manager.AddImage(456, y - 5);
        this.downWind.setStateDrawable(R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, R.drawable.can_rh7_icon03_dn);
        this.downWind.setTag(18);
        this.downWind.setOnClickListener(this);
    }

    private void addArrayView(RelativeLayout contentView, String[] array) {
        RelativeLayoutManager manager = new RelativeLayoutManager(contentView);
        this.arrayViews = new TextView[array.length];
        ImageView[] lineViews = new ImageView[(array.length - 1)];
        for (int i = 0; i < array.length; i++) {
            this.arrayViews[i] = manager.AddText(0, (i * 70) + 0, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 70);
            this.arrayViews[i].setTextColor(-1);
            this.arrayViews[i].setGravity(17);
            this.arrayViews[i].setTextSize(0, 30.0f);
            this.arrayViews[i].setText(array[i]);
            this.arrayViews[i].setTag(Integer.valueOf(i));
            this.arrayViews[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CanJni.GolfSendCmd(CanGolfRzcTeramonACView.this.windowId == 10 ? 177 : 176, ((Integer) v.getTag()).intValue());
                    CanGolfRzcTeramonACView.this.window.dismiss();
                    CanFunc.mLastACTick = CanGolfRzcTeramonACView.this.GetTickCount();
                }
            });
            if (i < array.length - 1) {
                lineViews[i] = manager.AddImage(0, (i + 1) * 70, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 1);
                lineViews[i].setBackgroundColor(Color.parseColor("#ccc1c1c1"));
            }
        }
    }
}
