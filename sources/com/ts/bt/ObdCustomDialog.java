package com.ts.bt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.ts.MainUI.R;

public class ObdCustomDialog {
    protected AlertDialog mDlg;
    private int mScrH;
    private int mScrW;
    protected Window mWindow;

    public ObdCustomDialog(int resId, Context context) {
        create(resId, context);
    }

    public ObdCustomDialog() {
    }

    public AlertDialog create(int resId, Context context) {
        this.mDlg = new AlertDialog.Builder(context).create();
        this.mDlg.show();
        this.mDlg.setContentView(resId);
        this.mWindow = this.mDlg.getWindow();
        Resources resources = context.getResources();
        String mCustomNumShowStr = resources.getString(R.string.custom_num_show);
        String mCustomScreenShowStr = resources.getString(R.string.custom_screen_show);
        if ("Lexus".equals(mCustomNumShowStr) && "1920x720".equals(mCustomScreenShowStr)) {
            Log.d("boned", "setAttributes");
            WindowManager.LayoutParams windowLayoutParams = this.mWindow.getAttributes();
            windowLayoutParams.x = -24;
            this.mWindow.setAttributes(windowLayoutParams);
        }
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
