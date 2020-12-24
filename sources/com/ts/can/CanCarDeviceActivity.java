package com.ts.can;

import android.content.Intent;
import android.os.Bundle;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.can.audi.xhd.CanAudiWithCDExdActivity;
import com.ts.can.benc.withcd.CanBencWithCDExdActivity;
import com.ts.can.bmw.withcd.CanBmwWithCDExdActivity;
import com.ts.can.bmw.zmyt.CanBmwZmytWithCDExdActivity;
import com.ts.can.cadillac.withcd.CanCadillacWithCDExdActivity;
import com.ts.can.cadillac.xhd.CanCadillacAtsXhdExdActivity;
import com.ts.can.chrysler.CanChrOthCDActivity;
import com.ts.can.chrysler.rz.CanRZygCDActivity;
import com.ts.can.chrysler.xbs.CanXbsygCDActivity;
import com.ts.can.fiat.CanFiatAllExdActivity;
import com.ts.can.fiat.CanFiatBravoExdActivity;
import com.ts.can.gm.ats.CanCadillacAtsExdActivity;
import com.ts.can.gm.mkc.CanLincsMkcExdActivity;
import com.ts.can.gm.xt5.CanCadillacXt5ExdActivity;
import com.ts.can.honda.accord8.CanAccord8ExdActivity;
import com.ts.can.jiangling.CanJlCarSetActivity;
import com.ts.can.lexus.is250.CanLexusIs250CarDevActivity;
import com.ts.can.mzd.axela.CanMzd3CDActivity;
import com.ts.can.mzd.cx4.CanMzdCx4CDActivity;
import com.ts.can.nissan.CanNissanOldDeviceActivity;
import com.ts.can.psa.pg408.CanPg408ExdActivity;
import com.ts.can.volvo.xfy.CanVolvoXfyCarDeviceActivity;
import com.ts.main.common.MainUI;

public class CanCarDeviceActivity extends CanBaseActivity {
    public static final String TAG = "CanCarDeviceActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        switch (CanJni.GetCanType()) {
            case 4:
                enterSubWin(CanHondaExdActivity.class);
                break;
            case 5:
                if (5 == CanJni.GetSubType()) {
                    enterSubWin(CanOdysseyExdActivity.class);
                    break;
                }
                break;
            case 28:
                if (79 == CanJni.GetCanFsTp()) {
                    enterSubWin(CanJlCarSetActivity.class);
                    break;
                }
                break;
            case 29:
                if (38 != CanJni.GetCanFsTp()) {
                    if (114 != CanJni.GetCanFsTp()) {
                        enterSubWin(CanChrOthCDActivity.class);
                        break;
                    } else {
                        enterSubWin(CanXbsygCDActivity.class);
                        break;
                    }
                } else {
                    enterSubWin(CanRZygCDActivity.class);
                    break;
                }
            case 36:
                enterSubWin(CanFiatAllExdActivity.class);
                break;
            case 37:
                enterSubWin(CanFiatBravoExdActivity.class);
                break;
            case 39:
                enterSubWin(CanMzd3CDActivity.class);
                break;
            case 69:
            case Can.CAN_MZD_TXB:
                enterSubWin(CanMzdCx4CDActivity.class);
                break;
            case 88:
                if ((3 != CanJni.GetSubType() && 10 != CanJni.GetSubType()) || MainUI.nMcuRet != 1) {
                    if ((4 != CanJni.GetSubType() && 5 != CanJni.GetSubType()) || MainUI.nMcuRet != 1) {
                        if ((6 == CanJni.GetSubType() || 7 == CanJni.GetSubType() || 8 == CanJni.GetSubType() || 9 == CanJni.GetSubType() || 11 == CanJni.GetSubType()) && MainUI.nMcuRet == 1) {
                            enterSubWin(CanCadillacAtsExdActivity.class);
                            break;
                        }
                    } else {
                        enterSubWin(CanLincsMkcExdActivity.class);
                        break;
                    }
                } else {
                    enterSubWin(CanCadillacXt5ExdActivity.class);
                    break;
                }
                break;
            case 101:
                enterSubWin(CanPg408ExdActivity.class);
                break;
            case 107:
                enterSubWin(CanNissanOldDeviceActivity.class);
                break;
            case 115:
                enterSubWin(CanLexusIs250CarDevActivity.class);
                break;
            case 116:
                enterSubWin(CanAccord8ExdActivity.class);
                break;
            case 118:
                enterSubWin(CanCadillacWithCDExdActivity.class);
                break;
            case 136:
                enterSubWin(CanCadillacAtsXhdExdActivity.class);
                break;
            case 138:
                enterSubWin(CanBmwWithCDExdActivity.class);
                break;
            case Can.CAN_BENC_ZMYT:
                enterSubWin(CanBencWithCDExdActivity.class);
                break;
            case Can.CAN_AUDI_ZMYT:
                enterSubWin(CanAudiWithCDExdActivity.class);
                break;
            case 162:
                enterSub1Win(CanCarInfoSub1Activity.class, -1);
                break;
            case 164:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -1);
                break;
            case 168:
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -3);
                break;
            case 176:
                enterSubWin(CanBmwZmytWithCDExdActivity.class);
                break;
            case 181:
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -1);
                break;
            case 183:
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -1);
                break;
            case 199:
                if (7 != CanJni.GetSubType()) {
                    enterSubWin(CanNissanOldDeviceActivity.class);
                    break;
                } else {
                    enterSubWin(CanBaseCarDeviceActivity.class);
                    break;
                }
            case Can.CAN_VOLVO_XFY:
                enterSubWin(CanVolvoXfyCarDeviceActivity.class);
                break;
            case 256:
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -1);
                break;
            default:
                enterSubWin(CanBaseCarDeviceActivity.class);
                break;
        }
        finish();
        super.onCreate(arg0);
    }

    public void enterSub1Win(Class<?> cls, int id) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtra("ID", id);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
