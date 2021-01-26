package com.ts.can.saic.baojun;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanBaseCarInfoActivity;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanBaojunCarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_CARMERA = 1;
    public static final int ITEM_HSJZDZD = 3;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SETUP = 5;
    public static final int ITEM_TYJC = 4;
    public static final int ITEM_TYPE = 2;
    public static final String TAG = "CanNissanCarInfoActivity";
    private static final String[] mTypeArr = {"730", "730(2015,2017),560,510", "530", "RS-3(2020) "};
    private CanItemSwitchList mItemCamera;
    private CanItemCarType mItemCarType;
    private CanItemSwitchList mItemHsjzdzd;
    private CanItemTextBtnList mItemSetup;
    private CanItemTextBtnList mItemTyjc;
    private CanScrollList mManager;
    private CanDataInfo.Baojun_Info mSetData = new CanDataInfo.Baojun_Info();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.BaojunGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemHsjzdzd.SetCheck(SwSet(this.mSetData.Hsjzdzd));
        }
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
        Log.d("CanNissanCarInfoActivity", "subtype = " + CanJni.GetSubType());
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, mTypeArr, 2, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
        this.mItemHsjzdzd = AddCheckItem(R.string.can_zdhsjzd, 3);
        this.mItemTyjc = AddTextBtn(R.string.can_tpms_reset_notice, 4);
        this.mItemSetup = new CanItemTextBtnList((Context) this, R.string.can_vehi_setup);
        this.mItemSetup.SetIdClickListener(this, 5);
        this.mManager.AddView(this.mItemSetup.GetView());
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 5; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 3:
                if (CanJni.GetSubType() == 2) {
                    ret = 1;
                    break;
                }
                break;
            case 4:
                ret = 1;
                break;
            case 5:
                if (CanJni.GetSubType() == 3) {
                    ret = 1;
                    break;
                }
                break;
        }
        return i2b(ret);
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
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 3:
                this.mItemHsjzdzd.ShowGone(show);
                return;
            case 4:
                this.mItemTyjc.ShowGone(show);
                return;
            case 5:
                this.mItemSetup.ShowGone(show);
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
        return item;
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        return btn;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                FtSet.SetCanSubT(Neg(FtSet.GetCanSubT()));
                ResetData(false);
                return;
            case 3:
                CanJni.BaojunCarSet(1, NegSwSet(this.mSetData.Hsjzdzd));
                return;
            case 4:
                new CanItemMsgBox(4, this, R.string.can_cmp_reset_notice, this);
                return;
            case 5:
                enterSubWin(CanBaseCarInfoActivity.class);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        if (id == 2) {
            Log.d("CanNissanCarInfoActivity", "Select = " + item);
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }

    public void onOK(int param) {
        switch (param) {
            case 4:
                CanJni.BaojunCarSet(2, 1);
                return;
            default:
                return;
        }
    }
}
