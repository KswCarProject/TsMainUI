package com.ts.can.bmw.mini;

import com.ts.MainUI.R;
import com.ts.canview.CanItemPopupList;

public class CanBMWMiniSetDateActivity extends CanBMWMiniBaseActivity {
    public static final int ITEM_DATE_FORMAT = 1;
    public static final int ITEM_TIME_FORMAT = 0;
    private int[] mDateArrays = {R.string.can_date_format1, R.string.can_date_format2, R.string.can_date_format3, R.string.can_date_format4};
    private CanItemPopupList mItemDate;
    private CanItemPopupList mItemTime;
    private int[] mTimeArrays = {R.string.can12_hours, R.string.can24_hours};

    /* access modifiers changed from: protected */
    public void AddItemView() {
        this.mItemTime = AddItemPopup(R.string.can_time_format, this.mTimeArrays, 0);
        this.mItemDate = AddItemPopup(R.string.can_date_format, this.mDateArrays, 1);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetMiniSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemTime.SetSel(this.mSetData.TimeFormat);
            this.mItemDate.SetSel(this.mSetData.DateFormat);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(18);
        Sleep(1);
        Query(20);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CarSet(18, item * 128);
                return;
            case 1:
                CarSet(20, (item * 16) + 128);
                return;
            default:
                return;
        }
    }
}
