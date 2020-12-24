package com.ts.can.chrysler;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.ts.can.toyota.dj.CanToyotaDJCarDeviceView;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.MyProgressBar;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.KeyDef;

public class CanChrOthACActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange, View.OnTouchListener {
    public static final int ITEM_AC = 2;
    public static final int ITEM_AUTO = 4;
    public static final int ITEM_FORE_WIND = 5;
    public static final int ITEM_LOOP = 3;
    public static final int ITEM_LTEMP_DEC = 8;
    public static final int ITEM_LTEMP_INC = 7;
    public static final int ITEM_LTSEAT_HOT = 15;
    public static final int ITEM_MAX_AC = 1;
    public static final int ITEM_OFF = 17;
    public static final int ITEM_REAR_WIND = 6;
    public static final int ITEM_RTEMP_DEC = 14;
    public static final int ITEM_RTEMP_INC = 13;
    public static final int ITEM_RTSEAT_HOT = 16;
    public static final int ITEM_SYNC = 20;
    public static final int ITEM_WD_DN = 11;
    public static final int ITEM_WD_PX = 9;
    public static final int ITEM_WD_PX_DN = 10;
    public static final int ITEM_WD_UP_DN = 12;
    public static final int ITEM_WIND_DEC = 18;
    public static final int ITEM_WIND_INC = 19;
    public static final String TAG = "CanMGGSACActivity";
    protected static boolean mIsAC;
    protected static boolean mfgJump;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAuto;
    private ParamButton mBtnForeWind;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnMaxAc;
    private ParamButton mBtnOff;
    private ParamButton mBtnRearWind;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnSync;
    private ParamButton mBtnWdDn;
    private ParamButton mBtnWdPx;
    private ParamButton mBtnWdPxDn;
    private ParamButton mBtnWdUpDn;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private CustomImgView mIvWindAuto;
    protected RelativeLayoutManager mManager;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private CustomTextView[] mTvWindLev = new CustomTextView[7];
    private CustomTextView mTvWindVal;
    private CustomImgView[] mWindLevel = new CustomImgView[7];
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
        Log.d("CanMGGSACActivity", "jump = " + mfgJump);
        if (!mfgJump) {
            CanJni.ChrOthQuery(33, 0, 0, 0);
        }
    }

    private void initScreen_768x1024() {
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_jeep_ac_bg1);
        this.mManager.AddImageEx(33, 183, 100, 50, R.drawable.can_jeep_ac_tmp);
        this.mManager.AddImageEx(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE5, 183, 100, 50, R.drawable.can_jeep_ac_tmp);
        mfgJump = CanFunc.IsCanActivityJumped(this);
        this.mACInfo = Can.mACInfo;
        this.mBtnMaxAc = AddBtn(1, 2, 37, 130, 52, R.drawable.can_jeep_ac_max_up, R.drawable.can_jeep_ac_max_dn);
        this.mBtnAc = AddBtn(2, 132, 37, 127, 52, R.drawable.can_jeep_ac_ac_up, R.drawable.can_jeep_ac_ac_dn);
        this.mBtnLoop = AddBtn(3, 259, 37, 126, 52, R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        this.mBtnAuto = AddBtn(4, 385, 37, 127, 52, R.drawable.can_jeep_ac_auto_up, R.drawable.can_jeep_ac_auto_dn);
        this.mBtnForeWind = AddBtn(5, CanCameraUI.BTN_YG9_XBS_MODE1, 37, 127, 52, R.drawable.can_jeep_ac_window_up, R.drawable.can_jeep_ac_window_dn);
        this.mBtnRearWind = AddBtn(6, 639, 37, 128, 52, R.drawable.can_jeep_ac_rear_up, R.drawable.can_jeep_ac_rear_dn);
        this.mBtnLtTempDec = AddBtn(8, 36, 265, 100, 50, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnLtTempInc = AddBtn(7, 36, 105, 100, 50, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvLtTemp = AddTemp(33, 183, 100, 50);
        this.mTvLtTemp.SetPixelSize(30);
        this.mBtnRtTempDec = AddBtn(14, 637, 265, 100, 50, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnRtTempInc = AddBtn(13, 637, 105, 100, 50, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvRtTemp = AddTemp(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE5, 183, 100, 50);
        this.mTvRtTemp.SetPixelSize(30);
        this.mBtnWdPx = AddBtn(9, 182, 126, 77, 77, R.drawable.can_jeep_ac_01_up, R.drawable.can_jeep_ac_01_dn);
        this.mBtnWdPxDn = AddBtn(10, KeyDef.RKEY_PRE, 126, 77, 77, R.drawable.can_jeep_ac_02_up, R.drawable.can_jeep_ac_02_dn);
        this.mBtnWdDn = AddBtn(11, CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, 126, 77, 77, R.drawable.can_jeep_ac_03_up, R.drawable.can_jeep_ac_03_dn);
        this.mBtnWdUpDn = AddBtn(12, 512, 126, 77, 77, R.drawable.can_jeep_ac_04_up, R.drawable.can_jeep_ac_04_dn);
        this.mBtnLtHot = AddBtn(15, 182, Can.CAN_SE_DX7_RZC, 187, 62, R.drawable.can_jeep_ac_lchair_up, R.drawable.can_jeep_ac_lchair_dn);
        this.mBtnRtHot = AddBtn(16, CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, Can.CAN_SE_DX7_RZC, 187, 62, R.drawable.can_jeep_ac_rchair_up, R.drawable.can_jeep_ac_rchair_dn);
        this.mBtnLtHot.setPadding(0, 0, 125, 0);
        this.mBtnLtHot.setTextSize(0, 24.0f);
        this.mBtnLtHot.setGravity(21);
        this.mBtnLtHot.setTextColor(-1);
        this.mBtnRtHot.setPadding(135, 0, 0, 0);
        this.mBtnRtHot.setTextSize(0, 24.0f);
        this.mBtnRtHot.setGravity(19);
        this.mBtnRtHot.setTextColor(-1);
        this.mBtnOff = AddBtn(17, 2, KeyDef.RKEY_EJECT_L, 100, 83, R.drawable.can_jeep_ac_off_up, R.drawable.can_jeep_ac_off_dn);
        this.mBtnWindDec = AddBtn(18, 102, KeyDef.RKEY_EJECT_L, 98, 83, R.drawable.can_jeep_ac_xfans_up, R.drawable.can_jeep_ac_xfans_dn);
        this.mBtnWindInc = AddBtn(19, CanCameraUI.BTN_CHANA_CS75_MODE3, KeyDef.RKEY_EJECT_L, 97, 83, R.drawable.can_jeep_ac_dfans_up, R.drawable.can_jeep_ac_dfans_dn);
        this.mWindProg = new MyProgressBar(this, R.drawable.can_jeep_ac_pro_up1, R.drawable.can_jeep_ac_pro_dn1);
        this.mWindProg.SetMinMax(0, 7);
        this.mManager.AddViewWrapContent(this.mWindProg, 216, 364);
        this.mIvWindAuto = this.mManager.AddImage(343, 360, R.drawable.can_yl_wind_auto);
        this.mBtnSync = AddBtn(20, 669, KeyDef.RKEY_EJECT_L, 98, 83, R.drawable.can_jeep_ac_sync_up, R.drawable.can_jeep_ac_sync_dn);
        this.mTvWindVal = AddTemp(536, 353, 32, 40);
    }

    private void initCommonScreen() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.can_comm_layout);
        if (CanJni.GetSubType() == 7) {
            relativeLayout.setBackgroundResource(R.drawable.can_wrangler_ac_bg);
        } else if (CanFunc.getInstance().IsCustomShow("Jeep")) {
            relativeLayout.setBackgroundResource(R.drawable.jeep_ac_bg);
        } else {
            relativeLayout.setBackgroundResource(R.drawable.can_jeep_ac_bg);
        }
        ViewGroup.LayoutParams lp = relativeLayout.getLayoutParams();
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        relativeLayout.setLayoutParams(lp);
        mfgJump = CanFunc.IsCanActivityJumped(this);
        this.mACInfo = Can.mACInfo;
        this.mBtnLtTempDec = AddBtn(8, 45, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnLtTempInc = AddBtn(7, 45, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvLtTemp = AddTemp(44, Can.CAN_FORD_SYNC3, 93, 51);
        this.mBtnRtTempDec = AddBtn(14, 890, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnRtTempInc = AddBtn(13, 890, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvRtTemp = AddTemp(889, Can.CAN_FORD_SYNC3, 95, 51);
        this.mBtnWdPx = AddBtn(9, 241, 144, R.drawable.can_jeep_ac_01_up, R.drawable.can_jeep_ac_01_dn);
        this.mBtnWdPxDn = AddBtn(10, 388, 144, R.drawable.can_jeep_ac_02_up, R.drawable.can_jeep_ac_02_dn);
        this.mBtnWdDn = AddBtn(11, 536, 144, R.drawable.can_jeep_ac_03_up, R.drawable.can_jeep_ac_03_dn);
        this.mBtnWdUpDn = AddBtn(12, 683, 144, R.drawable.can_jeep_ac_04_up, R.drawable.can_jeep_ac_04_dn);
        this.mBtnLtHot = AddBtn(15, 241, KeyDef.RKEY_FR, R.drawable.can_jeep_ac_lchair_up, R.drawable.can_jeep_ac_lchair_dn);
        this.mBtnRtHot = AddBtn(16, 536, KeyDef.RKEY_FR, R.drawable.can_jeep_ac_rchair_up, R.drawable.can_jeep_ac_rchair_dn);
        this.mBtnLtHot.setPadding(0, 0, 162, 0);
        this.mBtnLtHot.setTextSize(0, 30.0f);
        this.mBtnLtHot.setGravity(21);
        this.mBtnLtHot.setTextColor(-1);
        this.mBtnRtHot.setPadding(180, 0, 0, 0);
        this.mBtnRtHot.setTextSize(0, 30.0f);
        this.mBtnRtHot.setGravity(19);
        this.mBtnRtHot.setTextColor(-1);
        this.mBtnOff = AddBtn(17, 0, 407, R.drawable.can_jeep_ac_off_up, R.drawable.can_jeep_ac_off_dn);
        this.mBtnWindDec = AddBtn(18, 134, 407, R.drawable.can_jeep_ac_xfans_up, R.drawable.can_jeep_ac_xfans_dn);
        this.mBtnWindInc = AddBtn(19, 763, 407, R.drawable.can_jeep_ac_dfans_up, R.drawable.can_jeep_ac_dfans_dn);
        this.mWindProg = new MyProgressBar(this, R.drawable.can_jeep_ac_pro_up, R.drawable.can_jeep_ac_pro_dn);
        this.mWindProg.SetMinMax(0, 7);
        this.mManager.AddViewWrapContent(this.mWindProg, 287, 448);
        this.mBtnSync = AddBtn(20, 893, 407, R.drawable.can_jeep_ac_sync_up, R.drawable.can_jeep_ac_sync_dn);
        this.mTvWindVal = AddTemp(717, 441, 32, 40);
        if (CanJni.GetSubType() == 7) {
            this.mBtnMaxAc = AddBtn(1, 86, 25, R.drawable.can_jeep_ac_max_up, R.drawable.can_jeep_ac_max_dn);
            this.mBtnAc = AddBtn(2, 260, 25, R.drawable.can_jeep_ac_ac_up, R.drawable.can_jeep_ac_ac_dn);
            this.mBtnLoop = AddBtn(3, 430, 25, R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
            this.mBtnForeWind = AddBtn(5, 599, 25, R.drawable.can_jeep_ac_window_up, R.drawable.can_jeep_ac_window_dn);
            this.mBtnRearWind = AddBtn(6, CanToyotaDJCarDeviceView.ITEM_PLAY, 25, R.drawable.can_jeep_ac_rear_up, R.drawable.can_jeep_ac_rear_dn);
            for (int i = 0; i < 7; i++) {
                this.mWindLevel[i] = this.mManager.AddImageEx((i * 66) + 283, 443, 66, 40, 0);
                this.mWindLevel[i].setStateDrawable(R.drawable.can_jeep_dcac_pro01_up, R.drawable.can_jeep_dcac_pro_dn);
                this.mTvWindLev[i] = AddTemp((i * 66) + 283, 443, 66, 40);
                this.mTvWindLev[i].setText(String.format("%d", new Object[]{Integer.valueOf(i + 1)}));
                this.mTvWindLev[i].setTextColor(-12303292);
                this.mTvWindLev[i].ShowGone(false);
            }
            this.mWindProg.Show(false);
            this.mTvWindVal.ShowGone(false);
            this.mBtnLtHot.Show(false);
            this.mBtnRtHot.Show(false);
            this.mBtnSync.Show(false);
            this.mBtnLtTempInc.Show(false);
            this.mBtnLtTempDec.Show(false);
            this.mTvLtTemp.ShowGone(false);
            this.mBtnRtTempInc.Show(false);
            this.mBtnRtTempDec.Show(false);
            this.mTvRtTemp.ShowGone(false);
            this.mIvWindAuto = this.mManager.AddImage(475, 450, R.drawable.can_yl_wind_auto);
            this.mIvWindAuto.Show(false);
        } else if (CanFunc.getInstance().IsCustomShow("Jeep")) {
            this.mManager.AddImage(44, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
            this.mManager.AddImage(889, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
            this.mBtnMaxAc = AddBtn(1, 0, 25, R.drawable.can_jeep_ac_max_up, R.drawable.can_jeep_ac_max_dn);
            this.mBtnAc = AddBtn(2, 174, 25, R.drawable.can_jeep_ac_ac_up, R.drawable.can_jeep_ac_ac_dn);
            this.mBtnLoop = AddBtn(3, 344, 25, R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
            this.mBtnAuto = AddBtn(4, 513, 25, R.drawable.can_jeep_ac_auto_up, R.drawable.can_jeep_ac_auto_dn);
            this.mBtnForeWind = AddBtn(5, 682, 25, R.drawable.can_jeep_ac_window_up, R.drawable.can_jeep_ac_window_dn);
            this.mBtnRearWind = AddBtn(6, 852, 25, R.drawable.can_jeep_ac_rear_up, R.drawable.can_jeep_ac_rear_dn);
            for (int i2 = 0; i2 < 7; i2++) {
                this.mWindLevel[i2] = this.mManager.AddImageEx((i2 * 66) + 283, 443, 66, 40, 0);
                this.mWindLevel[i2].setStateDrawable(R.drawable.can_jeep_dcac_pro01_up, R.drawable.can_jeep_dcac_pro_dn);
                this.mTvWindLev[i2] = AddTemp((i2 * 66) + 283, 443, 66, 40);
                this.mTvWindLev[i2].setText(String.format("%d", new Object[]{Integer.valueOf(i2 + 1)}));
                this.mTvWindLev[i2].setTextColor(-12303292);
                this.mTvWindLev[i2].ShowGone(false);
            }
            this.mWindProg.Show(false);
            this.mTvWindVal.ShowGone(false);
            this.mIvWindAuto = this.mManager.AddImage(475, 450, R.drawable.can_yl_wind_auto);
        } else {
            this.mManager.AddImage(44, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
            this.mManager.AddImage(889, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
            this.mBtnMaxAc = AddBtn(1, 0, 25, R.drawable.can_jeep_ac_max_up, R.drawable.can_jeep_ac_max_dn);
            this.mBtnAc = AddBtn(2, 174, 25, R.drawable.can_jeep_ac_ac_up, R.drawable.can_jeep_ac_ac_dn);
            this.mBtnLoop = AddBtn(3, 344, 25, R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
            this.mBtnAuto = AddBtn(4, 513, 25, R.drawable.can_jeep_ac_auto_up, R.drawable.can_jeep_ac_auto_dn);
            this.mBtnForeWind = AddBtn(5, 682, 25, R.drawable.can_jeep_ac_window_up, R.drawable.can_jeep_ac_window_dn);
            this.mBtnRearWind = AddBtn(6, 852, 25, R.drawable.can_jeep_ac_rear_up, R.drawable.can_jeep_ac_rear_dn);
            this.mIvWindAuto = this.mManager.AddImage(457, 450, R.drawable.can_yl_wind_auto);
        }
        if (CanJni.GetSubType() == 8) {
            this.mBtnLtHot.Show(false);
            this.mBtnRtHot.Show(false);
        }
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
        Log.d("CanMGGSACActivity", "-----onResume-----");
        mIsAC = true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanMGGSACActivity", "-----onPause-----");
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
        if (this.mBtnAuto != null) {
            this.mBtnAuto.SetSel(this.mACInfo.nAutoLight);
        }
        if (CanFunc.getInstance().IsCustomShow("Jeep")) {
            this.mBtnLoop.SetSel(this.mACInfo.fgInnerLoop);
        } else if (this.mACInfo.fgInnerLoop != 0) {
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
                this.mBtnLtHot.setText(R.string.can_ac_low);
                break;
            case 3:
                this.mBtnLtHot.setText(R.string.can_ac_mid);
                break;
            default:
                this.mBtnLtHot.setText(R.string.can_ac_high);
                break;
        }
        switch (this.mACInfo.nRtChairHot) {
            case 0:
                this.mBtnRtHot.setText(R.string.can_off);
                break;
            case 1:
                this.mBtnRtHot.setText(R.string.can_ac_low);
                break;
            case 3:
                this.mBtnRtHot.setText(R.string.can_ac_mid);
                break;
            default:
                this.mBtnRtHot.setText(R.string.can_ac_high);
                break;
        }
        this.mBtnOff.SetSel(1 - this.mACInfo.PWR);
        this.mBtnSync.SetSel(this.mACInfo.fgDual);
        if (15 == this.mACInfo.nWindValue) {
            this.mWindProg.SetCurPos(0);
            this.mTvWindVal.setText("");
            this.mIvWindAuto.Show(true);
            if (this.mWindLevel[0] != null) {
                for (int i = 0; i < 7; i++) {
                    this.mWindLevel[i].setSelected(false);
                    this.mTvWindLev[i].ShowGone(false);
                }
                return;
            }
            return;
        }
        this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
        this.mTvWindVal.setText(new StringBuilder(String.valueOf(this.mACInfo.nWindValue)).toString());
        this.mIvWindAuto.Show(false);
        if (this.mWindLevel[0] != null) {
            int WindLevel = this.mACInfo.nWindValue & 7;
            for (int i2 = 0; i2 < WindLevel; i2++) {
                this.mWindLevel[i2].setSelected(true);
            }
            for (int i3 = WindLevel; i3 < 7; i3++) {
                this.mWindLevel[i3].setSelected(false);
            }
            for (int i4 = 0; i4 < 7; i4++) {
                if (WindLevel == 0 || i4 != WindLevel - 1) {
                    this.mTvWindLev[i4].ShowGone(false);
                } else {
                    this.mTvWindLev[i4].ShowGone(true);
                }
            }
        }
    }

    private void ResetData(boolean check) {
        Can.updateAC();
        if (!check || Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (mfgJump && GetTickCount() > CanFunc.mLastACTick + 3000) {
            finish();
            Log.d("CanMGGSACActivity", "UserAll Exit Ac");
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
            CanFunc.showCanActivity(CanChrOthACActivity.class);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            Log.d("CanMGGSACActivity", "****ACTION_DOWN*****");
            switch (Id) {
                case 1:
                    ACSet(15);
                    return false;
                case 2:
                    ACSet(1);
                    return false;
                case 3:
                    ACSet(3);
                    return false;
                case 4:
                    ACSet(2);
                    return false;
                case 5:
                    ACSet(12);
                    return false;
                case 6:
                    ACSet(14);
                    return false;
                case 7:
                    if (FtSet.GetACTempSwap() > 0) {
                        ACSet(20);
                        return false;
                    }
                    ACSet(4);
                    return false;
                case 8:
                    if (FtSet.GetACTempSwap() > 0) {
                        ACSet(21);
                        return false;
                    }
                    ACSet(5);
                    return false;
                case 9:
                    ACSet(8);
                    return false;
                case 10:
                    ACSet(9);
                    return false;
                case 11:
                    ACSet(10);
                    return false;
                case 12:
                    ACSet(11);
                    return false;
                case 13:
                    if (FtSet.GetACTempSwap() > 0) {
                        ACSet(4);
                        return false;
                    }
                    ACSet(20);
                    return false;
                case 14:
                    if (FtSet.GetACTempSwap() > 0) {
                        ACSet(5);
                        return false;
                    }
                    ACSet(21);
                    return false;
                case 15:
                    ACSet(17);
                    return false;
                case 16:
                    ACSet(18);
                    return false;
                case 17:
                    ACSet(16);
                    return false;
                case 18:
                    ACSet(7);
                    return false;
                case 19:
                    ACSet(6);
                    return false;
                case 20:
                    ACSet(13);
                    return false;
                default:
                    return false;
            }
        } else if (1 != action) {
            return false;
        } else {
            Log.d("CanMGGSACActivity", "****ACTION_UP*****");
            switch (Id) {
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void ACSet(int cmd) {
        CanJni.ChrOthACCtrl(1, cmd);
    }
}
