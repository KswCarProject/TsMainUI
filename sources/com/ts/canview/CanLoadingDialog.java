package com.ts.canview;

import android.app.ProgressDialog;
import android.content.Context;
import com.ts.MainUI.R;

public class CanLoadingDialog {
    private static final long TIME = 15000;
    private static ProgressDialog mLoadingDialog;
    private static int mShowTime = 0;

    public static void Show(Context context) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(context, 16974374);
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.setMessage(context.getString(R.string.can_audio_change_loading));
        }
        mLoadingDialog.getWindow().setType(2003);
        mLoadingDialog.show();
        mShowTime = 0;
    }

    public static void Hide() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    public static void CheckTimeOut() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mShowTime += 30;
            if (((long) mShowTime) > TIME) {
                Hide();
            }
        }
    }
}
