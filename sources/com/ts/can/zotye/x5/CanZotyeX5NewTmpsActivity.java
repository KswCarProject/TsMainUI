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

public class CanZotyeX5NewTmpsActivity extends CanBaseActivity implements UserCallBack {
    protected CanDataInfo.ZtX5TPMSData mData = new CanDataInfo.ZtX5TPMSData();
    protected CustomImgView[] mIvTyres = new CustomImgView[4];
    protected RelativeLayoutManager mManager;
    protected CustomTextView[] mTvPress = new CustomTextView[4];
    protected CustomTextView[] mTvTemp = new CustomTextView[4];
    protected CustomTextView[] mTvWarn = new CustomTextView[4];
    private String[] mWarnStatus = {"系统故障  ", "信号丢失  ", "系统自检  ", "胎压故障灯  ", "电池电量低  ", "高温  ", "超高温  ", "气压低  ", "气压高  ", "慢速漏气  ", "快速漏气  "};

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_rf_tpms_bg);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = this.mManager.AddImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 97, ((i / 2) * Can.CAN_CC_HF_DJ) + 69, 138, 50);
            this.mTvPress[i].SetPixelSize(35);
            this.mTvPress[i].setGravity(3);
            this.mTvTemp[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 97, ((i / 2) * Can.CAN_CC_HF_DJ) + 130, 138, 50);
            this.mTvTemp[i].SetPixelSize(35);
            this.mTvTemp[i].setGravity(3);
            this.mTvWarn[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 210, ((i / 2) * Can.CAN_CC_HF_DJ) + 44, Can.CAN_BJ20_WC, Can.CAN_AUDI_ZMYT);
            this.mTvWarn[i].SetPixelSize(22);
            this.mTvWarn[i].setGravity(3);
            this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
        }
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
        CanJni.ZotyeQuery(64, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        CanJni.ZtDmX5GetTpms(this.mData);
        if (i2b(this.mData.FlUpdateOnce) && (!check || i2b(this.mData.FlUpdate))) {
            this.mData.FlUpdate = 0;
            SetVal(0, this.mData.FlVal, this.mData.FlTemp);
            this.mTvWarn[0].setText(GetWarnStr(this.mData.FlSysWarn, this.mData.FlMsgWarn, this.mData.FlSysChk, this.mData.FlTpmsLed, this.mData.FlBatLow, this.mData.FlHigTemp, this.mData.FlHigTempM, this.mData.FlBarLow, this.mData.FlBarHigh, this.mData.FlSlowLeak, this.mData.FlFastLeak));
            if (this.mData.FlSysWarn > 0 || this.mData.FlMsgWarn > 0 || this.mData.FlSysChk > 0 || this.mData.FlTpmsLed > 0 || this.mData.FlBatLow > 0 || this.mData.FlHigTemp > 0 || this.mData.FlHigTempM > 0 || this.mData.FlBarLow > 0 || this.mData.FlBarHigh > 0 || this.mData.FlSlowLeak > 0 || this.mData.FlFastLeak > 0) {
                this.mIvTyres[0].setSelected(true);
            } else {
                this.mIvTyres[0].setSelected(false);
            }
        }
        if (i2b(this.mData.FrUpdateOnce) && (!check || i2b(this.mData.FrUpdate))) {
            this.mData.FrUpdate = 0;
            SetVal(1, this.mData.FrVal, this.mData.FrTemp);
            this.mTvWarn[1].setText(GetWarnStr(this.mData.FrSysWarn, this.mData.FrMsgWarn, this.mData.FrSysChk, this.mData.FrTpmsLed, this.mData.FrBatLow, this.mData.FrHigTemp, this.mData.FrHigTempM, this.mData.FrBarLow, this.mData.FrBarHigh, this.mData.FrSlowLeak, this.mData.FrFastLeak));
            if (this.mData.FrSysWarn > 0 || this.mData.FrMsgWarn > 0 || this.mData.FrSysChk > 0 || this.mData.FrTpmsLed > 0 || this.mData.FrBatLow > 0 || this.mData.FrHigTemp > 0 || this.mData.FrHigTempM > 0 || this.mData.FrBarLow > 0 || this.mData.FrBarHigh > 0 || this.mData.FrSlowLeak > 0 || this.mData.FrFastLeak > 0) {
                this.mIvTyres[1].setSelected(true);
            } else {
                this.mIvTyres[1].setSelected(false);
            }
        }
        if (i2b(this.mData.RlUpdateOnce) && (!check || i2b(this.mData.RlUpdate))) {
            this.mData.RlUpdate = 0;
            SetVal(2, this.mData.RlVal, this.mData.RlTemp);
            this.mTvWarn[2].setText(GetWarnStr(this.mData.RlSysWarn, this.mData.RlMsgWarn, this.mData.RlSysChk, this.mData.RlTpmsLed, this.mData.RlBatLow, this.mData.RlHigTemp, this.mData.RlHigTempM, this.mData.RlBarLow, this.mData.RlBarHigh, this.mData.RlSlowLeak, this.mData.RlFastLeak));
            if (this.mData.RlSysWarn > 0 || this.mData.RlMsgWarn > 0 || this.mData.RlSysChk > 0 || this.mData.RlTpmsLed > 0 || this.mData.RlBatLow > 0 || this.mData.RlHigTemp > 0 || this.mData.RlHigTempM > 0 || this.mData.RlBarLow > 0 || this.mData.RlBarHigh > 0 || this.mData.RlSlowLeak > 0 || this.mData.RlFastLeak > 0) {
                this.mIvTyres[2].setSelected(true);
            } else {
                this.mIvTyres[2].setSelected(false);
            }
        }
        if (!i2b(this.mData.RrUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData.RrUpdate)) {
            this.mData.RrUpdate = 0;
            SetVal(3, this.mData.RrVal, this.mData.RrTemp);
            this.mTvWarn[3].setText(GetWarnStr(this.mData.RrSysWarn, this.mData.RrMsgWarn, this.mData.RrSysChk, this.mData.RrTpmsLed, this.mData.RrBatLow, this.mData.RrHigTemp, this.mData.RrHigTempM, this.mData.RrBarLow, this.mData.RrBarHigh, this.mData.RrSlowLeak, this.mData.RrFastLeak));
            if (this.mData.RrSysWarn > 0 || this.mData.RrMsgWarn > 0 || this.mData.RrSysChk > 0 || this.mData.RrTpmsLed > 0 || this.mData.RrBatLow > 0 || this.mData.RrHigTemp > 0 || this.mData.RrHigTempM > 0 || this.mData.RrBarLow > 0 || this.mData.RrBarHigh > 0 || this.mData.RrSlowLeak > 0 || this.mData.RrFastLeak > 0) {
                this.mIvTyres[3].setSelected(true);
            } else {
                this.mIvTyres[3].setSelected(false);
            }
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
        if (press == 255) {
            return "-- Kpa";
        }
        return String.format("%d Kpa", new Object[]{Integer.valueOf(press)});
    }

    public String GetTempStr(int temp) {
        if (temp == 255) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 40)});
    }

    public String GetWarnStr(int temp1, int temp2, int temp3, int temp4, int temp5, int temp6, int temp7, int temp8, int temp9, int temp10, int temp11) {
        String stringw = "";
        if (temp1 != 0) {
            stringw = this.mWarnStatus[0];
        }
        if (temp2 != 0) {
            stringw = String.valueOf(stringw) + this.mWarnStatus[1];
        }
        if (temp3 != 0) {
            stringw = String.valueOf(stringw) + this.mWarnStatus[2];
        }
        if (temp4 != 0) {
            stringw = String.valueOf(stringw) + this.mWarnStatus[3];
        }
        if (temp5 != 0) {
            stringw = String.valueOf(stringw) + this.mWarnStatus[4];
        }
        if (temp7 != 0) {
            stringw = String.valueOf(stringw) + this.mWarnStatus[6];
        } else if (temp6 != 0) {
            stringw = String.valueOf(stringw) + this.mWarnStatus[5];
        }
        if (temp8 != 0) {
            stringw = String.valueOf(stringw) + this.mWarnStatus[7];
        } else if (temp9 != 0) {
            stringw = String.valueOf(stringw) + this.mWarnStatus[8];
        }
        if (temp10 != 0) {
            return String.valueOf(stringw) + this.mWarnStatus[9];
        }
        if (temp11 != 0) {
            return String.valueOf(stringw) + this.mWarnStatus[10];
        }
        return stringw;
    }

    public void UserAll() {
        ResetData(true);
    }
}
