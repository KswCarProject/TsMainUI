package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemPopupList;

public class CanHondaDaRzcMotoRearDoorView extends CanScrollCarInfoView {
    public static final int ITEM_HBXGGXKB = 3;
    public static final int ITEM_SYWSBDDDK = 1;
    public static final int ITEM_YKKQTJSD = 0;
    public static final int ITEM_ZDFZCMHSJ = 4;
    public static final int ITEM_ZDKQXLXG = 2;
    private static int[] mSywsbdddk;
    private static int[] mSywsbdddkXp;
    protected CanDataInfo.HondaSetData mSetData;

    public CanHondaDaRzcMotoRearDoorView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HondaDACarSet(37, item);
                return;
            case 1:
                CanJni.HondaDACarSet(38, item);
                return;
            case 4:
                CanJni.HondaDACarSet(71, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.HondaDACarSet(51, Neg(this.mSetData.Zdkqxlxg));
                return;
            case 3:
                CanJni.HondaDACarSet(70, Neg(this.mSetData.Hbxgygbgn));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ykkqtjsd, R.string.can_sywsbdddk, R.string.can_zdkqxlxg, R.string.can_hbxzdkq, R.string.can_hsjzdms};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_renhsh, R.string.can_unlock};
        this.mPopValueIds[1] = new int[0];
        this.mPopValueIds[4] = new int[]{R.string.can_shoud, R.string.can_zdzd};
        this.mSetData = new CanDataInfo.HondaSetData();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        mSywsbdddk = new int[]{R.string.can_dd_and_shoud, R.string.can_shoud};
        mSywsbdddkXp = new int[]{R.string.can_shoud, R.string.can_dd_and_shoud};
        getScrollManager().RemoveView(1);
        this.mItemObjects[1] = getScrollManager().addItemPopupList(this.mItemTitleIds[1], mSywsbdddk, 1, (CanItemPopupList.onPopItemClick) this);
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (i2b(this.mSetData.XtszUpdateOnce) && (!check || i2b(this.mSetData.XtszUpdate))) {
            this.mSetData.XtszUpdate = 0;
            updateItem(0, this.mSetData.Ykkqtjsd);
            updateItem(1, this.mSetData.Sywsbdddk);
            updateItem(3, this.mSetData.Hbxgygbgn);
            updateItem(4, this.mSetData.Zdfzcmhsj);
        }
        if (!i2b(this.mSetData.DoorWindowUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.DoorWindowUpdate)) {
            this.mSetData.DoorWindowUpdate = 0;
            updateItem(2, this.mSetData.Zdkqxlxg);
        }
    }

    public void QueryData() {
    }
}
