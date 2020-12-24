package com.ts.can.geely.yjx1;

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

public class CanGeelyYjX1CarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_BJJL = 38;
    public static final int ITEM_BSCMZDGC = 9;
    public static final int ITEM_BSCMZDGC2 = 53;
    public static final int ITEM_BSGBTCZYL = 10;
    public static final int ITEM_BSHZDXMWZD = 5;
    public static final int ITEM_BSHZDXMWZD2 = 33;
    public static final int ITEM_CSSZ = 19;
    public static final int ITEM_CSSZ2 = 48;
    public static final int ITEM_DZZLMSXZ = 7;
    public static final int ITEM_ESC = 20;
    public static final int ITEM_HSJZDZD = 14;
    public static final int ITEM_HSJZDZD2 = 54;
    public static final int ITEM_KMZXDSXTS = 3;
    public static final int ITEM_LANG = 39;
    private static final int ITEM_MAX = 54;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_MKZXDSS = 17;
    public static final int ITEM_MKZXDSS2 = 50;
    public static final int ITEM_QPZYJ = 35;
    public static final int ITEM_RJXCD = 11;
    public static final int ITEM_SFTSLX = 12;
    public static final int ITEM_SPEED_LOCK = 1;
    public static final int ITEM_SSZDGBWZD = 18;
    public static final int ITEM_SSZDGBWZD2 = 52;
    public static final int ITEM_STOP_UNLOK = 2;
    public static final int ITEM_TQMS = 13;
    public static final int ITEM_XDZDJS = 15;
    public static final int ITEM_XHJS = 32;
    public static final int ITEM_XHJS2 = 49;
    public static final int ITEM_YKLSFK = 4;
    public static final int ITEM_YKLSFK2 = 8;
    public static final int ITEM_YKSSSY = 16;
    public static final int ITEM_YKSSSY2 = 51;
    public static final int ITEM_ZDJJZD = 34;
    public static final int ITEM_ZDPHJSXT = 37;
    public static final int ITEM_ZNWDD = 6;
    public static final int ITEM_ZNYGDGCHCTJXT = 36;
    public static final String TAG = "CanGeelyYjX1CarInfoActivity";
    private static final int[] mBjjlArr = {R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_1};
    private static final int[] mDzzlmsArr = {R.string.can_comfort, R.string.can_sport};
    private static final int[] mLangArr = {R.string.lang_cn, R.string.lang_en_uk};
    private static final int[] mSftsArr = {R.string.can_dghlb, R.string.can_only_light};
    protected CanItemPopupList mItemBjjl;
    protected CanItemSwitchList mItemBscmzdgc;
    protected CanItemSwitchList mItemBscmzdgc2;
    protected CanItemSwitchList mItemBsgbtczyl;
    protected CanItemSwitchList mItemBshzdxmwzd;
    protected CanItemSwitchList mItemBshzdxmwzd2;
    protected CanItemSwitchList mItemCssz;
    protected CanItemSwitchList mItemCssz2;
    protected CanItemPopupList mItemDzzlmsxz;
    protected CanItemSwitchList mItemEsc;
    protected CanItemSwitchList mItemHsjzdzd;
    protected CanItemSwitchList mItemHsjzdzd2;
    protected CanItemSwitchList mItemKmzxdsxts;
    protected CanItemPopupList mItemLang;
    protected CanItemSwitchList mItemMkzxdss;
    protected CanItemSwitchList mItemMkzxdss2;
    protected CanItemSwitchList mItemQpzyj;
    protected CanItemSwitchList mItemRjxcd;
    protected CanItemPopupList mItemSftslx;
    private CanItemSwitchList mItemSpeedLock;
    protected CanItemSwitchList mItemSszdgbwzd;
    protected CanItemSwitchList mItemSszdgbwzd2;
    protected CanItemSwitchList mItemStopUnlok;
    protected CanItemSwitchList mItemTqms;
    protected CanItemSwitchList mItemXdzdjs;
    protected CanItemSwitchList mItemXhjs;
    protected CanItemSwitchList mItemXhjs2;
    protected CanItemSwitchList mItemYklsfk;
    protected CanItemSwitchList mItemYklsfk2;
    protected CanItemSwitchList mItemYksssy;
    protected CanItemSwitchList mItemYksssy2;
    protected CanItemSwitchList mItemZdjjzd;
    protected CanItemSwitchList mItemZdphjsxt;
    protected CanItemSwitchList mItemZnwdd;
    protected CanItemSwitchList mItemZnygdgchctjxt;
    protected CanDataInfo.Geely_LangSet mLangData = new CanDataInfo.Geely_LangSet();
    private CanScrollList mManager;
    protected CanDataInfo.Geely_CarSet mSetData = new CanDataInfo.Geely_CarSet();
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
            return 0;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GeelyGetCarSet(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            this.mItemSpeedLock.SetCheck(SwSet(this.mSetData.Csszgn));
            this.mItemStopUnlok.SetCheck(SwSet(this.mSetData.Tcjs));
            this.mItemKmzxdsxts.SetCheck(SwSet(this.mSetData.Kmzxdssts));
            this.mItemYklsfk.SetCheck(SwSet(this.mSetData.Yklsfk));
            this.mItemBshzdxmwzd.SetCheck(SwSet(this.mSetData.Bshzdxmwzd));
            this.mItemZnwdd.SetCheck(SwSet(this.mSetData.Znwdd));
            this.mItemDzzlmsxz.SetSel(this.mSetData.Dzzlmsxz);
            this.mItemYklsfk2.SetCheck(SwSet(this.mSetData.Yklsfk2));
            this.mItemBscmzdgc.SetCheck(SwSet(this.mSetData.Bscmzdgc));
            this.mItemRjxcd.SetCheck(SwSet(this.mSetData.Rjxcd));
            this.mItemSftslx.SetSel(this.mSetData.Sfts);
            this.mItemBsgbtczyl.SetCheck(SwSet(this.mSetData.Bsgbtczyl));
            this.mItemTqms.SetCheck(SwSet(this.mSetData.Tqms));
            this.mItemHsjzdzd.SetCheck(SwSet(this.mSetData.Hsjzdzd));
            this.mItemXdzdjs.SetCheck(SwSet(this.mSetData.Xdzdjs));
            this.mItemYksssy.SetCheck(SwSet(this.mSetData.Yksssy));
            this.mItemMkzxdss.SetCheck(SwSet(this.mSetData.Mkzxdss));
            this.mItemSszdgbwzd.SetCheck(SwSet(this.mSetData.Sszdgbzd));
            this.mItemCssz.SetCheck(SwSet(this.mSetData.Cssz));
            this.mItemEsc.SetCheck(SwSet(this.mSetData.Esc));
            this.mItemXhjs.SetCheck(SwSet(this.mSetData.Xhjs));
            this.mItemBshzdxmwzd2.SetCheck(SwSet(this.mSetData.Bshzdxmwzd2));
            this.mItemZdjjzd.SetCheck(SwSet(this.mSetData.Zdjjzd));
            this.mItemQpzyj.SetCheck(SwSet(this.mSetData.Qpzyj));
            this.mItemZnygdgchctjxt.SetCheck(SwSet(this.mSetData.Znygdgchctjxt));
            this.mItemZdphjsxt.SetCheck(SwSet(this.mSetData.Zdphjsxt));
            this.mItemBjjl.SetSel(this.mSetData.Bjjl);
            this.mItemCssz2.SetCheck(SwSet(this.mSetData.Cssz2));
            this.mItemXhjs2.SetCheck(SwSet(this.mSetData.Xhjs2));
            this.mItemMkzxdss2.SetCheck(SwSet(this.mSetData.Mkzxdss2));
            this.mItemYksssy2.SetCheck(SwSet(this.mSetData.Yksssy2));
            this.mItemSszdgbwzd2.SetCheck(SwSet(this.mSetData.Sszdgbwzd));
            this.mItemBscmzdgc2.SetCheck(SwSet(this.mSetData.Bscmzdgc2));
            this.mItemHsjzdzd2.SetCheck(SwSet(this.mSetData.Hsjzdzd2));
        }
        CanJni.GeelyRzcGetLangSet(this.mLangData);
        if (!i2b(this.mLangData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLangData.Update)) {
            this.mItemLang.SetSel(this.mLangData.Val);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GeelyCarQuery(80, 0);
        Sleep(10);
        CanJni.GeelyCarQuery(39, 0);
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
        this.mItemSpeedLock = AddCheckItem(R.string.can_speed_lock, 1);
        this.mItemStopUnlok = AddCheckItem(R.string.can_stop_unlock, 2);
        this.mItemKmzxdsxts = AddCheckItem(R.string.can_kmzxdssts, 3);
        this.mItemYklsfk = AddCheckItem(R.string.can_yklsts, 4);
        this.mItemBshzdxmwzd = AddCheckItem(R.string.can_geely_boy_zdxmwzdzt, 5);
        this.mItemZnwdd = AddCheckItem(R.string.can_znwdd, 6);
        this.mItemHsjzdzd = AddCheckItem(R.string.can_hsjzdzd, 14);
        this.mItemYklsfk2 = AddCheckItem(R.string.can_yklsts, 8);
        this.mItemBscmzdgc = AddCheckItem(R.string.can_geely_boy_bscmzdgc, 9);
        this.mItemBsgbtczyl = AddCheckItem(R.string.can_bsgbtczyl, 10);
        this.mItemRjxcd = AddCheckItem(R.string.can_rjxcd, 11);
        this.mItemTqms = AddCheckItem(R.string.can_tqms, 13);
        this.mItemXdzdjs = AddCheckItem(R.string.can_xdzdjs, 15);
        this.mItemYksssy = AddCheckItem(R.string.can_yksssy, 16);
        this.mItemMkzxdss = AddCheckItem(R.string.can_mkzxdss, 17);
        this.mItemSszdgbwzd = AddCheckItem(R.string.can_sszdgbwzd, 18);
        this.mItemCssz = AddCheckItem(R.string.can_cssz, 19);
        this.mItemEsc = AddCheckItem(R.string.can_esc, 20);
        this.mItemDzzlmsxz = AddPopupItem(R.string.can_geely_boy_mode, mDzzlmsArr, 7);
        this.mItemSftslx = AddPopupItem(R.string.can_set_tip, mSftsArr, 12);
        this.mItemXhjs = AddCheckItem(R.string.can_kaiyi_3x_xhjs, 32);
        this.mItemBshzdxmwzd2 = AddCheckItem(R.string.can_geely_boy_zdxmwzdzt, 33);
        this.mItemZdjjzd = AddCheckItem(R.string.can_zdjjzdxt, 34);
        this.mItemQpzyj = AddCheckItem(R.string.can_qpzyj, 35);
        this.mItemZnygdgchctjxt = AddCheckItem(R.string.can_znygdgchctjxt, 36);
        this.mItemZdphjsxt = AddCheckItem(R.string.can_zdphjstx, 37);
        this.mItemBjjl = AddPopupItem(R.string.can_bjjl, mBjjlArr, 38);
        this.mItemLang = AddPopupItem(R.string.can_car_lang, mLangArr, 39);
        this.mItemCssz2 = AddCheckItem(R.string.can_scsz, 48);
        this.mItemXhjs2 = AddCheckItem(R.string.can_kaiyi_3x_xhjs, 49);
        this.mItemMkzxdss2 = AddCheckItem(R.string.can_mkzxdss, 50);
        this.mItemYksssy2 = AddCheckItem(R.string.can_yksssy, 51);
        this.mItemSszdgbwzd2 = AddCheckItem(R.string.can_sszdgbwzd, 52);
        this.mItemBscmzdgc2 = AddCheckItem(R.string.can_geely_boy_bscmzdgc, 53);
        this.mItemHsjzdzd2 = AddCheckItem(R.string.can_hsjzdzd, 54);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 54; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                if (CanJni.GetSubType() == 2) {
                    ret = 1;
                    break;
                }
                break;
            case 6:
            case 14:
                if (CanJni.GetSubType() == 5 || CanJni.GetSubType() == 1) {
                    ret = 1;
                    break;
                }
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
                if (CanJni.GetSubType() == 3) {
                    ret = 1;
                    break;
                }
                break;
            case 12:
                if (CanJni.GetSubType() == 4) {
                    ret = 1;
                    break;
                }
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                if (CanJni.GetSubType() == 9) {
                    ret = 1;
                    break;
                }
                break;
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
                if (CanJni.GetSubType() == 10) {
                    ret = 1;
                    break;
                }
                break;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
                if (CanJni.GetSubType() == 11 || CanJni.GetSubType() == 12 || CanJni.GetSubType() == 14) {
                    ret = 1;
                    break;
                }
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemSpeedLock.ShowGone(show);
                return;
            case 2:
                this.mItemStopUnlok.ShowGone(show);
                return;
            case 3:
                this.mItemKmzxdsxts.ShowGone(show);
                return;
            case 4:
                this.mItemYklsfk.ShowGone(show);
                return;
            case 5:
                this.mItemBshzdxmwzd.ShowGone(show);
                return;
            case 6:
                this.mItemZnwdd.ShowGone(show);
                return;
            case 7:
                this.mItemDzzlmsxz.ShowGone(show);
                return;
            case 8:
                this.mItemYklsfk2.ShowGone(show);
                return;
            case 9:
                this.mItemBscmzdgc.ShowGone(show);
                return;
            case 10:
                this.mItemBsgbtczyl.ShowGone(show);
                return;
            case 11:
                this.mItemRjxcd.ShowGone(show);
                return;
            case 12:
                this.mItemSftslx.ShowGone(show);
                return;
            case 13:
                this.mItemTqms.ShowGone(show);
                return;
            case 14:
                this.mItemHsjzdzd.ShowGone(show);
                return;
            case 15:
                this.mItemXdzdjs.ShowGone(show);
                return;
            case 16:
                this.mItemYksssy.ShowGone(show);
                return;
            case 17:
                this.mItemMkzxdss.ShowGone(show);
                return;
            case 18:
                this.mItemSszdgbwzd.ShowGone(show);
                return;
            case 19:
                this.mItemCssz.ShowGone(show);
                return;
            case 20:
                this.mItemEsc.ShowGone(show);
                return;
            case 32:
                this.mItemXhjs.ShowGone(show);
                return;
            case 33:
                this.mItemBshzdxmwzd2.ShowGone(show);
                return;
            case 34:
                this.mItemZdjjzd.ShowGone(show);
                return;
            case 35:
                this.mItemQpzyj.ShowGone(show);
                return;
            case 36:
                this.mItemZnygdgchctjxt.ShowGone(show);
                return;
            case 37:
                this.mItemZdphjsxt.ShowGone(show);
                return;
            case 38:
                this.mItemBjjl.ShowGone(show);
                return;
            case 39:
                this.mItemLang.ShowGone(show);
                return;
            case 48:
                this.mItemCssz2.ShowGone(show);
                return;
            case 49:
                this.mItemXhjs2.ShowGone(show);
                return;
            case 50:
                this.mItemMkzxdss2.ShowGone(show);
                return;
            case 51:
                this.mItemYksssy2.ShowGone(show);
                return;
            case 52:
                this.mItemSszdgbwzd2.ShowGone(show);
                return;
            case 53:
                this.mItemBscmzdgc2.ShowGone(show);
                return;
            case 54:
                this.mItemHsjzdzd2.ShowGone(show);
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
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(this, id);
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
                CanJni.GeelyCarSet(0, NegSwSet(this.mSetData.Csszgn));
                return;
            case 2:
                CanJni.GeelyCarSet(1, NegSwSet(this.mSetData.Tcjs));
                return;
            case 3:
                CanJni.GeelyCarSet(2, NegSwSet(this.mSetData.Kmzxdssts));
                return;
            case 4:
                CanJni.GeelyCarSet(3, NegSwSet(this.mSetData.Yklsfk));
                return;
            case 5:
                CanJni.GeelyCarSet(4, NegSwSet(this.mSetData.Bshzdxmwzd));
                return;
            case 6:
                CanJni.GeelyCarSet(5, NegSwSet(this.mSetData.Znwdd));
                return;
            case 8:
                CanJni.GeelyCarSet(7, NegSwSet(this.mSetData.Yklsfk2));
                return;
            case 9:
                CanJni.GeelyCarSet(8, NegSwSet(this.mSetData.Bscmzdgc));
                return;
            case 10:
                CanJni.GeelyCarSet(9, NegSwSet(this.mSetData.Bsgbtczyl));
                return;
            case 11:
                CanJni.GeelyCarSet(10, NegSwSet(this.mSetData.Rjxcd));
                return;
            case 13:
                CanJni.GeelyCarSet(18, NegSwSet(this.mSetData.Tqms));
                return;
            case 14:
                CanJni.GeelyCarSet(20, NegSwSet(this.mSetData.Hsjzdzd));
                return;
            case 15:
                CanJni.GeelyCarSet(1, NegSwSet(this.mSetData.Xdzdjs));
                return;
            case 16:
                CanJni.GeelyCarSet(3, NegSwSet(this.mSetData.Yksssy));
                return;
            case 17:
                CanJni.GeelyCarSet(2, NegSwSet(this.mSetData.Mkzxdss));
                return;
            case 18:
                CanJni.GeelyCarSet(4, NegSwSet(this.mSetData.Sszdgbzd));
                return;
            case 19:
                CanJni.GeelyCarSet(0, NegSwSet(this.mSetData.Cssz));
                return;
            case 20:
                CanJni.GeelyCarSet(19, NegSwSet(this.mSetData.Esc));
                return;
            case 32:
                CanJni.GeelyCarSet(1, NegSwSet(this.mSetData.Xhjs));
                return;
            case 33:
                CanJni.GeelyCarSet(4, NegSwSet(this.mSetData.Bshzdxmwzd2));
                return;
            case 34:
                CanJni.GeelyCarSet(21, NegSwSet(this.mSetData.Zdjjzd));
                return;
            case 35:
                CanJni.GeelyCarSet(22, NegSwSet(this.mSetData.Qpzyj));
                return;
            case 36:
                CanJni.GeelyCarSet(23, NegSwSet(this.mSetData.Znygdgchctjxt));
                return;
            case 37:
                CanJni.GeelyCarSet(24, NegSwSet(this.mSetData.Zdphjsxt));
                return;
            case 48:
                CanJni.GeelyCarSet(0, NegSwSet(this.mSetData.Cssz2));
                return;
            case 49:
                CanJni.GeelyCarSet(1, NegSwSet(this.mSetData.Xhjs2));
                return;
            case 50:
                CanJni.GeelyCarSet(2, NegSwSet(this.mSetData.Mkzxdss2));
                return;
            case 51:
                CanJni.GeelyCarSet(3, NegSwSet(this.mSetData.Yksssy2));
                return;
            case 52:
                CanJni.GeelyCarSet(4, NegSwSet(this.mSetData.Sszdgbwzd));
                return;
            case 53:
                CanJni.GeelyCarSet(8, NegSwSet(this.mSetData.Bscmzdgc2));
                return;
            case 54:
                CanJni.GeelyCarSet(20, NegSwSet(this.mSetData.Hsjzdzd2));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 7:
                CanJni.GeelyCarSet(6, item);
                return;
            case 12:
                CanJni.GeelyCarSet(17, item);
                return;
            case 38:
                CanJni.GeelyCarSet(25, item);
                return;
            case 39:
                CanJni.GeelyCarSet(26, item);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
