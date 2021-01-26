package com.ts.can.lexus.lz;

import android.app.Activity;
import android.graphics.Color;
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

public class CanLexusLZIs250ACView extends CanBaseACView {
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
    private static int[] mIcons = {R.drawable.can_rh7_signal01_dn, R.drawable.can_rh7_signal02_dn, R.drawable.can_rh7_signal03_dn, R.drawable.can_rh7_signal04_dn, R.drawable.can_rh7_signal05_dn, R.drawable.can_rh7_signal06_dn, R.drawable.can_rh7_signal07_dn};
    private ParamButton[] mACMode;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private TextView mAutoMode;
    private TextView mAutoWind;
    private TextView mLeftTemp;
    private TextView mRightTemp;
    private ParamButton mStatusAc;
    private ParamButton mStatusAuto;
    private ParamButton mStatusAutoMode;
    private ParamButton mStatusDual;
    private ParamButton mStatusMode;
    private ParamButton mStatusOutLoop;
    private ParamButton mStatusRearWin;
    private ParamButton mStatusWindow;
    private CustomImgView[] mWindIcons;

    public CanLexusLZIs250ACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        if (action == 0) {
            SendKey(id, 1);
        } else if (action == 1) {
            SendKey(id, 0);
        }
        return false;
    }

    private void SendKey(int id, int para) {
        switch (id) {
            case 0:
                CanJni.LexusLzAirKey(3, para);
                return;
            case 1:
                CanJni.LexusLzAirKey(2, para);
                return;
            case 2:
                CanJni.LexusLzAirKey(5, para);
                return;
            case 3:
                CanJni.LexusLzAirKey(4, para);
                return;
            case 4:
                CanJni.LexusLzAirKey(10, para);
                return;
            case 5:
                CanJni.LexusLzAirKey(9, para);
                return;
            case 10:
                CanJni.LexusLzAirKey(18, para);
                return;
            case 11:
                CanJni.LexusLzAirKey(16, para);
                return;
            case 12:
                CanJni.LexusLzAirKey(25, para);
                return;
            case 13:
                CanJni.LexusLzAirKey(21, para);
                return;
            case 14:
                CanJni.LexusLzAirKey(20, para);
                return;
            case 15:
                CanJni.LexusLzAirKey(23, para);
                return;
            case 16:
                CanJni.LexusLzAirKey(1, para);
                return;
            case 17:
                CanJni.LexusLzAirKey(40, para);
                return;
            case 18:
                CanJni.LexusLzAirKey(36, para);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void QueryData() {
        super.QueryData();
        CanJni.LexusLzQuery(40, 0);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        setBackgroundResource(R.drawable.can_rh7_bg);
        this.mAutoMode = getRelativeManager().AddCusText(295, 426, 118, 30);
        this.mAutoMode.setTextColor(-1);
        this.mAutoMode.setTextSize(12.0f);
        this.mAutoMode.setText("AUTO");
        this.mAutoMode.setGravity(17);
        this.mAutoWind = getRelativeManager().AddCusText(178, 196, 118, 30);
        this.mAutoWind.setTextColor(-1);
        this.mAutoWind.setTextSize(12.0f);
        this.mAutoWind.setText("AUTO");
        this.mAutoWind.setGravity(17);
        this.mWindIcons = new CustomImgView[7];
        this.mACMode = new ParamButton[3];
        addLexusButton(50, 87, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        addLexusButton(50, 284, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = addLexusText(53, 198, 92, 61);
        addLexusButton(880, 87, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 2);
        addLexusButton(880, 284, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 3);
        this.mRightTemp = addLexusText(883, 198, 92, 61);
        addLexusButton(188, 87, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 4);
        addLexusButton(188, 284, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 5);
        addLexusImage(191, 198, R.drawable.can_rh7_signal_up);
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addLexusImage(191, 198, mIcons[i]);
        }
        this.mACMode[0] = addLexusButton(305, 78, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 6);
        this.mACMode[1] = addLexusButton(305, Can.CAN_DFFG_S560, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 8);
        this.mACMode[2] = addLexusButton(305, Can.CAN_LIEBAO_WC, R.drawable.can_rh7_icon05_up, R.drawable.can_rh7_icon05_dn, 8);
        this.mStatusMode = addLexusButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 407, R.drawable.can_rh7_mode_up, R.drawable.can_rh7_mode_dn, 18);
        this.mStatusOutLoop = addLexusButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 182, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, 12);
        this.mStatusWindow = addLexusButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 70, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 10);
        this.mStatusRearWin = addLexusButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 295, R.drawable.can_rh7_window02_up, R.drawable.can_rh7_window02_dn, 14);
        this.mStatusAc = addLexusButton(757, 295, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 15);
        this.mStatusDual = addLexusButton(757, 70, R.drawable.can_rh7_dual_up, R.drawable.can_rh7_dual_dn, 11);
        this.mStatusAuto = addLexusButton(757, 182, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 13);
        this.mStatusAutoMode = addLexusButton(757, 407, R.drawable.can_rh7_05_up, R.drawable.can_rh7_05_dn, 17);
        addLexusButton(462, 406, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 16);
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mAcInfo = Can.mACInfo;
        setViewShow(this.mAutoMode, this.mAcInfo.fgAutoMode);
        setViewShow(this.mAutoWind, this.mAcInfo.fgAutoWind);
        this.mLeftTemp.setText(this.mAcInfo.szLtTemp);
        this.mRightTemp.setText(this.mAcInfo.szRtTemp);
        setWindValue(this.mAcInfo.nWindValue);
        int footWind = this.mAcInfo.fgDownWind;
        int headWind = this.mAcInfo.fgParallelWind;
        int winWind = this.mAcInfo.fgForeWindMode;
        for (ParamButton selected : this.mACMode) {
            selected.setSelected(false);
        }
        if (i2b(headWind)) {
            setAcMode(0);
        }
        if (i2b(footWind)) {
            setAcMode(1);
        }
        if (i2b(winWind)) {
            setAcMode(2);
        }
        this.mStatusWindow.SetSel(this.mAcInfo.fgMaxFornt);
        this.mStatusAc.SetSel(this.mAcInfo.fgAC);
        this.mStatusDual.SetSel(this.mAcInfo.fgDual);
        this.mStatusAuto.SetSel(this.mAcInfo.nAutoLight);
        this.mStatusRearWin.SetSel(this.mAcInfo.fgRearLight);
        if (this.mAcInfo.fgAQS != 0) {
            this.mStatusOutLoop.setDrawable(R.drawable.can_rh7_autoloop_up, R.drawable.can_rh7_autoloop_dn);
        } else if (this.mAcInfo.fgInnerLoop != 0) {
            this.mStatusOutLoop.setDrawable(R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_nxh_dn);
        } else {
            this.mStatusOutLoop.setDrawable(R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn);
        }
        this.mStatusAutoMode.SetSel(this.mAcInfo.fgDFBL);
    }

    public ParamButton addLexusButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = getRelativeManager().AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    public ParamButton addLexusButton1(int x, int y, int normal, int pressed, int selected, int id) {
        ParamButton button = getRelativeManager().AddButton(x, y);
        button.setStateDrawable(normal, pressed, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    private CustomImgView addLexusImage(int x, int y, int resId) {
        return getRelativeManager().AddImage(x, y, resId);
    }

    private TextView addLexusText(int x, int y, int w, int h) {
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextColor(Color.parseColor("#08D2D3"));
        text.setTextSize(0, 27.0f);
        text.setGravity(17);
        return text;
    }

    protected static boolean int2Bool(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    public void setViewShow(View v, int val) {
        if (int2Bool(val)) {
            v.setVisibility(0);
        } else {
            v.setVisibility(4);
        }
    }

    private void setWindValue(int wind) {
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i].Show(i + 1 <= wind);
        }
    }

    private void setAcMode(int index) {
        this.mACMode[index].setSelected(true);
    }
}
