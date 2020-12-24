package com.ts.can;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.bt.BtExe;
import com.ts.main.common.MainUI;
import com.ts.main.common.WinShow;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;
import java.util.Arrays;
import net.easyconn.platform.wrc.core.WrcManager;

public class CanIF {
    public static final int AC_FML_KEY_AC = 4;
    public static final int AC_FML_KEY_HCS = 6;
    public static final int AC_FML_KEY_LOOP = 5;
    public static final int AC_FML_KEY_MODE = 3;
    public static final int AC_FML_KEY_PWR = 0;
    public static final int AC_FML_KEY_QCS = 7;
    public static final int AC_FML_KEY_TEMP = 2;
    public static final int AC_FML_KEY_WIND = 1;
    public static final int AC_KEY_AC = 8;
    public static final int AC_KEY_AC_MAX = 12;
    public static final int AC_KEY_AUTO = 6;
    public static final int AC_KEY_FRONT_WIN = 11;
    public static final int AC_KEY_LOOP = 9;
    public static final int AC_KEY_MODE = 5;
    public static final int AC_KEY_PTC = 7;
    public static final int AC_KEY_PWR = 10;
    public static final int AC_KEY_REAR_WIN = 14;
    public static final int AC_KEY_TEMP_DEC = 2;
    public static final int AC_KEY_TEMP_INC = 1;
    public static final int AC_KEY_WIND_CYCLEC = 13;
    public static final int AC_KEY_WIND_DEC = 4;
    public static final int AC_KEY_WIND_INC = 3;
    public static final int AC_MG_CLOSE_COPWID = 19;
    public static final int AC_MG_CLOSE_HOSTWID = 18;
    public static final int AC_MG_CLOSE_SKYWID = 15;
    public static final int AC_MG_OPEN_COPWID = 17;
    public static final int AC_MG_OPEN_HOSTWID = 16;
    public static final int AC_MG_OPEN_SKYWID = 14;
    public static final int AC_MG_PWR_OFF = 2;
    public static final int AC_MG_PWR_ON = 1;
    public static final int AC_MG_TEMP_DEC = 4;
    public static final int AC_MG_TEMP_HIGH = 8;
    public static final int AC_MG_TEMP_INC = 3;
    public static final int AC_MG_TEMP_LOW = 9;
    public static final int AC_MG_TEMP_VAL_DEC = 6;
    public static final int AC_MG_TEMP_VAL_INC = 5;
    public static final int AC_MG_TEMP_VAL_SET = 7;
    public static final int AC_MG_WIND_DEC = 11;
    public static final int AC_MG_WIND_INC = 10;
    public static final int AC_MG_WIND_MAX = 12;
    public static final int AC_MG_WIND_MIN = 13;
    public static final int CAN_WORKMODE_A2DP = 5;
    public static final int CAN_WORKMODE_ATV = 10;
    public static final int CAN_WORKMODE_AUX1 = 7;
    public static final int CAN_WORKMODE_AUX2 = 8;
    public static final int CAN_WORKMODE_CMMB = 6;
    public static final int CAN_WORKMODE_DISC = 2;
    public static final int CAN_WORKMODE_EXCD = 14;
    public static final int CAN_WORKMODE_EXD = 12;
    public static final int CAN_WORKMODE_EXRAD = 13;
    public static final int CAN_WORKMODE_IPOD = 9;
    public static final int CAN_WORKMODE_MAINUI = 17;
    public static final int CAN_WORKMODE_NONE = 0;
    public static final int CAN_WORKMODE_NV = 15;
    public static final int CAN_WORKMODE_RADIO = 1;
    public static final int CAN_WORKMODE_REC = 11;
    public static final int CAN_WORKMODE_SD = 4;
    public static final int CAN_WORKMODE_USB = 3;
    public static final int CAN_WORKMODE_VM6CD = 16;
    public static final int CanMediaStaPause = 2;
    public static final int CanMediaStaPlay = 1;
    public static final int CanMediaStaStop = 0;
    public static final String TAG = "CanIF";
    public static final int UI_A2DP = 7;
    public static final int UI_AM = 1;
    public static final int UI_APPS = 16;
    public static final int UI_AUX = 8;
    public static final int UI_BMW_LMENU = 80;
    public static final int UI_CD = 4;
    public static final int UI_FM = 9;
    public static final int UI_FM1 = 2;
    public static final int UI_FM2 = 3;
    public static final int UI_MENU = 18;
    public static final int UI_RETURN = 17;
    public static final int UI_RSYS = 15;
    public static final int UI_SXM = 13;
    public static final int UI_TV2 = 14;
    public static final int UI_USB = 10;
    public static final int UI_USB1 = 5;
    public static final int UI_USB2 = 6;
    public static final int UI_XM1 = 11;
    public static final int UI_XM2 = 12;
    public static CanDataInfo.CAN_ACInfo mACInfo = new CanDataInfo.CAN_ACInfo();
    public static final String[] mCanTypeArray = {"NULL", "NULL", "NULL"};
    public static CanDataInfo.CAN_Msg mCarInfo = new CanDataInfo.CAN_Msg();
    public static CanDataInfo.CAN_DoorInfo mDoorInfo = new CanDataInfo.CAN_DoorInfo();
    public static int mGpsVoiceDelay = 0;
    public static CAN_ID3 mID3 = new CAN_ID3();
    public static MediaInfo mMediaInfo = new MediaInfo();
    public static MediaStatus mMediaSta = new MediaStatus();
    public static NaviInfo mNaviInfo = new NaviInfo();
    public static int mOtherCamMode;
    public static int mOtherCamModeOld;
    public static boolean mbExdAvalid = false;
    public static int mfgAvm = 0;
    public static boolean mfgCamKey;
    public static boolean mfgDealGps = false;
    public static CanTypeStrCallBack mpfnType = null;
    static byte nNaviVoice = 0;
    static byte nSpeakVoice = 0;

    public static class CAN_ID3 {
        public String sAlbum;
        public String sArtist;
        public String sName;
    }

    public static class MediaInfo {
        public int Avalid;
        public int CurHour;
        public int CurMin;
        public int CurNum;
        public int CurSec;
        public int CurTime;
        public int MediaType;
        public int TotalHour;
        public int TotalMin;
        public int TotalNum;
        public int TotalSec;
        public int TotalTime;
        public int fgLoading;
        public int fgNumAvalid;
        public int fgPause;
        public int fgTimeAvalid;
    }

    public static class MediaStatus {
        public int PlaySta;
        public int fgRnd;
        public int fgRpt;
    }

    public static class NaviInfo {
        public int Avalid;
        public int DestDis;
        public int DestDisDw;
        public int DestDisM;
        public int DestTime;
        public int GpsAngle;
        public int LastTime;
        public int MsgC;
        public int MsgDis;
        public int MsgDisM;
        public int Para;
        public int RoadDirInfo;
        public int Speed;
        public int Sta;
        public int TurnIcon;
        public int cPara1;
        public int cPara2;
        public int cPara3;
        public int cPara4;
        public int cPara5;
        public int cPara6;
        public int cPara7;
        public int cPara8;
        public String sNextRoadName;
        public String sRoadName;
    }

    public static int GetWorkMode() {
        return Iop.GetWorkMode();
    }

    public static boolean IsExdMode() {
        return GetWorkMode() == 12;
    }

    public static String byte2UnicodeStr(byte[] data) {
        try {
            int len = data.length / 2;
            int bytes = len * 2;
            for (int i = 0; i < len; i++) {
                if (data[i * 2] == 0 && data[(i * 2) + 1] == 0) {
                    bytes = i * 2;
                }
            }
            return new String(data, 0, bytes, "UNICODE");
        } catch (Exception e) {
            return "";
        }
    }

    public static String byte2String(byte[] b) {
        int Datalen = b.length;
        int i = 0;
        while (true) {
            if (i >= b.length) {
                break;
            } else if (b[i] == 0) {
                Datalen = i;
                break;
            } else {
                i++;
            }
        }
        if (Datalen == 0) {
            return "";
        }
        try {
            if (Datalen == b.length) {
                return new String(b, "GBK");
            }
            return new String(Arrays.copyOf(b, Datalen), "GBK");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean IsExdModeAvalid() {
        return mbExdAvalid;
    }

    public static void SetOtherCamMode(int mode) {
        mOtherCamMode = mode;
        if (mOtherCamModeOld != mOtherCamMode) {
            Log.d(TAG, "Other Camera Mode Change, Old = " + mOtherCamModeOld + ", Current = " + mOtherCamMode);
            mOtherCamModeOld = mOtherCamMode;
        }
    }

    public static int GetOtherCamMode() {
        return mOtherCamMode;
    }

    public static boolean IsSetMenuRvsAssistLineAvalid() {
        if (CanJni.GetCanType() == 6 && (CanJni.GetSubType() == 7 || CanJni.GetSubType() == 11)) {
            return false;
        }
        return true;
    }

    public static boolean IsSetMenuAirConditionerAvalid() {
        if (CanJni.GetCanType() == 6 && (CanJni.GetSubType() == 7 || CanJni.GetSubType() == 11)) {
            return false;
        }
        return true;
    }

    public static boolean IsCanHave360Camera() {
        switch (CanJni.GetCanType()) {
            case 6:
                if (FtSet.GetCanSubT() == 1 || FtSet.GetCanSubT() == 2 || FtSet.GetCanSubT() == 5 || FtSet.GetCanSubT() == 6 || FtSet.GetCanSubT() == 9 || FtSet.GetCanSubT() == 8) {
                    return true;
                }
                return false;
            case 145:
                if (CanJni.GetSubType() == 3) {
                    return true;
                }
                return false;
            case 199:
                if (CanJni.GetSubType() == 3) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public static boolean Deal360CameraKey() {
        switch (CanJni.GetCanType()) {
            case 6:
                if (!IsCanHave360Camera()) {
                    return false;
                }
                CanJni.NissanCamera360Key(1);
                return true;
            case 46:
                if (CanJni.GetSubType() != 1) {
                    return false;
                }
                CanJni.HmS5CameraSet(128);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanJni.HmS5CameraSet(0);
                return true;
            case 47:
                CanJni.QCCamSwitch();
                return true;
            case 145:
                if (CanJni.GetSubType() != 3) {
                    return false;
                }
                CanJni.RzcSciCameraSet(0, 1);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                CanJni.RzcSciCameraSet(0, 0);
                return true;
            case 199:
                if (!IsCanHave360Camera()) {
                    return false;
                }
                CanJni.NissanCamera360Key(1);
                return true;
            default:
                return false;
        }
    }

    public static int IsHaveIco(int ico) {
        int[] icoArray = new int[100];
        FtSet.GetIcon(icoArray);
        for (int i = 0; i < 50; i++) {
            if (icoArray[i] == ico) {
                return 1;
            }
        }
        return 0;
    }

    public static boolean IsHaveIcoExdRadio() {
        return IsHaveIco(16) != 0;
    }

    public static boolean IsCurExdRadioWin(String strTopActivity) {
        if (strTopActivity == null) {
            return false;
        }
        if (strTopActivity.equals("com.ts.can.honda.accord.CanAccordRadioActivity")) {
            return true;
        }
        if (strTopActivity.equals("com.ts.can.honda.crosstour.CanCrstourRadioActivity")) {
            return true;
        }
        if (strTopActivity.equals("com.ts.can.honda.accord_xbs.CanAccordXbsRadioActivity")) {
            return true;
        }
        return false;
    }

    public static boolean GotoExdRadWin() {
        if (!IsHaveIcoExdRadio()) {
            return false;
        }
        switch (CanJni.GetCanType()) {
            case 9:
            case 270:
                WinShow.show("com.ts.MainUI", "com.ts.can.honda.accord.CanAccordRadioActivity");
                return true;
            case 20:
                WinShow.show("com.ts.MainUI", "com.ts.can.honda.crosstour.CanCrstourRadioActivity");
                return true;
            case 96:
                WinShow.show("com.ts.MainUI", "com.ts.can.honda.accord_xbs.CanAccordXbsRadioActivity");
                return true;
            default:
                return false;
        }
    }

    public static boolean IsCurExdWin(String strTopActivity) {
        if (strTopActivity == null) {
            return false;
        }
        if (strTopActivity.equals("com.ts.can.benc.withcd.CanBencWithCDExdActivity")) {
            return true;
        }
        if (strTopActivity.equals("com.ts.can.bmw.withcd.CanBmwWithCDExdActivity")) {
            return true;
        }
        return false;
    }

    public static void GotoExdMode() {
        switch (CanJni.GetCanType()) {
            case 9:
            case 270:
                if (IsHaveIcoExdRadio()) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.honda.accord.CanAccordRadioActivity");
                }
                return;
            case 20:
                if (IsHaveIcoExdRadio()) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.honda.crosstour.CanCrstourRadioActivity");
                }
                return;
            case 29:
                if (IsHaveIco(15) != 0) {
                    if (38 == CanJni.GetCanFsTp()) {
                        WinShow.show("com.ts.MainUI", "com.ts.can.chrysler.rz.CanRZygCDActivity");
                    } else {
                        WinShow.show("com.ts.MainUI", "com.ts.can.chrysler.CanChrOthCDActivity");
                    }
                }
                return;
            case 36:
                if (IsHaveIco(15) != 0) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.fiat.CanFiatAllExdActivity");
                }
                return;
            case 37:
                if (IsHaveIco(15) != 0) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.fiat.CanFiatBravoExdActivity");
                }
                return;
            case 39:
                if (IsHaveIco(15) != 0) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.mzd.axela.CanMzd3CDActivity");
                }
                return;
            case 69:
            case Can.CAN_MZD_TXB:
                if (IsHaveIco(15) != 0) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.mzd.cx4.CanMzdCx4CDActivity");
                }
                return;
            case 88:
                if (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 5) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.gm.mkc.CanLincsMkcExdActivity");
                } else if (CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9 || CanJni.GetSubType() == 11) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.gm.ats.CanCadillacAtsExdActivity");
                } else {
                    WinShow.show("com.ts.MainUI", "com.ts.can.gm.xt5.CanCadillacXt5ExdActivity");
                }
                return;
            case 96:
                if (IsHaveIcoExdRadio()) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.honda.accord_xbs.CanAccordXbsRadioActivity");
                }
                return;
            case 101:
                if (IsHaveIco(15) != 0) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.psa.pg408.CanPg408ExdActivity");
                }
                return;
            case 107:
                if (IsHaveIco(15) != 0) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.nissan.CanNissanOldDeviceActivity");
                }
                return;
            case 116:
                if (IsHaveIco(15) != 0) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.honda.accord8.CanAccord8ExdActivity");
                }
                return;
            case 118:
                WinShow.show("com.ts.MainUI", "com.ts.can.cadillac.withcd.CanCadillacWithCDExdActivity");
                return;
            case 136:
                WinShow.show("com.ts.MainUI", "com.ts.can.cadillac.xhd.CanCadillacAtsXhdExdActivity");
                return;
            case 138:
                WinShow.show("com.ts.MainUI", "com.ts.can.bmw.withcd.CanBmwWithCDExdActivity");
                return;
            case Can.CAN_BENC_ZMYT:
                WinShow.show("com.ts.MainUI", "com.ts.can.benc.withcd.CanBencWithCDExdActivity");
                return;
            case Can.CAN_AUDI_ZMYT:
                WinShow.show("com.ts.MainUI", "com.ts.can.audi.xhd.CanAudiWithCDExdActivity");
                return;
            case Can.CAN_HONDA_WC:
                if ((CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8) && IsHaveIco(15) != 0) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.CanBaseCarDeviceActivity");
                }
                return;
            case 162:
                WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                return;
            case 164:
                WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                return;
            case 168:
                WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                return;
            case 171:
                WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                return;
            case 176:
                WinShow.show("com.ts.MainUI", "com.ts.can.bmw.zmyt.CanBmwZmytWithCDExdActivity");
                return;
            case 181:
                WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                return;
            case 183:
                WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                return;
            case 199:
                if (IsHaveIco(15) != 0) {
                    if (CanJni.GetSubType() == 4) {
                        WinShow.show("com.ts.MainUI", "com.ts.can.nissan.CanNissanOldDeviceActivity");
                    } else if (CanJni.GetSubType() == 7) {
                        WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                    }
                }
                return;
            case Can.CAN_LEXUS_IZ:
            case Can.CAN_TEANA_OLD_DJ:
            case Can.CAN_VOLVO_XFY:
            case Can.CAN_CC_HF_DJ:
            case Can.CAN_MZD_LUOMU:
            case 256:
            case 261:
            case 266:
            case 274:
            case 284:
                WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                return;
            case Can.CAN_LEXUS_ZMYT:
            case 276:
                WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                return;
            case 260:
                if (IsHaveIco(15) != 0) {
                    WinShow.show("com.ts.MainUI", "com.ts.can.CanBaseCarDeviceActivity");
                }
                return;
            default:
                return;
        }
    }

    public static int DealExdKey(int nKey) {
        Log.d(TAG, "[lgb]DealExdKey");
        switch (CanJni.GetCanType()) {
            case 9:
            case 270:
                return DealAccordKey(nKey);
            case 20:
                return DealCrosstourKey(nKey);
            case 29:
                return DealChrOthKey(nKey);
            case 36:
                return DealFiatAllKey(nKey);
            case 37:
                return DealFiatBravoKey(nKey);
            case 39:
                return DealMzd3Key(nKey);
            case 69:
            case Can.CAN_MZD_TXB:
                return DealMzdCx4Key(nKey);
            case 96:
                return DealAccordXbsKey(nKey);
            case 101:
                return DealPg408Key(nKey);
            case 162:
                return DealChrOthWcKey(nKey);
            case 164:
                return DealMzdWcKey(nKey);
            case 168:
                CanDataInfo.CrownWcHeadStatus headInfo = new CanDataInfo.CrownWcHeadStatus();
                CanJni.CrownWcGetHeadStatus(headInfo);
                if (headInfo.Mode == 1) {
                    return DealCrownWcCDKey(nKey);
                }
                if (headInfo.Mode == 0) {
                    return DealCrownWcRadioKey(nKey);
                }
                return 0;
            case 171:
                DealHondaOldSysWcKey(nKey);
                return 0;
            case 181:
                DealHondaODKey(nKey);
                return 0;
            case Can.CAN_CC_HF_DJ:
                DealCcHfDjKey(nKey);
                return 0;
            default:
                return 0;
        }
    }

    private static int DealHondaODKey(int nKey) {
        switch (nKey) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                CanJni.HondaOdTouchKey(145, 3);
                CanJni.HondaOdTouchKey(145, 0);
                return 1;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                CanJni.HondaOdTouchKey(145, 4);
                CanJni.HondaOdTouchKey(145, 0);
                return 1;
            case 46:
            case KeyDef.RKEY_FF /*293*/:
                CanJni.HondaOdTouchKey(145, 1);
                CanJni.HondaOdTouchKey(145, 0);
                return 1;
            case 47:
            case KeyDef.RKEY_FR /*294*/:
                CanJni.HondaOdTouchKey(145, 2);
                CanJni.HondaOdTouchKey(145, 0);
                return 1;
            default:
                return 0;
        }
    }

    private static int DealHondaOldSysWcKey(int nKey) {
        switch (nKey) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                CanJni.HondaOldSysWcMediaCmd(7, 0);
                return 1;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                CanJni.HondaOldSysWcMediaCmd(7, 1);
                return 1;
            case 68:
            case 288:
                CanJni.HondaOldSysWcMediaCmd(7, 1);
                return 1;
            case 69:
            case 287:
                CanJni.HondaOldSysWcMediaCmd(7, 0);
                return 1;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                CanJni.HondaOldSysWcMediaCmd(7, 1);
                return 1;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                CanJni.HondaOldSysWcMediaCmd(7, 0);
                return 1;
            default:
                return 0;
        }
    }

    private static void RadioCtrl(int cmd, int para) {
        CanJni.CrownWcTunerSet(cmd, para);
    }

    private static int DealCrownWcRadioKey(int nKey) {
        switch (nKey) {
            case 12:
                return 1;
            case 32:
            case 276:
                RadioCtrl(8, 1);
                return 1;
            case 33:
            case 277:
                RadioCtrl(8, 2);
                return 1;
            case 34:
            case 278:
                RadioCtrl(8, 3);
                return 1;
            case 35:
            case 279:
                RadioCtrl(8, 4);
                return 1;
            case 36:
            case 280:
                RadioCtrl(8, 5);
                return 1;
            case 37:
            case 281:
                RadioCtrl(8, 6);
                return 1;
            case 43:
            case 263:
                RadioCtrl(16, 0);
                return 1;
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                RadioCtrl(1, 0);
                return 1;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                RadioCtrl(1, 1);
                return 1;
            case 46:
            case KeyDef.RKEY_FF /*293*/:
                RadioCtrl(1, 0);
                return 1;
            case 47:
            case KeyDef.RKEY_FR /*294*/:
                RadioCtrl(1, 1);
                return 1;
            case 51:
                RadioCtrl(16, 0);
                return 1;
            case 52:
                CanDataInfo.CrownWcTunerInfo tunerInfo = new CanDataInfo.CrownWcTunerInfo();
                CanJni.CrownWcGetTunerInfo(tunerInfo);
                if (tunerInfo.Band == 2) {
                    RadioCtrl(3, 2);
                    return 1;
                }
                RadioCtrl(3, 1);
                return 1;
            case 53:
                RadioCtrl(3, 0);
                return 1;
            case 56:
            case 516:
                RadioCtrl(2, 0);
                return 1;
            case 57:
            case 515:
                RadioCtrl(2, 1);
                return 1;
            case 58:
            case KeyDef.RKEY_AMS /*295*/:
                RadioCtrl(19, 1);
                return 1;
            case 59:
            case KeyDef.RKEY_RADIO_SCAN /*296*/:
                RadioCtrl(17, 1);
                return 1;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                RadioCtrl(1, 0);
                return 1;
            case 67:
            case 290:
                RadioCtrl(1, 1);
                return 1;
            case 68:
            case 288:
                RadioCtrl(1, 1);
                return 1;
            case 69:
            case 287:
                RadioCtrl(1, 0);
                return 1;
            case 75:
            case KeyDef.RKEY_RADIO_1S /*323*/:
                RadioCtrl(4, 1);
                return 1;
            case 76:
            case KeyDef.RKEY_RADIO_2S /*324*/:
                RadioCtrl(4, 2);
                return 1;
            case 77:
            case KeyDef.RKEY_RADIO_3S /*325*/:
                RadioCtrl(4, 3);
                return 1;
            case 78:
            case KeyDef.RKEY_RADIO_4S /*326*/:
                RadioCtrl(4, 4);
                return 1;
            case 79:
            case KeyDef.RKEY_RADIO_5S /*327*/:
                RadioCtrl(4, 5);
                return 1;
            case 80:
            case KeyDef.RKEY_RADIO_6S /*328*/:
                RadioCtrl(4, 6);
                return 1;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                RadioCtrl(1, 0);
                return 1;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                RadioCtrl(1, 1);
                return 1;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                RadioCtrl(1, 1);
                return 1;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                RadioCtrl(1, 0);
                return 1;
            default:
                return 0;
        }
    }

    public static int DealCrownWcCDKey(int nKey) {
        switch (nKey) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                CrownWcNext();
                break;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                CrownWcPrev();
                break;
            case 46:
            case KeyDef.RKEY_FF /*293*/:
                CrownWcFF();
                break;
            case 47:
            case KeyDef.RKEY_FR /*294*/:
                CrownWcFR();
                break;
            case 61:
            case 300:
                CrownWcRdm();
                break;
            case 62:
            case 301:
                CrownWcRpt();
                break;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                CrownWcNext();
                break;
            case 67:
            case 290:
                CrownWcPrev();
                break;
            case 68:
            case 288:
                CrownWcPrev();
                break;
            case 69:
            case 287:
                CrownWcNext();
                break;
            case 90:
                CrownWcPlay();
                break;
            case 91:
                CrownWcPause();
                break;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                CrownWcNext();
                break;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                CrownWcPrev();
                break;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                CrownWcNext();
                break;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                CrownWcPrev();
                break;
        }
        return 1;
    }

    public static void CrownWcPlay() {
        CanJni.CrownWcCdSet(1, 0);
    }

    public static void CrownWcPause() {
        CanJni.CrownWcCdSet(2, 0);
    }

    public static void CrownWcRpt() {
        CanJni.CrownWcCdSet(16, 1);
    }

    public static void CrownWcRdm() {
        CanJni.CrownWcCdSet(18, 1);
    }

    public static void CrownWcNext() {
        CanJni.CrownWcCdSet(7, 1);
    }

    public static void CrownWcPrev() {
        CanJni.CrownWcCdSet(7, 0);
    }

    public static void CrownWcFF() {
        CanJni.CrownWcCdSet(8, 1);
    }

    public static void CrownWcFR() {
        CanJni.CrownWcCdSet(8, 0);
    }

    public static int DealMzdWcKey(int nKey) {
        CanDataInfo.MzdWcCD cdInfo = new CanDataInfo.MzdWcCD();
        CanJni.MzdWcGetCDInfo(cdInfo);
        switch (nKey) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                MzdWcNext();
                break;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                MzdWcPrev();
                break;
            case 46:
            case KeyDef.RKEY_FF /*293*/:
                MzdWcFF();
                break;
            case 47:
            case KeyDef.RKEY_FR /*294*/:
                MzdWcFR();
                break;
            case 61:
            case 300:
                MzdWcRdm(cdInfo.Rdm);
                break;
            case 62:
            case 301:
                MzdWcRpt(cdInfo.Rpt);
                break;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                MzdWcNext();
                break;
            case 67:
            case 290:
                MzdWcPrev();
                break;
            case 68:
            case 288:
                MzdWcPrev();
                break;
            case 69:
            case 287:
                MzdWcNext();
                break;
            case 90:
                MzdWcPlay();
                break;
            case 91:
                MzdWcPause();
                break;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                MzdWcNext();
                break;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                MzdWcPrev();
                break;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                MzdWcNext();
                break;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                MzdWcPrev();
                break;
        }
        return 1;
    }

    public static void MzdWcPlay() {
        CanJni.MzdWcCdCmd(1, 0);
    }

    public static void MzdWcPause() {
        CanJni.MzdWcCdCmd(2, 0);
    }

    public static void MzdWcRpt(int rpt) {
        CanJni.MzdWcCdCmd(3, rpt != 2 ? 2 : 0);
    }

    public static void MzdWcRdm(int rdm) {
        CanJni.MzdWcCdCmd(5, rdm != 2 ? 2 : 0);
    }

    public static void MzdWcNext() {
        CanJni.MzdWcCdCmd(7, 0);
    }

    public static void MzdWcPrev() {
        CanJni.MzdWcCdCmd(7, 1);
    }

    public static void MzdWcFF() {
        CanJni.MzdWcCdCmd(8, 0);
    }

    public static void MzdWcFR() {
        CanJni.MzdWcCdCmd(8, 1);
    }

    public static int DealAccordKey(int nkey) {
        switch (nkey) {
            case 12:
                return 1;
            case 32:
            case 276:
                CanJni.AccordRadioCtrl(55, 0);
                return 1;
            case 33:
            case 277:
                CanJni.AccordRadioCtrl(55, 1);
                return 1;
            case 34:
            case 278:
                CanJni.AccordRadioCtrl(55, 2);
                return 1;
            case 35:
            case 279:
                CanJni.AccordRadioCtrl(55, 3);
                return 1;
            case 36:
            case 280:
                CanJni.AccordRadioCtrl(55, 4);
                return 1;
            case 37:
            case 281:
                CanJni.AccordRadioCtrl(55, 5);
                return 1;
            case 43:
            case 263:
                CanJni.AccordRadioCtrl(80, 0);
                return 1;
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                CanJni.AccordRadioCtrl(48, 1);
                return 1;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                CanJni.AccordRadioCtrl(48, 2);
                return 1;
            case 46:
            case KeyDef.RKEY_FF /*293*/:
                CanJni.AccordRadioCtrl(48, 1);
                return 1;
            case 47:
            case KeyDef.RKEY_FR /*294*/:
                CanJni.AccordRadioCtrl(48, 2);
                return 1;
            case 51:
                CanJni.AccordRadioCtrl(80, 0);
                return 1;
            case 52:
                CanJni.AccordRadioCtrl(56, 1);
                return 1;
            case 53:
                CanJni.AccordRadioCtrl(56, 2);
                return 1;
            case 56:
            case 516:
                CanJni.AccordRadioCtrl(53, 2);
                return 1;
            case 57:
            case 515:
                CanJni.AccordRadioCtrl(53, 1);
                return 1;
            case 58:
            case KeyDef.RKEY_AMS /*295*/:
                CanJni.AccordRadioCtrl(52, 1);
                return 1;
            case 59:
            case KeyDef.RKEY_RADIO_SCAN /*296*/:
                CanJni.AccordRadioCtrl(49, 1);
                return 1;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                CanJni.AccordRadioCtrl(48, 1);
                return 1;
            case 67:
            case 290:
                CanJni.AccordRadioCtrl(48, 2);
                return 1;
            case 68:
            case 288:
                CanJni.AccordRadioCtrl(48, 2);
                return 1;
            case 69:
            case 287:
                CanJni.AccordRadioCtrl(48, 1);
                return 1;
            case 75:
            case KeyDef.RKEY_RADIO_1S /*323*/:
                CanJni.AccordRadioCtrl(50, 0);
                return 1;
            case 76:
            case KeyDef.RKEY_RADIO_2S /*324*/:
                CanJni.AccordRadioCtrl(50, 1);
                return 1;
            case 77:
            case KeyDef.RKEY_RADIO_3S /*325*/:
                CanJni.AccordRadioCtrl(50, 2);
                return 1;
            case 78:
            case KeyDef.RKEY_RADIO_4S /*326*/:
                CanJni.AccordRadioCtrl(50, 3);
                return 1;
            case 79:
            case KeyDef.RKEY_RADIO_5S /*327*/:
                CanJni.AccordRadioCtrl(50, 4);
                return 1;
            case 80:
            case KeyDef.RKEY_RADIO_6S /*328*/:
                CanJni.AccordRadioCtrl(50, 5);
                return 1;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                CanJni.AccordRadioCtrl(48, 1);
                return 1;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                CanJni.AccordRadioCtrl(48, 2);
                return 1;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                CanJni.AccordRadioCtrl(48, 2);
                return 1;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                CanJni.AccordRadioCtrl(48, 1);
                return 1;
            default:
                return 0;
        }
    }

    public static int DealAccordXbsKey(int nkey) {
        switch (nkey) {
            case 12:
                CanJni.Yg9XbsRadioCtrl(80, 0);
                return 1;
            case 32:
            case 276:
                CanJni.Yg9XbsRadioCtrl(6, 1);
                return 1;
            case 33:
            case 277:
                CanJni.Yg9XbsRadioCtrl(6, 2);
                return 1;
            case 34:
            case 278:
                CanJni.Yg9XbsRadioCtrl(6, 3);
                return 1;
            case 35:
            case 279:
                CanJni.Yg9XbsRadioCtrl(6, 4);
                return 1;
            case 36:
            case 280:
                CanJni.Yg9XbsRadioCtrl(6, 5);
                return 1;
            case 37:
            case 281:
                CanJni.Yg9XbsRadioCtrl(6, 6);
                return 1;
            case 43:
            case 263:
                CanJni.Yg9XbsRadioCtrl(80, 0);
                return 1;
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                CanJni.Yg9XbsRadioCtrl(2, 0);
                return 1;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                CanJni.Yg9XbsRadioCtrl(3, 0);
                return 1;
            case 46:
            case KeyDef.RKEY_FF /*293*/:
                CanJni.Yg9XbsRadioCtrl(4, 0);
                return 1;
            case 47:
            case KeyDef.RKEY_FR /*294*/:
                CanJni.Yg9XbsRadioCtrl(5, 2);
                return 1;
            case 51:
                CanJni.Yg9XbsRadioCtrl(80, 0);
                return 1;
            case 52:
                CanJni.Yg9XbsRadioCtrl(81, 0);
                return 1;
            case 53:
                CanJni.Yg9XbsRadioCtrl(81, 1);
                return 1;
            case 56:
            case 516:
                CanJni.Yg9XbsRadioCtrl(8, 0);
                return 1;
            case 57:
            case 515:
                CanJni.Yg9XbsRadioCtrl(9, 0);
                return 1;
            case 58:
            case KeyDef.RKEY_AMS /*295*/:
                return 1;
            case 59:
            case KeyDef.RKEY_RADIO_SCAN /*296*/:
                CanJni.Yg9XbsRadioCtrl(10, 0);
                return 1;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                CanJni.Yg9XbsRadioCtrl(8, 0);
                return 1;
            case 67:
            case 290:
                CanJni.Yg9XbsRadioCtrl(9, 0);
                return 1;
            case 68:
            case 288:
                CanJni.Yg9XbsRadioCtrl(3, 0);
                return 1;
            case 69:
            case 287:
                CanJni.Yg9XbsRadioCtrl(2, 0);
                return 1;
            case 75:
            case KeyDef.RKEY_RADIO_1S /*323*/:
                CanJni.Yg9XbsRadioCtrl(7, 1);
                return 1;
            case 76:
            case KeyDef.RKEY_RADIO_2S /*324*/:
                CanJni.Yg9XbsRadioCtrl(7, 2);
                return 1;
            case 77:
            case KeyDef.RKEY_RADIO_3S /*325*/:
                CanJni.Yg9XbsRadioCtrl(7, 3);
                return 1;
            case 78:
            case KeyDef.RKEY_RADIO_4S /*326*/:
                CanJni.Yg9XbsRadioCtrl(7, 4);
                return 1;
            case 79:
            case KeyDef.RKEY_RADIO_5S /*327*/:
                CanJni.Yg9XbsRadioCtrl(7, 5);
                return 1;
            case 80:
            case KeyDef.RKEY_RADIO_6S /*328*/:
                CanJni.Yg9XbsRadioCtrl(7, 6);
                return 1;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                CanJni.Yg9XbsRadioCtrl(5, 0);
                return 1;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                CanJni.Yg9XbsRadioCtrl(4, 0);
                return 1;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                CanJni.Yg9XbsRadioCtrl(3, 0);
                return 1;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                CanJni.Yg9XbsRadioCtrl(2, 0);
                return 1;
            default:
                return 0;
        }
    }

    public static int DealChrOthKey(int nkey) {
        switch (nkey) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                CanJni.ChrOthCDCtrl(4, 0);
                break;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                CanJni.ChrOthCDCtrl(3, 0);
                break;
            case 46:
            case KeyDef.RKEY_FF /*293*/:
                CanJni.ChrOthCDCtrl(5, 0);
                break;
            case 47:
            case KeyDef.RKEY_FR /*294*/:
                CanJni.ChrOthCDCtrl(6, 0);
                break;
            case 60:
            case KeyDef.RKEY_MEDIA_PP /*299*/:
                CanJni.ChrOthCDCtrl(240, 0);
                break;
            case 61:
            case 300:
                CanJni.ChrOthCDCtrl(241, 0);
                break;
            case 62:
            case 301:
                CanJni.ChrOthCDCtrl(Can.CAN_MZD_LUOMU, 0);
                break;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                CanJni.ChrOthCDCtrl(4, 0);
                break;
            case 67:
            case 290:
                CanJni.ChrOthCDCtrl(3, 0);
                break;
            case 68:
            case 288:
                CanJni.ChrOthCDCtrl(3, 0);
                break;
            case 69:
            case 287:
                CanJni.ChrOthCDCtrl(4, 0);
                break;
            case 90:
                CanJni.ChrOthCDCtrl(2, 0);
                break;
            case 91:
                CanJni.ChrOthCDCtrl(1, 0);
                break;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                CanJni.ChrOthCDCtrl(4, 0);
                break;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                CanJni.ChrOthCDCtrl(3, 0);
                break;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                CanJni.ChrOthCDCtrl(4, 0);
                break;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                CanJni.ChrOthCDCtrl(3, 0);
                break;
            case KeyDef.SKEY_PP_1 /*824*/:
                CanJni.ChrOthCDCtrl(240, 0);
                break;
        }
        return 1;
    }

    public static int DealCrosstourKey(int nkey) {
        switch (nkey) {
            case 12:
                return 1;
            case 32:
            case 276:
                CanJni.CrstourRadioCtrl(6, 1);
                return 1;
            case 33:
            case 277:
                CanJni.CrstourRadioCtrl(6, 2);
                return 1;
            case 34:
            case 278:
                CanJni.CrstourRadioCtrl(6, 3);
                return 1;
            case 35:
            case 279:
                CanJni.CrstourRadioCtrl(6, 4);
                return 1;
            case 36:
            case 280:
                CanJni.CrstourRadioCtrl(6, 5);
                return 1;
            case 37:
            case 281:
                CanJni.CrstourRadioCtrl(6, 6);
                return 1;
            case 43:
            case 263:
                CanJni.CrstourRadioCtrl(1, 0);
                return 1;
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                CanJni.CrstourRadioCtrl(4, 0);
                return 1;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                CanJni.CrstourRadioCtrl(5, 0);
                return 1;
            case 46:
            case KeyDef.RKEY_FF /*293*/:
                CanJni.CrstourRadioCtrl(4, 0);
                return 1;
            case 47:
            case KeyDef.RKEY_FR /*294*/:
                CanJni.CrstourRadioCtrl(5, 0);
                return 1;
            case 51:
                CanJni.CrstourRadioCtrl(1, 0);
                return 1;
            case 52:
                CanJni.CrstourRadioCtrl(1, 0);
                return 1;
            case 53:
                CanJni.CrstourRadioCtrl(1, 0);
                return 1;
            case 56:
            case 516:
                CanJni.CrstourRadioCtrl(8, 0);
                return 1;
            case 57:
            case 515:
                CanJni.CrstourRadioCtrl(9, 0);
                return 1;
            case 58:
            case KeyDef.RKEY_AMS /*295*/:
                CanJni.CrstourRadioCtrl(12, 1);
                return 1;
            case 59:
            case KeyDef.RKEY_RADIO_SCAN /*296*/:
                CanJni.CrstourRadioCtrl(10, 1);
                return 1;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                CanJni.CrstourRadioCtrl(4, 0);
                return 1;
            case 67:
            case 290:
                CanJni.CrstourRadioCtrl(5, 0);
                return 1;
            case 68:
            case 288:
                CanJni.CrstourRadioCtrl(4, 0);
                return 1;
            case 69:
            case 287:
                CanJni.CrstourRadioCtrl(5, 0);
                return 1;
            case 75:
            case KeyDef.RKEY_RADIO_1S /*323*/:
                CanJni.CrstourRadioCtrl(7, 1);
                return 1;
            case 76:
            case KeyDef.RKEY_RADIO_2S /*324*/:
                CanJni.CrstourRadioCtrl(7, 2);
                return 1;
            case 77:
            case KeyDef.RKEY_RADIO_3S /*325*/:
                CanJni.CrstourRadioCtrl(7, 3);
                return 1;
            case 78:
            case KeyDef.RKEY_RADIO_4S /*326*/:
                CanJni.CrstourRadioCtrl(7, 4);
                return 1;
            case 79:
            case KeyDef.RKEY_RADIO_5S /*327*/:
                CanJni.CrstourRadioCtrl(7, 5);
                return 1;
            case 80:
            case KeyDef.RKEY_RADIO_6S /*328*/:
                CanJni.CrstourRadioCtrl(7, 6);
                return 1;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                CanJni.CrstourRadioCtrl(4, 0);
                return 1;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                CanJni.CrstourRadioCtrl(5, 0);
                return 1;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                CanJni.CrstourRadioCtrl(4, 0);
                return 1;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                CanJni.CrstourRadioCtrl(5, 0);
                return 1;
            default:
                return 0;
        }
    }

    public static int DealChrOthWcKey(int nkey) {
        CanDataInfo.ChrWcCDInfo mCdInfo = new CanDataInfo.ChrWcCDInfo();
        CanJni.ChryslerWcGetCDInfo(mCdInfo);
        switch (nkey) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                CanJni.ChryslerWcCdCmd(4, 0, 0);
                break;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                CanJni.ChryslerWcCdCmd(3, 0, 0);
                break;
            case 46:
            case KeyDef.RKEY_FF /*293*/:
                CanJni.ChryslerWcCdCmd(4, 1, 0);
                break;
            case 47:
            case KeyDef.RKEY_FR /*294*/:
                CanJni.ChryslerWcCdCmd(3, 1, 0);
                break;
            case 60:
            case KeyDef.RKEY_MEDIA_PP /*299*/:
                if (mCdInfo.DiscSta != 1) {
                    CanJni.ChryslerWcCdCmd(1, 0, 0);
                    break;
                } else {
                    CanJni.ChryslerWcCdCmd(2, 0, 0);
                    break;
                }
            case 61:
            case 300:
                if (mCdInfo.PlayRdm <= 0) {
                    CanJni.ChryslerWcCdCmd(6, 1, 0);
                    break;
                } else {
                    CanJni.ChryslerWcCdCmd(6, 0, 0);
                    break;
                }
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                CanJni.ChryslerWcCdCmd(3, 0, 0);
                break;
            case 67:
            case 290:
                CanJni.ChryslerWcCdCmd(4, 0, 0);
                break;
            case 68:
            case 288:
                CanJni.ChryslerWcCdCmd(3, 1, 0);
                break;
            case 69:
            case 287:
                CanJni.ChryslerWcCdCmd(4, 1, 0);
                break;
            case 90:
                CanJni.ChryslerWcCdCmd(1, 0, 0);
                break;
            case 91:
                CanJni.ChryslerWcCdCmd(2, 0, 0);
                break;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                CanJni.ChryslerWcCdCmd(3, 0, 0);
                break;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                CanJni.ChryslerWcCdCmd(4, 0, 0);
                break;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                CanJni.ChryslerWcCdCmd(3, 0, 0);
                break;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                CanJni.ChryslerWcCdCmd(4, 0, 0);
                break;
            case KeyDef.SKEY_PP_1 /*824*/:
                if (mCdInfo.DiscSta != 1) {
                    CanJni.ChryslerWcCdCmd(1, 0, 0);
                    break;
                } else {
                    CanJni.ChryslerWcCdCmd(2, 0, 0);
                    break;
                }
        }
        return 1;
    }

    public static void FiatAllPlay() {
        CanJni.FiatAllUsbCtrl(129);
    }

    public static void FiatAllStop() {
        CanJni.FiatAllUsbCtrl(128);
    }

    public static void FiatAllPrev() {
        CanJni.FiatAllUsbCtrl(4);
    }

    public static void FiatAllNext() {
        CanJni.FiatAllUsbCtrl(3);
    }

    public static int DealFiatAllKey(int nkey) {
        switch (nkey) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                FiatAllNext();
                break;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                FiatAllPrev();
                break;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                FiatAllNext();
                break;
            case 67:
            case 290:
                FiatAllPrev();
                break;
            case 68:
            case 288:
                FiatAllPrev();
                break;
            case 69:
            case 287:
                FiatAllNext();
                break;
            case 90:
                FiatAllPlay();
                break;
            case 91:
                FiatAllStop();
                break;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                FiatAllNext();
                break;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                FiatAllPrev();
                break;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                FiatAllNext();
                break;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                FiatAllPrev();
                break;
        }
        return 1;
    }

    public static void FiatBravoPlay() {
        CanJni.FiatBravoUsbCtrl(129);
    }

    public static void FiatBravoStop() {
        CanJni.FiatBravoUsbCtrl(128);
    }

    public static void FiatBravoPrev() {
    }

    public static void FiatBravoNext() {
    }

    public static int DealFiatBravoKey(int nkey) {
        switch (nkey) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                FiatBravoNext();
                break;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                FiatBravoPrev();
                break;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                FiatBravoNext();
                break;
            case 67:
            case 290:
                FiatBravoPrev();
                break;
            case 68:
            case 288:
                FiatBravoPrev();
                break;
            case 69:
            case 287:
                FiatBravoNext();
                break;
            case 90:
                FiatBravoPlay();
                break;
            case 91:
                FiatBravoStop();
                break;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                FiatBravoNext();
                break;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                FiatBravoPrev();
                break;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                FiatBravoNext();
                break;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                FiatBravoPrev();
                break;
        }
        return 1;
    }

    public static void Mzd3Play() {
        CanJni.AxelaCDCtrl(2, 0);
    }

    public static void Mzd3Pause() {
        CanJni.AxelaCDCtrl(1, 0);
    }

    public static void Mzd3Prev() {
        CanJni.AxelaCDCtrl(4, 0);
    }

    public static void Mzd3Next() {
        CanJni.AxelaCDCtrl(3, 0);
    }

    public static void Mzd3PP() {
        CanJni.AxelaCDCtrl(170, 0);
    }

    public static void Mzd3Rdm() {
        CanJni.AxelaCDCtrl(187, 0);
    }

    public static void Mzd3Rpt() {
        CanJni.AxelaCDCtrl(204, 0);
    }

    public static int DealMzd3Key(int nkey) {
        switch (nkey) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                Mzd3Next();
                break;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                Mzd3Prev();
                break;
            case 60:
            case KeyDef.RKEY_MEDIA_PP /*299*/:
                Mzd3PP();
                break;
            case 61:
            case 300:
                Mzd3Rdm();
                break;
            case 62:
            case 301:
                Mzd3Rpt();
                break;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                Mzd3Next();
                break;
            case 67:
            case 290:
                Mzd3Prev();
                break;
            case 68:
            case 288:
                Mzd3Prev();
                break;
            case 69:
            case 287:
                Mzd3Next();
                break;
            case 90:
                Mzd3Play();
                break;
            case 91:
                Mzd3Pause();
                break;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                Mzd3Next();
                break;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                Mzd3Prev();
                break;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                Mzd3Next();
                break;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                Mzd3Prev();
                break;
            case KeyDef.SKEY_PP_1 /*824*/:
                Mzd3PP();
                break;
        }
        return 1;
    }

    public static void MzdCx4Next() {
        CanJni.MzdCx4CdCmd(4);
    }

    public static void MzdCx4Prev() {
        CanJni.MzdCx4CdCmd(5);
    }

    public static void MzdCx4PP() {
        CanJni.MzdCx4CdCmd(170);
    }

    public static void MzdCx4Rdm() {
        CanJni.MzdCx4CdCmd(187);
    }

    public static void MzdCx4Rpt() {
        CanJni.MzdCx4CdCmd(204);
    }

    public static void MzdCx4Play() {
        CanJni.MzdCx4CdCmd(0);
    }

    public static void MzdCx4Pause() {
        CanJni.MzdCx4CdCmd(1);
    }

    public static int DealMzdCx4Key(int key) {
        switch (key) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                MzdCx4Next();
                break;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                MzdCx4Prev();
                break;
            case 60:
            case KeyDef.RKEY_MEDIA_PP /*299*/:
                MzdCx4PP();
                break;
            case 61:
            case 300:
                MzdCx4Rdm();
                break;
            case 62:
            case 301:
                MzdCx4Rpt();
                break;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                MzdCx4Next();
                break;
            case 67:
            case 290:
                MzdCx4Prev();
                break;
            case 68:
            case 288:
                MzdCx4Prev();
                break;
            case 69:
            case 287:
                MzdCx4Next();
                break;
            case 90:
                MzdCx4Play();
                break;
            case 91:
                MzdCx4Pause();
                break;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                MzdCx4Next();
                break;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                MzdCx4Prev();
                break;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                MzdCx4Next();
                break;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                MzdCx4Prev();
                break;
            case KeyDef.SKEY_PP_1 /*824*/:
                MzdCx4PP();
                break;
        }
        return 1;
    }

    public static void Pg408Play() {
        CanJni.Pg408SetUsbCtrl(1);
    }

    public static void Pg408Stop() {
    }

    public static void Pg408Prev() {
        CanJni.Pg408SetUsbCtrl(3);
    }

    public static void Pg408Next() {
        CanJni.Pg408SetUsbCtrl(2);
    }

    public static void Pg408Ff() {
        CanJni.Pg408SetUsbCtrl(4);
    }

    public static void Pg408Fr() {
        CanJni.Pg408SetUsbCtrl(5);
    }

    public static void Pg408Pause() {
        CanJni.Pg408SetUsbCtrl(0);
    }

    public static int DealPg408Key(int nkey) {
        switch (nkey) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                Pg408Next();
                break;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                Pg408Prev();
                break;
            case 46:
            case KeyDef.RKEY_FF /*293*/:
                Pg408Ff();
                break;
            case 47:
            case KeyDef.RKEY_FR /*294*/:
                Pg408Fr();
                break;
            case 60:
            case KeyDef.RKEY_MEDIA_PP /*299*/:
                Pg408Play();
                break;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                Pg408Prev();
                break;
            case 67:
            case 290:
                Pg408Next();
                break;
            case 68:
            case 288:
                Pg408Prev();
                break;
            case 69:
            case 287:
                Pg408Next();
                break;
            case 90:
                Pg408Play();
                break;
            case 91:
                Pg408Pause();
                break;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
                Pg408Prev();
                break;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
                Pg408Next();
                break;
            case KeyDef.SKEY_CHUP_1 /*794*/:
                Pg408Prev();
                break;
            case KeyDef.SKEY_CHDN_1 /*799*/:
                Pg408Next();
                break;
        }
        return 1;
    }

    private static int DealCcHfDjKey(int nKey) {
        switch (nKey) {
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                CanJni.CcHfDjMediaKey(8, 1);
                CanJni.CcHfDjMediaKey(8, 0);
                return 1;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                CanJni.CcHfDjMediaKey(9, 1);
                CanJni.CcHfDjMediaKey(9, 0);
                return 1;
            case 46:
            case 47:
            case KeyDef.RKEY_FF /*293*/:
            case KeyDef.RKEY_FR /*294*/:
                return 1;
            case 515:
                CanJni.CcHfDjMediaKey(9, 1);
                CanJni.CcHfDjMediaKey(9, 0);
                return 1;
            case 516:
                CanJni.CcHfDjMediaKey(8, 1);
                CanJni.CcHfDjMediaKey(8, 0);
                return 1;
            default:
                return 0;
        }
    }

    public static void DealJLACKey(int key) {
        byte[] msg = new byte[8];
        msg[0] = -15;
        msg[1] = 17;
        msg[2] = WrcManager.WrcCallback.MINI_KEY_2;
        msg[3] = 51;
        msg[4] = (byte) key;
        msg[5] = (byte) (((key + 343) & 255) ^ 255);
        Mcu.SendCanMsg(msg, 6);
    }

    public static void DealMgAcSpeadkSet(int cmd, int val) {
        switch (cmd) {
            case 1:
                CanJni.MgAcSpeakSet(1, 1, 0);
                return;
            case 2:
                CanJni.MgAcSpeakSet(1, 2, 0);
                return;
            case 3:
                CanJni.MgAcSpeakSet(2, 1, 0);
                return;
            case 4:
                CanJni.MgAcSpeakSet(2, 2, 0);
                return;
            case 5:
                if (val <= 32) {
                    CanJni.MgAcSpeakSet(2, 3, val);
                    return;
                }
                return;
            case 6:
                if (val <= 32) {
                    CanJni.MgAcSpeakSet(2, 4, val);
                    return;
                }
                return;
            case 7:
                if (val <= 32) {
                    CanJni.MgAcSpeakSet(2, 5, val);
                    return;
                }
                return;
            case 8:
                CanJni.MgAcSpeakSet(2, 6, 0);
                return;
            case 9:
                CanJni.MgAcSpeakSet(2, 7, 0);
                return;
            case 10:
                CanJni.MgAcSpeakSet(3, 1, 0);
                return;
            case 11:
                CanJni.MgAcSpeakSet(3, 2, 0);
                return;
            case 12:
                CanJni.MgAcSpeakSet(3, 3, 0);
                return;
            case 13:
                CanJni.MgAcSpeakSet(3, 4, 0);
                return;
            case 14:
                CanJni.MgAcSpeakSet(4, 1, 0);
                return;
            case 15:
                CanJni.MgAcSpeakSet(4, 2, 0);
                return;
            case 16:
                CanJni.MgAcSpeakSet(5, 1, 0);
                return;
            case 17:
                CanJni.MgAcSpeakSet(5, 2, 0);
                return;
            case 18:
                CanJni.MgAcSpeakSet(5, 3, 0);
                return;
            case 19:
                CanJni.MgAcSpeakSet(5, 4, 0);
                return;
            default:
                return;
        }
    }

    public static void UpdateDoorUI() {
        CanDataInfo.CAN_DoorInfo curDoor = Can.mDoorInfo;
        CanJni.GetDoorInfo(curDoor);
        if (CanJni.GetCanType() == 0) {
            mDoorInfo.LFOpen = 0;
            mDoorInfo.RFOpen = 0;
            mDoorInfo.LROpen = 0;
            mDoorInfo.RROpen = 0;
            mDoorInfo.RearOpen = 0;
            mDoorInfo.HeadOpen = 0;
            return;
        }
        switch (FtSet.GetFdoor()) {
            case 1:
                mDoorInfo.LFOpen = curDoor.RFOpen;
                mDoorInfo.RFOpen = curDoor.LFOpen;
                break;
            case 2:
                mDoorInfo.LFOpen = 0;
                mDoorInfo.RFOpen = 0;
                break;
            default:
                mDoorInfo.LFOpen = curDoor.LFOpen;
                mDoorInfo.RFOpen = curDoor.RFOpen;
                break;
        }
        switch (FtSet.GetBdoor()) {
            case 1:
                mDoorInfo.LROpen = curDoor.RROpen;
                mDoorInfo.RROpen = curDoor.LROpen;
                break;
            case 2:
                mDoorInfo.LROpen = 0;
                mDoorInfo.RROpen = 0;
                break;
            default:
                mDoorInfo.LROpen = curDoor.LROpen;
                mDoorInfo.RROpen = curDoor.RROpen;
                break;
        }
        if (1 == FtSet.GetTrunk()) {
            mDoorInfo.RearOpen = 0;
        } else {
            mDoorInfo.RearOpen = curDoor.RearOpen;
        }
        if (1 == FtSet.GetHood()) {
            mDoorInfo.HeadOpen = 0;
            return;
        }
        mDoorInfo.HeadOpen = curDoor.HeadOpen;
    }

    public static CanDataInfo.QorosBnrBaseMsg UpdateQorosInfo() {
        if (CanJni.GetCanType() != 132) {
            return null;
        }
        CanDataInfo.QorosBnrBaseMsg mBaseInfo = new CanDataInfo.QorosBnrBaseMsg();
        CanJni.QorosBnrGetCarInfo(mBaseInfo);
        if (mBaseInfo.UpdateOnce <= 0) {
            return null;
        }
        return mBaseInfo;
    }

    public static void UpdateCarInfo() {
        CanJni.GetCarInfo(mCarInfo);
    }

    public static int IsPhoneActive() {
        BtExe bt = BtExe.getBtInstance();
        if (bt.getSta() == 3 || bt.getSta() == 2 || bt.getSta() == 4) {
            return 1;
        }
        return 0;
    }

    public static int IsBtMediaActive() {
        return Iop.GetMediaOrBlue();
    }

    public static int BencWithCDRadioUi() {
        if ((FtSet.Getlgb3() & 2) <= 0 || ((FtSet.Getlgb2() & 61440) >> 12) != 1) {
            return 0;
        }
        return 1;
    }

    public static int BmwZmytDisRPart() {
        int temp = (FtSet.Getlgb4() & 3840) >> 8;
        if (temp == 1 || temp == 2) {
            return 0;
        }
        return 1;
    }

    public static boolean IsKeyAvalid() {
        switch (CanJni.GetCanType()) {
            case 6:
                if (7 == CanJni.GetSubType() || 11 == CanJni.GetSubType()) {
                    CanDataInfo.CanNissanWarn canNissanWarn = new CanDataInfo.CanNissanWarn();
                    CanJni.NissanXzcGetWarnData(canNissanWarn);
                    if (canNissanWarn.Sta == 1 || canNissanWarn.Sta == 3) {
                        return false;
                    }
                }
            case 14:
                if (9 == CanJni.GetSubType()) {
                    CanDataInfo.HyundaiWarn canHyundaiWarn = new CanDataInfo.HyundaiWarn();
                    CanJni.HyundaiGetWarmInfo(canHyundaiWarn);
                    if (canHyundaiWarn.Sta == 1 || canHyundaiWarn.Sta == 3) {
                        return false;
                    }
                }
                break;
        }
        return true;
    }

    public static boolean IsRightCameraVedio() {
        switch (CanJni.GetCanType()) {
            case Can.CAN_LUXGEN_WC:
                return true;
            default:
                return false;
        }
    }

    public static void DealSpeakVoice(int sta) {
        switch (CanJni.GetCanType()) {
            case 88:
                if (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 5) {
                    CanDataInfo.GmSb_CarSta mCarSta = new CanDataInfo.GmSb_CarSta();
                    CanJni.GmSbCarGetCarSta(mCarSta);
                    if ((mCarSta.Mode == 2 || (FtSet.Getlgb4() == 0 && mCarSta.Mode == 5)) && sta > 0 && IsExdMode()) {
                        nSpeakVoice = 1;
                        Log.d(TAG, "Speak on ");
                        CanJni.GmSbCarMoudleCtl(3, 1);
                        Iop.RstPort(1);
                        return;
                    }
                    if (MainUI.IsCameraMode() == 0) {
                        CanJni.GmSbCarMoudleCtl(3, 0);
                    }
                    if (nNaviVoice == 0) {
                        Iop.RstPort(0);
                    }
                    Log.d(TAG, "Speak off");
                    nSpeakVoice = 0;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static boolean DealGpsIng() {
        return mfgDealGps;
    }

    public static int IsAvmMode() {
        return mfgAvm;
    }

    public static int IsForceReadNaviSta() {
        if (CanJni.GetCanType() == 260) {
            return 1;
        }
        return 0;
    }

    public static void DealGpsVoice(int sta) {
        int temp = 0;
        switch (CanJni.GetCanType()) {
            case 88:
                if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 10) {
                    CanDataInfo.GM_OnStarSta mStaData = new CanDataInfo.GM_OnStarSta();
                    CanJni.GMGetOnStar(mStaData, new CanDataInfo.GM_PhoneInfo());
                    CanDataInfo.GmSb_CarSta mCarSta = new CanDataInfo.GmSb_CarSta();
                    CanJni.GmSbCarGetCarSta(mCarSta);
                    if (sta == 0 || ((!IsExdMode() && sta != 2) || (mCarSta.Mode == 6 && sta != 2))) {
                        CanJni.GmSbCarMoudleCtl(2, 0);
                        try {
                            Thread.sleep(350);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Iop.RstPort(1);
                        Log.d(TAG, "GpsVoice off");
                        return;
                    }
                    Log.d(TAG, "GpsVoice on " + mStaData.Sta);
                    if (mStaData.Sta == 0) {
                        Iop.RstPort(0);
                    }
                    CanJni.GmSbCarMoudleCtl(2, 1);
                    return;
                } else if (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 5) {
                    CanDataInfo.GmSb_CarSta mCarSta2 = new CanDataInfo.GmSb_CarSta();
                    CanJni.GmSbCarGetCarSta(mCarSta2);
                    if ((mCarSta2.Mode == 2 || (FtSet.Getlgb4() == 0 && mCarSta2.Mode == 5)) && sta > 0 && IsExdMode()) {
                        Log.d(TAG, "GpsVoice on ");
                        Iop.RstPort(1);
                        nNaviVoice = 1;
                        return;
                    }
                    if (nSpeakVoice == 0) {
                        Iop.RstPort(0);
                    }
                    Log.d(TAG, "GpsVoice off");
                    nNaviVoice = 0;
                    return;
                } else if (CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9 || CanJni.GetSubType() == 11) {
                    CanDataInfo.GM_OnStarSta mStaData2 = new CanDataInfo.GM_OnStarSta();
                    CanJni.GMGetOnStar(mStaData2, new CanDataInfo.GM_PhoneInfo());
                    CanDataInfo.GmSb_CarSta mCarSta3 = new CanDataInfo.GmSb_CarSta();
                    CanJni.GmSbCarGetCarSta(mCarSta3);
                    if (sta == 0 || !IsExdMode() || mCarSta3.Mode == 6) {
                        CanJni.GmSbCarMoudleCtl(2, 0);
                        mGpsVoiceDelay = 7;
                        Log.d(TAG, "GpsVoice off");
                        return;
                    }
                    mGpsVoiceDelay = 0;
                    Log.d(TAG, "GpsVoice on " + mStaData2.Sta);
                    if (mStaData2.Sta == 0) {
                        Iop.RstPort(0);
                    }
                    CanJni.GmSbCarMoudleCtl(2, 1);
                    return;
                } else {
                    return;
                }
            case 115:
                if (sta == 0 || !IsExdMode()) {
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                }
                Log.d(TAG, "GpsVoice on ");
                Iop.RstPort(1);
                return;
            case 118:
                if (sta == 0 && IsPhoneActive() == 0) {
                    mGpsVoiceDelay = 0;
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                }
                mGpsVoiceDelay = 7;
                Log.d(TAG, "GpsVoice delay");
                return;
            case 136:
                if (!(sta == 0 && IsPhoneActive() == 0) && IsExdMode()) {
                    mGpsVoiceDelay = 7;
                    Log.d(TAG, "GpsVoice delay");
                    return;
                }
                mGpsVoiceDelay = 0;
                Iop.RstPort(0);
                Log.d(TAG, "GpsVoice off");
                return;
            case 138:
                CanDataInfo.BmwWithCD_WorkMode mWorkMode = new CanDataInfo.BmwWithCD_WorkMode();
                CanJni.BmwWithCDGetWorkMode(mWorkMode);
                if (sta == 0 || mWorkMode.VedioMode == 0) {
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                }
                Log.d(TAG, "GpsVoice on");
                Iop.RstPort(1);
                return;
            case Can.CAN_BENC_ZMYT:
                mfgDealGps = false;
                if (!IsExdMode()) {
                    if (IsPhoneActive() == 0) {
                        mGpsVoiceDelay = 0;
                        Iop.RstPort(0);
                        Log.d(TAG, "GpsVoice off");
                        return;
                    }
                    return;
                } else if (BencWithCDRadioUi() == 0) {
                    if (sta == 0 && IsPhoneActive() == 0) {
                        mGpsVoiceDelay = 0;
                        Iop.RstPort(0);
                        Log.d(TAG, "GpsVoice off");
                        return;
                    }
                    mGpsVoiceDelay = 7;
                    Log.d(TAG, "GpsVoice delay");
                    return;
                } else if (sta == 0 && IsPhoneActive() == 0) {
                    mGpsVoiceDelay = 0;
                    Iop.RstPort(1);
                    Log.d(TAG, "GpsVoice off");
                    return;
                } else {
                    Log.d(TAG, "GpsVoice on");
                    Iop.RstPort(0);
                    mfgDealGps = true;
                    return;
                }
            case Can.CAN_AUDI_ZMYT:
                if (!IsExdMode()) {
                    mGpsVoiceDelay = 0;
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                } else if (sta == 0 && IsPhoneActive() == 0) {
                    mGpsVoiceDelay = 0;
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                } else {
                    mGpsVoiceDelay = 7;
                    Log.d(TAG, "GpsVoice delay");
                    return;
                }
            case 176:
                if (!IsExdMode() && CanJni.GetSubType() != 1) {
                    mGpsVoiceDelay = 0;
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                } else if (sta == 0 && IsPhoneActive() == 0) {
                    mGpsVoiceDelay = 0;
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                } else {
                    mGpsVoiceDelay = 7;
                    Log.d(TAG, "GpsVoice delay");
                    return;
                }
            case Can.CAN_LEXUS_ZMYT:
                if (!IsExdMode()) {
                    mGpsVoiceDelay = 0;
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                } else if (sta == 0 && IsPhoneActive() == 0) {
                    mGpsVoiceDelay = 0;
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                } else {
                    mGpsVoiceDelay = 7;
                    Log.d(TAG, "GpsVoice delay");
                    return;
                }
            case 260:
                if (CanJni.GetSubType() != 2 && CanJni.GetSubType() != 3) {
                    return;
                }
                if (sta == 0 && IsPhoneActive() == 0) {
                    CanJni.MzdRzcCarAudioSet(5, 0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                }
                CanJni.MzdRzcCarAudioSet(5, 1);
                Log.d(TAG, "GpsVoice on");
                return;
            case 261:
                if (IsPhoneActive() > 0) {
                    temp = 4;
                }
                if (sta == 0) {
                    mfgDealGps = false;
                    CanJni.HondaAccord8XbsHostSta(temp & Can.CAN_FLAT_RZC);
                    Log.d(TAG, "GpsVoice off");
                    return;
                }
                mfgDealGps = true;
                CanJni.HondaAccord8XbsHostSta(temp | 1);
                Log.d(TAG, "GpsVoice on ");
                return;
            case 276:
                if (!IsExdMode()) {
                    mGpsVoiceDelay = 0;
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                } else if (sta == 0 && IsPhoneActive() == 0) {
                    mGpsVoiceDelay = 0;
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                } else {
                    mGpsVoiceDelay = 7;
                    Log.d(TAG, "GpsVoice delay");
                    return;
                }
            case 277:
                if (sta == 0 && IsBtMediaActive() == 0) {
                    Iop.RstPort(0);
                    Log.d(TAG, "GpsVoice off");
                    return;
                }
                Iop.RstPort(1);
                Log.d(TAG, "GpsVoice on");
                return;
            default:
                return;
        }
    }

    protected static boolean int2Bool(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    public static void DealFmlACKey(int key, int sta) {
        mACInfo = Can.mACInfo;
        byte[] msg = new byte[8];
        msg[0] = 46;
        msg[1] = -118;
        msg[2] = 2;
        msg[3] = (byte) key;
        if (key != 4 || sta == 0) {
            if (key != 5 || sta == 0) {
                if (key != 6 || sta == 0) {
                    if (key != 7 || sta == 0) {
                        msg[4] = (byte) sta;
                    } else if (int2Bool(mACInfo.fgDFBL)) {
                        msg[4] = 2;
                    } else {
                        msg[4] = 1;
                    }
                } else if (int2Bool(mACInfo.fgRearLight)) {
                    msg[4] = 2;
                } else {
                    msg[4] = 1;
                }
            } else if (int2Bool(mACInfo.fgInnerLoop)) {
                msg[4] = 1;
            } else {
                msg[4] = 2;
            }
        } else if (int2Bool(mACInfo.fgAC)) {
            msg[4] = 2;
        } else {
            msg[4] = 1;
        }
        msg[5] = (byte) ((((key + Can.CAN_BENC_ZMYT) + msg[4]) & 255) ^ 255);
        Mcu.SendCanMsg(msg, 6);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg[4] = 0;
        msg[5] = (byte) ((((key + Can.CAN_BENC_ZMYT) + msg[4]) & 255) ^ 255);
        Mcu.SendCanMsg(msg, 6);
    }

    public static void DealCam360Key(int nKeyCode) {
        switch (CanJni.GetCanType()) {
            case 6:
                Log.d(TAG, "**********360 Key,Can.CAN_NISSAN**********");
                if (CanJni.GetSubType() == 1 || FtSet.GetCanSubT() == 5 || FtSet.GetCanSubT() == 9 || FtSet.GetCanSubT() == 8) {
                    if (nKeyCode == 92) {
                        CanJni.NissanCamera360Key(3);
                        return;
                    } else {
                        CanJni.NissanCamera360Key(4);
                        return;
                    }
                } else if ((CanJni.GetSubType() == 2 || FtSet.GetCanSubT() == 6) && nKeyCode == 92) {
                    CanJni.NissanCamera360Key(1);
                    return;
                } else {
                    return;
                }
            case 46:
                if (CanJni.GetSubType() == 1 && nKeyCode == 92) {
                    CanJni.HmS5CameraSet(128);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CanJni.HmS5CameraSet(0);
                    return;
                }
                return;
            case 47:
                if (nKeyCode == 92) {
                    CanJni.QCCamSwitch();
                    return;
                }
                return;
            case 145:
                if (CanJni.GetSubType() == 3 && nKeyCode == 92) {
                    CanJni.RzcSciCameraSet(0, 1);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    CanJni.RzcSciCameraSet(0, 0);
                    return;
                }
                return;
            case 199:
                Log.d(TAG, "**********360 Key,Can.CAN_NISSAN**********");
                if (CanJni.GetSubType() == 3 && nKeyCode == 92) {
                    CanJni.NissanCamera360Key(1);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0076 A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean RvsEntExdMode() {
        /*
            r0 = 1
            int r1 = com.lgb.canmodule.CanJni.GetCanType()
            r2 = 88
            if (r1 != r2) goto L_0x0078
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            r2 = 4
            if (r1 != r2) goto L_0x0017
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            if (r1 != 0) goto L_0x0017
        L_0x0016:
            return r0
        L_0x0017:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            r2 = 5
            if (r1 != r2) goto L_0x0024
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            if (r1 == 0) goto L_0x0016
        L_0x0024:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            r2 = 6
            if (r1 != r2) goto L_0x0031
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            if (r1 == 0) goto L_0x0016
        L_0x0031:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            r2 = 7
            if (r1 != r2) goto L_0x003e
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            if (r1 == 0) goto L_0x0016
        L_0x003e:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            r2 = 8
            if (r1 != r2) goto L_0x004c
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            if (r1 == 0) goto L_0x0016
        L_0x004c:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            r2 = 9
            if (r1 != r2) goto L_0x005a
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            if (r1 == 0) goto L_0x0016
        L_0x005a:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            r2 = 11
            if (r1 != r2) goto L_0x0068
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            if (r1 == 0) goto L_0x0016
        L_0x0068:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            r2 = 10
            if (r1 != r2) goto L_0x0076
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            if (r1 == 0) goto L_0x0016
        L_0x0076:
            r0 = 0
            goto L_0x0016
        L_0x0078:
            int r1 = com.lgb.canmodule.CanJni.GetCanType()
            r2 = 118(0x76, float:1.65E-43)
            if (r1 != r2) goto L_0x0087
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            if (r1 != r0) goto L_0x0076
            goto L_0x0016
        L_0x0087:
            int r1 = com.lgb.canmodule.CanJni.GetCanType()
            r2 = 136(0x88, float:1.9E-43)
            if (r1 != r2) goto L_0x0096
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            if (r1 != r0) goto L_0x0076
            goto L_0x0016
        L_0x0096:
            int r1 = com.lgb.canmodule.CanJni.GetCanType()
            r2 = 140(0x8c, float:1.96E-43)
            if (r1 != r2) goto L_0x00a8
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            r1 = r1 & 1
            if (r1 != r0) goto L_0x0076
            goto L_0x0016
        L_0x00a8:
            int r1 = com.lgb.canmodule.CanJni.GetCanType()
            r2 = 152(0x98, float:2.13E-43)
            if (r1 != r2) goto L_0x00ba
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            r1 = r1 & 1
            if (r1 != r0) goto L_0x0076
            goto L_0x0016
        L_0x00ba:
            int r1 = com.lgb.canmodule.CanJni.GetCanType()
            r2 = 176(0xb0, float:2.47E-43)
            if (r1 != r2) goto L_0x00cc
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            r1 = r1 & 1
            if (r1 != r0) goto L_0x0076
            goto L_0x0016
        L_0x00cc:
            int r1 = com.lgb.canmodule.CanJni.GetCanType()
            r2 = 207(0xcf, float:2.9E-43)
            if (r1 != r2) goto L_0x00de
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            r1 = r1 & 1
            if (r1 != r0) goto L_0x0076
            goto L_0x0016
        L_0x00de:
            int r1 = com.lgb.canmodule.CanJni.GetCanType()
            r2 = 229(0xe5, float:3.21E-43)
            if (r1 != r2) goto L_0x00ee
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 != r0) goto L_0x0076
            goto L_0x0016
        L_0x00ee:
            int r1 = com.lgb.canmodule.CanJni.GetCanType()
            r2 = 276(0x114, float:3.87E-43)
            if (r1 != r2) goto L_0x0076
            int r1 = com.yyw.ts70xhw.FtSet.Getlgb1()
            r1 = r1 & 1
            if (r1 != r0) goto L_0x0076
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.can.CanIF.RvsEntExdMode():boolean");
    }

    public static void DealGeelyYjx1ACKey(int key) {
        mACInfo = Can.mACInfo;
        byte[] msg = new byte[8];
        msg[0] = 46;
        msg[1] = -118;
        msg[2] = 4;
        msg[3] = 0;
        if (key == 10) {
            msg[3] = Byte.MIN_VALUE;
        } else if (key == 5) {
            msg[3] = 64;
        } else if (key == 11) {
            msg[3] = WrcManager.WrcCallback.KEY_CENTER;
        } else if (key == 8) {
            msg[3] = 2;
        } else if (key == 12) {
            msg[3] = 1;
        }
        msg[4] = 0;
        int temp = mACInfo.nWindValue;
        if (key == 13) {
            temp = temp >= mACInfo.nWindMax ? 0 : temp + 1;
        } else if (key == 3) {
            temp = temp >= mACInfo.nWindMax ? mACInfo.nWindMax : temp + 1;
        } else if (key == 4 && temp != 0) {
            temp = temp <= 1 ? 1 : temp - 1;
        }
        msg[4] = (byte) (temp << 4);
        if (key == 14) {
            msg[4] = (byte) (msg[4] | 4);
        }
        msg[5] = 0;
        if (key == 9) {
            msg[5] = (byte) (msg[5] | 1);
        }
        msg[6] = 0;
        if (key == 1) {
            msg[6] = (byte) (msg[6] | 2);
        }
        if (key == 2) {
            msg[6] = (byte) (msg[6] | 1);
        }
        msg[7] = (byte) ((((((msg[3] + 142) + msg[4]) + msg[5]) + msg[6]) & 255) ^ 255);
        Mcu.SendCanMsg(msg, 8);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 3; i < 7; i++) {
            msg[i] = 0;
        }
        msg[7] = (byte) ((((((msg[3] + 142) + msg[4]) + msg[5]) + msg[6]) & 255) ^ 255);
        Mcu.SendCanMsg(msg, 8);
    }

    public static void DealXt5Key() {
        if (CanJni.GetCanType() != 88) {
            return;
        }
        if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 10) {
            CanJni.GmSbCarKeyCtl(11, 1);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CanJni.GmSbCarKeyCtl(11, 0);
        }
    }

    public static void GotoAcWin() {
        CanJni.GotoAcWin();
    }

    public static void TouchDownEven() {
        switch (CanJni.GetCanType()) {
            case Can.CAN_BENC_ZMYT:
                Mcu.SendXKey(70);
                Log.d(TAG, "TouchDownEven");
                return;
            default:
                return;
        }
    }

    public static Boolean BmwWithCDCmd(int cmd, int Sta) {
        int temp;
        if (CanJni.GetCanType() != 138) {
            return false;
        }
        switch (cmd) {
            case 0:
                int temp2 = FtSet.Getlgb1();
                if (FtSet.GetCamTrack() > 0) {
                    temp = temp2 & 191;
                } else {
                    temp = temp2 | 64;
                }
                if (Sta <= 0) {
                    CanJni.BmwWithCDCarSet(149, temp & Can.CAN_MG_ZS_WC, 0, 0);
                    break;
                } else {
                    CanJni.BmwWithCDCarSet(149, temp | 4, 0, 0);
                    break;
                }
            case 1:
                if (Sta <= 0) {
                    CanJni.BmwWithCDCarSet(Can.CAN_FORD_WC, 0, 0, 0);
                    break;
                } else {
                    CanJni.BmwWithCDCarSet(Can.CAN_FORD_WC, 1, 0, 0);
                    break;
                }
            case 2:
                if (Sta <= 0) {
                    CanJni.BmwWithCDCarSet(162, 0, 0, 0);
                    break;
                } else {
                    CanJni.BmwWithCDCarSet(162, 4, 0, 0);
                    break;
                }
            case 3:
                if (Sta <= 0) {
                    CanJni.BmwWithCDCarSet(162, 0, 0, 0);
                    break;
                } else {
                    CanJni.BmwWithCDCarSet(162, 5, 0, 0);
                    break;
                }
            case 4:
                if (Sta <= 0) {
                    CanJni.BmwWithCDCarSet(149, FtSet.Getlgb1() | 64, 0, 0);
                    break;
                } else {
                    CanJni.BmwWithCDCarSet(149, FtSet.Getlgb1() & 191, 0, 0);
                    break;
                }
        }
        return true;
    }

    public static void SetCanTypeCb(CanTypeStrCallBack cb) {
        mpfnType = cb;
    }

    public static String[] GetCanTypeArray() {
        Log.d(TAG, "[lgb]###GetCanTypeArray###");
        if (mpfnType == null) {
            return mCanTypeArray;
        }
        Log.d(TAG, "[lgb]###mpfnType.GetCanTypeArray###");
        return mpfnType.GetCanTypeArray();
    }

    public static int GetResId(Context context, String name, String type, String packageName) {
        try {
            return context.getPackageManager().getResourcesForApplication(packageName).getIdentifier(name, type, packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String GetTsResStr(Context context, String name) {
        int resStr = GetResId(context, name, "string", "com.ts.MainUI");
        if (resStr > 0) {
            Log.d(TAG, "###Get resStr = " + resStr);
            return context.getResources().getString(resStr);
        }
        Log.d(TAG, "###Can't Get resStr = " + resStr);
        return "";
    }

    public static String[] GetTsResStrArray(Context context, String name) {
        int resStrArr = GetResId(context, name, "array", "com.ts.MainUI");
        if (resStrArr > 0) {
            Log.d(TAG, "###Get resStrArr = " + resStrArr);
            return context.getResources().getStringArray(resStrArr);
        }
        Log.d(TAG, "###Can't Get resStrArr = " + resStrArr);
        return null;
    }
}
