package com.ts.MainUI;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioSystem;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.txznet.sdk.bean.Poi;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;

public class Evc implements AudioManager.OnAudioFocusChangeListener {
    private static final int COMMON_PARA_SIZE = 24;
    private static final int CONSTANT_256 = 256;
    private static final int CONSTANT_32 = 32;
    private static final int DATA_SIZE = 1444;
    public static final int MAX_VOL_GAIN = 88;
    public static final int NAVI_VOICE_DELAY = 500;
    public static final int PHONE_CALLIN = 1;
    public static final int PHONE_IDLE = 0;
    public static final int PHONE_TALKING = 2;
    public static final int POP_MUTE_DELAYTIME = 30;
    /* access modifiers changed from: private */
    public static String TAG = "Evcsetting";
    public static final int V0L_GAIN_MAX = 100;
    private static final int VOLUME_SIZE = 22;
    public static final int VOL_MAX = 30;
    public static final int WORKMODE_BT_VOL = 19;
    public static final int WORKMODE_NAVI_VOL = 18;
    public static boolean bNaviVol = false;
    private static Evc mEvc = null;
    public static int mSystemState = 0;
    public static int nNaviHunyin = 0;
    public static int nNaviSpeeShow = 0;
    public static int nNaviSpeeking = 0;
    public int Alarm_vol_max = 15;
    public int Gis_vol_max = 100;
    public int NaviVoiceDelay = 0;
    public int Other_vol_max = 30;
    public int PhoneState;
    public int Ring_vol_max = 100;
    public int Sys_vol_max = 100;
    public boolean bGoogle = false;
    private boolean bMusicChange = false;
    boolean bNaviforce;
    boolean bPopMuteDisable = false;
    private int[] g_VolDbtb;
    private int[] g_VolDbtbGlsz;
    private AudioManager mAudioManager;
    protected EvcCallBack mEvcCallBack;
    private TelephonyManager mTelephonyManager;
    private Context m_context;
    int nCheckNavi;
    public int nIsGlsx = 0;
    int nMuteState;
    int nPer;
    public int nWorkModeMute = 0;
    public int nWorkModeReq = 0;
    public int volume_max = 30;

    public Evc() {
        int[] iArr = new int[31];
        iArr[1] = 3;
        iArr[2] = 8;
        iArr[3] = 13;
        iArr[4] = 18;
        iArr[5] = 23;
        iArr[6] = 28;
        iArr[7] = 30;
        iArr[8] = 36;
        iArr[9] = 42;
        iArr[10] = 47;
        iArr[11] = 52;
        iArr[12] = 54;
        iArr[13] = 56;
        iArr[14] = 58;
        iArr[15] = 66;
        iArr[16] = 67;
        iArr[17] = 71;
        iArr[18] = 74;
        iArr[19] = 76;
        iArr[20] = 77;
        iArr[21] = 80;
        iArr[22] = 84;
        iArr[23] = 85;
        iArr[24] = 87;
        iArr[25] = 89;
        iArr[26] = 92;
        iArr[27] = 94;
        iArr[28] = 97;
        iArr[29] = 99;
        iArr[30] = 100;
        this.g_VolDbtb = iArr;
        int[] iArr2 = new int[31];
        iArr2[1] = 20;
        iArr2[2] = 26;
        iArr2[3] = 30;
        iArr2[4] = 34;
        iArr2[5] = 38;
        iArr2[6] = 40;
        iArr2[7] = 42;
        iArr2[8] = 44;
        iArr2[9] = 45;
        iArr2[10] = 47;
        iArr2[11] = 52;
        iArr2[12] = 54;
        iArr2[13] = 56;
        iArr2[14] = 58;
        iArr2[15] = 66;
        iArr2[16] = 67;
        iArr2[17] = 71;
        iArr2[18] = 74;
        iArr2[19] = 76;
        iArr2[20] = 77;
        iArr2[21] = 80;
        iArr2[22] = 84;
        iArr2[23] = 85;
        iArr2[24] = 87;
        iArr2[25] = 89;
        iArr2[26] = 92;
        iArr2[27] = 94;
        iArr2[28] = 97;
        iArr2[29] = 99;
        iArr2[30] = 100;
        this.g_VolDbtbGlsz = iArr2;
        this.mAudioManager = null;
        this.mTelephonyManager = null;
        this.nCheckNavi = 0;
        this.nMuteState = 0;
        this.PhoneState = 0;
        this.mEvcCallBack = null;
        this.bNaviforce = false;
        this.nPer = 0;
    }

    public static Evc GetInstance() {
        if (mEvc == null) {
            mEvc = new Evc();
        }
        return mEvc;
    }

    public int task(int mode) {
        if (mode == 2 && this.nCheckNavi == 0) {
            this.nCheckNavi = 1;
            ChechNaviStream();
        }
        if (this.NaviVoiceDelay > 0) {
            Log.d(TAG, "NaviVoiceDelay==" + this.NaviVoiceDelay);
            this.NaviVoiceDelay--;
            if (this.NaviVoiceDelay == 0) {
                SetGisMute(false);
                Iop.NaviSpeaking(0);
                nNaviSpeeking = 0;
                if (this.bMusicChange) {
                    SetMusicVolume(Iop.GetVolume(0));
                    this.bMusicChange = false;
                }
            }
        }
        if (this.nIsGlsx == 1) {
            if (Iop.GetMute() == 0 && Iop.GetVolume(0) == 0 && nNaviSpeeking == 0 && Iop.GetMediaOrBlue() == 0) {
                Mcu.SetMutestate((byte) 1);
                this.nMuteState = 1;
                Log.d(TAG, "nIsGlsx set mute");
            } else if (this.nMuteState == 1) {
                Mcu.SetMutestate((byte) 0);
                this.nMuteState = 0;
                Log.d(TAG, "nIsGlsx clear mute");
            }
        }
        if (this.bPopMuteDisable) {
            this.bPopMuteDisable = false;
            Iop.PopMuteDelay(20);
        }
        return Iop.EvolTask(mode);
    }

    public void ChechNaviStream() {
        int nVol;
        if (this.mAudioManager != null && StSet.GetNvol() != (nVol = this.mAudioManager.getStreamVolume(10))) {
            Log.d(TAG, "nVol==" + nVol);
            Log.d(TAG, "StSet.GetNvol()==" + StSet.GetNvol());
        }
    }

    /* access modifiers changed from: package-private */
    public void CheckVolMax() {
        this.Sys_vol_max = this.mAudioManager.getStreamMaxVolume(1);
        this.Alarm_vol_max = this.mAudioManager.getStreamMaxVolume(4);
        this.Ring_vol_max = this.mAudioManager.getStreamMaxVolume(2);
        this.Gis_vol_max = this.mAudioManager.getStreamMaxVolume(10);
        Log.d(TAG, "Sys_vol_max==" + this.Sys_vol_max);
        Log.d(TAG, "AudioManager.STREAM_MUSIC==" + this.mAudioManager.getStreamVolume(3));
        Log.d(TAG, "getStreamMaxVolume AudioManager.STREAM_MUSIC==" + this.mAudioManager.getStreamMaxVolume(3));
        Log.d(TAG, "AudioManager.STREAM_GIS==" + this.mAudioManager.getStreamVolume(10));
        Log.d(TAG, "getStreamMaxVolume AudioManager.STREAM_GIS==" + this.mAudioManager.getStreamMaxVolume(10));
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

    class MyOhoneListener extends PhoneStateListener {
        MyOhoneListener() {
        }

        public void onCallStateChanged(int state, String incomingNumber) {
            Log.i(Evc.TAG, "incomingNumber==" + incomingNumber);
            Log.i(Evc.TAG, "state==" + state);
            switch (state) {
                case 0:
                    Iop.BlueSpeaking(0);
                    Evc.this.PhoneState = 0;
                    break;
                case 1:
                    Iop.BlueSpeaking(1);
                    Evc.this.PhoneState = 1;
                    break;
                case 2:
                    Iop.BlueSpeaking(1);
                    Evc.this.PhoneState = 2;
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }

    public void InintEvc(Context aContext) {
        if (this.mAudioManager == null) {
            this.m_context = aContext;
            this.mAudioManager = (AudioManager) this.m_context.getSystemService(Poi.PoiAction.ACTION_AUDIO);
            Log.i(TAG, "SetMyContext mAudioManager==" + this.mAudioManager);
        }
        if (this.mTelephonyManager == null) {
            this.mTelephonyManager = (TelephonyManager) this.m_context.getSystemService("phone");
        }
        this.mTelephonyManager.listen(new MyOhoneListener(), 32);
        CheckVolMax();
        SetNotificationVoume(1);
        SetSystemEnforceVoume(1);
        SetMusicVolume(Iop.GetVolume(0));
        SetGisVolume(StSet.GetNvol(), true);
        SetBtVolume(Iop.GetVolume(1));
        SetSystemVolume(StSet.GetSvol());
        SetRingVolume(StSet.GetRvol());
        SetAlarmVolume(StSet.GetAvol());
        Log.i(TAG, "StSet.GetWorkMode() == " + Iop.GetWorkMode());
    }

    public int SetNaviMixScal(int nScal) {
        if (this.mAudioManager != null) {
            return 1;
        }
        this.mAudioManager.setAudPolicyStrategy(10, 3, nScal);
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

    public void evol_workmode_set(int newmode) {
        if (newmode == 0) {
            if (this.nWorkModeReq != newmode) {
                this.bPopMuteDisable = true;
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
                Iop.PopMuteDelay(this.nWorkModeMute);
            }
            this.bPopMuteDisable = true;
            Log.i(TAG, "requestAudioFocus  newmode==" + newmode);
            int result = this.mAudioManager.requestAudioFocus(this, 3, 1);
            this.mAudioManager.requestAudioFocus(this, 3, 2);
            if (result != 1) {
                Log.e(TAG, "could not get audio focus");
            } else {
                Log.e(TAG, "could  get audio focus == " + this.mAudioManager.isMusicActive());
            }
        }
        if (FtSet.IsIconExist(2) == 1 && newmode != 2) {
            Log.i(TAG, "DiscStop==" + newmode);
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Iop.DiscStop();
                }
            }.start();
        }
        Iop.SetWorkMode(newmode);
        if (!(this.nWorkModeReq == newmode || this.mEvcCallBack == null)) {
            this.mEvcCallBack.DealWorkMode(this.nWorkModeReq, newmode);
        }
        this.nWorkModeReq = newmode;
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
        int nVol;
        Log.i(TAG, "Evol_vol_tune == " + updn + "nNaviSpeeking=" + nNaviSpeeking + " nNaviSpeeShow = " + nNaviSpeeShow);
        evol_mut_set(0);
        if (!bNaviVol || (nNaviSpeeking != 1 && nNaviSpeeShow <= 0)) {
            int nSta = Iop.GetMediaOrBlue();
            Iop.AdjVolume(nSta, updn);
            int nVol2 = Iop.GetVolume(nSta);
            if (nSta == 1) {
                SetBtVolume(nVol2);
            } else {
                SetMusicVolume(nVol2);
            }
        } else {
            int nVol3 = StSet.GetNvol();
            if (updn == 1) {
                if (nVol3 + 3 < this.Gis_vol_max) {
                    nVol = nVol3 + 3;
                } else {
                    nVol = this.Gis_vol_max;
                }
            } else if (nVol3 > 3) {
                nVol = nVol3 - 3;
            } else {
                nVol = 0;
            }
            evol_navivol_set(nVol);
        }
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

    public void evol_mix_set(int onoff, boolean bMute) {
        if (onoff == 1) {
            SetGisMute(false);
            if (nNaviHunyin == 1) {
                ChVolForNavi(bMute);
            }
            SetGisVolume(StSet.GetNvol(), true);
            Iop.NaviSpeaking(onoff);
            this.NaviVoiceDelay = 0;
        } else if (!this.bNaviforce) {
            this.NaviVoiceDelay = 50;
        } else {
            this.NaviVoiceDelay = 500;
        }
    }

    public void evol_navi_set(int onoff, boolean bMute) {
        if (onoff == 1) {
            SetGisMute(false);
            if (nNaviHunyin == 1) {
                ChVolForNavi(bMute);
            }
            if (this.nPer <= 0 || this.nPer > 100) {
                SetGisVolume(StSet.GetNvol(), true);
            } else {
                SetGisVolume((StSet.GetNvol() * this.nPer) / 100, false);
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

    public void SetNaviVolDn(int nPercent) {
        this.nPer = nPercent;
    }

    public void evol_navi_set_force(int onoff) {
        if (onoff == 1) {
            SetGisMute(false);
            if (nNaviHunyin == 1) {
                ChVolForNavi(false);
            }
            if (this.nPer <= 0 || this.nPer > 100) {
                SetGisVolume(StSet.GetNvol(), true);
            } else {
                SetGisVolume((StSet.GetNvol() * this.nPer) / 100, false);
            }
            Iop.NaviSpeaking(onoff);
            nNaviSpeeking = onoff;
            this.NaviVoiceDelay = 500;
            this.bNaviforce = true;
            return;
        }
        this.NaviVoiceDelay = 20;
        this.bNaviforce = false;
    }

    public void evol_blue_set(int onoff) {
        Iop.BlueSpeaking(onoff);
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
        int nGain = FtSet.GetBtMicGain() - 33;
        if (nGain < 0) {
            nGain = 0;
        } else if (nGain > 15) {
            nGain = 15;
        }
        SetMicDigitalEnhance(nGain);
    }

    private int GetVolGain(int nVol, int VolMax) {
        if (VolMax < 0) {
            VolMax = 30;
        }
        if (nVol > VolMax) {
            nVol = VolMax;
        }
        if (this.nIsGlsx == 1) {
            return this.g_VolDbtbGlsz[nVol];
        }
        return this.g_VolDbtb[nVol];
    }

    public int SetGisMute(boolean bMute) {
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamMute(10, bMute);
            return 1;
        }
        Log.d(TAG, "mAudioManager==null SetGisMute" + bMute);
        return 0;
    }

    private int SetGisVolume(int u4Vol, boolean bSave) {
        Log.d(TAG, "SetGisVolume==" + u4Vol);
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamVolume(10, u4Vol, 0);
            if (!bSave) {
                return 1;
            }
            StSet.SetNvol(u4Vol);
            return 1;
        }
        Log.d(TAG, "mAudioManager==null STREAM_GIS" + u4Vol);
        return 1;
    }

    private int SetBtVolume(int u4Vol) {
        Log.d(TAG, "SetBtVolume" + u4Vol);
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamVolume(0, (int) (((double) u4Vol) * 2.5d), 0);
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
        Log.d(TAG, "SetMuscVolume" + u4Vol + "==" + GetVolGain(u4Vol, this.volume_max));
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamVolume(3, GetVolGain(u4Vol, this.volume_max), 0);
            return 1;
        }
        Log.d(TAG, "mAudioManager==null STREAM_MUSIC" + u4Vol);
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
                Log.i(TAG, "onAudioFocusChange  nWorkModeReq==" + this.nWorkModeReq);
                Log.i(TAG, "onAudioFocusChange  Evol.workmode==" + Iop.GetWorkMode());
                Log.i(TAG, "Iop.GetMediaOrBlue()==" + Iop.GetMediaOrBlue());
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
                if (this.nWorkModeReq == Iop.GetWorkMode()) {
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
        if (inputValue < 0 || inputValue > 15) {
            return false;
        }
        byte[] mData = new byte[DATA_SIZE];
        for (int n = 0; n < DATA_SIZE; n++) {
            mData[n] = 0;
        }
        if (AudioSystem.getEmParameter(mData, DATA_SIZE) != 0) {
            return false;
        }
        mData[92] = (byte) (inputValue % 256);
        mData[93] = (byte) (inputValue / 256);
        if (AudioSystem.setEmParameter(mData, DATA_SIZE) == 0) {
            return true;
        }
        return false;
    }
}
