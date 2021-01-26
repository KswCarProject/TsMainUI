package com.ts.can.gm.wc.enclave;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanGmEnclaveWcSetLanguageView extends CanScrollCarInfoView {
    public CanGmEnclaveWcSetLanguageView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.GmWcCarLangSet(1, item + 1);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 1) {
            new CanItemMsgBox(id, getActivity(), R.string.can_sure_setup, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.GmEnclaveWcCarFactorySet(0);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_lang, R.string.can_rw_rx5_hfccsz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.string.lang_en_uk, R.string.lang_cn, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_francais, R.string.lang_svenska, R.string.lang_espanol, R.string.lang_nederlands, R.string.lang_portugues, R.string.lang_Japanese, R.string.lang_norsk, R.string.lang_suomi, R.string.lang_dansk, R.string.lang_greek, R.string.lang_arab, R.string.lang_turkce, R.string.lang_Korean, R.string.lang_pyccknn, R.string.lang_Thai, R.string.lang_Romanian};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
