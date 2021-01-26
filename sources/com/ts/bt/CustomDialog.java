package com.ts.bt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.ts.MainUI.R;

public class CustomDialog {
    protected AlertDialog mDlg;
    private int mScrH;
    private int mScrW;
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
        this.mScrW = SystemProperties.getInt("ro.forfan.hardware.width", 1024);
        this.mScrH = SystemProperties.getInt("ro.forfan.hardware.height", 600);
        Log.d("boned", "mScrW = " + this.mScrW);
        Log.d("boned", "mScrH = " + this.mScrH);
        Resources resources = context.getResources();
        String mFlagStr = resources.getString(R.string.custom_num_show);
        String mCustomScreenShowStr = resources.getString(R.string.custom_screen_show);
        String mSupportRotationStr = resources.getString(R.string.support_rotation);
        if (this.mScrH - 55 < 484) {
            Log.d("boned", "setAttributes");
            WindowManager.LayoutParams windowLayoutParams = this.mWindow.getAttributes();
            windowLayoutParams.height = -1;
            this.mWindow.setAttributes(windowLayoutParams);
        } else if ("KDLK".equals(mFlagStr)) {
            Log.d("boned", "KDLK setAttributes");
            WindowManager.LayoutParams windowLayoutParams2 = this.mWindow.getAttributes();
            windowLayoutParams2.gravity = 49;
            windowLayoutParams2.height = 432;
            windowLayoutParams2.y = 119;
            this.mWindow.setAttributes(windowLayoutParams2);
        } else if (!"768x1024".equals(mCustomScreenShowStr) || !"1".equals(mSupportRotationStr)) {
            WindowManager.LayoutParams windowLayoutParams3 = this.mWindow.getAttributes();
            windowLayoutParams3.height = 484;
            this.mWindow.setAttributes(windowLayoutParams3);
        } else {
            Log.d("boned", "768x1024 rotate setAttributes");
            WindowManager.LayoutParams windowLayoutParams4 = this.mWindow.getAttributes();
            windowLayoutParams4.gravity = 49;
            windowLayoutParams4.height = 432;
            windowLayoutParams4.y = 119;
            this.mWindow.setAttributes(windowLayoutParams4);
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
