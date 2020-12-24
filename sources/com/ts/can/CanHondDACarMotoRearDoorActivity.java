package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanHondDACarMotoRearDoorActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick, View.OnClickListener, UserCallBack {
    public static final int ITEM_SYWSBDDDK = 2;
    public static final int ITEM_YKKQTJSD = 1;
    public static final int ITEM_ZDKQXLXG = 3;
    public static final String TAG = "CanHondDACarMotoRearDoorActivity";
    private static final int[] mSywsbdddk = {R.string.can_dd_and_shoud, R.string.can_shoud};
    private static final int[] mSywsbdddkXp = {R.string.can_shoud, R.string.can_dd_and_shoud};
    private static final int[] mYkkqtjsd = {R.string.can_renhsh, R.string.can_unlock};
    protected CanItemPopupList mItemStwsbdddk;
    protected CanItemPopupList mItemYkkqtjsd;
    protected CanItemSwitchList mItemZdkqxlxg;
    protected CanScrollList mManager;
    protected CanDataInfo.HondaSetData mSetData = new CanDataInfo.HondaSetData();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemYkkqtjsd = AddPopupItem(R.string.can_ykkqtjsd, mYkkqtjsd, 1);
        if (CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7) {
            this.mItemStwsbdddk = AddPopupItem(R.string.can_sywsbdddk, mSywsbdddk, 2);
        } else {
            this.mItemStwsbdddk = AddPopupItem(R.string.can_sywsbdddk, mSywsbdddkXp, 2);
        }
        this.mItemZdkqxlxg = AddCheckItem(R.string.can_zdkqxlxg, 3);
        if (CanJni.GetSubType() != 11) {
            this.mItemZdkqxlxg.ShowGone(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.HondaDACarSet(37, item);
                return;
            case 2:
                CanJni.HondaDACarSet(38, item);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                CanJni.HondaDACarSet(51, Neg(this.mSetData.Zdkqxlxg));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (i2b(this.mSetData.XtszUpdateOnce) && (!check || i2b(this.mSetData.XtszUpdate))) {
            this.mSetData.XtszUpdate = 0;
            this.mItemYkkqtjsd.SetSel(this.mSetData.Ykkqtjsd);
            this.mItemStwsbdddk.SetSel(this.mSetData.Sywsbdddk);
        }
        if (!i2b(this.mSetData.DoorWindowUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.DoorWindowUpdate)) {
            this.mSetData.DoorWindowUpdate = 0;
            this.mItemZdkqxlxg.SetCheck(this.mSetData.Zdkqxlxg);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
