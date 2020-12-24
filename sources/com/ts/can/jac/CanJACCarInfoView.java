package com.ts.can.jac;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanJACCarInfoView extends CanScrollCarInfoView {
    private CanDataInfo.JAC_Charg_Data mChargData;
    private CanDataInfo.JAC_Drive_Data mDriveData;
    private String[] mDwxx;
    private String[] mJsms;
    private String[] mNll;
    private CanDataInfo.JAC_Power_Data mPowerData;

    public CanJACCarInfoView(Activity activity) {
        super(activity, 13);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() != 0) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_psa_wc_jsms, R.string.can_psa_wc_dwxx, R.string.can_dfqc_ac_a, R.string.can_battery_v, R.string.can_total_mile, R.string.can_sylc, R.string.can_cdztbfb, R.string.can_ec_nll, R.string.can_jac_pjnh, R.string.can_jac_ssnh, R.string.can_jac_nlfp_kt, R.string.can_jac_nlfp_hs, R.string.can_jac_nlfp_qd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mDriveData = new CanDataInfo.JAC_Drive_Data();
        this.mChargData = new CanDataInfo.JAC_Charg_Data();
        this.mPowerData = new CanDataInfo.JAC_Power_Data();
        this.mJsms = new String[]{"S", "E", "L"};
        this.mDwxx = new String[]{"R", "N", "D"};
        this.mNll = new String[]{getActivity().getResources().getString(R.string.can_jac_nll_bxs), getActivity().getResources().getString(R.string.can_jac_nll_qd), getActivity().getResources().getString(R.string.can_jac_nll_nlhs)};
    }

    public void ResetData(boolean check) {
        CanJni.JacRzcGetDriveData(this.mDriveData);
        if (i2b(this.mDriveData.UpdateOnce) && (!check || i2b(this.mDriveData.Update))) {
            this.mDriveData.Update = 0;
            updateItem(0, this.mDriveData.Jsms, this.mJsms[this.mDriveData.Jsms]);
            updateItem(1, this.mDriveData.Dwxs, this.mDwxx[this.mDriveData.Dwxs]);
        }
        CanJni.JacRzcGetChargData(this.mChargData);
        if (i2b(this.mChargData.UpdateOnce) && (!check || i2b(this.mChargData.Update))) {
            this.mChargData.Update = 0;
            updateItem(2, this.mChargData.Dl, String.format("%.1f A", new Object[]{Float.valueOf(((float) this.mChargData.Dl) * 0.1f)}));
            updateItem(3, this.mChargData.Dy, String.format("%.1f V", new Object[]{Float.valueOf(((float) this.mChargData.Dy) * 0.1f)}));
        }
        CanJni.JacRzcGetPowerData(this.mPowerData);
        if (!i2b(this.mPowerData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPowerData.Update)) {
            this.mPowerData.Update = 0;
            updateItem(4, this.mPowerData.Zlc, String.format("%.1f km", new Object[]{Float.valueOf(((float) this.mPowerData.Zlc) * 0.1f)}));
            updateItem(5, this.mPowerData.Cksylc, String.format("%d km", new Object[]{Integer.valueOf(this.mPowerData.Cksylc)}));
            updateItem(6, this.mPowerData.Cddlbfb, String.valueOf(String.format("%d", new Object[]{Integer.valueOf(this.mPowerData.Cddlbfb)})) + " %");
            updateItem(7, this.mPowerData.Nll, this.mNll[this.mPowerData.Nll]);
            updateItem(8, this.mPowerData.Pjnh, String.format("%.1f km/kmh", new Object[]{Float.valueOf(((float) this.mPowerData.Pjnh) * 0.1f)}));
            updateItem(9, this.mPowerData.Ssnh, String.format("%.1f km/kmh", new Object[]{Float.valueOf(((float) this.mPowerData.Ssnh) * 0.1f)}));
            updateItem(10, this.mPowerData.Nlfp_kt, String.format("%.1f km", new Object[]{Float.valueOf(((float) this.mPowerData.Nlfp_kt) * 0.1f)}));
            updateItem(11, this.mPowerData.Nlfp_hs, String.format("%.1f km", new Object[]{Float.valueOf(((float) this.mPowerData.Nlfp_hs) * 0.1f)}));
            updateItem(12, this.mPowerData.Nlfp_dj, String.format("%.2f km", new Object[]{Float.valueOf(((float) this.mPowerData.Nlfp_dj) * 0.75f)}));
        }
    }

    public void QueryData() {
        CanJni.JacRzcQuery(60, 0);
        Sleep(10);
        CanJni.JacRzcQuery(61, 0);
        Sleep(10);
        CanJni.JacRzcQuery(62, 0);
    }
}
