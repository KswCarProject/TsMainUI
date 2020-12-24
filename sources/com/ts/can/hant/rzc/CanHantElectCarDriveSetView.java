package com.ts.can.hant.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHantElectCarDriveSetView extends CanScrollCarInfoView {
    private static int HANT_DJDL = 6;
    private static int HANT_LOWPOW = 3;
    private static int HANT_WORKDIR = 2;
    private static int HANT_WORKMODE = 1;
    private static int HANT_XSLC = 4;
    private static int HANT_ZLDY = 5;
    private static int HANT_ZS = 0;
    private static int[] workDirNums = {R.string.can_tripbresettiming_wc_1, R.string.can_driving_qj, R.string.can_driving_ht};
    private static int[] workModeNums = {R.string.can_driving_gs, R.string.can_driving_ds};
    private CanDataInfo.HanTang_DriveInfo mDriveData;
    private CanDataInfo.HanTang_Moto mZSData;

    public CanHantElectCarDriveSetView(Activity activity) {
        super(activity, HANT_DJDL + 1);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mZSData = new CanDataInfo.HanTang_Moto();
        this.mDriveData = new CanDataInfo.HanTang_DriveInfo();
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mItemTitleIds = new int[]{R.string.can_dfqc_motor_r, R.string.can_car_yx_mode, R.string.can_car_yx_dir, R.string.can_car_lowpow, R.string.can_driving_mileage, R.string.can_driving_zldy, R.string.can_driving_djdl};
    }

    public void ResetData(boolean check) {
        CanJni.HanTangElectCarGetMotoData(this.mZSData);
        if (i2b(this.mZSData.UpdateOnce) && (!check || i2b(this.mZSData.Update))) {
            this.mZSData.Update = 0;
            updateItem(HANT_ZS, this.mZSData.Zs, String.format("%d r/min", new Object[]{Integer.valueOf(this.mZSData.Zs)}));
        }
        CanJni.HanTangElectCarGetDriveInfo(this.mDriveData);
        if (!i2b(this.mDriveData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDriveData.Update)) {
            this.mDriveData.Update = 0;
            updateItem(HANT_WORKMODE, this.mDriveData.WorkMode, getActivity().getResources().getString(workModeNums[this.mDriveData.WorkMode]));
            updateItem(HANT_WORKDIR, this.mDriveData.WorkDir, getActivity().getResources().getString(workDirNums[this.mDriveData.WorkDir]));
            updateItem(HANT_LOWPOW, this.mDriveData.LowPow);
            updateItem(HANT_XSLC, this.mDriveData.Xslc, String.format("%.1f Km", new Object[]{Float.valueOf(((float) this.mDriveData.Xslc) * 0.1f)}));
            updateItem(HANT_ZLDY, this.mDriveData.Zldy, String.format("%.1f V", new Object[]{Float.valueOf(((float) this.mDriveData.Zldy) * 0.1f)}));
            updateItem(HANT_DJDL, this.mDriveData.Djdl, String.format("%.1f A", new Object[]{Float.valueOf(((float) this.mDriveData.Djdl) * 0.1f)}));
        }
    }

    public void QueryData() {
        CanJni.HanTangElectCarQuery(34);
        Sleep(3);
        CanJni.HanTangElectCarQuery(35);
    }
}
