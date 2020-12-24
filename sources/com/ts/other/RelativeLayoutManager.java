package com.ts.other;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RelativeLayoutManager {
    protected static final String TAG = "RelativeLayoutManager";
    protected Context mContext;
    protected RelativeLayout mLayout;

    public RelativeLayoutManager(Activity activity, int layoutId) {
        Init(activity, layoutId);
    }

    public RelativeLayoutManager(RelativeLayout rl) {
        this.mLayout = rl;
        this.mContext = rl.getContext();
    }

    public void Init(Activity activity, int layoutId) {
        this.mContext = activity;
        this.mLayout = (RelativeLayout) activity.findViewById(layoutId);
    }

    public void AddView(View view, int x, int y, int w, int h) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.mLayout.addView(view);
    }

    public void AddViewWrapContent(View view, int x, int y) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(-2, -2);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.mLayout.addView(view);
    }

    public ParamButton AddButton(int x, int y, int w, int h) {
        ParamButton btn = new ParamButton(this.mContext);
        AddView(btn, x, y, w, h);
        return btn;
    }

    public ParamButton AddButton(int x, int y) {
        ParamButton btn = new ParamButton(this.mContext);
        AddViewWrapContent(btn, x, y);
        return btn;
    }

    public CustomImgView AddImage(int x, int y, int w, int h) {
        CustomImgView img = new CustomImgView(this.mContext);
        AddView(img, x, y, w, h);
        return img;
    }

    public CustomImgView AddImage(int x, int y) {
        CustomImgView img = new CustomImgView(this.mContext);
        AddViewWrapContent(img, x, y);
        return img;
    }

    public CustomImgView AddImage(int x, int y, int res) {
        CustomImgView img = new CustomImgView(this.mContext);
        if (res != 0) {
            img.setImageResource(res);
        }
        AddViewWrapContent(img, x, y);
        return img;
    }

    public CustomImgView AddImageEx(int x, int y, int w, int h, int resId) {
        CustomImgView img = new CustomImgView(this.mContext);
        if (resId != 0) {
            img.setImageResource(resId);
        }
        AddView(img, x, y, w, h);
        return img;
    }

    public TextView AddText(int x, int y, int w, int h) {
        TextView text = new TextView(this.mContext);
        AddView(text, x, y, w, h);
        return text;
    }

    public TextView AddText(int x, int y) {
        TextView text = new TextView(this.mContext);
        AddViewWrapContent(text, x, y);
        return text;
    }

    public CustomTextView AddCusText(int x, int y, int w, int h) {
        CustomTextView text = new CustomTextView(this.mContext);
        AddView(text, x, y, w, h);
        return text;
    }

    public void AddCanItem(View v, int line) {
        AddView(v, 0, (line * 85) + 10, -2, 85);
    }

    public void RemoveAllViews() {
        this.mLayout.removeAllViews();
    }

    public RelativeLayout GetLayout() {
        return this.mLayout;
    }
}
