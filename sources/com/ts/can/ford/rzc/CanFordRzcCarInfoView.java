package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanFordRzcCarInfoView extends CanScrollCarInfoView {
    public static final int ITEM_CHARGE_MIX = 8;
    public static final int ITEM_DRIVE_ASSIST = 10;
    public static final int ITEM_LOCK_SET = 11;
    public static final int ITEM_OTHER_SET = 12;
    public static final int ITEM_ZNXF = 9;
    private CanDataInfo.FordAdt mAdtData;
    private CanDataInfo.FordRzcFwd mFwdData;
    private CanDataInfo.FordCarLang mLangData;
    private int nOldConfig = 255;

    public CanFordRzcCarInfoView(Activity activity) {
        super(activity, 13);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_car_config, R.string.can_car_lang, R.string.can_car_set, R.string.can_light_setup, R.string.can_amp_set, R.string.can_tmps, R.string.can_chair_set, R.string.can_hybrid_image, R.string.can_ford_zhinengxinfeng, R.string.can_aqhjsfz, R.string.can_lock_center, R.string.can_other_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mAdtData = new CanDataInfo.FordAdt();
        this.mLangData = new CanDataInfo.FordCarLang();
        this.mFwdData = new CanDataInfo.FordRzcFwd();
        CanJni.FordGetAdt(this.mAdtData);
        CanJni.FordRzcGetLightSet(this.mFwdData);
        this.mItemVisibles = new int[]{1, 1, 1, this.mAdtData.SetEnable, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        if (CanJni.GetSubType() != 10) {
            this.mItemVisibles[8] = 0;
        }
        if (CanJni.GetSubType() != 12) {
            this.mItemVisibles[9] = 0;
            this.mItemVisibles[10] = 0;
            this.mItemVisibles[11] = 0;
            this.mItemVisibles[12] = 0;
        }
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_146};
        this.mPopValueIds[1] = new int[]{R.string.can_car_low_config, R.string.can_car_mid_config, R.string.can_car_hi_config};
        this.mPopValueIds[2] = new int[]{R.string.lang_en_uk, R.string.lang_en_us, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_francais, R.string.lang_france_can, R.string.lang_espanol, R.string.lang_espanol_la, R.string.lang_turkce, R.string.lang_pyccknn, R.string.lang_nederlands, R.string.can_car_null, R.string.lang_polski, R.string.lang_cestina, R.string.can_car_null, R.string.can_car_null, R.string.lang_svenska, R.string.lang_dansk, R.string.lang_norsk, R.string.lang_suomi, R.string.lang_portugues, R.string.lang_portugues_br, R.string.can_car_null, R.string.lang_en_au, R.string.can_car_null, R.string.lang_cn, R.string.can_lang_tw, R.string.lang_arab, R.string.can_car_null, R.string.lang_Thai};
    }

    public void doOnResume() {
        updateItem(0, CanJni.GetSubType());
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        } else if (id == 1) {
            FtSet.Setyw2((FtSet.Getyw2() & 65520) + item);
        } else if (id == 2 && item != 11 && item != 14 && item != 15 && item != 22 && item != 24 && item != 28) {
            CanJni.FordCarSet(164, item + 2);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub1Activity.class, ((Integer) v.getTag()).intValue());
    }

    public void ResetData(boolean check) {
        CanJni.FordGetLang(this.mLangData);
        if (i2b(this.mLangData.UpdateOnce) && (!check || i2b(this.mLangData.Update))) {
            this.mLangData.Update = 0;
            updateItem(2, this.mLangData.Lang - 2);
        }
        CanJni.FordRzcGetLightSet(this.mFwdData);
        if (i2b(this.mFwdData.UpdateOnce) && (!check || i2b(this.mFwdData.Update))) {
            this.mFwdData.Update = 0;
            showItem(4, this.mFwdData.Adt);
        }
        if (this.nOldConfig != (FtSet.Getyw2() & 15)) {
            this.nOldConfig = FtSet.Getyw2() & 15;
            updateItem(1, this.nOldConfig);
        }
    }

    public void QueryData() {
        CanJni.FordQuery(39, 0);
        Sleep(5);
        CanJni.FordQuery(97, 0);
    }
}
