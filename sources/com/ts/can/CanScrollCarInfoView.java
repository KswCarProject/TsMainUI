package com.ts.can;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.ts.can.CanBaseCarInfoView;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemPopupCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanItemTitleValList;
import java.lang.reflect.Array;

public abstract class CanScrollCarInfoView extends CanBaseCarInfoView implements CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange, View.OnTouchListener, CanItemPopupCheckList.onPopCheckItemClick {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$ts$can$CanScrollCarInfoView$Item;
    private int mItemCount;
    protected int[] mItemIcons;
    protected Object[] mItemObjects;
    protected int[] mItemTitleIds;
    protected Item[] mItemTypes;
    protected int[] mItemVisibles;
    protected int[][] mPopCheckValueIds;
    protected int[][] mPopValueIds;
    protected int[][] mProgressAttrs;

    public enum Item {
        CAR_TYPE,
        POP,
        CHECK,
        SWITCH,
        PROGRESS,
        TITLE,
        ICON,
        TEXT,
        VALUE,
        SWITCH_TOUCH,
        ICON_TOUCH,
        DATE,
        SWITCH_TOUCH2,
        POP_CHECK
    }

    /* access modifiers changed from: protected */
    public abstract void InitData();

    static /* synthetic */ int[] $SWITCH_TABLE$com$ts$can$CanScrollCarInfoView$Item() {
        int[] iArr = $SWITCH_TABLE$com$ts$can$CanScrollCarInfoView$Item;
        if (iArr == null) {
            iArr = new int[Item.values().length];
            try {
                iArr[Item.CAR_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Item.CHECK.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Item.DATE.ordinal()] = 12;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Item.ICON.ordinal()] = 7;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Item.ICON_TOUCH.ordinal()] = 11;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[Item.POP.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[Item.POP_CHECK.ordinal()] = 14;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[Item.PROGRESS.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[Item.SWITCH.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[Item.SWITCH_TOUCH.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[Item.SWITCH_TOUCH2.ordinal()] = 13;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[Item.TEXT.ordinal()] = 8;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[Item.TITLE.ordinal()] = 6;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[Item.VALUE.ordinal()] = 9;
            } catch (NoSuchFieldError e14) {
            }
            $SWITCH_TABLE$com$ts$can$CanScrollCarInfoView$Item = iArr;
        }
        return iArr;
    }

    public CanScrollCarInfoView(Activity activity, int itemCount) {
        super(activity, CanBaseCarInfoView.Type.SCROLL);
        this.mItemCount = itemCount;
        this.mItemObjects = new Object[itemCount];
        this.mItemVisibles = new int[itemCount];
        this.mPopValueIds = new int[itemCount][];
        this.mPopCheckValueIds = new int[itemCount][];
        this.mProgressAttrs = (int[][]) Array.newInstance(Integer.TYPE, new int[]{itemCount, 4});
        for (int i = 0; i < itemCount; i++) {
            this.mItemVisibles[i] = 1;
        }
        InitData();
        InitUI();
    }

    /* access modifiers changed from: protected */
    public View getView() {
        return getScrollManager().getLayout();
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        if (this.mItemIcons != null && this.mItemIcons.length < this.mItemCount) {
            showToast("mItemIcons.length < mItemCount");
        } else if (this.mItemTitleIds == null || this.mItemTitleIds.length < this.mItemCount) {
            showToast("mItemTitleIds == null 或者 mItemTitleIds.length < mItemCount");
        } else if (this.mItemTypes == null || this.mItemTypes.length < this.mItemCount) {
            showToast("mItemTypes == null 或者 mItemTypes.length < mItemCount");
        } else {
            for (int i = 0; i < this.mItemCount; i++) {
                addItem(i);
                showItem(i, this.mItemVisibles[i]);
            }
        }
    }

    private void addItem(int index) {
        switch ($SWITCH_TABLE$com$ts$can$CanScrollCarInfoView$Item()[this.mItemTypes[index].ordinal()]) {
            case 1:
                if (this.mPopValueIds[index].length == 1) {
                    this.mItemObjects[index] = getScrollManager().addItemCarType(this.mItemTitleIds[index], getActivity().getResources().getStringArray(this.mPopValueIds[index][0]), index, (CanItemPopupList.onPopItemClick) this);
                    return;
                }
                this.mItemObjects[index] = getScrollManager().addItemCarType(this.mItemTitleIds[index], this.mPopValueIds[index], index, (CanItemPopupList.onPopItemClick) this);
                return;
            case 2:
                if (this.mPopValueIds[index].length == 1) {
                    this.mItemObjects[index] = getScrollManager().addItemPopupList(this.mItemTitleIds[index], getActivity().getResources().getStringArray(this.mPopValueIds[index][0]), index, (CanItemPopupList.onPopItemClick) this);
                    return;
                }
                this.mItemObjects[index] = getScrollManager().addItemPopupList(this.mItemTitleIds[index], this.mPopValueIds[index], index, (CanItemPopupList.onPopItemClick) this);
                return;
            case 3:
                this.mItemObjects[index] = getScrollManager().addItemLeftCheckBox(this.mItemTitleIds[index], index, this);
                return;
            case 4:
                this.mItemObjects[index] = getScrollManager().addItemCheckBox(this.mItemTitleIds[index], index, this);
                return;
            case 5:
                CanItemProgressList progressItem = getScrollManager().addItemProgressList(this.mItemTitleIds[index], index, (CanItemProgressList.onPosChange) this);
                progressItem.SetMinMax(this.mProgressAttrs[index][0], this.mProgressAttrs[index][1]);
                progressItem.SetStep(this.mProgressAttrs[index][2]);
                if (this.mProgressAttrs[index][3] > 0) {
                    progressItem.SetUserValText();
                }
                this.mItemObjects[index] = progressItem;
                return;
            case 6:
                this.mItemObjects[index] = getScrollManager().addItemFsSetList(this.mItemTitleIds[index], index, this);
                return;
            case 7:
                this.mItemObjects[index] = getScrollManager().addItemIconList(this.mItemIcons[index], this.mItemTitleIds[index], index, this);
                return;
            case 8:
                this.mItemObjects[index] = getScrollManager().addItemTextTitle(this.mItemTitleIds[index], index);
                return;
            case 9:
                this.mItemObjects[index] = getScrollManager().addItemTitleValList(this.mItemTitleIds[index], index, false, this);
                return;
            case 10:
                this.mItemObjects[index] = getScrollManager().addTouchItemCheckBox(this.mItemIcons[index], this.mItemTitleIds[index], index, this);
                return;
            case 11:
                this.mItemObjects[index] = getScrollManager().addTouchItemIconList(this.mItemIcons[index], this.mItemTitleIds[index], index, this);
                return;
            case 12:
                CanItemPopupList dateItem = getScrollManager().addItemPopupList(this.mItemTitleIds[index], (String[]) null, index, (CanItemPopupList.onPopItemClick) null);
                dateItem.GetView().setOnClickListener(this);
                dateItem.GetView().setTag(Integer.valueOf(index));
                this.mItemObjects[index] = dateItem;
                return;
            case 13:
                this.mItemObjects[index] = getScrollManager().addTouchItemCheckBox(this.mItemTitleIds[index], index, this);
                return;
            case 14:
                if (this.mPopCheckValueIds[index].length == 1) {
                    this.mItemObjects[index] = getScrollManager().addItemPopupCheckList(this.mItemTitleIds[index], getActivity().getResources().getStringArray(this.mPopCheckValueIds[index][0]), index, (CanItemPopupCheckList.onPopCheckItemClick) this);
                    return;
                }
                this.mItemObjects[index] = getScrollManager().addItemPopupCheckList(this.mItemTitleIds[index], this.mPopCheckValueIds[index], index, (CanItemPopupCheckList.onPopCheckItemClick) this);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void showItem(int[] values) {
        for (int i = 0; i < values.length; i++) {
            showItem(i, values[i]);
        }
    }

    public void showItem() {
        for (int index = 0; index < this.mItemCount; index++) {
            showItem(index, this.mItemVisibles[index]);
        }
    }

    /* access modifiers changed from: protected */
    public void showItem(int index, int show) {
        switch ($SWITCH_TABLE$com$ts$can$CanScrollCarInfoView$Item()[this.mItemTypes[index].ordinal()]) {
            case 1:
                ((CanItemCarType) this.mItemObjects[index]).GetView().setVisibility(show == 0 ? 8 : 0);
                return;
            case 2:
                ((CanItemPopupList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 3:
                ((CanItemCheckList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 4:
                ((CanItemSwitchList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 5:
                ((CanItemProgressList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 6:
                ((CanItemTextBtnList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 7:
                ((CanItemIcoList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 8:
                ((CanItemBlankTextList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 9:
                ((CanItemTitleValList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 10:
                ((CanItemSwitchList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 11:
                ((CanItemIcoList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 12:
                ((CanItemPopupList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 13:
                ((CanItemSwitchList) this.mItemObjects[index]).ShowGone(show);
                return;
            case 14:
                ((CanItemPopupCheckList) this.mItemObjects[index]).ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void updateItem(int[] values) {
        for (int i = 0; i < values.length; i++) {
            updateItem(i, values[i]);
        }
    }

    /* access modifiers changed from: protected */
    public void updateItem(int index, int value) {
        updateItem(index, value, (String) null);
    }

    /* access modifiers changed from: protected */
    public void updateItem(int index, int[] value) {
        if (this.mItemTypes[index] == Item.POP_CHECK) {
            ((CanItemPopupCheckList) this.mItemObjects[index]).SetSel(value);
        }
    }

    /* access modifiers changed from: protected */
    public void updateItem(int index, int value, String userValue) {
        switch ($SWITCH_TABLE$com$ts$can$CanScrollCarInfoView$Item()[this.mItemTypes[index].ordinal()]) {
            case 1:
                ((CanItemCarType) this.mItemObjects[index]).GetPopItem().SetSel(value);
                return;
            case 2:
                ((CanItemPopupList) this.mItemObjects[index]).SetSel(value);
                return;
            case 3:
                ((CanItemCheckList) this.mItemObjects[index]).SetCheck(value);
                return;
            case 4:
                ((CanItemSwitchList) this.mItemObjects[index]).SetCheck(value);
                return;
            case 5:
                ((CanItemProgressList) this.mItemObjects[index]).SetCurVal(value);
                if (userValue != null) {
                    ((CanItemProgressList) this.mItemObjects[index]).SetValText(userValue);
                    return;
                }
                return;
            case 9:
                ((CanItemTitleValList) this.mItemObjects[index]).SetVal(userValue);
                return;
            case 10:
                ((CanItemSwitchList) this.mItemObjects[index]).SetCheck(value);
                return;
            case 12:
                ((CanItemPopupList) this.mItemObjects[index]).SetVal(userValue);
                return;
            case 13:
                ((CanItemSwitchList) this.mItemObjects[index]).SetCheck(value);
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onPositiveItem(int id, int[] item) {
    }

    public void doOnResume() {
    }

    public void doOnPause() {
    }

    public void doOnFinish() {
    }

    public void doOnStart() {
    }
}
