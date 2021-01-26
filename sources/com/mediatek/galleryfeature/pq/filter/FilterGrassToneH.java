package com.mediatek.galleryfeature.pq.filter;

public class FilterGrassToneH extends Filter {
    public String getCurrentValue() {
        return "Grass tone(Hue):  " + (((this.mCurrentIndex + (this.mRange / 2)) + 1) - this.mRange);
    }

    public String getMaxValue() {
        return Integer.toString((this.mRange - 1) / 2);
    }

    public String getMinValue() {
        return Integer.toString(((this.mRange / 2) + 1) - this.mRange);
    }

    public void init() {
        this.mDefaultIndex = nativeGetGrassToneHIndex();
        this.mCurrentIndex = this.mDefaultIndex;
        this.mRange = nativeGetGrassToneHRange();
    }

    public void setIndex(int index) {
        nativeSetGrassToneHIndex(index);
    }
}
