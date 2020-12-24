package com.ts.can.chana.cs75;

import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.View;
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

public class CanChanATpmsActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanChanATpmsActivity";
    private static final String[] mWarnArrays = {"胎压正常", "温度异常", "胎压异常", "低压报警", "高压报警", "快速泄露", "传感器丢失", "传感器电池电量低", "传感器故障"};
    protected CanDataInfo.CHANA_TPMS_DATA mData = new CanDataInfo.CHANA_TPMS_DATA();
    protected CustomImgView[] mIvTyres = new CustomImgView[4];
    protected RelativeLayoutManager mManager;
    protected CustomTextView[] mTvPress = new CustomTextView[4];
    protected CustomTextView mTvStatus;
    protected CustomTextView[] mTvTemp = new CustomTextView[4];
    protected CustomTextView[] mTvWarn = new CustomTextView[4];
    protected CanDataInfo.CHANA_TPMS_WARN mWarn = new CanDataInfo.CHANA_TPMS_WARN();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_rf_tpms_bg);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
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
        CanJni.Cs75CarQuery(56, 0);
        Sleep(10);
        CanJni.Cs75CarQuery(57, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    private void ResetData(boolean check) {
        CanJni.ChanAGetTpmsData(this.mData);
        if (i2b(this.mData.UpdateOnce) && (!check || i2b(this.mData.Update))) {
            this.mData.Update = 0;
            SetVal(0, this.mData.FLPress, this.mData.FLTemp);
            SetVal(1, this.mData.FRPress, this.mData.FRTemp);
            SetVal(2, this.mData.RLPress, this.mData.RLTemp);
            SetVal(3, this.mData.RRPress, this.mData.RRTemp);
        }
        CanJni.ChanAGetTpmsWarn(this.mWarn);
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
            if ((states[i] & 8) > 0) {
                this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
                this.mTvWarn[i].setText(mWarnArrays[1]);
            } else if ((states[i] & 4) > 0) {
                this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
                this.mTvWarn[i].setText(mWarnArrays[2]);
            } else if ((states[i] & 2) > 0) {
                this.mTvWarn[i].setTextColor(-256);
                this.mTvWarn[i].setText(mWarnArrays[3]);
            } else if ((states[i] & 1) > 0) {
                this.mTvWarn[i].setTextColor(-256);
                this.mTvWarn[i].setText(mWarnArrays[4]);
            } else if ((states[i] & 16) > 0) {
                this.mTvWarn[i].setTextColor(-256);
                this.mTvWarn[i].setText(mWarnArrays[5]);
            } else if ((states[i] & 32) > 0) {
                this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
                this.mTvWarn[i].setText(mWarnArrays[6]);
            } else if ((states[i] & 64) > 0) {
                this.mTvWarn[i].setTextColor(-256);
                this.mTvWarn[i].setText(mWarnArrays[7]);
            } else if ((states[i] & 128) > 0) {
                this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
                this.mTvWarn[i].setText(mWarnArrays[8]);
            } else {
                this.mTvWarn[i].setTextColor(-1);
                this.mTvWarn[i].setText(mWarnArrays[0]);
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
        if (press >= 254) {
            return "-.- bar";
        }
        return String.format("%.1f bar", new Object[]{Double.valueOf((((double) press) * 0.01428d) - 0.1d)});
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
