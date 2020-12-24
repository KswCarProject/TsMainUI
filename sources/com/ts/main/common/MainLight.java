package com.ts.main.common;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;

public class MainLight implements SeekBar.OnSeekBarChangeListener {
    public static final int DISPLAY_SHOW_TIME = 60;
    static MainLight MyLight = null;
    private static final String TAG = "MainVolume";
    public static final int VOL_BAR_OUTPOSY = -79;
    public static final int VOL_BAR_SIZEX = 463;
    public static final int VOL_BAR_SIZEY = 80;
    private RelativeLayout mFloatLayoutNaw = null;
    private RelativeLayout mFloatLayoutScreenFor;
    private RelativeLayout mFloatLayoutVol;
    SeekBar mSeekBar;
    Application m_Application;
    Context m_Context;
    public int nAidlVolumeShow = 0;
    int nLiangdu = 255;
    int nNUm = 0;
    int nShowTime = 0;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    public static MainLight GetInstance() {
        if (MyLight == null) {
            MyLight = new MainLight();
        }
        return MyLight;
    }

    public void InintWinManage(int nSizeX, int nSizeY, Context MyContext) {
        this.wManager = (WindowManager) MyContext.getSystemService("window");
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.flags = 8;
        this.wmParams.gravity = 49;
        this.wmParams.width = nSizeX;
        this.wmParams.height = nSizeY;
    }

    public void Inint(Application MyApplication, Context MyContext) {
        this.m_Application = MyApplication;
        this.m_Context = MyContext;
        this.mFloatLayoutVol = (RelativeLayout) LayoutInflater.from(MyApplication).inflate(R.layout.main_light, (ViewGroup) null);
        this.mSeekBar = (SeekBar) this.mFloatLayoutVol.findViewById(R.id.seekBar1);
        this.mSeekBar.setOnSeekBarChangeListener(this);
        InintVolBar();
    }

    public void DealTask() {
        this.nNUm++;
        if (this.nNUm % 15 == 0) {
            UpdateProcess();
        }
        if (this.nShowTime > 0) {
            this.nShowTime--;
            if (this.nShowTime == 0) {
                WinHide();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void UpdateProcess() {
        if (Mcu.GetIll() == 1 && StSet.GetBLIll() == 1) {
            if (this.nLiangdu != StSet.GetBLNight()) {
                this.nLiangdu = StSet.GetBLNight();
                if (this.mSeekBar != null) {
                    this.mSeekBar.setProgress(this.nLiangdu);
                }
            }
        } else if (this.nLiangdu != StSet.GetBLDay()) {
            this.nLiangdu = StSet.GetBLDay();
            if (this.mSeekBar != null) {
                this.mSeekBar.setProgress(this.nLiangdu);
            }
        }
    }

    public void InintVolBar() {
        if (this.mFloatLayoutNaw != this.mFloatLayoutVol) {
            Destroy();
            this.mFloatLayoutNaw = this.mFloatLayoutVol;
            InintWinManage(VOL_BAR_SIZEX, 0, this.m_Context);
            this.wManager.addView(this.mFloatLayoutVol, this.wmParams);
        }
    }

    public void Destroy() {
        Log.i(TAG, "Destroy==" + this.mFloatLayoutNaw);
        if (this.mFloatLayoutNaw != null) {
            this.wManager.removeView(this.mFloatLayoutNaw);
            this.mFloatLayoutNaw = null;
        }
    }

    public void WinShow() {
        if (this.mFloatLayoutNaw != this.mFloatLayoutScreenFor) {
            InintVolBar();
            if (this.mFloatLayoutVol == this.mFloatLayoutNaw) {
                this.wmParams.height = 80;
                this.mFloatLayoutVol.setVisibility(0);
                this.nShowTime = 60;
                this.wManager.updateViewLayout(this.mFloatLayoutVol, this.wmParams);
            }
        }
    }

    public void WinHide() {
        if (this.mFloatLayoutVol == this.mFloatLayoutNaw) {
            this.wmParams.height = 0;
            this.mFloatLayoutVol.setVisibility(8);
            this.wManager.updateViewLayout(this.mFloatLayoutVol, this.wmParams);
        }
    }

    public void onProgressChanged(SeekBar seekbar, int progress, boolean frpmTouch) {
        this.nShowTime = 60;
        if (Mcu.GetIll() == 1 && StSet.GetBLIll() == 1) {
            StSet.SetBLNight(progress);
        } else {
            StSet.SetBLDay(progress);
        }
        seekbar.setProgress(progress);
    }

    public void onStartTrackingTouch(SeekBar seekbar) {
    }

    public void onStopTrackingTouch(SeekBar seekbar) {
    }
}
