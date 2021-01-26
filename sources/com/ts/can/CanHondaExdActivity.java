package com.ts.can;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;

public class CanHondaExdActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    public static final int BTN_NEXT = 3;
    public static final int BTN_PLAY = 1;
    public static final int BTN_PREV = 0;
    public static final int BTN_STOP = 2;
    public static final String TAG = "CanHondaExdActivity";
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    /* access modifiers changed from: private */
    public Bitmap mBmpProgDn;
    private ParamButton mBtnNext;
    private ParamButton mBtnPlay;
    private ParamButton mBtnPrev;
    private ParamButton mBtnStop;
    private CustomTextView mCurTime;
    private CustomImgView mImgIpod;
    private CustomImgView mImgUsb;
    protected RelativeLayoutManager mManager;
    /* access modifiers changed from: private */
    public CanDataInfo.HondaMediaDev mMediaData = new CanDataInfo.HondaMediaDev();
    private CustomTextView mPercent;
    private CustomImgView mProgress;
    private CustomTextView mSongNum;
    private CustomTextView mStatus;
    private String[] mStrArrStatus;
    private String mStrDevSta;

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
        this.mImgUsb = this.mManager.AddImage(466, 79, R.drawable.original_car_usb);
        this.mImgIpod = this.mManager.AddImage(Can.CAN_VOLVO_XFY, 60, R.drawable.original_car_ipod);
        this.mManager.AddImage(76, 23, R.drawable.original_car_arrow);
        this.mStatus = this.mManager.AddCusText(111, 23, 300, 38);
        this.mStatus.setGravity(19);
        this.mStatus.SetPxSize(38);
        this.mStatus.setTextColor(-1);
        this.mSongNum = this.mManager.AddCusText(800, 23, 200, 38);
        this.mSongNum.setGravity(19);
        this.mSongNum.SetPxSize(38);
        this.mSongNum.setTextColor(-1);
        this.mBmpProgDn = ((BitmapDrawable) getResources().getDrawable(R.drawable.can_mediadev_slider_dn)).getBitmap();
        this.mProgress = this.mManager.AddImage(131, 378, 766, 25);
        this.mProgress.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                int pos = CanHondaExdActivity.this.mMediaData.Progress;
                view.drawImage(R.drawable.can_mediadev_slider_up, 12, 0);
                if (pos > 0 && pos <= 100) {
                    int w = (pos * 741) / 100;
                    Rect src = new Rect();
                    src.left = 0;
                    src.top = 0;
                    src.right = w;
                    src.bottom = 25;
                    Rect des = new Rect();
                    des.left = 12;
                    des.top = 0;
                    des.right = w + 12;
                    des.bottom = 25;
                    canvas.drawBitmap(CanHondaExdActivity.this.mBmpProgDn, src, des, paint);
                }
                return false;
            }
        });
        this.mCurTime = this.mManager.AddCusText(10, 375, 120, 30);
        this.mCurTime.setGravity(21);
        this.mCurTime.SetPxSize(30);
        this.mCurTime.setTextColor(-1);
        this.mPercent = this.mManager.AddCusText(897, 375, 130, 30);
        this.mPercent.setGravity(19);
        this.mPercent.SetPxSize(30);
        this.mPercent.setTextColor(-1);
        this.mBtnPrev = this.mManager.AddButton(257, 427);
        this.mBtnPrev.setTag(0);
        this.mBtnPrev.setStateUpDn(R.drawable.original_car_seekup_up, R.drawable.original_car_seekup_dn);
        this.mBtnPrev.setOnClickListener(this);
        this.mBtnPlay = this.mManager.AddButton(396, 427);
        this.mBtnPlay.setTag(1);
        this.mBtnPlay.setStateUpDn(R.drawable.original_car_play_up, R.drawable.original_car_play_dn);
        this.mBtnPlay.setOnClickListener(this);
        this.mBtnStop = this.mManager.AddButton(536, 427);
        this.mBtnStop.setTag(2);
        this.mBtnStop.setStateUpDn(R.drawable.original_car_stop_up, R.drawable.original_car_stop_dn);
        this.mBtnStop.setOnClickListener(this);
        this.mBtnNext = this.mManager.AddButton(675, 427);
        this.mBtnNext.setTag(3);
        this.mBtnNext.setStateUpDn(R.drawable.original_car_seekdn_up, R.drawable.original_car_seekdn_dn);
        this.mBtnNext.setOnClickListener(this);
        this.mImgIpod.Show(false);
        this.mImgUsb.Show(false);
        this.mStrDevSta = getResources().getString(R.string.can_car_dev_sta);
        this.mStrArrStatus = getResources().getStringArray(R.array.can_car_play_sta);
        this.mManager.GetLayout().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
        this.mManager.GetLayout().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
        Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
    }

    private void ResetData(boolean check) {
        CanJni.HondaOldGetMediaDev(this.mMediaData);
        if (!i2b(this.mMediaData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mMediaData.Update)) {
            this.mMediaData.Update = 0;
            this.mImgIpod.Show(false);
            this.mImgUsb.Show(false);
            if (this.mMediaData.Status > 6) {
                this.mMediaData.Status = 6;
            }
            this.mStatus.setText(String.valueOf(this.mStrDevSta) + ": " + this.mStrArrStatus[this.mMediaData.Status]);
            if (this.mMediaData.Status >= 4) {
                if (2 == this.mMediaData.Source) {
                    this.mImgUsb.Show(true);
                } else if (2 == this.mMediaData.Source) {
                    this.mImgIpod.Show(true);
                }
                this.mSongNum.setText(String.format("%d/%d", new Object[]{Integer.valueOf(this.mMediaData.CurSong), Integer.valueOf(this.mMediaData.TotalSong)}));
                this.mCurTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mMediaData.Min), Integer.valueOf(this.mMediaData.Sec)}));
                if (this.mMediaData.Progress <= 100) {
                    this.mPercent.setText(String.valueOf(this.mMediaData.Progress) + "%");
                }
            } else {
                this.mMediaData.Progress = 200;
                this.mSongNum.setText(TXZResourceManager.STYLE_DEFAULT);
                this.mCurTime.setText(TXZResourceManager.STYLE_DEFAULT);
                this.mPercent.setText(TXZResourceManager.STYLE_DEFAULT);
            }
            this.mProgress.invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        Evc.GetInstance().evol_workmode_set(12);
        ResetData(false);
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.HondaOldPlayCtrl(3);
                return;
            case 1:
                CanJni.HondaOldPlayCtrl(1);
                return;
            case 2:
                CanJni.HondaOldPlayCtrl(2);
                return;
            case 3:
                CanJni.HondaOldPlayCtrl(4);
                return;
            default:
                return;
        }
    }
}
