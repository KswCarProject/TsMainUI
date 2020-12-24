package com.ts.can.gac.trumpchi;

import android.graphics.Color;
import android.os.Bundle;
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

public class CanGs8AcActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener {
    private static final int AC_MAX = 17;
    private static final int AC_REAR = 9;
    private static final int CLOSED = 16;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int LT_TEMP__DECREASE = 1;
    private static final int RR_TEMP_DECREASE = 7;
    private static final int RR_TEMP_INCREASE = 6;
    private static final int RT_TEMP_INCREASE = 2;
    private static final int RT_TEMP__DECREASE = 3;
    private static final int STATUS_AC = 15;
    private static final int STATUS_AUTO = 13;
    private static final int STATUS_DUAL = 11;
    private static final int STATUS_MODE = 14;
    private static final int STATUS_OUT_LOOP = 12;
    private static final int STATUS_WINOW = 10;
    private static final int WIND_DECREASE = 5;
    private static final int WIND_INCREASE = 4;
    private static boolean mfgJump;
    private boolean isAutoFinish = false;
    private CustomImgView[] mACMode = new CustomImgView[3];
    private int[] mIcons = {R.drawable.can_rh7_signal01_dn, R.drawable.can_rh7_signal02_dn, R.drawable.can_rh7_signal03_dn, R.drawable.can_rh7_signal04_dn, R.drawable.can_rh7_signal05_dn, R.drawable.can_rh7_signal06_dn, R.drawable.can_rh7_signal07_dn};
    private TextView mLeftTemp;
    private RelativeLayoutManager mManager;
    private TextView mRRightTemp;
    private TextView mRearAir;
    private TextView mRightTemp;
    private ParamButton mStatusAc;
    private ParamButton mStatusAcMax;
    private ParamButton mStatusAcRear;
    private ParamButton mStatusAuto;
    private ParamButton mStatusDual;
    private ParamButton mStatusMode;
    private ParamButton mStatusOutLoop;
    private ParamButton mStatusWindow;
    private CustomImgView[] mWindIcons = new CustomImgView[7];
    private CanDataInfo.GCAirData m_RearAcInfo = new CanDataInfo.GCAirData();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        mfgJump = CanFunc.IsCanActivityJumped(this);
        InitView();
    }

    private void InitView() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_rh7_bg);
        addButton(50, 87, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        addButton(50, 284, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = addText(53, 198, 92, 61);
        addButton(880, 87, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 2);
        addButton(880, 284, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 3);
        this.mRightTemp = addText(883, 198, 92, 61);
        addButton(188, 87, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 4);
        addButton(188, 284, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 5);
        addImage(191, 198, R.drawable.can_rh7_signal_up);
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addImage(191, 198, this.mIcons[i]);
        }
        this.mACMode[0] = this.mManager.AddImage(KeyDef.RKEY_MEDIA_ANGLE, 78);
        this.mACMode[0].setStateDrawable(R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn);
        this.mACMode[1] = this.mManager.AddImage(KeyDef.RKEY_MEDIA_ANGLE, Can.CAN_DFFG_S560);
        this.mACMode[1].setStateDrawable(R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn);
        this.mACMode[2] = this.mManager.AddImage(KeyDef.RKEY_MEDIA_ANGLE, Can.CAN_FLAT_WC);
        this.mACMode[2].setStateDrawable(R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn);
        this.mStatusWindow = addButton(757, 408, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 10);
        this.mStatusOutLoop = addButton(757, 182, R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_wxh_up, 12);
        this.mStatusMode = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_AMS, R.drawable.can_rh7_mode_up, R.drawable.can_rh7_mode_dn, 14);
        this.mStatusDual = addButton(757, 70, R.drawable.can_rh7_3zone_up, R.drawable.can_rh7_3zone_dn, 11);
        this.mStatusAuto = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 182, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 13);
        this.mStatusAc = addButton(757, KeyDef.RKEY_AMS, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 15);
        this.mStatusAcRear = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 70, R.drawable.can_rh7_rear_up, R.drawable.can_rh7_rear_dn, 9);
        this.mStatusAcMax = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 408, R.drawable.can_rh7_max_up, R.drawable.can_rh7_max_dn, 17);
        addButton(50, 406, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 16);
        this.mRearAir = addText(Can.CAN_BENC_ZMYT, 18, Can.CAN_NISSAN_XFY, 61);
        if (CanJni.GetSubType() == 6) {
            addButton(166, 414, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 6);
            addButton(365, 414, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 7);
            this.mRRightTemp = addText(268, 425, 92, 61);
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
        text.setTextColor(Color.parseColor("#ffffff"));
        text.setTextSize(0, 27.0f);
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
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        CanDataInfo.CAN_ACInfo mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.m_RearAcInfo.Update = 0;
        this.mLeftTemp.setText(mAcInfo.szLtTemp);
        this.mRightTemp.setText(mAcInfo.szRtTemp);
        if (this.mRRightTemp != null) {
            this.mRRightTemp.setText(this.m_RearAcInfo.szRearTemp);
        }
        this.mStatusWindow.SetSel(mAcInfo.fgDFBL);
        this.mStatusAc.SetSel(mAcInfo.fgAC);
        this.mStatusDual.SetSel(mAcInfo.fgDual);
        this.mStatusOutLoop.setSelected(mAcInfo.fgInnerLoop == 0);
        this.mStatusAcMax.SetSel(mAcInfo.fgACMax);
        if (!i2b(mAcInfo.fgRearLock)) {
            this.mRearAir.setText(" ");
            setWindValue(mAcInfo.nWindValue);
            CustomImgView customImgView = this.mACMode[0];
            if (!i2b(mAcInfo.fgParallelWind) || i2b(mAcInfo.fgDownWind)) {
                z3 = false;
            } else {
                z3 = true;
            }
            customImgView.setSelected(z3);
            CustomImgView customImgView2 = this.mACMode[1];
            if (!i2b(mAcInfo.fgDownWind) || i2b(mAcInfo.fgParallelWind)) {
                z4 = false;
            } else {
                z4 = true;
            }
            customImgView2.setSelected(z4);
            CustomImgView customImgView3 = this.mACMode[2];
            if (!i2b(mAcInfo.fgDownWind) || !i2b(mAcInfo.fgParallelWind)) {
                z5 = false;
            }
            customImgView3.setSelected(z5);
            this.mStatusAuto.SetSel(mAcInfo.nAutoLight);
        } else {
            this.mRearAir.setText(R.string.can_gs8_rearac);
            setWindValue(this.m_RearAcInfo.nWindValue);
            CustomImgView customImgView4 = this.mACMode[0];
            if (!i2b(this.m_RearAcInfo.bParallelWindFlg) || i2b(this.m_RearAcInfo.bDownWindFlg)) {
                z = false;
            } else {
                z = true;
            }
            customImgView4.setSelected(z);
            CustomImgView customImgView5 = this.mACMode[1];
            if (!i2b(this.m_RearAcInfo.bDownWindFlg) || i2b(this.m_RearAcInfo.bParallelWindFlg)) {
                z2 = false;
            } else {
                z2 = true;
            }
            customImgView5.setSelected(z2);
            CustomImgView customImgView6 = this.mACMode[2];
            if (!i2b(this.m_RearAcInfo.bDownWindFlg) || !i2b(this.m_RearAcInfo.bParallelWindFlg)) {
                z5 = false;
            }
            customImgView6.setSelected(z5);
            this.mStatusAuto.SetSel(this.m_RearAcInfo.nAutoLight);
        }
        this.mStatusAcRear.SetSel(mAcInfo.fgRearLock);
    }

    private void setWindValue(int wind) {
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i].Show(i + 1 <= wind);
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
                CanJni.Gs8AcSet(13, param);
                break;
            case 1:
                CanJni.Gs8AcSet(14, param);
                break;
            case 2:
                CanJni.Gs8AcSet(15, param);
                break;
            case 3:
                CanJni.Gs8AcSet(16, param);
                break;
            case 4:
                CanJni.Gs8AcSet(11, param);
                break;
            case 5:
                CanJni.Gs8AcSet(12, param);
                break;
            case 6:
                CanJni.Gs8AcSet(28, param);
                break;
            case 7:
                CanJni.Gs8AcSet(29, param);
                break;
            case 9:
                CanJni.Gs8AcSet(27, param);
                break;
            case 10:
                CanJni.Gs8AcSet(6, param);
                break;
            case 11:
                if (CanJni.GetSubType() != 6) {
                    CanJni.Gs8AcSet(3, param);
                    break;
                } else {
                    CanJni.Gs8AcSet(31, param);
                    break;
                }
            case 12:
                CanJni.Gs8AcSet(21, param);
                break;
            case 13:
                CanJni.Gs8AcSet(2, param);
                break;
            case 14:
                CanJni.Gs8AcSet(17, param);
                break;
            case 15:
                CanJni.Gs8AcSet(1, param);
                break;
            case 16:
                CanJni.Gs8AcSet(0, param);
                break;
            case 17:
                CanJni.Gs8AcSet(32, param);
                break;
        }
        CanFunc.mLastACTick = GetTickCount();
        return false;
    }

    public void UserAll() {
        Can.updateAC();
        CanJni.Gs8GetAcSet(this.m_RearAcInfo);
        if (!(Can.mACInfo.Update == 0 && this.m_RearAcInfo.Update == 0)) {
            UpdateACUI();
        }
        if (mfgJump && GetTickCount() > CanFunc.mLastACTick + 3000) {
            finish();
            this.isAutoFinish = true;
        }
    }
}
