package com.ts.can.psa.rzc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanPSARzcCarSetActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2 {
    public static final int ITEM_AC = 24;
    public static final int ITEM_AUTOLIGHT = 22;
    public static final int ITEM_AUTOLOCK = 23;
    public static final int ITEM_BWHJ = 7;
    public static final int ITEM_BXFZ = 35;
    public static final int ITEM_CAR_TYPE = 27;
    public static final int ITEM_CMXX = 14;
    public static final int ITEM_CONSDW = 11;
    public static final int ITEM_DAUL = 25;
    public static final int ITEM_DCLDTY = 4;
    public static final int ITEM_DDHBXG = 30;
    public static final int ITEM_DDYSGB = 16;
    public static final int ITEM_FDJQT = 13;
    public static final int ITEM_FPZ = 36;
    public static final int ITEM_FWZM = 6;
    public static final int ITEM_GXHYB = 21;
    public static final int ITEM_HBXZDKQ = 31;
    public static final int ITEM_HSJZSY = 34;
    public static final int ITEM_HYS = 5;
    public static final int ITEM_JJSHBX = 17;
    public static final int ITEM_JJZD = 28;
    public static final int ITEM_JMDBXFPZFS = 37;
    public static final int ITEM_JTBZSB = 33;
    public static final int ITEM_JYZDZDHSJ = 29;
    public static final int ITEM_LANG = 1;
    private static final int ITEM_MAX = 40;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_MQTC = 12;
    public static final int ITEM_PLJC = 19;
    public static final int ITEM_QYLKZ = 18;
    public static final int ITEM_RXD = 9;
    public static final int ITEM_SDZXDD = 26;
    public static final int ITEM_SOUND = 2;
    public static final int ITEM_TYBD = 15;
    public static final int ITEM_WDDW = 40;
    public static final int ITEM_WYSJRXT = 38;
    public static final int ITEM_XSTS = 32;
    public static final int ITEM_YBBJYS = 20;
    public static final int ITEM_YBGN = 10;
    public static final int ITEM_YBZM = 8;
    public static final int ITEM_YJGZNFZXT = 39;
    public static final int ITEM_ZCFZ = 3;
    public static final String TAG = "CanPSARzcCarSetActivity";
    private static final int[] mBwhjArr = {R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_60s};
    private static String[] mCarTypeArr;
    private static final int[] mCmxxArr = {R.string.can_jsym, R.string.can_sym};
    private static final String[] mConsDWArr = {"KM/L-KM", "L/100KM-KM", "MPG"};
    private static final int[] mDdysgbmArr = {R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_60s};
    private static final int[] mGxhybArr = {R.string.can_psa_2017_4008_gxhybsz_0, R.string.can_psa_2017_4008_gxhybsz_1, R.string.can_psa_2017_4008_gxhybsz_2, R.string.can_psa_2017_4008_gxhybsz_3, R.string.can_psa_2017_4008_gxhybsz_4, R.string.can_psa_2017_4008_gxhybsz_5, R.string.can_psa_2017_4008_gxhybsz_6, R.string.can_psa_2017_4008_gxhybsz_7, R.string.can_psa_2017_4008_gxhybsz_8, R.string.can_psa_2017_4008_gxhybsz_9};
    private static final int[] mJjzdArr = {R.string.can_off, R.string.can_sdqfwxjgjl_3, R.string.can_cdpyyzxt_1, R.string.can_sdqfwxjgjl_1};
    private static final int[] mLangArr = {R.string.lang_en_uk, R.string.lang_cn, R.string.lang_francais};
    private static final int[] mSoundArr = {R.string.can_eq_classic, R.string.can_eq_crystal, R.string.can_eq_urban, R.string.can_eq_jungle};
    private static final int[] mStaArr = {R.string.can_off, R.string.can_on};
    private static final int[] mWddwArr = {R.string.can_temperature_c, R.string.can_temperature_f};
    private static final int[] mYbbjysArr = {R.string.can_psa_2017_4008_ybbjys_1, R.string.can_psa_2017_4008_ybbjys_6, R.string.can_psa_2017_4008_ybbjys_7, R.string.can_psa_2017_4008_ybbjys_3, R.string.can_psa_2017_4008_ybbjys_4, R.string.can_psa_2017_4008_ybbjys_5, R.string.can_psa_2017_4008_ybbjys_2};
    private static final int[] mYbzmArr = {R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_60s};
    private CanDataInfo.PeugAdt mAdtData = new CanDataInfo.PeugAdt();
    private String[] mFwzmArr;
    private CanItemPopupList mItemAc;
    private CanItemSwitchList mItemAutoLight;
    private CanItemSwitchList mItemAutoLock;
    private CanItemPopupList mItemBwhj;
    private CanItemSwitchList mItemBxfz;
    private CanItemPopupList mItemCarType;
    private CanItemPopupList mItemCmxx;
    private CanItemPopupList mItemConsDW;
    private CanItemPopupList mItemDaul;
    private CanItemSwitchList mItemDcldty;
    private CanItemSwitchList mItemDdhbxg;
    private CanItemPopupList mItemDdysgb;
    private CanItemSwitchList mItemFdjqt;
    private CanItemSwitchList mItemFpz;
    private CanItemPopupList mItemFwzm;
    private CanItemPopupList mItemGxhyb;
    private CanItemSwitchList mItemHbxzdkq;
    private CanItemSwitchList mItemHsjzsy;
    private CanItemSwitchList mItemHys;
    private CanItemSwitchList mItemJjshbx;
    private CanItemPopupList mItemJjzd;
    private CanItemSwitchList mItemJmdbxfpzfs;
    private CanItemSwitchList mItemJtbzsb;
    private CanItemSwitchList mItemJyzdzdhsj;
    private CanItemPopupList mItemLang;
    private CanItemSwitchList mItemMqtc;
    private CanItemSwitchList mItemPljc;
    private CanItemSwitchList mItemQylkz;
    private CanItemSwitchList mItemRxd;
    private CanItemSwitchList mItemSdzxdd;
    private CanItemPopupList mItemSound;
    private CanItemTextBtnList mItemTybd;
    private CanItemPopupList mItemWddw;
    private CanItemSwitchList mItemWysjrxt;
    private CanItemSwitchList mItemXsts;
    private CanItemPopupList mItemYbbjys;
    private CanItemSwitchList mItemYbgn;
    private CanItemPopupList mItemYbzm;
    private CanItemSwitchList mItemYjgznfzxt;
    private CanItemSwitchList mItemZcfz;
    private CanScrollList mManager;
    private CanDataInfo.PeugSet mSetData = new CanDataInfo.PeugSet();
    private CanDataInfo.PsaRzcSet2 mSetData2 = new CanDataInfo.PsaRzcSet2();
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
            int temp = this.mSetData.Ybbjs;
            if (this.mSetData.Ybbjs >= 5) {
                temp = (this.mSetData.Ybbjs + 3) - 5;
            }
            this.mItemYbbjys.SetSel(temp);
            this.mItemAutoLight.SetCheck(this.mSetData.AutoLight);
            this.mItemAutoLock.SetCheck(this.mSetData.AutoLock);
            this.mItemSdzxdd.SetCheck(this.mSetData.Sdzxdd);
            this.mItemJjzd.SetSel(this.mSetData.Jjzd);
            this.mItemJyzdzdhsj.SetCheck(this.mSetData.Jyzdzdhsj);
            this.mItemDdhbxg.SetCheck(this.mSetData.Ddhbxg);
            this.mItemHbxzdkq.SetCheck(this.mSetData.Hbxzdkq);
            this.mItemXsts.SetCheck(this.mSetData.Xsts);
            this.mItemJtbzsb.SetCheck(this.mSetData.Jtbzsb);
            this.mItemHsjzsy.SetCheck(this.mSetData.Hsjzsy);
            this.mItemWddw.SetSel(this.mSetData.Wddw);
        }
        CanJni.PsaRzcGetCarSet2(this.mSetData2);
        if (!i2b(this.mSetData2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData2.Update)) {
            this.mSetData2.Update = 0;
            this.mItemBxfz.SetCheck(this.mSetData2.Bxfz);
            this.mItemFpz.SetCheck(this.mSetData2.Fpz);
            this.mItemJmdbxfpzfs.SetCheck(this.mSetData2.Jmdbxfpzfs);
            this.mItemWysjrxt.SetCheck(this.mSetData2.Wysjrxt);
            this.mItemYjgznfzxt.SetCheck(this.mSetData2.Yjgznfzxt);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.PSAQuery(56, 0);
        Sleep(5);
        CanJni.PSAQuery(65, 0);
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
        this.mItemSdzxdd = AddCheckItem(R.string.can_sdzxdd, 26);
        mCarTypeArr = getResources().getStringArray(R.array.can_psa_rzc_cartype);
        this.mItemCarType = AddPopupItem(R.string.can_car_type_select, mCarTypeArr, 27);
        this.mItemJjzd = AddPopupItem(R.string.can_jjzd, mJjzdArr, 28);
        this.mItemJyzdzdhsj = AddCheckItem(R.string.can_jyzdzdhsj, 29);
        this.mItemDdhbxg = AddCheckItem(R.string.can_ddhbxg, 30);
        this.mItemHbxzdkq = AddCheckItem(R.string.can_hbxzdkq, 31);
        this.mItemXsts = AddCheckItem(R.string.can_psa_wc_xsts, 32);
        this.mItemJtbzsb = AddCheckItem(R.string.can_traffice_sign_rec, 33);
        this.mItemHsjzsy = AddCheckItem(R.string.can_hsjzsy, 34);
        this.mItemBxfz = AddCheckItem(R.string.can_bxfz, 35);
        this.mItemFpz = AddCheckItem(R.string.can_cds, 36);
        this.mItemJmdbxfpzfs = AddCheckItem(R.string.can_jmdbxfpzfs, 37);
        this.mItemWysjrxt = AddCheckItem(R.string.can_wysjr, 38);
        this.mItemYjgznfzxt = AddCheckItem(R.string.can_yjgznfzxt, 39);
        this.mItemWddw = AddPopupItem(R.string.can_temp_dw, mWddwArr, 40);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 40; i++) {
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
                ret = 1;
                break;
            case 14:
                ret = 1;
                break;
            case 15:
                ret = 1;
                break;
            case 16:
                ret = 0;
                break;
            case 17:
                ret = 1;
                break;
            case 18:
                ret = 1;
                break;
            case 19:
                ret = 1;
                break;
            case 20:
                ret = 1;
                break;
            case 21:
                ret = 1;
                break;
            case 22:
                ret = 0;
                break;
            case 23:
                ret = 0;
                break;
            case 24:
                ret = 0;
                break;
            case 25:
                ret = 0;
                break;
            case 26:
                ret = 1;
                break;
            case 27:
                ret = 1;
                break;
            case 28:
                ret = 1;
                break;
            case 29:
                ret = 1;
                break;
            case 30:
                ret = 1;
                break;
            case 31:
                ret = 1;
                break;
            case 32:
                ret = 1;
                break;
            case 33:
                ret = 1;
                break;
            case 34:
                ret = 1;
                break;
            case 35:
                ret = 1;
                break;
            case 36:
                ret = 1;
                break;
            case 37:
                ret = 1;
                break;
            case 38:
                ret = 1;
                break;
            case 39:
                ret = 1;
                break;
            case 40:
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
            case 26:
                this.mItemSdzxdd.ShowGone(show);
                return;
            case 27:
                this.mItemCarType.ShowGone(show);
                return;
            case 28:
                this.mItemJjzd.ShowGone(show);
                return;
            case 29:
                this.mItemJyzdzdhsj.ShowGone(show);
                return;
            case 30:
                this.mItemDdhbxg.ShowGone(show);
                return;
            case 31:
                this.mItemHbxzdkq.ShowGone(show);
                return;
            case 32:
                this.mItemXsts.ShowGone(show);
                return;
            case 33:
                this.mItemJtbzsb.ShowGone(show);
                return;
            case 34:
                this.mItemHsjzsy.ShowGone(show);
                return;
            case 35:
                this.mItemBxfz.ShowGone(show);
                return;
            case 36:
                this.mItemFpz.ShowGone(show);
                return;
            case 37:
                this.mItemJmdbxfpzfs.ShowGone(show);
                return;
            case 38:
                this.mItemWysjrxt.ShowGone(show);
                return;
            case 39:
                this.mItemYjgznfzxt.ShowGone(show);
                return;
            case 40:
                this.mItemWddw.ShowGone(show);
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
                CanJni.PSACarSet(6, Neg(this.mSetData.fgStopRadar));
                return;
            case 5:
                CanJni.PSACarSet(2, Neg(this.mSetData.fgRearWiper));
                return;
            case 9:
                CanJni.PSACarSet(3, Neg(this.mSetData.fgRxd));
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
                CanJni.PSACarSet(22, Neg(this.mSetData.Qylkzxtkg));
                return;
            case 19:
                CanJni.PSACarSet(21, Neg(this.mSetData.Pljkxt));
                return;
            case 26:
                CanJni.PSACarSet(18, Neg(this.mSetData.Sdzxdd));
                return;
            case 29:
                CanJni.PSACarSet(25, Neg(this.mSetData.Jyzdzdhsj));
                return;
            case 30:
                CanJni.PSACarSet(26, Neg(this.mSetData.Ddhbxg));
                return;
            case 31:
                CanJni.PSACarSet(27, Neg(this.mSetData.Hbxzdkq));
                return;
            case 32:
                CanJni.PSACarSet(28, Neg(this.mSetData.Xsts));
                return;
            case 33:
                CanJni.PSACarSet(29, Neg(this.mSetData.Jtbzsb));
                return;
            case 34:
                CanJni.PSACarSet(30, Neg(this.mSetData.Hsjzsy));
                return;
            case 35:
                CanJni.PSACarSet(31, Neg(this.mSetData2.Bxfz));
                return;
            case 36:
                CanJni.PSACarSet(32, Neg(this.mSetData2.Fpz));
                return;
            case 37:
                CanJni.PSACarSet(35, Neg(this.mSetData2.Jmdbxfpzfs));
                return;
            case 38:
                CanJni.PSACarSet(33, Neg(this.mSetData2.Wysjrxt));
                return;
            case 39:
                CanJni.PSACarSet(34, Neg(this.mSetData2.Yjgznfzxt));
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
                CanJni.PSACarSet(5, item);
                return;
            case 7:
                CanJni.PSACarSet(7, item);
                return;
            case 8:
                CanJni.PSACarSet(8, item);
                return;
            case 11:
                CanJni.PSACarSet(10, item);
                return;
            case 14:
                CanJni.PSACarSet(15, item);
                return;
            case 20:
                int cmd = item;
                if (item == 1) {
                    cmd = 2;
                } else if (item == 2) {
                    cmd = 3;
                } else if (item == 3) {
                    cmd = 6;
                } else if (item == 4) {
                    cmd = 7;
                } else if (item == 5) {
                    cmd = 8;
                } else if (item == 6) {
                    cmd = 1;
                }
                CanJni.PSACarSet(23, cmd);
                return;
            case 21:
                if (item == 0) {
                    CanJni.PsaMeterSet(3, 2);
                    return;
                } else if (item == 1) {
                    CanJni.PsaMeterSet(3, 1);
                    return;
                } else if (item == 2) {
                    CanJni.PsaMeterSet(2, 3);
                    return;
                } else if (item == 3) {
                    CanJni.PsaMeterSet(2, 1);
                    return;
                } else if (item == 4) {
                    CanJni.PsaMeterSet(1, 3);
                    return;
                } else if (item == 5) {
                    CanJni.PsaMeterSet(1, 2);
                    return;
                } else if (item == 6) {
                    CanJni.PsaMeterSet(4, 2);
                    return;
                } else if (item == 7) {
                    CanJni.PsaMeterSet(4, 3);
                    return;
                } else if (item == 8) {
                    CanJni.PsaMeterSet(5, 2);
                    return;
                } else if (item == 9) {
                    CanJni.PsaMeterSet(5, 3);
                    return;
                } else {
                    return;
                }
            case 27:
                if (CanJni.GetSubType() == 3) {
                    CanJni.PsaCarTypeSet(4, item + 1);
                    return;
                } else {
                    CanJni.PsaCarTypeSet(3, item + 1);
                    return;
                }
            case 28:
                CanJni.PSACarSet(24, item);
                return;
            case 40:
                CanJni.PSACarSet(20, item);
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
