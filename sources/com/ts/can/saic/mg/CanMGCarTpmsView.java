package com.ts.can.saic.mg;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;

public class CanMGCarTpmsView extends CanRelativeCarInfoView {
    protected CustomImgView[] mIvTyres;
    RelativeLayoutManager mManager;
    private CanDataInfo.MG_GS_TPMS mTpmsData;
    protected TextView[] mTvPress;
    protected TextView mTvStatus;
    protected TextView[] mTvTemp;
    protected TextView[] mTvWarn;

    public CanMGCarTpmsView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        this.mIvTyres = new CustomImgView[4];
        this.mTvPress = new TextView[4];
        this.mTvTemp = new TextView[4];
        this.mTvWarn = new TextView[4];
        this.mTpmsData = new CanDataInfo.MG_GS_TPMS();
        setBackgroundResource(R.drawable.can_rf_tpms_bg);
        if (MainSet.GetScreenType() == 5) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            initCommon();
            this.mManager.GetLayout().setScaleX(1.25f);
            this.mManager.GetLayout().setScaleY(0.78f);
            return;
        }
        initCommon();
    }

    private void initCommon() {
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
            this.mTvWarn[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 144, 281, 50);
            this.mTvWarn[i].setTextSize(0, 35.0f);
            this.mTvWarn[i].setTextColor(-1);
            this.mTvWarn[i].setGravity(17);
        }
    }

    public void ResetData(boolean check) {
        CanJni.MGGSGetTpmsData(this.mTpmsData);
        if (!i2b(this.mTpmsData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmsData.Update)) {
            this.mTpmsData.Update = 0;
            SetVal(0, this.mTpmsData.Val[0], this.mTpmsData.Temp[0], this.mTpmsData.Dw);
            SetVal(1, this.mTpmsData.Val[1], this.mTpmsData.Temp[1], this.mTpmsData.Dw);
            SetVal(2, this.mTpmsData.Val[2], this.mTpmsData.Temp[2], this.mTpmsData.Dw);
            SetVal(3, this.mTpmsData.Val[3], this.mTpmsData.Temp[3], this.mTpmsData.Dw);
            updateWarn(0, this.mTpmsData.Sta[0]);
            updateWarn(1, this.mTpmsData.Sta[1]);
            updateWarn(2, this.mTpmsData.Sta[2]);
            updateWarn(3, this.mTpmsData.Sta[3]);
        }
    }

    public void QueryData() {
        CanJni.MGGSQuery(82);
    }

    private void updateWarn(int index, int warn) {
        if (warn > 0) {
            this.mIvTyres[index].setSelected(true);
            this.mTvWarn[index].setText(R.string.can_ytylyc);
            this.mTvWarn[index].setTextColor(SupportMenu.CATEGORY_MASK);
            return;
        }
        this.mIvTyres[index].setSelected(false);
        this.mTvWarn[index].setText(R.string.can_ytylzc);
        this.mTvWarn[index].setTextColor(-1);
    }

    public String GetPressStr(int press, int dw) {
        if (press == 255) {
            return "-.-";
        }
        if (dw == 1) {
            return String.format("%.1f bar", new Object[]{Double.valueOf(((double) press) * 0.1d)});
        } else if (dw == 2) {
            return String.format("%d psi", new Object[]{Integer.valueOf(press)});
        } else {
            return String.format("%d kpa", new Object[]{Integer.valueOf(press * 10)});
        }
    }

    public String GetTempStr(int temp) {
        if (temp == 255) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 60)});
    }

    public void SetVal(int id, int press, int temp, int dw) {
        this.mTvPress[id].setText(GetPressStr(press, dw));
        this.mTvTemp[id].setText(GetTempStr(temp));
    }
}
