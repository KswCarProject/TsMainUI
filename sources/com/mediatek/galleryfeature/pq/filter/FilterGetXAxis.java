package com.mediatek.galleryfeature.pq.filter;

public class FilterGetXAxis extends Filter {
    public String getMaxValue() {
        return "-1";
    }

    public String getMinValue() {
        return "0";
    }

    public String getSeekbarProgressValue() {
        return "0";
    }

    public void init() {
        this.mRange = nativeGetXAxisRange();
        this.mDefaultIndex = nativeGetXAxisIndex();
        this.mCurrentIndex = this.mDefaultIndex;
    }

    public void setIndex(int index) {
        nativeSetXAxisIndex(index);
    }
}
