package com.ts.can;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.TextView;
import com.ts.can.CanBaseCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;

public abstract class CanRelativeCarInfoView extends CanBaseCarInfoView implements View.OnTouchListener {
    public CanRelativeCarInfoView(Activity activity) {
        super(activity, CanBaseCarInfoView.Type.RELATIVE);
        InitUI();
    }

    public void doOnStart() {
    }

    public void doOnResume() {
    }

    public void doOnPause() {
    }

    public void doOnFinish() {
    }

    public CustomImgView addImage(int x, int y, int w, int h, int resId, boolean isBackground) {
        CustomImgView v = getRelativeManager().AddImage(x, y, w, h);
        if (resId != 0) {
            if (isBackground) {
                v.setBackgroundResource(resId);
            } else {
                v.setImageResource(resId);
            }
        }
        return v;
    }

    public CustomImgView addImage(int x, int y, int w, int h, int resId) {
        return addImage(x, y, w, h, resId, false);
    }

    public CustomImgView addImage(int x, int y, int w, int h) {
        return addImage(x, y, w, h, 0);
    }

    public CustomImgView addImage(int x, int y, int resId) {
        return addImage(x, y, -2, -2, resId);
    }

    public CustomImgView addImage(int x, int y) {
        return addImage(x, y, -2, -2);
    }

    public CustomImgView addImageState(int x, int y, int normal, int selected) {
        CustomImgView v = getRelativeManager().AddImage(x, y);
        v.setImageDrawable(getStateListDrawable(normal, selected, selected));
        return v;
    }

    public ParamButton addButton(int x, int y, int w, int h) {
        return getRelativeManager().AddButton(x, y, w, h);
    }

    public ParamButton addButton(int x, int y) {
        return addButton(x, y, -2, -2);
    }

    public ParamButton addButtonState(int x, int y, int normal, int pressed, int selected) {
        ParamButton v = getRelativeManager().AddButton(x, y);
        v.setStateDrawable(normal, pressed, selected);
        return v;
    }

    public ParamButton addButtonState(int x, int y, int normal, int pressed) {
        ParamButton v = getRelativeManager().AddButton(x, y);
        v.setStateDrawable(normal, pressed, pressed);
        return v;
    }

    public TextView addText(int x, int y, int w, int h, String text) {
        TextView v = getRelativeManager().AddText(x, y, w, h);
        if (text != null) {
            v.setText(text);
        }
        return v;
    }

    public TextView addText(int x, int y, int w, int h, int textId) {
        return addText(x, y, w, h, textId != 0 ? getActivity().getString(textId) : TXZResourceManager.STYLE_DEFAULT);
    }

    public TextView addText(int x, int y, int w, int h) {
        return addText(x, y, w, h, 0);
    }

    public TextView addText(int x, int y, String text) {
        return addText(x, y, -2, -2, text);
    }

    public TextView addText(int x, int y, int textId) {
        return addText(x, y, -2, -2, textId);
    }

    public CanRelativeCarInfoView setTextStyle(TextView v, int textId, int textColor, int textSize, int gravity) {
        if (textId != 0) {
            v.setText(textId);
        }
        if (textColor != 0) {
            v.setTextColor(textColor);
        }
        if (textSize != 0) {
            v.setTextSize(0, ((float) textSize) * 1.5f);
        }
        if (gravity != 0) {
            v.setGravity(gravity);
        }
        return this;
    }

    public CanRelativeCarInfoView setTextStyle(TextView v, int textColor, int textSize, int gravity) {
        return setTextStyle(v, 0, textColor, textSize, gravity);
    }

    public CanRelativeCarInfoView setTextStyle(TextView v, int textColor, int textSize) {
        return setTextStyle(v, 0, textColor, textSize, 0);
    }

    public CanRelativeCarInfoView setTextColorState(TextView v, int normal, int pressed) {
        v.setTextColor(getColorStateList(normal, pressed, pressed));
        return this;
    }

    public CanRelativeCarInfoView setIdClickListener(View v, int id) {
        v.setTag(Integer.valueOf(id));
        v.setClickable(true);
        v.setOnClickListener(this);
        return this;
    }

    public CanRelativeCarInfoView setIdTouchListener(View v, int id) {
        v.setTag(Integer.valueOf(id));
        v.setOnTouchListener(this);
        return this;
    }

    public CanRelativeCarInfoView setShowGone(View v, boolean show) {
        v.setVisibility(show ? 0 : 8);
        return this;
    }

    public CanRelativeCarInfoView setShowGone(View v, int show) {
        return setShowGone(v, i2b(show));
    }

    public CanRelativeCarInfoView setBackgroundResource(int resId) {
        getView().setBackgroundResource(resId);
        return this;
    }

    public CanRelativeCarInfoView setStateDrawable(View v, int normal, int pressed, int selected) {
        if (v instanceof ParamButton) {
            ((ParamButton) v).setStateDrawable(normal, pressed, selected);
        } else if (v instanceof CustomImgView) {
            ((CustomImgView) v).setStateDrawable(normal, pressed, selected);
        } else if (v instanceof TextView) {
            ((TextView) v).setTextColor(getColorStateList(normal, pressed, selected));
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public View getView() {
        return getRelativeManager().GetLayout();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public StateListDrawable getStateListDrawable(int normal, int pressed, int selected) {
        StateListDrawable stateDrawable = new StateListDrawable();
        Drawable selectedDrawable = getActivity().getDrawable(selected);
        Drawable pressedDrawable = getActivity().getDrawable(pressed);
        Drawable normalDrawable = getActivity().getDrawable(normal);
        stateDrawable.addState(new int[]{16842913}, selectedDrawable);
        stateDrawable.addState(new int[]{16842919}, pressedDrawable);
        stateDrawable.addState(new int[0], normalDrawable);
        return stateDrawable;
    }

    /* access modifiers changed from: protected */
    public ColorStateList getColorStateList(int normal, int pressed, int selected) {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842913}, new int[0]}, new int[]{pressed, selected, normal});
    }
}
