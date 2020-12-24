package com.ts.can.gm.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMWcSetConvView extends CanScrollCarInfoView {
    private CanDataInfo.GmWc_ConvenData mConvAdt;
    private CanDataInfo.GmWc_ConvenData2 mConvAdt2;
    private CanDataInfo.GmWc_ConvenData mConvData;
    private CanDataInfo.GmWc_ConvenData2 mConvData2;

    public CanGMWcSetConvView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.GmWcCarConvenSet(3, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GmWcCarConvenSet(1, Neg(this.mConvData.Jsyzytcyw));
                return;
            case 2:
                CanJni.GmWcCarConvenSet(2, Neg(this.mConvData.Whsjdczdqx));
                return;
            case 3:
                CanJni.GmWcCarConvenSet(4, Neg(this.mConvData.Whsjzdzd));
                return;
            case 4:
                CanJni.GmWcCarConvenSet(5, Neg(this.mConvData.Jsygxsz));
                return;
            case 5:
                CanJni.GmWcCarConvenSet(6, Neg(this.mConvData.Ddzdhcys));
                return;
            case 6:
                CanJni.GmWcCarConvenSet(7, Neg(this.mConvData.Zxgzlcqx));
                return;
            case 7:
                CanJni.GmWcCarConvenSet(8, Neg(this.mConvData2.Zdyg));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_jsyzytcyw, R.string.can_zxgzlcyw, R.string.can_dchsjzdqx, R.string.can_zdhsjzd, R.string.can_personalization, R.string.can_ddzdhcys, R.string.can_zxgzlcqx, R.string.can_zdys};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_off, R.string.can_zxzss, R.string.can_zxzts, R.string.can_zxzsyts};
        for (int i = 0; i < this.mItemVisibles.length; i++) {
            this.mItemVisibles[i] = 0;
        }
        this.mConvAdt = new CanDataInfo.GmWc_ConvenData();
        this.mConvData = new CanDataInfo.GmWc_ConvenData();
        this.mConvAdt2 = new CanDataInfo.GmWc_ConvenData2();
        this.mConvData2 = new CanDataInfo.GmWc_ConvenData2();
    }

    public void ResetData(boolean check) {
        CanJni.GmWcGetCarConvenSetAdt(this.mConvAdt);
        CanJni.GmWcGetCarConvenSet(this.mConvData);
        if (i2b(this.mConvAdt.UpdateOnce) && (!check || i2b(this.mConvAdt.Update))) {
            this.mConvAdt.Update = 0;
            int[] values = {this.mConvAdt.Jsyzytcyw, this.mConvAdt.Zxgzlcyw, this.mConvAdt.Whsjdczdqx, this.mConvAdt.Whsjzdzd, this.mConvAdt.Jsygxsz, this.mConvAdt.Ddzdhcys, this.mConvAdt.Zxgzlcqx};
            for (int i = 0; i < values.length; i++) {
                showItem(i, values[i]);
            }
        }
        if (i2b(this.mConvData.UpdateOnce) && (!check || i2b(this.mConvData.Update))) {
            this.mConvData.Update = 0;
            int[] values2 = {this.mConvData.Jsyzytcyw, this.mConvData.Zxgzlcyw, this.mConvData.Whsjdczdqx, this.mConvData.Whsjzdzd, this.mConvData.Jsygxsz, this.mConvData.Ddzdhcys, this.mConvData.Zxgzlcqx};
            for (int i2 = 0; i2 < values2.length; i2++) {
                updateItem(i2, values2[i2]);
            }
        }
        CanJni.GmWcGetCarConvenSet2Adt(this.mConvAdt2);
        CanJni.GmWcGetCarConvenSet2(this.mConvData2);
        if (i2b(this.mConvAdt2.UpdateOnce) && (!check || i2b(this.mConvAdt2.Update))) {
            this.mConvAdt2.Update = 0;
            showItem(7, new int[]{this.mConvAdt2.Zdyg}[0]);
        }
        if (!i2b(this.mConvData2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mConvData2.Update)) {
            this.mConvData2.Update = 0;
            updateItem(7, new int[]{this.mConvData2.Zdyg}[0]);
        }
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 85);
        Sleep(5);
        CanJni.GmWcCarQuery(5, 1, 86);
    }
}
