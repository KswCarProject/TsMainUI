package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.txznet.sdk.TXZPoiSearchManager;

public class CanMzdRzcServiceInfoView extends CanScrollCarInfoView {
    private CanDataInfo.Mzd_Rzc_SericeInfo2 mServiceInfo;

    public CanMzdRzcServiceInfoView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.MzdRzcCarServiceSet2(1, 2, pos, 0, 0, 0);
                return;
            case 2:
                CanJni.MzdRzcCarServiceSet2(1, 3, pos / 256, pos % 256, 0, 0);
                return;
            case 4:
                CanJni.MzdRzcCarServiceSet2(2, 3, pos / 256, pos % 256, 0, 0);
                return;
            case 6:
                CanJni.MzdRzcCarServiceSet2(3, 3, pos / 256, pos % 256, 0, 0);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 0:
                CanJni.MzdRzcCarServiceSet2(1, 1, Neg(this.mServiceInfo.YyybySw), 0, 0, 0);
                return;
            case 3:
                CanJni.MzdRzcCarServiceSet2(2, 1, Neg(this.mServiceInfo.LthwSw), 0, 0, 0);
                return;
            case 5:
                CanJni.MzdRzcCarServiceSet2(3, 1, Neg(this.mServiceInfo.HjySw), 0, 0, 0);
                return;
            case 7:
            case 8:
            case 9:
                new CanItemMsgBox(id, getActivity(), R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.MzdRzcCarServiceSet2(param - 6, 4, 1, 0, 0, 0);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_yyyby_status, R.string.can_yyyby_time, R.string.can_yyyby_distance, R.string.can_lthw_status, R.string.can_lthw_distance, R.string.can_hjy_status, R.string.can_hjy_distance, R.string.can_yyyby_reset, R.string.can_lthw_reset, R.string.can_hjy_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mProgressAttrs[1] = new int[]{1, 36, 1, 1};
        this.mProgressAttrs[2] = new int[]{1000, TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT, 500, 1};
        this.mProgressAttrs[4] = new int[]{1000, 60000, 500, 1};
        this.mProgressAttrs[6] = new int[]{1000, TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT, 500, 1};
        this.mServiceInfo = new CanDataInfo.Mzd_Rzc_SericeInfo2();
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarServiceSet2(this.mServiceInfo);
        if (!i2b(this.mServiceInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mServiceInfo.Update)) {
            this.mServiceInfo.Update = 0;
            updateItem(0, this.mServiceInfo.YyybySw);
            if (this.mServiceInfo.Time >= 1 && this.mServiceInfo.Time <= 36) {
                updateItem(1, this.mServiceInfo.Time, checkTime(this.mServiceInfo.Time));
            }
            if (this.mServiceInfo.Yyyby >= 1000 && this.mServiceInfo.Yyyby <= 10000) {
                updateItem(2, this.mServiceInfo.Yyyby, String.valueOf(this.mServiceInfo.Yyyby) + " KM");
            }
            showItem(1, this.mServiceInfo.YyybySw);
            showItem(2, this.mServiceInfo.YyybySw);
            updateItem(3, this.mServiceInfo.LthwSw);
            if (this.mServiceInfo.Lthw >= 1000 && this.mServiceInfo.Lthw <= 60000) {
                updateItem(4, this.mServiceInfo.Lthw, String.valueOf(this.mServiceInfo.Lthw) + " KM");
            }
            showItem(4, this.mServiceInfo.LthwSw);
            updateItem(5, this.mServiceInfo.HjySw);
            if (this.mServiceInfo.Hjy >= 1000 && this.mServiceInfo.Hjy <= 10000) {
                updateItem(6, this.mServiceInfo.Hjy, String.valueOf(this.mServiceInfo.Hjy) + " KM");
            }
            showItem(6, this.mServiceInfo.HjySw);
        }
    }

    private String checkTime(int val) {
        if (val == 1) {
            return getString(R.string.can_service_time_30d);
        }
        return String.valueOf(val) + " " + getString(R.string.can_service_time_unit);
    }

    public void QueryData() {
        CanJni.MzdCx4Query(49, 0);
    }
}
