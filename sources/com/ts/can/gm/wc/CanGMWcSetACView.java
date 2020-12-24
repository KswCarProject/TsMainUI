package com.ts.can.gm.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMWcSetACView extends CanScrollCarInfoView {
    private CanDataInfo.GmWc_ACData mACAdt;
    private CanDataInfo.GmWc_ACData mACData;

    public CanGMWcSetACView(Activity activity) {
        super(activity, 14);
    }

    public void onItem(int id, int item) {
        int cmd = -1;
        switch (id) {
            case 0:
                cmd = 1;
                break;
            case 1:
                cmd = 2;
                break;
            case 2:
                cmd = 3;
                break;
            case 3:
                cmd = 4;
                break;
            case 8:
                cmd = 9;
                break;
            case 10:
                cmd = 11;
                break;
            case 11:
                cmd = 12;
                break;
            case 12:
                cmd = 13;
                break;
            case 13:
                cmd = 14;
                break;
        }
        CarACSet(cmd, item);
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int cmd = -1;
        int param = -1;
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                cmd = 5;
                param = Neg(this.mACData.Zyzdtf);
                break;
            case 5:
                param = Neg(this.mACData.Zyzdjr);
                cmd = 6;
                break;
            case 6:
                param = Neg(this.mACData.Ykqdzyzdtf);
                cmd = 7;
                break;
            case 7:
                param = Neg(this.mACData.Ykqdzyzdjr);
                cmd = 8;
                break;
            case 9:
                param = Neg(this.mACData.Qczdqw);
                cmd = 10;
                break;
        }
        CarACSet(cmd, param);
    }

    private void CarACSet(int cmd, int param) {
        if (cmd != -1) {
            CanJni.GmWcCarAirSet(cmd, param);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ac_zdfl, R.string.can_ac_mode, R.string.can_ac_cgq, R.string.can_auto_area_temp, R.string.can_seat_auto_wind, R.string.can_seat_auto_hot, R.string.can_ycqdzyzdtf, R.string.can_ac_zyjr, R.string.can_rear_area_temp, R.string.can_ac_qccw, R.string.can_ac_hccw, R.string.can_ykqdkt, R.string.can_ac_cgq1, R.string.can_gl8_2017_ycqdzyjr};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[1] = new int[]{R.string.can_off, R.string.can_on, R.string.can_gl8_2017_ktycqd_last};
        this.mPopValueIds[2] = new int[]{R.string.can_ac_lo_sens, R.string.can_ac_hi_sens, R.string.can_off};
        this.mPopValueIds[3] = new int[]{R.string.can_ty_set, R.string.can_fq_set, R.string.can_sc_set};
        this.mPopValueIds[8] = new int[]{R.string.can_gl8_2017_hqktqd_close, R.string.can_gl8_2017_hqktqd_same, R.string.can_gl8_2017_hqktqd_last};
        this.mPopValueIds[10] = new int[]{R.string.can_soudong, R.string.can_gl8_2017_auto};
        this.mPopValueIds[11] = new int[]{R.string.can_soudong, R.string.can_gl8_2017_auto};
        this.mPopValueIds[12] = new int[]{R.string.can_ac_lo_sens, R.string.can_ac_hi_sens, R.string.can_off};
        this.mPopValueIds[13] = new int[]{R.string.can_off, R.string.can_ckhjsy, R.string.can_jiashiyuan};
        for (int i = 0; i < this.mItemVisibles.length; i++) {
            this.mItemVisibles[i] = 0;
        }
        this.mACAdt = new CanDataInfo.GmWc_ACData();
        this.mACData = new CanDataInfo.GmWc_ACData();
    }

    public void ResetData(boolean check) {
        CanJni.GmWcGetCarAcSetAdt(this.mACAdt);
        CanJni.GmWcGetCarAcSet(this.mACData);
        if (i2b(this.mACAdt.UpdateOnce) && (!check || i2b(this.mACAdt.Update))) {
            this.mACAdt.Update = 0;
            showItem(new int[]{this.mACAdt.Zdflms, this.mACAdt.Ktms, this.mACAdt.Kqzlcgq, this.mACAdt.Zdqywd, this.mACAdt.Zyzdtf, this.mACAdt.Zyzdjr, this.mACAdt.Ykqdzyzdtf, this.mACAdt.Ykqdzyzdjr, this.mACAdt.Hzqywd, this.mACAdt.Qczdqw, this.mACAdt.Hczdqw, this.mACAdt.Ykqdkt, this.mACAdt.Kqzlcgq1, this.mACAdt.Ycqdzyzdjr1});
        }
        if (!i2b(this.mACData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mACData.Update)) {
            this.mACData.Update = 0;
            updateItem(new int[]{this.mACData.Zdflms, this.mACData.Ktms, this.mACData.Kqzlcgq, this.mACData.Zdqywd, this.mACData.Zyzdtf, this.mACData.Zyzdjr, this.mACData.Ykqdzyzdtf, this.mACData.Ykqdzyzdjr, this.mACData.Hzqywd, this.mACData.Qczdqw, this.mACData.Hczdqw, this.mACData.Ykqdkt, this.mACData.Kqzlcgq1, this.mACData.Ycqdzyzdjr1});
        }
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 53);
    }
}
