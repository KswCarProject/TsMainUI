package com.ts.can.honda.wc.civic;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;

public class CanHondaWcCivicExdView extends CanRelativeCarInfoView {
    public static final int BTN_NEXT = 1;
    public static final int BTN_PLAY = 2;
    public static final int BTN_PREV = 0;
    public static final int BTN_STOP = 3;
    public static final String TAG = "CanCivicWcExdView";
    /* access modifiers changed from: private */
    public Bitmap mBmpProgDn;
    private ParamButton mBtnNext;
    private ParamButton mBtnPlay;
    private ParamButton mBtnPrev;
    private ParamButton mBtnStop;
    private CustomTextView mCurTime;
    private CustomImgView mImgIpod;
    private CustomImgView mImgUsb;
    /* access modifiers changed from: private */
    public CanDataInfo.HondaOldSysMedia mMediaData;
    private CustomTextView mPercent;
    private CustomImgView mProgress;
    private CustomTextView mSongNum;
    private CustomTextView mStatus;
    private String[] mStrArrStatus;
    private String mStrDevSta;

    public CanHondaWcCivicExdView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CdCtrl(7, 1);
                return;
            case 1:
                CdCtrl(7, 0);
                return;
            case 2:
                CdCtrl(1, 0);
                return;
            case 3:
                CdCtrl(2, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        initCommonScreen();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    private void initCommonScreen() {
        this.mMediaData = new CanDataInfo.HondaOldSysMedia();
        this.mImgUsb = addImage(466, 79, R.drawable.original_car_usb);
        this.mImgIpod = addImage(Can.CAN_VOLVO_XFY, 60, R.drawable.original_car_ipod);
        addImage(76, 23, R.drawable.original_car_arrow);
        this.mStatus = getRelativeManager().AddCusText(111, 23, 300, 38);
        this.mStatus.setGravity(19);
        this.mStatus.SetPxSize(38);
        this.mStatus.setTextColor(-1);
        this.mSongNum = getRelativeManager().AddCusText(800, 23, 200, 38);
        this.mSongNum.setGravity(19);
        this.mSongNum.SetPxSize(38);
        this.mSongNum.setTextColor(-1);
        this.mBmpProgDn = ((BitmapDrawable) getActivity().getResources().getDrawable(R.drawable.can_mediadev_slider_dn)).getBitmap();
        this.mProgress = addImage(131, 378, 766, 25);
        this.mProgress.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                int pos = CanHondaWcCivicExdView.this.mMediaData.PlayPro;
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
                    canvas.drawBitmap(CanHondaWcCivicExdView.this.mBmpProgDn, src, des, paint);
                }
                return false;
            }
        });
        this.mCurTime = getRelativeManager().AddCusText(10, 375, 120, 30);
        this.mCurTime.setGravity(21);
        this.mCurTime.SetPxSize(30);
        this.mCurTime.setTextColor(-1);
        this.mPercent = getRelativeManager().AddCusText(897, 375, 130, 30);
        this.mPercent.setGravity(19);
        this.mPercent.SetPxSize(30);
        this.mPercent.setTextColor(-1);
        this.mBtnPrev = getRelativeManager().AddButton(257, 427);
        this.mBtnPrev.setTag(0);
        this.mBtnPrev.setStateUpDn(R.drawable.original_car_seekup_up, R.drawable.original_car_seekup_dn);
        this.mBtnPrev.setOnClickListener(this);
        this.mBtnPlay = getRelativeManager().AddButton(396, 427);
        this.mBtnPlay.setTag(2);
        this.mBtnPlay.setStateUpDn(R.drawable.original_car_play_up, R.drawable.original_car_play_dn);
        this.mBtnPlay.setOnClickListener(this);
        this.mBtnStop = getRelativeManager().AddButton(536, 427);
        this.mBtnStop.setTag(3);
        this.mBtnStop.setStateUpDn(R.drawable.original_car_stop_up, R.drawable.original_car_stop_dn);
        this.mBtnStop.setOnClickListener(this);
        this.mBtnNext = addButton(675, 427);
        this.mBtnNext.setTag(1);
        this.mBtnNext.setStateUpDn(R.drawable.original_car_seekdn_up, R.drawable.original_car_seekdn_dn);
        this.mBtnNext.setOnClickListener(this);
        this.mImgIpod.Show(false);
        this.mImgUsb.Show(false);
        this.mStrDevSta = getActivity().getResources().getString(R.string.can_car_dev_sta);
        this.mStrArrStatus = getActivity().getResources().getStringArray(R.array.can_civic_car_play_sta);
    }

    public void ResetData(boolean check) {
        CanJni.HondaOldSysWcGetMediaInfo(this.mMediaData);
        if (!i2b(this.mMediaData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mMediaData.Update)) {
            this.mMediaData.Update = 0;
            this.mImgIpod.Show(false);
            this.mImgUsb.Show(false);
            int mediaSta = getMediaSta(this.mMediaData.PlaySta);
            if (mediaSta == 7) {
                this.mStatus.setText(TXZResourceManager.STYLE_DEFAULT);
            } else {
                this.mStatus.setText(String.valueOf(this.mStrDevSta) + ": " + this.mStrArrStatus[mediaSta]);
            }
            if (13 == this.mMediaData.Source) {
                this.mImgUsb.Show(true);
            } else if (14 == this.mMediaData.Source) {
                this.mImgIpod.Show(true);
            }
            this.mSongNum.setText(String.format("%d/%d", new Object[]{Integer.valueOf(this.mMediaData.Track), Integer.valueOf(this.mMediaData.TotalTrack)}));
            this.mCurTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mMediaData.PlayTimeM), Integer.valueOf(this.mMediaData.PlayTimeS)}));
            if (this.mMediaData.PlayPro <= 100) {
                this.mPercent.setText(String.valueOf(this.mMediaData.PlayPro) + "%");
            }
            this.mProgress.invalidate();
        }
    }

    private int getMediaSta(int playSta) {
        switch (playSta) {
            case 0:
            case 255:
                return 7;
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 6:
                return 3;
            case 9:
                return 4;
            case 12:
                return 5;
            case 13:
                return 6;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: protected */
    public void CdCtrl(int cmd, int part1) {
        CanJni.HondaOldSysWcMediaCmd(cmd, part1);
    }

    public void QueryData() {
    }
}
