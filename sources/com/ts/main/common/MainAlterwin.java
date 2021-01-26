package com.ts.main.common;

import android.app.Application;
import android.content.Context;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.SdkConstants;
import com.ts.MainUI.R;
import com.ts.MainUI.TsFile;
import com.txznet.sdk.TXZResourceManager;

public class MainAlterwin {
    static MainAlterwin MyAlteWin = null;
    private static final String TAG = "MainAlterwin";
    Button BtnLoad;
    Button BtnLoadoff;
    Button BtnUnc;
    Button BtnUncoff;
    long ClockTime;
    int ErrorNum = 0;
    String StrLoad;
    boolean bCppyNaw = false;
    boolean bStartCompress = false;
    boolean bcloseMesWin = true;
    LayoutInflater inflater;
    private RelativeLayout mBrightnessWin = null;
    private RelativeLayout mLoadWin = null;
    private RelativeLayout mMessageWin = null;
    private RelativeLayout mPowerOffWin = null;
    private RelativeLayout mScrolWin = null;
    private RelativeLayout mUnCompressWin = null;
    private RelativeLayout mUnRegDevWin = null;
    Application m_Application;
    Context m_Context;
    String message;
    long oldSize = 0;
    /* access modifiers changed from: private */
    public ProgressBar pProgressLoading;
    private boolean pow_off_screen_mute = false;
    long temptime = 0;
    TextView tvLoad;
    TextView tvLoad2;
    TextView tvShow;
    TextView tvUnc;
    TextView tvmesShow;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams;
    private WindowManager.LayoutParams wmParamsBright;

    public static MainAlterwin GetInstance() {
        if (MyAlteWin == null) {
            MyAlteWin = new MainAlterwin();
        }
        return MyAlteWin;
    }

    public void Inint(Application MyApplication, Context MyContext) {
        if (this.m_Context == null) {
            this.m_Application = MyApplication;
            this.m_Context = MyContext;
            this.inflater = LayoutInflater.from(MyApplication);
            this.wManager = (WindowManager) MyContext.getSystemService("window");
            if (this.m_Context.getResources().getIdentifier("power_off_screen_mute", SdkConstants.TAG_STRING, MyApplication.getPackageName()) > 0) {
                this.pow_off_screen_mute = true;
            }
        }
    }

    public void task() {
        if (this.mLoadWin != null) {
            if (!(this.tvLoad2 == null || this.StrLoad == null)) {
                this.tvLoad2.setText(this.StrLoad);
            }
            if (SystemClock.uptimeMillis() - this.temptime > 1000) {
                this.temptime = SystemClock.uptimeMillis();
                long time = SystemClock.uptimeMillis() - this.ClockTime;
                if (this.bCppyNaw) {
                    long CopySize = tool.GetInstance().GetSdMapSize();
                    long TotalSize = tool.GetInstance().totalSize;
                    if (!(this.tvLoad == null || this.StrLoad == null || time <= 1000)) {
                        this.tvLoad2.setText(this.StrLoad);
                        this.tvLoad.setText(((CopySize / 1024) / 1024) + "MB/" + ((TotalSize / 1024) / 1024) + "MB " + (((CopySize / 1024) / 1024) / (time / 1000)) + "MB/s");
                    }
                    if (CopySize == TotalSize) {
                        this.pProgressLoading.setVisibility(4);
                        this.BtnLoadoff.setVisibility(0);
                        this.tvLoad.setText(String.valueOf(this.m_Context.getResources().getString(R.string.copy_end)) + " Time=" + (time / 1000) + "s");
                        this.bCppyNaw = false;
                        this.tvLoad2.setText(TXZResourceManager.STYLE_DEFAULT);
                        UpdateLoadWinStr((String) null);
                    }
                    if (this.oldSize == CopySize) {
                        if (!TsFile.fileIsExists(tool.GetInstance().MapPath)) {
                            this.pProgressLoading.setVisibility(4);
                            this.BtnLoad.setVisibility(0);
                            this.BtnLoadoff.setVisibility(0);
                            this.tvLoad.setText(this.m_Context.getResources().getString(R.string.copy_bad));
                            this.bCppyNaw = false;
                            this.ErrorNum = 0;
                            this.tvLoad2.setText(TXZResourceManager.STYLE_DEFAULT);
                            UpdateLoadWinStr((String) null);
                        }
                        Log.i("tool", "ErrorNum=" + this.ErrorNum);
                    } else {
                        this.ErrorNum = 0;
                    }
                    this.oldSize = CopySize;
                }
            }
        } else if (this.mUnCompressWin != null) {
            if (this.bStartCompress && UnzipFile.TotalSize > 0) {
                this.tvUnc.setText(String.valueOf(this.m_Context.getResources().getString(R.string.comress_updatefile)) + " " + ((UnzipFile.NowSize * 100) / UnzipFile.TotalSize) + "%");
                if (UnzipFile.TotalSize == UnzipFile.NowSize) {
                    HideUncompessWin();
                    MainSet.GetInstance().bCheckUpdateFile(false);
                }
            }
        } else if (this.mMessageWin != null && this.tvmesShow != null && this.message != null) {
            this.tvmesShow.setText(this.message);
        }
    }

    public void UpdateLoadWinStr(String str) {
        this.StrLoad = str;
    }

    public void HideLoadWin() {
        if (this.mLoadWin != null) {
            this.wManager.removeView(this.mLoadWin);
            this.mLoadWin = null;
            this.tvLoad = null;
        }
    }

    public void ShowLoadWin(String Str, boolean bCopy) {
        if (this.mLoadWin != null) {
            HideLoadWin();
        }
        this.bCppyNaw = false;
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.flags = 8;
        this.wmParams.gravity = 49;
        this.wmParams.width = -1;
        this.wmParams.height = -1;
        this.wmParams.flags |= 1024;
        this.wmParams.systemUiVisibility = 516;
        this.mLoadWin = (RelativeLayout) this.inflater.inflate(R.layout.screen_load, (ViewGroup) null);
        this.tvLoad = (TextView) this.mLoadWin.findViewById(R.id.loadText);
        this.tvLoad.setText(Str);
        this.tvLoad2 = (TextView) this.mLoadWin.findViewById(R.id.loadText0);
        this.BtnLoad = (Button) this.mLoadWin.findViewById(R.id.Btnload);
        this.BtnLoadoff = (Button) this.mLoadWin.findViewById(R.id.Btnloadoff);
        this.pProgressLoading = (ProgressBar) this.mLoadWin.findViewById(R.id.loadprocess);
        this.pProgressLoading.setVisibility(4);
        if (!bCopy) {
            this.BtnLoad.setVisibility(4);
        }
        this.BtnLoad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (!MainAlterwin.this.bCppyNaw) {
                    MainAlterwin.this.ClockTime = SystemClock.uptimeMillis();
                    tool.GetInstance().CopyMapdata();
                    MainAlterwin.this.bCppyNaw = true;
                    MainAlterwin.this.pProgressLoading.setVisibility(0);
                    MainAlterwin.this.BtnLoad.setVisibility(4);
                    MainAlterwin.this.BtnLoadoff.setVisibility(4);
                }
            }
        });
        this.BtnLoadoff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                MainAlterwin.this.HideLoadWin();
            }
        });
        this.wManager.addView(this.mLoadWin, this.wmParams);
    }

    public void HideUncompessWin() {
        if (this.mUnCompressWin != null) {
            this.wManager.removeView(this.mUnCompressWin);
            this.mUnCompressWin = null;
        }
    }

    public void ShowUncompessWin() {
        if (this.mUnCompressWin != null) {
            HideUncompessWin();
        }
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.flags = 8;
        this.wmParams.gravity = 49;
        this.wmParams.width = -1;
        this.wmParams.height = -1;
        this.wmParams.flags |= 1024;
        this.wmParams.systemUiVisibility = 516;
        this.mUnCompressWin = (RelativeLayout) this.inflater.inflate(R.layout.screen_uncompress, (ViewGroup) null);
        this.bStartCompress = false;
        this.tvUnc = (TextView) this.mUnCompressWin.findViewById(R.id.uncomperssText0);
        this.tvUnc.setText(R.string.comress_checked_updatefile);
        this.BtnUnc = (Button) this.mUnCompressWin.findViewById(R.id.Btnuncomperss);
        this.BtnUnc.setText(R.string.comress_checked_update);
        this.BtnUnc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                MainAlterwin.this.bStartCompress = true;
                tool.GetInstance().UnCompressFile();
                MainAlterwin.this.BtnUnc.setVisibility(4);
                MainAlterwin.this.BtnUncoff.setVisibility(4);
            }
        });
        this.BtnUncoff = (Button) this.mUnCompressWin.findViewById(R.id.Btnuncomperssoff);
        this.BtnUncoff.setText(R.string.comress_checked_cancel);
        this.BtnUncoff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                MainAlterwin.this.HideUncompessWin();
            }
        });
        this.wManager.addView(this.mUnCompressWin, this.wmParams);
    }

    public void HidenUnRegWin() {
        if (this.mUnRegDevWin != null) {
            this.wManager.removeView(this.mUnRegDevWin);
            this.mUnRegDevWin = null;
        }
    }

    public void ShowTv(String str) {
        if (this.tvShow != null) {
            this.tvShow.setText(str);
        }
    }

    public void ShowUnRegWin(int nState) {
        if (this.mUnRegDevWin == null) {
            this.wmParams = new WindowManager.LayoutParams();
            this.wmParams.type = 2003;
            this.wmParams.format = 1;
            this.wmParams.flags = 24;
            this.wmParams.gravity = 49;
            this.wmParams.width = -1;
            this.wmParams.height = -1;
            this.wmParams.flags |= 1024;
            this.wmParams.systemUiVisibility = 516;
            this.mUnRegDevWin = (RelativeLayout) this.inflater.inflate(R.layout.screen_unregdev, (ViewGroup) null);
            this.tvShow = (TextView) this.mUnRegDevWin.findViewById(R.id.unregText);
            if (nState == 0) {
                this.tvShow.setText(this.m_Context.getResources().getString(R.string.unregdev_information));
            } else if (nState == 1) {
                this.tvShow.setText(this.m_Context.getResources().getString(R.string.unregdev_information_error));
            } else if (nState == 3) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.tvShow.getLayoutParams();
                params.addRule(15);
                params.topMargin = 0;
                this.tvShow.setLayoutParams(params);
                this.tvShow.setText(this.m_Context.getResources().getString(R.string.record_start));
                this.tvShow.setTextColor(-1);
                this.tvShow.setTextSize(35.0f);
            }
            this.wManager.addView(this.mUnRegDevWin, this.wmParams);
        } else if (nState == 3) {
            HidenUnRegWin();
        }
    }

    /* access modifiers changed from: package-private */
    public void HidenMessageWin() {
        if (this.mMessageWin != null) {
            this.wManager.removeView(this.mMessageWin);
            this.mMessageWin = null;
        }
        this.tvmesShow = null;
    }

    public void UpdateMessage(String str) {
        this.message = str;
    }

    public void SetMessWinEnClose(boolean bEn) {
        this.bcloseMesWin = bEn;
    }

    public void ShowMessageWin(String str) {
        if (this.mMessageWin != null) {
            HidenMessageWin();
        }
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.gravity = 49;
        this.wmParams.width = -1;
        this.wmParams.height = -1;
        this.mMessageWin = (RelativeLayout) this.inflater.inflate(R.layout.screenshowmessage, (ViewGroup) null);
        this.tvmesShow = (TextView) this.mMessageWin.findViewById(R.id.showmessage);
        if (str != null) {
            this.tvmesShow.setText(str);
        }
        this.mMessageWin.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (!MainAlterwin.this.bcloseMesWin) {
                    return false;
                }
                MainAlterwin.this.HidenMessageWin();
                return false;
            }
        });
        this.wManager.addView(this.mMessageWin, this.wmParams);
    }

    public void ShowScrolWin() {
        if (this.mScrolWin != null) {
            HidenScrolWin();
            return;
        }
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.flags = 8;
        this.wmParams.gravity = 49;
        this.wmParams.width = -1;
        this.wmParams.height = -1;
        this.wmParams.flags |= 1024;
        this.wmParams.systemUiVisibility = 516;
        this.mScrolWin = (RelativeLayout) this.inflater.inflate(R.layout.screen_mute, (ViewGroup) null);
        this.wManager.addView(this.mScrolWin, this.wmParams);
    }

    public void HidenScrolWin() {
        if (this.mScrolWin != null) {
            this.wManager.removeView(this.mScrolWin);
            this.mScrolWin = null;
        }
    }

    public void ShowBrightnessWin() {
        this.wmParamsBright = new WindowManager.LayoutParams();
        this.wmParamsBright.type = 2010;
        this.wmParamsBright.format = 1;
        this.wmParamsBright.flags = 8;
        this.wmParamsBright.gravity = 49;
        this.wmParamsBright.width = -1;
        this.wmParamsBright.height = -1;
        this.wmParamsBright.flags = 1304;
        this.wmParamsBright.systemUiVisibility = 516;
        this.wmParamsBright.alpha = 0.0f;
        this.mBrightnessWin = (RelativeLayout) this.inflater.inflate(R.layout.screen_mute, (ViewGroup) null);
        this.mBrightnessWin.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.wManager.addView(this.mBrightnessWin, this.wmParamsBright);
    }

    public void UpdateBrightnessWin(int nBright) {
        if (this.mBrightnessWin != null) {
            this.wmParamsBright.alpha = (255.0f - ((float) nBright)) / 265.0f;
            this.wManager.updateViewLayout(this.mBrightnessWin, this.wmParamsBright);
        }
    }

    public void ShowPowerOffWin() {
        if (this.mPowerOffWin != null) {
            HidenPoweroffWin();
            return;
        }
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.flags = 8;
        this.wmParams.gravity = 49;
        this.wmParams.width = -1;
        this.wmParams.height = -1;
        this.wmParams.flags |= 1024;
        this.wmParams.systemUiVisibility = 516;
        this.mPowerOffWin = (RelativeLayout) this.inflater.inflate(R.layout.screen_mute, (ViewGroup) null);
        if (this.pow_off_screen_mute) {
            this.mPowerOffWin.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        this.wManager.addView(this.mPowerOffWin, this.wmParams);
    }

    public void HidenPoweroffWin() {
        if (this.mPowerOffWin != null) {
            this.wManager.removeView(this.mPowerOffWin);
            this.mPowerOffWin = null;
        }
    }
}
