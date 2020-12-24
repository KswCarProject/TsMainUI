package com.ts.can;

import android.os.SystemClock;
import android.util.Log;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.yyw.ts70xhw.FtSet;

public class CanRadarManager {
    public static final int Mode_CameraForeRadar = 4;
    public static final int Mode_CameraOnly = 2;
    public static final int Mode_CameraRearRadar = 3;
    public static final int Mode_Null = 0;
    public static final int Mode_RadarOnly = 1;
    public static final int WaitStatus_ForeDelayBegin = 1;
    public static final int WaitStatus_ForeDelayEnd = 2;
    public static final int WaitStatus_Null = 0;
    public static CanRadarManager mInstance;
    public ManageData mData = new ManageData();
    private CanDataInfo.FordForeRadarEx mFordFore = new CanDataInfo.FordForeRadarEx();
    private CanDataInfo.FordRearRadarEx mFordRear = new CanDataInfo.FordRearRadarEx();

    class ManageData {
        public long DelayTick;
        public CanDataInfo.CAN_RadarInfo ForeRadar;
        public int Mode;
        public long WaitStartTick;
        public int WaitStatus;
        public boolean fgHaveCamera;
        public boolean fgHaveForeRadar;
        public boolean fgHaveRadar;

        ManageData() {
        }
    }

    protected CanRadarManager() {
    }

    public static long GetTickCount() {
        return SystemClock.uptimeMillis();
    }

    public static CanRadarManager GetInstance() {
        if (mInstance == null) {
            mInstance = new CanRadarManager();
        }
        return mInstance;
    }

    public void Init() {
        this.mData.fgHaveCamera = i2b(FtSet.GetCamFix());
        this.mData.DelayTick = 2000;
        this.mData.fgHaveRadar = CanJni.IsHaveRadar();
        this.mData.fgHaveForeRadar = CanJni.IsHaveForeRadar();
        if (this.mData.fgHaveRadar) {
            if (!this.mData.fgHaveCamera) {
                this.mData.Mode = 1;
                if (FtSet.Getyw14() > 0) {
                    this.mData.Mode = 0;
                }
            } else if (this.mData.fgHaveForeRadar) {
                if (13 == CanJni.GetCanType() && CanJni.GetSubType() != 8) {
                    this.mData.Mode = 3;
                } else if (146 == CanJni.GetCanType()) {
                    this.mData.Mode = 3;
                } else {
                    this.mData.Mode = 4;
                }
                if (FtSet.Getyw14() > 0) {
                    this.mData.Mode = 3;
                }
            } else {
                this.mData.Mode = 3;
            }
        } else if (this.mData.fgHaveCamera) {
            this.mData.Mode = 2;
        } else {
            this.mData.Mode = 0;
        }
    }

    public void Main() {
        if (4 == this.mData.Mode) {
            if (IsRadarWin()) {
                this.mData.WaitStatus = 0;
            } else if (1 == this.mData.WaitStatus) {
                long curTick = GetTickCount();
                long lrRadarTick = 0;
                if (CanJni.IsHaveLrRadar()) {
                    lrRadarTick = (long) (Can.mRadarLeftInfo.dwTick + Can.mRadarRightInfo.dwTick);
                }
                if (curTick > ((long) Can.mRadarForeInfo.dwTick) + this.mData.DelayTick + 1000 + lrRadarTick) {
                    this.mData.WaitStatus = 0;
                } else if (curTick > this.mData.WaitStartTick + this.mData.DelayTick) {
                    this.mData.WaitStatus = 0;
                    GotoForeRadarWin();
                }
            }
        }
    }

    public void ShowRadar() {
        switch (this.mData.Mode) {
            case 1:
                GotoRadarWin();
                return;
            case 3:
                InvalidRadarWin();
                return;
            case 4:
                boolean isForeUpdate = i2b(Can.mRadarForeInfo.Update);
                boolean isLeftUpdate = i2b(Can.mRadarLeftInfo.Update);
                boolean isRightUpdate = i2b(Can.mRadarRightInfo.Update);
                if (!isForeUpdate && (!CanJni.IsHaveLrRadar() || (!isLeftUpdate && !isRightUpdate))) {
                    InvalidRadarWin();
                    return;
                } else if (IsRadarWin()) {
                    this.mData.WaitStatus = 0;
                    InvalidRadarWin();
                    return;
                } else {
                    switch (this.mData.WaitStatus) {
                        case 0:
                            this.mData.WaitStartTick = GetTickCount();
                            this.mData.WaitStatus = 1;
                            Log.d("", "WaitStatus_ForeDelayBegin");
                            return;
                        case 2:
                            GotoForeRadarWin();
                            this.mData.WaitStatus = 0;
                            return;
                        default:
                            return;
                    }
                }
            default:
                return;
        }
    }

    public boolean IsRadarWin() {
        if (CanRadarActivity.IsRadarWin() || i2b(CanFunc.IsCamMode()) || CanIF.mfgAvm > 0) {
            return true;
        }
        return false;
    }

    public void InvalidRadarWin() {
    }

    public void GotoRadarWin() {
        CanFunc.showCanActivity(CanRadarActivity.class);
    }

    public void GotoForeRadarWin() {
        switch (CanJni.GetCanType()) {
            case 13:
            case 146:
                if (IsFordForeAvalid(Can.mRadarForeInfo)) {
                    GotoRadarWin();
                    return;
                }
                return;
            default:
                if (IsForeAvalid(Can.mRadarForeInfo) || ((IsForeAvalid(Can.mRadarLeftInfo) && CanJni.IsHaveLrRadar()) || (IsForeAvalid(Can.mRadarRightInfo) && CanJni.IsHaveLrRadar()))) {
                    GotoRadarWin();
                    return;
                }
                return;
        }
    }

    public boolean IsForeAvalid(CanDataInfo.CAN_RadarInfo fore) {
        if (fore.nLeftDis == 0 && fore.nRightDis == 0 && fore.nMidLtDis == 0 && fore.nMidRtDis == 0) {
            return false;
        }
        return true;
    }

    public boolean IsFordForeAvalid(CanDataInfo.CAN_RadarInfo fore) {
        CanJni.FordGetForeRadarEx(this.mFordFore);
        CanJni.FordGetRearRadarEx(this.mFordRear);
        if (fore.nLeftDis == 0 && fore.nRightDis == 0 && fore.nMidLtDis == 0 && fore.nMidRtDis == 0 && this.mFordFore.LtAssist == 0 && this.mFordFore.RtAssist == 0 && this.mFordFore.LtMidAssist == 0 && this.mFordFore.RtMidAssist == 0 && this.mFordRear.LtAssist == 0 && this.mFordRear.RtAssist == 0 && this.mFordRear.LtMidAssist == 0 && this.mFordRear.RtMidAssist == 0) {
            return false;
        }
        return true;
    }

    protected static boolean i2b(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }
}
