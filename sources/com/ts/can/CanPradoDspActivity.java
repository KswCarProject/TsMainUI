package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanPradoDspActivity extends CanToyotaBaseActivity implements View.OnClickListener, UserCallBack, CanItemProgressList.onPosChange {
    public static final int ITEM_AROUND = 2;
    public static final int ITEM_ASL = 1;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_VOL = 3;
    public static final String TAG = "CanPradoDspActivity";
    private CanItemCheckList mItemASL;
    private CanItemCheckList mItemAround;
    private CanItemProgressList mItemVol;
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
        GetAmpData();
        if (!this.mbLayout) {
            LayoutUI();
            check = false;
            this.mbLayout = true;
        }
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            this.mItemASL.SetCheck(this.mAmpData.fgASL);
            this.mItemAround.SetCheck(this.mAmpData.fgHhyx);
            this.mItemVol.SetCurVal(this.mAmpData.Vol);
            this.mItemVol.SetValText(String.valueOf(this.mAmpData.Vol));
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
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        for (int i = 1; i <= 3; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        for (int i = 1; i <= 3; i++) {
            AddItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 1;
        switch (item) {
            case 3:
                if (CanJni.GetCanFsTp() == 7) {
                    ret = 0;
                    break;
                }
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void InitItem(int item) {
        switch (item) {
            case 1:
                this.mItemASL = AddCheckItem(R.string.can_a_s_l, item);
                return;
            case 2:
                this.mItemAround = AddCheckItem(R.string.can_around, item);
                return;
            case 3:
                this.mItemVol = AddProgressItem(R.string.can_vol, 3);
                this.mItemVol.SetMinMax(0, 63);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void AddItem(int item) {
        if (IsHaveItem(item)) {
            switch (item) {
                case 1:
                    this.mManager.AddView(this.mItemASL.GetView());
                    return;
                case 2:
                    this.mManager.AddView(this.mItemAround.GetView());
                    return;
                case 3:
                    this.mManager.AddView(this.mItemVol.GetView());
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public CanItemCheckList AddCheckItem(int resId, int Id) {
        CanItemCheckList item = new CanItemCheckList(this, resId);
        item.SetIdClickListener(this, Id);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemProgressList AddProgressItem(int resId, int Id) {
        CanItemProgressList item = new CanItemProgressList((Context) this, resId);
        item.SetIdCallBack(Id, this);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                AmpSet(3, Neg(this.mAmpData.fgASL));
                return;
            case 2:
                AmpSet(9, Neg(this.mAmpData.fgHhyx));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 3:
                AmpSet(7, pos);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
