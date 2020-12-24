package com.ts.can.saic.mg;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.MyProgressBar;
import com.ts.main.common.KeyTouch;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanMGGSACActivity extends CanMGGSBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange, View.OnTouchListener {
    public static final int ITEM_AC = 11;
    public static final int ITEM_AUTO = 10;
    public static final int ITEM_FORE_WIND = 8;
    public static final int ITEM_LOOP = 7;
    public static final int ITEM_LTEMP_DEC = 2;
    public static final int ITEM_LTEMP_INC = 1;
    public static final int ITEM_LTHOT = 13;
    public static final int ITEM_MODE = 4;
    public static final int ITEM_PWR = 3;
    public static final int ITEM_REAR = 17;
    public static final int ITEM_REAR_WIND = 9;
    public static final int ITEM_REAR_WIND_DEC = 15;
    public static final int ITEM_REAR_WIND_INC = 16;
    public static final int ITEM_RTHOT = 14;
    public static final int ITEM_WIND_DEC = 5;
    public static final int ITEM_WIND_INC = 6;
    public static final String TAG = "CanMGGSACActivity";
    protected static boolean mIsAC;
    protected static boolean mfgJump;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ParamButton mBtnAC;
    private ParamButton mBtnAuto;
    private ParamButton mBtnForeWind;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnMode;
    private ParamButton mBtnOff;
    private ParamButton mBtnRear;
    private ParamButton mBtnRearWind;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private CustomImgView mIvWindAuto;
    private CustomImgView mIvWindDirectAuto;
    private CustomImgView mIvWindDn;
    private CustomImgView mIvWindPx;
    private CustomImgView mIvWindUp;
    protected RelativeLayoutManager mManager;
    private CustomTextView mTvLtTemp;
    private MyProgressBar mWindProg;
    private MyProgressBar windProgress;
    private TextView windVal;
    private PopupWindow window;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_mg_bg);
        mfgJump = CanFunc.IsCanActivityJumped(this);
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp = AddTemp(109, 18, 131, 62);
        this.mBtnLtTempInc = AddBtn(1, 138, 107, R.drawable.can_yl_upward_up, R.drawable.can_yl_upward_dn);
        this.mBtnLtTempDec = AddBtn(2, 138, 287, R.drawable.can_yl_down_up, R.drawable.can_yl_down_dn);
        this.mWindProg = new MyProgressBar(this, R.drawable.can_yl_rect_up, R.drawable.can_yl_rect_dn);
        this.mWindProg.SetMinMax(0, 8);
        this.mWindProg.SetCurPos(1);
        this.mManager.AddViewWrapContent(this.mWindProg, 352, KeyDef.RKEY_PRE);
        this.mBtnWindDec = AddBtn(5, 287, 271, R.drawable.can_yl_jian_up, R.drawable.can_yl_jian_dn);
        this.mBtnWindInc = AddBtn(6, KeyDef.SKEY_SPEECH_3, 271, R.drawable.can_yl_jia_up, R.drawable.can_yl_jia_dn);
        this.mIvWindAuto = this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE6, KeyDef.RKEY_RADIO_SCAN, R.drawable.can_yl_wind_auto);
        this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, 76, R.drawable.can_mg_people);
        this.mIvWindUp = this.mManager.AddImage(CanCameraUI.BTN_VW_WC_MODE1, 62, R.drawable.can_mg_wind);
        this.mIvWindPx = this.mManager.AddImage(569, 89, R.drawable.can_mg_right);
        this.mIvWindDn = this.mManager.AddImage(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST9, 112, R.drawable.can_mg_down);
        this.mIvWindDirectAuto = this.mManager.AddImage(535, 84, R.drawable.can_mg_auto);
        this.mBtnOff = AddBtn(3, KeyTouch.GAMMA_MAX_NUM, 82, R.drawable.can_mg_del_up, R.drawable.can_mg_del_dn2);
        this.mBtnMode = AddBtn(4, 747, 82, R.drawable.can_mg_mode_up, R.drawable.can_mg_mode_dn);
        this.mBtnLtHot = AddBtn(13, 44, 418, R.drawable.can_mg_lhot_up, R.drawable.can_mg_lhot_dn);
        this.mBtnLoop = AddBtn(7, 184, 418, R.drawable.can_mg_car01_up, R.drawable.can_mg_car01_dn);
        this.mBtnForeWind = AddBtn(8, KeyDef.RKEY_RADIO_2S, 418, R.drawable.can_mg_wind_up, R.drawable.can_mg_wind_dn);
        this.mBtnRearWind = AddBtn(9, 464, 418, R.drawable.can_mg_rear_up, R.drawable.can_mg_rear_dn);
        if (CanJni.GetSubType() == 12) {
            this.mBtnAuto = AddBtn(10, 744, 418, R.drawable.can_psa_408_auto_up, R.drawable.can_psa_408_auto_dn);
            this.mBtnAC = AddBtn(11, 887, 82, 77, 77, R.drawable.can_psa_408_ac_up, R.drawable.can_psa_408_ac_dn);
            this.mBtnRear = AddBtn(17, 604, 418, 95, 95, R.drawable.can_psa_408_rear_up, R.drawable.can_psa_408_rear_dn);
        } else {
            this.mBtnAuto = AddBtn(10, 604, 418, R.drawable.can_psa_408_auto_up, R.drawable.can_psa_408_auto_dn);
            this.mBtnAC = AddBtn(11, 744, 418, R.drawable.can_psa_408_ac_up, R.drawable.can_psa_408_ac_dn);
        }
        this.mBtnRtHot = AddBtn(14, 884, 418, R.drawable.can_mg_rhot_up, R.drawable.can_mg_rhot_dn);
        Log.d("CanMGGSACActivity", "jump = " + mfgJump);
        if (!mfgJump) {
            CanJni.PSAQuery(33, 0);
        }
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
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
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d("CanMGGSACActivity", "-----onResume-----");
        mIsAC = true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanMGGSACActivity", "-----onPause-----");
        mIsAC = false;
        mfgJump = false;
    }

    private void updateACUI() {
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        this.mBtnOff.SetSel(1 - this.mACInfo.PWR);
        this.mIvWindAuto.Show(this.mACInfo.fgAutoWind);
        if (this.mACInfo.fgAutoWind != 0) {
            this.mWindProg.SetCurPos(0);
        } else {
            this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
        }
        if (this.mACInfo.fgInnerLoop != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_mg_car01_up, R.drawable.can_mg_car01_dn);
        } else {
            this.mBtnLoop.setDrawable(R.drawable.can_mg_car02_up, R.drawable.can_mg_car02_dn);
        }
        this.mIvWindUp.Show(this.mACInfo.fgUpWind);
        this.mIvWindPx.Show(this.mACInfo.fgParallelWind);
        this.mIvWindDn.Show(this.mACInfo.fgDownWind);
        this.mIvWindDirectAuto.Show(this.mACInfo.nWindAutoLevel);
        this.mBtnForeWind.SetSel(this.mACInfo.fgDFBL);
        this.mBtnRearWind.SetSel(this.mACInfo.fgRearLight);
        this.mBtnAuto.SetSel(this.mACInfo.nAutoLight);
        this.mBtnAC.SetSel(this.mACInfo.fgAC);
        this.mBtnLtHot.SetSel(this.mACInfo.nLtChairHot);
        this.mBtnRtHot.SetSel(this.mACInfo.nRtChairHot);
        Log.d("CanMGGSACActivity", "mACInfo.nRearWindValue = " + this.mACInfo.nRearWindValue);
        if (this.window != null && this.window.isShowing()) {
            this.windProgress.SetCurPos(this.mACInfo.nRearWindValue);
            this.windVal.setText(String.valueOf(this.mACInfo.nRearWindValue));
        }
    }

    private void ResetData(boolean check) {
        Can.updateAC();
        if (!check || Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (mfgJump && GetTickCount() > CanFunc.mLastACTick + 6000) {
            finish();
            Log.d("CanMGGSACActivity", "UserAll Exit Ac");
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        CanFunc.mLastACTick = GetTickCount();
        switch (id) {
            case 17:
                showWindow(v, 621, 100);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public static void ShowAC() {
        if (!mIsAC) {
            mfgJump = true;
            CanFunc.showCanActivity(CanMGGSACActivity.class);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        CanFunc.mLastACTick = GetTickCount();
        if (action != 0) {
            if (1 == action) {
                Log.d("CanMGGSACActivity", "****ACTION_UP*****");
                switch (Id) {
                    case 1:
                        ACSet(1, 0);
                        break;
                    case 2:
                        ACSet(1, 0);
                        break;
                    case 3:
                        ACSet(5, 0);
                        break;
                    case 4:
                        ACSet(2, 0);
                        break;
                    case 5:
                        ACSet(4, 0);
                        break;
                    case 6:
                        ACSet(4, 0);
                        break;
                    case 7:
                        ACSet(3, 0);
                        break;
                    case 8:
                        ACSet(6, 0);
                        break;
                    case 9:
                        ACSet(7, 0);
                        break;
                    case 10:
                        ACSet(9, 0);
                        break;
                    case 11:
                        ACSet(8, 0);
                        break;
                    case 13:
                        ACSet(17, 0);
                        break;
                    case 14:
                        ACSet(18, 0);
                        break;
                    case 15:
                        ACSet(20, 0);
                        break;
                    case 16:
                        ACSet(20, 0);
                        break;
                }
            }
        } else {
            Log.d("CanMGGSACActivity", "****ACTION_DOWN*****");
            switch (Id) {
                case 1:
                    ACSet(1, 1);
                    break;
                case 2:
                    ACSet(1, 2);
                    break;
                case 3:
                    ACSet(5, 1);
                    break;
                case 4:
                    ACSet(2, 1);
                    break;
                case 5:
                    ACSet(4, 2);
                    break;
                case 6:
                    ACSet(4, 1);
                    break;
                case 7:
                    ACSet(3, 1);
                    break;
                case 8:
                    ACSet(6, 1);
                    break;
                case 9:
                    ACSet(7, 1);
                    break;
                case 10:
                    ACSet(9, 1);
                    break;
                case 11:
                    ACSet(8, 1);
                    break;
                case 13:
                    ACSet(17, 1);
                    break;
                case 14:
                    ACSet(18, 1);
                    break;
                case 15:
                    ACSet(20, 2);
                    break;
                case 16:
                    ACSet(20, 1);
                    break;
            }
        }
        return false;
    }

    private void showWindow(View anchor, int width, int height) {
        this.window = new PopupWindow(width, height);
        this.window.setBackgroundDrawable(new ColorDrawable());
        this.window.setFocusable(true);
        this.window.setContentView(getContentView(width, height));
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        this.window.showAtLocation(anchor, 0, location[0] - ((this.window.getWidth() - anchor.getWidth()) / 2), (location[1] - this.window.getHeight()) - 20);
        updateACUI();
        ResetData(false);
    }

    private View getContentView(int width, int height) {
        RelativeLayout contentView = new RelativeLayout(this);
        contentView.setBackgroundResource(R.drawable.can_popup_bg);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        addACViews(contentView);
        return contentView;
    }

    private void addACViews(RelativeLayout contentView) {
        RelativeLayoutManager manager = new RelativeLayoutManager(contentView);
        this.windProgress = new MyProgressBar(this, R.drawable.conditioning_progress_bar_up, R.drawable.conditioning_progress_bar_dn);
        manager.AddViewWrapContent(this.windProgress, 80, 40);
        this.windProgress.SetMinMax(0, 7);
        this.windProgress.SetCurPos(0);
        this.windVal = manager.AddText(CanCameraUI.BTN_YG9_XBS_MODE1, 27, 60, 40);
        this.windVal.setTextSize(0, 30.0f);
        this.windVal.setTextColor(-1);
        this.windVal.setText("0");
        this.windVal.setGravity(19);
        ParamButton decreaseWind = manager.AddButton(0, 0);
        decreaseWind.setStateDrawable(R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, R.drawable.can_rh7_fss_dn);
        decreaseWind.setTag(15);
        decreaseWind.setOnTouchListener(this);
        ParamButton increaseWind = manager.AddButton(CanCameraUI.BTN_GEELY_YJX6_MODE1, 5);
        increaseWind.setStateDrawable(R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, R.drawable.can_rh7_fsb_dn);
        increaseWind.setTag(16);
        increaseWind.setOnTouchListener(this);
    }
}
