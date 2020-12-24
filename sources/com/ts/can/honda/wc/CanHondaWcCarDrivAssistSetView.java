package com.ts.can.honda.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaWcCarDrivAssistSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaWcAssistSet mAssistData;

    public CanHondaWcCarDrivAssistSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HondaWcCarAssistSet(1, item);
                return;
            case 3:
                CanJni.HondaWcCarAssistSet(4, item);
                return;
            case 4:
                CanJni.HondaWcCarAssistSet(7, item);
                return;
            case 6:
                CanJni.HondaWcCarAssistSet(9, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.HondaWcCarAssistSet(2, Neg(this.mAssistData.ACCqctztsy));
                return;
            case 2:
                CanJni.HondaWcCarAssistSet(3, Neg(this.mAssistData.ZtLKAStsy));
                return;
            case 5:
                CanJni.HondaWcCarAssistSet(8, Neg(this.mAssistData.Ttjg));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_sdqfwxjgjl, R.string.can_accqctztsy, R.string.can_ztlkastsy, R.string.can_cdplfxxtsd, R.string.can_jsyzyljcq, R.string.can_ttjg, R.string.can_cdpyyzxt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_tripbresettiming_wc_1, R.string.can_sdqfwxjgjl_1, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_3};
        this.mPopValueIds[3] = new int[]{R.string.can_tripbresettiming_wc_1, R.string.can_cdplfxxtsd_1, R.string.can_cdplfxxtsd_2, R.string.can_cdplfxxtsd_3};
        this.mPopValueIds[4] = new int[]{R.string.can_Scsfctsy_3, R.string.can_sjjg, R.string.can_cjhsjjg};
        this.mPopValueIds[6] = new int[]{R.string.can_tripbresettiming_wc_1, R.string.can_cdpyyzxt_1, R.string.can_cdpyyzxt_2, R.string.can_cdpyyzxt_3, R.string.can_cdpyyzxt_4};
        this.mAssistData = new CanDataInfo.HondaWcAssistSet();
        if (4 != CanJni.GetSubType() && 5 != CanJni.GetSubType()) {
            this.mItemVisibles[5] = 0;
        }
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcGetAssistSet(this.mAssistData);
        if (!i2b(this.mAssistData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAssistData.Update)) {
            this.mAssistData.Update = 0;
            updateItem(0, this.mAssistData.Sdqfwxjgjl);
            updateItem(1, this.mAssistData.ACCqctztsy);
            updateItem(2, this.mAssistData.ZtLKAStsy);
            updateItem(3, this.mAssistData.Cdplfxxtsd);
            updateItem(4, this.mAssistData.Jsyzyljcq);
            updateItem(5, this.mAssistData.Ttjg);
            updateItem(6, this.mAssistData.Cdpyyzxt);
        }
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 104);
    }
}
