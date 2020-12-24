package com.ts.can.fiat.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFiatWcLangSetInfoView extends CanScrollCarInfoView {
    private CanDataInfo.FiatAllWcLang mLangData;

    public CanFiatWcLangSetInfoView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                switch (item) {
                    case 0:
                        CanJni.FiatAllWcLangSet(1);
                        return;
                    case 1:
                        CanJni.FiatAllWcLangSet(3);
                        return;
                    case 2:
                        CanJni.FiatAllWcLangSet(4);
                        return;
                    case 3:
                        CanJni.FiatAllWcLangSet(5);
                        return;
                    case 4:
                        CanJni.FiatAllWcLangSet(7);
                        return;
                    case 5:
                        CanJni.FiatAllWcLangSet(8);
                        return;
                    case 6:
                        CanJni.FiatAllWcLangSet(9);
                        return;
                    case 7:
                        CanJni.FiatAllWcLangSet(16);
                        return;
                    case 8:
                        CanJni.FiatAllWcLangSet(21);
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_psa_wc_lang_setup};
        this.mPopValueIds[0] = new int[]{R.string.lang_en_us, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_francais, R.string.lang_espanol, R.string.lang_nederlands, R.string.lang_portugues, R.string.lang_turkce, R.string.lang_polski};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mLangData = new CanDataInfo.FiatAllWcLang();
    }

    public void ResetData(boolean check) {
        CanJni.FiatAllWcGetLang(this.mLangData);
        if (!i2b(this.mLangData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLangData.Update)) {
            this.mLangData.Update = 0;
            switch (this.mLangData.Lang) {
                case 1:
                    updateItem(0, 0);
                    return;
                case 3:
                    updateItem(0, 1);
                    return;
                case 4:
                    updateItem(0, 2);
                    return;
                case 5:
                    updateItem(0, 3);
                    return;
                case 7:
                    updateItem(0, 4);
                    return;
                case 8:
                    updateItem(0, 5);
                    return;
                case 9:
                    updateItem(0, 6);
                    return;
                case 16:
                    updateItem(0, 7);
                    return;
                case 21:
                    updateItem(0, 8);
                    return;
                default:
                    return;
            }
        }
    }

    public void QueryData() {
    }
}
