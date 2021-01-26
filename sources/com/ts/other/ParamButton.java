package com.ts.other;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class ParamButton extends Button {
    private static Context mFsContext;
    private static ViewGroup mFsLayout;
    private static int mFsYDt;
    private Context mContext;
    private int mFocusResId = 0;
    private int mNormalResId = 0;
    private int mParam;
    private int mParam2;
    private int mPressResId = 0;
    private int mSelectResId = 0;
    private String mThemeName;

    public ParamButton(Context context) {
        super(context);
        this.mContext = context;
    }

    public ParamButton(Context context, AttributeSet attributeSet) {
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

    public void setStateUpDn(int normal, int pressed) {
        setStateDrawable(normal, pressed, -1);
    }

    public void setStateUpSel(int normal, int selected) {
        setStateDrawable(normal, -1, selected);
    }

    public void setDrawable(int normal, int pressedSel) {
        setStateDrawable(normal, pressedSel, pressedSel);
    }

    public void setStateDrawable(int normal, int pressed, int selected) {
        Drawable iSelected = null;
        Resources resources = this.mContext.getResources();
        StateListDrawable sd = new StateListDrawable();
        this.mNormalResId = normal;
        this.mPressResId = pressed;
        this.mSelectResId = selected;
        Drawable iNormal = normal <= 0 ? null : getThemeDrawable(normal);
        Drawable iPressed = pressed <= 0 ? null : getThemeDrawable(pressed);
        if (selected > 0) {
            iSelected = getThemeDrawable(selected);
        }
        sd.addState(new int[]{16842913}, iSelected);
        sd.addState(new int[]{16842919}, iPressed);
        sd.addState(new int[]{16842910}, iNormal);
        sd.addState(new int[0], iNormal);
        setBackground(sd);
    }

    public void setStateFocus(int normal, int pressedSel, int focused) {
        Drawable iFocused = null;
        Resources resources = this.mContext.getResources();
        StateListDrawable sd = new StateListDrawable();
        this.mNormalResId = normal;
        this.mPressResId = pressedSel;
        this.mFocusResId = focused;
        Drawable iNormal = normal <= 0 ? null : getThemeDrawable(normal);
        Drawable iPressed = pressedSel <= 0 ? null : getThemeDrawable(pressedSel);
        if (focused > 0) {
            iFocused = getThemeDrawable(focused);
        }
        sd.addState(new int[]{16842913}, iPressed);
        sd.addState(new int[]{16842919}, iPressed);
        sd.addState(new int[]{16842908}, iFocused);
        sd.addState(new int[]{16842910}, iNormal);
        sd.addState(new int[0], iNormal);
        setBackground(sd);
    }

    public void setStateFocus(int normal, int pressedSel, int selected, int focused) {
        Drawable iFocused = null;
        Resources resources = this.mContext.getResources();
        StateListDrawable sd = new StateListDrawable();
        this.mNormalResId = normal;
        this.mPressResId = pressedSel;
        this.mFocusResId = focused;
        this.mSelectResId = selected;
        Drawable iNormal = normal <= 0 ? null : getThemeDrawable(normal);
        Drawable iPressed = pressedSel <= 0 ? null : getThemeDrawable(pressedSel);
        Drawable iSelected = selected <= 0 ? null : getThemeDrawable(selected);
        if (focused > 0) {
            iFocused = getThemeDrawable(focused);
        }
        sd.addState(new int[]{16842908}, iFocused);
        sd.addState(new int[]{16842913}, iSelected);
        sd.addState(new int[]{16842919}, iPressed);
        sd.addState(new int[]{16842910}, iNormal);
        sd.addState(new int[0], iNormal);
        setBackground(sd);
    }

    public void setColorUpDn(int normal, int pressed) {
        int[][] states = {new int[]{16842919}, new int[0]};
        setTextColor(new ColorStateList(states, new int[]{pressed, normal}));
    }

    public void setColor(int normal, int pressedDn) {
        int[][] states = {new int[]{16842919}, new int[]{16842913}, new int[0]};
        setTextColor(new ColorStateList(states, new int[]{pressedDn, pressedDn, normal}));
    }

    public void setStateColor(int normal, int pressed, int selected, int unable) {
        int[][] states = {new int[]{16842919, 16842910}, new int[]{16842910, 16842913}, new int[]{16842910}, new int[]{16842913}, new int[]{16842909}, new int[0]};
        setTextColor(new ColorStateList(states, new int[]{pressed, selected, normal, selected, unable, normal}));
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        event.getAction();
        return super.dispatchTouchEvent(event);
    }

    public static void initFactory(Context context, ViewGroup layout, int yDt) {
        mFsContext = context;
        mFsLayout = layout;
        mFsYDt = yDt;
    }

    public static ParamButton fsCreateRelative(int x, int y, int w, int h) {
        ParamButton btn = new ParamButton(mFsContext);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
        rl.leftMargin = x;
        rl.topMargin = mFsYDt + y;
        btn.setLayoutParams(rl);
        mFsLayout.addView(btn);
        return btn;
    }

    public void Show(boolean show) {
        if (show) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    public void SetSel(int val) {
        if (val == 0) {
            setSelected(false);
        } else {
            setSelected(true);
        }
    }

    public void SetBgTransparent() {
        setBackgroundColor(0);
    }

    public void setTheme(String name) {
        this.mThemeName = name;
        if (this.mFocusResId > 0 && this.mSelectResId > 0) {
            setStateFocus(this.mNormalResId, this.mPressResId, this.mSelectResId, this.mFocusResId);
        } else if (this.mFocusResId > 0) {
            setStateFocus(this.mNormalResId, this.mPressResId, this.mFocusResId);
        } else {
            setStateDrawable(this.mNormalResId, this.mPressResId, this.mSelectResId);
        }
    }

    private int getNewThemeResId(int resId) {
        if (this.mThemeName == null || resId <= 0) {
            return resId;
        }
        String entryName = this.mContext.getResources().getResourceEntryName(resId);
        if (entryName != null && !entryName.endsWith(this.mThemeName) && (resId = this.mContext.getResources().getIdentifier(String.valueOf(entryName) + "_" + this.mThemeName, "drawable", this.mContext.getPackageName())) <= 0) {
            new Exception("can't find themed drawable named " + entryName + "_" + this.mThemeName).printStackTrace();
        }
        return resId;
    }

    private Drawable getThemeDrawable(int resId) {
        int themeId;
        if (resId > 0 && (themeId = getNewThemeResId(resId)) > 0) {
            return this.mContext.getResources().getDrawable(themeId);
        }
        return null;
    }
}
