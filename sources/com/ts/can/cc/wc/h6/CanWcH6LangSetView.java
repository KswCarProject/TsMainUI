package com.ts.can.cc.wc.h6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanWcH6LangSetView extends CanScrollCarInfoView {
    public CanWcH6LangSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.CcH6WcLangSet(1, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_lang_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_language_english, R.string.can_language_chinese};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
