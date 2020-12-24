package com.ts.canview;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemFsSetList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;

public class CanScrollBaseActivity extends CanBaseActivity {
    public static final String TAG = "CanScrollBaseActivity";
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddSwitch(View.OnClickListener l, int text, int id) {
        CanItemSwitchList item = new CanItemSwitchList(this, text);
        item.SetIdClickListener(l, id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddSwitch(View.OnClickListener l, String text, int id) {
        CanItemSwitchList item = new CanItemSwitchList(this, 0);
        item.SetIdClickListener(l, id);
        item.GetBtn().setText(text);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemIcoVal AddIcoVal(int ico, int text, int id) {
        CanItemIcoVal item = new CanItemIcoVal(this, ico, text);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopup(CanItemPopupList.onPopItemClick callBack, int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, callBack);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopup(CanItemPopupList.onPopItemClick callBack, int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, callBack);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemTextBtnList AddTextBtn(View.OnClickListener l, int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(l, id);
        this.mManager.AddView(btn.GetView());
        return btn;
    }

    /* access modifiers changed from: protected */
    public CanItemProgressList AddProgress(CanItemProgressList.onPosChange cb, int text, int id) {
        CanItemProgressList item = new CanItemProgressList((Context) this, text);
        item.SetIdCallBack(id, cb);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemIcoList AddIcoItem(View.OnClickListener l, int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(l, id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemIcoList AddIcoItem(View.OnClickListener l, int ico, String text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, 0);
        item.GetBtn().setText(text);
        item.SetIdClickListener(l, id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemBlankTextList AddTitleLine(int text) {
        CanItemBlankTextList item = new CanItemBlankTextList((Context) this, text);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemTitleValList AddTitleVal(View.OnClickListener l, int text, int id) {
        CanItemTitleValList item = new CanItemTitleValList(this, text);
        this.mManager.AddView(item.GetView());
        item.GetView().setOnClickListener(l);
        item.GetView().setTag(Integer.valueOf(id));
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemFsSetList AddFsSetItem(CanItemFsSetList.onFsSetClick l, int text, int id) {
        CanItemFsSetList item = new CanItemFsSetList(this, text);
        item.SetIdClickListener(id, l);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemFsSetList AddFsSetItem(CanItemFsSetList.onFsSetClick l, int id) {
        CanItemFsSetList item = new CanItemFsSetList(this);
        item.SetIdClickListener(id, l);
        this.mManager.AddView(item.GetView());
        return item;
    }
}
