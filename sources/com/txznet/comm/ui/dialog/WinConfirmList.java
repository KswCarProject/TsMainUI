package com.txznet.comm.ui.dialog;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.txznet.txz.comm.R;
import java.util.List;

/* compiled from: Proguard */
public abstract class WinConfirmList extends WinConfirm {
    public WinConfirmList() {
        TE();
    }

    public WinConfirmList(boolean isSystem) {
        super(isSystem);
        TE();
    }

    private void TE() {
        T("确定");
        Ty("取消");
        this.Te.Tn.setVisibility(8);
        this.Te.T9.setVisibility(0);
    }

    public WinConfirmList setListItem(List<String> msgs) {
        this.Te.T9.setAdapter(new ArrayAdapter(getContext(), R.layout.comm_win_list_item, msgs));
        return this;
    }

    public WinConfirmList setListAdapter(ListAdapter adapter) {
        this.Te.T9.setAdapter(adapter);
        return this;
    }

    public WinConfirmList setTitle(String s) {
        super.setTitle(s);
        return this;
    }

    public WinConfirmList setMessageData(Object data) {
        super.setMessageData(data);
        return this;
    }

    public WinConfirmList setCancelText(String s) {
        super.Ty(s);
        return this;
    }

    public WinConfirmList setSureText(String s) {
        super.T(s);
        return this;
    }
}
