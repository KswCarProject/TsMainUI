package com.ts.can.nissan.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanNissanWcCarInfoView extends CanScrollCarInfoView {
    public CanNissanWcCarInfoView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        } else if (id != 2) {
        } else {
            if (item >= 17) {
                CanJni.NissanWcLangSet(1, item + 5);
            } else if (item >= 14) {
                CanJni.NissanWcLangSet(1, item + 2);
            } else {
                CanJni.NissanWcLangSet(1, item + 1);
            }
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub1Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_amp_set, R.string.can_lang_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_149};
        this.mPopValueIds[2] = new int[]{R.string.can_language_english, R.string.can_language_chinese, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_francais, R.string.lang_svenska, R.string.lang_espanol, R.string.lang_nederlands, R.string.lang_portugues, R.string.lang_Japanese, R.string.lang_norsk, R.string.lang_suomi, R.string.lang_dansk, R.string.lang_greek, R.string.lang_turkce, R.string.lang_Korean, R.string.lang_pyccknn, R.string.lang_Ykpaihcbka, R.string.lang_polski, R.string.lang_Slovencina, R.string.lang_cestina, R.string.lang_Magyar, R.string.lang_cn_ft};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }

    public void doOnResume() {
        updateItem(0, CanJni.GetSubType());
    }
}
