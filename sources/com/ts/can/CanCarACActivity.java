package com.ts.can;

import android.os.Bundle;
import android.util.Log;
import com.lgb.canmodule.CanJni;
import com.ts.can.baic.senova.m50f.CanM50FCarACActivity;
import com.ts.can.cc.h2s.CanCcH2sCarACActivity;
import com.ts.can.cc.h6_rzc.CanCCH9RzcACActivity;
import com.ts.can.chery.airuize.CanTigger7AcActivity;
import com.ts.can.chrysler.CanChrOthACActivity;
import com.ts.can.chrysler.rz.CanRZygACActivity;
import com.ts.can.chrysler.xbs.CanXbsygACActivity;
import com.ts.can.df.d60.CanD60CarACActivity;
import com.ts.can.df.d60_rzc.CanD60RzcCarACActivity;
import com.ts.can.df.t90.CanT90CarACActivity;
import com.ts.can.ford.CanFordEscapeACActivity;
import com.ts.can.ford.chiaxWc.CanChiaxWcACActivity;
import com.ts.can.ford.rzc.CanFordRzcACActivity;
import com.ts.can.gac.trumpchi.CanGs3AcActivity;
import com.ts.can.gac.trumpchi.CanGs8AcActivity;
import com.ts.can.geely.boy.CanGeelyBoyCarACActivity;
import com.ts.can.geely.yjx1.CanGeelyYjX1ACActivity;
import com.ts.can.gm.comm.CanGL8ACActivity;
import com.ts.can.gm.comm.CanGMACActivity;
import com.ts.can.gm.sb.CanGMSBACActivity;
import com.ts.can.gm.sb.CanSBGL8ACActivity;
import com.ts.can.hm.CanHmV70AcActivity;
import com.ts.can.ht.od.CanHtOdACActivity;
import com.ts.can.ht.x7.CanHtX5ACActivity;
import com.ts.can.kaiyi.x3.CanKY3XACActivity;
import com.ts.can.landwind.rzc.CanLandWindAcActivity;
import com.ts.can.lexus.is250.CanLexusIs250CarACActivity;
import com.ts.can.psa.CanPSAACActivity;
import com.ts.can.psa.wc.CanPSAWCACActivity;
import com.ts.can.renault.kadjar.koleos.CanKoleosACActivity;
import com.ts.can.renault.koleos.CanKoleosXpACActivity;
import com.ts.can.saic.mg.CanMGGSACActivity;
import com.ts.can.vw.golf.wc.CanGolfWcACActivity;
import com.ts.can.vw.touareg.CanTouaregCarACActivity;
import com.ts.can.zotye.x7.CanZotyetX7ACActivity;

public class CanCarACActivity extends CanBaseActivity {
    public static final String TAG = "CanCarACActivity";
    public static boolean mfgAcIconClick = false;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        mfgAcIconClick = true;
        Log.d(TAG, "-----onCreate-----");
        super.onCreate(savedInstanceState);
        switch (CanJni.GetCanType()) {
            case 5:
                if (CanFunc.IsHaveIco(26) != 0 && (3 == CanJni.GetSubType() || 4 == CanJni.GetSubType() || CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7)) {
                    enterSubWin(CanHondaDAAcActivity.class);
                    break;
                }
            case 8:
                if (CanJni.GetSubType() != 4 || CanFunc.IsHaveIco(26) == 0) {
                    if (CanJni.GetSubType() == 9 && CanFunc.IsHaveIco(26) != 0) {
                        enterSubWin(CanGL8ACActivity.class);
                        break;
                    }
                } else {
                    enterSubWin(CanGMACActivity.class);
                    break;
                }
                break;
            case 11:
            case 58:
                if ((CanJni.GetSubType() == 2 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8) && CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanPSAACActivity.class);
                    break;
                }
            case 13:
                if (CanFunc.IsHaveIco(26) != 0 && CanJni.GetSubType() == 8) {
                    enterSubWin(CanFordEscapeACActivity.class);
                    break;
                }
            case 17:
                if (CanFunc.IsHaveIco(26) != 0) {
                    if (CanJni.GetSubType() != 6 && CanJni.GetSubType() != 8 && CanJni.GetSubType() != 14 && CanJni.GetSubType() != 15 && CanJni.GetSubType() != 16) {
                        if (CanJni.GetSubType() == 7 || CanJni.GetSubType() == 9 || CanJni.GetSubType() == 10 || CanJni.GetSubType() == 11) {
                            enterSubWin(CanGs3AcActivity.class);
                            break;
                        }
                    } else {
                        enterSubWin(CanGs8AcActivity.class);
                        break;
                    }
                }
                break;
            case 26:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanMGGSACActivity.class);
                    break;
                }
                break;
            case 29:
                if (CanFunc.IsHaveIco(26) != 0) {
                    if (38 != CanJni.GetCanFsTp()) {
                        if (114 != CanJni.GetCanFsTp()) {
                            enterSubWin(CanChrOthACActivity.class);
                            break;
                        } else {
                            enterSubWin(CanXbsygACActivity.class);
                            break;
                        }
                    } else {
                        enterSubWin(CanRZygACActivity.class);
                        break;
                    }
                }
                break;
            case 47:
                if (CanFunc.IsHaveIco(26) != 0) {
                    if (CanJni.GetSubType() != 2 && CanJni.GetSubType() != 4) {
                        if (CanJni.GetSubType() == 3) {
                            enterSubWin(CanD60RzcCarACActivity.class);
                            break;
                        }
                    } else {
                        enterSubWin(CanT90CarACActivity.class);
                        break;
                    }
                }
                break;
            case 49:
                if (CanFunc.IsHaveIco(26) != 0 && CanJni.GetSubType() == 3) {
                    enterSubWin(CanM50FCarACActivity.class);
                    break;
                }
            case 50:
                if (CanFunc.IsHaveIco(26) != 0 && 2 == CanJni.GetSubType()) {
                    enterSubWin(CanHmV70AcActivity.class);
                    break;
                }
            case 55:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanKoleosACActivity.class);
                    break;
                }
                break;
            case 57:
                if (CanFunc.IsHaveIco(26) != 0 && (CanJni.GetSubType() == 1 || CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6)) {
                    enterSubWin(CanTigger7AcActivity.class);
                    break;
                }
            case 60:
                if (CanFunc.IsHaveIco(26) != 0) {
                    if (CanJni.GetSubType() != 2) {
                        if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 4 || CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6) {
                            enterSubWin(CanBaseACActivity.class);
                            break;
                        }
                    } else {
                        enterSubWin(CanZotyetX7ACActivity.class);
                        break;
                    }
                }
                break;
            case 72:
                if (CanFunc.IsHaveIco(26) != 0) {
                    if (CanJni.GetSubType() != 2) {
                        enterSubWin(CanBaseACActivity.class);
                        break;
                    } else {
                        enterSubWin(CanGeelyYjX1ACActivity.class);
                        break;
                    }
                }
                break;
            case 78:
                if (CanFunc.IsHaveIco(26) != 0) {
                    if (CanJni.GetSubType() != 3) {
                        if (CanJni.GetSubType() != 8) {
                            enterSubWin(CanBaseACActivity.class);
                            break;
                        } else {
                            enterSubWin(CanCCH9RzcACActivity.class);
                            break;
                        }
                    } else {
                        enterSubWin(CanCcH2sCarACActivity.class);
                        break;
                    }
                }
                break;
            case 86:
                if (CanFunc.IsHaveIco(26) != 0 && CanJni.GetSubType() == 2) {
                    enterSubWin(CanHtX5ACActivity.class);
                    break;
                }
            case 88:
                if (CanJni.GetSubType() != 1 || CanFunc.IsHaveIco(26) == 0) {
                    if (CanJni.GetSubType() == 2 && CanFunc.IsHaveIco(26) != 0) {
                        enterSubWin(CanSBGL8ACActivity.class);
                        break;
                    }
                } else {
                    enterSubWin(CanGMSBACActivity.class);
                    break;
                }
                break;
            case 89:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanKY3XACActivity.class);
                    break;
                }
                break;
            case 91:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanGeelyBoyCarACActivity.class);
                    break;
                }
                break;
            case 115:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanLexusIs250CarACActivity.class);
                    break;
                }
                break;
            case 120:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanD60CarACActivity.class);
                    break;
                }
                break;
            case 122:
                if (CanFunc.IsHaveIco(26) != 0) {
                    if (CanJni.GetSubType() != 3) {
                        enterSubWin(CanGolfACActivity.class);
                        break;
                    } else {
                        enterSubWin(CanTeramontACActivity.class);
                        break;
                    }
                }
                break;
            case 127:
                if ((CanJni.GetSubType() == 1 || CanJni.GetSubType() == 2) && CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanPSAACActivity.class);
                    break;
                }
            case 129:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanTouaregCarACActivity.class);
                    break;
                }
                break;
            case 133:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanKoleosXpACActivity.class);
                    break;
                }
                break;
            case 134:
                if (CanFunc.IsHaveIco(26) != 0 && (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 2)) {
                    enterSubWin(CanHtOdACActivity.class);
                    break;
                }
            case 139:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanChiaxWcACActivity.class);
                    break;
                }
                break;
            case 142:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanGolfWcACActivity.class);
                    break;
                }
                break;
            case 146:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanFordRzcACActivity.class);
                    break;
                }
                break;
            case 147:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanLandWindAcActivity.class);
                    break;
                }
                break;
            case 148:
                if (CanFunc.IsHaveIco(26) != 0) {
                    enterSubWin(CanPSAWCACActivity.class);
                    break;
                }
                break;
            default:
                if (CanFunc.IsHaveIco(26) != 0) {
                    CanBaseACActivity.isACShow = true;
                    enterSubWin(CanBaseACActivity.class);
                    break;
                }
                break;
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.d(TAG, "-----onResume-----");
    }
}
