package com.ts.can.ford.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordWcLangSetView extends CanScrollCarInfoView {
    private CanDataInfo.FordWcLang mLangData;

    public CanFordWcLangSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 2) {
                    item = 26;
                }
                CanJni.FordWcLangSet(1, item + 1);
                return;
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
        this.mItemTitleIds = new int[]{R.string.can_lang_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.lang_en_uk, R.string.lang_cn, R.string.lang_cn_ft};
        this.mLangData = new CanDataInfo.FordWcLang();
    }

    public void ResetData(boolean check) {
        CanJni.FordWcGetLang(this.mLangData);
        if (!i2b(this.mLangData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLangData.Update)) {
            this.mLangData.Update = 0;
            int Lang = this.mLangData.Lang;
            if (Lang == 27) {
                Lang = 3;
            }
            updateItem(0, Lang - 1);
        }
    }

    public void QueryData() {
        CanJni.FordWcQuery(5, 1, 148);
    }
}
