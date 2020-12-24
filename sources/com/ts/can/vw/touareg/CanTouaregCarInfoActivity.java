package com.ts.can.vw.touareg;

import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanScrollList;

public class CanTouaregCarInfoActivity extends CanBaseActivity implements View.OnClickListener {
    public static final int ITEM_ASSIST = 2;
    public static final int ITEM_DATE = 7;
    public static final int ITEM_LIGHT = 3;
    public static final int ITEM_PARK = 5;
    public static final int ITEM_SERVICE = 8;
    public static final int ITEM_TYRES = 1;
    public static final int ITEM_UNIT = 6;
    public static final int ITEM_WINDOW = 4;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mManager.addItemIconList(R.drawable.can_golf_icon03, R.string.can_tyres, 1, this);
        this.mManager.addItemIconList(R.drawable.can_golf_icon02, R.string.can_drivet_assist, 2, this);
        this.mManager.addItemIconList(R.drawable.can_golf_icon05, R.string.can_light, 3, this);
        this.mManager.addItemIconList(R.drawable.can_golf_icon06, R.string.can_window_control, 4, this);
        this.mManager.addItemIconList(R.drawable.can_golf_icon04, R.string.can_park_warning, 5, this);
        this.mManager.addItemIconList(R.drawable.can_golf_icon11, R.string.can_units, 6, this);
        this.mManager.addItemIconList(R.drawable.can_golf_icon09, R.string.can_time_date, 7, this);
        this.mManager.addItemIconList(R.drawable.can_golf_icon13, R.string.can_service, 8, this);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanTouaregSetTyresActivity.class);
                return;
            case 2:
                enterSubWin(CanTouaregSetAssistActivity.class);
                return;
            case 3:
                enterSubWin(CanTouaregSetLightActivity.class);
                return;
            case 4:
                enterSubWin(CanTouaregSetWindowActivity.class);
                return;
            case 5:
                enterSubWin(CanTouaregSetParkActivity.class);
                return;
            case 6:
                enterSubWin(CanTouaregSetUnitActivity.class);
                return;
            case 7:
                enterSubWin(CanTouaregSetDateActivity.class);
                return;
            case 8:
                enterSubWin(CanTouaregSetServiceActivity.class);
                return;
            default:
                return;
        }
    }
}
