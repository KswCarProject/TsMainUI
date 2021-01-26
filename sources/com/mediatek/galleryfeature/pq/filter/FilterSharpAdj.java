package com.mediatek.galleryfeature.pq.filter;

public class FilterSharpAdj extends Filter {
    public String getCurrentValue() {
        return "Sharpness:  " + super.getCurrentValue();
    }

    public void setIndex(int index) {
        nativeSetSharpAdjIndex(index);
    }

    public void init() {
        this.mDefaultIndex = nativeGetSharpAdjIndex();
        this.mCurrentIndex = this.mDefaultIndex;
        this.mRange = nativeGetSharpAdjRange();
    }
}
