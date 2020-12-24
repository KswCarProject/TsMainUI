package com.ts.can.se.rzc.dx7;

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

public class CanSeDx7RzcTpmsView extends CanRelativeCarInfoView {
    protected CustomImgView[] mIvTyres;
    private CanDataInfo.SeDx7Rzc_TpmsData mTpmsInfo;
    protected TextView[] mTvPress;
    protected TextView[] mTvStatus;
    protected TextView[] mTvTemp;

    public CanSeDx7RzcTpmsView(Activity activity) {
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
            this.mTvPress[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 95, 281, 50);
            this.mTvPress[i].setTextSize(0, 35.0f);
            this.mTvPress[i].setTextColor(-1);
            this.mTvPress[i].setGravity(17);
        }
        this.mTpmsInfo = new CanDataInfo.SeDx7Rzc_TpmsData();
    }

    public void ResetData(boolean check) {
        CanJni.SeDx7RzcGetTpmsData(this.mTpmsInfo);
        if (!i2b(this.mTpmsInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmsInfo.Update)) {
            this.mTpmsInfo.Update = 0;
            setTpms(this.mTpmsInfo.Val);
        }
    }

    public void QueryData() {
        CanJni.SeDx7RzcCarQuery(37, 0);
    }

    private void setTpms(int[] tpms) {
        for (int i = 0; i < this.mTvPress.length; i++) {
            this.mTvPress[i].setText(String.valueOf(tpms[i]) + " KPA");
        }
    }
}
