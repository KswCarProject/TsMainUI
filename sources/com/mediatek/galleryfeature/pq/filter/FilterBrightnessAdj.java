package com.mediatek.galleryfeature.pq.filter;

public class FilterBrightnessAdj extends Filter {
    public String getCurrentValue() {
        return "Brightness:  " + super.getCurrentValue();
    }

    public void init() {
        this.mDefaultIndex = nativeGetBrightnessAdjIndex();
        this.mCurrentIndex = this.mDefaultIndex;
        this.mRange = nativeGetBrightnessAdjRange();
    }

    public void setIndex(int index) {
        nativeSetBrightnessAdjIndex(index);
    }
}
