package com.mediatek.galleryfeature.pq.filter;

public class FilterSkinToneH extends Filter {
    public String getCurrentValue() {
        return "Skin tone(Hue):  " + ((((this.mRange / 2) + 1) - this.mRange) + this.mCurrentIndex);
    }

    public String getMaxValue() {
        return Integer.toString((this.mRange - 1) / 2);
    }

    public String getMinValue() {
        return Integer.toString(((this.mRange / 2) + 1) - this.mRange);
    }

    public void setIndex(int index) {
        nativeSetSkinToneHIndex(index);
    }

    public void init() {
        this.mDefaultIndex = nativeGetSkinToneHIndex();
        this.mCurrentIndex = this.mDefaultIndex;
        this.mRange = nativeGetSkinToneHRange();
    }
}
