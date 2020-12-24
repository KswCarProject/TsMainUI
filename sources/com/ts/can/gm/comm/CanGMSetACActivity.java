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

public class CanGMSetACActivity extends CanGMBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_FQWD = 8;
    public static final int ITEM_HCCW = 1;
    public static final int ITEM_HQKTQD = 12;
    public static final int ITEM_KQZLCGQ = 7;
    public static final int ITEM_KTYCQD = 11;
    private static final int ITEM_MAX = 12;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_QCCW = 2;
    public static final int ITEM_QDMS = 5;
    public static final int ITEM_YCQDZYCF = 10;
    public static final int ITEM_YCQDZYJR = 9;
    public static final int ITEM_ZDMSFL = 6;
    public static final int ITEM_ZYJR_POP = 4;
    public static final int ITEM_ZYJR_SW = 3;
    public static final String TAG = "CanGMCarInfoActivity";
    private static final int[] mFqwdlArr = {R.string.can_ty_set, R.string.can_fq_set, R.string.can_sc_set};
    private static final int[] mHqktqdArrays = {R.string.can_gl8_2017_hqktqd_close, R.string.can_gl8_2017_hqktqd_same, R.string.can_gl8_2017_hqktqd_last};
    private static final int[] mKqzlcgqlArr = {R.string.can_off, R.string.can_ac_lo_sens, R.string.can_ac_hi_sens};
    private static final int[] mKtycqdArrays = {R.string.can_gl8_2017_ktycqd_auto, R.string.can_gl8_2017_ktycqd_last};
    private static final int[] mQdmsArr = {R.string.can_off, R.string.can_on, R.string.can_sc_set};
    private static final int[] mZdmsflArr = {R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
    private static final int[] mZyjrArr = {R.string.can_off, R.string.can_ckhjsy, R.string.can_jiashiyuan};
    private CanDataInfo.GM_ACSet mACSetData = new CanDataInfo.GM_ACSet();
    private CanDataInfo.GM_AdtAC mAdtACData = new CanDataInfo.GM_AdtAC();
    private CanItemPopupList mItemFqwd;
    private CanItemSwitchList mItemHccw;
    private CanItemPopupList mItemHqktqd;
    private CanItemPopupList mItemKqzlcgq;
    private CanItemPopupList mItemKtycqd;
    private CanItemSwitchList mItemQccw;
    private CanItemPopupList mItemQdms;
    private CanItemSwitchList mItemYcqdzycf;
    private CanItemSwitchList mItemYcqdzyjr;
    private CanItemPopupList mItemZdmsfl;
    private CanItemPopupList mItemZyjrPop;
    private CanItemSwitchList mItemZyjrSW;
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
        boolean z = true;
        CanJni.GMGetACSet(this.mACSetData);
        CanJni.GMGetAdtAC(this.mAdtACData);
        if (i2b(this.mAdtACData.UpdateOnce)) {
            if (!check || i2b(this.mAdtACData.Update)) {
                this.mAdtACData.Update = 0;
                LayoutUI();
                check = false;
                this.mbLayout = true;
            }
            if (!i2b(this.mACSetData.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mACSetData.Update)) {
                this.mACSetData.Update = 0;
                this.mItemQccw.SetCheck(this.mACSetData.QCZDCW);
                this.mItemHccw.SetCheck(this.mACSetData.HCZDCW);
                this.mItemQdms.SetSel(this.mACSetData.QDMS);
                this.mItemZdmsfl.SetSel(this.mACSetData.AutoMode);
                this.mItemKqzlcgq.SetSel(this.mACSetData.KQZLLMD);
                this.mItemFqwd.SetSel(this.mACSetData.FQWD);
                this.mItemYcqdzyjr.SetCheck(this.mACSetData.YCQDZYJR);
                this.mItemYcqdzycf.SetCheck(this.mACSetData.YCQDZYCF);
                this.mItemKtycqd.SetSel(this.mACSetData.KTYCQD);
                this.mItemHqktqd.SetSel(this.mACSetData.HQKTQD);
                if (this.mAdtACData.YKZYJR != 0) {
                    this.mItemZyjrSW.ShowGone(this.mACSetData.ZyjrType == 0);
                    CanItemPopupList canItemPopupList = this.mItemZyjrPop;
                    if (1 != this.mACSetData.ZyjrType) {
                        z = false;
                    }
                    canItemPopupList.ShowGone(z);
                    this.mItemZyjrSW.SetCheck(this.mACSetData.YKZYJR);
                    if (3 == this.mACSetData.YKZYJR) {
                        this.mItemZyjrPop.SetSel(2);
                    } else {
                        this.mItemZyjrPop.SetSel(this.mACSetData.YKZYJR);
                    }
                } else {
                    this.mItemZyjrSW.ShowGone(false);
                    this.mItemZyjrPop.ShowGone(false);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (!i2b(this.mAdtACData.UpdateOnce)) {
            CanJni.GMQuery(26);
        }
        if (!i2b(this.mACSetData.UpdateOnce)) {
            CanJni.GMQuery(5);
        }
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
        this.mItemQccw = AddCheckItem(R.string.can_ac_qccw, 2);
        this.mItemHccw = AddCheckItem(R.string.can_ac_hccw, 1);
        this.mItemZyjrSW = AddCheckItem(R.string.can_ac_zyjr, 3);
        this.mItemZyjrPop = AddPopupItem(R.string.can_ac_zyjr, mZyjrArr, 4);
        this.mItemQdms = AddPopupItem(R.string.can_ac_qdms, mQdmsArr, 5);
        this.mItemZdmsfl = AddPopupItem(R.string.can_ac_zdfl, mZdmsflArr, 6);
        this.mItemKqzlcgq = AddPopupItem(R.string.can_ac_cgq, mKqzlcgqlArr, 7);
        this.mItemFqwd = AddPopupItem(R.string.can_ac_fqwd, mFqwdlArr, 8);
        this.mItemYcqdzyjr = AddCheckItem(R.string.can_gl8_2017_ycqdzyjr, 9);
        this.mItemYcqdzycf = AddCheckItem(R.string.can_gl8_2017_ycqdzycf, 10);
        this.mItemKtycqd = AddPopupItem(R.string.can_gl8_2017_ktycqd, mKtycqdArrays, 11);
        this.mItemHqktqd = AddPopupItem(R.string.can_gl8_2017_hqktqd, mHqktqdArrays, 12);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 12; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtACData.HCZDCW;
                break;
            case 2:
                ret = this.mAdtACData.QCZDCW;
                break;
            case 3:
                ret = 0;
                break;
            case 4:
                ret = 0;
                break;
            case 5:
                ret = this.mAdtACData.QDMS;
                break;
            case 6:
                ret = this.mAdtACData.AutoMode;
                break;
            case 7:
                ret = this.mAdtACData.KQZLLMD;
                break;
            case 8:
                ret = this.mAdtACData.FQWD;
                break;
            case 9:
                ret = this.mAdtACData.YCQDZYJR;
                break;
            case 10:
                ret = this.mAdtACData.YCQDZYCF;
                break;
            case 11:
                ret = this.mAdtACData.KTYCQD;
                break;
            case 12:
                ret = this.mAdtACData.HQKTQD;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemHccw.ShowGone(show);
                return;
            case 2:
                this.mItemQccw.ShowGone(show);
                return;
            case 3:
                this.mItemZyjrSW.ShowGone(show);
                return;
            case 4:
                this.mItemZyjrPop.ShowGone(show);
                return;
            case 5:
                this.mItemQdms.ShowGone(show);
                return;
            case 6:
                this.mItemZdmsfl.ShowGone(show);
                return;
            case 7:
                this.mItemKqzlcgq.ShowGone(show);
                return;
            case 8:
                this.mItemFqwd.ShowGone(show);
                return;
            case 9:
                this.mItemYcqdzyjr.ShowGone(show);
                return;
            case 10:
                this.mItemYcqdzycf.ShowGone(show);
                return;
            case 11:
                this.mItemKtycqd.ShowGone(show);
                return;
            case 12:
                this.mItemHqktqd.ShowGone(show);
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
                CanJni.GMACCtrl(3, Neg(this.mACSetData.HCZDCW));
                return;
            case 2:
                CanJni.GMACCtrl(4, Neg(this.mACSetData.QCZDCW));
                return;
            case 3:
                CanJni.GMACCtrl(5, Neg(this.mACSetData.YKZYJR));
                return;
            case 9:
                CanJni.GMACCtrl(65, Neg(this.mACSetData.YCQDZYJR));
                return;
            case 10:
                CanJni.GMACCtrl(66, Neg(this.mACSetData.YCQDZYCF));
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
                CanJni.GMACCtrl(5, item);
                return;
            case 5:
                CanJni.GMACCtrl(6, item);
                return;
            case 6:
                CanJni.GMACCtrl(0, item);
                return;
            case 7:
                CanJni.GMACCtrl(1, item);
                return;
            case 8:
                CanJni.GMACCtrl(2, item);
                return;
            case 11:
                CanJni.GMACCtrl(67, item);
                return;
            case 12:
                CanJni.GMACCtrl(68, item);
                return;
            default:
                return;
        }
    }
}
