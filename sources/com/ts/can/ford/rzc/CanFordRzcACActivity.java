package com.ts.can.ford.rzc;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanFordRzcACActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener {
    private static final int ITEM_AC = 12;
    private static final int ITEM_AUTO = 11;
    private static final int ITEM_DUAL = 15;
    private static final int ITEM_FRONT = 16;
    private static final int ITEM_LOOP = 14;
    private static final int ITEM_LT_DECREASE = 1;
    private static final int ITEM_LT_HOT = 19;
    private static final int ITEM_LT_INCREASE = 0;
    private static final int ITEM_MAX_AC = 13;
    private static final int ITEM_MAX_FRONT = 10;
    private static final int ITEM_MODE_FOOT = 8;
    private static final int ITEM_MODE_FRONT = 6;
    private static final int ITEM_MODE_HEAD = 7;
    private static final int ITEM_POWER = 9;
    private static final int ITEM_REAR = 17;
    private static final int ITEM_REAR_LOCK = 26;
    private static final int ITEM_REAR_POWER = 21;
    private static final int ITEM_RR_TEMP_DEC = 25;
    private static final int ITEM_RR_TEMP_INC = 24;
    private static final int ITEM_RR_WIND_DEC = 23;
    private static final int ITEM_RR_WIND_INC = 22;
    private static final int ITEM_RT_DECREASE = 3;
    private static final int ITEM_RT_HOT = 20;
    private static final int ITEM_RT_INCREASE = 2;
    private static final int ITEM_SW_HOT = 18;
    private static final int ITEM_WIND_DECREASE = 5;
    private static final int ITEM_WIND_INCREASE = 4;
    private static boolean isLtHotAdd = true;
    private static boolean isRtHotAdd = true;
    private boolean isSelected;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    protected boolean mAutoFinish = false;
    private ParamButton mBtnAc;
    private ParamButton mBtnAuto;
    private ParamButton mBtnDual;
    private ParamButton mBtnForeWin;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnMaxAc;
    private ParamButton mBtnMaxFront;
    private ParamButton mBtnModeFoot;
    private ParamButton mBtnModeFront;
    private ParamButton mBtnModeHead;
    private ParamButton mBtnPower;
    private ParamButton mBtnRearLock;
    private ParamButton mBtnRearPower;
    private ParamButton mBtnRearTempDec;
    private ParamButton mBtnRearTempInc;
    private ParamButton mBtnRearWin;
    private ParamButton mBtnRearWinDec;
    private ParamButton mBtnRearWinInc;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnSwHot;
    private CustomImgView mIvAutoIcon;
    private CustomImgView mIvRearFootWind;
    private CustomImgView mIvRearHeadWind;
    private CustomImgView[] mIvRearTemps = new CustomImgView[9];
    private CustomImgView[] mIvRearWinds = new CustomImgView[7];
    private CustomImgView[] mIvWinds = new CustomImgView[7];
    private int[] mLtColdIds = {R.drawable.can_ac_yh2_rjr_b01_dn, R.drawable.can_ac_yh2_rjr_b02_dn, R.drawable.can_ac_yh2_rjr_b03_dn};
    private int[] mLtHotIds = {R.drawable.can_ac_yh2_rjr_r01_dn, R.drawable.can_ac_yh2_rjr_r02_dn, R.drawable.can_ac_yh2_rjr_r03_dn};
    private RelativeLayoutManager mManager;
    private int[] mRtColdIds = {R.drawable.can_ac_yh2_ljr_b01_dn, R.drawable.can_ac_yh2_ljr_b02_dn, R.drawable.can_ac_yh2_ljr_b03_dn};
    private int[] mRtHotIds = {R.drawable.can_ac_yh2_ljr_r01_dn, R.drawable.can_ac_yh2_ljr_r02_dn, R.drawable.can_ac_yh2_ljr_r03_dn};
    private TextView mTvLtTemp;
    private TextView mTvRtTemp;
    protected boolean mfgJump;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        InitUI();
    }

    private void InitUI() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        initCommonScreen();
        this.mfgJump = CanFunc.IsCanActivityJumped(this);
    }

    private void initCommonScreen() {
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_ac_yh4_bg);
        for (int i = 0; i < this.mIvRearWinds.length; i++) {
            this.mIvRearWinds[i] = AddImage((i * 32) + 152, 24, 22, 50, R.drawable.can_ac_yh_fl_up, R.drawable.can_ac_yh_fl_dn);
        }
        for (int i2 = 0; i2 < this.mIvRearTemps.length; i2++) {
            this.mIvRearTemps[i2] = AddImage((i2 * 28) + 710, 24, 22, 50, R.drawable.can_ac_yh_fl_up, R.drawable.can_ac_yh_fl_blue);
        }
        this.mBtnRearPower = AddButton(458, 13, 21, R.drawable.can_ac_yh2_gj_up, R.drawable.can_ac_yh2_gj_dn);
        this.mBtnSwHot = AddButton(26, 99, 18, R.drawable.can_ac_yh2_fxpjr_up, R.drawable.can_ac_yh2_fxpjr_dn);
        this.mBtnRearLock = AddButton(136, 99, 26, R.drawable.can_ac_yh2_rear_up, R.drawable.can_ac_yh2_rear_dn);
        this.mIvRearHeadWind = AddImage(277, 99, 106, 69, R.drawable.can_ac_yh2_pcf_up, R.drawable.can_ac_yh2_pcf_dn);
        this.mIvRearFootWind = AddImage(388, 99, 106, 69, R.drawable.can_ac_yh2_xcf_up, R.drawable.can_ac_yh2_xcf_dn);
        this.mBtnLtHot = AddButton(CanCameraUI.BTN_CHANA_ALSVINV7_MODE2, 99, 19, R.drawable.can_ac_yh2_rjr_up, R.drawable.can_ac_yh2_rjr_dn);
        this.mBtnRtHot = AddButton(CanCameraUI.BTN_LANDWIND_2D_FRONT, 99, 20, R.drawable.can_ac_yh2_ljr_up, R.drawable.can_ac_yh2_ljr_dn);
        this.mBtnForeWin = AddButton(KeyDef.SKEY_VOLDN_3, 99, 16, R.drawable.can_ac_yh2_qcs_up, R.drawable.can_ac_yh2_qcs_dn);
        this.mBtnRearWin = AddButton(891, 99, 17, R.drawable.can_ac_yh2_hcs_up, R.drawable.can_ac_yh2_hcs_dn);
        AddButton(22, 208, 0, R.drawable.can_ac_yh_jia_up, R.drawable.can_ac_yh_jia_dn);
        AddButton(22, 355, 1, R.drawable.can_ac_yh_jian_up, R.drawable.can_ac_yh_jian_dn);
        AddButton(KeyDef.SKEY_VOLDN_1, 208, 2, R.drawable.can_ac_yh_jia_up, R.drawable.can_ac_yh_jia_dn);
        AddButton(KeyDef.SKEY_VOLDN_1, 355, 3, R.drawable.can_ac_yh_jian_up, R.drawable.can_ac_yh_jian_dn);
        this.mTvLtTemp = AddText(22, 286, Can.CAN_X80_RZC, 69);
        this.mTvRtTemp = AddText(KeyDef.SKEY_VOLDN_1, 286, Can.CAN_X80_RZC, 69);
        AddButton(CanCameraUI.BTN_GEELY_YJX6_FXP, 208, 4, R.drawable.can_ac_yh_jiab_up, R.drawable.can_ac_yh_jiab_dn);
        AddButton(CanCameraUI.BTN_GEELY_YJX6_FXP, 355, 5, R.drawable.can_ac_yh_jianb_up, R.drawable.can_ac_yh_jianb_dn);
        for (int i3 = 0; i3 < this.mIvWinds.length; i3++) {
            if (i3 < 3) {
                this.mIvWinds[i3] = this.mManager.AddImage((i3 * 26) + CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, 297, R.drawable.can_ac_yh_fl_up);
            } else if (i3 == 3) {
                this.mIvWinds[i3] = this.mManager.AddImage(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE2, 297, R.drawable.can_ac_yh_fl_up);
            } else {
                this.mIvWinds[i3] = this.mManager.AddImage(((i3 - 4) * 26) + 665, 297, R.drawable.can_ac_yh_fl_up);
            }
        }
        this.mIvAutoIcon = this.mManager.AddImage(272, 180, R.drawable.can_ac_yh_bg01);
        this.mIvAutoIcon.setVisibility(4);
        this.mBtnModeFront = AddButton(274, 208, 6, R.drawable.can_ac_yh_wd_up, R.drawable.can_ac_yh_wd_dn);
        this.mBtnModeHead = AddButton(274, 286, 7, R.drawable.can_ac_yh_jt1_up, R.drawable.can_ac_yh_jt1_dn);
        this.mBtnModeFoot = AddButton(274, 355, 8, R.drawable.can_ac_yh_jt2_up, R.drawable.can_ac_yh_jt2_dn);
        this.mBtnMaxFront = AddButton(64, 455, 10, R.drawable.can_ac_yh_wmax_up, R.drawable.can_ac_yh_wmax_dn);
        this.mBtnAuto = AddButton(194, 455, 11, R.drawable.can_ac_yh_auto_up, R.drawable.can_ac_yh_auto_dn);
        this.mBtnAc = AddButton(KeyDef.RKEY_MEDIA_SLOW, 455, 12, R.drawable.can_ac_yh_ac_up, R.drawable.can_ac_yh_ac_dn);
        this.mBtnPower = AddButton(469, 462, 9, R.drawable.can_ac_yh2_gj_up, R.drawable.can_ac_yh2_gj_dn);
        this.mBtnMaxAc = AddButton(CanCameraUI.BTN_CAMERY_2018_MODE2, 455, 13, R.drawable.can_ac_yh_mac_up, R.drawable.can_ac_yh_mac_dn);
        this.mBtnLoop = AddButton(711, 455, 14, R.drawable.can_ac_yh_wxh_up, R.drawable.can_ac_yh_wxh_dn);
        this.mBtnDual = AddButton(KeyDef.SKEY_RETURN_3, 455, 15, R.drawable.can_ac_yh_dual_up, R.drawable.can_ac_yh_dual_dn);
        this.mBtnRearWinDec = AddButtonBackground(33, 21, 180, 56, 23);
        this.mBtnRearWinInc = AddButtonBackground(Can.CAN_MG_ZS_WC, 21, 180, 56, 22);
        this.mBtnRearTempDec = AddButtonBackground(CanCameraUI.BTN_CAMERY_2018_MODE2, 21, 180, 56, 25);
        this.mBtnRearTempInc = AddButtonBackground(KeyDef.SKEY_SEEKDN_4, 21, 180, 56, 24);
    }

    private CustomImgView AddImage(int x, int y, int w, int h, int normal, int sel) {
        CustomImgView img = this.mManager.AddImage(x, y, w, h);
        img.setStateDrawable(normal, sel);
        return img;
    }

    private TextView AddText(int x, int y, int w, int h) {
        TextView view = this.mManager.AddText(x, y, w, h);
        view.setTextSize(0, 39.0f);
        view.setTextColor(-1);
        view.setGravity(17);
        return view;
    }

    private ParamButton AddButton(int x, int y, int id, int normal, int pressed) {
        ParamButton button = this.mManager.AddButton(x, y);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        button.setStateDrawable(normal, pressed, pressed);
        return button;
    }

    private ParamButton AddButtonBackground(int x, int y, int w, int h, int id) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        button.setBackgroundColor(0);
        return button;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        Can.updateAC();
        updateACUI();
        CanFunc.mfgShowAC = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        if (!CanFunc.mfgShowAC) {
            if (!this.mAutoFinish) {
                finish();
                Log.d(CanBaseActivity.TAG, "-----onPause finish-----");
            }
            this.mfgJump = false;
            this.mAutoFinish = false;
        }
    }

    private void updateACUI() {
        boolean z;
        boolean z2;
        this.mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mBtnPower.SetSel(this.mAcInfo.PWR);
        this.mBtnMaxFront.SetSel(this.mAcInfo.fgMaxFornt);
        this.mBtnAuto.SetSel(this.mAcInfo.nAutoLight);
        this.mBtnAc.SetSel(this.mAcInfo.fgAC);
        this.mBtnMaxAc.SetSel(this.mAcInfo.fgACMax);
        this.mBtnDual.SetSel(this.mAcInfo.fgDual);
        this.mBtnSwHot.SetSel(this.mAcInfo.fgWheelHot);
        this.mBtnForeWin.SetSel(this.mAcInfo.fgDFBL);
        this.mBtnRearWin.SetSel(this.mAcInfo.fgRearLight);
        this.mBtnRearLock.SetSel(this.mAcInfo.fgRearLock);
        this.mBtnRearPower.SetSel(this.mAcInfo.fgRac);
        this.mIvRearHeadWind.SetSel(this.mAcInfo.bRearParallelWindFlg);
        this.mIvRearFootWind.SetSel(this.mAcInfo.bRearDownWindFlg);
        if (this.mAcInfo.fgAutoMode == 1) {
            this.mIvAutoIcon.setVisibility(0);
        } else {
            this.mIvAutoIcon.setVisibility(4);
        }
        if (this.mAcInfo.fgInnerLoop == 0) {
            this.mBtnLoop.setStateDrawable(R.drawable.can_ac_yh_wxh_up, R.drawable.can_ac_yh_wxh_dn, R.drawable.can_ac_yh_wxh_dn);
            this.mBtnLoop.setSelected(true);
        } else {
            this.mBtnLoop.setStateDrawable(R.drawable.can_ac_yh_nxh_up, R.drawable.can_ac_yh_nxh_dn, R.drawable.can_ac_yh_nxh_dn);
            this.mBtnLoop.setSelected(true);
        }
        this.mBtnModeFront.SetSel(this.mAcInfo.fgUpWind);
        this.mBtnModeHead.SetSel(this.mAcInfo.fgParallelWind);
        this.mBtnModeFoot.SetSel(this.mAcInfo.fgDownWind);
        try {
            this.mTvLtTemp.setText(this.mAcInfo.szLtTemp);
            this.mTvRtTemp.setText(this.mAcInfo.szRtTemp);
        } catch (Exception e) {
            Log.e(CanBaseActivity.TAG, "set Temp text Exception!");
        }
        Log.d(CanBaseActivity.TAG, "mAcInfo.fgAutoWind =" + this.mAcInfo.fgAutoWind);
        for (int i = 0; i < this.mIvWinds.length; i++) {
            if (this.mAcInfo.fgAutoWind == 1) {
                this.mIvWinds[i].setVisibility(4);
            } else {
                this.mIvWinds[i].setVisibility(0);
                if (i < this.mAcInfo.nWindValue) {
                    this.mIvWinds[i].setImageResource(R.drawable.can_ac_yh_fl_dn);
                } else {
                    this.mIvWinds[i].setImageResource(R.drawable.can_ac_yh_fl_up);
                }
            }
        }
        for (int i2 = 0; i2 < this.mIvRearWinds.length; i2++) {
            if (this.mAcInfo.nRearWindValue <= 0 || this.mAcInfo.nRearWindValue >= 8) {
                this.mIvRearWinds[i2].setSelected(false);
            } else {
                CustomImgView customImgView = this.mIvRearWinds[i2];
                if (i2 < this.mAcInfo.nRearWindValue) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                customImgView.setSelected(z2);
            }
        }
        for (int i3 = 0; i3 < this.mIvRearTemps.length; i3++) {
            if (this.mAcInfo.nRearTemp <= 0 || this.mAcInfo.nRearTemp >= 10) {
                this.mIvRearTemps[i3].setSelected(false);
            } else {
                CustomImgView customImgView2 = this.mIvRearTemps[i3];
                if (i3 < this.mAcInfo.nRearTemp) {
                    z = true;
                } else {
                    z = false;
                }
                customImgView2.setSelected(z);
            }
        }
        int ltHot = this.mAcInfo.nLtChairHot;
        if (ltHot == 0) {
            this.mBtnLtHot.setStateUpSel(R.drawable.can_ac_yh2_rjr_up, R.drawable.can_ac_yh2_rjr_dn);
        } else if (ltHot > 0 && ltHot < 4) {
            this.mBtnLtHot.setStateUpSel(this.mLtColdIds[ltHot - 1], R.drawable.can_ac_yh2_rjr_dn);
        } else if (ltHot > 3 && ltHot < 7) {
            this.mBtnLtHot.setStateUpSel(this.mLtHotIds[ltHot - 4], R.drawable.can_ac_yh2_rjr_dn);
        }
        int rtHot = this.mAcInfo.nRtChairHot;
        if (rtHot == 0) {
            this.mBtnRtHot.setStateUpSel(R.drawable.can_ac_yh2_ljr_up, R.drawable.can_ac_yh2_ljr_dn);
        } else if (rtHot > 0 && rtHot < 4) {
            this.mBtnRtHot.setStateUpSel(this.mRtColdIds[rtHot - 1], R.drawable.can_ac_yh2_ljr_dn);
        } else if (rtHot > 3 && rtHot < 7) {
            this.mBtnRtHot.setStateUpSel(this.mRtHotIds[rtHot - 4], R.drawable.can_ac_yh2_ljr_dn);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        if (action == 0) {
            this.isSelected = v.isSelected();
            v.setSelected(true);
            switch (id) {
                case 0:
                    CanJni.FordCarSet(172, 26);
                    break;
                case 1:
                    CanJni.FordCarSet(172, 27);
                    break;
                case 2:
                    CanJni.FordCarSet(172, 28);
                    break;
                case 3:
                    CanJni.FordCarSet(172, 29);
                    break;
                case 4:
                    CanJni.FordCarSet(172, 30);
                    break;
                case 5:
                    CanJni.FordCarSet(172, 31);
                    break;
                case 6:
                    CanJni.FordCarSet(172, 32);
                    break;
                case 7:
                    CanJni.FordCarSet(172, 33);
                    break;
                case 8:
                    CanJni.FordCarSet(172, 34);
                    break;
                case 9:
                    CanJni.FordCarSet(172, 1);
                    break;
                case 10:
                    CanJni.FordCarSet(172, 25);
                    break;
                case 11:
                    CanJni.FordCarSet(172, 23);
                    break;
                case 12:
                    CanJni.FordCarSet(172, 2);
                    break;
                case 13:
                    CanJni.FordCarSet(172, 4);
                    break;
                case 14:
                    CanJni.FordCarSet(172, 3);
                    break;
                case 15:
                    CanJni.FordCarSet(172, 24);
                    break;
                case 16:
                    CanJni.FordCarSet(172, 5);
                    break;
                case 17:
                    CanJni.FordCarSet(172, 6);
                    break;
                case 18:
                    CanJni.FordCarSet(172, 11);
                    break;
                case 19:
                    if (this.mAcInfo.nLtChairHot >= 6) {
                        isLtHotAdd = false;
                    } else if (this.mAcInfo.nLtChairHot <= 0) {
                        isLtHotAdd = true;
                    }
                    if (!isLtHotAdd) {
                        CanJni.FordCarSet(172, 8);
                        break;
                    } else {
                        CanJni.FordCarSet(172, 7);
                        break;
                    }
                case 20:
                    if (this.mAcInfo.nRtChairHot >= 6) {
                        isRtHotAdd = false;
                    } else if (this.mAcInfo.nRtChairHot <= 0) {
                        isRtHotAdd = true;
                    }
                    if (!isRtHotAdd) {
                        CanJni.FordCarSet(172, 10);
                        break;
                    } else {
                        CanJni.FordCarSet(172, 9);
                        break;
                    }
                case 21:
                    CanJni.FordCarSet(172, 17);
                    break;
                case 22:
                    CanJni.FordCarSet(172, 22);
                    break;
                case 23:
                    CanJni.FordCarSet(172, 21);
                    break;
                case 24:
                    CanJni.FordCarSet(172, 20);
                    break;
                case 25:
                    CanJni.FordCarSet(172, 19);
                    break;
                case 26:
                    CanJni.FordCarSet(172, 18);
                    break;
            }
        } else if (action == 1) {
            v.setSelected(this.isSelected);
            CanJni.FordCarSet(172, 0);
        }
        return true;
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (this.mfgJump && GetTickCount() > CanFunc.mLastACTick + 6000) {
            finish();
            this.mAutoFinish = true;
            Log.d(CanBaseActivity.TAG, "UserAll Exit Ac");
        }
    }
}
