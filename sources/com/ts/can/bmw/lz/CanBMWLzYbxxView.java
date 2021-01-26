package com.ts.can.bmw.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.txznet.sdk.TXZResourceManager;

public class CanBMWLzYbxxView extends CanScrollCarInfoView {
    protected static final int ITEM_FDJZS = 3;
    protected static final int ITEM_KXSLC = 2;
    protected static final int ITEM_LQYWD = 0;
    protected static final int ITEM_SSCS = 1;
    protected static final int ITEM_ZLC = 4;
    private CanDataInfo.CAN_Msg mCanMsg;
    String mDisDw = TXZResourceManager.STYLE_DEFAULT;
    String mTempDw = TXZResourceManager.STYLE_DEFAULT;

    public CanBMWLzYbxxView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_lqywd, R.string.can_curspeed, R.string.can_kxslc, R.string.can_rpm, R.string.can_total_mile};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mCanMsg = new CanDataInfo.CAN_Msg();
    }

    public void ResetData(boolean check) {
        CanJni.GetCarInfo(this.mCanMsg);
        if (!i2b(this.mCanMsg.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCanMsg.Update)) {
            this.mCanMsg.Update = 0;
            if (i2b(this.mCanMsg.OutTemp)) {
                this.mTempDw = "℃";
            } else {
                this.mTempDw = "℉";
            }
            if (this.mCanMsg.DistanceDw == 0) {
                this.mDisDw = "km";
            } else if (this.mCanMsg.DistanceDw == 1) {
                this.mDisDw = "mile";
            }
            updateItem(0, this.mCanMsg.Lqywd, String.format("%d", new Object[]{Integer.valueOf(this.mCanMsg.Lqywd)}));
            updateItem(1, this.mCanMsg.Speed, String.format("%d", new Object[]{Integer.valueOf(this.mCanMsg.Speed)}));
            updateItem(2, this.mCanMsg.EndurOil, String.valueOf(String.format("%d ", new Object[]{Integer.valueOf(this.mCanMsg.EndurOil)})) + this.mDisDw);
            updateItem(3, this.mCanMsg.Rpm, String.format("%d", new Object[]{Integer.valueOf(this.mCanMsg.Rpm)}));
            updateItem(4, this.mCanMsg.Distance, String.valueOf(String.format("%d ", new Object[]{Integer.valueOf(this.mCanMsg.Distance)})) + this.mDisDw);
        }
    }

    public void QueryData() {
        CanJni.BmwLzQueryData(53, 0);
    }
}
