package com.ts.can.honda.accord_xbs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanAccordXbsScreenActivity extends CanAccordXbsBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange, CanItemPopupList.onPopItemClick {
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SCR_BRIGHT = 2;
    public static final int ITEM_SCR_CON = 4;
    public static final int ITEM_SCR_LIGHT = 1;
    public static final int ITEM_SCR_SAT = 3;
    public static final String TAG = "CanAccordXbsScreenActivity";
    private static final int[] mLdmsArr = {R.string.can_scr_close, R.string.can_scr_day, R.string.can_scr_night};
    protected CanItemPopupList mItemLight;
    protected CanItemProgressList mItemScrBri;
    protected CanItemProgressList mItemScrCon;
    protected CanItemProgressList mItemScrSat;
    protected CanScrollList mManager;
    protected CanDataInfo.AccordXbsScrta mScrData = new CanDataInfo.AccordXbsScrta();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemLight = AddPopupItem(R.string.can_ldms, mLdmsArr, 1);
        this.mItemScrBri = new CanItemProgressList((Context) this, R.string.can_mzd_cx4_drive_light);
        this.mItemScrBri.SetIdCallBack(2, this);
        this.mManager.AddView(this.mItemScrBri.GetView());
        this.mItemScrBri.SetMinMax(0, 10);
        this.mItemScrBri.SetUserValText();
        this.mItemScrSat = new CanItemProgressList((Context) this, R.string.can_sat);
        this.mItemScrSat.SetIdCallBack(3, this);
        this.mManager.AddView(this.mItemScrSat.GetView());
        this.mItemScrSat.SetMinMax(0, 10);
        this.mItemScrSat.SetUserValText();
        this.mItemScrCon = new CanItemProgressList((Context) this, R.string.can_con);
        this.mItemScrCon.SetIdCallBack(4, this);
        this.mManager.AddView(this.mItemScrCon.GetView());
        this.mItemScrCon.SetMinMax(0, 10);
        this.mItemScrCon.SetUserValText();
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(11, 0);
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

    private void ResetData(boolean check) {
        CanJni.Yg9XbsGetScrSta(this.mScrData);
        if (!i2b(this.mScrData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mScrData.Update)) {
            this.mScrData.Update = 0;
            this.mItemLight.SetSel(this.mScrData.Mode);
            this.mItemScrBri.SetCurVal(this.mScrData.Bright);
            if (this.mScrData.Bright >= 5) {
                this.mItemScrBri.SetValText("+" + (this.mScrData.Bright - 5));
            } else {
                this.mItemScrBri.SetValText(new StringBuilder(String.valueOf(this.mScrData.Bright - 5)).toString());
            }
            this.mItemScrSat.SetCurVal(this.mScrData.Sat);
            if (this.mScrData.Sat >= 5) {
                this.mItemScrSat.SetValText("+" + (this.mScrData.Sat - 5));
            } else {
                this.mItemScrSat.SetValText(new StringBuilder(String.valueOf(this.mScrData.Sat - 5)).toString());
            }
            this.mItemScrCon.SetCurVal(this.mScrData.Con);
            if (this.mScrData.Con >= 5) {
                this.mItemScrCon.SetValText("+" + (this.mScrData.Con - 5));
            } else {
                this.mItemScrCon.SetValText(new StringBuilder(String.valueOf(this.mScrData.Con - 5)).toString());
            }
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 2:
                CarBlkSet(2, pos);
                return;
            case 3:
                CarBlkSet(3, pos);
                return;
            case 4:
                CarBlkSet(4, pos);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CarBlkSet(1, item);
                return;
            default:
                return;
        }
    }
}
