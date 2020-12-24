package com.ts.can.geely.rzc;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGeelyRzcCarInfoView extends CanScrollCarInfoView {
    protected static final int ITEM_IN_PM25 = 0;
    protected static final int ITEM_MAX = 2;
    protected static final int ITEM_MIN = 0;
    protected static final int ITEM_OUT_PM25 = 1;
    private CanDataInfo.Geely_PmInfo mPmInfo;
    private String[] mStrData;

    public CanGeelyRzcCarInfoView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_in_pm25, R.string.can_out_pm25};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mPmInfo = new CanDataInfo.Geely_PmInfo();
        this.mStrData = getActivity().getResources().getStringArray(R.array.can_pm25_arrays);
    }

    /* access modifiers changed from: protected */
    public int PmVal(int data) {
        if (data >= 0 && data <= 49) {
            return 0;
        }
        if (data >= 50 && data <= 99) {
            return 1;
        }
        if (data >= 100 && data <= 149) {
            return 2;
        }
        if (data >= 150 && data <= 199) {
            return 3;
        }
        if (data >= 200 && data <= 299) {
            return 4;
        }
        if (data < 300 || data > 999) {
            return 6;
        }
        return 5;
    }

    public void ResetData(boolean check) {
        CanJni.GeelyRzcGetPmData(this.mPmInfo);
        if (!i2b(this.mPmInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPmInfo.Update)) {
            this.mPmInfo.Update = 0;
            Log.d("yw", "mPmInfo.InPm25 =" + this.mPmInfo.InPm25);
            updateItem(0, 0, String.format("%s", new Object[]{this.mStrData[PmVal(this.mPmInfo.InPm25)]}));
            updateItem(1, 0, String.format("%s", new Object[]{this.mStrData[PmVal(this.mPmInfo.OutPm25)]}));
        }
    }

    public void QueryData() {
    }
}
