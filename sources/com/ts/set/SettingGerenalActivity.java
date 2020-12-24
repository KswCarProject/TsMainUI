package com.ts.set;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;
import com.ts.main.txz.TxzReg;
import com.ts.set.setview.SetItemDialog;
import com.ts.set.setview.SetItemPopupList;
import com.ts.set.setview.SetMainItemNoIcon;
import com.ts.set.setview.SetMainItemSw;
import com.ts.set.setview.SetMainItemTopName;
import com.ts.set.setview.SettingNumInuptDlg;
import com.ts.set.setview.UISetSroView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;

public class SettingGerenalActivity extends Activity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, SetItemPopupList.onPopItemClick, SettingNumInuptDlg.onInputOK {
    private static final String TAG = "SettingGerenalActivity";
    SetItemPopupList BackCarVolume;
    SetItemDialog BootDialog;
    SettingNumInuptDlg BootLogoDialog;
    SetItemDialog FtSetDialog;
    SetItemPopupList IdleDelayTime;
    SetMainItemSw KeyTouchSW;
    Context MyContext;
    SetMainItemNoIcon SystemBoot;
    SetMainItemNoIcon SystemResume;
    SettingNumInuptDlg SystemresumeDialog;
    SetItemDialog VoiceDialog;
    SetItemPopupList VoiceLin;
    SetItemPopupList VoiceState;
    SetMainItemNoIcon bootLogo;
    int[] nBackCarValue = {R.string.set_general_backcarvol_none, R.string.set_general_backcarvol_min, R.string.set_general_backcarvol_mid, R.string.set_general_backcarvol_high};
    int[] nIdleTimeValue = {R.string.set_general_idletime_0, R.string.set_general_idletime_10M, R.string.set_general_idletime_20M, R.string.set_general_idletime_30M, R.string.set_general_idletime_60M};
    int[] nVmcdValue = {R.string.set_general_voice_min, R.string.set_general_voice_mid, R.string.set_general_voice_high};
    int[] nVoiceState = {R.string.set_general_voice_wakeup_all, R.string.set_general_voice_wakeup_v, R.string.set_general_voice_wakeup_b, R.string.set_general_voice_wakeup_off};
    private String[] setGeneraOptions;
    SetMainItemTopName topname;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UISetSroView.Inint(this);
        initSetGeneraOptions();
        UISetSroView.Show(this);
        this.MyContext = this;
    }

    /* access modifiers changed from: package-private */
    public boolean bIsBeep() {
        return Settings.System.getInt(getContentResolver(), "sound_effects_enabled", 1) != 0;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.IdleDelayTime.SetSel(FtSet.GetStandByTime());
        this.KeyTouchSW.SetSwitch(FtSet.GetTouchBall());
        Log.i(TAG, "StSet.GetParkMute()=" + StSet.GetParkMute());
        Log.i(TAG, "StSet.GetSpeechMode()=" + FtSet.GetSpeechMode());
        this.VoiceState.SetSel(FtSet.GetSpeechMode());
        this.BackCarVolume.SetSel(StSet.GetParkMute());
        this.VoiceLin.SetSel(FtSet.GetXuNiDisc());
        super.onResume();
    }

    private boolean isHaveOption(int setOption) {
        return true;
    }

    private boolean isZh() {
        if (getResources().getConfiguration().locale.getLanguage().endsWith("zh")) {
            return true;
        }
        return false;
    }

    private void initSetGeneraOptions() {
        this.topname = new SetMainItemTopName(this, getResources().getStringArray(R.array.set_main_options)[0]);
        this.topname.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingGerenalActivity.this.finish();
            }
        });
        UISetSroView.AddView(this.topname.GetView(), 1280, 80);
        this.setGeneraOptions = getResources().getStringArray(R.array.set_genera_options);
        for (int setOpt = 0; setOpt < 8; setOpt++) {
            if (isHaveOption(setOpt)) {
                switch (setOpt) {
                    case 0:
                        this.IdleDelayTime = new SetItemPopupList((Context) this, 0, this.nIdleTimeValue, (SetItemPopupList.onPopItemClick) this);
                        this.IdleDelayTime.SetId(setOpt);
                        this.IdleDelayTime.GetBtn().setText(this.setGeneraOptions[setOpt]);
                        UISetSroView.AddView(this.IdleDelayTime.GetView());
                        break;
                    case 1:
                        this.VoiceState = new SetItemPopupList((Context) this, 0, this.nVoiceState, (SetItemPopupList.onPopItemClick) this);
                        this.VoiceState.SetId(setOpt);
                        this.VoiceState.GetBtn().setText(this.setGeneraOptions[setOpt]);
                        this.VoiceLin = new SetItemPopupList((Context) this, 0, this.nVmcdValue, (SetItemPopupList.onPopItemClick) this);
                        this.VoiceLin.SetId(5);
                        this.VoiceLin.GetBtn().setText(this.setGeneraOptions[5]);
                        if (isZh() && MainSet.GetInstance().IsHaveApk("com.txznet.txz")) {
                            UISetSroView.AddView(this.VoiceState.GetView());
                            UISetSroView.AddView(this.VoiceLin.GetView());
                            break;
                        }
                    case 2:
                        this.KeyTouchSW = new SetMainItemSw(this, this.setGeneraOptions[setOpt]);
                        this.KeyTouchSW.SetUserCallback(setOpt, this);
                        UISetSroView.AddView(this.KeyTouchSW.GetView());
                        break;
                    case 3:
                        this.BackCarVolume = new SetItemPopupList((Context) this, 0, this.nBackCarValue, (SetItemPopupList.onPopItemClick) this);
                        this.BackCarVolume.SetId(setOpt);
                        this.BackCarVolume.GetBtn().setText(this.setGeneraOptions[setOpt]);
                        UISetSroView.AddView(this.BackCarVolume.GetView());
                        break;
                    case 4:
                        this.bootLogo = new SetMainItemNoIcon(this, this.setGeneraOptions[setOpt]);
                        this.bootLogo.SetUserCallback(setOpt, this);
                        if (FtSet.GetOptionLogo() != 1) {
                            break;
                        } else {
                            UISetSroView.AddView(this.bootLogo.GetView());
                            break;
                        }
                    case 6:
                        this.SystemResume = new SetMainItemNoIcon(this, this.setGeneraOptions[setOpt]);
                        this.SystemResume.SetUserCallback(setOpt, this);
                        UISetSroView.AddView(this.SystemResume.GetView());
                        break;
                    case 7:
                        this.SystemBoot = new SetMainItemNoIcon(this, this.setGeneraOptions[setOpt]);
                        this.SystemBoot.SetUserCallback(setOpt, this);
                        if (MainSet.GetInstance().Is3561() && getResources().getIdentifier("system_recover_optioned", "string", getPackageName()) <= 0) {
                            UISetSroView.AddView(this.SystemBoot.GetView());
                            break;
                        }
                }
            }
        }
    }

    public void onCheckedChanged(CompoundButton arg0, boolean bRet) {
        int i;
        int nRet = 0;
        if (bRet) {
            nRet = 1;
        }
        switch (((Integer) arg0.getTag()).intValue()) {
            case 2:
                if (bRet) {
                    Intent intent = new Intent();
                    intent.setPackage("com.ts.mytouch");
                    intent.setAction("com.ts.mytouch.TouchService");
                    startService(intent);
                } else {
                    Intent intent2 = new Intent();
                    intent2.setPackage("com.ts.mytouch");
                    intent2.setAction("com.ts.mytouch.TouchService");
                    stopService(intent2);
                }
                if (nRet != FtSet.GetTouchBall()) {
                    FtSet.SetTouchBall(nRet);
                }
                SetMainItemSw setMainItemSw = this.KeyTouchSW;
                if (bRet) {
                    i = 1;
                } else {
                    i = 0;
                }
                setMainItemSw.SetSwitch(i);
                return;
            default:
                return;
        }
    }

    public void onClick(View arg0) {
        switch (((Integer) arg0.getTag()).intValue()) {
            case 4:
                this.BootLogoDialog = new SettingNumInuptDlg();
                this.BootLogoDialog.createDlg(this, this, 4);
                return;
            case 6:
                this.FtSetDialog = new SetItemDialog(this, R.string.set_general_Recovery, new View.OnClickListener() {
                    public void onClick(View arg0) {
                        switch (((Integer) arg0.getTag()).intValue()) {
                            case 16:
                                StSet.SetDefault();
                                Mcu.SendXKey(19);
                                SettingGerenalActivity.this.FtSetDialog.Hide();
                                return;
                            case 17:
                                SettingGerenalActivity.this.FtSetDialog.Hide();
                                return;
                            default:
                                return;
                        }
                    }
                });
                return;
            case 7:
                if (MainSet.GetInstance().IsCustom("XPH")) {
                    this.SystemresumeDialog = new SettingNumInuptDlg();
                    this.SystemresumeDialog.createDlg(this, this, 4);
                    return;
                }
                this.BootDialog = new SetItemDialog(this, R.string.set_general_boot, new View.OnClickListener() {
                    public void onClick(View arg0) {
                        switch (((Integer) arg0.getTag()).intValue()) {
                            case 16:
                                Mcu.SendXKey(21);
                                SettingGerenalActivity.this.sendBroadcast(new Intent("android.intent.action.MASTER_CLEAR"));
                                SettingGerenalActivity.this.BootDialog.Hide();
                                return;
                            case 17:
                                SettingGerenalActivity.this.BootDialog.Hide();
                                return;
                            default:
                                return;
                        }
                    }
                });
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 0:
                FtSet.SetStandByTime(item);
                this.IdleDelayTime.SetSel(item);
                return;
            case 1:
                FtSet.SetSpeechMode(item);
                this.VoiceState.SetSel(item);
                TxzReg.GetInstance().SetTXZState(FtSet.GetSpeechMode());
                return;
            case 3:
                StSet.SetParkMute(item);
                this.BackCarVolume.SetSel(item);
                Log.i(TAG, "item=" + item);
                return;
            case 5:
                FtSet.SetXuNiDisc(item);
                this.VoiceLin.SetSel(item);
                TxzReg.GetInstance().InintLinMin();
                return;
            default:
                return;
        }
    }

    public void onOK(String val) {
        if (val.equals(getResources().getString(R.string.custom_bootlogo_num))) {
            WinShow.GotoWin(17, 0);
        } else if (val.equals("2648")) {
            Mcu.SendXKey(21);
            sendBroadcast(new Intent("android.intent.action.MASTER_CLEAR"));
        }
    }
}
