package com.ts.can.chery.xp.tiggo;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanCheryTiggoXpCarInfoView extends CanScrollCarInfoView {
    public static final int ITEM_LANG = 0;

    public CanCheryTiggoXpCarInfoView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 0) {
                    CanJni.CheryTiggoXpCarSet(164, 27);
                    return;
                } else {
                    CanJni.CheryTiggoXpCarSet(164, 2);
                    return;
                }
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_language};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.lang_cn, R.string.lang_en_uk};
    }

    public void doOnResume() {
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
