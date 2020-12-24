package com.ts.can.radio;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.Radio;

public class CanRadioRdsSetActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_PI = 2;
    public static final int ITEM_REG = 1;
    public static final int ITEM_RT = 5;
    public static final int ITEM_TA = 3;
    public static final int ITEM_VL = 4;
    public static final String TAG = "CanRadioRdsSetActivity";
    private CanItemPopupList mItemPi;
    private CanItemPopupList mItemReg;
    private CanItemPopupList mItemRt;
    private CanItemPopupList mItemTa;
    private CanScrollList mManager;
    private String[] mStrPi = {"Sound", "Mute"};
    private String[] mStrReg = {"Off", "On"};
    private String[] mStrRt = {"Short", "Long"};
    private String[] mStrTa = {"Alarm", "Seek"};

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData() {
        for (int i = 1; i <= 5; i++) {
            UpdateItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData();
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(String text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, 0, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        item.GetBtn().setText(text);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        if (getResources().getString(R.string.custom_num_show).equals("LOMU08")) {
            this.mStrPi[0] = getString(R.string.rds_set_sound);
            this.mStrPi[1] = getString(R.string.rds_set_mute);
            this.mStrTa[0] = getString(R.string.rds_set_alarm);
            this.mStrTa[1] = getString(R.string.rds_set_seek);
            this.mStrRt[0] = getString(R.string.rds_set_short);
            this.mStrRt[1] = getString(R.string.rds_set_long);
        }
        String language = getResources().getConfiguration().locale.getCountry();
        if (!getResources().getString(R.string.custom_num_show).equals("LM_RENAULT_UI6")) {
            this.mItemReg = AddPopupItem("Region", this.mStrReg, 1);
            this.mItemPi = AddPopupItem("PI", this.mStrPi, 2);
            this.mItemTa = AddPopupItem("TA", this.mStrTa, 3);
            this.mItemRt = AddPopupItem("Retune", this.mStrRt, 5);
        } else if (language.equals("BR")) {
            this.mItemReg = AddPopupItem("Region", new String[]{"DESL.", "LIG."}, 1);
        } else {
            this.mItemReg = AddPopupItem("Region", this.mStrReg, 1);
        }
    }

    /* access modifiers changed from: protected */
    public void UpdateItem(int item) {
        int val = Radio.RdsGetSetting(item);
        switch (item) {
            case 1:
                if (this.mItemReg != null) {
                    this.mItemReg.SetSel(val);
                    return;
                }
                return;
            case 2:
                if (this.mItemPi != null) {
                    this.mItemPi.SetSel(val);
                    return;
                }
                return;
            case 3:
                if (this.mItemTa != null) {
                    this.mItemTa.SetSel(val);
                    return;
                }
                return;
            case 5:
                if (this.mItemRt != null) {
                    this.mItemRt.SetSel(val);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void UserAll() {
    }

    public void onItem(int id, int item) {
        Radio.RdsSetSetting(id, item);
        UpdateItem(id);
    }

    public void onClick(View v) {
    }
}
