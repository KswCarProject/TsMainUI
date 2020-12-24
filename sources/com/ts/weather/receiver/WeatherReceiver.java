package com.ts.weather.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherReceiver {
    public static final String REFRESH_TIME = "refresh_time";
    public static final String REFRESH_WEATHER = "android.intent.action.REFRESH_WEATHER";
    private static final String TAG = "weather";
    public static final String UPDATE_LAUNCHER_WEATHER = "android.intent.action.UPDATE_LAUNCHER_WEATHER";
    public static final String WEATHER_STR = "weather_str";
    private static Context mContext;
    private static SharedPreferences mPreferences;
    private static BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (WeatherReceiver.UPDATE_LAUNCHER_WEATHER.equals(intent.getAction())) {
                int code = intent.getIntExtra("code", 102);
                String detail = intent.getStringExtra("detail");
                String weatherStr = intent.getStringExtra("weathers");
                if (weatherStr != null) {
                    WeatherReceiver.setWeatherStr(weatherStr);
                    if (WeatherReceiver.mReceiverMsg != null) {
                        WeatherReceiver.mReceiverMsg.receiverMsg(code, detail, WeatherReceiver.updateWeathers(code, weatherStr));
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public static IReceiverMsg mReceiverMsg;

    public static void setReceiverMsg(IReceiverMsg receiverMsg) {
        mReceiverMsg = receiverMsg;
        String weathers = getWeatherStr();
        if (!TextUtils.isEmpty(weathers)) {
            String refreshTime = getRefreshTime();
            Log.d("__lh__", "refreshTime: " + refreshTime);
            if (!TextUtils.isEmpty(refreshTime)) {
                boolean isExpire = IsExpire(refreshTime);
                Log.d("__lh__", "isExpire: " + isExpire);
                if (isExpire && mReceiverMsg != null) {
                    mReceiverMsg.receiverMsg(0, (String) null, updateWeathers(0, weathers));
                }
            }
        }
    }

    public static void init(Context context) {
        mContext = context;
        if (mPreferences == null) {
            mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        registerUpdateLauncherWeather();
        startAMapService();
    }

    public static void uninit() {
        stopAMapService();
        if (mReceiver != null) {
            mContext.unregisterReceiver(mReceiver);
        }
        mReceiverMsg = null;
        mPreferences = null;
        mContext = null;
    }

    public static boolean IsExpire(String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar pre = Calendar.getInstance();
        pre.setTime(new Date(System.currentTimeMillis()));
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        if (cal.get(1) == pre.get(1)) {
            int diffDay = pre.get(6) - cal.get(6);
            int diffHour = pre.get(11) - cal.get(11);
            if (diffDay == 0) {
                if (diffHour >= 4) {
                    return false;
                }
            } else if (diffDay > 0) {
                return false;
            }
        } else if (cal.get(1) < pre.get(1)) {
            return false;
        }
        return true;
    }

    public static void registerUpdateLauncherWeather() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPDATE_LAUNCHER_WEATHER);
        mContext.registerReceiver(mReceiver, filter);
    }

    public static void setWeatherStr(String weathers) {
        if (mPreferences != null) {
            mPreferences.edit().putString(WEATHER_STR, weathers).apply();
        }
    }

    public static String getWeatherStr() {
        if (mPreferences != null) {
            return mPreferences.getString(WEATHER_STR, "");
        }
        return null;
    }

    public static void refreshWeather() {
        mContext.sendBroadcast(new Intent(REFRESH_WEATHER));
    }

    public static void setRefreshTime(String refreshTime) {
        if (mPreferences != null) {
            mPreferences.edit().putString(REFRESH_TIME, refreshTime).apply();
        }
    }

    public static String getRefreshTime() {
        if (mPreferences != null) {
            return mPreferences.getString(REFRESH_TIME, "");
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static Weathers updateWeathers(int code, String weatherStr) {
        Weathers weathers = new Weathers();
        try {
            JSONObject jsonObject = new JSONObject(weatherStr);
            String adCode = jsonObject.getString("adCode");
            String city = jsonObject.getString("city");
            String humidity = jsonObject.getString("humidity");
            String province = jsonObject.getString("province");
            String reportTime = jsonObject.getString("reportTime");
            String temperature = jsonObject.getString("temperature");
            String weather = jsonObject.getString(TAG);
            String windDirection = jsonObject.getString("windDirection");
            String windPower = jsonObject.getString("windPower");
            weathers.setAdCode(adCode);
            weathers.setCity(city);
            weathers.setHumidity(humidity);
            weathers.setProvince(province);
            weathers.setReportTime(reportTime);
            weathers.setTemperature(temperature);
            weathers.setWeather(weather);
            weathers.setWindDirection(windDirection);
            weathers.setWindPower(windPower);
            if (code != 0) {
                return weathers;
            }
            setRefreshTime(jsonObject.getString("refreshTime"));
            return weathers;
        } catch (JSONException e) {
            Log.d(TAG, e.toString());
            e.printStackTrace();
            return null;
        }
    }

    public static void startAMapService() {
        Log.d("__lh__", "startAMapService");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.ts.weather", "com.ts.weather.AMapTestService"));
        mContext.startService(intent);
    }

    public static void stopAMapService() {
        Log.d("__lh__", "stopAMapService");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.ts.weather", "com.ts.weather.AMapTestService"));
        mContext.stopService(intent);
    }
}
