package com.ts.can.vw.golf.wc;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.MyProgressBar;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanGolfWcACActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    private static final int BTN_AC = 15;
    private static final int BTN_AUTO = 16;
    private static final int BTN_CLEAR_AIR = 5;
    private static final int BTN_DECREASE_WIND = 13;
    private static final int BTN_DOWN_WIND = 18;
    private static final int BTN_INCREASE_WIND = 14;
    private static final int BTN_LEFT_CHAIR_WIND = 28;
    private static final int BTN_LEFT_TEMP_ADD = 21;
    private static final int BTN_LEFT_TEMP_SUB = 22;
    private static final int BTN_LOOPER = 27;
    private static final int BTN_LT_HOT = 1;
    private static final int BTN_PALLAX_DOWN_WIND = 19;
    private static final int BTN_PALLAX_WIND = 17;
    private static final int BTN_REAR_TEMP_ADD = 25;
    private static final int BTN_REAR_TEMP_SUB = 26;
    private static final int BTN_RIGHT_CHAIR_WIND = 29;
    private static final int BTN_RIGHT_TEMP_ADD = 23;
    private static final int BTN_RIGHT_TEMP_SUB = 24;
    private static final int BTN_RT_HOT = 2;
    private static final int BTN_SW_HOT = 3;
    private static final int BTN_SW_SYNC = 4;
    private static final int BTN_UP_WIND = 20;
    private static final int ITEM_AUTO_WIND = 11;
    private static final int ITEM_FRONT_AIR = 9;
    private static final int ITEM_OFF = 6;
    private static final int ITEM_REAR_AIR = 10;
    private static final int ITEM_REAR_LOCK = 8;
    private static final int ITEM_SETTINGS = 12;
    private static final int ITEM_SYNC = 7;
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private static int[] mLtChairWindIds = {R.drawable.cadg_ac_lfan_00, R.drawable.cadg_ac_lfan_01, R.drawable.cadg_ac_lfan_02, R.drawable.cadg_ac_lfan_03};
    private static int[] mRearLtHotIds = {R.drawable.cadg_ac_lchair_up, R.drawable.cadg_ac_lchair01_dn, R.drawable.cadg_ac_lchair02_dn, R.drawable.cadg_ac_lchair_dn};
    private static int[] mRearRtHotIds = {R.drawable.cadg_ac_rchair_up, R.drawable.cadg_ac_rchair01_dn, R.drawable.cadg_ac_rchair02_dn, R.drawable.cadg_ac_rchair_dn};
    private static int[] mRtChairWindIds = {R.drawable.cadg_ac_rfan_00, R.drawable.cadg_ac_rfan_01, R.drawable.cadg_ac_rfan_02, R.drawable.cadg_ac_rfan_03};
    protected static boolean mfgJump;
    private CanDataInfo.GolfACData mAcData = new CanDataInfo.GolfACData();
    protected boolean mAutoFinish = false;
    private String[] mAutoWindArrays = new String[3];
    private ParamButton mBtnAc;
    private ParamButton mBtnAuto;
    private ParamButton mBtnCleanAir;
    private ParamButton mBtnDownWind;
    private ParamButton mBtnLooper;
    private ParamButton mBtnLtChairWind;
    private ParamButton mBtnOff;
    private ParamButton mBtnPallaxDownWind;
    private ParamButton mBtnPallaxWind;
    private ParamButton mBtnRearLock;
    private ParamButton mBtnRearLtHot;
    private ParamButton mBtnRearOff;
    private ParamButton mBtnRearRtHot;
    private ParamButton mBtnRtChairWind;
    private ParamButton mBtnSwHot;
    private ParamButton mBtnSwSync;
    private ParamButton mBtnSync;
    private ParamButton mBtnUpWind;
    private CustomImgView mIvAc;
    private CustomImgView mIvAuto;
    private CustomImgView mIvFrontWind;
    private CustomImgView mIvLooper;
    private CustomImgView mIvLtFire;
    private CustomImgView[] mIvLtHots = new CustomImgView[3];
    private CustomImgView mIvMaxAC;
    private CustomImgView mIvRtFire;
    private CustomImgView[] mIvRtHots = new CustomImgView[3];
    private RelativeLayoutManager mManager;
    private CanDataInfo.GolfRearAir mRearAir = new CanDataInfo.GolfRearAir();
    private MyProgressBar mRearWindProgress;
    private String[] mSetArrays;
    private TextView mTvClearAirProgress;
    private TextView mTvLtTemp;
    private TextView mTvRearTemp;
    private TextView mTvRearWindVal;
    private TextView mTvRtTemp;
    private TextView[] mTvViews;
    private TextView mTvWindVal;
    private CustomImgView mWindLtDn;
    private CustomImgView mWindLtPxLt;
    private CustomImgView mWindLtPxRt;
    private CustomImgView mWindLtUp;
    private MyProgressBar mWindProgress;
    private CustomImgView mWindRtDn;
    private CustomImgView mWindRtPxLt;
    private CustomImgView mWindRtPxRt;
    private CustomImgView mWindRtUp;
    /* access modifiers changed from: private */
    public PopupWindow window;
    /* access modifiers changed from: private */
    public int windowId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
        lp.width = 1024;
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        lp.gravity = 17;
        this.mManager.GetLayout().setLayoutParams(lp);
        InitUI();
        this.mManager.GetLayout().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
        this.mManager.GetLayout().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
        Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
    }

    private void InitUI() {
        this.mSetArrays = new String[]{"AUTO", "ACmax", "max FRONT", getString(R.string.can_soudong)};
        String str = String.valueOf(getString(R.string.can_ac_zdfl)) + " : ";
        this.mAutoWindArrays[0] = String.valueOf(str) + getString(R.string.can_ac_low);
        this.mAutoWindArrays[1] = String.valueOf(str) + getString(R.string.can_ac_mid);
        this.mAutoWindArrays[2] = String.valueOf(str) + getString(R.string.can_ac_high);
        mfgJump = CanFunc.IsCanActivityJumped(this);
        AddButton(this.mManager, 30, 20, 90, 50, R.drawable.can_ac_blue, R.drawable.can_ac_blue, 22);
        AddButton(this.mManager, 100, 20, 90, 50, R.drawable.can_ac_red, R.drawable.can_ac_red, 21);
        AddButton(this.mManager, KeyDef.SKEY_NAVI_2, 20, 90, 50, R.drawable.can_ac_blue, R.drawable.can_ac_blue, 24);
        AddButton(this.mManager, 906, 20, 90, 50, R.drawable.can_ac_red, R.drawable.can_ac_red, 23);
        AddButton(this.mManager, 30, 350, 90, 50, R.drawable.can_ac_blue, R.drawable.can_ac_blue, 26);
        AddButton(this.mManager, 106, 350, 90, 50, R.drawable.can_ac_red, R.drawable.can_ac_red, 25);
        this.mTvLtTemp = AddText(this.mManager, 40, 16, Can.CAN_BENC_ZMYT, 50);
        this.mTvRtTemp = AddText(this.mManager, KeyDef.SKEY_SPEECH_3, 16, Can.CAN_BENC_ZMYT, 50);
        this.mTvRearTemp = AddText(this.mManager, 43, 350, Can.CAN_BENC_ZMYT, 50);
        this.mWindProgress = AddWindProgress(this.mManager, 267, 32);
        this.mTvWindVal = AddText(this.mManager, 746, 16, 40, 40);
        this.mTvWindVal.setTextSize(0, 30.0f);
        AddButton(this.mManager, 190, 0, 98, 70, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 13);
        AddButton(this.mManager, 660, 0, 98, 70, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 14);
        this.mManager.AddImageEx(12, 76, 999, 1, R.drawable.conditioning_line_up);
        this.mManager.AddImageEx(481, 94, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, Can.CAN_CHRYSLER_ONE_HC, R.drawable.can_golf_bg_new);
        this.mManager.AddImageEx(411, 104, 2, 267, R.drawable.can_golf_sline);
        this.mManager.AddImage(0, 428, 1024, 127).setBackgroundResource(R.drawable.can_golf_bel_bg);
        this.mManager.AddImageEx(50, 90, 84, 47, R.drawable.can_golf_green_dn);
        this.mTvClearAirProgress = AddText(this.mManager, 110, 82, 80, 50);
        AddButton(this.mManager, 30, 150, Can.CAN_DFFG_S560, 138, R.drawable.conditioning_leftseat_up, R.drawable.conditioning_leftseat_dn, 1);
        AddButton(this.mManager, 217, 150, Can.CAN_DFFG_S560, 138, R.drawable.conditioning_rightseat_up, R.drawable.conditioning_rightseat_dn, 2);
        this.mBtnSwHot = AddButton(this.mManager, 220, 354, 84, 47, R.drawable.can_golf_wheel_hot_up, R.drawable.can_golf_wheel_hot_dn, 3);
        this.mBtnSwSync = AddButton(this.mManager, KeyDef.RKEY_OPEN, 354, 58, 55, R.drawable.can_golf_wheel_sync_up, R.drawable.can_golf_wheel_sync_dn, 4);
        this.mIvLtFire = this.mManager.AddImageEx(102, 279, 41, 44, R.drawable.conditioning_direction);
        this.mIvRtFire = this.mManager.AddImageEx(Can.CAN_RENAUL_KOLEOS_XFY, 279, 41, 44, R.drawable.conditioning_direction);
        for (int i = 0; i < 3; i++) {
            this.mIvLtHots[i] = this.mManager.AddImage((i * 39) + 62, KeyDef.RKEY_POWER_ON, 36, 16);
            this.mIvLtHots[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
            this.mIvRtHots[i] = this.mManager.AddImage((i * 39) + Can.CAN_ZH_H530, KeyDef.RKEY_POWER_ON, 36, 16);
            this.mIvRtHots[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
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
        this.mIvLooper = this.mManager.AddImage(466, KeyDef.RKEY_res1);
        this.mIvLooper.setStateDrawable(R.drawable.conditioning_car_dn, R.drawable.conditioning_car01_dn);
        this.mIvMaxAC = this.mManager.AddImage(CanCameraUI.BTN_CHANA_CS75_MODE1, KeyDef.RKEY_res1);
        this.mIvMaxAC.setStateDrawable(R.drawable.conditioning_acmax_up, R.drawable.conditioning_acmax_dn);
        this.mIvAc = this.mManager.AddImage(674, KeyDef.RKEY_res1);
        this.mIvAc.setStateDrawable(R.drawable.conditioning_ac_up, R.drawable.conditioning_ac_dn);
        this.mIvAuto = this.mManager.AddImage(KeyDef.SKEY_VOLUP_5, KeyDef.RKEY_res1);
        this.mIvAuto.setStateDrawable(R.drawable.conditioning_auto_up, R.drawable.conditioning_auto01_dn);
        this.mIvFrontWind = this.mManager.AddImage(882, KeyDef.RKEY_res1);
        this.mIvFrontWind.setStateDrawable(R.drawable.conditioning_wind_up, R.drawable.conditioning_wind_dn);
        this.mBtnOff = AddButton(this.mManager, 4, 437, 121, 100, R.drawable.can_golf_bel_off_up, R.drawable.can_golf_bel_off_dn, 6);
        this.mBtnSync = AddButton(this.mManager, 132, 437, 121, 100, R.drawable.can_golf_bel_sync_up, R.drawable.can_golf_bel_sync_dn, 7);
        this.mBtnRearLock = AddButton(this.mManager, 259, 437, 121, 100, R.drawable.can_golf_bel_rear_up, R.drawable.can_golf_bel_rear_dn, 8);
        this.mBtnCleanAir = AddButton(this.mManager, 387, 437, 121, 100, R.drawable.can_golf_bel_cleanair_up, R.drawable.can_golf_bel_cleanair_dn, 5);
        AddButton(this.mManager, 515, 437, 121, 100, R.drawable.can_golf_bel_frontair_up, R.drawable.can_golf_bel_frontair_dn, 9);
        AddButton(this.mManager, CanCameraUI.BTN_LANDWIND_2D_LEFT, 437, 121, 100, R.drawable.can_golf_bel_rearair_up, R.drawable.can_golf_bel_rearair_dn, 10);
        AddButton(this.mManager, KeyDef.SKEY_POWEWR_4, 437, 121, 100, R.drawable.can_golf_bel_fan_up, R.drawable.can_golf_bel_fan_dn, 11);
        AddButton(this.mManager, 899, 437, 121, 100, R.drawable.can_golf_bel_set_up, R.drawable.can_golf_bel_set_dn, 12);
    }

    private TextView AddText(RelativeLayoutManager manager, int x, int y, int w, int h) {
        TextView tv = manager.AddText(x, y, w, h);
        tv.setTextSize(0, 40.0f);
        tv.setTextColor(-1);
        tv.setGravity(17);
        return tv;
    }

    private ParamButton AddButton(RelativeLayoutManager manager, int x, int y, int w, int h, int upId, int dnId, int id) {
        ParamButton button = manager.AddButton(x, y, w, h);
        button.setStateDrawable(upId, dnId, dnId);
        button.setTag(Integer.valueOf(id));
        button.setOnClickListener(this);
        return button;
    }

    private MyProgressBar AddWindProgress(RelativeLayoutManager manager, int x, int y) {
        MyProgressBar pb = new MyProgressBar(this, R.drawable.conditioning_progress_bar_up, R.drawable.conditioning_progress_bar_dn);
        pb.SetMinMax(0, 7);
        pb.SetCurPos(0);
        manager.AddViewWrapContent(pb, x, y);
        return pb;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        CanJni.GolfWcAcSet(Can.CAN_VOLKS_XP, 0);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Can.updateAC();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        if (!CanFunc.mfgShowAC) {
            if (!this.mAutoFinish) {
                finish();
                Log.d(CanBaseActivity.TAG, "-----onPause finish-----");
            }
            mfgJump = false;
            this.mAutoFinish = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        updateACUI();
        MainTask.GetInstance().SetUserCallBack(this);
        CanFunc.mfgShowAC = false;
        ResetRearAirData(false);
    }

    private void ResetRearAirData(boolean check) {
        CanJni.TeramontGetRearAir(this.mRearAir);
        if (!i2b(this.mRearAir.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mRearAir.Update)) {
            this.mRearAir.Update = 0;
            if (this.window != null && this.window.isShowing() && this.windowId == 10) {
                this.mBtnRearOff.SetSel(Neg(this.mRearAir.bPowerFlg));
                this.mRearWindProgress.SetCurPos(this.mRearAir.nWindValue);
                this.mTvRearWindVal.setText(String.valueOf(this.mRearAir.nWindValue));
                this.mBtnAuto.SetSel(this.mRearAir.nAutoLight);
                int hot = this.mRearAir.nLtChairHot;
                if (hot >= 0 && hot < 4) {
                    this.mBtnRearLtHot.setStateUpDn(mRearLtHotIds[hot], mRearLtHotIds[hot]);
                }
                int hot2 = this.mRearAir.nRtChairHot;
                if (hot2 >= 0 && hot2 < 4) {
                    this.mBtnRearRtHot.setStateUpDn(mRearRtHotIds[hot2], mRearRtHotIds[hot2]);
                }
                this.mBtnPallaxWind.setSelected(false);
                this.mBtnDownWind.setSelected(false);
                this.mBtnPallaxDownWind.setSelected(false);
                if (i2b(this.mRearAir.bParallelWindFlg) && i2b(this.mRearAir.bDownWindFlg)) {
                    this.mBtnPallaxDownWind.setSelected(true);
                } else if (i2b(this.mRearAir.bParallelWindFlg)) {
                    this.mBtnPallaxWind.setSelected(true);
                } else if (i2b(this.mRearAir.bDownWindFlg)) {
                    this.mBtnDownWind.setSelected(true);
                }
            }
        }
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            updateACUI();
        }
        ResetRearAirData(true);
        if (mfgJump && GetTickCount() > CanFunc.mLastACTick + 3000) {
            finish();
            Log.d(CanBaseActivity.TAG, "UserAll Exit Ac");
            this.mAutoFinish = true;
        }
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        CanDataInfo.CAN_ACInfo mACInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mBtnOff.SetSel(Neg(mACInfo.PWR));
        this.mIvMaxAC.SetSel(mACInfo.fgACMax);
        this.mIvAuto.SetSel(mACInfo.nAutoLight);
        this.mBtnSync.SetSel(mACInfo.fgDual);
        this.mIvAc.SetSel(mACInfo.fgAC);
        this.mIvLooper.SetSel(mACInfo.fgInnerLoop);
        this.mIvFrontWind.SetSel(mACInfo.fgDFBL);
        this.mBtnRearLock.SetSel(mACInfo.fgRearLock);
        this.mBtnSwHot.SetSel(this.mAcData.SwHot);
        this.mBtnSwSync.SetSel(this.mAcData.ChairSwSync);
        this.mBtnCleanAir.SetSel(this.mAcData.ClearAir);
        if (this.mAcData.ClearAirPro < 0 || this.mAcData.ClearAirPro > 10) {
            this.mTvClearAirProgress.setText(TXZResourceManager.STYLE_DEFAULT);
        } else {
            this.mTvClearAirProgress.setText(String.valueOf(this.mAcData.ClearAirPro));
        }
        int ltHot = mACInfo.nLtChairHot & 3;
        int rtHot = mACInfo.nRtChairHot & 3;
        for (int i = 0; i < ltHot; i++) {
            this.mIvLtHots[i].setSelected(true);
        }
        for (int i2 = ltHot; i2 < 3; i2++) {
            this.mIvLtHots[i2].setSelected(false);
        }
        for (int i3 = 0; i3 < rtHot; i3++) {
            this.mIvRtHots[i3].setSelected(true);
        }
        for (int i4 = rtHot; i4 < 3; i4++) {
            this.mIvRtHots[i4].setSelected(false);
        }
        this.mIvLtFire.Show(ltHot);
        this.mIvRtFire.Show(rtHot);
        this.mTvWindVal.setText(Integer.toString(mACInfo.nWindValue));
        this.mWindProgress.SetMinMax(0, mACInfo.nWindMax);
        this.mWindProgress.SetCurPos(mACInfo.nWindValue);
        this.mWindLtUp.Show(mACInfo.fgUpWind);
        this.mWindRtUp.Show(mACInfo.fgUpWind);
        this.mWindLtPxLt.Show(mACInfo.fgParallelWind);
        this.mWindRtPxLt.Show(mACInfo.fgParallelWind);
        this.mWindLtPxRt.Show(mACInfo.fgParallelWind);
        this.mWindRtPxRt.Show(mACInfo.fgParallelWind);
        this.mWindLtDn.Show(mACInfo.fgDownWind);
        this.mWindRtDn.Show(mACInfo.fgDownWind);
        try {
            this.mTvLtTemp.setText(mACInfo.szLtTemp);
            this.mTvRtTemp.setText(mACInfo.szRtTemp);
            this.mTvRearTemp.setText(mACInfo.szRearTemp);
        } catch (Exception e) {
            Log.e(CanBaseActivity.TAG, "set Temp text Exception!");
        }
        CanJni.GolfGetACData(this.mAcData);
        if (this.window != null && this.window.isShowing()) {
            if (this.windowId == 9) {
                this.mBtnAc.SetSel(mACInfo.fgAC);
                this.mBtnLooper.SetSel(mACInfo.fgInnerLoop);
                this.mBtnUpWind.SetSel(mACInfo.fgUpWind);
                this.mBtnPallaxWind.SetSel(mACInfo.fgParallelWind);
                this.mBtnDownWind.SetSel(mACInfo.fgDownWind);
                int chairWind = mACInfo.nLtChairWind;
                if (chairWind >= 0 && chairWind < 4) {
                    this.mBtnLtChairWind.setStateUpDn(mLtChairWindIds[chairWind], mLtChairWindIds[chairWind]);
                }
                int chairWind2 = mACInfo.nRtChairWind;
                if (chairWind2 >= 0 && chairWind2 < 4) {
                    this.mBtnRtChairWind.setStateUpDn(mRtChairWindIds[chairWind2], mRtChairWindIds[chairWind2]);
                }
            } else if (this.windowId == 11) {
                updateTvView(this.mAcData.Profile);
            }
        }
    }

    private void updateTvView(int value) {
        for (int i = 0; i < this.mTvViews.length; i++) {
            if (i == value) {
                this.mTvViews[i].setTextColor(SupportMenu.CATEGORY_MASK);
            } else {
                this.mTvViews[i].setTextColor(-1);
            }
        }
    }

    public void onClick(View v) {
        int i = 0;
        int i2 = 7;
        CanDataInfo.CAN_ACInfo mACInfo = Can.mACInfo;
        CanFunc.mLastACTick = GetTickCount();
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                if (this.window == null || !this.window.isShowing()) {
                    CanJni.GolfWcAcSet(33, checkChairHotOrWind(mACInfo.nLtChairHot));
                    return;
                } else {
                    CanJni.GolfWcAcSet(43, checkChairHotOrWind(this.mRearAir.nLtChairHot));
                    return;
                }
            case 2:
                if (this.window == null || !this.window.isShowing()) {
                    CanJni.GolfWcAcSet(34, checkChairHotOrWind(mACInfo.nRtChairHot));
                    return;
                } else {
                    CanJni.GolfWcAcSet(44, checkChairHotOrWind(this.mRearAir.nRtChairHot));
                    return;
                }
            case 3:
                CanJni.GolfWcAcSet(35, Neg(this.mAcData.SwHot));
                return;
            case 4:
                CanJni.GolfWcAcSet(36, Neg(this.mAcData.ChairSwSync));
                return;
            case 5:
                CanJni.GolfWcAcSet(32, Neg(this.mAcData.ClearAir));
                return;
            case 6:
                if (this.window == null || !this.window.isShowing()) {
                    CanJni.GolfWcAcSet(2, Neg(mACInfo.PWR));
                    return;
                } else {
                    CanJni.GolfWcAcSet(39, Neg(this.mRearAir.bPowerFlg));
                    return;
                }
            case 7:
                CanJni.GolfWcAcSet(17, Neg(mACInfo.fgDual));
                return;
            case 8:
                CanJni.GolfWcAcSet(18, Neg(mACInfo.fgRearLock));
                return;
            case 9:
                showWindow(v, 621, 120);
                return;
            case 10:
                showWindow(v, 621, 190);
                return;
            case 11:
                showWindow(v, 360, this.mAutoWindArrays.length * 80);
                return;
            case 12:
                showWindow(v, 200, this.mSetArrays.length * 80);
                return;
            case 13:
                if (this.window == null || !this.window.isShowing()) {
                    CanJni.GolfWcAcSet(23, mACInfo.nWindValue + -1 < 0 ? 0 : mACInfo.nWindValue - 1);
                    return;
                }
                if (this.mRearAir.nWindValue - 1 >= 0) {
                    i = this.mRearAir.nWindValue - 1;
                }
                CanJni.GolfWcAcSet(41, i);
                return;
            case 14:
                if (this.window == null || !this.window.isShowing()) {
                    CanJni.GolfWcAcSet(23, mACInfo.nWindValue + 1 > 7 ? 7 : mACInfo.nWindValue + 1);
                    return;
                }
                if (this.mRearAir.nWindValue + 1 <= 7) {
                    i2 = this.mRearAir.nWindValue + 1;
                }
                CanJni.GolfWcAcSet(41, i2);
                return;
            case 15:
                CanJni.GolfWcAcSet(15, Neg(mACInfo.fgAC));
                return;
            case 16:
                if (this.windowId == 10) {
                    CanJni.GolfWcAcSet(40, 1);
                    return;
                }
                return;
            case 17:
                if (this.windowId == 9) {
                    CanJni.GolfWcAcSet(24, Neg(mACInfo.fgParallelWind));
                    return;
                } else {
                    CanJni.GolfWcAcSet(42, 2);
                    return;
                }
            case 18:
                if (this.windowId == 9) {
                    CanJni.GolfWcAcSet(25, Neg(mACInfo.fgDownWind));
                    return;
                } else {
                    CanJni.GolfWcAcSet(42, 1);
                    return;
                }
            case 19:
                if (this.windowId == 10) {
                    CanJni.GolfWcAcSet(42, 3);
                    return;
                }
                return;
            case 20:
                if (this.windowId == 9) {
                    CanJni.GolfWcAcSet(26, Neg(mACInfo.fgUpWind));
                    return;
                }
                return;
            case 21:
                CanJni.GolfWcAcSet(20, checkAddTemp(mACInfo.nLeftTemp));
                return;
            case 22:
                CanJni.GolfWcAcSet(20, checkSubTemp(mACInfo.nLeftTemp));
                return;
            case 23:
                CanJni.GolfWcAcSet(21, checkAddTemp(mACInfo.nRightTemp));
                return;
            case 24:
                CanJni.GolfWcAcSet(21, checkSubTemp(mACInfo.nRightTemp));
                return;
            case 25:
                CanJni.GolfWcAcSet(22, checkAddTemp(mACInfo.nRearTemp));
                return;
            case 26:
                CanJni.GolfWcAcSet(22, checkSubTemp(mACInfo.nRearTemp));
                return;
            case 27:
                CanJni.GolfWcAcSet(19, Neg(mACInfo.fgInnerLoop));
                return;
            case 28:
                CanJni.GolfWcAcSet(37, checkChairHotOrWind(mACInfo.nLtChairWind));
                return;
            case 29:
                CanJni.GolfWcAcSet(38, checkChairHotOrWind(mACInfo.nRtChairWind));
                return;
            default:
                return;
        }
    }

    private int checkChairHotOrWind(int hot) {
        int hot2 = (hot & 3) + 1;
        if (hot2 > 3) {
            return 0;
        }
        return hot2;
    }

    private int checkSubTemp(int temp) {
        if (temp == 255) {
            return 59;
        }
        if (temp == 32 || temp == 254) {
            return Can.CAN_FLAT_RZC;
        }
        if (temp <= 32 || temp > 59) {
            return temp;
        }
        return temp - 1;
    }

    private int checkAddTemp(int temp) {
        if (temp == 59 || temp == 255) {
            return 255;
        }
        if (temp == 254) {
            return 32;
        }
        if (temp < 32 || temp >= 59) {
            return temp;
        }
        return temp + 1;
    }

    private void showWindow(View anchor, int width, int height) {
        this.windowId = ((Integer) anchor.getTag()).intValue();
        this.window = new PopupWindow(width, height);
        this.window.setBackgroundDrawable(new ColorDrawable());
        this.window.setFocusable(true);
        this.window.setContentView(getContentView(width, height));
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        int x = location[0] - ((this.window.getWidth() - anchor.getWidth()) / 2);
        int y = (location[1] - this.window.getHeight()) - 20;
        if (this.window.getWidth() + x >= mDisplayMetrics.widthPixels) {
            x = (mDisplayMetrics.widthPixels - this.window.getWidth()) - 10;
        }
        this.window.showAtLocation(anchor, 0, x, y);
        updateACUI();
        ResetRearAirData(false);
    }

    private View getContentView(int width, int height) {
        RelativeLayout contentView = new RelativeLayout(this);
        contentView.setBackgroundResource(R.drawable.can_popup_bg);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        RelativeLayoutManager manager = new RelativeLayoutManager(contentView);
        if (this.windowId == 9 || this.windowId == 10) {
            addACViews(manager);
        } else if (this.windowId == 11) {
            addArrayView(manager, this.mAutoWindArrays);
        } else if (this.windowId == 12) {
            addArrayView(manager, this.mSetArrays);
        }
        return contentView;
    }

    private void addACViews(RelativeLayoutManager manager) {
        if (this.windowId == 10) {
            this.mRearWindProgress = AddWindProgress(manager, 80, 40);
            this.mTvRearWindVal = AddText(manager, 498, 27, 40, 40);
            this.mTvRearWindVal.setTextSize(0, 30.0f);
            AddButton(manager, 0, 0, 98, 84, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 13);
            AddButton(manager, CanCameraUI.BTN_GEELY_YJX6_MODE1, 5, 98, 84, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 14);
            this.mBtnRearOff = AddButton(manager, 0, 66, 121, 100, R.drawable.can_golf_bel_off_up, R.drawable.can_golf_bel_off_dn, 6);
            this.mBtnAuto = AddButton(manager, 113, 80, 90, 60, R.drawable.cad_ac_auto_up, R.drawable.cad_ac_auto_dn, 16);
            this.mBtnRearLtHot = AddButton(manager, 190, 73, 110, 80, R.drawable.cadg_ac_lchair_up, R.drawable.cadg_ac_lchair_up, 1);
            this.mBtnRearRtHot = AddButton(manager, 260, 73, 110, 80, R.drawable.cadg_ac_rchair_up, R.drawable.cadg_ac_rchair_up, 2);
            this.mBtnPallaxWind = AddButton(manager, 350, 75, 95, 68, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 17);
            this.mBtnDownWind = AddButton(manager, 426, 75, 95, 68, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 18);
            this.mBtnPallaxDownWind = AddButton(manager, CanCameraUI.BTN_YG9_XBS_MODE1, 75, 95, 68, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 19);
        } else if (this.windowId == 9) {
            this.mBtnAc = AddButton(manager, 20, 25, 89, 65, R.drawable.conditioning_ac_up, R.drawable.conditioning_ac_dn, 15);
            this.mBtnLooper = AddButton(manager, 114, 25, 89, 65, R.drawable.conditioning_car_dn, R.drawable.conditioning_car01_dn, 27);
            this.mBtnLtChairWind = AddButton(manager, 200, 20, 110, 70, R.drawable.cadg_ac_lfan_00, R.drawable.cadg_ac_lfan_00, 28);
            this.mBtnRtChairWind = AddButton(manager, 274, 20, 110, 70, R.drawable.cadg_ac_rfan_00, R.drawable.cadg_ac_rfan_00, 29);
            this.mBtnUpWind = AddButton(manager, 360, 20, 95, 68, R.drawable.can_rh7_icon05_up, R.drawable.can_rh7_icon05_dn, 20);
            this.mBtnPallaxWind = AddButton(manager, 440, 20, 95, 68, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 17);
            this.mBtnDownWind = AddButton(manager, CanCameraUI.BTN_GEELY_YJX6_MODE1, 20, 95, 68, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 18);
        }
    }

    private void addArrayView(RelativeLayoutManager manager, String[] array) {
        this.mTvViews = new TextView[array.length];
        ImageView[] lineViews = new ImageView[(array.length - 1)];
        int itemWidth = 360;
        if (this.windowId == 12) {
            itemWidth = 200;
        }
        for (int i = 0; i < array.length; i++) {
            this.mTvViews[i] = AddText(manager, 0, (i * 70) + 0, itemWidth, 70);
            this.mTvViews[i].setTextSize(0, 27.0f);
            this.mTvViews[i].setText(array[i]);
            this.mTvViews[i].setTag(Integer.valueOf(i));
            this.mTvViews[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int cmd;
                    int tag = ((Integer) v.getTag()).intValue();
                    if (CanGolfWcACActivity.this.windowId == 11) {
                        cmd = 1;
                    } else {
                        cmd = 129;
                    }
                    CanJni.GolfWcAcSet(cmd, tag);
                    CanGolfWcACActivity.this.window.dismiss();
                    CanFunc.mLastACTick = CanGolfWcACActivity.GetTickCount();
                }
            });
            if (i < array.length - 1) {
                lineViews[i] = manager.AddImage(0, (i + 1) * 70, itemWidth, 1);
                lineViews[i].setBackgroundColor(Color.parseColor("#ccc1c1c1"));
            }
        }
    }
}
