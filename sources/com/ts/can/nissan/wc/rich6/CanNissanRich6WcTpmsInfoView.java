package com.ts.can.nissan.wc.rich6;

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

public class CanNissanRich6WcTpmsInfoView extends CanRelativeCarInfoView {
    protected CustomImgView[] mIvTyres;
    private CanDataInfo.NissanRich6Wc_TpmsData mTpmsData;
    protected TextView[] mTvPress;
    protected TextView[] mTvTemp;

    public CanNissanRich6WcTpmsInfoView(Activity activity) {
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
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = addImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_JIANGLING_MYX) + 66, 281, 50);
            this.mTvPress[i].setTextSize(0, 35.0f);
            this.mTvPress[i].setGravity(17);
            this.mTvPress[i].setTextColor(-1);
            this.mTvTemp[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_JIANGLING_MYX) + 112, 281, 50);
            this.mTvTemp[i].setTextSize(0, 35.0f);
            this.mTvTemp[i].setGravity(17);
            this.mTvTemp[i].setTextColor(-1);
        }
        this.mTpmsData = new CanDataInfo.NissanRich6Wc_TpmsData();
    }

    public void ResetData(boolean check) {
        CanJni.NissanRiChWcGetTpmsData(this.mTpmsData);
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

    public String GetPressStr(int press) {
        if (press < 255) {
            return String.valueOf(press) + "Kpa";
        }
        return "-.-";
    }

    public String GetTempStr(int temp) {
        if (temp < 255) {
            return String.valueOf(temp) + "℃";
        }
        return "-";
    }

    public void SetVal(int id, int press, int temp) {
        this.mTvPress[id].setText(GetPressStr(press));
        this.mTvTemp[id].setText(GetTempStr(temp));
    }

    public void QueryData() {
        CanJni.NissanRiChWcQuery(5, 1, 72);
    }
}
