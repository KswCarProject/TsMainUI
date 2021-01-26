package com.ts.can.gm.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.txznet.sdk.TXZResourceManager;

public class CanGMWcCarStatusView extends CanScrollCarInfoView {
    private CanDataInfo.GmWcDisInfo mDisInfo;

    public CanGMWcCarStatusView(Activity activity) {
        super(activity, 9);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ssyh, R.string.can_range_xhlc, R.string.can_total_mile, R.string.can_avg_cosume_1, R.string.can_little_mile_1, R.string.can_avg_cosume_2, R.string.can_little_mile_2, R.string.can_avg_cosume_3, R.string.can_little_mile_3};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mDisInfo = new CanDataInfo.GmWcDisInfo();
    }

    public void ResetData(boolean check) {
        CanJni.GmWcGetCarDisInfo(this.mDisInfo);
        if (!i2b(this.mDisInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDisInfo.Update)) {
            this.mDisInfo.Update = 0;
            updateItem(0, 0, getCheckConsume(this.mDisInfo.Ssyh));
            updateItem(1, 0, String.valueOf(this.mDisInfo.Xhlc) + (this.mDisInfo.LcDw == 0 ? " KM" : " Mile"));
            updateItem(2, 0, getCheckMile(this.mDisInfo.Zlc));
            updateItem(3, 0, getCheckConsume(this.mDisInfo.Pjyh1));
            updateItem(4, 0, getCheckMile(this.mDisInfo.Xjlc1));
            updateItem(5, 0, getCheckConsume(this.mDisInfo.Pjyh2));
            updateItem(6, 0, getCheckMile(this.mDisInfo.Xjlc2));
            updateItem(7, 0, getCheckConsume(this.mDisInfo.Pjyh3));
            updateItem(8, 0, getCheckMile(this.mDisInfo.Xjlc3));
        }
    }

    private String getCheckMile(int value) {
        switch (this.mDisInfo.LcDw) {
            case 0:
                return String.format("%.1f %s", new Object[]{Float.valueOf(((float) value) / 10.0f), "KM"});
            case 1:
                return String.format("%.1f %s", new Object[]{Float.valueOf(((float) value) / 10.0f), "Mile"});
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    private String getCheckConsume(int value) {
        switch (this.mDisInfo.YhDw) {
            case 0:
                return String.format("%.1f %s", new Object[]{Float.valueOf(((float) value) / 10.0f), "MPG"});
            case 1:
                return String.format("%.1f %s", new Object[]{Float.valueOf(((float) value) / 10.0f), "KM/L"});
            case 2:
                return String.format("%.1f %s", new Object[]{Float.valueOf(((float) value) / 10.0f), "L/100KM"});
            case 3:
                return String.format("%.1f %s", new Object[]{Float.valueOf(((float) value) / 10.0f), "L/H"});
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 52);
    }
}
