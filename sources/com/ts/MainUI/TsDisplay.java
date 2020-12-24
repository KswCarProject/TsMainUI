package com.ts.MainUI;

import android.util.Log;
import com.mediatek.miravision.setting.MiraVisionJni;
import com.mediatek.pq.PictureQuality;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class TsDisplay {
    private static String TAG = "TsDisplay";
    private static TsDisplay mDisplay = null;
    private int nDelayTime = 0;
    private int nSetData = 255;
    public int nSetTcon = 1;
    private int nSrcMode = 255;

    public void SetSrcMute(int nTime) {
        Mcu.BklTurnInt(0);
        Log.d(TAG, "BklTurnInt start ");
        this.nDelayTime = nTime;
    }

    public static TsDisplay GetInstance() {
        if (mDisplay == null) {
            mDisplay = new TsDisplay();
        }
        return mDisplay;
    }

    public void Inint() {
        if (FtSet.GetCam8824() != 0) {
            SetDispParat(0);
        }
        this.nSrcMode = 255;
        this.nDelayTime = 0;
        this.nSetData = 255;
        Log.i(TAG, "nNowPQMode==" + MiraVisionJni.nativeGetPictureMode());
        MiraVisionJni.Range conRange = MiraVisionJni.getContrastIndexRange();
        Log.i(TAG, "conRange.max==" + conRange.max);
        Log.i(TAG, "conRange.min==" + conRange.min);
        Log.i(TAG, "conRange.defaultValue==" + conRange.defaultValue);
        Log.i(TAG, "getContrastIndex==" + MiraVisionJni.getContrastIndex());
        MiraVisionJni.Range statusRange = MiraVisionJni.getSaturationIndexRange();
        Log.i(TAG, "statusRange.max==" + statusRange.max);
        Log.i(TAG, "statusRange.min==" + statusRange.min);
        Log.i(TAG, "statusRange.defaultValue==" + statusRange.defaultValue);
        Log.i(TAG, "getSaturationIndex==" + MiraVisionJni.getSaturationIndex());
        MiraVisionJni.Range bretnessRange = MiraVisionJni.getPicBrightnessIndexRange();
        Log.i(TAG, "bretnessRange.max==" + bretnessRange.max);
        Log.i(TAG, "bretnessRange.min==" + bretnessRange.min);
        Log.i(TAG, "bretnessRange.defaultValue==" + bretnessRange.defaultValue);
        Log.i(TAG, "getPicBrightnessIndex==" + MiraVisionJni.getPicBrightnessIndex());
        MiraVisionJni.Range sharpbretnessRange = MiraVisionJni.getSharpnessIndexRange();
        Log.i(TAG, "sharpbretnessRange.max==" + sharpbretnessRange.max);
        Log.i(TAG, "sharpbretnessRange.min==" + sharpbretnessRange.min);
        Log.i(TAG, "sharpbretnessRange.defaultValue==" + sharpbretnessRange.defaultValue);
        Log.i(TAG, "getSharpnessIndex==" + MiraVisionJni.getSharpnessIndex());
        MiraVisionJni.Range ColorEffectRange = MiraVisionJni.getContrastIndexRange();
        Log.i(TAG, "ColorEffectRange.max==" + ColorEffectRange.max);
        Log.i(TAG, "ColorEffectRange.min==" + ColorEffectRange.min);
        Log.i(TAG, "ColorEffectRange.defaultValue==" + ColorEffectRange.defaultValue);
        Log.i(TAG, "getColorEffectIndex==" + MiraVisionJni.getColorEffectIndex());
        MiraVisionJni.Range DCEffectRange = MiraVisionJni.getDynamicContrastIndexRange();
        Log.i(TAG, "DCEffectRange.max==" + DCEffectRange.max);
        Log.i(TAG, "DCEffectRange.min==" + DCEffectRange.min);
        Log.i(TAG, "DCEffectRange.defaultValue==" + DCEffectRange.defaultValue);
        Log.i(TAG, "getDynamicContrastIndex==" + MiraVisionJni.getDynamicContrastIndex());
    }

    /* access modifiers changed from: package-private */
    public int CheckVal(int nVal) {
        if (nVal <= 9) {
            return 0;
        }
        int nRerutn = (nVal + 1) / 10;
        if (nRerutn > 0) {
            return nRerutn - 1;
        }
        return nRerutn;
    }

    public void SetDisp(int nMode) {
        this.nSetData = nMode;
    }

    public void SetDispParat(int nSrc) {
        Log.d(TAG, "SetDispParat ==" + nSrc);
        if (FtSet.GetTconAdj() != 0) {
            if (FtSet.GetCam8824() != 0) {
                if (nSrc >= 0) {
                    Mcu.SendLCDColor(nSrc, Iop.GetColor(nSrc, 0), Iop.GetColor(nSrc, 1), Iop.GetColor(nSrc, 2), Iop.GetColor(nSrc, 3));
                }
            } else if (nSrc >= 0) {
                Log.d(TAG, "Iop.GetColor(nSrc, Iop.CVBS_BRIGHTNESS)" + Iop.GetColor(nSrc, 0));
                Log.d(TAG, "Iop.GetColor(nSrc, Iop.CVBS_CONTRAST)" + Iop.GetColor(nSrc, 1));
                Log.d(TAG, "Iop.GetColor(nSrc, Iop.CVBS_SATURATION)" + Iop.GetColor(nSrc, 3));
                Log.d(TAG, "Iop.GetColor(nSrc, Iop.CVBS_HUE)" + Iop.GetColor(nSrc, 2));
                PictureQuality.setPictureMode(2);
                MiraVisionJni.nativeEnablePQColor(1);
                PictureQuality.enablePQ(1);
                PictureQuality.enableColorEffect(1);
                PictureQuality.enableDynamicContrast(1);
                PictureQuality.enableSharpness(1);
                MiraVisionJni.setPicBrightnessIndex(CheckVal(Iop.GetColor(nSrc, 0)));
                MiraVisionJni.setContrastIndex(CheckVal(Iop.GetColor(nSrc, 1)));
                MiraVisionJni.setSaturationIndex(CheckVal(Iop.GetColor(nSrc, 3)));
                MiraVisionJni.setSharpnessIndex(CheckVal(Iop.GetColor(nSrc, 2)));
                Log.d(TAG, "MiraVisionJni.getPicBrightnessIndex()==" + MiraVisionJni.getPicBrightnessIndex());
                Log.d(TAG, "IMiraVisionJni.getContrastIndex()==" + MiraVisionJni.getContrastIndex());
                Log.d(TAG, "MiraVisionJni.getSaturationIndex()==" + MiraVisionJni.getSaturationIndex());
                Log.d(TAG, "MiraVisionJni.getColorEffectIndex()==" + MiraVisionJni.getColorEffectIndex());
                Log.d(TAG, "MiraVisionJni.getSharpnessIndex()==" + MiraVisionJni.getSharpnessIndex());
                Log.d(TAG, "MiraVisionJni.getDynamicContrastIndex()==" + MiraVisionJni.getDynamicContrastIndex());
            } else {
                PictureQuality.setPictureMode(1);
            }
        }
    }

    public void UISetDefault(int nSrc) {
        Log.d(TAG, "UISetDefault ");
        Iop.SetColorDefault(nSrc);
        SetDispParat(nSrc);
    }

    public int UIGetVal(int nSrc, int nType) {
        if (nSrc > 3) {
            Log.d(TAG, "UISetVal error nSrc == " + nSrc);
            return 50;
        } else if (nType <= 3) {
            return Iop.GetColor(nSrc, nType);
        } else {
            Log.d(TAG, "UISetVal error nType == " + nType);
            return 50;
        }
    }

    /* access modifiers changed from: package-private */
    public void UISetColorParat(int nSrc, int nType, int nVal) {
    }

    public void UIValStep(int nSrc, int nType, int nStep) {
        int nVal;
        if (nSrc > 3) {
            Log.d(TAG, "UIValStep error nSrc == " + nSrc);
        } else if (nType > 3) {
            Log.d(TAG, "UIValStep error nType == " + nType);
        } else {
            int nVal2 = Iop.GetColor(nSrc, nType);
            if (nStep == 1) {
                if (FtSet.GetCam8824() != 0) {
                    nVal = nVal2 + 1;
                } else {
                    nVal = nVal2 + 10;
                }
                Iop.SetColor(nSrc, nType, nVal);
                SetDispParat(nSrc);
                return;
            }
            if (FtSet.GetCam8824() == 0) {
                nVal2 -= 10;
            } else if (nVal2 > 0) {
                nVal2--;
            }
            Iop.SetColor(nSrc, nType, nVal2);
            SetDispParat(nSrc);
        }
    }

    public void UISetVal(int nSrc, int nType, int nLevel) {
        if (nSrc > 3) {
            Log.d(TAG, "UISetVal error nSrc == " + nSrc);
        } else if (nType > 3) {
            Log.d(TAG, "UISetVal error nType == " + nType);
        } else {
            Iop.SetColor(nSrc, nType, nLevel);
            SetDispParat(nSrc);
        }
    }

    public int DealTask(int nParat) {
        if (this.nDelayTime > 0) {
            this.nDelayTime--;
            if (this.nDelayTime == 0) {
                Mcu.BklTurnInt(1);
                Log.d(TAG, "BklTurnInt end ");
            }
        }
        if (!(this.nSrcMode == this.nSetData || this.nSetData == 255)) {
            if (this.nSrcMode != 255) {
                SetSrcMute(10);
            }
            this.nSrcMode = this.nSetData;
            SetDispParat(this.nSrcMode);
            Log.d(TAG, "SetDisp == " + this.nSrcMode);
        }
        if (nParat == 2) {
            return 255;
        }
        return 1;
    }
}
