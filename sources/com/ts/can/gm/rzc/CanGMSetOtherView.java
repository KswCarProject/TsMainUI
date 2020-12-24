package com.ts.can.gm.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanGMSetOtherView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    private static final int ITEM_MAX = 1;
    private static final int ITEM_MIN = 0;
    public static final int ITEM_RADAR_SW = 1;
    public static final int ITEM_WARN_VOICE = 0;
    public static final String TAG = "CanGMSetOtherActivity";
    private CanDataInfo.GM_Radar mRadarData = new CanDataInfo.GM_Radar();
    private CanDataInfo.GM_WarnVoice mVoiceData = new CanDataInfo.GM_WarnVoice();
    private boolean mbLayout;

    public CanGMSetOtherView(Activity activity) {
        super(activity, 2);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_warn_voice, R.string.can_r_radar_sw};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.CHECK};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 15;
        iArr2[2] = 1;
        iArr[0] = iArr2;
    }

    public void ResetData(boolean check) {
        CanJni.GMGetCarRadar(this.mRadarData);
        CanJni.GMGetWarnVoice(this.mVoiceData);
        if (i2b(this.mRadarData.UpdateOnce) && (!check || i2b(this.mRadarData.Update))) {
            this.mRadarData.Update = 0;
            updateItem(1, this.mRadarData.fgOn);
        }
        if (!i2b(this.mVoiceData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mVoiceData.Update)) {
            this.mVoiceData.Update = 0;
            updateItem(0, this.mVoiceData.Voice);
        }
    }

    public void QueryData() {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMRadarCtrl(Neg(this.mRadarData.fgOn));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 0) {
            CanJni.GMWarnVoiceCtrl(pos);
        }
    }

    public void onOK(int param) {
        CanJni.GMCarCtrl(128, 1);
    }

    public void onItem(int id, int item) {
    }
}
