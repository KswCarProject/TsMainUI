package com.ts.can.ford;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanFordCarSetActivity extends CanFordBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_HJD = 10;
    public static final int ITEM_JBTSY = 3;
    public static final int ITEM_LCDW = 5;
    public static final int ITEM_LDMS = 6;
    private static final int ITEM_MAX = 10;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_QYLXT = 1;
    public static final int ITEM_RVSKEEP = 9;
    public static final int ITEM_TPMS = 8;
    public static final int ITEM_XXTSY = 2;
    public static final int ITEM_YSGYKG = 7;
    public static final int ITEM_ZXD = 4;
    public static final String TAG = "CanPSACarSetActivity";
    private static final int[] mLangArr = {R.string.lang_en_uk, R.string.lang_cn, R.string.lang_pyccknn, R.string.lang_francais, R.string.lang_deutsch, R.string.lang_espanol, R.string.lang_itanliano, R.string.lang_nederlands, R.string.lang_portugues, R.string.lang_turkce};
    private static final String[] mLcdwArr = {"km", "mi"};
    private static final int[] mLdmsArr = {R.string.can_shoudong, R.string.can_type_mode_auto};
    private CanDataInfo.FordAdt mAdtData = new CanDataInfo.FordAdt();
    private String[] mFwzmArr;
    private CanItemSwitchList mItemHjd;
    private CanItemSwitchList mItemJbtsy;
    private CanItemPopupList mItemLcdw;
    private CanItemPopupList mItemLdms;
    private CanItemSwitchList mItemQylxt;
    private CanItemSwitchList mItemRvsKeep;
    private CanItemTextBtnList mItemTpms;
    private CanItemTextBtnList mItemTybd;
    private CanItemSwitchList mItemXxtsy;
    private CanItemSwitchList mItemYsgykg;
    private CanItemPopupList mItemZxd;
    private CanScrollList mManager;
    private CanDataInfo.FordSet mSetData = new CanDataInfo.FordSet();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.FordGetSetup(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemQylxt.SetCheck(this.mSetData.Qylxt);
            this.mItemXxtsy.SetCheck(this.mSetData.Xytsy);
            this.mItemJbtsy.SetCheck(this.mSetData.Jgtsy);
            this.mItemYsgykg.SetCheck(this.mSetData.Ysgyq);
            this.mItemZxd.SetSel(this.mSetData.Zxd);
            this.mItemLcdw.SetSel(this.mSetData.RangeDW);
            this.mItemLdms.SetSel(this.mSetData.LightMode);
            if (this.mSetData.TpmsSet == 1) {
                Toast.makeText(this, R.string.can_tpms_reset, 0).show();
            }
            this.mItemRvsKeep.SetCheck(this.mSetData.RvsKeep);
            this.mItemHjd.SetCheck(this.mSetData.Hjdsd);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (this.mSetData.UpdateOnce == 0) {
            CanJni.FordQuery(36, 0);
        }
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
        String[] strZxdArr = getResources().getStringArray(R.array.can_fist_zxd);
        this.mItemQylxt = AddCheckItem(R.string.can_traction_control_sys, 1);
        this.mItemXxtsy = AddCheckItem(R.string.can_message_tone, 2);
        this.mItemJbtsy = AddCheckItem(R.string.can_alert_tone, 3);
        this.mItemYsgykg = AddCheckItem(R.string.can_rain_sensor, 7);
        this.mItemLcdw = AddPopupItem(R.string.can_lcdw, mLcdwArr, 5);
        this.mItemZxd = AddPopupItem(R.string.can_zxdsszs, strZxdArr, 4);
        this.mItemLdms = AddPopupItem(R.string.can_ldms, mLdmsArr, 6);
        this.mItemRvsKeep = AddCheckItem(R.string.can_rvs_keep, 9);
        this.mItemTpms = AddTextBtn(R.string.can_tpms_set, 8);
        this.mItemTpms.ShowArrow(false);
        this.mItemHjd = AddCheckItem(R.string.can_environment_light, 10);
        CanJni.FordGetAdt(this.mAdtData);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 10; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.Qylxt;
                break;
            case 2:
                ret = this.mAdtData.Xytsy;
                break;
            case 3:
                ret = this.mAdtData.Jgtsy;
                break;
            case 4:
                ret = this.mAdtData.Zxd;
                break;
            case 5:
                ret = this.mAdtData.RangeDW;
                break;
            case 6:
                ret = this.mAdtData.LightMode;
                break;
            case 7:
                ret = this.mAdtData.Ysgyq;
                break;
            case 8:
                ret = this.mAdtData.TpmsSet;
                break;
            case 9:
                ret = this.mAdtData.RvsKeep;
                break;
            case 10:
                ret = this.mAdtData.Hjdsd;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemQylxt.ShowGone(show);
                return;
            case 2:
                this.mItemXxtsy.ShowGone(show);
                return;
            case 3:
                this.mItemJbtsy.ShowGone(show);
                return;
            case 4:
                this.mItemZxd.ShowGone(show);
                return;
            case 5:
                this.mItemLcdw.ShowGone(show);
                return;
            case 6:
                this.mItemLdms.ShowGone(show);
                return;
            case 7:
                this.mItemYsgykg.ShowGone(show);
                return;
            case 8:
                this.mItemTpms.ShowGone(show);
                return;
            case 9:
                this.mItemRvsKeep.ShowGone(show);
                return;
            case 10:
                this.mItemHjd.ShowGone(show);
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

    /* access modifiers changed from: protected */
    public int GetSWVal(int cur, int on, int off) {
        return cur == 0 ? on : off;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.FordCarSet(163, GetSWVal(this.mSetData.Qylxt, 1, 2));
                return;
            case 2:
                CanJni.FordCarSet(163, GetSWVal(this.mSetData.Xytsy, 6, 5));
                return;
            case 3:
                CanJni.FordCarSet(163, GetSWVal(this.mSetData.Jgtsy, 8, 7));
                return;
            case 7:
                CanJni.FordCarSet(165, GetSWVal(this.mSetData.Ysgyq, 1, 0));
                return;
            case 8:
                new CanItemMsgBox(8, this, R.string.can_tpms_notice, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        if (CanJni.GetSubType() == 7) {
                            CanJni.FordCarSet(163, 18);
                        } else {
                            CanJni.FordCarSet(169, 1);
                        }
                    }
                }, (CanItemMsgBox.onMsgBoxClick2) null);
                return;
            case 9:
                CanJni.FordCarSet(174, GetSWVal(this.mSetData.RvsKeep, 1, 0));
                return;
            case 10:
                CanJni.FordCarSet(168, GetSWVal(this.mSetData.Hjdsd, 1, 0));
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
                CanJni.FordCarSet(163, item + 3);
                return;
            case 5:
                CanJni.FordCarSet(163, item + 14);
                return;
            case 6:
                CanJni.FordCarSet(163, item + 16);
                return;
            default:
                return;
        }
    }
}
