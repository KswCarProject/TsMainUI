package com.ts.can.renault.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanRenaultWcOtherSetView extends CanScrollCarInfoView {
    protected static final int ITEM_DDSDKHYS = 0;
    protected static final int ITEM_QFYSZD = 1;
    protected static final int ITEM_QHYSYCTZ = 2;
    protected static final int ITEM_QXXH = 5;
    protected static final int ITEM_ZDKQXH = 4;
    protected static final int ITEM_ZDZDWHSJ = 3;
    private CanDataInfo.RenaulWcCarSetData mSetData;

    public CanRenaultWcOtherSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.RenaultWcCarSet(41, Neg(this.mSetData.Ddsdkhys), 0, 0);
                return;
            case 1:
                CanJni.RenaultWcCarSet(42, Neg(this.mSetData.Qfyszd), 0, 0);
                return;
            case 2:
                CanJni.RenaultWcCarSet(43, Neg(this.mSetData.Qhysyctz), 0, 0);
                return;
            case 3:
                CanJni.RenaultWcCarSet(44, Neg(this.mSetData.Zdzdwhsj), 0, 0);
                return;
            case 4:
                CanJni.RenaultWcCarSet(45, Neg(this.mSetData.Zdkqxh), 0, 0);
                return;
            case 5:
                CanJni.RenaultWcCarSet(46, Neg(this.mSetData.Qxxh), 0, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_dcyhszdqd, R.string.can_qfyszd, R.string.can_qhysyctz, R.string.can_zdzdwhsj, R.string.can_auto_recirculate, R.string.can_kqzlqxxh};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.RenaulWcCarSetData();
    }

    public void ResetData(boolean check) {
        CanJni.RenaultWcGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Ddsdkhys);
            updateItem(1, this.mSetData.Qfyszd);
            updateItem(2, this.mSetData.Qhysyctz);
            updateItem(3, this.mSetData.Zdzdwhsj);
            updateItem(4, this.mSetData.Zdkqxh);
            updateItem(5, this.mSetData.Qxxh);
        }
    }

    public void QueryData() {
    }
}
