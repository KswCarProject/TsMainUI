package com.ts.canview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;

public class CanItemCarType implements CanItemPopupList.onPopItemClick, CanItemMsgBox.onMsgBoxClick {
    /* access modifiers changed from: private */
    public CanItemPopupList.onPopItemClick mCallBack;
    /* access modifiers changed from: private */
    public CanItemPopupList mItemPopup;
    /* access modifiers changed from: private */
    public int para = 0;

    public CanItemCarType(Context context, int text, int[] valarr, int id, CanItemPopupList.onPopItemClick callBack) {
        this.mItemPopup = new CanItemPopupList(context, text, valarr, (CanItemPopupList.onPopItemClick) this);
        this.mCallBack = callBack;
        this.mItemPopup.SetId(id);
    }

    public CanItemCarType(Context context, int text, String[] valarr, int id, CanItemPopupList.onPopItemClick callBack) {
        this.mCallBack = callBack;
        this.mItemPopup = new CanItemPopupList(context, text, valarr, (CanItemPopupList.onPopItemClick) this);
        this.mItemPopup.SetId(id);
    }

    public View GetView() {
        return this.mItemPopup.GetView();
    }

    public CanItemPopupList GetPopItem() {
        return this.mItemPopup;
    }

    public void onItem(int id, int item) {
        this.para = item;
        if (item != this.mItemPopup.GetSel()) {
            new AlertDialog.Builder(this.mItemPopup.GetContext()).setTitle(R.string.str_fs_tip).setMessage(String.format("%s %s ?", new Object[]{this.mItemPopup.GetContext().getResources().getString(R.string.can_cqsx_qdxg), this.mItemPopup.GetItemStr(item)})).setNegativeButton(R.string.can_cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.can_confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (CanItemCarType.this.mCallBack != null) {
                        CanItemCarType.this.mCallBack.onItem(CanItemCarType.this.mItemPopup.GetId(), CanItemCarType.this.para);
                    }
                }
            }).show();
        }
    }

    public void onOK(int param) {
    }
}
