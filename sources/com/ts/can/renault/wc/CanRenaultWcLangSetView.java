package com.ts.can.renault.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanRenaultWcLangSetView extends CanScrollCarInfoView {
    protected int[] mLangIndex;

    public CanRenaultWcLangSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.RenaultWcLangCmd(1, this.mLangIndex[item]);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_language};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.lang_en_us, R.string.lang_cn, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_francais, R.string.lang_svenska, R.string.lang_espanol, R.string.lang_nederlands, R.string.lang_portugues, R.string.lang_norsk, R.string.lang_suomi, R.string.lang_dansk, R.string.lang_greek, R.string.lang_arab, R.string.lang_turkce, R.string.lang_pyccknn, R.string.lang_Romanian, R.string.lang_Ykpaihcbka, R.string.lang_polski, R.string.lang_Slovencina, R.string.lang_Cesky, R.string.lang_Magyar, R.string.lang_Serbian, R.string.lang_portugues_br, R.string.lang_Hrvatski, R.string.lang_Bulgarian, R.string.lang_Eestlane, R.string.lang_Vlaams, R.string.lang_Israel, R.string.lang_India, R.string.lang_Persian, R.string.lang_Latvija, R.string.lang_Lietuvis, R.string.lang_Slovensko, R.string.lang_espanol_la};
        this.mLangIndex = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 18, 20, 22, 23, 24, 25, 26, 28, 29, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
