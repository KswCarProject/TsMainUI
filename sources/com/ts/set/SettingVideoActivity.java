package com.ts.set;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanIF;
import com.ts.factoryset.FsBaseActivity;
import com.ts.main.common.MainSet;
import com.ts.set.setview.SetItemDialog;
import com.ts.set.setview.SetItemPopupList;
import com.ts.set.setview.SetMainItemSw;
import com.ts.set.setview.SetMainItemTopName;
import com.ts.set.setview.SettingNumInuptDlg;
import com.ts.set.setview.UISetSroView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;

public class SettingVideoActivity extends Activity implements CompoundButton.OnCheckedChangeListener, SetItemPopupList.onPopItemClick {
    private static final String TAG = "SettingVideoActivity";
    /* access modifiers changed from: private */
    public static boolean bPswChecked = false;
    SetItemDialog BootDialog = null;
    SetItemPopupList CameraType;
    SetItemPopupList IdleFrontCamTime;
    SetItemDialog SetCamMir;
    SetMainItemSw[] VideoBtn = new SetMainItemSw[8];
    int[] nFrontTimeValue = {R.string.set_common_switch_off, R.string.set_video_frontcam_time3, R.string.set_video_frontcam_time5, R.string.set_video_frontcam_time8};
    private String[] setvideoOptions;
    SetMainItemTopName topname;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UISetSroView.Inint(this);
        initVideoOptions();
        UISetSroView.Show(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.VideoBtn[0].SetSwitch(StSet.GetDriveVideo());
        this.VideoBtn[1].SetSwitch(FtSet.GetCamTrack());
        this.VideoBtn[2].SetSwitch(FtSet.GetCamLine());
        this.VideoBtn[3].SetSwitch(FtSet.GetCamMir());
        this.VideoBtn[4].SetSwitch(StSet.GetAcDisplay());
        this.VideoBtn[5].SetSwitch(FtSet.GetGnssMode());
        this.IdleFrontCamTime.SetSel(FtSet.GetFcamTime());
        this.CameraType.SetSel(FtSet.GetCamType());
        super.onResume();
    }

    private void initVideoOptions() {
        this.topname = new SetMainItemTopName(this, getResources().getStringArray(R.array.set_main_options)[4]);
        this.topname.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingVideoActivity.this.finish();
            }
        });
        UISetSroView.AddView(this.topname.GetView(), 1024, -2);
        this.setvideoOptions = getResources().getStringArray(R.array.set_video_options);
        for (int setOpt = 0; setOpt < 8; setOpt++) {
            switch (setOpt) {
                case 0:
                    this.VideoBtn[setOpt] = new SetMainItemSw(this, this.setvideoOptions[setOpt]);
                    this.VideoBtn[setOpt].SetUserCallback(setOpt, this);
                    if (FtSet.GetBrakeDef() != 0 && FtSet.GetBrakeDef() != 3) {
                        break;
                    } else {
                        UISetSroView.AddView(this.VideoBtn[setOpt].GetView());
                        break;
                    }
                case 1:
                    this.VideoBtn[setOpt] = new SetMainItemSw(this, this.setvideoOptions[setOpt]);
                    this.VideoBtn[setOpt].SetUserCallback(setOpt, this);
                    if (CanJni.GetCanType() != 0 && CanIF.IsSetMenuRvsAssistLineAvalid()) {
                        UISetSroView.AddView(this.VideoBtn[setOpt].GetView());
                        break;
                    }
                case 2:
                    this.VideoBtn[setOpt] = new SetMainItemSw(this, this.setvideoOptions[setOpt]);
                    this.VideoBtn[setOpt].SetUserCallback(setOpt, this);
                    UISetSroView.AddView(this.VideoBtn[setOpt].GetView());
                    break;
                case 3:
                    this.VideoBtn[setOpt] = new SetMainItemSw(this, this.setvideoOptions[setOpt]);
                    this.VideoBtn[setOpt].SetUserCallback(setOpt, this);
                    UISetSroView.AddView(this.VideoBtn[setOpt].GetView());
                    break;
                case 4:
                    this.VideoBtn[setOpt] = new SetMainItemSw(this, this.setvideoOptions[setOpt]);
                    this.VideoBtn[setOpt].SetUserCallback(setOpt, this);
                    if (CanJni.GetCanType() != 0 && CanIF.IsSetMenuAirConditionerAvalid()) {
                        UISetSroView.AddView(this.VideoBtn[setOpt].GetView());
                        break;
                    }
                case 5:
                    this.VideoBtn[setOpt] = new SetMainItemSw(this, this.setvideoOptions[setOpt]);
                    this.VideoBtn[setOpt].SetUserCallback(setOpt, this);
                    if (!MainSet.GetInstance().IsTwcjw()) {
                        break;
                    } else {
                        UISetSroView.AddView(this.VideoBtn[setOpt].GetView());
                        break;
                    }
                case 6:
                    this.IdleFrontCamTime = new SetItemPopupList((Context) this, 0, this.nFrontTimeValue, (SetItemPopupList.onPopItemClick) this);
                    this.IdleFrontCamTime.SetId(setOpt);
                    this.IdleFrontCamTime.GetBtn().setText(this.setvideoOptions[setOpt]);
                    if (!MainSet.GetInstance().IsHaveFcam()) {
                        break;
                    } else {
                        UISetSroView.AddView(this.IdleFrontCamTime.GetView());
                        break;
                    }
                case 7:
                    this.CameraType = new SetItemPopupList((Context) this, 0, getResources().getStringArray(R.array.str_fsother_camtype), (SetItemPopupList.onPopItemClick) this);
                    this.CameraType.SetId(setOpt);
                    this.CameraType.GetBtn().setText(this.setvideoOptions[setOpt]);
                    this.CameraType.GetView().setOnClickListener(new View.OnClickListener() {
                        public void onClick(final View v) {
                            if (SettingVideoActivity.bPswChecked) {
                                SettingVideoActivity.this.CameraType.onClick(v);
                            } else {
                                new SettingNumInuptDlg().createDlg(SettingVideoActivity.this, new SettingNumInuptDlg.onInputOK() {
                                    public void onOK(String val) {
                                        if (val.equals("0000")) {
                                            SettingVideoActivity.bPswChecked = true;
                                            SettingVideoActivity.this.CameraType.onClick(v);
                                        }
                                    }
                                }, 8);
                            }
                        }
                    });
                    UISetSroView.AddView(this.CameraType.GetView());
                    break;
            }
        }
    }

    public void onCheckedChanged(CompoundButton arg0, boolean bRet) {
        int nRet;
        int i = 1;
        if (bRet) {
            nRet = 1;
        } else {
            nRet = 0;
        }
        Log.d(TAG, "onCheckedChanged =" + bRet);
        switch (((Integer) arg0.getTag()).intValue()) {
            case 0:
                if (StSet.GetDriveVideo() != nRet) {
                    this.VideoBtn[0].SetSwitch(nRet);
                    if (!bRet) {
                        i = 2;
                    }
                    StSet.SetDriveVideoSwitch(i);
                    return;
                }
                return;
            case 1:
                if (FtSet.GetCamTrack() != nRet) {
                    this.VideoBtn[1].SetSwitch(nRet);
                    if (bRet) {
                        this.VideoBtn[2].SetSwitch(0);
                        FtSet.SetCamLine(0);
                    }
                    FtSet.SetCamTrack(nRet);
                    return;
                }
                return;
            case 2:
                if (FtSet.GetCamLine() != nRet) {
                    this.VideoBtn[2].SetSwitch(nRet);
                    if (bRet) {
                        this.VideoBtn[1].SetSwitch(0);
                        FtSet.SetCamTrack(0);
                    }
                    FtSet.SetCamLine(nRet);
                    return;
                }
                return;
            case 3:
                if (FtSet.GetCamMir() != nRet) {
                    this.VideoBtn[3].SetSwitch(nRet);
                    FtSet.SetCamMir(nRet);
                    return;
                }
                return;
            case 4:
                if (StSet.GetAcDisplay() != nRet) {
                    this.VideoBtn[4].SetSwitch(nRet);
                    if (!bRet) {
                        i = 2;
                    }
                    StSet.SetAcDisplaySwitch(i);
                    return;
                }
                return;
            case 5:
                if (FtSet.GetGnssMode() != nRet) {
                    FtSet.SetGnssMode(nRet);
                    FtSet.Save(0);
                    this.VideoBtn[5].SetSwitch(nRet);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 6:
                FtSet.SetFcamTime(item);
                this.IdleFrontCamTime.SetSel(item);
                return;
            case 7:
                FtSet.SetCamType(item);
                this.CameraType.SetSel(item);
                FsBaseActivity.ReSetBackCarsource();
                FtSet.Save(0);
                showRebootDlg();
                return;
            default:
                return;
        }
    }

    private void showRebootDlg() {
        this.BootDialog = new SetItemDialog(this, R.string.set_navi_path_reboot, new View.OnClickListener() {
            public void onClick(View arg0) {
                switch (((Integer) arg0.getTag()).intValue()) {
                    case 16:
                        Mcu.SendXKey(19);
                        SettingVideoActivity.this.BootDialog.Hide();
                        return;
                    case 17:
                        SettingVideoActivity.this.BootDialog.Hide();
                        return;
                    default:
                        return;
                }
            }
        });
    }
}
