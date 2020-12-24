package com.ts.can;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.audi.xhd.CanAudiWithCDCarInfoView;
import com.ts.can.baic.wc.hss6.CanBaicHSS6WcCarInfoView;
import com.ts.can.bmw.lz.CanBMWLzCarInfoView;
import com.ts.can.bmw.lz.bmw2.CanBMW2LzCarInfoView;
import com.ts.can.bmw.x1.wc.CanBMWX1WcCarInfoView;
import com.ts.can.bmw.zmyt.CanBmwZmytWithCDCarInfoView;
import com.ts.can.byd.dj.m6.CanBydM6DjCarInfoView;
import com.ts.can.byd.hcy.s6s7.CanBydS6S7CarBaseInfoSetView;
import com.ts.can.byd.rsw.CanBydRswCarInfoView;
import com.ts.can.byd.wc.CanBydWcCarInfoView;
import com.ts.can.cc.dj.hf.CanHfDjCarInfoView;
import com.ts.can.cc.wc.CanCCWcCarInfoView;
import com.ts.can.cc.wc.h2.CanCCH2WcCarInfoView;
import com.ts.can.cc.wc.h6.CanWcH6CarInfoView;
import com.ts.can.chana.od.CanChanaODCarInfoView;
import com.ts.can.chana.wc.CanCs75WcCarInfoActivity;
import com.ts.can.chana.wc.cos.CanChanACosCarInfoView;
import com.ts.can.chana.wc.os.CanChanaWcCarInfoView;
import com.ts.can.chery.wc.CanCheryWcCarInfoView;
import com.ts.can.chrysler.txb.CanChryslerTxbCarInfoView;
import com.ts.can.chrysler.wc.CanChryslerWcCarInfoView;
import com.ts.can.chrysler.wc.compass.CanCompassWcCarInfoView;
import com.ts.can.chrysler.wc.jeep.CanJeepWcCarInfoView;
import com.ts.can.df.ax7.CanDFCarInfoView;
import com.ts.can.df.fg_rzc.CanDFFGRzcCarInfoView;
import com.ts.can.df.od.ax7.CanDfAx7OdCarInfoView;
import com.ts.can.df.wc.ax7.CanDfFsWcCarInfoView;
import com.ts.can.df.wc.jy.CanJYX5WcCarInfoView;
import com.ts.can.faw.t3.b30.CanFawB30T3CarInfoView;
import com.ts.can.fiat.rzc.CanFiatRzcCarInfoView;
import com.ts.can.fiat.wc.CanFiatWcCarInfoView;
import com.ts.can.ford.rzc.CanFordRzcCarInfoView;
import com.ts.can.ford.wc.CanFordWcCarInfoView;
import com.ts.can.ford.wc.fiesta.CanFordFiestaWcCarInfoView;
import com.ts.can.ford.wc.mondeo.CanFordMondeoWcCarInfoView;
import com.ts.can.gac.trumpchi_wc.CanTrumpchiWcCarInfoView;
import com.ts.can.gm.od.captiva.CanGmCaptivaOdCarInfoView;
import com.ts.can.gm.rzc.CanGMCarInfoView;
import com.ts.can.gm.wc.CanGMWcCarInfoView;
import com.ts.can.hant.rzc.CanHantElectCarInfoView;
import com.ts.can.honda.rzc.CanHondaDaRzcCarInfoView;
import com.ts.can.honda.wc.CanHondaWcCarInfoView;
import com.ts.can.honda.wc.accord9.CanAccord9WcCarInfoView;
import com.ts.can.honda.wc.crown.CanCrownWcCarInfoView;
import com.ts.can.hyundai.CanHyundaiCarInfoView;
import com.ts.can.hyundai.wc.CanHyundaiWcCarInfoView;
import com.ts.can.jac.od.CanJACRefineOdCarInfoView;
import com.ts.can.jac.wc.CanJACRefineWcCarInfoView;
import com.ts.can.jiangling.myx.CanJiangLingMyxCarInfoView;
import com.ts.can.kawei.wc.CanKaWeiWcCarInfoView;
import com.ts.can.landwind.od.CanLandWindOdCarInfoView;
import com.ts.can.landwind.rzc.CanLandWindCarInfoView;
import com.ts.can.lexus.lz.CanLexusLZIs250CarInfoView;
import com.ts.can.lexus.zmyt.CanLexusZMYTCarInfoView;
import com.ts.can.luxgen.od.CanLuxgenOdCarInfoView;
import com.ts.can.luxgen.wc.CanLuxgenWCCarInfoView;
import com.ts.can.mahindra.wc.CanMahindraWcCarInfoView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcCarInfoView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrCarInfoView;
import com.ts.can.mzd.hc.CanMzdHcCarInfoView;
import com.ts.can.mzd.lz.CanMzdLzCarInfoView;
import com.ts.can.mzd.rzc.CanMzdRzcCarInfoView;
import com.ts.can.mzd.wc.CanMzdWcCarInfoView;
import com.ts.can.nissan.dj.teana.CanTeanaOldDjCarInfoView;
import com.ts.can.nissan.rzc.CanNissanRzcCarInfoView;
import com.ts.can.nissan.wc.CanNissanWcCarInfoView;
import com.ts.can.nissan.wc.rich6.CanNissanRich6WcCarInfoView;
import com.ts.can.obd.bnr.CanObdBnrCarSetView;
import com.ts.can.obd.dst.CanSciDstCarInfoView;
import com.ts.can.porsche.lz.CanPorscheLzCarInfoView;
import com.ts.can.porsche.od.CanPorscheOdCarInfoView;
import com.ts.can.psa.rzc.scr.CanPSAScrRzcCarInfoView;
import com.ts.can.psa.wc.CanPSAWCCarInfoView;
import com.ts.can.renault.kadjar.koleos.CanKoleosTpmsView;
import com.ts.can.renault.wc.CanRenaultWcCarInfoView;
import com.ts.can.saic.baojun.wc.CanBaojunCarInfoView;
import com.ts.can.saic.dt.v80.CanDtV80CarInfoView;
import com.ts.can.saic.t60_rzc.CanDtT60RzcCarInfoView;
import com.ts.can.saic.wc.CanSaicRWMGWcCarInfoView;
import com.ts.can.saic.wc.mg_zs.CanMGZSWCCarInfoView;
import com.ts.can.saic.wc.rw550.CanRW550MG6WcCarInfoView;
import com.ts.can.saic.wc.sgmw.CanSgmwWcWarnView;
import com.ts.can.se.rzc.dx7.CanSeDx7RzcCarInfoView;
import com.ts.can.sitechdev.cw.CanSitechDevCwCarInfoView;
import com.ts.can.subuar.xp.CanSubuarCarInfoView;
import com.ts.can.swm.rzc.CanSwmRzcCarInfoView;
import com.ts.can.tata.wc.CanTataWcCarInfoView;
import com.ts.can.toyota.dj.CanToyotaDJCarInfoView;
import com.ts.can.volvo.lz.CanVolvoLZCarInfoView;
import com.ts.can.vw.rzc.golf.CanGolfRzcCarInfoView;
import com.ts.can.zh.wc.v3h3.CanZhWcCarInfoView;
import com.ts.can.zotye.x5.wc.CanZoyteX5WcCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.ts.main.common.MainSet;

public class CanBaseCarInfoActivity extends CanBaseActivity implements UserCallBack {
    protected CanBaseCarInfoView mBaseView;
    private CanItemMsgBox mMsgBox;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        Init(getIntent());
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Init(intent);
    }

    /* access modifiers changed from: protected */
    public void Init(Intent intent) {
        int id = 0;
        if (intent != null) {
            id = intent.getIntExtra("ID", 0);
        }
        InitView(CanJni.GetCanType(), id);
    }

    /* access modifiers changed from: protected */
    public void InitView(int canType, int id) {
        switch (canType) {
            case 2:
                this.mBaseView = new CanGolfRzcCarInfoView(this);
                return;
            case 14:
                this.mBaseView = new CanHyundaiCarInfoView(this);
                return;
            case 52:
                this.mBaseView = new CanDFCarInfoView(this);
                return;
            case 55:
                this.mBaseView = new CanKoleosTpmsView(this);
                return;
            case 70:
                if (CanJni.GetSubType() == 2) {
                    this.mBaseView = new CanDFFGRzcCarInfoView(this);
                    return;
                } else {
                    showDefaultDialog();
                    return;
                }
            case 110:
                this.mBaseView = new CanJACRefineWcCarInfoView(this);
                return;
            case 126:
                this.mBaseView = new CanDtT60RzcCarInfoView(this);
                return;
            case 146:
                this.mBaseView = new CanFordRzcCarInfoView(this);
                return;
            case 147:
                this.mBaseView = new CanLandWindCarInfoView(this);
                return;
            case 148:
                this.mBaseView = new CanPSAWCCarInfoView(this);
                return;
            case 149:
                this.mBaseView = new CanNissanWcCarInfoView(this);
                return;
            case Can.CAN_JAC_REFINE_OD:
                this.mBaseView = new CanJACRefineOdCarInfoView(this);
                return;
            case 151:
                this.mBaseView = new CanGMWcCarInfoView(this);
                return;
            case Can.CAN_AUDI_ZMYT:
                this.mBaseView = new CanAudiWithCDCarInfoView(this);
                return;
            case Can.CAN_HYUNDAI_WC:
                this.mBaseView = new CanHyundaiWcCarInfoView(this);
                return;
            case Can.CAN_CC_WC:
                this.mBaseView = new CanCCWcCarInfoView(this);
                return;
            case Can.CAN_HONDA_WC:
                this.mBaseView = new CanHondaWcCarInfoView(this);
                return;
            case Can.CAN_FORD_WC:
                this.mBaseView = new CanFordWcCarInfoView(this);
                return;
            case Can.CAN_DF_WC:
                this.mBaseView = new CanDfFsWcCarInfoView(this);
                return;
            case Can.CAN_CHANA_CS75_WC:
                this.mBaseView = new CanCs75WcCarInfoActivity(this);
                return;
            case 161:
                this.mBaseView = new CanTrumpchiWcCarInfoView(this);
                return;
            case 162:
                this.mBaseView = new CanChryslerWcCarInfoView(this);
                return;
            case 163:
                this.mBaseView = new CanBMWX1WcCarInfoView(this);
                return;
            case 164:
                this.mBaseView = new CanMzdWcCarInfoView(this);
                return;
            case 167:
                this.mBaseView = new CanDtV80CarInfoView(this);
                return;
            case 168:
                this.mBaseView = new CanCrownWcCarInfoView(this);
                return;
            case 169:
                this.mBaseView = new CanJeepWcCarInfoView(this);
                return;
            case 172:
                this.mBaseView = new CanWcH6CarInfoView(this);
                return;
            case 173:
                this.mBaseView = new CanCompassWcCarInfoView(this);
                return;
            case 174:
                this.mBaseView = new CanCCH2WcCarInfoView(this);
                return;
            case 176:
                this.mBaseView = new CanBmwZmytWithCDCarInfoView(this);
                return;
            case 177:
                this.mBaseView = new CanSaicRWMGWcCarInfoView(this);
                return;
            case 179:
                this.mBaseView = new CanBydWcCarInfoView(this);
                return;
            case 180:
                this.mBaseView = new CanCheryWcCarInfoView(this);
                return;
            case 182:
                this.mBaseView = new CanBaojunCarInfoView(this);
                return;
            case 191:
                this.mBaseView = new CanZoyteX5WcCarInfoView(this);
                return;
            case 194:
                this.mBaseView = new CanFordMondeoWcCarInfoView(this);
                return;
            case 196:
                this.mBaseView = new CanAccord9WcCarInfoView(this);
                return;
            case 197:
                this.mBaseView = new CanBaicHSS6WcCarInfoView(this);
                return;
            case 198:
                this.mBaseView = new CanPorscheOdCarInfoView(this);
                return;
            case 199:
                this.mBaseView = new CanNissanRzcCarInfoView(this);
                return;
            case 203:
                this.mBaseView = new CanSciDstCarInfoView(this);
                return;
            case 204:
                this.mBaseView = new CanMzdLzCarInfoView(this);
                return;
            case Can.CAN_LEXUS_IZ:
                this.mBaseView = new CanLexusLZIs250CarInfoView(this);
                return;
            case Can.CAN_SAIL_RW550_MG6_WC:
                this.mBaseView = new CanRW550MG6WcCarInfoView(this);
                return;
            case Can.CAN_LEXUS_ZMYT:
                this.mBaseView = new CanLexusZMYTCarInfoView(this);
                return;
            case 209:
                this.mBaseView = new CanObdBnrCarSetView(this);
                return;
            case 210:
            case 263:
                this.mBaseView = new CanGMCarInfoView(this);
                return;
            case 212:
                this.mBaseView = new CanHantElectCarInfoView(this);
                return;
            case 214:
                this.mBaseView = new CanChanaWcCarInfoView(this);
                return;
            case 216:
                this.mBaseView = new CanRenaultWcCarInfoView(this);
                return;
            case 217:
                this.mBaseView = new CanLuxgenOdCarInfoView(this);
                return;
            case 221:
                this.mBaseView = new CanPorscheLzCarInfoView(this);
                return;
            case Can.CAN_JIANGLING_MYX:
                this.mBaseView = new CanJiangLingMyxCarInfoView(this);
                return;
            case Can.CAN_TEANA_OLD_DJ:
                this.mBaseView = new CanTeanaOldDjCarInfoView(this);
                return;
            case Can.CAN_CC_HF_DJ:
                this.mBaseView = new CanHfDjCarInfoView(this);
                return;
            case Can.CAN_BYD_S6_S7:
                this.mBaseView = new CanBydS6S7CarBaseInfoSetView(this);
                return;
            case Can.CAN_SGMW_WC:
                this.mBaseView = new CanSgmwWcWarnView(this);
                return;
            case Can.CAN_ZH_WC:
                this.mBaseView = new CanZhWcCarInfoView(this);
                return;
            case Can.CAN_NISSAN_RICH6_WC:
                this.mBaseView = new CanNissanRich6WcCarInfoView(this);
                return;
            case Can.CAN_SE_DX7_RZC:
                this.mBaseView = new CanSeDx7RzcCarInfoView(this);
                return;
            case Can.CAN_GM_CAPTIVA_OD:
                this.mBaseView = new CanGmCaptivaOdCarInfoView(this);
                return;
            case 241:
                if (MainSet.GetInstance().bIsEnableCan()) {
                    this.mBaseView = new CanSitechDevCwCarInfoView(this);
                    return;
                }
                return;
            case Can.CAN_BYD_M6_DJ:
                this.mBaseView = new CanBydM6DjCarInfoView(this);
                return;
            case Can.CAN_CHRYSLER_TXB:
                this.mBaseView = new CanChryslerTxbCarInfoView(this);
                return;
            case Can.CAN_LUXGEN_WC:
                this.mBaseView = new CanLuxgenWCCarInfoView(this);
                return;
            case Can.CAN_MG_ZS_WC:
                this.mBaseView = new CanMGZSWCCarInfoView(this);
                return;
            case Can.CAN_FLAT_RZC:
                this.mBaseView = new CanFiatRzcCarInfoView(this);
                return;
            case 255:
                this.mBaseView = new CanChanACosCarInfoView(this);
                return;
            case 256:
                this.mBaseView = new CanMzdCx4BnrCarInfoView(this);
                return;
            case 257:
                this.mBaseView = new CanVolvoLZCarInfoView(this);
                return;
            case 258:
                this.mBaseView = new CanJYX5WcCarInfoView(this);
                return;
            case 260:
                this.mBaseView = new CanMzdRzcCarInfoView(this);
                return;
            case 262:
                this.mBaseView = new CanFordFiestaWcCarInfoView(this);
                return;
            case 264:
                this.mBaseView = new CanFiatWcCarInfoView(this);
                return;
            case 265:
                this.mBaseView = new CanMitsubshiRzcCarInfoView(this);
                return;
            case 266:
                this.mBaseView = new CanToyotaDJCarInfoView(this);
                return;
            case 267:
                this.mBaseView = new CanDfAx7OdCarInfoView(this);
                return;
            case 268:
                this.mBaseView = new CanBydRswCarInfoView(this);
                return;
            case 269:
                this.mBaseView = new CanChanaODCarInfoView(this);
                return;
            case 273:
                this.mBaseView = new CanMzdHcCarInfoView(this);
                return;
            case 274:
                this.mBaseView = new CanBMWLzCarInfoView(this);
                return;
            case 275:
                this.mBaseView = new CanLandWindOdCarInfoView(this);
                return;
            case 276:
                this.mBaseView = new CanLexusZMYTCarInfoView(this);
                return;
            case 277:
                this.mBaseView = new CanBMW2LzCarInfoView(this);
                return;
            case 278:
                this.mBaseView = new CanTataWcCarInfoView(this);
                return;
            case 280:
                this.mBaseView = new CanMahindraWcCarInfoView(this);
                return;
            case 282:
                this.mBaseView = new CanSwmRzcCarInfoView(this);
                return;
            case 284:
                this.mBaseView = new CanPSAScrRzcCarInfoView(this);
                return;
            case 285:
                this.mBaseView = new CanSubuarCarInfoView(this);
                return;
            case 286:
                this.mBaseView = new CanFawB30T3CarInfoView(this);
                return;
            case 287:
                this.mBaseView = new CanKaWeiWcCarInfoView(this);
                return;
            case 288:
                this.mBaseView = new CanHondaDaRzcCarInfoView(this);
                return;
            default:
                showDefaultDialog();
                return;
        }
    }

    private void showDefaultDialog() {
        if (this.mMsgBox == null) {
            this.mMsgBox = new CanItemMsgBox(0, this, R.string.can_not_support_msg, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanBaseCarInfoActivity.this.finish();
                }
            });
            this.mMsgBox.SetCancelCallBack(new CanItemMsgBox.onMsgBoxClick2() {
                public void onCancel(int param) {
                    CanBaseCarInfoActivity.this.finish();
                }
            });
            this.mMsgBox.getDlg().setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    CanBaseCarInfoActivity.this.finish();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        if (this.mBaseView != null) {
            this.mBaseView.doOnResume();
            this.mBaseView.ResetData(false);
            this.mBaseView.QueryData();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        if (this.mBaseView != null) {
            this.mBaseView.doOnPause();
        }
    }

    public void UserAll() {
        if (this.mBaseView != null) {
            this.mBaseView.ResetData(true);
            this.mBaseView.doOnFinish();
        }
    }
}
