package com.ts.MainUI;

import android.util.Log;
import com.lgb.canmodule.Can;
import com.txznet.sdk.TXZPoiSearchManager;
import com.yyw.ts70xhw.Iop;
import net.easyconn.platform.wrc.core.WrcManager;

public class StTpms {
    public static final int PSK_MAX = 800;
    public static final int PSK_MIN = 200;
    static final String TAG = "StTpms";
    public static final int TEMP_HIGH = 200;
    private static final int TMPS_MAX_NUM = 8;
    public static final int TPMS_HIGHPASK = 6;
    public static final int TPMS_HiGHTEMP = 7;
    public static final int TPMS_LEAK = 4;
    public static final int TPMS_LOWPASK = 5;
    public static final int TPMS_LOW_POWER = 2;
    public static final int TPMS_NODATA = 1;
    public static final int TPMS_NOERROR = 0;
    public static final int TPMS_NO_SIGNAL = 3;
    private static final String TPMS_SAVE_FILE = "/mnt/sdcard/TsStorage/TPMS2.bin";
    private static final String TPMS_SAVE_PATH = "/mnt/sdcard/TsStorage";
    private static StTpms mStTpms = null;
    static int nDelay = 100;
    static int nRet = 0;
    static int nflag = 1;
    public static final int study_ed = 0;
    public static final int study_ing = 2;
    public static final int study_start = 1;
    public static final int tpms_left_back = 2;
    public static final int tpms_left_front = 0;
    public static final int tpms_right_back = 3;
    public static final int tpms_right_front = 1;
    byte[] Buffer = new byte[256];
    byte[] Cmd = new byte[8];
    public TPMSDisp DispOld = new TPMSDisp();
    int ReadNum;
    TsBuf TpmsBuf = new TsBuf(256);
    public TPMSDisp TpmsDisp = new TPMSDisp();
    int nDelayNum = 0;
    int nDelayTime = 0;
    int nError = 0;
    int nNawStudy = 255;
    int nOldMode = 255;
    int nPlayNum = 0;
    int nStep = 0;
    int[] nTempData = new int[8];
    public TPMS_SAVE tpmsSave = new TPMS_SAVE();

    public class TPMSInfo {
        public int nID;
        public int nInvalid;
        public int nState;
        public int nTemp;
        public int nWarnP;
        public int nWarnS;
        public int nWarnV;
        public double npa;

        public TPMSInfo() {
        }
    }

    public class TPMSDisp {
        public TPMSInfo[] info = new TPMSInfo[8];
        public int ntpmsNum;

        TPMSDisp() {
            for (int i = 0; i < 8; i++) {
                this.info[i] = new TPMSInfo();
            }
        }
    }

    public class TPMS_SAVE {
        public int nOnOffFlag;
        public int nPskDW;
        public int nPskHigh;
        public int nPskLow;
        public int nTempDW;
        public int nTempHigh;
        public int nWarnTime;
        public int nWriteFlag;

        public TPMS_SAVE() {
        }
    }

    public static StTpms GetInstance() {
        if (mStTpms == null) {
            mStTpms = new StTpms();
        }
        return mStTpms;
    }

    /* access modifiers changed from: package-private */
    public void CopyDataToSave() {
        this.tpmsSave.nWriteFlag = this.nTempData[0];
        this.tpmsSave.nOnOffFlag = this.nTempData[1];
        this.tpmsSave.nTempDW = this.nTempData[2];
        this.tpmsSave.nTempHigh = this.nTempData[3];
        this.tpmsSave.nPskDW = this.nTempData[4];
        this.tpmsSave.nPskHigh = this.nTempData[5];
        this.tpmsSave.nPskLow = this.nTempData[6];
        this.tpmsSave.nWarnTime = this.nTempData[7];
    }

    /* access modifiers changed from: package-private */
    public void CopySaveToData() {
        this.nTempData[0] = this.tpmsSave.nWriteFlag;
        this.nTempData[1] = this.tpmsSave.nOnOffFlag;
        this.nTempData[2] = this.tpmsSave.nTempDW;
        this.nTempData[3] = this.tpmsSave.nTempHigh;
        this.nTempData[4] = this.tpmsSave.nPskDW;
        this.nTempData[5] = this.tpmsSave.nPskHigh;
        this.nTempData[6] = this.tpmsSave.nPskLow;
        this.nTempData[7] = this.tpmsSave.nWarnTime;
    }

    /* access modifiers changed from: package-private */
    public void ResetData() {
        this.tpmsSave.nWriteFlag = 1;
        this.tpmsSave.nTempDW = 0;
        this.tpmsSave.nTempHigh = 60;
        this.tpmsSave.nPskDW = 0;
        this.tpmsSave.nPskHigh = 300;
        this.tpmsSave.nPskLow = Can.CAN_CHANA_CS75_WC;
        this.tpmsSave.nOnOffFlag = 0;
        this.tpmsSave.nWarnTime = 0;
    }

    /* access modifiers changed from: package-private */
    public void ReadSaveData() {
        if (TsFile.fileIsExists(TPMS_SAVE_FILE)) {
            TsFile.reader(TPMS_SAVE_FILE, this.nTempData);
            CopyDataToSave();
            Log.i(TAG, "ReadSaveData ok");
        } else {
            TsFile.NewDir(TPMS_SAVE_PATH);
            ResetData();
            SaveData();
            Log.i(TAG, "first create the file");
        }
        if (this.tpmsSave.nWriteFlag != 1) {
            ResetData();
        }
    }

    /* access modifiers changed from: package-private */
    public void SaveData() {
        CopySaveToData();
        TsFile.writer(TPMS_SAVE_FILE, this.nTempData);
    }

    /* access modifiers changed from: package-private */
    public void InintData() {
        this.nStep = 0;
        this.nDelayTime = 0;
        this.nError = 0;
        this.nNawStudy = 255;
        this.nDelayNum = 0;
        this.nPlayNum = 0;
    }

    /* access modifiers changed from: package-private */
    public int InintCom() {
        if (Iop.UartOpen(19200) == 0) {
            Log.i(TAG, "JtxTpms*]JtxTpms.stTpmsCom error ");
            return 0;
        }
        Log.i(TAG, "JtxTpms*]JtxTpms.stTpmsCom OK ");
        return 1;
    }

    /* access modifiers changed from: package-private */
    public int ReadCom() {
        this.ReadNum = Iop.UartRead(this.Buffer, 256);
        if (this.ReadNum > 0) {
            nRet = 1;
            Log.i(TAG, "JtxTpms ReadDtaNum = " + this.ReadNum);
            for (int i = 0; i < this.ReadNum; i++) {
                this.TpmsBuf.Push(this.Buffer[i]);
                Log.i(TAG, "ReadDta " + i + "==" + this.Buffer[i]);
            }
        }
        return nRet;
    }

    /* access modifiers changed from: package-private */
    public void DealCmd6() {
        Log.i(TAG, "DealCmd6 ok  Cmd[3 = " + this.Cmd[3] + "Cmd[4]=" + this.Cmd[4]);
        switch (this.Cmd[3]) {
            case 16:
                switch (this.Cmd[4]) {
                    case 0:
                        this.TpmsDisp.info[0].nState = 2;
                        return;
                    case 1:
                        this.TpmsDisp.info[1].nState = 2;
                        return;
                    case 16:
                        this.TpmsDisp.info[2].nState = 2;
                        return;
                    case 17:
                        this.TpmsDisp.info[3].nState = 2;
                        return;
                    default:
                        return;
                }
            case 24:
                switch (this.Cmd[4]) {
                    case 0:
                        this.TpmsDisp.info[0].nState = 0;
                        return;
                    case 1:
                        this.TpmsDisp.info[1].nState = 0;
                        return;
                    case 16:
                        this.TpmsDisp.info[2].nState = 0;
                        return;
                    case 17:
                        this.TpmsDisp.info[3].nState = 0;
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void DealCmd7() {
        Log.i(TAG, "DealCmd7 ok  Cmd[3 = " + this.Cmd[3] + "Cmd[4]=" + this.Cmd[4] + "Cmd[5]=" + this.Cmd[5]);
        switch (this.Cmd[3]) {
            case 1:
                this.TpmsDisp.info[0].nID = this.Cmd[4];
                return;
            case 2:
                this.TpmsDisp.info[1].nID = this.Cmd[4];
                return;
            case 3:
                this.TpmsDisp.info[2].nID = this.Cmd[4];
                return;
            case 4:
                this.TpmsDisp.info[3].nID = this.Cmd[4];
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void DealCmd8() {
        int i;
        int i2;
        int i3 = 0;
        Log.i(TAG, "DealCmd8 ok  Cmd[3 = " + this.Cmd[3] + "Cmd[4]=" + this.Cmd[4] + "Cmd[5]=" + this.Cmd[5] + "Cmd[6]=" + this.Cmd[6]);
        byte ubIndex = -1;
        switch (this.Cmd[3]) {
            case 0:
                ubIndex = 0;
                break;
            case 1:
                ubIndex = 1;
                break;
            case 16:
                ubIndex = 2;
                break;
            case 17:
                ubIndex = 3;
                break;
        }
        Log.i(TAG, "ubIndex=" + ubIndex);
        int nDataCMD4 = this.Cmd[4] & 255;
        int nDataCMD5 = this.Cmd[5] & 255;
        if (ubIndex != -1) {
            this.TpmsDisp.info[ubIndex].npa = ((double) nDataCMD4) * 3.44d;
            this.TpmsDisp.info[ubIndex].nTemp = nDataCMD5 - 50;
            TPMSInfo tPMSInfo = this.TpmsDisp.info[ubIndex];
            if ((this.Cmd[6] & 8) > 0) {
                i = 1;
            } else {
                i = 0;
            }
            tPMSInfo.nWarnP = i;
            TPMSInfo tPMSInfo2 = this.TpmsDisp.info[ubIndex];
            if ((this.Cmd[6] & WrcManager.WrcCallback.KEY_CENTER) > 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            tPMSInfo2.nWarnV = i2;
            TPMSInfo tPMSInfo3 = this.TpmsDisp.info[ubIndex];
            if ((this.Cmd[6] & 32) > 0) {
                i3 = 1;
            }
            tPMSInfo3.nWarnS = i3;
            this.TpmsDisp.info[ubIndex].nInvalid = 1;
            Log.i(TAG, "TpmsDisp.info[ubIndex].npa=" + this.TpmsDisp.info[ubIndex].npa);
            Log.i(TAG, "TpmsDisp.info[ubIndex].nTemp=" + this.TpmsDisp.info[ubIndex].nTemp);
            Log.i(TAG, "TpmsDisp.info[ubIndex].nWarnP=" + this.TpmsDisp.info[ubIndex].nWarnP);
            Log.i(TAG, "TpmsDisp.info[ubIndex].nWarnV=" + this.TpmsDisp.info[ubIndex].nWarnV);
            Log.i(TAG, "TpmsDisp.info[ubIndex].nWarnS=" + this.TpmsDisp.info[ubIndex].nWarnS);
            Log.i(TAG, "TpmsDisp.info[ubIndex].nInvalid=" + this.TpmsDisp.info[ubIndex].nInvalid);
        }
    }

    /* access modifiers changed from: package-private */
    public void ClearCmd() {
        for (int i = 0; i < 8; i++) {
            this.Cmd[i] = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public int DealData() {
        byte[] ubNum = new byte[1];
        if (this.TpmsBuf.GetLen() >= 6) {
            for (int i = 0; i < this.TpmsBuf.GetLen(); i++) {
                ClearCmd();
                this.TpmsBuf.GetData(0, this.Cmd, 0);
                this.TpmsBuf.GetData(1, this.Cmd, 1);
                this.TpmsBuf.GetData(2, this.Cmd, 2);
                if (this.Cmd[0] != 85 || this.Cmd[1] != -86 || this.Cmd[2] <= 0 || this.Cmd[2] >= 9) {
                    this.TpmsBuf.Pop(ubNum);
                } else {
                    this.TpmsBuf.GetData(2, this.Cmd, 2);
                    byte ubLen = this.Cmd[2];
                    for (int k = 0; k < ubLen; k++) {
                        this.TpmsBuf.GetData(k, this.Cmd, k);
                    }
                    int ubSum = this.Cmd[0] & 255;
                    for (int j = 1; j < ubLen - 1; j++) {
                        ubSum ^= this.Cmd[j] & 255;
                    }
                    int ubSum2 = ubSum % 255;
                    Log.i(TAG, "Check...ubSum==" + ubSum2 + "   Cmd[ubLen-1]==" + this.Cmd[ubLen - 1]);
                    if (((byte) ubSum2) == this.Cmd[ubLen - 1]) {
                        Log.i(TAG, "TPMS Check ok");
                        switch (this.Cmd[2]) {
                            case 6:
                                DealCmd6();
                                break;
                            case 7:
                                DealCmd7();
                                break;
                            case 8:
                                DealCmd8();
                                break;
                        }
                        for (int n = 0; n < this.Cmd[2]; n++) {
                            this.TpmsBuf.Pop(ubNum);
                        }
                    } else {
                        this.TpmsBuf.Pop(ubNum);
                        Log.i(TAG, "TPMS Check Faile");
                    }
                }
            }
        }
        return 1;
    }

    public void Inint(int nParat) {
        nflag = 1;
        InintData();
        ReadSaveData();
    }

    public int MainFunc(int nParat) {
        if (this.nOldMode != nParat) {
            if (nParat == 2) {
                this.nStep = Can.CAN_FLAT_RZC;
            } else if (this.nOldMode == 2 && nParat == 0) {
                Inint(0);
            }
            this.nOldMode = nParat;
        }
        switch (this.nStep) {
            case 0:
                if (InintCom() != 1) {
                    this.nStep = 255;
                    break;
                } else {
                    this.nStep++;
                    break;
                }
            case 1:
                if (ReadCom() == 1) {
                    this.nStep++;
                    break;
                }
                break;
            case 2:
                DealData();
                this.nStep = 1;
                if (nDelay > 0) {
                    nDelay--;
                }
                if (nDelay == 0) {
                    if (this.tpmsSave.nOnOffFlag == 0) {
                        CheckError();
                    }
                    if (this.nDelayNum > 0) {
                        this.nDelayNum--;
                        if (this.nDelayNum == 0 && this.nNawStudy != 255) {
                            UnStudy(this.nNawStudy);
                            break;
                        }
                    }
                }
                break;
            case Can.CAN_FLAT_RZC:
                SaveData();
                Iop.UartClose();
                nflag = 0;
                this.nStep = 255;
                break;
            case 255:
                return 255;
        }
        return 1;
    }

    public int CheckError() {
        for (int i = 0; i < 4; i++) {
            if (this.TpmsDisp.info[i].nInvalid == 1) {
                if (this.DispOld.info[i].nWarnV != this.TpmsDisp.info[i].nWarnV) {
                    this.DispOld.info[i].nWarnV = this.TpmsDisp.info[i].nWarnV;
                    return 1;
                } else if (this.DispOld.info[i].nWarnS != this.TpmsDisp.info[i].nWarnS) {
                    this.DispOld.info[i].nWarnS = this.TpmsDisp.info[i].nWarnS;
                    return 2;
                } else if (this.DispOld.info[i].nWarnP != this.TpmsDisp.info[i].nWarnP) {
                    this.DispOld.info[i].nWarnP = this.TpmsDisp.info[i].nWarnP;
                    return 3;
                } else if (this.TpmsDisp.info[i].npa < ((double) this.tpmsSave.nPskLow)) {
                    return 4;
                } else {
                    if (this.TpmsDisp.info[i].npa > ((double) this.tpmsSave.nPskHigh)) {
                        return 5;
                    }
                    if (this.TpmsDisp.info[i].nTemp > this.tpmsSave.nTempHigh) {
                        return 6;
                    }
                }
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void CheckID() {
        Log.i(TAG, "*******************CheckID************");
        Iop.UartSend(new byte[]{85, -86, 6, 2, 0, -8}, 6);
    }

    public void UnStudy(int nType) {
        if (nType != 255) {
            Log.i(TAG, "*******************UnStudy************");
            byte[] ubBuf = new byte[6];
            ubBuf[0] = 85;
            ubBuf[1] = -86;
            ubBuf[2] = 6;
            ubBuf[3] = 6;
            switch (nType) {
                case 0:
                    ubBuf[4] = 0;
                    ubBuf[5] = -1;
                    break;
                case 1:
                    ubBuf[4] = 1;
                    ubBuf[5] = -2;
                    break;
                case 2:
                    ubBuf[4] = WrcManager.WrcCallback.KEY_CENTER;
                    ubBuf[5] = -17;
                    break;
                case 3:
                    ubBuf[4] = 17;
                    ubBuf[5] = -18;
                    break;
            }
            Iop.UartSend(ubBuf, 6);
            this.nNawStudy = 255;
            this.TpmsDisp.info[nType].nState = 0;
        }
    }

    public void Study(int nType) {
        Log.i(TAG, "*******************Study************");
        if (this.nNawStudy != 255) {
            UnStudy(this.nNawStudy);
        }
        this.nNawStudy = nType;
        this.nDelayNum = TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT;
        byte[] ubBuf = new byte[6];
        ubBuf[0] = 85;
        ubBuf[1] = -86;
        ubBuf[2] = 6;
        ubBuf[3] = 1;
        switch (nType) {
            case 0:
                ubBuf[4] = 0;
                ubBuf[5] = -8;
                break;
            case 1:
                ubBuf[4] = 1;
                ubBuf[5] = -7;
                break;
            case 2:
                ubBuf[4] = WrcManager.WrcCallback.KEY_CENTER;
                ubBuf[5] = -24;
                break;
            case 3:
                ubBuf[4] = 17;
                ubBuf[5] = -23;
                break;
        }
        Iop.UartSend(ubBuf, 6);
        this.TpmsDisp.info[nType].nState = 1;
    }
}
