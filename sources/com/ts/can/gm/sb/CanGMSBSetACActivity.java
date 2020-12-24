package com.ts.can.gm.sb;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGMSBSetACActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
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
    private static final int[] mFqwdlArr = {R.string.can_ty_set, R.string.can_fq_set, R.string.can_sc_set};
    private static final int[] mHqktqdArrays = {R.string.can_gl8_2017_hqktqd_close, R.string.can_gl8_2017_hqktqd_same, R.string.can_gl8_2017_hqktqd_last};
    private static final int[] mKqzlcgqlArr = {R.string.can_ac_lo_sens, R.string.can_mid_sens, R.string.can_ac_hi_sens};
    private static final int[] mKtycqdArrays = {R.string.can_gl8_2017_ktycqd_auto, R.string.can_gl8_2017_ktycqd_last};
    private static final int[] mQdmsArr = {R.string.can_off, R.string.can_on, R.string.can_sc_set};
    private static final int[] mYcqdzycfArrays = {R.string.can_off, R.string.can_ckhjsy, R.string.can_jiashiyuan};
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
    private CanItemPopupList mItemYcqdzycf;
    private CanItemPopupList mItemYcqdzyjr;
    private CanItemPopupList mItemZdmsfl;
    private CanItemPopupList mItemZyjrPop;
    private CanItemSwitchList mItemZyjrSW;
    private CanScrollList mManager;

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMACCtrl(5, Neg(this.mACSetData.HCZDCW));
                return;
            case 2:
                CanJni.GMACCtrl(6, Neg(this.mACSetData.QCZDCW));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 5:
                CanJni.GMACCtrl(3, item);
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
            case 9:
                CanJni.GMCarCtrl(13, item);
                return;
            case 10:
                CanJni.GMCarCtrl(26, item);
                return;
            case 11:
                CanJni.GMACCtrl(22, item);
                return;
            case 12:
                CanJni.GMACCtrl(4, item);
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
        this.mItemZdmsfl = this.mManager.addItemPopupList(R.string.can_ac_zdfl, mZdmsflArr, 6, (CanItemPopupList.onPopItemClick) this);
        this.mItemKqzlcgq = this.mManager.addItemPopupList(R.string.can_ac_cgq, mKqzlcgqlArr, 7, (CanItemPopupList.onPopItemClick) this);
        this.mItemFqwd = this.mManager.addItemPopupList(R.string.can_ac_fqwd, mFqwdlArr, 8, (CanItemPopupList.onPopItemClick) this);
        this.mItemQdms = this.mManager.addItemPopupList(R.string.can_ac_qdms, mQdmsArr, 5, (CanItemPopupList.onPopItemClick) this);
        this.mItemHqktqd = this.mManager.addItemPopupList(R.string.can_gl8_2017_hqktqd, mHqktqdArrays, 12, (CanItemPopupList.onPopItemClick) this);
        this.mItemHccw = this.mManager.addItemCheckBox(R.string.can_ac_hccw, 1, this);
        this.mItemQccw = this.mManager.addItemCheckBox(R.string.can_ac_qccw, 2, this);
        this.mItemKtycqd = this.mManager.addItemPopupList(R.string.can_gl8_2017_ktycqd, mKtycqdArrays, 11, (CanItemPopupList.onPopItemClick) this);
        this.mItemZyjrSW = this.mManager.addItemCheckBox(R.string.can_ac_zyjr, 3, this);
        this.mItemZyjrPop = this.mManager.addItemPopupList(R.string.can_ac_zyjr, mZyjrArr, 4, (CanItemPopupList.onPopItemClick) this);
        this.mItemYcqdzyjr = this.mManager.addItemPopupList(R.string.can_gl8_2017_ycqdzyjr, mYcqdzycfArrays, 9, (CanItemPopupList.onPopItemClick) this);
        this.mItemYcqdzycf = this.mManager.addItemPopupList(R.string.can_gl8_2017_ycqdzycf, mYcqdzycfArrays, 10, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        boolean z = true;
        CanJni.GMGetACSet(this.mACSetData);
        CanJni.GMGetAdtAC(this.mAdtACData);
        if (i2b(this.mAdtACData.UpdateOnce) && (!check || i2b(this.mAdtACData.Update))) {
            this.mAdtACData.Update = 0;
            LayoutUI();
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
            this.mItemYcqdzyjr.SetSel(this.mACSetData.YCQDZYJR);
            this.mItemYcqdzycf.SetSel(this.mACSetData.YCQDZYCF);
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

    private void LayoutUI() {
        for (int i = 1; i <= 12; i++) {
            ShowItem(i);
        }
    }

    private void ShowItem(int item) {
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

    private boolean IsHaveItem(int item) {
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
    public void QueryData() {
    }
}
