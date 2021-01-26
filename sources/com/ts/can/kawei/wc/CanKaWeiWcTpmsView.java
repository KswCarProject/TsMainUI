package com.ts.can.kawei.wc;

import android.app.Activity;
import android.text.TextUtils;
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
import com.txznet.sdk.TXZResourceManager;

public class CanKaWeiWcTpmsView extends CanRelativeCarInfoView {
    protected CustomImgView[] mIvTyres;
    protected CanDataInfo.KaWeiWc_Tpms mTpmsData;
    protected TextView[] mTvPress;
    protected TextView[] mTvStatus;
    protected TextView[] mTvTemp;

    public CanKaWeiWcTpmsView(Activity activity) {
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
        this.mTpmsData = new CanDataInfo.KaWeiWc_Tpms();
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
    }

    public void ResetData(boolean check) {
        CanJni.KaWWcGetTpmsInfo(this.mTpmsData);
        if (!i2b(this.mTpmsData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmsData.Update)) {
            this.mTpmsData.Update = 0;
            if (i2b(this.mTpmsData.Adt)) {
                for (int i = 0; i < this.mIvTyres.length; i++) {
                    SetVal(i, this.mTpmsData.Val[i], this.mTpmsData.Temp[i], this.mTpmsData.Warn[i]);
                }
            }
        }
    }

    public void QueryData() {
        CanJni.KaWWcQuery(5, 1, 72);
    }

    public String GetPressStr(int press, int warn) {
        float kpaValue;
        if (i2b(warn & 16)) {
            kpaValue = 3.137f;
        } else {
            kpaValue = 1.3725f;
        }
        if (press == 255) {
            return "-.- bar";
        }
        return String.format("%.1f bar", new Object[]{Float.valueOf(((float) press) * kpaValue)});
    }

    public String GetTempStr(int temp) {
        if (temp == 255) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 50)});
    }

    public String GetStatusStr(int warn) {
        if (i2b(warn & 8)) {
            return getString(R.string.can_gzbj);
        }
        if (i2b(warn & 4)) {
            return getString(R.string.can_ylbj);
        }
        if (i2b(warn & 2)) {
            return getString(R.string.can_kslqbj);
        }
        if (i2b(warn & 1)) {
            return getString(R.string.can_wdbj);
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public void SetVal(int id, int press, int temp, int warn) {
        this.mTvPress[id].setText(GetPressStr(press, warn));
        this.mTvTemp[id].setText(GetTempStr(temp));
        this.mTvStatus[id].setText(GetStatusStr(warn));
        this.mIvTyres[id].setSelected(!TextUtils.isEmpty(GetStatusStr(warn)));
    }
}
