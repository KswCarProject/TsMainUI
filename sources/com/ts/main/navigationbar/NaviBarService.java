package com.ts.main.navigationbar;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.ts.can.benc.withcd.CanBencWithCDCarInitActivity;
import com.ts.main.common.WinShow;
import com.ts.weather.receiver.IReceiverMsg;
import com.ts.weather.receiver.WeatherReceiver;
import com.ts.weather.receiver.Weathers;

public class NaviBarService extends Service implements IReceiverMsg {
    private static final String ACTION_IME_SHOW_HIDE_CHANGE = "com.android.InputMethodService.showhide";
    private static final String ACTION_LIFE_CYCLE_CHANGE = "com.android.activity.lifecyclechange";
    private static final String ACTION_NAVIBAR_DISMISS = "com.android.launcher.ACTION_NAVIBAR_DISMISS";
    private static final String ACTION_NAVIBAR_SHOW = "com.android.launcher.ACTION_NAVIBAR_SHOW";
    private static final String AUTONAVI_INFO_ACTION = "AUTONAVI_STANDARD_BROADCAST_SEND";
    private static final String DOOR_INFO_ACTION = "com.ts.can.BROADCAST_CAN_INFO";
    public static final int FIRST_SYSTEM_WINDOW = 2000;
    private static final String MAINUI_ACTION_ACTIVITY_START = "com.ts.MainUI.ActivityStart";
    private static final String MAINUI_ACTION_ACTIVITY_STOP = "com.ts.MainUI.ActivityStop";
    private static final String NAVI_BAR_SHOW_BIG = "com.ts.main.navigationBar.SHOW_BIG";
    private static final String NAVI_BAR_SHOW_SMALL = "com.ts.main.navigationBar.SHOW_SMALL";
    private static final String TAG = "NaviBarService";
    public static final int TYPE_NAVIGATION_BAR = 2019;
    public static final int TYPE_STATUS_BAR_SUB_PANEL = 2017;
    public static String mCurrentTopClass;
    public static String mCurrentTopPackage;
    /* access modifiers changed from: private */
    public static FloatView mFloatView;
    private static WindowManager wManager;
    private static WindowManager.LayoutParams wmNaviBarParams;
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(NaviBarService.ACTION_LIFE_CYCLE_CHANGE)) {
                NaviBarService.this.dealLifecycleChange(intent);
            } else if (action.equals(NaviBarService.ACTION_IME_SHOW_HIDE_CHANGE)) {
                String showHide = intent.getStringExtra("showHide");
                if (showHide == null) {
                    return;
                }
                if ("show".equals(showHide)) {
                    NaviBarService.this.removeFloatView();
                } else {
                    NaviBarService.this.dealCreateState();
                }
            } else if ("AUTONAVI_STANDARD_BROADCAST_SEND".equals(action)) {
                if (NaviBarService.mFloatView != null) {
                    NaviBarService.mFloatView.updateNavi(intent);
                }
            } else if (NaviBarService.DOOR_INFO_ACTION.equals(action)) {
                if (intent.getExtras() != null) {
                    int carType = intent.getIntExtra("CAR_DOOR_UI", 0);
                    Log.d("HAHA", "carType = " + carType);
                    if (NaviBarService.mFloatView != null) {
                        NaviBarService.mFloatView.updateDoors(carType);
                    }
                }
            } else if (NaviBarService.ACTION_NAVIBAR_SHOW.equals(action)) {
                NaviBarService.this.addFloatView();
            } else if (NaviBarService.ACTION_NAVIBAR_DISMISS.equals(action)) {
                NaviBarService.this.removeFloatView();
            }
        }
    };

    public void onCreate() {
        wManager = (WindowManager) getApplicationContext().getSystemService("window");
        IntentFilter filter = new IntentFilter("com.ts.MainUI.NaviBar");
        filter.addAction(NAVI_BAR_SHOW_BIG);
        filter.addAction(NAVI_BAR_SHOW_SMALL);
        filter.addAction(MAINUI_ACTION_ACTIVITY_START);
        filter.addAction(MAINUI_ACTION_ACTIVITY_STOP);
        filter.addAction(MAINUI_ACTION_ACTIVITY_STOP);
        filter.addAction(ACTION_LIFE_CYCLE_CHANGE);
        filter.addAction(ACTION_IME_SHOW_HIDE_CHANGE);
        filter.addAction("AUTONAVI_STANDARD_BROADCAST_SEND");
        filter.addAction(DOOR_INFO_ACTION);
        filter.addAction(ACTION_NAVIBAR_SHOW);
        filter.addAction(ACTION_NAVIBAR_DISMISS);
        registerReceiver(this.mReceiver, filter);
        wmNaviBarParams = new WindowManager.LayoutParams(450, -1, 2008, 8651624, 1);
        wmNaviBarParams.setTitle("NavigationBar");
        wmNaviBarParams.windowAnimations = 0;
        wmNaviBarParams.gravity = 3;
        WeatherReceiver.init(getApplicationContext());
        WeatherReceiver.setReceiverMsg(this);
    }

    /* access modifiers changed from: private */
    public void addFloatView() {
        if (mFloatView == null) {
            mFloatView = new FloatView(this);
        }
        if (mFloatView != null && mFloatView.getParent() == null) {
            wManager.addView(mFloatView, wmNaviBarParams);
        }
    }

    /* access modifiers changed from: private */
    public void removeFloatView() {
        if (mFloatView != null && mFloatView.getParent() != null) {
            wManager.removeView(mFloatView);
        }
    }

    /* access modifiers changed from: protected */
    public void dealCreateState() {
        Intent intent = new Intent();
        intent.putExtra("lifecycle", "onResume");
        intent.putExtra("packagename", WinShow.getTopPackName());
        intent.putExtra("classname", WinShow.getTopActivityName());
        Log.d(TAG, "packagename ===== " + WinShow.getTopPackName());
        Log.d(TAG, "classname ===== " + WinShow.getTopActivityName());
        dealLifecycleChange(intent);
    }

    /* access modifiers changed from: protected */
    public void dealLifecycleChange(Intent intent) {
        String lifecycle = intent.getStringExtra("lifecycle");
        String packageName = intent.getStringExtra("packagename");
        String className = intent.getStringExtra("classname");
        Log.d(TAG, "lifecycle = " + lifecycle);
        Log.d(TAG, "packageName = " + packageName);
        Log.d(TAG, "className = " + className);
        if (lifecycle != null && packageName != null && !packageName.startsWith("com.android.launcher")) {
            if (!lifecycle.equals("onStart") && !lifecycle.equals("onResume")) {
                return;
            }
            if ((packageName.equals("com.ts.dvdplayer") && !className.equals("com.ts.dvdplayer.USBActivity")) || className.startsWith("com.ts.bt") || className.equals("com.ts.main.radio.RadioMainActivity")) {
                addFloatView();
            } else if (!className.equals("com.ts.can.benc.withcd.CanBencWithCDExdActivity") || CanBencWithCDCarInitActivity.IsRviewDis() != 0) {
                removeFloatView();
            } else {
                addFloatView();
            }
        }
    }

    public void receiverMsg(int code, String detail, Weathers weathers) {
        if (detail != null) {
            Toast.makeText(this, detail, 0).show();
        }
        if (weathers != null && mFloatView != null) {
            mFloatView.updateWeather(weathers);
        }
    }

    public void onDestroy() {
        unregisterReceiver(this.mReceiver);
        WeatherReceiver.uninit();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
