package com.ts.canview;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.ts.MainUI.R;
import com.ts.canview.CanItemPopupCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;

public class CanScrollList {
    protected Context mContext;
    protected int mHeigh = 85;
    protected LinearLayout mLayout;

    public CanScrollList() {
    }

    public CanScrollList(Activity activity) {
        Init(activity);
    }

    public CanScrollList(Activity activity, int id, int h) {
        this.mLayout = (LinearLayout) activity.findViewById(id);
        this.mHeigh = h;
    }

    public CanScrollList(LinearLayout layout, int h) {
        this.mLayout = layout;
        if (h > 0) {
            this.mHeigh = h;
        }
    }

    public void Init(Activity activity) {
        this.mContext = activity;
        this.mLayout = (LinearLayout) activity.findViewById(R.id.can_comm_lineview);
    }

    public void AddView(View v) {
        this.mLayout.addView(v, new LinearLayout.LayoutParams(-2, this.mHeigh));
    }

    public void AddViewIndex(View v, int index) {
        this.mLayout.addView(v, index, new LinearLayout.LayoutParams(-2, this.mHeigh));
    }

    public void AddView(View v, int w, int h) {
        this.mLayout.addView(v, new LinearLayout.LayoutParams(w, h));
    }

    public void AddView(View v, int h) {
        this.mLayout.addView(v, new LinearLayout.LayoutParams(-2, h));
    }

    public void RemoveAllViews() {
        this.mLayout.removeAllViews();
    }

    public void RemoveView(int index) {
        this.mLayout.removeViewAt(index);
    }

    public void ShowHide(boolean show) {
        if (show) {
            this.mLayout.setVisibility(0);
        } else {
            this.mLayout.setVisibility(4);
        }
    }

    public void ShowHide(int show) {
        ShowHide(show != 0);
    }

    public void ShowGone(boolean show) {
        if (show) {
            this.mLayout.setVisibility(0);
        } else {
            this.mLayout.setVisibility(8);
        }
    }

    public void ShowGone(int show) {
        ShowGone(show != 0);
    }

    public LinearLayout getLayout() {
        return this.mLayout;
    }

    public CanItemCarType addItemCarType(int text, int[] valueIds, int id, CanItemPopupList.onPopItemClick callBack) {
        CanItemCarType item = new CanItemCarType(this.mContext, text, valueIds, id, callBack);
        AddView(item.GetView());
        return item;
    }

    public CanItemCarType addItemCarType(int text, String[] valueArr, int id, CanItemPopupList.onPopItemClick callBack) {
        CanItemCarType item = new CanItemCarType(this.mContext, text, valueArr, id, callBack);
        AddView(item.GetView());
        return item;
    }

    public CanItemPopupList addItemPopupList(int text, int[] valueIds, int id, CanItemPopupList.onPopItemClick callBack) {
        CanItemPopupList item = new CanItemPopupList(this.mContext, text, valueIds, callBack);
        item.SetId(id);
        AddView(item.GetView());
        return item;
    }

    public CanItemPopupList addItemPopupList(int text, String[] valueArr, int id, CanItemPopupList.onPopItemClick callBack) {
        CanItemPopupList item = new CanItemPopupList(this.mContext, text, valueArr, callBack);
        item.SetId(id);
        AddView(item.GetView());
        return item;
    }

    public CanItemPopupList addItemPopupList(int text, String[] valueArr, int id, CanItemPopupList.onPopItemClick callBack, int index) {
        CanItemPopupList item = new CanItemPopupList(this.mContext, text, valueArr, callBack);
        item.SetId(id);
        AddViewIndex(item.GetView(), index);
        return item;
    }

    public CanItemPopupCheckList addItemPopupCheckList(int text, int[] valueIds, int id, CanItemPopupCheckList.onPopCheckItemClick callBack) {
        CanItemPopupCheckList item = new CanItemPopupCheckList(this.mContext, text, valueIds, callBack);
        item.SetId(id);
        AddView(item.GetView());
        return item;
    }

    public CanItemPopupCheckList addItemPopupCheckList(int text, String[] valueArr, int id, CanItemPopupCheckList.onPopCheckItemClick callBack) {
        CanItemPopupCheckList item = new CanItemPopupCheckList(this.mContext, text, valueArr, callBack);
        item.SetId(id);
        AddView(item.GetView());
        return item;
    }

    public CanItemSwitchList addItemCheckBox(int text, int id, View.OnClickListener listener) {
        CanItemSwitchList item = new CanItemSwitchList(this.mContext, text);
        item.SetIdClickListener(listener, id);
        AddView(item.GetView());
        return item;
    }

    public CanItemSwitchList addTouchItemCheckBox(int text, int id, View.OnTouchListener listener) {
        CanItemSwitchList item = new CanItemSwitchList(this.mContext, text);
        item.SetIdTouchListener(listener, id);
        AddView(item.GetView());
        return item;
    }

    public CanItemSwitchList addTouchItemCheckBox(int icon, int text, int id, View.OnTouchListener listener) {
        CanItemSwitchList item = new CanItemSwitchList(this.mContext, icon, text);
        item.SetIdTouchListener(listener, id);
        AddView(item.GetView());
        return item;
    }

    public CanItemProgressList addItemProgressList(int textId, int id, CanItemProgressList.onPosChange callBack) {
        CanItemProgressList item = new CanItemProgressList(this.mContext, textId);
        item.SetIdCallBack(id, callBack);
        AddView(item.GetView());
        return item;
    }

    public CanItemProgressList addItemProgressList(String text, int id, CanItemProgressList.onPosChange callBack) {
        CanItemProgressList item = new CanItemProgressList(this.mContext, text);
        item.SetIdCallBack(id, callBack);
        AddView(item.GetView());
        return item;
    }

    public CanItemIcoList addItemIconList(int iconId, int text, int id, View.OnClickListener listener) {
        CanItemIcoList item = new CanItemIcoList(this.mContext, iconId, text);
        item.SetIdClickListener(listener, id);
        AddView(item.GetView());
        return item;
    }

    public CanItemIcoList addTouchItemIconList(int iconId, int text, int id, View.OnTouchListener listener) {
        CanItemIcoList item = new CanItemIcoList(this.mContext, iconId, text);
        item.SetIdTouchListener(listener, id);
        AddView(item.GetView());
        return item;
    }

    public CanItemTextBtnList addItemFsSetList(int text, int id, View.OnClickListener listener) {
        CanItemTextBtnList item = new CanItemTextBtnList(this.mContext, text);
        item.SetIdClickListener(listener, id);
        AddView(item.GetView());
        return item;
    }

    public CanItemTitleValList addItemTitleValList(int text, int id, boolean showIcon, View.OnClickListener listener) {
        CanItemTitleValList item = new CanItemTitleValList(this.mContext, text);
        item.SetIdClickListener(listener, id);
        if (showIcon) {
            item.SetIconVisibility(0);
        } else {
            item.SetIconVisibility(4);
        }
        item.SetValVisibility(0);
        AddView(item.GetView());
        return item;
    }

    public CanItemCheckList addItemLeftCheckBox(int text, int id, View.OnClickListener listener) {
        CanItemCheckList item = new CanItemCheckList(this.mContext, text);
        item.SetIdClickListener(listener, id);
        AddView(item.GetView());
        return item;
    }

    public CanItemBlankTextList addItemTextTitle(int text, int id) {
        CanItemBlankTextList item = new CanItemBlankTextList(this.mContext, text);
        item.GetView().setTag(Integer.valueOf(id));
        AddView(item.GetView());
        return item;
    }
}
