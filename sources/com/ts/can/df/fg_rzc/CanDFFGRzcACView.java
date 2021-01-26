package com.ts.can.df.fg_rzc;

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

public class CanDFFGRzcACView extends CanBaseACView {
    private static final int CLOSED = 17;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int LT_TEMP__DECREASE = 1;
    private static final int MODE_BOTH = 7;
    private static final int MODE_FOOT = 8;
    private static final int MODE_FOOT_WIN = 9;
    private static final int MODE_HEAD = 6;
    private static final int MODE_MODE = 2;
    private static final int REAR_WINDOW = 12;
    private static final int STATUS_AC = 16;
    private static final int STATUS_ACMAX = 19;
    private static final int STATUS_AUTO = 14;
    private static final int STATUS_INNER_OUT_LOOP = 13;
    private static final int STATUS_PTC = 18;
    private static final int STATUS_REAR = 15;
    private static final int STATUS_WINDOW = 11;
    private static final int WIND_DECREASE = 5;
    private static final int WIND_INCREASE = 4;
    private ParamButton[] mACMode;
    private TextView mLeftTemp;
    private ParamButton mRearWindow;
    private ParamButton mStatusAc;
    private ParamButton mStatusAcMax;
    private ParamButton mStatusAuto;
    private ParamButton mStatusClosed;
    private ParamButton mStatusInnerOutLoop;
    private ParamButton mStatusPTC;
    private ParamButton mStatusRear;
    private ParamButton mStatusWindow;
    private CustomImgView[] mWindIcons;

    public CanDFFGRzcACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            switch (id) {
                case 0:
                    if (Can.mACInfo.nLeftTemp != 32) {
                        CanJni.DffgRzcAcCmd(0, 0, 0, 2, 0, 0, 0);
                        break;
                    }
                    break;
                case 1:
                    if (Can.mACInfo.nLeftTemp != 17) {
                        CanJni.DffgRzcAcCmd(0, 0, 0, 1, 0, 0, 0);
                        break;
                    }
                    break;
                case 2:
                    CanJni.DffgRzcAcCmd(64, 0, 0, 0, 0, 0, 0);
                    break;
                case 4:
                    CanJni.DffgRzcAcCmd(0, 2, 0, 0, 0, 0, 0);
                    break;
                case 5:
                    CanJni.DffgRzcAcCmd(0, 1, 0, 0, 0, 0, 0);
                    break;
                case 6:
                    CanJni.DffgRzcAcCmd(0, 0, 32, 0, 0, 0, 0);
                    break;
                case 7:
                    CanJni.DffgRzcAcCmd(0, 0, 64, 0, 0, 0, 0);
                    break;
                case 8:
                    CanJni.DffgRzcAcCmd(0, 0, 96, 0, 0, 0, 0);
                    break;
                case 9:
                    CanJni.DffgRzcAcCmd(0, 0, 128, 0, 0, 0, 0);
                    break;
                case 11:
                    CanJni.DffgRzcAcCmd(16, 0, 0, 0, 0, 0, 0);
                    break;
                case 12:
                    CanJni.DffgRzcAcCmd(0, 4, 0, 0, 0, 0, 0);
                    break;
                case 13:
                    CanJni.DffgRzcAcCmd(0, 0, 1, 0, 0, 0, 0);
                    break;
                case 14:
                    CanJni.DffgRzcAcCmd(32, 0, 0, 0, 0, 0, 0);
                    break;
                case 16:
                    CanJni.DffgRzcAcCmd(2, 0, 0, 0, 0, 0, 0);
                    break;
                case 17:
                    CanJni.DffgRzcAcCmd(128, 0, 0, 0, 0, 0, 0);
                    break;
                case 18:
                    CanJni.DffgRzcAcCmd(0, 0, 0, 0, 0, 0, 128);
                    break;
                case 19:
                    CanJni.DffgRzcAcCmd(1, 0, 0, 0, 0, 0, 0);
                    break;
            }
        } else if (action == 1) {
            CanJni.DffgRzcAcCmd(0, 0, 0, 0, 0, 0, 0);
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
        CanJni.SaicRwMgQuery(5, 1, 49);
    }

    private void initCommonScreen() {
        this.mWindIcons = new CustomImgView[8];
        this.mACMode = new ParamButton[4];
        int[] mIcons = {R.drawable.can_rh7_8signal01_dn, R.drawable.can_rh7_8signal02_dn, R.drawable.can_rh7_8signal03_dn, R.drawable.can_rh7_8signal04_dn, R.drawable.can_rh7_8signal05_dn, R.drawable.can_rh7_8signal06_dn, R.drawable.can_rh7_8signal07_dn, R.drawable.can_rh7_8signal08_dn};
        DFFGRzcaddButton(50, 107, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        DFFGRzcaddButton(50, 304, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = DFFGRzcaddText(53, 218, 92, 61);
        DFFGRzcaddButton(188, 107, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 4);
        DFFGRzcaddButton(188, 304, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 5);
        addImage(191, 218, R.drawable.can_rh7_8signal00_up);
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addImage(191, 218, mIcons[i]);
        }
        this.mACMode[0] = DFFGRzcaddButton(305, 98, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 6);
        this.mACMode[1] = DFFGRzcaddButton(305, 175, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 7);
        this.mACMode[2] = DFFGRzcaddButton(305, Can.CAN_FLAT_RZC, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 8);
        this.mACMode[3] = DFFGRzcaddButton(305, KeyDef.RKEY_POWER_ON, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 9);
        this.mStatusWindow = DFFGRzcaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 98, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 11);
        this.mRearWindow = DFFGRzcaddButton(757, 98, R.drawable.can_rh7_window02_up, R.drawable.can_rh7_window02_dn, 12);
        this.mStatusInnerOutLoop = DFFGRzcaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 210, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, 13);
        DFFGRzcaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_MEDIA_10, R.drawable.can_rh7_mode_up, R.drawable.can_rh7_mode_dn, 2);
        this.mStatusAuto = DFFGRzcaddButton(757, KeyDef.RKEY_MEDIA_10, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 14);
        this.mStatusAc = DFFGRzcaddButton(757, 210, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 16);
        this.mStatusRear = DFFGRzcaddButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 434, R.drawable.can_rh7_rear_up, R.drawable.can_rh7_rear_dn, 15);
        if (CanJni.GetSubType() == 2) {
            this.mStatusPTC = DFFGRzcaddButton(757, 434, R.drawable.can_rh7_ptc_up, R.drawable.can_rh7_ptc_dn, 18);
        } else {
            this.mStatusAcMax = DFFGRzcaddButton(757, 434, R.drawable.can_rh7_max_up, R.drawable.can_rh7_max_dn, 19);
        }
        this.mStatusClosed = DFFGRzcaddButton(462, 406, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 17);
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
        this.mRearWindow.SetSel(mAcInfo.fgRearLight);
        this.mStatusAc.SetSel(mAcInfo.fgAC);
        this.mStatusAuto.SetSel(mAcInfo.nAutoLight);
        this.mStatusClosed.SetSel(mAcInfo.PWR);
        this.mStatusRear.SetSel(mAcInfo.fgRearLock);
        if (CanJni.GetSubType() == 2) {
            this.mStatusPTC.SetSel(mAcInfo.fgPTC);
        } else {
            this.mStatusAcMax.SetSel(mAcInfo.fgACMax);
        }
        if (mAcInfo.fgInnerLoop != 0) {
            this.mStatusInnerOutLoop.setStateDrawable(R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_nxh_dn, R.drawable.can_rh7_nxh_dn);
        } else {
            this.mStatusInnerOutLoop.setStateDrawable(R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, R.drawable.can_rh7_wxh_dn);
        }
        this.mStatusInnerOutLoop.setSelected(true);
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

    public ParamButton DFFGRzcaddButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = getRelativeManager().AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    private CustomImgView DFFGRzcaddImage(int x, int y, int resId) {
        return getRelativeManager().AddImage(x, y, resId);
    }

    private TextView DFFGRzcaddText(int x, int y, int w, int h) {
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextColor(Color.parseColor("#08D2D3"));
        text.setTextSize(0, 27.0f);
        text.setGravity(17);
        return text;
    }
}
