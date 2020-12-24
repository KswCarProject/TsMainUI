package com.ts.can.lifan;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanScrollList;

public class CanLiFanCarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    private static final int ITEM_RADAR_STATUS = 1;
    private static final int ITEM_WARN_VOICE = 2;
    private CanItemCheckList mItemRadarStatus;
    private CanItemCheckList mItemWarnVoice;
    private CanScrollList mManager;
    private CanDataInfo.Lifan_RadarSta mRadarData = new CanDataInfo.Lifan_RadarSta();
    private CanDataInfo.Lifan_WarnVoice mVoiceData = new CanDataInfo.Lifan_WarnVoice();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitViews();
    }

    private void InitViews() {
        this.mManager = new CanScrollList(this);
        this.mItemRadarStatus = AddCheckItem(R.string.can_r_radar_sw, 1);
        this.mItemWarnVoice = AddCheckItem(R.string.can_warn_voice, 2);
    }

    private CanItemCheckList AddCheckItem(int text, int id) {
        CanItemCheckList item = new CanItemCheckList(this, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    private void ResetData(boolean check) {
        CanJni.LifanGetRadarSta(this.mRadarData);
        CanJni.LifanGetWarnVoice(this.mVoiceData);
        if (i2b(this.mRadarData.UpdateOnce) && (!check || i2b(this.mRadarData.Update))) {
            this.mRadarData.Update = 0;
            this.mItemRadarStatus.SetCheck(this.mRadarData.Sta);
        }
        if (!i2b(this.mVoiceData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mVoiceData.Update)) {
            this.mVoiceData.Update = 0;
            this.mItemWarnVoice.SetCheck(this.mVoiceData.Voice);
        }
    }

    public void onClick(View v) {
        int param = 0;
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                if (this.mRadarData.Sta == 0) {
                    param = 1;
                }
                CanJni.LifanSetRadarCtrl(param);
                return;
            case 2:
                if (this.mVoiceData.Voice == 0) {
                    param = 255;
                }
                CanJni.LifanSetWarnVoice(param);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
