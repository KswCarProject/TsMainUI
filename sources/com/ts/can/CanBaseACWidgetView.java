package com.ts.can;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;

public abstract class CanBaseACWidgetView implements View.OnTouchListener {
    private Context mContext;
    private RelativeLayout mLayout;
    private RelativeLayoutManager mManager;

    public abstract void InitUI();

    public abstract void QueryData();

    /* access modifiers changed from: protected */
    public abstract void ResetData();

    protected CanBaseACWidgetView(RelativeLayout layout) {
        this.mContext = layout.getContext();
        this.mLayout = layout;
        this.mManager = new RelativeLayoutManager(layout);
    }

    public Context getContext() {
        return this.mContext;
    }

    public View getView() {
        return this.mLayout;
    }

    public RelativeLayoutManager getManager() {
        return this.mManager;
    }

    public void updateAC() {
        Can.updateAC();
        ResetData();
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddText(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(24);
        temp.setText(TXZResourceManager.STYLE_DEFAULT);
        temp.setTextColor(-1);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomImgView AddImage(int x, int y, int w, int h, int resId) {
        CustomImgView image = this.mManager.AddImage(x, y, w, h);
        if (resId != 0) {
            image.setImageResource(resId);
        }
        return image;
    }

    /* access modifiers changed from: protected */
    public CustomImgView AddImage(int x, int y, int w, int h, int upId, int dnId) {
        CustomImgView image = this.mManager.AddImage(x, y, w, h);
        image.setStateDrawable(upId, dnId);
        return image;
    }

    /* access modifiers changed from: protected */
    public boolean i2b(int value) {
        return value > 0;
    }

    public void doOnRun() {
    }

    public void doOnAttachedToWindow() {
    }

    public void doOnDetachedFromWindow() {
    }
}
