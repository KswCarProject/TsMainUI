package com.ts.can.chana.cs75;

import android.app.Activity;
import android.graphics.Color;
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

public class CanChanAACView extends CanBaseACView {
    private static final int CLOSED = 17;
    private static final int LT_TEMP_DECREASE = 1;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int MODE = 18;
    private static final int MODE_BOTH = 7;
    private static final int MODE_FOOT = 8;
    private static final int MODE_FOOT_WIN = 9;
    private static final int MODE_HEAD = 6;
    private static final int MODE_WIN = 10;
    private static final int STATUS_AC = 16;
    private static final int STATUS_AUTO = 14;
    private static final int STATUS_DUAL = 12;
    private static final int STATUS_INNER_LOOP = 15;
    private static final int STATUS_MAXAC = 21;
    private static final int STATUS_OUT_LOOP = 13;
    private static final int STATUS_REAR = 20;
    private static final int STATUS_REAR_WINOW = 19;
    private static final int STATUS_WINOW = 11;
    private static final int WIND_DECREASE = 5;
    private static final int WIND_INCREASE = 4;
    private ParamButton[] mACMode;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private TextView mLeftTemp;
    private ParamButton mStatusAc;
    private ParamButton mStatusAuto;
    private ParamButton mStatusClosed;
    private ParamButton mStatusMaxAc;
    private ParamButton mStatusOutLoop;
    private ParamButton mStatusRear;
    private ParamButton mStatusRearWindow;
    private ParamButton mStatusWindow;
    private CustomImgView[] mWindIcons;

    public CanChanAACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            switch (id) {
                case 0:
                    CanJni.ChanaRzcAirKey(0, 0, 0, 2, 0, 0);
                    break;
                case 1:
                    CanJni.ChanaRzcAirKey(0, 0, 0, 1, 0, 0);
                    break;
                case 4:
                    CanJni.ChanaRzcAirKey(0, 2, 0, 0, 0, 0);
                    break;
                case 5:
                    CanJni.ChanaRzcAirKey(0, 1, 0, 0, 0, 0);
                    break;
                case 6:
                    if (CanJni.GetSubType() == 11 || CanJni.GetSubType() == 18) {
                        CanJni.ChanaRzcAirKey(0, 0, 2, 0, 0, 0);
                        break;
                    }
                case 7:
                    if (CanJni.GetSubType() == 11 || CanJni.GetSubType() == 18) {
                        CanJni.ChanaRzcAirKey(0, 0, 4, 0, 0, 0);
                        break;
                    }
                case 8:
                    if (CanJni.GetSubType() == 11 || CanJni.GetSubType() == 18) {
                        CanJni.ChanaRzcAirKey(0, 0, 8, 0, 0, 0);
                        break;
                    }
                case 9:
                    if (CanJni.GetSubType() == 11 || CanJni.GetSubType() == 18) {
                        CanJni.ChanaRzcAirKey(0, 0, 16, 0, 0, 0);
                        break;
                    }
                case 11:
                    CanJni.ChanaRzcAirKey(16, 0, 0, 0, 0, 0);
                    break;
                case 13:
                    if (this.mAcInfo.fgInnerLoop == 0) {
                        CanJni.ChanaRzcAirKey(4, 0, 0, 0, 0, 0);
                        break;
                    } else {
                        CanJni.ChanaRzcAirKey(8, 0, 0, 0, 0, 0);
                        break;
                    }
                case 14:
                    CanJni.ChanaRzcAirKey(32, 0, 0, 0, 0, 0);
                    break;
                case 16:
                    CanJni.ChanaRzcAirKey(2, 0, 0, 0, 0, 0);
                    break;
                case 17:
                    CanJni.ChanaRzcAirKey(128, 0, 0, 0, 0, 0);
                    break;
                case 18:
                    CanJni.ChanaRzcAirKey(64, 0, 0, 0, 0, 0);
                    break;
                case 19:
                    CanJni.ChanaRzcAirKey(0, 4, 0, 0, 0, 0);
                    break;
                case 20:
                    CanJni.ChanaRzcAirKey(0, 0, 0, 0, 0, 4);
                    break;
                case 21:
                    CanJni.ChanaRzcAirKey(1, 0, 0, 0, 0, 4);
                    break;
            }
        } else if (action == 1) {
            CanJni.ChanaRzcAirKey(0, 0, 0, 0, 0, 0);
        }
        return false;
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
    }

    private void initCommonScreen() {
        this.mWindIcons = new CustomImgView[8];
        this.mACMode = new ParamButton[5];
        int[] mIcons = {R.drawable.can_rh7_8signal01_dn, R.drawable.can_rh7_8signal02_dn, R.drawable.can_rh7_8signal03_dn, R.drawable.can_rh7_8signal04_dn, R.drawable.can_rh7_8signal05_dn, R.drawable.can_rh7_8signal06_dn, R.drawable.can_rh7_8signal07_dn, R.drawable.can_rh7_8signal08_dn};
        ChanAaddButton(50, 107, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        ChanAaddButton(50, 304, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = ChanAaddText(53, 218, 92, 61);
        ChanAaddButton(188, 107, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 4);
        ChanAaddButton(188, 304, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 5);
        addImage(191, 218, R.drawable.can_rh7_8signal00_up);
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addImage(191, 218, mIcons[i]);
        }
        this.mStatusWindow = ChanAaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 98, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 11);
        this.mStatusOutLoop = ChanAaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 210, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, 13);
        this.mStatusAuto = ChanAaddButton(757, 210, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 14);
        this.mStatusAc = ChanAaddButton(757, 98, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 16);
        this.mStatusRearWindow = ChanAaddButton(757, KeyDef.RKEY_MEDIA_10, R.drawable.can_rh7_window02_up, R.drawable.can_rh7_window02_dn, 19);
        this.mStatusClosed = ChanAaddButton(462, 406, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 17);
        this.mACMode[0] = ChanAaddButton(305, 98, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 6);
        this.mACMode[1] = ChanAaddButton(305, 175, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 7);
        this.mACMode[2] = ChanAaddButton(305, Can.CAN_FLAT_RZC, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 8);
        this.mACMode[4] = ChanAaddButton(305, KeyDef.RKEY_POWER_ON, R.drawable.can_rh7_icon05_up, R.drawable.can_rh7_icon05_dn, 10);
        if (CanJni.GetSubType() == 11) {
            this.mStatusRear = ChanAaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_MEDIA_10, R.drawable.can_rh7_rear_up, R.drawable.can_rh7_rear_dn, 20);
            this.mACMode[3] = ChanAaddButton(305, KeyDef.RKEY_POWER_ON, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 9);
            this.mACMode[4].setVisibility(8);
        } else if (CanJni.GetSubType() == 18) {
            this.mStatusRear = ChanAaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 434, R.drawable.can_rh7_rear_up, R.drawable.can_rh7_rear_dn, 20);
            this.mACMode[3] = ChanAaddButton(305, KeyDef.RKEY_POWER_ON, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 9);
            this.mACMode[4].setVisibility(8);
            ChanAaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_MEDIA_10, R.drawable.can_rh7_mode_up, R.drawable.can_rh7_mode_dn, 18);
        } else {
            if (CanJni.GetSubType() == 15) {
                this.mStatusMaxAc = ChanAaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 434, R.drawable.can_rh7_max_up, R.drawable.can_rh7_max_dn, 21);
            }
            ChanAaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_MEDIA_10, R.drawable.can_rh7_mode_up, R.drawable.can_rh7_mode_dn, 18);
            this.mACMode[3] = ChanAaddButton(305, 410, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 9);
        }
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
        this.mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mLeftTemp.setText(this.mAcInfo.szLtTemp);
        Log.d("ACUI", "mAcInfo.nWindValue:" + this.mAcInfo.nWindValue);
        setWindValue(this.mAcInfo.nWindValue);
        int footWind = this.mAcInfo.fgDownWind;
        int headWind = this.mAcInfo.fgParallelWind;
        int winWind = this.mAcInfo.fgForeWindMode;
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
        this.mStatusWindow.SetSel(this.mAcInfo.fgDFBL);
        this.mStatusRearWindow.SetSel(this.mAcInfo.fgRearLight);
        this.mStatusAc.SetSel(this.mAcInfo.fgAC);
        this.mStatusAuto.SetSel(this.mAcInfo.nAutoLight);
        this.mStatusClosed.SetSel(this.mAcInfo.PWR);
        if (this.mStatusRear != null) {
            this.mStatusRear.SetSel(this.mAcInfo.fgRearLock);
        }
        if (this.mStatusMaxAc != null) {
            this.mStatusMaxAc.SetSel(this.mAcInfo.fgACMax);
        }
        if (this.mAcInfo.fgInnerLoop != 0) {
            this.mStatusOutLoop.setStateDrawable(R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_nxh_dn, R.drawable.can_rh7_nxh_dn);
        } else {
            this.mStatusOutLoop.setStateDrawable(R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, R.drawable.can_rh7_wxh_dn);
        }
        this.mStatusOutLoop.setSelected(true);
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

    public ParamButton ChanAaddButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = getRelativeManager().AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    private CustomImgView ChanAaddImage(int x, int y, int resId) {
        return getRelativeManager().AddImage(x, y, resId);
    }

    private TextView ChanAaddText(int x, int y, int w, int h) {
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextColor(Color.parseColor("#08D2D3"));
        text.setTextSize(0, 27.0f);
        text.setGravity(17);
        return text;
    }
}
