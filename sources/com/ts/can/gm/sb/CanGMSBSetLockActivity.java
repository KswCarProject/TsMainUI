package com.ts.can.gm.sb;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGMSBSetLockActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    public static final int ITEM_CMJS = 7;
    public static final int ITEM_CXYKSZDKDM = 8;
    public static final int ITEM_FZKMZDLS = 1;
    public static final int ITEM_JCZDJS = 9;
    public static final int ITEM_JSDGFK = 5;
    public static final int ITEM_LCZDLS = 11;
    public static final int ITEM_LSYS = 2;
    private static final int ITEM_MAX = 15;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_QBZDLS = 3;
    public static final int ITEM_SMDGLBFK = 6;
    public static final int ITEM_YKCCKZ = 12;
    public static final int ITEM_YKQDCL = 15;
    public static final int ITEM_YKZSM = 13;
    public static final int ITEM_YSYWTX = 10;
    public static final int ITEM_ZCZDJS = 4;
    public static final int ITEM_ZDLS = 14;
    private static final int[] mCmjsArr = {R.string.can_jsym, R.string.can_sym};
    private static final int[] mJczdjsArr = {R.string.can_jsym, R.string.can_sym};
    private static final int[] mLczdlsArr = {R.string.can_off, R.string.can_on, R.string.can_dlbdk};
    private static final int[] mSmdglbfkArr = {R.string.can_only_light, R.string.can_dghlb, R.string.can_only_lb, R.string.can_off};
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
    private CanItemSwitchList mItemZdls;
    private CanScrollList mManager;
    private CanDataInfo.GmSb_CarSetAdtEx mSbAdtData = new CanDataInfo.GmSb_CarSetAdtEx();
    private CanDataInfo.GmSb_CarSetEx mSbSetData = new CanDataInfo.GmSb_CarSetEx();
    private CanDataInfo.GM_CarSet mSetData = new CanDataInfo.GM_CarSet();

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMCarCtrl(2, Neg(this.mSetData.FZKMZDLS));
                return;
            case 2:
                CanJni.GMCarCtrl(5, Neg(this.mSetData.YSLS));
                return;
            case 3:
                CanJni.GMCarCtrl(3, Neg(this.mSetData.QBZDLS));
                return;
            case 5:
                CanJni.GMCarCtrl(6, Neg(this.mSetData.YKJSDGFK));
                return;
            case 10:
                CanJni.GMCarCtrl(12, Neg(this.mSetData.YSYWTX));
                return;
            case 13:
                CanJni.GMCarCtrl(9, Neg(this.mSetData.Ykzsm));
                return;
            case 14:
                CanJni.GMCarCtrl(35, Neg(this.mSetData.Zdls));
                return;
            case 15:
                CanJni.GMCarCtrl(10, Neg(this.mSetData.Ykqdcl));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.GMCarCtrl(4, item);
                return;
            case 6:
                CanJni.GMCarCtrl(7, item);
                return;
            case 7:
                CanJni.GMCarCtrl(8, item);
                return;
            case 9:
                CanJni.GMCarCtrl(14, item);
                return;
            case 11:
                CanJni.GMCarCtrl(27, item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemFzkmzdls = this.mManager.addItemCheckBox(R.string.can_fzkmzdll, 1, this);
        this.mItemQbzdls = this.mManager.addItemCheckBox(R.string.can_qbzzll, 3, this);
        this.mItemZczdjs = this.mManager.addItemPopupList(R.string.can_zczdjs, mZczdjsArr, 4, (CanItemPopupList.onPopItemClick) this);
        this.mItemLsys = this.mManager.addItemCheckBox(R.string.can_ysll, 2, this);
        this.mItemJsdgfk = this.mManager.addItemCheckBox(R.string.can_ykjsdgfk, 5, this);
        this.mItemSmdglbfk = this.mManager.addItemPopupList(R.string.can_ykdglbfk, mSmdglbfkArr, 6, (CanItemPopupList.onPopItemClick) this);
        this.mItemCmjs = this.mManager.addItemPopupList(R.string.can_ykjssz, mCmjsArr, 7, (CanItemPopupList.onPopItemClick) this);
        this.mItemYsywtx = this.mManager.addItemCheckBox(R.string.can_ysywtx, 10, this);
        this.mItemYkzsm = this.mManager.addItemCheckBox(R.string.can_gl8_2017_ykzsm, 13, this);
        this.mItemJczdjs = this.mManager.addItemPopupList(R.string.can_jczdjs, mJczdjsArr, 9, (CanItemPopupList.onPopItemClick) this);
        this.mItemLczdls = this.mManager.addItemPopupList(R.string.can_lczdls, mLczdlsArr, 11, (CanItemPopupList.onPopItemClick) this);
        this.mItemZdls = this.mManager.addItemCheckBox(R.string.can_tigger7_auto_lock, 14, this);
        this.mItemYkqdcl = this.mManager.addItemCheckBox(R.string.can_ykqdcl, 15, this);
        this.mItemCxykszdkdm = this.mManager.addItemCheckBox(R.string.can_cxszdkdm, 8, this);
        this.mItemYkcckz = this.mManager.addItemCheckBox(R.string.can_ykcckz, 12, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GMGetCarSet(this.mSetData);
        CanJni.GMGetAdtCarSet(this.mAdtLockData, this.mAdtRmtLock);
        CanJni.GmSbGetCarSetEx(this.mSbSetData);
        CanJni.GmSbGetCarSetExAdt(this.mSbAdtData);
        if (i2b(this.mAdtLockData.UpdateOnce) && (!check || i2b(this.mAdtLockData.Update))) {
            this.mAdtLockData.Update = 0;
            LayoutUI();
        }
        if (i2b(this.mAdtRmtLock.UpdateOnce) && (!check || i2b(this.mAdtRmtLock.Update))) {
            this.mAdtRmtLock.Update = 0;
            LayoutUI();
        }
        if (i2b(this.mSbAdtData.UpdateOnce) && (!check || i2b(this.mSbAdtData.Update))) {
            this.mSbAdtData.Update = 0;
            LayoutUI();
        }
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
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
            this.mItemYkcckz.SetCheck(this.mSetData.YKCCKZ);
            this.mItemYkzsm.SetCheck(this.mSetData.Ykzsm);
            this.mItemZdls.SetCheck(this.mSetData.Zdls);
            this.mItemYkqdcl.SetCheck(this.mSetData.Ykqdcl);
        }
        if (!i2b(this.mSbSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSbSetData.Update)) {
            this.mSbSetData.Update = 0;
            this.mItemLczdls.SetSel(this.mSbSetData.Lczdls);
        }
    }

    private void LayoutUI() {
        for (int i = 1; i <= 15; i++) {
            ShowItem(i);
        }
    }

    private void ShowItem(int item) {
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
                this.mItemZdls.ShowGone(show);
                return;
            case 15:
                this.mItemYkqdcl.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private boolean IsHaveItem(int item) {
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
                ret = this.mSbAdtData.Lczdls;
                break;
            case 12:
                ret = this.mAdtRmtLock.YKCCKZ;
                break;
            case 13:
                ret = this.mAdtRmtLock.Ykzsm;
                break;
            case 14:
                ret = this.mAdtLockData.Zdls;
                break;
            case 15:
                ret = this.mAdtRmtLock.Ykqdcl;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
