package com.ts.MainUI;

import android.util.Log;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;

public class CstTv {
    private static final int AddPllWr = 99;
    private static final int CHECK_DELAY = 20;
    private static final int FRE_ADD = 778;
    private static final int FRE_JIAN = 100;
    private static final int FRE_MAX = 17265;
    private static final int FRE_MIN = 965;
    private static final int FRE_STEP = 5;
    private static final int IF_address = 67;
    private static final int INVALID_CHANNEL_NUM = -1;
    private static final int LOCK_MIN = 4;
    private static final int MUTE_DELAY_TIME = 30;
    private static final int NTSC_VHFH_MAX = 8545;
    private static final int NTSC_VHFL_MAX = 2785;
    private static final int PAL_BG_VHFH_MAX = 8465;
    private static final int PAL_BG_VHFL_MAX = 2805;
    private static final int PAL_DK_VHFH_MAX = 8545;
    private static final int PAL_DK_VHFL_MAX = 2725;
    private static final int PAL_I_VHFH_MAX = 8465;
    private static final int PAL_I_VHFL_MAX = 2805;
    private static final int PLLCON1 = 136;
    private static final int PLLCON2_UHF = 8;
    private static final int PLLCON2_VHF_H = 2;
    private static final int PLLCON2_VHF_L = 1;
    private static final int SECAM_BG_VHFH_MAX = 8465;
    private static final int SECAM_BG_VHFL_MAX = 2805;
    private static final int SECAM_DK_VHFH_MAX = 8545;
    private static final int SECAM_DK_VHFL_MAX = 2725;
    private static final int SECAM_L_VHFH_MAX = 8545;
    private static final int SECAM_L_VHFL_MAX = 2725;
    private static final int Sub_Add_B_Data = 0;
    private static final int Sub_Add_C_Data = 1;
    private static final int Sub_Add_E_Data = 2;
    static final String TAG = "CstTv";
    private static final int TV_AUTO_SEARCH = 4;
    private static final int TV_DELAY_CHECK = 5;
    private static final int TV_FORMAT_MAX = 8;
    private static final int TV_ININT = 0;
    private static final int TV_LOCK_OK = 6;
    private static final int TV_MAX_NUM = 100;
    private static final int TV_MODE_CHG = 1;
    private static final int TV_NORMAL = 7;
    private static final int TV_NTSC_MN = 7;
    private static final int TV_PAL_BG = 2;
    private static final int TV_PAL_DK = 1;
    private static final int TV_PAL_I = 0;
    private static final int TV_PAL_M = 3;
    private static final int TV_PAL_N = 4;
    private static final int TV_POWER_E = 255;
    private static final int TV_POWER_S = 254;
    private static final String TV_SAVE_FILE = "/mnt/sdcard/TsStorage/atv.bin";
    private static final String TV_SAVE_PATH = "/mnt/sdcard/TsStorage";
    private static final int TV_SAVE_SIZE = 103;
    private static final int TV_SECAM_BG = 6;
    private static final int TV_SECAM_DK = 5;
    private static final int TV_STEP_MODE = 3;
    private static final int TV_WATCH_MODE = 2;
    private static CstTv mCstTv = null;
    static int noldMode = -1;
    static int num = 0;
    int FreP;
    int NawFp = 7;
    int TvState;
    boolean bAutoSearch = false;
    int delaynum;
    protected CstTvCallBack mCstTvCallBack = null;
    Evc mEvc = Evc.GetInstance();
    int m_B;
    int m_C;
    int m_E;
    public int nCurNum;
    int nMuteDelay;
    double[] nResetChanel = {48.25d, 57.75d, 65.75d, 77.25d, 85.25d, 112.25d, 120.25d, 128.25d, 136.25d, 144.25d, 152.25d, 160.25d, 168.25d, 176.25d, 184.25d, 192.25d, 200.25d, 208.25d, 216.25d, 224.25d, 232.25d, 240.25d, 248.25d, 256.25d, 264.25d, 272.25d, 280.25d, 288.25d, 296.25d, 304.25d, 312.25d, 320.25d, 328.25d, 336.25d, 344.25d, 352.25d, 360.25d, 368.25d, 376.25d, 384.25d, 392.25d, 400.25d, 408.25d, 416.25d, 424.25d, 432.25d, 440.25d, 448.25d, 456.25d, 464.25d, 471.25d, 479.25d, 487.25d, 495.25d, 503.25d, 511.25d, 519.25d, 527.25d, 535.25d, 543.25d, 551.25d, 559.25d, 607.25d, 615.25d, 623.25d, 631.25d, 639.25d, 647.25d, 655.25d, 663.25d, 671.25d, 679.25d, 687.25d, 695.25d, 703.25d, 711.25d, 719.25d, 727.25d, 735.25d, 743.25d, 751.25d, 759.25d, 767.25d, 775.25d, 783.25d, 791.25d, 799.25d, 807.25d, 815.25d, 823.25d, 831.25d, 839.25d, 847.25d, 855.25d, 856.25d, 858.25d, 860.25d, 861.25d, 862.25d, 863.25d};
    int[] nTempData = new int[103];
    public TV_SAVE tvSave = new TV_SAVE();

    public class TV_SAVE {
        public int nMode;
        public int[] nSaveCh = new int[100];
        public int nSaveNum;
        public int nWatchNum;

        public TV_SAVE() {
        }
    }

    public void Inint() {
        noldMode = -1;
        this.bAutoSearch = false;
        this.TvState = 0;
    }

    public static CstTv GetInstance() {
        if (mCstTv == null) {
            mCstTv = new CstTv();
        }
        return mCstTv;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x011f, code lost:
        if (r9 != 0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0121, code lost:
        Inint();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        return 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return 255;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int Tv_Main(int r9, boolean r10) {
        /*
            r8 = this;
            r7 = 5
            r6 = 3
            r5 = 1
            r1 = 0
            r0 = 255(0xff, float:3.57E-43)
            int r2 = noldMode
            int r3 = r8.TvState
            if (r2 == r3) goto L_0x0028
            int r2 = r8.TvState
            noldMode = r2
            java.lang.String r2 = "CstTv"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Tv_Main TvState =  "
            r3.<init>(r4)
            int r4 = r8.TvState
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.i(r2, r3)
        L_0x0028:
            r2 = 2
            if (r9 != r2) goto L_0x0036
            r2 = 254(0xfe, float:3.56E-43)
            r8.TvState = r2
        L_0x002f:
            int r2 = r8.TvState
            switch(r2) {
                case 0: goto L_0x0050;
                case 1: goto L_0x007a;
                case 2: goto L_0x0089;
                case 3: goto L_0x0090;
                case 4: goto L_0x0112;
                case 5: goto L_0x0099;
                case 6: goto L_0x00b4;
                case 254: goto L_0x011a;
                case 255: goto L_0x011f;
                default: goto L_0x0034;
            }
        L_0x0034:
            r0 = r1
        L_0x0035:
            return r0
        L_0x0036:
            r8.ClearTvMute()
            int r2 = com.yyw.ts70xhw.Iop.GetWorkMode()
            r3 = 7
            if (r2 == r3) goto L_0x0049
            int r2 = r8.TvState
            if (r2 == 0) goto L_0x0035
            r8.TvState = r5
            r8.bAutoSearch = r1
            goto L_0x0035
        L_0x0049:
            if (r9 != r6) goto L_0x002f
            r8.TvState = r5
            r8.bAutoSearch = r1
            goto L_0x0035
        L_0x0050:
            boolean r0 = r8.ReadSaveData()
            if (r0 == 0) goto L_0x0034
            if (r9 != r5) goto L_0x0061
            r8.ResetData()
            r8.ResetMode()
            r8.SaveData()
        L_0x0061:
            java.lang.String r0 = "CstTv"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Tv_Main param =  "
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r9)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r0, r2)
            r8.TvState = r5
            goto L_0x0034
        L_0x007a:
            r8.SetTvMute()
            r8.InintMode()
            boolean r0 = r8.WriteBCE()
            if (r0 == 0) goto L_0x0034
            r8.TvState = r6
            goto L_0x0034
        L_0x0089:
            int r0 = num
            int r0 = r0 + 1
            num = r0
            goto L_0x0034
        L_0x0090:
            r8.SetTvMute()
            r8.WriteChanel()
            r8.TvState = r7
            goto L_0x0034
        L_0x0099:
            boolean r0 = r8.IsPlayOK()
            if (r0 == 0) goto L_0x00a5
            r0 = 6
            r8.TvState = r0
            r8.delaynum = r1
            goto L_0x0034
        L_0x00a5:
            boolean r0 = r8.DealNext()
            if (r0 == 0) goto L_0x0034
            r8.SetTvMute()
            r8.WriteChanel()
            r8.TvState = r7
            goto L_0x0034
        L_0x00b4:
            int r0 = r8.delaynum
            r2 = 20
            if (r0 >= r2) goto L_0x00c2
            int r0 = r8.delaynum
            int r0 = r0 + 1
            r8.delaynum = r0
            goto L_0x0034
        L_0x00c2:
            boolean r0 = r8.IsPlayOK()
            if (r0 == 0) goto L_0x00f9
            boolean r0 = r8.IsLockOK()
            if (r0 == 0) goto L_0x00f9
            java.lang.String r0 = "CstTv"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "IsLockOK true=="
            r2.<init>(r3)
            int r3 = r8.TvState
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r0, r2)
            r8.DelaCur()
        L_0x00e9:
            boolean r0 = r8.DealNext()
            if (r0 == 0) goto L_0x0034
            r8.SetTvMute()
            r8.WriteChanel()
            r8.TvState = r7
            goto L_0x0034
        L_0x00f9:
            java.lang.String r0 = "CstTv"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "IsLockOK false=="
            r2.<init>(r3)
            int r3 = r8.TvState
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r0, r2)
            goto L_0x00e9
        L_0x0112:
            r0 = 965(0x3c5, float:1.352E-42)
            r8.nCurNum = r0
            r8.TvState = r6
            goto L_0x0034
        L_0x011a:
            r8.SaveData()
            r8.TvState = r0
        L_0x011f:
            if (r9 != 0) goto L_0x0035
            r8.Inint()
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.MainUI.CstTv.Tv_Main(int, boolean):int");
    }

    /* access modifiers changed from: package-private */
    public void InintMode() {
        if (this.tvSave.nMode >= 8) {
            this.tvSave.nMode = 0;
        }
        InintBEC();
    }

    /* access modifiers changed from: package-private */
    public void InintBEC() {
        Log.i(TAG, "InintBEC nTvMode== =  " + this.tvSave.nMode);
        switch (this.tvSave.nMode) {
            case 0:
                this.m_B = 214;
                this.m_C = 112;
                this.m_E = 10;
                return;
            case 1:
                this.m_B = 214;
                this.m_C = 112;
                this.m_E = 11;
                return;
            case 2:
                this.m_B = 214;
                this.m_C = 112;
                this.m_E = 9;
                return;
            case 3:
            case 4:
            case 7:
                this.m_B = 22;
                this.m_C = 48;
                this.m_E = 8;
                return;
            case 5:
                this.m_B = 214;
                this.m_C = 112;
                this.m_E = 11;
                return;
            case 6:
                this.m_B = 214;
                this.m_C = 112;
                this.m_E = 9;
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean WriteBCE() {
        if (Iop.IIcSendOneByte((byte) 67, (byte) 0, (byte) this.m_B) == -1) {
            Log.i(TAG, "I2cWrite B error");
            return false;
        } else if (Iop.IIcSendOneByte((byte) 67, (byte) 1, (byte) this.m_C) == -1) {
            Log.i(TAG, "I2cWrite C error");
            return false;
        } else if (Iop.IIcSendOneByte((byte) 67, (byte) 2, (byte) this.m_E) != -1) {
            return true;
        } else {
            Log.i(TAG, "I2cWrite E error");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void SetTvMute() {
        if (this.nMuteDelay == 0) {
            this.mEvc.evol_popmute_set(7);
            this.nMuteDelay = 30;
            return;
        }
        this.nMuteDelay = 30;
    }

    /* access modifiers changed from: package-private */
    public void ClearTvMute() {
        if (this.nMuteDelay > 0) {
            this.nMuteDelay--;
            if (this.nMuteDelay == 0) {
                this.mEvc.evol_popmute_clr(7);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int GetCurFre() {
        if (this.nCurNum > FRE_MAX || this.nCurNum < FRE_MIN) {
            Log.i(TAG, "GetCurFre()  nCurNum" + this.nCurNum);
            this.nCurNum = FRE_MIN;
        }
        return this.nCurNum + 778;
    }

    /* access modifiers changed from: package-private */
    public int GetVhl() {
        int vhl_lmax = 0;
        int vhl_hmax = 0;
        switch (this.tvSave.nMode) {
            case 0:
                vhl_lmax = 2805;
                vhl_hmax = 8465;
                break;
            case 1:
                vhl_lmax = 2725;
                vhl_hmax = 8545;
                break;
            case 2:
                vhl_lmax = 2805;
                vhl_hmax = 8465;
                break;
            case 3:
            case 4:
            case 7:
                vhl_lmax = NTSC_VHFL_MAX;
                vhl_hmax = 8545;
                break;
            case 5:
                vhl_lmax = 2725;
                vhl_hmax = 8545;
                break;
            case 6:
                vhl_lmax = 2805;
                vhl_hmax = 8465;
                break;
        }
        if (this.nCurNum <= vhl_lmax) {
            return 1;
        }
        if (this.nCurNum <= vhl_hmax) {
            return 2;
        }
        return 8;
    }

    /* access modifiers changed from: package-private */
    public void WriteFre(int nFre) {
    }

    /* access modifiers changed from: package-private */
    public void WriteChanel() {
        byte[] ubSendBuf = new byte[5];
        ubSendBuf[0] = (byte) (GetCurFre() / 256);
        ubSendBuf[1] = (byte) (GetCurFre() & 255);
        ubSendBuf[2] = -120;
        ubSendBuf[3] = (byte) GetVhl();
        for (int i = 0; i < 3 && Iop.IIcSend((byte) 99, ubSendBuf, 4) == -1; i++) {
            Log.i(TAG, "WriteChanel error" + i);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean IsInintOK() {
        if (this.TvState > 1) {
            return true;
        }
        return false;
    }

    public boolean isAutoSearch() {
        return this.bAutoSearch;
    }

    public void StopAutoSearch() {
        if (IsInintOK() && this.bAutoSearch) {
            if (this.tvSave.nSaveNum != -1) {
                this.nCurNum = this.tvSave.nSaveCh[0];
                this.tvSave.nWatchNum = 0;
            } else {
                this.nCurNum = FRE_MIN;
                this.tvSave.nWatchNum = 0;
                this.tvSave.nSaveNum = 0;
            }
            WriteChanel();
            this.TvState = 2;
            this.bAutoSearch = false;
        }
    }

    public void AutoSearch() {
        if (!this.bAutoSearch && IsInintOK()) {
            ResetData();
            this.tvSave.nSaveNum = -1;
            this.TvState = 4;
            this.bAutoSearch = true;
        } else if (this.bAutoSearch) {
            StopAutoSearch();
        }
    }

    public void TvStep(byte ubStep) {
        if (ubStep > 0) {
            if (this.nCurNum < FRE_MAX) {
                this.nCurNum += 5;
            }
        } else if (this.nCurNum > FRE_MIN) {
            this.nCurNum -= 5;
        }
        this.TvState = 3;
    }

    /* access modifiers changed from: package-private */
    public void CopyDataToBuf() {
        this.nTempData[0] = this.tvSave.nWatchNum;
        this.nTempData[1] = this.tvSave.nSaveNum;
        this.nTempData[2] = this.tvSave.nMode;
        for (int i = 0; i < 100; i++) {
            this.nTempData[i + 3] = this.tvSave.nSaveCh[i];
        }
    }

    /* access modifiers changed from: package-private */
    public void CopyDataToSave() {
        this.tvSave.nWatchNum = this.nTempData[0];
        this.tvSave.nSaveNum = this.nTempData[1];
        this.tvSave.nMode = this.nTempData[2];
        for (int i = 0; i < 100; i++) {
            this.tvSave.nSaveCh[i] = this.nTempData[i + 3];
        }
    }

    /* access modifiers changed from: package-private */
    public boolean ReadSaveData() {
        if (TsFile.fileIsExists(TV_SAVE_FILE)) {
            TsFile.reader(TV_SAVE_FILE, this.nTempData);
            CopyDataToSave();
            Log.i(TAG, "ReadSaveData ok");
            if (this.tvSave.nSaveNum == -1 || this.tvSave.nSaveNum == 99) {
                ResetData();
                ResetMode();
                SaveData();
                Log.i(TAG, "tv file open error");
            } else {
                if (this.tvSave.nWatchNum > this.tvSave.nSaveNum) {
                    this.tvSave.nWatchNum = 0;
                }
                this.nCurNum = this.tvSave.nSaveCh[this.tvSave.nWatchNum];
                Log.i(TAG, "Read savedata   ==" + this.nCurNum);
            }
        } else {
            TsFile.NewDir(TV_SAVE_PATH);
            ResetData();
            ResetMode();
            SaveData();
            Log.i(TAG, "first create the file");
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void ResetData() {
        this.tvSave.nWatchNum = 0;
        this.tvSave.nSaveNum = 99;
        this.nCurNum = FRE_MIN;
        for (int i = 0; i < 100; i++) {
            this.tvSave.nSaveCh[i] = (int) (this.nResetChanel[i] * 20.0d);
        }
    }

    /* access modifiers changed from: package-private */
    public void ResetMode() {
        if (FtSet.GetTvFormat() >= 8) {
            this.tvSave.nMode = 0;
            return;
        }
        this.tvSave.nMode = FtSet.GetTvFormat();
    }

    /* access modifiers changed from: package-private */
    public void SaveData() {
        CopyDataToBuf();
        TsFile.writer(TV_SAVE_FILE, this.nTempData);
    }

    public void SetCstTvCallBack(CstTvCallBack cb) {
        this.mCstTvCallBack = cb;
    }

    /* access modifiers changed from: package-private */
    public boolean IsLockOK() {
        if (this.mCstTvCallBack != null) {
            return this.mCstTvCallBack.bSingnalOK();
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean IsPlayOK() {
        byte[] temp = new byte[1];
        if (Iop.IIcReadnoadr((byte) 67, temp, 1) == -1) {
            Log.i(TAG, "IIcReadOneByte  ==error");
            return false;
        }
        byte ubLock = (byte) ((temp[0] >> 1) & 15);
        if (ubLock < 8) {
            this.FreP = ubLock;
        } else {
            this.FreP = 15 - ubLock;
        }
        if (ubLock > 4 && 15 - ubLock > 4) {
            return false;
        }
        Log.i(TAG, "IsPlayOK  ==" + this.FreP);
        Log.i(TAG, "nCurNum  ==" + this.nCurNum);
        Log.i(TAG, "ubLock  ==" + ubLock);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void DelaCur() {
        if (!this.bAutoSearch) {
            this.TvState = 2;
        } else if (this.tvSave.nSaveNum == -1 || this.tvSave.nSaveNum == 99) {
            this.tvSave.nSaveNum = 0;
            this.tvSave.nSaveCh[this.tvSave.nSaveNum] = this.nCurNum;
            this.NawFp = this.FreP;
        } else if (Math.abs(this.nCurNum - this.tvSave.nSaveCh[this.tvSave.nSaveNum]) >= 100) {
            this.tvSave.nSaveNum++;
            Log.i(TAG, "DelaCur  true  new=" + this.tvSave.nSaveNum + "nCurNum=" + this.nCurNum);
            this.tvSave.nSaveCh[this.tvSave.nSaveNum] = this.nCurNum;
            this.NawFp = this.FreP;
        } else if (this.FreP < this.NawFp) {
            this.tvSave.nSaveCh[this.tvSave.nSaveNum] = this.nCurNum;
            Log.i(TAG, "DelaCur  please  NawFp" + this.NawFp + "FreP=" + this.FreP);
            Log.i(TAG, "DelaCur   new=" + this.tvSave.nSaveNum + "nCurNum=" + this.nCurNum);
            this.NawFp = this.FreP;
        } else {
            Log.i(TAG, "DelaCur  false  NawFp" + this.NawFp + "FreP=" + this.FreP);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean DealNext() {
        if (!this.bAutoSearch) {
            this.TvState = 2;
            return false;
        } else if (this.nCurNum < FRE_MAX) {
            this.nCurNum += 5;
            this.TvState = 3;
            return true;
        } else {
            if (this.tvSave.nSaveNum != -1) {
                this.nCurNum = this.tvSave.nSaveCh[0];
                this.tvSave.nWatchNum = 0;
            } else {
                this.nCurNum = FRE_MIN;
                this.tvSave.nWatchNum = 0;
            }
            WriteChanel();
            this.TvState = 2;
            this.bAutoSearch = false;
            return false;
        }
    }

    public boolean PlayChg(byte ubNext) {
        if (!IsInintOK() || this.tvSave.nSaveNum == -1 || this.TvState != 2) {
            return false;
        }
        if (ubNext == 1) {
            if (this.tvSave.nWatchNum >= 100) {
                return false;
            }
            this.tvSave.nWatchNum++;
            this.nCurNum = this.tvSave.nSaveCh[this.tvSave.nWatchNum];
            this.TvState = 3;
            return true;
        } else if (this.tvSave.nWatchNum <= 0) {
            return false;
        } else {
            TV_SAVE tv_save = this.tvSave;
            tv_save.nWatchNum--;
            this.nCurNum = this.tvSave.nSaveCh[this.tvSave.nWatchNum];
            this.TvState = 3;
            return true;
        }
    }

    public void ChgMode(byte ubMode) {
        if (!this.bAutoSearch && IsInintOK()) {
            if (ubMode < 8) {
                this.tvSave.nMode = ubMode;
            }
            this.TvState = 1;
            Log.i(TAG, "ChgMode=" + ubMode);
        }
    }

    /* access modifiers changed from: package-private */
    public void ChgChannel() {
        if (!this.bAutoSearch && IsInintOK()) {
            this.tvSave.nMode++;
            if (this.tvSave.nMode >= 8) {
                this.tvSave.nMode = 0;
            }
            this.TvState = 1;
            Log.i(TAG, "ChgChannel=" + this.TvState);
        }
    }

    public boolean PlayFre(int nChg) {
        if (nChg <= 0 || this.bAutoSearch || this.tvSave.nSaveNum == -1) {
            return false;
        }
        if (nChg - 1 <= 100) {
            this.nCurNum = this.tvSave.nSaveCh[nChg - 1];
            this.tvSave.nWatchNum = nChg - 1;
        }
        this.TvState = 3;
        return true;
    }

    public int GetCurMode() {
        return this.tvSave.nMode;
    }
}
