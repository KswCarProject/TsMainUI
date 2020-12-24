package com.ts.can.gac.trumpchi_wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanTrumpchiWcCarInfoView extends CanScrollCarInfoView {
    public CanTrumpchiWcCarInfoView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub1Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_lang_set, R.string.can_ac_set, R.string.can_chair_set, R.string.can_jsfz, R.string.can_attachment, R.string.can_light_setup, R.string.can_gc_nlsz, R.string.can_cdsz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        if (CanJni.GetSubType() != 5) {
            this.mItemVisibles[6] = 0;
            this.mItemVisibles[7] = 0;
        }
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
