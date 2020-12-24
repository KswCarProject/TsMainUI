package com.ts.can.landwind.rzc;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;

public class CanLandWindTpmsView extends CanRelativeCarInfoView {
    private static final String[] mWarnArrays = {"胎压正常", "气压过高", "气压过低", "快速漏气", "未收到胎压信息"};
    protected CustomImgView[] mIvTyres;
    protected CanDataInfo.LandWind_Tpms mTpmsData;
    protected CanDataInfo.LandWind_TpmsWarn mTpmswarnData;
    protected TextView[] mTvPress;
    protected TextView mTvStatus;
    protected TextView[] mTvTemp;
    protected TextView[] mTvWarn;

    public CanLandWindTpmsView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mIvTyres = new CustomImgView[4];
        this.mTvPress = new TextView[4];
        this.mTvTemp = new TextView[4];
        this.mTvWarn = new TextView[4];
        this.mTpmsData = new CanDataInfo.LandWind_Tpms();
        this.mTpmswarnData = new CanDataInfo.LandWind_TpmsWarn();
        setBackgroundResource(R.drawable.can_rf_tpms_bg);
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = addImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 49, 281, 50);
            this.mTvPress[i].setTextSize(0, 35.0f);
            this.mTvPress[i].setTextColor(-1);
            this.mTvPress[i].setGravity(17);
            this.mTvTemp[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 95, 281, 50);
            this.mTvTemp[i].setTextSize(0, 35.0f);
            this.mTvTemp[i].setTextColor(-1);
            this.mTvTemp[i].setGravity(17);
            this.mTvWarn[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 144, 281, 50);
            this.mTvWarn[i].setTextSize(0, 35.0f);
            this.mTvWarn[i].setTextColor(-1);
            this.mTvWarn[i].setGravity(17);
        }
    }

    public void ResetData(boolean check) {
        GetTpmsData();
        GetTpmsWarnData();
        if (i2b(this.mTpmsData.UpdateOnce) && (!check || i2b(this.mTpmsData.Update))) {
            this.mTpmsData.Update = 0;
            SetVal(0, this.mTpmsData.TPMS[0], this.mTpmsData.TEMP[0]);
            SetVal(1, this.mTpmsData.TPMS[1], this.mTpmsData.TEMP[1]);
            SetVal(2, this.mTpmsData.TPMS[2], this.mTpmsData.TEMP[2]);
            SetVal(3, this.mTpmsData.TPMS[3], this.mTpmsData.TEMP[3]);
        }
        if (!i2b(this.mTpmswarnData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmswarnData.Update)) {
            this.mTpmswarnData.Update = 0;
            setTyresInfos((this.mTpmswarnData.PreWarn[0] >> 4) & 255, this.mTpmswarnData.PreWarn[0] & 15, (this.mTpmswarnData.PreWarn[1] >> 4) & 255, this.mTpmswarnData.PreWarn[1] & 15);
        }
    }

    private void setTyresInfos(int fLState, int fRState, int rLState, int rRState) {
        int[] states = {fLState, fRState, rLState, rRState};
        for (int i = 0; i < this.mIvTyres.length; i++) {
            this.mIvTyres[i].setSelected(states[i] != 0);
            switch (states[i]) {
                case 0:
                    this.mTvWarn[i].setTextColor(-1);
                    this.mTvWarn[i].setText(mWarnArrays[0]);
                    int FBatWarn = (this.mTpmswarnData.BatWarn >> 4) & 255;
                    int RBatWarn = this.mTpmswarnData.BatWarn & 15;
                    int FTempWarn = (this.mTpmswarnData.TempWarn >> 4) & 255;
                    int RTempWarn = this.mTpmswarnData.TempWarn & 15;
                    switch (i) {
                        case 0:
                            setTempandBat(i, 8, 4, FBatWarn, FTempWarn);
                            break;
                        case 1:
                            setTempandBat(i, 2, 1, FBatWarn, FTempWarn);
                            break;
                        case 2:
                            setTempandBat(i, 8, 4, RBatWarn, RTempWarn);
                            break;
                        case 3:
                            setTempandBat(i, 2, 1, RBatWarn, RTempWarn);
                            break;
                    }
                case 2:
                case 4:
                case 6:
                case 8:
                    this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
                    this.mTvWarn[i].setText(mWarnArrays[states[i] / 2]);
                    break;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void GetTpmsData() {
        CanJni.LoadWindRzcGetTpmsData(this.mTpmsData);
    }

    /* access modifiers changed from: protected */
    public void GetTpmsWarnData() {
        CanJni.LoadWindRzcGetTpmsWarn(this.mTpmswarnData);
    }

    private void setTempandBat(int i, int unreceivd, int warn, int FBatWarn, int FTempWarn) {
        if (FTempWarn == unreceivd) {
            this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
            this.mTvWarn[i].setText("未收到温度信息");
        } else if (FTempWarn == warn) {
            this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
            this.mTvWarn[i].setText("温度过高");
        } else if (FTempWarn != 0) {
        } else {
            if (FBatWarn == warn) {
                this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
                this.mTvWarn[i].setText("电量过低");
            } else if (FBatWarn == unreceivd) {
                this.mTvWarn[i].setTextColor(SupportMenu.CATEGORY_MASK);
                this.mTvWarn[i].setText("未收到电量信息");
            } else if (FBatWarn == 0) {
                this.mTvWarn[i].setTextColor(-1);
                this.mTvWarn[i].setText("一切正常");
            }
        }
    }

    public void QueryData() {
        CanJni.LoadWindRzcQuery(36);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CanJni.LoadWindRzcQuery(37);
    }

    public String GetPressStr(int press) {
        if (press == 255) {
            return "-.- bar";
        }
        return String.format("%.1f bar", new Object[]{Double.valueOf(((double) press) / 10.0d)});
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
