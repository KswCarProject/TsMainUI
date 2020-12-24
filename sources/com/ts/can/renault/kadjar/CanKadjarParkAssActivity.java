package com.ts.can.renault.kadjar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemFsSetList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollBaseActivity;

public class CanKadjarParkAssActivity extends CanScrollBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange, CanItemFsSetList.onFsSetClick {
    protected static final int ITEM_CDPLJG = 8;
    protected static final int ITEM_CDPLJGYL = 9;
    protected static final int ITEM_CDPLLMD = 10;
    protected static final int ITEM_CFTCFZ = 3;
    protected static final int ITEM_HFTCFZ = 5;
    protected static final int ITEM_LDJY = 6;
    protected static final int ITEM_MAX = 13;
    protected static final int ITEM_MIN = 1;
    protected static final int ITEM_MQTX = 2;
    protected static final int ITEM_PLJG = 12;
    protected static final int ITEM_QFTCFZ = 4;
    protected static final int ITEM_RESET = 13;
    protected static final int ITEM_ZDYGD = 11;
    protected static final int ITEM_ZDZD = 7;
    protected static final int ITEM_ZNBCFZ = 1;
    public static final String TAG = "CanKadjarParkAssActivity";
    private static int[] mLmdArrays = {R.string.can_cdjg, R.string.can_cdzj, R.string.can_cdjd};
    protected static final int[] mZnbcfzArr = {R.string.can_invalid, R.string.can_cfwtc, R.string.can_zjtc, R.string.can_xxtc};
    protected CanItemSwitchList mItemCdpljg;
    protected CanItemProgressList mItemCdpljgyl;
    protected CanItemPopupList mItemCdpllmd;
    protected CanItemSwitchList mItemCftcfz;
    protected CanItemSwitchList mItemHftcfz;
    protected CanItemSwitchList mItemLdjy;
    protected CanItemSwitchList mItemMqtx;
    protected CanItemSwitchList mItemPljg;
    protected CanItemSwitchList mItemQftcfz;
    protected CanItemFsSetList mItemReset;
    protected CanItemSwitchList mItemZdygd;
    protected CanItemSwitchList mItemZdzd;
    protected CanItemPopupList mItemZnbcfz;
    protected CanDataInfo.KadjarSet mSetData = new CanDataInfo.KadjarSet();
    protected boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemZnbcfz = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_znbcfzxt, mZnbcfzArr, 1);
        this.mItemMqtx = AddSwitch((View.OnClickListener) this, R.string.can_jsfz_mqtx, 2);
        this.mItemCftcfz = AddSwitch((View.OnClickListener) this, R.string.can_cftcfz, 3);
        this.mItemQftcfz = AddSwitch((View.OnClickListener) this, R.string.can_qftcfz, 4);
        this.mItemHftcfz = AddSwitch((View.OnClickListener) this, R.string.can_hftcfz, 5);
        this.mItemLdjy = AddSwitch((View.OnClickListener) this, R.string.can_radar_mute, 6);
        this.mItemZdzd = AddSwitch((View.OnClickListener) this, R.string.can_zdzczd, 7);
        this.mItemCdpljg = AddSwitch((View.OnClickListener) this, R.string.can_jp_cdpljg, 8);
        this.mItemCdpljgyl = AddProgress(this, R.string.can_cdpljgyl, 9);
        this.mItemCdpllmd = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_cdpllmd, mLmdArrays, 10);
        this.mItemZdygd = AddSwitch((View.OnClickListener) this, R.string.can_jp_zdfxygd, 11);
        this.mItemPljg = AddSwitch((View.OnClickListener) this, R.string.can_pljg, 12);
        this.mItemCdpljgyl.SetMinMax(1, 5);
        this.mItemCdpljgyl.SetStep(1);
        this.mItemReset = AddFsSetItem(this, R.string.can_setup_reset, 13);
        this.mItemReset.SetMsgText(R.string.can_sure_reset);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.KadjarGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemZnbcfz.SetSel(this.mSetData.Znbcfz);
            this.mItemMqtx.SetCheck(this.mSetData.Mqtx);
            this.mItemCftcfz.SetCheck(this.mSetData.Cftcfz);
            this.mItemQftcfz.SetCheck(this.mSetData.Qftcfz);
            this.mItemHftcfz.SetCheck(this.mSetData.Hftcfz);
            this.mItemLdjy.SetCheck(this.mSetData.Ldjy);
            this.mItemZdzd.SetCheck(this.mSetData.Zdzd);
            this.mItemCdpljg.SetCheck(this.mSetData.Cdpljg);
            this.mItemCdpljgyl.SetCurVal(this.mSetData.Cdpljgyl);
            this.mItemCdpllmd.SetSel(this.mSetData.Cdpllmd - 1);
            this.mItemZdygd.SetCheck(this.mSetData.Zdygd);
            this.mItemPljg.SetCheck(this.mSetData.Pljg);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.KadjarCarQuery(113, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
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
    public CanItemFsSetList AddFsSetItem(CanItemFsSetList.onFsSetClick l, int text, int id) {
        CanItemFsSetList item = new CanItemFsSetList(this, text);
        item.SetIdClickListener(id, l);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 13; i++) {
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
                if (CanJni.GetSubType() == 3) {
                    ret = 1;
                    break;
                }
                break;
            case 7:
                if (CanJni.GetSubType() == 3) {
                    ret = 1;
                    break;
                }
                break;
            case 8:
                if (CanJni.GetSubType() == 3) {
                    ret = 1;
                    break;
                }
                break;
            case 9:
                if (CanJni.GetSubType() == 3) {
                    ret = 1;
                    break;
                }
                break;
            case 10:
                if (CanJni.GetSubType() == 3) {
                    ret = 1;
                    break;
                }
                break;
            case 11:
                if (CanJni.GetSubType() == 3) {
                    ret = 1;
                    break;
                }
                break;
            case 12:
                if (CanJni.GetSubType() == 3) {
                    ret = 1;
                    break;
                }
                break;
            case 13:
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
                this.mItemZnbcfz.ShowGone(show);
                return;
            case 2:
                this.mItemMqtx.ShowGone(show);
                return;
            case 3:
                this.mItemCftcfz.ShowGone(show);
                return;
            case 4:
                this.mItemQftcfz.ShowGone(show);
                return;
            case 5:
                this.mItemHftcfz.ShowGone(show);
                return;
            case 6:
                this.mItemLdjy.ShowGone(show);
                return;
            case 7:
                this.mItemZdzd.ShowGone(show);
                return;
            case 8:
                this.mItemCdpljg.ShowGone(show);
                return;
            case 9:
                this.mItemCdpljgyl.ShowGone(show);
                return;
            case 10:
                this.mItemCdpllmd.ShowGone(show);
                return;
            case 11:
                this.mItemZdygd.ShowGone(show);
                return;
            case 12:
                this.mItemPljg.ShowGone(show);
                return;
            case 13:
                this.mItemReset.ShowGone(show);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.KadjarCarSet(17, Neg(this.mSetData.Mqtx));
                return;
            case 3:
                CanJni.KadjarCarSet(18, Neg(this.mSetData.Cftcfz));
                return;
            case 4:
                CanJni.KadjarCarSet(1, Neg(this.mSetData.Qftcfz));
                return;
            case 5:
                CanJni.KadjarCarSet(2, Neg(this.mSetData.Hftcfz));
                return;
            case 6:
                CanJni.KadjarCarSet(32, Neg(this.mSetData.Ldjy));
                return;
            case 7:
                CanJni.KadjarCarSet(29, Neg(this.mSetData.Zdzd));
                return;
            case 8:
                CanJni.KadjarCarSet(26, Neg(this.mSetData.Cdpljg));
                return;
            case 11:
                CanJni.KadjarCarSet(30, Neg(this.mSetData.Zdygd));
                return;
            case 12:
                CanJni.KadjarCarSet(31, Neg(this.mSetData.Pljg));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.KadjarCarSet(19, item);
                return;
            case 10:
                CanJni.KadjarCarSet(28, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 9:
                CanJni.KadjarCarSet(27, pos);
                return;
            default:
                return;
        }
    }

    public void onFsItem(int id, boolean sure) {
        if (sure) {
            CanJni.KadjarCarSet(128, 4);
        }
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
    }
}
