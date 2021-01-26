package com.autochips.camera.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private static Toast mToast;

    public static void showToast(Context context, String message) {
        if (context != null && message != null) {
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(context, message, 0);
            mToast.setGravity(81, 0, 0);
            mToast.show();
        }
    }
}
