package com.mediatek.galleryfeature.pq.filter;

public class FilterHueAdj extends Filter {
    public String getCurrentValue() {
        return "0";
    }

    public String getMaxValue() {
        return "0";
    }

    public String getMinValue() {
        return "0";
    }

    public String getSeekbarProgressValue() {
        return "0";
    }

    public void init() {
        this.mRange = nativeGetHueAdjRange();
        this.mDefaultIndex = nativeGetHueAdjIndex();
        this.mCurrentIndex = this.mDefaultIndex;
    }

    public void setCurrentIndex(int progress) {
    }

    public void setIndex(int index) {
        nativeSetHueAdjIndex(0);
    }
}
