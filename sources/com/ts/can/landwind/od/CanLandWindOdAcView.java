package com.ts.can.landwind.od;

import android.app.Activity;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanLandWindOdAcView extends CanBaseACView {
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
    private static final int STATUS_WINOW_REAR = 18;
    private static final int WIND_DECREASE = 5;
    private static final int WIND_INCREASE = 4;
    private static ParamButton[] mACMode = new ParamButton[4];
    private static int[] mIcons = {R.drawable.can_rh7_8signal01_dn, R.drawable.can_rh7_8signal02_dn, R.drawable.can_rh7_8signal03_dn, R.drawable.can_rh7_8signal04_dn, R.drawable.can_rh7_8signal05_dn, R.drawable.can_rh7_8signal06_dn, R.drawable.can_rh7_8signal07_dn, R.drawable.can_rh7_8signal08_dn};
    private static CustomImgView[] mWindIcons = new CustomImgView[8];
    private TextView mLeftTemp;
    private ParamButton mStatusAc;
    private ParamButton mStatusAuto;
    private ParamButton mStatusClosed;
    private ParamButton mStatusOutLoop;
    private ParamButton mStatusWindow;
    private ParamButton mStatusWindowRear;

    public CanLandWindOdAcView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            switch (id) {
                case 0:
                    CanJni.LandWindOdAcSet(0, 0, 0, 2, 0, 0);
                    break;
                case 1:
                    CanJni.LandWindOdAcSet(0, 0, 0, 1, 0, 0);
                    break;
                case 4:
                    CanJni.LandWindOdAcSet(0, 0, 2, 0, 0, 0);
                    break;
                case 5:
                    CanJni.LandWindOdAcSet(0, 0, 1, 0, 0, 0);
                    break;
                case 6:
                    CanJni.LandWindOdAcSet(0, 0, 32, 0, 0, 0);
                    break;
                case 7:
                    CanJni.LandWindOdAcSet(0, 0, 64, 0, 0, 0);
                    break;
                case 8:
                    CanJni.LandWindOdAcSet(0, 0, 96, 0, 0, 0);
                    break;
                case 9:
                    CanJni.LandWindOdAcSet(0, 0, 128, 0, 0, 0);
                    break;
                case 11:
                    CanJni.LandWindOdAcSet(0, 0, 160, 0, 0, 0);
                    break;
                case 13:
                    if (Can.mACInfo.fgInnerLoop == 0) {
                        CanJni.LandWindOdAcSet(4, 0, 0, 0, 0, 0);
                        break;
                    } else {
                        CanJni.LandWindOdAcSet(8, 0, 0, 0, 0, 0);
                        break;
                    }
                case 14:
                    CanJni.LandWindOdAcSet(32, 0, 0, 0, 0, 0);
                    break;
                case 16:
                    CanJni.LandWindOdAcSet(2, 0, 0, 0, 0, 0);
                    break;
                case 17:
                    CanJni.LandWindOdAcSet(128, 0, 0, 0, 0, 0);
                    break;
                case 18:
                    CanJni.LandWindOdAcSet(0, 0, 4, 0, 0, 0);
                    break;
            }
        } else {
            if (action == 1) {
                switch (id) {
                    case 0:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 11:
                    case 13:
                    case 14:
                    case 16:
                    case 17:
                    case 18:
                        CanJni.LandWindOdAcSet(0, 0, 0, 0, 0, 0);
                        break;
                }
            }
            return false;
        }
        CanFunc.mLastACTick = GetTickCount();
        return false;
    }

    public static long GetTickCount() {
        return SystemClock.uptimeMillis();
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        setBackgroundResource(R.drawable.can_rh7_bg02);
        addButton(50, 107, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        addButton(50, 304, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = addText(53, 218, 92, 61);
        addButton(188, 107, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 4);
        addButton(188, 304, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 5);
        addImage(191, 218, R.drawable.can_rh7_signal_up);
        for (int i = 0; i < mWindIcons.length; i++) {
            mWindIcons[i] = addImage(191, 218, mIcons[i]);
        }
        mACMode[0] = addButton(305, 98, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 6);
        mACMode[1] = addButton(305, 175, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 7);
        mACMode[2] = addButton(305, Can.CAN_FLAT_RZC, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 8);
        mACMode[3] = addButton(305, KeyDef.RKEY_MEDIA_10, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 9);
        this.mStatusWindow = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 98, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 11);
        this.mStatusWindowRear = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_MEDIA_10, R.drawable.can_rh7_window02_up, R.drawable.can_rh7_window02_dn, 18);
        this.mStatusOutLoop = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 210, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, 13);
        this.mStatusAuto = addButton(757, 210, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 14);
        this.mStatusAc = addButton(757, 98, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 16);
        this.mStatusClosed = addButton(751, KeyDef.RKEY_OPEN, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 17);
    }

    public ParamButton addButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = getRelativeManager().AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    public CustomImgView addImage(int x, int y, int resId) {
        return getRelativeManager().AddImage(x, y, resId);
    }

    public TextView addText(int x, int y, int w, int h) {
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextColor(Color.parseColor("#08D2D3"));
        text.setTextSize(0, 27.0f);
        text.setGravity(17);
        return text;
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
        this.mStatusWindowRear.SetSel(mAcInfo.fgRearLight);
        this.mStatusAc.SetSel(mAcInfo.fgAC);
        this.mStatusAuto.SetSel(mAcInfo.nAutoLight);
        this.mStatusClosed.SetSel(mAcInfo.PWR);
        if (mAcInfo.fgInnerLoop != 0) {
            this.mStatusOutLoop.setStateDrawable(R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_nxh_dn, R.drawable.can_rh7_nxh_dn);
        } else {
            this.mStatusOutLoop.setStateDrawable(R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, R.drawable.can_rh7_wxh_dn);
        }
        this.mStatusOutLoop.setSelected(true);
    }

    private void setWindValue(int wind) {
        for (int i = 0; i < mWindIcons.length; i++) {
            mWindIcons[i].Show(i + 1 <= wind);
        }
    }

    private void setAcMode(int index) {
        int i = 0;
        while (i < mACMode.length) {
            mACMode[i].setSelected(i == index);
            i++;
        }
    }
}
