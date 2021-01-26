package com.mediatek.galleryfeature.pq.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Filter implements FilterInterface {
    public static final String CURRRENT_INDEX = "textViewCurrentIndex";
    public static final String MIN_VALUE = "textViewMinValue";
    public static final String RANGE = "textViewMaxValue";
    public static final String SEEKBAR_PROGRESS = "seekbarProgress";
    public static final String TAG = "MtkGallery2/Filter";
    protected int mCurrentIndex;
    protected int mDefaultIndex;
    protected int mRange;
    public Map<String, String> map = new HashMap();

    public native int nativeGetBrightnessAdjIndex();

    public native int nativeGetBrightnessAdjRange();

    public native int nativeGetContrastAdjIndex();

    public native int nativeGetContrastAdjRange();

    public native int nativeGetGrassToneHIndex();

    public native int nativeGetGrassToneHRange();

    public native int nativeGetGrassToneSIndex();

    public native int nativeGetGrassToneSRange();

    public native int nativeGetHueAdjIndex();

    public native int nativeGetHueAdjRange();

    public native int nativeGetSatAdjIndex();

    public native int nativeGetSatAdjRange();

    public native int nativeGetSharpAdjIndex();

    public native int nativeGetSharpAdjRange();

    public native int nativeGetSkinToneHIndex();

    public native int nativeGetSkinToneHRange();

    public native int nativeGetSkinToneSIndex();

    public native int nativeGetSkinToneSRange();

    public native int nativeGetSkyToneHIndex();

    public native int nativeGetSkyToneHRange();

    public native int nativeGetSkyToneSIndex();

    public native int nativeGetSkyToneSRange();

    public native int nativeGetXAxisIndex();

    public native int nativeGetXAxisRange();

    public native int nativeGetYAxisIndex();

    public native int nativeGetYAxisRange();

    public native boolean nativeSetBrightnessAdjIndex(int i);

    public native boolean nativeSetContrastAdjIndex(int i);

    public native boolean nativeSetGrassToneHIndex(int i);

    public native boolean nativeSetGrassToneSIndex(int i);

    public native boolean nativeSetHueAdjIndex(int i);

    public native boolean nativeSetSatAdjIndex(int i);

    public native boolean nativeSetSharpAdjIndex(int i);

    public native boolean nativeSetSkinToneHIndex(int i);

    public native boolean nativeSetSkinToneSIndex(int i);

    public native boolean nativeSetSkyToneHIndex(int i);

    public native boolean nativeSetSkyToneSIndex(int i);

    public native boolean nativeSetXAxisIndex(int i);

    public native boolean nativeSetYAxisIndex(int i);

    static {
        System.loadLibrary("PQjni");
    }

    public Map<String, String> getDate() {
        return this.map;
    }

    public Filter() {
        init();
        this.map.put(MIN_VALUE, getMinValue());
        this.map.put(RANGE, getMaxValue());
        this.map.put(CURRRENT_INDEX, getCurrentValue());
        this.map.put(SEEKBAR_PROGRESS, getSeekbarProgressValue());
    }

    public boolean addToList(ArrayList<FilterInterface> list) {
        if (Integer.parseInt(getMaxValue()) <= 0) {
            return false;
        }
        list.add(this);
        return true;
    }

    public ArrayList<FilterInterface> getFilterList() {
        ArrayList<FilterInterface> list = new ArrayList<>();
        new FilterSharpAdj().addToList(list);
        new FilterSatAdj().addToList(list);
        new FilterContrastAdj().addToList(list);
        new FilterBrightnessAdj().addToList(list);
        new FilterHueAdj().addToList(list);
        new FilterSkinToneH().addToList(list);
        new FilterSkinToneS().addToList(list);
        new FilterSkyToneH().addToList(list);
        new FilterSkyToneS().addToList(list);
        new FilterGetXAxis().addToList(list);
        new FilterGetYAxis().addToList(list);
        new FilterGrassToneH().addToList(list);
        new FilterGrassToneS().addToList(list);
        return list;
    }

    public String getCurrentValue() {
        return Integer.toString(this.mCurrentIndex);
    }

    public String getMaxValue() {
        return Integer.toString(this.mRange - 1);
    }

    public String getMinValue() {
        return "0";
    }

    public String getSeekbarProgressValue() {
        return Integer.toString(this.mCurrentIndex);
    }

    public void init() {
    }

    public void setCurrentIndex(int progress) {
        this.mCurrentIndex = progress;
    }

    public void onDestroy() {
    }

    public void onResume() {
    }

    public int getCurrentIndex() {
        return this.mCurrentIndex;
    }

    public int getRange() {
        return this.mRange;
    }

    public int getDefaultIndex() {
        return this.mDefaultIndex;
    }

    public void setIndex(int index) {
    }
}
