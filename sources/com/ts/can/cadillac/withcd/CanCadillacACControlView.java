package com.ts.can.cadillac.withcd;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanCadillacACControlView implements View.OnTouchListener {
    public static final int ITEM_BTN_AC = 12;
    public static final int ITEM_BTN_AUTO = 6;
    public static final int ITEM_BTN_CLOSE = 16;
    public static final int ITEM_BTN_FRONT_WIND = 14;
    public static final int ITEM_BTN_HOME = 20;
    public static final int ITEM_BTN_LOOP = 15;
    public static final int ITEM_BTN_LT_HOT = 21;
    public static final int ITEM_BTN_LT_TEMP_DEC = 2;
    public static final int ITEM_BTN_LT_TEMP_INC = 1;
    public static final int ITEM_BTN_LT_WIND = 23;
    public static final int ITEM_BTN_MODE_DEC = 7;
    public static final int ITEM_BTN_MODE_INC = 8;
    public static final int ITEM_BTN_OFF = 5;
    public static final int ITEM_BTN_REAR_HOT = 13;
    public static final int ITEM_BTN_RT_HOT = 22;
    public static final int ITEM_BTN_RT_TEMP_DEC = 11;
    public static final int ITEM_BTN_RT_TEMP_INC = 10;
    public static final int ITEM_BTN_RT_WIND = 24;
    public static final int ITEM_BTN_SYNC = 9;
    public static final int ITEM_BTN_VOL = 19;
    public static final int ITEM_BTN_VOL_DEC = 18;
    public static final int ITEM_BTN_VOL_INC = 17;
    public static final int ITEM_BTN_WIND_DEC = 3;
    public static final int ITEM_BTN_WIND_INC = 4;
    private static final String TAG = "CanCadillacACStatusView";
    private static byte[] fileMsg = new byte[8];
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAuto;
    private ParamButton mBtnClose;
    private ParamButton mBtnFrontWind;
    private ParamButton mBtnHome;
    private ParamButton mBtnLoopMode;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnLtWind;
    private ParamButton mBtnModeDec;
    private ParamButton mBtnModeInc;
    private ParamButton mBtnOff;
    private ParamButton mBtnRearHot;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnRtWind;
    private ParamButton mBtnSync;
    private ParamButton mBtnVol;
    private ParamButton mBtnVolDecrease;
    private ParamButton mBtnVolIncrease;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private Context mContext;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            CanCadillacACControlView.this.updateAC();
            CanCadillacACControlView.this.mHandler.sendEmptyMessageDelayed(0, 200);
        }
    };
    private RelativeLayout mLayout;
    private OnShowACListener mListener;
    private int[] mLtHotIcons = {R.drawable.cadg_ac_lchair_up, R.drawable.cadg_ac_lchair01_dn, R.drawable.cadg_ac_lchair02_dn, R.drawable.cadg_ac_lchair_dn};
    private ParamButton[] mLtHots = new ParamButton[3];
    private ParamButton[] mLtWinds = new ParamButton[3];
    private RelativeLayoutManager mManager;
    private int[] mRtHotIcons = {R.drawable.cadg_ac_rchair_up, R.drawable.cadg_ac_rchair01_dn, R.drawable.cadg_ac_rchair02_dn, R.drawable.cadg_ac_rchair_dn};
    private ParamButton[] mRtHots = new ParamButton[3];
    private ParamButton[] mRtWinds = new ParamButton[3];

    public interface OnShowACListener {
        void onShowAC();

        void onUpdateFinish();
    }

    public void setOnUpdateListener(OnShowACListener listener) {
        this.mListener = listener;
    }

    public CanCadillacACControlView(Context context) {
        this.mContext = context;
        this.mLayout = new RelativeLayout(context);
        this.mManager = new RelativeLayoutManager(this.mLayout);
    }

    public RelativeLayout getView() {
        if (CanJni.GetCanFsTp() == 118) {
            if (CanJni.GetSubType() == 1 || CanJni.GetSubType() == 3 || CanJni.GetSubType() == 2 || CanJni.GetSubType() == 7) {
                initHighViews();
            } else {
                initLowViews();
            }
        } else if (CanJni.GetCanFsTp() != 88) {
            return null;
        } else {
            if (CanJni.GetSubType() == 6) {
                initLowViews();
            } else {
                initSbHighViews();
            }
        }
        this.mHandler.sendEmptyMessageDelayed(0, 200);
        return this.mLayout;
    }

    private void initHighViews() {
        this.mManager.AddImage(0, 0, 768, 287).setBackgroundResource(R.drawable.cadg_ac_bg02);
        this.mBtnClose = AddBtn(16, 24, 0, 88, 34, R.drawable.cadg_ac_close_up, R.drawable.cadg_ac_close_dn);
        this.mBtnLtTempInc = AddBtn(1, 58, 40, 73, 50, R.drawable.cad_ac_lred_up, R.drawable.cad_ac_lred_dn);
        this.mBtnLtTempDec = AddBtn(2, 80, 112, 73, 50, R.drawable.cad_ac_lblue_up, R.drawable.cad_ac_lblue_dn);
        this.mBtnRtTempInc = AddBtn(10, 636, 40, 73, 50, R.drawable.cad_ac_rred_up, R.drawable.cad_ac_rred_dn);
        this.mBtnRtTempDec = AddBtn(11, CanCameraUI.BTN_CCH9_MODE7, 112, 73, 50, R.drawable.cad_ac_rblue_up, R.drawable.cad_ac_rblue_dn);
        this.mBtnVolDecrease = AddBtn(18, 133, 27, 88, 47, R.drawable.cadg_ac_up_up, R.drawable.cadg_ac_up_dn);
        this.mBtnVolIncrease = AddBtn(17, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST7, 27, 88, 47, R.drawable.cadg_ac_dn_up, R.drawable.cadg_ac_dn_dn);
        this.mBtnVol = AddBtn(19, KeyDef.RKEY_RADIO_1S, 24, 122, 54, R.drawable.cadg_ac_vol_up, R.drawable.cadg_ac_vol_dn);
        this.mBtnHome = AddBtn(20, 658, 0, 88, 34, R.drawable.cadg_ac_home_up, R.drawable.cadg_ac_home_dn);
        this.mBtnWindDec = AddBtn(3, Can.CAN_CC_WC, 109, 122, 54, R.drawable.cad_ac_refan_up, R.drawable.cad_ac_refan_dn);
        this.mBtnWindInc = AddBtn(4, 493, 109, 122, 54, R.drawable.cad_ac_addfan_up, R.drawable.cad_ac_addfan_dn);
        this.mBtnOff = AddBtn(5, KeyDef.RKEY_RADIO_1S, 118, 122, 54, R.drawable.cad_ac_off_up, R.drawable.cad_ac_off_dn);
        this.mBtnAuto = AddBtn(6, 188, 199, 73, 50, R.drawable.cad_ac_auto_up, R.drawable.cad_ac_auto_dn);
        this.mBtnFrontWind = AddBtn(14, 284, 199, 88, 47, R.drawable.cad_ac_fwind_up, R.drawable.cad_ac_fwind_dn);
        this.mBtnRearHot = AddBtn(13, 377, 199, 88, 47, R.drawable.cadg_ac_rear_up, R.drawable.cadg_ac_rear_dn);
        this.mBtnLoopMode = AddBtn(15, 477, 199, 88, 47, R.drawable.cad_ac_wxh_up, R.drawable.cad_ac_wxh_dn);
        this.mBtnLtHot = AddBtn(21, 7, 166, 88, 47, R.drawable.cadg_ac_lchair_up, R.drawable.cadg_ac_lchair_dn);
        this.mBtnRtHot = AddBtn(22, 674, 166, 88, 47, R.drawable.cadg_ac_rchair_up, R.drawable.cadg_ac_rchair_dn);
        this.mBtnLtWind = AddBtn(23, 37, Can.CAN_FORD_SYNC3, 88, 47, R.drawable.cadg_ac_lfan_up, R.drawable.cadg_ac_lfan_dn);
        this.mBtnRtWind = AddBtn(24, CanCameraUI.BTN_LANDWIND_2D_RIGHT, Can.CAN_FORD_SYNC3, 88, 47, R.drawable.cadg_ac_rfan_up, R.drawable.cadg_ac_rfan_dn);
        this.mLtHots[2] = AddBtn(-1, 16, Can.CAN_ZH_H530, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mLtHots[1] = AddBtn(-1, 26, Can.CAN_GM_CAPTIVA_OD, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mLtHots[0] = AddBtn(-1, 36, Can.CAN_FLAT_RZC, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtHots[2] = AddBtn(-1, 735, Can.CAN_ZH_H530, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtHots[1] = AddBtn(-1, 725, Can.CAN_GM_CAPTIVA_OD, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtHots[0] = AddBtn(-1, 715, Can.CAN_FLAT_RZC, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
    }

    private void initLowViews() {
        this.mManager.AddImage(0, 0, 768, 287).setBackgroundResource(R.drawable.cad_ac_bg02);
        this.mBtnLtTempInc = AddBtn(1, 58, 31, 73, 50, R.drawable.cad_ac_lred_up, R.drawable.cad_ac_lred_dn);
        this.mBtnLtTempDec = AddBtn(2, 80, 108, 73, 50, R.drawable.cad_ac_lblue_up, R.drawable.cad_ac_lblue_dn);
        this.mBtnWindDec = AddBtn(3, 147, 16, 122, 54, R.drawable.cad_ac_refan_up, R.drawable.cad_ac_refan_dn);
        this.mBtnWindInc = AddBtn(4, 500, 16, 122, 54, R.drawable.cad_ac_addfan_up, R.drawable.cad_ac_addfan_dn);
        this.mBtnOff = AddBtn(5, KeyDef.RKEY_RADIO_1S, 16, 122, 54, R.drawable.cad_ac_off_up, R.drawable.cad_ac_off_dn);
        this.mBtnAuto = AddBtn(6, Can.CAN_CC_WC, 102, 73, 50, R.drawable.cad_ac_auto_up, R.drawable.cad_ac_auto_dn);
        this.mBtnModeDec = AddBtn(7, Can.CAN_NISSAN_RICH6_WC, 102, 73, 50, R.drawable.cad_ac_reduce_up, R.drawable.cad_ac_reduce_dn);
        this.mBtnModeInc = AddBtn(8, 459, 102, 73, 50, R.drawable.cad_ac_add_up, R.drawable.cad_ac_add_dn);
        this.mBtnSync = AddBtn(9, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST3, 102, 73, 50, R.drawable.cad_ac_sync_up, R.drawable.cad_ac_sync_dn);
        this.mBtnRtTempInc = AddBtn(10, 636, 31, 73, 50, R.drawable.cad_ac_rred_up, R.drawable.cad_ac_rred_dn);
        this.mBtnRtTempDec = AddBtn(11, CanCameraUI.BTN_CCH9_MODE7, 108, 73, 50, R.drawable.cad_ac_rblue_up, R.drawable.cad_ac_rblue_dn);
        this.mBtnAc = AddBtn(12, 188, 199, 88, 47, R.drawable.cad_ac_ac_up, R.drawable.cad_ac_ac_dn);
        this.mBtnRearHot = AddBtn(13, 284, 199, 88, 47, R.drawable.cad_ac_bwind_up, R.drawable.cad_ac_bwind_dn);
        this.mBtnFrontWind = AddBtn(14, 377, 199, 88, 47, R.drawable.cad_ac_fwind_up, R.drawable.cad_ac_fwind_dn);
        this.mBtnLoopMode = AddBtn(15, 477, 199, 88, 47, R.drawable.cad_ac_wxh_up, R.drawable.cad_ac_wxh_dn);
    }

    private void initSbHighViews() {
        this.mManager.AddImage(0, 0, 768, 287).setBackgroundResource(R.drawable.cadg_ac_bg02);
        this.mBtnClose = AddBtn(16, 24, 0, 88, 34, R.drawable.cadg_ac_close_up, R.drawable.cadg_ac_close_dn);
        this.mBtnLtTempInc = AddBtn(1, 58, 40, 73, 50, R.drawable.cad_ac_lred_up, R.drawable.cad_ac_lred_dn);
        this.mBtnLtTempDec = AddBtn(2, 80, 112, 73, 50, R.drawable.cad_ac_lblue_up, R.drawable.cad_ac_lblue_dn);
        this.mBtnRtTempInc = AddBtn(10, 636, 40, 73, 50, R.drawable.cad_ac_rred_up, R.drawable.cad_ac_rred_dn);
        this.mBtnRtTempDec = AddBtn(11, CanCameraUI.BTN_CCH9_MODE7, 112, 73, 50, R.drawable.cad_ac_rblue_up, R.drawable.cad_ac_rblue_dn);
        this.mBtnVolDecrease = AddBtn(18, 133, 27, 88, 47, R.drawable.cadg_ac_up_up, R.drawable.cadg_ac_up_dn);
        this.mBtnVolIncrease = AddBtn(17, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST7, 27, 88, 47, R.drawable.cadg_ac_dn_up, R.drawable.cadg_ac_dn_dn);
        this.mBtnVol = AddBtn(19, KeyDef.RKEY_RADIO_1S, 24, 122, 54, R.drawable.cadg_ac_vol_up, R.drawable.cadg_ac_vol_dn);
        this.mBtnHome = AddBtn(20, 658, 0, 88, 34, R.drawable.cadg_ac_home_up, R.drawable.cadg_ac_home_dn);
        this.mBtnWindDec = AddBtn(3, Can.CAN_CC_WC, 109, 122, 54, R.drawable.cad_ac_refan_up, R.drawable.cad_ac_refan_dn);
        this.mBtnWindInc = AddBtn(4, 493, 109, 122, 54, R.drawable.cad_ac_addfan_up, R.drawable.cad_ac_addfan_dn);
        this.mBtnOff = AddBtn(5, KeyDef.RKEY_RADIO_1S, 118, 122, 54, R.drawable.cad_ac_off_up, R.drawable.cad_ac_off_dn);
        this.mBtnAuto = AddBtn(6, 188, 199, 73, 50, R.drawable.cad_ac_auto_up, R.drawable.cad_ac_auto_dn);
        this.mBtnFrontWind = AddBtn(14, 284, 199, 88, 47, R.drawable.cad_ac_fwind_up, R.drawable.cad_ac_fwind_dn);
        this.mBtnRearHot = AddBtn(13, 377, 199, 88, 47, R.drawable.cadg_ac_rear_up, R.drawable.cadg_ac_rear_dn);
        this.mBtnLoopMode = AddBtn(15, 477, 199, 88, 47, R.drawable.cad_ac_wxh_up, R.drawable.cad_ac_wxh_dn);
        this.mBtnLtHot = AddBtn(21, 17, 166, 88, 47, R.drawable.cadg_ac_lchair_up, R.drawable.cadg_ac_lchair_dn);
        this.mBtnRtHot = AddBtn(22, 664, 166, 88, 47, R.drawable.cadg_ac_rchair_up, R.drawable.cadg_ac_rchair_dn);
        this.mBtnLtWind = AddBtn(23, 37, Can.CAN_FORD_SYNC3, 88, 47, R.drawable.cadg_ac_lfan_up, R.drawable.cadg_ac_lfan_dn);
        this.mBtnRtWind = AddBtn(24, CanCameraUI.BTN_LANDWIND_2D_RIGHT, Can.CAN_FORD_SYNC3, 88, 47, R.drawable.cadg_ac_rfan_up, R.drawable.cadg_ac_rfan_dn);
        this.mLtHots[2] = AddBtn(-1, 9, 176, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mLtHots[1] = AddBtn(-1, 19, 191, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mLtHots[0] = AddBtn(-1, 29, Can.CAN_SAIL_RW550_MG6_WC, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtHots[2] = AddBtn(-1, 741, 176, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtHots[1] = AddBtn(-1, 731, 191, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtHots[0] = AddBtn(-1, 721, Can.CAN_SAIL_RW550_MG6_WC, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mLtWinds[2] = AddBtn(-1, 26, Can.CAN_SE_DX7_RZC, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mLtWinds[1] = AddBtn(-1, 36, Can.CAN_FORD_ESCORT_LY, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mLtWinds[0] = AddBtn(-1, 46, 268, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtWinds[2] = AddBtn(-1, 725, Can.CAN_SE_DX7_RZC, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtWinds[1] = AddBtn(-1, 715, Can.CAN_FORD_ESCORT_LY, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtWinds[0] = AddBtn(-1, 705, 268, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
    }

    private void ResetXjrData() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        this.mAcInfo = Can.mACInfo;
        this.mAcInfo.Update = 0;
        this.mBtnOff.SetSel(this.mAcInfo.PWR == 0 ? 1 : 0);
        if (this.mAcInfo.PWR == 0) {
            this.mBtnFrontWind.SetSel(0);
            this.mBtnAuto.SetSel(0);
            if (this.mBtnAc != null) {
                this.mBtnAc.SetSel(0);
            }
            if (this.mBtnSync != null) {
                this.mBtnSync.SetSel(0);
            }
        } else {
            this.mBtnFrontWind.SetSel(this.mAcInfo.fgDFBL);
            this.mBtnAuto.SetSel(this.mAcInfo.nAutoLight);
            if (this.mBtnAc != null) {
                this.mBtnAc.SetSel(this.mAcInfo.fgAC);
            }
            if (this.mBtnSync != null) {
                this.mBtnSync.SetSel(this.mAcInfo.fgDual);
            }
        }
        this.mBtnRearHot.SetSel(this.mAcInfo.fgRearLight);
        if (this.mAcInfo.fgAQS == 1) {
            this.mBtnLoopMode.setDrawable(R.drawable.cad_ac_a_up, R.drawable.cad_ac_a_dn);
        } else if (this.mAcInfo.fgInnerLoop != 0) {
            this.mBtnLoopMode.setDrawable(R.drawable.cad_ac_nxh_dn, R.drawable.cad_ac_nxh_dn);
        } else {
            this.mBtnLoopMode.setDrawable(R.drawable.cad_ac_wxh_up, R.drawable.cad_ac_wxh_dn);
        }
        if (this.mBtnLtWind != null) {
            int ltWind = this.mAcInfo.nLtChairWind;
            this.mBtnLtWind.SetSel(ltWind);
            if (this.mAcInfo.nLtChairWind > 0 && this.mAcInfo.nLtChairHot > 0) {
                ltWind = 0;
            }
            for (int i = 0; i < this.mLtHots.length; i++) {
                ParamButton paramButton = this.mLtHots[i];
                if (i < ltWind) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                paramButton.setSelected(z4);
            }
        }
        if (this.mBtnRtWind != null) {
            int rtWind = this.mAcInfo.nRtChairWind;
            this.mBtnRtWind.SetSel(rtWind);
            if (this.mAcInfo.nRtChairWind > 0 && this.mAcInfo.nRtChairHot > 0) {
                rtWind = 0;
            }
            for (int i2 = 0; i2 < this.mRtHots.length; i2++) {
                ParamButton paramButton2 = this.mRtHots[i2];
                if (i2 < rtWind) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                paramButton2.setSelected(z3);
            }
        }
        if (this.mBtnLtHot != null) {
            int ltChairHot = this.mAcInfo.nLtChairHot;
            this.mBtnLtHot.SetSel(ltChairHot);
            for (int i3 = 0; i3 < this.mLtHots.length; i3++) {
                ParamButton paramButton3 = this.mLtHots[i3];
                if (i3 < ltChairHot) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                paramButton3.setSelected(z2);
            }
        }
        if (this.mBtnRtHot != null) {
            int rtChairHot = this.mAcInfo.nRtChairHot;
            this.mBtnRtHot.SetSel(rtChairHot);
            for (int i4 = 0; i4 < this.mRtHots.length; i4++) {
                ParamButton paramButton4 = this.mRtHots[i4];
                if (i4 < rtChairHot) {
                    z = true;
                } else {
                    z = false;
                }
                paramButton4.setSelected(z);
            }
        }
    }

    private void ResetSbData() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        this.mAcInfo = Can.mACInfo;
        this.mAcInfo.Update = 0;
        this.mBtnOff.SetSel(this.mAcInfo.PWR == 0 ? 1 : 0);
        if (this.mAcInfo.PWR == 0) {
            this.mBtnFrontWind.SetSel(0);
            this.mBtnAuto.SetSel(0);
            if (this.mBtnAc != null) {
                this.mBtnAc.SetSel(0);
            }
            if (this.mBtnSync != null) {
                this.mBtnSync.SetSel(0);
            }
        } else {
            this.mBtnFrontWind.SetSel(this.mAcInfo.fgDFBL);
            this.mBtnAuto.SetSel(this.mAcInfo.nAutoLight);
            if (this.mBtnAc != null) {
                this.mBtnAc.SetSel(this.mAcInfo.fgAC);
            }
            if (this.mBtnSync != null) {
                this.mBtnSync.SetSel(this.mAcInfo.fgDual);
            }
        }
        this.mBtnRearHot.SetSel(this.mAcInfo.fgRearLight);
        if (this.mAcInfo.fgAQS == 1) {
            this.mBtnLoopMode.setDrawable(R.drawable.cad_ac_a_up, R.drawable.cad_ac_a_dn);
        } else if (this.mAcInfo.fgInnerLoop != 0) {
            this.mBtnLoopMode.setDrawable(R.drawable.cad_ac_nxh_dn, R.drawable.cad_ac_nxh_dn);
        } else {
            this.mBtnLoopMode.setDrawable(R.drawable.cad_ac_wxh_up, R.drawable.cad_ac_wxh_dn);
        }
        if (this.mBtnLtWind != null) {
            int ltWind = this.mAcInfo.nLtChairWind;
            this.mBtnLtWind.SetSel(ltWind);
            for (int i = 0; i < this.mLtHots.length; i++) {
                ParamButton paramButton = this.mLtWinds[i];
                if (i < ltWind) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                paramButton.setSelected(z4);
            }
        }
        if (this.mBtnRtWind != null) {
            int rtWind = this.mAcInfo.nRtChairWind;
            this.mBtnRtWind.SetSel(rtWind);
            for (int i2 = 0; i2 < this.mRtHots.length; i2++) {
                ParamButton paramButton2 = this.mRtWinds[i2];
                if (i2 < rtWind) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                paramButton2.setSelected(z3);
            }
        }
        if (this.mBtnLtHot != null) {
            int ltChairHot = this.mAcInfo.nLtChairHot;
            this.mBtnLtHot.SetSel(ltChairHot);
            for (int i3 = 0; i3 < this.mLtHots.length; i3++) {
                ParamButton paramButton3 = this.mLtHots[i3];
                if (i3 < ltChairHot) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                paramButton3.setSelected(z2);
            }
        }
        if (this.mBtnRtHot != null) {
            int rtChairHot = this.mAcInfo.nRtChairHot;
            this.mBtnRtHot.SetSel(rtChairHot);
            for (int i4 = 0; i4 < this.mRtHots.length; i4++) {
                ParamButton paramButton4 = this.mRtHots[i4];
                if (i4 < rtChairHot) {
                    z = true;
                } else {
                    z = false;
                }
                paramButton4.setSelected(z);
            }
        }
    }

    private void ResetData() {
        if (CanJni.GetCanFsTp() == 88) {
            ResetSbData();
        } else {
            ResetXjrData();
        }
    }

    public void updateAC() {
        Can.updateAC();
        ResetData();
        if (this.mListener != null) {
            this.mListener.onUpdateFinish();
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            Log.d(TAG, "****ACTION_DOWN*****");
            v.setSelected(true);
            if (this.mListener != null) {
                this.mListener.onShowAC();
            }
            if (CanJni.GetCanFsTp() != 88) {
                switch (Id) {
                    case 1:
                        CanJni.CadillacWithCDSendKey(64, 1);
                        break;
                    case 2:
                        CanJni.CadillacWithCDSendKey(65, 1);
                        break;
                    case 3:
                        CanJni.CadillacWithCDSendKey(69, 1);
                        break;
                    case 4:
                        CanJni.CadillacWithCDSendKey(68, 1);
                        break;
                    case 5:
                        CanJni.CadillacWithCDSendKey(74, 1);
                        break;
                    case 6:
                        CanJni.CadillacWithCDSendKey(75, 1);
                        break;
                    case 7:
                        CanJni.CadillacWithCDSendKey(73, 1);
                        break;
                    case 8:
                        CanJni.CadillacWithCDSendKey(72, 1);
                        break;
                    case 9:
                        CanJni.CadillacWithCDSendKey(70, 1);
                        break;
                    case 10:
                        CanJni.CadillacWithCDSendKey(66, 1);
                        break;
                    case 11:
                        CanJni.CadillacWithCDSendKey(67, 1);
                        break;
                    case 12:
                        CanJni.CadillacWithCDSendKey(71, 1);
                        break;
                    case 13:
                        CanJni.CadillacWithCDSendKey(77, 1);
                        break;
                    case 14:
                        CanJni.CadillacWithCDSendKey(76, 1);
                        break;
                    case 15:
                        CanJni.CadillacWithCDSendKey(78, 1);
                        break;
                    case 16:
                        CanJni.CadillacWithCDSendKey(39, 1);
                        break;
                    case 17:
                        CanJni.CadillacWithCDSendKey(49, 1);
                        break;
                    case 18:
                        CanJni.CadillacWithCDSendKey(48, 1);
                        break;
                    case 20:
                        CanJni.CadillacWithCDSendKey(38, 1);
                        break;
                    case 21:
                        CanJni.CadillacWithCDSendKey(80, 1);
                        break;
                    case 22:
                        CanJni.CadillacWithCDSendKey(81, 1);
                        break;
                    case 23:
                        CanJni.CadillacWithCDSendKey(82, 1);
                        break;
                    case 24:
                        CanJni.CadillacWithCDSendKey(83, 1);
                        break;
                }
            } else {
                switch (Id) {
                    case 1:
                        CanJni.GmSbAcSet(2, 1);
                        break;
                    case 2:
                        CanJni.GmSbAcSet(2, 2);
                        break;
                    case 3:
                        CanJni.GmSbAcSet(5, 2);
                        break;
                    case 4:
                        CanJni.GmSbAcSet(5, 1);
                        break;
                    case 5:
                        CanJni.GmSbAcSet(8, 1);
                        break;
                    case 6:
                        CanJni.GmSbAcSet(4, 1);
                        break;
                    case 7:
                        CanJni.GmSbAcSet(6, Can.CAN_SITECHDEV_CW);
                        break;
                    case 8:
                        CanJni.GmSbAcSet(6, Can.CAN_MZD_LUOMU);
                        break;
                    case 9:
                        CanJni.GmSbAcSet(11, 1);
                        break;
                    case 10:
                        CanJni.GmSbAcSet(7, 1);
                        break;
                    case 11:
                        CanJni.GmSbAcSet(7, 2);
                        break;
                    case 12:
                        CanJni.GmSbAcSet(3, 1);
                        break;
                    case 13:
                        CanJni.GmSbAcSet(9, 1);
                        break;
                    case 14:
                        CanJni.GmSbAcSet(10, 1);
                        break;
                    case 15:
                        CanJni.GmSbAcSet(1, 1);
                        break;
                    case 16:
                        CanJni.GmSbCarKeyCtl(20, 1);
                        break;
                    case 17:
                        CanJni.GmSbCarKeyCtl(16, 1);
                        break;
                    case 18:
                        CanJni.GmSbCarKeyCtl(17, 1);
                        break;
                    case 20:
                        CanJni.GmSbCarKeyCtl(11, 1);
                        break;
                    case 21:
                        CanJni.GmSbAcSet(12, 1);
                        break;
                    case 22:
                        CanJni.GmSbAcSet(13, 1);
                        break;
                    case 23:
                        CanJni.GmSbAcSet(14, 1);
                        break;
                    case 24:
                        CanJni.GmSbAcSet(15, 1);
                        break;
                }
            }
        } else if (1 == action) {
            Log.d(TAG, "****ACTION_UP*****");
            v.setSelected(false);
            if (CanJni.GetCanFsTp() == 88) {
                switch (Id) {
                    case 1:
                        CanJni.GmSbAcSet(2, 0);
                        break;
                    case 2:
                        CanJni.GmSbAcSet(2, 0);
                        break;
                    case 3:
                        CanJni.GmSbAcSet(5, 0);
                        break;
                    case 4:
                        CanJni.GmSbAcSet(5, 0);
                        break;
                    case 5:
                        CanJni.GmSbAcSet(8, 0);
                        break;
                    case 6:
                        CanJni.GmSbAcSet(4, 0);
                        break;
                    case 7:
                        CanJni.GmSbAcSet(6, 0);
                        break;
                    case 8:
                        CanJni.GmSbAcSet(6, 0);
                        break;
                    case 9:
                        CanJni.GmSbAcSet(11, 0);
                        break;
                    case 10:
                        CanJni.GmSbAcSet(7, 0);
                        break;
                    case 11:
                        CanJni.GmSbAcSet(7, 0);
                        break;
                    case 12:
                        CanJni.GmSbAcSet(3, 0);
                        break;
                    case 13:
                        CanJni.GmSbAcSet(9, 0);
                        break;
                    case 14:
                        CanJni.GmSbAcSet(10, 0);
                        break;
                    case 15:
                        CanJni.GmSbAcSet(1, 0);
                        break;
                    case 16:
                        CanJni.GmSbCarKeyCtl(20, 0);
                        break;
                    case 17:
                        CanJni.GmSbCarKeyCtl(16, 0);
                        break;
                    case 18:
                        CanJni.GmSbCarKeyCtl(17, 0);
                        break;
                    case 20:
                        CanJni.GmSbCarKeyCtl(11, 0);
                        break;
                    case 21:
                        CanJni.GmSbAcSet(12, 0);
                        break;
                    case 22:
                        CanJni.GmSbAcSet(13, 0);
                        break;
                    case 23:
                        CanJni.GmSbAcSet(14, 0);
                        break;
                    case 24:
                        CanJni.GmSbAcSet(15, 0);
                        break;
                }
            } else {
                CanJni.CadillacWithCDSendKey(0, 0);
            }
        }
        return true;
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
    public CustomTextView AddTemp(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(55);
        temp.setText("88.8℃");
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddText(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(28);
        temp.setText(TXZResourceManager.STYLE_DEFAULT);
        temp.setGravity(17);
        return temp;
    }
}
