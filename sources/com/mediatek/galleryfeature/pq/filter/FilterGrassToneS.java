package com.mediatek.galleryfeature.pq.filter;

public class FilterGrassToneS extends Filter {
    public String getCurrentValue() {
        return "Grass tone(Sat):  " + super.getCurrentValue();
    }

    public void init() {
        this.mDefaultIndex = nativeGetGrassToneSIndex();
        this.mCurrentIndex = this.mDefaultIndex;
        this.mRange = nativeGetGrassToneSRange();
    }

    public void setIndex(int index) {
        nativeSetGrassToneSIndex(index);
    }
}
