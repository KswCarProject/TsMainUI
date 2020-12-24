package com.ts.can.mzd.cx4;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;

public class CanMzdCx4CarLightActivity extends CanMzdCx4BaseActivity {
    private static final int ITEM_CLOSE_AUTO_OFF = 1;
    private static final int ITEM_LIGHT_AUTO_OPEN = 5;
    private static final int ITEM_LIGHT_OFF_NOTICER = 3;
    private static final int ITEM_LIGHT_OFF_TIMER = 4;
    private static final int ITEM_LIGHT_SYSTEM = 2;
    private static final int ITEM_OPEN_AUTO_OFF = 0;
    private int[] mClosedArray = {R.string.can_mzd_cx4_time_7_5s, R.string.can_mzd_cx4_time_15s, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s};
    private CanItemPopupList mClosedAutoOff;
    private int[] mLightArray = {R.string.can_mzd_cx4_light_dark, R.string.can_mzd_cx4_light_middle_dark, R.string.can_mzd_cx4_light_middle, R.string.can_mzd_cx4_light_middle_lighter, R.string.can_mzd_cx4_light_lighter};
    private CanItemPopupList mLightAutoOpen;
    private CanItemPopupList mLightNoticer;
    private CanItemSwitchList mLightSystem;
    private CanItemPopupList mLightTimer;
    private int[] mNoticerArray = {R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};
    private int[] mOpenedArray = {R.string.can_mzd_cx4_time_10min, R.string.can_mzd_cx4_time_30min, R.string.can_mzd_cx4_time_60min};
    private CanItemPopupList mOpenedAutoOff;
    private int[] mTimerArray = {R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_120s};

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mOpenedAutoOff = AddPopupListItem(R.string.can_mzd_cx4_light_open_auto_off, this.mOpenedArray, 0);
        this.mClosedAutoOff = AddPopupListItem(R.string.can_mzd_cx4_light_close_auto_off, this.mClosedArray, 1);
        this.mLightSystem = AddCheckItem(R.string.can_mzd_cx4_light_turn_system, 2);
        this.mLightNoticer = AddPopupListItem(R.string.can_mzd_cx4_light_off_noticer, this.mNoticerArray, 3);
        this.mLightTimer = AddPopupListItem(R.string.can_mzd_cx4_light_off_timer, this.mTimerArray, 4);
        this.mLightAutoOpen = AddPopupListItem(R.string.can_mzd_cx4_light_auto_open, this.mLightArray, 5);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        MzdCx4GetCarSetInfo();
        if (!i2b(this.mCarSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSetData.Update)) {
            this.mCarSetData.Update = 0;
            this.mOpenedAutoOff.SetSel(this.mCarSetData.cmdkscndzdxm);
            this.mClosedAutoOff.SetSel(this.mCarSetData.cmgbscndzdxm);
            this.mLightSystem.SetCheck(this.mCarSetData.zsyzxqzdxt);
            this.mLightNoticer.SetSel(this.mCarSetData.cdwgtsq);
            this.mLightTimer.SetSel(this.mCarSetData.ddgbdsq);
            this.mLightAutoOpen.SetSel(this.mCarSetData.zdddkq);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        MzdCx4CarQuery(9, 0);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                MzdCx4SWCarSet(10, this.mCarSetData.zsyzxqzdxt);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                MzdCx4CarSet(8, item);
                return;
            case 1:
                MzdCx4CarSet(9, item);
                return;
            case 3:
                MzdCx4CarSet(11, item);
                return;
            case 4:
                MzdCx4CarSet(12, item);
                return;
            case 5:
                MzdCx4CarSet(13, item);
                return;
            default:
                return;
        }
    }
}
