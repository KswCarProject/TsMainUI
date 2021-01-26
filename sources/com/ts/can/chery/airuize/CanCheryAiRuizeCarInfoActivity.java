package com.ts.can.chery.airuize;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanCheryAiRuizeCarInfoActivity extends CanBaseActivity implements UserCallBack, CanItemPopupList.onPopItemClick, View.OnClickListener, CanItemProgressList.onPosChange, CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2 {
    public static final int ITEM_AIR = 37;
    public static final int ITEM_ALARM = 3;
    public static final int ITEM_AUTO_LOCK = 4;
    public static final int ITEM_AVM_ANIM = 8;
    public static final int ITEM_BDSD = 30;
    public static final int ITEM_BEHIND_LIGHT = 18;
    public static final int ITEM_BWHJ = 29;
    public static final int ITEM_CAR_LINE = 9;
    public static final int ITEM_CDPL = 24;
    public static final int ITEM_DAY_LIGHT = 6;
    public static final int ITEM_EPS = 32;
    public static final int ITEM_FWD = 33;
    public static final int ITEM_FWD_FWDLD = 42;
    public static final int ITEM_FWD_FWDYS = 43;
    public static final int ITEM_FWD_GLJSMS = 40;
    public static final int ITEM_FWD_YYLD = 41;
    public static final int ITEM_GFJTQKQ = 34;
    public static final int ITEM_GFJYSGB = 35;
    public static final int ITEM_HBXKQCD = 36;
    public static final int ITEM_HFMRSZ = 26;
    public static final int ITEM_JLBJXT = 46;
    public static final int ITEM_JYKKHBX = 21;
    public static final int ITEM_KJXSDNL = 22;
    public static final int ITEM_KQJH = 52;
    public static final int ITEM_LANG = 1;
    public static final int ITEM_LIGHT_DELAY = 5;
    public static final int ITEM_MQJC = 23;
    public static final int ITEM_QPYJXT = 48;
    public static final int ITEM_SPEED_VALUE = 17;
    public static final int ITEM_SPEED_WARN = 16;
    public static final int ITEM_SSSB = 27;
    public static final int ITEM_SSTC = 28;
    public static final int ITEM_START_AVM = 7;
    public static final int ITEM_TIP = 2;
    public static final int ITEM_TYPE = 0;
    public static final int ITEM_WHSJZDZD = 25;
    public static final int ITEM_YBD = 38;
    public static final int ITEM_YBGN = 31;
    public static final int ITEM_ZDJJZDXT = 47;
    public static final int ITEM_ZDJS = 20;
    public static final int ITEM_ZNYSGYJSLS = 39;
    public static final int ITEM_ZNYSGYWMKQ = 51;
    public static final int ITEM_ZSYXHXT = 49;
    public static final int ITEM_ZSYXHXT_CJ = 50;
    public static final int ITEM_ZXFZZM = 19;
    public static final int ITEM_ZXLMS = 45;
    public static final int ITEM_ZXLMS_GLJSMS = 44;
    private static final String TAG = "CanCheryAiRuizeCarInfoActivity";
    private static final int[] mAirArray = {R.string.can_mzd_cx4_drive_owner, R.string.can_mzd_cx4_drive_auto};
    private static final int[] mQpzyjArray = {R.string.can_mzd_cx4_mode_off, R.string.can_cdpyyzxt_1, R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_1};
    private static final int[] mZsyxhxtArray = {R.string.can_acc_dis_1, R.string.can_acc_dis_2, R.string.can_acc_dis_3};
    protected String[] mBDSDArray;
    protected String[] mBWHJArray;
    protected String[] mCarLineArray;
    protected String[] mCarType;
    protected String[] mEPSArray;
    protected String[] mFWDLDArray;
    protected String[] mFWDYSArray;
    protected CanItemPopupList mItemAirMode;
    protected CanItemSwitchList mItemAlarm;
    protected CanItemSwitchList mItemAutoLock;
    protected CanItemSwitchList mItemAvmAnim;
    protected CanItemPopupList mItemBDSD;
    protected CanItemPopupList mItemBWHJ;
    protected CanItemProgressList mItemBehindLight;
    protected CanItemPopupList mItemCarLine;
    protected CanItemSwitchList mItemCdpl;
    protected CanItemSwitchList mItemDayLight;
    protected CanItemPopupList mItemEPS;
    protected CanItemSwitchList mItemFWD;
    protected CanItemSwitchList mItemFwdGljsms;
    protected CanItemSwitchList mItemFwdYlld;
    protected CanItemPopupList mItemFwdld;
    protected CanItemPopupList mItemFwdys;
    protected CanItemSwitchList mItemGfjtqkq;
    protected CanItemSwitchList mItemGfjysgb;
    protected CanItemProgressList mItemHbxkqcd;
    protected CanItemTextBtnList mItemHfmrsz;
    protected CanItemSwitchList mItemJlbjxt;
    protected CanItemSwitchList mItemJykkhbx;
    protected CanItemSwitchList mItemKjxsdnl;
    protected CanItemSwitchList mItemKqjh;
    protected CanItemPopupList mItemLang;
    protected CanItemSwitchList mItemLightDelay;
    protected CanItemSwitchList mItemMqjc;
    protected CanItemPopupList mItemQpyjxt;
    protected CanItemSwitchList mItemSpeedWarn;
    protected CanItemProgressList mItemSpeedvalue;
    protected CanItemSwitchList mItemSssb;
    protected CanItemSwitchList mItemSstc;
    protected CanItemSwitchList mItemStartAvm;
    protected CanItemPopupList mItemTip;
    protected CanItemCarType mItemType;
    protected CanItemSwitchList mItemWhsjzdzd;
    protected CanItemSwitchList mItemYBD;
    protected CanItemSwitchList mItemYBGN;
    protected CanItemSwitchList mItemZdjjzdxt;
    protected CanItemSwitchList mItemZdjs;
    protected CanItemSwitchList mItemZnysgjjsls;
    protected CanItemSwitchList mItemZnysgywmkq;
    protected CanItemPopupList mItemZsyxhxt;
    protected CanItemSwitchList mItemZsyxhxtScxzcj;
    protected CanItemSwitchList mItemZxfzzm;
    protected CanItemPopupList mItemZxlms;
    protected CanItemSwitchList mItemZxlmsGljsms;
    protected String[] mLangArr;
    private CanScrollList mManager;
    protected CanDataInfo.AiRuizeCarInfo mSetData = new CanDataInfo.AiRuizeCarInfo();
    protected String[] mTipArr;
    protected String[] mZXLMSArray;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_list);
        Log.d(TAG, "onCreate");
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mCarType = getResources().getStringArray(R.array.can_fs_declare_57);
        this.mLangArr = getResources().getStringArray(R.array.can_tigger7_language_array);
        this.mTipArr = getResources().getStringArray(R.array.can_tigger7_tip_array);
        this.mCarLineArray = getResources().getStringArray(R.array.can_tigger7_car_line_array);
        this.mBWHJArray = new String[]{getResources().getString(R.string.can_off), getResources().getString(R.string.can_mzd_cx4_time_30s), getResources().getString(R.string.can_mzd_cx4_time_60s)};
        this.mBDSDArray = new String[]{getResources().getString(R.string.can_off), getResources().getString(R.string.can_3c), getResources().getString(R.string.can_5c), getResources().getString(R.string.can_7c)};
        this.mEPSArray = new String[]{getResources().getString(R.string.can_sport), getResources().getString(R.string.can_comfort)};
        this.mFWDLDArray = new String[]{String.valueOf(getResources().getString(R.string.can_level)) + "1", String.valueOf(getResources().getString(R.string.can_level)) + "2", String.valueOf(getResources().getString(R.string.can_level)) + "3", String.valueOf(getResources().getString(R.string.can_level)) + MainSet.SP_KS_QOROS, String.valueOf(getResources().getString(R.string.can_level)) + MainSet.SP_TW_CJW};
        this.mFWDYSArray = new String[]{getResources().getString(R.string.can_color_red), getResources().getString(R.string.can_orange_color), getResources().getString(R.string.can_color_yellow), getResources().getString(R.string.can_magoten_light_color_2), getResources().getString(R.string.can_qingse), getResources().getString(R.string.can_color_blue), getResources().getString(R.string.can_purple)};
        this.mZXLMSArray = new String[]{getResources().getString(R.string.can_sport), getResources().getString(R.string.can_comfort)};
        this.mItemType = AddCarTypeItem(R.string.can_car_type_select, this.mCarType, 0);
        this.mItemLang = AddPopupItem(R.string.can_language, this.mLangArr, 1);
        this.mItemTip = AddPopupItem(R.string.can_tslx, this.mTipArr, 2);
        this.mItemAlarm = AddCheckItem(R.string.can_tigger7_alarm, 3);
        this.mItemAutoLock = AddCheckItem(R.string.can_tigger7_auto_lock, 4);
        this.mItemLightDelay = AddCheckItem(R.string.can_tigger7_light_delay, 5);
        this.mItemDayLight = AddCheckItem(R.string.can_tigger7_day_light, 6);
        this.mItemStartAvm = AddCheckItem(R.string.can_tigger7_start_avm, 7);
        this.mItemAvmAnim = AddCheckItem(R.string.can_tigger7_avm_anim, 8);
        this.mItemCarLine = AddPopupItem(R.string.can_tigger7_car_line, this.mCarLineArray, 9);
        this.mItemSpeedWarn = AddCheckItem(R.string.can_tigger7_speed_warn, 16);
        this.mItemSpeedvalue = AddProgressItem(R.string.can_tigger7_speed_value, 17, 1, 1, 21);
        this.mItemSpeedvalue.SetUserValText();
        this.mItemBehindLight = AddProgressItem(R.string.can_tigger7_behind_light, 18, 1, 1, 10);
        this.mItemZxfzzm = AddCheckItem(R.string.can_airuiz7_zxfzzm, 19);
        this.mItemZdjs = AddCheckItem(R.string.can_zdjs, 20);
        this.mItemJykkhbx = AddCheckItem(R.string.can_airuiz7_jykkhbx, 21);
        this.mItemKjxsdnl = AddCheckItem(R.string.can_airuiz7_kjxsdnl, 22);
        this.mItemMqjc = AddCheckItem(R.string.can_blind_spot_monitoring, 23);
        this.mItemCdpl = AddCheckItem(R.string.can_cdpl, 24);
        this.mItemWhsjzdzd = AddCheckItem(R.string.can_zdhsjzd, 25);
        this.mItemSssb = AddCheckItem(R.string.can_sssb, 27);
        this.mItemSstc = AddCheckItem(R.string.can_sstc, 28);
        this.mItemBWHJ = AddPopupItem(R.string.can_bwhj, this.mBWHJArray, 29);
        this.mItemBDSD = AddPopupItem(R.string.can_bdsd, this.mBDSDArray, 30);
        this.mItemYBGN = AddCheckItem(R.string.can_yb_func, 31);
        this.mItemEPS = AddPopupItem(R.string.can_eps_mode, this.mEPSArray, 32);
        this.mItemFWD = AddCheckItem(R.string.can_hant_fwd, 33);
        this.mItemGfjtqkq = AddCheckItem(R.string.can_gfjtqkq, 34);
        this.mItemGfjysgb = AddCheckItem(R.string.can_gfjysgb, 35);
        this.mItemHbxkqcd = AddProgressItem(R.string.can_hbx_kd, 36, 1, 50, 100);
        this.mItemHbxkqcd.SetUserValText();
        this.mItemAirMode = AddPopupItem(R.string.can_dfqc_ac, mAirArray, 37);
        this.mItemYBD = AddCheckItem(R.string.can_yb_light, 38);
        this.mItemZnysgjjsls = AddCheckItem(R.string.can_znysgyjsls, 39);
        this.mItemFwdGljsms = AddCheckItem(R.string.can_fwdgljsms, 40);
        this.mItemFwdYlld = AddCheckItem(R.string.can_fwdyyld, 41);
        this.mItemFwdld = AddPopupItem(R.string.can_fwdlddj, this.mFWDLDArray, 42);
        this.mItemFwdys = AddPopupItem(R.string.can_fwd_color, this.mFWDYSArray, 43);
        this.mItemZxlmsGljsms = AddCheckItem(R.string.can_zxlmsgljsms, 44);
        this.mItemZxlms = AddPopupItem(R.string.can_zxlms, this.mZXLMSArray, 45);
        this.mItemJlbjxt = AddCheckItem(R.string.can_jsfz_jlbjxt, 46);
        this.mItemZdjjzdxt = AddCheckItem(R.string.can_zdjjzdxt, 47);
        this.mItemQpyjxt = AddPopupItem(R.string.can_qpzyj, mQpzyjArray, 48);
        this.mItemZsyxhxt = AddPopupItem(R.string.can_acc_drive, mZsyxhxtArray, 49);
        this.mItemZsyxhxtScxzcj = AddCheckItem(R.string.can_last_dis_selected, 50);
        this.mItemZnysgywmkq = AddCheckItem(R.string.can_znysgywmkq, 51);
        this.mItemKqjh = AddCheckItem(R.string.air_purifier, 52);
        this.mItemHfmrsz = AddTextBtn(R.string.can_factory_set, 26);
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
    public CanItemCarType AddCarTypeItem(int textId, String[] array, int id) {
        CanItemCarType item = new CanItemCarType((Context) this, textId, array, id, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(item.GetView());
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
    public CanItemSwitchList AddCheckItem(int text, int id) {
        CanItemSwitchList item = new CanItemSwitchList(this, text);
        item.SetIdClickListener(this, id);
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

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(false);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        int subType = CanJni.GetSubType();
        this.mItemType.GetPopItem().SetSel(subType);
        showItem(subType);
        CanJni.CheryRzcQuery(64, 0);
    }

    private void showItem(int subType) {
        int i = subType;
        if (subType == 2) {
            this.mItemAutoLock.ShowGone(true);
            this.mItemDayLight.ShowGone(true);
            this.mItemSpeedWarn.ShowGone(true);
            this.mItemSpeedvalue.ShowGone(true);
            this.mItemAirMode.ShowGone(true);
        } else if (subType == 3) {
            this.mItemAirMode.ShowGone(true);
        } else if (subType == 4) {
            this.mItemZxfzzm.ShowGone(true);
            this.mItemZdjs.ShowGone(true);
            this.mItemJykkhbx.ShowGone(true);
            this.mItemKjxsdnl.ShowGone(true);
            this.mItemAirMode.ShowGone(true);
        } else if (subType == 5) {
            this.mItemTip.ShowGone(true);
            this.mItemAutoLock.ShowGone(true);
            this.mItemLightDelay.ShowGone(true);
            this.mItemDayLight.ShowGone(true);
            this.mItemStartAvm.ShowGone(true);
            this.mItemAvmAnim.ShowGone(true);
            this.mItemCarLine.ShowGone(true);
            this.mItemSpeedWarn.ShowGone(true);
            this.mItemSpeedvalue.ShowGone(true);
            this.mItemBehindLight.ShowGone(true);
            this.mItemMqjc.ShowGone(true);
            this.mItemCdpl.ShowGone(true);
            this.mItemWhsjzdzd.ShowGone(true);
            this.mItemHfmrsz.ShowGone(true);
            this.mItemAirMode.ShowGone(true);
        } else if (subType == 1) {
            this.mItemTip.ShowGone(true);
            this.mItemAutoLock.ShowGone(true);
            this.mItemLightDelay.ShowGone(true);
            this.mItemDayLight.ShowGone(true);
            this.mItemStartAvm.ShowGone(true);
            this.mItemAvmAnim.ShowGone(true);
            this.mItemCarLine.ShowGone(true);
            this.mItemSpeedWarn.ShowGone(true);
            this.mItemSpeedvalue.ShowGone(true);
            this.mItemBehindLight.ShowGone(true);
            this.mItemAirMode.ShowGone(true);
        } else if (subType == 6) {
            this.mItemTip.ShowGone(true);
            this.mItemAutoLock.ShowGone(true);
            this.mItemDayLight.ShowGone(true);
            this.mItemStartAvm.ShowGone(true);
            this.mItemAvmAnim.ShowGone(true);
            this.mItemCarLine.ShowGone(true);
            this.mItemSpeedWarn.ShowGone(true);
            this.mItemSpeedvalue.ShowGone(true);
            this.mItemBehindLight.ShowGone(true);
            this.mItemWhsjzdzd.ShowGone(true);
            this.mItemSssb.ShowGone(true);
            this.mItemSstc.ShowGone(true);
            this.mItemHfmrsz.ShowGone(true);
            this.mItemAirMode.ShowGone(true);
        } else if (subType == 7) {
            this.mItemAlarm.ShowGone(true);
            this.mItemAutoLock.ShowGone(true);
            this.mItemZdjs.ShowGone(true);
            this.mItemMqjc.ShowGone(true);
            this.mItemCdpl.ShowGone(true);
            this.mItemWhsjzdzd.ShowGone(true);
            this.mItemBWHJ.ShowGone(true);
            this.mItemBDSD.ShowGone(true);
            this.mItemYBGN.ShowGone(true);
            this.mItemEPS.ShowGone(true);
            this.mItemFWD.ShowGone(true);
        } else if (subType == 8) {
            this.mItemTip.ShowGone(true);
            this.mItemAutoLock.ShowGone(true);
            this.mItemLightDelay.ShowGone(true);
            this.mItemDayLight.ShowGone(true);
            this.mItemStartAvm.ShowGone(true);
            this.mItemAvmAnim.ShowGone(true);
            this.mItemCarLine.ShowGone(true);
            this.mItemSpeedvalue.ShowGone(true);
            this.mItemBehindLight.ShowGone(true);
            this.mItemMqjc.ShowGone(true);
            this.mItemCdpl.ShowGone(true);
            this.mItemWhsjzdzd.ShowGone(true);
            this.mItemHfmrsz.ShowGone(true);
            this.mItemAirMode.ShowGone(true);
        } else if (subType == 9) {
            this.mItemTip.ShowGone(true);
            this.mItemAutoLock.ShowGone(true);
            this.mItemLightDelay.ShowGone(true);
            this.mItemDayLight.ShowGone(true);
            this.mItemStartAvm.ShowGone(true);
            this.mItemAvmAnim.ShowGone(true);
            this.mItemCarLine.ShowGone(true);
            this.mItemSpeedvalue.ShowGone(true);
            this.mItemBehindLight.ShowGone(true);
            this.mItemMqjc.ShowGone(true);
            this.mItemCdpl.ShowGone(true);
            this.mItemWhsjzdzd.ShowGone(true);
            this.mItemEPS.ShowGone(true);
            this.mItemGfjtqkq.ShowGone(true);
            this.mItemGfjysgb.ShowGone(true);
            this.mItemHbxkqcd.ShowGone(true);
            this.mItemHfmrsz.ShowGone(true);
            this.mItemAirMode.ShowGone(true);
        } else if (subType == 11) {
            this.mItemTip.ShowGone(true);
            this.mItemAutoLock.ShowGone(true);
            this.mItemDayLight.ShowGone(true);
            this.mItemSpeedWarn.ShowGone(true);
            this.mItemBehindLight.ShowGone(true);
            this.mItemFWD.ShowGone(true);
            this.mItemAirMode.ShowGone(true);
            this.mItemYBD.ShowGone(true);
            this.mItemZnysgjjsls.ShowGone(true);
            this.mItemFwdGljsms.ShowGone(true);
            this.mItemFwdYlld.ShowGone(true);
            this.mItemFwdld.ShowGone(true);
            this.mItemFwdys.ShowGone(true);
            this.mItemZxlmsGljsms.ShowGone(true);
            this.mItemZxlms.ShowGone(true);
            this.mItemHfmrsz.ShowGone(true);
        } else if (subType == 12) {
            this.mItemTip.ShowGone(true);
            this.mItemAutoLock.ShowGone(true);
            this.mItemDayLight.ShowGone(true);
            this.mItemSpeedWarn.ShowGone(true);
        } else if (subType == 13) {
            this.mItemTip.ShowGone(true);
            this.mItemAutoLock.ShowGone(true);
            this.mItemLightDelay.ShowGone(true);
            this.mItemDayLight.ShowGone(true);
            this.mItemSpeedWarn.ShowGone(true);
            this.mItemBehindLight.ShowGone(true);
            this.mItemWhsjzdzd.ShowGone(true);
            this.mItemFWD.ShowGone(true);
            this.mItemGfjtqkq.ShowGone(true);
            this.mItemGfjysgb.ShowGone(true);
            this.mItemHbxkqcd.ShowGone(true);
            this.mItemYBD.ShowGone(true);
            this.mItemZnysgjjsls.ShowGone(true);
            this.mItemFwdGljsms.ShowGone(true);
            this.mItemFwdYlld.ShowGone(true);
            this.mItemFwdld.ShowGone(true);
            this.mItemFwdys.ShowGone(true);
            this.mItemZxlmsGljsms.ShowGone(true);
            this.mItemZxlms.ShowGone(true);
            this.mItemJlbjxt.ShowGone(true);
            this.mItemZdjjzdxt.ShowGone(true);
            this.mItemQpyjxt.ShowGone(true);
            this.mItemZsyxhxt.ShowGone(true);
            this.mItemZsyxhxtScxzcj.ShowGone(true);
            this.mItemZnysgywmkq.ShowGone(true);
            this.mItemKqjh.ShowGone(true);
            this.mItemAirMode.ShowGone(true);
            this.mItemHfmrsz.ShowGone(true);
        } else {
            this.mItemLang.ShowGone(true);
            this.mItemTip.ShowGone(true);
            this.mItemAlarm.ShowGone(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void UserAll() {
        ResetData(true);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.AiRuizeGetCarSet(this.mSetData);
        if (i2b(this.mSetData.LangUpdateOnce) && (!check || i2b(this.mSetData.LangUpdate))) {
            this.mSetData.LangUpdate = 0;
            this.mItemLang.SetSel(this.mSetData.Lang);
        }
        if (i2b(this.mSetData.IllUpdateOnce) && (!check || i2b(this.mSetData.IllUpdate))) {
            this.mSetData.IllUpdate = 0;
            this.mItemTip.SetSel(this.mSetData.Ill);
        }
        if (i2b(this.mSetData.JjzdbjUpdateOnce) && (!check || i2b(this.mSetData.JjzdbjUpdate))) {
            this.mSetData.JjzdbjUpdate = 0;
            this.mItemAlarm.SetCheck(this.mSetData.Jjzdbj);
        }
        if (i2b(this.mSetData.ZdlsUpdateOnce) && (!check || i2b(this.mSetData.ZdlsUpdate))) {
            this.mSetData.ZdlsUpdate = 0;
            this.mItemAutoLock.SetCheck(this.mSetData.Zdls);
        }
        if (i2b(this.mSetData.QzdysUpdateOnce) && (!check || i2b(this.mSetData.QzdysUpdate))) {
            this.mSetData.QzdysUpdate = 0;
            this.mItemLightDelay.SetCheck(this.mSetData.Qzdys);
        }
        if (i2b(this.mSetData.RjxcdUpdateOnce) && (!check || i2b(this.mSetData.RjxcdUpdate))) {
            this.mSetData.RjxcdUpdate = 0;
            this.mItemDayLight.SetCheck(this.mSetData.Rjxcd);
        }
        if (i2b(this.mSetData.ZxqdAvmUpdateOnce) && (!check || i2b(this.mSetData.ZxqdAvmUpdate))) {
            this.mSetData.ZxqdAvmUpdate = 0;
            this.mItemStartAvm.SetCheck(this.mSetData.ZxqdAvm);
        }
        if (i2b(this.mSetData.AvmqddhUpdateOnce) && (!check || i2b(this.mSetData.AvmqddhUpdate))) {
            this.mSetData.AvmqddhUpdate = 0;
            this.mItemAvmAnim.SetCheck(this.mSetData.Avmqddh);
        }
        if (i2b(this.mSetData.CfxUpdateOnce) && (!check || i2b(this.mSetData.CfxUpdate))) {
            this.mSetData.CfxUpdate = 0;
            this.mItemCarLine.SetSel(this.mSetData.Cfx);
        }
        if (i2b(this.mSetData.CsbjUpdateOnce) && (!check || i2b(this.mSetData.CsbjUpdate))) {
            this.mSetData.CsbjUpdate = 0;
            this.mItemSpeedWarn.SetCheck(this.mSetData.Csbj);
            this.mItemSpeedvalue.ShowGone(this.mSetData.Csbj);
            if (this.mSetData.Csbj > 0 && this.mSetData.Csbj < 22) {
                this.mItemSpeedvalue.SetCurVal(this.mSetData.Csbj);
                this.mItemSpeedvalue.SetValText(String.valueOf(((this.mSetData.Csbj - 1) * 5) + 30) + " km/h");
            }
        }
        if (i2b(this.mSetData.YbbgUpdateOnce) && (!check || i2b(this.mSetData.YbbgUpdate))) {
            this.mSetData.YbbgUpdate = 0;
            if (this.mSetData.Ybbg >= 0 && this.mSetData.Ybbg <= 9) {
                this.mItemBehindLight.SetCurVal(this.mSetData.Ybbg + 1);
            }
        }
        if (i2b(this.mSetData.ZxbfzmUpdateOnce) && (!check || i2b(this.mSetData.ZxbfzmUpdate))) {
            this.mSetData.ZxbfzmUpdate = 0;
            this.mItemZxfzzm.SetCheck(this.mSetData.Zxbfzm);
        }
        if (i2b(this.mSetData.ZdjsUpdateOnce) && (!check || i2b(this.mSetData.ZdjsUpdate))) {
            this.mSetData.ZdjsUpdate = 0;
            this.mItemZdjs.SetCheck(this.mSetData.Zdjs);
        }
        if (i2b(this.mSetData.JykkhbxUpdateOnce) && (!check || i2b(this.mSetData.JykkhbxUpdate))) {
            this.mSetData.JykkhbxUpdate = 0;
            this.mItemJykkhbx.SetCheck(this.mSetData.Jykkhbx);
        }
        if (i2b(this.mSetData.KjxsdnlUpdateOnce) && (!check || i2b(this.mSetData.KjxsdnlUpdate))) {
            this.mSetData.KjxsdnlUpdate = 0;
            this.mItemKjxsdnl.SetCheck(this.mSetData.Kjxsdnl);
        }
        if (i2b(this.mSetData.MqjcUpdateOnce) && (!check || i2b(this.mSetData.MqjcUpdate))) {
            this.mSetData.MqjcUpdate = 0;
            this.mItemMqjc.SetCheck(this.mSetData.Mqjc);
        }
        if (i2b(this.mSetData.CdplUpdateOnce) && (!check || i2b(this.mSetData.CdplUpdate))) {
            this.mSetData.CdplUpdate = 0;
            this.mItemCdpl.SetCheck(this.mSetData.Cdpl);
        }
        if (i2b(this.mSetData.WhsjzdzdUpdateOnce) && (!check || i2b(this.mSetData.WhsjzdzdUpdate))) {
            this.mSetData.WhsjzdzdUpdate = 0;
            this.mItemWhsjzdzd.SetCheck(this.mSetData.Whsjzdzd);
        }
        if (i2b(this.mSetData.SssbUpdateOnce) && (!check || i2b(this.mSetData.SssbUpdate))) {
            this.mSetData.SssbUpdate = 0;
            this.mItemSssb.SetCheck(this.mSetData.Sssb);
        }
        if (i2b(this.mSetData.SstcUpdateOnce) && (!check || i2b(this.mSetData.SstcUpdate))) {
            this.mSetData.SstcUpdate = 0;
            this.mItemSstc.SetCheck(this.mSetData.Sstc);
        }
        if (i2b(this.mSetData.BwhjUpdateOnce) && (!check || i2b(this.mSetData.BwhjUpdate))) {
            this.mSetData.BwhjUpdate = 0;
            this.mItemBWHJ.SetSel(this.mSetData.Bwhj);
        }
        if (i2b(this.mSetData.BdsdUpdateOnce) && (!check || i2b(this.mSetData.BdsdUpdate))) {
            this.mSetData.BdsdUpdate = 0;
            this.mItemBDSD.SetSel(this.mSetData.Bdsd);
        }
        if (i2b(this.mSetData.YbgnUpdateOnce) && (!check || i2b(this.mSetData.YbgnUpdate))) {
            this.mSetData.YbgnUpdate = 0;
            this.mItemYBGN.SetCheck(this.mSetData.Ybgn);
        }
        if (i2b(this.mSetData.EpsDzzlzxUpdateOnce) && (!check || i2b(this.mSetData.EpsDzzlzxUpdate))) {
            this.mSetData.EpsDzzlzxUpdate = 0;
            this.mItemEPS.SetSel(this.mSetData.EpsDzzlzx);
        }
        if (i2b(this.mSetData.FwdUpdateOnce) && (!check || i2b(this.mSetData.FwdUpdate))) {
            this.mSetData.FwdUpdate = 0;
            this.mItemFWD.SetCheck(this.mSetData.Fwd);
        }
        if (i2b(this.mSetData.GfjtqkqUpdateOnce) && (!check || i2b(this.mSetData.GfjtqkqUpdate))) {
            this.mSetData.GfjtqkqUpdate = 0;
            this.mItemGfjtqkq.SetCheck(this.mSetData.Gfjtqkq);
        }
        if (i2b(this.mSetData.GfjycgbUpdateOnce) && (!check || i2b(this.mSetData.GfjycgbUpdate))) {
            this.mSetData.GfjtqkqUpdate = 0;
            this.mItemGfjysgb.SetCheck(this.mSetData.Gfjycgb);
        }
        if (i2b(this.mSetData.HbxkqcdUpdateOnce) && (!check || i2b(this.mSetData.HbxkqcdUpdate))) {
            this.mSetData.HbxkqcdUpdate = 0;
            if (this.mSetData.Hbxkqcd >= 50 && this.mSetData.Csbj <= 100) {
                this.mItemHbxkqcd.SetCurVal(this.mSetData.Hbxkqcd);
                this.mItemHbxkqcd.SetValText(String.valueOf(this.mSetData.Hbxkqcd) + " %");
            }
        }
        if (i2b(this.mSetData.AirModeUpdateOnce) && (!check || i2b(this.mSetData.AirModeUpdate))) {
            this.mSetData.AirModeUpdate = 0;
            this.mItemAirMode.SetSel(this.mSetData.AirMode);
        }
        if (i2b(this.mSetData.FwdldUpdateOnce) && (!check || i2b(this.mSetData.FwdldUpdate))) {
            this.mSetData.FwdldUpdate = 0;
            this.mItemFwdld.SetSel(this.mSetData.Fwdld - 1);
        }
        if (i2b(this.mSetData.FwdysUpdateOnce) && (!check || i2b(this.mSetData.FwdysUpdate))) {
            this.mSetData.FwdysUpdate = 0;
            this.mItemFwdys.SetSel(this.mSetData.Fwdys - 1);
        }
        if (i2b(this.mSetData.ZxlmsGljsmsyxUpdateOnce) && (!check || i2b(this.mSetData.ZxlmsGljsmsyxUpdate))) {
            this.mSetData.ZxlmsGljsmsyxUpdate = 0;
            this.mItemZxlms.SetSel(this.mSetData.ZxlmsGljsmsyx);
        }
        if (CanJni.GetSubType() == 11) {
            if (i2b(this.mSetData.ZxlmsGljsmsUpdateOnce) && (!check || i2b(this.mSetData.ZxlmsGljsmsUpdate))) {
                this.mSetData.ZxlmsGljsmsUpdate = 0;
                this.mItemZxlmsGljsms.SetCheck(this.mSetData.ZxlmsGljsms);
                this.mItemZxlms.ShowGone(this.mSetData.ZxlmsGljsms);
            }
            if (i2b(this.mSetData.YlldUpdateOnce) && (!check || i2b(this.mSetData.YlldUpdate))) {
                this.mSetData.YlldUpdate = 0;
                this.mItemFwdYlld.SetCheck(this.mSetData.Ylld);
                this.mItemFwdld.ShowGone(this.mSetData.Ylld);
                this.mItemFwdys.ShowGone(this.mSetData.Ylld);
            }
        }
        if (i2b(this.mSetData.FwdGljsmsUpdateOnce) && (!check || i2b(this.mSetData.FwdGljsmsUpdate))) {
            this.mSetData.FwdGljsmsUpdate = 0;
            this.mItemFwdGljsms.SetCheck(this.mSetData.FwdGljsms);
        }
        if (i2b(this.mSetData.ZnysgyjslsUpdateOnce) && (!check || i2b(this.mSetData.ZnysgyjslsUpdate))) {
            this.mSetData.ZnysgyjslsUpdate = 0;
            this.mItemZnysgjjsls.SetCheck(this.mSetData.Znysgyjsls);
        }
        if (i2b(this.mSetData.YbdUpdateOnce) && (!check || i2b(this.mSetData.YbdUpdate))) {
            this.mSetData.YbdUpdate = 0;
            this.mItemYBD.SetCheck(this.mSetData.Ybd);
        }
        if (i2b(this.mSetData.JlbjxtUpdateOnce) && (!check || i2b(this.mSetData.JlbjxtUpdate))) {
            this.mSetData.JlbjxtUpdate = 0;
            this.mItemJlbjxt.SetCheck(this.mSetData.Jlbjxt);
        }
        if (i2b(this.mSetData.ZdjjzdxtUpdateOnce) && (!check || i2b(this.mSetData.ZdjjzdxtUpdate))) {
            this.mSetData.ZdjjzdxtUpdate = 0;
            this.mItemZdjjzdxt.SetCheck(this.mSetData.Zdjjzdxt);
        }
        if (i2b(this.mSetData.QpyjxtUpdateOnce) && (!check || i2b(this.mSetData.QpyjxtUpdate))) {
            this.mSetData.QpyjxtUpdate = 0;
            this.mItemQpyjxt.SetSel(this.mSetData.Qpyjxt - 1);
        }
        if (i2b(this.mSetData.ZsyxhxtUpdateOnce) && (!check || i2b(this.mSetData.ZsyxhxtUpdate))) {
            this.mSetData.ZsyxhxtUpdate = 0;
            this.mItemZsyxhxt.SetSel(this.mSetData.Zsyxhxt - 1);
        }
        if (i2b(this.mSetData.ZsyxhxtScxzcjUpdateOnce) && (!check || i2b(this.mSetData.ZsyxhxtScxzcjUpdate))) {
            this.mSetData.ZsyxhxtScxzcjUpdate = 0;
            this.mItemZsyxhxtScxzcj.SetCheck(this.mSetData.ZsyxhxtScxzcj);
        }
        if (i2b(this.mSetData.ZnysgywmkqUpdateOnce) && (!check || i2b(this.mSetData.ZnysgywmkqUpdate))) {
            this.mSetData.ZnysgywmkqUpdate = 0;
            this.mItemZnysgywmkq.SetCheck(this.mSetData.Znysgywmkq);
        }
        if (!i2b(this.mSetData.KqjhUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.KqjhUpdate)) {
            this.mSetData.KqjhUpdate = 0;
            this.mItemKqjh.SetCheck(this.mSetData.Kqjh);
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                FtSet.SetCanSubT(item);
                Mcu.SendXKey(20);
                return;
            case 1:
                CanJni.AiRuizeCarSet(0, item);
                return;
            case 2:
                CanJni.AiRuizeCarSet(1, item);
                return;
            case 9:
                CanJni.AiRuizeCarSet(8, item);
                return;
            case 29:
                CanJni.AiRuizeCarSet(20, item);
                return;
            case 30:
                CanJni.AiRuizeCarSet(21, item);
                return;
            case 32:
                CanJni.AiRuizeCarSet(23, item);
                return;
            case 37:
                CanJni.AiRuizeCarSet(Can.CAN_FLAT_RZC, item);
                return;
            case 42:
                CanJni.AiRuizeCarSet(32, item + 1);
                return;
            case 43:
                CanJni.AiRuizeCarSet(33, item + 1);
                return;
            case 45:
                CanJni.AiRuizeCarSet(35, item);
                return;
            case 48:
                CanJni.AiRuizeCarSet(38, item + 1);
                return;
            case 49:
                CanJni.AiRuizeCarSet(39, item + 1);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                CanJni.AiRuizeCarSet(2, SwValue(this.mSetData.Jjzdbj));
                return;
            case 4:
                CanJni.AiRuizeCarSet(3, SwValue(this.mSetData.Zdls));
                return;
            case 5:
                CanJni.AiRuizeCarSet(4, SwValue(this.mSetData.Qzdys));
                return;
            case 6:
                CanJni.AiRuizeCarSet(5, SwValue(this.mSetData.Rjxcd));
                return;
            case 7:
                CanJni.AiRuizeCarSet(6, SwValue(this.mSetData.ZxqdAvm));
                return;
            case 8:
                CanJni.AiRuizeCarSet(7, SwValue(this.mSetData.Avmqddh));
                return;
            case 16:
                CanJni.AiRuizeCarSet(9, SwValue(this.mSetData.Csbj));
                return;
            case 19:
                CanJni.AiRuizeCarSet(11, SwValue(this.mSetData.Zxbfzm));
                return;
            case 20:
                CanJni.AiRuizeCarSet(12, SwValue(this.mSetData.Zdjs));
                return;
            case 21:
                CanJni.AiRuizeCarSet(13, SwValue(this.mSetData.Jykkhbx));
                return;
            case 22:
                CanJni.AiRuizeCarSet(14, SwValue(this.mSetData.Kjxsdnl));
                return;
            case 23:
                CanJni.AiRuizeCarSet(15, SwValue(this.mSetData.Mqjc));
                return;
            case 24:
                CanJni.AiRuizeCarSet(16, SwValue(this.mSetData.Cdpl));
                return;
            case 25:
                CanJni.AiRuizeCarSet(17, SwValue(this.mSetData.Whsjzdzd));
                return;
            case 26:
                new CanItemMsgBox(26, this, R.string.can_sure_setup, this, this);
                return;
            case 27:
                CanJni.AiRuizeCarSet(18, SwValue(this.mSetData.Sssb));
                return;
            case 28:
                CanJni.AiRuizeCarSet(19, SwValue(this.mSetData.Sstc));
                return;
            case 31:
                CanJni.AiRuizeCarSet(22, SwValue(this.mSetData.Ybgn));
                return;
            case 33:
                CanJni.AiRuizeCarSet(24, SwValue(this.mSetData.Fwd));
                return;
            case 34:
                CanJni.AiRuizeCarSet(25, SwValue(this.mSetData.Gfjtqkq));
                return;
            case 35:
                CanJni.AiRuizeCarSet(26, SwValue(this.mSetData.Gfjycgb));
                return;
            case 38:
                CanJni.AiRuizeCarSet(28, SwValue(this.mSetData.Ybd));
                return;
            case 39:
                CanJni.AiRuizeCarSet(29, SwValue(this.mSetData.Znysgyjsls));
                return;
            case 40:
                CanJni.AiRuizeCarSet(30, SwValue(this.mSetData.FwdGljsms));
                return;
            case 41:
                CanJni.AiRuizeCarSet(31, SwValue(this.mSetData.Ylld));
                return;
            case 44:
                CanJni.AiRuizeCarSet(34, SwValue(this.mSetData.ZxlmsGljsms));
                return;
            case 46:
                CanJni.AiRuizeCarSet(36, SwValue(this.mSetData.Jlbjxt));
                return;
            case 47:
                CanJni.AiRuizeCarSet(37, SwValue(this.mSetData.Zdjjzdxt));
                return;
            case 50:
                CanJni.AiRuizeCarSet(40, SwValue(this.mSetData.ZsyxhxtScxzcj));
                return;
            case 51:
                CanJni.AiRuizeCarSet(41, SwValue(this.mSetData.Znysgywmkq));
                return;
            case 52:
                CanJni.AiRuizeCarSet(42, SwValue(this.mSetData.Kqjh));
                return;
            default:
                return;
        }
    }

    private int SwValue(int val) {
        if (val == 0) {
            return 1;
        }
        return 0;
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 17:
                CanJni.AiRuizeCarSet(9, pos);
                return;
            case 18:
                CanJni.AiRuizeCarSet(10, pos);
                return;
            case 36:
                CanJni.AiRuizeCarSet(27, pos);
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        switch (param) {
            case 26:
                CanJni.AiRuizeCarSet(255, 1);
                return;
            default:
                return;
        }
    }

    public void onCancel(int param) {
        switch (param) {
            case 26:
                CanJni.AiRuizeCarSet(255, 0);
                return;
            default:
                return;
        }
    }
}
