package com.ts.can.chana.cs75;

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
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanCs75CarDoorSetActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CCDGNSZ = 7;
    public static final int ITEM_CCYKGNSZ = 8;
    public static final int ITEM_CCYSSJSZ = 6;
    public static final int ITEM_DLBSYSZ = 9;
    public static final int ITEM_ECLS = 14;
    public static final int ITEM_JBSFKSSSZ = 10;
    private static final int ITEM_MAX = 14;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SKTC = 11;
    public static final int ITEM_XCZDLS = 2;
    public static final int ITEM_XHZDLS = 3;
    public static final int ITEM_YKJS = 1;
    public static final int ITEM_YKTC = 12;
    public static final int ITEM_YLGTCSZ = 4;
    public static final int ITEM_YLGYTC = 13;
    public static final int ITEM_ZCZDJS = 5;
    public static final String TAG = "CanCs75CarDoorSetActivity";
    private static final int[] mCcycsjszArr = {R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_120s};
    private static final int[] mDlbsyszArr = {R.string.can_xchmwgbs, R.string.can_xunche, R.string.can_mwgbs};
    private static final int[] mJbsfkfsArr = {R.string.can_dghlb, R.string.can_only_light, R.string.can_only_lb};
    private static final int[] mYkjsArr = {R.string.can_remotedoor_all, R.string.can_remotedoor_driver};
    protected CanItemSwitchList mItemCcdgnsz;
    protected CanItemPopupList mItemCcycsjsz;
    protected CanItemSwitchList mItemCcykgnsz;
    protected CanItemPopupList mItemDlbsysz;
    protected CanItemSwitchList mItemEcls;
    protected CanItemPopupList mItemJbsfksssz;
    protected CanItemSwitchList mItemSKTCsz;
    protected CanItemSwitchList mItemXczdls;
    protected CanItemSwitchList mItemXhzdls;
    protected CanItemSwitchList mItemYKTCsz;
    protected CanItemSwitchList mItemYLGYTCsz;
    protected CanItemPopupList mItemYkjs;
    protected CanItemSwitchList mItemYlgtcsz;
    protected CanItemSwitchList mItemZczdjs;
    private CanScrollList mManager;
    protected CanDataInfo.CS75CarInfo mSetData = new CanDataInfo.CS75CarInfo();
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
            return 2;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.Cs75GetCarInfo(this.mSetData);
        if (i2b(this.mSetData.YkjsUpdateOnce) && (!check || i2b(this.mSetData.YkjsUpdate))) {
            this.mSetData.YkjsUpdate = 0;
            this.mItemYkjs.SetSel(SwSet(this.mSetData.Ykjs));
        }
        if (i2b(this.mSetData.XczdlsUpdateOnce) && (!check || i2b(this.mSetData.XczdlsUpdate))) {
            this.mSetData.XczdlsUpdate = 0;
            this.mItemXczdls.SetCheck(SwSet(this.mSetData.Xczdls));
        }
        if (i2b(this.mSetData.XhzdlsUpdateOnce) && (!check || i2b(this.mSetData.XhzdlsUpdate))) {
            this.mSetData.XhzdlsUpdate = 0;
            this.mItemXhzdls.SetCheck(SwSet(this.mSetData.Xhzdls));
        }
        if (i2b(this.mSetData.YlgtcszUpdateOnce) && (!check || i2b(this.mSetData.YlgtcszUpdate))) {
            this.mSetData.YlgtcszUpdate = 0;
            this.mItemYlgtcsz.SetCheck(SwSet(this.mSetData.Ylgtcsz));
        }
        if (i2b(this.mSetData.ZczdjsUpdateOnce) && (!check || i2b(this.mSetData.ZczdjsUpdate))) {
            this.mSetData.ZczdjsUpdate = 0;
            this.mItemZczdjs.SetCheck(SwSet(this.mSetData.Zczdjs));
        }
        if (i2b(this.mSetData.CcyssjszUpdateOnce) && (!check || i2b(this.mSetData.CcyssjszUpdate))) {
            this.mSetData.CcyssjszUpdate = 0;
            this.mItemCcycsjsz.SetSel(this.mSetData.Ccyssjsz);
        }
        if (i2b(this.mSetData.CcdgnszUpdateOnce) && (!check || i2b(this.mSetData.CcdgnszUpdate))) {
            this.mSetData.CcdgnszUpdate = 0;
            this.mItemCcdgnsz.SetCheck(SwSet(this.mSetData.Ccdgnsz));
        }
        if (i2b(this.mSetData.CcykgnszUpdateOnce) && (!check || i2b(this.mSetData.CcykgnszUpdate))) {
            this.mSetData.CcykgnszUpdate = 0;
            this.mItemCcykgnsz.SetCheck(SwSet(this.mSetData.Ccykgnsz));
        }
        if (i2b(this.mSetData.DlbsyUpdateOnce) && (!check || i2b(this.mSetData.DlbsyUpdate))) {
            this.mSetData.DlbsyUpdate = 0;
            this.mItemDlbsysz.SetSel(this.mSetData.Dlbsy - 1);
        }
        if (i2b(this.mSetData.JbsfkfsszUpdateOnce) && (!check || i2b(this.mSetData.JbsfkfsszUpdate))) {
            this.mSetData.JbsfkfsszUpdate = 0;
            this.mItemJbsfksssz.SetSel(this.mSetData.Jbsfkfssz - 1);
        }
        if (i2b(this.mSetData.SktcUpdateOnce) && (!check || i2b(this.mSetData.SktcUpdate))) {
            this.mSetData.SktcUpdate = 0;
            this.mItemSKTCsz.SetCheck(SwSet(this.mSetData.Sktc));
        }
        if (i2b(this.mSetData.YktcUpdateOnce) && (!check || i2b(this.mSetData.YktcUpdate))) {
            this.mSetData.YktcUpdate = 0;
            this.mItemYKTCsz.SetCheck(SwSet(this.mSetData.Yktc));
        }
        if (i2b(this.mSetData.YlgytcUpdateOnce) && (!check || i2b(this.mSetData.YlgytcUpdate))) {
            this.mSetData.YlgytcUpdate = 0;
            this.mItemYLGYTCsz.SetCheck(SwSet(this.mSetData.Ylgytc));
        }
        if (!i2b(this.mSetData.EclsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.EclsUpdate)) {
            this.mSetData.EclsUpdate = 0;
            this.mItemEcls.SetCheck(SwSet(this.mSetData.Ecls));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.Cs75CarQuery(82, 3);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 4);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 5);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 6);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 16);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 18);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 19);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 20);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 21);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 22);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 59);
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
        this.mItemYkjs = AddPopupItem(R.string.can_ykjs, mYkjsArr, 1);
        this.mItemXczdls = AddCheckItem(R.string.can_xczdls, 2);
        this.mItemXhzdls = AddCheckItem(R.string.can_xhzdls, 3);
        this.mItemYlgtcsz = AddCheckItem(R.string.can_ylgtcsz, 4);
        this.mItemZczdjs = AddCheckItem(R.string.can_zczdjs, 5);
        this.mItemCcycsjsz = AddPopupItem(R.string.can_wind_delay, mCcycsjszArr, 6);
        this.mItemCcdgnsz = AddCheckItem(R.string.can_sidewind_light, 7);
        this.mItemCcykgnsz = AddCheckItem(R.string.can_wind_remote, 8);
        this.mItemDlbsysz = AddPopupItem(R.string.can_la_audio_set, mDlbsyszArr, 9);
        this.mItemJbsfksssz = AddPopupItem(R.string.can_lock_feedback, mJbsfkfsArr, 10);
        this.mItemSKTCsz = AddCheckItem(R.string.can_sktc, 11);
        this.mItemYKTCsz = AddCheckItem(R.string.can_yktc, 12);
        this.mItemYLGYTCsz = AddCheckItem(R.string.can_ylgytc, 13);
        this.mItemEcls = AddCheckItem(R.string.can_carset_ecls, 14);
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
                if (CanJni.GetSubType() != 11) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 2:
                ret = 1;
                break;
            case 3:
                ret = 1;
                break;
            case 4:
                if (CanJni.GetSubType() != 5 && CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 5:
                ret = 1;
                break;
            case 6:
                if (CanJni.GetSubType() == 5 || CanJni.GetSubType() == 11) {
                    ret = 1;
                    break;
                }
            case 7:
                if (CanJni.GetSubType() == 5) {
                    ret = 1;
                    break;
                }
                break;
            case 8:
                if (CanJni.GetSubType() == 5 || CanJni.GetSubType() == 11) {
                    ret = 1;
                    break;
                }
            case 9:
                if (CanJni.GetSubType() == 5 || CanJni.GetSubType() == 11) {
                    ret = 1;
                    break;
                }
            case 10:
                if (CanJni.GetSubType() == 5) {
                    ret = 1;
                    break;
                }
                break;
            case 11:
                if (CanJni.GetSubType() == 11) {
                    ret = 1;
                    break;
                }
                break;
            case 12:
                if (CanJni.GetSubType() == 11) {
                    ret = 1;
                    break;
                }
                break;
            case 13:
                if (CanJni.GetSubType() == 11) {
                    ret = 1;
                    break;
                }
                break;
            case 14:
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
                this.mItemXczdls.ShowGone(show);
                return;
            case 3:
                this.mItemXhzdls.ShowGone(show);
                return;
            case 4:
                this.mItemYlgtcsz.ShowGone(show);
                return;
            case 5:
                this.mItemZczdjs.ShowGone(show);
                return;
            case 6:
                this.mItemCcycsjsz.ShowGone(show);
                return;
            case 7:
                this.mItemCcdgnsz.ShowGone(show);
                return;
            case 8:
                this.mItemCcykgnsz.ShowGone(show);
                return;
            case 9:
                this.mItemDlbsysz.ShowGone(show);
                return;
            case 10:
                this.mItemJbsfksssz.ShowGone(show);
                return;
            case 11:
                this.mItemSKTCsz.ShowGone(show);
                return;
            case 12:
                this.mItemYKTCsz.ShowGone(show);
                return;
            case 13:
                this.mItemYLGYTCsz.ShowGone(show);
                return;
            case 14:
                this.mItemEcls.ShowGone(show);
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
            case 2:
                CanJni.Cs75CarSet(4, NegSwSet(this.mSetData.Xczdls));
                return;
            case 3:
                CanJni.Cs75CarSet(5, NegSwSet(this.mSetData.Xhzdls));
                return;
            case 4:
                CanJni.Cs75CarSet(6, NegSwSet(this.mSetData.Ylgtcsz));
                return;
            case 5:
                CanJni.Cs75CarSet(16, NegSwSet(this.mSetData.Zczdjs));
                return;
            case 7:
                CanJni.Cs75CarSet(19, NegSwSet(this.mSetData.Ccdgnsz));
                return;
            case 8:
                CanJni.Cs75CarSet(20, NegSwSet(this.mSetData.Ccykgnsz));
                return;
            case 11:
                CanJni.Cs75CarSet(47, NegSwSet(this.mSetData.Sktc));
                return;
            case 12:
                CanJni.Cs75CarSet(48, NegSwSet(this.mSetData.Yktc));
                return;
            case 13:
                CanJni.Cs75CarSet(49, NegSwSet(this.mSetData.Ylgytc));
                return;
            case 14:
                CanJni.Cs75CarSet(59, NegSwSet(this.mSetData.Ecls));
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
                CanJni.Cs75CarSet(3, NegSwSet(this.mSetData.Ykjs));
                return;
            case 6:
                CanJni.Cs75CarSet(18, item + 1);
                return;
            case 9:
                CanJni.Cs75CarSet(21, item + 1);
                return;
            case 10:
                CanJni.Cs75CarSet(22, item + 1);
                return;
            default:
                return;
        }
    }
}
