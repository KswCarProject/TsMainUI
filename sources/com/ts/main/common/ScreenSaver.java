package com.ts.main.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.StSet;
import java.util.Calendar;

public class ScreenSaver {
    public static final String ACTION_LIFE_CYCLE_CHANGE = "com.android.activity.lifecyclechange";
    static boolean bScrAdd = false;
    private static Calendar calendar;
    private static ImageView mColon;
    private static Context mContext;
    /* access modifiers changed from: private */
    public static Handler mHandler;
    private static ImageView mHour;
    private static ImageView mHourUnit;
    private static ImageView mMinute;
    private static ImageView mMinuteUnit;
    private static Runnable mScreenSaveRunnable = new Runnable() {
        public void run() {
            if (ScreenSaver.mScreenSaveTime != 0) {
                if (ScreenSaver.timeCount * 100 == ScreenSaver.mScreenSaveTime * 1000) {
                    ScreenSaver.showScreenSaveWin();
                }
                ScreenSaver.timeCount = ScreenSaver.timeCount + 1;
            }
            if (ScreenSaver.timeCount * 100 >= ScreenSaver.mScreenSaveTime * 1000) {
                ScreenSaver.updateClock();
            }
            ScreenSaver.mHandler.postDelayed(this, 100);
        }
    };
    /* access modifiers changed from: private */
    public static int mScreenSaveTime = 0;
    private static ViewGroup mScreenSaverView;
    private static int mnHour;
    private static int mnMinute;
    private static TextView mtvAM;
    private static TextView mtvDate;
    private static TextView mtvPM;
    /* access modifiers changed from: private */
    public static int timeCount = 0;
    private static String timeFormat;
    private static int[] timeNum = {R.drawable.clock_digital_num00, R.drawable.clock_digital_num01, R.drawable.clock_digital_num02, R.drawable.clock_digital_num03, R.drawable.clock_digital_num04, R.drawable.clock_digital_num05, R.drawable.clock_digital_num06, R.drawable.clock_digital_num07, R.drawable.clock_digital_num08, R.drawable.clock_digital_num09};
    private static WindowManager wm;
    private static WindowManager.LayoutParams wmParams;
    BroadcastReceiver mLifecycleReceiver = new BroadcastReceiver() {
        public void onReceive(Context arg0, Intent intent) {
            ScreenSaver.this.dealLifecycleChange(intent);
        }
    };

    public ScreenSaver(Context context) {
        mContext = context;
        mHandler = new Handler();
        mContext.registerReceiver(this.mLifecycleReceiver, new IntentFilter(ACTION_LIFE_CYCLE_CHANGE));
        initScreenSaveWin();
    }

    /* access modifiers changed from: protected */
    public void dealLifecycleChange(Intent intent) {
        String lifecycle = intent.getStringExtra("lifecycle");
        String packageName = intent.getStringExtra("packagename");
        String className = intent.getStringExtra("classname");
        if (lifecycle.toLowerCase().equals("onresume")) {
            mHandler.removeCallbacks(mScreenSaveRunnable);
            clearTimeCount();
            if ((packageName.startsWith("com.android.launcher") || ((packageName.startsWith("com.ts.MainUI") && !className.equals("com.ts.backcar.BackcarMainActivity") && !className.equals("com.ts.main.avin.AvinMainActivity")) || ((packageName.endsWith("com.ts.dvdplayer") && !className.equals("com.ts.dvdplayer.USBActivity") && !className.equals("com.ts.dvdplayer.dvd.DVDVideoActivity")) || packageName.equals("com.android.settings")))) && StSet.GetScreenTimeout() > 0) {
                mScreenSaveTime = StSet.GetScreenTimeout();
                mHandler.post(mScreenSaveRunnable);
            }
        }
    }

    private void initScreenSaveWin() {
        wm = (WindowManager) mContext.getSystemService("window");
        wmParams = new WindowManager.LayoutParams(2010);
        wmParams.format = 3;
        wmParams.x = 0;
        wmParams.y = 0;
        wmParams.flags = 1024;
        wmParams.systemUiVisibility = 5894;
        initUI();
    }

    private void initUI() {
        mScreenSaverView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.screen_saver, (ViewGroup) null);
        mHour = (ImageView) mScreenSaverView.findViewById(R.id.hour);
        mHourUnit = (ImageView) mScreenSaverView.findViewById(R.id.hour_unit);
        mColon = (ImageView) mScreenSaverView.findViewById(R.id.clock_colon);
        mMinute = (ImageView) mScreenSaverView.findViewById(R.id.minute);
        mMinuteUnit = (ImageView) mScreenSaverView.findViewById(R.id.minute_unit);
        mtvAM = (TextView) mScreenSaverView.findViewById(R.id.text_am);
        mtvPM = (TextView) mScreenSaverView.findViewById(R.id.text_pm);
        mtvDate = (TextView) mScreenSaverView.findViewById(R.id.clock_date);
        timeFormat = Settings.System.getString(mContext.getContentResolver(), "time_12_24");
        updateClock();
    }

    /* access modifiers changed from: private */
    public static void updateClock() {
        calendar = Calendar.getInstance();
        int minute = calendar.get(12);
        if (timeFormat == null || !timeFormat.equals("24")) {
            int hour = calendar.get(10);
            if (hour == 0) {
                hour = 12;
            }
            if (hour / 10 == 0) {
                mHour.setVisibility(4);
            } else {
                mHour.setVisibility(0);
            }
            mHour.setImageResource(timeNum[(hour / 10) % 10]);
            if (calendar.get(9) == 0) {
                mtvAM.setVisibility(0);
                mtvPM.setVisibility(4);
            } else {
                mtvPM.setVisibility(0);
                mtvAM.setVisibility(4);
            }
            mHourUnit.setImageResource(timeNum[hour % 10]);
            mnHour = hour;
        } else {
            int hour2 = calendar.get(11);
            mHour.setVisibility(0);
            mHour.setImageResource(timeNum[(hour2 / 10) % 10]);
            mHourUnit.setImageResource(timeNum[hour2 % 10]);
            mnHour = hour2;
            mtvAM.setVisibility(4);
            mtvPM.setVisibility(4);
        }
        if (mnMinute != minute) {
            mMinute.setImageResource(timeNum[(minute / 10) % 10]);
            mMinuteUnit.setImageResource(timeNum[minute % 10]);
            mnMinute = minute;
            mtvDate.setText(String.format("%04d.%02d.%02d %s", new Object[]{Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5)), calendar.getDisplayName(7, 2, mContext.getResources().getConfiguration().locale)}));
        }
        try {
            wm.updateViewLayout(mScreenSaverView, wmParams);
        } catch (Exception e) {
        }
    }

    public static void clearTimeCount() {
        if (bScrAdd) {
            dismissScreenWin();
            bScrAdd = false;
        }
        timeCount = 0;
    }

    /* access modifiers changed from: private */
    public static void showScreenSaveWin() {
        timeFormat = Settings.System.getString(mContext.getContentResolver(), "time_12_24");
        try {
            wm.addView(mScreenSaverView, wmParams);
            bScrAdd = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startScreenCount() {
        mScreenSaveTime = StSet.GetScreenTimeout();
        mHandler.post(mScreenSaveRunnable);
    }

    public static void dismissScreenWin() {
        try {
            wm.removeView(mScreenSaverView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
