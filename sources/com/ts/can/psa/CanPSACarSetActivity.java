package com.ts.can.psa;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanPSACarSetActivity extends CanPSABaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2 {
    public static final int ITEM_AC = 24;
    public static final int ITEM_AUTOLIGHT = 22;
    public static final int ITEM_AUTOLOCK = 23;
    public static final int ITEM_BWHJ = 7;
    public static final int ITEM_CMXX = 14;
    public static final int ITEM_CONSDW = 11;
    public static final int ITEM_DAUL = 25;
    public static final int ITEM_DCLDTY = 4;
    public static final int ITEM_DDYSGB = 16;
    public static final int ITEM_FDJQT = 13;
    public static final int ITEM_FWZM = 6;
    public static final int ITEM_GXHYB = 21;
    public static final int ITEM_HYS = 5;
    public static final int ITEM_JJSHBX = 17;
    public static final int ITEM_LANG = 1;
    private static final int ITEM_MAX = 25;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_MQTC = 12;
    public static final int ITEM_PLJC = 19;
    public static final int ITEM_QYLKZ = 18;
    public static final int ITEM_RXD = 9;
    public static final int ITEM_SOUND = 2;
    public static final int ITEM_TYBD = 15;
    public static final int ITEM_YBBJYS = 20;
    public static final int ITEM_YBGN = 10;
    public static final int ITEM_YBZM = 8;
    public static final int ITEM_ZCFZ = 3;
    public static final String TAG = "CanPSACarSetActivity";
    private static final int[] mBwhjArr = {R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_60s};
    private static final int[] mCmxxArr = {R.string.can_jsym, R.string.can_sym};
    private static final String[] mConsDWArr = {"KM/L-KM", "L/100KM-KM", "MPG"};
    private static final int[] mDdysgbmArr = {R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_60s};
    private static final int[] mGxhybArr = {R.string.can_psa_2017_4008_gxhybsz_0, R.string.can_psa_2017_4008_gxhybsz_1, R.string.can_psa_2017_4008_gxhybsz_2, R.string.can_psa_2017_4008_gxhybsz_3, R.string.can_psa_2017_4008_gxhybsz_4, R.string.can_psa_2017_4008_gxhybsz_5};
    private static final int[] mLangArr = {R.string.lang_en_uk, R.string.lang_cn, R.string.lang_pyccknn, R.string.lang_francais, R.string.lang_deutsch, R.string.lang_espanol, R.string.lang_itanliano, R.string.lang_nederlands, R.string.lang_portugues, R.string.lang_turkce, R.string.lang_portugues, R.string.lang_portugues_brasil};
    private static final int[] mSoundArr = {R.string.can_eq_classic, R.string.can_eq_crystal, R.string.can_eq_urban, R.string.can_eq_jungle};
    private static final int[] mStaArr = {R.string.can_off, R.string.can_on};
    private static final int[] mYbbjysArr = {R.string.can_psa_2017_4008_ybbjys_1, R.string.can_psa_2017_4008_ybbjys_2};
    private static final int[] mYbzmArr = {R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_60s};
    private CanDataInfo.PeugAdt mAdtData = new CanDataInfo.PeugAdt();
    private String[] mFwzmArr;
    private CanItemPopupList mItemAc;
    private CanItemSwitchList mItemAutoLight;
    private CanItemSwitchList mItemAutoLock;
    private CanItemPopupList mItemBwhj;
    private CanItemPopupList mItemCmxx;
    private CanItemPopupList mItemConsDW;
    private CanItemPopupList mItemDaul;
    private CanItemSwitchList mItemDcldty;
    private CanItemPopupList mItemDdysgb;
    private CanItemSwitchList mItemFdjqt;
    private CanItemPopupList mItemFwzm;
    private CanItemPopupList mItemGxhyb;
    private CanItemSwitchList mItemHys;
    private CanItemSwitchList mItemJjshbx;
    private CanItemPopupList mItemLang;
    private CanItemSwitchList mItemMqtc;
    private CanItemSwitchList mItemPljc;
    private CanItemSwitchList mItemQylkz;
    private CanItemSwitchList mItemRxd;
    private CanItemPopupList mItemSound;
    private CanItemTextBtnList mItemTybd;
    private CanItemPopupList mItemYbbjys;
    private CanItemSwitchList mItemYbgn;
    private CanItemPopupList mItemYbzm;
    private CanItemSwitchList mItemZcfz;
    private CanScrollList mManager;
    private CanDataInfo.PeugSet mSetData = new CanDataInfo.PeugSet();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.PSAGetSetup(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            this.mItemLang.SetSel(this.mSetData.Lang);
            this.mItemSound.SetSel(this.mSetData.EQ);
            this.mItemConsDW.SetSel(this.mSetData.DWFuel);
            this.mItemCmxx.SetSel(this.mSetData.DoorOpt);
            this.mItemFwzm.SetSel(this.mSetData.Fwzm);
            this.mItemBwhj.SetSel(this.mSetData.Bwhj);
            this.mItemYbzm.SetSel(this.mSetData.Ybzm);
            this.mItemRxd.SetCheck(this.mSetData.fgRxd);
            this.mItemYbgn.SetCheck(this.mSetData.YbFunc);
            this.mItemZcfz.SetCheck(this.mSetData.fgZcfz);
            this.mItemDcldty.SetCheck(this.mSetData.fgStopRadar);
            this.mItemHys.SetCheck(this.mSetData.fgRearWiper);
            this.mItemMqtc.SetCheck(this.mSetData.Mqtc);
            this.mItemFdjqt.SetCheck(this.mSetData.StopFdj);
            this.mItemDdysgb.SetSel(this.mSetData.Ddysgb);
            this.mItemJjshbx.SetCheck(this.mSetData.Jjshbx);
            this.mItemQylkz.SetCheck(this.mSetData.Qylkzxtkg);
            this.mItemPljc.SetCheck(this.mSetData.Pljkxt);
            this.mItemYbbjys.SetSel(this.mSetData.Ybbjs);
            this.mItemAutoLight.SetCheck(this.mSetData.AutoLight);
            this.mItemAutoLock.SetCheck(this.mSetData.AutoLock);
        }
        CanJni.PSAGetAdt(this.mAdtData);
        if (!i2b(this.mAdtData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAdtData.Update)) {
            this.mAdtData.Update = 0;
            LayoutUI();
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.PSAQuery(56, 0);
        Sleep(5);
        CanJni.PSAQuery(60, 0);
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
        this.mFwzmArr = new String[8];
        this.mFwzmArr[0] = getResources().getString(R.string.can_off);
        for (int i = 1; i <= 7; i++) {
            this.mFwzmArr[i] = new StringBuilder(String.valueOf(i - 1)).toString();
        }
        this.mItemLang = AddPopupItem(R.string.can_language, mLangArr, 1);
        this.mItemSound = AddPopupItem(R.string.can_eq, mSoundArr, 2);
        this.mItemConsDW = AddPopupItem(R.string.can_fuel_d_w, mConsDWArr, 11);
        this.mItemCmxx = AddPopupItem(R.string.can_door_open_opt, mCmxxArr, 14);
        this.mItemFwzm = AddPopupItem(R.string.can_env_light, this.mFwzmArr, 6);
        this.mItemBwhj = AddPopupItem(R.string.can_bwhj_light, mBwhjArr, 7);
        this.mItemYbzm = AddPopupItem(R.string.can_ybzm, mYbzmArr, 8);
        this.mItemYbgn = AddCheckItem(R.string.can_yb_func, 10);
        this.mItemRxd = AddCheckItem(R.string.can_rjxcd, 9);
        this.mItemZcfz = AddCheckItem(R.string.can_car_zcfz, 3);
        this.mItemDcldty = AddCheckItem(R.string.can_stop_radar, 4);
        this.mItemHys = AddCheckItem(R.string.can_hys, 5);
        this.mItemMqtc = AddCheckItem(R.string.can_mqtc, 12);
        this.mItemFdjqt = AddCheckItem(R.string.can_fdj_qtgn, 13);
        this.mItemTybd = AddTextBtn(R.string.can_tybd, 15);
        this.mItemDdysgb = AddPopupItem(R.string.can_car_ddycxm, mDdysgbmArr, 16);
        this.mItemJjshbx = AddCheckItem(R.string.can_jjshbx, 17);
        this.mItemQylkz = AddCheckItem(R.string.can_traction_control_sys, 18);
        this.mItemPljc = AddCheckItem(R.string.can_psa_2017_4008_pljcxt, 19);
        this.mItemYbbjys = AddPopupItem(R.string.can_psa_2017_4008_ybbjys, mYbbjysArr, 20);
        this.mItemGxhyb = AddPopupItem(R.string.can_psa_2017_4008_gxhybsz, mGxhybArr, 21);
        this.mItemAutoLight = AddCheckItem(R.string.can_auto_light, 22);
        this.mItemAutoLock = AddCheckItem(R.string.can_xczdls, 23);
        this.mItemAc = AddPopupItem(R.string.can_air_ac, mStaArr, 24);
        this.mItemDaul = AddPopupItem(R.string.can_air_dual, mStaArr, 25);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 25; i++) {
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
                if (9 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
                break;
            case 3:
                if (9 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
                break;
            case 4:
                if (9 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
                break;
            case 5:
                if (CanFunc.mFsCanTp == 58 && 9 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                } else {
                    ret = this.mAdtData.Zdyg;
                    break;
                }
            case 6:
                if (CanFunc.mFsCanTp == 58 && 9 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                } else {
                    ret = this.mAdtData.Fwzm;
                    break;
                }
            case 7:
                if (CanFunc.mFsCanTp != 58) {
                    ret = this.mAdtData.Bwhj;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 8:
                if (CanFunc.mFsCanTp == 58 && 9 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                } else {
                    ret = this.mAdtData.Ybzm;
                    break;
                }
                break;
            case 9:
                if (CanFunc.mFsCanTp == 58 && 9 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                } else {
                    ret = this.mAdtData.Rxd;
                    break;
                }
            case 10:
                if (CanFunc.mFsCanTp == 58 && 9 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                } else {
                    ret = this.mAdtData.Ybgn;
                    break;
                }
            case 11:
                ret = 1;
                break;
            case 12:
                if (9 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
                break;
            case 13:
                if (9 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
                break;
            case 14:
                if (CanFunc.mFsCanTp == 58 && 9 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                } else {
                    ret = this.mAdtData.Cmxx;
                    break;
                }
            case 15:
                ret = 1;
                break;
            case 16:
                if (CanFunc.mFsCanTp == 58 || 9 == CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
            case 17:
                if (CanJni.GetSubType() == 7) {
                    ret = 1;
                    break;
                }
                break;
            case 18:
                ret = this.mAdtData.Qylkzxtkg;
                break;
            case 19:
                ret = this.mAdtData.Pljkxt;
                break;
            case 20:
                ret = this.mAdtData.Ybbjs;
                break;
            case 21:
                ret = this.mAdtData.Gxhybsz;
                break;
            case 22:
                ret = this.mAdtData.AutoLight;
                break;
            case 23:
                ret = this.mAdtData.AutoLock;
                break;
            case 24:
                ret = this.mAdtData.AcSw;
                break;
            case 25:
                ret = this.mAdtData.DaulSw;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemLang.ShowGone(show);
                return;
            case 2:
                this.mItemSound.ShowGone(show);
                return;
            case 3:
                this.mItemZcfz.ShowGone(show);
                return;
            case 4:
                this.mItemDcldty.ShowGone(show);
                return;
            case 5:
                this.mItemHys.ShowGone(show);
                return;
            case 6:
                this.mItemFwzm.ShowGone(show);
                return;
            case 7:
                this.mItemBwhj.ShowGone(show);
                return;
            case 8:
                this.mItemYbzm.ShowGone(show);
                return;
            case 9:
                this.mItemRxd.ShowGone(show);
                return;
            case 10:
                this.mItemYbgn.ShowGone(show);
                return;
            case 11:
                this.mItemConsDW.ShowGone(show);
                return;
            case 12:
                this.mItemMqtc.ShowGone(show);
                return;
            case 13:
                this.mItemFdjqt.ShowGone(show);
                return;
            case 14:
                this.mItemCmxx.ShowGone(show);
                return;
            case 15:
                this.mItemTybd.ShowGone(show);
                return;
            case 16:
                this.mItemDdysgb.ShowGone(show);
                return;
            case 17:
                this.mItemJjshbx.ShowGone(show);
                return;
            case 18:
                this.mItemQylkz.ShowGone(show);
                return;
            case 19:
                this.mItemPljc.ShowGone(show);
                return;
            case 20:
                this.mItemYbbjys.ShowGone(show);
                return;
            case 21:
                this.mItemGxhyb.ShowGone(show);
                return;
            case 22:
                this.mItemAutoLight.ShowGone(show);
                return;
            case 23:
                this.mItemAutoLock.ShowGone(show);
                return;
            case 24:
                this.mItemAc.ShowGone(show);
                return;
            case 25:
                this.mItemDaul.ShowGone(show);
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
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                CanJni.PSACarSet(1, Neg(this.mSetData.fgZcfz));
                return;
            case 4:
                CanJni.PSACarSet(5, Neg(this.mSetData.fgStopRadar));
                return;
            case 5:
                CanJni.PSACarSet(2, Neg(this.mSetData.fgRearWiper));
                return;
            case 9:
                CanJni.PSACarSet(8, Neg(this.mSetData.fgRxd));
                return;
            case 10:
                CanJni.PSACarSet(14, Neg(this.mSetData.YbFunc));
                return;
            case 12:
                CanJni.PSACarSet(12, Neg(this.mSetData.Mqtc));
                return;
            case 13:
                CanJni.PSACarSet(13, Neg(this.mSetData.StopFdj));
                return;
            case 15:
                new CanItemMsgBox(15, this, R.string.can_sure_tybd, this, this);
                return;
            case 17:
                CanJni.PSACarSet(19, Neg(this.mSetData.Jjshbx));
                return;
            case 18:
                CanJni.PSACarSet(20, Neg(this.mSetData.Qylkzxtkg));
                return;
            case 19:
                CanJni.PSACarSet(21, Neg(this.mSetData.Pljkxt));
                return;
            case 22:
                CanJni.PSACarSet(24, Neg(this.mSetData.AutoLight));
                return;
            case 23:
                CanJni.PSACarSet(25, Neg(this.mSetData.AutoLock));
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
                CanJni.PSACarSet(11, item);
                return;
            case 2:
                CanJni.PSACarSet(9, item + 1);
                return;
            case 6:
                CanJni.PSACarSet(4, item);
                return;
            case 7:
                CanJni.PSACarSet(6, item);
                return;
            case 8:
                CanJni.PSACarSet(7, item);
                return;
            case 11:
                CanJni.PSACarSet(10, item);
                return;
            case 14:
                CanJni.PSACarSet(15, item);
                return;
            case 16:
                CanJni.PSACarSet(18, item);
                return;
            case 20:
                CanJni.PSACarSet(22, item);
                return;
            case 21:
                CanJni.PSACarSet(23, item);
                return;
            case 24:
                CanJni.PSAACSet(2, item);
                return;
            case 25:
                CanJni.PSAACSet(11, item);
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        if (param == 15) {
            CanJni.PSACarSet(16, 1);
        }
    }

    public void onCancel(int param) {
        if (param == 15) {
            CanJni.PSACarSet(16, 0);
        }
    }
}
