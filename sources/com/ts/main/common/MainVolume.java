package com.ts.main.common;

import android.app.Application;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanIF;
import com.ts.main.txz.AmapAuto;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;

public class MainVolume implements SeekBar.OnSeekBarChangeListener {
    static MainVolume MyVolume = null;
    private static final String TAG = "MainVolume";
    public static final int VOL_BAR_OUTPOSY = -79;
    public static final int VOL_BAR_POSX = 24;
    public static final int VOL_BAR_POSX1 = 301;
    public static final int VOL_BAR_POSY = 20;
    public static final int VOL_BAR_POSY1 = 137;
    public static int VOL_BAR_SIZEX = 976;
    public static final int VOL_BAR_SIZEX1 = 166;
    public static final int VOL_BAR_SIZEY = 79;
    public static final int VOL_BAR_SIZEY1 = 166;
    public static int nBklisOn = 0;
    private static int screenOrientation = 2;
    Button Btnsst_cancel;
    boolean bFullScreen = true;
    boolean bTouchDn = false;
    private boolean isNewVolume = false;
    private boolean isVertical = false;
    /* access modifiers changed from: private */
    public Evc mEvc = Evc.GetInstance();
    public RelativeLayout mFloatLayoutNaw = null;
    private RelativeLayout mFloatLayoutScreenFor;
    private RelativeLayout mFloatLayoutVol;
    LinearLayout mLv;
    Button mProcessName;
    SeekBar mSeekBar;
    Context m_Context = null;
    TextView mtrackText;
    TextView mtrackforbiden;
    public int nAidlVolumeShow = 0;
    private int nBtVolold = -1;
    private int nMusicVolold = -1;
    private int nMute = 0;
    private int nMuteState = 0;
    private int nNVolold = -1;
    int nNaviVoiceState = 0;
    int nVolDnColor = 0;
    int nVolTimeDelay = 0;
    int nVolUpColor = 0;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    public static MainVolume GetInstance() {
        if (MyVolume == null) {
            MyVolume = new MainVolume();
        }
        return MyVolume;
    }

    public void InintWinManage(int nSizeX, int nSizeY, int nPosX, int nPosY, Context MyContext) {
        this.wManager = (WindowManager) MyContext.getSystemService("window");
        this.wmParams = new WindowManager.LayoutParams();
        if (this.mFloatLayoutNaw != this.mFloatLayoutScreenFor) {
            MainUI.GetInstance();
            if (MainUI.IsCameraMode() == 1) {
                this.wmParams.type = 2010;
            } else {
                this.wmParams.type = 2003;
            }
        } else {
            this.wmParams.type = 2003;
            this.mFloatLayoutNaw.setFocusableInTouchMode(true);
            this.mFloatLayoutNaw.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keycode, KeyEvent event) {
                    if (keycode != 4) {
                        return false;
                    }
                    MainUI.GetInstance().BackToLauncher();
                    return true;
                }
            });
        }
        this.wmParams.format = 1;
        if (nSizeX == MainUI.mScrW || nSizeY == MainUI.mScrH || nSizeY == 480) {
            if (this.mFloatLayoutNaw == this.mFloatLayoutScreenFor) {
                this.wmParams.flags |= 2048;
                this.wmParams.flags |= 256;
                if (WinShow.getTopPackName().equals("com.mxtech.videoplayer.pro")) {
                    this.wmParams.flags |= 67108864;
                }
            } else {
                this.wmParams.flags |= 1024;
                this.wmParams.systemUiVisibility = 516;
            }
            this.bFullScreen = true;
        } else {
            this.wmParams.flags = 8;
        }
        if (this.isNewVolume) {
            this.wmParams.gravity = 17;
        } else {
            this.wmParams.gravity = 81;
        }
        this.wmParams.width = nSizeX;
        this.wmParams.height = nSizeY;
    }

    public void Inint(Application MyApplication, Context MyContext) {
        if (this.m_Context == null) {
            this.m_Context = MyContext;
            this.nMusicVolold = -1;
            this.nBtVolold = -1;
            this.nNVolold = -1;
            this.nMute = 0;
            this.mFloatLayoutScreenFor = (RelativeLayout) LayoutInflater.from(MyApplication).inflate(R.layout.screen_forbiden, (ViewGroup) null);
            this.mFloatLayoutScreenFor.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            this.mtrackforbiden = (TextView) this.mFloatLayoutScreenFor.findViewById(R.id.forbiden);
            this.mtrackforbiden.setText(R.string.video_state_forbiden);
            initVolumBarView(MyContext);
            Log.i(TAG, "nBklisOn=" + nBklisOn);
        }
    }

    private void initVolumBarView(Context context) {
        this.nVolUpColor = context.getResources().getColor(R.color.vol_bar_up);
        this.nVolDnColor = context.getResources().getColor(R.color.vol_bar_dn);
        this.isNewVolume = MainSet.GetInstance().IsPCBAVol();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (this.isNewVolume) {
            this.mFloatLayoutVol = (RelativeLayout) inflater.inflate(R.layout.special_volume, (ViewGroup) null);
        } else if (MainSet.IsVSUI() || MainSet.IsBmwX1()) {
            this.isVertical = true;
            this.mFloatLayoutVol = (RelativeLayout) inflater.inflate(R.layout.vertical_volume, (ViewGroup) null);
        } else {
            this.isVertical = false;
            this.mFloatLayoutVol = (RelativeLayout) inflater.inflate(R.layout.common_volume, (ViewGroup) null);
        }
        this.mProcessName = (Button) this.mFloatLayoutVol.findViewById(R.id.volmodename);
        if (!this.isVertical) {
            this.mProcessName.setBackgroundColor(ViewCompat.MEASURED_SIZE_MASK);
        }
        this.mProcessName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Mcu.SetCkey(16);
            }
        });
        this.mtrackText = (TextView) this.mFloatLayoutVol.findViewById(R.id.volsize);
        if (this.isNewVolume) {
            this.mSeekBar = (SeekBar) this.mFloatLayoutVol.findViewById(R.id.seekBar1);
            this.mSeekBar.setOnSeekBarChangeListener(this);
            this.mProcessName.setBackgroundResource(R.drawable.main_volume_btn);
        } else if (this.isVertical) {
            this.mLv = (LinearLayout) this.mFloatLayoutVol.findViewById(R.id.lv);
            this.mProcessName.setBackgroundResource(R.drawable.vol_popup_music);
            this.mProcessName.setText(TXZResourceManager.STYLE_DEFAULT);
        } else {
            this.mSeekBar = (SeekBar) this.mFloatLayoutVol.findViewById(R.id.seekBar1);
            this.mSeekBar.setOnSeekBarChangeListener(this);
            this.mProcessName.setBackgroundResource(R.drawable.btn_vol_music);
        }
        this.mtrackText.setText(new StringBuilder().append(Iop.GetVolume(0)).toString());
        this.nMusicVolold = Iop.GetVolume(0);
        this.nBtVolold = Iop.GetVolume(1);
        this.nNVolold = StSet.GetNvol();
    }

    public void ResetVolState() {
        this.nMusicVolold = Iop.GetVolume(0);
        this.nBtVolold = Iop.GetVolume(1);
        this.nNVolold = StSet.GetNvol();
        Log.i(TAG, "ResetVolState= nMusicVolold==" + Iop.GetVolume(0));
    }

    public void InintVolBar() {
        int orientation = this.m_Context.getResources().getConfiguration().orientation;
        if (this.mFloatLayoutNaw != this.mFloatLayoutVol || orientation != screenOrientation) {
            Destroy();
            if (orientation != screenOrientation) {
                VOL_BAR_SIZEX = this.m_Context.getResources().getInteger(R.integer.volbar_sizex);
                initVolumBarView(this.m_Context);
                screenOrientation = orientation;
            }
            this.mFloatLayoutNaw = this.mFloatLayoutVol;
            if (this.isNewVolume) {
                InintWinManage(568, 0, 0, 0, this.m_Context);
            } else if (MainSet.GetScreenType() == 5) {
                InintWinManage(VOL_BAR_SIZEX, 0, 151, -79, this.m_Context);
            } else if (this.isVertical) {
                InintWinManage(166, 0, 301, -79, this.m_Context);
            } else {
                InintWinManage(VOL_BAR_SIZEX, 0, 24, -79, this.m_Context);
            }
            this.wManager.addView(this.mFloatLayoutVol, this.wmParams);
        }
    }

    public void InintForbidenScreen() {
        if (this.mFloatLayoutNaw != this.mFloatLayoutScreenFor) {
            Destroy();
            this.bFullScreen = true;
            this.mFloatLayoutNaw = this.mFloatLayoutScreenFor;
            if (MainUI.mScrW <= 0 || MainUI.mScrH <= 0) {
                InintWinManage(1024, 600, 0, 0, this.m_Context);
            } else if (MainSet.GetScreenType() == 3) {
                InintWinManage(768, 441, 0, 0, this.m_Context);
                this.wmParams.width = -1;
                this.wmParams.height = -1;
            } else if (MainSet.GetScreenType() == 6) {
                InintWinManage(MainUI.mScrH, 480, 0, 0, this.m_Context);
                this.wmParams.width = -1;
                this.wmParams.height = -1;
            } else if (MainSet.GetScreenType() == 8) {
                InintWinManage(MainUI.mScrW, MainUI.mScrH, 0, 0, this.m_Context);
                this.wmParams.width = -1;
                this.wmParams.height = -1;
            } else {
                InintWinManage(MainUI.mScrW, MainUI.mScrH, 0, 0, this.m_Context);
                this.wmParams.width = -1;
                this.wmParams.height = -1;
            }
            this.wManager.addView(this.mFloatLayoutScreenFor, this.wmParams);
        }
    }

    public void addview() {
        if (this.mLv != null && this.mLv.getChildCount() <= 20) {
            ImageView iv = new ImageView(this.m_Context);
            iv.setBackgroundResource(R.drawable.vol_popup_dot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(5, 4);
            params.setMargins(1, 0, 0, 0);
            this.mLv.addView(iv, params);
        }
    }

    public void removeView() {
        int count;
        if (this.mLv != null && (count = this.mLv.getChildCount()) > 0) {
            this.mLv.removeViewAt(count - 1);
        }
    }

    public void removeAllView() {
        if (this.mLv != null) {
            this.mLv.removeAllViews();
        }
    }

    public void setProgress(int progress) {
        Log.d("lh", "setProgress");
        if (this.mLv != null) {
            this.mLv.removeAllViews();
            if (progress <= 20) {
                for (int i = 0; i < progress; i++) {
                    ImageView iv = new ImageView(this.m_Context);
                    iv.setBackgroundResource(R.drawable.vol_popup_dot);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(5, 4);
                    params.setMargins(1, 0, 0, 0);
                    this.mLv.addView(iv, params);
                }
            }
        }
    }

    public void InintScreen(RelativeLayout v) {
        if (this.mFloatLayoutNaw != v) {
            Log.i(TAG, "InintScreen");
            Destroy();
            this.mFloatLayoutNaw = v;
            if (MainUI.mScrW <= 0 || MainUI.mScrH <= 0) {
                InintWinManage(1024, 600, 0, 0, this.m_Context);
            } else if (MainSet.GetScreenType() == 3) {
                InintWinManage(this.m_Context.getResources().getDimensionPixelOffset(R.dimen.screen_768backcar_width), this.m_Context.getResources().getDimensionPixelOffset(R.dimen.screen_768backcar_height), 0, 0, this.m_Context);
                this.wmParams.gravity = 49;
                this.wmParams.flags |= 1024;
                this.wmParams.systemUiVisibility = 516;
            } else if (MainSet.GetScreenType() == 11) {
                this.wmParams.width = -1;
                this.wmParams.height = -1;
            } else if (MainSet.GetScreenType() == 6) {
                InintWinManage(800, 620, 0, 0, this.m_Context);
                this.wmParams.width = -1;
                this.wmParams.height = -1;
            } else if (MainSet.GetScreenType() == 8) {
                InintWinManage(1024, 768, 0, 0, this.m_Context);
            } else {
                InintWinManage(MainUI.mScrW, MainUI.mScrH, 0, 0, this.m_Context);
                if (this.m_Context != null && this.m_Context.getResources().getConfiguration().orientation == 1) {
                    Log.i(TAG, "InintScreen getConfiguration().orientation ==" + this.m_Context.getResources().getConfiguration().orientation);
                    this.wmParams.width = -1;
                    this.wmParams.height = -1;
                }
            }
            this.wManager.addView(v, this.wmParams);
        }
    }

    public void InintScreen(RelativeLayout v, int x, int y) {
        if (this.mFloatLayoutNaw != v) {
            Log.i(TAG, "InintScreen");
            Destroy();
            this.mFloatLayoutNaw = v;
            InintWinManage(x, y, 0, 0, this.m_Context);
            this.wManager.addView(v, this.wmParams);
        }
    }

    public void Destroy() {
        Log.i(TAG, "Destroy==" + this.mFloatLayoutNaw);
        if (this.mFloatLayoutNaw != null) {
            this.wManager.removeView(this.mFloatLayoutNaw);
            this.mFloatLayoutNaw = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void InintVolBarState() {
        if (Iop.GetMute() == 1) {
            this.mProcessName.setTextColor(this.nVolDnColor);
            this.mtrackText.setTextColor(this.nVolDnColor);
            if (this.isNewVolume) {
                this.mProcessName.setBackgroundResource(R.drawable.main_volume02_btn);
            } else if (this.isVertical) {
                removeAllView();
                this.mProcessName.setText(TXZResourceManager.STYLE_DEFAULT);
                this.mProcessName.setBackgroundResource(R.drawable.vol_popup_mute);
            } else {
                this.mProcessName.setBackgroundResource(R.drawable.btn_vol_silent);
            }
        } else {
            this.mProcessName.setTextColor(this.nVolUpColor);
            this.mtrackText.setTextColor(this.nVolUpColor);
        }
        if (Iop.GetMediaOrBlue() == 1) {
            Log.d("volume", "bt");
            if (this.isNewVolume) {
                this.mSeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
                this.mSeekBar.setMax(30);
                this.mSeekBar.setOnSeekBarChangeListener(this);
            } else if (!this.isVertical) {
                this.mSeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
                this.mSeekBar.setMax(30);
                this.mSeekBar.setOnSeekBarChangeListener(this);
            }
            if (Iop.GetMute() == 1) {
                this.mtrackText.setText(new StringBuilder().append(Iop.GetVolume(1)).toString());
            } else {
                if (this.isNewVolume) {
                    this.mProcessName.setBackgroundResource(R.drawable.main_volume03_btn);
                    Log.d("volume", "bt isNewVolume");
                } else if (this.isVertical) {
                    this.mProcessName.setBackgroundResource(R.drawable.vol_popup_bt);
                    this.mProcessName.setText(TXZResourceManager.STYLE_DEFAULT);
                } else {
                    this.mProcessName.setBackgroundResource(R.drawable.btn_vol_bt);
                }
                this.mtrackText.setText(new StringBuilder().append(Iop.GetVolume(1)).toString());
            }
            if (this.isNewVolume) {
                this.mSeekBar.setProgress(Iop.GetVolume(1));
            } else if (this.isVertical) {
                setProgress((int) (((double) Iop.GetVolume(1)) / 1.5d));
                if (MainSet.IsVSUI() || MainSet.IsBmwX1()) {
                    this.mFloatLayoutVol.setBackgroundResource(R.drawable.vol_popup_box);
                } else {
                    this.mFloatLayoutVol.setBackgroundResource(R.drawable.vol_popup_box);
                }
            } else {
                this.mSeekBar.setProgress(Iop.GetVolume(1));
                if (MainSet.IsVSUI() || MainSet.IsBmwX1()) {
                    this.mFloatLayoutVol.setBackgroundResource(R.drawable.vertical_vol_bg_bt);
                } else {
                    this.mFloatLayoutVol.setBackgroundResource(R.drawable.common_btvol_bg);
                }
            }
        } else if (!Evc.bNaviVol || !(Evc.nNaviSpeeking == 1 || Evc.nNaviSpeeShow == 1)) {
            Log.d("volume", "common");
            if (this.isNewVolume) {
                this.mSeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
                this.mSeekBar.setMax(30);
                this.mSeekBar.setOnSeekBarChangeListener(this);
            } else if (!this.isVertical) {
                this.mSeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
                this.mSeekBar.setMax(30);
                this.mSeekBar.setOnSeekBarChangeListener(this);
            }
            if (Iop.GetMute() == 1) {
                this.mtrackText.setText(new StringBuilder().append(Iop.GetVolume(0)).toString());
            } else {
                if (this.isNewVolume) {
                    this.mProcessName.setBackgroundResource(R.drawable.main_volume05_btn);
                } else if (this.isVertical) {
                    this.mProcessName.setBackgroundResource(R.drawable.vol_popup_music);
                    this.mProcessName.setText(TXZResourceManager.STYLE_DEFAULT);
                } else {
                    this.mProcessName.setBackgroundResource(R.drawable.btn_vol_music);
                }
                this.mtrackText.setText(new StringBuilder().append(Iop.GetVolume(0)).toString());
            }
            if (this.isNewVolume) {
                this.mSeekBar.setProgress(Iop.GetVolume(0));
            } else if (this.isVertical) {
                setProgress((int) (((double) Iop.GetVolume(0)) / 1.5d));
                if (MainSet.IsVSUI() || MainSet.IsBmwX1()) {
                    this.mFloatLayoutVol.setBackgroundResource(R.drawable.vol_popup_box);
                } else {
                    this.mFloatLayoutVol.setBackgroundResource(R.drawable.vol_popup_box);
                }
            } else {
                this.mSeekBar.setProgress(Iop.GetVolume(0));
                if (MainSet.IsVSUI() || MainSet.IsBmwX1()) {
                    this.mFloatLayoutVol.setBackgroundResource(R.drawable.vertical_vol_bg);
                } else {
                    this.mFloatLayoutVol.setBackgroundResource(R.drawable.common_vol_bg);
                }
            }
        } else {
            Log.d("volume", "navi");
            if (this.isNewVolume) {
                this.mSeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
                this.mSeekBar.setMax(30);
                this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    public void onStopTrackingTouch(SeekBar arg0) {
                    }

                    public void onStartTrackingTouch(SeekBar arg0) {
                    }

                    public void onProgressChanged(SeekBar seekbar, int progress, boolean frpmTouch) {
                        MainVolume.this.mEvc.evol_navivol_set(progress);
                    }
                });
            } else if (!this.isVertical) {
                this.mSeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
                this.mSeekBar.setMax(Evc.GetInstance().Gis_vol_max);
                this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    public void onStopTrackingTouch(SeekBar arg0) {
                    }

                    public void onStartTrackingTouch(SeekBar arg0) {
                    }

                    public void onProgressChanged(SeekBar seekbar, int progress, boolean frpmTouch) {
                        MainVolume.this.mEvc.evol_navivol_set(progress);
                    }
                });
            }
            if (Iop.GetMute() == 1) {
                this.mtrackText.setText(new StringBuilder().append(Iop.GetVolume(0)).toString());
            } else {
                if (this.isNewVolume) {
                    this.mProcessName.setBackgroundResource(R.drawable.main_volume04_btn);
                } else if (this.isVertical) {
                    this.mProcessName.setBackgroundResource(R.drawable.vol_popup_navi);
                    this.mProcessName.setText(TXZResourceManager.STYLE_DEFAULT);
                } else {
                    this.mProcessName.setBackgroundResource(R.drawable.btn_vol_navi);
                }
                this.mtrackText.setText(new StringBuilder().append(StSet.GetNvol()).toString());
            }
            if (this.isNewVolume) {
                this.mSeekBar.setProgress(StSet.GetNvol());
            } else if (this.isVertical) {
                setProgress(StSet.GetNvol() / 5);
                this.mFloatLayoutVol.setBackgroundResource(R.drawable.vol_popup_box);
            } else {
                this.mSeekBar.setProgress(StSet.GetNvol());
                this.mFloatLayoutVol.setBackgroundResource(R.drawable.vertical_vol_bg_navi);
            }
            Evc.nNaviSpeeShow = 1;
        }
        if (Iop.GetMute() == 1 && this.isVertical) {
            removeAllView();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean NaviVolShow() {
        return this.nNVolold != StSet.GetNvol() && Evc.bNaviVol && (Evc.nNaviSpeeking == 1 || Evc.nNaviSpeeShow == 1);
    }

    public void CheckVol() {
        if ((FtSet.IsIconExist(1) == 0 || CanIF.IsForceReadNaviSta() > 0) && this.nNaviVoiceState != Evc.nNaviSpeeking) {
            this.nNaviVoiceState = Evc.nNaviSpeeking;
            CanIF.DealGpsVoice(this.nNaviVoiceState);
        }
        if (this.nAidlVolumeShow == 1) {
            VolWinShow();
            this.nAidlVolumeShow = 0;
        }
        if (this.nMuteState != Iop.GetMute()) {
            this.nMuteState = Iop.GetMute();
            AmapAuto.SendMuteState(this.nMuteState);
        }
        if (this.nMute != Iop.GetMute() || NaviVolShow() || ((this.nBtVolold != Iop.GetVolume(1) && Iop.GetMediaOrBlue() == 1) || this.nMusicVolold != Iop.GetVolume(0))) {
            this.nBtVolold = Iop.GetVolume(1);
            this.nMusicVolold = Iop.GetVolume(0);
            this.nNVolold = StSet.GetNvol();
            this.nMute = Iop.GetMute();
            VolWinShow();
        }
        if (this.nVolTimeDelay > 0 && Iop.GetMute() == 0) {
            this.nVolTimeDelay--;
            if (WinShow.getTopActivityName().equals("com.ts.set.SettingWheelActivity") && this.nVolTimeDelay > 10) {
                this.nVolTimeDelay = 10;
            }
            if (this.nVolTimeDelay == 0) {
                VolWinHide();
            }
        }
    }

    public int IsVolumeShow() {
        if (this.mFloatLayoutVol != this.mFloatLayoutNaw || this.nVolTimeDelay <= 0) {
            return 0;
        }
        return 1;
    }

    public void VolWinShow() {
        if (this.mFloatLayoutNaw != this.mFloatLayoutScreenFor) {
            InintVolBar();
            InintVolBarState();
            if (this.mFloatLayoutVol == this.mFloatLayoutNaw) {
                this.nVolTimeDelay = 60;
                if (this.isNewVolume) {
                    this.mFloatLayoutVol.setVisibility(0);
                    this.wmParams.height = 210;
                } else if (this.isVertical) {
                    this.mFloatLayoutVol.setVisibility(0);
                    this.wmParams.height = 166;
                    this.wmParams.y = 137;
                } else {
                    this.wmParams.height = 79;
                    this.wmParams.y = 20;
                }
                this.wManager.updateViewLayout(this.mFloatLayoutVol, this.wmParams);
                Log.d(TAG, "VolWinShow " + nBklisOn + " !");
            }
        }
    }

    public void VolWinHide() {
        if (this.mFloatLayoutVol == this.mFloatLayoutNaw) {
            if (this.isNewVolume) {
                this.mFloatLayoutVol.setVisibility(8);
                this.wmParams.height = 0;
            } else {
                if (this.isVertical) {
                    this.mFloatLayoutVol.setVisibility(8);
                }
                this.wmParams.height = 0;
                this.wmParams.y = -79;
            }
            this.wManager.updateViewLayout(this.mFloatLayoutVol, this.wmParams);
        }
        Evc.nNaviSpeeShow = 0;
    }

    public void onProgressChanged(SeekBar seekbar, int progress, boolean frpmTouch) {
        if (this.bTouchDn) {
            this.mEvc.evol_vol_set(progress);
        }
    }

    public void onStartTrackingTouch(SeekBar seekbar) {
        this.bTouchDn = true;
    }

    public void onStopTrackingTouch(SeekBar seekbar) {
        this.bTouchDn = false;
    }
}
