package com.ts.can.vw.touareg;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanTouaregCarACActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    private static final int ITEM_AUTO_AQS = 3;
    private static final int ITEM_LT_HOT = 4;
    private static final int ITEM_PROFILE = 2;
    private static final int ITEM_RT_HOT = 5;
    private static final int ITEM_WIND = 1;
    private static final int[] mProfile = {R.string.can_air_light, R.string.can_air_medium, R.string.can_air_strong};
    protected static boolean mfgJump;
    private CustomImgView mAc;
    private CanDataInfo.TouaregWcACData mAcData = new CanDataInfo.TouaregWcACData();
    private CustomImgView mAuto;
    protected boolean mAutoFinish = false;
    private ParamButton mBtnAutoAQS;
    private ParamButton mBtnClearAir;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnProfile;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnSwHot;
    private CustomImgView mDual;
    private CustomImgView mLtFire;
    private CustomImgView[] mLtHot = new CustomImgView[3];
    private TextView mLtTemp;
    private RelativeLayoutManager mManager;
    private CustomImgView mMaxFront;
    private CustomImgView mRearLight;
    private TextView mRearTemp;
    private CustomImgView mRtFire;
    private CustomImgView[] mRtHot = new CustomImgView[3];
    private TextView mRtTemp;
    private String mStrProfileText;
    private String[] mStrProfileVal;
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

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        mfgJump = CanFunc.IsCanActivityJumped(this);
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
        this.mWindProgress = new MyProgressBar(this, R.drawable.conditioning_progress_bar_up, R.drawable.conditioning_progress_bar_dn);
        this.mManager.AddViewWrapContent(this.mWindProgress, 267, 32);
        this.mWindProgress.SetMinMax(0, 7);
        this.mWindProgress.SetCurPos(7);
        this.mWindVal = this.mManager.AddText(736, 22, 60, 40);
        this.mWindVal.setTextSize(0, 30.0f);
        this.mWindVal.setTextColor(-1);
        this.mWindVal.setText(MainSet.SP_TW_CJW);
        this.mWindVal.setGravity(19);
        this.mManager.AddImageEx(Can.CAN_LIEBAO_WC, 30, 25, 26, R.drawable.conditioning_fan02_up);
        this.mManager.AddImageEx(686, 22, 39, 40, R.drawable.conditioning_fan01_up);
        this.mManager.AddImageEx(12, 76, 999, 1, R.drawable.conditioning_line_up);
        this.mManager.AddImageEx(12, 405, 999, 1, R.drawable.conditioning_line_up);
        this.mManager.AddImageEx(481, 94, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, Can.CAN_CHRYSLER_ONE_HC, R.drawable.can_golf_bg_new);
        this.mManager.AddImageEx(411, 104, 2, 267, R.drawable.can_golf_sline);
        this.mManager.AddImageEx(30, Can.CAN_JAC_REFINE_OD, Can.CAN_DFFG_S560, 138, R.drawable.conditioning_leftseat_up);
        this.mManager.AddImageEx(217, Can.CAN_JAC_REFINE_OD, Can.CAN_DFFG_S560, 138, R.drawable.conditioning_rightseat_up);
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
        this.mBtnProfile = this.mManager.AddButton(26, 430);
        this.mBtnProfile.setStateDrawable(R.drawable.can_golf_case_up, R.drawable.can_golf_case_dn, R.drawable.can_golf_case_dn);
        this.mBtnProfile.setTag(2);
        this.mBtnProfile.setOnClickListener(this);
        this.mBtnProfile.setTextSize(0, 35.0f);
        this.mBtnProfile.setTextColor(-1);
        this.mBtnAutoAQS = this.mManager.AddButton(467, 430);
        this.mBtnAutoAQS.setStateDrawable(R.drawable.can_golf_case_right_up, R.drawable.can_golf_case_right_dn, R.drawable.can_golf_case_right_dn);
        this.mBtnAutoAQS.setText(R.string.can_auto_recirculate);
        this.mBtnAutoAQS.setTextSize(0, 35.0f);
        this.mBtnAutoAQS.setTextColor(-1);
        this.mBtnAutoAQS.setTag(3);
        this.mBtnAutoAQS.setOnClickListener(this);
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
        this.mStrProfileText = String.valueOf(getResources().getString(R.string.can_air_con_profile)) + ":";
        this.mStrProfileVal = new String[mProfile.length];
        for (int i = 0; i < mProfile.length; i++) {
            this.mStrProfileVal[i] = getResources().getString(mProfile[i]);
        }
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
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            updateACUI();
        }
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
        this.mRearLight.SetSel(mACInfo.fgRearLight);
        this.mDual.SetSel(mACInfo.fgDual);
        this.mAc.SetSel(mACInfo.fgAC);
        this.mAuto.SetSel(mACInfo.nAutoLight);
        this.mMaxFront.SetSel(mACInfo.fgMaxFornt);
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
            Log.e(CanBaseActivity.TAG, "set Temp text Exception!");
        }
        CanJni.TouaregGetACData(this.mAcData);
        this.mBtnAutoAQS.SetSel(this.mAcData.fgAutoRecircule);
        this.mBtnProfile.setText(String.valueOf(this.mStrProfileText) + this.mStrProfileVal[this.mAcData.Profile & 3]);
    }

    public void onClick(View v) {
        CanDataInfo.CAN_ACInfo mACInfo = Can.mACInfo;
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                int param = this.mAcData.Profile + 1;
                if (param >= 3) {
                    param = 0;
                }
                CanJni.TouaregACSet(2, param);
                return;
            case 3:
                CanJni.TouaregACSet(1, Neg(this.mAcData.fgAutoRecircule));
                return;
            case 4:
                int ltHot = (mACInfo.nLtChairHot & 3) + 1;
                if (ltHot > 3) {
                    ltHot = 0;
                }
                CanJni.GolfSendCmd(173, ltHot);
                return;
            case 5:
                int RtHot = (mACInfo.nRtChairHot & 3) + 1;
                if (RtHot > 3) {
                    RtHot = 0;
                }
                CanJni.GolfSendCmd(174, RtHot);
                return;
            default:
                return;
        }
    }
}
