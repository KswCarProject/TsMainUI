package com.ts.can.zotye.x5;

import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;

public class CanZotyeX5TmpsActivity extends CanBaseActivity implements UserCallBack {
    protected CanDataInfo.ZtTPMSData mData = new CanDataInfo.ZtTPMSData();
    protected CustomImgView[] mIvTyres = new CustomImgView[4];
    protected RelativeLayoutManager mManager;
    protected CustomTextView[] mTvPress = new CustomTextView[4];
    protected CustomTextView mTvStatus;
    protected CustomTextView[] mTvTemp = new CustomTextView[4];
    protected CustomTextView[] mTvWarn = new CustomTextView[4];
    protected CanDataInfo.ZtTPMSWarn mWarn = new CanDataInfo.ZtTPMSWarn();
    private String[] mWarnStatus = {"正常", "失效", "低压", "高压"};

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
        this.mTvStatus = this.mManager.AddCusText(375, 45, 281, 50);
        this.mTvStatus.SetPixelSize(35);
        this.mTvStatus.setGravity(17);
        this.mManager.AddImage(375, 55, R.drawable.can_rf_tpms_line);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Query();
    }

    private void Query() {
        CanJni.ZotyeQuery(56, 0);
        Sleep(1);
        CanJni.ZotyeQuery(57, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        CanJni.ZotyeGetTpmsData(this.mData);
        CanJni.ZotyeGetTpmsWarn(this.mWarn);
        if (i2b(this.mData.UpdateOnce) && (!check || i2b(this.mData.Update))) {
            this.mData.Update = 0;
            for (int i = 0; i < this.mData.Val.length; i++) {
                SetVal(i, this.mData.Val[i], this.mData.Temp[i]);
            }
        }
        if (!i2b(this.mWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarn.Update)) {
            this.mWarn.Update = 0;
            for (int i2 = 0; i2 < this.mWarn.Warn.length; i2++) {
                setWarn(i2, this.mWarn.Warn[i2]);
            }
            if (i2b(this.mWarn.SysInvalid)) {
                this.mTvStatus.setText("系统失效");
                this.mTvStatus.setTextColor(SupportMenu.CATEGORY_MASK);
                return;
            }
            this.mTvStatus.setText("系统正常");
            this.mTvStatus.setTextColor(-1);
        }
    }

    public void setWarn(int id, int warn) {
        if (warn >= 0 && warn < this.mWarnStatus.length) {
            if (warn == 0) {
                this.mTvWarn[id].setTextColor(-1);
                this.mIvTyres[id].setSelected(false);
            } else {
                this.mTvWarn[id].setTextColor(SupportMenu.CATEGORY_MASK);
                this.mIvTyres[id].setSelected(true);
            }
            this.mTvWarn[id].setText(this.mWarnStatus[warn]);
        }
    }

    public void SetVal(int id, int press, int temp) {
        this.mTvPress[id].setText(GetPressStr(press));
        this.mTvTemp[id].setText(GetTempStr(temp));
    }

    public String GetPressStr(int press) {
        if (press >= 187) {
            return "-.- Kpa";
        }
        return String.format("%.1f Kpa", new Object[]{Float.valueOf(((float) press) * 1.373f)});
    }

    public String GetTempStr(int temp) {
        if (temp >= 166) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp)});
    }

    public void UserAll() {
        ResetData(true);
    }
}
