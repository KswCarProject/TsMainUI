package com.ts.main.common;

import android.util.Log;
import com.autochips.carplay.CarplayManager;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.FtSet;

public class CarplayControl {
    static CarplayManager CarMage = CarplayManager.getDefaultManager();
    static int Delaytime = 200;
    static int Delaytrans = 200;
    public static final int MEDIA_NEXT = 4;
    public static final int MEDIA_NONE = 0;
    public static final int MEDIA_PAUSE = 2;
    public static final int MEDIA_PLAY = 1;
    public static final int MEDIA_PP = 3;
    public static final int MEDIA_PREV = 5;
    public static final int PHONE_DROP = 2;
    public static final int PHONE_HOOK_SWITCH = 1;
    public static final int PHONE_KEY_0 = 4;
    public static final int PHONE_KEY_1 = 5;
    public static final int PHONE_KEY_2 = 6;
    public static final int PHONE_KEY_3 = 7;
    public static final int PHONE_KEY_4 = 8;
    public static final int PHONE_KEY_5 = 9;
    public static final int PHONE_KEY_6 = 10;
    public static final int PHONE_KEY_7 = 11;
    public static final int PHONE_KEY_8 = 12;
    public static final int PHONE_KEY_9 = 13;
    public static final int PHONE_KEY_DELETE = 16;
    public static final int PHONE_KEY_POUND = 15;
    public static final int PHONE_KEY_STAR = 14;
    public static final int PHONE_MUTE = 3;
    public static final int PHONE_UP = 0;
    private static final String TAG = "CarplayControl";
    public static boolean bConnect = false;
    static boolean bHaveCall = false;
    public static boolean bTranse = true;

    public static void PlayNext() {
        MediaControl(4);
        MediaControl(0);
    }

    public static void PlayPrev() {
        MediaControl(5);
        MediaControl(0);
    }

    public static void Play() {
        MediaControl(1);
        MediaControl(0);
    }

    public static void Pause() {
        MediaControl(2);
        MediaControl(0);
    }

    public static void Playpp() {
        MediaControl(3);
        MediaControl(0);
    }

    private static void MediaControl(int nCode) {
        if (CarMage != null) {
            CarMage.updateMedia(nCode);
        }
    }

    public static void SetCallState(boolean bEn) {
        bHaveCall = bEn;
    }

    public static void SetConnectState(boolean bEn) {
        bConnect = bEn;
    }

    public static boolean IsHaveCall() {
        return bHaveCall;
    }

    public static void ReceiveCall() {
        PhoneControl(1);
        PhoneControl(0);
    }

    public static void HangUp() {
        PhoneControl(2);
        PhoneControl(0);
    }

    private static void PhoneControl(int nCode) {
        if (CarMage != null) {
            CarMage.updateTelehony(nCode);
        }
    }

    public static void SetDefaultDisp(int mScrW, int mScrH) {
        if (CarMage != null) {
            Log.i(TAG, "mScrW=" + mScrW);
            Log.i(TAG, "mScrH=" + mScrH);
            if (mScrW > 0 && mScrH > 0) {
                if (MainSet.GetScreenType() == 3) {
                    int width = MainUI.GetInstance().getResources().getInteger(R.integer.carplay_disp_width);
                    int height = MainUI.GetInstance().getResources().getInteger(R.integer.carplay_disp_height);
                    CarMage.setScreenSize(220, (height * 220) / width);
                    CarMage.setVideoResolution(width, height);
                } else {
                    CarMage.setScreenSize(200, (mScrH * 200) / mScrW);
                    CarMage.setVideoResolution(mScrW, mScrH);
                }
            }
            CarMage.setOemIconLabel("Car");
        }
    }

    public static void SetCarplayDiable() {
        if (CarMage != null) {
            CarMage.disable();
        }
    }

    public static void SetCarplayState() {
        if (CarMage != null) {
            Log.d(TAG, "getEnableState = " + CarMage.getEnableState());
            if (FtSet.IsIconExist(130) == 1) {
                if (CarMage.getEnableState() == 0) {
                    CarMage.enable();
                }
            } else if (CarMage.getEnableState() != 0) {
                CarMage.disable();
            }
        }
    }

    public static void SetEnable(boolean bEnable) {
        if (CarMage == null) {
            return;
        }
        if (bEnable && CarMage.getEnableState() == 0) {
            CarMage.enable();
        } else if (!bEnable && CarMage.getEnableState() != 0) {
            CarMage.disable();
            bHaveCall = false;
        }
    }

    public static void task() {
        if (Delaytime > 0) {
            Delaytime--;
            if (Delaytime == 0) {
                SetCarplayState();
            }
        }
        if (Delaytrans > 0) {
            Delaytrans--;
            if (Delaytrans == 0) {
                bTranse = true;
            }
        }
    }

    public static void SetTransDelaytime() {
        Delaytrans = 200;
        bTranse = false;
    }

    public static void Reset(boolean enable) {
        if (CarMage != null) {
            Log.d(TAG, "Reset = " + enable);
            CarMage.disable();
            Delaytime = 100;
        }
    }

    public static void SetNightMode(boolean bMode) {
        if (CarMage != null) {
            CarMage.setNightMode(bMode);
        }
    }

    public static void KeyHome() {
        if (CarMage != null) {
            CarMage.updateKnob(false, true, false, 0, 0, 0);
            CarMage.updateKnob(false, false, false, 0, 0, 0);
        }
    }

    public static void KeyBack() {
        if (CarMage != null) {
            CarMage.updateKnob(false, false, true, 0, 0, 0);
            CarMage.updateKnob(false, false, false, 0, 0, 0);
        }
    }

    public static void KeyRight() {
        if (CarMage != null) {
            CarMage.updateKnob(false, false, false, 90, 0, 0);
            CarMage.updateKnob(false, false, false, 0, 0, 0);
        }
    }

    public static void KeyLeft() {
        if (CarMage != null) {
            CarMage.updateKnob(false, false, false, -90, 0, 0);
            CarMage.updateKnob(false, false, false, 0, 0, 0);
        }
    }

    public static void KeyUp() {
        if (CarMage != null) {
            CarMage.updateKnob(false, false, false, 0, -90, 0);
            CarMage.updateKnob(false, false, false, 0, 0, 0);
        }
    }

    public static void KeyDn() {
        if (CarMage != null) {
            CarMage.updateKnob(false, false, false, 0, 90, 0);
            CarMage.updateKnob(false, false, false, 0, 0, 0);
        }
    }

    public static void KeyEnter() {
        if (CarMage != null) {
            CarMage.updateKnob(true, false, false, 0, 0, 0);
            CarMage.updateKnob(false, false, false, 0, 0, 0);
        }
    }

    public static void KeyClockWise() {
        if (CarMage != null) {
            CarMage.updateKnob(false, false, false, 0, 0, 90);
            CarMage.updateKnob(false, false, false, 0, 0, 0);
        }
    }

    public static void KeyantiClockWise() {
        if (CarMage != null) {
            CarMage.updateKnob(false, false, false, 0, 0, -90);
            CarMage.updateKnob(false, false, false, 0, 0, 0);
        }
    }
}
