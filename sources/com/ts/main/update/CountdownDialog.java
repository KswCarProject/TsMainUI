package com.ts.main.update;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.bt.BtInCallService;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CountdownDialog {
    private static final String END_OF_TIME = "com.ts.countdown.END_OF_TIME";
    public static final int FIRST_SYSTEM_WINDOW = 2000;
    public static final String TAG = "BtPinDialog";
    public static final int TYPE_NAVIGATION_BAR = 2019;
    public static final int TYPE_STATUS_BAR_SUB_PANEL = 2017;
    static boolean isShow = false;
    /* access modifiers changed from: private */
    public static Animation mAnimation = null;
    private static Button mBtnCancel = null;
    private static Button mBtnUpdate = null;
    /* access modifiers changed from: private */
    public static CompleteView mCompleteView = null;
    static Context mContext = null;
    /* access modifiers changed from: private */
    public static int mCount = 5;
    static View mCountdownView = null;
    /* access modifiers changed from: private */
    public static Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                CountdownDialog.mTvCount.startAnimation(CountdownDialog.mAnimation);
            }
        }
    };
    private static final int mHeight = 260;
    static RelativeLayout mRelativeLayout = null;
    /* access modifiers changed from: private */
    public static TextView mTvCount = null;
    private static final int mWidth = 524;
    private static WindowManager.LayoutParams mWindowLayoutParams;
    private static WindowManager mWindowManager;

    public static void initWindow(Context context) {
        Log.d("BtPinDialog", "showWindow");
        mContext = context;
        mWindowManager = (WindowManager) context.getSystemService("window");
        mWindowLayoutParams = new WindowManager.LayoutParams();
        mWindowLayoutParams.type = 2003;
        mWindowLayoutParams.flags = 8;
        mWindowLayoutParams.format = 1;
        mWindowLayoutParams.gravity = 17;
        mWindowLayoutParams.width = 524;
        mWindowLayoutParams.height = 260;
        mRelativeLayout = new RelativeLayout(context);
        mCountdownView = LayoutInflater.from(context).inflate(R.layout.countdown_layout, (ViewGroup) null);
        mTvCount = (TextView) mCountdownView.findViewById(R.id.tv_count);
        mBtnCancel = (Button) mCountdownView.findViewById(R.id.btn_cancel);
        mBtnUpdate = (Button) mCountdownView.findViewById(R.id.btn_update);
        mCompleteView = (CompleteView) mCountdownView.findViewById(R.id.completeview);
        mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.scale_anim);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                int access$2 = CountdownDialog.mCount - 1;
                CountdownDialog.mCount = access$2;
                if (access$2 > 0) {
                    animation.reset();
                    CountdownDialog.mTvCount.setText(String.valueOf(CountdownDialog.mCount) + " s");
                    CountdownDialog.mCompleteView.updateProgress((float) CountdownDialog.mCount);
                    CountdownDialog.mHandler.sendEmptyMessage(0);
                    return;
                }
                CountdownDialog.mCompleteView.updateProgress((float) CountdownDialog.mCount);
                CountdownDialog.sendEndOfTimeBroadcast();
                CountdownDialog.hideView();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CountdownDialog.hideView();
            }
        });
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CountdownDialog.sendEndOfTimeBroadcast();
                CountdownDialog.hideView();
            }
        });
        mRelativeLayout.addView(mCountdownView);
    }

    /* access modifiers changed from: private */
    public static void sendEndOfTimeBroadcast() {
        mContext.sendBroadcast(new Intent(END_OF_TIME));
        FtSet.SetUpdateReq(1);
        MainSet.GetInstance().ResetTheFirstFlag();
        Mcu.SendXKey(19);
    }

    public static void showView() {
        try {
            if (!isShow && mRelativeLayout != null) {
                Log.d(BtInCallService.TAG, "showView");
                isShow = true;
                mCount = 5;
                mTvCount.setText(String.valueOf(mCount) + " s");
                mCompleteView.updateProgress((float) mCount);
                mWindowManager.addView(mRelativeLayout, mWindowLayoutParams);
                mTvCount.startAnimation(mAnimation);
            }
        } catch (Exception e) {
            Log.d(BtInCallService.TAG, "showView exception:" + e.toString());
            e.printStackTrace();
        }
    }

    public static void hideView() {
        try {
            if (isShow && mRelativeLayout != null) {
                Log.d(BtInCallService.TAG, "hideView");
                isShow = false;
                mWindowManager.removeViewImmediate(mRelativeLayout);
            }
        } catch (Exception e) {
            Log.d(BtInCallService.TAG, "hideView exception:" + e.toString());
            e.printStackTrace();
        }
    }
}
