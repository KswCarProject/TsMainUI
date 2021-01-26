package com.ts.can.obd.dst;

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

public class CanSciDstTPMSView extends CanRelativeCarInfoView {
    protected CustomImgView[] mIvTyres;
    private CanDataInfo.SciDst_CarData mTpmsData;
    protected TextView[] mTvTpms;
    protected TextView[] mTvWarns;
    private String[] mWarnsArray;

    public CanSciDstTPMSView(Activity activity) {
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
        this.mTpmsData = new CanDataInfo.SciDst_CarData();
        this.mWarnsArray = new String[]{"传感器失效", "胎压低", "胎压高", "胎压正常"};
    }

    public void ResetData(boolean check) {
        CanJni.ObdDstGetCarData(this.mTpmsData);
        if (!i2b(this.mTpmsData.UpdateOnce6)) {
            return;
        }
        if (!check || i2b(this.mTpmsData.Update6)) {
            this.mTpmsData.Update6 = 0;
            this.mTvTpms[0].setText(String.format("%.1fBar", new Object[]{Float.valueOf(((float) this.mTpmsData.LfPre) * 0.1f)}));
            this.mTvTpms[1].setText(String.format("%.1fBar", new Object[]{Float.valueOf(((float) this.mTpmsData.RfPre) * 0.1f)}));
            this.mTvTpms[2].setText(String.format("%.1fBar", new Object[]{Float.valueOf(((float) this.mTpmsData.LrPre) * 0.1f)}));
            this.mTvTpms[3].setText(String.format("%.1fBar", new Object[]{Float.valueOf(((float) this.mTpmsData.RrPre) * 0.1f)}));
            updateWarnStr(0, getWarnString(0, this.mTpmsData.PreWarn));
            updateWarnStr(1, getWarnString(1, this.mTpmsData.PreWarn));
            updateWarnStr(2, getWarnString(2, this.mTpmsData.PreWarn));
            updateWarnStr(3, getWarnString(3, this.mTpmsData.PreWarn));
        }
    }

    private String getWarnString(int id, int[] preWarn) {
        switch (id) {
            case 0:
                return this.mWarnsArray[getInt(0, preWarn[0])];
            case 1:
                return this.mWarnsArray[getInt(1, preWarn[0])];
            case 2:
                return this.mWarnsArray[getInt(0, preWarn[1])];
            case 3:
                return this.mWarnsArray[getInt(1, preWarn[1])];
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    private int getInt(int front, int Data) {
        int warnData = 3;
        if (front == 0) {
            if ((Data & 128) != 0) {
                warnData = 0;
            }
            if ((Data & 64) != 0) {
                warnData = 1;
            }
            if ((Data & 32) != 0) {
                warnData = 2;
            }
            if ((Data & 16) != 0) {
                return 3;
            }
            return warnData;
        } else if (front != 1) {
            return 3;
        } else {
            if ((Data & 8) != 0) {
                warnData = 0;
            }
            if ((Data & 4) != 0) {
                warnData = 1;
            }
            if ((Data & 2) != 0) {
                warnData = 2;
            }
            if ((Data & 1) != 0) {
                return 3;
            }
            return warnData;
        }
    }

    private void updateWarnStr(int index, String str) {
        if ("胎压正常".equals(str)) {
            this.mTvWarns[index].setTextColor(-1);
            this.mIvTyres[index].setSelected(false);
        } else {
            this.mTvWarns[index].setTextColor(SupportMenu.CATEGORY_MASK);
            this.mIvTyres[index].setSelected(true);
        }
        this.mTvWarns[index].setText(str);
    }

    public void QueryData() {
    }
}
