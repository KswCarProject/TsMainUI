package com.ts.main.navigationbar;

import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanIF;
import com.ts.can.benc.withcd.CanBencWithCDCarFuncActivity;
import com.ts.main.common.WinShow;
import com.ts.main.txz.AmapAuto;
import com.ts.weather.receiver.WeatherReceiver;
import com.ts.weather.receiver.Weathers;
import com.txznet.sdk.TXZConfigManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FloatView extends FrameLayout implements View.OnTouchListener, View.OnClickListener {
    private static final String Can_Factory_Set_fileName = "Can_Factory_Set.bin";
    private static final String Crash_PATH = "/mnt/sdcard/TsCarInfo/";
    private static final String LEFT_LIGHT = "left_light";
    private static final String PAGE_INDEX = "page_index";
    private static final String RIGHT_LIGHT = "right_light";
    private int[] icons;
    private boolean is12;
    /* access modifiers changed from: private */
    public boolean isAutoScroll;
    /* access modifiers changed from: private */
    public boolean isCarLuHu;
    private boolean isDoorUpdate;
    private boolean isRightOpen;
    /* access modifiers changed from: private */
    public boolean isSwitch;
    private ImageView ivDirectionIcon;
    private ImageView ivNaviIcon;
    private ObjectAnimator mAnimator;
    private int[][] mBigCarIcons;
    private View mBtnAirBag;
    private View mBtnCarUp;
    private View mBtnPOff;
    private Button mBtnRefresh;
    private View mBtnSport;
    private int[] mCarIcons;
    private int mCarType;
    private TextView mDate;
    private String mDateTimeStr;
    private int[] mDoorArr;
    private int[][][] mDoorIcons;
    private CanDataInfo.CAN_DoorInfo mDoorInfo;
    private String mExtensionStr;
    /* access modifiers changed from: private */
    public Handler mHandler;
    private ImageView mIvCar;
    private ImageView mIvClockHour;
    private ImageView mIvClockMinute;
    private ImageView[] mIvDoors;
    private ImageView mIvHourDecade;
    private ImageView mIvHourUnit;
    private ImageView mIvMinDecade;
    private ImageView mIvMinUnit;
    private ImageView mIvWeather;
    private int[][] mLFDoorIcons;
    private int[][] mLRDoorIcons;
    private int mLastPage;
    private CanDataInfo.CanBcZmytLed mLedInfo;
    private View mLeftKey;
    private int mLtLight;
    private CanDataInfo.CAN_Msg mMsgData;
    private NaviInfo mNaviInfo;
    /* access modifiers changed from: private */
    public View mOffEsp;
    /* access modifiers changed from: private */
    public List<View> mPageList;
    /* access modifiers changed from: private */
    public PagerAdapter mPagerAdapter;
    /* access modifiers changed from: private */
    public SharedPreferences mPreferences;
    private int[][] mRFDoorIcons;
    private int[][] mRRDoorIcons;
    private int[][] mRearDoorIcons;
    private String mRecordStr;
    private View mRightKey;
    private int mRtLight;
    /* access modifiers changed from: private */
    public Runnable mRunnable;
    private String mSpeedStr;
    private TextView mTvCity;
    private TextView mTvDateTime;
    private TextView mTvRecordMile;
    private TextView mTvSpeed;
    private TextView mTvTemperature;
    private TextView mTvTimeAp;
    private TextView mTvWeather;
    private ViewPager mViewPager;
    private int[] mWeatherIds;
    private List<String> mWeatherTypeList;
    private String[] mWeatherTypes;
    private TextView mWeek;
    private String[] mWeekDays;
    private int[] nums;
    private RelativeLayout show_time;
    private TextView tvCurRoad;
    private TextView tvNextRoad;
    private TextView tvRemainDistance;
    private TextView tvRoundNumber;

    public FloatView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.nums = new int[]{R.drawable.main_time_num00, R.drawable.main_time_num01, R.drawable.main_time_num02, R.drawable.main_time_num03, R.drawable.main_time_num04, R.drawable.main_time_num05, R.drawable.main_time_num06, R.drawable.main_time_num07, R.drawable.main_time_num08, R.drawable.main_time_num09};
        this.is12 = false;
        this.mWeatherTypeList = new ArrayList();
        this.mWeatherIds = new int[]{R.drawable.weather_qing, R.drawable.weather_duoyun, R.drawable.weather_yin, R.drawable.weather_zhenyu, R.drawable.weather_leizhenyu, R.drawable.weather_leizhenyubanbinbao, R.drawable.weather_yujiaoxue, R.drawable.weather_xiaoyu, R.drawable.weather_zhongyu, R.drawable.weather_dayu, R.drawable.weather_baoyu, R.drawable.weather_dabaoyu, R.drawable.weather_tedabaoyu, R.drawable.weather_zhenxue, R.drawable.weather_xiaoxue, R.drawable.weather_zhongxue, R.drawable.weather_daxue, R.drawable.weather_baoxue, R.drawable.weather_wu, R.drawable.weather_dongyu, R.drawable.weather_shachenbao, R.drawable.weather_xiaoyu_zhongyu, R.drawable.weather_zhongyu_dayu, R.drawable.weather_dayu_baoyu, R.drawable.weather_baoyu_dabaoyu, R.drawable.weather_dabaoyu_tedabaoyu, R.drawable.weather_xiaoxue_zhongxue, R.drawable.weather_zhongxue_daxue, R.drawable.weather_daxue_baoxue, R.drawable.weather_fuchen, R.drawable.weather_yangsha, R.drawable.weather_qiangshachenbao, R.drawable.weather_biao, R.drawable.weather_longjuanfeng, R.drawable.weather_ruogaochuixue, R.drawable.weather_qingmai, R.drawable.weather_mai};
        this.mDoorArr = new int[5];
        this.mCarIcons = new int[]{R.drawable.main_small_car01, R.drawable.main_small_car03, R.drawable.main_small_car02, R.drawable.main_small_car04, R.drawable.main_small_car05, R.drawable.main_small_car06, R.drawable.main_car01_02, R.drawable.main_small_car03};
        this.mBigCarIcons = new int[][]{new int[]{R.drawable.main_car01_02, R.drawable.main_car03_02, R.drawable.main_car02_02, R.drawable.main_car04_02, R.drawable.main_car05_02, R.drawable.main_car06_02, R.drawable.main_car01_02, R.drawable.main_car08_02}, new int[]{R.drawable.main_car01_01, R.drawable.main_car03_01, R.drawable.main_car02_01, R.drawable.main_car04_01, R.drawable.main_car05_01, R.drawable.main_car06_01, R.drawable.main_car01_02, R.drawable.main_car08_01}};
        this.mLFDoorIcons = new int[][]{new int[]{R.drawable.main_car01_door01, R.drawable.main_car03_door01, R.drawable.main_car02_door01, R.drawable.main_car04_door01, R.drawable.main_car05_door01, R.drawable.main_car06_door01, R.drawable.main_car01_02, R.drawable.main_car08_door01}, new int[]{R.drawable.main_small_car01_01, R.drawable.main_small_car03_01, R.drawable.main_small_car02_01, R.drawable.main_small_car04_01, R.drawable.main_small_car05_01, R.drawable.main_small_car06_01, R.drawable.main_car01_02, R.drawable.main_small_car03_01}};
        this.mRFDoorIcons = new int[][]{new int[]{R.drawable.main_car01_door03, R.drawable.main_car03_door03, R.drawable.main_car02_door02, R.drawable.main_car04_door03, R.drawable.main_car05_door03, R.drawable.main_car06_door03, R.drawable.main_car01_02, R.drawable.main_car08_door03}, new int[]{R.drawable.main_small_car01_03, R.drawable.main_small_car03_03, R.drawable.main_small_car02_02, R.drawable.main_small_car04_03, R.drawable.main_small_car05_03, R.drawable.main_small_car06_03, R.drawable.main_car01_02, R.drawable.main_small_car03_03}};
        int[] iArr = new int[8];
        iArr[0] = R.drawable.main_car01_door02;
        iArr[1] = R.drawable.main_car03_door02;
        iArr[3] = R.drawable.main_car04_door02;
        iArr[4] = R.drawable.main_car05_door02;
        iArr[5] = R.drawable.main_car06_door02;
        iArr[6] = R.drawable.main_car01_02;
        iArr[7] = R.drawable.main_car08_door02;
        int[] iArr2 = new int[8];
        iArr2[0] = R.drawable.main_small_car01_02;
        iArr2[1] = R.drawable.main_small_car03_02;
        iArr2[3] = R.drawable.main_small_car04_02;
        iArr2[4] = R.drawable.main_small_car05_02;
        iArr2[5] = R.drawable.main_small_car06_02;
        iArr2[6] = R.drawable.main_car01_02;
        iArr2[7] = R.drawable.main_small_car03_02;
        this.mLRDoorIcons = new int[][]{iArr, iArr2};
        int[] iArr3 = new int[8];
        iArr3[0] = R.drawable.main_car01_door04;
        iArr3[1] = R.drawable.main_car03_door04;
        iArr3[3] = R.drawable.main_car04_door04;
        iArr3[4] = R.drawable.main_car05_door04;
        iArr3[5] = R.drawable.main_car06_door04;
        iArr3[6] = R.drawable.main_car01_02;
        iArr3[7] = R.drawable.main_car08_door04;
        int[] iArr4 = new int[8];
        iArr4[0] = R.drawable.main_small_car01_04;
        iArr4[1] = R.drawable.main_small_car03_04;
        iArr4[3] = R.drawable.main_small_car04_04;
        iArr4[4] = R.drawable.main_small_car05_04;
        iArr4[5] = R.drawable.main_small_car06_04;
        iArr4[6] = R.drawable.main_car01_02;
        iArr4[7] = R.drawable.main_small_car03_04;
        this.mRRDoorIcons = new int[][]{iArr3, iArr4};
        this.mRearDoorIcons = new int[][]{new int[]{R.drawable.main_car01_door05, R.drawable.main_car03_door05, R.drawable.main_car02_door03, R.drawable.main_car04_door05, R.drawable.main_car05_door05, R.drawable.main_car06_door05, R.drawable.main_car01_02, R.drawable.main_car08_door05}, new int[]{R.drawable.main_small_car01_05, R.drawable.main_small_car03_05, R.drawable.main_small_car02_03, R.drawable.main_small_car04_05, R.drawable.main_small_car05_05, R.drawable.main_small_car06_05, R.drawable.main_car01_02, R.drawable.main_small_car03_05}};
        this.mDoorInfo = new CanDataInfo.CAN_DoorInfo();
        this.mMsgData = new CanDataInfo.CAN_Msg();
        this.mLedInfo = new CanDataInfo.CanBcZmytLed();
        this.mNaviInfo = new NaviInfo();
        this.icons = new int[]{R.drawable.right_navi_toleft, R.drawable.right_navi_toleft, R.drawable.right_navi_toright, R.drawable.right_navi_l_ahead, R.drawable.right_navi_r_ahead, R.drawable.right_navi_l_behind, R.drawable.right_navi_r_behind, R.drawable.right_navi_turnabout, R.drawable.right_navi_straight, R.drawable.right_navi_toright, R.drawable.right_navi_toright, R.drawable.right_navi_toright, R.drawable.right_navi_service, R.drawable.right_navi_toll, R.drawable.right_navi_terminal, R.drawable.right_navi_tunnel, R.drawable.right_navi_toright, R.drawable.right_navi_toright, R.drawable.right_navi_r_behind, R.drawable.right_navi_straight};
        this.mRunnable = new Runnable() {
            public void run() {
                FloatView.this.isSwitch = !FloatView.this.isSwitch;
                FloatView.this.switchCarSet(FloatView.this.isSwitch);
                FloatView.this.sendLightCmd();
                FloatView.this.mPreferences.edit().putBoolean("switch", FloatView.this.isSwitch).apply();
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(Message msg) {
                FloatView.this.setDate();
                FloatView.this.initDateTime();
                FloatView.this.ResetData(true);
                FloatView.this.mHandler.sendEmptyMessageDelayed(0, 500);
            }
        };
        this.mPagerAdapter = new PagerAdapter() {
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            public int getCount() {
                if (FloatView.this.isCarLuHu) {
                    return 1;
                }
                return FloatView.this.mPageList.size();
            }

            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) FloatView.this.mPageList.get(position));
            }

            public Object instantiateItem(ViewGroup container, int position) {
                container.addView((View) FloatView.this.mPageList.get(position));
                return FloatView.this.mPageList.get(position);
            }
        };
        updateWeatherType();
        initPages(context);
        initViews(context);
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.mLtLight = this.mPreferences.getInt(LEFT_LIGHT, 48);
        this.mRtLight = this.mPreferences.getInt(RIGHT_LIGHT, 48);
        this.isSwitch = this.mPreferences.getBoolean("switch", false);
        sendLightCmd();
    }

    /* access modifiers changed from: private */
    public void sendLightCmd() {
        if (this.isSwitch) {
            CanJni.BencZmytWithCDIllSet(0, 0, 1);
            return;
        }
        Log.d("HAHA", "mLtLight = " + this.mLtLight);
        CanJni.BencZmytWithCDIllSet(this.mLtLight, this.mRtLight, 0);
    }

    private void updateWeatherType() {
        this.mWeatherTypes = getResources().getStringArray(R.array.weather_type);
        for (String add : this.mWeatherTypes) {
            this.mWeatherTypeList.add(add);
        }
    }

    private void initPages(Context context) {
        this.mPageList = new ArrayList();
        View view = View.inflate(context, R.layout.door_page_item, (ViewGroup) null);
        this.mIvCar = (ImageView) view.findViewById(R.id.iv_car);
        int[] ids = {R.id.iv_lf_door, R.id.iv_rf_door, R.id.iv_lr_door, R.id.iv_rr_door, R.id.iv_rear_door};
        this.mIvDoors = new ImageView[ids.length];
        for (int i = 0; i < ids.length; i++) {
            this.mIvDoors[i] = (ImageView) view.findViewById(ids[i]);
        }
        this.mDoorIcons = new int[][][]{this.mLFDoorIcons, this.mRFDoorIcons, this.mLRDoorIcons, this.mRRDoorIcons, this.mRearDoorIcons};
        this.mPageList.add(view);
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        View view2 = View.inflate(context, R.layout.weather_page_item, (ViewGroup) null);
        this.mIvWeather = (ImageView) view2.findViewById(R.id.iv_weather);
        this.mBtnRefresh = (Button) view2.findViewById(R.id.btn_refresh);
        this.mBtnRefresh.setOnClickListener(this);
        this.mTvCity = (TextView) view2.findViewById(R.id.tv_city);
        this.mTvWeather = (TextView) view2.findViewById(R.id.tv_weather);
        this.mTvTemperature = (TextView) view2.findViewById(R.id.tv_temperature);
        this.mIvHourDecade = (ImageView) view2.findViewById(R.id.time1);
        this.mIvHourUnit = (ImageView) view2.findViewById(R.id.time2);
        this.mIvMinDecade = (ImageView) view2.findViewById(R.id.time3);
        this.mIvMinUnit = (ImageView) view2.findViewById(R.id.time4);
        this.mWeek = (TextView) view2.findViewById(R.id.week);
        this.mDate = (TextView) view2.findViewById(R.id.date);
        this.mTvTimeAp = (TextView) view2.findViewById(R.id.time_ap);
        this.show_time = (RelativeLayout) view2.findViewById(R.id.show_time);
        this.show_time.setOnClickListener(this);
        if ("zh".equals(language) && "CN".equals(country)) {
            this.mPageList.add(view2);
        }
        View view3 = View.inflate(context, R.layout.carinfo_page_item, (ViewGroup) null);
        this.mTvRecordMile = (TextView) view3.findViewById(R.id.tv_record_mile);
        this.mTvSpeed = (TextView) view3.findViewById(R.id.tv_speed);
        this.mPageList.add(view3);
        View view4 = View.inflate(context, R.layout.clock_page_item, (ViewGroup) null);
        this.mIvClockHour = (ImageView) view4.findViewById(R.id.iv_clock_hour);
        this.mIvClockMinute = (ImageView) view4.findViewById(R.id.iv_clock_minute);
        this.mTvDateTime = (TextView) view4.findViewById(R.id.tv_date_time);
        this.mPageList.add(view4);
        View view5 = View.inflate(context, R.layout.navi_page_item, (ViewGroup) null);
        view5.findViewById(R.id.navi_root).setOnClickListener(this);
        this.tvCurRoad = (TextView) view5.findViewById(R.id.tv_current_load);
        this.tvNextRoad = (TextView) view5.findViewById(R.id.tv_next_load);
        this.tvRemainDistance = (TextView) view5.findViewById(R.id.tv_distance);
        this.tvRoundNumber = (TextView) view5.findViewById(R.id.tv_round_number);
        this.ivDirectionIcon = (ImageView) view5.findViewById(R.id.iv_navi_direction);
        this.ivNaviIcon = (ImageView) view5.findViewById(R.id.iv_navi_gps);
        if ("zh".equals(language) && "CN".equals(country)) {
            this.mPageList.add(view5);
        }
        View view6 = View.inflate(context, R.layout.carset_page_item, (ViewGroup) null);
        View bg = view6.findViewById(R.id.car_set_bg);
        View ltAdd = view6.findViewById(R.id.btn_lt_add);
        View ltSub = view6.findViewById(R.id.btn_lt_sub);
        View rtAdd = view6.findViewById(R.id.btn_rt_add);
        View rtSub = view6.findViewById(R.id.btn_rt_sub);
        this.mBtnCarUp = view6.findViewById(R.id.btn_car_set);
        this.mBtnSport = view6.findViewById(R.id.btn_sport);
        this.mBtnPOff = view6.findViewById(R.id.btn_poff);
        this.mBtnAirBag = view6.findViewById(R.id.btn_air_bag);
        this.mOffEsp = view6.findViewById(R.id.btn_off_esp);
        this.mLeftKey = view6.findViewById(R.id.btn_left_key);
        this.mRightKey = view6.findViewById(R.id.btn_right_key);
        ltAdd.setOnClickListener(this);
        ltSub.setOnClickListener(this);
        rtAdd.setOnClickListener(this);
        rtSub.setOnClickListener(this);
        bg.setOnTouchListener(this);
        this.mBtnCarUp.setOnTouchListener(this);
        this.mBtnSport.setOnTouchListener(this);
        this.mBtnPOff.setOnTouchListener(this);
        this.mOffEsp.setOnTouchListener(this);
        this.mLeftKey.setOnTouchListener(this);
        this.mRightKey.setOnTouchListener(this);
        this.mPageList.add(view6);
    }

    /* access modifiers changed from: private */
    public void switchCarSet(boolean isSwitch2) {
        if (isSwitch2) {
            this.mOffEsp.setVisibility(0);
            this.mLeftKey.setVisibility(0);
            this.mRightKey.setVisibility(0);
            this.mBtnSport.setVisibility(8);
            return;
        }
        this.mOffEsp.setVisibility(8);
        this.mLeftKey.setVisibility(8);
        this.mRightKey.setVisibility(8);
        this.mBtnSport.setVisibility(0);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.float_view_layout, this, true);
        this.mViewPager = (ViewPager) findViewById(R.id.view_pager);
        this.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int page) {
                FloatView.this.mPreferences.edit().putInt(FloatView.PAGE_INDEX, page).apply();
                FloatView.this.isAutoScroll = false;
                if (page == FloatView.this.mPagerAdapter.getCount() - 1) {
                    CanJni.BencZmytWithCDQuery(1);
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
                FloatView.this.mOffEsp.removeCallbacks(FloatView.this.mRunnable);
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
        byte[] fileMsg = new byte[8];
        GetFileData("Can_Factory_Set.bin", fileMsg);
        updateDoors(fileMsg[2]);
        this.mWeekDays = getResources().getStringArray(R.array.WeekDay);
        this.mExtensionStr = getResources().getString(R.string.extension_mile);
        this.mRecordStr = getResources().getString(R.string.record_mile);
        this.mSpeedStr = getResources().getString(R.string.speed);
        this.mDateTimeStr = getResources().getString(R.string.date_time);
        showNaviView(false);
    }

    public static int GetFileData(String sPath, byte[] Buf) {
        int nReadNum = 0;
        File file = new File(Crash_PATH + sPath);
        if (!file.exists() || file.isDirectory()) {
            return 0;
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            nReadNum = fis.read(Buf);
            fis.close();
            return nReadNum;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return nReadNum;
        } catch (IOException e2) {
            e2.printStackTrace();
            return nReadNum;
        }
    }

    public void onClick(View v) {
        int i = 10;
        int id = v.getId();
        if (id == R.id.btn_lt_sub) {
            if (this.isSwitch) {
                CanJni.BencZmytWithCDIllSet(Can.CAN_MG_ZS_WC, 0, 1);
                return;
            }
            this.mLtLight -= 10;
            if (this.mLtLight >= 10) {
                i = this.mLtLight;
            }
            this.mLtLight = i;
            CanJni.BencZmytWithCDIllSet(this.mLtLight, this.mRtLight, 0);
            saveLightValue();
        } else if (id == R.id.btn_lt_add) {
            if (this.isSwitch) {
                CanJni.BencZmytWithCDIllSet(5, 0, 1);
                return;
            }
            this.mLtLight += 10;
            this.mLtLight = this.mLtLight > 250 ? 250 : this.mLtLight;
            CanJni.BencZmytWithCDIllSet(this.mLtLight, this.mRtLight, 0);
            saveLightValue();
        } else if (id == R.id.btn_rt_add) {
            setLight(getLight() + 1);
        } else if (id == R.id.btn_rt_sub) {
            setLight(getLight() - 1);
        } else if (id == R.id.btn_refresh) {
            animRefresh(v);
            WeatherReceiver.refreshWeather();
        } else if (id == R.id.show_time) {
            enterSetDate();
        } else if (id == R.id.navi_root) {
            WinShow.GotoWin(1, 0);
        }
    }

    private int getLight() {
        if (Mcu.GetIll() == 1) {
            return StSet.GetBLNight();
        }
        return StSet.GetBLDay();
    }

    private void setLight(int light) {
        if (light >= 0 && light <= 6) {
            if (Mcu.GetIll() == 1) {
                StSet.SetBLNight(light);
            } else {
                StSet.SetBLDay(light);
            }
        }
    }

    private void animRefresh(View v) {
        if (this.mAnimator != null) {
            this.mAnimator.cancel();
        }
        this.mAnimator = ObjectAnimator.ofFloat(v, "rotation", new float[]{0.0f, 360.0f});
        this.mAnimator.setDuration(1000);
        this.mAnimator.start();
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = v.getId();
        int action = event.getAction();
        if (action == 0) {
            return sendKey(id, 1);
        }
        if (action == 1) {
            return sendKey(id, 0);
        }
        return false;
    }

    private boolean sendKey(int id, int key) {
        if (id == R.id.btn_car_set) {
            CanJni.BencZmytWithCDSendKey(3, key);
        } else if (id == R.id.btn_sport) {
            CanJni.BencZmytWithCDSendKey(2, key);
        } else if (id == R.id.btn_poff) {
            CanJni.BencZmytWithCDSendKey(1, key);
        } else if (id == R.id.btn_off_esp) {
            CanJni.BencZmytWithCDSendKey(4, key);
        } else if (id == R.id.btn_left_key) {
            CanJni.BencZmytWithCDSendKey(5, key);
        } else if (id == R.id.btn_right_key) {
            CanJni.BencZmytWithCDSendKey(6, key);
        } else if (id == R.id.car_set_bg) {
            if (key == 1) {
                this.mOffEsp.postDelayed(this.mRunnable, 5000);
                return true;
            } else if (key == 0) {
                this.mOffEsp.removeCallbacks(this.mRunnable);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        initData();
        ResetData(false);
        this.mHandler.sendEmptyMessage(0);
    }

    private void initData() {
        this.mTvRecordMile.setText(String.valueOf(this.mRecordStr) + "  ---");
        this.mTvSpeed.setText(String.valueOf(this.mSpeedStr) + "  ---");
        switchCarSet(this.isSwitch);
        this.mViewPager.setCurrentItem(this.mPreferences.getInt(PAGE_INDEX, 0));
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAnimator != null) {
            this.mAnimator.cancel();
            this.mAnimator = null;
        }
        this.mHandler.removeCallbacksAndMessages((Object) null);
        SharedPreferences.Editor editor = this.mPreferences.edit();
        editor.putInt(PAGE_INDEX, this.mViewPager.getCurrentItem());
        editor.apply();
    }

    private void saveLightValue() {
        SharedPreferences.Editor editor = this.mPreferences.edit();
        editor.putInt(LEFT_LIGHT, this.mLtLight);
        editor.putInt(RIGHT_LIGHT, this.mRtLight);
        editor.apply();
    }

    /* access modifiers changed from: private */
    public void initDateTime() {
        Calendar c = Calendar.getInstance();
        int year = c.get(1);
        int month = c.get(2) + 1;
        int day = c.get(5);
        int i = c.get(7);
        int hour = c.get(10);
        int minute = c.get(12);
        int second = c.get(13);
        getResources();
        String week = Calendar.getInstance().getDisplayName(7, 2, Resources.getSystem().getConfiguration().locale);
        this.mTvDateTime.setText(String.format(this.mDateTimeStr, new Object[]{Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day), "      " + week}));
        this.mIvClockHour.setRotation((30.0f * ((float) hour)) + (0.5f * ((float) minute)));
        this.mIvClockMinute.setRotation((6.0f * ((float) minute)) + (0.1f * ((float) second)));
    }

    /* access modifiers changed from: private */
    public void setDate() {
        String day;
        int h;
        int m;
        String timestr;
        if (getContext() != null) {
            ContentResolver mResolver = getContext().getContentResolver();
            Calendar mCalendar = Calendar.getInstance();
            if (mResolver != null) {
                String timeFormat = Settings.System.getString(mResolver, "time_12_24");
                if (timeFormat == null) {
                    this.is12 = true;
                    h = mCalendar.get(10);
                    m = mCalendar.get(12);
                    if (h == 0) {
                        h = 12;
                    }
                } else if (timeFormat.equals("24")) {
                    this.is12 = false;
                    h = mCalendar.get(11);
                    m = mCalendar.get(12);
                } else {
                    this.is12 = true;
                    h = mCalendar.get(10);
                    m = mCalendar.get(12);
                    if (h == 0) {
                        h = 12;
                    }
                }
                if (this.is12) {
                    if (mCalendar.get(9) == 0) {
                        timestr = getResources().getString(R.string.time_am);
                    } else {
                        timestr = getResources().getString(R.string.time_pm);
                    }
                    this.mTvTimeAp.setText(timestr);
                } else {
                    this.mIvHourDecade.setVisibility(0);
                    this.mTvTimeAp.setText("");
                }
                if (this.mIvHourDecade != null) {
                    this.mIvHourDecade.setBackgroundResource(this.nums[(h / 10) % 10]);
                }
                if (this.mIvHourUnit != null) {
                    this.mIvHourUnit.setBackgroundResource(this.nums[(h / 1) % 10]);
                }
                if (this.mIvMinDecade != null) {
                    this.mIvMinDecade.setBackgroundResource(this.nums[(m / 10) % 10]);
                }
                if (this.mIvMinUnit != null) {
                    this.mIvMinUnit.setBackgroundResource(this.nums[(m / 1) % 10]);
                }
            }
        }
        Time time = new Time();
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int day1 = time.monthDay;
        if (day1 < 10) {
            day = "0" + day1;
        } else {
            day = new StringBuilder().append(day1).toString();
        }
        getResources();
        this.mWeek.setText(Calendar.getInstance().getDisplayName(7, 2, Resources.getSystem().getConfiguration().locale));
        if (month + 1 < 10) {
            this.mDate.setText(String.valueOf(year) + "-0" + (month + 1) + "-" + day);
        } else {
            this.mDate.setText(String.valueOf(year) + "-" + (month + 1) + "-" + day);
        }
    }

    private void enterSetDate() {
        try {
            Intent intent = new Intent("android.settings.DATE_SETTINGS");
            intent.addFlags(268435456);
            if (intent != null && getContext() != null) {
                getContext().startActivity(intent);
            }
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: private */
    public void ResetData(boolean check) {
        String disUnit;
        int distance;
        String unit;
        int speed;
        if (this.mCarType != 5) {
            CanJni.GetCarInfo(this.mMsgData);
            if (this.mMsgData.UpdateOnce == 1 && (!check || this.mMsgData.Update == 1)) {
                this.mMsgData.Update = 0;
                if (this.mMsgData.EndurOil >= 0) {
                }
                if (CanBencWithCDCarFuncActivity.BencZmytSpeedDw() > 0) {
                    disUnit = getResources().getString(R.string.dis_mile_unit);
                    distance = (int) (0.62137f * ((float) this.mMsgData.Distance));
                } else {
                    disUnit = getResources().getString(R.string.dis_km_unit);
                    distance = this.mMsgData.Distance;
                }
                this.mTvRecordMile.setText(String.format("%s  %d %s", new Object[]{this.mRecordStr, Integer.valueOf(distance), disUnit}));
                if (this.mMsgData.Speed == 255) {
                    this.mTvSpeed.setText(String.valueOf(this.mSpeedStr) + "  ---");
                } else {
                    if (CanBencWithCDCarFuncActivity.BencZmytSpeedDw() > 0) {
                        unit = getResources().getString(R.string.mph_unit);
                        speed = (int) (0.62137f * ((float) this.mMsgData.Speed));
                    } else {
                        unit = getResources().getString(R.string.km_unit);
                        speed = this.mMsgData.Speed;
                    }
                    this.mTvSpeed.setText(String.format("%s  %d %s", new Object[]{this.mSpeedStr, Integer.valueOf(speed), unit}));
                }
            }
            CanJni.BencZmytWithCDCarLedInfo(this.mLedInfo);
            if (this.mLedInfo.Update == 1 && this.mLedInfo.Update == 1) {
                this.mLedInfo.Update = 0;
                this.mBtnAirBag.setSelected(this.mLedInfo.Ckaqsnzsd != 0);
                this.mBtnCarUp.setSelected(this.mLedInfo.CarUp != 0);
                this.mBtnSport.setSelected(this.mLedInfo.Sport != 0);
                this.mBtnPOff.setSelected(this.mLedInfo.Poff != 0);
            }
            CanIF.UpdateDoorUI();
            this.mDoorInfo = Can.mDoorInfo;
            switchDoors();
            int[] doorArr = {this.mDoorInfo.LFOpen, this.mDoorInfo.RFOpen, this.mDoorInfo.LROpen, this.mDoorInfo.RROpen, this.mDoorInfo.RearOpen};
            for (int i = 0; i < doorArr.length; i++) {
                if (!((this.mCarType == 2 && (i == 2 || i == 3)) || doorArr[i] == this.mDoorArr[i])) {
                    this.isDoorUpdate = true;
                }
            }
            if (this.isDoorUpdate) {
                updateDoors(this.mCarType);
                this.isDoorUpdate = false;
            }
        }
    }

    private void switchDoors() {
        switch (FtSet.GetFdoor()) {
            case 1:
                int temp = this.mDoorInfo.LFOpen;
                this.mDoorInfo.LFOpen = this.mDoorInfo.RFOpen;
                this.mDoorInfo.RFOpen = temp;
                break;
            case 2:
                this.mDoorInfo.LFOpen = 0;
                this.mDoorInfo.RFOpen = 0;
                break;
        }
        switch (FtSet.GetBdoor()) {
            case 1:
                int temp2 = this.mDoorInfo.LROpen;
                this.mDoorInfo.LROpen = this.mDoorInfo.RROpen;
                this.mDoorInfo.RROpen = temp2;
                return;
            case 2:
                this.mDoorInfo.LROpen = 0;
                this.mDoorInfo.RROpen = 0;
                return;
            default:
                return;
        }
    }

    public class NaviInfo {
        String curRoadName;
        int directionIcon;
        String nextRoadName;
        int roundNumber;
        int signalRemainDistance;

        public NaviInfo() {
        }

        public void clear() {
            this.curRoadName = null;
            this.nextRoadName = null;
            this.directionIcon = 0;
            this.roundNumber = 0;
            this.signalRemainDistance = 0;
        }
    }

    public void updateWeather(Weathers weathers) {
        Log.d("lh3", "updateWeather " + weathers);
        String city = weathers.getCity();
        String weather = weathers.getWeather();
        String temperature = weathers.getTemperature();
        this.mTvCity.setText(city);
        this.mTvWeather.setText(weather);
        this.mTvTemperature.setText(String.valueOf(temperature) + "°C");
        int index = this.mWeatherTypeList.indexOf(weather);
        if (index == -1) {
            this.mIvWeather.setImageResource(R.drawable.weather_no);
        } else if (index >= 0 && index < this.mWeatherIds.length) {
            this.mIvWeather.setImageResource(this.mWeatherIds[index]);
        }
    }

    public void updateDoors(int carType) {
        int count;
        if (carType < 1 || carType > this.mCarIcons.length - 1) {
            carType = 0;
        }
        this.mCarType = carType;
        if (carType == 5) {
            this.isCarLuHu = true;
            this.mViewPager.setAdapter(this.mPagerAdapter);
            this.mIvCar.setImageResource(this.mBigCarIcons[0][carType]);
            for (ImageView view : this.mIvDoors) {
                view.setVisibility(8);
            }
            return;
        }
        this.isCarLuHu = false;
        this.mViewPager.setAdapter(this.mPagerAdapter);
        if (carType == 2) {
            count = this.mDoorInfo.LFOpen + this.mDoorInfo.RFOpen + this.mDoorInfo.RearOpen;
        } else {
            count = this.mDoorInfo.LFOpen + this.mDoorInfo.LROpen + this.mDoorInfo.RFOpen + this.mDoorInfo.RROpen + this.mDoorInfo.RearOpen;
        }
        this.mDoorArr = new int[]{this.mDoorInfo.LFOpen, this.mDoorInfo.RFOpen, this.mDoorInfo.LROpen, this.mDoorInfo.RROpen, this.mDoorInfo.RearOpen};
        for (int i = 0; i < this.mDoorArr.length; i++) {
            updateDoorIcon(i, count, carType);
        }
        if (count == 0) {
            for (ImageView view2 : this.mIvDoors) {
                view2.setVisibility(8);
            }
            if (this.isRightOpen) {
                this.mIvCar.setImageResource(this.mBigCarIcons[1][carType]);
            } else {
                this.mIvCar.setImageResource(this.mBigCarIcons[0][carType]);
            }
            if (this.isAutoScroll) {
                this.mViewPager.setCurrentItem(this.mLastPage, false);
                this.isAutoScroll = false;
            }
        } else if (this.mViewPager.getCurrentItem() != 0) {
            this.mLastPage = this.mViewPager.getCurrentItem();
            this.mViewPager.setCurrentItem(0, false);
            this.isAutoScroll = true;
        }
    }

    private void updateDoorIcon(int index, int count, int carType) {
        boolean z = true;
        int[][] doorIcons = this.mDoorIcons[index];
        if (carType == 2 && (index == 2 || index == 3)) {
            this.mIvDoors[index].setVisibility(8);
        } else if (this.mDoorArr[index] == 1) {
            if (count > 1) {
                this.mIvCar.setImageResource(this.mCarIcons[carType]);
                this.mIvDoors[index].setImageResource(doorIcons[1][carType]);
                this.mIvDoors[index].setVisibility(0);
            } else {
                this.mIvCar.setImageResource(doorIcons[0][carType]);
                this.mIvDoors[index].setVisibility(8);
            }
            if (carType == 2) {
                if (!(this.mDoorInfo.LFOpen == 0 && this.mDoorInfo.RFOpen == 1)) {
                    z = false;
                }
                this.isRightOpen = z;
                return;
            }
            if (!(this.mDoorInfo.LFOpen == 0 && this.mDoorInfo.LROpen == 0 && (this.mDoorInfo.RFOpen == 1 || this.mDoorInfo.RROpen == 1))) {
                z = false;
            }
            this.isRightOpen = z;
        } else {
            this.mIvDoors[index].setVisibility(8);
        }
    }

    public void updateNavi(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            switch (bundle.getInt("KEY_TYPE", 0)) {
                case TXZConfigManager.INIT_ERROR_ASR /*10001*/:
                    analayseNaviInfo(bundle);
                    updateNaviView();
                    return;
                case 10019:
                    int state = bundle.getInt("EXTRA_STATE");
                    if (state == 2 || state == 9 || state == 12) {
                        showNaviView(false);
                        return;
                    } else if (state == 8 || state == 10) {
                        showNaviView(true);
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private void updateNaviView() {
        if (this.tvCurRoad != null) {
            showNaviView(true);
            if (this.mNaviInfo.curRoadName != null) {
                this.tvCurRoad.setText(this.mNaviInfo.curRoadName);
            } else {
                this.tvCurRoad.setText("");
            }
            if (this.mNaviInfo.nextRoadName != null) {
                this.tvNextRoad.setText(this.mNaviInfo.nextRoadName);
            } else {
                this.tvNextRoad.setText("");
            }
            if (this.mNaviInfo.signalRemainDistance > 1000) {
                this.tvRemainDistance.setText(String.format("%.02f km后", new Object[]{Float.valueOf(((float) this.mNaviInfo.signalRemainDistance) / 1000.0f)}));
            } else {
                this.tvRemainDistance.setText(String.valueOf(this.mNaviInfo.signalRemainDistance) + " m后");
            }
            if (this.mNaviInfo.directionIcon != 0) {
                this.ivDirectionIcon.setVisibility(0);
                this.ivDirectionIcon.setImageResource(this.mNaviInfo.directionIcon);
                if (this.mNaviInfo.directionIcon == R.drawable.right_navi_island) {
                    this.tvRoundNumber.setText(new StringBuilder(String.valueOf(this.mNaviInfo.roundNumber)).toString());
                } else {
                    this.tvRoundNumber.setText("");
                }
            } else {
                this.ivDirectionIcon.setVisibility(8);
                this.tvRoundNumber.setVisibility(8);
            }
        }
    }

    private void analayseNaviInfo(Bundle bundle) {
        int tmpInt;
        String tmp = bundle.getString(AmapAuto.CUR_ROAD_NAME, (String) null);
        if (tmp != null) {
            this.mNaviInfo.curRoadName = new String(tmp);
            String tmp2 = bundle.getString(AmapAuto.NEXT_ROAD_NAME, (String) null);
            if (tmp2 != null) {
                this.mNaviInfo.nextRoadName = new String(tmp2);
            } else {
                this.mNaviInfo.nextRoadName = null;
            }
            int tmpInt2 = bundle.getInt(AmapAuto.ICON, 0);
            switch (tmpInt2) {
                case 1:
                    this.mNaviInfo.directionIcon = 0;
                    tmpInt = 0;
                    break;
                case 11:
                case 12:
                case 17:
                case 18:
                    this.mNaviInfo.directionIcon = R.drawable.right_navi_island;
                    tmpInt = bundle.getInt(AmapAuto.ROUND_ABOUT_NUM, 0);
                    break;
                default:
                    if (tmpInt2 <= 0 || tmpInt2 >= this.icons.length) {
                        this.mNaviInfo.directionIcon = 0;
                    } else {
                        this.mNaviInfo.directionIcon = this.icons[tmpInt2 - 1];
                    }
                    tmpInt = 0;
                    break;
            }
            this.mNaviInfo.roundNumber = tmpInt;
            this.mNaviInfo.signalRemainDistance = bundle.getInt(AmapAuto.SEG_REMAIN_DIS, 0);
            return;
        }
        this.mNaviInfo.clear();
    }

    private void showNaviView(boolean show) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        this.ivNaviIcon.setVisibility(show ? 8 : 0);
        ImageView imageView = this.ivDirectionIcon;
        if (show) {
            i = 0;
        } else {
            i = 8;
        }
        imageView.setVisibility(i);
        TextView textView = this.tvCurRoad;
        if (show) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        textView.setVisibility(i2);
        TextView textView2 = this.tvNextRoad;
        if (show) {
            i3 = 0;
        } else {
            i3 = 8;
        }
        textView2.setVisibility(i3);
        TextView textView3 = this.tvRemainDistance;
        if (show) {
            i4 = 0;
        } else {
            i4 = 8;
        }
        textView3.setVisibility(i4);
        TextView textView4 = this.tvRoundNumber;
        if (!show) {
            i5 = 8;
        }
        textView4.setVisibility(i5);
    }
}
