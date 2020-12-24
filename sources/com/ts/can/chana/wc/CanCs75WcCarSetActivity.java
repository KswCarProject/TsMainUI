package com.ts.can.chana.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanCs75WcCarSetActivity extends CanScrollCarInfoView {
    private CanDataInfo.Cs75WcCarSet mSetAdt;
    private CanDataInfo.Cs75WcCarSet mSetData;

    public CanCs75WcCarSetActivity(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.ChanAWcCs75CarSet(5, item);
                return;
            case 4:
                CanJni.ChanAWcCs75CarSet(4, item);
                return;
            case 7:
                CanJni.ChanAWcCs75CarSet(1, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.ChanAWcCs75CarSet(8, Neg(this.mSetData.Jszdhq));
                return;
            case 1:
                CanJni.ChanAWcCs75CarSet(7, Neg(this.mSetData.Hsjzdzd));
                return;
            case 2:
                CanJni.ChanAWcCs75CarSet(6, Neg(this.mSetData.Dchygfz));
                return;
            case 5:
                CanJni.ChanAWcCs75CarSet(3, Neg(this.mSetData.Xhzdjs));
                return;
            case 6:
                CanJni.ChanAWcCs75CarSet(2, Neg(this.mSetData.Xczdls));
                return;
            case 8:
                CanJni.ChanAWcCs75CarSet(9, Neg(this.mSetData.Ktzgz));
                return;
            case 9:
                FtSet.Setlgb4(Neg(FtSet.Getlgb4()));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_jszdhq, R.string.can_zdhsjzd, R.string.can_dchygfz, R.string.can_yjzx, R.string.can_tigger7_light_delay, R.string.can_xhzdjs, R.string.can_xczdls, R.string.can_ykjssz, R.string.can_ktzgz, R.string.can_right_camera};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[3] = new int[]{R.string.can_off, R.string.can_3c, R.string.can_5c, R.string.can_7c};
        this.mPopValueIds[4] = new int[]{R.string.can_off, R.string.can_10s, R.string.can_30s, R.string.can_60s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[7] = new int[]{R.string.can_door_unlock_key2, R.string.can_door_unlock_key1};
        for (int i = 0; i < this.mItemVisibles.length; i++) {
            this.mItemVisibles[i] = 0;
        }
        this.mItemVisibles[9] = 1;
        this.mSetAdt = new CanDataInfo.Cs75WcCarSet();
        this.mSetData = new CanDataInfo.Cs75WcCarSet();
    }

    public void ResetData(boolean check) {
        CanJni.ChanAWcCs75GetCarSetAdt(this.mSetAdt);
        if (i2b(this.mSetAdt.UpdateOnce) && (!check || i2b(this.mSetAdt.Update))) {
            this.mSetAdt.Update = 0;
            showItem(new int[]{this.mSetAdt.Jszdhq, this.mSetAdt.Hsjzdzd, this.mSetAdt.Dchygfz, this.mSetAdt.Yjzx, this.mSetAdt.Qzdys, this.mSetAdt.Xhzdjs, this.mSetAdt.Xczdls, this.mSetAdt.Ykjsms, this.mSetAdt.Ktzgz});
        }
        CanJni.ChanAWcCs75GetCarSet(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Jszdhq, this.mSetData.Hsjzdzd, this.mSetData.Dchygfz, this.mSetData.Yjzx, this.mSetData.Qzdys, this.mSetData.Xhzdjs, this.mSetData.Xczdls, this.mSetData.Ykjsms, this.mSetData.Ktzgz});
        }
        updateItem(9, FtSet.Getlgb4());
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 135);
    }
}
