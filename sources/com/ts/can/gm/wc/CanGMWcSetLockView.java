package com.ts.can.gm.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMWcSetLockView extends CanScrollCarInfoView {
    private CanDataInfo.GmWc_DoorLock mLockAdt;
    private CanDataInfo.GmWc_DoorLock mLockData;
    private CanDataInfo.GmWc_RemoteData mRemoteAdt;
    private CanDataInfo.GmWc_RemoteData mRemoteData;

    public CanGMWcSetLockView(Activity activity) {
        super(activity, 18);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.GmWcCarDoorLockSet(3, item);
                return;
            case 5:
                CanJni.GmWcCarDoorLockSet(5, item);
                return;
            case 6:
                CanJni.GmWcCarRemoteSet(1, item);
                return;
            case 8:
                CanJni.GmWcCarRemoteSet(3, item);
                return;
            case 13:
                CanJni.GmWcCarRemoteSet(8, item);
                return;
            case 15:
                CanJni.GmWcCarRemoteSet(10, item);
                return;
            case 16:
                CanJni.GmWcCarRemoteSet(11, item);
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
                CanJni.GmWcCarDoorLockSet(1, Neg(this.mLockData.Fzkmzdls));
                return;
            case 1:
                CanJni.GmWcCarDoorLockSet(2, Neg(this.mLockData.Zdls));
                return;
            case 3:
                CanJni.GmWcCarDoorLockSet(4, Neg(this.mLockData.Ycls));
                return;
            case 4:
                CanJni.GmWcCarDoorLockSet(6, Neg(this.mLockData.Fzfs));
                return;
            case 7:
                CanJni.GmWcCarRemoteSet(2, Neg(this.mRemoteData.Ykjsfk));
                return;
            case 9:
                CanJni.GmWcCarRemoteSet(4, Neg(this.mRemoteData.Ycjscmzdcs));
                return;
            case 10:
                CanJni.GmWcCarRemoteSet(5, Neg(this.mRemoteData.Csykdkdm));
                return;
            case 11:
                CanJni.GmWcCarRemoteSet(6, Neg(this.mRemoteData.Jsyyszdsb));
                return;
            case 12:
                CanJni.GmWcCarRemoteSet(7, Neg(this.mRemoteData.Ycqd));
                return;
            case 14:
                CanJni.GmWcCarRemoteSet(9, Neg(this.mRemoteData.Ysywts));
                return;
            case 17:
                CanJni.GmWcCarRemoteSet(12, Neg(this.mRemoteData.Ykcckz));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_fzkmzdll, R.string.can_qbzzll, R.string.can_auto_unlock_auto, R.string.can_ysll, R.string.can_fzfs, R.string.can_auto_unlock_manul, R.string.can_ykdglbfk, R.string.can_ykjsdgfk, R.string.can_ykjssz, R.string.can_ycjscmzdcs, R.string.can_cxszdkdm, R.string.can_jsyyszdsb, R.string.can_ykqdcl, R.string.can_smart_near_lock, R.string.can_ysywtx, R.string.can_lczdls, R.string.can_ykhymsz, R.string.can_ykcckz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[2] = new int[]{R.string.can_off, R.string.can_auto_unlock_auto_1, R.string.can_auto_unlock_auto_2};
        this.mPopValueIds[5] = new int[]{R.string.can_off, R.string.can_auto_unlock_manul_1, R.string.can_auto_unlock_manul_2};
        this.mPopValueIds[6] = new int[]{R.string.can_only_light, R.string.can_dghlb, R.string.can_only_lb, R.string.can_off};
        this.mPopValueIds[8] = new int[]{R.string.can_jsym, R.string.can_sym};
        this.mPopValueIds[13] = new int[]{R.string.can_jsym, R.string.can_sym};
        this.mPopValueIds[15] = new int[]{R.string.can_off, R.string.can_on, R.string.can_lbwjsqy};
        this.mPopValueIds[16] = new int[]{R.string.can_ykhymsz_1, R.string.can_ykhymsz_2};
        for (int i = 0; i < this.mItemVisibles.length; i++) {
            this.mItemVisibles[i] = 0;
        }
        this.mLockAdt = new CanDataInfo.GmWc_DoorLock();
        this.mLockData = new CanDataInfo.GmWc_DoorLock();
        this.mRemoteAdt = new CanDataInfo.GmWc_RemoteData();
        this.mRemoteData = new CanDataInfo.GmWc_RemoteData();
    }

    public void ResetData(boolean check) {
        CanJni.GmWcGetCarDoorLockSetAdt(this.mLockAdt);
        CanJni.GmWcGetCarDoorLockSet(this.mLockData);
        if (i2b(this.mLockAdt.UpdateOnce) && (!check || i2b(this.mLockAdt.Update))) {
            this.mLockAdt.Update = 0;
            showItem(new int[]{this.mLockAdt.Fzkmzdls, this.mLockAdt.Zdls, this.mLockAdt.ZdjsZdd, this.mLockAdt.Ycls, this.mLockAdt.Fzfs, this.mLockAdt.ZdjsSdd});
        }
        if (i2b(this.mLockData.UpdateOnce) && (!check || i2b(this.mLockData.Update))) {
            this.mLockData.Update = 0;
            updateItem(new int[]{this.mLockData.Fzkmzdls, this.mLockData.Zdls, this.mLockData.ZdjsZdd, this.mLockData.Ycls, this.mLockData.Fzfs, this.mLockData.ZdjsSdd});
        }
        CanJni.GmWcGetCarRemoteSetAdt(this.mRemoteAdt);
        CanJni.GmWcGetCarRemoteSet(this.mRemoteData);
        if (i2b(this.mRemoteAdt.UpdateOnce) && (!check || i2b(this.mRemoteAdt.Update))) {
            this.mRemoteAdt.Update = 0;
            int[] values = {this.mRemoteAdt.Yklsfk, this.mRemoteAdt.Ykjsfk, this.mRemoteAdt.Ykjs, this.mRemoteAdt.Ycjscmzdcs, this.mRemoteAdt.Csykdkdm, this.mRemoteAdt.Jsyyszdsb, this.mRemoteAdt.Ycqd, this.mRemoteAdt.Znjcjs, this.mRemoteAdt.Ysywts, this.mRemoteAdt.Znlcls, this.mRemoteAdt.Ykhym, this.mRemoteAdt.Ykcckz};
            for (int i = 0; i < values.length; i++) {
                showItem(i + 6, values[i]);
            }
        }
        if (!i2b(this.mRemoteData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mRemoteData.Update)) {
            this.mRemoteData.Update = 0;
            int[] values2 = {this.mRemoteData.Yklsfk, this.mRemoteData.Ykjsfk, this.mRemoteData.Ykjs, this.mRemoteData.Ycjscmzdcs, this.mRemoteData.Csykdkdm, this.mRemoteData.Jsyyszdsb, this.mRemoteData.Ycqd, this.mRemoteData.Znjcjs, this.mRemoteData.Ysywts, this.mRemoteData.Znlcls, this.mRemoteData.Ykhym, this.mRemoteData.Ykcckz};
            for (int i2 = 0; i2 < values2.length; i2++) {
                updateItem(i2 + 6, values2[i2]);
            }
        }
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 101);
        Sleep(5);
        CanJni.GmWcCarQuery(5, 1, 102);
    }
}
