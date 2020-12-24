package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanGolfWcMainActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CAR_INFO = 2;
    public static final int ITEM_CAR_TYPE = 1;
    public static final int ITEM_CONVCONSUMP = 4;
    public static final int ITEM_DR_DATA = 3;
    public static final int ITEM_HHDL = 8;
    public static final int ITEM_LANG = 7;
    public static final int ITEM_SETUP = 6;
    public static final int ITEM_STATUS = 5;
    protected static final int TOTAL_ITEM = 7;
    public static final int[] mOptId = {2, 3, 4, 5, 6, 8, 7};
    public static final int[] mOptTitle = {R.string.can_car_info, R.string.can_driving_data, R.string.can_conv_consumers, R.string.can_vehi_status, R.string.can_vehi_setup, R.string.can_hhdlzt, R.string.can_lang_set};
    private String[] mCarType;
    protected CanItemTextBtnList[] mOpt;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_list);
        CanScrollList sl = new CanScrollList(this);
        this.mOpt = new CanItemTextBtnList[7];
        for (int i = 0; i < 7; i++) {
            this.mOpt[i] = new CanItemTextBtnList((Context) this, mOptTitle[i]);
            this.mOpt[i].GetView().setOnClickListener(this);
            this.mOpt[i].GetView().setTag(Integer.valueOf(mOptId[i]));
            sl.AddView(this.mOpt[i].GetView());
        }
        if (CanJni.GetSubType() != 1) {
            this.mOpt[5].GetView().setVisibility(8);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                enterSubWin(CanGolfWcCarInfoActivity.class);
                return;
            case 3:
                enterSubWin(CanGolfWcDrivingDataActivity.class);
                return;
            case 4:
                enterSubWin(CanGolfWcConvConsumersActivity.class);
                return;
            case 5:
                enterSubWin(CanGolfWcVehicleStatusActivity.class);
                return;
            case 6:
                enterSubWin(CanGolfWcSetMainActivity.class);
                return;
            case 7:
                enterSubWin(CanGolfWcLangSetActivity.class);
                return;
            case 8:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -1);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onItem(int id, int item) {
    }
}
