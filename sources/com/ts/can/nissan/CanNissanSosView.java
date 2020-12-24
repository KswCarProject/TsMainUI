package com.ts.can.nissan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.KeyDef;

public class CanNissanSosView {
    public static CanNissanSosView mInstance = null;
    Context mContext;
    ImageView mImageView;
    WindowManager mWindowManager;

    public static CanNissanSosView getInstance() {
        if (mInstance == null) {
            mInstance = new CanNissanSosView();
        }
        return mInstance;
    }

    public void initWindow(Context context) {
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = 2010;
        params.flags = KeyDef.RKEY_RADIO_SCAN;
        params.format = 1;
        params.width = -1;
        params.height = -1;
        params.gravity = 51;
        this.mImageView = new ImageView(context);
        this.mWindowManager.addView(this.mImageView, params);
    }

    public void showView(Context context, int flag) {
        Log.d("lh", "showView");
        if (this.mContext == null) {
            this.mContext = context;
            initWindow(this.mContext);
        }
        int resId = 0;
        if (flag == 1) {
            if (isRu(context)) {
                resId = R.drawable.can_sos_call1;
            } else {
                resId = R.drawable.can_sos_call;
            }
        } else if (flag == 3) {
            if (isRu(context)) {
                resId = R.drawable.can_sos_test1;
            } else {
                resId = R.drawable.can_sos_test;
            }
        }
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        if (this.mImageView != null) {
            this.mImageView.setImageBitmap(bitmap);
        }
    }

    public boolean isRu(Context context) {
        if (context.getResources().getConfiguration().locale.getLanguage().endsWith("ru")) {
            return true;
        }
        return false;
    }

    public void hideView() {
        Log.d("lh", "hideView");
        if (this.mWindowManager != null) {
            if (this.mImageView != null) {
                this.mWindowManager.removeView(this.mImageView);
                this.mImageView = null;
            }
            this.mWindowManager = null;
        }
        if (this.mContext != null) {
            this.mContext = null;
        }
    }
}
