package com.ts.can.qoros.bnr;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanQorosBnrCarAmpInfoActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_AUDIO_PILOT = 1;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 1;
    private static final int ITEM_ROOM_MODE = 2;
    private static int[] mAmpModeArray = {R.string.can_amp_all, R.string.can_amp_drive};
    private CanItemSwitchList mItemAudioPilot;
    private CanItemPopupList mItemRoomMode;
    private CanDataInfo.QorosBnrSet mSetData = new CanDataInfo.QorosBnrSet();

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        CanScrollList manager = new CanScrollList(this);
        this.mItemAudioPilot = manager.addItemCheckBox(R.string.can_amp_audiopilot, 1, this);
        this.mItemRoomMode = manager.addItemPopupList(R.string.can_amp_mode, mAmpModeArray, 2, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.QorosBnrGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemAudioPilot.SetCheck(this.mSetData.AudioPilot);
            this.mItemRoomMode.SetSel(this.mSetData.AudioMode);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 2; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = 1;
                break;
            case 2:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemAudioPilot.ShowGone(show);
                return;
            case 2:
                this.mItemRoomMode.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.QorosBnrQuery(50, 0);
        LayoutUI();
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 1) {
            CanJni.QorosBnrCarSet(83, Neg(this.mSetData.AudioPilot));
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.QorosBnrCarSet(84, item);
                return;
            default:
                return;
        }
    }
}
