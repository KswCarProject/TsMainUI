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
    protected CanItemPopupList mItemLang;
    protected int[] mLangRzcVal = {R.string.lang_cn, R.string.lang_cn_ft, R.string.lang_en_uk};
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
            CarSet(38, mLangIndex[item]);
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
    }

    public void UserAll() {
        ResetData(true);
    }
}
