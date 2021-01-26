package com.mediatek.galleryfeature.pq.filter;

public class FilterSkyToneH extends Filter {
    public String getCurrentValue() {
        return "Sky tone(Hue):  " + Integer.toString((((this.mRange / 2) + 1) - this.mRange) + this.mCurrentIndex);
    }

    public String getMaxValue() {
        return Integer.toString((this.mRange - 1) / 2);
    }

    public String getMinValue() {
        return Integer.toString(((this.mRange / 2) + 1) - this.mRange);
    }

    public void init() {
        this.mDefaultIndex = nativeGetSkyToneHIndex();
        this.mCurrentIndex = this.mDefaultIndex;
        this.mRange = nativeGetSkyToneHRange();
    }

    public void setIndex(int index) {
        nativeSetSkyToneHIndex(index);
    }
}
