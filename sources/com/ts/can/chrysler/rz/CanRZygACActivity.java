package com.ts.can.chrysler.rz;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.MyProgressBar;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanRZygACActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange, View.OnTouchListener {
    public static final int ITEM_AC = 2;
    public static final int ITEM_AUTO = 4;
    public static final int ITEM_FORE_WIND = 5;
    public static final int ITEM_LOOP = 3;
    public static final int ITEM_LTEMP_DEC = 8;
    public static final int ITEM_LTEMP_INC = 7;
    public static final int ITEM_LTSEAT_HOT = 15;
    public static final int ITEM_LTSEAT_WIND = 22;
    public static final int ITEM_MAX_AC = 1;
    public static final int ITEM_OFF = 17;
    public static final int ITEM_REAR_WIND = 6;
    public static final int ITEM_RTEMP_DEC = 14;
    public static final int ITEM_RTEMP_INC = 13;
    public static final int ITEM_RTSEAT_HOT = 16;
    public static final int ITEM_RTSEAT_WIND = 23;
    public static final int ITEM_SYNC = 20;
    public static final int ITEM_WD_DN = 11;
    public static final int ITEM_WD_PX = 9;
    public static final int ITEM_WD_PX_DN = 10;
    public static final int ITEM_WD_UP_DN = 12;
    public static final int ITEM_WHEEL_HOT = 21;
    public static final int ITEM_WIND_DEC = 18;
    public static final int ITEM_WIND_INC = 19;
    public static final String TAG = "CanRZygACActivity";
    protected static boolean mIsAC;
    protected static boolean mUpdateOnce = false;
    protected static boolean mfgJump;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAuto;
    private ParamButton mBtnForeWind;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnLtWind;
    private ParamButton mBtnMaxAc;
    private ParamButton mBtnOff;
    private ParamButton mBtnRearWind;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnRtWind;
    private ParamButton mBtnSync;
    private ParamButton mBtnWdDn;
    private ParamButton mBtnWdPx;
    private ParamButton mBtnWdPxDn;
    private ParamButton mBtnWdUpDn;
    private ParamButton mBtnWheelHot;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private CustomImgView mIvWindAuto;
    protected RelativeLayoutManager mManager;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private CustomTextView mTvWindVal;
    private MyProgressBar mWindProg;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        if (MainSet.GetScreenType() == 3) {
            initScreen_768x1024();
        } else {
            initCommonScreen();
        }
        this.mACInfo = Can.mACInfo;
        mfgJump = CanFunc.IsCanActivityJumped(this);
        Log.d(TAG, "jump = " + mfgJump);
        if (!mfgJump) {
            CanJni.ChrOthQuery(5, 0, 0, 0);
        }
    }

    private void initScreen_768x1024() {
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_jeep_ac_bg);
        this.mManager.AddImageEx(33, 183, 100, 50, R.drawable.can_jeep_ac_tmp);
        this.mManager.AddImageEx(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE5, 183, 100, 50, R.drawable.can_jeep_ac_tmp);
        this.mBtnMaxAc = AddBtn(1, 2, 21, 130, 52, R.drawable.can_jeep_ac_max_up, R.drawable.can_jeep_ac_max_dn);
        this.mBtnAc = AddBtn(2, 132, 21, 127, 52, R.drawable.can_jeep_ac_ac_up, R.drawable.can_jeep_ac_ac_dn);
        this.mBtnLoop = AddBtn(3, 259, 21, 126, 52, R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        this.mBtnAuto = AddBtn(4, 385, 21, 127, 52, R.drawable.can_jeep_ac_auto_up, R.drawable.can_jeep_ac_auto_dn);
        this.mBtnForeWind = AddBtn(5, CanCameraUI.BTN_YG9_XBS_MODE1, 21, 127, 52, R.drawable.can_jeep_ac_window_up, R.drawable.can_jeep_ac_window_dn);
        this.mBtnRearWind = AddBtn(6, 639, 21, 128, 52, R.drawable.can_jeep_ac_rear_up, R.drawable.can_jeep_ac_rear_dn);
        this.mBtnLtTempDec = AddBtn(8, 36, 265, 100, 50, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnLtTempInc = AddBtn(7, 36, 105, 100, 50, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvLtTemp = AddTemp(33, 183, 100, 50);
        this.mTvLtTemp.SetPixelSize(30);
        this.mBtnRtTempDec = AddBtn(14, 637, 265, 100, 50, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnRtTempInc = AddBtn(13, 637, 105, 100, 50, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvRtTemp = AddTemp(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE5, 183, 100, 50);
        this.mTvRtTemp.SetPixelSize(30);
        this.mBtnWdPx = AddBtn(9, 182, 126, 77, 77, R.drawable.can_jeep_ac_01_up, R.drawable.can_jeep_ac_01_dn);
        this.mBtnWdPxDn = AddBtn(10, 292, 126, 77, 77, R.drawable.can_jeep_ac_02_up, R.drawable.can_jeep_ac_02_dn);
        this.mBtnWdDn = AddBtn(11, CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, 126, 77, 77, R.drawable.can_jeep_ac_03_up, R.drawable.can_jeep_ac_03_dn);
        this.mBtnWdUpDn = AddBtn(12, 512, 126, 77, 77, R.drawable.can_jeep_ac_04_up, R.drawable.can_jeep_ac_04_dn);
        this.mBtnLtHot = AddBtn(15, 182, Can.CAN_SE_DX7_RZC, 94, 62, R.drawable.can_jeep_ac_lchair01_up, R.drawable.can_jeep_ac_lchair01_dn);
        this.mBtnRtHot = AddBtn(16, 496, Can.CAN_SE_DX7_RZC, 94, 62, R.drawable.can_jeep_ac_rchair01_up, R.drawable.can_jeep_ac_rchair01_dn);
        this.mBtnLtHot.setPadding(0, 0, 60, 0);
        this.mBtnLtHot.setTextSize(0, 24.0f);
        this.mBtnLtHot.setGravity(21);
        this.mBtnLtHot.setTextColor(-1);
        this.mBtnRtHot.setPadding(60, 0, 0, 0);
        this.mBtnRtHot.setTextSize(0, 24.0f);
        this.mBtnRtHot.setGravity(19);
        this.mBtnRtHot.setTextColor(-1);
        this.mBtnLtWind = AddBtn(22, 276, Can.CAN_SE_DX7_RZC, 94, 62, R.drawable.can_jeep_ac_lchair02_up, R.drawable.can_jeep_ac_lchair02_dn);
        this.mBtnRtWind = AddBtn(23, CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, Can.CAN_SE_DX7_RZC, 94, 62, R.drawable.can_jeep_ac_rchair02_up, R.drawable.can_jeep_ac_rchair02_dn);
        this.mBtnLtWind.setPadding(0, 0, 60, 0);
        this.mBtnLtWind.setTextSize(0, 24.0f);
        this.mBtnLtWind.setGravity(21);
        this.mBtnLtWind.setTextColor(-1);
        this.mBtnRtWind.setPadding(60, 0, 0, 0);
        this.mBtnRtWind.setTextSize(0, 24.0f);
        this.mBtnRtWind.setGravity(19);
        this.mBtnRtWind.setTextColor(-1);
        this.mBtnOff = AddBtn(17, 2, KeyDef.RKEY_RADIO_4S, 100, 83, R.drawable.can_jeep_ac_off_up, R.drawable.can_jeep_ac_off_dn);
        this.mBtnWindDec = AddBtn(18, 102, KeyDef.RKEY_RADIO_4S, 98, 83, R.drawable.can_jeep_ac_xfans_up, R.drawable.can_jeep_ac_xfans_dn);
        this.mBtnWindInc = AddBtn(19, CanCameraUI.BTN_CHANA_CS75_MODE3, KeyDef.RKEY_RADIO_4S, 97, 83, R.drawable.can_jeep_ac_dfans_up, R.drawable.can_jeep_ac_dfans_dn);
        this.mWindProg = new MyProgressBar(this, R.drawable.can_jeep_ac_pro_up1, R.drawable.can_jeep_ac_pro_dn1);
        this.mWindProg.SetMinMax(0, 7);
        this.mManager.AddViewWrapContent(this.mWindProg, 216, 356);
        this.mIvWindAuto = this.mManager.AddImage(KeyDef.RKEY_BT, 354, R.drawable.can_yl_wind_auto);
        this.mBtnSync = AddBtn(20, 669, KeyDef.RKEY_RADIO_4S, 98, 83, R.drawable.can_jeep_ac_sync_up, R.drawable.can_jeep_ac_sync_dn);
        this.mTvWindVal = AddTemp(536, 346, 32, 40);
        this.mBtnWheelHot = AddBtn(21, CanCameraUI.BTN_CHANA_CS75_MODE5, 265, 80, 50, R.drawable.can_golf_wheel_hot_up, R.drawable.can_golf_wheel_hot_dn);
    }

    private void initCommonScreen() {
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_jeep_ac_bg);
        this.mManager.AddImage(44, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
        this.mManager.AddImage(889, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
        this.mBtnMaxAc = AddBtn(1, 0, 25, R.drawable.can_jeep_ac_max_up, R.drawable.can_jeep_ac_max_dn);
        this.mBtnAc = AddBtn(2, 174, 25, R.drawable.can_jeep_ac_ac_up, R.drawable.can_jeep_ac_ac_dn);
        this.mBtnLoop = AddBtn(3, 344, 25, R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        this.mBtnAuto = AddBtn(4, 513, 25, R.drawable.can_jeep_ac_auto_up, R.drawable.can_jeep_ac_auto_dn);
        this.mBtnForeWind = AddBtn(5, 682, 25, R.drawable.can_jeep_ac_window_up, R.drawable.can_jeep_ac_window_dn);
        this.mBtnRearWind = AddBtn(6, 852, 25, R.drawable.can_jeep_ac_rear_up, R.drawable.can_jeep_ac_rear_dn);
        this.mBtnLtTempDec = AddBtn(8, 45, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnLtTempInc = AddBtn(7, 45, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvLtTemp = AddTemp(44, Can.CAN_FORD_SYNC3, 93, 51);
        this.mBtnRtTempDec = AddBtn(14, 890, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnRtTempInc = AddBtn(13, 890, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvRtTemp = AddTemp(889, Can.CAN_FORD_SYNC3, 95, 51);
        this.mBtnWdPx = AddBtn(9, Can.CAN_SITECHDEV_CW, 144, R.drawable.can_jeep_ac_01_up, R.drawable.can_jeep_ac_01_dn);
        this.mBtnWdPxDn = AddBtn(10, 388, 144, R.drawable.can_jeep_ac_02_up, R.drawable.can_jeep_ac_02_dn);
        this.mBtnWdDn = AddBtn(11, 536, 144, R.drawable.can_jeep_ac_03_up, R.drawable.can_jeep_ac_03_dn);
        this.mBtnWdUpDn = AddBtn(12, 683, 144, R.drawable.can_jeep_ac_04_up, R.drawable.can_jeep_ac_04_dn);
        this.mBtnLtHot = AddBtn(15, Can.CAN_SITECHDEV_CW, 284, R.drawable.can_jeep_ac_lchair01_up, R.drawable.can_jeep_ac_lchair01_dn);
        this.mBtnRtHot = AddBtn(16, 661, 284, R.drawable.can_jeep_ac_rchair01_up, R.drawable.can_jeep_ac_rchair01_dn);
        this.mBtnLtHot.setPadding(0, 0, 85, 0);
        this.mBtnLtHot.setTextSize(0, 30.0f);
        this.mBtnLtHot.setGravity(21);
        this.mBtnLtHot.setTextColor(-1);
        this.mBtnRtHot.setPadding(85, 0, 0, 0);
        this.mBtnRtHot.setTextSize(0, 30.0f);
        this.mBtnRtHot.setGravity(19);
        this.mBtnRtHot.setTextColor(-1);
        this.mBtnLtWind = AddBtn(22, KeyTouch.GAMMA_MAX_NUM, 284, R.drawable.can_jeep_ac_lchair02_up, R.drawable.can_jeep_ac_lchair02_dn);
        this.mBtnRtWind = AddBtn(23, 536, 284, R.drawable.can_jeep_ac_rchair02_up, R.drawable.can_jeep_ac_rchair02_dn);
        this.mBtnLtWind.setPadding(0, 0, 85, 0);
        this.mBtnLtWind.setTextSize(0, 30.0f);
        this.mBtnLtWind.setGravity(21);
        this.mBtnLtWind.setTextColor(-1);
        this.mBtnRtWind.setPadding(85, 0, 0, 0);
        this.mBtnRtWind.setTextSize(0, 30.0f);
        this.mBtnRtWind.setGravity(19);
        this.mBtnRtWind.setTextColor(-1);
        this.mBtnOff = AddBtn(17, 0, 415, R.drawable.can_jeep_ac_off_up, R.drawable.can_jeep_ac_off_dn);
        this.mBtnWindDec = AddBtn(18, 134, 415, R.drawable.can_jeep_ac_xfans_up, R.drawable.can_jeep_ac_xfans_dn);
        this.mBtnWindInc = AddBtn(19, 763, 415, R.drawable.can_jeep_ac_dfans_up, R.drawable.can_jeep_ac_dfans_dn);
        this.mWindProg = new MyProgressBar(this, R.drawable.can_jeep_ac_pro_up, R.drawable.can_jeep_ac_pro_dn);
        this.mWindProg.SetMinMax(0, 7);
        this.mManager.AddViewWrapContent(this.mWindProg, 287, 456);
        this.mIvWindAuto = this.mManager.AddImage(457, 458, R.drawable.can_yl_wind_auto);
        this.mBtnSync = AddBtn(20, 893, 415, R.drawable.can_jeep_ac_sync_up, R.drawable.can_jeep_ac_sync_dn);
        this.mTvWindVal = AddTemp(717, 451, 32, 40);
        this.mBtnWheelHot = AddBtn(21, 802, KeyDef.RKEY_POWER_OFF, 84, 47, R.drawable.can_golf_wheel_hot_up, R.drawable.can_golf_wheel_hot_dn);
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTemp(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(40);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
        mIsAC = true;
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
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mACInfo.szRtTemp);
        this.mBtnMaxAc.SetSel(this.mACInfo.fgACMax);
        this.mBtnAc.SetSel(this.mACInfo.fgAC);
        this.mBtnAuto.SetSel(this.mACInfo.nAutoLight);
        if (this.mACInfo.fgInnerLoop != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        } else {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_wxh_up, R.drawable.can_jeep_ac_wxh_dn);
        }
        this.mBtnForeWind.SetSel(this.mACInfo.fgDFBL);
        this.mBtnRearWind.SetSel(this.mACInfo.fgRearLight);
        int windDirect = (this.mACInfo.fgUpWind * 1) + (this.mACInfo.fgParallelWind * 2) + (this.mACInfo.fgDownWind * 4);
        ParamButton paramButton = this.mBtnWdPx;
        if (2 == windDirect) {
            z = true;
        } else {
            z = false;
        }
        paramButton.setSelected(z);
        ParamButton paramButton2 = this.mBtnWdPxDn;
        if (6 == windDirect) {
            z2 = true;
        } else {
            z2 = false;
        }
        paramButton2.setSelected(z2);
        ParamButton paramButton3 = this.mBtnWdDn;
        if (4 == windDirect) {
            z3 = true;
        } else {
            z3 = false;
        }
        paramButton3.setSelected(z3);
        ParamButton paramButton4 = this.mBtnWdUpDn;
        if (5 == windDirect) {
            z4 = true;
        } else {
            z4 = false;
        }
        paramButton4.setSelected(z4);
        this.mBtnLtHot.SetSel(this.mACInfo.nLtChairHot);
        this.mBtnRtHot.SetSel(this.mACInfo.nRtChairHot);
        switch (this.mACInfo.nLtChairHot) {
            case 0:
                this.mBtnLtHot.setText(R.string.can_off);
                break;
            case 1:
                this.mBtnLtHot.setText(R.string.can_jeepac_low);
                break;
            case 2:
                this.mBtnLtHot.setText(R.string.can_jeepac_mid);
                break;
            default:
                this.mBtnLtHot.setText(R.string.can_jeepac_high);
                break;
        }
        switch (this.mACInfo.nRtChairHot) {
            case 0:
                this.mBtnRtHot.setText(R.string.can_off);
                break;
            case 1:
                this.mBtnRtHot.setText(R.string.can_jeepac_low);
                break;
            case 2:
                this.mBtnRtHot.setText(R.string.can_jeepac_mid);
                break;
            default:
                this.mBtnRtHot.setText(R.string.can_jeepac_high);
                break;
        }
        this.mBtnLtWind.SetSel(this.mACInfo.nLtChairWind);
        this.mBtnRtWind.SetSel(this.mACInfo.nRtChairWind);
        switch (this.mACInfo.nLtChairWind) {
            case 0:
                this.mBtnLtWind.setText(R.string.can_off);
                break;
            case 1:
                this.mBtnLtWind.setText(R.string.can_jeepac_low);
                break;
            default:
                this.mBtnLtWind.setText(R.string.can_jeepac_high);
                break;
        }
        switch (this.mACInfo.nRtChairWind) {
            case 0:
                this.mBtnRtWind.setText(R.string.can_off);
                break;
            case 1:
                this.mBtnRtWind.setText(R.string.can_jeepac_low);
                break;
            default:
                this.mBtnRtWind.setText(R.string.can_ac_high);
                break;
        }
        this.mBtnOff.SetSel(1 - this.mACInfo.PWR);
        this.mBtnSync.SetSel(this.mACInfo.fgDual);
        this.mBtnWheelHot.SetSel(this.mACInfo.fgHeat);
        if (15 == this.mACInfo.nWindValue) {
            this.mWindProg.SetCurPos(0);
            this.mTvWindVal.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mIvWindAuto.Show(true);
            return;
        }
        this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
        this.mTvWindVal.setText(new StringBuilder(String.valueOf(this.mACInfo.nWindValue)).toString());
        this.mIvWindAuto.Show(false);
    }

    private void ResetData(boolean check) {
        Can.updateAC();
        if (!check || Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (mfgJump && GetTickCount() > CanFunc.mLastACTick + 3000) {
            finish();
            Log.d(TAG, "UserAll Exit Ac");
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public static void ShowAC() {
        if (!mIsAC) {
            mfgJump = true;
            CanFunc.showCanActivity(CanRZygACActivity.class);
        }
        mUpdateOnce = true;
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            Log.d(TAG, "****ACTION_DOWN*****");
            switch (Id) {
                case 1:
                    ACDown(18);
                    return false;
                case 2:
                    ACDown(17);
                    return false;
                case 3:
                    ACDown(19);
                    return false;
                case 4:
                    ACDown(20);
                    return false;
                case 5:
                    ACDown(21);
                    return false;
                case 6:
                    ACDown(22);
                    return false;
                case 7:
                    ACDown(31);
                    return false;
                case 8:
                    ACDown(30);
                    return false;
                case 9:
                    ACDown(24);
                    return false;
                case 10:
                    ACDown(25);
                    return false;
                case 11:
                    ACDown(26);
                    return false;
                case 12:
                    ACDown(27);
                    return false;
                case 13:
                    ACDown(33);
                    return false;
                case 14:
                    ACDown(32);
                    return false;
                case 15:
                    ACDown(48);
                    return false;
                case 16:
                    ACDown(50);
                    return false;
                case 17:
                    if (this.mACInfo.PWR != 0) {
                        ACDown(16);
                        return false;
                    }
                    ACDown(9);
                    return false;
                case 18:
                    ACDown(28);
                    return false;
                case 19:
                    ACDown(29);
                    return false;
                case 20:
                    ACDown(23);
                    return false;
                case 21:
                    ACDown(52);
                    return false;
                case 22:
                    ACDown(49);
                    return false;
                case 23:
                    ACDown(51);
                    return false;
                default:
                    return false;
            }
        } else if (1 != action) {
            return false;
        } else {
            Log.d(TAG, "****ACTION_UP*****");
            switch (Id) {
                case 1:
                    ACRel(18);
                    return false;
                case 2:
                    ACRel(17);
                    return false;
                case 3:
                    ACRel(19);
                    return false;
                case 4:
                    ACRel(20);
                    return false;
                case 5:
                    ACRel(21);
                    return false;
                case 6:
                    ACRel(22);
                    return false;
                case 7:
                    ACRel(31);
                    return false;
                case 8:
                    ACRel(30);
                    return false;
                case 9:
                    ACRel(24);
                    return false;
                case 10:
                    ACRel(25);
                    return false;
                case 11:
                    ACRel(26);
                    return false;
                case 12:
                    ACRel(27);
                    return false;
                case 13:
                    ACRel(33);
                    return false;
                case 14:
                    ACRel(32);
                    return false;
                case 15:
                    ACRel(48);
                    return false;
                case 16:
                    ACRel(50);
                    return false;
                case 17:
                    if (this.mACInfo.PWR != 0) {
                        ACRel(16);
                        return false;
                    }
                    ACRel(9);
                    return false;
                case 18:
                    ACRel(28);
                    return false;
                case 19:
                    ACRel(29);
                    return false;
                case 20:
                    ACRel(23);
                    return false;
                case 21:
                    ACRel(52);
                    return false;
                case 22:
                    ACRel(49);
                    return false;
                case 23:
                    ACRel(51);
                    return false;
                default:
                    return false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void ACDown(int cmd) {
        CanJni.ChrOthACCtrl(cmd, 1);
    }

    /* access modifiers changed from: protected */
    public void ACRel(int cmd) {
        CanJni.ChrOthACCtrl(cmd, 0);
    }
}
