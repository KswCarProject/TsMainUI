package com.ts.can.vw.golf.wc;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetMainActivity extends CanBaseActivity implements View.OnClickListener {
    public static final int ITEM_AIR = 12;
    public static final int ITEM_CDSZ = 14;
    public static final int ITEM_DRIVE_ASS = 3;
    public static final int ITEM_ESC_SYSTEM = 1;
    public static final int ITEM_FACTORY_SET = 13;
    public static final int ITEM_JSMS = 15;
    public static final int ITEM_LIGHT = 5;
    public static final int ITEM_MFD = 8;
    public static final int ITEM_MW = 6;
    public static final int ITEM_OC = 7;
    public static final int ITEM_PM = 4;
    public static final int ITEM_SERVICE = 11;
    public static final int ITEM_TIME_DATE = 9;
    public static final int ITEM_TYRES = 2;
    public static final int ITEM_UNITS = 10;
    protected CanItemIcoList mItemAir;
    protected CanItemIcoList mItemCDSZ;
    protected CanItemIcoList mItemDriveAss;
    protected CanItemIcoList mItemEscSystem;
    protected CanItemIcoList mItemFactorySet;
    protected CanItemIcoList mItemJSMS;
    protected CanItemIcoList mItemLight;
    protected CanItemIcoList mItemMFD;
    protected CanItemIcoList mItemMW;
    protected CanItemIcoList mItemOC;
    protected CanItemIcoList mItemPM;
    protected CanItemIcoList mItemService;
    protected CanItemIcoList mItemTimeAndData;
    protected CanItemIcoList mItemTyres;
    protected CanItemIcoList mItemUnits;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mItemEscSystem = new CanItemIcoList(this, R.drawable.can_golf_icon01, R.string.can_esc_system);
        this.mItemTyres = new CanItemIcoList(this, R.drawable.can_golf_icon03, R.string.can_tyres);
        this.mItemDriveAss = new CanItemIcoList(this, R.drawable.can_golf_icon02, R.string.can_drivet_assist);
        this.mItemPM = new CanItemIcoList(this, R.drawable.can_golf_icon04, R.string.can_park_and_m);
        this.mItemLight = new CanItemIcoList(this, R.drawable.can_golf_icon05, R.string.can_light_camera);
        this.mItemMW = new CanItemIcoList(this, R.drawable.can_golf_icon06, R.string.can_mirr_and_wiper);
        this.mItemOC = new CanItemIcoList(this, R.drawable.can_golf_icon07, R.string.can_open_and_close);
        this.mItemMFD = new CanItemIcoList(this, R.drawable.can_golf_icon08, R.string.can_mfd);
        this.mItemTimeAndData = new CanItemIcoList(this, R.drawable.can_golf_icon09, R.string.can_time_date);
        this.mItemAir = new CanItemIcoList(this, R.drawable.can_golf_icon10, R.string.can_ac_set);
        this.mItemUnits = new CanItemIcoList(this, R.drawable.can_golf_icon11, R.string.can_units);
        this.mItemCDSZ = new CanItemIcoList(this, R.drawable.can_golf_icon13, R.string.can_cdsz);
        this.mItemJSMS = new CanItemIcoList(this, R.drawable.can_golf_icon01, R.string.can_psa_wc_jsms);
        this.mItemService = new CanItemIcoList(this, R.drawable.can_golf_icon13, R.string.can_service);
        this.mItemFactorySet = new CanItemIcoList(this, R.drawable.can_golf_icon14, R.string.can_factory_set);
        this.mItemEscSystem.SetIdClickListener(this, 1);
        this.mItemTyres.SetIdClickListener(this, 2);
        this.mItemDriveAss.SetIdClickListener(this, 3);
        this.mItemPM.SetIdClickListener(this, 4);
        this.mItemLight.SetIdClickListener(this, 5);
        this.mItemMW.SetIdClickListener(this, 6);
        this.mItemOC.SetIdClickListener(this, 7);
        this.mItemMFD.SetIdClickListener(this, 8);
        this.mItemTimeAndData.SetIdClickListener(this, 9);
        this.mItemUnits.SetIdClickListener(this, 10);
        this.mItemCDSZ.SetIdClickListener(this, 14);
        this.mItemJSMS.SetIdClickListener(this, 15);
        this.mItemService.SetIdClickListener(this, 11);
        this.mItemAir.SetIdClickListener(this, 12);
        this.mItemFactorySet.SetIdClickListener(this, 13);
        CanScrollList sl = new CanScrollList(this);
        sl.AddView(this.mItemEscSystem.GetView());
        sl.AddView(this.mItemTyres.GetView());
        sl.AddView(this.mItemDriveAss.GetView());
        sl.AddView(this.mItemPM.GetView());
        sl.AddView(this.mItemLight.GetView());
        sl.AddView(this.mItemMW.GetView());
        sl.AddView(this.mItemOC.GetView());
        sl.AddView(this.mItemMFD.GetView());
        sl.AddView(this.mItemTimeAndData.GetView());
        sl.AddView(this.mItemUnits.GetView());
        if (CanJni.GetSubType() == 1) {
            sl.AddView(this.mItemCDSZ.GetView());
        }
        sl.AddView(this.mItemJSMS.GetView());
        sl.AddView(this.mItemService.GetView());
        sl.AddView(this.mItemAir.GetView());
        sl.AddView(this.mItemFactorySet.GetView());
    }

    public void onClick(View v) {
        Class cls = null;
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                cls = CanGolfWcSetESCSystemActivity.class;
                break;
            case 2:
                cls = CanGolfWcSetTyresActivity.class;
                break;
            case 3:
                cls = CanGolfWcSetDriveAssActivity.class;
                break;
            case 4:
                cls = CanGolfWcSetPMActivity.class;
                break;
            case 5:
                cls = CanGolfWcSetLightActivity.class;
                break;
            case 6:
                cls = CanGolfWcSetMWActivity.class;
                break;
            case 7:
                cls = CanGolfWcSetOCActivity.class;
                break;
            case 8:
                cls = CanGolfWcSetMFDActivity.class;
                break;
            case 9:
                cls = CanGolfWcSetTimeAndDateActivity.class;
                break;
            case 10:
                cls = CanGolfWcSetUnitsActivity.class;
                break;
            case 11:
                cls = CanGolfWcSetServiceActivity.class;
                break;
            case 12:
                cls = CanGolfWcSetACActivity.class;
                break;
            case 13:
                cls = CanGolfWcSetFactoryActivity.class;
                break;
            case 14:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
                break;
            case 15:
                if (CanJni.GetSubType() != 1) {
                    cls = CanGolfWcSeatDriveProfileActivity.class;
                    break;
                } else {
                    CanFunc.getInstance();
                    CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
                    break;
                }
        }
        if (cls != null) {
            enterSubWin(cls);
        }
    }
}
