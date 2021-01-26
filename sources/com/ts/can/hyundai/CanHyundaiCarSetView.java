package com.ts.can.hyundai;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiCarSetView extends CanScrollCarInfoView {
    public CanHyundaiCarSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HyundaiXpCarSet(2, item + 1);
                return;
            case 1:
                CanJni.HyundaiXpCarTypeSet(item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
        int intValue = ((Integer) arg0.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_lang_set, R.string.can_car_type_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_language_chinese, R.string.can_language_english, R.string.lang_Korean, R.string.lang_pyccknn, R.string.lang_turkce, R.string.lang_francais, R.string.lang_deutsch, R.string.lang_espanol, R.string.lang_Cesky, R.string.lang_dansk, R.string.lang_itanliano, R.string.lang_Magyar, R.string.lang_nederlands, R.string.lang_norsk, R.string.lang_polski, R.string.lang_Slovencina, R.string.lang_svenska, R.string.lang_portugues};
        this.mPopValueIds[1] = new int[]{R.string.can_car_type1, R.string.can_car_type2, R.string.can_car_type3};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
