package com.ts.can.hm.fml17;

import android.content.Context;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanHmFml17CarInfoActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_CAR_SET = 1;
    protected static final int ITEM_MAX = 1;
    protected static final int ITEM_MIN = 0;
    private static final int ITEM_TYPE = 0;
    private static String[] mTypeArr;
    protected CanItemTextBtnList mCarSet;
    protected CanScrollList mManager;

    public void onClick(View arg0) {
        switch (((Integer) arg0.getTag()).intValue()) {
            case 1:
                enterSubWin(CanHmFm17CarSetActivity.class);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        mTypeArr = getResources().getStringArray(R.array.can_fs_declare_82);
        this.mManager.addItemCarType(R.string.can_car_type_select, mTypeArr, 0, (CanItemPopupList.onPopItemClick) this).GetPopItem().SetSel(CanJni.GetSubType());
        this.mCarSet = AddTextBtn(R.string.can_car_set, 1);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 0; i <= 1; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int GetSubType = CanJni.GetSubType();
        int ret = 0;
        switch (item) {
            case 1:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mCarSet.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(true);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
