package com.ts.can.jac;

import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.canview.CanItemProgressList;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;

public class CanJACRefineTpmsActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanJACRefineTpmsActivity";
    private static final String[] mWarnArrays = {"胎压正常", "胎压异常", "轮胎漏气", "胎压警告"};
    protected CanDataInfo.JAC_PMS_DATA mData = new CanDataInfo.JAC_PMS_DATA();
    protected CustomImgView[] mIvTyres = new CustomImgView[4];
    protected RelativeLayoutManager mManager;
    protected CustomTextView[] mTvPress = new CustomTextView[4];
    protected CustomTextView mTvStatus;
    protected CustomTextView[] mTvTemp = new CustomTextView[4];
    protected CustomTextView[] mTvWarn = new CustomTextView[4];
    protected CanDataInfo.JAC_TPMS_WARN mWarn = new CanDataInfo.JAC_TPMS_WARN();
    private int screenHeight;
    private int screenWidth;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.can_comm_layout);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        initView(relativeLayout);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        this.screenWidth = mDisplayMetrics.widthPixels;
        this.screenHeight = mDisplayMetrics.heightPixels;
        if (this.screenWidth == 1024 && this.screenHeight == 400) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            initView(relativeLayout);
            this.mManager.GetLayout().setScaleY(0.63f);
            return;
        }
        initView(relativeLayout);
    }

    private void initView(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundResource(R.drawable.can_rf_tpms_bg);
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = this.mManager.AddImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 49, 281, 50);
            this.mTvPress[i].SetPixelSize(35);
            this.mTvPress[i].setGravity(17);
            this.mTvTemp[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 95, 281, 50);
            this.mTvTemp[i].SetPixelSize(35);
            this.mTvTemp[i].setGravity(17);
            this.mTvWarn[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 144, 281, 50);
            this.mTvWarn[i].SetPixelSize(35);
            this.mTvWarn[i].setGravity(17);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    private void ResetData(boolean check) {
        CanJni.JACRefineGetTpms(this.mData);
        if (i2b(this.mData.UpdateOnce) && (!check || i2b(this.mData.Update))) {
            this.mData.Update = 0;
            SetVal(0, this.mData.FLPress, this.mData.FLTemp);
            SetVal(1, this.mData.FRPress, this.mData.FRTemp);
            SetVal(2, this.mData.RLPress, this.mData.RLTemp);
            SetVal(3, this.mData.RRPress, this.mData.RRTemp);
        }
        CanJni.JacGetTpmsrSta(this.mWarn);
        if (!i2b(this.mWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarn.Update)) {
            this.mWarn.Update = 0;
            setTyresInfos(this.mWarn.FLState, this.mWarn.FRState, this.mWarn.RLState, this.mWarn.RRState);
        }
    }

    private void setTyresInfos(int fLState, int fRState, int rLState, int rRState) {
        boolean z;
        int[] states = {fLState, fRState, rLState, rRState};
        for (int i = 0; i < this.mIvTyres.length; i++) {
            CustomImgView customImgView = this.mIvTyres[i];
            if (states[i] != 0) {
                z = true;
            } else {
                z = false;
            }
            customImgView.setSelected(z);
            this.mTvWarn[i].setText(mWarnArrays[states[i]]);
            switch (states[i]) {
                case 0:
                    this.mTvWarn[i].setTextColor(-1);
                    break;
                case 1:
                case 2:
                    this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
                    break;
                case 3:
                    this.mTvWarn[i].setTextColor(-256);
                    break;
            }
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public String GetPressStr(int press) {
        if (press >= 166) {
            return "-.- bar";
        }
        return String.format("%.1f bar", new Object[]{Double.valueOf(((double) press) * 0.02745d)});
    }

    public String GetTempStr(int temp) {
        if (temp >= 166) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 40)});
    }

    public void SetVal(int id, int press, int temp) {
        this.mTvPress[id].setText(GetPressStr(press));
        this.mTvTemp[id].setText(GetTempStr(temp));
    }
}
