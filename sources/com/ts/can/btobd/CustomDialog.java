package com.ts.can.btobd;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

public class CustomDialog {
    protected AlertDialog mDlg;
    protected Window mWindow;

    public CustomDialog(int resId, Context context) {
        create(resId, context);
    }

    public CustomDialog() {
    }

    public AlertDialog create(int resId, Context context) {
        this.mDlg = new AlertDialog.Builder(context).create();
        this.mDlg.show();
        this.mDlg.setContentView(resId);
        this.mWindow = this.mDlg.getWindow();
        this.mWindow.clearFlags(131080);
        return this.mDlg;
    }

    public AlertDialog getDlg() {
        return this.mDlg;
    }

    public void dismiss() {
        if (this.mDlg != null) {
            this.mDlg.dismiss();
            this.mDlg = null;
            this.mWindow = null;
        }
    }

    public View findViewById(int id) {
        if (this.mWindow != null) {
            return this.mWindow.findViewById(id);
        }
        return null;
    }
}
