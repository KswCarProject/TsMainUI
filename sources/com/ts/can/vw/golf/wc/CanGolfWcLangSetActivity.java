package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanGolfWcLangSetActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick, UserCallBack {
    protected static final int ITEM_LANG_SET = 1;
    protected CanItemPopupList mItemLang;
    protected CanDataInfo.GolfData mLangData = new CanDataInfo.GolfData();
    protected int[] mLangVal = {R.string.lang_en_uk, R.string.lang_cn, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_francais, R.string.lang_svenska, R.string.lang_espanol, R.string.lang_nederlands, R.string.lang_portugues, R.string.lang_Japanese, R.string.lang_norsk, R.string.lang_suomi, R.string.lang_dansk, R.string.lang_greek, R.string.lang_arab, R.string.lang_turkce};

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
            CanJni.GolfWcLangSet(1, item + 1);
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
