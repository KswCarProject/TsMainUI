package com.ts.can.ford;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanFordCarInfoActivity extends CanFordBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_CAR_SET = 2;
    public static final int ITEM_CAR_SET2 = 6;
    public static final int ITEM_CAR_TYPE = 1;
    public static final int ITEM_ECO_MODE = 3;
    public static final int ITEM_LANG_SET = 5;
    private static final int ITEM_MAX = 6;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_WARN_INFO = 4;
    public static final String TAG = "CanFordCarInfoActivity";
    private CanDataInfo.FordAdt mAdtData = new CanDataInfo.FordAdt();
    private CanItemTextBtnList mItemCarSet;
    private CanItemTextBtnList mItemCarSet2;
    private CanItemTextBtnList mItemCarType;
    private CanItemTextBtnList mItemEcoMode;
    private CanItemTextBtnList mItemLangSet;
    private CanItemTextBtnList mItemWarnInfo;
    private CanScrollList mManager;
    private boolean mbLayout;
    private CanDataInfo.SyncVer ver = new CanDataInfo.SyncVer();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
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
        this.mItemCarType = AddTextBtn(R.string.can_car_type_select, 1);
        this.mItemCarSet = AddTextBtn(R.string.can_car_set, 2);
        this.mItemEcoMode = AddTextBtn(R.string.can_eco_mode, 3);
        this.mItemWarnInfo = AddTextBtn(R.string.can_warn_info, 4);
        this.mItemLangSet = AddTextBtn(R.string.can_car_lang, 5);
        this.mItemCarSet2 = AddTextBtn(R.string.can_yhsz, 6);
        this.mItemCarType.ShowGone(true);
        CanJni.FordGetAdt(this.mAdtData);
        CanJni.FordGetSyncVer(this.ver);
        LayoutUI();
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(false);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 6; i++) {
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
                ret = this.mAdtData.SetEnable;
                break;
            case 3:
                ret = this.mAdtData.EcoEnable;
                break;
            case 4:
                ret = this.mAdtData.WarnEnable;
                break;
            case 5:
                if (this.ver.Ver != 0) {
                    ret = 1;
                }
                if (CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
                break;
            case 6:
                if (CanJni.GetSubType() == 11) {
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
                this.mItemCarType.ShowGone(show);
                return;
            case 2:
                this.mItemCarSet.ShowGone(show);
                return;
            case 3:
                this.mItemEcoMode.ShowGone(show);
                return;
            case 4:
                this.mItemWarnInfo.ShowGone(show);
                return;
            case 5:
                this.mItemLangSet.ShowGone(show);
                return;
            case 6:
                this.mItemCarSet2.ShowGone(show);
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

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanFordCarTypeActivity.class);
                return;
            case 2:
                enterSubWin(CanFordCarSetActivity.class);
                return;
            case 3:
                enterSubWin(CanFordEcoModeActivity.class);
                return;
            case 4:
                enterSubWin(CanFordWarnInfoActivity.class);
                return;
            case 5:
                enterSubWin(CanFordLangSetActivity.class);
                return;
            case 6:
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
