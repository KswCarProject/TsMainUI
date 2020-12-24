package com.ts.can.chrysler.rz;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanRZygPanControlView extends CanScrollCarInfoView {
    public static final int ITEM_AUTO_START_STOP = 6;
    public static final int ITEM_CDPL = 1;
    public static final int ITEM_ECO = 5;
    public static final int ITEM_FH = 3;
    public static final int ITEM_FPZ = 0;
    public static final int ITEM_FXPBC = 2;
    public static final int ITEM_MAX = 8;
    public static final int ITEM_RADAR_SW = 7;
    public static final int ITEM_SPORT = 4;
    private CanDataInfo.ChrOthSetData3 mSetData;

    public CanRZygPanControlView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                switch (Id) {
                    case 0:
                        CanJni.ChOthRZCarSet3(8, 0);
                        break;
                    case 1:
                        CanJni.ChOthRZCarSet3(7, 0);
                        break;
                    case 2:
                        CanJni.ChOthRZCarSet3(6, 0);
                        break;
                    case 3:
                        CanJni.ChOthRZCarSet3(5, 0);
                        break;
                    case 4:
                        CanJni.ChOthRZCarSet3(4, 0);
                        break;
                    case 5:
                        CanJni.ChOthRZCarSet3(3, 0);
                        break;
                    case 6:
                        CanJni.ChOthRZCarSet3(2, 0);
                        break;
                    case 7:
                        CanJni.ChOthRZCarSet3(1, 0);
                        break;
                }
            }
        } else {
            switch (Id) {
                case 0:
                    CanJni.ChOthRZCarSet3(8, 1);
                    break;
                case 1:
                    CanJni.ChOthRZCarSet3(7, 1);
                    break;
                case 2:
                    CanJni.ChOthRZCarSet3(6, 1);
                    break;
                case 3:
                    CanJni.ChOthRZCarSet3(5, 1);
                    break;
                case 4:
                    CanJni.ChOthRZCarSet3(4, 1);
                    break;
                case 5:
                    CanJni.ChOthRZCarSet3(3, 1);
                    break;
                case 6:
                    CanJni.ChOthRZCarSet3(2, 1);
                    break;
                case 7:
                    CanJni.ChOthRZCarSet3(1, 1);
                    break;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_pzjc, R.string.can_cdpl, R.string.can_lane_assist, R.string.can_non_slip, R.string.can_sport_mode, R.string.can_enter_eco_mode, R.string.can_auto_start_stop, R.string.title_activity_can_radar};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH_TOUCH, CanScrollCarInfoView.Item.SWITCH_TOUCH, CanScrollCarInfoView.Item.SWITCH_TOUCH, CanScrollCarInfoView.Item.SWITCH_TOUCH, CanScrollCarInfoView.Item.SWITCH_TOUCH, CanScrollCarInfoView.Item.SWITCH_TOUCH, CanScrollCarInfoView.Item.SWITCH_TOUCH, CanScrollCarInfoView.Item.SWITCH_TOUCH};
        this.mItemIcons = new int[]{R.drawable.can_icon_pm, R.drawable.can_icon_driver_assist, R.drawable.can_icon_tpms, R.drawable.can_icon_esc, R.drawable.can_icon_sport, R.drawable.can_icon_units, R.drawable.can_icon_light2, R.drawable.can_icon_off};
        this.mSetData = new CanDataInfo.ChrOthSetData3();
    }

    public void ResetData(boolean check) {
        CanJni.ChOthRZGetSetData3(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Fpz, this.mSetData.Cdpl, this.mSetData.Fxpbc, this.mSetData.Fh, this.mSetData.Sport, this.mSetData.Eco, this.mSetData.AutoStartStop, this.mSetData.RadarSw});
        }
    }

    public void QueryData() {
        CanJni.ChrOthQuery(25, 0, 0, 0);
    }
}
