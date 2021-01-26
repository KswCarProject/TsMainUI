package com.ts.set.dsp;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    List<View> mList = new ArrayList();

    public ViewPagerAdapter(List<View> list) {
        this.mList = list;
    }

    public int getCount() {
        return this.mList.size();
    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(this.mList.get(position));
        return this.mList.get(position);
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public int getItemPosition(Object object) {
        if (this.mList.contains((View) object)) {
            return super.getItemPosition(object);
        }
        return -2;
    }
}
