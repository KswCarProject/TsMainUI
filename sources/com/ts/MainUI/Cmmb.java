package com.ts.MainUI;

import android.util.Log;
import com.lgb.canmodule.Can;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;

public class Cmmb {
    static final int IR_CONTROL_MODE = 1;
    static final int IR_CONTROL_MODE1 = 2;
    static final int IR_CONTROL_MODE_KLD = 5;
    static final int IR_CONTROL_MODE_KLD2 = 7;
    static final int IR_CONTROL_XH = 3;
    static final int IR_TOUCH_MODE = 0;
    static final int IR_TOUCH_MODE2 = 4;
    static final String TAG = "Cmmb";
    static final int TOUCH_USB_CMMB = 6;
    private static Cmmb mCmmb = null;
    private static int nCmmbType = 255;

    public void SendTouch(int x, int y) {
    }

    public void PlayNext() {
    }

    public void PlayPrev() {
    }

    public void CmmbList() {
    }

    public void CmmbUp() {
    }

    public void CmmbDn() {
    }

    public void CmmbLeft() {
    }

    public void CmmbRight() {
    }

    public void CmmbEnter() {
    }

    public void CmmbReturn() {
    }

    public void CmmbSearch() {
    }

    public void CmmbLanguage() {
    }

    public void SendIrCode(int a, int b, int x, int y) {
        Log.i(TAG, "SendIrCode a= " + a + "b=" + b + "x=" + x + "y=" + y);
        Mcu.SendxyTouch(a, b, x, y);
    }

    class TsTouchYK extends Cmmb {
        TsTouchYK() {
        }

        public void CmmbEnter() {
            SendIrCode(0, 255, 84, 171);
        }

        public void SendTouch(int x, int y) {
            SendIrCode(170, 85, x, y);
        }

        public void PlayNext() {
            SendIrCode(0, 255, 4, Can.CAN_MG_ZS_WC);
        }

        public void PlayPrev() {
            SendIrCode(0, 255, 14, 241);
        }
    }

    class TsButtonControl extends Cmmb {
        TsButtonControl() {
        }

        public void SendTouch(int x, int y) {
        }

        public void CmmbList() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 4, Can.CAN_MG_ZS_WC);
        }

        public void CmmbUp() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 5, Can.CAN_NISSAN_XFY);
        }

        public void CmmbDn() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 13, Can.CAN_MZD_LUOMU);
        }

        public void CmmbLeft() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 8, Can.CAN_FORD_EDGE_XFY);
        }

        public void CmmbRight() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 10, Can.CAN_FORD_MONDEO_XFY);
        }

        public void CmmbEnter() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 9, Can.CAN_CHRYSLER_TXB);
        }

        public void CmmbReturn() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 12, Can.CAN_MZD_TXB);
        }

        public void PlayNext() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 5, Can.CAN_NISSAN_XFY);
        }

        public void PlayPrev() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 13, Can.CAN_MZD_LUOMU);
        }
    }

    class TsBtnConTrol extends Cmmb {
        TsBtnConTrol() {
        }

        public void SendTouch(int x, int y) {
            SendIrCode(170, 85, x, y);
        }

        public void CmmbList() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 4, Can.CAN_MG_ZS_WC);
        }

        public void CmmbUp() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 5, Can.CAN_NISSAN_XFY);
        }

        public void CmmbDn() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 13, Can.CAN_MZD_LUOMU);
        }

        public void CmmbLeft() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 8, Can.CAN_FORD_EDGE_XFY);
        }

        public void CmmbRight() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 10, Can.CAN_FORD_MONDEO_XFY);
        }

        public void CmmbEnter() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 9, Can.CAN_CHRYSLER_TXB);
        }

        public void CmmbReturn() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 12, Can.CAN_MZD_TXB);
        }

        public void PlayNext() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 5, Can.CAN_NISSAN_XFY);
        }

        public void PlayPrev() {
            SendIrCode(0, Can.CAN_GM_CAPTIVA_OD, 13, Can.CAN_MZD_LUOMU);
        }
    }

    class StBtnControlXh extends Cmmb {
        StBtnControlXh() {
        }

        public void PlayNext() {
            SendIrCode(151, 123, 178, 77);
        }

        public void PlayPrev() {
            SendIrCode(151, 123, 179, 76);
        }

        public void CmmbSearch() {
            SendIrCode(151, 123, Can.CAN_MZD_LUOMU, 13);
        }

        public void CmmbLanguage() {
            SendIrCode(151, 123, Can.CAN_FLAT_WC, 23);
        }
    }

    class TsTouchYK2 extends Cmmb {
        TsTouchYK2() {
        }

        public void SendTouch(int x, int y) {
            SendIrCode(170, 85, x, y);
        }

        public void PlayNext() {
            SendIrCode(0, 255, 14, 241);
        }

        public void PlayPrev() {
            SendIrCode(0, 255, 4, Can.CAN_MG_ZS_WC);
        }
    }

    class TsControlkld extends Cmmb {
        static final int KLD_CMMB_CUSTOM = 128;
        static final int KLD_CMMB__CUSTOM = 127;

        TsControlkld() {
        }

        public void CmmbSearch() {
            SendIrCode(128, 127, 24, Can.CAN_BYD_S6_S7);
        }

        public void CmmbList() {
            SendIrCode(128, 127, 71, 184);
        }

        public void CmmbUp() {
            SendIrCode(128, 127, 5, Can.CAN_NISSAN_XFY);
        }

        public void CmmbDn() {
            SendIrCode(128, 127, 15, 240);
        }

        public void CmmbLeft() {
            SendIrCode(128, 127, 7, Can.CAN_RENAUL_KOLEOS_XFY);
        }

        public void CmmbRight() {
            SendIrCode(128, 127, 2, Can.CAN_FORD_ESCORT_LY);
        }

        public void CmmbEnter() {
            SendIrCode(128, 127, 64, 191);
        }

        public void CmmbReturn() {
            SendIrCode(128, 127, 72, 183);
        }

        public void PlayNext() {
            SendIrCode(128, 127, 69, 186);
        }

        public void PlayPrev() {
            SendIrCode(128, 127, 1, Can.CAN_FLAT_RZC);
        }
    }

    class TsControlkld2 extends Cmmb {
        static final int KLD_CMMB_CUSTOM2 = 128;
        static final int KLD_CMMB__CUSTOM2 = 127;

        TsControlkld2() {
        }

        public void CmmbSearch() {
            SendIrCode(128, 127, 24, Can.CAN_BYD_S6_S7);
        }

        public void CmmbList() {
            SendIrCode(128, 127, 71, 184);
        }

        public void CmmbUp() {
            SendIrCode(128, 127, 5, Can.CAN_NISSAN_XFY);
        }

        public void CmmbDn() {
            SendIrCode(128, 127, 15, 240);
        }

        public void CmmbLeft() {
            SendIrCode(128, 127, 7, Can.CAN_RENAUL_KOLEOS_XFY);
        }

        public void CmmbRight() {
            SendIrCode(128, 127, 2, Can.CAN_FORD_ESCORT_LY);
        }

        public void CmmbEnter() {
            SendIrCode(128, 127, 64, 191);
        }

        public void CmmbReturn() {
            SendIrCode(128, 127, 72, 183);
        }

        public void PlayNext() {
            SendIrCode(128, 127, 5, Can.CAN_NISSAN_XFY);
        }

        public void PlayPrev() {
            SendIrCode(128, 127, 15, 240);
        }
    }

    public static Cmmb GetInstance() {
        if (mCmmb == null) {
            mCmmb = new Cmmb();
            mCmmb.SetType(FtSet.GetDtvType());
        }
        return mCmmb;
    }

    public void SetType(int nType) {
        nCmmbType = nType;
        switch (nType) {
            case 0:
                mCmmb = new TsTouchYK();
                return;
            case 1:
                mCmmb = new TsButtonControl();
                return;
            case 2:
                mCmmb = new TsBtnConTrol();
                return;
            case 3:
                mCmmb = new StBtnControlXh();
                return;
            case 4:
                mCmmb = new TsTouchYK2();
                return;
            case 5:
                mCmmb = new TsControlkld();
                return;
            case 7:
                mCmmb = new TsControlkld2();
                return;
            case 99:
                mCmmb = new TsTouchYK();
                return;
            default:
                mCmmb = new Cmmb();
                return;
        }
    }

    public int DealCmmbKey(int nKeyCode) {
        switch (nKeyCode) {
            case 21:
            case 304:
                CmmbEnter();
                return 1;
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
            case 516:
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
            case KeyDef.SKEY_CHUP_1 /*794*/:
                PlayNext();
                return 1;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
            case 515:
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
            case KeyDef.SKEY_CHDN_1 /*799*/:
                PlayPrev();
                return 1;
            case 66:
            case KeyDef.RKEY_UP /*289*/:
                CmmbUp();
                return 1;
            case 67:
            case 290:
                CmmbDn();
                return 1;
            case 68:
            case 288:
                CmmbLeft();
                return 1;
            case 69:
            case 287:
                CmmbRight();
                return 1;
            default:
                return 0;
        }
    }
}
