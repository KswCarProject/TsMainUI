package com.ts.MainUI;

import android.content.Context;
import android.media.AudioManager;
import android.os.SystemProperties;
import android.util.Log;
import com.txznet.sdk.bean.Poi;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;
import java.io.IOException;

public class Evc implements AudioManager.OnAudioFocusChangeListener {
    public static final int MAX_VOL_GAIN = 88;
    static final String MeM_FILE = "/mnt/sdcard/mem.ini";
    public static final int PHONE_CALLIN = 1;
    public static final int PHONE_IDLE = 0;
    public static final int PHONE_TALKING = 2;
    public static final int POP_MUTE_DELAYTIME = 30;
    /* access modifiers changed from: private */
    public static String TAG = "Evcsetting";
    public static final int V0L_GAIN_MAX = 100;
    public static final int VOL_MAX = 30;
    public static final int WORKMODE_BT_VOL = 19;
    public static final int WORKMODE_NAVI_VOL = 18;
    public static boolean bNaviToLow = false;
    public static boolean bNaviVol = false;
    private static Evc mEvc = null;
    public static int mSystemState = 0;
    private static int nNaviHunyin = 1;
    public static int nNaviSpeeShow = 0;
    public static int nNaviSpeeking = 0;
    public int Alarm_vol_max = 15;
    public int Gis_vol_max = 100;
    private int[] MaxV = {27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5};
    public int NaviVoiceDelay = 0;
    public int Other_vol_max = 30;
    public int Ring_vol_max = 100;
    public int Sys_vol_max = 100;
    public boolean bGoogle = false;
    boolean bMix = false;
    private boolean bMusicChange = false;
    boolean bNaviforce = false;
    public boolean bPopMuteEnable = false;
    boolean bRequefocus = true;
    private int[] cpuT = {95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117};
    private int[] g_VolDbtb = {1, 3, 8, 13, 18, 23, 28, 30, 36, 42, 47, 52, 56, 60, 63, 66, 69, 71, 74, 76, 77, 80, 84, 85, 87, 89, 92, 94, 97, 99, 100};
    private AudioManager mAudioManager = null;
    protected EvcCallBack mEvcCallBack = null;
    private Context m_context;
    String memString = "test";
    public int nCheckCpu = 0;
    int nCheckNavi = 0;
    int nCpuMode = -1;
    int nLastTemp = 0;
    int nMixSize = -1;
    int nOldmode = 0;
    int nPer = 0;
    int nRestMode = 0;
    int nVolTempMax = 30;
    private int nWorkModeMute = 30;
    public int nWorkModeReq = 0;
    int nnUM = 0;
    public int volume_max = 30;

    public void SetPopMuteTime(int nTime) {
        this.nWorkModeMute = nTime;
    }

    public static Evc GetInstance() {
        if (mEvc == null) {
            mEvc = new Evc();
        }
        return mEvc;
    }

    /* access modifiers changed from: package-private */
    public int GetCpuTemp() {
        return ((int) ThermalInfoUtil.GetCputemp()) / 1000;
    }

    /* access modifiers changed from: package-private */
    public void CheckCpuTemp() {
        int nTempNaw = GetCpuTemp();
        int nVol = Iop.GetVolume(0);
        Log.d(TAG, "CPU TEMP ==" + nTempNaw);
        if (nTempNaw > this.nLastTemp) {
            int i = this.cpuT.length - 1;
            while (true) {
                if (i >= 0) {
                    if (nTempNaw >= this.cpuT[i] && nVol >= this.MaxV[i] && this.nCpuMode < i) {
                        this.nCpuMode = i;
                        this.nVolTempMax = this.MaxV[i];
                        SetMusicVolume(this.MaxV[i]);
                        break;
                    }
                    i--;
                } else {
                    break;
                }
            }
        } else if (nTempNaw < this.nLastTemp && this.nCpuMode >= 0 && nTempNaw < this.cpuT[this.nCpuMode] - 5) {
            if (this.nCpuMode > 5) {
                this.nCpuMode -= 5;
                this.nVolTempMax = this.MaxV[this.nCpuMode];
            } else {
                this.nCpuMode = -1;
                this.nVolTempMax = this.volume_max;
            }
            SetMusicVolume(nVol);
        }
        this.nLastTemp = nTempNaw;
    }

    public int task(int mode) {
        if (this.nOldmode != mode) {
            if (this.nOldmode == 3 && mode == 0) {
                this.nRestMode = 5;
            }
            this.nOldmode = mode;
        }
        if (this.nRestMode > 0) {
            this.nRestMode--;
            if (this.nRestMode == 0) {
                ResetVol();
            }
        }
        this.nnUM++;
        if (this.nnUM > 150) {
            this.nnUM = 0;
            if (this.nCheckCpu == 1 && Mcu.GetReverse() == 0) {
                CheckCpuTemp();
            }
        }
        if (this.NaviVoiceDelay > 0) {
            this.NaviVoiceDelay--;
            if (this.NaviVoiceDelay == 0) {
                SetGisMute(false);
                if (!this.bMix) {
                    Iop.NaviSpeaking(0);
                }
                nNaviSpeeking = 0;
                if (this.bMusicChange) {
                    SetMusicVolume(Iop.GetVolume(0));
                    this.bMusicChange = false;
                }
            }
        }
        if (this.bPopMuteEnable) {
            this.bPopMuteEnable = false;
            Iop.PopMuteDelay(20);
        }
        return Iop.EvolTask(mode);
    }

    /* access modifiers changed from: package-private */
    public void CheckVolMax() {
        this.Sys_vol_max = this.mAudioManager.getStreamMaxVolume(1);
        this.Alarm_vol_max = this.mAudioManager.getStreamMaxVolume(4);
        this.Ring_vol_max = this.mAudioManager.getStreamMaxVolume(2);
        Log.d(TAG, "Sys_vol_max==" + this.Sys_vol_max);
        Log.d(TAG, "AudioManager.STREAM_MUSIC==" + this.mAudioManager.getStreamVolume(3));
        Log.d(TAG, "getStreamMaxVolume AudioManager.STREAM_MUSIC==" + this.mAudioManager.getStreamMaxVolume(3));
        Log.d(TAG, "AudioManager.STREAM_GIS==" + this.mAudioManager.getStreamVolume(11));
        Log.d(TAG, "getStreamMaxVolume AudioManager.STREAM_GIS==" + this.mAudioManager.getStreamMaxVolume(11));
        Log.d(TAG, "AudioManager.STREAM_BLUETOOTH_SCO==" + this.mAudioManager.getStreamVolume(6));
        Log.d(TAG, "getStreamMaxVolume AudioManager.STREAM_BLUETOOTH_SCO==" + this.mAudioManager.getStreamMaxVolume(6));
        Log.d(TAG, "AudioManager.STREAM_ALARM==" + this.mAudioManager.getStreamVolume(4));
        Log.d(TAG, "getStreamMaxVolume AudioManager.STREAM_ALARM==" + this.mAudioManager.getStreamMaxVolume(4));
        Log.d(TAG, "AudioManager.STREAM_RING==" + this.mAudioManager.getStreamVolume(2));
        Log.d(TAG, "getStreamMaxVolume AudioManager.STREAM_RING==" + this.mAudioManager.getStreamMaxVolume(2));
        Log.d(TAG, "AudioManager.STREAM_SYSTEM==" + this.mAudioManager.getStreamVolume(1));
        Log.d(TAG, "getStreamMaxVolume AudioManager.STREAM_SYSTEM==" + this.mAudioManager.getStreamMaxVolume(1));
        Log.d(TAG, "AudioManager.STREAM_NOTIFICATION==" + this.mAudioManager.getStreamVolume(5));
        Log.d(TAG, "getStreamMaxVolume AudioManager.STREAM_NOTIFICATION==" + this.mAudioManager.getStreamMaxVolume(5));
        Log.d(TAG, "AudioManager.STREAM_SYSTEM_ENFORCED==" + this.mAudioManager.getStreamVolume(7));
        Log.d(TAG, "getStreamMaxVolume AudioManager.STREAM_SYSTEM_ENFORCED==" + this.mAudioManager.getStreamMaxVolume(7));
    }

    /* access modifiers changed from: package-private */
    public void ResetVol() {
        if (bNaviToLow) {
            this.Gis_vol_max = 30;
        }
        if (!bNaviToLow || StSet.GetNvol() <= 30) {
            SetGisVolume(StSet.GetNvol(), true);
        } else {
            SetGisVolume(15, true);
        }
        SetBtVolume(Iop.GetVolume(1));
        SetSystemVolume(StSet.GetSvol());
        SetRingVolume(StSet.GetRvol());
        SetAlarmVolume(StSet.GetAvol());
        SetMusicVolume(Iop.GetVolume(0));
    }

    public void InintEvc(Context aContext) {
        if (this.mAudioManager == null) {
            this.m_context = aContext;
            this.mAudioManager = (AudioManager) this.m_context.getSystemService(Poi.PoiAction.ACTION_AUDIO);
            Log.i(TAG, "SetMyContext mAudioManager==" + this.mAudioManager);
        }
        ResetVol();
        Log.i(TAG, "StSet.GetWorkMode() == " + Iop.GetWorkMode());
    }

    public int SetNaviMixScal(int nScal) {
        if (this.mAudioManager != null) {
            return 1;
        }
        this.mAudioManager.setAudPolicyStrategy(11, 3, nScal);
        return 1;
    }

    public int GetWorkMode() {
        return Iop.GetWorkMode();
    }

    public int GetMute() {
        return Iop.GetMute();
    }

    public void SetEvcCallBack(EvcCallBack cb) {
        this.mEvcCallBack = cb;
    }

    public boolean IsHaveDisk() {
        String Val = SystemProperties.get("forfan.usbdvd.index");
        if (Val != null && Val.equals("1")) {
            return TsFile.fileIsExists("/dev/block/sr1");
        }
        if (Val == null || !Val.equals("0")) {
            return TsFile.fileIsExists("/dev/block/sr0");
        }
        return TsFile.fileIsExists("/dev/block/sr0");
    }

    public void DiskStop() {
        String Val = SystemProperties.get("forfan.usbdvd.index");
        if (Val != null && Val.equals("1")) {
            Iop.DiscStop(1);
        } else if (Val == null || !Val.equals("0")) {
            Iop.DiscStop(0);
            Log.e(TAG, "DiscStop 0 but val=null");
        } else {
            Iop.DiscStop(0);
        }
    }

    public void DiskEject() {
        String Val = SystemProperties.get("forfan.usbdvd.index");
        if (Val != null && Val.equals("1")) {
            Iop.DiscEject(1);
            Log.e(TAG, "DiskEject(1) =");
        } else if (Val == null || !Val.equals("0")) {
            Iop.DiscEject(0);
            Log.e(TAG, "DiskEject 0 but val=null");
        } else {
            Iop.DiscEject(0);
            Log.e(TAG, "DiskEject(0) =");
        }
    }

    public void SetNoFocusChange() {
        this.bRequefocus = false;
    }

    public void evol_workmode_set(int newmode) {
        Log.i(TAG, "evol_workmode_set  newmode==" + newmode);
        if (newmode == 0) {
            if (this.nWorkModeReq != newmode) {
                this.bPopMuteEnable = true;
            }
            if (this.mAudioManager != null) {
                Log.i(TAG, "abandonAudioFocus  newmode==" + newmode);
                this.mAudioManager.abandonAudioFocus(new AudioManager.OnAudioFocusChangeListener() {
                    public void onAudioFocusChange(int arg0) {
                        Log.e(Evc.TAG, "abandonAudioFocus==" + arg0);
                    }
                });
            }
        } else if (!(this.mAudioManager == null || this.nWorkModeReq == newmode)) {
            if (this.nWorkModeMute != 0) {
                this.bPopMuteEnable = true;
            }
            if (this.bRequefocus) {
                Log.i(TAG, "requestAudioFocus  newmode= AUDIOFOCUS_GAIN_TRANSIENT=" + newmode);
                if (this.mAudioManager.requestAudioFocus(this, 3, 2) != 1) {
                    Log.e(TAG, "could not get audio focus");
                } else {
                    Log.i(TAG, "could  get audio focus 11== " + this.mAudioManager.isMusicActive());
                }
            }
        }
        if (newmode != 2 && IsHaveDisk()) {
            Log.i(TAG, "DiscStop==" + newmode);
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Evc.this.DiskStop();
                }
            }.start();
        }
        if (newmode != 0) {
            WriteMem("test");
        }
        Iop.SetWorkMode(newmode);
        if (!(this.nWorkModeReq == newmode || this.mEvcCallBack == null)) {
            this.mEvcCallBack.DealWorkMode(this.nWorkModeReq, newmode);
        }
        this.nWorkModeReq = newmode;
        this.bRequefocus = true;
    }

    public void SetVolAllChannelGain(int nVolGain) {
    }

    public void evol_popmute_set(int workmode) {
        Iop.PopMuteSet(workmode);
    }

    public void evol_popmute_clr(int workmode) {
        Iop.PopMuteClr(workmode);
    }

    public void evol_popmute_clr_force(int workmode) {
        evol_popmute_clr(workmode);
    }

    public void evol_mediavol_set(int vol) {
        Iop.SetVolume(0, vol);
        SetMusicVolume(vol);
    }

    public void evol_navivol_set(int vol) {
        SetGisVolume(vol, true);
    }

    public void evol_btvol_set(int vol) {
        Iop.SetVolume(1, vol);
        SetBtVolume(vol);
    }

    public void evol_vol_set(int vol) {
        evol_mut_set(0);
        int nSta = Iop.GetMediaOrBlue();
        Iop.SetVolume(nSta, vol);
        int nVol = Iop.GetVolume(nSta);
        if (nSta == 1) {
            SetBtVolume(nVol);
        } else {
            SetMusicVolume(nVol);
        }
    }

    public void evol_mut_set(int onoff) {
        Log.i(TAG, "evol_mut_set == " + onoff);
        Iop.SetMute(onoff);
    }

    public void evol_ringvol_set(int vol) {
        SetRingVolume(vol);
    }

    public void evol_Alarmvol_set(int vol) {
        SetAlarmVolume(vol);
    }

    public void evol_systemvol_set(int vol) {
        SetSystemVolume(vol);
    }

    public void evol_ringvol_tune(int updn) {
        int nVol = StSet.GetRvol();
        if (updn == 0) {
            if (nVol > 0) {
                nVol--;
            }
        } else if (nVol < this.Ring_vol_max) {
            nVol++;
        }
        SetRingVolume(nVol);
    }

    public void evol_alarmvol_tune(int updn) {
        int nVol = StSet.GetAvol();
        if (updn == 0) {
            if (nVol > 0) {
                nVol--;
            }
        } else if (nVol < this.Alarm_vol_max) {
            nVol++;
        }
        SetAlarmVolume(nVol);
    }

    public void evol_systemvol_tune(int updn) {
        int nVol = StSet.GetSvol();
        if (updn == 0) {
            if (nVol > 0) {
                nVol--;
            }
        } else if (nVol < this.Sys_vol_max) {
            nVol++;
        }
        SetSystemVolume(nVol);
    }

    public void Evol_vol_tune(int updn) {
        Log.i(TAG, "Evol_vol_tune == " + updn + "nNaviSpeeking=" + nNaviSpeeking + " nNaviSpeeShow = " + nNaviSpeeShow);
        evol_mut_set(0);
        if (!bNaviVol || (nNaviSpeeking != 1 && nNaviSpeeShow <= 0)) {
            int nSta = Iop.GetMediaOrBlue();
            Iop.AdjVolume(nSta, updn);
            int nVol = Iop.GetVolume(nSta);
            if (nSta == 1) {
                SetBtVolume(nVol);
            } else {
                SetMusicVolume(nVol);
            }
        } else {
            int nVol2 = StSet.GetNvol();
            if (updn == 1) {
                if (bNaviToLow) {
                    nVol2++;
                    if (nVol2 > this.Gis_vol_max) {
                        nVol2 = this.Gis_vol_max;
                    }
                } else if (nVol2 + 3 < this.Gis_vol_max) {
                    nVol2 += 3;
                } else {
                    nVol2 = this.Gis_vol_max;
                }
            } else if (bNaviToLow) {
                if (nVol2 > 0) {
                    nVol2--;
                }
            } else if (nVol2 > 3) {
                nVol2 -= 3;
            } else {
                nVol2 = 0;
            }
            evol_navivol_set(nVol2);
        }
    }

    private boolean IsNaviState() {
        Boolean getGISAudiostatus = new Boolean(false);
        Log.i(TAG, "getGISAudiostatus == " + getGISAudiostatus);
        return getGISAudiostatus.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public void ChVolForNavi(boolean bMute) {
        if (bMute) {
            SetMusicVolume(0);
        } else {
            SetMusicVolume((StSet.Getmvwns() * Iop.GetVolume(0)) / 100);
        }
        this.bMusicChange = true;
    }

    public void SetNaviVolDn(int nPercent) {
        this.nPer = nPercent;
    }

    public void evol_navi_set(int onoff, boolean bMute) {
        if (onoff == 1) {
            SetGisMute(false);
            if (Iop.GetMediaOrBlue() == 1) {
                SetGisVolume(0, false);
            } else {
                if (nNaviHunyin == 1) {
                    ChVolForNavi(bMute);
                }
                if (this.nPer <= 0 || this.nPer > 100) {
                    SetGisVolume(StSet.GetNvol(), true);
                } else {
                    SetGisVolume((StSet.GetNvol() * this.nPer) / 100, false);
                }
            }
            Iop.NaviSpeaking(onoff);
            nNaviSpeeking = onoff;
            this.NaviVoiceDelay = 0;
        } else if (!this.bNaviforce) {
            this.NaviVoiceDelay = 50;
        } else {
            this.NaviVoiceDelay = 500;
        }
    }

    public void evol_mix_set(int onoff) {
        Log.d(TAG, "evol_mix_set==" + onoff);
        if (onoff == 1) {
            this.nMixSize = StSet.Getmvwns();
            StSet.Setmvwns(100);
            Iop.NaviSpeaking(onoff);
            this.bMix = true;
            return;
        }
        Iop.NaviSpeaking(onoff);
        if (this.nMixSize >= 0 && this.nMixSize <= 100) {
            StSet.Setmvwns(this.nMixSize);
            this.nMixSize = -1;
        }
        this.bMix = false;
    }

    public void evol_navi_set_force(int onoff) {
        if (onoff == 1) {
            SetGisMute(false);
            if (Iop.GetMediaOrBlue() == 1) {
                SetGisVolume(0, false);
            } else {
                if (nNaviHunyin == 1) {
                    ChVolForNavi(false);
                }
                if (this.nPer <= 0 || this.nPer > 100) {
                    SetGisVolume(StSet.GetNvol(), true);
                } else {
                    SetGisVolume((StSet.GetNvol() * this.nPer) / 100, false);
                }
            }
            Iop.NaviSpeaking(onoff);
            nNaviSpeeking = onoff;
            this.NaviVoiceDelay = 1000;
            this.bNaviforce = true;
            return;
        }
        this.NaviVoiceDelay = 5;
        this.bNaviforce = false;
    }

    public void evol_blue_set(int onoff) {
        Iop.BlueSpeaking(onoff);
        if (onoff == 1) {
            SetGisVolume(0, false);
        } else {
            SetGisVolume(StSet.GetNvol(), false);
        }
    }

    public void evol_bal_set(int bal) {
        Iop.BalSet(bal);
    }

    public void evol_bal_tune(int updn) {
        Iop.BalAdj(updn);
    }

    public void evol_fad_set(int fad) {
        Iop.FadSet(fad);
    }

    public void evol_fad_tune(int updn) {
        Iop.FadAdj(updn);
    }

    public void evol_ch51_set(int onoff) {
    }

    public void evol_bal_def() {
        Iop.BalSet(7);
        Iop.FadSet(7);
    }

    public void evol_eband_set(int band, int val) {
        switch (band) {
            case 0:
                Iop.BasSet(val);
                return;
            case 1:
                Iop.MidSet(val);
                return;
            case 2:
                Iop.TreSet(val);
                return;
            default:
                return;
        }
    }

    public void evol_rds_hold() {
    }

    public void evol_rds_Release() {
        evol_aux_release();
    }

    public void evol_aux_hold() {
        Iop.AuxHold();
    }

    public void evol_aux_release() {
        Iop.AuxRelease();
    }

    public void evol_eqm_set(int mode) {
        Iop.EqmSet(mode);
    }

    public void evol_lud_set(int lud) {
        Iop.LudSet(lud);
    }

    public void evol_sub_set(int sub) {
    }

    public void SetMicGain() {
        Log.d(TAG, "SetMicGain" + FtSet.GetBtMicGain());
        int nGain = FtSet.GetBtMicGain() - 39;
        if (nGain < 0) {
            nGain = 0;
        } else if (nGain > 15) {
            nGain = 15;
        }
        SetMicDigitalEnhance(nGain);
    }

    private void mtkdsp_send_init() {
        SetMicGain();
    }

    private void mtkdsp_send_micgain(int gain) {
        if (gain <= 63) {
        }
    }

    private void mtkdsp_send_lud(int lud) {
        Iop.LudSet(lud);
    }

    private int GetVolGain(int nVol, int VolMax) {
        if (VolMax < 0) {
            VolMax = 30;
        }
        if (nVol > VolMax) {
            nVol = VolMax;
        }
        return this.g_VolDbtb[nVol];
    }

    public int SetGisMute(boolean bMute) {
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamMute(11, bMute);
            return 1;
        }
        Log.d(TAG, "mAudioManager==null SetGisMute" + bMute);
        return 0;
    }

    private int SetGisVolume(int u4Vol, boolean bSave) {
        Log.d(TAG, "SetGisVolume==" + u4Vol);
        if (this.mAudioManager != null) {
            if (bNaviToLow) {
                this.mAudioManager.setStreamVolume(11, (u4Vol * 100) / this.Gis_vol_max, 0);
            } else {
                this.mAudioManager.setStreamVolume(11, u4Vol, 0);
            }
            if (!bSave) {
                return 1;
            }
            StSet.SetNvol(u4Vol);
            return 1;
        }
        Log.d(TAG, "mAudioManager==null STREAM_GIS" + u4Vol);
        return 1;
    }

    private int SetBtMute(boolean bMute) {
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamMute(0, bMute);
            return 1;
        }
        Log.d(TAG, "mAudioManager==null SetGisMute" + bMute);
        return 0;
    }

    private int SetBtVolume(int u4Vol) {
        Log.d(TAG, "SetBtVolume" + u4Vol);
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamVolume(0, u4Vol * 3, 0);
            return 1;
        }
        Log.d(TAG, "mAudioManager==null STREAM_BLUETOOTH_SCO" + u4Vol);
        return 1;
    }

    private int SetSystemVolume(int u4Vol) {
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamVolume(1, u4Vol, 0);
            StSet.SetSvol(u4Vol);
        } else {
            Log.d(TAG, "mAudioManager==null STREAM_SYSTEM" + u4Vol);
        }
        return 1;
    }

    private int SetRingVolume(int u4Vol) {
        Log.d(TAG, "SetRingVolume=" + u4Vol);
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamVolume(2, u4Vol, 0);
            StSet.SetRvol(u4Vol);
            return 1;
        }
        Log.d(TAG, "mAudioManager==null STREAM_RING" + u4Vol);
        return 1;
    }

    public int SetNotificationVoume(int u4Vol) {
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamVolume(5, u4Vol, 0);
            return 1;
        }
        Log.d(TAG, "mAudioManager==null STREAM_NOTIFICATION" + u4Vol);
        return 1;
    }

    public int SetSystemEnforceVoume(int u4Vol) {
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamVolume(7, u4Vol, 0);
            return 1;
        }
        Log.d(TAG, "mAudioManager==null STREAM_SYSTEM_ENFORCED" + u4Vol);
        return 1;
    }

    private int SetAlarmVolume(int u4Vol) {
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamVolume(4, u4Vol, 0);
            StSet.SetAvol(u4Vol);
            return 1;
        }
        Log.d(TAG, "mAudioManager==null STREAM_RING" + u4Vol);
        return 1;
    }

    public void AddNaviWhileList(String navipath) {
        if (this.mAudioManager != null) {
            String addGisName = String.valueOf("AudioAddWhiteListName=") + navipath;
            Log.d(TAG, "AddNaviWhileList==" + addGisName);
            this.mAudioManager.setParameters(addGisName);
        }
    }

    public void DelNaviWhileList(String navipath) {
        if (this.mAudioManager != null) {
            String delGisName = String.valueOf("AudioDelWhiteListName=") + navipath;
            Log.d(TAG, "DelNaviWhileList==" + delGisName);
            this.mAudioManager.setParameters(delGisName);
        }
    }

    public int SetMusicVolume(int u4Vol) {
        if (this.mAudioManager == null) {
            Log.d(TAG, "mAudioManager==null STREAM_MUSIC" + u4Vol);
        } else if (this.nCheckCpu == 1) {
            Log.d(TAG, "SetMuscVolume u4Vol=" + u4Vol);
            Log.d(TAG, "SetMuscVolume nVolTempMax==" + this.nVolTempMax);
            this.mAudioManager.setStreamVolume(3, GetVolGain(u4Vol, this.nVolTempMax), 0);
        } else {
            Log.d(TAG, "SetMuscVolume" + u4Vol);
            Log.d(TAG, "SetMuscVolume setStreamVolume " + GetVolGain(u4Vol, this.volume_max));
            this.mAudioManager.setStreamVolume(3, GetVolGain(u4Vol, this.volume_max), 0);
            Log.d(TAG, "SetMuscVolume GetStreamVolume " + this.mAudioManager.getStreamVolume(3));
        }
        return 1;
    }

    public void SetMusicMute(boolean bMute) {
        if (bMute) {
            SetMusicVolume(0);
        } else {
            SetMusicVolume(Iop.GetVolume(0));
        }
    }

    public void SetMusicVolumeTest(int u4Vol) {
        Log.d(TAG, "SetMusicVolumeTest STREAM_MUSIC" + u4Vol);
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamVolume(3, u4Vol, 0);
        } else {
            Log.d(TAG, "mAudioManager==null STREAM_MUSIC" + u4Vol);
        }
    }

    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case -3:
                Log.i(TAG, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK lost audio focus");
                return;
            case -2:
                Log.i(TAG, "AUDIOFOCUS_LOSS_TRANSIENT lost audio focus");
                if (this.mEvcCallBack != null) {
                    this.mEvcCallBack.AudioFocusTRANSIENT(Iop.GetWorkMode(), false);
                }
                if (this.nWorkModeReq == Iop.GetWorkMode() && this.bGoogle) {
                    Iop.PopMuteSet(Iop.GetWorkMode());
                    return;
                }
                return;
            case -1:
                Log.i(TAG, "AUDIOFOCUS_LOSS lost audio focus");
                Log.i(TAG, "onAudioFocusChange  nWorkModeReq==" + this.nWorkModeReq);
                Log.i(TAG, "onAudioFocusChange  Evol.workmode==" + Iop.GetWorkMode());
                if (this.nWorkModeReq == Iop.GetWorkMode() && Iop.GetWorkMode() != 10) {
                    evol_workmode_set(0);
                    return;
                }
                return;
            case 1:
                Log.i(TAG, "AUDIOFOCUS_GAIN get audio focus");
                if (this.mEvcCallBack != null) {
                    this.mEvcCallBack.AudioFocusTRANSIENT(Iop.GetWorkMode(), true);
                }
                if (this.bGoogle) {
                    Iop.PopMuteClr(Iop.GetWorkMode());
                    return;
                }
                return;
            default:
                return;
        }
    }

    private boolean SetMicDigitalEnhance(int inputValue) {
        if (this.mAudioManager == null) {
            return true;
        }
        this.mAudioManager.setParameters("atcDigtalGain=" + inputValue);
        return true;
    }

    public String ReadMem() {
        if (TsFile.fileIsExists(MeM_FILE)) {
            try {
                this.memString = TsFile.readFileSdcardFile(MeM_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.memString;
    }

    public void WriteMem(String str) {
        if (str != null) {
            try {
                this.memString = str;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            TsFile.writeFileSdcardFile(MeM_FILE, this.memString);
        }
    }
}
