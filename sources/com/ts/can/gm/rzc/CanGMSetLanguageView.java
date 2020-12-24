package com.ts.can.gm.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMSetLanguageView extends CanScrollCarInfoView {
    private static final int ITEM_LANGUAGE = 0;
    private static int[] mLangArray = {R.string.lang_cn, R.string.lang_en_us, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_francais, R.string.lang_svenska, R.string.lang_espanol, R.string.lang_nederlands, R.string.lang_portugues, R.string.lang_norsk, R.string.lang_suomi, R.string.lang_dansk, R.string.lang_polski, R.string.lang_turkce, R.string.lang_arab, R.string.lang_pyccknn, R.string.lang_Korean};

    public CanGMSetLanguageView(Activity activity) {
        super(activity, 1);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_lang_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = mLangArray;
    }

    public void onClick(View v) {
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.GMLangCtrl(item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }
}
