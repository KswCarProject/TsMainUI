package com.ts.can.gac.trumpchi;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanGqcqCarSetActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_AC = 1;
    public static final int ITEM_ATTACH = 3;
    public static final int ITEM_CHAIR = 2;
    public static final int ITEM_DRV_ASS = 4;
    public static final int ITEM_LIGHT = 5;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_NLXX = 6;
    public static final int ITEM_OTHER = 7;
    public static final String TAG = "CanGqcqCarSetActivity";
    private CanItemTextBtnList mItemAC;
    private CanItemTextBtnList mItemAttach;
    private CanItemTextBtnList mItemChair;
    private CanItemTextBtnList mItemDrvAss;
    private CanItemTextBtnList mItemLight;
    private CanItemTextBtnList mItemNLXX;
    private CanItemTextBtnList mItemOther;
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
        this.mItemAC = AddTextBtn(R.string.can_ac_set, 1);
        this.mItemChair = AddTextBtn(R.string.can_chair_set, 2);
        this.mItemDrvAss = AddTextBtn(R.string.can_jsfz, 4);
        this.mItemAttach = AddTextBtn(R.string.can_attachment, 3);
        this.mItemLight = AddTextBtn(R.string.can_light_set, 5);
        this.mItemNLXX = AddTextBtn(R.string.can_jac_nlxx, 6);
        this.mItemOther = AddTextBtn(R.string.can_other_set, 7);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 7; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 6:
                if (CanJni.GetSubType() == 10 || CanJni.GetSubType() == 12) {
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
            case 6:
                this.mItemNLXX.ShowGone(show);
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

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(true);
        return btn;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanGqcqSetACActivity.class);
                return;
            case 2:
                enterSubWin(CanGqcqSetChairActivity.class);
                return;
            case 3:
                enterSubWin(CanGqcqSetAttachActivity.class);
                return;
            case 4:
                enterSubWin(CanGqcqSetDrvAssActivity.class);
                return;
            case 5:
                enterSubWin(CanGqcqSetLightActivity.class);
                return;
            case 6:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
                return;
            case 7:
                enterSubWin(CanGqcqSetOtherActivity.class);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
