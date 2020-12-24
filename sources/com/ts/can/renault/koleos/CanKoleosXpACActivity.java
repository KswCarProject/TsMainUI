package com.ts.can.renault.koleos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
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

public class CanKoleosXpACActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener {
    private static final int LT_TEMP_DECREASE = 1;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int MODE_FOOT = 8;
    private static final int MODE_HEAD = 6;
    private static final int MODE_WIN = 9;
    private static final int RT_TEMP_DECREASE = 3;
    private static final int RT_TEMP_INCREASE = 2;
    private static final int STATUS_AC = 15;
    private static final int STATUS_AUTO = 13;
    private static final int STATUS_DUAL = 11;
    private static final int STATUS_FAST = 20;
    private static final int STATUS_LOOP_AUTO = 17;
    private static final int STATUS_MODE = 21;
    private static final int STATUS_NORMAL = 19;
    private static final int STATUS_OUT_LOOP = 12;
    private static final int STATUS_REAR_WIDOW = 16;
    private static final int STATUS_SOFT = 18;
    private static final int STATUS_WINOW = 10;
    private static final int WIND_DECREASE = 5;
    private static final int WIND_INCREASE = 4;
    private static boolean mfgJump;
    private boolean isAutoFinish = false;
    private ParamButton[] mACMode = new ParamButton[3];
    private int[] mIcons = {R.drawable.can_rh7_8signal01_dn, R.drawable.can_rh7_8signal02_dn, R.drawable.can_rh7_8signal03_dn, R.drawable.can_rh7_8signal04_dn, R.drawable.can_rh7_8signal05_dn, R.drawable.can_rh7_8signal06_dn, R.drawable.can_rh7_8signal07_dn, R.drawable.can_rh7_8signal08_dn};
    private TextView mLeftTemp;
    private RelativeLayoutManager mManager;
    private TextView mRightTemp;
    private ParamButton mStatusAc;
    private ParamButton mStatusAuto;
    private ParamButton mStatusDual;
    private ParamButton mStatusFast;
    private ParamButton mStatusLoopAuto;
    private ParamButton mStatusMode;
    private ParamButton mStatusNormal;
    private ParamButton mStatusOutLoop;
    private ParamButton mStatusRearWindow;
    private ParamButton mStatusSoft;
    private ParamButton mStatusWindow;
    private CustomImgView[] mWindIcons = new CustomImgView[8];

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        mfgJump = CanFunc.IsCanActivityJumped(this);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        if (MainSet.GetScreenType() == 3) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            InitView();
            this.mManager.GetLayout().setScaleX(0.78f);
            this.mManager.GetLayout().setScaleY(0.79f);
            return;
        }
        InitView();
    }

    private void InitView() {
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_rh7_bg_01);
        addButton(50, 117, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        addButton(50, KeyDef.RKEY_POWER, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = addText(51, Can.CAN_TEANA_OLD_DJ, 100, 61);
        addButton(880, 117, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 2);
        addButton(880, KeyDef.RKEY_POWER, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 3);
        this.mRightTemp = addText(881, Can.CAN_TEANA_OLD_DJ, 100, 61);
        addButton(188, 117, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 4);
        addButton(188, KeyDef.RKEY_POWER, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 5);
        addImage(191, Can.CAN_TEANA_OLD_DJ, R.drawable.can_rh7_8signal00_up);
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addImage(191, Can.CAN_TEANA_OLD_DJ, this.mIcons[i]);
        }
        this.mACMode[0] = addButton(KeyDef.RKEY_MEDIA_ANGLE, 78, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 6);
        this.mACMode[1] = addButton(KeyDef.RKEY_MEDIA_ANGLE, Can.CAN_LIEBAO_WC, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 8);
        this.mACMode[2] = addButton(KeyDef.RKEY_MEDIA_ANGLE, KeyDef.RKEY_MEDIA_OSD, R.drawable.can_rh7_icon05_up, R.drawable.can_rh7_icon05_dn, 9);
        this.mStatusWindow = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 70, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 10);
        this.mStatusOutLoop = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 182, R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_wxh_dn, 12);
        this.mStatusAc = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_AMS, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 15);
        this.mStatusDual = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 398, R.drawable.can_rh7_dual_up, R.drawable.can_rh7_dual_dn, 11);
        this.mStatusRearWindow = addButton(757, 70, R.drawable.can_rh7_window02_up, R.drawable.can_rh7_window02_dn, 16);
        this.mStatusMode = addButton(757, 182, R.drawable.can_rh7_mode_up, R.drawable.can_rh7_mode_dn, 21);
        this.mStatusAuto = addButton(757, KeyDef.RKEY_AMS, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 13);
        this.mStatusLoopAuto = addButton(757, 398, R.drawable.can_rh7_autoloop_up, R.drawable.can_rh7_autoloop_dn, 17);
        this.mStatusNormal = addButton(200, 20, R.drawable.conditioning_eco_01_up, R.drawable.conditioning_eco_01_dn, 19);
        this.mStatusFast = addButton(450, 20, R.drawable.conditioning_fast_up, R.drawable.conditioning_fast_dn, 20);
        this.mStatusSoft = addButton(KeyDef.RKEY_MEDIA_SLOW, 20, R.drawable.conditioning_soft_up, R.drawable.conditioning_soft_dn, 18);
        if (1 == CanJni.GetSubType()) {
            this.mStatusNormal.Show(false);
            this.mStatusFast.Show(false);
            this.mStatusSoft.Show(false);
            this.mStatusAuto.Show(false);
            this.mStatusDual.Show(false);
            this.mStatusLoopAuto.Show(false);
        }
    }

    public ParamButton addButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    private CustomImgView addImage(int x, int y, int resId) {
        return this.mManager.AddImage(x, y, resId);
    }

    private TextView addText(int x, int y, int w, int h) {
        TextView text = this.mManager.AddText(x, y, w, h);
        text.setTextColor(Color.parseColor("#08D2D3"));
        text.setTextSize(0, 24.0f);
        text.setGravity(17);
        return text;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        UpdateACUI();
        CanFunc.mfgShowAC = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        Can.updateAC();
        if (!CanFunc.mfgShowAC) {
            if (!this.isAutoFinish) {
                finish();
            }
            mfgJump = false;
            this.isAutoFinish = false;
        }
    }

    private void UpdateACUI() {
        CanDataInfo.CAN_ACInfo mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mLeftTemp.setText(mAcInfo.szLtTemp);
        this.mRightTemp.setText(mAcInfo.szRtTemp);
        setWindValue(mAcInfo.nWindValue);
        this.mACMode[0].SetSel(mAcInfo.fgParallelWind);
        this.mACMode[1].SetSel(mAcInfo.fgDownWind);
        this.mACMode[2].SetSel(mAcInfo.fgForeWindMode);
        this.mStatusWindow.SetSel(mAcInfo.fgMaxFornt);
        this.mStatusAc.SetSel(mAcInfo.fgAC);
        this.mStatusDual.SetSel(mAcInfo.fgDual);
        this.mStatusAuto.SetSel(mAcInfo.nAutoLight);
        this.mStatusRearWindow.SetSel(mAcInfo.fgRearLight);
        this.mStatusLoopAuto.SetSel(mAcInfo.fgAQS);
        this.mStatusOutLoop.setSelected(mAcInfo.fgInnerLoop == 0);
        this.mStatusSoft.SetSel(0);
        this.mStatusNormal.SetSel(0);
        this.mStatusFast.SetSel(0);
        if (mAcInfo.nWindAutoLevel == 0) {
            this.mStatusSoft.SetSel(1);
        } else if (mAcInfo.nWindAutoLevel == 1) {
            this.mStatusNormal.SetSel(1);
        } else if (mAcInfo.nWindAutoLevel == 2) {
            this.mStatusFast.SetSel(1);
        }
    }

    private void setWindValue(int wind) {
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i].Show(i + 1 <= wind);
        }
    }

    private void setAcMode(int index) {
        int i = 0;
        while (i < this.mACMode.length) {
            this.mACMode[i].setSelected(i == index);
            i++;
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int param;
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            param = 1;
        } else {
            if (action == 1) {
                param = 0;
            }
            return false;
        }
        switch (id) {
            case 0:
                CanJni.RenauKoleosAcSet(31, param);
                break;
            case 1:
                CanJni.RenauKoleosAcSet(30, param);
                break;
            case 2:
                CanJni.RenauKoleosAcSet(33, param);
                break;
            case 3:
                CanJni.RenauKoleosAcSet(32, param);
                break;
            case 4:
                CanJni.RenauKoleosAcSet(29, param);
                break;
            case 5:
                CanJni.RenauKoleosAcSet(28, param);
                break;
            case 6:
                CanJni.RenauKoleosAcSet(24, param);
                break;
            case 8:
                CanJni.RenauKoleosAcSet(26, param);
                break;
            case 9:
                CanJni.RenauKoleosAcSet(27, param);
                break;
            case 10:
                CanJni.RenauKoleosAcSet(21, param);
                break;
            case 11:
                CanJni.RenauKoleosAcSet(23, param);
                break;
            case 12:
                CanJni.RenauKoleosAcSet(19, param);
                break;
            case 13:
                CanJni.RenauKoleosAcSet(20, param);
                break;
            case 15:
                CanJni.RenauKoleosAcSet(17, param);
                break;
            case 16:
                CanJni.RenauKoleosAcSet(22, param);
                break;
            case 17:
                CanJni.RenauKoleosAcSet(34, param);
                break;
            case 18:
                CanJni.RenauKoleosAcSet(64, param);
                break;
            case 19:
                CanJni.RenauKoleosAcSet(65, param);
                break;
            case 20:
                CanJni.RenauKoleosAcSet(66, param);
                break;
            case 21:
                CanJni.RenauKoleosAcSet(25, param);
                break;
        }
        CanFunc.mLastACTick = GetTickCount();
        return false;
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            UpdateACUI();
        }
        if (mfgJump && GetTickCount() > CanFunc.mLastACTick + 3000) {
            finish();
            this.isAutoFinish = true;
        }
    }
}
