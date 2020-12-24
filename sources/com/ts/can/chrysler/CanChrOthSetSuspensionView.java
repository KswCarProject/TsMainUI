package com.ts.can.chrysler;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanFunc;
import com.ts.can.CanScrollCarInfoView;

public class CanChrOthSetSuspensionView extends CanScrollCarInfoView {
    private CanDataInfo.ChrOthAdt mSetAdt;
    private CanDataInfo.ChrOthSetData mSetData;

    public CanChrOthSetSuspensionView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        if (id == 4) {
            CanJni.ChrOthCarSet(212, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.ChrOthCarSet(208, Neg(this.mSetData.Sxzdtjxj));
                return;
            case 1:
                CanJni.ChrOthCarSet(209, Neg(this.mSetData.Ltqjd));
                return;
            case 2:
                CanJni.ChrOthCarSet(210, Neg(this.mSetData.Ysms));
                return;
            case 3:
                CanJni.ChrOthCarSet(211, Neg(this.mSetData.Cljzms));
                return;
            case 5:
                CanJni.ChrOthCarSet(213, Neg(this.mSetData.Zdjcxj));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_sxczdtjxj, R.string.can_ltqjd, R.string.can_ysms, R.string.can_cljzms, R.string.can_xsxjxx, R.string.can_zdjcxj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[4] = new int[]{R.string.can_all, R.string.can_warn};
        this.mSetAdt = new CanDataInfo.ChrOthAdt();
        this.mSetData = new CanDataInfo.ChrOthSetData();
        CanJni.ChrOthGetAdt(this.mSetAdt);
        this.mItemVisibles = new int[]{this.mSetAdt.Sxzdtjxj, this.mSetAdt.Ltqjd, this.mSetAdt.Ysms, this.mSetAdt.Cljzms, this.mSetAdt.Xsxjxx, this.mSetAdt.Zdjcxj};
        if (CanFunc.getInstance().IsCustomShow("Jeep")) {
            getActivity().findViewById(R.id.can_comm_list_layout).setBackgroundResource(R.drawable.can_grand_cherokee_bg);
        }
    }

    public void ResetData(boolean check) {
        CanJni.ChrOthGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.XJUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.XJUpdate)) {
            this.mSetData.XJUpdate = 0;
            updateItem(new int[]{this.mSetData.Sxzdtjxj, this.mSetData.Ltqjd, this.mSetData.Ysms, this.mSetData.Cljzms, this.mSetData.Xsxjxx, this.mSetData.Zdjcxj});
        }
    }

    public void QueryData() {
        CanJni.ChrOthQuery(64, 208, 0, 0);
    }
}
