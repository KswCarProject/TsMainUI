package com.ts.can.toyota.wc;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanToyotaWCSetLangActivity extends CanToyotaWCBaseActivity implements CanItemPopupList.onPopItemClick, UserCallBack {
    protected static final int ITEM_LANG_SET = 1;
    public static final String TAG = "CanToyotaSetLangActivity";
    protected static final int[] mLangIndex = {1, 2, 3};
    protected CanItemPopupList mItemLang;
    protected int[] mLangVal = {R.string.lang_en_us, R.string.lang_cn, R.string.lang_cn_ft};

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_list);
        this.mItemLang = new CanItemPopupList((Context) this, R.string.can_lang_set, this.mLangVal, (CanItemPopupList.onPopItemClick) this);
        this.mItemLang.SetId(1);
        new CanScrollList(this).AddView(this.mItemLang.GetView());
    }

    public void onItem(int Id, int item) {
        if (Id == 1) {
            CanJni.ToyotaWcLangCmd(1, mLangIndex[item]);
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
