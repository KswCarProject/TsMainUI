package com.ts.can.hant.rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanRelativeCarInfoView;

public class CanHantElectCarWarnInfoView extends CanRelativeCarInfoView {
    private static String[] mWarnInfo = {"高踏板故障", "预充电故障", "过流", "控制器过热", "", "电流采样电路故障", "", "BMD故障", "电池组欠压", "电池组过压", "电机过热", "", "加速器故障"};
    private CanDataInfo.HanTang_Warn mWarnData;
    private TextView mWarnTxt;

    public CanHantElectCarWarnInfoView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mWarnData = new CanDataInfo.HanTang_Warn();
        this.mWarnTxt = addText(0, 222, 1024, 100);
        this.mWarnTxt.setGravity(17);
        this.mWarnTxt.setTextSize(0, 50.0f);
        this.mWarnTxt.setTextColor(-1);
    }

    public void ResetData(boolean check) {
        CanJni.HanTangElectCarGetWarnInfo(this.mWarnData);
        if (!i2b(this.mWarnData.UpdateOnce)) {
            return;
        }
        if ((!check || i2b(this.mWarnData.UpdateOnce)) && this.mWarnData.Info - 1 < mWarnInfo.length && this.mWarnData.Info != 0) {
            this.mWarnTxt.setText(mWarnInfo[this.mWarnData.Info - 1]);
        }
    }

    public void QueryData() {
        CanJni.HanTangElectCarQuery(36);
    }
}
