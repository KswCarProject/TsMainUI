package com.ts.can.faw.t3.b30;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFawB30T3MachineStatusView extends CanScrollCarInfoView {
    private CanDataInfo.FawB30T3_TmInfo mTmInfo;

    public CanFawB30T3MachineStatusView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_djed, R.string.can_zdgl, R.string.can_dfqc_motor_r, R.string.can_machine_infos, R.string.can_djnj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mTmInfo = new CanDataInfo.FawB30T3_TmInfo();
    }

    public void ResetData(boolean check) {
        CanJni.FawB30T3GetTmInfo(this.mTmInfo);
        if (!i2b(this.mTmInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTmInfo.Update)) {
            this.mTmInfo.Update = 0;
            if (this.mTmInfo.Djed != 255) {
                updateItem(0, this.mTmInfo.Djed, String.valueOf(this.mTmInfo.Djed) + " KW");
            } else {
                updateItem(0, 0, "-.-");
            }
            if (this.mTmInfo.Zdgl != 255) {
                updateItem(1, this.mTmInfo.Zdgl, String.valueOf(this.mTmInfo.Zdgl) + " KW");
            } else {
                updateItem(1, 0, "-.-");
            }
            if (this.mTmInfo.Djzs < 0 || this.mTmInfo.Djzs > 28000) {
                updateItem(2, 0, "-.-");
            } else {
                updateItem(2, this.mTmInfo.Djzs, String.valueOf(this.mTmInfo.Djzs - 14000) + " RPM");
            }
            if (this.mTmInfo.Gzxx < 0 || this.mTmInfo.Gzxx > 1) {
                updateItem(3, 0, "-.-");
            } else {
                updateItem(3, this.mTmInfo.Gzxx, getString(this.mTmInfo.Gzxx == 0 ? R.string.can_normal : R.string.can_dfqc_error));
            }
            if (this.mTmInfo.Djnj < 0 || this.mTmInfo.Djnj > 2800) {
                updateItem(4, 0, "-.-");
            } else {
                updateItem(4, this.mTmInfo.Djnj, String.valueOf((((float) this.mTmInfo.Djnj) * 0.25f) - 350.0f) + " NM");
            }
        }
    }

    public void QueryData() {
        CanJni.FawB30T3Query(5);
    }
}
