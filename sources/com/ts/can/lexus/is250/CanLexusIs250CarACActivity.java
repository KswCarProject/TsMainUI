package com.ts.can.lexus.is250;

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

public class CanLexusIs250CarACActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener {
    private static final int CLOSED = 16;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int LT_TEMP__DECREASE = 1;
    private static final int MODE_BOTH = 7;
    private static final int MODE_FOOT = 8;
    private static final int MODE_FOOT_WIN = 9;
    private static final int MODE_HEAD = 6;
    private static final int RT_TEMP_INCREASE = 2;
    private static final int RT_TEMP__DECREASE = 3;
    private static final int STATUS_AC = 15;
    private static final int STATUS_AUTO = 13;
    private static final int STATUS_AUTO_MODE = 17;
    private static final int STATUS_DUAL = 11;
    private static final int STATUS_LOOP = 12;
    private static final int STATUS_MODE = 18;
    private static final int STATUS_REAR_WIN = 14;
    private static final int STATUS_WINOW = 10;
    private static final int WIND_DECREASE = 5;
    private static final int WIND_INCREASE = 4;
    private static boolean mfgJump;
    private boolean isAutoFinish = false;
    private ParamButton[] mACMode = new ParamButton[4];
    private int[] mIcons = {R.drawable.can_rh7_signal01_dn, R.drawable.can_rh7_signal02_dn, R.drawable.can_rh7_signal03_dn, R.drawable.can_rh7_signal04_dn, R.drawable.can_rh7_signal05_dn, R.drawable.can_rh7_signal06_dn, R.drawable.can_rh7_signal07_dn};
    private TextView mLeftTemp;
    private RelativeLayoutManager mManager;
    private TextView mRightTemp;
    private ParamButton mStatusAc;
    private ParamButton mStatusAuto;
    private ParamButton mStatusAutoMode;
    private ParamButton mStatusDual;
    private ParamButton mStatusMode;
    private ParamButton mStatusOutLoop;
    private ParamButton mStatusRearWin;
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
        this.mACMode[0] = addButton(KeyDef.RKEY_MEDIA_ANGLE, 78, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 6);
        this.mACMode[1] = addButton(KeyDef.RKEY_MEDIA_ANGLE, Can.CAN_DFFG_S560, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 7);
        this.mACMode[2] = addButton(KeyDef.RKEY_MEDIA_ANGLE, Can.CAN_LIEBAO_WC, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 8);
        this.mACMode[3] = addButton(KeyDef.RKEY_MEDIA_ANGLE, KeyDef.RKEY_MEDIA_OSD, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 9);
        this.mStatusMode = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 407, R.drawable.can_rh7_mode_up, R.drawable.can_rh7_mode_dn, 18);
        this.mStatusOutLoop = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 182, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, 12);
        this.mStatusWindow = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 70, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 10);
        this.mStatusRearWin = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_AMS, R.drawable.can_rh7_window02_up, R.drawable.can_rh7_window02_dn, 14);
        this.mStatusAc = addButton(757, KeyDef.RKEY_AMS, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 15);
        this.mStatusDual = addButton(757, 70, R.drawable.can_rh7_dual_up, R.drawable.can_rh7_dual_dn, 11);
        this.mStatusAuto = addButton(757, 182, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 13);
        this.mStatusAutoMode = addButton(757, 407, R.drawable.can_rh7_05_up, R.drawable.can_rh7_05_dn, 17);
        addButton(462, 406, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 16);
    }

    public ParamButton addButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    public ParamButton addButton1(int x, int y, int normal, int pressed, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y);
        button.setStateDrawable(normal, pressed, selected);
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
        CanDataInfo.CAN_ACInfo mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mLeftTemp.setText(mAcInfo.szLtTemp);
        this.mRightTemp.setText(mAcInfo.szRtTemp);
        setWindValue(mAcInfo.nWindValue);
        int footWind = mAcInfo.fgDownWind;
        int headWind = mAcInfo.fgParallelWind;
        int winWind = mAcInfo.fgUpWind;
        if (i2b(footWind) && i2b(headWind)) {
            setAcMode(1);
        } else if (i2b(headWind)) {
            setAcMode(0);
        } else if (i2b(footWind) && i2b(winWind)) {
            setAcMode(3);
        } else if (i2b(footWind)) {
            setAcMode(2);
        } else if (!i2b(winWind)) {
            setAcMode(-1);
        }
        this.mStatusWindow.SetSel(mAcInfo.fgDFBL);
        this.mStatusAc.SetSel(mAcInfo.fgAC);
        this.mStatusDual.SetSel(mAcInfo.fgDual);
        this.mStatusAuto.SetSel(mAcInfo.nAutoLight);
        this.mStatusRearWin.SetSel(mAcInfo.fgRearLight);
        if (mAcInfo.fgAQS != 0) {
            this.mStatusOutLoop.setDrawable(R.drawable.can_rh7_autoloop_up, R.drawable.can_rh7_autoloop_dn);
        } else if (mAcInfo.fgInnerLoop != 0) {
            this.mStatusOutLoop.setDrawable(R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_nxh_dn);
        } else {
            this.mStatusOutLoop.setDrawable(R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn);
        }
        this.mStatusAutoMode.SetSel(mAcInfo.fgAutoMode);
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
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            switch (id) {
                case 0:
                    CanJni.LexusIs250AirKey(2, 1);
                    break;
                case 1:
                    CanJni.LexusIs250AirKey(2, 0);
                    break;
                case 2:
                    CanJni.LexusIs250AirKey(3, 1);
                    break;
                case 3:
                    CanJni.LexusIs250AirKey(3, 0);
                    break;
                case 4:
                    CanJni.LexusIs250AirKey(1, 1);
                    break;
                case 5:
                    CanJni.LexusIs250AirKey(1, 0);
                    break;
                case 10:
                    CanJni.LexusIs250AirKey(7, 0);
                    break;
                case 11:
                    CanJni.LexusIs250AirKey(10, 0);
                    break;
                case 12:
                    CanJni.LexusIs250AirKey(6, 0);
                    break;
                case 13:
                    CanJni.LexusIs250AirKey(12, 0);
                    break;
                case 14:
                    CanJni.LexusIs250AirKey(8, 0);
                    break;
                case 15:
                    CanJni.LexusIs250AirKey(5, 0);
                    break;
                case 16:
                    CanJni.LexusIs250AirKey(9, 1);
                    break;
                case 17:
                    CanJni.LexusIs250AirKey(11, 0);
                    break;
                case 18:
                    CanJni.LexusIs250AirKey(4, 1);
                    break;
            }
            CanFunc.mLastACTick = GetTickCount();
        }
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
