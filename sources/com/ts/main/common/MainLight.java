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
    public static final int VOL_BAR_SIZEX = 463;
    public static final int VOL_BAR_SIZEY = 80;
    private RelativeLayout mFloatLayoutLight;
    private RelativeLayout mFloatLayoutNaw = null;
    SeekBar mSeekBar;
    Context m_Context;
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
        if (this.m_Context == null) {
            this.m_Context = MyContext;
            this.mFloatLayoutLight = (RelativeLayout) LayoutInflater.from(MyApplication).inflate(R.layout.main_light, (ViewGroup) null);
            this.mSeekBar = (SeekBar) this.mFloatLayoutLight.findViewById(R.id.seekBar1);
            this.mSeekBar.setOnSeekBarChangeListener(this);
        }
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

    public void InintLightBar() {
        if (this.mFloatLayoutNaw != this.mFloatLayoutLight) {
            Destroy();
            this.mFloatLayoutNaw = this.mFloatLayoutLight;
            InintWinManage(VOL_BAR_SIZEX, 0, this.m_Context);
            this.wManager.addView(this.mFloatLayoutLight, this.wmParams);
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
        InintLightBar();
        if (this.mFloatLayoutLight == this.mFloatLayoutNaw) {
            this.wmParams.height = 80;
            this.mFloatLayoutLight.setVisibility(0);
            this.nShowTime = 60;
            this.wManager.updateViewLayout(this.mFloatLayoutLight, this.wmParams);
        }
    }

    public void WinHide() {
        if (this.mFloatLayoutLight == this.mFloatLayoutNaw) {
            this.wmParams.height = 0;
            this.mFloatLayoutLight.setVisibility(8);
            this.wManager.updateViewLayout(this.mFloatLayoutLight, this.wmParams);
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
