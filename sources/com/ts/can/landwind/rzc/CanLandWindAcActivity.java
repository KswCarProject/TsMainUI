package com.ts.can.landwind.rzc;

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

public class CanLandWindAcActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener {
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
    private static boolean mfgJump;
    private boolean isAutoFinish = false;
    private ParamButton[] mACMode = new ParamButton[4];
    private int[] mIcons = {R.drawable.can_rh7_signal01_dn, R.drawable.can_rh7_signal02_dn, R.drawable.can_rh7_signal03_dn, R.drawable.can_rh7_signal04_dn, R.drawable.can_rh7_signal05_dn, R.drawable.can_rh7_signal06_dn, R.drawable.can_rh7_signal07_dn};
    private TextView mLeftTemp;
    private RelativeLayoutManager mManager;
    private ParamButton mStatusAc;
    private ParamButton mStatusAuto;
    private ParamButton mStatusClosed;
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
        addButton(50, 107, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        addButton(50, 304, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = addText(53, 218, 92, 61);
        addButton(188, 107, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 4);
        addButton(188, 304, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 5);
        addImage(191, 218, R.drawable.can_rh7_signal_up);
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addImage(191, 218, this.mIcons[i]);
        }
        this.mACMode[0] = addButton(KeyDef.RKEY_MEDIA_ANGLE, 98, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 6);
        this.mACMode[1] = addButton(KeyDef.RKEY_MEDIA_ANGLE, 175, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 7);
        this.mACMode[2] = addButton(KeyDef.RKEY_MEDIA_ANGLE, Can.CAN_FLAT_RZC, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 8);
        this.mACMode[3] = addButton(KeyDef.RKEY_MEDIA_ANGLE, KeyDef.RKEY_POWER_ON, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 9);
        this.mStatusWindow = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 98, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 11);
        this.mStatusOutLoop = addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 210, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn, 13);
        this.mStatusAuto = addButton(757, 210, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 14);
        this.mStatusAc = addButton(757, 98, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 16);
        this.mStatusClosed = addButton(462, 406, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 17);
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
        text.setTextColor(Color.parseColor("#08D2D3"));
        text.setTextSize(0, 27.0f);
        text.setGravity(17);
        return text;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        CanJni.LoadWindRzcQuery(35);
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
                    if (Can.mACInfo.nLeftTemp < 31) {
                        CanJni.LoadWindRzcAcKey(0, 0, 0, Can.mACInfo.nLeftTemp + 19, 0);
                        break;
                    }
                    break;
                case 1:
                    if (Can.mACInfo.nLeftTemp > 1) {
                        CanJni.LoadWindRzcAcKey(0, 0, 0, Can.mACInfo.nLeftTemp + 17, 0);
                        break;
                    }
                    break;
                case 4:
                    if (Can.mACInfo.nWindValue != 7) {
                        CanJni.LoadWindRzcAcKey(0, ((Can.mACInfo.nWindValue + 1) << 4) & 240, 0, 0, 0);
                        break;
                    }
                    break;
                case 5:
                    if (Can.mACInfo.nWindValue != 0) {
                        CanJni.LoadWindRzcAcKey(0, ((Can.mACInfo.nWindValue - 1) << 4) & 240, 0, 0, 0);
                        break;
                    }
                    break;
                case 6:
                    CanJni.LoadWindRzcAcKey(0, 0, 2, 0, 0);
                    break;
                case 7:
                    CanJni.LoadWindRzcAcKey(0, 0, 4, 0, 0);
                    break;
                case 8:
                    CanJni.LoadWindRzcAcKey(0, 0, 8, 0, 0);
                    break;
                case 9:
                    CanJni.LoadWindRzcAcKey(0, 0, 16, 0, 0);
                    break;
                case 11:
                    CanJni.LoadWindRzcAcKey(16, 0, 0, 0, 0);
                    break;
                case 13:
                    CanJni.LoadWindRzcAcKey(0, 0, 1, 0, 0);
                    break;
                case 14:
                    CanJni.LoadWindRzcAcKey(32, 0, 0, 0, 0);
                    break;
                case 16:
                    CanJni.LoadWindRzcAcKey(2, 0, 0, 0, 0);
                    break;
                case 17:
                    CanJni.LoadWindRzcAcKey(128, 0, 0, 0, 0);
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
                        CanJni.LoadWindRzcAcKey(0, 0, 0, 0, 0);
                        break;
                }
            }
            return false;
        }
        CanFunc.mLastACTick = GetTickCount();
        return false;
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            UpdateACUI();
        }
        if (mfgJump && GetTickCount() > CanFunc.mLastACTick + 6000) {
            finish();
            this.isAutoFinish = true;
        }
    }
}
