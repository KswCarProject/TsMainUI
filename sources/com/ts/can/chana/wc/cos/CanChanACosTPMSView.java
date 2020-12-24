package com.ts.can.chana.wc.cos;

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

public class CanChanACosTPMSView extends CanRelativeCarInfoView {
    protected CustomImgView[] mIvTyres;
    protected CanDataInfo.Cos1WcTpms mTpmsData;
    protected TextView[] mTvPress;
    protected TextView mTvStatus;
    protected TextView[] mTvTemp;

    public CanChanACosTPMSView(Activity activity) {
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
        this.mTpmsData = new CanDataInfo.Cos1WcTpms();
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
        }
    }

    public void ResetData(boolean check) {
        CanJni.ChanAWcCos1GetTpmsData(this.mTpmsData);
        if (!i2b(this.mTpmsData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmsData.Update)) {
            this.mTpmsData.Update = 0;
            SetVal(0, this.mTpmsData.Val[0], this.mTpmsData.Temp[0]);
            SetVal(1, this.mTpmsData.Val[1], this.mTpmsData.Temp[1]);
            SetVal(2, this.mTpmsData.Val[2], this.mTpmsData.Temp[2]);
            SetVal(3, this.mTpmsData.Val[3], this.mTpmsData.Temp[3]);
        }
    }

    public void QueryData() {
        CanJni.ChanAWcCos1Query(5, 1, 72);
    }

    public String GetPressStr(int press) {
        if (press == 255) {
            return "-.- bar";
        }
        return String.format("%.1f bar", new Object[]{Double.valueOf(((double) press) / 10.0d)});
    }

    public String GetTempStr(int temp) {
        if (temp == 255) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 40)});
    }

    public void SetVal(int id, int press, int temp) {
        this.mTvPress[id].setText(GetPressStr(press));
        this.mTvTemp[id].setText(GetTempStr(temp));
    }
}
