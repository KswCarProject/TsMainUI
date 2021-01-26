package com.ts.can.gm.xt5;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.MyProgressBar;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;

public class CanCadillacXt5ACUI implements UserCallBack, CanItemProgressList.onPosChange, View.OnTouchListener {
    public static final int ITEM_AC = 5;
    public static final int ITEM_LOOP_MODE = 6;
    public static final int ITEM_WIND_DN = 1;
    public static final int ITEM_WIND_DN_FORE = 4;
    public static final int ITEM_WIND_PX = 3;
    public static final int ITEM_WIND_PXDN = 2;
    public static final String TAG = "CanCadillacXt5ACUI";
    protected static Context mContext;
    static CanCadillacXt5ACUI mInstance;
    protected static boolean mIsAC;
    protected static boolean mfgJump;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private CustomTextView mAcAuto;
    private CustomTextView mBtnAC;
    private ParamButton[] mBtnAirMode = new ParamButton[4];
    private ParamButton mBtnLoopMode;
    private RelativeLayout mLayout;
    private RelativeLayoutManager mManager;
    private CustomTextView mModeAuto;
    private CustomTextView mTvAirSync;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private CustomTextView mWindAuto;
    private MyProgressBar mWindProg;
    private int nDelayCheck = 0;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    protected CanCadillacXt5ACUI() {
    }

    public static CanCadillacXt5ACUI GetInstance() {
        if (mInstance == null) {
            mInstance = new CanCadillacXt5ACUI();
        }
        return mInstance;
    }

    public void InintWinManage(int nSizeX, int nSizeY, int nPosX, int nPosY, Context MyContext) {
        this.wManager = (WindowManager) MyContext.getSystemService("window");
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.flags = 8;
        this.wmParams.gravity = 81;
        this.wmParams.x = nPosX;
        this.wmParams.y = nPosY;
        this.wmParams.width = nSizeX;
        this.wmParams.height = nSizeY;
    }

    public void InitAc(Context context) {
        if (this.mLayout != null || context == null) {
            Log.d(TAG, "Already have instance");
        } else if (88 != CanJni.GetCanType()) {
        } else {
            if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 10) {
                Log.d(TAG, "Init");
                mContext = context;
                this.mLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.activity_can_ac_cadillax_xt5, (ViewGroup) null);
                InintWinManage(800, 269, 0, 0, mContext);
                this.wManager.addView(this.mLayout, this.wmParams);
                this.mManager = new RelativeLayoutManager(this.mLayout);
                this.mTvLtTemp = AddTemp(0, 110, 135, 57);
                this.mTvRtTemp = AddTemp(679, 110, 135, 57);
                this.mWindProg = new MyProgressBar(mContext, R.drawable.xt5_ac_pro_up, R.drawable.xt5_ac_pro_dn);
                this.mWindProg.SetMinMax(0, 7);
                this.mWindProg.SetCurPos(1);
                this.mManager.AddViewWrapContent(this.mWindProg, 222, 120);
                this.mWindAuto = AddText(360, 116, 79, 29);
                this.mWindAuto.setText("自动");
                this.mWindAuto.setTextColor(-256);
                this.mWindAuto.setVisibility(8);
                this.mModeAuto = AddText(360, 178, 79, 29);
                this.mModeAuto.setTextColor(-256);
                this.mAcAuto = AddText(130, 127, 79, 29);
                this.mAcAuto.setText("自动");
                this.mAcAuto.setTextColor(-1);
                this.mBtnAC = AddText(130, 97, 79, 29);
                this.mBtnAC.setText("制冷");
                this.mBtnAC.setTag(5);
                this.mBtnAC.setTextColor(-1);
                this.mBtnAC.setOnTouchListener(this);
                this.mTvAirSync = AddTemp(284, 24, Can.CAN_LIEBAO_WC, 51);
                this.mTvAirSync.setText("同步温度");
                this.mTvAirSync.setTextColor(-1);
                this.mBtnAirMode[0] = AddBtn(1, 9, 174, 117, 95, R.drawable.xt5_ac_bt01_up, R.drawable.xt5_ac_bt01_dn);
                this.mBtnAirMode[1] = AddBtn(2, Can.CAN_CC_HF_DJ, 174, 117, 95, R.drawable.xt5_ac_bt02_up, R.drawable.xt5_ac_bt02_dn);
                this.mBtnAirMode[2] = AddBtn(3, 452, 174, 117, 95, R.drawable.xt5_ac_bt03_up, R.drawable.xt5_ac_bt03_dn);
                this.mBtnAirMode[3] = AddBtn(4, 675, 174, 117, 95, R.drawable.xt5_ac_bt04_up, R.drawable.xt5_ac_bt04_dn);
                this.mBtnLoopMode = AddBtn(6, 593, 103, 77, 49, R.drawable.xt5_ac_wxh_up, R.drawable.xt5_ac_nxh_dn);
            }
        }
    }

    public void Destroy() {
        Log.i(TAG, "Destroy");
        if (this.mLayout != null) {
            this.wManager.removeView(this.mLayout);
            this.mLayout = null;
        }
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTemp(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(55);
        temp.setText("88.8℃");
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddText(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(28);
        temp.setText(TXZResourceManager.STYLE_DEFAULT);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        InitAc(CanFunc.mContext);
        ResetData(false);
        this.mLayout.setVisibility(0);
        Log.d(TAG, "-----onResume-----");
        mIsAC = true;
        this.nDelayCheck = 100;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d(TAG, "-----onPause-----");
        this.mLayout.setVisibility(4);
        mIsAC = false;
        mfgJump = false;
        this.nDelayCheck = 0;
    }

    private void updateACUI() {
        this.mACInfo = Can.mACInfo;
        if (this.mACInfo.nLeftTemp == 0) {
            this.mTvLtTemp.setText("低");
        } else if (this.mACInfo.nLeftTemp == 63) {
            this.mTvLtTemp.setText("高");
        } else {
            this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        }
        if (this.mACInfo.nRightTemp == 0) {
            this.mTvRtTemp.setText("低");
        } else if (this.mACInfo.nRightTemp == 63) {
            this.mTvRtTemp.setText("高");
        } else {
            this.mTvRtTemp.setText(this.mACInfo.szRtTemp);
        }
        if (this.mACInfo.fgAutoWind != 0) {
            this.mWindProg.SetCurPos(0);
            this.mWindAuto.setVisibility(0);
        } else {
            this.mWindAuto.setVisibility(8);
            if (this.mACInfo.fgAutoWind != 0) {
                this.mWindProg.SetCurPos(0);
            } else {
                this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
            }
        }
        if (this.mACInfo.fgAutoMode != 0) {
            this.mModeAuto.setText("自动");
        } else if (this.mACInfo.fgRearLight != 0) {
            this.mModeAuto.setText("除霜");
        } else {
            this.mModeAuto.setText(TXZResourceManager.STYLE_DEFAULT);
        }
        if (this.mACInfo.fgAutoAC != 0) {
            this.mAcAuto.setTextColor(-256);
        } else {
            this.mAcAuto.setTextColor(-1);
        }
        if (this.mACInfo.fgInnerLoop != 0) {
            this.mBtnLoopMode.setSelected(true);
        } else {
            this.mBtnLoopMode.setSelected(false);
        }
        if (this.mACInfo.fgAC != 0) {
            this.mBtnAC.setTextColor(-256);
        } else {
            this.mBtnAC.setTextColor(-1);
        }
        this.mBtnAirMode[0].setSelected(false);
        this.mBtnAirMode[1].setSelected(false);
        this.mBtnAirMode[2].setSelected(false);
        this.mBtnAirMode[3].setSelected(false);
        if (this.mACInfo.fgUpWind != 0 && this.mACInfo.fgDownWind != 0) {
            this.mBtnAirMode[3].setSelected(true);
        } else if (this.mACInfo.fgParallelWind != 0 && this.mACInfo.fgDownWind != 0) {
            this.mBtnAirMode[1].setSelected(true);
        } else if (this.mACInfo.fgDownWind != 0) {
            this.mBtnAirMode[0].setSelected(true);
        } else if (this.mACInfo.fgParallelWind != 0) {
            this.mBtnAirMode[2].setSelected(true);
        }
    }

    private void ResetData(boolean check) {
        Can.updateAC();
        if (!check || Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (mfgJump && this.nDelayCheck != 0) {
            this.nDelayCheck--;
            if (this.nDelayCheck == 0 || CanFunc.IsCamMode() > 0) {
                onPause();
                Log.d(TAG, "UserAll Exit Ac");
            }
        }
    }

    public void UserAll() {
        if (mIsAC && 88 == CanJni.GetCanType()) {
            if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 10) {
                ResetData(true);
            }
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void ShowAC() {
        if (mIsAC || CanCadillacXt5ExdActivity.IsCadillacXt5Win()) {
            this.nDelayCheck = 100;
            return;
        }
        mfgJump = true;
        onResume();
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int intValue = ((Integer) v.getTag()).intValue();
        this.nDelayCheck = 100;
        if (action == 0) {
            Log.d(TAG, "****ACTION_DOWN*****");
        } else if (1 == action) {
            Log.d(TAG, "****ACTION_UP*****");
        }
        return true;
    }
}
