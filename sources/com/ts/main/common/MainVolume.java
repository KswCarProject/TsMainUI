package com.ts.main.common;

import android.app.Application;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.Log;
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
import com.ts.can.CanCameraUI;
import com.ts.can.CanIF;
import com.ts.can.toyota.dj.CanToyotaDJCarDeviceView;
import com.ts.main.txz.AmapAuto;
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
    public static final int VOL_BAR_SIZEX = 976;
    public static final int VOL_BAR_SIZEX1 = 166;
    public static final int VOL_BAR_SIZEY = 79;
    public static final int VOL_BAR_SIZEY1 = 166;
    public static boolean bVolNotShow = true;
    public static int nBklisOn = 0;
    Button Btnsst_cancel;
    boolean bFullScreen = true;
    boolean bTouchDn = false;
    private boolean isVertical = false;
    /* access modifiers changed from: private */
    public Evc mEvc = Evc.GetInstance();
    private RelativeLayout mFloatLayoutNaw = null;
    private RelativeLayout mFloatLayoutScreenFor;
    private RelativeLayout mFloatLayoutVol;
    LinearLayout mLv;
    Button mProcessName;
    SeekBar mSeekBar;
    Application m_Application;
    Context m_Context;
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
        if (this.mFloatLayoutNaw == this.mFloatLayoutScreenFor) {
            this.wmParams.type = 2003;
        } else if (MainSet.GetInstance().IsCustom("TSYQ")) {
            this.wmParams.type = 2003;
        } else if (CanIF.IsAvmMode() > 0) {
            this.wmParams.type = 2003;
        } else {
            this.wmParams.type = 2010;
        }
        this.wmParams.format = 1;
        if (nSizeX == MainUI.mScrW || nSizeY == MainUI.mScrH || nSizeX == 480) {
            if (this.mFloatLayoutNaw == this.mFloatLayoutScreenFor) {
                this.wmParams.flags |= 2048;
                this.wmParams.flags |= 256;
                if (WinShow.getTopPackName().equals("com.mxtech.videoplayer.pro")) {
                    this.wmParams.flags |= 67108864;
                }
            } else {
                this.wmParams.flags |= 1024;
                this.wmParams.systemUiVisibility = 516;
                if (CanIF.IsAvmMode() > 0) {
                    this.wmParams.systemUiVisibility |= 4096;
                }
            }
            this.bFullScreen = true;
        } else {
            this.wmParams.flags = 8;
        }
        this.wmParams.gravity = 81;
        this.wmParams.width = nSizeX;
        this.wmParams.height = nSizeY;
    }

    public void Inint(Application MyApplication, Context MyContext) {
        this.m_Application = MyApplication;
        this.m_Context = MyContext;
        this.nMusicVolold = -1;
        this.nBtVolold = -1;
        this.nNVolold = -1;
        this.nMute = 0;
        LayoutInflater inflater = LayoutInflater.from(MyApplication);
        this.mFloatLayoutScreenFor = (RelativeLayout) inflater.inflate(R.layout.screen_forbiden, (ViewGroup) null);
        this.mFloatLayoutScreenFor.setBackgroundColor(-16777216);
        this.mtrackforbiden = (TextView) this.mFloatLayoutScreenFor.findViewById(R.id.forbiden);
        this.mtrackforbiden.setText(R.string.drive_state_forbiden);
        this.nVolUpColor = MyContext.getResources().getColor(R.color.vol_bar_up);
        this.nVolDnColor = MyContext.getResources().getColor(R.color.vol_bar_dn);
        if (MainSet.IsVSUI() || MainSet.IsBmwX1()) {
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
        if (this.isVertical) {
            this.mLv = (LinearLayout) this.mFloatLayoutVol.findViewById(R.id.lv);
        } else {
            this.mSeekBar = (SeekBar) this.mFloatLayoutVol.findViewById(R.id.seekBar1);
            this.mSeekBar.setOnSeekBarChangeListener(this);
        }
        if (this.isVertical) {
            this.mProcessName.setBackgroundResource(R.drawable.vol_popup_music);
            this.mProcessName.setText("");
        } else {
            this.mProcessName.setBackgroundResource(R.drawable.btn_vol_music);
        }
        this.mtrackText.setText(new StringBuilder().append(Iop.GetVolume(0)).toString());
        this.nMusicVolold = Iop.GetVolume(0);
        this.nBtVolold = Iop.GetVolume(1);
        this.nNVolold = StSet.GetNvol();
        Log.i(TAG, "nBklisOn=" + nBklisOn);
        InintVolBar();
    }

    public void InintVolBar() {
        if (this.mFloatLayoutNaw != this.mFloatLayoutVol) {
            Destroy();
            this.mFloatLayoutNaw = this.mFloatLayoutVol;
            if (MainSet.GetScreenType() == 5) {
                InintWinManage(800, 0, 151, -79, this.m_Context);
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
                InintWinManage(1024, CanCameraUI.BTN_GOLF_WC_MODE1, 0, 0, this.m_Context);
            } else if (MainSet.GetScreenType() == 3) {
                InintWinManage(CanToyotaDJCarDeviceView.ITEM_PLAY, 441, 0, 0, this.m_Context);
                this.wmParams.width = -1;
                this.wmParams.height = -1;
            } else if (MainSet.GetScreenType() == 8) {
                InintWinManage(MainUI.mScrW, MainUI.mScrH, 0, 0, this.m_Context);
                this.wmParams.width = -1;
                this.wmParams.height = -1;
            } else {
                InintWinManage(MainUI.mScrW, MainUI.mScrH, 0, 0, this.m_Context);
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
                InintWinManage(1024, CanCameraUI.BTN_GOLF_WC_MODE1, 0, 0, this.m_Context);
            } else if (MainSet.GetScreenType() == 3) {
                InintWinManage(CanToyotaDJCarDeviceView.ITEM_PLAY, 441, 0, 0, this.m_Context);
                this.wmParams.width = -1;
                this.wmParams.height = -1;
            } else if (MainSet.GetScreenType() == 8) {
                InintWinManage(1024, CanToyotaDJCarDeviceView.ITEM_PLAY, 0, 0, this.m_Context);
            } else {
                InintWinManage(MainUI.mScrW, MainUI.mScrH, 0, 0, this.m_Context);
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
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x011a, code lost:
        if (com.ts.MainUI.Evc.nNaviSpeeShow == 1) goto L_0x011c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void InintVolBarState() {
        /*
            r9 = this;
            r8 = 30
            r6 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            r3 = 0
            r5 = 0
            r4 = 1
            int r1 = com.yyw.ts70xhw.Iop.GetMute()
            if (r1 != r4) goto L_0x009c
            android.widget.Button r1 = r9.mProcessName
            int r2 = r9.nVolDnColor
            r1.setTextColor(r2)
            android.widget.TextView r1 = r9.mtrackText
            int r2 = r9.nVolDnColor
            r1.setTextColor(r2)
            boolean r1 = r9.isVertical
            if (r1 == 0) goto L_0x0094
            r9.removeAllView()
            android.widget.Button r1 = r9.mProcessName
            java.lang.String r2 = ""
            r1.setText(r2)
            android.widget.Button r1 = r9.mProcessName
            int r2 = com.ts.MainUI.R.drawable.vol_popup_mute
            r1.setBackgroundResource(r2)
        L_0x0030:
            int r1 = com.yyw.ts70xhw.Iop.GetMediaOrBlue()
            if (r1 != r4) goto L_0x010a
            boolean r1 = r9.isVertical
            if (r1 != 0) goto L_0x0049
            android.widget.SeekBar r1 = r9.mSeekBar
            r1.setOnSeekBarChangeListener(r3)
            android.widget.SeekBar r1 = r9.mSeekBar
            r1.setMax(r8)
            android.widget.SeekBar r1 = r9.mSeekBar
            r1.setOnSeekBarChangeListener(r9)
        L_0x0049:
            int r1 = com.yyw.ts70xhw.Iop.GetMute()
            if (r1 != r4) goto L_0x00ab
            android.widget.TextView r1 = r9.mtrackText
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r3 = com.yyw.ts70xhw.Iop.GetVolume(r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.setText(r2)
        L_0x0065:
            boolean r1 = r9.isVertical
            if (r1 == 0) goto L_0x00e4
            int r1 = com.yyw.ts70xhw.Iop.GetVolume(r4)
            double r1 = (double) r1
            double r1 = r1 / r6
            int r0 = (int) r1
            r9.setProgress(r0)
            boolean r1 = com.ts.main.common.MainSet.IsVSUI()
            if (r1 != 0) goto L_0x007f
            boolean r1 = com.ts.main.common.MainSet.IsBmwX1()
            if (r1 == 0) goto L_0x00dc
        L_0x007f:
            android.widget.RelativeLayout r1 = r9.mFloatLayoutVol
            int r2 = com.ts.MainUI.R.drawable.vol_popup_box
            r1.setBackgroundResource(r2)
        L_0x0086:
            int r1 = com.yyw.ts70xhw.Iop.GetMute()
            if (r1 != r4) goto L_0x0093
            boolean r1 = r9.isVertical
            if (r1 == 0) goto L_0x0093
            r9.removeAllView()
        L_0x0093:
            return
        L_0x0094:
            android.widget.Button r1 = r9.mProcessName
            int r2 = com.ts.MainUI.R.drawable.btn_vol_silent
            r1.setBackgroundResource(r2)
            goto L_0x0030
        L_0x009c:
            android.widget.Button r1 = r9.mProcessName
            int r2 = r9.nVolUpColor
            r1.setTextColor(r2)
            android.widget.TextView r1 = r9.mtrackText
            int r2 = r9.nVolUpColor
            r1.setTextColor(r2)
            goto L_0x0030
        L_0x00ab:
            boolean r1 = r9.isVertical
            if (r1 == 0) goto L_0x00d4
            android.widget.Button r1 = r9.mProcessName
            int r2 = com.ts.MainUI.R.drawable.vol_popup_bt
            r1.setBackgroundResource(r2)
            android.widget.Button r1 = r9.mProcessName
            java.lang.String r2 = ""
            r1.setText(r2)
        L_0x00bd:
            android.widget.TextView r1 = r9.mtrackText
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r3 = com.yyw.ts70xhw.Iop.GetVolume(r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.setText(r2)
            goto L_0x0065
        L_0x00d4:
            android.widget.Button r1 = r9.mProcessName
            int r2 = com.ts.MainUI.R.drawable.btn_vol_bt
            r1.setBackgroundResource(r2)
            goto L_0x00bd
        L_0x00dc:
            android.widget.RelativeLayout r1 = r9.mFloatLayoutVol
            int r2 = com.ts.MainUI.R.drawable.vol_popup_box
            r1.setBackgroundResource(r2)
            goto L_0x0086
        L_0x00e4:
            android.widget.SeekBar r1 = r9.mSeekBar
            int r2 = com.yyw.ts70xhw.Iop.GetVolume(r4)
            r1.setProgress(r2)
            boolean r1 = com.ts.main.common.MainSet.IsVSUI()
            if (r1 != 0) goto L_0x00f9
            boolean r1 = com.ts.main.common.MainSet.IsBmwX1()
            if (r1 == 0) goto L_0x0101
        L_0x00f9:
            android.widget.RelativeLayout r1 = r9.mFloatLayoutVol
            int r2 = com.ts.MainUI.R.drawable.vertical_vol_bg_bt
            r1.setBackgroundResource(r2)
            goto L_0x0086
        L_0x0101:
            android.widget.RelativeLayout r1 = r9.mFloatLayoutVol
            int r2 = com.ts.MainUI.R.drawable.common_btvol_bg
            r1.setBackgroundResource(r2)
            goto L_0x0086
        L_0x010a:
            boolean r1 = com.ts.MainUI.Evc.bNaviVol
            if (r1 == 0) goto L_0x01af
            com.ts.MainUI.Evc.GetInstance()
            int r1 = com.ts.MainUI.Evc.nNaviSpeeking
            if (r1 == r4) goto L_0x011c
            com.ts.MainUI.Evc.GetInstance()
            int r1 = com.ts.MainUI.Evc.nNaviSpeeShow
            if (r1 != r4) goto L_0x01af
        L_0x011c:
            boolean r1 = r9.isVertical
            if (r1 != 0) goto L_0x0136
            android.widget.SeekBar r1 = r9.mSeekBar
            r1.setOnSeekBarChangeListener(r3)
            android.widget.SeekBar r1 = r9.mSeekBar
            r2 = 100
            r1.setMax(r2)
            android.widget.SeekBar r1 = r9.mSeekBar
            com.ts.main.common.MainVolume$2 r2 = new com.ts.main.common.MainVolume$2
            r2.<init>()
            r1.setOnSeekBarChangeListener(r2)
        L_0x0136:
            int r1 = com.yyw.ts70xhw.Iop.GetMute()
            if (r1 != r4) goto L_0x016d
            android.widget.TextView r1 = r9.mtrackText
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r3 = com.yyw.ts70xhw.Iop.GetVolume(r5)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.setText(r2)
        L_0x0152:
            boolean r1 = r9.isVertical
            if (r1 == 0) goto L_0x019e
            int r1 = com.yyw.ts70xhw.StSet.GetNvol()
            int r0 = r1 / 5
            r9.setProgress(r0)
            android.widget.RelativeLayout r1 = r9.mFloatLayoutVol
            int r2 = com.ts.MainUI.R.drawable.vol_popup_box
            r1.setBackgroundResource(r2)
        L_0x0166:
            com.ts.MainUI.Evc.GetInstance()
            com.ts.MainUI.Evc.nNaviSpeeShow = r4
            goto L_0x0086
        L_0x016d:
            boolean r1 = r9.isVertical
            if (r1 == 0) goto L_0x0196
            android.widget.Button r1 = r9.mProcessName
            int r2 = com.ts.MainUI.R.drawable.vol_popup_navi
            r1.setBackgroundResource(r2)
            android.widget.Button r1 = r9.mProcessName
            java.lang.String r2 = ""
            r1.setText(r2)
        L_0x017f:
            android.widget.TextView r1 = r9.mtrackText
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r3 = com.yyw.ts70xhw.StSet.GetNvol()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.setText(r2)
            goto L_0x0152
        L_0x0196:
            android.widget.Button r1 = r9.mProcessName
            int r2 = com.ts.MainUI.R.drawable.btn_vol_navi
            r1.setBackgroundResource(r2)
            goto L_0x017f
        L_0x019e:
            android.widget.SeekBar r1 = r9.mSeekBar
            int r2 = com.yyw.ts70xhw.StSet.GetNvol()
            r1.setProgress(r2)
            android.widget.RelativeLayout r1 = r9.mFloatLayoutVol
            int r2 = com.ts.MainUI.R.drawable.vertical_vol_bg_navi
            r1.setBackgroundResource(r2)
            goto L_0x0166
        L_0x01af:
            boolean r1 = r9.isVertical
            if (r1 != 0) goto L_0x01c2
            android.widget.SeekBar r1 = r9.mSeekBar
            r1.setOnSeekBarChangeListener(r3)
            android.widget.SeekBar r1 = r9.mSeekBar
            r1.setMax(r8)
            android.widget.SeekBar r1 = r9.mSeekBar
            r1.setOnSeekBarChangeListener(r9)
        L_0x01c2:
            int r1 = com.yyw.ts70xhw.Iop.GetMute()
            if (r1 != r4) goto L_0x0201
            android.widget.TextView r1 = r9.mtrackText
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r3 = com.yyw.ts70xhw.Iop.GetVolume(r5)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.setText(r2)
        L_0x01de:
            boolean r1 = r9.isVertical
            if (r1 == 0) goto L_0x023b
            int r1 = com.yyw.ts70xhw.Iop.GetVolume(r5)
            double r1 = (double) r1
            double r1 = r1 / r6
            int r0 = (int) r1
            r9.setProgress(r0)
            boolean r1 = com.ts.main.common.MainSet.IsVSUI()
            if (r1 != 0) goto L_0x01f8
            boolean r1 = com.ts.main.common.MainSet.IsBmwX1()
            if (r1 == 0) goto L_0x0232
        L_0x01f8:
            android.widget.RelativeLayout r1 = r9.mFloatLayoutVol
            int r2 = com.ts.MainUI.R.drawable.vol_popup_box
            r1.setBackgroundResource(r2)
            goto L_0x0086
        L_0x0201:
            boolean r1 = r9.isVertical
            if (r1 == 0) goto L_0x022a
            android.widget.Button r1 = r9.mProcessName
            int r2 = com.ts.MainUI.R.drawable.vol_popup_music
            r1.setBackgroundResource(r2)
            android.widget.Button r1 = r9.mProcessName
            java.lang.String r2 = ""
            r1.setText(r2)
        L_0x0213:
            android.widget.TextView r1 = r9.mtrackText
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r3 = com.yyw.ts70xhw.Iop.GetVolume(r5)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.setText(r2)
            goto L_0x01de
        L_0x022a:
            android.widget.Button r1 = r9.mProcessName
            int r2 = com.ts.MainUI.R.drawable.btn_vol_music
            r1.setBackgroundResource(r2)
            goto L_0x0213
        L_0x0232:
            android.widget.RelativeLayout r1 = r9.mFloatLayoutVol
            int r2 = com.ts.MainUI.R.drawable.vol_popup_box
            r1.setBackgroundResource(r2)
            goto L_0x0086
        L_0x023b:
            android.widget.SeekBar r1 = r9.mSeekBar
            int r2 = com.yyw.ts70xhw.Iop.GetVolume(r5)
            r1.setProgress(r2)
            boolean r1 = com.ts.main.common.MainSet.IsVSUI()
            if (r1 != 0) goto L_0x0250
            boolean r1 = com.ts.main.common.MainSet.IsBmwX1()
            if (r1 == 0) goto L_0x0259
        L_0x0250:
            android.widget.RelativeLayout r1 = r9.mFloatLayoutVol
            int r2 = com.ts.MainUI.R.drawable.vertical_vol_bg
            r1.setBackgroundResource(r2)
            goto L_0x0086
        L_0x0259:
            android.widget.RelativeLayout r1 = r9.mFloatLayoutVol
            int r2 = com.ts.MainUI.R.drawable.common_vol_bg
            r1.setBackgroundResource(r2)
            goto L_0x0086
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.common.MainVolume.InintVolBarState():void");
    }

    /* access modifiers changed from: package-private */
    public boolean NaviVolShow() {
        if (this.nNVolold != StSet.GetNvol() && Evc.bNaviVol) {
            Evc.GetInstance();
            if (Evc.nNaviSpeeking == 1) {
                return true;
            }
            Evc.GetInstance();
            if (Evc.nNaviSpeeShow == 1) {
                return true;
            }
        }
        return false;
    }

    public void CheckVol() {
        if (FtSet.IsIconExist(1) == 0) {
            int i = this.nNaviVoiceState;
            Evc.GetInstance();
            if (i != Evc.nNaviSpeeking) {
                Evc.GetInstance();
                this.nNaviVoiceState = Evc.nNaviSpeeking;
                CanIF.DealGpsVoice(this.nNaviVoiceState);
            }
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
        if ((!MainSet.IsFlkj() || !bVolNotShow) && this.mFloatLayoutNaw != this.mFloatLayoutScreenFor) {
            InintVolBar();
            InintVolBarState();
            if (this.mFloatLayoutVol == this.mFloatLayoutNaw) {
                this.nVolTimeDelay = 60;
                if (this.isVertical) {
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
            if (this.isVertical) {
                this.mFloatLayoutVol.setVisibility(8);
            }
            this.wmParams.height = 0;
            this.wmParams.y = -79;
            this.wManager.updateViewLayout(this.mFloatLayoutVol, this.wmParams);
        }
        Evc.GetInstance();
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
