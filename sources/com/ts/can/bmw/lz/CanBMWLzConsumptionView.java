package com.ts.can.bmw.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanItemTitleValList;

public class CanBMWLzConsumptionView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    protected static final int ITEM_ARRIVAL_TIME = 3;
    protected static final int ITEM_AV_SPEED = 2;
    protected static final int ITEM_CONSUMPTION1 = 0;
    protected static final int ITEM_CONSUMPTION1_RESET = 4;
    protected static final int ITEM_CONSUMPTION2 = 1;
    protected static final int ITEM_CONSUMPTION2_RESET = 5;
    String mConsumDw = "";
    private CanDataInfo.BmwLz_FuleData mFuleData;
    private CanDataInfo.BmwLz_SetData mSetData;
    String mSpeedDw = "";

    public CanBMWLzConsumptionView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                new CanItemMsgBox(4, getActivity(), R.string.can_sure_reset, this);
                return;
            case 5:
                new CanItemMsgBox(5, getActivity(), R.string.can_sure_reset, this);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mFuleData = new CanDataInfo.BmwLz_FuleData();
        this.mSetData = new CanDataInfo.BmwLz_SetData();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        ((CanItemTitleValList) this.mItemObjects[0]).GetBtn().setText("Consumption1");
        ((CanItemTitleValList) this.mItemObjects[1]).GetBtn().setText("Consumption2");
        ((CanItemTitleValList) this.mItemObjects[2]).GetBtn().setText("Av speed");
        ((CanItemTitleValList) this.mItemObjects[3]).GetBtn().setText("Arrival Time");
        ((CanItemTextBtnList) this.mItemObjects[4]).SetVal("Consumption1 reset");
        ((CanItemTextBtnList) this.mItemObjects[5]).SetVal("Consumption2 reset");
    }

    public void ResetData(boolean check) {
        String timeHour;
        String timeMin;
        CanJni.BmwLzGetFuleData(this.mFuleData);
        if (i2b(this.mFuleData.UpdateOnce)) {
            if (!check || i2b(this.mFuleData.Update)) {
                this.mFuleData.Update = 0;
                if (i2b(this.mFuleData.SpeedDw)) {
                    this.mSpeedDw = "mph";
                } else {
                    this.mSpeedDw = "km/h";
                }
                if (this.mFuleData.ConDw == 0) {
                    this.mConsumDw = "l/100km";
                } else if (this.mFuleData.ConDw == 1) {
                    this.mConsumDw = "mpg";
                } else {
                    this.mConsumDw = "km/l";
                }
                if (this.mFuleData.Con1 == 65535) {
                    updateItem(0, this.mFuleData.Con1, "--");
                } else {
                    updateItem(0, this.mFuleData.Con1, String.valueOf(String.format("%.1f ", new Object[]{Double.valueOf(((double) this.mFuleData.Con1) * 0.1d)})) + this.mConsumDw);
                }
                if (this.mFuleData.Con2 == 65535) {
                    updateItem(1, this.mFuleData.Con2, "--");
                } else {
                    updateItem(1, this.mFuleData.Con2, String.valueOf(String.format("%.1f ", new Object[]{Double.valueOf(((double) this.mFuleData.Con2) * 0.1d)})) + this.mConsumDw);
                }
                if (this.mFuleData.AveSpeed == 65535) {
                    updateItem(2, this.mFuleData.AveSpeed, "--");
                } else {
                    updateItem(2, this.mFuleData.AveSpeed, String.valueOf(String.format("%.1f ", new Object[]{Double.valueOf(((double) this.mFuleData.AveSpeed) * 0.1d)})) + this.mSpeedDw);
                }
            }
            CanJni.BmwLzGetSetData(this.mSetData);
            if (!i2b(this.mSetData.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mSetData.Update)) {
                this.mSetData.Update = 0;
                if (this.mSetData.ArrivalHour == 255) {
                    timeHour = "--";
                } else {
                    timeHour = String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.ArrivalHour)});
                }
                if (this.mSetData.ArrivalMin == 255) {
                    timeMin = "--";
                } else {
                    timeMin = String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.ArrivalMin)});
                }
                updateItem(3, this.mSetData.ArrivalHour, String.format("%s:%s", new Object[]{timeHour, timeMin}));
            }
        }
    }

    public void QueryData() {
        CanJni.BmwLzQueryData(55, 0);
    }

    public void onOK(int param) {
        switch (param) {
            case 4:
                CanJni.BmwLzCarSet(9, 0, 0);
                return;
            case 5:
                CanJni.BmwLzCarSet(10, 0, 0);
                return;
            default:
                return;
        }
    }
}
