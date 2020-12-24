package com.ts.can.gm.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMSetACView extends CanScrollCarInfoView {
    public static final String TAG = "CanGMCarInfoActivity";
    private static final int[] mFqwdlArr = {R.string.can_ty_set, R.string.can_fq_set, R.string.can_sc_set};
    private static final int[] mHqktqdArrays = {R.string.can_gl8_2017_hqktqd_close, R.string.can_gl8_2017_hqktqd_same, R.string.can_gl8_2017_hqktqd_last};
    private static final int[] mKqzlcgqlArr = {R.string.can_off, R.string.can_ac_lo_sens, R.string.can_ac_hi_sens};
    private static final int[] mKtycqdArrays = {R.string.can_gl8_2017_ktycqd_auto, R.string.can_gl8_2017_ktycqd_last};
    private static final int[] mQdmsArr = {R.string.can_off, R.string.can_on, R.string.can_sc_set};
    private static final int[] mZdmsflArr = {R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
    private static final int[] mZyjrArr = {R.string.can_off, R.string.can_ckhjsy, R.string.can_jiashiyuan};
    private CanDataInfo.GM_ACSet mACSetData = new CanDataInfo.GM_ACSet();
    private boolean mbLayout;

    public CanGMSetACView(Activity activity) {
        super(activity, 12);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ac_qccw, R.string.can_ac_hccw, R.string.can_ac_zyjr, R.string.can_ac_qdms, R.string.can_ac_zdfl, R.string.can_ac_cgq, R.string.can_ac_fqwd, R.string.can_gl8_2017_ycqdzyjr, R.string.can_gl8_2017_ycqdzycf, R.string.can_gl8_2017_ktycqd, R.string.can_gl8_2017_hqktqd, R.string.can_auto_hot};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK};
        this.mPopValueIds[3] = mQdmsArr;
        this.mPopValueIds[4] = mZdmsflArr;
        this.mPopValueIds[5] = mKqzlcgqlArr;
        this.mPopValueIds[6] = mFqwdlArr;
        this.mPopValueIds[9] = mKtycqdArrays;
        this.mPopValueIds[10] = mHqktqdArrays;
    }

    public void ResetData(boolean check) {
        CanJni.GMGetACSet(this.mACSetData);
        if (!i2b(this.mACSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mACSetData.Update)) {
            this.mACSetData.Update = 0;
            updateItem(0, this.mACSetData.QCZDCW);
            updateItem(1, this.mACSetData.HCZDCW);
            updateItem(2, this.mACSetData.YKZYJR);
            updateItem(3, this.mACSetData.QDMS);
            updateItem(4, this.mACSetData.AutoMode);
            updateItem(5, this.mACSetData.KQZLLMD);
            updateItem(6, this.mACSetData.FQWD);
            updateItem(7, this.mACSetData.YCQDZYJR);
            updateItem(8, this.mACSetData.YCQDZYCF);
            updateItem(9, this.mACSetData.KTYCQD);
            updateItem(10, this.mACSetData.HQKTQD);
            updateItem(11, this.mACSetData.Zdzyjr);
        }
    }

    public void QueryData() {
        if (!i2b(this.mACSetData.UpdateOnce)) {
            CanJni.GMQuery(5);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GMACCtrl(4, Neg(this.mACSetData.QCZDCW));
                return;
            case 1:
                CanJni.GMACCtrl(3, Neg(this.mACSetData.HCZDCW));
                return;
            case 2:
                CanJni.GMACCtrl(5, Neg(this.mACSetData.YKZYJR));
                return;
            case 7:
                CanJni.GMACCtrl(65, Neg(this.mACSetData.YCQDZYJR));
                return;
            case 8:
                CanJni.GMACCtrl(66, Neg(this.mACSetData.YCQDZYCF));
                return;
            case 11:
                CanJni.GMACCtrl(8, Neg(this.mACSetData.Zdzyjr));
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 3:
                CanJni.GMACCtrl(6, item);
                return;
            case 4:
                CanJni.GMACCtrl(0, item);
                return;
            case 5:
                CanJni.GMACCtrl(1, item);
                return;
            case 6:
                CanJni.GMACCtrl(2, item);
                return;
            case 9:
                CanJni.GMACCtrl(67, item);
                return;
            case 10:
                CanJni.GMACCtrl(68, item);
                return;
            default:
                return;
        }
    }
}
