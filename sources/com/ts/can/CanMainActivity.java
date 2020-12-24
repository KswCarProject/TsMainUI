package com.ts.can;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.baic.ec180.CanBaicEc180CarInfoActivity;
import com.ts.can.baic.senova.CanSenovaCarInfoActivity;
import com.ts.can.benc.withcd.CanBencWithCDCarInfoActivity;
import com.ts.can.blsu.comm.CanBlsuCarTypeActivity;
import com.ts.can.blsu.ts.CanBlsuTsCarTypeActivity;
import com.ts.can.bmw.e90.CanE90CarInfoActivity;
import com.ts.can.bmw.mini.CanBMWMiniCarInfoActivity;
import com.ts.can.bmw.mini.fstt.CanBmwMiniFsttCarInfoActivity;
import com.ts.can.bmw.withcd.CanBmwWithCDCarInfoActivity;
import com.ts.can.bmw.x1.CanX1CarInfoActivity;
import com.ts.can.cadillac.withcd.CanCadillacWithCDCarInfoActivity;
import com.ts.can.cadillac.xhd.CanCadillacAtsXhdCarInfoActivity;
import com.ts.can.cc.h2.CanCcCarTypeActivity;
import com.ts.can.cc.h6.CanCCH6CarInfoActivity;
import com.ts.can.chana.cs75.CanCs75CarInfoActivity;
import com.ts.can.chery.airuize.CanCheryAiRuizeCarInfoActivity;
import com.ts.can.chery.tiggo.CanCheryTiggoCarInfoActivity;
import com.ts.can.chrysler.CanChrOthCarInfoActivity;
import com.ts.can.chrysler.rz.CanRZygCarInfoActivity;
import com.ts.can.chrysler.xbs.CanXbsygCarInfoActivity;
import com.ts.can.df.CanT70CarInfoActivity;
import com.ts.can.df.ax7.CanDFCarTypeActivity;
import com.ts.can.df.er70.CanDFQCCarInfoActivity;
import com.ts.can.df.jyx5.CanJYX5CarInfoActivity;
import com.ts.can.dj.qoros.CanQorosCarInfoActivity;
import com.ts.can.faw.CanB50_17CarInfoActivity;
import com.ts.can.faw.CanB70_14CarInfoActivity;
import com.ts.can.fiat.doblo.CanFlatDobloCarInfoActivity;
import com.ts.can.ford.CanFordCarInfoActivity;
import com.ts.can.ford.fiesta.CanOldFiestaCarInfoActivity;
import com.ts.can.gac.trumpchi.CanGqcqCarInfoActivity;
import com.ts.can.geely.boy.CanGeelyBoyCarInfoActivity;
import com.ts.can.geely.comm.CanGeelyCarTypeActivity;
import com.ts.can.gm.comm.CanGMCarInfoActivity;
import com.ts.can.gm.sb.CanGMSBCarInfoActivity;
import com.ts.can.gm.xt5.CanCadillacXt5CarInfoActivity;
import com.ts.can.hm.CanHmCarTypeActivity;
import com.ts.can.hm.fml17.CanHmFml17CarInfoActivity;
import com.ts.can.hm.m3.CanHmM3CarTypeActivity;
import com.ts.can.honda.accord.CanAccordCarInfoActivity;
import com.ts.can.honda.accord_xbs.CanAccordXbsCarInfoActivity;
import com.ts.can.ht.od.CanHtOdCarInfoActivity;
import com.ts.can.ht.x7.CanHTX7CarInfoActivity;
import com.ts.can.hyundai.rzc.CanHyunDaiRzcCarInfoActivity;
import com.ts.can.hyundai.xbs.CanHyunDaiXbsCarInfoActivity;
import com.ts.can.jac.CanJACCarTypeActivity;
import com.ts.can.jiangling.CanJlCarInfoActivity;
import com.ts.can.kaiyi.x3.CanKY3XCarInfoActivity;
import com.ts.can.lexus.is250.CanLexusIs250CarInfosActivity;
import com.ts.can.lifan.CanLiFanCarInfoActivity;
import com.ts.can.mitsubishi.outlander.CanMitSubiShiOutLanderCarInfoActivity;
import com.ts.can.mzd.CanMZDCarInfoActivity;
import com.ts.can.mzd.cx4.CanMzdCx4CarInfoActivity;
import com.ts.can.mzd.cx5.CanMzdCx5CarInfoActivity;
import com.ts.can.mzd.cx7.CanMZDCX7KeyActivity;
import com.ts.can.obd.CanObdCarInfoActivity;
import com.ts.can.psa.CanPSACarInfoActivity;
import com.ts.can.psa.hc.CanBZCarInfoActivity;
import com.ts.can.psa.pg206.CanPg206CarInfoActivity;
import com.ts.can.psa.pg3008.CanPg3008ScrKeyActivity;
import com.ts.can.psa.pg307.CanPg307CarInfoActivity;
import com.ts.can.psa.pg408.CanPg408KeySetActivity;
import com.ts.can.psa.rzc.CanPSARzcCarInfoActivity;
import com.ts.can.qoros.bnr.CanQorosBnrCarInfoActivity;
import com.ts.can.renault.kadjar.CanKadjarCarInfoActivity;
import com.ts.can.renault.renault.CanRenaultCarInfoActivity;
import com.ts.can.saic.baojun.CanBaojunCarInfoActivity;
import com.ts.can.saic.dt.CanDtT60CarInfoActivity;
import com.ts.can.saic.mg.CanMGGSCarInfoActivity;
import com.ts.can.saic.roewe.CanRW550CarInfoActivity;
import com.ts.can.saic.roewe.CanRoeweCarInfoActivity;
import com.ts.can.saic.rw950.CanRW950CarInfoActivity;
import com.ts.can.saic.rx5.CanRWRx5CarInfoActivity;
import com.ts.can.sci.CanRzcMZDKeyActivity;
import com.ts.can.toyota.spy.CanToyotaSpyCarInfoActivity;
import com.ts.can.toyota.wc.CanToyotaWCCarInfoActivity;
import com.ts.can.vw.dasauto_wc.CanCarVwWcCarInfoActivity;
import com.ts.can.vw.golf.wc.CanGolfWcMainActivity;
import com.ts.can.vw.touareg.CanTouaregCarInfoActivity;
import com.ts.can.zh.h3.CanZhH3CarInfoActivity;
import com.ts.can.zotye.CanZtY100CarInfoActivity;
import com.ts.can.zotye.x5.CanZotyetX5CarInfoActivity;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.Mcu;

public class CanMainActivity extends CanBaseActivity {
    public static final boolean DEBUG = false;
    public static final String TAG = "CanMainActivity";
    /* access modifiers changed from: private */
    public static Handler handler = null;
    /* access modifiers changed from: private */
    public static int mCurSyncSta = 0;
    /* access modifiers changed from: private */
    public static int mLastSyncSta = 0;
    public static boolean mbInit = false;
    private View.OnClickListener McuUpdateClick = new View.OnClickListener() {
        CanDataInfo.CAN_RadarInfo pFore;
        CanDataInfo.CAN_RadarInfo pRear;

        public void onClick(View v) {
            if (v.getId() == R.id.mcu_update_btn) {
                Can.updateRadar();
                this.pFore = Can.mRadarForeInfo;
                this.pRear = Can.mRadarRearInfo;
            }
        }
    };
    /* access modifiers changed from: private */
    public Button mBtnUpdate;
    private byte[] mCanData = new byte[128];
    /* access modifiers changed from: private */
    public int mTaskCount = 0;
    private Runnable runnable = new Runnable() {
        public void run() {
            CanMainActivity canMainActivity = CanMainActivity.this;
            canMainActivity.mTaskCount = canMainActivity.mTaskCount + 1;
            CanMainActivity.mCurSyncSta = Mcu.mcutask();
            if (1 == CanMainActivity.mCurSyncSta && CanMainActivity.mLastSyncSta == 0) {
                Log.e(CanMainActivity.TAG, "1 == Mcu.mcutask()");
                CanMainActivity.mLastSyncSta = 1;
                CanMainActivity.this.mBtnUpdate.setVisibility(0);
            }
            CanMainActivity.handler.postDelayed(this, 30);
            if (CanMainActivity.mLastSyncSta > 0) {
                CanJni.CanMain(0);
            }
        }
    };

    /* access modifiers changed from: protected */
    public void enterWin() {
        switch (CanFunc.mFsCanTp) {
            case 1:
            case 103:
            case 111:
            case 240:
                enterSubWin(CanVwCarInfoActivity.class);
                break;
            case 3:
            case 128:
            case Can.CAN_TOYOTA_SP_XP:
                enterSubWin(CanToyotaCarInfoActivity.class);
                break;
            case 4:
                enterSubWin(CanHondaOldCarInfoActivity.class);
                break;
            case 5:
                enterSubWin(CanHondaDACarInfoActivity.class);
                break;
            case 6:
                enterSubWin(CanNissanCarInfoActivity.class);
                break;
            case 7:
            case 121:
                enterSubWin(CanPradoCarInfoActivity.class);
                break;
            case 8:
                enterSubWin(CanGMCarInfoActivity.class);
                break;
            case 9:
            case 270:
                enterSubWin(CanAccordCarInfoActivity.class);
                break;
            case 11:
            case 58:
                enterSubWin(CanPSACarInfoActivity.class);
                break;
            case 13:
            case Can.CAN_FORD_SYNC3:
                enterSubWin(CanFordCarInfoActivity.class);
                break;
            case 16:
                enterSubWin(CanBaojunCarInfoActivity.class);
                break;
            case 17:
                enterSubWin(CanGqcqCarInfoActivity.class);
                break;
            case 18:
                enterSubWin(CanRW550CarInfoActivity.class);
                break;
            case 19:
                enterSubWin(CanRoeweCarInfoActivity.class);
                break;
            case 20:
                enterSubWin(CanHondaDAConsumpCurrentActivity.class);
                break;
            case 21:
                enterSubWin(CanMZDCarInfoActivity.class);
                break;
            case 23:
                enterSubWin(CanB70_14CarInfoActivity.class);
                break;
            case 25:
                enterSubWin(CanRW950CarInfoActivity.class);
                break;
            case 26:
                enterSubWin(CanMGGSCarInfoActivity.class);
                break;
            case 27:
                enterSubWin(CanJACCarTypeActivity.class);
                break;
            case 28:
            case 79:
                enterSubWin(CanJlCarInfoActivity.class);
                break;
            case 29:
                enterSubWin(CanChrOthCarInfoActivity.class);
                break;
            case 30:
                enterSubWin(CanZtY100CarInfoActivity.class);
                break;
            case 38:
                enterSubWin(CanRZygCarInfoActivity.class);
                break;
            case 42:
                enterSubWin(CanE90CarInfoActivity.class);
                break;
            case 44:
                enterSubWin(CanBZCarInfoActivity.class);
                break;
            case 45:
                enterSubWin(CanPg3008ScrKeyActivity.class);
                break;
            case 46:
                enterSubWin(CanHmM3CarTypeActivity.class);
                break;
            case 47:
                enterSubWin(CanT70CarInfoActivity.class);
                break;
            case 49:
                enterSubWin(CanSenovaCarInfoActivity.class);
                break;
            case 50:
                enterSubWin(CanHmCarTypeActivity.class);
                break;
            case 52:
                if (CanJni.GetSubType() != 4 && CanJni.GetSubType() != 5) {
                    enterSubWin(CanDFCarTypeActivity.class);
                    break;
                } else {
                    enterSubWin(CanBaseCarInfoActivity.class);
                    break;
                }
            case 53:
                enterSubWin(CanOldFiestaCarInfoActivity.class);
                break;
            case 54:
                enterSubWin(CanCheryTiggoCarInfoActivity.class);
                break;
            case 55:
                enterSubWin(CanKadjarCarInfoActivity.class);
                break;
            case 56:
                enterSubWin(CanMitSubiShiOutLanderCarInfoActivity.class);
                break;
            case 57:
                enterSubWin(CanCheryAiRuizeCarInfoActivity.class);
                break;
            case 59:
                enterSubWin(CanZhH3CarInfoActivity.class);
                break;
            case 60:
                enterSubWin(CanZotyetX5CarInfoActivity.class);
                break;
            case 61:
                enterSubWin(CanX1CarInfoActivity.class);
                break;
            case 64:
                enterSubWin(CanFlatDobloCarInfoActivity.class);
                break;
            case 65:
                if (MainSet.GetInstance().IsCustom("MINI")) {
                    enterSubWin(CanBMWMiniCarInfoActivity.class);
                    break;
                }
                break;
            case 66:
                enterSubWin(CanDFQCCarInfoActivity.class);
                break;
            case 69:
            case Can.CAN_MZD_TXB:
                enterSubWin(CanMzdCx4CarInfoActivity.class);
                break;
            case 71:
                enterSubWin(CanB50_17CarInfoActivity.class);
                break;
            case 72:
                enterSubWin(CanGeelyCarTypeActivity.class);
                break;
            case 73:
                enterSubWin(CanCs75CarInfoActivity.class);
                break;
            case 74:
                enterSubWin(CanLiFanCarInfoActivity.class);
                break;
            case 77:
                enterSubWin(CanMZDCX7KeyActivity.class);
                break;
            case 78:
                enterSubWin(CanCcCarTypeActivity.class);
                break;
            case 80:
                enterSubWin(CanCCH6CarInfoActivity.class);
                break;
            case 82:
                enterSubWin(CanHmFml17CarInfoActivity.class);
                break;
            case 84:
                enterSubWin(CanRWRx5CarInfoActivity.class);
                break;
            case 85:
                enterSubWin(CanBmwMiniFsttCarInfoActivity.class);
                break;
            case 86:
                enterSubWin(CanHTX7CarInfoActivity.class);
                break;
            case 88:
                if (CanJni.GetSubType() != 3 && CanJni.GetSubType() != 4 && CanJni.GetSubType() != 5 && CanJni.GetSubType() != 6 && CanJni.GetSubType() != 7 && CanJni.GetSubType() != 8 && CanJni.GetSubType() != 9 && 10 != CanJni.GetSubType() && CanJni.GetSubType() != 11) {
                    enterSubWin(CanGMSBCarInfoActivity.class);
                    break;
                } else {
                    enterSubWin(CanCadillacXt5CarInfoActivity.class);
                    break;
                }
                break;
            case 89:
                enterSubWin(CanKY3XCarInfoActivity.class);
                break;
            case 91:
                enterSubWin(CanGeelyBoyCarInfoActivity.class);
                break;
            case 92:
                enterSubWin(CanJYX5CarInfoActivity.class);
                break;
            case 93:
                enterSubWin(CanBlsuTsCarTypeActivity.class);
                break;
            case 96:
            case 100:
                enterSubWin(CanAccordXbsCarInfoActivity.class);
                break;
            case 97:
                enterSubWin(CanBlsuCarTypeActivity.class);
                break;
            case 98:
                enterSubWin(CanBaicEc180CarInfoActivity.class);
                break;
            case 101:
                enterSubWin(CanPg408KeySetActivity.class);
                break;
            case 102:
                enterSubWin(CanHyunDaiRzcCarInfoActivity.class);
                break;
            case 104:
                enterSubWin(CanPg206CarInfoActivity.class);
                break;
            case 108:
                enterSubWin(CanPg307CarInfoActivity.class);
                break;
            case 109:
                enterSubWin(CanDtT60CarInfoActivity.class);
                break;
            case 110:
                enterSubWin(CanBaseCarInfoActivity.class);
                break;
            case 112:
                enterSubWin(CanMzdCx5CarInfoActivity.class);
                break;
            case 113:
                enterSubWin(CanToyotaSpyCarInfoActivity.class);
                break;
            case 114:
                enterSubWin(CanXbsygCarInfoActivity.class);
                break;
            case 115:
                enterSubWin(CanLexusIs250CarInfosActivity.class);
                break;
            case 117:
                enterSubWin(CanRenaultCarInfoActivity.class);
                break;
            case 118:
                enterSubWin(CanCadillacWithCDCarInfoActivity.class);
                break;
            case 119:
                enterSubWin(CanObdCarInfoActivity.class);
                break;
            case 122:
                enterSubWin(CanGolfMainActivity.class);
                break;
            case 127:
                enterSubWin(CanPSARzcCarInfoActivity.class);
                break;
            case 129:
                enterSubWin(CanTouaregCarInfoActivity.class);
                break;
            case 130:
                enterSubWin(CanQorosCarInfoActivity.class);
                break;
            case 132:
                enterSubWin(CanQorosBnrCarInfoActivity.class);
                break;
            case 134:
                enterSubWin(CanHtOdCarInfoActivity.class);
                break;
            case 136:
                enterSubWin(CanCadillacAtsXhdCarInfoActivity.class);
                break;
            case 138:
                enterSubWin(CanBmwWithCDCarInfoActivity.class);
                break;
            case Can.CAN_BENC_ZMYT:
                enterSubWin(CanBencWithCDCarInfoActivity.class);
                break;
            case 141:
                enterSubWin(CanHyunDaiXbsCarInfoActivity.class);
                break;
            case 142:
                enterSubWin(CanGolfWcMainActivity.class);
                break;
            case 143:
                enterSubWin(CanCarVwWcCarInfoActivity.class);
                break;
            case 144:
                enterSubWin(CanToyotaWCCarInfoActivity.class);
                break;
            case 145:
                if (CanJni.GetSubType() == 2 || CanJni.GetSubType() == 1) {
                    enterSubWin(CanRzcMZDKeyActivity.class);
                    break;
                }
            default:
                enterSubWin(CanBaseCarInfoActivity.class);
                break;
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enterWin();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.e(TAG, "onResume");
        MainSet.GetInstance().TwShowTitle(getResources().getString(R.string.title_activity_car_info));
        super.onResume();
    }
}
