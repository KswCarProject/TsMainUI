package com.ts.can;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.dvdplayer.definition.MediaDef;
import java.io.UnsupportedEncodingException;

public class CanOdysseyExdActivity extends CanBaseActivity implements UserCallBack {
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private ImageView mAutoSelect;
    private ImageView mBtBattery;
    private int[] mBtBatteryArray = {R.drawable.can_aode_battery00, R.drawable.can_aode_battery02, R.drawable.can_aode_battery03, R.drawable.can_aode_battery04, R.drawable.can_aode_battery05, R.drawable.can_aode_battery06};
    private ImageView mBtIcon;
    private ImageView mBtSignal;
    private int[] mBtSignalsArray = {R.drawable.can_aode_signal01, R.drawable.can_aode_signal02, R.drawable.can_aode_signal03, R.drawable.can_aode_signal04, R.drawable.can_aode_signal05, R.drawable.can_aode_signal06};
    private int[] mCdStatusArray = {R.drawable.can_aode_xh01, R.drawable.can_aode_sj01, R.drawable.can_aode_scan03};
    private CanDataInfo.OdysseyIcon mIconData = new CanDataInfo.OdysseyIcon();
    private LinearLayout mIconLayout;
    private RelativeLayout mManager;
    private CanDataInfo.OdysseyMenu mMenuData = new CanDataInfo.OdysseyMenu();
    private ImageView mPlayIcon;
    private ImageView mSTStatus;
    private ImageView mScanStatus;
    private ImageView mSelBottomArrow;
    private ImageView mSelIcon;
    private ImageView mSelTopArrow;
    private TextView mTvMenu1;
    private TextView mTvMenu2;
    private TextView mTvMenu3;
    private TextView mTvTitle;
    private TextView mTvVol;
    private ImageView mUsbIcon;
    private int[] mUsbStatusArray = {R.drawable.can_aode_xh01, R.drawable.can_aode_xh02, R.drawable.can_aode_sj02, R.drawable.can_aode_sj01, R.drawable.can_aode_scan03, R.drawable.can_aode_scan02};
    private CanDataInfo.OdysseyVol mVolData = new CanDataInfo.OdysseyVol();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        InitViews();
    }

    private void InitViews() {
        this.mManager = (RelativeLayout) findViewById(R.id.can_comm_layout);
        this.mManager.setBackgroundResource(R.drawable.can_aode_bg);
        this.mIconLayout = AddIconLayout(90, 40);
        this.mBtIcon = AddIcon(R.drawable.can_aode_bt);
        this.mBtSignal = AddIcon(this.mBtSignalsArray[5]);
        this.mBtBattery = AddIcon(this.mBtBatteryArray[5]);
        this.mUsbIcon = AddIcon(R.drawable.can_aode_usb);
        this.mPlayIcon = AddIcon(R.drawable.can_aode_sj01);
        this.mAutoSelect = AddIcon(R.drawable.can_aode_auto);
        this.mSTStatus = AddIcon(R.drawable.can_aode_st);
        this.mScanStatus = AddIcon(R.drawable.can_aode_scan01);
        this.mTvVol = AddVol();
        this.mSelIcon = AddImage(76, Can.CAN_CHRYSLER_TXB, R.drawable.can_aode_bg01);
        this.mSelTopArrow = AddImage(76, Can.CAN_CHRYSLER_TXB, R.drawable.can_aode_bg02);
        this.mSelBottomArrow = AddImage(76, 422, R.drawable.can_aode_bg03);
        this.mTvTitle = AddText(Can.CAN_JAC_REFINE_OD, 105, MediaDef.PROGRESS_MAX, 120);
        this.mTvTitle.setTextSize(0, 45.0f);
        this.mTvMenu1 = AddText(178, 221, MediaDef.PROGRESS_MAX, 94);
        this.mTvMenu2 = AddText(178, 301, MediaDef.PROGRESS_MAX, 94);
        this.mTvMenu3 = AddText(178, 382, MediaDef.PROGRESS_MAX, 94);
    }

    private LinearLayout AddIconLayout(int x, int y) {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(0);
        layout.setGravity(16);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        lp.leftMargin = x;
        lp.topMargin = y;
        layout.setLayoutParams(lp);
        this.mManager.addView(layout);
        return layout;
    }

    private ImageView AddIcon(int resId) {
        ImageView icon = new ImageView(this);
        if (resId != 0) {
            icon.setImageResource(resId);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        lp.leftMargin = 10;
        lp.rightMargin = 10;
        icon.setLayoutParams(lp);
        icon.setVisibility(4);
        this.mIconLayout.addView(icon);
        return icon;
    }

    private TextView AddVol() {
        TextView vol = new TextView(this);
        vol.setTextColor(-1);
        vol.setTextSize(0, 23.0f);
        vol.setTextScaleX(1.2f);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        lp.leftMargin = 10;
        lp.rightMargin = 10;
        vol.setLayoutParams(lp);
        vol.setVisibility(4);
        this.mIconLayout.addView(vol);
        return vol;
    }

    private TextView AddText(int x, int y, int w, int h) {
        TextView text = new TextView(this);
        text.setTextColor(-1);
        text.setTextSize(0, 38.0f);
        text.setPadding(50, 0, 30, 0);
        text.setTextScaleX(1.2f);
        text.setTypeface(Typeface.SERIF);
        text.setGravity(16);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(w, h);
        lp.leftMargin = x;
        lp.topMargin = y;
        text.setLayoutParams(lp);
        this.mManager.addView(text);
        return text;
    }

    private ImageView AddImage(int x, int y, int resId) {
        ImageView image = new ImageView(this);
        image.setImageResource(resId);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        lp.leftMargin = x;
        lp.topMargin = y;
        image.setLayoutParams(lp);
        image.setVisibility(4);
        this.mManager.addView(image);
        return image;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Evc.GetInstance().evol_workmode_set(12);
        mfgShow = true;
        mfgFinish = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        mfgShow = false;
    }

    public static void showOdysseyWin() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanOdysseyExdActivity.class);
        }
    }

    public static void finishOdysseyWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    private void ResetData(boolean check) {
        CanJni.OdysseyGetMenu(this.mMenuData);
        CanJni.OdysseyGetIcon(this.mIconData);
        CanJni.OdysseyGetVol(this.mVolData);
        if (i2b(this.mMenuData.UpdateOnce) && (!check || i2b(this.mMenuData.Update))) {
            this.mMenuData.Update = 0;
            int[] encode = this.mMenuData.Code;
            int[] dataLen = this.mMenuData.DataLen;
            int[] isLight = this.mMenuData.Gl;
            String title = byte2String(encode[0], this.mMenuData.TitleData, dataLen[0]);
            String line1 = byte2String(encode[1], this.mMenuData.Menu1Data, dataLen[1]);
            String line2 = byte2String(encode[2], this.mMenuData.Menu2Data, dataLen[2]);
            String line3 = byte2String(encode[3], this.mMenuData.Menu3Data, dataLen[3]);
            if (TextUtils.isEmpty(title)) {
                title = "";
            }
            this.mTvTitle.setText(title.trim());
            changeLineText(this.mTvMenu1, i2b(isLight[1]), line1.trim());
            changeLineText(this.mTvMenu2, i2b(isLight[2]), line2.trim());
            changeLineText(this.mTvMenu3, i2b(isLight[3]), line3.trim());
            changeSelIcon(line1.trim(), line2.trim(), line3.trim());
        }
        if (i2b(this.mIconData.UpdateOnce) && (!check || i2b(this.mIconData.Update))) {
            this.mIconData.Update = 0;
            showGone(this.mAutoSelect, i2b(this.mIconData.AutoSelect));
            showGone(this.mSTStatus, i2b(this.mIconData.St));
            showGone(this.mScanStatus, i2b(this.mIconData.Scan));
            int cdStatus = this.mIconData.CdState;
            int usbStatus = this.mIconData.UsbState;
            int haveUsb = this.mIconData.HaveUsb;
            showGone(this.mUsbIcon, i2b(haveUsb));
            if (i2b(haveUsb)) {
                showGone(this.mPlayIcon, i2b(usbStatus));
                if (usbStatus > 0 && usbStatus <= 6) {
                    this.mPlayIcon.setImageResource(this.mUsbStatusArray[usbStatus - 1]);
                }
            } else {
                showGone(this.mPlayIcon, i2b(cdStatus));
                if (cdStatus > 0 && cdStatus <= 3) {
                    this.mPlayIcon.setImageResource(this.mCdStatusArray[cdStatus - 1]);
                }
            }
            int btConn = this.mIconData.BtState;
            showGone(this.mBtIcon, i2b(btConn));
            showGone(this.mBtSignal, i2b(btConn));
            showGone(this.mBtBattery, i2b(btConn));
            if (i2b(btConn)) {
                int signalStatus = this.mIconData.BtXhqd;
                int batteryStatus = this.mIconData.BtDcdl;
                showGone(this.mBtSignal, i2b(signalStatus));
                showGone(this.mBtBattery, i2b(batteryStatus));
                if (signalStatus > 0 && signalStatus <= 6) {
                    this.mBtSignal.setImageResource(this.mBtSignalsArray[signalStatus - 1]);
                }
                if (batteryStatus > 0 && batteryStatus <= 6) {
                    this.mBtBattery.setImageResource(this.mBtBatteryArray[batteryStatus - 1]);
                }
            }
        }
        if (!i2b(this.mVolData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mVolData.Update)) {
            this.mVolData.Update = 0;
            this.mTvVol.setText("VOL " + this.mVolData.Vol);
            showGone(this.mTvVol, i2b(this.mVolData.Disp));
        }
    }

    private void changeSelIcon(String line1, String line2, String line3) {
        showGone(this.mSelIcon, true);
        showGone(this.mSelTopArrow, true);
        showGone(this.mSelBottomArrow, true);
        if (TextUtils.isEmpty(line1) && TextUtils.isEmpty(line2) && TextUtils.isEmpty(line3)) {
            showGone(this.mSelIcon, false);
            showGone(this.mSelTopArrow, false);
            showGone(this.mSelBottomArrow, false);
        } else if (TextUtils.isEmpty(line1) && TextUtils.isEmpty(line3)) {
            showGone(this.mSelTopArrow, false);
            showGone(this.mSelBottomArrow, false);
        } else if (TextUtils.isEmpty(line1)) {
            showGone(this.mSelTopArrow, false);
        } else if (TextUtils.isEmpty(line3)) {
            showGone(this.mSelBottomArrow, false);
        }
    }

    private void showGone(View view, boolean isShow) {
        view.setVisibility(isShow ? 0 : 4);
    }

    private void changeLineText(TextView view, boolean isLight, String text) {
        int background;
        if (isLight) {
            background = R.drawable.can_aode_bg04;
        } else {
            background = 17170445;
        }
        if (TextUtils.isEmpty(text)) {
            text = "";
            background = 17170445;
        }
        view.setText(text);
        view.setBackgroundResource(background);
    }

    private String byte2UnicodeString(byte[] data, int len) {
        try {
            return new String(data, 0, len, "UNICODE");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    private String byte2ASCIIString(byte[] data, int len) {
        try {
            return new String(data, 0, len, "GBK");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    private String byte2String(int encode, byte[] data, int len) {
        if (encode == 0) {
            return byte2ASCIIString(data, len);
        }
        return byte2UnicodeString(data, len);
    }

    public void UserAll() {
        ResetData(true);
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            finish();
        }
    }
}
