package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanGolfSetESCSystemActivity extends CanGolfBaseActivity implements CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_ESC_SYSTEM = 0;
    public static final String TAG = "CanGolfSetESCSystemActivity";
    private CanDataInfo.GolfAdtEscSystem mAdtEsc = new CanDataInfo.GolfAdtEscSystem();
    private CanDataInfo.GolfData mEscData = new CanDataInfo.GolfData();
    protected CanItemPopupList mItemEscSystem;
    private CanScrollList mManager;
    private int[] mMenu;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        if (!i2b(this.mAdtEsc.UpdateOnce)) {
            CanJni.GolfGetAdtEsc(this.mAdtEsc);
            if (i2b(this.mAdtEsc.UpdateOnce)) {
                this.mManager.RemoveAllViews();
                if (i2b(this.mAdtEsc.Esc)) {
                    this.mManager.AddView(this.mItemEscSystem.GetView());
                }
            } else {
                return;
            }
        }
        CanJni.GolfGetEscSys(this.mEscData);
        if (!i2b(this.mEscData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mEscData.Update)) {
            this.mEscData.Update = 0;
            this.mItemEscSystem.SetSel(this.mEscData.Data);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (!i2b(this.mAdtEsc.UpdateOnce)) {
            CanJni.GolfQuery(65, 16);
            Sleep(5);
        }
        if (!i2b(this.mEscData.UpdateOnce)) {
            CanJni.GolfQuery(64, 16);
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

    private void InitUI() {
        if (CanJni.GetSubType() == 0) {
            this.mMenu = new int[]{R.string.can_a_s_r_off, R.string.can_active_already};
        } else {
            this.mMenu = new int[]{R.string.can_a_s_r_off, R.string.can_active_already, R.string.can_esc_sports_mode};
        }
        this.mItemEscSystem = new CanItemPopupList((Context) this, R.string.can_esc_system, this.mMenu, (CanItemPopupList.onPopItemClick) this);
        this.mItemEscSystem.SetId(0);
        this.mManager = new CanScrollList(this);
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void AddItem(int item) {
    }

    public void onItem(int Id, int item) {
        if (Id == 0) {
            CanJni.GolfSendCmd(16, item);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
