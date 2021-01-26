package com.ts.main.common;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.mediatek.galleryfeature.pq.filter.Filter;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.can.CanCameraUI;

public class ScreenSet {
    private static final String TAG = "ScreenSet";
    static ScreenSet m_ScreenSet = null;
    private Button BtnBrightness;
    private Button BtnCance;
    private Button BtnContast;
    private Button BtnDefault;
    private Button BtnHue;
    private Button BtnInfoAdd;
    private Button BtnInfoDec;
    private Button BtnSaturation;
    View DialogView;
    Context MyContext;
    /* access modifiers changed from: private */
    public TextView TexShowInfo;
    public boolean bShow = false;
    SeekBar mScreenSeekBar;
    TsDisplay mTsDisplay = TsDisplay.GetInstance();
    Context m_Context;
    public int nAidlHide = 0;
    public int nAidlShow = 0;
    /* access modifiers changed from: private */
    public int nShowSrc = 0;
    /* access modifiers changed from: private */
    public int nShowType = 0;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    /* access modifiers changed from: private */
    public void ClearBtnState() {
        this.BtnBrightness.setSelected(false);
        this.BtnContast.setSelected(false);
        this.BtnHue.setSelected(false);
        this.BtnSaturation.setSelected(false);
    }

    /* access modifiers changed from: package-private */
    public void SetBtnState(int nMode) {
        this.nShowType = nMode;
        switch (nMode) {
            case 0:
                this.BtnBrightness.setSelected(true);
                return;
            case 1:
                this.BtnContast.setSelected(true);
                return;
            case 2:
                this.BtnHue.setSelected(true);
                return;
            case 3:
                this.BtnSaturation.setSelected(true);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void InintBtn(View DialogView2) {
        this.TexShowInfo = (TextView) DialogView2.findViewById(R.id.screenset_showinfo);
        this.BtnBrightness = (Button) DialogView2.findViewById(R.id.screensettings_brightnessid);
        this.BtnBrightness.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ScreenSet.this.ClearBtnState();
                ScreenSet.this.SetBtnState(0);
                ScreenSet.this.mScreenSeekBar.setProgress(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType));
                ScreenSet.this.TexShowInfo.setText(new StringBuilder().append(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType)).toString());
            }
        });
        this.BtnContast = (Button) DialogView2.findViewById(R.id.screensettings_contrastid);
        this.BtnContast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ScreenSet.this.ClearBtnState();
                ScreenSet.this.SetBtnState(1);
                ScreenSet.this.mScreenSeekBar.setProgress(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType));
                ScreenSet.this.TexShowInfo.setText(new StringBuilder().append(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType)).toString());
            }
        });
        this.BtnHue = (Button) DialogView2.findViewById(R.id.screensettings_hueid);
        this.BtnHue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ScreenSet.this.ClearBtnState();
                ScreenSet.this.SetBtnState(2);
                ScreenSet.this.mScreenSeekBar.setProgress(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType));
                ScreenSet.this.TexShowInfo.setText(new StringBuilder().append(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType)).toString());
            }
        });
        this.BtnSaturation = (Button) DialogView2.findViewById(R.id.screensettings_saturationid);
        this.BtnSaturation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ScreenSet.this.ClearBtnState();
                ScreenSet.this.SetBtnState(3);
                ScreenSet.this.mScreenSeekBar.setProgress(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType));
                ScreenSet.this.TexShowInfo.setText(new StringBuilder().append(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType)).toString());
            }
        });
        this.BtnDefault = (Button) DialogView2.findViewById(R.id.screensettings_defaultid);
        this.BtnDefault.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ScreenSet.this.ClearBtnState();
                ScreenSet.this.SetBtnState(0);
                ScreenSet.this.mTsDisplay.UISetDefault(ScreenSet.this.nShowSrc);
                ScreenSet.this.mScreenSeekBar.setProgress(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType));
                ScreenSet.this.TexShowInfo.setText(new StringBuilder().append(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType)).toString());
            }
        });
        this.BtnCance = (Button) DialogView2.findViewById(R.id.screensettings_cancelid);
        this.BtnCance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ScreenSet.this.Hide();
            }
        });
        this.BtnInfoAdd = (Button) DialogView2.findViewById(R.id.screensettings_addingid);
        this.BtnInfoAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ScreenSet.this.mTsDisplay.UIValStep(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType, 1);
                ScreenSet.this.mScreenSeekBar.setProgress(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType));
                ScreenSet.this.TexShowInfo.setText(new StringBuilder().append(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType)).toString());
            }
        });
        this.BtnInfoDec = (Button) DialogView2.findViewById(R.id.screensettings_reduceid);
        this.BtnInfoDec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ScreenSet.this.mTsDisplay.UIValStep(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType, 0);
                ScreenSet.this.mScreenSeekBar.setProgress(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType));
                ScreenSet.this.TexShowInfo.setText(new StringBuilder().append(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType)).toString());
            }
        });
        this.mScreenSeekBar = (SeekBar) DialogView2.findViewById(R.id.screenset_seekBar);
        this.mScreenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            public void onStartTrackingTouch(SeekBar arg0) {
            }

            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                ScreenSet.this.mTsDisplay.UISetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType, ((progress + 1) / 10) * 10);
                ScreenSet.this.TexShowInfo.setText(new StringBuilder().append(ScreenSet.this.mTsDisplay.UIGetVal(ScreenSet.this.nShowSrc, ScreenSet.this.nShowType)).toString());
            }
        });
    }

    public void Inint(Context Ctx) {
        this.MyContext = Ctx;
    }

    public void Hide() {
        if (this.DialogView != null) {
            Log.i(TAG, "Hide " + this.nAidlHide);
            this.wManager.removeViewImmediate(this.DialogView);
            this.DialogView = null;
            this.bShow = false;
        }
    }

    public void Inint() {
        Filter MyFilter = new Filter();
        Log.i(TAG, "Brightness Range=" + MyFilter.nativeGetBrightnessAdjRange());
        Log.i(TAG, "Contrast Range=" + MyFilter.nativeGetContrastAdjRange());
        Log.i(TAG, "Hue Range=" + MyFilter.nativeGetHueAdjRange());
        Log.i(TAG, "sta Range=" + MyFilter.nativeGetSatAdjRange());
        Log.i(TAG, "Brightness default=" + MyFilter.nativeGetBrightnessAdjIndex());
        Log.i(TAG, "Contrast default=" + MyFilter.nativeGetContrastAdjIndex());
        Log.i(TAG, "Hue default=" + MyFilter.nativeGetHueAdjIndex());
        Log.i(TAG, "sta default=" + MyFilter.nativeGetSatAdjIndex());
    }

    public void Show(int nSrc) {
        this.nShowSrc = nSrc;
        if (this.DialogView == null) {
            this.DialogView = LayoutInflater.from(this.MyContext).inflate(R.layout.screensetting, (ViewGroup) null);
            InintBtn(this.DialogView);
            ClearBtnState();
            SetBtnState(0);
            this.mScreenSeekBar.setProgress(this.mTsDisplay.UIGetVal(this.nShowSrc, this.nShowType));
            this.TexShowInfo.setText(new StringBuilder().append(this.mTsDisplay.UIGetVal(this.nShowSrc, this.nShowType)).toString());
            this.wManager = (WindowManager) this.MyContext.getSystemService("window");
            this.wmParams.type = 2010;
            this.wmParams.format = 1;
            if (MainSet.GetScreenType() == 3 || MainSet.GetScreenType() == 6) {
                this.wmParams.gravity = 17;
                this.wmParams.width = CanCameraUI.BTN_TRUMPCHI_GS7_MODE5;
                this.wmParams.height = 200;
            } else {
                this.wmParams.gravity = 83;
                this.wmParams.x = 200;
                this.wmParams.y = 200;
                this.wmParams.width = 800;
                this.wmParams.height = 200;
            }
            this.wManager.addView(this.DialogView, this.wmParams);
            this.bShow = true;
        }
    }

    public static ScreenSet GetInstance() {
        if (m_ScreenSet == null) {
            m_ScreenSet = new ScreenSet();
        }
        return m_ScreenSet;
    }

    public void Task(int nState) {
        if (this.nAidlShow != 0) {
            Log.i(TAG, "nAidlShow " + this.nAidlShow);
            Show(this.nAidlShow);
            this.nAidlShow = 0;
        }
        if (this.nAidlHide != 0) {
            Log.i(TAG, "nAidlHide " + this.nAidlHide);
            Hide();
            this.nAidlHide = 0;
        }
    }
}
