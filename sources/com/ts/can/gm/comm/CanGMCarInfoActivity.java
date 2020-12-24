package com.ts.can.gm.comm;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanGMCarInfoActivity extends CanGMBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_AC = 2;
    public static final int ITEM_CAR_TYPE = 0;
    public static final int ITEM_CDS = 5;
    public static final int ITEM_CONV = 4;
    public static final int ITEM_HYBRID = 8;
    public static final int ITEM_LANGUAGE = 7;
    public static final int ITEM_LIGHT = 3;
    public static final int ITEM_LOCK = 1;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 0;
    public static final int ITEM_OTHER = 6;
    public static final String TAG = "CanGMCarInfoActivity";
    private CanDataInfo.GM_AdtAll mAdtAllData = new CanDataInfo.GM_AdtAll();
    private CanDataInfo.GM_Hybrid mAdtHybrid = new CanDataInfo.GM_Hybrid();
    private CanItemIcoList mItemAc;
    private CanItemIcoList mItemCarType;
    private CanItemIcoList mItemCds;
    private CanItemIcoList mItemConv;
    private CanItemIcoList mItemHybrid;
    private CanItemIcoList mItemLanguage;
    private CanItemIcoList mItemLight;
    private CanItemIcoList mItemLock;
    private CanItemIcoList mItemOther;
    private CanScrollList mManager;
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
        this.mItemCarType.ShowGone(true);
        this.mItemLanguage.ShowGone(true);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GMGetAdtAll(this.mAdtAllData);
        if (i2b(this.mAdtAllData.UpdateOnce) && (!check || i2b(this.mAdtAllData.Update))) {
            this.mAdtAllData.Update = 0;
            LayoutUI();
        }
        if (CanJni.GetSubType() == 11) {
            CanJni.GMGetHyBridInfo(this.mAdtHybrid);
            if (!i2b(this.mAdtHybrid.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mAdtHybrid.Update)) {
                this.mAdtHybrid.Update = 0;
                if (this.mAdtHybrid.Vaild > 0) {
                    this.mItemHybrid.ShowGone(true);
                } else {
                    this.mItemHybrid.ShowGone(false);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (!i2b(this.mAdtAllData.UpdateOnce)) {
            CanJni.GMQuery(26);
        }
        if (CanJni.GetSubType() == 11) {
            CanJni.GMQuery(71);
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
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCarType = AddIcoItem(R.drawable.can_icon_esc, R.string.can_car_type_select, 0);
        this.mItemLock = AddIcoItem(R.drawable.can_icon_lock, R.string.can_car_lock_set, 1);
        this.mItemAc = AddIcoItem(R.drawable.can_icon_ac, R.string.can_ac_set, 2);
        this.mItemLight = AddIcoItem(R.drawable.can_icon_light, R.string.can_c4_l_light, 3);
        this.mItemConv = AddIcoItem(R.drawable.can_icon_service, R.string.can_sshbl, 4);
        this.mItemCds = AddIcoItem(R.drawable.can_icon_cds, R.string.can_cds, 5);
        this.mItemOther = AddIcoItem(R.drawable.can_icon_setup, R.string.can_other_set, 6);
        this.mItemLanguage = AddIcoItem(R.drawable.can_icon_tyres, R.string.can_lang_set, 7);
        this.mItemHybrid = AddIcoItem(R.drawable.can_icon_hybrid, R.string.can_hybrid_image, 8);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 0; i <= 8; i++) {
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
                ret = this.mAdtAllData.AutoLock + this.mAdtAllData.RmtLock;
                break;
            case 2:
                ret = this.mAdtAllData.AC;
                break;
            case 3:
                ret = this.mAdtAllData.Light;
                break;
            case 4:
                ret = this.mAdtAllData.Conv;
                break;
            case 5:
                ret = this.mAdtAllData.Pzjc;
                break;
            case 6:
                if (CanJni.GetSubType() != 9) {
                    ret = 1;
                    break;
                }
                break;
            case 7:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 0:
                this.mItemCarType.ShowGone(show);
                return;
            case 1:
                this.mItemLock.ShowGone(show);
                return;
            case 2:
                this.mItemAc.ShowGone(show);
                return;
            case 3:
                this.mItemLight.ShowGone(show);
                return;
            case 4:
                this.mItemConv.ShowGone(show);
                return;
            case 5:
                this.mItemCds.ShowGone(show);
                return;
            case 6:
                this.mItemOther.ShowGone(show);
                return;
            case 7:
                this.mItemLanguage.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemCheckList AddCheckItem(int resId, int Id) {
        CanItemCheckList item = new CanItemCheckList(this, resId);
        item.SetIdClickListener(this, Id);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanGMCarTypeActivity.class);
                return;
            case 1:
                enterSubWin(CanGMSetLockActivity.class);
                return;
            case 2:
                enterSubWin(CanGMSetACActivity.class);
                return;
            case 3:
                enterSubWin(CanGMSetLightActivity.class);
                return;
            case 4:
                enterSubWin(CanGMSetConvActivity.class);
                return;
            case 5:
                enterSubWin(CanGMSetCDSActivity.class);
                return;
            case 6:
                enterSubWin(CanGMSetOtherActivity.class);
                return;
            case 7:
                enterSubWin(CanGMSetLanguageActivity.class);
                return;
            case 8:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
