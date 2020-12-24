package com.ts.canview;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.ParamButton;

public class RelativeLayoutContainer {
    private RelativeLayout mContainer;
    private Context mContext;

    public RelativeLayoutContainer(Activity activity) {
        this.mContext = activity;
        this.mContainer = (RelativeLayout) activity.findViewById(R.id.can_comm_layout);
    }

    public RelativeLayout getContainer() {
        return this.mContainer;
    }

    public RelativeLayoutContainer setBackgroundResource(int resId) {
        this.mContainer.setBackgroundResource(resId);
        return this;
    }

    public RelativeLayoutContainer setBackgroundColor(int color) {
        this.mContainer.setBackgroundColor(color);
        return this;
    }

    public RelativeLayoutContainer setIdClickListener(View view, int id, View.OnClickListener listener) {
        view.setClickable(true);
        view.setTag(Integer.valueOf(id));
        view.setOnClickListener(listener);
        return this;
    }

    public RelativeLayoutContainer setIdTouchListener(View view, int id, View.OnTouchListener listener) {
        view.setTag(Integer.valueOf(id));
        view.setOnTouchListener(listener);
        return this;
    }

    public RelativeLayoutContainer setTextStyle1(TextView view, int textId, int color, int size) {
        return setTextStyle(view, textId, 0, color, size);
    }

    public RelativeLayoutContainer setTextStyle2(TextView view, int textId, int gravity, int size) {
        return setTextStyle(view, textId, gravity, 0, size);
    }

    public RelativeLayoutContainer setTextStyle3(TextView view, int textId, int gravity, int color) {
        return setTextStyle(view, textId, gravity, color, 0);
    }

    public RelativeLayoutContainer setTextStyle(TextView view, int textId, int gravity, int color, int size) {
        return setTextStyle(view, textId > 0 ? this.mContext.getResources().getString(textId) : "", gravity, color, size);
    }

    public RelativeLayoutContainer setTextStyle(TextView view, String text, int gravity, int color, int size) {
        if (text != null) {
            view.setText(text);
        }
        if (gravity != 0) {
            view.setGravity(gravity);
        }
        if (color != 0) {
            view.setTextColor(color);
        }
        if (size != 0) {
            view.setTextSize(0, ((float) size) * 1.5f);
        }
        return this;
    }

    public RelativeLayoutContainer setDrawableUpDnSel(View view, int normal, int preAndSel) {
        return setDrawableStateList(view, normal, preAndSel, preAndSel);
    }

    public RelativeLayoutContainer setDrawableUpDn(View view, int normal, int pressed) {
        return setDrawableStateList(view, normal, pressed, -1);
    }

    public RelativeLayoutContainer setDrawableUpSel(View view, int normal, int selected) {
        return setDrawableStateList(view, normal, -1, selected);
    }

    public RelativeLayoutContainer setDrawableStateList(View view, int normal, int pressed, int selected) {
        Drawable selectedDrawable = null;
        Resources res = this.mContext.getResources();
        StateListDrawable drawable = new StateListDrawable();
        Drawable normalDrawable = normal <= 0 ? null : res.getDrawable(normal);
        Drawable pressedDrawable = pressed <= 0 ? null : res.getDrawable(pressed);
        if (selected > 0) {
            selectedDrawable = res.getDrawable(selected);
        }
        drawable.addState(new int[]{16842919}, pressedDrawable);
        drawable.addState(new int[]{16842913}, selectedDrawable);
        drawable.addState(new int[0], normalDrawable);
        view.setBackground(drawable);
        return this;
    }

    public RelativeLayoutContainer setColorUpDnSelList(TextView view, int normal, int preAndSel) {
        return setColorStateList(view, normal, preAndSel, preAndSel);
    }

    public RelativeLayoutContainer setColorUpDnList(TextView view, int normal, int pressed) {
        return setColorStateList(view, normal, pressed, normal);
    }

    public RelativeLayoutContainer setColorUpSelList(TextView view, int normal, int selected) {
        return setColorStateList(view, normal, normal, selected);
    }

    public RelativeLayoutContainer setColorStateList(TextView view, int normal, int pressed, int selected) {
        int[][] states = {new int[]{16842919}, new int[]{16842913}, new int[0]};
        view.setTextColor(new ColorStateList(states, new int[]{pressed, selected, normal}));
        return this;
    }

    public ParamButton addButton(int x, int y, int w, int h) {
        ParamButton button = new ParamButton(this.mContext);
        button.setLayoutParams(createLayoutParam(x, y, w, h));
        this.mContainer.addView(button);
        return button;
    }

    public ParamButton addButton(int x, int y) {
        return addButton(x, y, -2, -2);
    }

    public ImageView addImage(int x, int y, int w, int h, int resId) {
        ImageView image = new ImageView(this.mContext);
        image.setLayoutParams(createLayoutParam(x, y, w, h));
        if (resId > 0) {
            image.setImageResource(resId);
        }
        this.mContainer.addView(image);
        return image;
    }

    public ImageView addImage(int x, int y, int resId) {
        return addImage(x, y, -2, -2, resId);
    }

    public TextView addText(int x, int y, int w, int h) {
        TextView text = new TextView(this.mContext);
        text.setLayoutParams(createLayoutParam(x, y, w, h));
        this.mContainer.addView(text);
        return text;
    }

    public TextView addText(int x, int y) {
        return addText(x, y, -2, -2);
    }

    private RelativeLayout.LayoutParams createLayoutParam(int x, int y, int w, int h) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(w, h);
        lp.leftMargin = x;
        lp.topMargin = y;
        return lp;
    }

    public View addView(View view, int x, int y) {
        return addView(view, x, y, -2, -2);
    }

    public View addView(View view, int x, int y, int w, int h) {
        view.setLayoutParams(createLayoutParam(x, y, w, h));
        this.mContainer.addView(view);
        return view;
    }
}
