package com.ts.can.ford;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanFordLangSetActivity extends CanFordBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_LANGSET = 1;
    private static final int ITEM_MAX = 1;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanFordLangSetActivity";
    private static final int[] mLangArr = {R.string.lang_en_uk, R.string.lang_en_us, R.string.lang_deutsch, R.string.lang_itanliano, R.string.lang_francais, R.string.lang_france_us, R.string.lang_espanol, R.string.lang_espanol_us, R.string.lang_turkce, R.string.lang_pyccknn, R.string.lang_nederlands, R.string.can_car_null, R.string.lang_polski, R.string.can_car_null, R.string.can_car_null, R.string.can_car_null, R.string.lang_svenska, R.string.can_car_null, R.string.can_car_null, R.string.can_car_null, R.string.lang_portugues, R.string.lang_portugues_brasil, R.string.can_car_null, R.string.can_car_null, R.string.can_car_null, R.string.lang_cn, R.string.lang_cn_ft};
    private CanItemPopupList mItemLangSet;
    private CanDataInfo.FordCarLang mLangData = new CanDataInfo.FordCarLang();
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.FordGetLang(this.mLangData);
        if (!i2b(this.mLangData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLangData.Update)) {
            this.mLangData.Update = 0;
            this.mItemLangSet.SetSel(this.mLangData.Lang - 2);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.FordQuery(39, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemLangSet = AddPopupItem(R.string.can_car_lang, mLangArr, 1);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 1; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemLangSet.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        return btn;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                if (item != 11 && item != 13 && item != 14 && item != 15 && item != 17 && item != 18 && item != 19 && item != 22 && item != 23 && item != 24) {
                    CanJni.FordCarSet(164, item + 2);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
