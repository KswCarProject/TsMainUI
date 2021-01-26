package com.mediatek.galleryfeature.pq.filter;

public class FilterGetYAxis extends Filter {
    public String getCurrentValue() {
        return "0";
    }

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
    }

    public void setIndex(int index) {
        this.mRange = nativeGetYAxisRange();
        this.mDefaultIndex = nativeGetYAxisIndex();
        this.mCurrentIndex = this.mDefaultIndex;
    }
}
