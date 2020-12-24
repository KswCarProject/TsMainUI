package com.ts.main.common;

import android.app.Application;
import android.content.Context;
import android.os.SystemClock;
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
import com.ts.MainUI.R;
import com.ts.MainUI.TsFile;

public class MainAlterwin {
    static MainAlterwin MyAlteWin = null;
    Button BtnLoad;
    Button BtnLoadoff;
    long ClockTime;
    int ErrorNum = 0;
    String StrLoad;
    boolean bCppyNaw = false;
    boolean bcloseMesWin = true;
    LayoutInflater inflater;
    private RelativeLayout mLoadWin = null;
    private RelativeLayout mMessageWin = null;
    private RelativeLayout mPowerOffWin = null;
    Application m_Application;
    Context m_Context;
    String message;
    long oldSize = 0;
    /* access modifiers changed from: private */
    public ProgressBar pProgressLoading;
    long temptime = 0;
    TextView tvLoad;
    TextView tvLoad2;
    TextView tvmesShow;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams;

    public static MainAlterwin GetInstance() {
        if (MyAlteWin == null) {
            MyAlteWin = new MainAlterwin();
        }
        return MyAlteWin;
    }

    public void Inint(Application MyApplication, Context MyContext) {
        this.m_Application = MyApplication;
        this.m_Context = MyContext;
        this.inflater = LayoutInflater.from(MyApplication);
        this.wManager = (WindowManager) MyContext.getSystemService("window");
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
                        this.tvLoad2.setText("");
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
                            this.tvLoad2.setText("");
                            UpdateLoadWinStr((String) null);
                        }
                        Log.i("tool", "ErrorNum=" + this.ErrorNum);
                    } else {
                        this.ErrorNum = 0;
                    }
                    this.oldSize = CopySize;
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

    public void ShowPowerOffWin() {
        ShowPowerOffWin(0);
    }

    public void ShowPowerOffWin(int mode) {
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
        if (mode != 0) {
            this.mPowerOffWin.setBackgroundColor(mode);
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
