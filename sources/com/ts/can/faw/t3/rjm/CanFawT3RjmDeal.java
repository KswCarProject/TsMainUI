package com.ts.can.faw.t3.rjm;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.yyw.ts70xhw.Mcu;

public class CanFawT3RjmDeal {
    public static final String TAG = "CanFawT3RjmDeal";
    public static CanDataInfo.T3Fl_CanData mT3FlCanDat_7e0 = new CanDataInfo.T3Fl_CanData();
    public static CanDataInfo.T3Fl_CanData mT3FlCanDat_7f1 = new CanDataInfo.T3Fl_CanData();
    public static CanDataInfo.T3Fl_CanData mT3FlCanDat_7f2 = new CanDataInfo.T3Fl_CanData();
    public static CanDataInfo.T3Fl_CanData mT3FlCanDat_7f3 = new CanDataInfo.T3Fl_CanData();
    public static CanDataInfo.T3Fl_CanData mT3FlCanDat_7f4 = new CanDataInfo.T3Fl_CanData();
    public static CanDataInfo.T3Fl_DevInfo mT3FlDevInfo = new CanDataInfo.T3Fl_DevInfo();
    public static CanDataInfo.T3Fl_CanSpeed mT3FlSpeed = new CanDataInfo.T3Fl_CanSpeed();
    public static CanDataInfo.T3Fl_Sta mT3FlSta = new CanDataInfo.T3Fl_Sta();
    public static int[] mT3FlTexlCmd = new int[128];
    public static CanDataInfo.T3Fl_TexlInfo mT3FlTexlData = new CanDataInfo.T3Fl_TexlInfo();
    public static CanDataInfo.T3Fl_TexlDisCur mT3FlTexlDisCur = new CanDataInfo.T3Fl_TexlDisCur();
    public static CanDataInfo.T3Fl_TexlDisOver mT3FlTexlDisOver = new CanDataInfo.T3Fl_TexlDisOver();
    public static CanDataInfo.T3Fl_TexlPjxx mT3FlTexlPjxx = new CanDataInfo.T3Fl_TexlPjxx();
    public static int[] mT3FlTexlUpt = new int[5];
    private static int nReadCnt = 0;

    public static void T3TxelCmdSend(int[] cmd, int Len) {
        byte[] mCmdBuf = new byte[80];
        byte chk = 0;
        mCmdBuf[0] = 46;
        mCmdBuf[1] = 48;
        mCmdBuf[2] = (byte) (Len & 255);
        for (byte i = 0; i < Len; i = (byte) (i + 1)) {
            mCmdBuf[i + 3] = (byte) (cmd[i] & 255);
        }
        for (byte i2 = 1; i2 < Len + 3; i2 = (byte) (i2 + 1)) {
            chk = (byte) (((byte) (mCmdBuf[i2] & 255)) + chk);
        }
        mCmdBuf[Len + 3] = (byte) (((byte) (chk ^ 255)) & 255);
        Mcu.SendCanMsg(mCmdBuf, Len + 6);
    }

    public static void UserAll() {
        nReadCnt++;
        if (nReadCnt > 10) {
            nReadCnt = 0;
            CanJni.T3FlGetDevInfo(mT3FlDevInfo);
            CanJni.T3FlGetCarSta(mT3FlSta);
            CanJni.T3FlGetCarCanData(0, mT3FlCanDat_7f1);
            CanJni.T3FlGetCarCanData(1, mT3FlCanDat_7f2);
            CanJni.T3FlGetCarCanData(2, mT3FlCanDat_7f3);
            CanJni.T3FlGetCarCanData(3, mT3FlCanDat_7f4);
            CanJni.T3FlGetCarCanData(4, mT3FlCanDat_7e0);
            if (CanJni.GetSubType() == 3) {
                CanJni.T3FlGetTexlInfo(mT3FlTexlData);
                CanJni.T3FlGetTexlDisCur(mT3FlTexlDisCur);
                if (mT3FlTexlDisCur.Update > 0) {
                    mT3FlTexlDisCur.Update = 0;
                    mT3FlTexlUpt[0] = 1;
                }
                CanJni.T3FlGetTexlDisOver(mT3FlTexlDisOver);
                if (mT3FlTexlDisOver.Update > 0) {
                    mT3FlTexlDisOver.Update = 0;
                    mT3FlTexlUpt[1] = 1;
                }
                CanJni.T3FlGetTexlPjxx(mT3FlTexlPjxx);
                if (mT3FlTexlPjxx.Update > 0) {
                    mT3FlTexlPjxx.Update = 0;
                    mT3FlTexlUpt[2] = 1;
                }
                int[] CmdData = new int[80];
                if (mT3FlTexlCmd[0] > 0) {
                    mT3FlTexlCmd[0] = 0;
                    CmdData[0] = mT3FlTexlCmd[1];
                    CmdData[1] = mT3FlTexlCmd[2];
                    T3TxelCmdSend(CmdData, 2);
                } else if (mT3FlTexlCmd[10] > 0) {
                    mT3FlTexlCmd[10] = 0;
                    CmdData[0] = mT3FlTexlCmd[11];
                    CmdData[1] = mT3FlTexlCmd[12];
                    T3TxelCmdSend(CmdData, 2);
                } else if (mT3FlTexlCmd[20] > 0) {
                    mT3FlTexlCmd[20] = 0;
                    CmdData[0] = mT3FlTexlCmd[21];
                    CmdData[1] = (mT3FlTexlCmd[22] >> 24) & 255;
                    CmdData[2] = (mT3FlTexlCmd[22] >> 16) & 255;
                    CmdData[3] = (mT3FlTexlCmd[22] >> 8) & 255;
                    CmdData[4] = mT3FlTexlCmd[22] & 255;
                    T3TxelCmdSend(CmdData, 5);
                } else if (mT3FlTexlCmd[30] > 0) {
                    mT3FlTexlCmd[30] = 0;
                    CmdData[0] = mT3FlTexlCmd[31];
                    T3TxelCmdSend(CmdData, 1);
                } else if (mT3FlTexlCmd[40] > 0) {
                    mT3FlTexlCmd[40] = 0;
                    CmdData[0] = mT3FlTexlCmd[41];
                    CmdData[1] = mT3FlTexlCmd[42];
                    CmdData[2] = mT3FlTexlCmd[43];
                    CmdData[3] = mT3FlTexlCmd[44];
                    CmdData[4] = mT3FlTexlCmd[45];
                    T3TxelCmdSend(CmdData, 5);
                } else if (mT3FlTexlCmd[50] > 0) {
                    mT3FlTexlCmd[50] = 0;
                    CmdData[0] = mT3FlTexlCmd[51];
                    CmdData[1] = mT3FlTexlCmd[52];
                    T3TxelCmdSend(CmdData, 2);
                } else if (mT3FlTexlCmd[60] > 0) {
                    mT3FlTexlCmd[60] = 0;
                    CmdData[0] = mT3FlTexlCmd[61];
                    for (int i = 0; i < 35; i++) {
                        CmdData[i + 1] = mT3FlTexlCmd[i + 62];
                    }
                    T3TxelCmdSend(CmdData, 36);
                } else if (mT3FlTexlCmd[110] > 0) {
                    mT3FlTexlCmd[110] = 0;
                    CmdData[0] = mT3FlTexlCmd[111];
                    CmdData[1] = mT3FlTexlCmd[112];
                    T3TxelCmdSend(CmdData, 2);
                }
            }
        }
        CanJni.T3FlGetCarSpeed(mT3FlSpeed);
    }
}
