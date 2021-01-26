package com.ts.can.saic.mg;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemFsSetList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanMGConvActivity extends CanMGGSBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemFsSetList.onFsSetClick, CanItemProgressList.onPosChange {
    public static final int ITEM_BDFZ = 36;
    public static final int ITEM_BJLMD = 20;
    public static final int ITEM_BJLMD2 = 24;
    public static final int ITEM_BJLMD3 = 40;
    public static final int ITEM_BJY = 21;
    public static final int ITEM_BWHJ = 1;
    public static final int ITEM_BWHJBCLDSJ = 27;
    public static final int ITEM_CDBCFZXT = 38;
    public static final int ITEM_CDPLBJ = 19;
    public static final int ITEM_CSFZ = 17;
    public static final int ITEM_CSWDKZ = 13;
    public static final int ITEM_DPHJKZXT = 29;
    public static final int ITEM_FQWDSZ = 6;
    public static final int ITEM_FZMS = 18;
    public static final int ITEM_FZMS2 = 23;
    public static final int ITEM_FZMS3 = 39;
    public static final int ITEM_HCCWCSLD = 4;
    public static final int ITEM_HFCCSZ = 10;
    public static final int ITEM_HXJSFZXT = 34;
    public static final int ITEM_HXJTJS = 37;
    public static final int ITEM_JJJS = 16;
    public static final int ITEM_JSMS = 7;
    public static final int ITEM_JSXRTSY = 11;
    public static final int ITEM_JSXRTSYYL = 30;
    public static final int ITEM_JTYDFZXT = 41;
    public static final int ITEM_LANG = 42;
    private static final int ITEM_MAX = 42;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_MQJC = 35;
    public static final int ITEM_TCFZXT = 31;
    public static final int ITEM_TCZT = 28;
    public static final int ITEM_TURN_FELL = 3;
    public static final int ITEM_TYFW = 9;
    public static final int ITEM_WHSJZDZD = 15;
    public static final int ITEM_XCLS = 14;
    public static final int ITEM_XCZS = 2;
    public static final int ITEM_XCZSCXSJ = 32;
    public static final int ITEM_XQPZFZXT = 22;
    public static final int ITEM_YBD = 25;
    public static final int ITEM_YBDBCLDSJ = 26;
    public static final int ITEM_YBLD = 12;
    public static final int ITEM_ZDMSFL = 5;
    public static final int ITEM_ZDTX = 33;
    public static final int ITEM_ZNJCJS = 8;
    public static final String TAG = "CanMGConvActivity";
    private static final int[] mBjlmd3Arr = {R.string.can_sensitivity_low, R.string.can_cdpyyzxt_1, R.string.can_sensitivity_high};
    private static final int[] mBjlmdArr = {R.string.can_sensitivity_low, R.string.can_sensitivity_mid, R.string.can_sensitivity_high};
    private static final int[] mConvsArr = {R.string.can_only_light, R.string.can_dghlb};
    private static final int[] mFqwdszArr = {R.string.can_fqwdsz_1, R.string.can_fqwdsz_2, R.string.can_fqwdsz_3};
    private static final int[] mFzms2Arr = {R.string.can_baojing, R.string.can_bjhzd};
    private static final int[] mFzms3Arr = {R.string.can_baojing, R.string.can_bjcdbc, R.string.can_bjcdplfz};
    private static final int[] mFzmsArr = {R.string.can_sdxs, R.string.can_tigger7_speed_warn, R.string.can_znxs};
    private static final int[] mJsmsArr = {R.string.can_door_unlock_key2, R.string.can_door_unlock_key1};
    private static final int[] mJsxrtsyylArr = {R.string.can_yybjxtyl_1, R.string.can_yybjxtyl_2};
    private static final int[] mLangArr = {R.string.lang_cn, R.string.lang_en_uk};
    private static final int[] mTurnFellsArr = {R.string.can_yiban, R.string.can_mode_ss, R.string.can_sport};
    private static final int[] mZdmsflArr = {R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
    private static final int[] mbcldsjArr = {R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
    private CanItemSwitchList mItemBdfz;
    private CanItemPopupList mItemBjlmd;
    private CanItemPopupList mItemBjlmd2;
    private CanItemPopupList mItemBjlmd3;
    private CanItemSwitchList mItemBjy;
    private CanItemSwitchList mItemBwhj;
    private CanItemPopupList mItemBwhjbcldsj;
    private CanItemSwitchList mItemCSWDKZ;
    private CanItemFsSetList mItemCarRst;
    private CanItemSwitchList mItemCdbcfzxt;
    private CanItemSwitchList mItemCdplbj;
    private CanItemSwitchList mItemCsfz;
    private CanItemSwitchList mItemDphjkzxt;
    private CanItemPopupList mItemFqwdsz;
    private CanItemPopupList mItemFzms;
    private CanItemPopupList mItemFzms2;
    private CanItemPopupList mItemFzms3;
    private CanItemSwitchList mItemHccscwld;
    private CanItemSwitchList mItemHxjsfzxt;
    private CanItemSwitchList mItemHxjtjs;
    private CanItemSwitchList mItemJSXRTSY;
    private CanItemSwitchList mItemJjjs;
    private CanItemPopupList mItemJsms;
    private CanItemPopupList mItemJsxrtsyyl;
    private CanItemSwitchList mItemJtydfzxt;
    private CanItemPopupList mItemLang;
    private CanItemSwitchList mItemMqjc;
    private CanItemSwitchList mItemTcfzxt;
    private CanItemProgressList mItemTczt;
    private CanItemFsSetList mItemTpmsRst;
    private CanItemPopupList mItemTurnFell;
    private CanItemSwitchList mItemWhsjzdzd;
    private CanItemSwitchList mItemXcls;
    private CanItemPopupList mItemXczs;
    private CanItemPopupList mItemXczscxsj;
    private CanItemSwitchList mItemXqpzfzxt;
    private CanItemProgressList mItemYBLD;
    private CanItemSwitchList mItemYbd;
    private CanItemPopupList mItemYbdbcldsj;
    private CanItemPopupList mItemZdmsfl;
    private CanItemSwitchList mItemZdtx;
    private CanItemPopupList mItemZnjcjs;
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
        GetSetData1();
        if (!i2b(this.mSetData1.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData1.Update)) {
            this.mSetData1.Update = 0;
            this.mItemBwhj.SetCheck(this.mSetData1.fgHomeRevLight);
            this.mItemXczs.SetSel(this.mSetData1.FindIndicator);
            this.mItemTurnFell.SetSel(this.mSetData1.TurnFeel);
            this.mItemHccscwld.SetCheck(this.mSetData1.Hccscwld);
            this.mItemZdmsfl.SetSel(this.mSetData1.Zdmsfl);
            this.mItemFqwdsz.SetSel(this.mSetData1.Fqwdsz);
            this.mItemYBLD.SetCurVal(this.mSetData1.Ybld);
            this.mItemYBLD.SetValText(String.format("%d", new Object[]{Integer.valueOf(this.mSetData1.Ybld)}));
            this.mItemJSXRTSY.SetCheck(this.mSetData1.Jsxrtsy);
            this.mItemCSWDKZ.SetCheck(this.mSetData1.Cswdkz);
            this.mItemJsms.SetSel(this.mSetData1.Jsms);
            this.mItemZnjcjs.SetSel(this.mSetData1.Znjcjs);
            if (i2b(this.mSetData1.Tyfw)) {
                this.mItemTpmsRst.SetColor(-12303292);
                this.mItemTpmsRst.GetView().setEnabled(false);
            } else {
                this.mItemTpmsRst.SetColor(-1);
                this.mItemTpmsRst.GetView().setEnabled(true);
            }
            if (i2b(this.mSetData1.Hfccsz)) {
                this.mItemCarRst.SetColor(-12303292);
                this.mItemCarRst.GetView().setEnabled(false);
            } else {
                this.mItemCarRst.SetColor(-1);
                this.mItemCarRst.GetView().setEnabled(true);
            }
            this.mItemXcls.SetCheck(this.mSetData1.Xcls);
            this.mItemWhsjzdzd.SetCheck(this.mSetData1.Whsjzdzd);
            this.mItemJjjs.SetCheck(this.mSetData1.Jjjs);
            this.mItemCsfz.SetCheck(this.mSetData1.Csfz);
            this.mItemFzms.SetSel(this.mSetData1.Fzms);
            this.mItemCdplbj.SetCheck(this.mSetData1.Cdplbj);
            this.mItemBjlmd.SetSel(this.mSetData1.Bjlmd);
            this.mItemBjy.SetCheck(this.mSetData1.Bjy);
            this.mItemXqpzfzxt.SetCheck(this.mSetData1.Xqpzfzxt);
            this.mItemFzms2.SetSel(this.mSetData1.Fzms2);
            this.mItemBjlmd2.SetSel(this.mSetData1.Bjlmd2);
            this.mItemYbd.SetCheck(this.mSetData1.Ybd);
            this.mItemYbdbcldsj.SetSel(this.mSetData1.Ybdbcldsj);
            this.mItemBwhjbcldsj.SetSel(this.mSetData1.Bwhjbcldsj);
            this.mItemTczt.SetCurVal(this.mSetData1.Tczt);
            this.mItemTczt.SetValText(String.format("%d", new Object[]{Integer.valueOf(this.mSetData1.Tczt)}));
            this.mItemDphjkzxt.SetCheck(this.mSetData1.Dphjkzxt);
            this.mItemJsxrtsyyl.SetSel(this.mSetData1.Jsxrtsyyl);
            this.mItemTcfzxt.SetCheck(this.mSetData1.Tcfzxt);
            this.mItemXczscxsj.SetSel(this.mSetData1.Xczscxsj);
            this.mItemZdtx.SetCheck(this.mSetData1.Zdtx);
            this.mItemHxjsfzxt.SetCheck(this.mSetData1.Hxjsfzxt);
            this.mItemMqjc.SetCheck(this.mSetData1.Mqjc);
            this.mItemBdfz.SetCheck(this.mSetData1.Bdfz);
            this.mItemHxjtjs.SetCheck(this.mSetData1.Hxjtjs);
            this.mItemCdbcfzxt.SetCheck(this.mSetData1.Cdbcfzxt);
            this.mItemFzms3.SetSel(this.mSetData1.Fzms3);
            this.mItemBjlmd3.SetSel(this.mSetData1.Bjlmd3);
            this.mItemJtydfzxt.SetCheck(this.mSetData1.Jtydfzxt);
            this.mItemLang.SetSel(this.mSetData1.Lang - 1);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(65);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        Log.d(TAG, "-----onResume-----");
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemBwhj = AddCheckItem(R.string.can_bwhj, 1);
        this.mItemXczs = AddPopupItem(R.string.can_xcd, mConvsArr, 2);
        this.mItemTurnFell = AddPopupItem(R.string.can_mg_turnfell, mTurnFellsArr, 3);
        this.mItemHccscwld = AddCheckItem(R.string.can_hccscwld, 4);
        this.mItemZdmsfl = AddPopupItem(R.string.can_ac_zdfl, mZdmsflArr, 5);
        this.mItemFqwdsz = AddPopupItem(R.string.can_fqwdsz, mFqwdszArr, 6);
        this.mItemJsms = AddPopupItem(R.string.can_unlock_mode, mJsmsArr, 7);
        this.mItemZnjcjs = AddPopupItem(R.string.can_smart_near_lock, mJsmsArr, 8);
        this.mItemXcls = AddCheckItem(R.string.can_xczdls, 14);
        this.mItemWhsjzdzd = AddCheckItem(R.string.can_carset_hsjzdzd, 15);
        this.mItemJSXRTSY = AddCheckItem(R.string.can_jsxrtsy, 11);
        this.mItemJsxrtsyyl = AddPopupItem(R.string.can_jsxrtsyyl, mJsxrtsyylArr, 30);
        this.mItemTcfzxt = AddCheckItem(R.string.can_tcfzxt, 31);
        this.mItemCSWDKZ = AddCheckItem(R.string.can_cswdkz, 13);
        this.mItemJjjs = AddCheckItem(R.string.can_jjms, 16);
        this.mItemDphjkzxt = AddCheckItem(R.string.can_teramont_dphj_system, 29);
        this.mItemYBLD = AddProgressItem(R.string.can_ybld, 12, 1, 1, 10);
        this.mItemCsfz = AddCheckItem(R.string.can_csfz, 17);
        this.mItemFzms = AddPopupItem(R.string.can_fzms, mFzmsArr, 18);
        this.mItemCdplbj = AddCheckItem(R.string.can_jp_cdpljg, 19);
        this.mItemBjlmd = AddPopupItem(R.string.can_bjlmd, mBjlmdArr, 20);
        this.mItemBjy = AddCheckItem(R.string.can_bjying, 21);
        this.mItemXqpzfzxt = AddCheckItem(R.string.can_xqpzfzxt, 22);
        this.mItemFzms2 = AddPopupItem(R.string.can_fzms2, mFzms2Arr, 23);
        this.mItemBjlmd2 = AddPopupItem(R.string.can_bjlmd2, mBjlmdArr, 24);
        this.mItemYbd = AddCheckItem(R.string.can_yb_light, 25);
        this.mItemYbdbcldsj = AddPopupItem(R.string.can_ybdsc, mbcldsjArr, 26);
        this.mItemBwhjbcldsj = AddPopupItem(R.string.can_dgsjkz_bwhj, mbcldsjArr, 27);
        this.mItemXczscxsj = AddPopupItem(R.string.can_cxsj_xcd, mbcldsjArr, 32);
        this.mItemTczt = AddProgressItem(R.string.can_hant_tczt, 28, 1, 0, 10);
        this.mItemZdtx = AddCheckItem(R.string.can_zdtx, 33);
        this.mItemHxjsfzxt = AddCheckItem(R.string.can_xhjsfzxt, 34);
        this.mItemMqjc = AddCheckItem(R.string.can_blind_spot_monitoring, 35);
        this.mItemBdfz = AddCheckItem(R.string.can_psa_wc_bdfz, 36);
        this.mItemHxjtjs = AddCheckItem(R.string.can_hxjtjs, 37);
        this.mItemCdbcfzxt = AddCheckItem(R.string.can_lane_assist, 38);
        this.mItemFzms3 = AddPopupItem(R.string.can_fzms3, mFzms3Arr, 39);
        this.mItemBjlmd3 = AddPopupItem(R.string.can_bjlmd3, mBjlmd3Arr, 40);
        this.mItemJtydfzxt = AddCheckItem(R.string.can_jtydfzxt, 41);
        this.mItemLang = AddPopupItem(R.string.can_car_lang, mLangArr, 42);
        this.mItemTpmsRst = new CanItemFsSetList(this, R.string.can_rw_rx5_tyfw);
        this.mItemTpmsRst.SetIdClickListener(9, this);
        this.mManager.AddView(this.mItemTpmsRst.GetView());
        this.mItemCarRst = new CanItemFsSetList(this, R.string.can_rw_rx5_hfccsz);
        this.mItemCarRst.SetIdClickListener(10, this);
        this.mManager.AddView(this.mItemCarRst.GetView());
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 42; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 1;
        switch (item) {
            case 3:
                if (6 == CanJni.GetSubType() || 7 == CanJni.GetSubType() || 8 == CanJni.GetSubType() || 10 == CanJni.GetSubType() || 12 == CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
            case 4:
                if (4 == CanJni.GetSubType() || 8 == CanJni.GetSubType() || 10 == CanJni.GetSubType() || 12 == CanJni.GetSubType() || 13 == CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
            case 5:
                if (4 == CanJni.GetSubType() || 8 == CanJni.GetSubType() || 10 == CanJni.GetSubType() || 12 == CanJni.GetSubType() || 13 == CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
            case 6:
                if (4 == CanJni.GetSubType() || 6 == CanJni.GetSubType() || 7 == CanJni.GetSubType() || 8 == CanJni.GetSubType() || 9 == CanJni.GetSubType() || 10 == CanJni.GetSubType() || 12 == CanJni.GetSubType() || 13 == CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
            case 7:
                if (4 == CanJni.GetSubType() || 6 == CanJni.GetSubType() || 7 == CanJni.GetSubType() || 13 == CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
            case 8:
                if (4 == CanJni.GetSubType() || 6 == CanJni.GetSubType() || 7 == CanJni.GetSubType() || 13 == CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
            case 9:
                if (4 == CanJni.GetSubType() || 6 == CanJni.GetSubType() || 7 == CanJni.GetSubType() || 8 == CanJni.GetSubType() || 9 == CanJni.GetSubType() || 12 == CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
            case 10:
                if (4 == CanJni.GetSubType() || 8 == CanJni.GetSubType() || 9 == CanJni.GetSubType() || 13 == CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
            case 11:
                if (!(6 == CanJni.GetSubType() || 7 == CanJni.GetSubType() || 11 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 12:
                if (!(6 == CanJni.GetSubType() || 7 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 13:
                if (!(6 == CanJni.GetSubType() || 7 == CanJni.GetSubType() || 8 == CanJni.GetSubType() || 9 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 14:
                if (9 != CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
                break;
            case 15:
                if (!(8 == CanJni.GetSubType() || 12 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 16:
                if (!(9 == CanJni.GetSubType() || 10 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 17:
                if (!(9 == CanJni.GetSubType() || 13 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 18:
                if (!(9 == CanJni.GetSubType() || 13 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 19:
                if (9 != CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
                break;
            case 20:
                if (9 != CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
                break;
            case 21:
                if (!(9 == CanJni.GetSubType() || 13 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 22:
                if (!(9 == CanJni.GetSubType() || 13 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 23:
                if (!(9 == CanJni.GetSubType() || 13 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 24:
                if (!(9 == CanJni.GetSubType() || 13 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 25:
                if (!(9 == CanJni.GetSubType() || 13 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 26:
                if (!(9 == CanJni.GetSubType() || 13 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 27:
                if (!(9 == CanJni.GetSubType() || 10 == CanJni.GetSubType() || 12 == CanJni.GetSubType() || 13 == CanJni.GetSubType())) {
                    ret = 0;
                    break;
                }
            case 28:
                if (9 != CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
                break;
            case 29:
                if (11 != CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
                break;
            case 30:
                if (11 != CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
                break;
            case 31:
                if (12 != CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
                break;
            case 32:
                if (12 != CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
                break;
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
                if (13 != CanJni.GetSubType()) {
                    ret = 0;
                    break;
                }
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
                this.mItemXczs.ShowGone(show);
                return;
            case 3:
                this.mItemTurnFell.ShowGone(show);
                return;
            case 4:
                this.mItemHccscwld.ShowGone(show);
                return;
            case 5:
                this.mItemZdmsfl.ShowGone(show);
                return;
            case 6:
                this.mItemFqwdsz.ShowGone(show);
                return;
            case 7:
                this.mItemJsms.ShowGone(show);
                return;
            case 8:
                this.mItemZnjcjs.ShowGone(show);
                return;
            case 9:
                this.mItemTpmsRst.ShowGone(show);
                return;
            case 10:
                this.mItemCarRst.ShowGone(show);
                return;
            case 11:
                this.mItemJSXRTSY.ShowGone(show);
                return;
            case 12:
                this.mItemYBLD.ShowGone(show);
                return;
            case 13:
                this.mItemCSWDKZ.ShowGone(show);
                return;
            case 14:
                this.mItemXcls.ShowGone(show);
                return;
            case 15:
                this.mItemWhsjzdzd.ShowGone(show);
                return;
            case 16:
                this.mItemJjjs.ShowGone(show);
                return;
            case 17:
                this.mItemCsfz.ShowGone(show);
                return;
            case 18:
                this.mItemFzms.ShowGone(show);
                return;
            case 19:
                this.mItemCdplbj.ShowGone(show);
                return;
            case 20:
                this.mItemBjlmd.ShowGone(show);
                return;
            case 21:
                this.mItemBjy.ShowGone(show);
                return;
            case 22:
                this.mItemXqpzfzxt.ShowGone(show);
                return;
            case 23:
                this.mItemFzms2.ShowGone(show);
                return;
            case 24:
                this.mItemBjlmd2.ShowGone(show);
                return;
            case 25:
                this.mItemYbd.ShowGone(show);
                return;
            case 26:
                this.mItemYbdbcldsj.ShowGone(show);
                return;
            case 27:
                this.mItemBwhjbcldsj.ShowGone(show);
                return;
            case 28:
                this.mItemTczt.ShowGone(show);
                return;
            case 29:
                this.mItemDphjkzxt.ShowGone(show);
                return;
            case 30:
                this.mItemJsxrtsyyl.ShowGone(show);
                return;
            case 31:
                this.mItemTcfzxt.ShowGone(show);
                return;
            case 32:
                this.mItemXczscxsj.ShowGone(show);
                return;
            case 33:
                this.mItemZdtx.ShowGone(show);
                return;
            case 34:
                this.mItemHxjsfzxt.ShowGone(show);
                return;
            case 35:
                this.mItemMqjc.ShowGone(show);
                return;
            case 36:
                this.mItemBdfz.ShowGone(show);
                return;
            case 37:
                this.mItemHxjtjs.ShowGone(show);
                return;
            case 38:
                this.mItemCdbcfzxt.ShowGone(show);
                return;
            case 39:
                this.mItemFzms3.ShowGone(show);
                return;
            case 40:
                this.mItemBjlmd3.ShowGone(show);
                return;
            case 41:
                this.mItemJtydfzxt.ShowGone(show);
                return;
            case 42:
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
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemProgressList AddProgressItem(int resId, int id, int step, int min, int max) {
        CanItemProgressList item = new CanItemProgressList((Context) this, resId);
        item.SetIdCallBack(id, this);
        item.SetStep(step);
        item.SetMinMax(min, max);
        item.setValueAlign();
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CarSet(3, 1, Neg(this.mSetData1.fgHomeRevLight));
                return;
            case 4:
                CarSet(5, 1, Neg(this.mSetData1.Hccscwld));
                return;
            case 11:
                CarSet(6, 1, Neg(this.mSetData1.Jsxrtsy));
                return;
            case 13:
                CarSet(4, 2, Neg(this.mSetData1.Cswdkz));
                return;
            case 14:
                CarSet(1, 1, Neg(this.mSetData1.Xcls));
                return;
            case 15:
                CarSet(8, 1, Neg(this.mSetData1.Whsjzdzd));
                return;
            case 16:
                CarSet(4, 3, Neg(this.mSetData1.Jjjs));
                return;
            case 17:
                CarSet(6, 2, Neg(this.mSetData1.Csfz));
                return;
            case 19:
                CarSet(6, 4, Neg(this.mSetData1.Cdplbj));
                return;
            case 21:
                CarSet(6, 6, Neg(this.mSetData1.Bjy));
                return;
            case 22:
                CarSet(6, 7, Neg(this.mSetData1.Xqpzfzxt));
                return;
            case 25:
                CarSet(3, 4, Neg(this.mSetData1.Ybd));
                return;
            case 29:
                CarSet(4, 4, Neg(this.mSetData1.Dphjkzxt));
                return;
            case 31:
                CarSet(6, 11, Neg(this.mSetData1.Tcfzxt));
                return;
            case 33:
                CarSet(6, 12, Neg(this.mSetData1.Zdtx));
                return;
            case 34:
                CarSet(6, 13, Neg(this.mSetData1.Hxjsfzxt));
                return;
            case 35:
                CarSet(6, 14, Neg(this.mSetData1.Mqjc));
                return;
            case 36:
                CarSet(6, 15, Neg(this.mSetData1.Bdfz));
                return;
            case 37:
                CarSet(6, 16, Neg(this.mSetData1.Hxjtjs));
                return;
            case 38:
                CarSet(6, 17, Neg(this.mSetData1.Cdbcfzxt));
                return;
            case 41:
                CarSet(6, 20, Neg(this.mSetData1.Jtydfzxt));
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
            case 2:
                CarSet(3, 2, item);
                return;
            case 3:
                CarSet(3, 3, item);
                return;
            case 5:
                CarSet(5, 2, item);
                return;
            case 6:
                CarSet(5, 3, item);
                return;
            case 7:
                CarSet(1, 3, item);
                return;
            case 8:
                CarSet(1, 4, item);
                return;
            case 18:
                CarSet(6, 3, item);
                return;
            case 20:
                CarSet(6, 5, item);
                return;
            case 23:
                CarSet(6, 8, item);
                return;
            case 24:
                CarSet(6, 9, item);
                return;
            case 26:
                CarSet(3, 4, item + 2);
                return;
            case 27:
                CarSet(3, 1, item + 2);
                return;
            case 30:
                CarSet(6, 10, item);
                return;
            case 32:
                CarSet(2, 4, item);
                return;
            case 39:
                CarSet(6, 18, item);
                return;
            case 40:
                CarSet(6, 19, item);
                return;
            case 42:
                CarSet(10, 1, item + 1);
                return;
            default:
                return;
        }
    }

    public void onFsItem(int id, boolean sure) {
        if (sure) {
            switch (id) {
                case 9:
                    CarSet(4, 1, 1);
                    return;
                case 10:
                    CarSet(3, 0, 0);
                    return;
                default:
                    return;
            }
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 12:
                CarSet(7, 1, pos);
                return;
            case 28:
                CarSet(9, 1, pos);
                return;
            default:
                return;
        }
    }
}
