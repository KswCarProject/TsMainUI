package com.ts.can.zh.h3;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanZhH3CarInfoActivity extends CanBaseActivity implements UserCallBack, CanItemPopupList.onPopItemClick, View.OnClickListener, CanItemProgressList.onPosChange {
    private static final int ITEM_CMJSMS = 9;
    private static final int ITEM_CSSS = 8;
    private static final int ITEM_DMJS = 3;
    private static final int ITEM_HSJZDZD = 6;
    private static final int ITEM_JSSS = 5;
    private static final int ITEM_LANGUAGE = 7;
    private static final int ITEM_LSSS = 4;
    private static final int ITEM_MAX = 13;
    private static final int ITEM_MIN = 0;
    private static final int ITEM_RJXCD = 11;
    private static final int ITEM_SSJFK = 10;
    private static final int ITEM_ZCBJD = 12;
    private static final int ITEM_ZDCS = 2;
    private static final int ITEM_ZDJS = 1;
    private static final int ITEM_ZDLS = 0;
    private static final int ITEM_ZHYBLD = 13;
    private static final int[] mCmjsmsArrs = {R.string.can_cch9_gatingsettings_key2, R.string.can_cch9_gatingsettings_key1};
    private static final int[] mLanguageArrays = {R.string.can_lang_cn, R.string.can_lang_en};
    private static final int[] mSsjsfkfsArrs = {R.string.can_mzd_cx4_mode_off, R.string.can_only_light, R.string.can_only_lb, R.string.can_dghlb};
    private CanDataInfo.ZhCarInfo mCarInfo = new CanDataInfo.ZhCarInfo();
    private CanItemSwitchList mDmjsItem;
    private CanItemSwitchList mHskzdzdItem;
    private CanItemPopupList mItemCmjsms;
    private CanItemSwitchList mItemCsss;
    private CanItemSwitchList mItemRjxcd;
    private CanItemPopupList mItemSsjsfk;
    private CanItemProgressList mItemZcbjd;
    private CanItemProgressList mItemZhybld;
    private CanItemSwitchList mJsssItem;
    private CanItemPopupList mLanguageItem;
    private CanItemSwitchList mLsssItem;
    private CanScrollList mManager;
    private CanItemSwitchList mZdcsItem;
    private CanItemSwitchList mZdjsItem;
    private CanItemSwitchList mZdlsItem;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        System.out.println("onCreate");
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mZdlsItem = AddCheckItem(R.string.can_tigger7_auto_lock, 0);
        this.mZdjsItem = AddCheckItem(R.string.can_zdjs, 1);
        this.mZdcsItem = AddCheckItem(R.string.can_auto_lock_key, 2);
        this.mDmjsItem = AddCheckItem(R.string.can_lock_dmjs, 3);
        this.mLsssItem = AddCheckItem(R.string.can_lock_lsss, 4);
        this.mJsssItem = AddCheckItem(R.string.can_lock_jsss, 5);
        this.mHskzdzdItem = AddCheckItem(R.string.can_zdhsjzd, 6);
        this.mLanguageItem = this.mManager.addItemPopupList(R.string.can_lang_set, mLanguageArrays, 7, (CanItemPopupList.onPopItemClick) this);
        this.mItemCsss = AddCheckItem(R.string.can_df_jyx5_csss, 8);
        this.mItemCmjsms = this.mManager.addItemPopupList(R.string.can_unlock_type, mCmjsmsArrs, 9, (CanItemPopupList.onPopItemClick) this);
        this.mItemSsjsfk = this.mManager.addItemPopupList(R.string.can_lock_feedback, mSsjsfkfsArrs, 10, (CanItemPopupList.onPopItemClick) this);
        this.mItemRjxcd = AddCheckItem(R.string.can_tigger7_day_light, 11);
        this.mItemZcbjd = this.mManager.addItemProgressList(R.string.can_honda_bjstj, 12, (CanItemProgressList.onPosChange) this);
        this.mItemZcbjd.SetMinMax(0, 15);
        this.mItemZcbjd.SetStep(1);
        this.mItemZcbjd.SetUserValText();
        this.mItemZhybld = this.mManager.addItemProgressList(R.string.can_ybld, 13, (CanItemProgressList.onPosChange) this);
        this.mItemZhybld.SetMinMax(0, 8);
        this.mItemZhybld.SetStep(1);
        this.mItemZhybld.SetUserValText();
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
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.ZhH3Query(4);
        LayoutUI();
    }

    private void LayoutUI() {
        for (int i = 0; i <= 13; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 0:
                ret = 1;
                break;
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
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                if (CanJni.GetSubType() == 1) {
                    ret = 1;
                    break;
                }
                break;
        }
        return i2b(ret);
    }

    private void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 0:
                this.mZdlsItem.ShowGone(show);
                return;
            case 1:
                this.mZdjsItem.ShowGone(show);
                return;
            case 2:
                this.mZdcsItem.ShowGone(show);
                return;
            case 3:
                this.mDmjsItem.ShowGone(show);
                return;
            case 4:
                this.mLsssItem.ShowGone(show);
                return;
            case 5:
                this.mJsssItem.ShowGone(show);
                return;
            case 6:
                this.mHskzdzdItem.ShowGone(show);
                return;
            case 7:
                this.mLanguageItem.ShowGone(show);
                return;
            case 8:
                this.mItemCsss.ShowGone(show);
                return;
            case 9:
                this.mItemCmjsms.ShowGone(show);
                return;
            case 10:
                this.mItemSsjsfk.ShowGone(show);
                return;
            case 11:
                this.mItemRjxcd.ShowGone(show);
                return;
            case 12:
                this.mItemZcbjd.ShowGone(show);
                return;
            case 13:
                this.mItemZhybld.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private void ResetData(boolean check) {
        CanJni.ZhH3GetCarSet(this.mCarInfo);
        if (!i2b(this.mCarInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarInfo.Update)) {
            this.mCarInfo.Update = 0;
            this.mZdlsItem.SetCheck(this.mCarInfo.Zdls);
            this.mZdjsItem.SetCheck(this.mCarInfo.Zdjs);
            this.mZdcsItem.SetCheck(this.mCarInfo.Zdcs);
            this.mDmjsItem.SetCheck(this.mCarInfo.Dmjs);
            this.mLsssItem.SetCheck(this.mCarInfo.Lsss);
            this.mJsssItem.SetCheck(this.mCarInfo.Jsss);
            this.mHskzdzdItem.SetCheck(this.mCarInfo.Hsjzdzd);
            this.mLanguageItem.SetSel(this.mCarInfo.Lang);
            this.mItemCsss.SetCheck(this.mCarInfo.Csss);
            this.mItemCmjsms.SetSel(this.mCarInfo.Cmjsms);
            this.mItemSsjsfk.SetSel(this.mCarInfo.Ssjsfk);
            this.mItemRjxcd.SetCheck(this.mCarInfo.Rjxcd);
            this.mItemZcbjd.SetCurVal(this.mCarInfo.Zcbjd);
            this.mItemZcbjd.SetValText(" " + this.mCarInfo.Zcbjd);
            this.mItemZhybld.SetCurVal(this.mCarInfo.Zhybld);
            this.mItemZhybld.SetValText(" " + this.mCarInfo.Zhybld);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 7:
                CanJni.ZhH3CarSet(0, item);
                return;
            case 9:
                CanJni.ZhH3CarSet(13, item);
                return;
            case 10:
                CanJni.ZhH3CarSet(12, item);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.ZhH3CarSet(1, Neg(this.mCarInfo.Zdls));
                return;
            case 1:
                CanJni.ZhH3CarSet(2, Neg(this.mCarInfo.Zdjs));
                return;
            case 2:
                CanJni.ZhH3CarSet(3, Neg(this.mCarInfo.Zdcs));
                return;
            case 3:
                CanJni.ZhH3CarSet(4, Neg(this.mCarInfo.Dmjs));
                return;
            case 4:
                CanJni.ZhH3CarSet(5, Neg(this.mCarInfo.Lsss));
                return;
            case 5:
                CanJni.ZhH3CarSet(6, Neg(this.mCarInfo.Jsss));
                return;
            case 6:
                CanJni.ZhH3CarSet(7, Neg(this.mCarInfo.Hsjzdzd));
                return;
            case 8:
                CanJni.ZhH3CarSet(10, Neg(this.mCarInfo.Csss));
                return;
            case 11:
                CanJni.ZhH3CarSet(11, Neg(this.mCarInfo.Rjxcd));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 12:
                CanJni.ZhH3CarSet(14, pos);
                return;
            case 13:
                CanJni.ZhH3CarSet(15, pos);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
