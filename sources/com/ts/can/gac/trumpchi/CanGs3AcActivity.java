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

public class CanGs3AcActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener {
    private static final int CLOSED = 16;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int LT_TEMP__DECREASE = 1;
    private static final int STATUS_AC = 15;
    private static final int STATUS_AUTO = 13;
    private static final int STATUS_ION = 17;
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
    private ParamButton mStatusAc;
    private ParamButton mStatusAuto;
    private ParamButton mStatusIon;
    private ParamButton mStatusMode;
    private ParamButton mStatusOutLoop;
    private ParamButton mStatusWindow;
    private CustomImgView[] mWindIcons = new CustomImgView[7];

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        mfgJump = CanFunc.IsCanActivityJumped(this);
        InitView();
    }

    private void InitView() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_rh7_bg02);
        addButton(50, 117, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        addButton(50, KeyDef.RKEY_POWER, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = addText(53, Can.CAN_TEANA_OLD_DJ, 92, 61);
        addButton(188, 117, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 4);
        addButton(188, KeyDef.RKEY_POWER, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 5);
        addImage(191, Can.CAN_TEANA_OLD_DJ, R.drawable.can_rh7_signal_up);
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addImage(191, Can.CAN_TEANA_OLD_DJ, this.mIcons[i]);
        }
        this.mACMode[0] = this.mManager.AddImage(305, 108);
        this.mACMode[0].setStateDrawable(R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn);
        this.mACMode[1] = this.mManager.AddImage(305, 188);
        this.mACMode[1].setStateDrawable(R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn);
        this.mACMode[2] = this.mManager.AddImage(305, 268);
        this.mACMode[2].setStateDrawable(R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn);
        this.mStatusWindow = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 202, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 10);
        this.mStatusOutLoop = addButton(KeyDef.SKEY_POWEWR_2, 90, R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_wxh_up, 12);
        this.mStatusMode = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_EJECT, R.drawable.can_rh7_mode_up, R.drawable.can_rh7_mode_dn, 14);
        this.mStatusAuto = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 90, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 13);
        this.mStatusAc = addButton(KeyDef.SKEY_POWEWR_2, 202, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 15);
        this.mStatusIon = addButton(KeyDef.SKEY_POWEWR_2, KeyDef.RKEY_EJECT, R.drawable.can_rh7_ion_up, R.drawable.can_rh7_ion_dn, 17);
        addButton(470, 420, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 16);
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
        boolean z3 = true;
        CanDataInfo.CAN_ACInfo mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mLeftTemp.setText(mAcInfo.szLtTemp);
        this.mStatusWindow.SetSel(mAcInfo.fgDFBL);
        this.mStatusAc.SetSel(mAcInfo.fgAC);
        this.mStatusOutLoop.setSelected(mAcInfo.fgInnerLoop == 0);
        this.mStatusIon.SetSel(mAcInfo.fgIon);
        setWindValue(mAcInfo.nWindValue);
        CustomImgView customImgView = this.mACMode[0];
        if (!i2b(mAcInfo.fgParallelWind) || i2b(mAcInfo.fgDownWind)) {
            z = false;
        } else {
            z = true;
        }
        customImgView.setSelected(z);
        CustomImgView customImgView2 = this.mACMode[1];
        if (!i2b(mAcInfo.fgDownWind) || i2b(mAcInfo.fgParallelWind)) {
            z2 = false;
        } else {
            z2 = true;
        }
        customImgView2.setSelected(z2);
        CustomImgView customImgView3 = this.mACMode[2];
        if (!i2b(mAcInfo.fgDownWind) || !i2b(mAcInfo.fgParallelWind)) {
            z3 = false;
        }
        customImgView3.setSelected(z3);
        this.mStatusAuto.SetSel(mAcInfo.nAutoLight);
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
            case 4:
                CanJni.Gs8AcSet(11, param);
                break;
            case 5:
                CanJni.Gs8AcSet(12, param);
                break;
            case 10:
                CanJni.Gs8AcSet(6, param);
                break;
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
                CanJni.Gs8AcSet(30, param);
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
