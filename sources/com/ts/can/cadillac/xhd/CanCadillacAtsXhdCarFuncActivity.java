package com.ts.can.cadillac.xhd;

import android.content.Context;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanCadillacAtsXhdCarFuncActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    private static final int ITEM_CAMERA = 0;
    private static final int ITEM_R_CAMERA = 1;
    private static int m_Camerb = 0;
    private static int m_RCamerb = 0;
    private String[] mCameraArr = {"加装(CVBS)模式", "原车模式"};
    private CanItemPopupList mItemCamera;
    private CanItemSwitchList mItemRCamera;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCamera = AddPopupItem(R.string.can_camera_360, this.mCameraArr, 0);
        this.mItemRCamera = AddCheckItem(R.string.can_tigger7_start_avm, 1);
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        if (m_Camerb != FtSet.Getlgb1() || m_RCamerb != FtSet.Getlgb2() || !check) {
            m_Camerb = FtSet.Getlgb1();
            m_RCamerb = FtSet.Getlgb2();
            this.mItemCamera.SetSel(FtSet.Getlgb1());
            this.mItemRCamera.SetCheck(FtSet.Getlgb2());
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                if (FtSet.Getlgb2() > 0) {
                    FtSet.Setlgb2(0);
                    Mcu.SendXKey(40);
                    return;
                }
                FtSet.Setlgb2(1);
                Mcu.SendXKey(41);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 0) {
                    FtSet.Setlgb1(0);
                    Mcu.SendXKey(34);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb1(1);
                    Mcu.SendXKey(33);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
