package com.ts.can.benc.wc.Metris;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBencMetrisWcCarSetView extends CanScrollCarInfoView {
    protected static final int ITEM_CDBCFZXT = 4;
    protected static final int ITEM_DJMS = 6;
    protected static final int ITEM_ESP = 0;
    protected static final int ITEM_FZCSB = 12;
    protected static final int ITEM_HJZM = 9;
    protected static final int ITEM_LANG = 14;
    protected static final int ITEM_LCDW = 13;
    protected static final int ITEM_MDFZXT = 2;
    protected static final int ITEM_NBZMYCGB = 11;
    protected static final int ITEM_SUXZ = 5;
    protected static final int ITEM_WBZMYCGB = 10;
    protected static final int ITEM_ZDCMSZ = 8;
    protected static final int ITEM_ZDZDFZXT = 1;
    protected static final int ITEM_ZYLFZXT = 3;
    protected static final int ITEM_ZYMSSYFK = 7;
    protected int[] mLangIndex;
    private CanDataInfo.CanBencWcSet mSetData;

    public CanBencMetrisWcCarSetView(Activity activity) {
        super(activity, 15);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.BencWcCarSet(2, item, 0, 0);
                return;
            case 3:
                CanJni.BencWcCarSet(4, item, 0, 0);
                return;
            case 5:
                CanJni.BencWcCarSet(17, item, 0, 0);
                return;
            case 10:
                CanJni.BencWcCarSet(34, item, 0, 0);
                return;
            case 13:
                CanJni.BencWcCarSet(50, item, 0, 0);
                return;
            case 14:
                CanJni.BencWcLangSet(1, this.mLangIndex[item]);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.BencWcCarSet(1, Neg(this.mSetData.ESP), 0, 0);
                return;
            case 2:
                CanJni.BencWcCarSet(3, Neg(this.mSetData.Mdfzxt), 0, 0);
                return;
            case 4:
                CanJni.BencWcCarSet(5, Neg(this.mSetData.Cdbcfzxt), 0, 0);
                return;
            case 6:
                CanJni.BencWcCarSet(18, Neg(this.mSetData.Djms), 0, 0);
                return;
            case 7:
                CanJni.BencWcCarSet(19, Neg(this.mSetData.Zymssyfk), 0, 0);
                return;
            case 8:
                CanJni.BencWcCarSet(20, Neg(this.mSetData.Zdcmsz), 0, 0);
                return;
            case 9:
                CanJni.BencWcCarSet(33, Neg(this.mSetData.Hjzm), 0, 0);
                return;
            case 11:
                CanJni.BencWcCarSet(35, Neg(this.mSetData.Nbzmycgb), 0, 0);
                return;
            case 12:
                CanJni.BencWcCarSet(49, Neg(this.mSetData.Fzcsb), 0, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_esp, R.string.can_zdzdfzxt, R.string.can_mdfzxt, R.string.can_zylfzxt, R.string.can_lane_assist, R.string.can_speed_limit, R.string.can_djms, R.string.can_zymssyfk, R.string.can_psa_wc_cmzdsd, R.string.can_environment_light, R.string.can_wbzmycgb, R.string.can_nbzmycgb, R.string.can_fzcsb, R.string.can_lcdw, R.string.can_lang_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_off, R.string.can_cdpyyzxt_2, R.string.can_cdpyyzxt_1, R.string.can_cdpyyzxt_4};
        this.mPopValueIds[3] = new int[]{R.string.can_off, R.string.can_cdpyyzxt_1, R.string.can_lingmin};
        this.mPopValueIds[5] = new int[]{R.string.can_off, R.string.can_120kmh, R.string.can_130kmh, R.string.can_140kmh, R.string.can_150kmh, R.string.can_160kmh, R.string.can_170kmh, R.string.can_180kmh};
        this.mPopValueIds[10] = new int[]{R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_time_45s, R.string.can_60s};
        this.mPopValueIds[13] = new int[]{R.string.can_service_distance_km, R.string.can_service_distance_mi};
        this.mPopValueIds[14] = new int[]{R.string.lang_en_uk, R.string.lang_cn, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_francais, R.string.lang_svenska, R.string.lang_espanol, R.string.lang_nederlands, R.string.lang_portugues, R.string.lang_norsk, R.string.lang_suomi, R.string.lang_dansk, R.string.lang_turkce, R.string.lang_Korean, R.string.lang_pyccknn, R.string.lang_Romanian, R.string.lang_Ykpaihcbka, R.string.lang_polski, R.string.lang_Slovencina, R.string.lang_cestina, R.string.lang_Magyar, R.string.lang_cn_ft, R.string.lang_Serbian, R.string.lang_Hrvatski, R.string.lang_Bulgarian};
        this.mLangIndex = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 16, 17, 18, 20, 22, 23, 24, 25, 26, 27, 28, 31, 32};
        this.mSetData = new CanDataInfo.CanBencWcSet();
    }

    public void ResetData(boolean check) {
        CanJni.BencWcGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.ESP);
            updateItem(1, this.mSetData.Zdzdfzxt);
            updateItem(2, this.mSetData.Mdfzxt);
            updateItem(3, this.mSetData.Zylfzxt);
            updateItem(4, this.mSetData.Cdbcfzxt);
            updateItem(5, this.mSetData.Sdxz);
            updateItem(6, this.mSetData.Djms);
            updateItem(7, this.mSetData.Zymssyfk);
            updateItem(8, this.mSetData.Zdcmsz);
            updateItem(9, this.mSetData.Hjzm);
            updateItem(10, this.mSetData.Wbzmycgb);
            updateItem(11, this.mSetData.Nbzmycgb);
            updateItem(12, this.mSetData.Fzcsb);
            updateItem(13, this.mSetData.Lcdw);
        }
    }

    public void QueryData() {
        CanJni.BencWcQuery(97, 0, 0);
    }
}
