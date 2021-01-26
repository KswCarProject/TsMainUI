package com.ts.can.porsche.lz;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.txznet.sdk.TXZResourceManager;

public class CanPorscheLzStatueView extends CanScrollCarInfoView {
    private CanDataInfo.CAN_Msg mData;

    public CanPorscheLzStatueView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_curspeed, R.string.can_range_xhlc, R.string.can_rpm, R.string.can_driving_mileage};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mData = new CanDataInfo.CAN_Msg();
    }

    /* access modifiers changed from: package-private */
    public String Byte2String(int item, int data) {
        String str = TXZResourceManager.STYLE_DEFAULT;
        switch (data) {
            case SupportMenu.USER_MASK:
            case ViewCompat.MEASURED_SIZE_MASK:
                return "--";
            default:
                switch (item) {
                    case 0:
                        str = String.format("%d km/h", new Object[]{Integer.valueOf(data)});
                        break;
                    case 1:
                        str = String.format("%d km", new Object[]{Integer.valueOf(data)});
                        break;
                    case 2:
                        str = String.format("%d rpm", new Object[]{Integer.valueOf(data)});
                        break;
                    case 3:
                        str = String.format("%d km", new Object[]{Integer.valueOf(data)});
                        break;
                }
                return str;
        }
    }

    public void ResetData(boolean check) {
        CanJni.GetCarInfo(this.mData);
        if (!i2b(this.mData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData.Update)) {
            this.mData.Update = 0;
            updateItem(0, this.mData.Speed, Byte2String(0, this.mData.Speed));
            updateItem(1, this.mData.EndurOil, Byte2String(1, this.mData.EndurOil));
            updateItem(2, this.mData.Rpm, Byte2String(2, this.mData.Rpm));
            updateItem(3, this.mData.Distance, Byte2String(3, this.mData.Distance));
        }
    }

    public void QueryData() {
        CanJni.PorscheLzQuery(53);
        Sleep(5);
    }
}
