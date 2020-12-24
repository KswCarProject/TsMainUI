package com.ts.can;

import android.content.Intent;
import android.os.Bundle;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.UserCallBack;
import com.ts.can.baic.ec180.CanBaicEcACView;
import com.ts.can.baic.od.senova.CanSenovaOdACView;
import com.ts.can.baic.wc.m50f.CanVenuciaWcM50FACView;
import com.ts.can.byd.dj.CanBYDDJACView;
import com.ts.can.byd.hcy.s6s7.CanBydS6S7ACView;
import com.ts.can.byd.rsw.CanBydRswACView;
import com.ts.can.cc.h6_rzc.CanCCH6RzcACView;
import com.ts.can.cc.wc.h2.CanCCH2WcACView;
import com.ts.can.cc.wc.h6.CanWcH6CarACView;
import com.ts.can.chana.cs75.CanChanAACView;
import com.ts.can.chana.wc.cos.CanChanACosACView;
import com.ts.can.chana.wc.cx70.CanChanAWcCx70ACView;
import com.ts.can.chery.wc.CanCheryWcCarACView;
import com.ts.can.chrysler.wc.CanChryslerWcACView;
import com.ts.can.chrysler.wc.compass.CanCompassWcACView;
import com.ts.can.df.ax7.CanDFAX7CarACView;
import com.ts.can.df.fg_rzc.CanDFFGRzcACView;
import com.ts.can.df.wc.CanVenuciaWcACView;
import com.ts.can.df.wc.ax7.CanDfFsWcACView;
import com.ts.can.faw.B50.CanB50RzcACView;
import com.ts.can.ford.wc.CanFordWcCarACView;
import com.ts.can.ford.wc.mondeo.CanFordMondeoWcCarACView;
import com.ts.can.ford.xfy.CanFordEdgeXfyACView;
import com.ts.can.gac.trumpchi_wc.CanTrumpchiWcACView;
import com.ts.can.geely.rzc.CanGeelyRzcACView;
import com.ts.can.gm.rzc.CanGL8ACView;
import com.ts.can.gm.wc.CanGMWcACView;
import com.ts.can.hm.wc.fml.CanHMFMLWcCarACView;
import com.ts.can.hm.wc.v70.CanHMV70WcCarACView;
import com.ts.can.honda.cyt.accord7.CanAccord7CYTACView;
import com.ts.can.honda.rzc.CanHondaDaRzcAcView;
import com.ts.can.honda.wc.CanHondaWcACView;
import com.ts.can.honda.wc.crown.CanCrownWcACView;
import com.ts.can.jiangling.myx.CanJiangLingMyxACView;
import com.ts.can.kawei.wc.CanKaWeiWcACView;
import com.ts.can.landwind.od.CanLandWindOdAcView;
import com.ts.can.lexus.lz.CanLexusLZIs250ACView;
import com.ts.can.nissan.dj.teana.CanTeanaOldDjACView;
import com.ts.can.renault.renault.CanRenaultLuoMuAcView;
import com.ts.can.saic.wc.CanSaicRWMGWcACView;
import com.ts.can.sitechdev.cw.CanSitechDevCwACView;
import com.ts.can.swm.rzc.CanSwmRzcACView;
import com.ts.can.tata.wc.CanTataWcACView;
import com.ts.can.toyota.dj.CanToyotaDJACView;
import com.ts.can.toyota.rzc.CanToyotaRzcACView;
import com.ts.can.vw.rzc.golf.CanGolfRzcACView;
import com.ts.can.vw.rzc.golf.CanGolfRzcTeramonACView;
import com.ts.can.zotye.x5.CanZotyetT500ACView;
import com.ts.main.common.MainSet;

public class CanBaseACActivity extends CanBaseActivity implements UserCallBack {
    protected static final String TAG = "CanBaseACActivity";
    public static boolean isACShow;
    private static boolean isShowing;
    protected CanBaseACView mBaseACView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitView(CanJni.GetCanType(), CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        InitView(CanJni.GetCanType(), CanJni.GetSubType());
    }

    private void InitView(int canType, int subType) {
        switch (canType) {
            case 2:
                if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 4) {
                    this.mBaseACView = new CanGolfRzcTeramonACView(this);
                    return;
                } else {
                    this.mBaseACView = new CanGolfRzcACView(this);
                    return;
                }
            case 52:
                this.mBaseACView = new CanDFAX7CarACView(this);
                return;
            case 60:
                this.mBaseACView = new CanZotyetT500ACView(this);
                return;
            case 70:
                this.mBaseACView = new CanDFFGRzcACView(this);
                return;
            case 71:
                this.mBaseACView = new CanB50RzcACView(this);
                return;
            case 72:
                this.mBaseACView = new CanGeelyRzcACView(this);
                return;
            case 73:
                this.mBaseACView = new CanChanAACView(this);
                return;
            case 78:
                this.mBaseACView = new CanCCH6RzcACView(this);
                return;
            case 98:
                this.mBaseACView = new CanBaicEcACView(this);
                return;
            case 117:
                if (CanFunc.getInstance().IsCustomShow("LM_RENAULT_UI6")) {
                    this.mBaseACView = new CanRenaultLuoMuAcView(this);
                    return;
                }
                return;
            case 128:
                this.mBaseACView = new CanToyotaRzcACView(this);
                return;
            case 151:
                this.mBaseACView = new CanGMWcACView(this);
                return;
            case Can.CAN_HONDA_WC:
                this.mBaseACView = new CanHondaWcACView(this);
                return;
            case Can.CAN_FORD_WC:
                this.mBaseACView = new CanFordWcCarACView(this);
                return;
            case Can.CAN_DF_WC:
                this.mBaseACView = new CanDfFsWcACView(this);
                return;
            case 161:
                this.mBaseACView = new CanTrumpchiWcACView(this);
                return;
            case 162:
                this.mBaseACView = new CanChryslerWcACView(this);
                return;
            case 166:
                this.mBaseACView = new CanVenuciaWcACView(this);
                return;
            case 168:
                this.mBaseACView = new CanCrownWcACView(this);
                return;
            case 172:
                this.mBaseACView = new CanWcH6CarACView(this);
                return;
            case 173:
                this.mBaseACView = new CanCompassWcACView(this);
                return;
            case 174:
                this.mBaseACView = new CanCCH2WcACView(this);
                return;
            case 177:
                this.mBaseACView = new CanSaicRWMGWcACView(this);
                return;
            case 180:
                this.mBaseACView = new CanCheryWcCarACView(this);
                return;
            case 192:
                this.mBaseACView = new CanHMV70WcCarACView(this);
                return;
            case 193:
                this.mBaseACView = new CanHMFMLWcCarACView(this);
                return;
            case 194:
                this.mBaseACView = new CanFordMondeoWcCarACView(this);
                return;
            case 200:
                this.mBaseACView = new CanBYDDJACView(this);
                return;
            case 201:
                this.mBaseACView = new CanAccord7CYTACView(this);
                return;
            case Can.CAN_LEXUS_IZ:
                this.mBaseACView = new CanLexusLZIs250ACView(this);
                return;
            case 210:
            case 263:
                this.mBaseACView = new CanGL8ACView(this);
                return;
            case 219:
                this.mBaseACView = new CanVenuciaWcM50FACView(this);
                return;
            case 220:
                this.mBaseACView = new CanChanAWcCx70ACView(this);
                return;
            case 222:
                this.mBaseACView = new CanSenovaOdACView(this);
                return;
            case Can.CAN_JIANGLING_MYX:
                this.mBaseACView = new CanJiangLingMyxACView(this);
                return;
            case Can.CAN_TEANA_OLD_DJ:
                this.mBaseACView = new CanTeanaOldDjACView(this);
                return;
            case Can.CAN_BYD_S6_S7:
                this.mBaseACView = new CanBydS6S7ACView(this);
                return;
            case 241:
                if (MainSet.GetInstance().bIsEnableCan()) {
                    this.mBaseACView = new CanSitechDevCwACView(this);
                    return;
                }
                return;
            case Can.CAN_FORD_EDGE_XFY:
                this.mBaseACView = new CanFordEdgeXfyACView(this);
                return;
            case 255:
                this.mBaseACView = new CanChanACosACView(this);
                return;
            case 266:
                this.mBaseACView = new CanToyotaDJACView(this);
                return;
            case 268:
                this.mBaseACView = new CanBydRswACView(this);
                return;
            case 275:
                this.mBaseACView = new CanLandWindOdAcView(this);
                return;
            case 278:
                this.mBaseACView = new CanTataWcACView(this);
                return;
            case 282:
                this.mBaseACView = new CanSwmRzcACView(this);
                return;
            case 287:
                this.mBaseACView = new CanKaWeiWcACView(this);
                return;
            case 288:
                this.mBaseACView = new CanHondaDaRzcAcView(this);
                return;
            default:
                if (CanFunc.IsCanActivityJumped(this)) {
                    CanFunc.showCanActivity(CanACActivity.class);
                }
                finish();
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        isShowing = true;
        MainTask.GetInstance().SetUserCallBack(this);
        if (this.mBaseACView != null) {
            this.mBaseACView.doOnResume();
            this.mBaseACView.QueryData();
            this.mBaseACView.ResetData(false);
        }
        Can.updateAC();
        updateACUI();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        isShowing = false;
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        if (this.mBaseACView != null) {
            this.mBaseACView.doOnPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        isACShow = false;
        if (this.mBaseACView != null) {
            this.mBaseACView.doOnDestory();
        }
    }

    private void updateACUI() {
        if (this.mBaseACView != null) {
            this.mBaseACView.updateACUI();
        }
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (this.mBaseACView != null) {
            this.mBaseACView.ResetData(true);
            this.mBaseACView.doOnFinish();
        }
    }

    public static void ShowAC() {
        if (!isShowing) {
            CanFunc.showCanActivity(CanBaseACActivity.class);
        }
    }
}
