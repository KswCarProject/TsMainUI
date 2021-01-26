package com.ts.can.chana.wc.cos;

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

public class CanChanACosACView extends CanBaseACView {
    private static final int CLOSED = 10;
    private static final int LT_TEMP_DECREASE = 1;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int MODE_MODE = 4;
    private static final int REARCLOSED = 11;
    private static final int STATUS_AC = 5;
    private static final int STATUS_ACMAX = 12;
    private static final int STATUS_AUTO = 6;
    private static final int STATUS_CYCLE = 9;
    private static final int STATUS_REARWINOW = 8;
    private static final int STATUS_WINOW = 7;
    private static final int USELESS = 100;
    private static final int WIND_DECREASE = 3;
    private static final int WIND_INCREASE = 2;
    private ParamButton[] mACMode;
    private TextView mLeftTemp;
    private ParamButton mStatusAc;
    private ParamButton mStatusAcMax;
    private ParamButton mStatusAuto;
    private ParamButton mStatusClosed;
    private ParamButton mStatusCycle;
    private ParamButton mStatusInnerLoop;
    private ParamButton mStatusRearClosed;
    private ParamButton mStatusRearWindow;
    private ParamButton mStatusWindow;
    private CustomImgView[] mWindIcons;

    public CanChanACosACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            sendAcKey(id, 1);
        } else if (action == 1) {
            sendAcKey(id, 0);
        }
        return false;
    }

    private void sendAcKey(int id, int para) {
        switch (id) {
            case 0:
                CanJni.ChanAWcCos1AcSet(13, para);
                return;
            case 1:
                CanJni.ChanAWcCos1AcSet(14, para);
                return;
            case 2:
                CanJni.ChanAWcCos1AcSet(11, para);
                return;
            case 3:
                CanJni.ChanAWcCos1AcSet(12, para);
                return;
            case 4:
                CanJni.ChanAWcCos1AcSet(21, para);
                return;
            case 5:
                CanJni.ChanAWcCos1AcSet(2, para);
                return;
            case 6:
                CanJni.ChanAWcCos1AcSet(4, para);
                return;
            case 7:
                CanJni.ChanAWcCos1AcSet(5, para);
                return;
            case 8:
                CanJni.ChanAWcCos1AcSet(6, para);
                return;
            case 9:
                CanJni.ChanAWcCos1AcSet(7, para);
                return;
            case 10:
                CanJni.ChanAWcCos1AcSet(1, para);
                return;
            case 12:
                CanJni.ChanAWcCos1AcSet(30, para);
                return;
            default:
                return;
        }
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        setBackgroundResource(R.drawable.can_rh7_bg02);
        initCommonScreen();
    }

    public void QueryData() {
        super.QueryData();
        CanJni.ChanAWcCos1Query(5, 1, 49);
    }

    private void initCommonScreen() {
        this.mWindIcons = new CustomImgView[8];
        this.mACMode = new ParamButton[5];
        int[] mIcons = {R.drawable.can_rh7_8signal01_dn, R.drawable.can_rh7_8signal02_dn, R.drawable.can_rh7_8signal03_dn, R.drawable.can_rh7_8signal04_dn, R.drawable.can_rh7_8signal05_dn, R.drawable.can_rh7_8signal06_dn, R.drawable.can_rh7_8signal07_dn, R.drawable.can_rh7_8signal08_dn};
        ACaddButton(50, 107, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        ACaddButton(50, 304, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = ACaddText(53, 218, 92, 61);
        ACaddButton(188, 107, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 2);
        ACaddButton(188, 304, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 3);
        addImage(191, 218, R.drawable.can_rh7_8signal00_up);
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addImage(191, 218, mIcons[i]);
        }
        this.mACMode[0] = ACaddButton(305, 88, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 100);
        this.mACMode[1] = ACaddButton(305, 165, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 100);
        this.mACMode[2] = ACaddButton(305, Can.CAN_BYD_M6_DJ, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 100);
        this.mACMode[3] = ACaddButton(305, KeyDef.RKEY_MEDIA_10, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 100);
        this.mACMode[4] = ACaddButton(305, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, R.drawable.can_rh7_icon05_up, R.drawable.can_rh7_icon05_dn, 100);
        this.mStatusWindow = ACaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 98, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 7);
        this.mStatusRearWindow = ACaddButton(757, 98, R.drawable.can_rh7_window02_up, R.drawable.can_rh7_window02_dn, 8);
        this.mStatusCycle = ACaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 210, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, 9);
        ACaddButton(757, 210, R.drawable.can_rh7_mode_up, R.drawable.can_rh7_mode_dn, 4);
        this.mStatusAuto = ACaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_MEDIA_10, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 6);
        this.mStatusAc = ACaddButton(757, KeyDef.RKEY_MEDIA_10, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 5);
        this.mStatusAcMax = ACaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 434, R.drawable.can_rh7_max_up, R.drawable.can_rh7_max_dn, 12);
        this.mStatusClosed = ACaddButton(462, 406, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 10);
    }

    protected static int uint2Bool(int val) {
        if (val == 0) {
            return 1;
        }
        return 0;
    }

    protected static boolean int2Bool(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        CanDataInfo.CAN_ACInfo mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mLeftTemp.setText(mAcInfo.szLtTemp);
        setWindValue(mAcInfo.nWindValue);
        int footWind = mAcInfo.fgDownWind;
        int headWind = mAcInfo.fgParallelWind;
        int winWind = mAcInfo.fgForeWindMode;
        if (i2b(footWind) && i2b(headWind)) {
            setAcMode(1);
        } else if (i2b(headWind)) {
            setAcMode(0);
        } else if (i2b(footWind) && i2b(winWind)) {
            setAcMode(3);
        } else if (i2b(footWind)) {
            setAcMode(2);
        } else if (i2b(winWind)) {
            setAcMode(4);
        } else {
            setAcMode(-1);
        }
        this.mStatusWindow.SetSel(mAcInfo.fgDFBL);
        this.mStatusRearWindow.SetSel(mAcInfo.fgRearLight);
        this.mStatusAc.SetSel(mAcInfo.fgAC);
        this.mStatusAuto.SetSel(mAcInfo.nAutoLight);
        this.mStatusClosed.SetSel(mAcInfo.PWR);
        this.mStatusAcMax.SetSel(mAcInfo.fgACMax);
        if (mAcInfo.fgInnerLoop != 0) {
            this.mStatusCycle.setStateDrawable(R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_nxh_dn, R.drawable.can_rh7_nxh_dn);
        } else {
            this.mStatusCycle.setStateDrawable(R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, R.drawable.can_rh7_wxh_dn);
        }
        this.mStatusCycle.setSelected(true);
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

    public ParamButton ACaddButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = getRelativeManager().AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    private CustomImgView ACaddImage(int x, int y, int resId) {
        return getRelativeManager().AddImage(x, y, resId);
    }

    private TextView ACaddText(int x, int y, int w, int h) {
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextColor(Color.parseColor("#08D2D3"));
        text.setTextSize(0, 27.0f);
        text.setGravity(17);
        return text;
    }
}
