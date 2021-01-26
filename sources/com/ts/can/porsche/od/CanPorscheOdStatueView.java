package com.ts.can.porsche.od;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanPorscheOdStatueView extends CanScrollCarInfoView {
    private CanDataInfo.PorscheCarSet mData;

    public CanPorscheOdStatueView(Activity activity) {
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
        this.mItemTitleIds = new int[]{R.string.can_sscs, R.string.can_range_xhlc, R.string.can_rpm, R.string.can_driving_mileage};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mData = new CanDataInfo.PorscheCarSet();
    }

    public void ResetData(boolean check) {
        CanJni.PorscheOdGetCarSet(this.mData);
        if (i2b(this.mData.SpeedUpdateOnce) && (!check || i2b(this.mData.SpeedUpdate))) {
            this.mData.SpeedUpdate = 0;
            updateItem(0, this.mData.Speed, String.format("%d Km/h", new Object[]{Integer.valueOf(this.mData.Speed)}));
        }
        if (i2b(this.mData.XhlcUpdateOnce) && (!check || i2b(this.mData.XhlcUpdate))) {
            this.mData.XhlcUpdate = 0;
            updateItem(1, this.mData.Xhlc, String.format("%d Km", new Object[]{Integer.valueOf(this.mData.Xhlc)}));
        }
        if (i2b(this.mData.RpmUpdateOnce) && (!check || i2b(this.mData.RpmUpdate))) {
            this.mData.RpmUpdate = 0;
            updateItem(2, this.mData.Rpm, String.format("%d Rpm", new Object[]{Integer.valueOf(this.mData.Rpm)}));
        }
        if (!i2b(this.mData.DistanceUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData.DistanceUpdate)) {
            this.mData.DistanceUpdate = 0;
            updateItem(3, this.mData.Distance, String.format("%d Km", new Object[]{Integer.valueOf(this.mData.Distance)}));
        }
    }

    public void QueryData() {
        CanJni.PorscheOdQuery(64, 1);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 2);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 3);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 4);
    }
}
