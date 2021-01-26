package com.ts.can.renault.renault;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanRenaultCarSetView extends CanScrollCarInfoView {
    protected static final int ITEM_LANG = 0;
    protected static final int ITEM_MAX = 1;
    protected static final int ITEM_MIN = 0;
    private CanDataInfo.RenaulXpLang mLangData;

    public CanRenaultCarSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.RenaultCarSet(3, item);
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
        this.mItemTitleIds = new int[]{R.string.can_car_lang};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.lang_en_uk, R.string.lang_francais, R.string.lang_espanol, R.string.lang_pyccknn, R.string.lang_turkce, R.string.lang_portugues, R.string.lang_itanliano, R.string.lang_deutsch, R.string.lang_Israeli, R.string.lang_Japanese, R.string.lang_Korean, R.string.lang_cn, R.string.lang_Persian, R.string.lang_portugues_br, R.string.lang_Mexican, R.string.lang_greek, R.string.lang_polski, R.string.lang_Romanian, R.string.lang_norsk, R.string.lang_nederlands, R.string.lang_dansk, R.string.lang_cestina, R.string.lang_suomi};
        this.mLangData = new CanDataInfo.RenaulXpLang();
    }

    public void ResetData(boolean check) {
        CanJni.RenaultGetLangData(this.mLangData);
        if (!i2b(this.mLangData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLangData.Update)) {
            this.mLangData.Update = 0;
            updateItem(0, this.mLangData.Type);
        }
    }

    public void QueryData() {
        CanJni.RenaultQuery(51, 0);
    }
}
