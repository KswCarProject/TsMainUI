package com.ts.set;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import com.ts.MainUI.R;
import com.ts.MainUI.TsFile;
import com.ts.bt.BtExe;
import com.ts.set.setview.SetMainItemNoIcon;
import com.ts.set.setview.SetMainItemSw;
import com.ts.set.setview.SetMainItemTopName;
import com.ts.set.setview.SettingNumInuptDlg;
import com.ts.set.setview.UISetSroView;

public class SettingBtActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SettingNumInuptDlg.onInputOK {
    private static final String TAG = "SettingBtActivity";
    SetMainItemSw BtAudoAnswer;
    SetMainItemSw BtAutoConnect;
    SettingNumInuptDlg BtCodeInput;
    SetMainItemNoIcon BtPinCode;
    private boolean bBtFcMode;
    private boolean mIsSetting = false;
    private String[] setBtOptions;
    SetMainItemTopName topname;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UISetSroView.Inint(this);
        initBtOptions();
        UISetSroView.Show(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        int i;
        int i2 = 1;
        BtExe.getBtInstance().readDeviceNamePin();
        this.BtPinCode.ShowEndInfo(BtExe.getBtInstance().getDevPin());
        this.mIsSetting = true;
        Log.d(TAG, "isAutoConnect = " + BtExe.getBtInstance().isAutoConnect() + ", isAutoAnswer = " + BtExe.getBtInstance().isAutoAnswer());
        SetMainItemSw setMainItemSw = this.BtAutoConnect;
        if (BtExe.getBtInstance().isAutoConnect()) {
            i = 1;
        } else {
            i = 0;
        }
        setMainItemSw.SetSwitch(i);
        SetMainItemSw setMainItemSw2 = this.BtAudoAnswer;
        if (!BtExe.getBtInstance().isAutoAnswer()) {
            i2 = 0;
        }
        setMainItemSw2.SetSwitch(i2);
        this.mIsSetting = false;
        super.onResume();
    }

    private void initBtOptions() {
        this.topname = new SetMainItemTopName(this, getResources().getStringArray(R.array.set_main_options)[6]);
        this.topname.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingBtActivity.this.finish();
            }
        });
        UISetSroView.AddView(this.topname.GetView(), 1280, 80);
        this.setBtOptions = getResources().getStringArray(R.array.set_bt_options);
        for (int setOpt = 0; setOpt < 3; setOpt++) {
            switch (setOpt) {
                case 0:
                    this.BtPinCode = new SetMainItemNoIcon(this, this.setBtOptions[setOpt]);
                    this.BtPinCode.SetUserCallback(setOpt, this);
                    this.bBtFcMode = SystemProperties.getBoolean("persist.atc.bt.disablessp", false);
                    if (!TsFile.fileIsExists("system/etc/bluetooth/usepin.ini") && !this.bBtFcMode) {
                        break;
                    } else {
                        UISetSroView.AddView(this.BtPinCode.GetView(), 1280, 85);
                        break;
                    }
                case 1:
                    this.BtAutoConnect = new SetMainItemSw(this, this.setBtOptions[setOpt]);
                    this.BtAutoConnect.SetUserCallback(setOpt, this);
                    UISetSroView.AddView(this.BtAutoConnect.GetView(), 1280, 85);
                    break;
                case 2:
                    this.BtAudoAnswer = new SetMainItemSw(this, this.setBtOptions[setOpt]);
                    this.BtAudoAnswer.SetUserCallback(setOpt, this);
                    UISetSroView.AddView(this.BtAudoAnswer.GetView(), 1280, 85);
                    break;
            }
        }
    }

    public void onClick(View arg0) {
        switch (((Integer) arg0.getTag()).intValue()) {
            case 0:
                this.BtCodeInput = new SettingNumInuptDlg();
                this.BtCodeInput.createDlg(this, this, 4);
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(CompoundButton v, boolean bRet) {
        int i;
        int i2 = 1;
        if (!this.mIsSetting) {
            switch (((Integer) v.getTag()).intValue()) {
                case 1:
                    SetMainItemSw setMainItemSw = this.BtAutoConnect;
                    if (bRet) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    setMainItemSw.SetSwitch(i);
                    BtExe.getBtInstance().autoConnectSw();
                    return;
                case 2:
                    SetMainItemSw setMainItemSw2 = this.BtAudoAnswer;
                    if (!bRet) {
                        i2 = 0;
                    }
                    setMainItemSw2.SetSwitch(i2);
                    BtExe.getBtInstance().autoAnswerSw();
                    return;
                default:
                    return;
            }
        }
    }

    public void onOK(String val) {
        if (val.length() == 4) {
            BtExe.getBtInstance().setDevPin(val);
            Log.d(TAG, "in = " + val + ", Pin = " + BtExe.getBtInstance().getDevPin());
            this.BtPinCode.ShowEndInfo(val);
        }
    }
}
