package com.ts.can;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanQorosACBox implements View.OnClickListener, UserCallBack {
    private static final int ITEM_LEFT_HIDE = 1;
    private static final int ITEM_RIGHT_HIDE = 2;
    private static final int STATE_AC = 6;
    private static final int STATE_AIR = 7;
    private static final int STATE_AUTO = 1;
    private static final int STATE_DUAL = 2;
    private static final int STATE_FRONT_MAX = 4;
    private static final int STATE_LOOPER = 5;
    private static final int STATE_WINDOW = 3;
    private static final String TAG = "CanQorosACBox";
    private static boolean mIsAC;
    private static boolean mfgJump;
    private static CanQorosACBox sInstance;
    private CanDataInfo.QorosBnrAcInfo mACInfo = new CanDataInfo.QorosBnrAcInfo();
    private Context mAppContext;
    private int[] mHotIcons = {R.drawable.can_qoros_air_arrows01, R.drawable.can_qoros_air_arrows02};
    private int[] mHotIds = {R.drawable.can_qoros_air_num01, R.drawable.can_qoros_air_num02};
    private ImageView mIvAC;
    private ImageView mIvAuto;
    private ImageView mIvDual;
    private ImageView mIvFront;
    private ImageView[] mIvHotIcons = new ImageView[2];
    private ImageView[] mIvHotValues = new ImageView[2];
    private ImageView mIvLooper;
    private ImageView mIvMax;
    private ImageView mIvModeDown;
    private ImageView mIvModeFront;
    private ImageView mIvModeMiddle;
    private ImageView mIvOFF;
    private ImageView[] mIvSeats = new ImageView[2];
    private ImageView[] mIvTempIcons = new ImageView[2];
    private ImageView[] mIvTempUnit = new ImageView[2];
    private ImageView[] mIvTempValue1 = new ImageView[2];
    private ImageView[] mIvTempValue2 = new ImageView[2];
    private ImageView[] mIvToggles = new ImageView[2];
    private ImageView[] mIvWindIcons = new ImageView[2];
    private ImageView[] mIvWindValues = new ImageView[2];
    private ImageView mIvWindow;
    private int mLeftBoxDelayTime = 0;
    private RelativeLayoutManager mLeftManager;
    private RelativeLayoutManager mManager;
    private int mRightBoxDelayTime = 0;
    private RelativeLayoutManager mRightManager;
    private int[] mTempIconIds = {R.drawable.can_qoros_air_temperature_ring16, R.drawable.can_qoros_air_temperature_ring17, R.drawable.can_qoros_air_temperature_ring18, R.drawable.can_qoros_air_temperature_ring19, R.drawable.can_qoros_air_temperature_ring20, R.drawable.can_qoros_air_temperature_ring21, R.drawable.can_qoros_air_temperature_ring22, R.drawable.can_qoros_air_temperature_ring23, R.drawable.can_qoros_air_temperature_ring24, R.drawable.can_qoros_air_temperature_ring25, R.drawable.can_qoros_air_temperature_ring26, R.drawable.can_qoros_air_temperature_ring27, R.drawable.can_qoros_air_temperature_ring28};
    private int[] mTempIds = {R.drawable.can_qoros_air_temperature_num00, R.drawable.can_qoros_air_temperature_num01, R.drawable.can_qoros_air_temperature_num02, R.drawable.can_qoros_air_temperature_num03, R.drawable.can_qoros_air_temperature_num04, R.drawable.can_qoros_air_temperature_num05, R.drawable.can_qoros_air_temperature_num06, R.drawable.can_qoros_air_temperature_num07, R.drawable.can_qoros_air_temperature_num08, R.drawable.can_qoros_air_temperature_num09};
    private WindowManager mWinManager;
    private WindowManager.LayoutParams mWinParams;
    private int[] mWindIds = {R.drawable.can_qoros_air_ventilation01, R.drawable.can_qoros_air_ventilation02, R.drawable.can_qoros_air_ventilation03, R.drawable.can_qoros_air_ventilation04, R.drawable.can_qoros_air_ventilation05, R.drawable.can_qoros_air_ventilation06, R.drawable.can_qoros_air_ventilation07, R.drawable.can_qoros_air_ventilation08};
    private int nDelayCheck = 0;

    private CanQorosACBox() {
    }

    public static CanQorosACBox GetInstance() {
        if (sInstance == null) {
            sInstance = new CanQorosACBox();
        }
        return sInstance;
    }

    public void Init(Context context) {
        if (this.mWinManager == null) {
            this.mAppContext = context.getApplicationContext();
            this.mWinManager = (WindowManager) this.mAppContext.getSystemService("window");
            this.mWinParams = new WindowManager.LayoutParams();
            this.mWinParams.type = 2003;
            this.mWinParams.format = 1;
            this.mWinParams.flags = 8;
            this.mWinParams.gravity = 81;
            this.mWinParams.width = 849;
            this.mWinParams.height = 172;
            this.mManager = new RelativeLayoutManager(new RelativeLayout(this.mAppContext));
            this.mLeftManager = AddChildManager(0, 0);
            this.mRightManager = AddChildManager(CanCameraUI.BTN_GEELY_YJX6_MODE4, 0);
            initViews();
        }
    }

    private void initViews() {
        RelativeLayoutManager[] managers = {this.mLeftManager, this.mRightManager};
        int[] ids = {1, 2};
        for (int i = 0; i < 2; i++) {
            AddButton(managers[i], 136, 0, 55, 44, ids[i], R.drawable.can_qoros_air_pulldown_up, R.drawable.can_qoros_air_pulldown_dn);
            this.mIvWindIcons[i] = AddImage(managers[i], 25, 31, 276, 63, R.drawable.can_qoros_air_ventilation_bg);
            this.mIvWindValues[i] = AddImage(managers[i], 48, 95, Can.CAN_CC_HF_DJ, 58, this.mWindIds[0]);
            this.mIvTempIcons[i] = AddImage(managers[i], 51, 39, 110, 110, R.drawable.can_qoros_air_temperature_ring28);
            this.mIvTempUnit[i] = AddImage(managers[i], Can.CAN_BYD_M6_DJ, 71, 32, 45, R.drawable.can_qoros_air_temperature_ic);
            this.mIvTempValue1[i] = AddImage(managers[i], 194, 72, 25, 45, this.mTempIds[0]);
            this.mIvTempValue2[i] = AddImage(managers[i], 219, 72, 25, 45, this.mTempIds[0]);
        }
        this.mIvSeats[0] = AddImage(managers[0], 50, 50, 100, 100, R.drawable.can_qoros_air_seatheating);
        this.mIvSeats[1] = AddImage(managers[1], 176, 50, 100, 100, R.drawable.can_qoros_air_seatheating02);
        this.mIvHotIcons[0] = AddImage(managers[0], 117, 97, 22, 46, R.drawable.can_qoros_air_arrows02);
        this.mIvHotIcons[1] = AddImage(managers[1], 196, 97, 22, 46, R.drawable.can_qoros_air_arrows02);
        this.mIvHotValues[0] = AddImage(managers[0], Can.CAN_FLAT_WC, 71, 27, 46, this.mHotIds[1]);
        this.mIvHotValues[1] = AddImage(managers[1], 67, 71, 27, 46, this.mHotIds[1]);
        this.mIvToggles[0] = AddImage(managers[0], 183, 79, 95, 44, R.drawable.can_qoros_air_off);
        this.mIvToggles[1] = AddImage(managers[1], 48, 79, 95, 44, R.drawable.can_qoros_air_off);
        this.mIvAuto = AddImage(this.mLeftManager, 62, 79, 95, 44, R.drawable.can_qoros_air_auto);
        this.mIvDual = AddImage(this.mLeftManager, 60, 81, 100, 40, R.drawable.can_qoros_air_dual);
        this.mIvWindow = AddImage(this.mLeftManager, 56, 65, 88, 77, R.drawable.can_qoros_air_window);
        this.mIvFront = AddImage(this.mLeftManager, 66, 73, 67, 54, R.drawable.can_qoros_air_frontfan01);
        this.mIvMax = AddImage(this.mLeftManager, 45, 50, 59, 26, R.drawable.can_qoros_air_max);
        this.mIvLooper = AddImage(this.mLeftManager, 56, 49, 214, 96, R.drawable.can_qoros_air_outcarcirculation);
        this.mIvAC = AddImage(this.mLeftManager, 62, 79, 95, 44, R.drawable.can_qoros_air_ac);
        this.mIvOFF = AddImage(this.mLeftManager, 76, 39, 175, 114, R.drawable.can_qoros_air_toggle);
        this.mIvModeFront = AddImage(this.mLeftManager, 128, 50, 34, 29, R.drawable.can_qoros_air_frontfan);
        this.mIvModeMiddle = AddImage(this.mLeftManager, 99, 62, 27, 27, R.drawable.can_qoros_air_arrowtop_left);
        this.mIvModeDown = AddImage(this.mLeftManager, 146, 86, 27, 27, R.drawable.can_qoros_air_arrowbottom_left);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.d(TAG, "-----onResume-----");
        showBox(0);
        this.mWinManager.addView(this.mManager.GetLayout(), this.mWinParams);
        ResetData(false);
        mIsAC = true;
        this.nDelayCheck = 100;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d(TAG, "----onPause-----");
        this.mWinManager.removeView(this.mManager.GetLayout());
        mIsAC = false;
        mfgJump = false;
        this.nDelayCheck = 0;
    }

    private void showBox(int boxStatus) {
        switch (boxStatus) {
            case 0:
                this.mLeftManager.GetLayout().setVisibility(8);
                this.mRightManager.GetLayout().setVisibility(8);
                return;
            case 1:
                this.mLeftBoxDelayTime = 100;
                this.mLeftManager.GetLayout().setVisibility(0);
                return;
            case 2:
                this.mRightBoxDelayTime = 100;
                this.mRightManager.GetLayout().setVisibility(0);
                return;
            case 3:
                this.mLeftBoxDelayTime = 100;
                this.mRightBoxDelayTime = 100;
                this.mLeftManager.GetLayout().setVisibility(0);
                this.mRightManager.GetLayout().setVisibility(0);
                return;
            default:
                return;
        }
    }

    private int[] getCheckValues(int leftReq, int rightReq, int leftVal, int rightVal) {
        int boxStatus;
        int[] values;
        if (leftReq == 1 && rightReq == 1) {
            boxStatus = 3;
            values = new int[]{leftVal, rightVal};
            hideChildren(this.mLeftManager.GetLayout());
            hideChildren(this.mRightManager.GetLayout());
        } else if (leftReq == 1) {
            boxStatus = 1;
            values = new int[]{leftVal};
            hideChildren(this.mLeftManager.GetLayout());
        } else {
            boxStatus = 2;
            values = new int[]{rightVal};
            hideChildren(this.mRightManager.GetLayout());
        }
        showBox(boxStatus);
        return values;
    }

    private void updateTemp(int leftReq, int rightReq, int leftTemp, int rightTemp) {
        if (leftReq != 0 || rightReq != 0) {
            int dIndex = 0;
            int[] temps = getCheckValues(leftReq, rightReq, leftTemp, rightTemp);
            if (leftReq == 0 && rightReq == 1) {
                dIndex = 1;
            }
            for (int i = 0; i < temps.length; i++) {
                int viewIndex = i + dIndex;
                this.mIvTempIcons[viewIndex].setVisibility(0);
                this.mIvTempValue1[viewIndex].setVisibility(0);
                this.mIvTempValue2[viewIndex].setVisibility(0);
                if (temps[i] > 16 && temps[i] < 28) {
                    this.mIvTempUnit[viewIndex].setVisibility(0);
                    this.mIvTempIcons[viewIndex].setImageResource(this.mTempIconIds[temps[i] - 16]);
                    this.mIvTempValue1[viewIndex].setImageResource(this.mTempIds[temps[i] / 10]);
                    this.mIvTempValue2[viewIndex].setImageResource(this.mTempIds[temps[i] % 10]);
                } else if (temps[i] == 16) {
                    this.mIvTempUnit[viewIndex].setVisibility(8);
                    this.mIvTempIcons[viewIndex].setImageResource(this.mTempIconIds[0]);
                    this.mIvTempValue1[viewIndex].setImageResource(R.drawable.can_qoros_l);
                    this.mIvTempValue2[viewIndex].setImageResource(R.drawable.can_qoros_o);
                } else if (temps[i] == 28) {
                    this.mIvTempUnit[viewIndex].setVisibility(8);
                    this.mIvTempIcons[viewIndex].setImageResource(this.mTempIconIds[this.mTempIconIds.length - 1]);
                    this.mIvTempValue1[viewIndex].setImageResource(R.drawable.can_qoros_h);
                    this.mIvTempValue2[viewIndex].setImageResource(R.drawable.can_qoros_i);
                }
            }
        }
    }

    private void updateWind(int leftReq, int rightReq, int leftWind, int rightWind) {
        if (leftReq != 0 || rightReq != 0) {
            int dIndex = 0;
            int[] winds = getCheckValues(leftReq, rightReq, leftWind, rightWind);
            if (leftReq == 0 && rightReq == 1) {
                dIndex = 1;
            }
            for (int i = 0; i < winds.length; i++) {
                int viewIndex = i + dIndex;
                this.mIvWindIcons[viewIndex].setVisibility(0);
                this.mIvWindValues[viewIndex].setVisibility(0);
                if (winds[i] >= 0 && winds[i] < 8) {
                    this.mIvWindValues[viewIndex].setImageResource(this.mWindIds[winds[i]]);
                }
            }
        }
    }

    private void updateSeatHot(int leftReq, int rightReq, int leftHot, int rightHot) {
        if (leftReq != 0 || rightReq != 0) {
            int dIndex = 0;
            int[] hots = getCheckValues(leftReq, rightReq, leftHot, rightHot);
            if (leftReq == 0 && rightReq == 1) {
                dIndex = 1;
            }
            for (int i = 0; i < hots.length; i++) {
                int viewIndex = i + dIndex;
                this.mIvSeats[viewIndex].setVisibility(0);
                if (hots[i] == 0) {
                    this.mIvHotIcons[viewIndex].setVisibility(8);
                    this.mIvHotValues[viewIndex].setVisibility(8);
                    this.mIvToggles[viewIndex].setVisibility(0);
                    this.mIvToggles[viewIndex].setImageResource(R.drawable.can_qoros_air_off);
                } else if (hots[i] == 1 || hots[i] == 2) {
                    this.mIvHotIcons[viewIndex].setVisibility(0);
                    this.mIvHotValues[viewIndex].setVisibility(0);
                    this.mIvToggles[viewIndex].setVisibility(8);
                    this.mIvHotIcons[viewIndex].setImageResource(this.mHotIcons[hots[i] - 1]);
                    this.mIvHotValues[viewIndex].setImageResource(this.mHotIds[hots[i] - 1]);
                }
            }
        }
    }

    private void updateStatus(int state, int req, int value) {
        if (req != 0) {
            showBox(1);
            hideChildren(this.mLeftManager.GetLayout());
            int iconId = value == 0 ? R.drawable.can_qoros_air_off : R.drawable.can_qoros_air_on;
            this.mIvToggles[0].setVisibility(0);
            this.mIvToggles[0].setImageResource(iconId);
            switch (state) {
                case 1:
                    this.mIvAuto.setVisibility(0);
                    return;
                case 2:
                    this.mIvDual.setVisibility(0);
                    return;
                case 3:
                    this.mIvWindow.setVisibility(0);
                    return;
                case 4:
                    this.mIvFront.setVisibility(0);
                    this.mIvMax.setVisibility(0);
                    return;
                case 5:
                    this.mIvLooper.setVisibility(0);
                    this.mIvToggles[0].setVisibility(8);
                    this.mIvLooper.setImageResource(value == 0 ? R.drawable.can_qoros_air_incarcirculation : R.drawable.can_qoros_air_incarcirculation_dn);
                    return;
                case 6:
                    this.mIvAC.setVisibility(0);
                    return;
                case 7:
                    this.mIvOFF.setVisibility(0);
                    this.mIvToggles[0].setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    }

    private void updateWindMode(int req, int mode) {
        if (req != 0) {
            showBox(1);
            hideChildren(this.mLeftManager.GetLayout());
            this.mIvSeats[0].setVisibility(0);
            switch (mode) {
                case 1:
                    this.mIvModeDown.setVisibility(0);
                    this.mIvModeFront.setVisibility(0);
                    return;
                case 2:
                    this.mIvModeFront.setVisibility(0);
                    return;
                case 3:
                    this.mIvModeMiddle.setVisibility(0);
                    return;
                case 4:
                    this.mIvModeMiddle.setVisibility(0);
                    this.mIvModeDown.setVisibility(0);
                    return;
                case 5:
                    this.mIvModeDown.setVisibility(0);
                    return;
                default:
                    return;
            }
        }
    }

    public void ShowAC() {
        if (!mIsAC) {
            mfgJump = true;
            onResume();
            return;
        }
        this.nDelayCheck = 100;
    }

    public void UserAll() {
        if (mIsAC) {
            ResetData(true);
        }
    }

    private void ResetData(boolean check) {
        CanJni.QorosBnrGetAcInfo(this.mACInfo);
        if (this.mACInfo.UpdateOnce != 0 && (!check || this.mACInfo.Update != 0)) {
            this.mACInfo.Update = 0;
            updateACUI();
        }
        if (mfgJump) {
            if (this.mLeftBoxDelayTime != 0) {
                this.mLeftBoxDelayTime--;
                if (this.mLeftBoxDelayTime == 0) {
                    this.mLeftManager.GetLayout().setVisibility(8);
                }
            }
            if (this.mRightBoxDelayTime != 0) {
                this.mRightBoxDelayTime--;
                if (this.mRightBoxDelayTime == 0) {
                    this.mRightManager.GetLayout().setVisibility(8);
                }
            }
            if (this.nDelayCheck != 0) {
                this.nDelayCheck--;
                if (this.nDelayCheck == 0 || CanFunc.IsCamMode() > 0) {
                    onPause();
                    Log.d(TAG, "UserAll Exit Ac");
                }
            }
        }
    }

    private void updateACUI() {
        updateTemp(this.mACInfo.Req_LeftTemp_Adjust, this.mACInfo.Req_RightTemp_Adjust, this.mACInfo.LeftTemp_Adjust, this.mACInfo.RightTemp_Adjust);
        updateSeatHot(this.mACInfo.Req_SeatHeat_Driver, this.mACInfo.Req_SeatHeat_Pass, this.mACInfo.SeatHeat_Driver, this.mACInfo.SeatHeat_Pass);
        updateWind(this.mACInfo.Req_Blower_Status, this.mACInfo.Req_RightBlower_Status, this.mACInfo.Blower_Status, this.mACInfo.RightBlower_Status);
        updateWindMode(this.mACInfo.Req_Air_Distribution, this.mACInfo.Air_Distribution);
        updateStatus(6, this.mACInfo.Req_Ac_Status, this.mACInfo.Ac_Status);
        updateStatus(1, this.mACInfo.Req_Auto, this.mACInfo.Auto);
        updateStatus(2, this.mACInfo.Req_Dual, this.mACInfo.Dual);
        updateStatus(3, this.mACInfo.Req_RearScreenHeating, this.mACInfo.RearScreenHeating);
        updateStatus(4, this.mACInfo.Req_Max_Defrost, this.mACInfo.Max_Defrost);
        updateStatus(5, this.mACInfo.Req_Inlet_Mode, this.mACInfo.Inlet_Mode);
        updateStatus(7, this.mACInfo.Req_Air_Power, this.mACInfo.Air_Power);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                this.mLeftManager.GetLayout().setVisibility(8);
                break;
            case 2:
                this.mRightManager.GetLayout().setVisibility(8);
                break;
        }
        if (this.mLeftManager.GetLayout().getVisibility() != 0 && this.mRightManager.GetLayout().getVisibility() != 0) {
            onPause();
        }
    }

    private void hideChildren(RelativeLayout container) {
        int childCount = container.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = container.getChildAt(i);
            if (child != null && (child instanceof ImageView)) {
                child.setVisibility(8);
            }
        }
    }

    private RelativeLayoutManager AddChildManager(int x, int y) {
        RelativeLayout view = new RelativeLayout(this.mAppContext);
        view.setBackgroundResource(R.drawable.can_qoros_air_bg);
        this.mManager.AddView(view, x, y, KeyDef.RKEY_RADIO_4S, 172);
        return new RelativeLayoutManager(view);
    }

    private ParamButton AddButton(RelativeLayoutManager manager, int x, int y, int w, int h, int id, int upId, int dnId) {
        ParamButton btn = manager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(upId, dnId);
        return btn;
    }

    private ImageView AddImage(RelativeLayoutManager manager, int x, int y, int w, int h, int resId) {
        ImageView img = manager.AddImage(x, y, w, h);
        if (resId != -1) {
            img.setImageResource(resId);
        }
        img.setVisibility(8);
        return img;
    }
}
