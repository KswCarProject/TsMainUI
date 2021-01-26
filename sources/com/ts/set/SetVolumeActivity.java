package com.ts.set;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.set.setview.SetItemProgressList;
import com.ts.set.setview.SetMainItemTopName;
import com.ts.set.setview.UISetSroView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.StSet;

public class SetVolumeActivity extends Activity implements SetItemProgressList.onPosChange, UserCallBack, SeekBar.OnSeekBarChangeListener {
    private static final String TAG = "SetVolumeActivity";
    SetItemProgressList AlarmVolume;
    SetItemProgressList BtVolume;
    SetItemProgressList MediaVolume;
    SetItemProgressList NaviVolume;
    SetItemProgressList RingVolume;
    SetItemProgressList SystemVolume;
    Evc mEvc = Evc.GetInstance();
    int nAvol = 0;
    int nBvol = 0;
    int nMvol = 0;
    int nNvol = 0;
    int nRvol = 0;
    int nSvol = 0;
    private String[] setVolumeOptions;
    SetMainItemTopName topname;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UISetSroView.Inint(this);
        initSetVolumeOptions();
        UISetSroView.Show(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        this.nMvol = Iop.GetVolume(0);
        this.nBvol = Iop.GetVolume(1);
        Log.i(TAG, "Iop.GetVolume(0)==" + Iop.GetVolume(0));
        Log.i(TAG, "Iop.GetVolume(1)==" + Iop.GetVolume(1));
        this.MediaVolume.SetCurVal(Iop.GetVolume(0));
        this.BtVolume.SetCurVal(Iop.GetVolume(1));
        this.RingVolume.SetCurVal(StSet.GetRvol());
        this.AlarmVolume.SetCurVal(StSet.GetAvol());
        this.SystemVolume.SetCurVal(StSet.GetSvol());
        this.NaviVolume.SetCurVal(StSet.GetNvol());
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        finish();
        super.onPause();
    }

    private boolean isHaveOption(int setOption) {
        return true;
    }

    private void initSetVolumeOptions() {
        this.topname = new SetMainItemTopName(this, getResources().getStringArray(R.array.set_main_options)[2]);
        this.topname.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SetVolumeActivity.this.finish();
            }
        });
        UISetSroView.AddView(this.topname.GetView(), 1024, -2);
        this.setVolumeOptions = getResources().getStringArray(R.array.set_volume_options);
        for (int setOpt = 0; setOpt < 6; setOpt++) {
            switch (setOpt) {
                case 0:
                    this.MediaVolume = new SetItemProgressList((Context) this, this.setVolumeOptions[setOpt]);
                    this.MediaVolume.SetMinMax(0, this.mEvc.volume_max);
                    this.MediaVolume.SetIdCallBack(setOpt, this);
                    UISetSroView.AddView(this.MediaVolume.GetView());
                    break;
                case 1:
                    this.NaviVolume = new SetItemProgressList((Context) this, this.setVolumeOptions[setOpt]);
                    this.NaviVolume.SetMinMax(0, this.mEvc.Gis_vol_max);
                    this.NaviVolume.SetIdCallBack(setOpt, this);
                    if (FtSet.IsIconExist(101) != 1) {
                        break;
                    } else {
                        UISetSroView.AddView(this.NaviVolume.GetView());
                        break;
                    }
                case 2:
                    this.BtVolume = new SetItemProgressList((Context) this, this.setVolumeOptions[setOpt]);
                    this.BtVolume.SetMinMax(0, this.mEvc.Other_vol_max);
                    this.BtVolume.SetIdCallBack(setOpt, this);
                    UISetSroView.AddView(this.BtVolume.GetView());
                    break;
                case 3:
                    this.RingVolume = new SetItemProgressList((Context) this, this.setVolumeOptions[setOpt]);
                    this.RingVolume.SetMinMax(0, this.mEvc.Ring_vol_max);
                    this.RingVolume.SetIdCallBack(setOpt, this);
                    UISetSroView.AddView(this.RingVolume.GetView());
                    break;
                case 4:
                    this.AlarmVolume = new SetItemProgressList((Context) this, this.setVolumeOptions[setOpt]);
                    this.AlarmVolume.SetMinMax(0, this.mEvc.Alarm_vol_max);
                    this.AlarmVolume.SetIdCallBack(setOpt, this);
                    break;
                case 5:
                    this.SystemVolume = new SetItemProgressList((Context) this, this.setVolumeOptions[setOpt]);
                    this.SystemVolume.SetMinMax(0, this.mEvc.Sys_vol_max);
                    this.SystemVolume.SetIdCallBack(setOpt, this);
                    if (getResources().getString(R.string.custom_num_show).equals("FORYOU")) {
                        break;
                    } else {
                        UISetSroView.AddView(this.SystemVolume.GetView());
                        break;
                    }
            }
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                this.mEvc.evol_mediavol_set(pos);
                this.MediaVolume.SetCurVal(pos);
                return;
            case 1:
                this.mEvc.evol_navivol_set(pos);
                this.NaviVolume.SetCurVal(pos);
                return;
            case 2:
                this.mEvc.evol_btvol_set(pos);
                this.BtVolume.SetCurVal(pos);
                return;
            case 3:
                this.mEvc.evol_ringvol_set(pos);
                this.RingVolume.SetCurVal(pos);
                return;
            case 4:
                this.mEvc.evol_Alarmvol_set(pos);
                this.AlarmVolume.SetCurVal(pos);
                return;
            case 5:
                this.mEvc.evol_systemvol_set(pos);
                this.SystemVolume.SetCurVal(pos);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        if (this.nMvol != Iop.GetVolume(0)) {
            this.nMvol = Iop.GetVolume(0);
            this.MediaVolume.SetCurVal(Iop.GetVolume(0));
        }
        if (this.nNvol != StSet.GetNvol()) {
            this.nNvol = StSet.GetNvol();
            this.NaviVolume.SetCurVal(StSet.GetNvol());
        }
        if (this.nBvol != Iop.GetVolume(1)) {
            this.nBvol = Iop.GetVolume(1);
            this.BtVolume.SetCurVal(Iop.GetVolume(1));
        }
        if (this.nRvol != StSet.GetRvol()) {
            this.nRvol = StSet.GetRvol();
            this.RingVolume.SetCurVal(StSet.GetRvol());
        }
        if (this.nAvol != StSet.GetAvol()) {
            this.nAvol = StSet.GetAvol();
            this.AlarmVolume.SetCurVal(StSet.GetAvol());
        }
        if (this.nSvol != StSet.GetSvol()) {
            this.nSvol = StSet.GetSvol();
            this.SystemVolume.SetCurVal(StSet.GetSvol());
        }
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (((Integer) seekBar.getTag()).intValue()) {
            case 0:
                this.mEvc.evol_mediavol_set(progress);
                this.MediaVolume.SetCurVal(progress);
                return;
            case 1:
                this.mEvc.evol_navivol_set(progress);
                this.NaviVolume.SetCurVal(progress);
                return;
            case 2:
                this.mEvc.evol_btvol_set(progress);
                this.BtVolume.SetCurVal(progress);
                return;
            case 3:
                this.mEvc.evol_ringvol_set(progress);
                this.RingVolume.SetCurVal(progress);
                return;
            case 4:
                this.mEvc.evol_Alarmvol_set(progress);
                this.AlarmVolume.SetCurVal(progress);
                return;
            case 5:
                this.mEvc.evol_systemvol_set(progress);
                this.SystemVolume.SetCurVal(progress);
                return;
            default:
                return;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
