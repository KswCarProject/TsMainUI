package com.mediatek.galleryfeature.pq.filter;

import java.util.ArrayList;

public interface FilterInterface {
    int getCurrentIndex();

    String getCurrentValue();

    int getDefaultIndex();

    ArrayList<FilterInterface> getFilterList();

    String getMaxValue();

    String getMinValue();

    int getRange();

    String getSeekbarProgressValue();

    void init();

    void onDestroy();

    void onResume();

    void setCurrentIndex(int i);

    void setIndex(int i);
}
