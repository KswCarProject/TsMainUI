package com.ts.other;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class ParamTextView extends TextView {
    private Context mContext;
    private int mParam;
    private int mParam2;

    public ParamTextView(Context context) {
        super(context);
        this.mContext = context;
    }

    public ParamTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void setIntParam(int p) {
        this.mParam = p;
    }

    public int getIntParam() {
        return this.mParam;
    }

    public void setIntParam2(int p) {
        this.mParam2 = p;
    }

    public int getIntParam2() {
        return this.mParam2;
    }
}
