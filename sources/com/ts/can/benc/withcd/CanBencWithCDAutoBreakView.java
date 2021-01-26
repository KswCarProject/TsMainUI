package com.ts.can.benc.withcd;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBencWithCDAutoBreakView extends CanScrollCarInfoView {
    private static int[] Values = new int[4];
    private CanDataInfo.CanBcZmytDevZdsc mDevZdsc;

    public CanBencWithCDAutoBreakView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        Values[id - 3] = pos;
        updateProgress();
        CanJni.BencZmytDevCmd(1, 1, this.mDevZdsc.Sta, Values[0], Values[1], Values[2], Values[3], 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    private int SwValue(int val) {
        return val == 0 ? 1 : 0;
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 0) {
            CanJni.BencZmytDevCmd(1, 1, SwValue(this.mDevZdsc.Sta), Values[0], Values[1], Values[2], Values[3], 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        int i;
        this.mItemTitleIds = new int[]{R.string.can_auto_break_sta, R.string.can_auto_break_speed, R.string.can_auto_break_aqd, R.string.can_auto_break_speed_stop_title, R.string.can_auto_break_speed_start, R.string.can_auto_break_rear_radar, R.string.can_auto_break_front_radar};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS};
        this.mProgressAttrs[3] = new int[]{10, 20, 1, 1};
        this.mProgressAttrs[4] = new int[]{5, 15, 1, 1};
        this.mProgressAttrs[5] = new int[]{2, 9, 1, 1};
        this.mProgressAttrs[6] = new int[]{2, 9, 1, 1};
        this.mDevZdsc = new CanDataInfo.CanBcZmytDevZdsc();
        Values[0] = CanBencWithCDSoSync.GetValue(getActivity(), CanBencWithCDSoSync.KEY_SEAT_BREAK_SPEED_STOP);
        Values[1] = CanBencWithCDSoSync.GetValue(getActivity(), CanBencWithCDSoSync.KEY_SEAT_BREAK_SPEED_START);
        Values[2] = CanBencWithCDSoSync.GetValue(getActivity(), CanBencWithCDSoSync.KEY_SEAT_BREAK_REAR_RADAR);
        Values[3] = CanBencWithCDSoSync.GetValue(getActivity(), CanBencWithCDSoSync.KEY_SEAT_BREAK_FRONT_RADAR);
        Values[0] = Values[0] == 0 ? 15 : Values[0];
        Values[1] = Values[1] == 0 ? 10 : Values[1];
        Values[2] = Values[2] == 0 ? 3 : Values[2];
        int[] iArr = Values;
        if (Values[3] == 0) {
            i = 3;
        } else {
            i = Values[3];
        }
        iArr[3] = i;
    }

    public void ResetData(boolean check) {
        CanJni.BencZmytWithCDGetDevZdsc(this.mDevZdsc);
        if (i2b(this.mDevZdsc.UpdateOnce) && (!check || i2b(this.mDevZdsc.Update))) {
            this.mDevZdsc.Update = 0;
            updateItem(0, this.mDevZdsc.Sta);
            if (this.mDevZdsc.Csxs == 0) {
                updateItem(1, this.mDevZdsc.Csxs, getString(R.string.can_auto_break_normal_work));
            } else {
                updateItem(1, this.mDevZdsc.Csxs, getString(R.string.can_auto_break_speed_stop));
            }
            if (this.mDevZdsc.Aqdxs == 0) {
                updateItem(2, this.mDevZdsc.Aqdxs, getString(R.string.can_auto_break_normal_work));
            } else {
                updateItem(2, this.mDevZdsc.Aqdxs, getString(R.string.can_auto_break_aqd_stop));
            }
        }
        if (!check) {
            updateProgress();
        }
    }

    private void updateProgress() {
        updateItem(3, Values[0], String.valueOf(Values[0]) + "KM/H");
        updateItem(4, Values[1], String.valueOf(Values[1]) + "KM/H");
        updateItem(5, Values[2], String.valueOf(Values[2]) + "级");
        updateItem(6, Values[3], String.valueOf(Values[3]) + "级");
        CanBencWithCDSoSync.SetValue(getActivity(), new String[]{CanBencWithCDSoSync.KEY_SEAT_BREAK_STATUS, CanBencWithCDSoSync.KEY_SEAT_BREAK_SPEED_STOP, CanBencWithCDSoSync.KEY_SEAT_BREAK_SPEED_START, CanBencWithCDSoSync.KEY_SEAT_BREAK_REAR_RADAR, CanBencWithCDSoSync.KEY_SEAT_BREAK_FRONT_RADAR}, new int[]{this.mDevZdsc.Sta, Values[0], Values[1], Values[2], Values[3]});
    }

    public void QueryData() {
    }
}
