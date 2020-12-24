package com.txznet.comm.ui.dialog2;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.txznet.comm.ui.dialog2.WinConfirm;
import com.txznet.txz.comm.R;
import java.util.List;

/* compiled from: Proguard */
public abstract class WinConfirmList extends WinConfirm {

    /* renamed from: T  reason: collision with root package name */
    T f585T;

    /* compiled from: Proguard */
    public static class T extends WinConfirm.T {

        /* renamed from: T  reason: collision with root package name */
        List<String> f589T;
        String[] Tr;
        ListAdapter Ty;
    }

    public WinConfirmList(T data) {
        this(data, true);
    }

    protected WinConfirmList(T data, boolean init) {
        super(data, false);
        this.f585T = data;
        if (init) {
            T9();
        }
    }

    /* access modifiers changed from: protected */
    public void Tr() {
        if (this.f585T.Ty != null) {
            this.Tq.T9.setAdapter(this.f585T.Ty);
        } else if (this.f585T.f589T != null) {
            this.Tq.T9.setAdapter(new ArrayAdapter(TZ(), R.layout.comm_win_list_item, this.f585T.f589T));
        } else if (this.f585T.Tr != null) {
            this.Tq.T9.setAdapter(new ArrayAdapter(TZ(), R.layout.comm_win_list_item, this.f585T.Tr));
        }
    }

    @Deprecated
    public WinConfirmList setListAdapter(List<String> msgs) {
        this.f585T.Ty = null;
        this.f585T.f589T = msgs;
        this.f585T.Tr = null;
        runOnUiGround(new Runnable() {
            public void run() {
                WinConfirmList.this.Tr();
            }
        }, 0);
        return this;
    }

    @Deprecated
    public WinConfirmList setListAdapter(String[] msgs) {
        this.f585T.Ty = null;
        this.f585T.f589T = null;
        this.f585T.Tr = msgs;
        runOnUiGround(new Runnable() {
            public void run() {
                WinConfirmList.this.Tr();
            }
        }, 0);
        return this;
    }

    @Deprecated
    public WinConfirmList setListAdapter(ListAdapter adapter) {
        this.f585T.Ty = adapter;
        this.f585T.f589T = null;
        this.f585T.Tr = null;
        runOnUiGround(new Runnable() {
            public void run() {
                WinConfirmList.this.Tr();
            }
        }, 0);
        return this;
    }
}
