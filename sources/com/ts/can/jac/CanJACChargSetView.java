package com.ts.can.jac;

import android.app.Activity;
import android.text.format.Time;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanNumInuptDlg;

public class CanJACChargSetView extends CanScrollCarInfoView {
    public static final int ITEM_CHARG_ENDHOUR = 5;
    public static final int ITEM_CHARG_ENDMINUTE = 6;
    public static final int ITEM_CHARG_MODE = 0;
    public static final int ITEM_CHARG_PERCENT = 7;
    public static final int ITEM_CHARG_STARTHOUR = 3;
    public static final int ITEM_CHARG_STARTMINUTE = 4;
    public static final int ITEM_CHARG_SZ = 1;
    public static final int ITEM_CHARG_WEEK = 2;
    /* access modifiers changed from: private */
    public int chargEHour = 0;
    /* access modifiers changed from: private */
    public int chargEMinute = 0;
    private int chargItem = -1;
    /* access modifiers changed from: private */
    public int chargPercent = 0;
    /* access modifiers changed from: private */
    public int chargSHour = 0;
    /* access modifiers changed from: private */
    public int chargSMinute = 0;
    private int chargWeek = 0;

    public CanJACChargSetView(Activity activity) {
        super(activity, 8);
    }

    public void onPositiveItem(int id, int[] item) {
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.JacRzcChargSet(item + 1);
                return;
            case 1:
                this.chargItem = item;
                updateItem(1, item);
                if (item == 0) {
                    showItem(7, 1);
                    showItem(5, 0);
                    showItem(6, 0);
                } else if (item == 1) {
                    showItem(5, 1);
                    showItem(6, 1);
                    showItem(7, 0);
                }
                chooseChargTimeSet();
                return;
            case 2:
                updateItem(2, item);
                this.chargWeek = changeWeekValue(item);
                chooseChargTimeSet();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void chooseChargTimeSet() {
        switch (this.chargItem) {
            case 0:
                chargTimeSetPercent();
                break;
            case 1:
                chargTimeSetTimePeriod();
                break;
        }
        setCurrentTime();
    }

    private void chargTimeSetTimePeriod() {
        CanJni.JacRzcChargTimeSet(2, this.chargWeek, this.chargSHour, this.chargSMinute, this.chargEHour, this.chargEMinute);
    }

    private void chargTimeSetPercent() {
        CanJni.JacRzcChargTimeSet(1, this.chargWeek, this.chargSHour, this.chargSMinute, this.chargPercent, 0);
    }

    private int changeWeekValue(int item) {
        switch (item) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            default:
                return 0;
        }
    }

    public void onChanged(int id, int pos) {
    }

    private void setCurrentTime() {
        int mWeekDay;
        Time time = new Time();
        time.setToNow();
        if (time.weekDay == 0) {
            mWeekDay = 7;
        } else {
            mWeekDay = time.weekDay;
        }
        CanJni.JacRzcChargTimeSet(3, mWeekDay, time.hour, time.minute, 0, 0);
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 3:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            CanJACChargSetView.this.updateItem(3, num, String.format("%02d", new Object[]{Integer.valueOf(num)}));
                            CanJACChargSetView.this.chargSHour = num;
                            CanJACChargSetView.this.chooseChargTimeSet();
                        }
                    }
                }, 2, id);
                return;
            case 4:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 59) {
                            CanJACChargSetView.this.updateItem(4, num, String.format("%02d", new Object[]{Integer.valueOf(num)}));
                            CanJACChargSetView.this.chargSMinute = num;
                            CanJACChargSetView.this.chooseChargTimeSet();
                        }
                    }
                }, 2, id);
                return;
            case 5:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            CanJACChargSetView.this.updateItem(5, num, String.format("%02d", new Object[]{Integer.valueOf(num)}));
                            CanJACChargSetView.this.chargEHour = num;
                            CanJACChargSetView.this.chooseChargTimeSet();
                        }
                    }
                }, 2, id);
                return;
            case 6:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 59) {
                            CanJACChargSetView.this.updateItem(6, num, String.format("%02d", new Object[]{Integer.valueOf(num)}));
                            CanJACChargSetView.this.chargEMinute = num;
                            CanJACChargSetView.this.chooseChargTimeSet();
                        }
                    }
                }, 2, id);
                return;
            case 7:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 100) {
                            CanJACChargSetView.this.updateItem(7, num, String.valueOf(String.format("%d", new Object[]{Integer.valueOf(num)})) + " %");
                            CanJACChargSetView.this.chargPercent = num;
                            CanJACChargSetView.this.chooseChargTimeSet();
                        }
                    }
                }, 3, id);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_charg_mode, R.string.can_dscdsz, R.string.can_week, R.string.can_start_hour, R.string.can_start_minute, R.string.can_end_hour, R.string.can_end_minute, R.string.can_charg_percent};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mPopValueIds[0] = new int[]{R.string.can_charg_ptmode, R.string.can_charg_csmode};
        this.mPopValueIds[2] = new int[]{R.array.can_gc_week_arrays};
        this.mPopValueIds[1] = new int[]{R.string.can_dscdsz_bfb, R.string.can_dscdsz_sjd};
    }

    public void ResetData(boolean check) {
    }

    private int[] setTime(int hour, int minute) {
        return new int[]{(hour >> 2) & 7, ((hour << 6) & 192) + (minute & 63)};
    }

    public void QueryData() {
    }
}
