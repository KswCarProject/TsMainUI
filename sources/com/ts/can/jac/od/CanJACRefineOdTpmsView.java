package com.ts.can.jac.od;

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

public class CanJACRefineOdTpmsView extends CanRelativeCarInfoView {
    private static final String[] mWarnArrays = {"胎压正常", "胎压异常", "轮胎漏气", "胎压警告"};
    private CanDataInfo.JAC_PMS_DATA mData;
    protected CustomImgView[] mIvTyres;
    protected TextView[] mTvPress;
    protected TextView[] mTvTemp;
    protected TextView[] mTvWarn;
    private CanDataInfo.JAC_TPMS_WARN mWarn;

    public CanJACRefineOdTpmsView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_rf_tpms_bg);
        this.mIvTyres = new CustomImgView[4];
        this.mTvPress = new TextView[4];
        this.mTvTemp = new TextView[4];
        this.mTvWarn = new TextView[4];
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = addImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 49, 281, 50);
            this.mTvPress[i].setTextSize(0, 35.0f);
            this.mTvPress[i].setGravity(17);
            this.mTvPress[i].setTextColor(-1);
            this.mTvTemp[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 95, 281, 50);
            this.mTvTemp[i].setTextSize(0, 35.0f);
            this.mTvTemp[i].setGravity(17);
            this.mTvTemp[i].setTextColor(-1);
            this.mTvWarn[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 144, 281, 50);
            this.mTvWarn[i].setTextSize(0, 35.0f);
            this.mTvWarn[i].setGravity(17);
            this.mTvWarn[i].setTextColor(-1);
        }
        this.mData = new CanDataInfo.JAC_PMS_DATA();
        this.mWarn = new CanDataInfo.JAC_TPMS_WARN();
    }

    public void ResetData(boolean check) {
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

    public void QueryData() {
    }
}
