package com.ts.can.vw.touareg;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanTouaregSetAssistActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_YLCGQ = 1;
    private CanDataInfo.TouaregWcAssist mAssistData = new CanDataInfo.TouaregWcAssist();
    private CanItemSwitchList mItemYlcgq;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mItemYlcgq = new CanScrollList(this).addItemCheckBox(R.string.can_ylcgq, 1, this);
        this.mItemYlcgq.ShowGone(false);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.TouaregQuery(5, 1, 72);
    }

    private void ResetData(boolean check) {
        CanJni.TouaregGetAssistData(this.mAssistData);
        if (!i2b(this.mAssistData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAssistData.Update)) {
            this.mAssistData.Update = 0;
            this.mItemYlcgq.ShowGone(this.mAssistData.YlcgqAvalid);
            this.mItemYlcgq.SetCheck(this.mAssistData.Ylcgq);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.TouaregAssistSet(1, Neg(this.mAssistData.Ylcgq));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
