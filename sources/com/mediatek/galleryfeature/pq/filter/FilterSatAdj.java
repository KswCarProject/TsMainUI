package com.mediatek.galleryfeature.pq.filter;

public class FilterSatAdj extends Filter {
    public String getCurrentValue() {
        return "GlobalSat:  " + super.getCurrentValue();
    }

    public void setIndex(int index) {
        nativeSetSatAdjIndex(index);
    }

    public void init() {
        this.mDefaultIndex = nativeGetSatAdjIndex();
        this.mCurrentIndex = this.mDefaultIndex;
        this.mRange = nativeGetSatAdjRange();
    }
}
