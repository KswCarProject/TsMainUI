package com.ts.can.mzd.cx4;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;

public class CanMzdCx4OtherSetupActivity extends CanMzdCx4BaseActivity {
    private static final int ITEM_CONTROL_VOICE = 1;
    private static final int ITEM_LEAV_HOME_LIGHT = 3;
    private static final int ITEM_SHACHE_SYSTEM = 0;
    private static final int ITEM_TRIP_A = 2;
    private CanItemPopupList mItemControlVoice;
    private CanItemSwitchList mItemLeavHomeLight;
    private CanItemSwitchList mItemShaChe;
    private CanItemSwitchList mItemTripA;
    private int[] mVoiceArray = {R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemShaChe = AddCheckItem(R.string.can_mzd_cx4_other_sc_system, 0);
        this.mItemControlVoice = AddPopupListItem(R.string.can_mzd_cx4_other_control_voice, this.mVoiceArray, 1);
        this.mItemTripA = AddCheckItem(R.string.can_mzd_cx4_other_sync_tripa, 2);
        this.mItemLeavHomeLight = AddCheckItem(R.string.can_leaving_func, 3);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        MzdCx4GetCarSetInfo();
        if (!i2b(this.mCarSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSetData.Update)) {
            this.mCarSetData.Update = 0;
            this.mItemShaChe.SetCheck(this.mCarSetData.zncsscxt);
            this.mItemControlVoice.SetSel(this.mCarSetData.mdjkyl);
            this.mItemTripA.SetCheck(this.mCarSetData.tbpjhlcA);
            this.mItemLeavHomeLight.SetCheck(this.mCarSetData.LeavHomeLight);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        MzdCx4CarQuery(9, 0);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                MzdCx4SWCarSet(20, this.mCarSetData.zncsscxt);
                return;
            case 2:
                MzdCx4SWCarSet(22, this.mCarSetData.tbpjhlcA);
                return;
            case 3:
                MzdCx4SWCarSet(48, this.mCarSetData.LeavHomeLight);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                MzdCx4CarSet(21, item);
                return;
            default:
                return;
        }
    }
}
