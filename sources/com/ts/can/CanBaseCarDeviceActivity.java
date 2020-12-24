package com.ts.can;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.blsu.od.CanBlsuOdDvrView;
import com.ts.can.bmw.lz.CanBMWLzHighCDView;
import com.ts.can.bmw.lz.CanBMWLzLowCDView;
import com.ts.can.bmw.lz.bmw2.CanBMW2LzCarcomputerView;
import com.ts.can.cc.dj.hf.CanHfDjExdView;
import com.ts.can.chery.airuize.CanCheryAiRuizeDvrView;
import com.ts.can.chery.wc.CanCheryWcDvrView;
import com.ts.can.df.jyx5.CanJYX5RzcDvrView;
import com.ts.can.honda.od.CanHondaODCarDeviceView;
import com.ts.can.honda.wc.CanHondaWcRadioView;
import com.ts.can.honda.wc.CanHondaWcUsbIpodView;
import com.ts.can.honda.wc.civic.CanHondaWcCivicExdView;
import com.ts.can.honda.wc.crown.CanCrownWcCDView;
import com.ts.can.honda.wc.crown.CanCrownWcCarDeviceView;
import com.ts.can.honda.wc.crown.CanCrownWcRadioView;
import com.ts.can.honda.xbs.accord8.CanAccord8XbsExdView;
import com.ts.can.lexus.lz.CanLexusLZIs250CarDevView;
import com.ts.can.lexus.zmyt.CanLexusZMYTCarDevView;
import com.ts.can.lexus.zmyt.CanLexusZMYTCarInitView;
import com.ts.can.lexus.zmyt.h.CanLexushZmytCarDevView;
import com.ts.can.luxgen.od.CanLuxgenOdCarDeviceView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrCDView;
import com.ts.can.mzd.luomu.CanLuoMuMzdExCdView;
import com.ts.can.mzd.rzc.CanMzdRzcCDView;
import com.ts.can.mzd.rzc.CanMzdRzcCarDeviceView;
import com.ts.can.mzd.rzc.CanMzdRzcRadioView;
import com.ts.can.mzd.rzc.CanMzdRzcTxtView;
import com.ts.can.nissan.dj.teana.CanTeanaOldDjCarDeviceView;
import com.ts.can.nissan.rzc.CanNissanRzcTeanaOldTwView;
import com.ts.can.nissan.wc.teana.CanTeanaWcCarDeviceView;
import com.ts.can.psa.rzc.scr.CanPSAScrRzcCarDeviceView;
import com.ts.can.renault.kadjar.CanKadjarExdView;
import com.ts.can.toyota.dj.CanToyotaDJCarDeviceView;
import com.ts.canview.CanItemMsgBox;

public class CanBaseCarDeviceActivity extends CanBaseActivity implements UserCallBack {
    protected CanBaseCarInfoView mBaseView;
    private CanItemMsgBox mMsgBox;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        CreateInit();
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
            case 49:
                if (5 == CanJni.GetSubType()) {
                    this.mBaseView = new CanJYX5RzcDvrView(this);
                    return;
                }
                return;
            case 55:
                if (CanJni.GetSubType() == 4) {
                    this.mBaseView = new CanKadjarExdView(this);
                    return;
                }
                return;
            case 57:
                if (7 == CanJni.GetSubType()) {
                    this.mBaseView = new CanCheryAiRuizeDvrView(this);
                    return;
                }
                return;
            case Can.CAN_HONDA_WC:
                if (7 == CanJni.GetSubType()) {
                    this.mBaseView = new CanHondaWcRadioView(this);
                    return;
                } else if (8 == CanJni.GetSubType()) {
                    this.mBaseView = new CanHondaWcUsbIpodView(this);
                    return;
                } else {
                    return;
                }
            case 168:
                if (id == -1) {
                    this.mBaseView = new CanCrownWcRadioView(this);
                    return;
                } else if (id == -2) {
                    this.mBaseView = new CanCrownWcCDView(this);
                    return;
                } else if (id == -3) {
                    this.mBaseView = new CanCrownWcCarDeviceView(this);
                    return;
                } else {
                    return;
                }
            case 171:
                this.mBaseView = new CanHondaWcCivicExdView(this);
                return;
            case 180:
                if (8 == CanJni.GetSubType()) {
                    this.mBaseView = new CanCheryWcDvrView(this);
                    return;
                }
                return;
            case 181:
                this.mBaseView = new CanHondaODCarDeviceView(this);
                return;
            case 183:
                this.mBaseView = new CanTeanaWcCarDeviceView(this);
                return;
            case 199:
                if (7 == CanJni.GetSubType()) {
                    this.mBaseView = new CanNissanRzcTeanaOldTwView(this);
                    return;
                }
                return;
            case Can.CAN_LEXUS_IZ:
                this.mBaseView = new CanLexusLZIs250CarDevView(this);
                return;
            case Can.CAN_LEXUS_ZMYT:
                this.mBaseView = new CanLexusZMYTCarDevView(this);
                return;
            case 217:
                this.mBaseView = new CanLuxgenOdCarDeviceView(this);
                return;
            case Can.CAN_TEANA_OLD_DJ:
                this.mBaseView = new CanTeanaOldDjCarDeviceView(this);
                return;
            case Can.CAN_CC_HF_DJ:
                this.mBaseView = new CanHfDjExdView(this);
                return;
            case Can.CAN_MZD_LUOMU:
                this.mBaseView = new CanLuoMuMzdExCdView(this);
                return;
            case 256:
                this.mBaseView = new CanMzdCx4BnrCDView(this);
                return;
            case 259:
                this.mBaseView = new CanBlsuOdDvrView(this);
                return;
            case 260:
                if (id == -1) {
                    this.mBaseView = new CanMzdRzcRadioView(this);
                    return;
                } else if (id == -2) {
                    this.mBaseView = new CanMzdRzcCDView(this);
                    return;
                } else if (id == -3) {
                    this.mBaseView = new CanMzdRzcTxtView(this);
                    return;
                } else if (CanJni.GetSubType() == 0 || 1 == CanJni.GetSubType()) {
                    this.mBaseView = new CanMzdRzcCDView(this);
                    return;
                } else {
                    this.mBaseView = new CanMzdRzcCarDeviceView(this);
                    return;
                }
            case 261:
                this.mBaseView = new CanAccord8XbsExdView(this);
                return;
            case 266:
                this.mBaseView = new CanToyotaDJCarDeviceView(this);
                return;
            case 274:
                if (CanJni.GetSubType() == 0) {
                    this.mBaseView = new CanBMWLzLowCDView(this);
                    return;
                } else if (CanJni.GetSubType() == 1) {
                    this.mBaseView = new CanBMWLzHighCDView(this);
                    return;
                } else {
                    return;
                }
            case 276:
                this.mBaseView = new CanLexushZmytCarDevView(this);
                return;
            case 277:
                this.mBaseView = new CanBMW2LzCarcomputerView(this);
                return;
            case 284:
                this.mBaseView = new CanPSAScrRzcCarDeviceView(this);
                return;
            default:
                if (this.mMsgBox == null) {
                    this.mMsgBox = new CanItemMsgBox(0, this, R.string.can_not_support_msg, new CanItemMsgBox.onMsgBoxClick() {
                        public void onOK(int param) {
                            CanBaseCarDeviceActivity.this.finish();
                        }
                    });
                    this.mMsgBox.SetCancelCallBack(new CanItemMsgBox.onMsgBoxClick2() {
                        public void onCancel(int param) {
                            CanBaseCarDeviceActivity.this.finish();
                        }
                    });
                    this.mMsgBox.getDlg().setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface arg0) {
                            CanBaseCarDeviceActivity.this.finish();
                        }
                    });
                    return;
                }
                return;
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

    public void onBackPressed() {
        int canSubType = CanJni.GetSubType();
        switch (CanJni.GetCanType()) {
            case Can.CAN_HONDA_WC:
                if (canSubType == 7 && (this.mBaseView instanceof CanHondaWcRadioView) && ((CanHondaWcRadioView) this.mBaseView).setPage()) {
                    return;
                }
            case 260:
                if ((this.mBaseView instanceof CanMzdRzcRadioView) && ((CanMzdRzcRadioView) this.mBaseView).setPage()) {
                    return;
                }
        }
        super.onBackPressed();
    }

    public void CreateInit() {
        switch (CanJni.GetCanType()) {
            case Can.CAN_LEXUS_ZMYT:
                if (CanLexusZMYTCarInitView.HostRes() != 0) {
                    getWindow().setFlags(1024, 1024);
                    return;
                }
                return;
            case 276:
            case 277:
                getWindow().setFlags(1024, 1024);
                return;
            default:
                return;
        }
    }
}
