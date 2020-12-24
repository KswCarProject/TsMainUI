package com.ts.can.hant.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHantElectCarKeyLockSetView extends CanScrollCarInfoView {
    private static int[] BJQ_NUMS = {R.string.can_hant_bjq_gb, R.string.can_hant_bjq_ddkq, R.string.can_hant_bjq_xckq, R.string.can_hant_bjq_xys};
    private static final int HANT_BJQ = 4;
    private static final int HANT_HBXS = 1;
    private static final int HANT_YHDDC = 8;
    private static final int HANT_YQDDC = 7;
    private static final int HANT_YZXD = 3;
    private static final int HANT_ZDDC = 5;
    private static final int HANT_ZHDDC = 6;
    private static final int HANT_ZKS = 0;
    private static final int HANT_ZZXD = 2;
    private CanDataInfo.HanTang_LockWin mLockData;

    public CanHantElectCarKeyLockSetView(Activity activity) {
        super(activity, 9);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 5:
                CanJni.HanTangElectCarWinLockSet(2, getValue(item));
                return;
            case 6:
                CanJni.HanTangElectCarWinLockSet(3, getValue(item));
                return;
            case 7:
                CanJni.HanTangElectCarWinLockSet(4, getValue(item));
                return;
            case 8:
                CanJni.HanTangElectCarWinLockSet(5, getValue(item));
                return;
            default:
                return;
        }
    }

    private int getValue(int item) {
        switch (item) {
            case 0:
                return 4;
            case 1:
                return 5;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return 0;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.HanTangElectCarWinLockSet(6, Neg(this.mLockData.Zks) + 1);
                return;
            case 1:
                CanJni.HanTangElectCarWinLockSet(1, Neg(this.mLockData.Hbxs) + 1);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        showItem(2, 0);
        showItem(3, 0);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mLockData = new CanDataInfo.HanTang_LockWin();
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mItemTitleIds = new int[]{R.string.can_hant_zks, R.string.can_hant_hbxs, R.string.can_ill_zzxd, R.string.can_ill_yzxd, R.string.can_hant_bjq, R.string.can_hant_zddc, R.string.can_hant_zhddc, R.string.can_hant_yqddc, R.string.can_hant_yhddc};
        this.mPopValueIds[5] = new int[]{R.string.can_hant_window_uped, R.string.can_hant_window_dned, R.string.can_hant_window_up, R.string.can_hant_window_dn, R.string.can_hant_window_stop};
        this.mPopValueIds[6] = this.mPopValueIds[5];
        this.mPopValueIds[7] = this.mPopValueIds[5];
        this.mPopValueIds[8] = this.mPopValueIds[5];
    }

    public void ResetData(boolean check) {
        CanJni.HanTangElectCarGetLockWinData(this.mLockData);
        if (!i2b(this.mLockData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLockData.Update)) {
            this.mLockData.Update = 0;
            updateItem(new int[]{this.mLockData.Zks, this.mLockData.Hbxs, this.mLockData.Zzxd, this.mLockData.Rzxd, this.mLockData.Bjq, this.mLockData.Zddc, this.mLockData.Zhddc, this.mLockData.Rqddc, this.mLockData.Rhddc});
            updateItem(4, this.mLockData.Bjq, getActivity().getResources().getString(BJQ_NUMS[this.mLockData.Bjq]));
        }
    }

    public void QueryData() {
        CanJni.HanTangElectCarQuery(37);
    }
}
