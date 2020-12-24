package com.ts.can.gm.comm;

import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanGMSetLanguageActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_LANGUAGE = 0;
    private int[] mLangArray = {R.string.lang_cn, R.string.lang_en_us, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_francais, R.string.lang_svenska, R.string.lang_espanol, R.string.lang_nederlands, R.string.lang_portugues, R.string.lang_norsk, R.string.lang_suomi, R.string.lang_dansk, R.string.lang_polski, R.string.lang_turkce, R.string.lang_arab, R.string.lang_pyccknn, R.string.lang_Korean};
    private CanScrollList mManager;

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mManager.addItemPopupList(R.string.can_lang_set, this.mLangArray, 0, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 16) {
                    CanJni.GMForceLang(17);
                    return;
                } else {
                    CanJni.GMLangCtrl(item);
                    return;
                }
            default:
                return;
        }
    }
}
