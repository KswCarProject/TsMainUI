package com.ts.can.bmw.x1.wc;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.TimePicker;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanBMWX1WcZCTFView extends CanScrollCarInfoView {
    private TimePickerDialog.OnTimeSetListener mTime1Listener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker arg0, int hour, int minute) {
            CanJni.BmwX1WcZctfSet(4, hour, minute);
        }
    };
    private TimePickerDialog.OnTimeSetListener mTime2Listener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker arg0, int hour, int minute) {
            CanJni.BmwX1WcZctfSet(7, hour, minute);
        }
    };
    private CanDataInfo.BmwX1Wc_Zctf mZctfData;

    public CanBMWX1WcZCTFView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 0:
                CanJni.BmwX1WcZctfSet(1, checkBlower(), 0);
                return;
            case 1:
                new TimePickerDialog(getActivity(), 16974546, this.mTime1Listener, this.mZctfData.Time1_Hour, this.mZctfData.Time1_Min, true).show();
                return;
            case 2:
                new TimePickerDialog(getActivity(), 16974546, this.mTime2Listener, this.mZctfData.Time2_Hour, this.mZctfData.Time2_Min, true).show();
                return;
            case 3:
            case 4:
            case 5:
            case 6:
                new CanItemMsgBox(id, getActivity(), R.string.can_cmp_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int id) {
                        if (id == 3) {
                            CanJni.BmwX1WcZctfSet(2, 0, 0);
                        }
                        if (id == 4) {
                            CanJni.BmwX1WcZctfSet(3, 0, 0);
                        }
                        if (id == 5) {
                            CanJni.BmwX1WcZctfSet(5, 0, 0);
                        }
                        if (id == 6) {
                            CanJni.BmwX1WcZctfSet(6, 0, 0);
                        }
                    }
                });
                return;
            default:
                return;
        }
    }

    private int checkBlower() {
        int blowerSta = this.mZctfData.BlowerSta;
        if (blowerSta == 2 || blowerSta == 3) {
            return 0;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_blower, R.string.can_time1, R.string.can_time2, R.string.can_time1_reset, R.string.can_time1_recall, R.string.can_time2_reset, R.string.can_time2_recall};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.DATE, CanScrollCarInfoView.Item.DATE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mItemVisibles[0] = 0;
        this.mZctfData = new CanDataInfo.BmwX1Wc_Zctf();
    }

    public void ResetData(boolean check) {
        CanJni.BmwX1WcGetZctfInfo(this.mZctfData);
        if (!i2b(this.mZctfData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mZctfData.Update)) {
            this.mZctfData.Update = 0;
            int blower = this.mZctfData.BlowerSta;
            if (blower == 0 || blower == 2) {
                showItem(0, 0);
            } else {
                showItem(0, 1);
            }
            if (blower == 2 || blower == 3) {
                updateItem(0, 1);
            } else {
                updateItem(0, 0);
            }
            int timeSta = this.mZctfData.TimeSta;
            if (timeSta == 0 || timeSta == 2) {
                updateItem(1, 0, "-- : --");
            } else {
                updateItem(1, 0, String.format("%02d : %02d", new Object[]{Integer.valueOf(this.mZctfData.Time1_Hour), Integer.valueOf(this.mZctfData.Time1_Min)}));
            }
            if (timeSta == 0 || timeSta == 1) {
                updateItem(2, 0, "-- : --");
                return;
            }
            updateItem(2, 0, String.format("%02d : %02d", new Object[]{Integer.valueOf(this.mZctfData.Time2_Hour), Integer.valueOf(this.mZctfData.Time2_Min)}));
        }
    }

    public void QueryData() {
    }
}
