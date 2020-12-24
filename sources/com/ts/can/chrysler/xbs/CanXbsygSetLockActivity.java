package com.ts.can.chrysler.xbs;

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

public class CanXbsygSetLockActivity extends CanXbsygBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CMBJ = 6;
    public static final int ITEM_DDWMBJ = 10;
    public static final int ITEM_JSKQCD = 8;
    private static final int ITEM_MAX = 13;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SCSFCSY = 11;
    public static final int ITEM_SCSFCTSY = 7;
    public static final int ITEM_SCYSJS = 4;
    public static final int ITEM_SCZXDSS = 3;
    public static final int ITEM_WYSJR = 5;
    public static final int ITEM_XCZDJS = 2;
    public static final int ITEM_YCJS = 13;
    public static final int ITEM_YCQDTSY = 9;
    public static final int ITEM_ZDCMSD = 1;
    public static final int ITEM_ZNYSGXHSZ = 12;
    public static final String TAG = "CanRZygSetLockActivity";
    private static final int[] mScsfctsyArr = {R.string.can_Scsfctsy_3, R.string.can_Scsfctsy_2, R.string.can_Scsfctsy_1};
    private static final int[] mScysjsArr = {R.string.can_jsym, R.string.can_sym};
    protected CanItemSwitchList mItemCmbj;
    protected CanItemSwitchList mItemDdwmbj;
    protected CanItemSwitchList mItemJskqcd;
    protected CanItemSwitchList mItemScsfcsy;
    protected CanItemPopupList mItemScsfctsy;
    protected CanItemPopupList mItemScysjs;
    protected CanItemSwitchList mItemSczxdss;
    protected CanItemSwitchList mItemWysjr;
    protected CanItemSwitchList mItemXczdjs;
    protected CanItemPopupList mItemYcjs;
    protected CanItemSwitchList mItemYcqdtsy;
    protected CanItemSwitchList mItemZdcmsd;
    protected CanItemSwitchList mItemZnysgxhsz;
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
        if (!i2b(this.mSetData.LockUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.LockUpdate)) {
            this.mSetData.LockUpdate = 0;
            this.mItemZdcmsd.SetCheck(this.mSetData.AutoLock);
            this.mItemXczdjs.SetCheck(this.mSetData.AutoUnlockExit);
            this.mItemSczxdss.SetCheck(this.mSetData.FlashLock);
            this.mItemWysjr.SetCheck(this.mSetData.PassiveEntry);
            this.mItemScysjs.SetSel(this.mSetData.RmtUnlock);
            this.mItemCmbj.SetCheck(this.mSetData.Cmbj);
            this.mItemScsfctsy.SetSel(this.mSetData.Scsfctsy);
            this.mItemJskqcd.SetCheck(this.mSetData.Jsckqcd);
            this.mItemYcqdtsy.SetCheck(this.mSetData.Ycqdtsy);
            this.mItemDdwmbj.SetCheck(this.mSetData.Ddwmbj);
            this.mItemScsfcsy.SetCheck(this.mSetData.LockBeep);
            this.mItemZnysgxhsz.SetCheck(this.mSetData.Znysgxhsz);
            this.mItemYcjs.SetSel(this.mSetData.RemoteDoorUnlck);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query2(138);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
        ResetData(false);
        QueryData();
        Log.d("CanRZygSetLockActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanRZygSetLockActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemZdcmsd = AddCheckItem(R.string.can_zmzdsd, 1);
        this.mItemXczdjs = AddCheckItem(R.string.can_xczdjs, 2);
        this.mItemSczxdss = AddCheckItem(R.string.can_scszxdss, 3);
        this.mItemWysjr = AddCheckItem(R.string.can_wysjr, 5);
        this.mItemCmbj = AddCheckItem(R.string.can_jp_cmbj, 6);
        this.mItemScysjs = AddPopupItem(R.string.can_sccysjs, mScysjsArr, 4);
        this.mItemScsfctsy = AddPopupItem(R.string.can_scsfctsy, mScsfctsyArr, 7);
        this.mItemJskqcd = AddCheckItem(R.string.can_zyx_jskqdg, 8);
        this.mItemYcqdtsy = AddCheckItem(R.string.can_jeep_znz_ycqdtsy, 9);
        this.mItemDdwmbj = AddCheckItem(R.string.can_ddwmbj, 10);
        this.mItemScsfcsy = AddCheckItem(R.string.can_scsfcsy, 11);
        this.mItemZnysgxhsz = AddCheckItem(R.string.can_znysgxhsz, 12);
        this.mItemYcjs = AddPopupItem(R.string.can_remotedoorunlock, mScysjsArr, 13);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 13; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.AutoLock;
                break;
            case 2:
                ret = this.mAdtData.AutoUnlockExit;
                break;
            case 3:
                ret = this.mAdtData.FlashLock;
                break;
            case 4:
                ret = this.mAdtData.RmtUnlock;
                break;
            case 5:
                ret = this.mAdtData.PassiveEntry;
                break;
            case 6:
                ret = this.mAdtData.Cmbj;
                break;
            case 7:
                ret = this.mAdtData.Scsfctsy;
                break;
            case 8:
                ret = this.mAdtData.Jsckqcd;
                break;
            case 9:
                ret = this.mAdtData.Ycqdtsy;
                break;
            case 10:
                ret = this.mAdtData.Ddwmbj;
                break;
            case 11:
                ret = this.mAdtData.LockBeep;
                break;
            case 12:
                ret = this.mAdtData.Znysgxhsz;
                break;
            case 13:
                ret = this.mAdtData.RemoteDoorUnlck;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemZdcmsd.ShowGone(show);
                return;
            case 2:
                this.mItemXczdjs.ShowGone(show);
                return;
            case 3:
                this.mItemSczxdss.ShowGone(show);
                return;
            case 4:
                this.mItemScysjs.ShowGone(show);
                return;
            case 5:
                this.mItemWysjr.ShowGone(show);
                return;
            case 6:
                this.mItemCmbj.ShowGone(show);
                return;
            case 7:
                this.mItemScsfctsy.ShowGone(show);
                return;
            case 8:
                this.mItemJskqcd.ShowGone(show);
                return;
            case 9:
                this.mItemYcqdtsy.ShowGone(show);
                return;
            case 10:
                this.mItemDdwmbj.ShowGone(show);
                return;
            case 11:
                this.mItemScsfcsy.ShowGone(show);
                return;
            case 12:
                this.mItemZnysgxhsz.ShowGone(show);
                return;
            case 13:
                this.mItemYcjs.ShowGone(show);
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
                CarSWSet(49, this.mSetData.AutoLock);
                return;
            case 2:
                CarSWSet(50, this.mSetData.AutoUnlockExit);
                return;
            case 3:
                CarSWSet(59, this.mSetData.FlashLock);
                return;
            case 5:
                CarSWSet(52, this.mSetData.PassiveEntry);
                return;
            case 6:
                CarSWSet(54, this.mSetData.Cmbj);
                return;
            case 8:
                CarSWSet(41, this.mSetData.Jsckqcd);
                return;
            case 9:
                CarSWSet(62, this.mSetData.Ycqdtsy);
                return;
            case 10:
                CarSWSet(55, this.mSetData.Ddwmbj);
                return;
            case 11:
                CarSWSet(56, this.mSetData.LockBeep);
                return;
            case 12:
                CarSWSet(53, this.mSetData.Znysgxhsz);
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
            case 4:
                CarSet(51, item);
                return;
            case 7:
                CarSet(60, item);
                return;
            case 13:
                CarSet(61, item);
                return;
            default:
                return;
        }
    }
}
