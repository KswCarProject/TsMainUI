package com.txznet.comm.ui.plugin;

import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.txznet.T.T;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.dialog.WinDialog;

/* compiled from: Proguard */
public class WinPlugin extends WinDialog {
    /* access modifiers changed from: private */
    public static SparseArray<WinPlugin> Ty = new SparseArray<>();

    /* renamed from: T  reason: collision with root package name */
    private int f645T = 0;
    private View Tr;

    protected WinPlugin(int type) {
        this.f645T = type;
    }

    /* access modifiers changed from: protected */
    public void T(View view, WindowManager.LayoutParams layoutParams) {
        if (view == null) {
            view = this.Tr;
        }
        if (layoutParams == null) {
            layoutParams = getWindow().getAttributes();
        }
        getWindow().setContentView(view, layoutParams);
    }

    /* access modifiers changed from: protected */
    public View T() {
        TextView textView = new TextView(getContext());
        textView.setText("默认View");
        this.Tr = textView;
        return this.Tr;
    }

    public static void showPluginView(View view, WindowManager.LayoutParams layoutParams) {
        showPluginView(view, layoutParams, 0, new Object[0]);
    }

    public static void showPluginView(final View view, final WindowManager.LayoutParams layoutParams, final int type, Object... objects) {
        T.Ty(new Runnable() {
            public void run() {
                WinPlugin winPlugin = (WinPlugin) WinPlugin.Ty.get(type);
                if (winPlugin == null) {
                    winPlugin = new WinPlugin(type);
                    WinPlugin.Ty.put(type, winPlugin);
                }
                winPlugin.T(view, layoutParams);
                winPlugin.show();
            }
        }, 0);
    }

    public static void showWin(final int type) {
        if (Ty.get(type) == null) {
            Tn.Tn("plugin win not exist,type:" + type);
        } else {
            T.Ty(new Runnable() {
                public void run() {
                    ((WinPlugin) WinPlugin.Ty.get(type)).show();
                }
            }, 0);
        }
    }

    public static void dismissWin(final int type) {
        if (Ty.get(type) == null) {
            Tn.Tn("plugin win not exist,type:" + type);
        } else {
            T.Ty(new Runnable() {
                public void run() {
                    ((WinPlugin) WinPlugin.Ty.get(type)).dismiss();
                }
            }, 0);
        }
    }

    public static void updateViewData(int type, Object... objects) {
        if (Ty.get(type) == null) {
            Tn.Tn("plugin win not exist,type:" + type);
        }
    }
}
