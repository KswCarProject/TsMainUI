package com.ts.can.gm.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMSetConvView extends CanScrollCarInfoView {
    public static final String TAG = "CanGMCarInfoActivity";
    private static final int[] mClzdbcArr = {R.string.can_cxzdzczd, R.string.can_dxzdzczd};
    private static final int[] mMrbDchsjzdqxArr = {R.string.can_off, R.string.can_ckhjsy, R.string.can_jiashiyuan, R.string.can_chengk};
    private static final int[] mPdqbfzArr = {R.string.can_bzzd, R.string.can_gjzd};
    private static final int[] mZsyqzdArr = {R.string.can_mzd_cx4_mode_off, R.string.can_jlhwdzm, R.string.can_zndgfp};
    private CanDataInfo.GM_AdtConv mAdtConvData = new CanDataInfo.GM_AdtConv();
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

    public CanGMSetConvView(Activity activity) {
        super(activity, 15);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_dcyhszdqd, R.string.can_personalization, R.string.can_dyjywz, R.string.can_jsyblxc, R.string.can_zdhsjzd, R.string.can_zdys, R.string.can_rear_tx, R.string.can_dchsjzdfz, R.string.can_jsyzyzdhw, R.string.can_jsyyszdsb, R.string.can_sport_turn, R.string.can_dchsjzdqx, R.string.can_teramont_pdqbfz, R.string.can_clzdbc, R.string.can_adt_front_light};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[11] = mMrbDchsjzdqxArr;
        this.mPopValueIds[12] = mPdqbfzArr;
        this.mPopValueIds[13] = mClzdbcArr;
        this.mPopValueIds[14] = mZsyqzdArr;
    }

    public void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.DCYHSZDQD);
            updateItem(1, this.mSetData.PersonByDriver);
            updateItem(2, this.mSetData.AutoMemRecall);
            updateItem(3, this.mSetData.EasyExitSeat);
            updateItem(4, this.mSetData.AutoMirrorFold);
            updateItem(5, this.mSetData.ZDYX);
            updateItem(6, this.mSetData.RearTx);
            updateItem(7, this.mSetData.Dchsjzdfz);
            updateItem(8, this.mSetData.Jsyzyzdhw);
            updateItem(9, this.mSetData.Zsjyszdsb);
            updateItem(10, this.mSetData.Ydzx);
            updateItem(11, this.mSetData.RevTiltMirror);
            updateItem(12, this.mSetData.Pdqbfz);
            updateItem(13, this.mSetData.Clzdbc);
            updateItem(14, this.mSetData.Zsyqzd);
        }
    }

    public void QueryData() {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GMCarCtrl(9, Neg(this.mSetData.DCYHSZDQD));
                return;
            case 1:
                CanJni.GMCarCtrl(14, Neg(this.mSetData.PersonByDriver));
                return;
            case 2:
                CanJni.GMCarCtrl(88, Neg(this.mSetData.AutoMemRecall));
                return;
            case 3:
                CanJni.GMCarCtrl(89, Neg(this.mSetData.EasyExitSeat));
                return;
            case 4:
                CanJni.GMCarCtrl(91, Neg(this.mSetData.AutoMirrorFold));
                return;
            case 5:
                CanJni.GMCarCtrl(26, Neg(this.mSetData.ZDYX));
                return;
            case 6:
                CanJni.GMCarCtrl(83, Neg(this.mSetData.RearTx));
                return;
            case 7:
                CanJni.GMCarCtrl(19, Neg(this.mSetData.Dchsjzdfz));
                return;
            case 8:
                CanJni.GMCarCtrl(20, Neg(this.mSetData.Jsyzyzdhw));
                return;
            case 9:
                CanJni.GMCarCtrl(21, Neg(this.mSetData.Zsjyszdsb));
                return;
            case 10:
                CanJni.GMCarCtrl(93, Neg(this.mSetData.Ydzx));
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 11:
                CanJni.GMCarCtrl(90, item);
                return;
            case 12:
                CanJni.GMCarCtrl(81, item);
                return;
            case 13:
                CanJni.GMCarCtrl(92, item);
                return;
            case 14:
                CanJni.GMCarCtrl(94, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }
}
