package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanNumInuptDlg;
import com.txznet.sdk.TXZResourceManager;

public class CanMzdRzcClbySetView extends CanScrollCarInfoView {
    private static final int ITEM_DATE = 1;
    private static final int ITEM_DIS = 2;
    private static final int ITEM_MODE = 0;
    private static final int ITEM_RESET = 3;
    public static final String TAG = "CanGolfRzcTimeSetView";
    private DatePickerDialog.OnDateSetListener mDatelistener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
            int year;
            Log.d("CanGolfRzcTimeSetView", "您设置了时间：年" + myyear + "月 " + monthOfYear + "日 " + dayOfMonth);
            if (myyear - 2018 < 0) {
                year = 0;
            } else {
                year = myyear - 2018;
            }
            CanJni.MzdRzcCarServiceSet(CanMzdRzcClbySetView.this.mServiceData.Mode, year, monthOfYear + 1, dayOfMonth, CanMzdRzcClbySetView.this.mServiceData.Dis >> 8, CanMzdRzcClbySetView.this.mServiceData.Dis & 255, 0);
        }
    };
    /* access modifiers changed from: private */
    public CanDataInfo.Mzd_Rzc_ServiceInfo mServiceData = new CanDataInfo.Mzd_Rzc_ServiceInfo();

    public CanMzdRzcClbySetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.MzdRzcCarServiceSet(item, this.mServiceData.Year, this.mServiceData.Month, this.mServiceData.Day, this.mServiceData.Dis >> 8, this.mServiceData.Dis & 255, 0);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int year;
        int month;
        int day;
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                if (i2b(this.mServiceData.UpdateOnce)) {
                    year = this.mServiceData.Year + 2018;
                    month = this.mServiceData.Month - 1;
                    day = this.mServiceData.Day;
                } else {
                    Time t = new Time();
                    t.setToNow();
                    year = t.year;
                    month = t.month;
                    day = t.monthDay;
                }
                new DatePickerDialog(getActivity(), this.mDatelistener, year, month, day).show();
                return;
            case 2:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 400 && num <= 30000) {
                            CanJni.MzdRzcCarServiceSet(CanMzdRzcClbySetView.this.mServiceData.Mode, CanMzdRzcClbySetView.this.mServiceData.Year, CanMzdRzcClbySetView.this.mServiceData.Month, CanMzdRzcClbySetView.this.mServiceData.Day, num >> 8, num & 255, 0);
                        }
                    }
                }, 5, 2);
                return;
            case 3:
                new CanItemMsgBox(3, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.MzdRzcCarServiceSet(2, CanMzdRzcClbySetView.this.mServiceData.Year, CanMzdRzcClbySetView.this.mServiceData.Month, CanMzdRzcClbySetView.this.mServiceData.Day, CanMzdRzcClbySetView.this.mServiceData.Dis >> 8, CanMzdRzcClbySetView.this.mServiceData.Dis & 255, 1);
                    }
                }, (CanItemMsgBox.onMsgBoxClick2) null);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_amp_mode, R.string.can_mzd_wc_xcfwrq, R.string.can_mzd_wc_xcfwjl, R.string.can_service_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.string.can_type_mode_auto, R.string.can_soudong};
        this.mServiceData = new CanDataInfo.Mzd_Rzc_ServiceInfo();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    public void ResetData(boolean check) {
        int i;
        int i2 = 1;
        CanJni.MzdRzcGetCarServiceSet(this.mServiceData);
        if (!i2b(this.mServiceData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mServiceData.Update)) {
            this.mServiceData.Update = 0;
            String strDate = TXZResourceManager.STYLE_DEFAULT;
            if (!(this.mServiceData.Year == 255 || this.mServiceData.Month == 255 || this.mServiceData.Day == 255)) {
                strDate = String.format("%04d.%02d.%02d", new Object[]{Integer.valueOf(this.mServiceData.Year + 2018), Integer.valueOf(this.mServiceData.Month), Integer.valueOf(this.mServiceData.Day)});
            }
            updateItem(0, this.mServiceData.Mode);
            updateItem(1, 0, strDate);
            if (this.mServiceData.Dis >= 30000 || this.mServiceData.Dis <= 400) {
                updateItem(2, 0, TXZResourceManager.STYLE_DEFAULT);
            } else {
                updateItem(2, 0, String.format("%d", new Object[]{Integer.valueOf(this.mServiceData.Dis)}));
            }
            if (this.mServiceData.Mode != 0) {
                i = 1;
            } else {
                i = 0;
            }
            showItem(1, i);
            if (this.mServiceData.Mode == 0) {
                i2 = 0;
            }
            showItem(2, i2);
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(48, 0);
    }

    public void doOnResume() {
    }
}
