package com.ts.can.ford;

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
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanFordEscapeACActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    private static final int ITEM_AC = 12;
    private static final int ITEM_AUTO = 11;
    private static final int ITEM_DUAL = 15;
    private static final int ITEM_LOOP = 14;
    private static final int ITEM_LT_DECREASE = 1;
    private static final int ITEM_LT_INCREASE = 0;
    private static final int ITEM_MAX_AC = 13;
    private static final int ITEM_MAX_FRONT = 10;
    private static final int ITEM_MODE_FOOT = 8;
    private static final int ITEM_MODE_FRONT = 6;
    private static final int ITEM_MODE_HEAD = 7;
    private static final int ITEM_POWER = 9;
    private static final int ITEM_RT_DECREASE = 3;
    private static final int ITEM_RT_INCREASE = 2;
    private static final int ITEM_WIND_DECREASE = 5;
    private static final int ITEM_WIND_INCREASE = 4;
    public static final String TAG = "CanFordEscapeACActivity";
    private CanDataInfo.CAN_ACInfo mAcInfo;
    protected boolean mAutoFinish = false;
    private ParamButton mBtnAc;
    private ParamButton mBtnAuto;
    private ParamButton mBtnDual;
    private ParamButton mBtnLoop;
    private ParamButton mBtnMaxAc;
    private ParamButton mBtnMaxFront;
    private ParamButton mBtnModeFoot;
    private ParamButton mBtnModeFront;
    private ParamButton mBtnModeHead;
    private ParamButton mBtnPower;
    private CustomImgView mIvAutoIcon;
    private CustomImgView[] mIvWinds = new CustomImgView[7];
    private RelativeLayoutManager mManager;
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
        if (MainSet.GetScreenType() == 3) {
            initScreen_768x1024();
        } else {
            initCommonScreen();
        }
        this.mfgJump = CanFunc.IsCanActivityJumped(this);
    }

    private void initScreen_768x1024() {
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_spac_yh_bg);
        AddButton(17, 109, 168, 58, 0, R.drawable.can_ac_yh_jia_up, R.drawable.can_ac_yh_jia_dn);
        AddButton(17, 219, 168, 58, 1, R.drawable.can_ac_yh_jian_up, R.drawable.can_ac_yh_jian_dn);
        AddButton(584, 109, 168, 58, 2, R.drawable.can_ac_yh_jia_up, R.drawable.can_ac_yh_jia_dn);
        AddButton(584, 219, 168, 58, 3, R.drawable.can_ac_yh_jian_up, R.drawable.can_ac_yh_jian_dn);
        this.mTvLtTemp = AddText(17, 165, 168, 58);
        this.mTvRtTemp = AddText(584, 165, 168, 58);
        AddButton(394, 109, 168, 58, 4, R.drawable.can_ac_yh_jiab_up, R.drawable.can_ac_yh_jiab_dn);
        AddButton(394, 219, 168, 58, 5, R.drawable.can_ac_yh_jianb_up, R.drawable.can_ac_yh_jianb_dn);
        for (int i = 0; i < this.mIvWinds.length; i++) {
            if (i < 3) {
                this.mIvWinds[i] = this.mManager.AddImageEx((i * 17) + 407, 176, 17, 37, R.drawable.can_ac_yh_fl_up);
            } else if (i == 3) {
                this.mIvWinds[i] = this.mManager.AddImageEx(471, 176, 17, 37, R.drawable.can_ac_yh_fl_up);
            } else {
                this.mIvWinds[i] = this.mManager.AddImageEx(((i - 4) * 17) + 499, 176, 17, 37, R.drawable.can_ac_yh_fl_up);
            }
        }
        this.mBtnModeFront = AddButton(Can.CAN_LEXUS_IZ, 109, 168, 58, 6, R.drawable.can_ac_yh_wd_up, R.drawable.can_ac_yh_wd_dn);
        this.mBtnModeHead = AddButton(Can.CAN_LEXUS_IZ, 167, 168, 58, 7, R.drawable.can_ac_yh_jt1_up, R.drawable.can_ac_yh_jt1_dn);
        this.mBtnModeFoot = AddButton(Can.CAN_SAIL_RW550_MG6_WC, Can.CAN_ZH_H530, 168, 58, 8, R.drawable.can_ac_yh_jt2_up, R.drawable.can_ac_yh_jt2_dn);
        this.mBtnPower = AddButton(350, 24, 68, 46, 9, R.drawable.can_ac_yh_gj_up, R.drawable.can_ac_yh_gj_dn);
        this.mBtnMaxFront = AddButton(93, KeyDef.RKEY_MEDIA_ZOOM, 97, 59, 10, R.drawable.can_ac_yh_wmax_up, R.drawable.can_ac_yh_wmax_dn);
        this.mBtnAuto = AddButton(189, KeyDef.RKEY_MEDIA_ZOOM, 97, 59, 11, R.drawable.can_ac_yh_auto_up, R.drawable.can_ac_yh_auto_dn);
        this.mBtnAc = AddButton(287, KeyDef.RKEY_MEDIA_ZOOM, 97, 59, 12, R.drawable.can_ac_yh_ac_up, R.drawable.can_ac_yh_ac_dn);
        this.mBtnMaxAc = AddButton(384, KeyDef.RKEY_MEDIA_ZOOM, 97, 59, 13, R.drawable.can_ac_yh_mac_up, R.drawable.can_ac_yh_mac_dn);
        this.mBtnLoop = AddButton(482, KeyDef.RKEY_MEDIA_ZOOM, 97, 59, 14, R.drawable.can_ac_yh_wxh_up, R.drawable.can_ac_yh_wxh_dn);
        this.mBtnDual = AddButton(CanCameraUI.BTN_CAMERY_2018_MODE1, KeyDef.RKEY_MEDIA_ZOOM, 97, 59, 15, R.drawable.can_ac_yh_dual_up, R.drawable.can_ac_yh_dual_dn);
        this.mIvAutoIcon = this.mManager.AddImageEx(199, 83, 372, 199, R.drawable.can_ac_yh_bg01);
        this.mIvAutoIcon.setVisibility(4);
    }

    private void initCommonScreen() {
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_ac_yh_bg);
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
                this.mIvWinds[i] = this.mManager.AddImage((i * 26) + CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, Can.CAN_JIANGLING_MYX, R.drawable.can_ac_yh_fl_up);
            } else if (i == 3) {
                this.mIvWinds[i] = this.mManager.AddImage(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE2, Can.CAN_JIANGLING_MYX, R.drawable.can_ac_yh_fl_up);
            } else {
                this.mIvWinds[i] = this.mManager.AddImage(((i - 4) * 26) + 665, Can.CAN_JIANGLING_MYX, R.drawable.can_ac_yh_fl_up);
            }
        }
        this.mBtnModeFront = AddButton(274, 138, 6, R.drawable.can_ac_yh_wd_up, R.drawable.can_ac_yh_wd_dn);
        this.mBtnModeHead = AddButton(274, 216, 7, R.drawable.can_ac_yh_jt1_up, R.drawable.can_ac_yh_jt1_dn);
        this.mBtnModeFoot = AddButton(274, 285, 8, R.drawable.can_ac_yh_jt2_up, R.drawable.can_ac_yh_jt2_dn);
        this.mBtnPower = AddButton(467, 25, 9, R.drawable.can_ac_yh_gj_up, R.drawable.can_ac_yh_gj_dn);
        this.mBtnMaxFront = AddButton(124, 405, 10, R.drawable.can_ac_yh_wmax_up, R.drawable.can_ac_yh_wmax_dn);
        this.mBtnAuto = AddButton(Can.CAN_TOYOTA_SP_XP, 405, 11, R.drawable.can_ac_yh_auto_up, R.drawable.can_ac_yh_auto_dn);
        this.mBtnAc = AddButton(382, 405, 12, R.drawable.can_ac_yh_ac_up, R.drawable.can_ac_yh_ac_dn);
        this.mBtnMaxAc = AddButton(CanCameraUI.BTN_YG9_XBS_MODE2, 405, 13, R.drawable.can_ac_yh_mac_up, R.drawable.can_ac_yh_mac_dn);
        this.mBtnLoop = AddButton(CanCameraUI.BTN_LANDWIND_2D_REAR, 405, 14, R.drawable.can_ac_yh_wxh_up, R.drawable.can_ac_yh_wxh_dn);
        this.mBtnDual = AddButton(KeyDef.SKEY_POWEWR_5, 405, 15, R.drawable.can_ac_yh_dual_up, R.drawable.can_ac_yh_dual_dn);
        this.mIvAutoIcon = this.mManager.AddImage(272, 110, R.drawable.can_ac_yh_bg01);
        this.mIvAutoIcon.setVisibility(4);
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
        button.setOnClickListener(this);
        button.setTag(Integer.valueOf(id));
        button.setStateDrawable(normal, pressed, pressed);
        return button;
    }

    private ParamButton AddButton(int x, int y, int w, int h, int id, int normal, int pressed) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setOnClickListener(this);
        button.setTag(Integer.valueOf(id));
        button.setStateDrawable(normal, pressed, pressed);
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
                Log.d(TAG, "-----onPause finish-----");
            }
            this.mfgJump = false;
            this.mAutoFinish = false;
        }
    }

    private void updateACUI() {
        this.mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mBtnPower.SetSel(this.mAcInfo.PWR);
        this.mBtnMaxFront.SetSel(this.mAcInfo.fgMaxFornt);
        this.mBtnAuto.SetSel(this.mAcInfo.nAutoLight);
        this.mBtnAc.SetSel(this.mAcInfo.fgAC);
        this.mBtnMaxAc.SetSel(this.mAcInfo.fgACMax);
        this.mBtnDual.SetSel(this.mAcInfo.fgDual);
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
            Log.e(TAG, "set Temp text Exception!");
        }
        Log.d(TAG, "mAcInfo.fgAutoWind =" + this.mAcInfo.fgAutoWind);
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
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        CanJni.FordCarSet(172, 0);
        switch (id) {
            case 0:
                CanJni.FordCarSet(172, 26);
                return;
            case 1:
                CanJni.FordCarSet(172, 27);
                return;
            case 2:
                CanJni.FordCarSet(172, 28);
                return;
            case 3:
                CanJni.FordCarSet(172, 29);
                return;
            case 4:
                CanJni.FordCarSet(172, 30);
                return;
            case 5:
                CanJni.FordCarSet(172, 31);
                return;
            case 6:
                CanJni.FordCarSet(172, 32);
                return;
            case 7:
                CanJni.FordCarSet(172, 33);
                return;
            case 8:
                CanJni.FordCarSet(172, 34);
                return;
            case 9:
                CanJni.FordCarSet(172, 1);
                return;
            case 10:
                CanJni.FordCarSet(172, 25);
                return;
            case 11:
                CanJni.FordCarSet(172, 23);
                return;
            case 12:
                CanJni.FordCarSet(172, 2);
                return;
            case 13:
                CanJni.FordCarSet(172, 4);
                return;
            case 14:
                CanJni.FordCarSet(172, 3);
                return;
            case 15:
                CanJni.FordCarSet(172, 24);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (this.mfgJump && GetTickCount() > CanFunc.mLastACTick + 3000) {
            finish();
            this.mAutoFinish = true;
            Log.d(TAG, "UserAll Exit Ac");
        }
    }
}
