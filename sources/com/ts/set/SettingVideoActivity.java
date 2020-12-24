package com.ts.set;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import com.ts.MainUI.R;
import com.ts.set.setview.SetItemDialog;
import com.ts.set.setview.SetItemPopupList;
import com.ts.set.setview.SetMainItemSw;
import com.ts.set.setview.SetMainItemTopName;
import com.ts.set.setview.UISetSroView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;

public class SettingVideoActivity extends Activity implements CompoundButton.OnCheckedChangeListener, SetItemPopupList.onPopItemClick {
    private static final String TAG = "SettingVideoActivity";
    SetItemPopupList OutPutFormat;
    SetItemDialog SetCamMir;
    SetMainItemSw[] VideoBtn = new SetMainItemSw[5];
    int[] nVideoFormat = {R.string.set_video_ntsc, R.string.set_video_pal};
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
        this.OutPutFormat.SetSel(FtSet.GetVedioOutFmt());
        super.onResume();
    }

    private void initVideoOptions() {
        this.topname = new SetMainItemTopName(this, getResources().getStringArray(R.array.set_main_options)[4]);
        this.topname.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingVideoActivity.this.finish();
            }
        });
        UISetSroView.AddView(this.topname.GetView(), 1280, 80);
        this.setvideoOptions = getResources().getStringArray(R.array.set_video_options);
        for (int setOpt = 0; setOpt < 7; setOpt++) {
            switch (setOpt) {
                case 0:
                    this.VideoBtn[setOpt] = new SetMainItemSw(this, this.setvideoOptions[setOpt]);
                    this.VideoBtn[setOpt].SetUserCallback(setOpt, this);
                    if (FtSet.GetBrakeDef() != 0 && FtSet.GetBrakeDef() != 3) {
                        break;
                    } else {
                        UISetSroView.AddView(this.VideoBtn[setOpt].GetView(), 1280, 87);
                        break;
                    }
                case 1:
                case 2:
                case 3:
                case 4:
                    this.VideoBtn[setOpt] = new SetMainItemSw(this, this.setvideoOptions[setOpt]);
                    this.VideoBtn[setOpt].SetUserCallback(setOpt, this);
                    if (setOpt == 3) {
                        break;
                    } else {
                        UISetSroView.AddView(this.VideoBtn[setOpt].GetView(), 1280, 87);
                        break;
                    }
                case 5:
                    this.OutPutFormat = new SetItemPopupList((Context) this, 0, this.nVideoFormat, (SetItemPopupList.onPopItemClick) this);
                    this.OutPutFormat.SetId(setOpt);
                    this.OutPutFormat.GetBtn().setText(this.setvideoOptions[setOpt]);
                    if (setOpt == 5) {
                        break;
                    } else {
                        UISetSroView.AddView(this.OutPutFormat.GetView(), 1280, 87);
                        break;
                    }
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
                    this.SetCamMir = new SetItemDialog(this, R.string.set_general_cammir, new View.OnClickListener() {
                        public void onClick(View arg0) {
                            switch (((Integer) arg0.getTag()).intValue()) {
                                case 16:
                                    Mcu.SendXKey(20);
                                    SettingVideoActivity.this.SetCamMir.Hide();
                                    return;
                                case 17:
                                    SettingVideoActivity.this.SetCamMir.Hide();
                                    return;
                                default:
                                    return;
                            }
                        }
                    });
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
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 5:
                this.OutPutFormat.SetSel(item);
                FtSet.SetVedioOutFmt(item);
                return;
            default:
                return;
        }
    }
}
