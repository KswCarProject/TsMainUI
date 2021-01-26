package com.ts.can.vw.golf.wc;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;
import com.txznet.sdk.TXZResourceManager;
import java.util.Calendar;

public class CanGolfWcSetTimeAndDateActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick, UserCallBack {
    private static final int ITEM_DATE = 4;
    private static final int ITEM_DATE_FORMAT = 5;
    private static final int ITEM_SUMMER_TIME = 6;
    private static final int ITEM_TIME = 2;
    private static final int ITEM_TIME_FORMAT = 3;
    private static final int[] mDateFmt = {R.string.can_df_d_m_y, R.string.can_df_y_m_d, R.string.can_df_m_d_y};
    private static final int[] mTimeAFmt = {R.string.can12_hours, R.string.can24_hours};
    /* access modifiers changed from: private */
    public CanDataInfo.GolfTime mCurTime = new CanDataInfo.GolfTime();
    private DatePickerDialog.OnDateSetListener mDatelistener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
            Log.d(CanBaseActivity.TAG, "您设置了时间：" + myyear + "年" + (monthOfYear + 1) + "月 " + dayOfMonth + "日 ");
            CanJni.GolfGetTime(CanGolfWcSetTimeAndDateActivity.this.mCurTime);
            CanGolfWcSetTimeAndDateActivity.this.mCurTime.Year = myyear;
            CanGolfWcSetTimeAndDateActivity.this.mCurTime.Month = monthOfYear + 1;
            CanGolfWcSetTimeAndDateActivity.this.mCurTime.Day = dayOfMonth;
            CanJni.GolfSetTime(CanGolfWcSetTimeAndDateActivity.this.mCurTime);
        }
    };
    private CanItemPopupList mItemDate;
    private CanItemPopupList mItemDateFmt;
    private CanItemCheckList mItemSummerTime;
    private CanItemPopupList mItemTime;
    private CanItemPopupList mItemTimeFmt;
    private CanScrollList mManager;
    private CanDataInfo.GolfTime mTimeData = new CanDataInfo.GolfTime();
    private TimePickerDialog.OnTimeSetListener mTimeListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Log.d(CanBaseActivity.TAG, "您设置了时间：" + hourOfDay + "时" + minute + "分");
            CanJni.GolfGetTime(CanGolfWcSetTimeAndDateActivity.this.mCurTime);
            CanGolfWcSetTimeAndDateActivity.this.mCurTime.Hour = hourOfDay;
            CanGolfWcSetTimeAndDateActivity.this.mCurTime.Min = minute;
            CanJni.GolfSetTime(CanGolfWcSetTimeAndDateActivity.this.mCurTime);
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfGetTime(this.mTimeData);
        if (!i2b(this.mTimeData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTimeData.Update)) {
            this.mTimeData.Update = 0;
            String strDate = TXZResourceManager.STYLE_DEFAULT;
            switch (this.mTimeData.DateFormat - 1) {
                case 0:
                    strDate = String.format("%02d.%02d.%04d", new Object[]{Integer.valueOf(this.mTimeData.Day), Integer.valueOf(this.mTimeData.Month), Integer.valueOf(this.mTimeData.Year)});
                    break;
                case 1:
                    strDate = String.format("%04d.%02d.%02d", new Object[]{Integer.valueOf(this.mTimeData.Year), Integer.valueOf(this.mTimeData.Month), Integer.valueOf(this.mTimeData.Day)});
                    break;
                case 2:
                    strDate = String.format("%02d.%02d.%04d", new Object[]{Integer.valueOf(this.mTimeData.Month), Integer.valueOf(this.mTimeData.Day), Integer.valueOf(this.mTimeData.Year)});
                    break;
            }
            this.mItemDate.SetVal(strDate);
            this.mItemDateFmt.SetSel(this.mTimeData.DateFormat - 1);
            this.mItemTimeFmt.SetSel(this.mTimeData.TimeMode);
            if (this.mTimeData.TimeMode == 0) {
                int hour = this.mTimeData.Hour;
                String unit = "a.m.";
                if (hour == 24) {
                    hour = 0;
                    unit = "a.m.";
                } else if (hour > 12) {
                    hour -= 12;
                    unit = "p.m.";
                } else if (hour == 12) {
                    unit = "p.m.";
                }
                this.mItemTime.SetVal(String.format("%02d:%02d %s", new Object[]{Integer.valueOf(hour), Integer.valueOf(this.mTimeData.Min), unit}));
            } else {
                this.mItemTime.SetVal(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mTimeData.Hour), Integer.valueOf(this.mTimeData.Min)}));
            }
            this.mItemSummerTime.SetCheck(this.mTimeData.AST);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 194);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemTime = new CanItemPopupList((Context) this, R.string.can_time, (String[]) null, (CanItemPopupList.onPopItemClick) null);
        this.mItemTime.GetView().setOnClickListener(this);
        this.mItemTime.GetView().setTag(2);
        this.mManager.AddView(this.mItemTime.GetView());
        this.mItemTimeFmt = this.mManager.addItemPopupList(R.string.can_time_format, mTimeAFmt, 3, (CanItemPopupList.onPopItemClick) this);
        this.mItemDate = new CanItemPopupList((Context) this, R.string.can_date, (String[]) null, (CanItemPopupList.onPopItemClick) null);
        this.mItemDate.GetView().setOnClickListener(this);
        this.mItemDate.GetView().setTag(4);
        this.mManager.AddView(this.mItemDate.GetView());
        this.mItemDateFmt = this.mManager.addItemPopupList(R.string.can_date_format, mDateFmt, 5, (CanItemPopupList.onPopItemClick) this);
        this.mItemSummerTime = new CanItemCheckList(this, R.string.can_zdxls);
        this.mItemSummerTime.SetIdClickListener(this, 6);
        this.mManager.AddView(this.mItemSummerTime.GetView());
    }

    public void onClick(View v) {
        int hour;
        int min;
        boolean is24hour;
        int year;
        int month;
        int day;
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                if (i2b(this.mTimeData.UpdateOnce)) {
                    hour = this.mTimeData.Hour;
                    min = this.mTimeData.Min;
                    is24hour = i2b(this.mTimeData.TimeMode);
                } else {
                    Calendar c = Calendar.getInstance();
                    hour = c.get(11);
                    min = c.get(12);
                    is24hour = true;
                }
                new TimePickerDialog(this, 16974546, this.mTimeListener, hour, min, is24hour).show();
                return;
            case 4:
                if (i2b(this.mTimeData.UpdateOnce)) {
                    year = this.mTimeData.Year;
                    month = this.mTimeData.Month - 1;
                    day = this.mTimeData.Day;
                } else {
                    Calendar c2 = Calendar.getInstance();
                    year = c2.get(1);
                    month = c2.get(2);
                    day = c2.get(5);
                }
                new DatePickerDialog(this, 16974546, this.mDatelistener, year, month, day).show();
                return;
            case 6:
                CanJni.GolfGetTime(this.mCurTime);
                this.mCurTime.AST = 1 - (this.mCurTime.AST & 1);
                CanJni.GolfSetTime(this.mCurTime);
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        if (Id == 3) {
            CanJni.GolfGetTime(this.mCurTime);
            this.mCurTime.TimeMode = item;
            CanJni.GolfSetTime(this.mCurTime);
        } else if (Id == 5) {
            CanJni.GolfGetTime(this.mCurTime);
            this.mCurTime.DateFormat = item + 1;
            CanJni.GolfSetTime(this.mCurTime);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
