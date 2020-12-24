package com.ts.can.chrysler.rz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanRZygSetWiperMirrorView extends CanScrollCarInfoView {
    public static final int ITEM_DCSQXHSJ = 0;
    public static final int ITEM_HSJTGJ = 2;
    public static final int ITEM_MAX = 5;
    public static final int ITEM_YLGYSYS = 1;
    public static final int ITEM_YSYKGKCC = 4;
    public static final int ITEM_ZDZDWHSJ = 3;
    private CanDataInfo.ChrOthAdt mSetAdt;
    private CanDataInfo.ChrOthSetData mSetData;

    public CanRZygSetWiperMirrorView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.ChrOthCarSet(6, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.ChrOthCarSet(4, Neg(this.mSetData.Dcsqxhsj) + 1);
                return;
            case 2:
                CanJni.ChrOthCarSet(81, Neg(this.mSetData.Hsjtgj) + 1);
                return;
            case 3:
                CanJni.ChrOthCarSet(134, Neg(this.mSetData.Zdzdwhsj) + 1);
                return;
            case 4:
                CanJni.ChrOthCarSet(137, Neg(this.mSetData.Ysykkgcc) + 1);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_dchsjzdqx, R.string.can_jp_ylgysyg, R.string.can_jp_hsjtgj, R.string.can_hsjzdzd, R.string.can_chrysler_ysykkgcc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_off, R.string.can_on};
        this.mSetAdt = new CanDataInfo.ChrOthAdt();
        this.mSetData = new CanDataInfo.ChrOthSetData();
        CanJni.ChrOthGetAdt(this.mSetAdt);
        this.mItemVisibles = new int[]{this.mSetAdt.Dcsqxhsj, this.mSetAdt.Ylgysyg, this.mSetAdt.Hsjtgj, this.mSetAdt.Zdzdwhsj, this.mSetAdt.Ysykkgcc};
    }

    public void ResetData(boolean check) {
        CanJni.ChrOthGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.SensorUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.SensorUpdate)) {
            this.mSetData.SensorUpdate = 0;
            updateItem(0, this.mSetData.Dcsqxhsj);
            updateItem(1, this.mSetData.Ylgysyg);
            updateItem(2, this.mSetData.Hsjtgj);
            updateItem(3, this.mSetData.Zdzdwhsj);
            updateItem(4, this.mSetData.Ysykkgcc);
        }
    }

    public void QueryData() {
        CanJni.ChrOthQuery(50, 0, 0, 0);
    }
}
