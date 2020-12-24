package com.ts.can.chrysler.rz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanRZygSetXnkzxtView extends CanScrollCarInfoView {
    public static final int ITEM_CURENGINE = 9;
    public static final int ITEM_CURMODE = 2;
    public static final int ITEM_CURPADDLE = 10;
    public static final int ITEM_CURSTEERING = 12;
    public static final int ITEM_CURTRACTION = 11;
    public static final int ITEM_ENGINE = 4;
    public static final int ITEM_MAX = 13;
    public static final int ITEM_PADDLE = 5;
    public static final int ITEM_QDQPKZ = 3;
    public static final int ITEM_QPKZ = 1;
    public static final int ITEM_STEERING = 7;
    public static final int ITEM_TITLE_CUR = 8;
    public static final int ITEM_TITLE_JSFZ = 0;
    public static final int ITEM_TRACTION = 6;
    private CanDataInfo.ChrOthAdt mSetAdt;
    private CanDataInfo.ChrOthXnkzInfo mSetData;

    public CanRZygSetXnkzxtView(Activity activity) {
        super(activity, 13);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.ChrOthCarSet(176, item);
                return;
            case 2:
                CanJni.ChrOthCarSet(182, Neg(item));
                return;
            case 4:
                CanJni.ChrOthCarSet(178, item);
                return;
            case 6:
                CanJni.ChrOthCarSet(180, item);
                return;
            case 7:
                CanJni.ChrOthCarSet(181, item);
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
                CanJni.ChrOthCarSet(177, 0);
                return;
            case 5:
                CanJni.ChrOthCarSet(179, Neg(this.mSetData.Paddle));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_jsfzxysz, R.string.can_launchctl, R.string.can_curmode, R.string.can_qdlaunchctl, R.string.can_enginetrans, R.string.can_paddle, R.string.can_traction, R.string.can_steering, R.string.can_curmodestatus, R.string.can_enginetrans, R.string.can_paddle, R.string.can_traction, R.string.can_steering};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mPopValueIds[1] = new int[]{R.array.can_launch_control_array};
        this.mPopValueIds[2] = new int[]{R.string.can_normal_mode, R.string.can_sport_mode};
        this.mPopValueIds[4] = new int[]{R.string.can_mode_normal, R.string.can_sport};
        this.mPopValueIds[6] = new int[]{R.string.can_mode_normal, R.string.can_sport};
        this.mPopValueIds[7] = new int[]{R.string.can_mode_normal, R.string.can_sport, R.string.can_comfort};
        this.mPopValueIds[9] = new int[]{R.string.can_mode_normal, R.string.can_sport};
        this.mPopValueIds[10] = new int[]{R.string.can_off, R.string.can_on};
        this.mPopValueIds[11] = new int[]{R.string.can_mode_normal, R.string.can_sport};
        this.mPopValueIds[12] = new int[]{R.string.can_mode_normal, R.string.can_sport, R.string.can_comfort};
        this.mSetAdt = new CanDataInfo.ChrOthAdt();
        this.mSetData = new CanDataInfo.ChrOthXnkzInfo();
    }

    public void ResetData(boolean check) {
        CanJni.RZChrOthGetPerformanceControlData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(1, this.mSetData.LaunchCtl);
            updateItem(2, this.mSetData.CurrentMode);
            updateItem(4, this.mSetData.EngineTrans);
            updateItem(5, this.mSetData.Paddle);
            updateItem(6, this.mSetData.Traction);
            updateItem(7, this.mSetData.Steering);
            updateItem(9, this.mSetData.CurEngineTrans, getString(this.mPopValueIds[9][this.mSetData.CurEngineTrans]));
            updateItem(10, this.mSetData.CurPaddle, getString(this.mPopValueIds[10][this.mSetData.CurPaddle]));
            updateItem(11, this.mSetData.CurTraction, getString(this.mPopValueIds[11][this.mSetData.CurTraction]));
            updateItem(12, this.mSetData.CurSteering, getString(this.mPopValueIds[12][this.mSetData.CurSteering]));
        }
    }

    public void QueryData() {
        CanJni.ChrOthQuery(50, 0, 0, 0);
    }
}
