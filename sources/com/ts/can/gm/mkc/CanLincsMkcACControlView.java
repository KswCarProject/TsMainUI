package com.ts.can.gm.mkc;

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
import com.ts.can.toyota.dj.CanToyotaDJCarDeviceView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;

public class CanLincsMkcACControlView implements View.OnTouchListener {
    public static final int ITEM_BTN_AC = 20;
    public static final int ITEM_BTN_AC_MAX = 11;
    public static final int ITEM_BTN_AC_POWER = 6;
    public static final int ITEM_BTN_AUTO = 19;
    public static final int ITEM_BTN_DUAL = 16;
    public static final int ITEM_BTN_ECD1_NEXT = 27;
    public static final int ITEM_BTN_ECD1_PRE = 26;
    public static final int ITEM_BTN_ECD2_NEXT = 29;
    public static final int ITEM_BTN_ECD2_PRE = 28;
    public static final int ITEM_BTN_FRONT_WIND = 10;
    public static final int ITEM_BTN_HOT_WHEEL = 25;
    public static final int ITEM_BTN_LOOP = 14;
    public static final int ITEM_BTN_LT_AC_CHAIR = 21;
    public static final int ITEM_BTN_LT_HOT_CHAIR = 23;
    public static final int ITEM_BTN_LT_TEMP_DEC = 8;
    public static final int ITEM_BTN_LT_TEMP_INC = 7;
    public static final int ITEM_BTN_MAX = 9;
    public static final int ITEM_BTN_MODE_DOWN = 18;
    public static final int ITEM_BTN_MODE_PX = 17;
    public static final int ITEM_BTN_NEXT = 4;
    public static final int ITEM_BTN_POWER = 1;
    public static final int ITEM_BTN_PRE = 2;
    public static final int ITEM_BTN_RT_AC_CHAIR = 22;
    public static final int ITEM_BTN_RT_HOT_CHAIR = 24;
    public static final int ITEM_BTN_RT_TEMP_DEC = 13;
    public static final int ITEM_BTN_RT_TEMP_INC = 12;
    public static final int ITEM_BTN_R_WIN_HOT = 15;
    public static final int ITEM_BTN_SOURCE = 3;
    public static final int ITEM_BTN_WIND = 5;
    private static final String TAG = "CanMkcACControlView";
    private static byte[] fileMsg = new byte[8];
    private int[] WindImage = {R.drawable.lin_ac_fan01_dn, R.drawable.lin_ac_fan02_dn, R.drawable.lin_ac_fan03_dn, R.drawable.lin_ac_fan04_dn, R.drawable.lin_ac_fan05_dn, R.drawable.lin_ac_fan06_dn, R.drawable.lin_ac_fan01_up, R.drawable.lin_ac_fan02_up, R.drawable.lin_ac_fan03_up, R.drawable.lin_ac_fan04_up, R.drawable.lin_ac_fan05_up, R.drawable.lin_ac_fan06_up};
    CustomImgView backgroundView;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAcMax;
    private ParamButton mBtnAcPower;
    private ParamButton mBtnAuto;
    private ParamButton mBtnDual;
    private ParamButton mBtnFrontWind;
    private ParamButton mBtnLoopMode;
    private ParamButton mBtnLtChairAc;
    private ParamButton mBtnLtChairHot;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnMax;
    private ParamButton mBtnModeDown;
    private ParamButton mBtnModePx;
    private ParamButton mBtnNext;
    private ParamButton mBtnPower;
    private ParamButton mBtnPre;
    private ParamButton mBtnRtChairAc;
    private ParamButton mBtnRtChairHot;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnRwindHot;
    private ParamButton mBtnSource;
    private ParamButton mBtnWheelHot;
    private ParamButton mBtnWind;
    private Context mContext;
    private CustomImgView[] mEcdIcon = new CustomImgView[2];
    private ParamButton[] mEcdKey = new ParamButton[4];
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            CanLincsMkcACControlView.this.updateAC();
            CanLincsMkcACControlView.this.mHandler.sendEmptyMessageDelayed(0, 200);
        }
    };
    private CustomImgView[] mHl = new CustomImgView[9];
    private CustomImgView[] mHlShort = new CustomImgView[12];
    private RelativeLayout mLayout;
    private OnShowACListener mListener;
    private RelativeLayoutManager mManager;
    private CustomImgView[] mWindVal = new CustomImgView[7];

    public interface OnShowACListener {
        void onShowAC();
    }

    public void setOnUpdateListener(OnShowACListener listener) {
        this.mListener = listener;
    }

    public CanLincsMkcACControlView(Context context) {
        this.mContext = context;
        this.mLayout = new RelativeLayout(context);
        this.mManager = new RelativeLayoutManager(this.mLayout);
    }

    public RelativeLayout getView() {
        if (CanJni.GetCanFsTp() != 88) {
            return null;
        }
        addChildViews();
        return this.mLayout;
    }

    private void addChildViews() {
        this.backgroundView = this.mManager.AddImage(0, 0, CanToyotaDJCarDeviceView.ITEM_PLAY, 719);
        this.backgroundView.setBackgroundResource(R.drawable.lin_ac_bg01);
        this.mBtnPower = AddBtn(1, 191, Can.CAN_NISSAN_RICH6_WC, 72, 68, R.drawable.lin_ac_shut_up, R.drawable.lin_ac_shut_dn);
        this.mBtnPre = AddBtn(2, 265, Can.CAN_NISSAN_RICH6_WC, 72, 68, R.drawable.lin_ac_prve_up, R.drawable.lin_ac_prve_dn);
        this.mBtnSource = AddBtn(3, KeyDef.RKEY_res2, Can.CAN_NISSAN_RICH6_WC, 92, 68, R.drawable.lin_ac_source_up, R.drawable.lin_ac_source_dn);
        this.mBtnNext = AddBtn(4, 433, Can.CAN_NISSAN_RICH6_WC, 72, 68, R.drawable.lin_ac_next_up, R.drawable.lin_ac_next_dn);
        this.mBtnWind = AddBtn(5, 507, Can.CAN_NISSAN_RICH6_WC, 72, 68, R.drawable.lin_ac_fan_up, R.drawable.lin_ac_fan_dn);
        this.mHl[0] = AddImage(81, 376, 35, 8, R.drawable.lin_ac_1pro_up);
        this.mHl[1] = AddImage(213, 371, 35, 8, R.drawable.lin_ac_1pro_up);
        this.mHl[2] = AddImage(514, 371, 35, 8, R.drawable.lin_ac_1pro_up);
        this.mHl[3] = AddImage(653, 376, 35, 8, R.drawable.lin_ac_1pro_up);
        this.mHl[4] = AddImage(108, CanCameraUI.BTN_TRUMPCHI_GS7_MODE6, 35, 8, R.drawable.lin_ac_1pro_up);
        this.mHl[5] = AddImage(213, 568, 35, 8, R.drawable.lin_ac_1pro_up);
        this.mHl[6] = AddImage(514, 568, 35, 8, R.drawable.lin_ac_1pro_up);
        this.mHl[7] = AddImage(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE2, CanCameraUI.BTN_TRUMPCHI_GS7_MODE6, 35, 8, R.drawable.lin_ac_1pro_up);
        this.mHl[8] = AddImage(350, CanCameraUI.BTN_CCH9_MODE9, 67, 9, R.drawable.lin_ac_2pro_up);
        this.mWindVal[0] = AddImage(279, 362, 26, 26, R.drawable.lin_ac_ac_icon);
        this.mWindVal[1] = AddImage(KeyDef.RKEY_MEDIA_TITLE, 346, 26, 26, R.drawable.lin_ac_fan01_up);
        this.mWindVal[2] = AddImage(KeyDef.RKEY_RDS_PTY, KeyDef.RKEY_DEL, 26, 26, R.drawable.lin_ac_fan02_up);
        this.mWindVal[3] = AddImage(358, KeyDef.RKEY_RADIO_5S, 26, 26, R.drawable.lin_ac_fan03_up);
        this.mWindVal[4] = AddImage(386, KeyDef.RKEY_RADIO_5S, 26, 26, R.drawable.lin_ac_fan04_up);
        this.mWindVal[5] = AddImage(413, KeyDef.RKEY_DEL, 26, 26, R.drawable.lin_ac_fan05_up);
        this.mWindVal[6] = AddImage(439, 346, 26, 26, R.drawable.lin_ac_fan06_up);
        this.mBtnAcPower = AddBtn(6, 66, 389, 62, 47, R.drawable.lin_ac_closed_up, R.drawable.lin_ac_closed_dn);
        this.mBtnLtTempInc = AddBtn(7, 131, 386, 62, 47, R.drawable.lin_ac_red_up, R.drawable.lin_ac_red_dn);
        this.mBtnRtTempInc = AddBtn(12, CanCameraUI.BTN_CHANA_CS75_MODE7, 386, 62, 47, R.drawable.lin_ac_red_up, R.drawable.lin_ac_red_dn);
        this.mBtnMax = AddBtn(9, 196, 385, 73, 47, R.drawable.lin_ac_max_up, R.drawable.lin_ac_max_dn);
        this.mBtnAcMax = AddBtn(11, 500, 385, 73, 47, R.drawable.lin_ac_maxac_up, R.drawable.lin_ac_maxac_dn);
        this.mBtnLoopMode = AddBtn(14, CanCameraUI.BTN_LANDWIND_2D_FRONT, 389, 62, 47, R.drawable.lin_ac_wxh_up, R.drawable.lin_ac_wxh_dn);
        this.mBtnRwindHot = AddBtn(15, 86, 516, 62, 47, R.drawable.lin_ac_r_up, R.drawable.lin_ac_r_dn);
        this.mBtnLtTempDec = AddBtn(8, 149, 516, 62, 47, R.drawable.lin_ac_blue_up, R.drawable.lin_ac_blue_dn);
        this.mBtnRtTempDec = AddBtn(13, 559, 516, 62, 47, R.drawable.lin_ac_blue_up, R.drawable.lin_ac_blue_dn);
        this.mBtnDual = AddBtn(16, 212, 516, 62, 47, R.drawable.lin_ac_dual_up, R.drawable.lin_ac_dual_dn);
        this.mBtnAuto = AddBtn(19, 496, 516, 62, 47, R.drawable.lin_ac_auto_up, R.drawable.lin_ac_auto_dn);
        this.mBtnAc = AddBtn(20, 622, 516, 62, 47, R.drawable.lin_ac_ac_up, R.drawable.lin_ac_ac_dn);
        this.mBtnFrontWind = AddBtn(10, KeyDef.RKEY_ANGLEUP, 387, 135, 47, R.drawable.lin_ac_wind_up, R.drawable.lin_ac_wind_dn);
        this.mBtnModePx = AddBtn(17, KeyDef.RKEY_ANGLEUP, 439, 135, 47, R.drawable.lin_ac_p1_up, R.drawable.lin_ac_p1_dn);
        this.mBtnModeDown = AddBtn(18, KeyDef.RKEY_ANGLEUP, 490, 135, 47, R.drawable.lin_ac_p2_up, R.drawable.lin_ac_p2_dn);
        this.mBtnLtChairAc = AddBtn(21, 168, 629, 80, 52, R.drawable.lin_ac_acchair_up, R.drawable.lin_ac_acchair_up);
        this.mBtnRtChairAc = AddBtn(22, CanCameraUI.BTN_GEELY_YJX6_MODE1, 629, 80, 52, R.drawable.lin_ac_acchair_up, R.drawable.lin_ac_acchair_up);
        this.mBtnLtChairHot = AddBtn(23, 258, 629, 80, 52, R.drawable.lin_ac_hotchair_up, R.drawable.lin_ac_hotchair_up);
        this.mBtnRtChairHot = AddBtn(24, 430, 629, 80, 52, R.drawable.lin_ac_hotchair_up, R.drawable.lin_ac_hotchair_up);
        this.mBtnWheelHot = AddBtn(25, 345, 629, 80, 52, R.drawable.lin_ac_wheel_up, R.drawable.lin_ac_wheel_up);
        for (int i = 0; i < 3; i++) {
            this.mHlShort[i] = AddImage((i * 24) + 172, CanCameraUI.BTN_CCH9_MODE10, 17, 7, R.drawable.lin_ac_3pro_up);
            this.mHlShort[i + 3] = AddImage((i * 24) + 263, CanCameraUI.BTN_CCH9_MODE10, 17, 7, R.drawable.lin_ac_3pro_up);
            this.mHlShort[i + 6] = AddImage((i * 24) + 440, CanCameraUI.BTN_CCH9_MODE10, 17, 7, R.drawable.lin_ac_3pro_up);
            this.mHlShort[i + 9] = AddImage((i * 24) + CanCameraUI.BTN_CHANA_ALSVINV7_MODE3, CanCameraUI.BTN_CCH9_MODE10, 17, 7, R.drawable.lin_ac_3pro_up);
        }
        this.mEcdIcon[0] = AddImage(33, 212, 115, 115, R.drawable.lin_ac_vol_up);
        this.mEcdIcon[1] = AddImage(622, 212, 115, 115, R.drawable.lin_ac_tune_up);
        this.mEcdKey[0] = AddBtn(26, 1, 190, 70, 58, R.drawable.lin_ac_ljt_up, R.drawable.lin_ac_ljt_dn);
        this.mEcdKey[1] = AddBtn(27, 111, 190, 70, 58, R.drawable.lin_ac_rjt_up, R.drawable.lin_ac_rjt_dn);
        this.mEcdKey[2] = AddBtn(28, 588, 190, 70, 58, R.drawable.lin_ac_ljt_up, R.drawable.lin_ac_ljt_dn);
        this.mEcdKey[3] = AddBtn(29, 698, 190, 70, 58, R.drawable.lin_ac_rjt_up, R.drawable.lin_ac_rjt_dn);
        this.mHandler.sendEmptyMessageDelayed(0, 200);
        if (CanJni.GetSubType() != 4) {
            this.backgroundView.setBackgroundResource(R.drawable.lin_ac_mkz_bg);
            this.mBtnPower.Show(false);
            this.mBtnPre.Show(false);
            this.mBtnSource.Show(false);
            this.mBtnNext.Show(false);
            this.mBtnWind.Show(false);
            this.mEcdIcon[0].Show(false);
            this.mEcdIcon[1].Show(false);
            this.mEcdKey[0].Show(false);
            this.mEcdKey[1].Show(false);
            this.mEcdKey[2].Show(false);
            this.mEcdKey[3].Show(false);
            Log.d(TAG, "**** Can.GM_Sb_Sub_Mkz nViewInit*****");
        }
    }

    private void ResetData() {
        this.mAcInfo = Can.mACInfo;
        if (this.mAcInfo.PWR > 0) {
            this.mHl[0].setImageResource(R.drawable.lin_ac_1pro_dn);
        } else {
            this.mHl[0].setImageResource(R.drawable.lin_ac_1pro_up);
        }
        if (this.mAcInfo.fgMaxFornt > 0) {
            this.mHl[1].setImageResource(R.drawable.lin_ac_1pro_dn);
        } else {
            this.mHl[1].setImageResource(R.drawable.lin_ac_1pro_up);
        }
        if (this.mAcInfo.fgACMax > 0) {
            this.mHl[2].setImageResource(R.drawable.lin_ac_1pro_dn);
        } else {
            this.mHl[2].setImageResource(R.drawable.lin_ac_1pro_up);
        }
        if (this.mAcInfo.fgInnerLoop > 0) {
            this.mHl[3].setImageResource(R.drawable.lin_ac_1pro_dn);
        } else {
            this.mHl[3].setImageResource(R.drawable.lin_ac_1pro_up);
        }
        if (this.mAcInfo.fgRearLight > 0) {
            this.mHl[4].setImageResource(R.drawable.lin_ac_1pro_dn);
        } else {
            this.mHl[4].setImageResource(R.drawable.lin_ac_1pro_up);
        }
        if (this.mAcInfo.fgDual > 0) {
            this.mHl[5].setImageResource(R.drawable.lin_ac_1pro_dn);
        } else {
            this.mHl[5].setImageResource(R.drawable.lin_ac_1pro_up);
        }
        if (this.mAcInfo.fgAutoAC > 0) {
            this.mHl[6].setImageResource(R.drawable.lin_ac_1pro_dn);
        } else {
            this.mHl[6].setImageResource(R.drawable.lin_ac_1pro_up);
        }
        if (this.mAcInfo.fgAC > 0) {
            this.mHl[7].setImageResource(R.drawable.lin_ac_1pro_dn);
        } else {
            this.mHl[7].setImageResource(R.drawable.lin_ac_1pro_up);
        }
        if (this.mAcInfo.fgWheelHot > 0) {
            this.mHl[8].setImageResource(R.drawable.lin_ac_1pro_dn);
        } else {
            this.mHl[8].setImageResource(R.drawable.lin_ac_1pro_up);
        }
        for (int i = 0; i < 6; i++) {
            if (i < this.mAcInfo.nWindValue) {
                this.mWindVal[i + 1].setImageResource(this.WindImage[i]);
            } else {
                this.mWindVal[i + 1].setImageResource(this.WindImage[i + 6]);
            }
        }
        for (int i2 = 0; i2 < 3; i2++) {
            if (i2 < this.mAcInfo.nLtChairWind) {
                this.mHlShort[i2].setImageResource(R.drawable.lin_ac_3pro_dn);
            } else {
                this.mHlShort[i2].setImageResource(R.drawable.lin_ac_3pro_up);
            }
            if (i2 < this.mAcInfo.nLtChairHot) {
                this.mHlShort[i2 + 3].setImageResource(R.drawable.lin_ac_3pro_dn);
            } else {
                this.mHlShort[i2 + 3].setImageResource(R.drawable.lin_ac_3pro_up);
            }
            if (i2 < this.mAcInfo.nRtChairHot) {
                this.mHlShort[i2 + 6].setImageResource(R.drawable.lin_ac_3pro_dn);
            } else {
                this.mHlShort[i2 + 6].setImageResource(R.drawable.lin_ac_3pro_up);
            }
            if (i2 < this.mAcInfo.nRtChairWind) {
                this.mHlShort[i2 + 9].setImageResource(R.drawable.lin_ac_3pro_dn);
            } else {
                this.mHlShort[i2 + 9].setImageResource(R.drawable.lin_ac_3pro_up);
            }
        }
        Log.d(TAG, "****ResetData*****");
    }

    public void updateAC() {
        Can.updateAC();
        ResetData();
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                Log.d(TAG, "****ACTION_UP*****");
                v.setSelected(false);
                switch (Id) {
                    case 1:
                        CanJni.GmSbCarKeyCtl(20, 0);
                        break;
                    case 2:
                        if (Iop.GetWorkMode() == 12) {
                            CanJni.GmSbCarKeyCtl(25, 0);
                            break;
                        }
                        break;
                    case 3:
                        CanJni.GmSbCarKeyCtl(21, 0);
                        break;
                    case 4:
                        if (Iop.GetWorkMode() == 12) {
                            CanJni.GmSbCarKeyCtl(24, 0);
                            break;
                        }
                        break;
                    case 5:
                        CanJni.GmSbAcSet(6, 0);
                        break;
                    case 6:
                        CanJni.GmSbAcSet(8, 0);
                        break;
                    case 7:
                        CanJni.GmSbAcSet(2, 0);
                        break;
                    case 8:
                        CanJni.GmSbAcSet(2, 0);
                        break;
                    case 9:
                        CanJni.GmSbAcSet(10, 0);
                        break;
                    case 10:
                        CanJni.GmSbAcSet(6, 0);
                        break;
                    case 11:
                        CanJni.GmSbAcSet(3, 0);
                        break;
                    case 12:
                        CanJni.GmSbAcSet(7, 0);
                        break;
                    case 13:
                        CanJni.GmSbAcSet(7, 0);
                        break;
                    case 14:
                        CanJni.GmSbAcSet(1, 0);
                        break;
                    case 15:
                        CanJni.GmSbAcSet(9, 0);
                        break;
                    case 16:
                        CanJni.GmSbAcSet(11, 0);
                        break;
                    case 17:
                        CanJni.GmSbAcSet(6, 0);
                        break;
                    case 18:
                        CanJni.GmSbAcSet(6, 0);
                        break;
                    case 19:
                        CanJni.GmSbAcSet(4, 0);
                        break;
                    case 20:
                        CanJni.GmSbAcSet(3, 0);
                        break;
                    case 21:
                        CanJni.GmSbAcSet(14, 0);
                        break;
                    case 22:
                        CanJni.GmSbAcSet(15, 0);
                        break;
                    case 23:
                        CanJni.GmSbAcSet(12, 0);
                        break;
                    case 24:
                        CanJni.GmSbAcSet(13, 0);
                        break;
                    case 25:
                        CanJni.GmSbAcSet(16, 0);
                        break;
                    case 26:
                        CanJni.GmSbCarKeyCtl(17, 0);
                        break;
                    case 27:
                        CanJni.GmSbCarKeyCtl(16, 0);
                        break;
                    case 28:
                        CanJni.GmSbCarKeyCtl(27, 0);
                        break;
                    case 29:
                        CanJni.GmSbCarKeyCtl(26, 0);
                        break;
                }
            }
        } else {
            Log.d(TAG, "****ACTION_DOWN*****");
            v.setSelected(true);
            if (this.mListener != null) {
                this.mListener.onShowAC();
            }
            switch (Id) {
                case 1:
                    CanJni.GmSbCarKeyCtl(20, 1);
                    break;
                case 2:
                    if (Iop.GetWorkMode() != 12) {
                        Mcu.SetCkey(45);
                        break;
                    } else {
                        CanJni.GmSbCarKeyCtl(25, 1);
                        break;
                    }
                case 3:
                    CanJni.GmSbCarKeyCtl(21, 1);
                    break;
                case 4:
                    if (Iop.GetWorkMode() != 12) {
                        Mcu.SetCkey(44);
                        break;
                    } else {
                        CanJni.GmSbCarKeyCtl(24, 1);
                        break;
                    }
                case 5:
                    CanJni.GmSbAcSet(6, Can.CAN_MZD_LUOMU);
                    break;
                case 6:
                    CanJni.GmSbAcSet(8, 1);
                    break;
                case 7:
                    CanJni.GmSbAcSet(2, 1);
                    break;
                case 8:
                    CanJni.GmSbAcSet(2, 2);
                    break;
                case 9:
                    CanJni.GmSbAcSet(10, 2);
                    break;
                case 10:
                    CanJni.GmSbAcSet(6, 4);
                    break;
                case 11:
                    CanJni.GmSbAcSet(3, 2);
                    break;
                case 12:
                    CanJni.GmSbAcSet(7, 1);
                    break;
                case 13:
                    CanJni.GmSbAcSet(7, 2);
                    break;
                case 14:
                    CanJni.GmSbAcSet(1, 1);
                    break;
                case 15:
                    CanJni.GmSbAcSet(9, 1);
                    break;
                case 16:
                    CanJni.GmSbAcSet(11, 1);
                    break;
                case 17:
                    CanJni.GmSbAcSet(6, 5);
                    break;
                case 18:
                    CanJni.GmSbAcSet(6, 1);
                    break;
                case 19:
                    CanJni.GmSbAcSet(4, 1);
                    break;
                case 20:
                    CanJni.GmSbAcSet(3, 1);
                    break;
                case 21:
                    CanJni.GmSbAcSet(14, 1);
                    break;
                case 22:
                    CanJni.GmSbAcSet(15, 1);
                    break;
                case 23:
                    CanJni.GmSbAcSet(12, 1);
                    break;
                case 24:
                    CanJni.GmSbAcSet(13, 1);
                    break;
                case 25:
                    CanJni.GmSbAcSet(16, 1);
                    break;
                case 26:
                    CanJni.GmSbCarKeyCtl(17, 1);
                    CanJni.GmSbCarKeyCtl(17, 0);
                    break;
                case 27:
                    CanJni.GmSbCarKeyCtl(16, 1);
                    CanJni.GmSbCarKeyCtl(16, 0);
                    break;
                case 28:
                    CanJni.GmSbCarKeyCtl(27, 1);
                    CanJni.GmSbCarKeyCtl(27, 0);
                    break;
                case 29:
                    CanJni.GmSbCarKeyCtl(26, 1);
                    CanJni.GmSbCarKeyCtl(26, 0);
                    break;
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
        temp.setText("");
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomImgView AddImage(int x, int y, int w, int h, int resId) {
        CustomImgView image = this.mManager.AddImage(x, y, w, h);
        if (resId != 0) {
            image.setImageResource(resId);
        }
        return image;
    }
}
