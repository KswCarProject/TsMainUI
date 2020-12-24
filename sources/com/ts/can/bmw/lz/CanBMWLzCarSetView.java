package com.ts.can.bmw.lz;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanNumInuptDlg;

public class CanBMWLzCarSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    protected static final int ITEM_AUD_OBC = 7;
    protected static final int ITEM_CONS_UNIT = 3;
    protected static final int ITEM_DATE = 10;
    protected static final int ITEM_DISTANCE = 8;
    protected static final int ITEM_DIS_UNIT = 4;
    protected static final int ITEM_LANG = 9;
    protected static final int ITEM_MEMO = 5;
    protected static final int ITEM_RESET = 12;
    protected static final int ITEM_SPEED_LIMIT = 0;
    protected static final int ITEM_SPEED_LIMIT_A = 1;
    protected static final int ITEM_TEMP_UNIT = 2;
    protected static final int ITEM_TIME = 11;
    protected static final int ITEM_TIME_MODE = 6;
    private DatePickerDialog.OnDateSetListener mDatelistener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
            Log.d("lq", "date: year" + (myyear - 2000) + "month " + (monthOfYear + 1) + "day " + dayOfMonth);
            CanJni.BmwLzCarTimeSet(0, myyear - 2000, monthOfYear + 1, dayOfMonth, 0, 0);
        }
    };
    private String[] mLangValues;
    private CanDataInfo.BmwLz_SetData mSetData;
    private TimePickerDialog.OnTimeSetListener mTimeListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Log.d("lq", "time:" + hourOfDay + "hour" + minute + "minute");
            CanJni.BmwLzCarTimeSet(1, hourOfDay, minute, 0, 0, 0);
        }
    };
    private String[] mTimeModeValues;

    public CanBMWLzCarSetView(Activity activity) {
        super(activity, 13);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.BmwLzCarSet(2, item, 0);
                return;
            case 3:
                CanJni.BmwLzCarSet(3, item, 0);
                return;
            case 4:
                CanJni.BmwLzCarSet(4, item, 0);
                return;
            case 6:
                CanJni.BmwLzCarTimeSet(2, item, 0, 0, 0, 0);
                return;
            case 9:
                CanJni.BmwLzCarSet(7, item, 0);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 0:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 6 && num <= 299) {
                            CanJni.BmwLzCarSet(1, num >> 8, num & 255);
                        }
                    }
                }, 3, id);
                return;
            case 1:
                CanJni.BmwLzCarSet(0, Neg(this.mSetData.SpeedLimActiv), 0);
                return;
            case 5:
                CanJni.BmwLzCarSet(5, Neg(this.mSetData.Memo), 0);
                return;
            case 7:
                CanJni.BmwLzCarSet(6, Neg(this.mSetData.AudObc), 0);
                return;
            case 8:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 9999) {
                            CanJni.BmwLzCarSet(8, num >> 8, num & 255);
                        }
                    }
                }, 4, id);
                return;
            case 10:
                Time t = new Time();
                t.setToNow();
                new DatePickerDialog(getActivity(), this.mDatelistener, t.year, t.month, t.monthDay).show();
                return;
            case 11:
                Time time = new Time();
                time.setToNow();
                new TimePickerDialog(getActivity(), this.mTimeListener, time.hour, time.minute, true).show();
                return;
            case 12:
                new CanItemMsgBox(12, getActivity(), R.string.can_sure_setup, this);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name, R.string.can_lang_set, R.string.can_date, R.string.can_time, R.string.can_hfcssz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[2] = new int[]{R.string.can_temperature_c, R.string.can_temperature_f};
        this.mPopValueIds[3] = new int[]{R.string.can_fuels_lkm, R.string.can_fuels_mpg, R.string.can_fuels_kml};
        this.mPopValueIds[4] = new int[]{R.string.can_distance_km, R.string.can_distance_mile};
        this.mPopValueIds[6] = new int[]{R.string.app_name};
        this.mPopValueIds[9] = new int[]{R.string.app_name};
        this.mSetData = new CanDataInfo.BmwLz_SetData();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        this.mTimeModeValues = new String[]{"24h", "12h"};
        this.mLangValues = new String[]{"US", "E", "I", "F", "GB", "D"};
        getScrollManager().RemoveView(6);
        this.mItemObjects[6] = getScrollManager().addItemPopupList(this.mItemTitleIds[6], this.mTimeModeValues, 6, this, 6);
        getScrollManager().RemoveView(9);
        this.mItemObjects[9] = getScrollManager().addItemPopupList(this.mItemTitleIds[9], this.mLangValues, 9, this, 9);
        ((CanItemTitleValList) this.mItemObjects[0]).GetBtn().setText("Speed limit");
        ((CanItemSwitchList) this.mItemObjects[1]).GetBtn().setText("Speed limit activation");
        ((CanItemPopupList) this.mItemObjects[2]).GetBtn().setText("Temp unit");
        ((CanItemPopupList) this.mItemObjects[3]).GetBtn().setText("Consumption unit");
        ((CanItemPopupList) this.mItemObjects[4]).GetBtn().setText("Distance unit");
        ((CanItemSwitchList) this.mItemObjects[5]).GetBtn().setText("Memo");
        ((CanItemPopupList) this.mItemObjects[6]).GetBtn().setText("Time mode");
        ((CanItemSwitchList) this.mItemObjects[7]).GetBtn().setText("AUD+OBC");
        ((CanItemTitleValList) this.mItemObjects[8]).GetBtn().setText("Distance");
    }

    public void ResetData(boolean check) {
        CanJni.BmwLzGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            if (this.mSetData.SpeedLimit == 65535) {
                updateItem(0, this.mSetData.SpeedLimit, "--");
            } else {
                updateItem(0, this.mSetData.SpeedLimit, String.format("%d km", new Object[]{Integer.valueOf(this.mSetData.SpeedLimit)}));
            }
            updateItem(1, this.mSetData.SpeedLimActiv);
            updateItem(2, this.mSetData.TempUnit);
            updateItem(3, this.mSetData.ConUnit);
            updateItem(4, this.mSetData.DisUnit);
            updateItem(5, this.mSetData.Memo);
            updateItem(7, this.mSetData.AudObc);
            if (this.mSetData.Distance == 65535) {
                updateItem(8, this.mSetData.Distance, "--");
            } else {
                updateItem(8, this.mSetData.Distance, String.format("%d km", new Object[]{Integer.valueOf(this.mSetData.Distance)}));
            }
            updateItem(9, this.mSetData.Lang);
        }
    }

    public void QueryData() {
        CanJni.BmwLzQueryData(56, 0);
    }

    public void onOK(int param) {
        switch (param) {
            case 12:
                CanJni.BmwLzCarSet(11, 0, 0);
                return;
            default:
                return;
        }
    }
}
