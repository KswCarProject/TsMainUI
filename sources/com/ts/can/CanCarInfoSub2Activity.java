package com.ts.can;

import android.content.Intent;
import android.os.Bundle;
import com.lgb.canmodule.CanJni;
import com.ts.can.bmw.lz.bmw2.CanBMW2LzCarcomputerView;
import com.ts.can.ford.rzc.CanFordRzcCDListView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcAirSetView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcDoorView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcKeylessOperSystemView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcLightsView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcOtherSetView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcTouchControllerView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcTumSignalView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcWipersView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrCarAmpView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrCarDoorView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrCarLightView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrCarTurnView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrDriveDisplayView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrOtherSetupView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrSafeSetupView;
import com.ts.can.mzd.rzc.CanMzdRzcCarDoorView;
import com.ts.can.mzd.rzc.CanMzdRzcCarLightView;
import com.ts.can.mzd.rzc.CanMzdRzcCarSafeSetView;
import com.ts.can.mzd.rzc.CanMzdRzcCarTurnView;
import com.ts.can.mzd.rzc.CanMzdRzcDriveDisplayView;
import com.ts.can.mzd.rzc.CanMzdRzcOtherSetupView;
import com.ts.can.mzd.rzc.CanMzdRzcSetAmpView;
import com.ts.can.vw.rzc.golf.CanGolfRzcAmpSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcCameraSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcChairSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcDriveAssSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcESCSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcEleSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcElecEcoProfileSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcLightSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcMFDSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcMWSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcOCSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcPMSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcPersonalSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcReportsView;
import com.ts.can.vw.rzc.golf.CanGolfRzcServiceSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcSetFactoryView;
import com.ts.can.vw.rzc.golf.CanGolfRzcStartStopView;
import com.ts.can.vw.rzc.golf.CanGolfRzcTeramonDriveProfileSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcTeramonEcoProfileSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcTimeSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcTyresSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcUnitSetView;

public class CanCarInfoSub2Activity extends CanBaseCarInfoActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        CreateInit(getIntent());
        super.onCreate(arg0);
    }

    /* access modifiers changed from: protected */
    public void InitView(int canType, int id) {
        switch (canType) {
            case 2:
                if (id == 0) {
                    this.mBaseView = new CanGolfRzcESCSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanGolfRzcTyresSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanGolfRzcDriveAssSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanGolfRzcPMSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanGolfRzcLightSetView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanGolfRzcMWSetView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanGolfRzcOCSetView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanGolfRzcMFDSetView(this);
                    return;
                } else if (id == 8) {
                    this.mBaseView = new CanGolfRzcTimeSetView(this);
                    return;
                } else if (id == 9) {
                    this.mBaseView = new CanGolfRzcUnitSetView(this);
                    return;
                } else if (id == 10) {
                    this.mBaseView = new CanGolfRzcServiceSetView(this);
                    return;
                } else if (id == 11) {
                    this.mBaseView = new CanGolfRzcChairSetView(this);
                    return;
                } else if (id == 12) {
                    this.mBaseView = new CanGolfRzcPersonalSetView(this);
                    return;
                } else if (id == 13) {
                    this.mBaseView = new CanGolfRzcAmpSetView(this);
                    return;
                } else if (id == 14) {
                    this.mBaseView = new CanGolfRzcCameraSetView(this);
                    return;
                } else if (id == 15) {
                    this.mBaseView = new CanGolfRzcEleSetView(this);
                    return;
                } else if (id == 16) {
                    this.mBaseView = new CanGolfRzcSetFactoryView(this);
                    return;
                } else if (id == -1) {
                    this.mBaseView = new CanGolfRzcTeramonDriveProfileSetView(this);
                    return;
                } else if (id == -2) {
                    this.mBaseView = new CanGolfRzcTeramonEcoProfileSetView(this);
                    return;
                } else if (id == -3) {
                    this.mBaseView = new CanGolfRzcReportsView(this);
                    return;
                } else if (id == -4) {
                    this.mBaseView = new CanGolfRzcStartStopView(this);
                    return;
                } else if (id == -5) {
                    this.mBaseView = new CanGolfRzcElecEcoProfileSetView(this);
                    return;
                } else {
                    return;
                }
            case 146:
                if (id == -1) {
                    this.mBaseView = new CanFordRzcCDListView(this);
                    return;
                }
                return;
            case 256:
                if (id == 0) {
                    this.mBaseView = new CanMzdCx4BnrCarDoorView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanMzdCx4BnrCarTurnView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanMzdCx4BnrCarLightView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanMzdCx4BnrDriveDisplayView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanMzdCx4BnrSafeSetupView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanMzdCx4BnrOtherSetupView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanMzdCx4BnrCarAmpView(this);
                    return;
                } else {
                    return;
                }
            case 260:
                if (id == 0) {
                    this.mBaseView = new CanMzdRzcCarDoorView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanMzdRzcCarTurnView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanMzdRzcCarLightView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanMzdRzcDriveDisplayView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanMzdRzcOtherSetupView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanMzdRzcSetAmpView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanMzdRzcCarSafeSetView(this);
                    return;
                } else {
                    return;
                }
            case 265:
                if (id == 0) {
                    this.mBaseView = new CanMitsubshiRzcTouchControllerView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanMitsubshiRzcKeylessOperSystemView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanMitsubshiRzcWipersView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanMitsubshiRzcLightsView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanMitsubshiRzcTumSignalView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanMitsubshiRzcDoorView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanMitsubshiRzcAirSetView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanMitsubshiRzcOtherSetView(this);
                    return;
                } else {
                    return;
                }
            case 277:
                if (id == 0) {
                    this.mBaseView = new CanBMW2LzCarcomputerView(this);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        switch (CanJni.GetCanFsTp()) {
            case 2:
                if (this.mBaseView instanceof CanGolfRzcTeramonEcoProfileSetView) {
                    Intent i = new Intent(this, CanCarInfoSub2Activity.class);
                    i.putExtra("drive_pattern", 1);
                    i.putExtra("ID", -1);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                    return;
                }
                break;
        }
        super.onBackPressed();
    }

    public void CreateInit(Intent intent) {
        int id = -1;
        if (intent != null) {
            id = intent.getIntExtra("ID", -1);
        }
        switch (CanJni.GetCanType()) {
            case 277:
                if (id == 0) {
                    getWindow().setFlags(1024, 1024);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
