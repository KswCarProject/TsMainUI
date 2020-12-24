package com.ts.can.ford.wc.mondeo;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanFordMondeoWcCarACView extends CanBaseACView {
    private static final int ITEM_AC = 12;
    private static final int ITEM_AUTO = 11;
    private static final int ITEM_DUAL = 15;
    private static final int ITEM_FRONT = 16;
    private static final int ITEM_LOOP = 14;
    private static final int ITEM_LT_DECREASE = 1;
    private static final int ITEM_LT_HOT = 19;
    private static final int ITEM_LT_INCREASE = 0;
    private static final int ITEM_LT_WIND = 21;
    private static final int ITEM_MAX_AC = 13;
    private static final int ITEM_MAX_FRONT = 10;
    private static final int ITEM_MODE_FOOT = 8;
    private static final int ITEM_MODE_FRONT = 6;
    private static final int ITEM_MODE_HEAD = 7;
    private static final int ITEM_POWER = 9;
    private static final int ITEM_REAR = 17;
    private static final int ITEM_RT_DECREASE = 3;
    private static final int ITEM_RT_HOT = 20;
    private static final int ITEM_RT_INCREASE = 2;
    private static final int ITEM_RT_WIND = 22;
    private static final int ITEM_SW_HOT = 18;
    private static final int ITEM_WHEEL_HOT = 23;
    private static final int ITEM_WIND_DECREASE = 5;
    private static final int ITEM_WIND_INCREASE = 4;
    public static final String TAG = "CanFordMondeoWcCarACView";
    private static int[] mLtHotIds = {R.drawable.can_ac_yh2_rjr_r01_dn, R.drawable.can_ac_yh2_rjr_r02_dn, R.drawable.can_ac_yh2_rjr_r03_dn};
    private static int[] mRtHotIds = {R.drawable.can_ac_yh2_ljr_r01_dn, R.drawable.can_ac_yh2_ljr_r02_dn, R.drawable.can_ac_yh2_ljr_r03_dn};
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAuto;
    private ParamButton mBtnDual;
    private ParamButton mBtnForeWin;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnLtWind;
    private ParamButton mBtnMaxAc;
    private ParamButton mBtnMaxFront;
    private ParamButton mBtnModeFoot;
    private ParamButton mBtnModeFront;
    private ParamButton mBtnModeHead;
    private ParamButton mBtnPower;
    private ParamButton mBtnRearWin;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnRtWind;
    private ParamButton mBtnWheelHot;
    private CustomImgView mIvAutoIcon;
    private CustomImgView[] mIvAutos;
    private CustomImgView[] mIvWinds;
    private int[] mLtColdIds = {R.drawable.can_ac_yh2_rjr_b01_dn, R.drawable.can_ac_yh2_rjr_b02_dn, R.drawable.can_ac_yh2_rjr_b03_dn};
    private int[] mRtColdIds = {R.drawable.can_ac_yh2_ljr_b01_dn, R.drawable.can_ac_yh2_ljr_b02_dn, R.drawable.can_ac_yh2_ljr_b03_dn};
    private TextView mTvLtTemp;
    private TextView mTvRtTemp;

    public CanFordMondeoWcCarACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View view, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) view.getTag()).intValue();
        if (action == 0) {
            switch (Id) {
                case 0:
                    FordMondeoACDownSet(13);
                    return false;
                case 1:
                    FordMondeoACDownSet(14);
                    return false;
                case 2:
                    FordMondeoACDownSet(15);
                    return false;
                case 3:
                    FordMondeoACDownSet(16);
                    return false;
                case 4:
                    FordMondeoACDownSet(11);
                    return false;
                case 5:
                    FordMondeoACDownSet(12);
                    return false;
                case 6:
                    FordMondeoACDownSet(8);
                    return false;
                case 7:
                    FordMondeoACDownSet(9);
                    return false;
                case 8:
                    FordMondeoACDownSet(10);
                    return false;
                case 9:
                    FordMondeoACDownSet(1);
                    return false;
                case 10:
                    FordMondeoACDownSet(5);
                    return false;
                case 11:
                    FordMondeoACDownSet(4);
                    return false;
                case 12:
                    FordMondeoACDownSet(2);
                    return false;
                case 13:
                    FordMondeoACDownSet(26);
                    return false;
                case 14:
                    FordMondeoACDownSet(7);
                    return false;
                case 15:
                    FordMondeoACDownSet(3);
                    return false;
                case 17:
                    FordMondeoACDownSet(6);
                    return false;
                case 19:
                    FordMondeoACDownSet(17);
                    return false;
                case 20:
                    FordMondeoACDownSet(18);
                    return false;
                case 21:
                    FordMondeoACDownSet(23);
                    return false;
                case 22:
                    FordMondeoACDownSet(24);
                    return false;
                case 23:
                    FordMondeoACDownSet(45);
                    return false;
                default:
                    return false;
            }
        } else if (1 != action) {
            return false;
        } else {
            switch (Id) {
                case 0:
                    FordMondeoACUpSet(13);
                    return false;
                case 1:
                    FordMondeoACUpSet(14);
                    return false;
                case 2:
                    FordMondeoACUpSet(15);
                    return false;
                case 3:
                    FordMondeoACUpSet(16);
                    return false;
                case 4:
                    FordMondeoACUpSet(11);
                    return false;
                case 5:
                    FordMondeoACUpSet(12);
                    return false;
                case 6:
                    FordMondeoACUpSet(8);
                    return false;
                case 7:
                    FordMondeoACUpSet(9);
                    return false;
                case 8:
                    FordMondeoACUpSet(10);
                    return false;
                case 9:
                    FordMondeoACUpSet(1);
                    return false;
                case 10:
                    FordMondeoACUpSet(5);
                    return false;
                case 11:
                    FordMondeoACUpSet(4);
                    return false;
                case 12:
                    FordMondeoACUpSet(2);
                    return false;
                case 13:
                    FordMondeoACUpSet(26);
                    return false;
                case 14:
                    FordMondeoACUpSet(7);
                    return false;
                case 15:
                    FordMondeoACUpSet(3);
                    return false;
                case 17:
                    FordMondeoACUpSet(6);
                    return false;
                case 19:
                    FordMondeoACUpSet(17);
                    return false;
                case 20:
                    FordMondeoACUpSet(18);
                    return false;
                case 21:
                    FordMondeoACUpSet(23);
                    return false;
                case 22:
                    FordMondeoACUpSet(24);
                    return false;
                case 23:
                    FordMondeoACUpSet(45);
                    return false;
                default:
                    return false;
            }
        }
    }

    private void FordMondeoACDownSet(int cmd) {
        CanJni.FordWcMondeo2013AirKey(cmd, 1);
    }

    private void FordMondeoACUpSet(int cmd) {
        CanJni.FordWcMondeo2013AirKey(cmd, 0);
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mIvWinds = new CustomImgView[7];
        this.mIvAutos = new CustomImgView[3];
        initCommonScreen();
    }

    private void initCommonScreen() {
        setBackgroundResource(R.drawable.can_ac_yh5_bg);
        AddButton(22, 138, 0, R.drawable.can_ac_yh_jia_up, R.drawable.can_ac_yh_jia_dn);
        AddButton(22, 285, 1, R.drawable.can_ac_yh_jian_up, R.drawable.can_ac_yh_jian_dn);
        AddButton(KeyDef.SKEY_VOLDN_1, 138, 2, R.drawable.can_ac_yh_jia_up, R.drawable.can_ac_yh_jia_dn);
        AddButton(KeyDef.SKEY_VOLDN_1, 285, 3, R.drawable.can_ac_yh_jian_up, R.drawable.can_ac_yh_jian_dn);
        this.mTvLtTemp = AddText(22, 216, Can.CAN_X80_RZC, 69);
        this.mTvRtTemp = AddText(KeyDef.SKEY_VOLDN_1, 216, Can.CAN_X80_RZC, 69);
        AddButton(CanCameraUI.BTN_GEELY_YJX6_FXP, 138, 4, R.drawable.can_ac_yh_jiab_up, R.drawable.can_ac_yh_jiab_dn);
        AddButton(CanCameraUI.BTN_GEELY_YJX6_FXP, 285, 5, R.drawable.can_ac_yh_jianb_up, R.drawable.can_ac_yh_jianb_dn);
        for (int i = 0; i < this.mIvWinds.length; i++) {
            if (i < 3) {
                this.mIvWinds[i] = getRelativeManager().AddImage((i * 26) + CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, Can.CAN_JIANGLING_MYX, R.drawable.can_ac_yh_fl_up);
            } else if (i == 3) {
                this.mIvWinds[i] = getRelativeManager().AddImage(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE2, Can.CAN_JIANGLING_MYX, R.drawable.can_ac_yh_fl_up);
            } else {
                this.mIvWinds[i] = getRelativeManager().AddImage(((i - 4) * 26) + 665, Can.CAN_JIANGLING_MYX, R.drawable.can_ac_yh_fl_up);
            }
        }
        for (int i2 = 0; i2 < this.mIvAutos.length; i2++) {
            this.mIvAutos[i2] = getRelativeManager().AddImage((i2 * 10) + 348, 435, R.drawable.can_ac_yh_autop_up);
        }
        this.mBtnModeFront = AddButton(274, 138, 6, R.drawable.can_ac_yh_wd_up, R.drawable.can_ac_yh_wd_dn);
        this.mBtnModeHead = AddButton(274, 216, 7, R.drawable.can_ac_yh_jt1_up, R.drawable.can_ac_yh_jt1_dn);
        this.mBtnModeFoot = AddButton(274, 285, 8, R.drawable.can_ac_yh_jt2_up, R.drawable.can_ac_yh_jt2_dn);
        this.mBtnPower = AddButton(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST1, 25, 9, R.drawable.can_ac_yh_gj_up, R.drawable.can_ac_yh_gj_dn);
        this.mBtnMaxFront = AddButton(124, 405, 10, R.drawable.can_ac_yh_wmax_up, R.drawable.can_ac_yh_wmax_dn);
        this.mBtnAuto = AddButton(Can.CAN_TOYOTA_SP_XP, 405, 11, R.drawable.can_ac_yh_auto01_up, R.drawable.can_ac_yh_auto01_dn);
        this.mBtnAc = AddButton(382, 405, 12, R.drawable.can_ac_yh_ac_up, R.drawable.can_ac_yh_ac_dn);
        this.mBtnMaxAc = AddButton(CanCameraUI.BTN_YG9_XBS_MODE2, 405, 13, R.drawable.can_ac_yh_mac_up, R.drawable.can_ac_yh_mac_dn);
        this.mBtnLoop = AddButton(CanCameraUI.BTN_LANDWIND_2D_REAR, 405, 14, R.drawable.can_ac_yh_wxh_up, R.drawable.can_ac_yh_wxh_dn);
        this.mBtnDual = AddButton(KeyDef.SKEY_POWEWR_5, 405, 15, R.drawable.can_ac_yh_dual_up, R.drawable.can_ac_yh_dual_dn);
        this.mBtnLtHot = AddButton(22, 25, 19, R.drawable.can_ac_yh2_rjr_up, R.drawable.can_ac_yh2_rjr_dn);
        this.mBtnRtHot = AddButton(132, 25, 20, R.drawable.can_ac_yh2_ljr_up, R.drawable.can_ac_yh2_ljr_dn);
        this.mBtnForeWin = AddButton(KeyDef.SKEY_VOLDN_3, 25, 16, R.drawable.can_ac_yh2_qcs_up, R.drawable.can_ac_yh2_qcs_dn);
        this.mBtnRearWin = AddButton(891, 25, 17, R.drawable.can_ac_yh2_hcs_up, R.drawable.can_ac_yh2_hcs_dn);
        this.mIvAutoIcon = getRelativeManager().AddImage(272, 110, R.drawable.can_ac_yh_bg01);
        this.mIvAutoIcon.setVisibility(4);
        this.mBtnLtWind = AddButton(274, 25, 21, R.drawable.can_ac_yh2_rjr_up, R.drawable.can_ac_yh2_rjr_dn);
        this.mBtnRtWind = AddButton(384, 25, 22, R.drawable.can_ac_yh2_ljr_up, R.drawable.can_ac_yh2_ljr_dn);
        this.mBtnWheelHot = AddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 25, 23, R.drawable.can_ac_yh_fxp_up, R.drawable.can_ac_yh_fxp_dn);
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mBtnPower.SetSel(this.mAcInfo.PWR);
        this.mBtnMaxFront.SetSel(this.mAcInfo.fgMaxFornt);
        this.mBtnAuto.SetSel(this.mAcInfo.nAutoLight);
        this.mBtnAc.SetSel(this.mAcInfo.fgAC);
        this.mBtnMaxAc.SetSel(this.mAcInfo.fgACMax);
        this.mBtnDual.SetSel(this.mAcInfo.fgDual);
        this.mBtnForeWin.SetSel(this.mAcInfo.fgDFBL);
        this.mBtnRearWin.SetSel(this.mAcInfo.fgRearLight);
        int ltHot = this.mAcInfo.nLtChairHot;
        if (ltHot == 0) {
            this.mBtnLtHot.setStateUpSel(R.drawable.can_ac_yh2_rjr_up, R.drawable.can_ac_yh2_rjr_dn);
        } else if (ltHot > 0 && ltHot < 4) {
            this.mBtnLtHot.setStateUpSel(mLtHotIds[ltHot - 1], R.drawable.can_ac_yh2_rjr_dn);
        }
        int rtHot = this.mAcInfo.nRtChairHot;
        if (rtHot == 0) {
            this.mBtnRtHot.setStateUpSel(R.drawable.can_ac_yh2_ljr_up, R.drawable.can_ac_yh2_ljr_dn);
        } else if (rtHot > 0 && rtHot < 4) {
            this.mBtnRtHot.setStateUpSel(mRtHotIds[rtHot - 1], R.drawable.can_ac_yh2_ljr_dn);
        }
        int ltWind = this.mAcInfo.nLtChairWind;
        if (ltWind == 0) {
            this.mBtnLtWind.setStateUpSel(R.drawable.can_ac_yh2_rjr_up, R.drawable.can_ac_yh2_rjr_dn);
        } else if (ltWind > 0 && ltWind < 4) {
            this.mBtnLtWind.setStateUpSel(this.mLtColdIds[ltWind - 1], R.drawable.can_ac_yh2_rjr_dn);
        }
        int rtWind = this.mAcInfo.nRtChairWind;
        if (rtWind == 0) {
            this.mBtnRtWind.setStateUpSel(R.drawable.can_ac_yh2_ljr_up, R.drawable.can_ac_yh2_ljr_dn);
        } else if (rtWind > 0 && rtWind < 4) {
            this.mBtnRtWind.setStateUpSel(this.mRtColdIds[rtWind - 1], R.drawable.can_ac_yh2_ljr_dn);
        }
        this.mBtnWheelHot.SetSel(this.mAcInfo.fgWheelHot);
        int autoLevel = this.mAcInfo.nWindAutoLevel;
        for (int i = 0; i < this.mIvAutos.length; i++) {
            if (i < autoLevel) {
                this.mIvAutos[i].setImageResource(R.drawable.can_ac_yh_autop_dn);
            } else {
                this.mIvAutos[i].setImageResource(R.drawable.can_ac_yh_autop_up);
            }
        }
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
        this.mBtnModeFront.SetSel(this.mAcInfo.fgForeWindMode);
        this.mBtnModeHead.SetSel(this.mAcInfo.fgParallelWind);
        this.mBtnModeFoot.SetSel(this.mAcInfo.fgDownWind);
        try {
            this.mTvLtTemp.setText(this.mAcInfo.szLtTemp);
            this.mTvRtTemp.setText(this.mAcInfo.szRtTemp);
        } catch (Exception e) {
            Log.e(TAG, "set Temp text Exception!");
        }
        for (int i2 = 0; i2 < this.mIvWinds.length; i2++) {
            if (this.mAcInfo.fgAutoWind == 1) {
                this.mIvWinds[i2].setVisibility(4);
            } else {
                this.mIvWinds[i2].setVisibility(0);
                if (i2 < this.mAcInfo.nWindValue) {
                    this.mIvWinds[i2].setImageResource(R.drawable.can_ac_yh_fl_dn);
                } else {
                    this.mIvWinds[i2].setImageResource(R.drawable.can_ac_yh_fl_up);
                }
            }
        }
    }

    private TextView AddText(int x, int y, int w, int h) {
        TextView view = getRelativeManager().AddText(x, y, w, h);
        view.setTextSize(0, 39.0f);
        view.setTextColor(-1);
        view.setGravity(17);
        return view;
    }

    private ParamButton AddButton(int x, int y, int id, int normal, int pressed) {
        ParamButton button = getRelativeManager().AddButton(x, y);
        button.setOnClickListener(this);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        button.setStateDrawable(normal, pressed, pressed);
        return button;
    }

    private ParamButton AddButton(int x, int y, int w, int h, int id, int normal, int pressed) {
        ParamButton button = getRelativeManager().AddButton(x, y, w, h);
        button.setOnClickListener(this);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        button.setStateDrawable(normal, pressed, pressed);
        return button;
    }
}
