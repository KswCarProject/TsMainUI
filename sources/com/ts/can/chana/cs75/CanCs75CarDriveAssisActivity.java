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
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanCs75CarDriveAssisActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    public static final int ITEM_AVM_CQLDZDJH = 66;
    public static final int ITEM_AVM_DSZXDZDJH = 65;
    public static final int ITEM_AVM_TITLE = 64;
    public static final int ITEM_BASE_TITLE = 1;
    public static final int ITEM_BSZDGC = 8;
    public static final int ITEM_CDFZ_CSYJ = 54;
    public static final int ITEM_CDFZ_CSYJPC = 55;
    public static final int ITEM_CDFZ_CSYJTSY = 56;
    public static final int ITEM_CDFZ_GNXZ = 50;
    public static final int ITEM_CDFZ_LMD = 49;
    public static final int ITEM_CDFZ_TITLE = 48;
    public static final int ITEM_CDFZ_XSBZSB = 52;
    public static final int ITEM_CDFZ_YJFS = 51;
    public static final int ITEM_CDFZ_ZDQD = 53;
    public static final int ITEM_DCHYGFZ = 3;
    public static final int ITEM_DLMS = 98;
    public static final int ITEM_HFYJ_BXFZ = 80;
    public static final int ITEM_HFYJ_DCHXYJ = 81;
    public static final int ITEM_HFYJ_HZWYJ = 82;
    public static final int ITEM_HFYJ_HZWYJTSY = 83;
    public static final int ITEM_HFYJ_TITLE = 73;
    public static final int ITEM_HSJZDDJ = 2;
    public static final int ITEM_JCSZSYXH = 17;
    public static final int ITEM_JSMS = 100;
    public static final int ITEM_JSMSJY = 87;
    private static final int ITEM_MAX = 100;
    public static final int ITEM_MBTSY = 18;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_QFZFZ_QFZYJ = 33;
    public static final int ITEM_QFZFZ_QPZYJLMD = 34;
    public static final int ITEM_QFZFZ_TITLE = 32;
    public static final int ITEM_QFZFZ_ZDJJZD = 35;
    public static final int ITEM_SYSTEM_SET_LANG = 96;
    public static final int ITEM_SYSTEM_SET_THEME = 97;
    public static final int ITEM_SYSTEM_SET_TITLE = 89;
    public static final int ITEM_WXCDKGGN = 10;
    public static final int ITEM_XH_TITLE = 16;
    public static final int ITEM_YBTSY1 = 4;
    public static final int ITEM_YBTSY2 = 5;
    public static final int ITEM_YBTSY3 = 6;
    public static final int ITEM_YCMQYX_QLDZDJH = 86;
    public static final int ITEM_YCMQYX_TITLE = 84;
    public static final int ITEM_YCMQYX_ZXDZDJH = 85;
    public static final int ITEM_YLDX = 88;
    public static final int ITEM_ZJCMDDJS = 9;
    public static final int ITEM_ZNYB = 7;
    public static final int ITEM_ZXMS = 99;
    public static final String TAG = "CanCs75CarDriveAssisActivity";
    private static final int[] mCdfzGnxzArr = {R.string.can_cdfz_jyj, R.string.can_cdfz_jjp, R.string.can_cdfz_yjjp};
    private static final int[] mCdfzYjfsArr = {R.string.can_type_vol, R.string.can_cdfz_zd, R.string.can_cdfz_syzd};
    private static final int[] mCdfzZdqd = {R.string.can_cdjg, R.string.can_mode_normal, R.string.can_cdjd};
    private static final int[] mCdfzlmdArr = {R.string.can_sensitivity_high, R.string.can_cdpyyzxt_1};
    private static final int[] mDlmsArrays = {R.string.can_comfort, R.string.can_sport, R.string.can_eco};
    private static final int[] mJsmsArrays = {R.string.can_comfort, R.string.can_sport, R.string.can_eco, R.string.can_individual};
    private static final int[] mLanguageArrays = {R.string.can_lang_cn, R.string.can_lang_en};
    private static final int[] mMbtisArr = {R.string.can_trunk_close, R.string.can_mbtsy_jsbts, R.string.can_mbtsy_jxsts, R.string.can_mbtsy_sbxsjts};
    private static final int[] mQpzyjlmdArr = {R.string.can_sensitivity_high, R.string.can_cdpyyzxt_1, R.string.can_sensitivity_low};
    private static final int[] mThemeArrays = {R.string.can_mzd_cx4_drive_auto, R.string.can_psa_eq_classic, R.string.can_sport, R.string.can_kj};
    private static final int[] mVolArrays = {R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_middle, R.string.can_mzd_cx4_voice_high};
    private static String[] mYbtsyArr;
    private static final int[] mZxmsArrays = {R.string.can_comfort, R.string.can_sport, R.string.can_light_qb};
    protected CanItemSwitchList mItemAvmCqldzdjh;
    protected CanItemSwitchList mItemAvmDszxdzdjh;
    private CanItemBlankTextList mItemAvmTitle;
    private CanItemBlankTextList mItemBaseTitle;
    protected CanItemSwitchList mItemBszdgc;
    protected CanItemSwitchList mItemCdfzCsyj;
    protected CanItemProgressList mItemCdfzCsyjpc;
    protected CanItemSwitchList mItemCdfzCsyjtsy;
    protected CanItemPopupList mItemCdfzGnxz;
    protected CanItemPopupList mItemCdfzLmd;
    private CanItemBlankTextList mItemCdfzTitle;
    protected CanItemSwitchList mItemCdfzXsbzsb;
    protected CanItemPopupList mItemCdfzYjfs;
    protected CanItemPopupList mItemCdfzZdqd;
    protected CanItemSwitchList mItemDchygfz;
    protected CanItemPopupList mItemDlms;
    protected CanItemSwitchList mItemHfyjBxfz;
    protected CanItemSwitchList mItemHfyjDchxyj;
    protected CanItemSwitchList mItemHfyjHzwyj;
    protected CanItemSwitchList mItemHfyjHzwyjTsy;
    private CanItemBlankTextList mItemHfyjTitle;
    protected CanItemSwitchList mItemHsjzddj;
    protected CanItemSwitchList mItemJcszsyxh;
    protected CanItemPopupList mItemJsms;
    protected CanItemSwitchList mItemJsmsjy;
    protected CanItemPopupList mItemLanguge;
    protected CanItemPopupList mItemMbtsy;
    private CanItemBlankTextList mItemQfzfzTitle;
    protected CanItemSwitchList mItemQfzyj;
    protected CanItemPopupList mItemQpzyjlmd;
    private CanItemBlankTextList mItemSystemSetTitle;
    protected CanItemPopupList mItemTheme;
    protected CanItemSwitchList mItemWxcdkggn;
    private CanItemBlankTextList mItemXhTitle;
    protected CanItemPopupList mItemYbtsy1;
    protected CanItemPopupList mItemYbtsy2;
    protected CanItemPopupList mItemYbtsy3;
    protected CanItemSwitchList mItemYcmqyxQldzdjh;
    private CanItemBlankTextList mItemYcmqyxTitle;
    protected CanItemSwitchList mItemYcmqyxZxdzdjh;
    protected CanItemPopupList mItemYldx;
    protected CanItemSwitchList mItemZdjjzd;
    protected CanItemSwitchList mItemZjcmddjs;
    protected CanItemSwitchList mItemZnyb;
    protected CanItemPopupList mItemZxms;
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
        if (i2b(this.mSetData.HsjzddjUpdateOnce) && (!check || i2b(this.mSetData.HsjzddjUpdate))) {
            this.mSetData.HsjzddjUpdate = 0;
            this.mItemHsjzddj.SetCheck(SwSet(this.mSetData.Hsjzdzd));
        }
        if (i2b(this.mSetData.DchygfzUpdateOnce) && (!check || i2b(this.mSetData.DchygfzUpdate))) {
            this.mSetData.DchygfzUpdate = 0;
            this.mItemDchygfz.SetCheck(SwSet(this.mSetData.Dchygfz));
        }
        if (i2b(this.mSetData.YbsyUpdateOnce) && (!check || i2b(this.mSetData.YbsyUpdate))) {
            this.mSetData.YbsyUpdate = 0;
            if (this.mSetData.Ybsy > 0) {
                this.mItemYbtsy1.SetSel(this.mSetData.Ybsy - 1);
            }
        }
        if (i2b(this.mSetData.TsysyUpdateOnce) && (!check || i2b(this.mSetData.TsysyUpdate))) {
            this.mSetData.TsysyUpdate = 0;
            if (this.mSetData.Tsysy > 0) {
                this.mItemYbtsy2.SetSel(this.mSetData.Tsysy - 1);
            }
        }
        if (i2b(this.mSetData.BjtsyUpdateOnce) && (!check || i2b(this.mSetData.BjtsyUpdate))) {
            this.mSetData.BjtsyUpdate = 0;
            if (this.mSetData.Bjtsy > 0) {
                this.mItemYbtsy3.SetSel(this.mSetData.Bjtsy - 1);
            }
        }
        if (i2b(this.mSetData.JcszsyxhUpdateOnce) && (!check || i2b(this.mSetData.JcszsyxhUpdate))) {
            this.mSetData.JcszsyxhUpdate = 0;
            this.mItemJcszsyxh.SetCheck(SwSet(this.mSetData.Jcszsyxh));
        }
        if (i2b(this.mSetData.MbtsyUpdateOnce) && (!check || i2b(this.mSetData.MbtsyUpdate))) {
            this.mSetData.MbtsyUpdate = 0;
            this.mItemMbtsy.SetSel(this.mSetData.Mbtsy);
        }
        if (i2b(this.mSetData.QfzyjUpdateOnce) && (!check || i2b(this.mSetData.QfzyjUpdate))) {
            this.mSetData.QfzyjUpdate = 0;
            this.mItemQfzyj.SetCheck(SwSet(this.mSetData.Qfzyj));
        }
        if (i2b(this.mSetData.QpzyjlmdUpdateOnce) && (!check || i2b(this.mSetData.QpzyjlmdUpdate))) {
            this.mSetData.QpzyjlmdUpdate = 0;
            this.mItemQpzyjlmd.SetSel(this.mSetData.Qpzyjlmd);
        }
        if (i2b(this.mSetData.ZdjjzdUpdateOnce) && (!check || i2b(this.mSetData.ZdjjzdUpdate))) {
            this.mSetData.ZdjjzdUpdate = 0;
            this.mItemZdjjzd.SetCheck(SwSet(this.mSetData.Zdjjzd));
        }
        if (i2b(this.mSetData.CdfzLmdUpdateOnce) && (!check || i2b(this.mSetData.CdfzLmdUpdate))) {
            this.mSetData.CdfzLmdUpdate = 0;
            this.mItemCdfzLmd.SetSel(this.mSetData.CdfzLmd);
        }
        if (i2b(this.mSetData.CdfzGnxzUpdateOnce) && (!check || i2b(this.mSetData.CdfzGnxzUpdate))) {
            this.mSetData.CdfzGnxzUpdate = 0;
            if (this.mSetData.CdfzGnxz > 0) {
                this.mItemCdfzGnxz.SetSel(this.mSetData.CdfzGnxz - 1);
            }
        }
        if (i2b(this.mSetData.CdfzYjfsUpdateOnce) && (!check || i2b(this.mSetData.CdfzYjfsUpdate))) {
            this.mSetData.CdfzYjfsUpdate = 0;
            if (this.mSetData.CdfzYjfs > 0) {
                this.mItemCdfzYjfs.SetSel(this.mSetData.CdfzYjfs - 1);
            }
        }
        if (i2b(this.mSetData.CdfzXsbzsbUpdateOnce) && (!check || i2b(this.mSetData.CdfzXsbzsbUpdate))) {
            this.mSetData.CdfzXsbzsbUpdate = 0;
            this.mItemCdfzXsbzsb.SetCheck(SwSet(this.mSetData.CdfzXsbzsb));
        }
        if (i2b(this.mSetData.CdfzCsyjUpdateOnce) && (!check || i2b(this.mSetData.CdfzCsyjUpdate))) {
            this.mSetData.CdfzCsyjUpdate = 0;
            this.mItemCdfzCsyj.SetCheck(SwSet(this.mSetData.CdfzCsyj));
        }
        if (i2b(this.mSetData.ZdqdUpdateOnce) && (!check || i2b(this.mSetData.ZdqdUpdate))) {
            this.mSetData.ZdqdUpdate = 0;
            this.mItemCdfzZdqd.SetSel(this.mSetData.Zdqd);
        }
        if (i2b(this.mSetData.CsyjtsyUpdateOnce) && (!check || i2b(this.mSetData.CsyjtsyUpdate))) {
            this.mSetData.CsyjtsyUpdate = 0;
            this.mItemCdfzCsyjtsy.SetCheck(SwSet(this.mSetData.Csyjtsy));
        }
        if (i2b(this.mSetData.CsyjpcUpdateOnce) && (!check || i2b(this.mSetData.CsyjpcUpdate))) {
            this.mSetData.CsyjpcUpdate = 0;
            this.mItemCdfzCsyjpc.SetCurVal(this.mSetData.Csyjpc);
            this.mItemCdfzCsyjpc.SetValText(String.format("%dKM/H", new Object[]{Integer.valueOf(this.mSetData.Csyjpc - 10)}));
        }
        if (i2b(this.mSetData.AvmDszxdzdjhUpdateOnce) && (!check || i2b(this.mSetData.AvmDszxdzdjhUpdate))) {
            this.mSetData.AvmDszxdzdjhUpdate = 0;
            this.mItemAvmDszxdzdjh.SetCheck(SwSet(this.mSetData.AvmDszxdzdjh));
        }
        if (i2b(this.mSetData.AvmCqldzdjhUpdateOnce) && (!check || i2b(this.mSetData.AvmCqldzdjhUpdate))) {
            this.mSetData.AvmCqldzdjhUpdate = 0;
            this.mItemAvmCqldzdjh.SetCheck(SwSet(this.mSetData.AvmCqldzdjh));
        }
        if (i2b(this.mSetData.BxfzUpdateOnce) && (!check || i2b(this.mSetData.BxfzUpdate))) {
            this.mSetData.BxfzUpdate = 0;
            this.mItemHfyjBxfz.SetCheck(SwSet(this.mSetData.Bxfz));
        }
        if (i2b(this.mSetData.DchxyjUpdateOnce) && (!check || i2b(this.mSetData.DchxyjUpdate))) {
            this.mSetData.DchxyjUpdate = 0;
            this.mItemHfyjDchxyj.SetCheck(SwSet(this.mSetData.Dchxyj));
        }
        if (i2b(this.mSetData.HzwyjUpdateOnce) && (!check || i2b(this.mSetData.HzwyjUpdate))) {
            this.mSetData.HzwyjUpdate = 0;
            this.mItemHfyjHzwyj.SetCheck(SwSet(this.mSetData.Hzwyj));
        }
        if (i2b(this.mSetData.HzwyjtsyUpdateOnce) && (!check || i2b(this.mSetData.HzwyjtsyUpdate))) {
            this.mSetData.HzwyjtsyUpdate = 0;
            this.mItemHfyjHzwyjTsy.SetCheck(SwSet(this.mSetData.Hzwyjtsy));
        }
        if (i2b(this.mSetData.ZxdZdjhUpdateOnce) && (!check || i2b(this.mSetData.ZxdZdjhUpdate))) {
            this.mSetData.ZxdZdjhUpdate = 0;
            this.mItemYcmqyxZxdzdjh.SetCheck(SwSet(this.mSetData.ZxdZdjh));
        }
        if (i2b(this.mSetData.QldZdjhUpdateOnce) && (!check || i2b(this.mSetData.QldZdjhUpdate))) {
            this.mSetData.QldZdjhUpdate = 0;
            this.mItemYcmqyxQldzdjh.SetCheck(SwSet(this.mSetData.QldZdjh));
        }
        if (i2b(this.mSetData.JsmsjyUpdateOnce) && (!check || i2b(this.mSetData.JsmsjyUpdate))) {
            this.mSetData.JsmsjyUpdate = 0;
            this.mItemJsmsjy.SetCheck(SwSet(this.mSetData.Jsmsjy));
        }
        if (i2b(this.mSetData.YldxUpdateOnce) && (!check || i2b(this.mSetData.YldxUpdate))) {
            this.mSetData.YldxUpdate = 0;
            this.mItemYldx.SetSel(this.mSetData.Yldx - 1);
        }
        if (i2b(this.mSetData.LangUpdateOnce) && (!check || i2b(this.mSetData.LangUpdate))) {
            this.mSetData.LangUpdate = 0;
            this.mItemLanguge.SetSel(this.mSetData.Lang - 1);
        }
        if (i2b(this.mSetData.YbztUpdateOnce) && (!check || i2b(this.mSetData.YbztUpdate))) {
            this.mSetData.YbztUpdate = 0;
            this.mItemTheme.SetSel(this.mSetData.Ybzt - 1);
        }
        if (i2b(this.mSetData.ZnybUpdateOnce) && (!check || i2b(this.mSetData.ZnybUpdate))) {
            this.mSetData.ZnybUpdate = 0;
            this.mItemZnyb.SetCheck(SwSet(this.mSetData.Znyb));
        }
        if (i2b(this.mSetData.BszdgcUpdateOnce) && (!check || i2b(this.mSetData.BszdgcUpdate))) {
            this.mSetData.BszdgcUpdate = 0;
            this.mItemBszdgc.SetCheck(SwSet(this.mSetData.Bszdgc));
        }
        if (i2b(this.mSetData.ZjcmddjsUpdateOnce) && (!check || i2b(this.mSetData.ZjcmddjsUpdate))) {
            this.mSetData.ZjcmddjsUpdate = 0;
            this.mItemZjcmddjs.SetCheck(SwSet(this.mSetData.Zjcmddjs));
        }
        if (i2b(this.mSetData.DlmsUpdateOnce) && (!check || i2b(this.mSetData.DlmsUpdate))) {
            this.mSetData.DlmsUpdate = 0;
            this.mItemDlms.SetSel(this.mSetData.Dlms - 1);
        }
        if (i2b(this.mSetData.ZxmsUpdateOnce) && (!check || i2b(this.mSetData.ZxmsUpdate))) {
            this.mSetData.ZxmsUpdate = 0;
            this.mItemZxms.SetSel(this.mSetData.Zxms - 1);
        }
        if (i2b(this.mSetData.JsmsUpdateOnce) && (!check || i2b(this.mSetData.JsmsUpdate))) {
            this.mSetData.JsmsUpdate = 0;
            this.mItemJsms.SetSel(this.mSetData.Jsms - 1);
        }
        if (!i2b(this.mSetData.WxcdkggnUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.WxcdkggnUpdate)) {
            this.mSetData.WxcdkggnUpdate = 0;
            this.mItemWxcdkggn.SetCheck(this.mSetData.Wxcdkggn - 1);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.Cs75CarQuery(82, 1);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 2);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 13);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 14);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 15);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 27);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 28);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 29);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 30);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 51);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 52);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 53);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 56);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 57);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 58);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 73);
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
        this.mItemBaseTitle = AddTitleItem(R.string.can_base_setup);
        this.mItemHsjzddj = AddCheckItem(R.string.can_zdhsjzd, 2);
        this.mItemDchygfz = AddCheckItem(R.string.can_hys, 3);
        mYbtsyArr = getResources().getStringArray(R.array.can_ybtsy);
        this.mItemYbtsy1 = AddPopupItem(R.string.can_ybsy, mYbtsyArr, 4);
        this.mItemYbtsy2 = AddPopupItem(R.string.can_tsysy, mYbtsyArr, 5);
        this.mItemYbtsy3 = AddPopupItem(R.string.can_bjtsy, mYbtsyArr, 6);
        this.mItemYldx = AddPopupItem(R.string.can_metert_vol, mVolArrays, 88);
        this.mItemJsmsjy = AddCheckItem(R.string.can_jsmsjy, 87);
        this.mItemZnyb = AddCheckItem(R.string.can_znyb, 7);
        this.mItemBszdgc = AddCheckItem(R.string.can_geely_boy_bscmzdgc, 8);
        this.mItemZjcmddjs = AddCheckItem(R.string.can_zjcmddjs, 9);
        this.mItemWxcdkggn = AddCheckItem(R.string.can_wxcd, 10);
        this.mItemXhTitle = AddTitleItem(R.string.can_cruise_crl);
        this.mItemJcszsyxh = AddCheckItem(R.string.can_cruise_jcszsyxh, 17);
        this.mItemMbtsy = AddPopupItem(R.string.can_cruise_mbtsy, mMbtisArr, 18);
        this.mItemQfzfzTitle = AddTitleItem(R.string.can_cds);
        this.mItemQfzyj = AddCheckItem(R.string.can_jp_qfpzjg, 33);
        this.mItemQpzyjlmd = AddPopupItem(R.string.can_qfzfz_qpzyjlmd, mQpzyjlmdArr, 34);
        this.mItemZdjjzd = AddCheckItem(R.string.can_zdjjzdxt, 35);
        this.mItemCdfzTitle = AddTitleItem(R.string.can_lane_assist);
        this.mItemCdfzLmd = AddPopupItem(R.string.can_cdfz_lmd, mCdfzlmdArr, 49);
        this.mItemCdfzGnxz = AddPopupItem(R.string.can_func_chos, mCdfzGnxzArr, 50);
        this.mItemCdfzYjfs = AddPopupItem(R.string.can_cdfz_yjfs, mCdfzYjfsArr, 51);
        this.mItemCdfzXsbzsb = AddCheckItem(R.string.can_cdfz_xsbzsb, 52);
        this.mItemCdfzZdqd = AddPopupItem(R.string.can_zdqd, mCdfzZdqd, 53);
        this.mItemCdfzCsyj = AddCheckItem(R.string.can_tigger7_speed_warn, 54);
        this.mItemCdfzCsyjtsy = AddCheckItem(R.string.can_csyjtsy, 56);
        this.mItemCdfzCsyjpc = this.mManager.addItemProgressList(R.string.can_csyjpc, 55, (CanItemProgressList.onPosChange) this);
        this.mItemCdfzCsyjpc.SetStep(1);
        this.mItemCdfzCsyjpc.SetMinMax(0, 20);
        this.mItemAvmTitle = AddTitleItem(R.string.can_honda_qjyxxtsz);
        this.mItemAvmDszxdzdjh = AddCheckItem(R.string.can_avm_dszxdzdjh, 65);
        this.mItemAvmCqldzdjh = AddCheckItem(R.string.can_avm_cqldzdjh, 66);
        this.mItemHfyjTitle = AddTitleItem(R.string.can_hfyj);
        this.mItemHfyjBxfz = AddCheckItem(R.string.can_bxfz, 80);
        this.mItemHfyjDchxyj = AddCheckItem(R.string.can_dchxyj, 81);
        this.mItemHfyjHzwyj = AddCheckItem(R.string.can_hzwyj, 82);
        this.mItemHfyjHzwyjTsy = AddCheckItem(R.string.can_hzwyjtsy, 83);
        this.mItemYcmqyxTitle = AddTitleItem(R.string.can_ycmqyx);
        this.mItemYcmqyxZxdzdjh = AddCheckItem(R.string.can_ycmqyx_zxdzdjh, 85);
        this.mItemYcmqyxQldzdjh = AddCheckItem(R.string.can_ycmqyx_qldzdjh, 86);
        this.mItemSystemSetTitle = AddTitleItem(R.string.can_system);
        this.mItemLanguge = AddPopupItem(R.string.can_language, mLanguageArrays, 96);
        this.mItemTheme = AddPopupItem(R.string.can_theme, mThemeArrays, 97);
        this.mItemDlms = AddPopupItem(R.string.can_power_mode, mDlmsArrays, 98);
        this.mItemZxms = AddPopupItem(R.string.can_zxms, mZxmsArrays, 99);
        this.mItemJsms = AddPopupItem(R.string.can_psa_wc_jsms, mJsmsArrays, 100);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 100; i++) {
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
                if (CanJni.GetSubType() != 5) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 3:
                ret = 1;
                break;
            case 4:
                if (CanJni.GetSubType() != 11) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 5:
                if (CanJni.GetSubType() != 11) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 6:
                if (CanJni.GetSubType() != 11) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 7:
                if (CanJni.GetSubType() == 15) {
                    ret = 1;
                    break;
                }
                break;
            case 8:
                if (CanJni.GetSubType() == 15) {
                    ret = 1;
                    break;
                }
                break;
            case 9:
                if (CanJni.GetSubType() == 15) {
                    ret = 1;
                    break;
                }
                break;
            case 10:
                if (CanJni.GetSubType() == 18) {
                    ret = 1;
                    break;
                }
                break;
            case 16:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 17:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 18:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 32:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 33:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 34:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 35:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 48:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 49:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 50:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 51:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 52:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 53:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 54:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 55:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 56:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 64:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 65:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 66:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 73:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 80:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 81:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 82:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 83:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 84:
                if (CanJni.GetSubType() != 12) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 85:
                if (CanJni.GetSubType() != 12 && CanJni.GetSubType() != 13 && CanJni.GetSubType() != 14) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 86:
                if (CanJni.GetSubType() != 12) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 87:
                if (CanJni.GetSubType() != 12) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 88:
                if (CanJni.GetSubType() != 12) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 89:
                ret = 1;
                break;
            case 96:
                ret = 1;
                break;
            case 97:
                if (CanJni.GetSubType() != 12) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 98:
                if (CanJni.GetSubType() == 15) {
                    ret = 1;
                    break;
                }
                break;
            case 99:
                if (CanJni.GetSubType() == 15) {
                    ret = 1;
                    break;
                }
                break;
            case 100:
                if (CanJni.GetSubType() == 15) {
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
                this.mItemBaseTitle.ShowGone(show);
                return;
            case 2:
                this.mItemHsjzddj.ShowGone(show);
                return;
            case 3:
                this.mItemDchygfz.ShowGone(show);
                return;
            case 4:
                this.mItemYbtsy1.ShowGone(show);
                return;
            case 5:
                this.mItemYbtsy2.ShowGone(show);
                return;
            case 6:
                this.mItemYbtsy3.ShowGone(show);
                return;
            case 7:
                this.mItemZnyb.ShowGone(show);
                return;
            case 8:
                this.mItemBszdgc.ShowGone(show);
                return;
            case 9:
                this.mItemZjcmddjs.ShowGone(show);
                return;
            case 10:
                this.mItemWxcdkggn.ShowGone(show);
                return;
            case 16:
                this.mItemXhTitle.ShowGone(show);
                return;
            case 17:
                this.mItemJcszsyxh.ShowGone(show);
                return;
            case 18:
                this.mItemMbtsy.ShowGone(show);
                return;
            case 32:
                this.mItemQfzfzTitle.ShowGone(show);
                return;
            case 33:
                this.mItemQfzyj.ShowGone(show);
                return;
            case 34:
                this.mItemQpzyjlmd.ShowGone(show);
                return;
            case 35:
                this.mItemZdjjzd.ShowGone(show);
                return;
            case 48:
                this.mItemCdfzTitle.ShowGone(show);
                return;
            case 49:
                this.mItemCdfzLmd.ShowGone(show);
                return;
            case 50:
                this.mItemCdfzGnxz.ShowGone(show);
                return;
            case 51:
                this.mItemCdfzYjfs.ShowGone(show);
                return;
            case 52:
                this.mItemCdfzXsbzsb.ShowGone(show);
                return;
            case 53:
                this.mItemCdfzZdqd.ShowGone(show);
                return;
            case 54:
                this.mItemCdfzCsyj.ShowGone(show);
                return;
            case 55:
                this.mItemCdfzCsyjpc.ShowGone(show);
                return;
            case 56:
                this.mItemCdfzCsyjtsy.ShowGone(show);
                return;
            case 64:
                this.mItemAvmTitle.ShowGone(show);
                return;
            case 65:
                this.mItemAvmDszxdzdjh.ShowGone(show);
                return;
            case 66:
                this.mItemAvmCqldzdjh.ShowGone(show);
                return;
            case 73:
                this.mItemHfyjTitle.ShowGone(show);
                return;
            case 80:
                this.mItemHfyjBxfz.ShowGone(show);
                return;
            case 81:
                this.mItemHfyjDchxyj.ShowGone(show);
                return;
            case 82:
                this.mItemHfyjHzwyj.ShowGone(show);
                return;
            case 83:
                this.mItemHfyjHzwyjTsy.ShowGone(show);
                return;
            case 84:
                this.mItemYcmqyxTitle.ShowGone(show);
                return;
            case 85:
                this.mItemYcmqyxZxdzdjh.ShowGone(show);
                return;
            case 86:
                this.mItemYcmqyxQldzdjh.ShowGone(show);
                return;
            case 87:
                this.mItemJsmsjy.ShowGone(show);
                return;
            case 88:
                this.mItemYldx.ShowGone(show);
                return;
            case 89:
                this.mItemSystemSetTitle.ShowGone(show);
                return;
            case 96:
                this.mItemLanguge.ShowGone(show);
                return;
            case 97:
                this.mItemTheme.ShowGone(show);
                return;
            case 98:
                this.mItemDlms.ShowGone(show);
                return;
            case 99:
                this.mItemZxms.ShowGone(show);
                return;
            case 100:
                this.mItemJsms.ShowGone(show);
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
    public CanItemPopupList AddPopupItem(int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int resId, int[] arry, int Id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, resId, arry, (CanItemPopupList.onPopItemClick) this);
        item.SetId(Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemBlankTextList AddTitleItem(int resId) {
        CanItemBlankTextList item = new CanItemBlankTextList((Context) this, resId);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.Cs75CarSet(1, NegSwSet(this.mSetData.Hsjzdzd));
                return;
            case 3:
                CanJni.Cs75CarSet(2, NegSwSet(this.mSetData.Dchygfz));
                return;
            case 7:
                CanJni.Cs75CarSet(60, NegSwSet(this.mSetData.Znyb));
                return;
            case 8:
                CanJni.Cs75CarSet(61, NegSwSet(this.mSetData.Bszdgc));
                return;
            case 9:
                CanJni.Cs75CarSet(62, NegSwSet(this.mSetData.Zjcmddjs));
                return;
            case 10:
                CanJni.Cs75CarSet(73, NegSwSet(this.mSetData.Wxcdkggn));
                return;
            case 17:
                CanJni.Cs75CarSet(27, NegSwSet(this.mSetData.Jcszsyxh));
                return;
            case 33:
                CanJni.Cs75CarSet(29, NegSwSet(this.mSetData.Qfzyj));
                return;
            case 35:
                CanJni.Cs75CarSet(31, NegSwSet(this.mSetData.Zdjjzd));
                return;
            case 52:
                CanJni.Cs75CarSet(35, NegSwSet(this.mSetData.CdfzXsbzsb));
                return;
            case 54:
                CanJni.Cs75CarSet(36, NegSwSet(this.mSetData.CdfzCsyj));
                return;
            case 56:
                CanJni.Cs75CarSet(42, NegSwSet(this.mSetData.Csyjtsy));
                return;
            case 65:
                CanJni.Cs75CarSet(37, NegSwSet(this.mSetData.AvmDszxdzdjh));
                return;
            case 66:
                CanJni.Cs75CarSet(38, NegSwSet(this.mSetData.AvmCqldzdjh));
                return;
            case 80:
                CanJni.Cs75CarSet(43, NegSwSet(this.mSetData.Bxfz));
                return;
            case 81:
                CanJni.Cs75CarSet(44, NegSwSet(this.mSetData.Dchxyj));
                return;
            case 82:
                CanJni.Cs75CarSet(45, NegSwSet(this.mSetData.Hzwyj));
                return;
            case 83:
                CanJni.Cs75CarSet(46, NegSwSet(this.mSetData.Hzwyjtsy));
                return;
            case 85:
                CanJni.Cs75CarSet(51, NegSwSet(this.mSetData.ZxdZdjh));
                return;
            case 86:
                CanJni.Cs75CarSet(52, NegSwSet(this.mSetData.QldZdjh));
                return;
            case 87:
                CanJni.Cs75CarSet(53, NegSwSet(this.mSetData.Jsmsjy));
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
            case 4:
                CanJni.Cs75CarSet(13, item + 1);
                return;
            case 5:
                CanJni.Cs75CarSet(14, item + 1);
                return;
            case 6:
                CanJni.Cs75CarSet(15, item + 1);
                return;
            case 18:
                CanJni.Cs75CarSet(28, item);
                return;
            case 34:
                CanJni.Cs75CarSet(30, item);
                return;
            case 49:
                CanJni.Cs75CarSet(32, item);
                return;
            case 50:
                CanJni.Cs75CarSet(33, item + 1);
                return;
            case 51:
                CanJni.Cs75CarSet(34, item + 1);
                return;
            case 53:
                CanJni.Cs75CarSet(40, item);
                return;
            case 88:
                CanJni.Cs75CarSet(56, item + 1);
                return;
            case 96:
                CanJni.Cs75CarSet(57, item + 1);
                return;
            case 97:
                CanJni.Cs75CarSet(58, item + 1);
                return;
            case 98:
                CanJni.Cs75CarSet(63, item + 1);
                return;
            case 99:
                CanJni.Cs75CarSet(64, item + 1);
                return;
            case 100:
                CanJni.Cs75CarSet(66, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 55:
                CanJni.Cs75CarSet(41, pos);
                return;
            default:
                return;
        }
    }
}
