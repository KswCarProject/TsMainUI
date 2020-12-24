package com.ts.can.byd.wc;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBydWcCarInfoView extends CanScrollCarInfoView {
    private static Toast mToast;
    private CanDataInfo.BydWcSetData mSetAdt;
    private CanDataInfo.BydWcSetData mSetData;
    private CanDataInfo.BydWcTsData mTsAdt;
    private CanDataInfo.BydWcTsData mTsData;

    public CanBydWcCarInfoView(Activity activity) {
        super(activity, 12);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.BydWcCarSet(16, item, 255, 255);
                return;
            case 1:
                CanJni.BydWcCarSet(17, item, 255, 255);
                return;
            case 2:
                CanJni.BydWcCarSet(18, item, 255, 255);
                return;
            case 10:
                CanJni.BydWcCarSet(49, item, 255, 255);
                return;
            case 11:
                CanJni.BydWcCarSet(50, item, 255, 255);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                CanJni.BydWcCarSet(19, Neg(this.mSetData.Zczdnxh), 255, 255);
                return;
            case 4:
                CanJni.BydWcCarSet(20, Neg(this.mSetData.Lythzdjfl), 255, 255);
                return;
            case 5:
                CanJni.BydWcCarSet(32, Neg(this.mSetData.Cawdkgjsjc), 255, 255);
                return;
            case 6:
                CanJni.BydWcCarSet(33, Neg(this.mSetData.Cawdkgbssc), 255, 255);
                return;
            case 7:
                CanJni.BydWcCarSet(34, Neg(this.mSetData.Ykjc), 255, 255);
                return;
            case 8:
                CanJni.BydWcCarSet(35, Neg(this.mSetData.Whsjzdsd), 255, 255);
                return;
            case 9:
                CanJni.BydWcCarSet(48, Neg(this.mSetData.Cdkfdsz), 255, 255);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_auto_wind, R.string.can_auto_ac, R.string.can_pm_25, R.string.can_auto_zhuche, R.string.can_lythjfs, R.string.can_cawdkgjsjc, R.string.can_cawdkgbssc, R.string.can_carset_ykjc, R.string.can_whsjzdsd, R.string.can_cdkfdsz, R.string.can_nlhkms, R.string.can_power_steer};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_yybjxtyl_1, R.string.can_air_medium, R.string.can_yybjxtyl_2};
        this.mPopValueIds[1] = new int[]{R.string.can_eco, R.string.can_mode_ss};
        this.mPopValueIds[2] = new int[]{R.array.can_pm25_check_array};
        this.mPopValueIds[10] = new int[]{R.string.can_mode_normal, R.string.can_yybjxtyl_2};
        this.mPopValueIds[11] = new int[]{R.string.can_comfort, R.string.can_sport};
        this.mItemVisibles = new int[12];
        this.mSetAdt = new CanDataInfo.BydWcSetData();
        this.mSetData = new CanDataInfo.BydWcSetData();
    }

    public void ResetData(boolean check) {
        CanJni.BydWcGetCarSetData(this.mSetAdt, 0);
        if (i2b(this.mSetAdt.UpdateOnce) && (!check || i2b(this.mSetAdt.Update))) {
            this.mSetAdt.Update = 0;
            showItem(new int[]{this.mSetAdt.Zdflmssd, this.mSetAdt.Ktzdmssd, this.mSetAdt.Pm25jc, this.mSetAdt.Zczdnxh, this.mSetAdt.Lythzdjfl, this.mSetAdt.Cawdkgjsjc, this.mSetAdt.Cawdkgbssc, this.mSetAdt.Ykjc, this.mSetAdt.Whsjzdsd, this.mSetAdt.Cdkfdsz, this.mSetAdt.Nlhkms, this.mSetAdt.Zxzlms});
        }
        CanJni.BydWcGetCarSetData(this.mSetData, 1);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Zdflmssd, this.mSetData.Ktzdmssd, this.mSetData.Pm25jc, this.mSetData.Zczdnxh, this.mSetData.Lythzdjfl, this.mSetData.Cawdkgjsjc, this.mSetData.Cawdkgbssc, this.mSetData.Ykjc, this.mSetData.Whsjzdsd, this.mSetData.Cdkfdsz, this.mSetData.Nlhkms, this.mSetData.Zxzlms});
        }
    }

    public void QueryData() {
        CanJni.BydWcQuery(5, 1, 97);
    }

    public static void ShowWarnToast(Context context) {
        CanDataInfo.BydWcTsData setData = new CanDataInfo.BydWcTsData();
        CanJni.BydWcGetCarTsData(setData, 1);
        if (setData.Wtcdystsy != 0 || setData.Fpdlcjgtsy != 0 || setData.Aqdtsy != 0 || setData.Xccmwgtsy != 0) {
            String text = "";
            if (setData.WtcdystsyUpdate == 1) {
                setData.WtcdystsyUpdate = 0;
                if (setData.Wtcdystsy == 1) {
                    text = context.getString(R.string.can_check_key_exist);
                }
            } else if (setData.FpdlcjgtsyUpdate == 1) {
                setData.FpdlcjgtsyUpdate = 0;
                if (setData.Fpdlcjgtsy == 1) {
                    text = context.getString(R.string.can_choose_p_area);
                }
            } else if (setData.AqdtsyUpdate == 1) {
                setData.AqdtsyUpdate = 0;
                if (setData.Aqdtsy == 1) {
                    text = context.getString(R.string.can_check_safe_tie);
                }
            } else if (setData.XccmwgtsyUpdate == 1) {
                setData.XccmwgtsyUpdate = 0;
                if (setData.Xccmwgtsy == 1) {
                    text = context.getString(R.string.can_close_door);
                }
            } else {
                return;
            }
            if (mToast == null) {
                mToast = Toast.makeText(context, "", 1);
            }
            mToast.setText(text);
            mToast.show();
        }
    }
}
