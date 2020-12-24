package com.ts.can.saic.rw950;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanScrollList;

public class CanRW950CarInfoActivity extends CanRW950BaseActivity implements View.OnClickListener, UserCallBack, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_AC = 6;
    public static final int ITEM_DOOR_LOCK = 4;
    public static final int ITEM_FS_SET = 7;
    public static final int ITEM_LIGHT = 3;
    public static final int ITEM_RMT_LOCK = 5;
    public static final String TAG = "CanRW950CarInfoActivity";
    private CanItemIcoList mItemAc;
    private CanItemIcoList mItemDoorLock;
    private CanItemIcoList mItemFsSet;
    private CanItemIcoList mItemLight;
    private CanItemIcoList mItemRmtLock;
    private CanScrollList mManager;

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
        Log.d(TAG, "-----onResume-----");
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
        return item;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemLight = AddIcoItem(R.drawable.can_icon_light, R.string.can_c4_l_light, 3);
        this.mItemDoorLock = AddIcoItem(R.drawable.can_icon_lock3, R.string.can_lock_center, 4);
        this.mItemRmtLock = AddIcoItem(R.drawable.can_icon_lock, R.string.can_lock_remote, 5);
        this.mItemAc = AddIcoItem(R.drawable.can_icon_ac, R.string.can_ac_set, 6);
        this.mItemFsSet = AddIcoItem(R.drawable.can_icon_factory, R.string.can_factory_set, 7);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                enterSubWin(CanRW950LightSetActivity.class);
                return;
            case 4:
                enterSubWin(CanRW950DoorLockActivity.class);
                return;
            case 5:
                enterSubWin(CanRW950RmtLockActivity.class);
                return;
            case 6:
                enterSubWin(CanRW950ACSetActivity.class);
                return;
            case 7:
                new CanItemMsgBox(7, this, R.string.can_sure_setup, this);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onOK(int param) {
        if (param == 7) {
            CarSet(128, 1);
        }
    }
}
