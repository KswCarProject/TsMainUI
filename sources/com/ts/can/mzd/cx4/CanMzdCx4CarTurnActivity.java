package com.ts.can.mzd.cx4;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;

public class CanMzdCx4CarTurnActivity extends CanMzdCx4BaseActivity {
    private static final int ITEM_THREE_LIGHT = 0;
    private static final int ITEM_TURN_VOICE = 1;
    private CanItemSwitchList mThreeLight;
    private CanItemPopupList mTurnVoice;
    private int[] mVoiceArray = {R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mThreeLight = AddCheckItem(R.string.can_mzd_cx4_turn_three_light, 0);
        this.mTurnVoice = AddPopupListItem(R.string.can_mzd_cx4_turn_voice, this.mVoiceArray, 1);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        MzdCx4GetCarSetInfo();
        if (!i2b(this.mCarSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSetData.Update)) {
            this.mCarSetData.Update = 0;
            this.mThreeLight.SetCheck(this.mCarSetData.scsdzxxh);
            this.mTurnVoice.SetSel(this.mCarSetData.zxxhyl);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        MzdCx4CarQuery(9, 0);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                MzdCx4SWCarSet(6, this.mCarSetData.scsdzxxh);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                MzdCx4CarSet(7, item);
                return;
            default:
                return;
        }
    }
}
