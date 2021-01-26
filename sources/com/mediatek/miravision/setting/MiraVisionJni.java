package com.mediatek.miravision.setting;

import android.content.Context;
import android.util.Log;
import com.mediatek.pq.PictureQuality;

public class MiraVisionJni {
    public static final int AAL_FUNC_CABC = 2;
    public static final int AAL_FUNC_DRE = 4;
    private static final String AAL_FUNC_PROPERTY_NAME = "persist.vendor.sys.aal.function";
    public static final int PIC_MODE_STANDARD = 0;
    public static final int PIC_MODE_USER_DEF = 2;
    public static final int PIC_MODE_VIVID = 1;
    public static final String TAG = "MiraVisionJni";
    static boolean sLibStatus;

    private static native void nativeSetAALFunction(int i);

    static {
        sLibStatus = true;
        try {
            Log.v(TAG, "loadLibrary");
            System.loadLibrary("MiraVision_jni");
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, "UnsatisfiedLinkError");
            sLibStatus = false;
        }
    }

    public static class Range {
        public int defaultValue;
        public int max;
        public int min;

        public Range() {
            set(0, 0, 0);
        }

        public void set(int min2, int max2, int defaultValue2) {
            this.min = min2;
            this.max = max2;
            this.defaultValue = defaultValue2;
        }
    }

    private MiraVisionJni() {
    }

    public static boolean getLibStatus() {
        return sLibStatus;
    }

    public static boolean nativeEnablePQColor(int isEnable) {
        Log.v(TAG, "nativeEnablePQColor");
        return PictureQuality.enableColor(isEnable);
    }

    public static int nativeGetPictureMode() {
        Log.v(TAG, "nativeGetPictureMode");
        return PictureQuality.getPictureMode();
    }

    public static boolean nativeSetPictureMode(int mode) {
        Log.v(TAG, "nativeSetPictureMode");
        return PictureQuality.setPictureMode(mode);
    }

    public static boolean nativeSetPQColorRegion(int isEnable, int startX, int startY, int endX, int endY) {
        Log.v(TAG, "nativeSetPQColorRegion");
        return PictureQuality.setColorRegion(isEnable, startX, startY, endX, endY);
    }

    public static Range getContrastIndexRange() {
        Range r = new Range();
        Log.v(TAG, "getContrastIndexRange");
        PictureQuality.Range PQrange = PictureQuality.getContrastIndexRange();
        r.set(PQrange.min, PQrange.max, PQrange.defaultValue);
        return r;
    }

    public static int getContrastIndex() {
        return PictureQuality.getContrastIndex();
    }

    public static void setContrastIndex(int index) {
        PictureQuality.setContrastIndex(index);
    }

    public static Range getSaturationIndexRange() {
        Range r = new Range();
        Log.v(TAG, "getSaturationIndexRange");
        PictureQuality.Range PQrange = PictureQuality.getSaturationIndexRange();
        r.set(PQrange.min, PQrange.max, PQrange.defaultValue);
        return r;
    }

    public static int getSaturationIndex() {
        return PictureQuality.getSaturationIndex();
    }

    public static void setSaturationIndex(int index) {
        PictureQuality.setSaturationIndex(index);
    }

    public static Range getPicBrightnessIndexRange() {
        Range r = new Range();
        Log.v(TAG, "getPicBrightnessIndexRange");
        PictureQuality.Range PQrange = PictureQuality.getPicBrightnessIndexRange();
        r.set(PQrange.min, PQrange.max, PQrange.defaultValue);
        return r;
    }

    public static int getPicBrightnessIndex() {
        return PictureQuality.getPicBrightnessIndex();
    }

    public static void setPicBrightnessIndex(int index) {
        PictureQuality.setPicBrightnessIndex(index);
    }

    public static boolean nativeSetTuningMode(int mode) {
        return true;
    }

    public static Range getSharpnessIndexRange() {
        Range r = new Range();
        Log.v(TAG, "getSharpnessIndexRange");
        PictureQuality.Range PQrange = PictureQuality.getSharpnessIndexRange();
        r.set(PQrange.min, PQrange.max, PQrange.defaultValue);
        return r;
    }

    public static int getSharpnessIndex() {
        return PictureQuality.getSharpnessIndex();
    }

    public static void setSharpnessIndex(int index) {
        PictureQuality.setSharpnessIndex(index);
    }

    public static Range getDynamicContrastIndexRange() {
        Range r = new Range();
        Log.v(TAG, "getDynamicContrastIndexRange");
        PictureQuality.Range PQrange = PictureQuality.getDynamicContrastIndexRange();
        r.set(PQrange.min, PQrange.max, PQrange.defaultValue);
        return r;
    }

    public static int getDynamicContrastIndex() {
        return PictureQuality.getDynamicContrastIndex();
    }

    public static void setDynamicContrastIndex(int index) {
        PictureQuality.setDynamicContrastIndex(index);
    }

    public static int getColorEffectIndex() {
        return PictureQuality.getColorEffectIndex();
    }

    public static void setColorEffectIndex(int index) {
        PictureQuality.setColorEffectIndex(index);
    }

    public static boolean nativeEnableODDemo(int isEnable) {
        if (isEnable == 2) {
            Log.v(TAG, "nativeEnableODDemo, query OD support!");
            if ((PictureQuality.getCapability() & 16) == 16) {
                return true;
            }
            return false;
        }
        Log.v(TAG, "nativeEnableODDemo..");
        PictureQuality.enableOD(isEnable);
        return true;
    }

    public static Range getBlueLightIndexRange() {
        Range r = new Range();
        Log.v(TAG, "getBlueLightIndexRange");
        PictureQuality.Range PQrange = PictureQuality.getBlueLightStrengthRange();
        r.set(PQrange.min, PQrange.max, PQrange.defaultValue);
        return r;
    }

    public static void setBlueLightIndex(int index) {
        PictureQuality.setBlueLightStrength(index);
    }

    public static int getBlueLightIndex() {
        return PictureQuality.getBlueLightStrength();
    }

    public static boolean enableBlueLight(boolean enable) {
        return PictureQuality.enableBlueLight(enable);
    }

    public static boolean isBlueLightEnabled() {
        return PictureQuality.isBlueLightEnabled();
    }

    public static Range getGammaIndexRange() {
        Range r = new Range();
        Log.v(TAG, "getGammaIndexRange");
        PictureQuality.Range PQrange = PictureQuality.getGammaIndexRange();
        r.set(PQrange.min, PQrange.max, PQrange.defaultValue);
        return r;
    }

    public static void setGammaIndex(int index) {
        PictureQuality.setGammaIndex(index);
    }

    public static int getGammaIndex() {
        return PictureQuality.getGammaIndex();
    }

    public static void setAALFunction(int func) {
        PictureQuality.setAALFunctionProperty(func);
    }

    public static int getAALFunction() {
        return 1;
    }

    public static int getDefaultAALFunction() {
        return 2;
    }

    public static Range getUserBrightnessRange() {
        Range r = new Range();
        r.set(0, 1, 0);
        return r;
    }

    public static void setUserBrightness(int level) {
    }

    public static void resetPQ(Context context) {
    }
}
