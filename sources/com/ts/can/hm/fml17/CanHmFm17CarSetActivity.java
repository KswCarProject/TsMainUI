package com.ts.can.hm.fml17;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanHmFm17CarSetActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    public static final int ITEM_BWHJ = 1;
    public static final int ITEM_BWHJKGSJ = 2;
    public static final int ITEM_CDPL = 3;
    public static final int ITEM_LANG = 4;
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanHmFm17CarSetActivity";
    private static final int[] mLangArr = {R.string.can_language_chinese, R.string.can_language_english};
    protected CanItemProgressList mBarBwhjkgsj;
    protected CanItemSwitchList mItemBwhj;
    protected CanItemSwitchList mItemCdpl;
    protected CanItemPopupList mItemLang;
    private CanScrollList mManager;
    protected CanDataInfo.HmS5Young_CarSet mSetData = new CanDataInfo.HmS5Young_CarSet();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public int SwSet(int val) {
        if (1 == val) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int NegSwSet(int val) {
        if (1 == val) {
            return 0;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HmS5YoungRzcGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemBwhj.SetCheck(this.mSetData.Bwhj);
            this.mBarBwhjkgsj.SetCurVal(this.mSetData.Bwhjkgsj);
            this.mBarBwhjkgsj.SetValText(String.valueOf(this.mSetData.Bwhjkgsj) + "s");
            this.mItemCdpl.SetCheck(this.mSetData.Cdpl);
            this.mItemLang.SetSel(this.mSetData.Lang);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.HmS5YoungRzcQuery(64);
        Sleep(10);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
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
        this.mItemBwhj = AddCheckItem(R.string.can_dgkz_bwhj, 1);
        this.mBarBwhjkgsj = new CanItemProgressList((Context) this, R.string.can_dgsjkz_bwhj);
        this.mBarBwhjkgsj.SetMinMax(0, 60);
        this.mBarBwhjkgsj.SetIdCallBack(2, this);
        this.mManager.AddView(this.mBarBwhjkgsj.GetView());
        this.mItemCdpl = AddCheckItem(R.string.can_jp_cdpljg, 3);
        this.mItemLang = AddPopupItem(R.string.can_lang_set, mLangArr, 4);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 4; i++) {
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
            case 2:
                ret = 1;
                break;
            case 3:
                ret = 1;
                break;
            case 4:
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
                this.mItemBwhj.ShowGone(show);
                return;
            case 2:
                this.mBarBwhjkgsj.ShowGone(show);
                return;
            case 3:
                this.mItemCdpl.ShowGone(show);
                return;
            case 4:
                this.mItemLang.ShowGone(show);
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
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.HmS5YoungRzcCarSet(1, (NegSwSet(this.mSetData.Bwhj) << 7) | (this.mSetData.Bwhjkgsj & 127));
                return;
            case 3:
                CanJni.HmS5YoungRzcCarSet(2, NegSwSet(this.mSetData.Cdpl));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 2) {
            CanJni.HmS5YoungRzcCarSet(1, (this.mSetData.Bwhj << 7) | pos);
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.HmS5YoungRzcCarSet(3, item);
                return;
            default:
                return;
        }
    }
}
