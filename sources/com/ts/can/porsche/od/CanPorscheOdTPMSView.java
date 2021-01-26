package com.ts.can.porsche.od;

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
import com.txznet.sdk.TXZResourceManager;

public class CanPorscheOdTPMSView extends CanRelativeCarInfoView {
    protected CustomImgView[] mIvTyres;
    private CanDataInfo.PorscheTpmsData mTpmsData;
    private CanDataInfo.PorscheTpmsWarn mTpmsWarn;
    protected TextView mTvStatus;
    protected TextView[] mTvTpms;
    protected TextView[] mTvWarns;
    private String[] mWarnsArray;

    public CanPorscheOdTPMSView(Activity activity) {
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
        addImage(375, 55, R.drawable.can_rf_tpms_line);
        this.mIvTyres = new CustomImgView[4];
        this.mTvTpms = new TextView[4];
        this.mTvWarns = new TextView[4];
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = addImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvTpms[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 80, 281, 50);
            this.mTvTpms[i].setTextSize(0, 35.0f);
            this.mTvTpms[i].setGravity(17);
            this.mTvTpms[i].setTextColor(-1);
        }
        this.mTpmsData = new CanDataInfo.PorscheTpmsData();
        this.mTpmsWarn = new CanDataInfo.PorscheTpmsWarn();
        this.mWarnsArray = getActivity().getResources().getStringArray(R.array.can_porsche_tyres_warn_array);
        this.mTvStatus = addText(375, 45, 281, 50);
        this.mTvStatus.setTextSize(0, 40.0f);
        this.mTvStatus.setGravity(17);
    }

    /* access modifiers changed from: package-private */
    public String PreStr(int data, int dw) {
        switch (dw) {
            case 0:
                return String.valueOf(((double) data) * 0.1d) + "bar";
            case 1:
                return String.valueOf(((double) data) * 0.5d) + "psi";
            case 2:
                return String.valueOf(data * 10) + "kPa";
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    public void ResetData(boolean check) {
        CanJni.PorscheOdGetCarTpmsSet(this.mTpmsData);
        if (i2b(this.mTpmsData.UpdateOnce) && (!check || i2b(this.mTpmsData.Update))) {
            this.mTpmsData.Update = 0;
            this.mTvTpms[0].setText(PreStr(this.mTpmsData.RealVal[0], this.mTpmsData.Dw));
            this.mTvTpms[1].setText(PreStr(this.mTpmsData.RealVal[1], this.mTpmsData.Dw));
            this.mTvTpms[2].setText(PreStr(this.mTpmsData.RealVal[2], this.mTpmsData.Dw));
            this.mTvTpms[3].setText(PreStr(this.mTpmsData.RealVal[3], this.mTpmsData.Dw));
        }
        CanJni.PorscheOdGetCarTpmsWarn(this.mTpmsWarn);
        if (!i2b(this.mTpmsWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmsWarn.Update)) {
            this.mTpmsWarn.Update = 0;
            updateWarnStr(0, this.mTpmsWarn.FlSta);
            updateWarnStr(1, this.mTpmsWarn.FrSta);
            updateWarnStr(2, this.mTpmsWarn.RlSta);
            updateWarnStr(3, this.mTpmsWarn.RrSta);
            if (this.mTpmsWarn.WarnType > 0) {
                this.mTvStatus.setText(this.mWarnsArray[this.mTpmsWarn.WarnType]);
                this.mTvStatus.setTextColor(SupportMenu.CATEGORY_MASK);
                return;
            }
            this.mTvStatus.setText("  ");
            this.mTvStatus.setTextColor(-1);
        }
    }

    private void updateWarnStr(int index, int warn) {
        if (warn > 0) {
            this.mIvTyres[index].setSelected(true);
        } else {
            this.mIvTyres[index].setSelected(false);
        }
    }

    public void QueryData() {
        CanJni.PorscheOdQuery(102, 0);
        Sleep(5);
        CanJni.PorscheOdQuery(104, 0);
    }
}
