package com.ts.can.honda.xbs.accord8;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.bt.BtExe;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarDeviceActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.dvdplayer.definition.MediaDef;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZPoiSearchManager;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;
import java.io.UnsupportedEncodingException;

public class CanAccord8XbsExdView extends CanRelativeCarInfoView implements UserCallBack {
    public static final int PAGE_AUX = 2;
    public static final int PAGE_DISC = 1;
    public static final int PAGE_DVD = 6;
    public static final int PAGE_HDD = 5;
    public static final int PAGE_POW_OF = 7;
    public static final int PAGE_RADIO = 0;
    public static final int PAGE_TEL = 4;
    public static final int PAGE_USB = 3;
    public static final String[] mAmpStaArr = {"", "Bass: ", "Trble: ", "Fade: ", "Balance: ", "Subw: ", "Svc: "};
    public static final String[] mBandDwArr = {"MHz ", "MHz ", "MHz ", "KHz ", "KHz ", "KHz "};
    public static final String[] mBandNameArr = {"FM", "FM1 ", "FM2 ", "AM ", "LW ", "MW "};
    public static final int[] mDsicNumArr = {R.drawable.can_klz_num01, R.drawable.can_klz_num02, R.drawable.can_klz_num03, R.drawable.can_klz_num04, R.drawable.can_klz_num05, R.drawable.can_klz_num06};
    public static final int[] mFreqNumArr = {R.drawable.can_yg_radio_num0, R.drawable.can_yg_radio_num1, R.drawable.can_yg_radio_num2, R.drawable.can_yg_radio_num3, R.drawable.can_yg_radio_num4, R.drawable.can_yg_radio_num5, R.drawable.can_yg_radio_num6, R.drawable.can_yg_radio_num7, R.drawable.can_yg_radio_num8, R.drawable.can_yg_radio_num9};
    private static int mLastDiscSta = 0;
    private static long mLastTick = 0;
    public static int mOldBtSta = 0;
    public static final String[] mPlayModeArr = {"Normal play ", "Repeat one track ", "Repeat all ", "Random ", "Scan ", "D-Scan ", "Repeat one fld ", "random in fld ", "scan fld"};
    public static final String[] mPlayStaArr = {"Playing ", "No disc ", "Busy ", "Load ", "Read ", "Eject ", "Fast forward ", "Fast backward ", "Disc error ", "No data ", "No song ", "Usb load ", "Unsupported"};
    public static final String[] mRadioStaArr = {" ", " ", "TUNE+", "TUNE- ", "SEEK+ ", "SEEK- ", "SCAN ", "A.SEL "};
    public static final String[] mSvcValueArr = {"OFF", "LOW", "MID", "HIGH"};
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private CanDataInfo.Accord8_AudioInfo mAmpData;
    private CanDataInfo.Accord8_CdTextInfo mCdTextData;
    private CanDataInfo.Accord8_DiscInfo mDiscData;
    private CanDataInfo.Accord8_IconInfo mIconData;
    private TextView[] mId3Text;
    private CustomImgView mImgFreqBk;
    private CustomImgView mImgMain;
    /* access modifiers changed from: private */
    public CustomImgView mImgST;
    private CanDataInfo.Accord8_ListInfo mListData;
    protected RelativeLayoutManager mManager;
    /* access modifiers changed from: private */
    public CanDataInfo.Accord8_MediaInfo mMediaData;
    private TextView[] mMediaListText;
    /* access modifiers changed from: private */
    public TextView mMen;
    private CanDataInfo.Accord8_MenuInfo mMenuData;
    private TextView[] mMenuText;
    /* access modifiers changed from: private */
    public CanDataInfo.Accord8_RadioInfo mRadioData;
    /* access modifiers changed from: private */
    public CanDataInfo.Accord8_StaInfo mStaData;
    private TextView mTextAmp;
    private TextView mTextTitle;
    /* access modifiers changed from: private */
    public TextView mTextV1;
    /* access modifiers changed from: private */
    public TextView mTextV2;
    /* access modifiers changed from: private */
    public TextView mTextV3;
    private CanDataInfo.Accord8_TimeInfo mTimeData;
    private CustomImgView[] m_CdSta;

    public CanAccord8XbsExdView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int intValue = ((Integer) v.getTag()).intValue();
        return false;
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_comm_bg);
        this.mId3Text = new TextView[5];
        this.mMediaListText = new TextView[3];
        this.mMenuText = new TextView[3];
        this.m_CdSta = new CustomImgView[6];
        this.mStaData = new CanDataInfo.Accord8_StaInfo();
        this.mRadioData = new CanDataInfo.Accord8_RadioInfo();
        this.mMediaData = new CanDataInfo.Accord8_MediaInfo();
        this.mDiscData = new CanDataInfo.Accord8_DiscInfo();
        this.mCdTextData = new CanDataInfo.Accord8_CdTextInfo();
        this.mListData = new CanDataInfo.Accord8_ListInfo();
        this.mMenuData = new CanDataInfo.Accord8_MenuInfo();
        this.mTimeData = new CanDataInfo.Accord8_TimeInfo();
        this.mIconData = new CanDataInfo.Accord8_IconInfo();
        this.mAmpData = new CanDataInfo.Accord8_AudioInfo();
        if (MainSet.GetScreenType() == 5) {
            InitViews1280x480();
        } else {
            InitViews();
        }
    }

    private void InitViews() {
        this.mManager = getRelativeManager();
        this.mImgFreqBk = this.mManager.AddImage(138, 360, R.drawable.can_yg_radio_bg);
        this.mImgST = this.mManager.AddImage(718, Can.CAN_NISSAN_XFY, R.drawable.can_yg_radio_st);
        this.mTextV1 = AddText(260, 301, 120, 40);
        this.mTextV1.setTextSize(0, 28.0f);
        this.mTextV3 = AddText(718, 301, 170, 40);
        this.mTextV3.setTextSize(0, 28.0f);
        this.mMen = AddText(676, 301, 32, 32);
        this.mMen.setTextSize(0, 28.0f);
        this.mTextV2 = AddText(30, 440, 300, 60);
        this.mTextAmp = AddText(724, 440, 300, 60);
        for (int i = 0; i < this.m_CdSta.length; i++) {
            this.m_CdSta[i] = this.mManager.AddImage((i * 35) + 718, 210, mDsicNumArr[i]);
            this.m_CdSta[i].Show(false);
        }
        for (int i2 = 0; i2 < this.mId3Text.length; i2++) {
            this.mId3Text[i2] = AddText(280, (i2 * 40) + 10, CanCameraUI.BTN_GOLF_WC_MODE1, 30);
            this.mId3Text[i2].setTextSize(0, 22.0f);
        }
        for (int i3 = 0; i3 < this.mMediaListText.length; i3++) {
            this.mMediaListText[i3] = AddText(280, (i3 * 70) + 10, CanCameraUI.BTN_GOLF_WC_MODE1, 70);
        }
        this.mMediaListText[1].setTextSize(0, 50.0f);
        for (int i4 = 0; i4 < this.mMenuText.length; i4++) {
            this.mMenuText[i4] = AddText(280, (i4 * 70) + 10, CanCameraUI.BTN_GOLF_WC_MODE1, 70);
        }
        this.mMenuText[1].setTextSize(0, 50.0f);
        this.mTextTitle = AddText(426, Can.CAN_NISSAN_RICH6_WC, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 105);
        this.mTextTitle.setTextSize(0, 90.0f);
        this.mImgMain = this.mManager.AddImage(386, Can.CAN_FLAT_WC, 263, 97);
        this.mImgMain.setDrawDt(-386, 0);
        this.mImgMain.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                if (CanAccord8XbsExdView.this.mStaData.PowSta <= 0) {
                    CanAccord8XbsExdView.this.ShowPage(7);
                } else if (CanAccord8XbsExdView.this.mStaData.Mode == 0) {
                    if (CanAccord8XbsExdView.this.mRadioData.UpdateOnce == 0 || CanAccord8XbsExdView.this.mRadioData.CurFrq == 0) {
                        return false;
                    }
                    CanAccord8XbsExdView.this.ShowPage(0);
                    if (CanAccord8XbsExdView.this.mRadioData.Band >= 3) {
                        int Bai = (CanAccord8XbsExdView.this.mRadioData.CurFrq / 100) % 10;
                        int Shi = (CanAccord8XbsExdView.this.mRadioData.CurFrq / 10) % 10;
                        int Ge = CanAccord8XbsExdView.this.mRadioData.CurFrq % 10;
                        if (CanAccord8XbsExdView.this.mRadioData.CurFrq >= 1000) {
                            view.drawImage(CanAccord8XbsExdView.mFreqNumArr[1], 444, 0);
                        }
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Bai], 492, 0);
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Shi], CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST1, 0);
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Ge], 588, 0);
                    } else {
                        int Qian = (CanAccord8XbsExdView.this.mRadioData.CurFrq / TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT) % 10;
                        int Bai2 = (CanAccord8XbsExdView.this.mRadioData.CurFrq / MediaDef.PROGRESS_MAX) % 10;
                        int Shi2 = (CanAccord8XbsExdView.this.mRadioData.CurFrq / 100) % 10;
                        int Ge2 = (CanAccord8XbsExdView.this.mRadioData.CurFrq / 10) % 10;
                        if (CanAccord8XbsExdView.this.mRadioData.CurFrq >= 100000) {
                            view.drawImage(CanAccord8XbsExdView.mFreqNumArr[1], 386, 0);
                        }
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Qian], 434, 0);
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Bai2], 482, 0);
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Shi2], 551, 0);
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Ge2], 599, 0);
                        view.drawImage(R.drawable.can_yg_radio_point, 518, 0);
                    }
                    CanAccord8XbsExdView.this.mImgST.Show(CanAccord8XbsExdView.this.mRadioData.St);
                    CanAccord8XbsExdView.this.mTextV3.setText(CanAccord8XbsExdView.mBandDwArr[CanAccord8XbsExdView.this.mRadioData.Band]);
                    if (CanAccord8XbsExdView.this.mRadioData.Men > 0) {
                        CanAccord8XbsExdView.this.mMen.setText(String.format("%d", new Object[]{Integer.valueOf(CanAccord8XbsExdView.this.mRadioData.Men)}));
                    } else {
                        CanAccord8XbsExdView.this.mMen.setText("");
                    }
                    CanAccord8XbsExdView.this.mTextV1.setText(CanAccord8XbsExdView.mBandNameArr[CanAccord8XbsExdView.this.mRadioData.Band]);
                    CanAccord8XbsExdView.this.mTextV2.setText(CanAccord8XbsExdView.mRadioStaArr[CanAccord8XbsExdView.this.mRadioData.Sta]);
                } else if (CanAccord8XbsExdView.this.mStaData.Mode == 1 || CanAccord8XbsExdView.this.mStaData.Mode == 3 || CanAccord8XbsExdView.this.mStaData.Mode == 5) {
                    CanAccord8XbsExdView.this.ShowPage(CanAccord8XbsExdView.this.mStaData.Mode);
                    int MShi = (CanAccord8XbsExdView.this.mMediaData.Play_Min / 10) % 10;
                    int MGe = CanAccord8XbsExdView.this.mMediaData.Play_Min % 10;
                    int SShi = (CanAccord8XbsExdView.this.mMediaData.Play_Sec / 10) % 10;
                    int SGe = CanAccord8XbsExdView.this.mMediaData.Play_Sec % 10;
                    view.drawImage(CanAccord8XbsExdView.mFreqNumArr[MShi], 434, 0);
                    view.drawImage(CanAccord8XbsExdView.mFreqNumArr[MGe], 482, 0);
                    view.drawImage(R.drawable.can_yg_radio_colon, 518, 0);
                    view.drawImage(CanAccord8XbsExdView.mFreqNumArr[SShi], 551, 0);
                    view.drawImage(CanAccord8XbsExdView.mFreqNumArr[SGe], 599, 0);
                    CanAccord8XbsExdView.this.mTextV1.setText(String.format("TR %d", new Object[]{Integer.valueOf(CanAccord8XbsExdView.this.mMediaData.Track)}));
                    CanAccord8XbsExdView.this.mTextV2.setText(CanAccord8XbsExdView.mPlayStaArr[CanAccord8XbsExdView.this.mMediaData.Play_Sta]);
                    CanAccord8XbsExdView.this.mTextV3.setText(CanAccord8XbsExdView.mPlayModeArr[CanAccord8XbsExdView.this.mMediaData.Play_Mode]);
                } else if (CanAccord8XbsExdView.this.mStaData.Mode == 2 || CanAccord8XbsExdView.this.mStaData.Mode == 4 || CanAccord8XbsExdView.this.mStaData.Mode == 6) {
                    CanAccord8XbsExdView.this.ShowPage(CanAccord8XbsExdView.this.mStaData.Mode);
                }
                return false;
            }
        });
    }

    private void InitViews1280x480() {
        this.mManager = getRelativeManager();
        this.mImgFreqBk = this.mManager.AddImage(264, 360, R.drawable.can_yg_radio_bg);
        this.mImgST = this.mManager.AddImage(KeyDef.SKEY_SPEECH_1, Can.CAN_NISSAN_XFY, R.drawable.can_yg_radio_st);
        this.mTextV1 = AddText(386, 301, 120, 40);
        this.mTextV1.setTextSize(0, 28.0f);
        this.mTextV3 = AddText(KeyDef.SKEY_SPEECH_1, 301, Can.CAN_JAC_REFINE_OD, 40);
        this.mTextV3.setTextSize(0, 28.0f);
        this.mMen = AddText(802, 301, 32, 32);
        this.mMen.setTextSize(0, 28.0f);
        this.mTextV2 = AddText(Can.CAN_HONDA_WC, 440, 300, 60);
        this.mTextAmp = AddText(850, 440, 300, 60);
        for (int i = 0; i < this.m_CdSta.length; i++) {
            this.m_CdSta[i] = this.mManager.AddImage((i * 35) + KeyDef.SKEY_SPEECH_1, 210, mDsicNumArr[i]);
            this.m_CdSta[i].Show(false);
        }
        for (int i2 = 0; i2 < this.mId3Text.length; i2++) {
            this.mId3Text[i2] = AddText(406, (i2 * 40) + 10, CanCameraUI.BTN_GOLF_WC_MODE1, 30);
            this.mId3Text[i2].setTextSize(0, 22.0f);
        }
        for (int i3 = 0; i3 < this.mMediaListText.length; i3++) {
            this.mMediaListText[i3] = AddText(406, (i3 * 70) + 10, CanCameraUI.BTN_GOLF_WC_MODE1, 70);
        }
        this.mMediaListText[1].setTextSize(0, 50.0f);
        for (int i4 = 0; i4 < this.mMenuText.length; i4++) {
            this.mMenuText[i4] = AddText(406, (i4 * 70) + 10, CanCameraUI.BTN_GOLF_WC_MODE1, 70);
        }
        this.mMenuText[1].setTextSize(0, 50.0f);
        this.mTextTitle = AddText(552, Can.CAN_NISSAN_RICH6_WC, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 105);
        this.mTextTitle.setTextSize(0, 90.0f);
        this.mImgMain = this.mManager.AddImage(512, Can.CAN_FLAT_WC, 263, 97);
        this.mImgMain.setDrawDt(-386, 0);
        this.mImgMain.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                if (CanAccord8XbsExdView.this.mStaData.PowSta <= 0) {
                    CanAccord8XbsExdView.this.ShowPage(7);
                } else if (CanAccord8XbsExdView.this.mStaData.Mode == 0) {
                    if (CanAccord8XbsExdView.this.mRadioData.UpdateOnce == 0 || CanAccord8XbsExdView.this.mRadioData.CurFrq == 0) {
                        return false;
                    }
                    CanAccord8XbsExdView.this.ShowPage(0);
                    if (CanAccord8XbsExdView.this.mRadioData.Band >= 3) {
                        int Bai = (CanAccord8XbsExdView.this.mRadioData.CurFrq / 100) % 10;
                        int Shi = (CanAccord8XbsExdView.this.mRadioData.CurFrq / 10) % 10;
                        int Ge = CanAccord8XbsExdView.this.mRadioData.CurFrq % 10;
                        if (CanAccord8XbsExdView.this.mRadioData.CurFrq >= 1000) {
                            view.drawImage(CanAccord8XbsExdView.mFreqNumArr[1], 444, 0);
                        }
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Bai], 492, 0);
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Shi], CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST1, 0);
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Ge], 588, 0);
                    } else {
                        int Qian = (CanAccord8XbsExdView.this.mRadioData.CurFrq / TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT) % 10;
                        int Bai2 = (CanAccord8XbsExdView.this.mRadioData.CurFrq / MediaDef.PROGRESS_MAX) % 10;
                        int Shi2 = (CanAccord8XbsExdView.this.mRadioData.CurFrq / 100) % 10;
                        int Ge2 = (CanAccord8XbsExdView.this.mRadioData.CurFrq / 10) % 10;
                        if (CanAccord8XbsExdView.this.mRadioData.CurFrq >= 100000) {
                            view.drawImage(CanAccord8XbsExdView.mFreqNumArr[1], 386, 0);
                        }
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Qian], 434, 0);
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Bai2], 482, 0);
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Shi2], 551, 0);
                        view.drawImage(CanAccord8XbsExdView.mFreqNumArr[Ge2], 599, 0);
                        view.drawImage(R.drawable.can_yg_radio_point, 518, 0);
                    }
                    CanAccord8XbsExdView.this.mImgST.Show(CanAccord8XbsExdView.this.mRadioData.St);
                    CanAccord8XbsExdView.this.mTextV3.setText(CanAccord8XbsExdView.mBandDwArr[CanAccord8XbsExdView.this.mRadioData.Band]);
                    if (CanAccord8XbsExdView.this.mRadioData.Men > 0) {
                        CanAccord8XbsExdView.this.mMen.setText(String.format("%d", new Object[]{Integer.valueOf(CanAccord8XbsExdView.this.mRadioData.Men)}));
                    } else {
                        CanAccord8XbsExdView.this.mMen.setText("");
                    }
                    CanAccord8XbsExdView.this.mTextV1.setText(CanAccord8XbsExdView.mBandNameArr[CanAccord8XbsExdView.this.mRadioData.Band]);
                    CanAccord8XbsExdView.this.mTextV2.setText(CanAccord8XbsExdView.mRadioStaArr[CanAccord8XbsExdView.this.mRadioData.Sta]);
                } else if (CanAccord8XbsExdView.this.mStaData.Mode == 1 || CanAccord8XbsExdView.this.mStaData.Mode == 3 || CanAccord8XbsExdView.this.mStaData.Mode == 5) {
                    CanAccord8XbsExdView.this.ShowPage(CanAccord8XbsExdView.this.mStaData.Mode);
                    int MShi = (CanAccord8XbsExdView.this.mMediaData.Play_Min / 10) % 10;
                    int MGe = CanAccord8XbsExdView.this.mMediaData.Play_Min % 10;
                    int SShi = (CanAccord8XbsExdView.this.mMediaData.Play_Sec / 10) % 10;
                    int SGe = CanAccord8XbsExdView.this.mMediaData.Play_Sec % 10;
                    view.drawImage(CanAccord8XbsExdView.mFreqNumArr[MShi], 434, 0);
                    view.drawImage(CanAccord8XbsExdView.mFreqNumArr[MGe], 482, 0);
                    view.drawImage(R.drawable.can_yg_radio_colon, 518, 0);
                    view.drawImage(CanAccord8XbsExdView.mFreqNumArr[SShi], 551, 0);
                    view.drawImage(CanAccord8XbsExdView.mFreqNumArr[SGe], 599, 0);
                    CanAccord8XbsExdView.this.mTextV1.setText(String.format("TR %d", new Object[]{Integer.valueOf(CanAccord8XbsExdView.this.mMediaData.Track)}));
                    CanAccord8XbsExdView.this.mTextV2.setText(CanAccord8XbsExdView.mPlayStaArr[CanAccord8XbsExdView.this.mMediaData.Play_Sta]);
                    CanAccord8XbsExdView.this.mTextV3.setText(CanAccord8XbsExdView.mPlayModeArr[CanAccord8XbsExdView.this.mMediaData.Play_Mode]);
                } else if (CanAccord8XbsExdView.this.mStaData.Mode == 2 || CanAccord8XbsExdView.this.mStaData.Mode == 4 || CanAccord8XbsExdView.this.mStaData.Mode == 6) {
                    CanAccord8XbsExdView.this.ShowPage(CanAccord8XbsExdView.this.mStaData.Mode);
                }
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void ShowPage(int page) {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        this.mTextAmp.setVisibility(7 == page ? 4 : 0);
        CustomImgView customImgView = this.mImgST;
        if (page == 0) {
            z = true;
        } else {
            z = false;
        }
        customImgView.Show(z);
        TextView textView = this.mMen;
        if (page == 0) {
            i = 0;
        } else {
            i = 4;
        }
        textView.setVisibility(i);
        TextView textView2 = this.mMen;
        if (page == 0) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        textView2.setVisibility(i2);
        TextView textView3 = this.mTextV1;
        if (page == 0 || 1 == page || 3 == page || 5 == page) {
            i3 = 0;
        } else {
            i3 = 4;
        }
        textView3.setVisibility(i3);
        TextView textView4 = this.mTextV2;
        if (page == 0 || 1 == page || 3 == page || 5 == page) {
            i4 = 0;
        } else {
            i4 = 4;
        }
        textView4.setVisibility(i4);
        TextView textView5 = this.mTextV3;
        if (page == 0 || 1 == page || 3 == page || 5 == page) {
            i5 = 0;
        } else {
            i5 = 4;
        }
        textView5.setVisibility(i5);
        TextView textView6 = this.mTextTitle;
        if (page == 0 || 1 == page || 3 == page || 5 == page) {
            i6 = 4;
        } else {
            i6 = 0;
        }
        textView6.setVisibility(i6);
        if (page != 1) {
            for (CustomImgView Show : this.m_CdSta) {
                Show.Show(false);
            }
            for (TextView visibility : this.mId3Text) {
                visibility.setVisibility(4);
            }
        }
        if (!(page == 3 || page == 1)) {
            for (TextView visibility2 : this.mMediaListText) {
                visibility2.setVisibility(4);
            }
        }
        switch (page) {
            case 2:
                this.mTextTitle.setText("AUX");
                return;
            case 4:
                this.mTextTitle.setText("TEL");
                return;
            case 6:
                this.mTextTitle.setText("DVD");
                return;
            case 7:
                this.mTextTitle.setText("OFF");
                return;
            default:
                return;
        }
    }

    private TextView AddText(int x, int y, int w, int h) {
        new TextView(getActivity());
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextSize(0, 40.0f);
        text.setTextColor(-1);
        text.setGravity(19);
        text.setVisibility(4);
        return text;
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        mfgShow = true;
        mfgFinish = false;
    }

    public void doOnPause() {
        super.doOnPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        mfgShow = false;
    }

    public static void showOdysseyWin() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanCarDeviceActivity.class);
        }
    }

    public static void finishOdysseyWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public void ResetData(boolean check) {
        CanJni.Accord8GetStaInfo(this.mStaData);
        if (i2b(this.mStaData.UpdateOnce) && (!check || i2b(this.mStaData.Update))) {
            this.mStaData.Update = 0;
            if (this.mStaData.MenuVail > 0) {
                for (TextView visibility : this.mMenuText) {
                    visibility.setVisibility(0);
                }
                for (TextView visibility2 : this.mId3Text) {
                    visibility2.setVisibility(4);
                }
                for (TextView visibility3 : this.mMediaListText) {
                    visibility3.setVisibility(4);
                }
            } else {
                for (TextView visibility4 : this.mMenuText) {
                    visibility4.setVisibility(4);
                }
                if (this.mStaData.Mode == 1 || this.mStaData.Mode == 3) {
                    if (this.mMediaData.Text_List == 1) {
                        if (this.mStaData.Mode == 1 && this.mId3Text[0].getVisibility() == 4) {
                            for (TextView visibility5 : this.mId3Text) {
                                visibility5.setVisibility(0);
                            }
                        }
                    } else if (this.mMediaData.Text_List == 2 && this.mMediaListText[0].getVisibility() == 4) {
                        for (TextView visibility6 : this.mMediaListText) {
                            visibility6.setVisibility(0);
                        }
                    }
                }
            }
            this.mImgMain.invalidate();
        }
        CanJni.Accord8GetRadioInfo(this.mRadioData);
        if (i2b(this.mRadioData.UpdateOnce) && (!check || i2b(this.mRadioData.Update))) {
            this.mRadioData.Update = 0;
            this.mImgMain.invalidate();
        }
        CanJni.Accord8GetMediaInfo(this.mMediaData);
        if (i2b(this.mMediaData.UpdateOnce) && (!check || i2b(this.mMediaData.Update))) {
            this.mMediaData.Update = 0;
            if (this.mMediaData.Text_List == 0 || (!(this.mStaData.Mode == 1 || this.mStaData.Mode == 3) || this.mStaData.MenuVail > 0)) {
                for (TextView visibility7 : this.mId3Text) {
                    visibility7.setVisibility(4);
                }
                for (TextView visibility8 : this.mMediaListText) {
                    visibility8.setVisibility(4);
                }
            } else if (this.mMediaData.Text_List == 1) {
                if (this.mStaData.Mode == 1) {
                    for (TextView visibility9 : this.mId3Text) {
                        visibility9.setVisibility(0);
                    }
                }
                for (TextView visibility10 : this.mMediaListText) {
                    visibility10.setVisibility(4);
                }
            } else if (this.mMediaData.Text_List == 2) {
                for (TextView visibility11 : this.mId3Text) {
                    visibility11.setVisibility(4);
                }
                for (TextView visibility12 : this.mMediaListText) {
                    visibility12.setVisibility(0);
                }
            }
            this.mImgMain.invalidate();
        }
        CanJni.Accord8GetDiscInfo(this.mDiscData);
        if (i2b(this.mDiscData.UpdateOnce) && (!check || i2b(this.mDiscData.Update))) {
            this.mDiscData.Update = 0;
            this.mImgMain.invalidate();
        }
        CanJni.Accord8GetCdTextInfo(this.mCdTextData);
        if (i2b(this.mCdTextData.UpdateOnce) && (!check || i2b(this.mCdTextData.Update))) {
            this.mCdTextData.Update = 0;
            if (i2b(this.mCdTextData.AlbumUpdateOnce) && (!check || i2b(this.mCdTextData.AlbumUpdate))) {
                this.mCdTextData.AlbumUpdate = 0;
                this.mId3Text[0].setText("Album:  " + byte2ASCIIString(this.mCdTextData.AlbumText, 30));
            }
            if (i2b(this.mCdTextData.TrackUpdateOnce) && (!check || i2b(this.mCdTextData.TrackUpdate))) {
                this.mCdTextData.TrackUpdate = 0;
                this.mId3Text[1].setText("Track:  " + byte2ASCIIString(this.mCdTextData.TrackText, 30));
            }
            if (i2b(this.mCdTextData.ArtistUpdateOnce) && (!check || i2b(this.mCdTextData.ArtistUpdate))) {
                this.mCdTextData.ArtistUpdate = 0;
                this.mId3Text[2].setText("Artist:  " + byte2ASCIIString(this.mCdTextData.ArtistText, 30));
            }
            if (i2b(this.mCdTextData.FolderUpdateOnce) && (!check || i2b(this.mCdTextData.FolderUpdate))) {
                this.mCdTextData.FolderUpdate = 0;
                this.mId3Text[3].setText("Folder:  " + byte2ASCIIString(this.mCdTextData.FolderText, 30));
            }
            if (i2b(this.mCdTextData.FileUpdateOnce) && (!check || i2b(this.mCdTextData.FileUpdate))) {
                this.mCdTextData.FileUpdate = 0;
                this.mId3Text[4].setText("File:  " + byte2ASCIIString(this.mCdTextData.FileText, 30));
            }
        }
        CanJni.Accord8GetListInfo(this.mListData);
        if (i2b(this.mListData.UpdateOnce) && (!check || i2b(this.mListData.Update))) {
            this.mListData.Update = 0;
            this.mMediaListText[0].setText(byte2ASCIIString(this.mListData.List1Text, 16));
            this.mMediaListText[1].setText(byte2ASCIIString(this.mListData.List2Text, 16));
            this.mMediaListText[2].setText(byte2ASCIIString(this.mListData.List3Text, 16));
        }
        CanJni.Accord8GetMenuInfo(this.mMenuData);
        if (i2b(this.mMenuData.UpdateOnce) && (!check || i2b(this.mMenuData.Update))) {
            this.mMenuData.Update = 0;
            this.mMenuText[0].setText(byte2ASCIIString(this.mMenuData.List1Text, 16));
            this.mMenuText[1].setText(byte2ASCIIString(this.mMenuData.List2Text, 16));
            this.mMenuText[2].setText(byte2ASCIIString(this.mMenuData.List3Text, 16));
        }
        CanJni.HondaAccord8XbsGetAudioInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            switch (this.mAmpData.Sta) {
                case 0:
                    this.mTextAmp.setText(mAmpStaArr[this.mAmpData.Sta]);
                    return;
                case 1:
                    this.mTextAmp.setText(String.valueOf(mAmpStaArr[this.mAmpData.Sta]) + String.format("%d", new Object[]{Integer.valueOf(10 - this.mAmpData.Val)}));
                    return;
                case 2:
                    this.mTextAmp.setText(String.valueOf(mAmpStaArr[this.mAmpData.Sta]) + String.format("%d", new Object[]{Integer.valueOf(10 - this.mAmpData.Val)}));
                    return;
                case 3:
                    this.mTextAmp.setText(String.valueOf(mAmpStaArr[this.mAmpData.Sta]) + setValText(this.mAmpData.Val, 1));
                    return;
                case 4:
                    this.mTextAmp.setText(String.valueOf(mAmpStaArr[this.mAmpData.Sta]) + setValText(this.mAmpData.Val, 2));
                    return;
                case 5:
                    this.mTextAmp.setText(String.valueOf(mAmpStaArr[this.mAmpData.Sta]) + String.format("%d", new Object[]{Integer.valueOf(10 - this.mAmpData.Val)}));
                    return;
                case 6:
                    if (this.mAmpData.Val < mSvcValueArr.length) {
                        this.mTextAmp.setText(String.valueOf(mAmpStaArr[this.mAmpData.Sta]) + mSvcValueArr[this.mAmpData.Val]);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
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

    private void UpdateDiscFlag() {
        if (this.mStaData.Mode == 1) {
            long curTick = SystemClock.uptimeMillis();
            if (curTick > mLastTick + 666) {
                mLastTick = curTick;
                if (mLastDiscSta != 0) {
                    mLastDiscSta = 0;
                } else {
                    mLastDiscSta = 1;
                }
                for (int i = 0; i < 6; i++) {
                    this.m_CdSta[i].Show(false);
                }
                if (this.mMediaData.CurMen != 255) {
                    if (this.mDiscData.Disc1 == 2 || this.mDiscData.Disc1 == 3 || this.mDiscData.Disc1 == 5) {
                        this.m_CdSta[0].Show(true);
                    } else if (this.mDiscData.Disc1 == 4) {
                        if (mLastDiscSta != 0) {
                            this.m_CdSta[0].Show(true);
                        } else {
                            this.m_CdSta[0].Show(false);
                        }
                    }
                    if (this.mDiscData.Disc2 == 2 || this.mDiscData.Disc2 == 3 || this.mDiscData.Disc2 == 5) {
                        this.m_CdSta[1].Show(true);
                    } else if (this.mDiscData.Disc2 == 4) {
                        if (mLastDiscSta != 0) {
                            this.m_CdSta[1].Show(true);
                        } else {
                            this.m_CdSta[1].Show(false);
                        }
                    }
                    if (this.mDiscData.Disc3 == 2 || this.mDiscData.Disc3 == 3 || this.mDiscData.Disc3 == 5) {
                        this.m_CdSta[2].Show(true);
                    } else if (this.mDiscData.Disc3 == 4) {
                        if (mLastDiscSta != 0) {
                            this.m_CdSta[2].Show(true);
                        } else {
                            this.m_CdSta[2].Show(false);
                        }
                    }
                    if (this.mDiscData.Disc4 == 2 || this.mDiscData.Disc4 == 3 || this.mDiscData.Disc4 == 5) {
                        this.m_CdSta[3].Show(true);
                    } else if (this.mDiscData.Disc4 == 4) {
                        if (mLastDiscSta != 0) {
                            this.m_CdSta[3].Show(true);
                        } else {
                            this.m_CdSta[3].Show(false);
                        }
                    }
                    if (this.mDiscData.Disc5 == 2 || this.mDiscData.Disc5 == 3 || this.mDiscData.Disc5 == 5) {
                        this.m_CdSta[4].Show(true);
                    } else if (this.mDiscData.Disc5 == 4) {
                        if (mLastDiscSta != 0) {
                            this.m_CdSta[4].Show(true);
                        } else {
                            this.m_CdSta[4].Show(false);
                        }
                    }
                    if (this.mDiscData.Disc6 == 2 || this.mDiscData.Disc6 == 3 || this.mDiscData.Disc6 == 5) {
                        this.m_CdSta[5].Show(true);
                    } else if (this.mDiscData.Disc6 != 4) {
                    } else {
                        if (mLastDiscSta != 0) {
                            this.m_CdSta[5].Show(true);
                        } else {
                            this.m_CdSta[5].Show(false);
                        }
                    }
                }
            }
        }
    }

    public void UserAll() {
        ResetData(true);
        UpdateDiscFlag();
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            getActivity().finish();
        }
    }

    public void QueryData() {
    }

    private String setValText(int val, int sta) {
        switch (sta) {
            case 1:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "F" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "R" + String.valueOf(val - 10);
                }
                break;
            case 2:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "L" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "R" + String.valueOf(val - 10);
                }
                break;
        }
        return "0";
    }

    public static void DealDevEvent() {
        int temp = 0;
        BtExe bt = BtExe.getBtInstance();
        if (mOldBtSta != bt.getSta()) {
            mOldBtSta = bt.getSta();
            if (CanIF.DealGpsIng()) {
                temp = 1;
            }
            if (mOldBtSta == 3 || mOldBtSta == 2 || mOldBtSta == 4) {
                Log.d("lq", "Bt call on ");
                Iop.RstPort(1);
                CanJni.HondaAccord8XbsHostSta(temp | 4);
                return;
            }
            Log.d("lq", "Bt call of ");
            Iop.RstPort(0);
            CanJni.HondaAccord8XbsHostSta(temp & Can.CAN_MG_ZS_WC);
        }
    }
}
