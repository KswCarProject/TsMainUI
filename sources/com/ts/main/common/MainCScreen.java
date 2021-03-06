package com.ts.main.common;

import android.graphics.Rect;
import android.util.Log;
import com.ts.MainUI.Evc;
import com.ts.can.CanIF;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;

public class MainCScreen {
    private static final int CKEY1_AC = 26;
    private static final int CKEY1_ACADD = 20;
    private static final int CKEY1_ACAUTO = 24;
    private static final int CKEY1_ACBACK = 30;
    private static final int CKEY1_ACDEC = 19;
    private static final int CKEY1_ACFRONT = 29;
    private static final int CKEY1_ACLOOP = 27;
    private static final int CKEY1_ACMODE = 23;
    private static final int CKEY1_ACOFF = 31;
    private static final int CKEY1_ACPTC = 25;
    private static final int CKEY1_ACSWITCH = 28;
    private static final int CKEY1_FSADD = 22;
    private static final int CKEY1_FSDEC = 21;
    private static final int CKEY1_SEEKADD = 18;
    private static final int CKEY1_SEEKDEC = 17;
    private static final int CKEY_AMS = 12;
    private static final int CKEY_APP_SWITCH = 102;
    private static final int CKEY_A_SWITCH = 20;
    private static final int CKEY_BT_MUSIC = 26;
    private static final int CKEY_CARINFO = 30;
    private static final int CKEY_DVD = 18;
    private static final int CKEY_EJECT = 11;
    private static final int CKEY_EJECT_MENU = 24;
    private static final int CKEY_EJECT_MUTE = 25;
    private static final int CKEY_EJECT_NAVI = 27;
    private static final int CKEY_HAND = 8;
    private static final int CKEY_HOME = 2;
    private static final int CKEY_LIGHT = 21;
    public static final int CKEY_MAX = 32;
    private static final int CKEY_MODE = 13;
    private static final int CKEY_MUSIC = 19;
    private static final int CKEY_MUTE = 14;
    private static final int CKEY_NAVI = 6;
    public static final int CKEY_NAW_MAX = 31;
    private static final int CKEY_NEXT = 10;
    private static final int CKEY_PHOME = 7;
    private static final int CKEY_PLAYPAUSE = 16;
    private static final int CKEY_POWER = 1;
    private static final int CKEY_POWER_MENU = 23;
    private static final int CKEY_POWER_MUTE = 22;
    private static final int CKEY_PREV = 9;
    private static final int CKEY_RADIO_BAND = 17;
    private static final int CKEY_RETURN = 3;
    private static final int CKEY_ROTATE = 31;
    private static final int CKEY_SCREEN_MUTE = 101;
    private static final int CKEY_SOUND = 29;
    private static final int CKEY_SPEAKER = 28;
    private static final int CKEY_STOP = 15;
    private static final int CKEY_VOLADD = 4;
    private static final int CKEY_VOLDEC = 5;
    static MainCScreen MyScreen = null;
    private static final int SLIE_LEN = 24;
    private static final String TAG = "MainCScreen";
    private static final int TOUCH_LEN = 30;
    private static final int TOUCH_LEN_MIN = 5;
    private static final int TOUCH_LEN_Max = 60;
    Rect MyRect = new Rect();
    private int OldY;
    private boolean bDown = false;
    private boolean bSlide = false;
    private boolean bStand = false;
    boolean bSupportSlde = false;
    int nCKey = 0;
    int[] nCstudy = new int[32];
    private int nFirst = 1;
    private int[] nPoint = new int[3];
    private int[] nPointOld = new int[3];
    private int[] nPointOld2 = new int[3];
    private int nTouchDownKey = 0;
    public int nTouchLen = 30;
    private int nTouchStand = 0;
    private int nTouchStandKey = 0;
    private int nTouchUpKey = 0;
    int nType = 2;

    /* access modifiers changed from: package-private */
    public void SetZhonyiKey() {
        this.nCstudy[4] = 6030024;
        this.nCstudy[3] = 22282952;
        this.nCstudy[0] = 34013896;
        this.nCstudy[16] = 45810380;
        this.nCstudy[17] = 61145804;
        this.nCstudy[18] = 7865102;
        this.nCstudy[19] = 17695502;
        this.nCstudy[20] = 49349389;
        this.nCstudy[21] = 59179791;
        this.nCstudy[22] = 14549879;
        this.nCstudy[23] = 52757365;
        this.nCstudy[24] = 15270840;
        this.nCstudy[25] = 25101243;
        this.nCstudy[26] = 38667194;
        this.nCstudy[27] = 50987960;
    }

    /* access modifiers changed from: package-private */
    public void SetCWKey() {
        this.nCstudy[1] = 7537360;
        this.nCstudy[4] = 15860432;
        this.nCstudy[3] = 23855824;
        this.nCstudy[0] = 33882865;
        this.nCstudy[16] = 44237532;
        this.nCstudy[12] = 52167377;
        this.nCstudy[6] = 59900630;
        this.nCstudy[19] = 11600767;
        this.nCstudy[18] = 11600960;
        this.nCstudy[25] = 19465070;
        this.nCstudy[22] = 28705722;
        this.nCstudy[20] = 56493120;
        this.nCstudy[21] = 56296321;
        this.nCstudy[26] = 47907692;
        this.nCstudy[28] = 19596330;
        this.nCstudy[29] = 47907887;
        this.nCstudy[30] = 47907774;
    }

    /* access modifiers changed from: package-private */
    public void SetHmS7Key() {
        this.nCstudy[1] = 4063951;
        this.nCstudy[4] = 13566671;
        this.nCstudy[3] = 22348495;
        this.nCstudy[0] = 33424113;
        this.nCstudy[16] = 44499663;
        this.nCstudy[12] = 53674703;
        this.nCstudy[6] = 63570639;
        this.nCstudy[19] = 9175930;
        this.nCstudy[18] = 9176135;
        this.nCstudy[25] = 17695586;
        this.nCstudy[22] = 28509110;
        this.nCstudy[20] = 57672775;
        this.nCstudy[21] = 57934714;
        this.nCstudy[26] = 49021788;
        this.nCstudy[28] = 17433656;
        this.nCstudy[29] = 48956472;
        this.nCstudy[30] = 48759740;
    }

    /* access modifiers changed from: package-private */
    public void SetGeelyYjx1Key() {
        this.nCstudy[24] = 77004807;
        this.nCstudy[25] = 77004865;
        this.nCstudy[14] = 77004906;
        this.nCstudy[28] = 77004953;
        this.nCstudy[29] = 77004995;
        this.nCstudy[26] = 77005034;
        this.nCstudy[0] = 72745042;
        this.nCstudy[1] = 72745112;
        this.nCstudy[2] = 72745183;
        this.nCstudy[3] = 72745360;
        this.nCstudy[4] = 72745422;
        this.nCstudy[13] = 72745476;
    }

    /* access modifiers changed from: package-private */
    public void SetSLideKey() {
        int nX = this.nCstudy[3] / 65536;
        if (nX > 1024) {
            int nLeft = nX - 15;
            if (nLeft <= 1024) {
                nLeft = 1025;
            }
            this.MyRect.left = nLeft;
            this.MyRect.right = nLeft + 30;
        }
        this.MyRect.top = this.nCstudy[3] % 65536;
        this.MyRect.bottom = this.nCstudy[4] % 65536;
        this.bSupportSlde = true;
    }

    public static MainCScreen GetInstance() {
        if (MyScreen == null) {
            MyScreen = new MainCScreen();
        }
        return MyScreen;
    }

    /* access modifiers changed from: package-private */
    public int CacuLen(int nx1, int ny1, int nx2, int ny2) {
        int nDetx = nx1 - nx2;
        int nDety = ny1 - ny2;
        return (int) Math.sqrt((double) ((nDetx * nDetx) + (nDety * nDety)));
    }

    /* access modifiers changed from: package-private */
    public void DealCommonKey(int nKeyCode) {
        if (Mcu.BklisOn() == 0 && nKeyCode != 21) {
            Mcu.BklTurn();
        } else if (!MainSet.GetInstance().IsCustom("XZHC") || CanIF.IsKeyAvalid()) {
            Log.i(TAG, "DealCommonKey " + nKeyCode);
            switch (nKeyCode) {
                case 1:
                    Mcu.SetCkey(70);
                    return;
                case 2:
                    Mcu.SetCkey(8);
                    return;
                case 3:
                    Mcu.SetCkey(22);
                    return;
                case 4:
                    Mcu.SetCkey(19);
                    return;
                case 5:
                    Mcu.SetCkey(20);
                    return;
                case 6:
                    Mcu.SetCkey(11);
                    return;
                case 7:
                    Mcu.SetCkey(29);
                    return;
                case 8:
                    Mcu.SetCkey(30);
                    return;
                case 9:
                    Mcu.SetCkey(45);
                    return;
                case 10:
                    Mcu.SetCkey(44);
                    return;
                case 11:
                    Mcu.SetCkey(71);
                    return;
                case 12:
                    Mcu.SetCkey(58);
                    return;
                case 13:
                    Mcu.SetCkey(10);
                    return;
                case 14:
                    Mcu.SetCkey(16);
                    return;
                case 15:
                    Mcu.SetCkey(64);
                    return;
                case 16:
                    Mcu.SetCkey(60);
                    return;
                case 17:
                    Mcu.SetCkey(43);
                    return;
                case 18:
                    Mcu.SetCkey(13);
                    return;
                case 19:
                    Mcu.SetCkey(15);
                    return;
                case 20:
                    KeyTouch.GetInstance().sendKeyClick(187);
                    return;
                case 21:
                    Mcu.SetCkey(6);
                    return;
                case 22:
                    Mcu.SetCkey(70);
                    return;
                case 26:
                    if (MainUI.IsCameraMode() == 0) {
                        WinShow.GotoWin(7, 4);
                        return;
                    }
                    return;
                case 28:
                    Mcu.SetCkey(KeyDef.SKEY_SPEECH_1);
                    return;
                case 29:
                    WinShow.TurnToEq();
                    return;
                case 30:
                    WinShow.show("com.ts.MainUI", "com.ts.can.CanMainActivity");
                    return;
                case 31:
                    Mcu.SendXKey(23);
                    return;
                case 101:
                    Mcu.SetCkey(24);
                    return;
                case 102:
                    if (FtSet.IsIconExist(2) == 1) {
                        Mcu.SetCkey(71);
                        return;
                    } else {
                        KeyTouch.GetInstance().sendKeyClick(187);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void DealZhongyiKey(int nKeyCode) {
        Log.i(TAG, "DealZhongyiKey " + nKeyCode);
        switch (nKeyCode) {
            case 1:
                Mcu.SetCkey(70);
                return;
            case 4:
                Mcu.SetCkey(19);
                return;
            case 5:
                Mcu.SetCkey(20);
                return;
            case 17:
                Mcu.SetCkey(47);
                return;
            case 18:
                Mcu.SetCkey(46);
                return;
            case 19:
                CanIF.DealJLACKey(2);
                return;
            case 20:
                CanIF.DealJLACKey(1);
                return;
            case 21:
                CanIF.DealJLACKey(4);
                return;
            case 22:
                CanIF.DealJLACKey(3);
                return;
            case 23:
                CanIF.DealJLACKey(5);
                return;
            case 24:
                CanIF.DealJLACKey(6);
                return;
            case 25:
                CanIF.DealJLACKey(7);
                return;
            case 26:
                CanIF.DealJLACKey(8);
                return;
            case 27:
                CanIF.DealJLACKey(9);
                return;
            case 28:
                CanIF.DealJLACKey(10);
                return;
            case 101:
                Mcu.SetCkey(24);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void DealCWKey(int nKeyCode) {
        Log.i(TAG, "DealCWKey " + nKeyCode);
        switch (nKeyCode) {
            case 1:
                Mcu.SetCkey(70);
                return;
            case 2:
                Mcu.SetCkey(8);
                return;
            case 3:
                Mcu.SetCkey(22);
                return;
            case 4:
                Mcu.SetCkey(19);
                return;
            case 5:
                Mcu.SetCkey(20);
                return;
            case 7:
                Mcu.SetCkey(29);
                return;
            case 13:
                Mcu.SetCkey(10);
                return;
            case 14:
                Mcu.SetCkey(16);
                return;
            case 17:
                Mcu.SetCkey(43);
                return;
            case 19:
                CanIF.DealFmlACKey(2, 2);
                return;
            case 20:
                CanIF.DealFmlACKey(2, 1);
                return;
            case 21:
                CanIF.DealFmlACKey(1, 2);
                return;
            case 22:
                CanIF.DealFmlACKey(1, 1);
                return;
            case 23:
                CanIF.DealFmlACKey(3, 1);
                return;
            case 26:
                CanIF.DealFmlACKey(4, 1);
                return;
            case 27:
                CanIF.DealFmlACKey(5, 1);
                return;
            case 29:
                CanIF.DealFmlACKey(7, 1);
                return;
            case 30:
                CanIF.DealFmlACKey(6, 1);
                return;
            case 31:
                CanIF.DealFmlACKey(0, 1);
                return;
            case 101:
                Mcu.SetCkey(16);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void DealGeelyYjX1Key(int nKeyCode) {
        Log.i(TAG, "DealGeelyYjX1Key " + nKeyCode);
        switch (nKeyCode) {
            case 1:
                Mcu.SetCkey(70);
                return;
            case 2:
                Mcu.SetCkey(8);
                return;
            case 3:
                Mcu.SetCkey(22);
                return;
            case 4:
                Mcu.SetCkey(19);
                return;
            case 5:
                Mcu.SetCkey(20);
                return;
            case 14:
                Mcu.SetCkey(16);
                return;
            case 15:
                CanIF.DealGeelyYjx1ACKey(12);
                return;
            case 25:
                CanIF.DealGeelyYjx1ACKey(13);
                return;
            case 26:
                CanIF.DealGeelyYjx1ACKey(8);
                return;
            case 27:
                CanIF.DealGeelyYjx1ACKey(9);
                return;
            case 29:
                CanIF.DealGeelyYjx1ACKey(11);
                return;
            case 30:
                CanIF.DealGeelyYjx1ACKey(14);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void DealCkey(int nKeyCode) {
        switch (FtSet.GetCtType()) {
            case 0:
                DealCommonKey(nKeyCode);
                return;
            case 1:
                DealZhongyiKey(nKeyCode);
                return;
            case 2:
            case 3:
                DealCWKey(nKeyCode);
                return;
            case 4:
                DealGeelyYjX1Key(nKeyCode);
                return;
            case 5:
                DealCommonKey(nKeyCode);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean InSize(int x, int y, Rect Rec) {
        if (x < Rec.left || x > Rec.right || y < Rec.top || y > Rec.bottom) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void DealUpKey() {
        if (this.nTouchUpKey == 255) {
            return;
        }
        if (this.nTouchUpKey == 1) {
            if ((Mcu.GetPowState() & 8) == 0) {
                DealCkey(1);
            } else {
                DealCkey(101);
            }
        } else if (this.nTouchUpKey == 22) {
            if ((Mcu.GetPowState() & 8) == 0) {
                DealCkey(1);
            } else {
                DealCkey(14);
            }
        } else if (this.nTouchUpKey == 23) {
            if ((Mcu.GetPowState() & 8) == 0) {
                DealCkey(1);
            } else {
                DealCkey(2);
            }
        } else if (this.nTouchUpKey == 24) {
            DealCkey(2);
        } else if (this.nTouchUpKey == 25) {
            DealCkey(14);
        } else if (this.nTouchUpKey == 27) {
            DealCkey(6);
        } else {
            DealCkey(this.nTouchUpKey);
        }
    }

    /* access modifiers changed from: package-private */
    public void DealSlideKey() {
        if (this.nPointOld2[0] != this.nPoint[0] || this.nPointOld2[1] != this.nPoint[1] || this.nPointOld2[2] != this.nPoint[2]) {
            for (int i = 0; i < 3; i++) {
                this.nPointOld2[i] = this.nPoint[i];
            }
            Log.i(TAG, "nPointOld2[0]==" + this.nPointOld2[0]);
            Log.i(TAG, "nPointOld2[1]==" + this.nPointOld2[1]);
            Log.i(TAG, "nPointOld2[2]==" + this.nPointOld2[2]);
            if (this.bSlide) {
                if (this.nPoint[2] > this.OldY) {
                    int Det = this.nPoint[2] - this.OldY;
                    while (Det > 24) {
                        Evc.GetInstance().Evol_vol_tune(0);
                        this.OldY += 24;
                        Det = this.nPoint[2] - this.OldY;
                    }
                    return;
                }
                int Det2 = this.OldY - this.nPoint[2];
                while (Det2 > 24) {
                    Evc.GetInstance().Evol_vol_tune(1);
                    this.OldY -= 24;
                    Det2 = this.OldY - this.nPoint[2];
                }
            } else if (this.nPoint[0] != 1) {
                this.bSlide = false;
            } else if (InSize(this.nPoint[1], this.nPoint[2], this.MyRect)) {
                this.bSlide = true;
                this.OldY = this.nPoint[2];
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void DealKeyStandDn() {
        switch (this.nTouchDownKey) {
            case 1:
            case 22:
            case 23:
                if (this.nTouchStand == 60) {
                    if (MainSet.IsRxfKoren()) {
                        Mcu.SendXKey(19);
                    } else {
                        DealCkey(1);
                    }
                    this.bStand = true;
                    return;
                }
                return;
            case 2:
                if (this.nTouchStand == 60) {
                    DealCkey(102);
                    this.bStand = true;
                    return;
                }
                return;
            case 4:
                if (this.nTouchStand % 5 == 0) {
                    this.nTouchStand = 0;
                    if (!this.bSupportSlde) {
                        DealCkey(4);
                    }
                    this.bStand = true;
                    return;
                }
                return;
            case 5:
                if (this.nTouchStand % 5 == 0) {
                    this.nTouchStand = 0;
                    if (!this.bSupportSlde) {
                        DealCkey(5);
                    }
                    this.bStand = true;
                    return;
                }
                return;
            case 24:
            case 25:
            case 27:
                if (this.nTouchStand == 60) {
                    DealCkey(11);
                    this.bStand = true;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void DealTask(int nMode) {
        if (Iop.GetTouchExt(this.nPoint) != 0) {
            if (this.nFirst == 1) {
                this.nTouchLen = FtSet.GetCtErr();
                if (this.nTouchLen < 5 || this.nTouchLen > 60) {
                    Log.i(TAG, "DealTask ERROR nTouchLen ==" + this.nTouchLen);
                    this.nTouchLen = 30;
                    FtSet.SetCtErr(this.nTouchLen);
                    FtSet.Save(0);
                } else {
                    Log.i(TAG, "DealTask OK nTouchLen ==" + this.nTouchLen);
                }
                switch (FtSet.GetCtType()) {
                    case 0:
                        FtSet.GetCtStudy(this.nCstudy);
                        break;
                    case 1:
                        SetZhonyiKey();
                        break;
                    case 2:
                        SetCWKey();
                        break;
                    case 3:
                        SetHmS7Key();
                        break;
                    case 4:
                        SetGeelyYjx1Key();
                        break;
                    case 5:
                        FtSet.GetCtStudy(this.nCstudy);
                        SetSLideKey();
                        break;
                }
                for (int i = 0; i < 3; i++) {
                    this.nPointOld[i] = this.nPoint[i];
                    this.nPointOld2[i] = this.nPoint[i];
                }
                this.nFirst = 0;
                return;
            }
            if (this.bSupportSlde) {
                DealSlideKey();
            }
            if (this.nPointOld[0] != this.nPoint[0]) {
                for (int i2 = 0; i2 < 3; i2++) {
                    this.nPointOld[i2] = this.nPoint[i2];
                }
                if (this.nPointOld[0] == 1) {
                    this.bDown = true;
                    this.nTouchDownKey = DealCtouch(this.nPointOld[1], this.nPointOld[2], this.nPointOld[0]);
                    this.nTouchStand = 1;
                    this.bStand = false;
                } else if (this.nPointOld[0] == 0) {
                    this.bDown = false;
                    this.bSlide = false;
                    if (!this.bStand) {
                        this.nTouchUpKey = DealCtouch(this.nPointOld[1], this.nPointOld[2], this.nPointOld[0]);
                        if (this.nTouchDownKey == this.nTouchUpKey) {
                            DealUpKey();
                        }
                    }
                }
            } else if (this.bDown) {
                this.nTouchStand++;
                this.nTouchStandKey = DealCtouch(this.nPoint[1], this.nPoint[2], this.nPoint[0]);
                if (this.nTouchDownKey == this.nTouchStandKey) {
                    DealKeyStandDn();
                } else {
                    this.nTouchStand = 0;
                }
            }
        }
    }

    public int IsStudy(int nKey) {
        if (this.nCstudy[nKey] > 0) {
            return 1;
        }
        return 0;
    }

    public String GetShowString(int nKey) {
        if (this.nCstudy[nKey] > 0) {
            return String.format("X=" + (this.nCstudy[nKey] / 65536) + ";Y=" + (this.nCstudy[nKey] % 65536), new Object[0]);
        }
        return null;
    }

    public void ClearAllKey() {
        for (int i = 0; i < 32; i++) {
            this.nCstudy[i] = 0;
        }
        FtSet.SetCtStudy(this.nCstudy);
        FtSet.Save(0);
        this.nCKey = 0;
    }

    public void SetNawStudyID(int nCode) {
        if (nCode > 0) {
            this.nCstudy[nCode - 1] = 0;
            this.nCKey = nCode;
        }
    }

    public int GetStudyID() {
        return this.nCKey;
    }

    public boolean IsMuteKey(int x, int y) {
        int nCode;
        int nMaxLen = this.nTouchLen;
        for (int i = 0; i < 32; i++) {
            if ((i == 1 || i == 22 || i == 25) && i - 1 >= 0 && this.nCstudy[nCode] != 0 && CacuLen(x, y, this.nCstudy[nCode] / 65536, this.nCstudy[nCode] % 65536) < nMaxLen) {
                return true;
            }
        }
        return false;
    }

    public int DealCtouch(int nPosX, int nPosY, int nMode) {
        int nLen;
        Log.i(TAG, "nPosX==" + nPosX + "nPosY==" + nPosY + "nMode==" + nMode);
        int nMaxLen = this.nTouchLen;
        int nCode = 255;
        if (this.nCKey == 0) {
            for (int i = 0; i < 32; i++) {
                if (this.nCstudy[i] != 0 && (nLen = CacuLen(nPosX, nPosY, this.nCstudy[i] / 65536, this.nCstudy[i] % 65536)) < nMaxLen) {
                    nMaxLen = nLen;
                    nCode = i + 1;
                }
            }
        } else if (this.nCKey > 0) {
            this.nCstudy[this.nCKey - 1] = (nPosX * 65536) + nPosY;
            this.nCKey = 0;
            FtSet.SetCtStudy(this.nCstudy);
            FtSet.Save(0);
        }
        return nCode;
    }

    /* access modifiers changed from: package-private */
    public void KeyBeep() {
    }
}
