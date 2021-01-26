package com.ts.can.sitechdev.cw;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.android.SdkConstants;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;

public class CanSitechDevCwTpmsInfoView extends CanRelativeCarInfoView {
    protected CustomImgView[] mIvTyres;
    private CanDataInfo.SitechDev_Tpms mTpmsData;
    protected TextView[] mTvPress;
    protected TextView[] mTvTemp;

    public CanSitechDevCwTpmsInfoView(Activity activity) {
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
        this.mTpmsData = new CanDataInfo.SitechDev_Tpms();
    }

    public void ResetData(boolean check) {
        for (int i = 0; i < 4; i++) {
            CanJni.SitechDevCwGetTpmsData(this.mTpmsData, i);
            if (i2b(this.mTpmsData.UpdateOnce) && (!check || i2b(this.mTpmsData.Update))) {
                this.mTpmsData.Update = 0;
                SetVal(i, this.mTpmsData.Pre, this.mTpmsData.Temp);
            }
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
        return SdkConstants.RES_QUALIFIER_SEP;
    }

    public void SetVal(int id, int press, int temp) {
        this.mTvPress[id].setText(String.valueOf(press) + "Kpa");
        if (temp < 0 || temp > 200) {
            this.mTvTemp[id].setText("-℃");
        } else {
            this.mTvTemp[id].setText(String.valueOf(temp - 50) + "℃");
        }
    }

    public void QueryData() {
    }
}
