package com.ts.MainUI;

import android.util.Log;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;
import java.util.Calendar;

public class Dvr {
    static final int CUSTOM_CODE_TWZ = 0;
    static final int CUSTOM_CODE__TWZ = 255;
    static final int IR_DVR_KELAIDE = 9;
    static final int IR_DVR_KFR = 5;
    static final int IR_DVR_LEILMA = 8;
    static final int IR_DVR_MODE0 = 0;
    static final int IR_DVR_MODE1 = 1;
    static final int IR_DVR_MODE2 = 2;
    static final int IR_DVR_MODE3 = 10;
    static final int IR_DVR_XHTW = 4;
    static final int IR_DVR_XHTW2 = 6;
    static final int IR_DVR_ZHY = 3;
    static final int IR_ZHONGKE_BTN = 13;
    static final String TAG = "Dvr";
    static final int UART_DVR_BST = 11;
    static final int UART_DVR_LL = 7;
    static final int UART_SHANLING_YSJ = 12;
    private static Dvr mDvr = null;
    private static int nDvrType = 255;

    public void SendIrCode(byte a, byte b, byte x, byte y) {
        Log.i(TAG, "SendIrCode a= " + a + "b=" + b + "x=" + x + "y=" + y);
        Mcu.SendxyTouch(a, b, x, y);
    }

    public int BtnFun(int nBtn) {
        return 1;
    }

    public int PowerOff() {
        return 1;
    }

    public int SendTouchXY(int x, int y) {
        return 1;
    }

    public int Calibrilate() {
        return 1;
    }

    public void SendBlack() {
    }

    class StDvrYSJ extends Dvr {
        StDvrYSJ() {
        }

        public int BtnFun(int nBtn) {
            switch (nBtn) {
                case 0:
                    SendIrCode((byte) 16, (byte) -17, (byte) 89, (byte) -90);
                    return 1;
                case 1:
                    SendIrCode((byte) 16, (byte) -17, (byte) 22, (byte) -23);
                    return 1;
                case 2:
                    SendIrCode((byte) 16, (byte) -17, (byte) 8, (byte) -9);
                    return 1;
                case 3:
                    SendIrCode((byte) 16, (byte) -17, (byte) 84, (byte) -85);
                    return 1;
                case 4:
                    SendIrCode((byte) 16, (byte) -17, (byte) 28, (byte) -29);
                    return 1;
                case 5:
                    SendIrCode((byte) 16, (byte) -17, (byte) 83, (byte) -84);
                    return 1;
                default:
                    return 1;
            }
        }
    }

    class StDvrYSJTW extends Dvr {
        StDvrYSJTW() {
        }

        public int BtnFun(int nBtn) {
            switch (nBtn) {
                case 0:
                    SendIrCode((byte) 0, (byte) -1, (byte) 21, (byte) -22);
                    return 1;
                case 1:
                    SendIrCode((byte) 0, (byte) -1, (byte) 4, (byte) -5);
                    return 1;
                case 2:
                    SendIrCode((byte) 0, (byte) -1, (byte) 25, (byte) -26);
                    return 1;
                case 3:
                    SendIrCode((byte) 0, (byte) -1, (byte) 3, (byte) -4);
                    return 1;
                case 4:
                    SendIrCode((byte) 0, (byte) -1, (byte) 7, (byte) -8);
                    return 1;
                case 5:
                    SendIrCode((byte) 0, (byte) -1, (byte) 9, (byte) -10);
                    return 1;
                default:
                    return 1;
            }
        }

        public int PowerOff() {
            SendIrCode((byte) 0, (byte) -1, (byte) 71, (byte) -72);
            return 1;
        }
    }

    class StDvrYSJTW2 extends Dvr {
        StDvrYSJTW2() {
        }

        public int BtnFun(int nBtn) {
            switch (nBtn) {
                case 0:
                    SendIrCode((byte) 16, (byte) -17, (byte) 89, (byte) -90);
                    return 1;
                case 1:
                    SendIrCode((byte) 16, (byte) -17, (byte) 22, (byte) -23);
                    return 1;
                case 2:
                    SendIrCode((byte) 16, (byte) -17, (byte) 8, (byte) -9);
                    return 1;
                case 3:
                    SendIrCode((byte) 16, (byte) -17, (byte) 84, (byte) -85);
                    return 1;
                case 4:
                    SendIrCode((byte) 16, (byte) -17, (byte) 28, (byte) -29);
                    return 1;
                case 5:
                    SendIrCode((byte) 16, (byte) -17, (byte) 83, (byte) -84);
                    return 1;
                default:
                    return 1;
            }
        }

        public int PowerOff() {
            SendIrCode((byte) 0, (byte) -1, (byte) 71, (byte) -72);
            return 1;
        }
    }

    class StDvrZHY extends Dvr {
        StDvrZHY() {
        }

        public int SendTouchXY(int x, int y) {
            SendIrCode((byte) -86, (byte) 85, (byte) x, (byte) y);
            return 1;
        }

        public int Calibrilate() {
            Calendar c = Calendar.getInstance();
            byte ubSendData = (byte) (c.get(1) - 2000);
            SendIrCode((byte) -96, (byte) 95, ubSendData, (byte) (255 - ubSendData));
            byte ubSendData2 = (byte) c.get(2);
            SendIrCode((byte) -95, (byte) 94, ubSendData2, (byte) (255 - ubSendData2));
            byte ubSendData3 = (byte) c.get(5);
            SendIrCode((byte) -94, (byte) 93, ubSendData3, (byte) (255 - ubSendData3));
            byte ubSendData4 = (byte) c.get(11);
            SendIrCode((byte) -93, (byte) 92, ubSendData4, (byte) (255 - ubSendData4));
            byte ubSendData5 = (byte) c.get(12);
            SendIrCode((byte) -92, (byte) 91, ubSendData5, (byte) (255 - ubSendData5));
            return 1;
        }
    }

    class StDvrYSJTWZ extends Dvr {
        StDvrYSJTWZ() {
        }

        public int BtnFun(int nBtn) {
            switch (nBtn) {
                case 0:
                    SendIrCode((byte) 0, (byte) -1, (byte) 21, (byte) -22);
                    break;
                case 1:
                    SendIrCode((byte) 0, (byte) -1, (byte) 25, (byte) -26);
                    break;
                case 2:
                    SendIrCode((byte) 0, (byte) -1, (byte) 2, (byte) -3);
                    break;
                case 3:
                    SendIrCode((byte) 0, (byte) -1, (byte) 4, (byte) -5);
                    break;
                case 4:
                    SendIrCode((byte) 0, (byte) -1, (byte) 7, (byte) -8);
                    break;
                case 5:
                    SendIrCode((byte) 0, (byte) -1, (byte) 64, (byte) -65);
                    break;
                case 6:
                    SendIrCode((byte) 0, (byte) -1, (byte) 9, (byte) -10);
                    break;
            }
            return 0;
        }
    }

    public static Dvr GetInstance() {
        if (mDvr == null) {
            mDvr = new Dvr();
            mDvr.SetType(FtSet.GetDvrType());
        }
        return mDvr;
    }

    public void SetType(int nType) {
        nDvrType = nType;
        switch (nType) {
            case 0:
                mDvr = new StDvrZHY();
                return;
            case 1:
                mDvr = new StDvrYSJTW();
                return;
            case 2:
                mDvr = new StDvrYSJTW2();
                return;
            case 3:
                mDvr = new StDvrZHY();
                return;
            case 10:
                mDvr = new StDvrYSJTWZ();
                return;
            default:
                mDvr = new StDvrZHY();
                return;
        }
    }
}
