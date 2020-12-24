package com.ts.can.faw.t3;

import android.util.Log;
import com.lgb.canmodule.CanJni;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.WinShow;
import com.yyw.ts70xhw.Iop;
import java.util.LinkedList;

public class CanFawT3TouchDeal {
    static byte[] Cbuf = new byte[100];
    static byte[] Readtestdata = new byte[100];
    private static final String TAG = "CanFawT3TouchDeal";
    public static int nRightPreCnt = 0;
    static int nSta = 0;
    static int nX = 0;
    static int nY = 0;
    static LinkedList<Byte> readdata = new LinkedList<>();

    static int GetCmd() {
        int len;
        int nNum = Iop.UartRead(Readtestdata, 100);
        if (nNum > 0) {
            for (int i = 0; i < nNum; i++) {
                readdata.add(Byte.valueOf(Readtestdata[i]));
            }
        }
        if (readdata.size() < 9) {
            return 0;
        }
        if (readdata.get(0).byteValue() == 46 && (len = readdata.get(2).byteValue() + 4) == 9 && len <= readdata.size()) {
            for (int p = 0; p < len; p++) {
                Cbuf[p] = readdata.get(0).byteValue();
                readdata.removeFirst();
            }
            return 1;
        }
        readdata.removeFirst();
        return 0;
    }

    public static int DealCmd() {
        int i;
        int i2 = 0;
        if (GetCmd() == 0) {
            return 0;
        }
        byte len = (byte) (Cbuf[2] + 2);
        byte chk = Cbuf[1];
        byte i3 = 2;
        while (i3 <= len) {
            chk = (byte) (Cbuf[i3] + chk);
            i3 = (byte) (i3 + 1);
        }
        if (((byte) (chk ^ 255)) != Cbuf[i3]) {
            return 0;
        }
        switch (Cbuf[1]) {
            case 69:
                int x = ((Cbuf[4] & 255) * 256) + (Cbuf[3] & 255);
                int y = ((Cbuf[6] & 255) * 256) + (Cbuf[5] & 255);
                KeyTouch GetInstance = KeyTouch.GetInstance();
                float f = (float) x;
                float f2 = (float) y;
                if (Cbuf[7] > 0) {
                    i = 1;
                } else {
                    i = 0;
                }
                GetInstance.sendTap(f, f2, i);
                int ndy = 0;
                if (CanJni.GetCanType() == 283) {
                    ndy = 240;
                }
                if (x >= 1180 && x <= 1280 && y >= 650 - ndy && y <= 720 - ndy && Cbuf[7] > 0) {
                    if (nRightPreCnt == 0) {
                        nRightPreCnt = 1;
                        break;
                    }
                } else {
                    nRightPreCnt = 0;
                    break;
                }
                break;
            case 70:
                int x2 = ((Cbuf[4] & 255) * 256) + (Cbuf[3] & 255);
                int y2 = ((Cbuf[6] & 255) * 256) + (Cbuf[5] & 255);
                KeyTouch GetInstance2 = KeyTouch.GetInstance();
                float f3 = (float) x2;
                float f4 = (float) y2;
                if (Cbuf[7] > 0) {
                    i2 = 1;
                }
                GetInstance2.sendMulTap(f3, f4, i2);
                break;
        }
        return 1;
    }

    public static void Time() {
        if (nRightPreCnt > 0) {
            nRightPreCnt++;
            Log.i(TAG, "nRightPreCnt =" + nRightPreCnt);
            if (nRightPreCnt == 300) {
                WinShow.show("com.android.settings", "com.android.settings.Settings");
            }
        }
    }

    public static void ReadTheTouchPanal() {
        if (CanJni.GetCanType() == 283) {
            Iop.UartOpen(38400);
        } else {
            Iop.UartOpen(115200);
        }
        new Thread() {
            public void run() {
                while (true) {
                    if (CanFawT3TouchDeal.DealCmd() <= 0) {
                        try {
                            sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
}
