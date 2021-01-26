package com.mediatek.galleryfeature.pq.filter;

public class FilterSkyToneS extends Filter {
    public String getCurrentValue() {
        return "Sky tone(Sat):  " + super.getCurrentValue();
    }

    public void init() {
        this.mRange = nativeGetSkyToneSRange();
        this.mDefaultIndex = nativeGetSkyToneSIndex();
        this.mCurrentIndex = this.mDefaultIndex;
    }

    public void setIndex(int index) {
        nativeSetSkyToneSIndex(index);
    }
}
