package com.ts.can.chery.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanCheryWcCarDrivAssistSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2 {
    private CanDataInfo.CheryWc_SetData mAdtData;
    private CanDataInfo.CheryWc_SetData mSetData;

    public CanCheryWcCarDrivAssistSetView(Activity activity) {
        super(activity, 24);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CarSet(2, item);
                return;
            case 7:
                CarSet(11, item);
                return;
            case 10:
                CanJni.CheryWcLangSet(1, item + 1);
                return;
            case 16:
                CarSet(17, item);
                return;
            case 19:
                CarSet(21, item);
                return;
            case 20:
                CarSet(22, item);
                return;
            case 21:
                CarSet(23, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 8:
                CarSet(7, pos);
                return;
            case 9:
                CarSet(8, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CarSet(1, Neg(this.mSetData.Jjzdbj));
                return;
            case 2:
                CarSet(4, Neg(this.mSetData.Zdls));
                return;
            case 3:
                CarSet(5, Neg(this.mSetData.Qzdys));
                return;
            case 4:
                CarSet(6, Neg(this.mSetData.Rjxcd));
                return;
            case 5:
                CarSet(9, Neg(this.mSetData.ZxqdAVM));
                return;
            case 6:
                CarSet(10, Neg(this.mSetData.Zxqddh));
                return;
            case 11:
                CarSet(12, Neg(this.mSetData.Jykhbx));
                return;
            case 12:
                CarSet(13, Neg(this.mSetData.Zdjs));
                return;
            case 13:
                CarSet(14, Neg(this.mSetData.Whsjzdzd));
                return;
            case 14:
                CarSet(15, Neg(this.mSetData.Mqjc));
                return;
            case 15:
                CarSet(16, Neg(this.mSetData.Cdpl));
                return;
            case 17:
                CarSet(19, Neg(this.mSetData.Sssn));
                return;
            case 18:
                CarSet(20, Neg(this.mSetData.Sstc));
                return;
            case 22:
                CarSet(24, Neg(this.mSetData.Ybgn));
                return;
            case 23:
                new CanItemMsgBox(23, getActivity(), R.string.can_sure_setup, this, this);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_alarm_switch, R.string.can_alarm_way, R.string.can_tigger7_auto_lock, R.string.can_tigger7_light_delay, R.string.can_tigger7_day_light, R.string.can_tigger7_start_avm, R.string.can_tigger7_avm_anim, R.string.can_tigger7_car_line, R.string.can_tigger7_speed_warn, R.string.can_tigger7_behind_light, R.string.can_lang_set, R.string.can_airuiz7_jykkhbx, R.string.can_zdjs, R.string.can_hsjzdzd, R.string.can_mqtc, R.string.can_cdpl, R.string.can_set_tip, R.string.can_sssb, R.string.can_sstc, R.string.can_bwhj, R.string.can_kaiyi_3x_bdsg, R.string.can_eps_mode, R.string.can_yb_func, R.string.can_factory_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[1] = new int[]{R.string.can_only_light, R.string.can_only_lb, R.string.can_dghlb};
        this.mPopValueIds[7] = new int[]{R.array.can_tigger7_car_line_array};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 21;
        iArr2[2] = 1;
        iArr[8] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = 1;
        iArr4[1] = 10;
        iArr4[2] = 1;
        iArr3[9] = iArr4;
        this.mPopValueIds[10] = new int[]{R.string.can_lang_en, R.string.can_lang_cn};
        this.mPopValueIds[16] = new int[]{R.string.can_only_light, R.string.can_only_lb, R.string.can_dghlb};
        this.mPopValueIds[19] = new int[]{R.string.can_trunk_close, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s};
        this.mPopValueIds[20] = new int[]{R.string.can_trunk_close, R.string.can_3c, R.string.can_5c, R.string.can_7c};
        this.mPopValueIds[21] = new int[]{R.string.can_comfort, R.string.can_sport};
        this.mAdtData = new CanDataInfo.CheryWc_SetData();
        this.mSetData = new CanDataInfo.CheryWc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.CheryWcGetSetData(this.mAdtData, 0);
        CanJni.CheryWcGetSetData(this.mSetData, 1);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(new int[]{this.mAdtData.Jjzdbj, this.mAdtData.Jjzdbj, this.mAdtData.Zdls, this.mAdtData.Qzdys, this.mAdtData.Rjxcd, this.mAdtData.ZxqdAVM, this.mAdtData.Zxqddh, this.mAdtData.Xzclfzx, this.mAdtData.Csbj, this.mAdtData.Ybbg, 1, this.mAdtData.Jykhbx, this.mAdtData.Zdjs, this.mAdtData.Whsjzdzd, this.mAdtData.Mqjc, this.mAdtData.Cdpl, this.mAdtData.Sfts, this.mAdtData.Sssn, this.mAdtData.Sstc, this.mAdtData.Bwhj, this.mAdtData.Bdsd, this.mAdtData.Epszlzx, this.mAdtData.Ybgn, 1});
        }
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Jjzdbj, this.mSetData.Jjzdbjfs, this.mSetData.Zdls, this.mSetData.Qzdys, this.mSetData.Rjxcd, this.mSetData.ZxqdAVM, this.mSetData.Zxqddh, this.mSetData.Xzclfzx, this.mSetData.Csbj, this.mSetData.Ybbg, 255, this.mSetData.Jykhbx, this.mSetData.Zdjs, this.mSetData.Whsjzdzd, this.mSetData.Mqjc, this.mSetData.Cdpl, this.mSetData.Sfts, this.mSetData.Sssn, this.mSetData.Sstc, this.mSetData.Bwhj, this.mSetData.Bdsd, this.mSetData.Epszlzx, this.mSetData.Ybgn, 255});
            if (this.mSetData.Csbj != 0) {
                updateItem(8, this.mSetData.Csbj, new StringBuilder(String.valueOf((this.mSetData.Csbj * 5) + 25)).toString());
            }
        }
    }

    private void CarSet(int cmd, int para1) {
        CanJni.CheryWcCarSet(cmd, para1);
    }

    public void QueryData() {
        CanJni.CheryWcQuery(5, 1, 135);
    }

    public void onCancel(int param) {
        if (param == 23) {
            CarSet(18, 0);
        }
    }

    public void onOK(int param) {
        if (param == 23) {
            CarSet(18, 1);
        }
    }
}
