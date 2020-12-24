package com.ts.can;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;

public abstract class CanBaseACView extends CanRelativeCarInfoView {
    protected boolean isJumped;
    protected int[][] mButtonAttrs;
    protected ParamButton[] mButtonObjects;
    protected int[] mButtonTouch;
    protected int[][] mImageAttrs;
    protected CustomImgView[] mImageObjects;
    protected int[][] mTextAttrs;
    protected TextView[] mTextObjects;

    /* access modifiers changed from: protected */
    public abstract void InitData();

    /* access modifiers changed from: protected */
    public abstract void InitViews();

    /* access modifiers changed from: protected */
    public abstract void updateACUI();

    public CanBaseACView(Activity activity) {
        super(activity);
        this.isJumped = CanFunc.IsCanActivityJumped(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        InitData();
        addItems();
        InitViews();
    }

    private void addItems() {
        if (this.mTextAttrs != null) {
            this.mTextObjects = new TextView[this.mTextAttrs.length];
            for (int i = 0; i < this.mTextAttrs.length; i++) {
                addTextItem(i, this.mTextAttrs[i]);
            }
        }
        if (this.mImageAttrs != null) {
            this.mImageObjects = new CustomImgView[this.mImageAttrs.length];
            for (int i2 = 0; i2 < this.mImageAttrs.length; i2++) {
                addImageItem(i2, this.mImageAttrs[i2]);
            }
        }
        if (this.mButtonAttrs != null) {
            if (this.mButtonTouch == null) {
                this.mButtonTouch = new int[this.mButtonAttrs.length];
            }
            this.mButtonObjects = new ParamButton[this.mButtonAttrs.length];
            for (int i3 = 0; i3 < this.mButtonAttrs.length; i3++) {
                addButtonItem(i3, this.mButtonAttrs[i3]);
            }
        }
    }

    private void addImageItem(int i, int[] attr) {
        int length = attr.length - 1;
        switch (length) {
            case 2:
                this.mImageObjects[i] = addImage(attr[0], attr[1]);
                break;
            case 3:
                this.mImageObjects[i] = addImage(attr[0], attr[1], attr[2]);
                break;
            case 4:
                if (attr[2] > 10000 && attr[3] > 10000) {
                    this.mImageObjects[i] = addImageState(attr[0], attr[1], attr[2], attr[3]);
                    break;
                } else {
                    this.mImageObjects[i] = addImage(attr[0], attr[1], attr[2], attr[3]);
                    break;
                }
            case 5:
                this.mImageObjects[i] = addImage(attr[0], attr[1], attr[2], attr[3], attr[4]);
                break;
            case 6:
                this.mImageObjects[i] = addImage(attr[0], attr[1], attr[2], attr[3]);
                this.mImageObjects[i].setStateDrawable(attr[4], attr[5]);
                break;
        }
        this.mImageObjects[i].setTag(Integer.valueOf(attr[length]));
    }

    private void addTextItem(int i, int[] attr) {
        int length = attr.length - 1;
        switch (length) {
            case 2:
                this.mTextObjects[i] = addText(attr[0], attr[1], "");
                break;
            case 3:
                this.mTextObjects[i] = addText(attr[0], attr[1], attr[2]);
                break;
            case 4:
                this.mTextObjects[i] = addText(attr[0], attr[1], attr[2], attr[3]);
                break;
            case 5:
                this.mTextObjects[i] = addText(attr[0], attr[1], attr[2], attr[3], attr[4]);
                break;
        }
        this.mTextObjects[i].setTag(Integer.valueOf(attr[length]));
    }

    private void addButtonItem(int i, int[] attr) {
        int length = attr.length - 1;
        switch (length) {
            case 2:
                this.mButtonObjects[i] = addButton(attr[0], attr[1]);
                break;
            case 3:
                this.mButtonObjects[i] = addButton(attr[0], attr[1]);
                this.mButtonObjects[i].setText(attr[2]);
                break;
            case 4:
                if (attr[2] != 0 || attr[3] != 0) {
                    if (attr[2] > 10000 && attr[3] > 10000) {
                        this.mButtonObjects[i] = addButtonState(attr[0], attr[1], attr[2], attr[3]);
                        break;
                    } else {
                        this.mButtonObjects[i] = addButton(attr[0], attr[1], attr[2], attr[3]);
                        break;
                    }
                } else {
                    this.mButtonObjects[i] = addButton(attr[0], attr[1]);
                    break;
                }
                break;
            case 5:
                this.mButtonObjects[i] = addButton(attr[0], attr[1], attr[2], attr[3]);
                this.mButtonObjects[i].setText(attr[4]);
                break;
            case 6:
                this.mButtonObjects[i] = addButton(attr[0], attr[1], attr[2], attr[3]);
                this.mButtonObjects[i].setStateDrawable(attr[4], attr[5], attr[5]);
                break;
        }
        if (this.mButtonTouch[i] == 1) {
            setIdTouchListener(this.mButtonObjects[i], attr[length]);
        } else {
            setIdClickListener(this.mButtonObjects[i], attr[length]);
        }
    }

    /* access modifiers changed from: protected */
    public void updateText(int id, String value) {
        int index = getIndexById(id, this.mTextObjects);
        if (index != -1) {
            this.mTextObjects[index].setText(value);
        }
    }

    /* access modifiers changed from: protected */
    public void updateText(int id, int value) {
        int index = getIndexById(id, this.mTextObjects);
        if (index != -1) {
            this.mTextObjects[index].setSelected(i2b(value));
        }
    }

    /* access modifiers changed from: protected */
    public void updateImage(int id, int resId) {
        int index = getIndexById(id, this.mImageObjects);
        if (index != -1) {
            this.mImageObjects[index].setImageResource(resId);
        }
    }

    /* access modifiers changed from: protected */
    public void updateImage(int id, int value, int[] resIds) {
        int index = getIndexById(id, this.mImageObjects);
        if (index != -1) {
            this.mImageObjects[index].setImageResource(resIds[value]);
        }
    }

    /* access modifiers changed from: protected */
    public void updateButton(int id, int value) {
        int index = getIndexById(id, this.mButtonObjects);
        if (index != -1) {
            this.mButtonObjects[index].SetSel(value);
        }
    }

    /* access modifiers changed from: protected */
    public void updateButton(int id, int value, int[] upIds, int[] dnIds) {
        int index = getIndexById(id, this.mButtonObjects);
        if (index != -1) {
            this.mButtonObjects[index].setStateDrawable(upIds[value], dnIds[value], dnIds[value]);
        }
    }

    public CanBaseACView setTextStyle(int id, int textId, int textColor, int textSize, int gravity) {
        int index = getIndexById(id, this.mTextObjects);
        if (index != -1) {
            setTextStyle(this.mTextObjects[index], textId, textColor, textSize, gravity);
        }
        return this;
    }

    public CanBaseACView setTextStyle(int id, int textColor, int textSize, int gravity) {
        int index = getIndexById(id, this.mTextObjects);
        if (index != -1) {
            setTextStyle(this.mTextObjects[index], textColor, textSize, gravity);
        }
        return this;
    }

    public CanBaseACView setTextStyle(int id, int textColor, int textSize) {
        int index = getIndexById(id, this.mTextObjects);
        if (index != -1) {
            setTextStyle(this.mTextObjects[index], textColor, textSize);
        }
        return this;
    }

    public CanBaseACView setTextColorState(int id, int normal, int pressed) {
        int index = getIndexById(id, this.mTextObjects);
        if (index != -1) {
            setTextColorState(this.mTextObjects[index], normal, pressed);
        }
        return this;
    }

    public CanBaseACView setIdClickListener(int id, View[] views) {
        int index = getIndexById(id, views);
        if (index != -1) {
            setIdClickListener(views[index], id);
        }
        return this;
    }

    public CanBaseACView setIdTouchListener(int id, View[] views) {
        int index = getIndexById(id, views);
        if (index != -1) {
            setIdTouchListener(views[index], id);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public int getIndexById(int id, View[] views) {
        for (int i = 0; i < views.length; i++) {
            if (id == ((Integer) views[i].getTag()).intValue()) {
                return i;
            }
        }
        return -1;
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }

    public void doOnFinish() {
        if (CanBaseActivity.GetTickCount() > CanFunc.mLastACTick + 6000 && this.isJumped && !CanBaseACActivity.isACShow) {
            getActivity().finish();
        }
    }

    public void doOnDestory() {
    }
}
