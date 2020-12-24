package com.ts.can.geely.comm;

import android.app.Activity;
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

public class CanGeelyX3TpmsView extends CanRelativeCarInfoView {
    private static final String[] mWarnArrays = {"压力过低", "压力过高", "温度过高", "快速漏气", "传感器失效"};
    protected CustomImgView[] mIvTyres;
    private CanDataInfo.Geely_TpmsInfo mTpmsInfo;
    protected TextView[] mTvPress;
    protected TextView[] mTvStatus;
    protected TextView[] mTvTemp;

    public CanGeelyX3TpmsView(Activity activity) {
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
        this.mTvStatus = new TextView[4];
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
            this.mTvStatus[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 144, 281, 50);
            this.mTvStatus[i].setTextSize(0, 35.0f);
            this.mTvStatus[i].setTextColor(-1);
            this.mTvStatus[i].setGravity(17);
        }
        this.mTpmsInfo = new CanDataInfo.Geely_TpmsInfo();
    }

    public void ResetData(boolean check) {
        CanJni.GeelyRzcGetTpmsData(this.mTpmsInfo);
        if (!i2b(this.mTpmsInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmsInfo.Update)) {
            this.mTpmsInfo.Update = 0;
            setWarn(this.mTpmsInfo.Warn);
            setTpms(this.mTpmsInfo.Val);
            setTemp(this.mTpmsInfo.Tmp);
        }
    }

    public void QueryData() {
        CanJni.GeelyCarQuery(82, 0);
    }

    private void setWarn(int[] warn) {
        for (int i = 0; i < this.mTvStatus.length; i++) {
            if (warn[i] == 0) {
                this.mTvStatus[i].setText("胎压正常");
                this.mIvTyres[i].setSelected(false);
            } else if (warn[i] - 1 >= 0 && warn[i] - 1 < mWarnArrays.length) {
                this.mTvStatus[i].setText(mWarnArrays[warn[i] - 1]);
                this.mIvTyres[i].setSelected(true);
            }
        }
    }

    private void setTpms(int[] tpms) {
        for (int i = 0; i < this.mTvPress.length; i++) {
            this.mTvPress[i].setText(String.valueOf(Math.round(((double) tpms[i]) * 1.366d)) + " KPA");
        }
    }

    private void setTemp(int[] temp) {
        for (int i = 0; i < this.mTvTemp.length; i++) {
            this.mTvTemp[i].setText(String.format("%d ℃", new Object[]{Integer.valueOf(temp[i] - 50)}));
        }
    }
}
