package com.ts.can.chrysler.rz;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;
import com.ts.main.common.MainSet;

public class CanRZygSetSafeAssistActivity extends CanRZygBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CAR_TYPE = 17;
    public static final int ITEM_CDPLJG = 8;
    public static final int ITEM_CDPLJZLD = 9;
    public static final int ITEM_DTYDX = 3;
    public static final int ITEM_DZZCZD = 14;
    public static final int ITEM_FMQKG = 16;
    public static final int ITEM_FRONT_VOL = 6;
    public static final int ITEM_GDYDX = 4;
    public static final int ITEM_HSJTGJ = 18;
    public static final int ITEM_HZDFZ = 24;
    public static final int ITEM_JGZDZD = 21;
    public static final int ITEM_LDLX = 1;
    public static final int ITEM_LDZDFZ = 2;
    private static final int ITEM_MAX = 24;
    public static final int ITEM_MDJB = 13;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_PDQBFZ = 5;
    public static final int ITEM_QFPZJG = 11;
    public static final int ITEM_QFPZJG2 = 20;
    public static final int ITEM_QFPZLMD = 22;
    public static final int ITEM_QFPZZDZD = 10;
    public static final int ITEM_REAR_VOL = 7;
    public static final int ITEM_TYFZ = 19;
    public static final int ITEM_YLGYSYG = 12;
    public static final int ITEM_YXBCYS = 15;
    public static final String TAG = "CanRZygSetSafeAssistActivity";
    private static final String[] mCarTypeArr = {MainSet.SP_XPH5, MainSet.SP_RLF_KORON};
    private static final int[] mCdpljgArr = {R.string.can_jp_early, R.string.can_ac_mid, R.string.can_jp_late};
    private static final int[] mCdpljzldArr = {R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
    private static final int[] mLdlxArr = {R.string.can_type_vol, R.string.can_vol_img};
    private static final int[] mMdjbArr = {R.string.can_off, R.string.can_light, R.string.can_jp_cdjbj};
    private static final int[] mQbylArr = {R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
    private static final int[] mQfpzjgArr = {R.string.can_off, R.string.can_warn, R.string.can_jp_jghzdzd};
    private static final int[] mQfpzlmdArr = {R.string.can_jp_early, R.string.can_ac_mid, R.string.can_jp_late};
    private static final int[] mYlgysygArr = {R.string.can_off, R.string.can_on};
    protected CanItemPopupList mItemCarType;
    protected CanItemPopupList mItemCdpljg;
    protected CanItemPopupList mItemCdpljzld;
    protected CanItemSwitchList mItemDtydx;
    protected CanItemSwitchList mItemDzzczd;
    protected CanItemSwitchList mItemFmqkg;
    protected CanItemPopupList mItemFrontVol;
    protected CanItemSwitchList mItemGdydx;
    protected CanItemSwitchList mItemHsjtgj;
    protected CanItemSwitchList mItemHzdfz;
    protected CanItemSwitchList mItemJgzdzd;
    protected CanItemPopupList mItemLdlx;
    protected CanItemSwitchList mItemLdzdfz;
    protected CanItemPopupList mItemMdjb;
    protected CanItemSwitchList mItemPdqbfz;
    protected CanItemSwitchList mItemQfpzjg;
    protected CanItemPopupList mItemQfpzjg2;
    protected CanItemPopupList mItemQfpzlmd;
    protected CanItemSwitchList mItemQfpzzdzd;
    protected CanItemPopupList mItemRearVol;
    protected CanItemSwitchList mItemTyfz;
    protected CanItemPopupList mItemYlgysyg;
    protected CanItemSwitchList mItemYxbcys;
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
        if (!i2b(this.mSetData.DrvAssUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.DrvAssUpdate)) {
            this.mSetData.DrvAssUpdate = 0;
            this.mItemLdlx.SetSel(this.mSetData.RadarShowType - 1);
            this.mItemFrontVol.SetSel(this.mSetData.FrontVol - 1);
            this.mItemRearVol.SetSel(this.mSetData.RearVol - 1);
            this.mItemLdzdfz.SetCheck(this.mSetData.RearRadarAss);
            this.mItemPdqbfz.SetCheck(this.mSetData.PdqbAss);
            this.mItemDtydx.SetCheck(this.mSetData.ParkTrack);
            this.mItemGdydx.SetCheck(this.mSetData.ParkLine);
            this.mItemCdpljg.SetSel(this.mSetData.Cdpljg);
            this.mItemCdpljzld.SetSel(this.mSetData.Cdpljzld);
            this.mItemQfpzzdzd.SetCheck(this.mSetData.Qfpzjbzdzd);
            this.mItemQfpzjg.SetCheck(this.mSetData.Qfpzjg);
            this.mItemYlgysyg.SetSel(this.mSetData.Ylgysyg);
            this.mItemMdjb.SetSel(this.mSetData.Mdbj - 1);
            this.mItemDzzczd.SetCheck(this.mSetData.Dzzczd);
            this.mItemYxbcys.SetCheck(this.mSetData.Yxbcys);
            this.mItemFmqkg.SetCheck(this.mSetData.Fmqkg);
            this.mItemHsjtgj.SetCheck(this.mSetData.Hsjtgj);
            this.mItemTyfz.SetCheck(this.mSetData.Tyfz);
            this.mItemJgzdzd.SetCheck(this.mSetData.JgZdzd);
            this.mItemQfpzjg2.SetSel(this.mSetData.Qfpzjg2);
            this.mItemQfpzlmd.SetSel(this.mSetData.Qfpzlmd);
            this.mItemHzdfz.SetCheck(this.mSetData.RearRadarAutoPark);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(64, Can.CAN_CHANA_CS75_WC);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
        ResetData(false);
        QueryData();
        Log.d(TAG, "-----onResume-----");
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
        this.mItemLdlx = AddPopupItem(R.string.can_psbcld, mLdlxArr, 1);
        this.mItemFrontVol = AddPopupItem(R.string.can_qpsyl, mQbylArr, 6);
        this.mItemRearVol = AddPopupItem(R.string.can_hpsyl, mQbylArr, 7);
        this.mItemLdzdfz = AddCheckItem(R.string.can_hpsbcfz, 2);
        this.mItemPdqbfz = AddCheckItem(R.string.can_pdqbfz, 5);
        this.mItemDtydx = AddCheckItem(R.string.can_pvyxdtydx, 3);
        this.mItemGdydx = AddCheckItem(R.string.can_pvyxgdydx, 4);
        this.mItemCdpljg = AddPopupItem(R.string.can_jp_cdpljg, mCdpljgArr, 8);
        this.mItemCdpljzld = AddPopupItem(R.string.can_jp_cdpljzld, mCdpljzldArr, 9);
        this.mItemMdjb = AddPopupItem(R.string.can_jp_mdjb, mMdjbArr, 13);
        this.mItemQfpzzdzd = AddCheckItem(R.string.can_jp_qfpzzdzd, 10);
        this.mItemQfpzjg = AddCheckItem(R.string.can_jp_qfpzjg, 11);
        this.mItemYlgysyg = AddPopupItem(R.string.can_jp_ylgysyg, mYlgysygArr, 12);
        this.mItemDzzczd = AddCheckItem(R.string.can_jp_Dzzczd, 14);
        this.mItemYxbcys = AddCheckItem(R.string.can_jeep_znz_yxbcys, 15);
        this.mItemFmqkg = AddCheckItem(R.string.can_fmqkg, 16);
        this.mItemHsjtgj = AddCheckItem(R.string.can_jp_hsjtgj, 18);
        this.mItemTyfz = AddCheckItem(R.string.can_tyfz, 19);
        this.mItemJgzdzd = AddCheckItem(R.string.can_jp_jghzdzd, 21);
        this.mItemQfpzjg2 = AddPopupItem(R.string.can_jp_qfpzjg, mQfpzjgArr, 20);
        this.mItemQfpzlmd = AddPopupItem(R.string.can_qfzfz_qpzyjlmd, mQfpzlmdArr, 22);
        this.mItemHzdfz = AddCheckItem(R.string.can_jp_hzdfz, 24);
        this.mItemCarType = AddPopupItem(R.string.can_car_auto, mCarTypeArr, 17);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 24; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.RadarShowType;
                break;
            case 2:
                ret = this.mAdtData.RearRadarAss;
                break;
            case 3:
                ret = this.mAdtData.ParkTrack;
                break;
            case 4:
                ret = this.mAdtData.ParkLine;
                break;
            case 5:
                ret = this.mAdtData.PdqbAss;
                break;
            case 6:
                ret = this.mAdtData.FrontVol;
                break;
            case 7:
                ret = this.mAdtData.RearVol;
                break;
            case 8:
                ret = this.mAdtData.Cdpljg;
                break;
            case 9:
                ret = this.mAdtData.Cdpljzld;
                break;
            case 10:
                ret = this.mAdtData.Qfpzjbzdzd;
                break;
            case 11:
                ret = this.mAdtData.Qfpzjg;
                break;
            case 12:
                ret = this.mAdtData.Ylgysyg;
                break;
            case 13:
                ret = this.mAdtData.Mdbj;
                break;
            case 14:
                ret = this.mAdtData.Dzzczd;
                break;
            case 15:
                ret = this.mAdtData.Yxbcys;
                break;
            case 16:
                ret = this.mAdtData.Fmqkg;
                break;
            case 17:
                ret = this.mAdtData.CarType;
                break;
            case 18:
                ret = this.mAdtData.Hsjtgj;
                break;
            case 19:
                ret = this.mAdtData.Tyfz;
                break;
            case 20:
                ret = this.mAdtData.Qfpzjg2;
                break;
            case 21:
                ret = this.mAdtData.JgZdzd;
                break;
            case 22:
                ret = this.mAdtData.Qfpzlmd;
                break;
            case 24:
                ret = this.mAdtData.RearRadarAutoPark;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemLdlx.ShowGone(show);
                return;
            case 2:
                this.mItemLdzdfz.ShowGone(show);
                return;
            case 3:
                this.mItemDtydx.ShowGone(show);
                return;
            case 4:
                this.mItemGdydx.ShowGone(show);
                return;
            case 5:
                this.mItemPdqbfz.ShowGone(show);
                return;
            case 6:
                this.mItemFrontVol.ShowGone(show);
                return;
            case 7:
                this.mItemRearVol.ShowGone(show);
                return;
            case 8:
                this.mItemCdpljg.ShowGone(show);
                return;
            case 9:
                this.mItemCdpljzld.ShowGone(show);
                return;
            case 10:
                this.mItemQfpzzdzd.ShowGone(show);
                return;
            case 11:
                this.mItemQfpzjg.ShowGone(show);
                return;
            case 12:
                this.mItemYlgysyg.ShowGone(show);
                return;
            case 13:
                this.mItemMdjb.ShowGone(show);
                return;
            case 14:
                this.mItemDzzczd.ShowGone(show);
                return;
            case 15:
                this.mItemYxbcys.ShowGone(show);
                return;
            case 16:
                this.mItemFmqkg.ShowGone(show);
                return;
            case 17:
                this.mItemCarType.ShowGone(show);
                return;
            case 18:
                this.mItemHsjtgj.ShowGone(show);
                return;
            case 19:
                this.mItemTyfz.ShowGone(show);
                return;
            case 20:
                this.mItemQfpzjg2.ShowGone(show);
                return;
            case 21:
                this.mItemJgzdzd.ShowGone(show);
                return;
            case 22:
                this.mItemQfpzlmd.ShowGone(show);
                return;
            case 24:
                this.mItemHzdfz.ShowGone(show);
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

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CarSWSet(9, this.mSetData.RearRadarAss);
                return;
            case 3:
                CarSet(5, Neg(this.mSetData.ParkTrack));
                return;
            case 4:
                CarSet(8, Neg(this.mSetData.ParkLine));
                return;
            case 5:
                CarSWSet(7, this.mSetData.PdqbAss);
                return;
            case 10:
                CarSWSet(15, this.mSetData.Qfpzjbzdzd);
                return;
            case 11:
                CarSWSet(16, this.mSetData.Qfpzjg);
                return;
            case 13:
                CarSWSet(14, this.mSetData.Mdbj);
                return;
            case 14:
                CarSWSet(128, this.mSetData.Dzzczd);
                return;
            case 15:
                CarSet(97, Neg(this.mSetData.Yxbcys));
                return;
            case 16:
                CarSet(96, Neg(this.mSetData.Fmqkg) + 1);
                return;
            case 18:
                CarSet(81, Neg(this.mSetData.Hsjtgj) + 1);
                return;
            case 19:
                CarSet(133, Neg(this.mSetData.Tyfz) + 1);
                return;
            case 21:
                CarSet(131, Neg(this.mSetData.JgZdzd) + 1);
                return;
            case 24:
                CarSet(132, Neg(this.mSetData.RearRadarAutoPark) + 1);
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
                CarSet(1, item + 1);
                return;
            case 6:
                CarSet(2, item);
                return;
            case 7:
                CarSet(3, item);
                return;
            case 8:
                CarSet(12, item);
                return;
            case 9:
                CarSet(13, item);
                return;
            case 12:
                CarSet(6, item + 1);
                return;
            case 13:
                CarSet(14, item + 1);
                return;
            case 17:
                CarTypeSet(96, item);
                return;
            case 20:
                CarSet(129, item);
                return;
            case 22:
                CarSet(130, item);
                return;
            default:
                return;
        }
    }
}
