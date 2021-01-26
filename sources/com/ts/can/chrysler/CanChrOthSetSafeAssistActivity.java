package com.ts.can.chrysler;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanChrOthSetSafeAssistActivity extends CanChrOthBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_BLIND_SPOT = 9;
    public static final int ITEM_CAR_TYPE = 8;
    public static final int ITEM_CDPLJG = 17;
    public static final int ITEM_CDPLJZLD = 13;
    public static final int ITEM_DCSQXHSJ = 12;
    public static final int ITEM_DTYDX = 3;
    public static final int ITEM_FRONT_VOL = 6;
    public static final int ITEM_GDYDX = 4;
    public static final int ITEM_HLDBCZDFZ = 15;
    public static final int ITEM_LDLX = 1;
    public static final int ITEM_LDZDFZ = 2;
    private static final int ITEM_MAX = 22;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_PDQBFZ = 5;
    public static final int ITEM_PDSHIFT = 18;
    public static final int ITEM_PYJLBJ = 20;
    public static final int ITEM_PYJLBJYL = 21;
    public static final int ITEM_QFPZJBZDZD = 11;
    public static final int ITEM_QFPZJG = 10;
    public static final int ITEM_REAR_VOL = 7;
    public static final int ITEM_YLGYSYS = 14;
    public static final int ITEM_ZDBCZD = 16;
    public static final int ITEM_ZDZDWHSJ = 22;
    public static final int ITEM_ZXZL = 19;
    public static final String TAG = "CanChrOthSetSafeAssistActivity";
    private static final int[] mBlindSpot = {R.string.can_trunk_close, R.string.can_light, R.string.can_jp_cdjbj};
    private static final int[] mCarTypeArr = {R.string.can_car_hi_config, R.string.can_car_low_config};
    private static final int[] mCdpljg = {R.string.can_jp_early, R.string.can_cdzj, R.string.can_jp_late};
    private static final int[] mLdlxArr = {R.string.can_type_vol, R.string.can_vol_img};
    private static final int[] mPyjlbjArr = {R.string.can_trunk_close, R.string.can_type_vol, R.string.can_vol_img};
    private static final int[] mPyjlbjVolArr = {R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
    private static final int[] mQbylArr = {R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
    private static final int[] mQfpzArray = {R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_1};
    private static final int[] mZxzl = {R.string.can_golf_seat_drive_normal, R.string.can_golf_seat_drive_sport, R.string.can_comfort};
    protected CanItemPopupList mItemBlindSpot;
    protected CanItemPopupList mItemCarType;
    protected CanItemPopupList mItemCdpljg;
    protected CanItemPopupList mItemCdpljzld;
    protected CanItemSwitchList mItemDcsqxhsj;
    protected CanItemSwitchList mItemDtydx;
    protected CanItemPopupList mItemFrontVol;
    protected CanItemSwitchList mItemGdydx;
    protected CanItemSwitchList mItemHldbczdfz;
    protected CanItemPopupList mItemLdlx;
    protected CanItemSwitchList mItemLdzdfz;
    protected CanItemSwitchList mItemPDShift;
    protected CanItemSwitchList mItemPdqbfz;
    protected CanItemPopupList mItemPyjlbj;
    protected CanItemPopupList mItemPyjlbjyl;
    protected CanItemSwitchList mItemQfpzjbzdzd;
    protected CanItemPopupList mItemQfpzjg;
    protected CanItemPopupList mItemRearVol;
    protected CanItemSwitchList mItemYlgysys;
    protected CanItemSwitchList mItemZdbczd;
    protected CanItemSwitchList mItemZdzdwhsj;
    protected CanItemPopupList mItemZxzl;
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
            this.mItemLdlx.SetSel(this.mSetData.RadarShowType);
            this.mItemFrontVol.SetSel(this.mSetData.FrontVol);
            this.mItemRearVol.SetSel(this.mSetData.RearVol);
            this.mItemLdzdfz.SetCheck(this.mSetData.RearRadarAss);
            this.mItemPdqbfz.SetCheck(this.mSetData.PdqbAss);
            this.mItemDtydx.SetCheck(this.mSetData.ParkTrack);
            this.mItemGdydx.SetCheck(this.mSetData.ParkLine);
            this.mItemBlindSpot.SetSel(this.mSetData.Mdbj);
            this.mItemQfpzjg.SetSel(this.mSetData.Qfpzjg);
            this.mItemQfpzjbzdzd.SetCheck(this.mSetData.Qfpzjbzdzd);
            this.mItemDcsqxhsj.SetCheck(this.mSetData.Dcsqxhsj);
            this.mItemCdpljzld.SetSel(this.mSetData.Cdpljzld);
            this.mItemYlgysys.SetCheck(this.mSetData.Ylgysyg);
            this.mItemHldbczdfz.SetCheck(this.mSetData.RearRadarAutoPark);
            this.mItemZdbczd.SetCheck(this.mSetData.AutoPark);
            this.mItemCdpljg.SetSel(this.mSetData.Cdpljg);
            this.mItemPDShift.SetCheck(this.mSetData.PaddleShifting);
            this.mItemZxzl.SetSel(this.mSetData.PowerSteering);
            this.mItemPyjlbj.SetSel(this.mSetData.Pyjlbj);
            this.mItemPyjlbjyl.SetSel(this.mSetData.Pyjlbjyl);
            this.mItemZdzdwhsj.SetCheck(this.mSetData.Zdzdwhsj);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(64, 160);
        Sleep(10);
        Query(64, 161);
        Sleep(10);
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
        this.mItemCarType = AddPopupItem(R.string.can_car_auto, mCarTypeArr, 8);
        this.mItemBlindSpot = AddPopupItem(R.string.can_jp_mdjb, mBlindSpot, 9);
        this.mItemQfpzjg = AddPopupItem(R.string.can_jp_qfpzjg, mQfpzArray, 10);
        this.mItemQfpzjbzdzd = AddCheckItem(R.string.can_jp_qfpzzdzd, 11);
        this.mItemDcsqxhsj = AddCheckItem(R.string.can_dchsjzdqx, 12);
        this.mItemCdpljzld = AddPopupItem(R.string.can_jp_cdpljzld, mQbylArr, 13);
        this.mItemYlgysys = AddCheckItem(R.string.can_jp_ylgysyg, 14);
        this.mItemHldbczdfz = AddCheckItem(R.string.can_rear_radar_stop_assis, 15);
        this.mItemZdbczd = AddCheckItem(R.string.can_active_park, 16);
        this.mItemCdpljg = AddPopupItem(R.string.can_jp_cdpljg, mCdpljg, 17);
        this.mItemPDShift = AddCheckItem(R.string.can_paddle_shift, 18);
        this.mItemZxzl = AddPopupItem(R.string.can_power_steer, mZxzl, 19);
        this.mItemPyjlbj = AddPopupItem(R.string.can_pyjlbj, mPyjlbjArr, 20);
        this.mItemPyjlbjyl = AddPopupItem(R.string.can_pyjlbj_vol, mPyjlbjVolArr, 21);
        this.mItemZdzdwhsj = AddCheckItem(R.string.can_zdzdwhsj, 22);
        if (CanFunc.getInstance().IsCustomShow("Jeep")) {
            findViewById(R.id.can_comm_list_layout).setBackgroundResource(R.drawable.can_grand_cherokee_bg);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 22; i++) {
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
                ret = this.mAdtData.CarType;
                break;
            case 9:
                ret = this.mAdtData.Mdbj;
                break;
            case 10:
                ret = this.mAdtData.Qfpzjg;
                break;
            case 11:
                ret = this.mAdtData.Qfpzjbzdzd;
                break;
            case 12:
                ret = this.mAdtData.Dcsqxhsj;
                break;
            case 13:
                ret = this.mAdtData.Cdpljzld;
                break;
            case 14:
                ret = this.mAdtData.Ylgysyg;
                break;
            case 15:
                ret = this.mAdtData.RearRadarAutoPark;
                break;
            case 16:
                ret = this.mAdtData.AutoPark;
                break;
            case 17:
                ret = this.mAdtData.Cdpljg;
                break;
            case 18:
                ret = this.mAdtData.PaddleShifting;
                break;
            case 19:
                ret = this.mAdtData.PowerSteering;
                break;
            case 20:
                ret = this.mAdtData.Pyjlbj;
                break;
            case 21:
                ret = this.mAdtData.Pyjlbjyl;
                break;
            case 22:
                ret = this.mAdtData.Zdzdwhsj;
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
                this.mItemCarType.ShowGone(show);
                return;
            case 9:
                this.mItemBlindSpot.ShowGone(show);
                return;
            case 10:
                this.mItemQfpzjg.ShowGone(show);
                return;
            case 11:
                this.mItemQfpzjbzdzd.ShowGone(show);
                return;
            case 12:
                this.mItemDcsqxhsj.ShowGone(show);
                return;
            case 13:
                this.mItemCdpljzld.ShowGone(show);
                return;
            case 14:
                this.mItemYlgysys.ShowGone(show);
                return;
            case 15:
                this.mItemHldbczdfz.ShowGone(show);
                return;
            case 16:
                this.mItemZdbczd.ShowGone(show);
                return;
            case 17:
                this.mItemCdpljg.ShowGone(show);
                return;
            case 18:
                this.mItemPDShift.ShowGone(show);
                return;
            case 19:
                this.mItemZxzl.ShowGone(show);
                return;
            case 20:
                this.mItemPyjlbj.ShowGone(show);
                return;
            case 21:
                this.mItemPyjlbjyl.ShowGone(show);
                return;
            case 22:
                this.mItemZdzdwhsj.ShowGone(show);
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
            case 2:
                CarSWSet(163, this.mSetData.RearRadarAss);
                return;
            case 3:
                CarSWSet(164, this.mSetData.ParkTrack);
                return;
            case 4:
                CarSWSet(165, this.mSetData.ParkLine);
                return;
            case 5:
                CarSWSet(166, this.mSetData.PdqbAss);
                return;
            case 11:
                CarSWSet(171, this.mSetData.Qfpzjbzdzd);
                return;
            case 12:
                CarSWSet(173, this.mSetData.Dcsqxhsj);
                return;
            case 14:
                CarSWSet(175, this.mSetData.Ylgysyg);
                return;
            case 15:
                CarSWSet(Can.CAN_VOLKS_XP, this.mSetData.RearRadarAutoPark);
                return;
            case 16:
                CarSWSet(Can.CAN_SITECHDEV_CW, this.mSetData.AutoPark);
                return;
            case 18:
                CarSet(167, Neg(this.mSetData.PaddleShifting));
                return;
            case 22:
                CarSet(Can.CAN_FORD_WC, Neg(this.mSetData.Zdzdwhsj));
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
                CarSet(160, item);
                return;
            case 6:
                CarSet(161, item);
                return;
            case 7:
                CarSet(162, item);
                return;
            case 8:
                CarTypeSet(item);
                return;
            case 9:
                CarSet(172, item);
                return;
            case 10:
                CarSet(170, item);
                return;
            case 13:
                CarSet(174, item);
                return;
            case 17:
                CarSet(169, item);
                return;
            case 19:
                CarSet(168, item);
                return;
            case 20:
                CarSet(Can.CAN_BJ20_WC, item);
                return;
            case 21:
                CarSet(Can.CAN_DF_WC, item);
                return;
            default:
                return;
        }
    }
}
