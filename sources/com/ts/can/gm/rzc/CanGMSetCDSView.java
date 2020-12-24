package com.ts.can.gm.rzc;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMSetCDSView extends CanScrollCarInfoView {
    public static final String TAG = "CanGMCarInfoActivity";
    private static final int[] mTslxArr = {R.string.can_bjying, R.string.can_aqtszy};
    private static final int[] mZdfzzbArr = {R.string.can_off, R.string.can_baojing, R.string.can_bjhzd};
    protected CanDataInfo.GM_CarSet mSetData = new CanDataInfo.GM_CarSet();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void GetSetData() {
        CanJni.GMGetCarSet(this.mSetData);
    }

    /* access modifiers changed from: protected */
    public void QuerySetData() {
        if (this.mSetData.UpdateOnce == 0) {
            CanJni.GMQuery(6);
        }
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.GMCarCtrl(cmd, para);
    }

    public CanGMSetCDSView(Activity activity) {
        super(activity, 8);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_cymqbj, R.string.can_qczttz, R.string.can_zdfzzb, R.string.can_gl8_2017_zsyxhzdtx, R.string.can_tslx, R.string.can_qzxrjc, R.string.can_hfcltgjs, R.string.can_cdbhjs};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK};
        this.mPopValueIds[2] = mZdfzzbArr;
        this.mPopValueIds[4] = mTslxArr;
        this.mPopValueIds[5] = mZdfzzbArr;
    }

    public void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            Log.d("CanGMCarInfoActivity", "mSetData.UpdateOnce");
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.CYMQJB);
            updateItem(1, this.mSetData.QCZTTZ);
            updateItem(2, this.mSetData.ZDFFZB);
            updateItem(3, this.mSetData.Zsyxhqdtx);
            updateItem(4, this.mSetData.Tslx);
            updateItem(5, this.mSetData.Qzxrjc);
            updateItem(6, this.mSetData.Hfcltgjs);
            updateItem(7, this.mSetData.Cdbhts);
        }
    }

    public void QueryData() {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GMCarCtrl(22, Neg(this.mSetData.CYMQJB));
                return;
            case 1:
                CanJni.GMCarCtrl(25, Neg(this.mSetData.QCZTTZ));
                return;
            case 3:
                CanJni.GMCarCtrl(82, Neg(this.mSetData.Zsyxhqdtx));
                return;
            case 6:
                CanJni.GMCarCtrl(86, Neg(this.mSetData.Hfcltgjs));
                return;
            case 7:
                CanJni.GMCarCtrl(87, Neg(this.mSetData.Cdbhts));
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 2:
                CanJni.GMCarCtrl(24, item);
                return;
            case 4:
                CanJni.GMCarCtrl(84, item);
                return;
            case 5:
                CanJni.GMCarCtrl(85, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }
}
