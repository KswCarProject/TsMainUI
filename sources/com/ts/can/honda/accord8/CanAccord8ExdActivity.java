package com.ts.can.honda.accord8;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZPoiSearchManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;
import java.io.UnsupportedEncodingException;

public class CanAccord8ExdActivity extends CanBaseActivity implements UserCallBack {
    public static final int PAGE_AUX = 2;
    public static final int PAGE_DISC = 1;
    public static final int PAGE_POW_OF = 3;
    public static final int PAGE_RADIO = 0;
    public static final String TAG = "CanAccord8ExdActivity";
    public static final String[] mBandDwArr = {"MHz ", "MHz ", "MHz ", "KHz ", "KHz ", "KHz "};
    public static final String[] mBandNameArr = {"FM", "FM1 ", "FM2 ", "AM ", "LW ", "MW "};
    public static final int[] mDsicNumArr = {R.drawable.can_klz_num01, R.drawable.can_klz_num02, R.drawable.can_klz_num03, R.drawable.can_klz_num04, R.drawable.can_klz_num05, R.drawable.can_klz_num06};
    public static final int[] mFreqNumArr = {R.drawable.can_yg_radio_num0, R.drawable.can_yg_radio_num1, R.drawable.can_yg_radio_num2, R.drawable.can_yg_radio_num3, R.drawable.can_yg_radio_num4, R.drawable.can_yg_radio_num5, R.drawable.can_yg_radio_num6, R.drawable.can_yg_radio_num7, R.drawable.can_yg_radio_num8, R.drawable.can_yg_radio_num9};
    private static int mLastDiscSta = 0;
    private static long mLastTick = 0;
    public static final String[] mPlayModeArr = {"Normal play ", "Repeat one track ", "Repeat all ", "Random ", "Scan ", "D-Scan ", "Repeat one fld ", "random in fld ", "scan fld"};
    public static final String[] mPlayStaArr = {"Playing ", "No disc ", "Busy ", "Load ", "Read ", "Eject ", "Fast forward ", "Fast backward ", "Disc error ", "No data ", "No song ", "Usb load ", "Unsupported"};
    public static final String[] mRadioStaArr = {" ", " ", "TUNE+", "TUNE- ", "SEEK+ ", "SEEK- ", "SCAN ", "A.SEL "};
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private CanDataInfo.Accord8_CdTextInfo mCdTextData = new CanDataInfo.Accord8_CdTextInfo();
    private CanDataInfo.Accord8_DiscInfo mDiscData = new CanDataInfo.Accord8_DiscInfo();
    private CanDataInfo.Accord8_IconInfo mIconData = new CanDataInfo.Accord8_IconInfo();
    private TextView[] mId3Text = new TextView[5];
    private CustomImgView mImgFreqBk;
    private CustomImgView mImgMain;
    /* access modifiers changed from: private */
    public CustomImgView mImgST;
    private CanDataInfo.Accord8_ListInfo mListData = new CanDataInfo.Accord8_ListInfo();
    protected RelativeLayoutManager mManager;
    /* access modifiers changed from: private */
    public CanDataInfo.Accord8_MediaInfo mMediaData = new CanDataInfo.Accord8_MediaInfo();
    private TextView[] mMediaListText = new TextView[3];
    /* access modifiers changed from: private */
    public TextView mMen;
    private CanDataInfo.Accord8_MenuInfo mMenuData = new CanDataInfo.Accord8_MenuInfo();
    private TextView[] mMenuText = new TextView[3];
    /* access modifiers changed from: private */
    public CanDataInfo.Accord8_RadioInfo mRadioData = new CanDataInfo.Accord8_RadioInfo();
    /* access modifiers changed from: private */
    public CanDataInfo.Accord8_StaInfo mStaData = new CanDataInfo.Accord8_StaInfo();
    private TextView mTextTitle;
    /* access modifiers changed from: private */
    public TextView mTextV1;
    /* access modifiers changed from: private */
    public TextView mTextV2;
    /* access modifiers changed from: private */
    public TextView mTextV3;
    private CanDataInfo.Accord8_TimeInfo mTimeData = new CanDataInfo.Accord8_TimeInfo();
    private CustomImgView[] m_CdSta = new CustomImgView[6];

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        if (MainSet.GetScreenType() == 5) {
            InitViews1280x480();
        } else {
            InitViews();
        }
    }

    private void InitViews() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mImgFreqBk = this.mManager.AddImage(138, 360, R.drawable.can_yg_radio_bg);
        this.mImgST = this.mManager.AddImage(718, Can.CAN_NISSAN_XFY, R.drawable.can_yg_radio_st);
        this.mTextV1 = AddText(260, 301, 120, 40);
        this.mTextV1.setTextSize(0, 28.0f);
        this.mTextV3 = AddText(718, 301, 150, 40);
        this.mTextV3.setTextSize(0, 28.0f);
        this.mMen = AddText(676, 301, 32, 32);
        this.mMen.setTextSize(0, 28.0f);
        this.mTextV2 = AddText(30, 440, 300, 60);
        for (int i = 0; i < this.m_CdSta.length; i++) {
            this.m_CdSta[i] = this.mManager.AddImage((i * 35) + 718, 210, mDsicNumArr[i]);
            this.m_CdSta[i].Show(false);
        }
        for (int i2 = 0; i2 < this.mId3Text.length; i2++) {
            this.mId3Text[i2] = AddText(280, (i2 * 40) + 10, 600, 30);
            this.mId3Text[i2].setTextSize(0, 22.0f);
        }
        for (int i3 = 0; i3 < this.mMediaListText.length; i3++) {
            this.mMediaListText[i3] = AddText(280, (i3 * 70) + 10, 600, 70);
        }
        this.mMediaListText[1].setTextSize(0, 50.0f);
        for (int i4 = 0; i4 < this.mMenuText.length; i4++) {
            this.mMenuText[i4] = AddText(280, (i4 * 70) + 10, 600, 70);
        }
        this.mMenuText[1].setTextSize(0, 50.0f);
        this.mTextTitle = AddText(426, Can.CAN_NISSAN_RICH6_WC, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 105);
        this.mTextTitle.setTextSize(0, 90.0f);
        this.mImgMain = this.mManager.AddImage(386, Can.CAN_FLAT_WC, 263, 97);
        this.mImgMain.setDrawDt(-386, 0);
        this.mImgMain.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                if (CanAccord8ExdActivity.this.mStaData.PowSta <= 0) {
                    CanAccord8ExdActivity.this.ShowPage(3);
                } else if (CanAccord8ExdActivity.this.mStaData.Mode == 0) {
                    if (CanAccord8ExdActivity.this.mRadioData.UpdateOnce == 0 || CanAccord8ExdActivity.this.mRadioData.CurFrq == 0) {
                        return false;
                    }
                    CanAccord8ExdActivity.this.ShowPage(0);
                    Log.d(CanAccord8ExdActivity.TAG, "PAGE_RADIO");
                    if (CanAccord8ExdActivity.this.mRadioData.Band >= 3) {
                        int Bai = (CanAccord8ExdActivity.this.mRadioData.CurFrq / 100) % 10;
                        int Shi = (CanAccord8ExdActivity.this.mRadioData.CurFrq / 10) % 10;
                        int Ge = CanAccord8ExdActivity.this.mRadioData.CurFrq % 10;
                        if (CanAccord8ExdActivity.this.mRadioData.CurFrq >= 1000) {
                            view.drawImage(CanAccord8ExdActivity.mFreqNumArr[1], 444, 0);
                        }
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Bai], 492, 0);
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Shi], 540, 0);
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Ge], 588, 0);
                    } else {
                        int Qian = (CanAccord8ExdActivity.this.mRadioData.CurFrq / TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT) % 10;
                        int Bai2 = (CanAccord8ExdActivity.this.mRadioData.CurFrq / 1000) % 10;
                        int Shi2 = (CanAccord8ExdActivity.this.mRadioData.CurFrq / 100) % 10;
                        int Ge2 = (CanAccord8ExdActivity.this.mRadioData.CurFrq / 10) % 10;
                        if (CanAccord8ExdActivity.this.mRadioData.CurFrq >= 100000) {
                            view.drawImage(CanAccord8ExdActivity.mFreqNumArr[1], 386, 0);
                        }
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Qian], 434, 0);
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Bai2], 482, 0);
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Shi2], 551, 0);
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Ge2], 599, 0);
                        view.drawImage(R.drawable.can_yg_radio_point, 518, 0);
                    }
                    CanAccord8ExdActivity.this.mImgST.Show(CanAccord8ExdActivity.this.mRadioData.St);
                    CanAccord8ExdActivity.this.mTextV3.setText(CanAccord8ExdActivity.mBandDwArr[CanAccord8ExdActivity.this.mRadioData.Band]);
                    if (CanAccord8ExdActivity.this.mRadioData.Men > 0) {
                        CanAccord8ExdActivity.this.mMen.setText(String.format("%d", new Object[]{Integer.valueOf(CanAccord8ExdActivity.this.mRadioData.Men)}));
                    } else {
                        CanAccord8ExdActivity.this.mMen.setText(TXZResourceManager.STYLE_DEFAULT);
                    }
                    CanAccord8ExdActivity.this.mTextV1.setText(CanAccord8ExdActivity.mBandNameArr[CanAccord8ExdActivity.this.mRadioData.Band]);
                    CanAccord8ExdActivity.this.mTextV2.setText(CanAccord8ExdActivity.mRadioStaArr[CanAccord8ExdActivity.this.mRadioData.Sta]);
                } else if (CanAccord8ExdActivity.this.mStaData.Mode == 1) {
                    CanAccord8ExdActivity.this.ShowPage(1);
                    Log.d(CanAccord8ExdActivity.TAG, "PAGE_DISC");
                    int MShi = (CanAccord8ExdActivity.this.mMediaData.Play_Min / 10) % 10;
                    int MGe = CanAccord8ExdActivity.this.mMediaData.Play_Min % 10;
                    int SShi = (CanAccord8ExdActivity.this.mMediaData.Play_Sec / 10) % 10;
                    int SGe = CanAccord8ExdActivity.this.mMediaData.Play_Sec % 10;
                    view.drawImage(CanAccord8ExdActivity.mFreqNumArr[MShi], 434, 0);
                    view.drawImage(CanAccord8ExdActivity.mFreqNumArr[MGe], 482, 0);
                    view.drawImage(R.drawable.can_yg_radio_colon, 518, 0);
                    view.drawImage(CanAccord8ExdActivity.mFreqNumArr[SShi], 551, 0);
                    view.drawImage(CanAccord8ExdActivity.mFreqNumArr[SGe], 599, 0);
                    Log.d(CanAccord8ExdActivity.TAG, "mMediaData.Track =" + CanAccord8ExdActivity.this.mMediaData.Track);
                    CanAccord8ExdActivity.this.mTextV1.setText(String.format("TR %d", new Object[]{Integer.valueOf(CanAccord8ExdActivity.this.mMediaData.Track)}));
                    CanAccord8ExdActivity.this.mTextV2.setText(CanAccord8ExdActivity.mPlayStaArr[CanAccord8ExdActivity.this.mMediaData.Play_Sta]);
                    CanAccord8ExdActivity.this.mTextV3.setText(CanAccord8ExdActivity.mPlayModeArr[CanAccord8ExdActivity.this.mMediaData.Play_Mode]);
                } else if (CanAccord8ExdActivity.this.mStaData.Mode == 2) {
                    CanAccord8ExdActivity.this.ShowPage(2);
                    Log.d(CanAccord8ExdActivity.TAG, "PAGE_AUX");
                }
                return false;
            }
        });
    }

    private void InitViews1280x480() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mImgFreqBk = this.mManager.AddImage(264, 360, R.drawable.can_yg_radio_bg);
        this.mImgST = this.mManager.AddImage(KeyDef.SKEY_SPEECH_1, Can.CAN_NISSAN_XFY, R.drawable.can_yg_radio_st);
        this.mTextV1 = AddText(386, 301, 120, 40);
        this.mTextV1.setTextSize(0, 28.0f);
        this.mTextV3 = AddText(KeyDef.SKEY_SPEECH_1, 301, 150, 40);
        this.mTextV3.setTextSize(0, 28.0f);
        this.mMen = AddText(802, 301, 32, 32);
        this.mMen.setTextSize(0, 28.0f);
        this.mTextV2 = AddText(Can.CAN_HONDA_WC, 440, 300, 60);
        for (int i = 0; i < this.m_CdSta.length; i++) {
            this.m_CdSta[i] = this.mManager.AddImage((i * 35) + KeyDef.SKEY_SPEECH_1, 210, mDsicNumArr[i]);
            this.m_CdSta[i].Show(false);
        }
        for (int i2 = 0; i2 < this.mId3Text.length; i2++) {
            this.mId3Text[i2] = AddText(406, (i2 * 40) + 10, 600, 30);
            this.mId3Text[i2].setTextSize(0, 22.0f);
        }
        for (int i3 = 0; i3 < this.mMediaListText.length; i3++) {
            this.mMediaListText[i3] = AddText(406, (i3 * 70) + 10, 600, 70);
        }
        this.mMediaListText[1].setTextSize(0, 50.0f);
        for (int i4 = 0; i4 < this.mMenuText.length; i4++) {
            this.mMenuText[i4] = AddText(406, (i4 * 70) + 10, 600, 70);
        }
        this.mMenuText[1].setTextSize(0, 50.0f);
        this.mTextTitle = AddText(552, Can.CAN_NISSAN_RICH6_WC, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 105);
        this.mTextTitle.setTextSize(0, 90.0f);
        this.mImgMain = this.mManager.AddImage(512, Can.CAN_FLAT_WC, 263, 97);
        this.mImgMain.setDrawDt(-386, 0);
        this.mImgMain.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                if (CanAccord8ExdActivity.this.mStaData.PowSta <= 0) {
                    CanAccord8ExdActivity.this.ShowPage(3);
                } else if (CanAccord8ExdActivity.this.mStaData.Mode == 0) {
                    if (CanAccord8ExdActivity.this.mRadioData.UpdateOnce == 0 || CanAccord8ExdActivity.this.mRadioData.CurFrq == 0) {
                        return false;
                    }
                    CanAccord8ExdActivity.this.ShowPage(0);
                    Log.d(CanAccord8ExdActivity.TAG, "PAGE_RADIO");
                    if (CanAccord8ExdActivity.this.mRadioData.Band >= 3) {
                        int Bai = (CanAccord8ExdActivity.this.mRadioData.CurFrq / 100) % 10;
                        int Shi = (CanAccord8ExdActivity.this.mRadioData.CurFrq / 10) % 10;
                        int Ge = CanAccord8ExdActivity.this.mRadioData.CurFrq % 10;
                        if (CanAccord8ExdActivity.this.mRadioData.CurFrq >= 1000) {
                            view.drawImage(CanAccord8ExdActivity.mFreqNumArr[1], 444, 0);
                        }
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Bai], 492, 0);
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Shi], 540, 0);
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Ge], 588, 0);
                    } else {
                        int Qian = (CanAccord8ExdActivity.this.mRadioData.CurFrq / TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT) % 10;
                        int Bai2 = (CanAccord8ExdActivity.this.mRadioData.CurFrq / 1000) % 10;
                        int Shi2 = (CanAccord8ExdActivity.this.mRadioData.CurFrq / 100) % 10;
                        int Ge2 = (CanAccord8ExdActivity.this.mRadioData.CurFrq / 10) % 10;
                        if (CanAccord8ExdActivity.this.mRadioData.CurFrq >= 100000) {
                            view.drawImage(CanAccord8ExdActivity.mFreqNumArr[1], 386, 0);
                        }
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Qian], 434, 0);
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Bai2], 482, 0);
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Shi2], 551, 0);
                        view.drawImage(CanAccord8ExdActivity.mFreqNumArr[Ge2], 599, 0);
                        view.drawImage(R.drawable.can_yg_radio_point, 518, 0);
                    }
                    CanAccord8ExdActivity.this.mImgST.Show(CanAccord8ExdActivity.this.mRadioData.St);
                    CanAccord8ExdActivity.this.mTextV3.setText(CanAccord8ExdActivity.mBandDwArr[CanAccord8ExdActivity.this.mRadioData.Band]);
                    if (CanAccord8ExdActivity.this.mRadioData.Men > 0) {
                        CanAccord8ExdActivity.this.mMen.setText(String.format("%d", new Object[]{Integer.valueOf(CanAccord8ExdActivity.this.mRadioData.Men)}));
                    } else {
                        CanAccord8ExdActivity.this.mMen.setText(TXZResourceManager.STYLE_DEFAULT);
                    }
                    CanAccord8ExdActivity.this.mTextV1.setText(CanAccord8ExdActivity.mBandNameArr[CanAccord8ExdActivity.this.mRadioData.Band]);
                    CanAccord8ExdActivity.this.mTextV2.setText(CanAccord8ExdActivity.mRadioStaArr[CanAccord8ExdActivity.this.mRadioData.Sta]);
                } else if (CanAccord8ExdActivity.this.mStaData.Mode == 1) {
                    CanAccord8ExdActivity.this.ShowPage(1);
                    Log.d(CanAccord8ExdActivity.TAG, "PAGE_DISC");
                    int MShi = (CanAccord8ExdActivity.this.mMediaData.Play_Min / 10) % 10;
                    int MGe = CanAccord8ExdActivity.this.mMediaData.Play_Min % 10;
                    int SShi = (CanAccord8ExdActivity.this.mMediaData.Play_Sec / 10) % 10;
                    int SGe = CanAccord8ExdActivity.this.mMediaData.Play_Sec % 10;
                    view.drawImage(CanAccord8ExdActivity.mFreqNumArr[MShi], 434, 0);
                    view.drawImage(CanAccord8ExdActivity.mFreqNumArr[MGe], 482, 0);
                    view.drawImage(R.drawable.can_yg_radio_colon, 518, 0);
                    view.drawImage(CanAccord8ExdActivity.mFreqNumArr[SShi], 551, 0);
                    view.drawImage(CanAccord8ExdActivity.mFreqNumArr[SGe], 599, 0);
                    Log.d(CanAccord8ExdActivity.TAG, "mMediaData.Track =" + CanAccord8ExdActivity.this.mMediaData.Track);
                    CanAccord8ExdActivity.this.mTextV1.setText(String.format("TR %d", new Object[]{Integer.valueOf(CanAccord8ExdActivity.this.mMediaData.Track)}));
                    CanAccord8ExdActivity.this.mTextV2.setText(CanAccord8ExdActivity.mPlayStaArr[CanAccord8ExdActivity.this.mMediaData.Play_Sta]);
                    CanAccord8ExdActivity.this.mTextV3.setText(CanAccord8ExdActivity.mPlayModeArr[CanAccord8ExdActivity.this.mMediaData.Play_Mode]);
                } else if (CanAccord8ExdActivity.this.mStaData.Mode == 2) {
                    CanAccord8ExdActivity.this.ShowPage(2);
                    Log.d(CanAccord8ExdActivity.TAG, "PAGE_AUX");
                }
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void ShowPage(int page) {
        switch (page) {
            case 0:
                for (CustomImgView Show : this.m_CdSta) {
                    Show.Show(false);
                }
                for (TextView visibility : this.mId3Text) {
                    visibility.setVisibility(4);
                }
                for (TextView visibility2 : this.mMediaListText) {
                    visibility2.setVisibility(4);
                }
                this.mTextTitle.setVisibility(4);
                this.mImgST.Show(true);
                this.mMen.setVisibility(0);
                this.mTextV1.setVisibility(0);
                this.mTextV2.setVisibility(0);
                this.mTextV3.setVisibility(0);
                return;
            case 1:
                this.mImgST.Show(false);
                this.mMen.setVisibility(4);
                this.mTextTitle.setVisibility(4);
                this.mTextV1.setVisibility(0);
                this.mTextV2.setVisibility(0);
                this.mTextV3.setVisibility(0);
                return;
            case 2:
                for (CustomImgView Show2 : this.m_CdSta) {
                    Show2.Show(false);
                }
                for (TextView visibility3 : this.mId3Text) {
                    visibility3.setVisibility(4);
                }
                for (TextView visibility4 : this.mMediaListText) {
                    visibility4.setVisibility(4);
                }
                this.mImgST.Show(false);
                this.mMen.setVisibility(4);
                this.mTextV1.setVisibility(4);
                this.mTextV2.setVisibility(4);
                this.mTextV3.setVisibility(4);
                this.mTextTitle.setVisibility(0);
                this.mTextTitle.setText("AUX");
                return;
            case 3:
                for (CustomImgView Show3 : this.m_CdSta) {
                    Show3.Show(false);
                }
                for (TextView visibility5 : this.mId3Text) {
                    visibility5.setVisibility(4);
                }
                for (TextView visibility6 : this.mMediaListText) {
                    visibility6.setVisibility(4);
                }
                this.mImgST.Show(false);
                this.mMen.setVisibility(4);
                this.mTextV1.setVisibility(4);
                this.mTextV2.setVisibility(4);
                this.mTextV3.setVisibility(4);
                this.mTextTitle.setVisibility(0);
                this.mTextTitle.setText("OFF");
                return;
            default:
                return;
        }
    }

    private TextView AddText(int x, int y, int w, int h) {
        new TextView(this);
        TextView text = this.mManager.AddText(x, y, w, h);
        text.setTextSize(0, 40.0f);
        text.setTextColor(-1);
        text.setGravity(19);
        text.setVisibility(4);
        return text;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Evc.GetInstance().evol_workmode_set(12);
        mfgShow = true;
        mfgFinish = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        mfgShow = false;
    }

    public static void showOdysseyWin() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanAccord8ExdActivity.class);
        }
    }

    public static void finishOdysseyWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    private void ResetData(boolean check) {
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
                if (this.mStaData.Mode == 1) {
                    if (this.mMediaData.Text_List == 1) {
                        if (this.mId3Text[0].getVisibility() == 4) {
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
            Log.d(TAG, "mStaData uptate");
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
            if (this.mMediaData.Text_List == 0 || this.mStaData.Mode != 1 || this.mStaData.MenuVail > 0) {
                for (TextView visibility7 : this.mId3Text) {
                    visibility7.setVisibility(4);
                }
                for (TextView visibility8 : this.mMediaListText) {
                    visibility8.setVisibility(4);
                }
            } else if (this.mMediaData.Text_List == 1) {
                for (TextView visibility9 : this.mId3Text) {
                    visibility9.setVisibility(0);
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
        if (!i2b(this.mMenuData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mMenuData.Update)) {
            this.mMenuData.Update = 0;
            this.mMenuText[0].setText(byte2ASCIIString(this.mMenuData.List1Text, 16));
            this.mMenuText[1].setText(byte2ASCIIString(this.mMenuData.List2Text, 16));
            this.mMenuText[2].setText(byte2ASCIIString(this.mMenuData.List3Text, 16));
        }
    }

    private void showGone(View view, boolean isShow) {
        view.setVisibility(isShow ? 0 : 4);
    }

    private String byte2UnicodeString(byte[] data, int len) {
        try {
            return new String(data, 0, len, "UNICODE");
        } catch (UnsupportedEncodingException e) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    private String byte2ASCIIString(byte[] data, int len) {
        try {
            return new String(data, 0, len, "GBK");
        } catch (UnsupportedEncodingException e) {
            return TXZResourceManager.STYLE_DEFAULT;
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
            long curTick = GetTickCount();
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
            finish();
        }
    }
}
