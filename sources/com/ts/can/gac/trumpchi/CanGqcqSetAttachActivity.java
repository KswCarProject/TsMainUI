package com.ts.can.gac.trumpchi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGqcqSetAttachActivity extends CanGqcqBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CSSS = 2;
    public static final int ITEM_DDZDYG = 6;
    public static final int ITEM_HSJZD = 7;
    public static final int ITEM_JSBSTSY = 8;
    private static final int ITEM_MAX = 16;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_WHSJJDSDTJ = 11;
    public static final int ITEM_WHSJJDZDTJ1 = 12;
    public static final int ITEM_WHSJJDZDTJ2 = 13;
    public static final int ITEM_WXCD = 16;
    public static final int ITEM_XLXGYKQ = 14;
    public static final int ITEM_YGWH = 5;
    public static final int ITEM_YKJS = 1;
    public static final int ITEM_YKQCTC = 4;
    public static final int ITEM_ZDJS = 3;
    public static final int ITEM_ZDYGGN = 15;
    public static final int ITEM_ZNZDBS = 9;
    public static final int ITEM_ZNZDJS = 10;
    public static final String TAG = "CanGqcqSetAttachActivity";
    private CanItemPopupList mItemCsss;
    private CanItemPopupList mItemDdzdyg;
    private CanItemPopupList mItemHsjzd;
    private CanItemPopupList mItemJsbstsy;
    private CanItemPopupList mItemWhsjjdsdtj;
    private CanItemPopupList mItemWhsjjdzdtj_1;
    private CanItemPopupList mItemWhsjjdzdtj_2;
    private CanItemSwitchList mItemWxcd;
    private CanItemPopupList mItemYgwh;
    private CanItemPopupList mItemYkjs;
    private CanItemPopupList mItemYkqctc;
    private CanItemPopupList mItemZdjs;
    private CanItemPopupList mItemZdyggn;
    private CanItemPopupList mItemZnzdbs;
    private CanItemPopupList mItemZnzdjs;
    private CanItemPopupList mItemxlxgykq;
    private CanScrollList mManager;
    protected int[] mWhsjjdzdtjArr = {R.string.can_trumpchi_setup, R.string.can_trumpchi_determine, R.string.can_cancel};
    protected int[] mXlxgykqjArr = {R.string.can_off, R.string.can_jkq, R.string.can_tsyk};
    protected int[] mYkjsArr = {R.string.can_sym, R.string.can_jsym};
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemYkjs.SetSel(this.mSetData.YkUnlck - 1);
            this.mItemCsss.SetSel(this.mSetData.SpeedLock - 1);
            this.mItemZdjs.SetSel(this.mSetData.AutoUnlock - 1);
            this.mItemYkqctc.SetSel(this.mSetData.Ykzqctc - 1);
            this.mItemYgwh.SetSel(this.mSetData.Qygwh - 1);
            this.mItemDdzdyg.SetSel(this.mSetData.Hygdd - 1);
            this.mItemHsjzd.SetSel(this.mSetData.Hsjzd - 1);
            this.mItemZnzdbs.SetSel(this.mSetData.Znzdbs - 1);
            this.mItemZnzdjs.SetSel(this.mSetData.Znzdjs - 1);
            Log.d("HAHA", new StringBuilder().append(this.mSetData.Jsgstsy).toString());
            this.mItemJsbstsy.SetSel(this.mSetData.Jsgstsy - 1);
            this.mItemWhsjjdsdtj.SetSel(this.mSetData.Whsjjdsdtj - 1);
            if (this.mSetData.Whsjjdzdtj == 1 || this.mSetData.Whsjjdzdtj == 3 || this.mSetData.Whsjjdzdtj == 4 || this.mSetData.Whsjjdzdtj == 5) {
                this.mItemWhsjjdzdtj_1.SetSel(0);
                this.mItemWhsjjdzdtj_2.ShowGone(true);
            } else {
                this.mItemWhsjjdzdtj_1.SetSel(1);
                this.mItemWhsjjdzdtj_2.ShowGone(false);
            }
            this.mItemxlxgykq.SetSel(this.mSetData.Xlxgykq - 1);
            this.mItemZdyggn.SetSel(this.mSetData.Zdyggn - 1);
            this.mItemWxcd.SetCheck(this.mSetData.Wxcd - 1);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemYkjs = AddPopupItem(R.string.can_ykjs, this.mYkjsArr, 1);
        this.mItemCsss = AddPopupItem(R.string.can_csss, this.mSWArr, 2);
        this.mItemZdjs = AddPopupItem(R.string.can_zdjs, this.mSWArr, 3);
        this.mItemYkqctc = AddPopupItem(R.string.can_ykqctc, this.mSWArr, 4);
        this.mItemYgwh = AddPopupItem(R.string.can_ygwh, this.mSWArr, 5);
        this.mItemDdzdyg = AddPopupItem(R.string.can_ddzdhyg, this.mSWArr, 6);
        this.mItemHsjzd = AddPopupItem(R.string.can_hsjzdzd, this.mSWArr, 7);
        this.mItemJsbstsy = AddPopupItem(R.string.can_gqcq_gs4_jsbstsy, this.mSWArr, 8);
        this.mItemZnzdbs = AddPopupItem(R.string.can_gs8_znzdbs, this.mSWArr, 9);
        this.mItemZnzdjs = AddPopupItem(R.string.can_gs8_znzdjs, this.mSWArr, 10);
        this.mItemWhsjjdsdtj = AddPopupItem(R.string.can_trumpchi_whsjjdsdtj, this.mSWArr, 11);
        this.mItemWhsjjdzdtj_1 = AddPopupItem(R.string.can_trumpchi_whsjdjzdtj, this.mSWArr, 12);
        this.mItemWhsjjdzdtj_2 = AddPopupItem(R.string.can_trumpchi_whsjdjzdtj_2, this.mWhsjjdzdtjArr, 13);
        this.mItemxlxgykq = AddPopupItem(R.string.can_xlxgykq, this.mXlxgykqjArr, 14);
        this.mItemZdyggn = AddPopupItem(R.string.can_zdys, this.mSWArr, 15);
        this.mItemWxcd = AddCheckItem(R.string.can_wxcd, 16);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 16; i++) {
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
            case 5:
                ret = 1;
                break;
            case 6:
                ret = 1;
                break;
            case 7:
                ret = 1;
                break;
            case 8:
                ret = 1;
                break;
            case 9:
                ret = 1;
                break;
            case 10:
                ret = 1;
                break;
            case 11:
                ret = 1;
                break;
            case 12:
                ret = 1;
                break;
            case 13:
                ret = 0;
                break;
            case 14:
                ret = 1;
                break;
            case 15:
                ret = 1;
                break;
            case 16:
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
                this.mItemYkjs.ShowGone(show);
                return;
            case 2:
                this.mItemCsss.ShowGone(show);
                return;
            case 3:
                this.mItemZdjs.ShowGone(show);
                return;
            case 4:
                this.mItemYkqctc.ShowGone(show);
                return;
            case 5:
                this.mItemYgwh.ShowGone(show);
                return;
            case 6:
                this.mItemDdzdyg.ShowGone(show);
                return;
            case 7:
                this.mItemHsjzd.ShowGone(show);
                return;
            case 8:
                this.mItemJsbstsy.ShowGone(show);
                return;
            case 9:
                this.mItemZnzdbs.ShowGone(show);
                return;
            case 10:
                this.mItemZnzdjs.ShowGone(show);
                return;
            case 11:
                this.mItemWhsjjdsdtj.ShowGone(show);
                return;
            case 12:
                this.mItemWhsjjdzdtj_1.ShowGone(show);
                return;
            case 13:
                this.mItemWhsjjdzdtj_2.ShowGone(show);
                return;
            case 14:
                this.mItemxlxgykq.ShowGone(show);
                return;
            case 15:
                this.mItemZdyggn.ShowGone(show);
                return;
            case 16:
                this.mItemWxcd.ShowGone(show);
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

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 16:
                CarSet(44, Neg(this.mSetData.Wxcd - 1));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CarSet(12, item);
                return;
            case 2:
                CarSet(13, item);
                return;
            case 3:
                CarSet(14, item);
                return;
            case 4:
                CarSet(15, item);
                return;
            case 5:
                CarSet(16, item);
                return;
            case 6:
                CarSet(17, item);
                return;
            case 7:
                CarSet(27, item);
                return;
            case 8:
                CarSet(28, item);
                return;
            case 9:
                CarSet(29, item);
                return;
            case 10:
                CarSet(30, item);
                return;
            case 11:
                CarSet(31, item);
                return;
            case 12:
                CarSet(32, item);
                return;
            case 13:
                CarSet(32, item + 2);
                return;
            case 14:
                CarSet(39, item);
                return;
            case 15:
                CarSet(40, item);
                return;
            default:
                return;
        }
    }
}
