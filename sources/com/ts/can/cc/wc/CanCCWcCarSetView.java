package com.ts.can.cc.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanCCWcCarSetView extends CanScrollCarInfoView {
    private CanDataInfo.CcWcCarData mCarAdt;
    private CanDataInfo.CcWcCarData mCarData;

    public CanCCWcCarSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.CcWcGetCarSet(16, item);
                return;
            case 1:
                CanJni.CcWcGetCarSet(17, item);
                return;
            case 2:
                CanJni.CcWcGetCarSet(18, item);
                return;
            case 3:
                CanJni.CcWcGetCarSet(19, item);
                return;
            case 5:
                CanJni.CcWcGetCarSet2(0, item, 255, 255);
                FtSet.Setlgb5(item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 4) {
            CanJni.CcWcGetCarSet(20, Neg(this.mCarData.Tcsz));
        } else if (id == 6) {
            FtSet.Setlgb4(Neg(FtSet.Getlgb4()));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_cch6_dingdeng_delaytime, R.string.can_cch6_gensuihuijia_delaytime, R.string.can_cch6_jiedian_setup, R.string.can_h6_coupe_ylgxcgq, R.string.can_cch9_trailer, R.string.can_fzhsj, R.string.can_has_right_camera};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        int[] iArr = new int[7];
        iArr[5] = 1;
        iArr[6] = 1;
        this.mItemVisibles = iArr;
        this.mPopValueIds[0] = new int[]{R.array.can_cch6_dd_delay_2017_array};
        this.mPopValueIds[1] = new int[]{R.array.can_cch6_hj_delay_array};
        this.mPopValueIds[2] = new int[]{R.array.can_cch6_jd_setup_array};
        this.mPopValueIds[3] = new int[]{R.string.can_h6_coupe_oz_mode, R.string.can_h6_coupe_yz_mode};
        this.mPopValueIds[5] = new int[]{R.string.can_mzd_cx4_drive_owner, R.string.can_mzd_cx4_drive_auto};
        this.mCarAdt = new CanDataInfo.CcWcCarData();
        this.mCarData = new CanDataInfo.CcWcCarData();
    }

    public void ResetData(boolean check) {
        CanJni.CcWcGetCarSetAdt(this.mCarAdt);
        CanJni.CcWcGetCarSetData(this.mCarData);
        if (i2b(this.mCarAdt.UpdateOnce) && (!check || i2b(this.mCarAdt.Update))) {
            this.mCarAdt.Update = 0;
            showItem(new int[]{this.mCarAdt.Ddyssjsz, this.mCarAdt.Gshjyssz, this.mCarAdt.Jdsz, this.mCarAdt.Ylgxcgqsz, this.mCarAdt.Tcsz});
        }
        if (i2b(this.mCarData.UpdateOnce) && (!check || i2b(this.mCarData.Update))) {
            this.mCarData.Update = 0;
            updateItem(new int[]{this.mCarData.Ddyssjsz, this.mCarData.Gshjyssz, this.mCarData.Jdsz, this.mCarData.Ylgxcgqsz, this.mCarData.Tcsz});
        }
        updateItem(5, FtSet.Getlgb5());
        updateItem(6, FtSet.Getlgb4());
    }

    public void QueryData() {
        CanJni.CcWcGetCarQuery(5, 1, 98);
    }
}
