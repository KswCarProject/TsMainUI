package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;

public class CanHondDACarCameraSetActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_CAMERA_STA = 1;
    public static final int ITEM_RIGHTCAMERA_STA = 2;
    public static final String TAG = "CanHondDACarCameraSetActivity";
    protected CanItemSwitchList mItemCameraSta;
    protected CanItemSwitchList mItemRightCameraSta;
    protected CanScrollList mManager;
    protected int nCamerSta = 255;
    protected int nRightCamerSta = 255;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemCameraSta = AddCheckItem(R.string.can_sxtqhzt, 1);
        this.mItemRightCameraSta = AddCheckItem(R.string.can_has_right_camera, 2);
        if (CanJni.GetSubType() == 11 || CanJni.GetSubType() == 12) {
            this.mItemCameraSta.ShowGone(false);
        } else {
            this.mItemRightCameraSta.ShowGone(false);
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

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                if (FtSet.Getlgb5() > 0) {
                    FtSet.Setlgb5(0);
                    return;
                } else {
                    FtSet.Setlgb5(1);
                    return;
                }
            case 2:
                FtSet.Setlgb4(Neg(FtSet.Getlgb4()));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        if (this.nCamerSta != FtSet.Getlgb5()) {
            this.nCamerSta = FtSet.Getlgb5();
            this.mItemCameraSta.SetCheck(this.nCamerSta);
        }
        if (this.nRightCamerSta != FtSet.Getlgb4()) {
            this.nRightCamerSta = FtSet.Getlgb4();
            this.mItemRightCameraSta.SetCheck(this.nRightCamerSta);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
