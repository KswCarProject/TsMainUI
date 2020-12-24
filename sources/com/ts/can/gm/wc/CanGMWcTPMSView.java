package com.ts.can.gm.wc;

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

public class CanGMWcTPMSView extends CanRelativeCarInfoView {
    protected CustomImgView[] mIvTyres;
    private CanDataInfo.GmWc_TpmsData mTpmsData;
    protected TextView[] mTvTpms;
    protected TextView[] mTvWarns;
    private String[] mWarnsArray;

    public CanGMWcTPMSView(Activity activity) {
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
            this.mTvTpms[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 60, 281, 50);
            this.mTvTpms[i].setTextSize(0, 35.0f);
            this.mTvTpms[i].setGravity(17);
            this.mTvTpms[i].setTextColor(-1);
            this.mTvWarns[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 120, 281, 50);
            this.mTvWarns[i].setTextSize(0, 32.0f);
            this.mTvWarns[i].setGravity(17);
            this.mTvWarns[i].setTextColor(-1);
        }
        this.mTpmsData = new CanDataInfo.GmWc_TpmsData();
        this.mWarnsArray = getActivity().getResources().getStringArray(R.array.can_tpms_warn_array);
    }

    public void ResetData(boolean check) {
        CanJni.GmWcGetCarTpmsInfo(this.mTpmsData);
        if (!i2b(this.mTpmsData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmsData.Update)) {
            this.mTpmsData.Update = 0;
            if (i2b(this.mTpmsData.Vaild)) {
                int[] tpms = this.mTpmsData.Prs;
                for (int i = 0; i < this.mTvTpms.length; i++) {
                    this.mTvTpms[i].setText(String.valueOf(tpms[i]) + " kpa");
                }
                updateWarnStr(0, this.mTpmsData.FlWarn);
                updateWarnStr(1, this.mTpmsData.FrWarn);
                updateWarnStr(2, this.mTpmsData.RlWarn);
                updateWarnStr(3, this.mTpmsData.RrWarn);
            }
        }
    }

    private void updateWarnStr(int index, int[] warns) {
        String str = "";
        for (int i = 0; i < warns.length; i++) {
            if (i2b(warns[i])) {
                str = this.mWarnsArray[i];
            }
        }
        if ("".equals(str)) {
            str = this.mWarnsArray[3];
            this.mTvWarns[index].setTextColor(-1);
            this.mIvTyres[index].setSelected(false);
        } else {
            this.mTvWarns[index].setTextColor(SupportMenu.CATEGORY_MASK);
            this.mIvTyres[index].setSelected(true);
        }
        this.mTvWarns[index].setText(str);
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 104);
    }
}
