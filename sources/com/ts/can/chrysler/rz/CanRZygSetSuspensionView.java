package com.ts.can.chrysler.rz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanRZygSetSuspensionView extends CanScrollCarInfoView {
    public static final int ITEM_CLJZMS = 4;
    public static final int ITEM_HDBP = 6;
    public static final int ITEM_LTQJDMS = 2;
    public static final int ITEM_MAX = 7;
    public static final int ITEM_SXCZDTJXJ = 0;
    public static final int ITEM_XSXJXX = 1;
    public static final int ITEM_YSMS = 3;
    public static final int ITEM_ZLZX = 5;
    private CanDataInfo.ChrOthAdt mSetAdt;
    private CanDataInfo.ChrOthSetData mSetData;

    public CanRZygSetSuspensionView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.ChrOthCarSet(66, item + 1);
                return;
            case 5:
                CanJni.ChrOthCarSet(70, item);
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
                CanJni.ChrOthCarSet(65, Neg(this.mSetData.Sxzdtjxj) + 1);
                return;
            case 2:
                CanJni.ChrOthCarSet(67, Neg(this.mSetData.Ltqjd) + 1);
                return;
            case 3:
                CanJni.ChrOthCarSet(68, Neg(this.mSetData.Ysms) + 1);
                return;
            case 4:
                CanJni.ChrOthCarSet(69, Neg(this.mSetData.Cljzms) + 1);
                return;
            case 6:
                CanJni.ChrOthCarSet(71, Neg(this.mSetData.PaddleShifting) + 1);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_sxczdtjxj, R.string.can_xsxjxx, R.string.can_ltqjd, R.string.can_ysms, R.string.can_cljzms, R.string.can_power_steer, R.string.can_jeep_hdbp};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_all, R.string.can_warn};
        this.mPopValueIds[5] = new int[]{R.string.can_golf_seat_drive_normal, R.string.can_golf_seat_drive_sport, R.string.can_comfort};
        this.mSetAdt = new CanDataInfo.ChrOthAdt();
        this.mSetData = new CanDataInfo.ChrOthSetData();
        CanJni.ChrOthGetAdt(this.mSetAdt);
        this.mItemVisibles = new int[]{this.mSetAdt.Sxzdtjxj, this.mSetAdt.Xsxjxx, this.mSetAdt.Ltqjd, this.mSetAdt.Ysms, this.mSetAdt.Cljzms, this.mSetAdt.PowerSteering, this.mSetAdt.PaddleShifting};
    }

    public void ResetData(boolean check) {
        CanJni.ChrOthGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.XJUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.XJUpdate)) {
            this.mSetData.XJUpdate = 0;
            updateItem(new int[]{this.mSetData.Sxzdtjxj, this.mSetData.Xsxjxx, this.mSetData.Ltqjd, this.mSetData.Ysms, this.mSetData.Cljzms, this.mSetData.PowerSteering, this.mSetData.PaddleShifting});
        }
    }

    public void QueryData() {
        CanJni.ChrOthQuery(7, 0, 0, 0);
    }
}
