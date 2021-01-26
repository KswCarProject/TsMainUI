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
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;

public class CanGeelyYjX1CarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    public static final int ITEM_BJJL = 38;
    public static final int ITEM_BSCMZDGC = 9;
    public static final int ITEM_BSCMZDGC2 = 53;
    public static final int ITEM_BSCMZDGC3 = 83;
    public static final int ITEM_BSCMZDGC4 = 90;
    public static final int ITEM_BSCMZDGC5 = 128;
    public static final int ITEM_BSCMZDGC6 = 144;
    public static final int ITEM_BSGBTCZYL = 10;
    public static final int ITEM_BSGBTCZYL2 = 129;
    public static final int ITEM_BSHZDXMWZD = 5;
    public static final int ITEM_BSHZDXMWZD2 = 33;
    public static final int ITEM_BWHJDDKQSJ = 88;
    public static final int ITEM_BWHJDDKQSJ2 = 116;
    public static final int ITEM_BWHJDDKQSJ3 = 150;
    public static final int ITEM_CCFJBJ = 84;
    public static final int ITEM_CCFJBJ2 = 149;
    public static final int ITEM_CSSZ = 19;
    public static final int ITEM_CSSZ2 = 48;
    public static final int ITEM_CSSZGN = 80;
    public static final int ITEM_DCHYGLD = 151;
    public static final int ITEM_DSTSY = 162;
    public static final int ITEM_DZZLMSXZ = 7;
    public static final int ITEM_ESC = 20;
    public static final int ITEM_ESC2 = 86;
    public static final int ITEM_FMQ = 153;
    public static final int ITEM_HBXWYSJS = 25;
    public static final int ITEM_HBXWYSJS2 = 115;
    public static final int ITEM_HBXZDJSJL = 23;
    public static final int ITEM_HBXZDKQ = 24;
    public static final int ITEM_HSJZDZD = 14;
    public static final int ITEM_HSJZDZD2 = 54;
    public static final int ITEM_HSJZDZD3 = 87;
    public static final int ITEM_HSJZDZD4 = 147;
    public static final int ITEM_KJJSPZ = 27;
    public static final int ITEM_KJJSPZ2 = 133;
    public static final int ITEM_KJJSPZ3 = 160;
    public static final int ITEM_KMZXDSXTS = 3;
    public static final int ITEM_KTZDDJ = 117;
    public static final int ITEM_LANG = 39;
    public static final int ITEM_LKSSPZ = 28;
    public static final int ITEM_LKSSPZ2 = 134;
    public static final int ITEM_LKSSPZ3 = 161;
    private static final int ITEM_MAX = 162;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_MKZXDSS = 17;
    public static final int ITEM_MKZXDSS2 = 50;
    public static final int ITEM_QPZYJ = 35;
    public static final int ITEM_RJXCD = 11;
    public static final int ITEM_RJXCD2 = 130;
    public static final int ITEM_RJXCD3 = 145;
    public static final int ITEM_RYMKQSCBJPZ = 26;
    public static final int ITEM_RYMKQSCBJPZ2 = 114;
    public static final int ITEM_SFTSLX = 12;
    public static final int ITEM_SFTSLX2 = 85;
    public static final int ITEM_SFTSLX3 = 112;
    public static final int ITEM_SFTSLX4 = 131;
    public static final int ITEM_SFTSLX5 = 146;
    public static final int ITEM_SPEED_LOCK = 1;
    public static final int ITEM_SSZDGBWZD = 18;
    public static final int ITEM_SSZDGBWZD2 = 52;
    public static final int ITEM_STOP_UNLOK = 2;
    public static final int ITEM_TCJS = 81;
    public static final int ITEM_TQMS = 13;
    public static final int ITEM_TYJKXTJZ = 89;
    public static final int ITEM_XDZDJS = 15;
    public static final int ITEM_XHJS = 32;
    public static final int ITEM_XHJS2 = 49;
    public static final int ITEM_YKLSFK = 4;
    public static final int ITEM_YKLSFK2 = 8;
    public static final int ITEM_YKLSFK3 = 82;
    public static final int ITEM_YKSSSY = 16;
    public static final int ITEM_YKSSSY2 = 51;
    public static final int ITEM_YSXTZXLD = 152;
    public static final int ITEM_ZCBGLD = 91;
    public static final int ITEM_ZCBGLD2 = 135;
    public static final int ITEM_ZDGC = 21;
    public static final int ITEM_ZDJJZD = 34;
    public static final int ITEM_ZDPHJSXT = 37;
    public static final int ITEM_ZNWDD = 6;
    public static final int ITEM_ZNYGDGCHCTJXT = 36;
    public static final int ITEM_ZTYS = 113;
    public static final int ITEM_ZTYS2 = 132;
    public static final int ITEM_ZTYS3 = 148;
    public static final String TAG = "CanGeelyYjX1CarInfoActivity";
    private static final int[] mBjjlArr = {R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_1};
    private static final int[] mBscmzdgcArr = {R.string.can_geely_boy_bscmzdgc, R.string.can_ccyszdsc};
    private static final int[] mBwhjddkqsjArr = {R.string.can_30s, R.string.can_60s, R.string.can_90s};
    private static final int[] mDstsyArr = {R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_middle, R.string.can_mzd_cx4_voice_high};
    private static final int[] mDstsyGseArr = {R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on};
    private static final int[] mDzzlmsArr = {R.string.can_comfort, R.string.can_sport};
    private static final int[] mHbxjsjlArr = {R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_1};
    private static final int[] mKtzddjArr = {R.string.can_rouruan, R.string.can_comfort, R.string.can_qiangjing};
    private static final int[] mLangArr = {R.string.lang_cn, R.string.lang_en_uk};
    private static final int[] mSftsArr = {R.string.can_dghlb, R.string.can_only_light};
    private static final int[] mZtysArr = {R.string.can_color_blue, R.string.can_color_red, R.string.can_apply_speed, R.string.can_color_yellow};
    private static int m_RCamerab = 0;
    protected CanItemPopupList mItemBjjl;
    protected CanItemSwitchList mItemBscmzdgc;
    protected CanItemSwitchList mItemBscmzdgc2;
    protected CanItemSwitchList mItemBscmzdgc3;
    protected CanItemPopupList mItemBscmzdgc4;
    protected CanItemPopupList mItemBscmzdgc5;
    protected CanItemPopupList mItemBscmzdgc6;
    protected CanItemSwitchList mItemBsgbtczyl;
    protected CanItemSwitchList mItemBsgbtczyl2;
    protected CanItemSwitchList mItemBshzdxmwzd;
    protected CanItemSwitchList mItemBshzdxmwzd2;
    protected CanItemPopupList mItemBwhjddkqsj;
    protected CanItemPopupList mItemBwhjddkqsj2;
    protected CanItemPopupList mItemBwhjddkqsj3;
    protected CanItemSwitchList mItemCcfjbj;
    protected CanItemSwitchList mItemCcfjbj2;
    protected CanItemSwitchList mItemCssz;
    protected CanItemSwitchList mItemCssz2;
    protected CanItemSwitchList mItemCsszgn;
    protected CanItemSwitchList mItemDchygld;
    protected CanItemPopupList mItemDstsy;
    protected CanItemPopupList mItemDzzlmsxz;
    protected CanItemSwitchList mItemEsc;
    protected CanItemSwitchList mItemEsc2;
    protected CanItemSwitchList mItemFmq;
    protected CanItemSwitchList mItemHbxwysjs;
    protected CanItemSwitchList mItemHbxwysjs2;
    protected CanItemPopupList mItemHbxzdjsjl;
    protected CanItemSwitchList mItemHbxzdkq;
    protected CanItemSwitchList mItemHsjzdzd;
    protected CanItemSwitchList mItemHsjzdzd2;
    protected CanItemSwitchList mItemHsjzdzd3;
    protected CanItemSwitchList mItemHsjzdzd4;
    protected CanItemSwitchList mItemKjjspz;
    protected CanItemSwitchList mItemKjjspz2;
    protected CanItemSwitchList mItemKjjspz3;
    protected CanItemSwitchList mItemKmzxdsxts;
    protected CanItemPopupList mItemKtzddj;
    protected CanItemPopupList mItemLang;
    protected CanItemSwitchList mItemLksspz;
    protected CanItemSwitchList mItemLksspz2;
    protected CanItemSwitchList mItemLksspz3;
    protected CanItemSwitchList mItemMkzxdss;
    protected CanItemSwitchList mItemMkzxdss2;
    protected CanItemSwitchList mItemQpzyj;
    protected CanItemSwitchList mItemRjxcd;
    protected CanItemSwitchList mItemRjxcd2;
    protected CanItemSwitchList mItemRjxcd3;
    protected CanItemSwitchList mItemRymkqscbjpz;
    protected CanItemSwitchList mItemRymkqscbjpz2;
    protected CanItemPopupList mItemSftslx;
    protected CanItemPopupList mItemSftslx2;
    protected CanItemPopupList mItemSftslx3;
    protected CanItemPopupList mItemSftslx4;
    protected CanItemPopupList mItemSftslx5;
    private CanItemSwitchList mItemSpeedLock;
    protected CanItemSwitchList mItemSszdgbwzd;
    protected CanItemSwitchList mItemSszdgbwzd2;
    protected CanItemSwitchList mItemStopUnlok;
    protected CanItemSwitchList mItemTcjs;
    protected CanItemSwitchList mItemTqms;
    protected CanItemSwitchList mItemTyjkxtjz;
    protected CanItemSwitchList mItemXdzdjs;
    protected CanItemSwitchList mItemXhjs;
    protected CanItemSwitchList mItemXhjs2;
    protected CanItemSwitchList mItemYklsfk;
    protected CanItemSwitchList mItemYklsfk2;
    protected CanItemSwitchList mItemYklsfk3;
    protected CanItemSwitchList mItemYksssy;
    protected CanItemSwitchList mItemYksssy2;
    protected CanItemSwitchList mItemYsxtzxld;
    protected CanItemProgressList mItemZcbgld;
    protected CanItemProgressList mItemZcbgld2;
    protected CanItemSwitchList mItemZdgc;
    protected CanItemSwitchList mItemZdjjzd;
    protected CanItemSwitchList mItemZdphjsxt;
    protected CanItemSwitchList mItemZnwdd;
    protected CanItemSwitchList mItemZnygdgchctjxt;
    protected CanItemPopupList mItemZtys;
    protected CanItemPopupList mItemZtys2;
    protected CanItemPopupList mItemZtys3;
    protected CanDataInfo.Geely_LangSet mLangData = new CanDataInfo.Geely_LangSet();
    private CanScrollList mManager;
    protected CanDataInfo.Geely_CarSet mSetData = new CanDataInfo.Geely_CarSet();
    protected CanDataInfo.Geely_Set2 mSetData2 = new CanDataInfo.Geely_Set2();
    protected CanDataInfo.Geely_Set mSetData3 = new CanDataInfo.Geely_Set();
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
            this.mItemRjxcd2.SetCheck(SwSet(this.mSetData.Rjxcd));
            this.mItemRjxcd3.SetCheck(SwSet(this.mSetData.Rjxcd));
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
            this.mItemZdgc.SetCheck(SwSet(this.mSetData.Zdgc));
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
            this.mItemHbxzdjsjl.SetSel(this.mSetData.Hbxzdjsjl);
            this.mItemHbxzdkq.SetCheck(SwSet(this.mSetData.Hbxzdkq));
            this.mItemHbxwysjs.SetCheck(SwSet(this.mSetData.Hbxwysjs));
            this.mItemRymkqscbjpz.SetCheck(SwSet(this.mSetData.Rymkqscbjpz));
            this.mItemKjjspz.SetCheck(SwSet(this.mSetData.Kjjspz));
            this.mItemLksspz.SetCheck(SwSet(this.mSetData.Lksspz));
            this.mItemKjjspz2.SetCheck(SwSet(this.mSetData.Kjjspz));
            this.mItemLksspz2.SetCheck(SwSet(this.mSetData.Lksspz));
            this.mItemRymkqscbjpz2.SetCheck(SwSet(this.mSetData.Rymkqscbjpz));
            this.mItemHbxwysjs2.SetCheck(SwSet(this.mSetData.Hbxwysjs));
            this.mItemBsgbtczyl2.SetCheck(SwSet(this.mSetData.Bsgbtczyl));
        }
        CanJni.GeelyRzcGetLangSet(this.mLangData);
        if (i2b(this.mLangData.UpdateOnce) && (!check || i2b(this.mLangData.Update))) {
            this.mItemLang.SetSel(this.mLangData.Val);
        }
        CanJni.GeelyGetCarSet2(this.mSetData2);
        if (i2b(this.mSetData2.UpdateOnce) && (!check || i2b(this.mSetData2.Update))) {
            this.mSetData2.Update = 0;
            this.mItemCsszgn.SetCheck(this.mSetData2.Csszgn);
            this.mItemTcjs.SetCheck(this.mSetData2.Tcjs);
            this.mItemYklsfk3.SetCheck(this.mSetData2.Yklsfk);
            this.mItemBscmzdgc3.SetCheck(this.mSetData2.Bscmzdgc);
            this.mItemCcfjbj.SetCheck(this.mSetData2.Ccfjbj);
            this.mItemCcfjbj2.SetCheck(this.mSetData2.Ccfjbj);
            this.mItemBscmzdgc4.SetSel(this.mSetData2.Bscmzdgc);
            this.mItemBscmzdgc5.SetSel(this.mSetData2.Bscmzdgc);
            this.mItemBscmzdgc6.SetSel(this.mSetData2.Bscmzdgc);
            this.mItemSftslx2.SetSel(this.mSetData2.Sftslx);
            this.mItemEsc2.SetCheck(this.mSetData2.ESC);
            this.mItemHsjzdzd3.SetCheck(this.mSetData2.Hsjzdzd);
            this.mItemHsjzdzd4.SetCheck(this.mSetData2.Hsjzdzd);
            this.mItemBwhjddkqsj.SetSel(this.mSetData2.Bwhjzdkqsj);
            this.mItemTyjkxtjz.SetCheck(this.mSetData2.Tyjkxtjz);
            this.mItemZcbgld.SetCurVal(this.mSetData2.Zcbgld);
            this.mItemZcbgld2.SetCurVal(this.mSetData2.Zcbgld);
            this.mItemSftslx3.SetSel(this.mSetData2.Sftslx);
            this.mItemSftslx4.SetSel(this.mSetData2.Sftslx);
            this.mItemSftslx5.SetSel(this.mSetData2.Sftslx);
            this.mItemKtzddj.SetSel(this.mSetData2.Ktzddj);
            this.mItemBwhjddkqsj2.SetSel(this.mSetData2.Bwhjzdkqsj);
            this.mItemBwhjddkqsj3.SetSel(this.mSetData2.Bwhjzdkqsj);
            this.mItemDchygld.SetCheck(SwSet(this.mSetData2.Dchygld));
            this.mItemFmq.SetCheck(SwSet(this.mSetData2.Fmq));
            this.mItemKjjspz3.SetCheck(SwSet(this.mSetData2.Kjjspz));
            this.mItemLksspz3.SetCheck(SwSet(this.mSetData2.Lksspz));
            this.mItemDstsy.SetSel(this.mSetData2.Dstsy);
        }
        CanJni.GeelyRzcGetSet(this.mSetData3);
        if (i2b(this.mSetData3.UpdateOnce) && (!check || i2b(this.mSetData3.Update))) {
            this.mSetData3.Update = 0;
            this.mItemZtys.SetSel(this.mSetData3.Ztys);
            this.mItemZtys2.SetSel(this.mSetData3.Ztys);
            this.mItemZtys3.SetSel(this.mSetData3.Ztys);
        }
        if (m_RCamerab != (FtSet.Getlgb4() & 1) || !check) {
            m_RCamerab = FtSet.Getlgb4() & 1;
            this.mItemYsxtzxld.SetCheck(m_RCamerab);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GeelyCarQuery(80, 0);
        Sleep(10);
        CanJni.GeelyCarQuery(39, 0);
        Sleep(10);
        CanJni.GeelyCarQuery(78, 0);
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
        this.mItemYklsfk2 = AddCheckItem(R.string.can_yklsts, 8);
        this.mItemBscmzdgc = AddCheckItem(R.string.can_geely_boy_bscmzdgc, 9);
        this.mItemBsgbtczyl = AddCheckItem(R.string.can_bsgbtczyl, 10);
        this.mItemRjxcd = AddCheckItem(R.string.can_rjxcd, 11);
        this.mItemTqms = AddCheckItem(R.string.can_tqms, 13);
        this.mItemHsjzdzd = AddCheckItem(R.string.can_hsjzdzd, 14);
        this.mItemXdzdjs = AddCheckItem(R.string.can_xdzdjs, 15);
        this.mItemYksssy = AddCheckItem(R.string.can_yksssy, 16);
        this.mItemMkzxdss = AddCheckItem(R.string.can_mkzxdss, 17);
        this.mItemSszdgbwzd = AddCheckItem(R.string.can_sszdgbwzd, 18);
        this.mItemCssz = AddCheckItem(R.string.can_cssz, 19);
        this.mItemEsc = AddCheckItem(R.string.can_esc, 20);
        this.mItemZdgc = AddCheckItem(R.string.can_zdgc, 21);
        this.mItemDzzlmsxz = AddPopupItem(R.string.can_geely_boy_mode, mDzzlmsArr, 7);
        this.mItemSftslx = AddPopupItem(R.string.can_set_tip, mSftsArr, 12);
        this.mItemHbxzdjsjl = AddPopupItem(R.string.can_hbxzdjsjl, mHbxjsjlArr, 23);
        this.mItemHbxzdkq = AddCheckItem(R.string.can_hbxzdkq, 24);
        this.mItemHbxwysjs = AddCheckItem(R.string.can_hbxwysjs, 25);
        this.mItemRymkqscbjpz = AddCheckItem(R.string.can_rymkqscbjpz, 26);
        this.mItemKjjspz = AddCheckItem(R.string.can_kjjs, 27);
        this.mItemLksspz = AddCheckItem(R.string.can_lksspz, 28);
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
        this.mItemCsszgn = AddCheckItem(R.string.can_scsz, 80);
        this.mItemTcjs = AddCheckItem(R.string.can_stop_unlock, 81);
        this.mItemYklsfk3 = AddCheckItem(R.string.can_yksssy, 82);
        this.mItemBscmzdgc3 = AddCheckItem(R.string.can_geely_boy_bscmzdgc, 83);
        this.mItemCcfjbj = AddCheckItem(R.string.can_ccfjbj, 84);
        this.mItemBscmzdgc4 = AddPopupItem(R.string.can_geely_boy_bscmzdgc, mBscmzdgcArr, 90);
        this.mItemSftslx2 = AddPopupItem(R.string.can_set_tip, mSftsArr, 85);
        this.mItemEsc2 = AddCheckItem(R.string.can_esc, 86);
        this.mItemHsjzdzd3 = AddCheckItem(R.string.can_hsjzdzd, 87);
        this.mItemBwhjddkqsj = AddPopupItem(R.string.can_dgsjkz_bwhj, mBwhjddkqsjArr, 88);
        this.mItemTyjkxtjz = AddCheckItem(R.string.can_tybd, 89);
        this.mItemZcbgld = AddProgressItem(R.string.can_zcbgldtj, 91, 1, 0, 10);
        this.mItemSftslx3 = AddPopupItem(R.string.can_set_tip, mSftsArr, 112);
        this.mItemZtys = AddPopupItem(R.string.can_psa_wc_ztyssz, mZtysArr, 113);
        this.mItemRymkqscbjpz2 = AddCheckItem(R.string.can_rymkqscbjpz, 114);
        this.mItemHbxwysjs2 = AddCheckItem(R.string.can_hbxwysjs, 115);
        this.mItemBwhjddkqsj2 = AddPopupItem(R.string.can_dgsjkz_bwhj, mBwhjddkqsjArr, 116);
        this.mItemKtzddj = AddPopupItem(R.string.can_ktzddj, mKtzddjArr, 117);
        this.mItemBscmzdgc5 = AddPopupItem(R.string.can_geely_boy_bscmzdgc, mBscmzdgcArr, 128);
        this.mItemBsgbtczyl2 = AddCheckItem(R.string.can_bsgbtczyl, 129);
        this.mItemRjxcd2 = AddCheckItem(R.string.can_rjxcd, 130);
        this.mItemSftslx4 = AddPopupItem(R.string.can_set_tip, mSftsArr, 131);
        this.mItemZtys2 = AddPopupItem(R.string.can_psa_wc_ztyssz, mZtysArr, 132);
        this.mItemKjjspz2 = AddCheckItem(R.string.can_kjjs, 133);
        this.mItemLksspz2 = AddCheckItem(R.string.can_lksspz, 134);
        this.mItemZcbgld2 = AddProgressItem(R.string.can_zcbgldtj, 91, 1, 0, 10);
        this.mItemBscmzdgc6 = AddPopupItem(R.string.can_geely_boy_bscmzdgc, mBscmzdgcArr, 144);
        this.mItemRjxcd3 = AddCheckItem(R.string.can_rjxcd, 145);
        this.mItemSftslx5 = AddPopupItem(R.string.can_set_tip, mSftsArr, 146);
        this.mItemHsjzdzd4 = AddCheckItem(R.string.can_hsjzdzd, 147);
        this.mItemZtys3 = AddPopupItem(R.string.can_psa_wc_ztyssz, mZtysArr, 148);
        this.mItemCcfjbj2 = AddCheckItem(R.string.can_ccfjbj, 149);
        this.mItemBwhjddkqsj3 = AddPopupItem(R.string.can_dgsjkz_bwhj, mBwhjddkqsjArr, 150);
        this.mItemDchygld = AddCheckItem(R.string.can_dchygfz, 151);
        this.mItemFmq = AddCheckItem(R.string.can_beep_sta, 153);
        this.mItemYsxtzxld = AddCheckItem(R.string.can_rzxqhsxt, 152);
        this.mItemKjjspz3 = AddCheckItem(R.string.can_kjjs, 160);
        this.mItemLksspz3 = AddCheckItem(R.string.can_lksspz, 161);
        if (CanJni.GetSubType() == 23) {
            this.mItemDstsy = AddPopupItem(R.string.can_dsjgy, mDstsyGseArr, 162);
        } else {
            this.mItemDstsy = AddPopupItem(R.string.can_dsjgy, mDstsyArr, 162);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 162; i++) {
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
                if (CanJni.GetSubType() == 4 || 16 == CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                if (CanJni.GetSubType() == 9) {
                    ret = 1;
                    break;
                }
                break;
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
                if (16 == CanJni.GetSubType()) {
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
                if (CanJni.GetSubType() == 11 || CanJni.GetSubType() == 12 || CanJni.GetSubType() == 14 || CanJni.GetSubType() == 15) {
                    ret = 1;
                    break;
                }
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
                if (CanJni.GetSubType() == 17) {
                    ret = 1;
                    break;
                }
                break;
            case 85:
                if (CanJni.GetSubType() == 18 || CanJni.GetSubType() == 23) {
                    ret = 1;
                    break;
                }
            case 86:
            case 88:
            case 90:
            case 91:
                if (CanJni.GetSubType() == 18) {
                    ret = 1;
                    break;
                }
                break;
            case 87:
                if (CanJni.GetSubType() == 18 || CanJni.GetSubType() == 19) {
                    ret = 1;
                    break;
                }
            case 89:
                if (CanJni.GetSubType() == 18 || CanJni.GetSubType() == 17) {
                    ret = 1;
                    break;
                }
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 160:
            case 161:
                if (CanJni.GetSubType() == 19) {
                    ret = 1;
                    break;
                }
                break;
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
                if (CanJni.GetSubType() == 20) {
                    ret = 1;
                    break;
                }
                break;
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
                if (CanJni.GetSubType() == 22) {
                    ret = 1;
                    break;
                }
                break;
            case 162:
                if (CanJni.GetSubType() == 23) {
                    ret = 1;
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
            case 21:
                this.mItemZdgc.ShowGone(show);
                return;
            case 23:
                this.mItemHbxzdjsjl.ShowGone(show);
                return;
            case 24:
                this.mItemHbxzdkq.ShowGone(show);
                return;
            case 25:
                this.mItemHbxwysjs.ShowGone(show);
                return;
            case 26:
                this.mItemRymkqscbjpz.ShowGone(show);
                return;
            case 27:
                this.mItemKjjspz.ShowGone(show);
                return;
            case 28:
                this.mItemLksspz.ShowGone(show);
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
            case 80:
                this.mItemCsszgn.ShowGone(show);
                return;
            case 81:
                this.mItemTcjs.ShowGone(show);
                return;
            case 82:
                this.mItemYklsfk3.ShowGone(show);
                return;
            case 83:
                this.mItemBscmzdgc3.ShowGone(show);
                return;
            case 84:
                this.mItemCcfjbj.ShowGone(show);
                return;
            case 85:
                this.mItemSftslx2.ShowGone(show);
                return;
            case 86:
                this.mItemEsc2.ShowGone(show);
                return;
            case 87:
                this.mItemHsjzdzd3.ShowGone(show);
                return;
            case 88:
                this.mItemBwhjddkqsj.ShowGone(show);
                return;
            case 89:
                this.mItemTyjkxtjz.ShowGone(show);
                return;
            case 90:
                this.mItemBscmzdgc4.ShowGone(show);
                return;
            case 91:
                this.mItemZcbgld.ShowGone(show);
                return;
            case 112:
                this.mItemSftslx3.ShowGone(show);
                return;
            case 113:
                this.mItemZtys.ShowGone(show);
                return;
            case 114:
                this.mItemRymkqscbjpz2.ShowGone(show);
                return;
            case 115:
                this.mItemHbxwysjs2.ShowGone(show);
                return;
            case 116:
                this.mItemBwhjddkqsj2.ShowGone(show);
                return;
            case 117:
                this.mItemKtzddj.ShowGone(show);
                return;
            case 128:
                this.mItemBscmzdgc5.ShowGone(show);
                return;
            case 129:
                this.mItemBsgbtczyl2.ShowGone(show);
                return;
            case 130:
                this.mItemRjxcd2.ShowGone(show);
                return;
            case 131:
                this.mItemSftslx4.ShowGone(show);
                return;
            case 132:
                this.mItemZtys2.ShowGone(show);
                return;
            case 133:
                this.mItemKjjspz2.ShowGone(show);
                return;
            case 134:
                this.mItemLksspz2.ShowGone(show);
                return;
            case 135:
                this.mItemZcbgld2.ShowGone(show);
                return;
            case 144:
                this.mItemBscmzdgc6.ShowGone(show);
                return;
            case 145:
                this.mItemRjxcd3.ShowGone(show);
                return;
            case 146:
                this.mItemSftslx5.ShowGone(show);
                return;
            case 147:
                this.mItemHsjzdzd4.ShowGone(show);
                return;
            case 148:
                this.mItemZtys3.ShowGone(show);
                return;
            case 149:
                this.mItemCcfjbj2.ShowGone(show);
                return;
            case 150:
                this.mItemBwhjddkqsj3.ShowGone(show);
                return;
            case 151:
                this.mItemDchygld.ShowGone(show);
                return;
            case 152:
                this.mItemYsxtzxld.ShowGone(show);
                return;
            case 153:
                this.mItemFmq.ShowGone(show);
                return;
            case 160:
                this.mItemKjjspz3.ShowGone(show);
                return;
            case 161:
                this.mItemLksspz3.ShowGone(show);
                return;
            case 162:
                this.mItemDstsy.ShowGone(show);
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
            case 129:
                CanJni.GeelyCarSet(9, NegSwSet(this.mSetData.Bsgbtczyl));
                return;
            case 11:
            case 130:
            case 145:
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
            case 21:
                CanJni.GeelyCarSet(8, NegSwSet(this.mSetData.Zdgc));
                return;
            case 24:
                CanJni.GeelyCarSet(34, NegSwSet(this.mSetData.Hbxzdkq));
                return;
            case 25:
            case 115:
                CanJni.GeelyCarSet(36, NegSwSet(this.mSetData.Hbxwysjs));
                return;
            case 26:
            case 114:
                CanJni.GeelyCarSet(30, NegSwSet(this.mSetData.Rymkqscbjpz));
                return;
            case 27:
            case 133:
                CanJni.GeelyCarSet(31, NegSwSet(this.mSetData.Kjjspz));
                return;
            case 28:
            case 134:
                CanJni.GeelyCarSet(32, NegSwSet(this.mSetData.Lksspz));
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
            case 80:
                CanJni.GeelyCarSet(0, NegSwSet(this.mSetData2.Csszgn));
                return;
            case 81:
                CanJni.GeelyCarSet(1, NegSwSet(this.mSetData2.Tcjs));
                return;
            case 82:
                CanJni.GeelyCarSet(3, NegSwSet(this.mSetData2.Yklsfk));
                return;
            case 83:
                CanJni.GeelyCarSet(8, NegSwSet(this.mSetData2.Bscmzdgc));
                return;
            case 84:
            case 149:
                CanJni.GeelyCarSet(33, NegSwSet(this.mSetData2.Ccfjbj));
                return;
            case 86:
                CanJni.GeelyCarSet(19, NegSwSet(this.mSetData2.ESC));
                return;
            case 87:
            case 147:
                CanJni.GeelyCarSet(20, NegSwSet(this.mSetData2.Hsjzdzd));
                return;
            case 89:
                CanJni.GeelyCarSet(40, NegSwSet(this.mSetData2.Tyjkxtjz));
                return;
            case 151:
                CanJni.GeelyCarSet(43, NegSwSet(this.mSetData2.Dchygld));
                return;
            case 152:
                if (FtSet.Getlgb4() > 0) {
                    FtSet.Setlgb4(0);
                    return;
                } else {
                    FtSet.Setlgb4(1);
                    return;
                }
            case 153:
                CanJni.GeelyCarSet(128, NegSwSet(this.mSetData2.Fmq));
                return;
            case 160:
                CanJni.GeelyCarSet(31, NegSwSet(this.mSetData2.Kjjspz));
                return;
            case 161:
                CanJni.GeelyCarSet(32, NegSwSet(this.mSetData2.Lksspz));
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
            case 23:
                CanJni.GeelyCarSet(35, item);
                return;
            case 38:
                CanJni.GeelyCarSet(25, item);
                return;
            case 39:
                CanJni.GeelyCarSet(26, item);
                return;
            case 85:
            case 112:
            case 131:
            case 146:
                CanJni.GeelyCarSet(17, item);
                return;
            case 88:
            case 116:
            case 150:
                CanJni.GeelyCarSet(39, item);
                return;
            case 90:
            case 128:
            case 144:
                CanJni.GeelyCarSet(8, item);
                return;
            case 113:
            case 132:
            case 148:
                CanJni.GeelyCarSet(28, item);
                return;
            case 117:
                CanJni.GeelyCarSet(42, item);
                return;
            case 162:
                CanJni.GeelyCarSet(27, item);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 91:
            case 135:
                CanJni.GeelyCarSet(41, pos);
                return;
            default:
                return;
        }
    }
}
