package com.mediatek.galleryfeature.pq.filter;

public class FilterContrastAdj extends Filter {
    public String getCurrentValue() {
        return "Contrast:  " + super.getCurrentValue();
    }

    public void init() {
        this.mDefaultIndex = nativeGetContrastAdjIndex();
        this.mCurrentIndex = this.mDefaultIndex;
        this.mRange = nativeGetContrastAdjRange();
    }

    public void setIndex(int index) {
        nativeSetContrastAdjIndex(index);
    }
}
