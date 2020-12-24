package com.ts.can.mzd.cx4;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;

public class CanMzdCx4CarDoorActivity extends CanMzdCx4BaseActivity {
    private static final int ITEM_LEAVE_LOCK = 5;
    private static final int ITEM_LOCK_MODE = 1;
    private static final int ITEM_LOCK_TIME = 3;
    private static final int ITEM_LOCK_VOICE = 2;
    private static final int ITEM_UNLOCK_MODE = 4;
    private static final int ITEM_YUSHUA = 0;
    private CanItemSwitchList mLeaveLock;
    private CanItemPopupList mLockMode;
    private int[] mLockModeArray = {R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_lock_mode1, R.string.can_mzd_cx4_lock_mode2, R.string.can_mzd_cx4_lock_mode3, R.string.can_mzd_cx4_lock_mode4};
    private CanItemPopupList mLockTime;
    private CanItemPopupList mLockVoice;
    private int[] mTimeArray = {R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
    private int[] mUnlockArray = {R.string.can_mzd_cx4_unlock_all, R.string.can_mzd_cx4_unlock_driver};
    private CanItemPopupList mUnlockMode;
    private int[] mVoiceArray = {R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_middle, R.string.can_mzd_cx4_voice_high};
    private CanItemSwitchList mYuShua;

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mYuShua = AddCheckItem(R.string.can_mzd_cx4_door_yushua, 0);
        this.mLockVoice = AddPopupListItem(R.string.can_mzd_cx4_door_lock_voice, this.mVoiceArray, 2);
        this.mLockMode = AddPopupListItem(R.string.can_mzd_cx4_door_lock_mode, this.mLockModeArray, 1);
        this.mLockTime = AddPopupListItem(R.string.can_mzd_cx4_door_lock_time, this.mTimeArray, 3);
        this.mUnlockMode = AddPopupListItem(R.string.can_mzd_cx4_door_unlock_mode, this.mUnlockArray, 4);
        this.mLeaveLock = AddCheckItem(R.string.can_mzd_cx4_door_leave_lock, 5);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        MzdCx4GetCarSetInfo();
        if (!i2b(this.mCarSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSetData.Update)) {
            this.mCarSetData.Update = 0;
            this.mYuShua.SetCheck(this.mCarSetData.gysys);
            this.mLockMode.SetSel(this.mCarSetData.zdssms);
            this.mLockVoice.SetSel(this.mCarSetData.wyssctsy);
            this.mLockTime.SetSel(this.mCarSetData.zdcssj);
            this.mUnlockMode.SetSel(this.mCarSetData.jsms);
            this.mLeaveLock.SetCheck(this.mCarSetData.lccs);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        MzdCx4CarQuery(9, 0);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                MzdCx4CarSet(2, item);
                return;
            case 2:
                MzdCx4CarSet(1, item);
                return;
            case 3:
                MzdCx4CarSet(3, item);
                return;
            case 4:
                MzdCx4CarSet(4, item);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                MzdCx4SWCarSet(0, this.mCarSetData.gysys);
                return;
            case 5:
                MzdCx4SWCarSet(5, this.mCarSetData.lccs);
                return;
            default:
                return;
        }
    }
}
