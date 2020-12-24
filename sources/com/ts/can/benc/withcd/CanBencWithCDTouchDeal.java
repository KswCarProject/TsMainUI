package com.ts.can.benc.withcd;

import android.util.Log;
import com.ts.main.common.KeyTouch;
import com.yyw.ts70xhw.Iop;
import java.util.LinkedList;

public class CanBencWithCDTouchDeal {
    static byte[] Cbuf = new byte[100];
    static byte[] Readtestdata = new byte[100];
    private static final String TAG = "CanBencWithCDTouchDeal";
    public static int nBoxMode = 0;
    public static int nCameraMode = 0;
    static int nChkCnt = 0;
    static LinkedList<Byte> readdata = new LinkedList<>();

    public static void StartCmd(int cmd) {
        byte chk = 0;
        byte[] data = new byte[5];
        data[0] = 46;
        data[1] = -127;
        data[2] = 1;
        data[3] = (byte) (cmd & 255);
        for (int i = 1; i <= data[2] + 2; i++) {
            chk = (byte) (((byte) (data[i] & 255)) + chk);
        }
        data[4] = (byte) (((byte) (chk ^ 255)) & 255);
        Iop.UartSend(data, 5);
    }

    static void Query(int cmd) {
        byte chk = 0;
        byte[] data = new byte[5];
        data[0] = 46;
        data[1] = -112;
        data[2] = 1;
        data[3] = (byte) (cmd & 255);
        for (int i = 1; i <= data[2] + 2; i++) {
            chk = (byte) (((byte) (data[i] & 255)) + chk);
        }
        data[4] = (byte) (((byte) (chk ^ 255)) & 255);
        Iop.UartSend(data, 5);
    }

    static void Set(int cmd, int para) {
        byte chk = 0;
        byte[] data = new byte[6];
        data[0] = 46;
        data[1] = -104;
        data[2] = 2;
        data[3] = (byte) (cmd & 255);
        data[4] = (byte) (para & 255);
        for (int i = 1; i <= data[2] + 2; i++) {
            chk = (byte) (((byte) (data[i] & 255)) + chk);
        }
        data[5] = (byte) (((byte) (chk ^ 255)) & 255);
        Iop.UartSend(data, 6);
    }

    public static int getUnsignedByte(byte data) {
        return data & 255;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int GetCmd() {
        /*
            r4 = 0
            r0 = 0
            r3 = 0
            r1 = 0
            r6 = 0
            byte[] r7 = Readtestdata
            r8 = 100
            int r5 = com.yyw.ts70xhw.Iop.UartRead(r7, r8)
            if (r5 <= 0) goto L_0x0016
            r0 = 0
        L_0x0010:
            if (r0 < r5) goto L_0x0020
            r7 = 500(0x1f4, float:7.0E-43)
            nChkCnt = r7
        L_0x0016:
            r0 = 0
        L_0x0017:
            java.util.LinkedList<java.lang.Byte> r7 = readdata
            int r7 = r7.size()
            if (r0 < r7) goto L_0x0030
        L_0x001f:
            return r6
        L_0x0020:
            java.util.LinkedList<java.lang.Byte> r7 = readdata
            byte[] r8 = Readtestdata
            byte r8 = r8[r0]
            java.lang.Byte r8 = java.lang.Byte.valueOf(r8)
            r7.add(r8)
            int r0 = r0 + 1
            goto L_0x0010
        L_0x0030:
            if (r4 != 0) goto L_0x00b3
            java.util.LinkedList<java.lang.Byte> r7 = readdata
            java.lang.Object r7 = r7.get(r0)
            java.lang.Byte r7 = (java.lang.Byte) r7
            byte r7 = r7.byteValue()
            r8 = 46
            if (r7 == r8) goto L_0x004b
            java.util.LinkedList<java.lang.Byte> r7 = readdata
            r7.removeFirst()
            r0 = 0
        L_0x0048:
            int r0 = r0 + 1
            goto L_0x0017
        L_0x004b:
            java.util.LinkedList<java.lang.Byte> r7 = readdata
            int r7 = r7.size()
            int r7 = r7 - r0
            r8 = 3
            if (r7 < r8) goto L_0x001f
            java.util.LinkedList<java.lang.Byte> r7 = readdata
            int r8 = r0 + 2
            java.lang.Object r7 = r7.get(r8)
            java.lang.Byte r7 = (java.lang.Byte) r7
            byte r7 = r7.byteValue()
            int r7 = getUnsignedByte(r7)
            int r3 = r7 + 4
            r7 = 60
            if (r3 <= r7) goto L_0x0080
            r1 = 0
        L_0x006e:
            java.util.LinkedList<java.lang.Byte> r7 = readdata
            int r7 = r7.size()
            if (r1 < r7) goto L_0x0078
            r0 = 1
            goto L_0x001f
        L_0x0078:
            java.util.LinkedList<java.lang.Byte> r7 = readdata
            r7.removeFirst()
            int r1 = r1 + 1
            goto L_0x006e
        L_0x0080:
            java.util.LinkedList<java.lang.Byte> r7 = readdata
            int r7 = r7.size()
            int r7 = r7 - r0
            if (r3 > r7) goto L_0x001f
            r4 = 1
            r1 = 0
            r2 = r1
        L_0x008c:
            if (r4 <= 0) goto L_0x00b1
            byte[] r8 = Cbuf
            int r1 = r2 + 1
            java.util.LinkedList<java.lang.Byte> r7 = readdata
            java.lang.Object r7 = r7.get(r0)
            java.lang.Byte r7 = (java.lang.Byte) r7
            byte r7 = r7.byteValue()
            r8[r2] = r7
            if (r1 < r3) goto L_0x0048
            int r0 = r0 + 1
            r4 = 0
            r6 = r1
            r1 = 0
        L_0x00a7:
            if (r1 >= r3) goto L_0x001f
            java.util.LinkedList<java.lang.Byte> r7 = readdata
            r7.removeFirst()
            int r1 = r1 + 1
            goto L_0x00a7
        L_0x00b1:
            r1 = r2
            goto L_0x0048
        L_0x00b3:
            r2 = r1
            goto L_0x008c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.can.benc.withcd.CanBencWithCDTouchDeal.GetCmd():int");
    }

    public static void DealCmd() {
        int i = 1;
        if (GetCmd() != 0) {
            byte len = (byte) (Cbuf[2] + 2);
            byte chk = Cbuf[1];
            byte i2 = 2;
            while (i2 <= len) {
                chk = (byte) (Cbuf[i2] + chk);
                i2 = (byte) (i2 + 1);
            }
            if (((byte) (chk ^ 255)) == Cbuf[i2]) {
                switch (Cbuf[1]) {
                    case 16:
                        Log.i(TAG, "UartRead type2 =" + Cbuf[3]);
                        if (nBoxMode != Cbuf[3]) {
                            nBoxMode = Cbuf[3];
                            if (nBoxMode == 1 && CanBencWithCDExdActivity.IsBencWithCDWin()) {
                                CanBencWithCDExdActivity.finishBencWithCDWin();
                            }
                        }
                        if (Cbuf[4] == 16) {
                            Cbuf[4] = 4;
                        }
                        nCameraMode = Cbuf[4];
                        return;
                    case 32:
                        int x = ((((Cbuf[4] & 255) * 256) + (Cbuf[5] & 255)) * 1920) / 1280;
                        int y = ((((Cbuf[6] & 255) * 256) + (Cbuf[7] & 255)) * 720) / 480;
                        Log.i(TAG, "UartRead x =" + x);
                        Log.i(TAG, "UartRead y =" + y);
                        KeyTouch GetInstance = KeyTouch.GetInstance();
                        float f = (float) x;
                        float f2 = (float) y;
                        if (Cbuf[3] <= 0) {
                            i = 0;
                        }
                        GetInstance.sendTap(f, f2, i);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public static void ReadTheTouchPanal() {
        Iop.UartOpen(38400);
        StartCmd(1);
        new Thread() {
            public void run() {
                while (true) {
                    CanBencWithCDTouchDeal.DealCmd();
                    if (CanBencWithCDTouchDeal.nChkCnt > 0) {
                        CanBencWithCDTouchDeal.nChkCnt--;
                    } else {
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
