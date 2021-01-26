package com.ts.can.cc.wc.h2;

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

public class CanCCH2WcACView extends CanBaseACView {
    private static final int CLOSED = 17;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int LT_TEMP__DECREASE = 1;
    private static final int MODE_BOTH = 7;
    private static final int MODE_FOOT = 8;
    private static final int MODE_FOOT_WIN = 9;
    private static final int MODE_HEAD = 6;
    private static final int STATUS_AC = 16;
    private static final int STATUS_AUTO = 14;
    private static final int STATUS_DUAL = 12;
    private static final int STATUS_INNER_LOOP = 15;
    private static final int STATUS_OUT_LOOP = 13;
    private static final int STATUS_WINOW = 11;
    private static final int WIND_DECREASE = 5;
    private static final int WIND_INCREASE = 4;
    private ParamButton[] mACMode;
    private TextView mLeftTemp;
    private ParamButton mStatusAc;
    private ParamButton mStatusClosed;
    private ParamButton mStatusOutLoop;
    private ParamButton mStatusWindow;
    private CustomImgView[] mWindIcons;

    public CanCCH2WcACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (action == 1) {
                switch (id) {
                    case 0:
                        CanJni.CcH2WcAirKey(13, 0);
                        break;
                    case 1:
                        CanJni.CcH2WcAirKey(14, 0);
                        break;
                    case 4:
                        CanJni.CcH2WcAirKey(11, 0);
                        break;
                    case 5:
                        CanJni.CcH2WcAirKey(12, 0);
                        break;
                    case 6:
                        CanJni.CcH2WcAirKey(26, 0);
                        break;
                    case 7:
                        CanJni.CcH2WcAirKey(27, 0);
                        break;
                    case 8:
                        CanJni.CcH2WcAirKey(29, 0);
                        break;
                    case 9:
                        CanJni.CcH2WcAirKey(28, 0);
                        break;
                    case 11:
                        CanJni.CcH2WcAirKey(5, 0);
                        break;
                    case 13:
                        CanJni.CcH2WcAirKey(7, 0);
                        break;
                    case 16:
                        CanJni.CcH2WcAirKey(2, 0);
                        break;
                    case 17:
                        CanJni.CcH2WcAirKey(1, 0);
                        break;
                }
            }
        } else {
            switch (id) {
                case 0:
                    CanJni.CcH2WcAirKey(13, 1);
                    break;
                case 1:
                    CanJni.CcH2WcAirKey(14, 1);
                    break;
                case 4:
                    CanJni.CcH2WcAirKey(11, 1);
                    break;
                case 5:
                    CanJni.CcH2WcAirKey(12, 1);
                    break;
                case 6:
                    CanJni.CcH2WcAirKey(26, 1);
                    break;
                case 7:
                    CanJni.CcH2WcAirKey(27, 1);
                    break;
                case 8:
                    CanJni.CcH2WcAirKey(29, 1);
                    break;
                case 9:
                    CanJni.CcH2WcAirKey(28, 1);
                    break;
                case 11:
                    CanJni.CcH2WcAirKey(5, 1);
                    break;
                case 13:
                    CanJni.CcH2WcAirKey(7, 1);
                    break;
                case 16:
                    CanJni.CcH2WcAirKey(2, 1);
                    break;
                case 17:
                    CanJni.CcH2WcAirKey(1, 1);
                    break;
            }
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
        CanJni.CcH2WcQuery(5, 1, 49);
    }

    private void initCommonScreen() {
        this.mWindIcons = new CustomImgView[8];
        this.mACMode = new ParamButton[4];
        int[] mIcons = {R.drawable.can_rh7_8signal01_dn, R.drawable.can_rh7_8signal02_dn, R.drawable.can_rh7_8signal03_dn, R.drawable.can_rh7_8signal04_dn, R.drawable.can_rh7_8signal05_dn, R.drawable.can_rh7_8signal06_dn, R.drawable.can_rh7_8signal07_dn, R.drawable.can_rh7_8signal08_dn};
        CCH2WCaddButton(50, 107, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        CCH2WCaddButton(50, 304, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = CCH2WCaddText(53, 218, 92, 61);
        CCH2WCaddButton(188, 107, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 4);
        CCH2WCaddButton(188, 304, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 5);
        addImage(191, 218, R.drawable.can_rh7_8signal00_up);
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addImage(191, 218, mIcons[i]);
        }
        this.mACMode[0] = CCH2WCaddButton(305, 98, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 6);
        this.mACMode[1] = CCH2WCaddButton(305, 175, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 7);
        this.mACMode[2] = CCH2WCaddButton(305, Can.CAN_FLAT_RZC, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 8);
        this.mACMode[3] = CCH2WCaddButton(305, KeyDef.RKEY_POWER_ON, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 9);
        this.mStatusWindow = CCH2WCaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 98, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 11);
        this.mStatusOutLoop = CCH2WCaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 210, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, 13);
        this.mStatusAc = CCH2WCaddButton(757, 98, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 16);
        this.mStatusClosed = CCH2WCaddButton(462, 406, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 17);
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
        this.mStatusAc.SetSel(mAcInfo.fgAC);
        this.mStatusClosed.SetSel(mAcInfo.PWR);
        if (mAcInfo.fgInnerLoop != 0) {
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

    public ParamButton CCH2WCaddButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = getRelativeManager().AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    private CustomImgView CCH2WCaddImage(int x, int y, int resId) {
        return getRelativeManager().AddImage(x, y, resId);
    }

    private TextView CCH2WCaddText(int x, int y, int w, int h) {
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextColor(Color.parseColor("#08D2D3"));
        text.setTextSize(0, 27.0f);
        text.setGravity(17);
        return text;
    }
}
