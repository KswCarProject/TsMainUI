package com.mediatek.galleryfeature.pq.filter;

public class FilterSkinToneS extends Filter {
    public String getCurrentValue() {
        return "Skin tone(Sat):  " + super.getCurrentValue();
    }

    public void init() {
        this.mDefaultIndex = nativeGetSkinToneSIndex();
        this.mCurrentIndex = this.mDefaultIndex;
        this.mRange = nativeGetSkinToneSRange();
    }

    public void setIndex(int index) {
        nativeSetSkinToneSIndex(index);
    }

    public String getSeekbarProgressValue() {
        return Integer.toString(this.mCurrentIndex);
    }
}
