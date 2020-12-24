package com.ts.can.zotye;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.widget.RelativeLayout;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanZtY100Item {
    public static final String TAG = "CanZtY100Item";
    protected Context mContext;
    protected RelativeLayout mLayout;
    protected RelativeLayoutManager mManager = new RelativeLayoutManager(this.mLayout);
    protected CustomTextView mTvTitle = this.mManager.AddCusText(40, 20, 290, 40);
    protected CustomTextView mTvVal;

    public CanZtY100Item(Context context) {
        this.mContext = context;
        this.mLayout = new RelativeLayout(context);
        this.mLayout.setClickable(true);
        this.mLayout.setFocusable(true);
        Log.d(TAG, TAG);
        this.mTvTitle.SetPixelSize(32);
        this.mTvTitle.setGravity(19);
        this.mTvVal = this.mManager.AddCusText(KeyDef.RKEY_RDS_TA, 20, 220, 40);
        this.mTvVal.SetPixelSize(32);
        this.mTvVal.setGravity(19);
    }

    public void setStateDrawable(int normal, int pressed, int selected) {
        Drawable iSelected = null;
        Resources res = this.mContext.getResources();
        StateListDrawable sd = new StateListDrawable();
        Drawable iNormal = normal == -1 ? null : res.getDrawable(normal);
        Drawable iPressed = pressed == -1 ? null : res.getDrawable(pressed);
        if (selected != -1) {
            iSelected = res.getDrawable(selected);
        }
        sd.addState(new int[]{16842913}, iSelected);
        sd.addState(new int[]{16842919}, iPressed);
        sd.addState(new int[]{16842910}, iNormal);
        sd.addState(new int[0], iNormal);
        this.mLayout.setBackground(sd);
    }

    public RelativeLayout GetView() {
        return this.mLayout;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddText(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public void SetText(String strText) {
        this.mTvTitle.setText(strText);
    }

    /* access modifiers changed from: protected */
    public void SetVal(String strVal) {
        this.mTvVal.setText(strVal);
    }

    /* access modifiers changed from: protected */
    public CustomTextView GetTitleTv() {
        return this.mTvTitle;
    }

    /* access modifiers changed from: protected */
    public CustomTextView GetValTv() {
        return this.mTvVal;
    }
}
