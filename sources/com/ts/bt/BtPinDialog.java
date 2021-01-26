package com.ts.bt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;

public class BtPinDialog {
    public static final int FIRST_SYSTEM_WINDOW = 2000;
    public static final String TAG = "BtPinDialog";
    public static final int TYPE_NAVIGATION_BAR = 2019;
    public static final int TYPE_STATUS_BAR_SUB_PANEL = 2017;
    static boolean isShow = false;
    static View mBtPinView;
    static Context mContext;
    static RelativeLayout mRelativeLayout;
    private static long mShowViewTime = 0;
    static TextView mTvPin;
    private static WindowManager.LayoutParams mWindowLayoutParams;
    private static WindowManager mWindowManager;

    public static void initWindow(Context context) {
        Log.d("BtPinDialog", "showWindow");
        if (mContext == null) {
            mContext = context;
            mWindowManager = (WindowManager) context.getSystemService("window");
            mWindowLayoutParams = new WindowManager.LayoutParams();
            mWindowLayoutParams.type = 2003;
            mWindowLayoutParams.flags = 8;
            mWindowLayoutParams.format = 1;
            mWindowLayoutParams.gravity = 17;
            mWindowLayoutParams.width = -1;
            mWindowLayoutParams.height = -1;
            mRelativeLayout = new RelativeLayout(context);
            mBtPinView = LayoutInflater.from(context).inflate(R.layout.boned_pin_layout, (ViewGroup) null);
            mTvPin = (TextView) mBtPinView.findViewById(R.id.tv_pin);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(311, Can.CAN_BENC_ZMYT);
            layoutParams.addRule(13);
            mRelativeLayout.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    Log.d(BtInCallService.TAG, "onTouch");
                    BtPinDialog.hideView();
                    return false;
                }
            });
            mRelativeLayout.addView(mBtPinView, layoutParams);
        }
    }

    public static void showView(String pin) {
        if (!isShow && mRelativeLayout != null) {
            Log.d(BtInCallService.TAG, "showView");
            long curTime = System.currentTimeMillis();
            if (curTime - mShowViewTime > 60000) {
                mShowViewTime = curTime;
                isShow = true;
                mTvPin.setText("PIN: " + pin);
                mWindowManager.addView(mRelativeLayout, mWindowLayoutParams);
            }
        }
    }

    public static void hideView() {
        if (isShow && mRelativeLayout != null) {
            Log.d(BtInCallService.TAG, "hideView");
            mWindowManager.removeView(mRelativeLayout);
            isShow = false;
            mShowViewTime = System.currentTimeMillis();
        }
    }
}
