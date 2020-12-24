package com.ts.can.baic.wc.m50f;

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
import com.yyw.ts70xhw.KeyDef;

public class CanVenuciaWcM50FACView extends CanBaseACView {
    private static final int AC = 10;
    private static final int CLOSED = 12;
    private static final int FRONT_WINOW = 8;
    private static final int LOOPER = 11;
    private static final int LT_TEMP_DECREASE = 1;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int MODE_DOWN = 5;
    private static final int MODE_MID = 4;
    private static final int MODE_MID_DOWN = 7;
    private static final int MODE_WIN_DOWN = 6;
    private static final int REAR_WINOW = 9;
    private static final int WIND_DECREASE = 3;
    private static final int WIND_INCREASE = 2;
    private static int[] mIcons = {R.drawable.can_rh7_8signal01_dn, R.drawable.can_rh7_8signal02_dn, R.drawable.can_rh7_8signal03_dn, R.drawable.can_rh7_8signal04_dn, R.drawable.can_rh7_8signal05_dn, R.drawable.can_rh7_8signal06_dn, R.drawable.can_rh7_8signal07_dn, R.drawable.can_rh7_8signal08_dn};
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAC;
    private ParamButton mBtnClose;
    private ParamButton mBtnFrontWin;
    private ParamButton mBtnLooper;
    private ParamButton mBtnRearWin;
    private TextView mLeftTemp;
    private CustomImgView[] mWindIcons;
    private ParamButton[] mWindMode;

    public CanVenuciaWcM50FACView(Activity activity) {
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
                CanJni.VenucaiWcM50fAirKey(13, para);
                return;
            case 1:
                CanJni.VenucaiWcM50fAirKey(14, para);
                return;
            case 2:
                CanJni.VenucaiWcM50fAirKey(11, para);
                return;
            case 3:
                CanJni.VenucaiWcM50fAirKey(12, para);
                return;
            case 4:
                CanJni.VenucaiWcM50fAirKey(26, para);
                return;
            case 5:
                CanJni.VenucaiWcM50fAirKey(29, para);
                return;
            case 6:
                CanJni.VenucaiWcM50fAirKey(28, para);
                return;
            case 7:
                CanJni.VenucaiWcM50fAirKey(27, para);
                return;
            case 8:
                CanJni.VenucaiWcM50fAirKey(5, para);
                return;
            case 9:
                CanJni.VenucaiWcM50fAirKey(6, para);
                return;
            case 10:
                CanJni.VenucaiWcM50fAirKey(2, para);
                return;
            case 11:
                CanJni.VenucaiWcM50fAirKey(7, para);
                return;
            case 12:
                CanJni.VenucaiWcM50fAirKey(1, para);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
    }

    public void QueryData() {
        CanJni.VenucaiWcM50fQuery(5, 1, 49);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        setBackgroundResource(R.drawable.can_rh7_bg02);
        this.mWindIcons = new CustomImgView[8];
        this.mWindMode = new ParamButton[4];
        addVenuciaButton(50, 117, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        addVenuciaButton(50, KeyDef.RKEY_POWER, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = addVenuciaText(53, Can.CAN_TEANA_OLD_DJ, 92, 61);
        addVenuciaButton(188, 117, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 2);
        addVenuciaButton(188, KeyDef.RKEY_POWER, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 3);
        addVenuciaImage(191, Can.CAN_TEANA_OLD_DJ, R.drawable.can_rh7_8signal00_up);
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addVenuciaImage(191, Can.CAN_TEANA_OLD_DJ, mIcons[i]);
        }
        this.mWindMode[0] = addVenuciaButton(KeyDef.RKEY_MEDIA_ANGLE, 108, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 4);
        this.mWindMode[1] = addVenuciaButton(KeyDef.RKEY_MEDIA_ANGLE, 185, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 5);
        this.mWindMode[2] = addVenuciaButton(KeyDef.RKEY_MEDIA_ANGLE, 264, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 6);
        this.mWindMode[3] = addVenuciaButton(KeyDef.RKEY_MEDIA_ANGLE, KeyDef.RKEY_res5, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 7);
        this.mBtnLooper = addVenuciaButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 212, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, 11);
        this.mBtnFrontWin = addVenuciaButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 100, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 8);
        this.mBtnRearWin = addVenuciaButton(757, 100, R.drawable.can_rh7_window02_up, R.drawable.can_rh7_window02_dn, 9);
        this.mBtnAC = addVenuciaButton(757, 212, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 10);
        addVenuciaButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_RADIO_3S, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 12);
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mAcInfo = Can.mACInfo;
        this.mLeftTemp.setText(this.mAcInfo.szLtTemp);
        setWindValue(this.mAcInfo.nWindValue);
        int footWind = this.mAcInfo.fgDownWind;
        int headWind = this.mAcInfo.fgParallelWind;
        int winWind = this.mAcInfo.fgForeWindMode;
        for (ParamButton selected : this.mWindMode) {
            selected.setSelected(false);
        }
        if (i2b(footWind) && i2b(winWind)) {
            this.mWindMode[2].setSelected(true);
        } else if (i2b(footWind) && i2b(headWind)) {
            this.mWindMode[3].setSelected(true);
        } else if (i2b(footWind)) {
            this.mWindMode[1].setSelected(true);
        } else if (i2b(headWind)) {
            this.mWindMode[0].setSelected(true);
        }
        this.mBtnAC.SetSel(this.mAcInfo.fgAC);
        this.mBtnFrontWin.SetSel(this.mAcInfo.fgDFBL);
        this.mBtnRearWin.SetSel(this.mAcInfo.fgRearLight);
        if (this.mAcInfo.fgAQS != 0) {
            this.mBtnLooper.setDrawable(R.drawable.can_rh7_autoloop_up, R.drawable.can_rh7_autoloop_dn);
        } else if (this.mAcInfo.fgInnerLoop != 0) {
            this.mBtnLooper.setDrawable(R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_nxh_dn);
        } else {
            this.mBtnLooper.setDrawable(R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn);
        }
    }

    public ParamButton addVenuciaButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = getRelativeManager().AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    private CustomImgView addVenuciaImage(int x, int y, int resId) {
        return getRelativeManager().AddImage(x, y, resId);
    }

    private TextView addVenuciaText(int x, int y, int w, int h) {
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
}
