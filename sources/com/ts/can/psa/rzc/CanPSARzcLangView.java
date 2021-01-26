package com.ts.can.psa.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanPSARzcLangView extends CanScrollCarInfoView {
    public static final int ITEM_TYPE = 0;
    private CanDataInfo.PsaRzcLang mSetData;

    public CanPSARzcLangView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.PSACarSet(11, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_language};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.lang_en_uk, R.string.lang_cn, R.string.lang_francais, R.string.lang_espanol, R.string.lang_portugues_br, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_portugues, R.string.lang_nederlands, R.string.lang_greek, R.string.lang_polski, R.string.lang_turkce, R.string.lang_pyccknn, R.string.lang_cestina, R.string.lang_Hrvatski, R.string.lang_Magyar, R.string.lang_arab, R.string.lang_dansk, R.string.lang_Bulgarian, R.string.lang_Eestlane, R.string.lang_suomi, R.string.lang_norsk, R.string.lang_Romanian, R.string.lang_Serbian, R.string.lang_svenska, R.string.lang_Ykpaihcbka, R.string.lang_Vietnam, R.string.lang_cn_ft, R.string.lang_Japanese, R.string.lang_Korean, R.string.lang_Israel};
        this.mSetData = new CanDataInfo.PsaRzcLang();
    }

    public void doOnResume() {
        super.doOnResume();
    }

    public void ResetData(boolean check) {
        CanJni.PsaRzcGetLangData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            updateItem(0, this.mSetData.Val);
        }
    }

    public void QueryData() {
    }
}
