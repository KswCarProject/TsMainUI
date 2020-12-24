package com.ts.can.gm.comm;

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
import com.ts.canview.CanScrollList;

public class CanGMSetLockActivity extends CanGMBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CMJS = 7;
    public static final int ITEM_CXYKSZDKDM = 8;
    public static final int ITEM_FZKMZDLS = 1;
    public static final int ITEM_JCZDJS = 9;
    public static final int ITEM_JSDGFK = 5;
    public static final int ITEM_LCZDLS = 11;
    public static final int ITEM_LSYS = 2;
    private static final int ITEM_MAX = 14;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_QBZDLS = 3;
    public static final int ITEM_SMDGLBFK = 6;
    public static final int ITEM_YKCCKZ = 12;
    public static final int ITEM_YKQDCL = 14;
    public static final int ITEM_YKZSM = 13;
    public static final int ITEM_YSYWTX = 10;
    public static final int ITEM_ZCZDJS = 4;
    public static final String TAG = "CanGMCarInfoActivity";
    private static final int[] mCmjsArr = {R.string.can_jsym, R.string.can_sym};
    private static final int[] mJczdjsArr = {R.string.can_sym, R.string.can_jsym};
    private static final int[] mLczdlsArr = {R.string.can_off, R.string.can_on, R.string.can_lbwjsqy};
    private static final int[] mSmdglbfkArr = {R.string.can_only_light, R.string.can_dghlb, R.string.can_only_lb, R.string.can_off};
    private static final int[] mZczdjs2Arr = {R.string.can_off, R.string.can_sym};
    private static final int[] mZczdjsArr = {R.string.can_off, R.string.can_jsym, R.string.can_sym};
    private CanDataInfo.GM_AdtAutoLock mAdtLockData = new CanDataInfo.GM_AdtAutoLock();
    private CanDataInfo.GM_AdtRmtLock mAdtRmtLock = new CanDataInfo.GM_AdtRmtLock();
    private CanItemPopupList mItemCmjs;
    private CanItemSwitchList mItemCxykszdkdm;
    private CanItemSwitchList mItemFzkmzdls;
    private CanItemPopupList mItemJczdjs;
    private CanItemSwitchList mItemJsdgfk;
    private CanItemPopupList mItemLczdls;
    private CanItemSwitchList mItemLsys;
    private CanItemSwitchList mItemQbzdls;
    private CanItemPopupList mItemSmdglbfk;
    private CanItemSwitchList mItemYkcckz;
    private CanItemSwitchList mItemYkqdcl;
    private CanItemSwitchList mItemYkzsm;
    private CanItemSwitchList mItemYsywtx;
    private CanItemPopupList mItemZczdjs;
    private CanScrollList mManager;
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
        CanJni.GMGetAdtCarSet(this.mAdtLockData, this.mAdtRmtLock);
        if (i2b(this.mAdtLockData.UpdateOnce) && (!check || i2b(this.mAdtLockData.Update))) {
            this.mAdtLockData.Update = 0;
            LayoutUI();
            check = false;
            this.mbLayout = true;
        }
        if (i2b(this.mAdtRmtLock.UpdateOnce) && (!check || i2b(this.mAdtRmtLock.Update))) {
            this.mAdtRmtLock.Update = 0;
            LayoutUI();
        }
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemFzkmzdls.SetCheck(this.mSetData.FZKMZDLS);
            this.mItemLsys.SetCheck(this.mSetData.YSLS);
            this.mItemQbzdls.SetCheck(this.mSetData.QBZDLS);
            this.mItemZczdjs.SetSel(this.mSetData.ZCZDJS);
            this.mItemJsdgfk.SetCheck(this.mSetData.YKJSDGFK);
            this.mItemSmdglbfk.SetSel(this.mSetData.YKSMDGLBFK);
            this.mItemCmjs.SetSel(this.mSetData.YKJS);
            this.mItemCxykszdkdm.SetCheck(this.mSetData.AutoRelock);
            this.mItemJczdjs.SetSel(this.mSetData.JCJS);
            this.mItemYsywtx.SetCheck(this.mSetData.YSYWTX);
            this.mItemLczdls.SetSel(this.mSetData.LCZDLS);
            this.mItemYkcckz.SetCheck(this.mSetData.YKCCKZ);
            this.mItemYkzsm.SetCheck(this.mSetData.Ykzsm);
            this.mItemYkqdcl.SetCheck(this.mSetData.Ykqdcl);
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
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemFzkmzdls = AddCheckItem(R.string.can_fzkmzdll, 1);
        this.mItemLsys = AddCheckItem(R.string.can_ysll, 2);
        this.mItemQbzdls = AddCheckItem(R.string.can_qbzzll, 3);
        if (4 == CanJni.GetSubType()) {
            this.mItemZczdjs = AddPopupItem(R.string.can_zczdjs, mZczdjs2Arr, 4);
        } else {
            this.mItemZczdjs = AddPopupItem(R.string.can_zczdjs, mZczdjsArr, 4);
        }
        this.mItemJsdgfk = AddCheckItem(R.string.can_ykjsdgfk, 5);
        this.mItemSmdglbfk = AddPopupItem(R.string.can_ykdglbfk, mSmdglbfkArr, 6);
        this.mItemCmjs = AddPopupItem(R.string.can_ykjssz, mCmjsArr, 7);
        this.mItemCxykszdkdm = AddCheckItem(R.string.can_cxszdkdm, 8);
        this.mItemJczdjs = AddPopupItem(R.string.can_jczdjs, mJczdjsArr, 9);
        this.mItemYsywtx = AddCheckItem(R.string.can_ysywtx, 10);
        this.mItemLczdls = AddPopupItem(R.string.can_lczdls, mLczdlsArr, 11);
        this.mItemYkcckz = AddCheckItem(R.string.can_ykcckz, 12);
        this.mItemYkzsm = AddCheckItem(R.string.can_gl8_2017_ykzsm, 13);
        this.mItemYkqdcl = AddCheckItem(R.string.can_ykqdcl, 14);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 14; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtLockData.FZKMZDLS;
                break;
            case 2:
                ret = this.mAdtLockData.YSLS;
                break;
            case 3:
                ret = this.mAdtLockData.QBZDLS;
                break;
            case 4:
                ret = this.mAdtLockData.ZCZDJS;
                break;
            case 5:
                ret = this.mAdtRmtLock.YKJSDGFK;
                break;
            case 6:
                ret = this.mAdtRmtLock.YKSMDGLBFK;
                break;
            case 7:
                ret = this.mAdtRmtLock.YKJS;
                break;
            case 8:
                ret = this.mAdtRmtLock.AutoRelock;
                break;
            case 9:
                ret = this.mAdtRmtLock.JCJS;
                break;
            case 10:
                ret = this.mAdtRmtLock.YSYWTX;
                break;
            case 11:
                ret = this.mAdtRmtLock.LCZDLS;
                break;
            case 12:
                ret = this.mAdtRmtLock.YKCCKZ;
                break;
            case 13:
                ret = this.mAdtRmtLock.Ykzsm;
                break;
            case 14:
                ret = this.mAdtRmtLock.Ykqdcl;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemFzkmzdls.ShowGone(show);
                return;
            case 2:
                this.mItemLsys.ShowGone(show);
                return;
            case 3:
                this.mItemQbzdls.ShowGone(show);
                return;
            case 4:
                this.mItemZczdjs.ShowGone(show);
                return;
            case 5:
                this.mItemJsdgfk.ShowGone(show);
                return;
            case 6:
                this.mItemSmdglbfk.ShowGone(show);
                return;
            case 7:
                this.mItemCmjs.ShowGone(show);
                return;
            case 8:
                this.mItemCxykszdkdm.ShowGone(show);
                return;
            case 9:
                this.mItemJczdjs.ShowGone(show);
                return;
            case 10:
                this.mItemYsywtx.ShowGone(show);
                return;
            case 11:
                this.mItemLczdls.ShowGone(show);
                return;
            case 12:
                this.mItemYkcckz.ShowGone(show);
                return;
            case 13:
                this.mItemYkzsm.ShowGone(show);
                return;
            case 14:
                this.mItemYkqdcl.ShowGone(show);
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
            case 1:
                CarSet(2, Neg(this.mSetData.FZKMZDLS));
                return;
            case 2:
                CarSet(5, Neg(this.mSetData.YSLS));
                return;
            case 3:
                CarSet(3, Neg(this.mSetData.QBZDLS));
                return;
            case 5:
                CarSet(6, Neg(this.mSetData.YKJSDGFK));
                return;
            case 8:
                CarSet(15, Neg(this.mSetData.AutoRelock));
                return;
            case 10:
                CarSet(13, Neg(this.mSetData.YSYWTX));
                return;
            case 12:
                CarSet(27, Neg(this.mSetData.YKCCKZ));
                return;
            case 13:
                CarSet(10, Neg(this.mSetData.Ykzsm));
                return;
            case 14:
                CarSet(11, Neg(this.mSetData.Ykqdcl));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 4:
                CarSet(4, item);
                return;
            case 6:
                CarSet(7, item);
                return;
            case 7:
                CarSet(8, item);
                return;
            case 9:
                CarSet(12, item);
                return;
            case 11:
                CarSet(23, item);
                return;
            default:
                return;
        }
    }
}
