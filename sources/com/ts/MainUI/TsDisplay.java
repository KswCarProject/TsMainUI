package com.ts.MainUI;

import android.util.Log;
import com.mediatek.galleryfeature.pq.filter.Filter;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class TsDisplay {
    public static final int CAM_MAX = 70;
    private static String TAG = "TsDisplay";
    private static TsDisplay mDisplay = null;
    Filter MyFilter = new Filter();
    private int[] mTcon = new int[40];
    int nBrightness = 0;
    int nContanst = 0;
    private int nDelayTime = 0;
    int nHue;
    private int nSetData = 255;
    public int nSetTcon = 1;
    private int nSrcMode = 255;
    int nSrcxx = 255;
    int nSta = 0;
    int nsharpness = 0;

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
        this.nSrcMode = 255;
        this.nDelayTime = 0;
        this.nSetData = 255;
        FtSet.GetTconVal(this.mTcon);
        this.nBrightness = CheckVal(this.mTcon[0]) - 1;
        if (this.nBrightness < 0) {
            this.nBrightness = 0;
        }
        this.nContanst = CheckVal(this.mTcon[1]) + 1;
        if (this.nContanst > 9) {
            this.nContanst = 9;
        }
        this.nHue = (CheckVal(this.mTcon[2]) + 1) * 25;
        this.nSta = CheckVal(this.mTcon[3]) + 2;
        if (this.nSta > 9) {
            this.nSta = 9;
        }
        this.nsharpness = 2;
        if (this.nsharpness > 9) {
            this.nsharpness = 9;
        }
        Log.d(TAG, "nBrightness=" + this.nBrightness);
        Log.d(TAG, "nContanst=" + this.nContanst);
        Log.d(TAG, "nsharpness=" + this.nsharpness);
        Log.d(TAG, "nHue=" + this.nHue);
        Log.d(TAG, "nSta=" + this.nSta);
        this.MyFilter.nativeSetBrightnessAdjIndex(this.nBrightness);
        this.MyFilter.nativeSetContrastAdjIndex(this.nContanst);
        this.MyFilter.nativeSetHueAdjIndex(this.nHue);
        this.MyFilter.nativeSetSatAdjIndex(this.nSta);
        this.MyFilter.nativeSetSharpAdjIndex(this.nsharpness);
        this.MyFilter.nativeSetGrassToneHIndex(9);
        this.MyFilter.nativeSetGrassToneSIndex(9);
        this.MyFilter.nativeSetSkinToneHIndex(4);
        this.MyFilter.nativeSetSkinToneSIndex(9);
        this.MyFilter.nativeSetSkyToneHIndex(9);
        this.MyFilter.nativeSetSkyToneSIndex(9);
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

    public void SetDispGarma(int nSrc) {
        Log.i(TAG, "SetDispGarma" + nSrc);
        switch (nSrc) {
            case 0:
            case 1:
            case 2:
            case 3:
                Iop.SetGamma(1);
                return;
            default:
                Iop.SetGamma(0);
                return;
        }
    }

    public void SetDispParat(int nSrc) {
        Log.d(TAG, "SetDispParat ==" + nSrc);
        if (this.nSrcxx != nSrc) {
            this.nSrcxx = nSrc;
            SetDispGarma(nSrc);
        }
        if (nSrc < 0) {
            this.MyFilter.nativeSetBrightnessAdjIndex(this.nBrightness);
            this.MyFilter.nativeSetContrastAdjIndex(this.nContanst);
            this.MyFilter.nativeSetHueAdjIndex(this.nHue);
            this.MyFilter.nativeSetSatAdjIndex(this.nSta);
            this.MyFilter.nativeSetSharpAdjIndex(this.nsharpness);
        } else if (FtSet.GetTconAdj() == 1 || nSrc == 0) {
            this.MyFilter.nativeSetBrightnessAdjIndex(CheckVal(Iop.GetColor(nSrc, 0)));
            this.MyFilter.nativeSetContrastAdjIndex(CheckVal(Iop.GetColor(nSrc, 1)));
            this.MyFilter.nativeSetSatAdjIndex(CheckVal(Iop.GetColor(nSrc, 3)));
            this.MyFilter.nativeSetHueAdjIndex((CheckVal(Iop.GetColor(nSrc, 2)) + 1) * 25);
            Log.d(TAG, "Iop.GetColor(nSrc, Iop.CVBS_BRIGHTNESS)" + Iop.GetColor(nSrc, 0));
            Log.d(TAG, "Iop.GetColor(nSrc, Iop.CVBS_CONTRAST)" + Iop.GetColor(nSrc, 1));
            Log.d(TAG, "Iop.GetColor(nSrc, Iop.CVBS_SATURATION)" + Iop.GetColor(nSrc, 3));
            Log.d(TAG, "Iop.GetColor(nSrc, Iop.CVBS_HUE)" + Iop.GetColor(nSrc, 2));
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
        if (nSrc > 3) {
            Log.d(TAG, "UIValStep error nSrc == " + nSrc);
        } else if (nType > 3) {
            Log.d(TAG, "UIValStep error nType == " + nType);
        } else {
            int nVal = Iop.GetColor(nSrc, nType);
            if (nStep == 1) {
                Iop.SetColor(nSrc, nType, nVal + 10);
                SetDispParat(nSrc);
                return;
            }
            Iop.SetColor(nSrc, nType, nVal - 10);
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
