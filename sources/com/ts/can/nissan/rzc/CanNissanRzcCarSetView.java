package com.ts.can.nissan.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanNissanRzcCarSetView extends CanScrollCarInfoView {
    private static final int ITEM_ALL_DEFAULT = 9;
    private static final int ITEM_BLIND_SPOT_DETECTION = 1;
    private static final int ITEM_CSLDJXSYGQ = 5;
    private static final int ITEM_LANE_DEPARTURE_DETECTION = 0;
    private static final int ITEM_MOVINF_OBJECT_DETECTION = 2;
    private static final int ITEM_MSJC_CNDZDDK = 3;
    private static final int ITEM_XCSJSZZDHT = 8;
    private static final int ITEM_ZDDDLMDTJ = 4;
    private static final int ITEM_ZDDGDSJSZ = 6;
    private static final int ITEM_ZNYSKSGN = 7;
    private CanDataInfo.CanTeanaJukeData mSetData;

    public CanNissanRzcCarSetView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.NissanCarSet(85, item);
                return;
            case 6:
                CanJni.NissanCarSet(87, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.NissanCarSet(82, 1);
        } else if (id == 1) {
            CanJni.NissanCarSet(83, 1);
        } else if (id == 2) {
            CanJni.NissanCarSet(81, 1);
        } else if (id == 3) {
            CanJni.NissanCarSet(84, Neg(this.mSetData.MsjsCndzddk));
        } else if (id == 5) {
            CanJni.NissanCarSet(86, Neg(this.mSetData.Csldjxsygq));
        } else if (id == 7) {
            CanJni.NissanCarSet(88, Neg(this.mSetData.Znysksgn));
        } else if (id == 8) {
            CanJni.NissanCarSet(89, Neg(this.mSetData.Xcsjszzdht));
        } else if (id == 9) {
            new CanItemMsgBox(id, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.NissanCarSet(90, 1);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_juke_Lanedeparture, R.string.can_juke_blindspotdetect, R.string.can_juke_movingobjdectect, R.string.can_msjccndzddk, R.string.can_zddlmd, R.string.can_csldjxsysq, R.string.can_zdgdsjsz, R.string.can_znysksgn, R.string.can_xcsjszzdht, R.string.can_hfcsz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.DATE};
        this.mSetData = new CanDataInfo.CanTeanaJukeData();
        this.mPopValueIds[4] = new int[]{R.string.can_digital_num1, R.string.can_digital_num2, R.string.can_digital_num3, R.string.can_digital_num4};
        this.mPopValueIds[6] = new int[]{R.string.can_0s, R.string.can_mzd_cx4_time_30s, R.string.can_time_45s, R.string.can_60s, R.string.can_90s, R.string.can_mzd_cx4_time_120s, R.string.can_mzd_cx4_time_150s, R.string.can_mzd_cx4_time_180s};
        if (CanJni.GetSubType() != 4 && CanJni.GetSubType() != 7) {
            this.mItemVisibles[3] = 0;
            this.mItemVisibles[4] = 0;
            this.mItemVisibles[5] = 0;
            this.mItemVisibles[6] = 0;
            this.mItemVisibles[7] = 0;
            this.mItemVisibles[8] = 0;
            this.mItemVisibles[9] = 0;
        }
    }

    public void ResetData(boolean check) {
        CanJni.NissanGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.LaneDepartDetect);
            updateItem(1, this.mSetData.BlindSpotDetect);
            updateItem(2, this.mSetData.MovObjDetect);
            updateItem(3, this.mSetData.MsjsCndzddk);
            updateItem(4, this.mSetData.Zddlmdtj);
            updateItem(5, this.mSetData.Csldjxsygq);
            updateItem(6, this.mSetData.Zddgdsssz);
            updateItem(7, this.mSetData.Znysksgn);
            updateItem(8, this.mSetData.Xcsjszzdht);
        }
    }

    public void QueryData() {
        CanJni.NissanQuery(149, 0);
    }
}
