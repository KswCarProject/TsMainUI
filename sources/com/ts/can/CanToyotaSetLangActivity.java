package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanToyotaSetLangActivity extends CanToyotaBaseActivity implements CanItemPopupList.onPopItemClick, UserCallBack {
    protected static final int ITEM_LANG_SET = 1;
    public static final String TAG = "CanToyotaSetLangActivity";
    protected static final int[] mLangIndex;
    protected static final int[] mLangRzcIndex;
    protected CanItemPopupList mItemLang;
    protected int[] mLangRzcVal = {R.string.lang_cn, R.string.lang_cn_ft, R.string.lang_en_uk, R.string.lang_deutsch, R.string.lang_francais, R.string.lang_espanol, R.string.lang_itanliano, R.string.lang_portugues, R.string.lang_nederlands, R.string.lang_svenska, R.string.lang_norsk, R.string.lang_dansk, R.string.lang_pyccknn, R.string.lang_suomi, R.string.lang_greek, R.string.lang_polski, R.string.lang_Ykpaihcbka, R.string.lang_turkce, R.string.lang_Magyar, R.string.lang_cestina, R.string.lang_Slovencina, R.string.lang_Romanian};
    protected int[] mLangVal = {R.string.lang_cn, R.string.lang_en_uk, R.string.lang_en_us, R.string.lang_espanol, R.string.lang_francais, R.string.lang_deutsch, R.string.lang_turkce, R.string.lang_en_uk, R.string.lang_pyccknn};

    static {
        int[] iArr = new int[9];
        iArr[1] = 1;
        iArr[2] = 2;
        iArr[3] = 3;
        iArr[4] = 4;
        iArr[5] = 6;
        iArr[6] = 7;
        iArr[7] = 8;
        iArr[8] = 9;
        mLangIndex = iArr;
        int[] iArr2 = new int[22];
        iArr2[1] = 1;
        iArr2[2] = 2;
        iArr2[3] = 3;
        iArr2[4] = 4;
        iArr2[5] = 5;
        iArr2[6] = 6;
        iArr2[7] = 7;
        iArr2[8] = 8;
        iArr2[9] = 9;
        iArr2[10] = 10;
        iArr2[11] = 11;
        iArr2[12] = 12;
        iArr2[13] = 13;
        iArr2[14] = 14;
        iArr2[15] = 15;
        iArr2[16] = 16;
        iArr2[17] = 17;
        iArr2[18] = 18;
        iArr2[19] = 19;
        iArr2[20] = 20;
        iArr2[21] = 21;
        mLangRzcIndex = iArr2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_list);
        if (CanJni.GetCanType() == 128) {
            this.mItemLang = new CanItemPopupList((Context) this, R.string.can_lang_set, this.mLangRzcVal, (CanItemPopupList.onPopItemClick) this);
        } else {
            this.mItemLang = new CanItemPopupList((Context) this, R.string.can_lang_set, this.mLangVal, (CanItemPopupList.onPopItemClick) this);
        }
        this.mItemLang.SetId(1);
        new CanScrollList(this).AddView(this.mItemLang.GetView());
    }

    public void onItem(int Id, int item) {
        if (Id != 1) {
            return;
        }
        if (CanJni.GetCanType() == 128) {
            CarSet(38, mLangRzcIndex[item]);
        } else {
            CarSet(36, mLangIndex[item]);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        if (CanJni.GetCanType() == 128) {
            GetSetData();
            if (i2b(this.mSetData.UpdateOnce)) {
                if (!check || i2b(this.mSetData.Update)) {
                    this.mSetData.Update = 0;
                }
                for (int i = 0; i < mLangRzcIndex.length; i++) {
                    if (this.mSetData.Lang == mLangRzcIndex[i]) {
                        this.mItemLang.SetSel(i);
                        return;
                    }
                }
            }
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
