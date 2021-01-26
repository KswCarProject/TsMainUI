package com.ts.can.vw.golf.wc;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.canview.MyProgressBar;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;

public class CanGolfWcConvConsumersActivity extends CanBaseActivity implements UserCallBack {
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private static String[] mWarnTips;
    private TextView mConCenter;
    private TextView mConDW;
    private TextView mConMax;
    private TextView mConMin;
    private MyProgressBar mConProgress;
    private TextView mConTitle;
    private CanDataInfo.GolfConvConsumers mConsumersData = new CanDataInfo.GolfConvConsumers();
    private CanDataInfo.GolfWcConvConWarn mConsumersWarn = new CanDataInfo.GolfWcConvConWarn();
    private RelativeLayoutManager mManager;
    private String[] mMaxArrays = {"1/4", "3/8", "1/2", "1", "3/2", "2"};
    private String[] mMiddleArrays = {"1/8", "3/16", "1/4", "1/2", "3/4", "1"};
    private TextView[] mTvWarns = new TextView[7];

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
        lp.width = 1024;
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        lp.gravity = 17;
        this.mManager.GetLayout().setLayoutParams(lp);
        InitUI();
        this.mManager.GetLayout().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
        this.mManager.GetLayout().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
        Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
    }

    private void InitUI() {
        mWarnTips = getResources().getStringArray(R.array.can_conv_tips);
        this.mConTitle = this.mManager.AddText(100, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 300, 50);
        this.mConTitle.setText(R.string.can_consumption);
        this.mConTitle.setGravity(19);
        this.mConTitle.setTextSize(0, 40.0f);
        this.mConTitle.setTextColor(-1);
        this.mConTitle.setVisibility(4);
        this.mConProgress = new MyProgressBar(this, R.drawable.can_golf_bar_dn, R.drawable.can_golf_bar_up);
        this.mConProgress.SetMinMax(0, 100);
        this.mConProgress.setVisibility(4);
        this.mManager.AddViewWrapContent(this.mConProgress, CanCameraUI.BTN_CHANA_ALSVINV7_MODE2, 420);
        this.mConMin = this.mManager.AddText(CanCameraUI.BTN_CHANA_ALSVINV7_MODE2, 440, 100, 30);
        this.mConMin.setGravity(19);
        this.mConMin.setTextSize(0, 25.0f);
        this.mConMin.setTextColor(-1);
        this.mConCenter = this.mManager.AddText(CanCameraUI.BTN_CC_WC_DIRECTION1, 440, 100, 30);
        this.mConCenter.setGravity(17);
        this.mConCenter.setTextSize(0, 25.0f);
        this.mConCenter.setTextColor(-1);
        this.mConMax = this.mManager.AddText(877, 440, 100, 30);
        this.mConMax.setGravity(21);
        this.mConMax.setTextSize(0, 25.0f);
        this.mConMax.setTextColor(-1);
        this.mConDW = this.mManager.AddText(877, AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, 100, 40);
        this.mConDW.setGravity(21);
        this.mConDW.setTextSize(0, 25.0f);
        this.mConDW.setTextColor(-1);
        for (int i = 0; i < this.mTvWarns.length; i++) {
            this.mTvWarns[i] = this.mManager.AddText(100, (i * 53) + 10, 800, 50);
            this.mTvWarns[i].setText(TXZResourceManager.STYLE_DEFAULT);
            this.mTvWarns[i].setGravity(19);
            this.mTvWarns[i].setTextSize(0, 28.0f);
            this.mTvWarns[i].setTextColor(-1);
        }
    }

    private void ResetData(boolean check) {
        CanJni.GolfGetDrivingConvConsumers(this.mConsumersData);
        CanJni.GolfWcGetConvConWarn(this.mConsumersWarn);
        if (i2b(this.mConsumersData.UpdateOnce) && (!check || i2b(this.mConsumersData.Update))) {
            this.mConsumersData.Update = 0;
            if (this.mConsumersData.DW == 0) {
                this.mConDW.setText("l/h");
            } else {
                this.mConDW.setText("gal/h");
            }
            int max = this.mConsumersData.Max;
            if (max >= 0 && max < this.mMaxArrays.length) {
                this.mConMax.setText(this.mMaxArrays[max]);
                this.mConCenter.setText(this.mMiddleArrays[max]);
                this.mConMin.setText("0");
            }
            if (this.mConsumersData.Bfb <= 100) {
                this.mConProgress.SetCurPos(this.mConsumersData.Bfb);
            }
            this.mConTitle.setVisibility(0);
            this.mConProgress.setVisibility(0);
        }
        if (!i2b(this.mConsumersWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mConsumersWarn.Update)) {
            this.mConsumersWarn.Update = 0;
            int num = this.mConsumersWarn.Num;
            int[] indexs = this.mConsumersWarn.Warn;
            if (num >= 0 && num <= 7) {
                for (int i = 0; i < this.mTvWarns.length; i++) {
                    if (i < num) {
                        if (indexs[i] > mWarnTips.length - 1) {
                            indexs[i] = 0;
                        }
                        this.mTvWarns[i].setText(mWarnTips[indexs[i]]);
                    } else {
                        this.mTvWarns[i].setText(TXZResourceManager.STYLE_DEFAULT);
                    }
                }
            }
        }
    }

    private void QueryData() {
        CanJni.GolfWcQueryData(1, 22);
        Sleep(5);
        CanJni.GolfWcQueryData(1, 119);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void UserAll() {
        ResetData(true);
    }
}
