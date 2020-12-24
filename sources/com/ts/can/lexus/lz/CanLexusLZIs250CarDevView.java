package com.ts.can.lexus.lz;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.dvdplayer.definition.MediaDef;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanLexusLZIs250CarDevView extends CanRelativeCarInfoView implements View.OnLongClickListener {
    public static final int ITEM_CD_KUAIJIN = 8;
    public static final int ITEM_CD_KUAITUI = 1;
    public static final int ITEM_CD_NEXT = 14;
    public static final int ITEM_CD_NEXT_DISC = 15;
    public static final int ITEM_CD_NUM1 = 2;
    public static final int ITEM_CD_NUM2 = 3;
    public static final int ITEM_CD_NUM3 = 4;
    public static final int ITEM_CD_NUM4 = 5;
    public static final int ITEM_CD_NUM5 = 6;
    public static final int ITEM_CD_NUM6 = 7;
    public static final int ITEM_CD_PREV = 10;
    public static final int ITEM_CD_PREV_DISC = 9;
    public static final int ITEM_CD_RDM = 13;
    public static final int ITEM_CD_RPT = 11;
    public static final int ITEM_CD_SCAN = 12;
    public static final int ITEM_RADIO_NEXT = 26;
    public static final int ITEM_RADIO_NUM1 = 18;
    public static final int ITEM_RADIO_NUM2 = 19;
    public static final int ITEM_RADIO_NUM3 = 20;
    public static final int ITEM_RADIO_NUM4 = 21;
    public static final int ITEM_RADIO_NUM5 = 22;
    public static final int ITEM_RADIO_NUM6 = 23;
    public static final int ITEM_RADIO_PREV = 24;
    public static final int ITEM_RADIO_SEARCH = 25;
    private static final int LONG_KEY_TICK = 6;
    public static final int PAGE_AUX = 3;
    public static final int PAGE_DISC = 2;
    public static final int PAGE_POW_OF = 0;
    public static final int PAGE_RADIO = 1;
    public static final int[] mDsicNumArr = {R.drawable.can_klz_num01, R.drawable.can_klz_num02, R.drawable.can_klz_num03, R.drawable.can_klz_num04, R.drawable.can_klz_num05, R.drawable.can_klz_num06};
    public static final int[] mFreqNumArr = {R.drawable.can_yg_radio_num0, R.drawable.can_yg_radio_num1, R.drawable.can_yg_radio_num2, R.drawable.can_yg_radio_num3, R.drawable.can_yg_radio_num4, R.drawable.can_yg_radio_num5, R.drawable.can_yg_radio_num6, R.drawable.can_yg_radio_num7, R.drawable.can_yg_radio_num8, R.drawable.can_yg_radio_num9};
    private static int mLastDiscSta = 0;
    private static long mLastTick = 0;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private String[] BtnCDName;
    private String[] BtnRadioName;
    private String[] CdSta;
    /* access modifiers changed from: private */
    public String[] RadioBand;
    private ParamButton[] mBtnCD;
    private ParamButton[] mBtnRadio;
    private CanDataInfo.LexusLz_DiscInfo mDiscData;
    /* access modifiers changed from: private */
    public CustomImgView mImgBandDW;
    private CustomImgView mImgBk;
    private CustomImgView mImgMainFreq;
    /* access modifiers changed from: private */
    public CanDataInfo.LexusLz_MediaInfo mMediaData;
    /* access modifiers changed from: private */
    public ParamButton[] mRadioFreqs;
    private TextView mTextAux;
    /* access modifiers changed from: private */
    public TextView mTextCDTR;
    /* access modifiers changed from: private */
    public TextView mTextCDTime;
    /* access modifiers changed from: private */
    public TextView mTextCycle;
    /* access modifiers changed from: private */
    public TextView mTextPlayDisc;
    private TextView mTextPlaySta;
    /* access modifiers changed from: private */
    public TextView mTextRandom;
    /* access modifiers changed from: private */
    public TextView mTextSCAN;
    /* access modifiers changed from: private */
    public TextView mTextST;
    /* access modifiers changed from: private */
    public TextView mTextV1;
    /* access modifiers changed from: private */
    public TextView mTextV2;
    private CustomImgView[] m_CdSta;
    private long mlongKeyTick = 0;
    private String preCDSta = "";

    public CanLexusLZIs250CarDevView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (this.mMediaData.Mode == 2) {
            Log.d("lq3", "PAGE_DISConTouch" + Id);
            if (action != 0) {
                if (1 == action) {
                    switch (Id) {
                        case 1:
                            CanJni.LexusLzCarsSet(25, 0);
                            break;
                        case 8:
                            CanJni.LexusLzCarsSet(24, 0);
                            break;
                    }
                }
            } else {
                switch (Id) {
                    case 1:
                        CanJni.LexusLzCarsSet(25, 1);
                        break;
                    case 8:
                        CanJni.LexusLzCarsSet(24, 1);
                        break;
                }
            }
        }
        return false;
    }

    public void onClick(View v) {
        int Id = ((Integer) v.getTag()).intValue();
        if (this.mMediaData.Mode == 2) {
            Log.d("lq3", "PAGE_DISC" + Id);
            switch (Id) {
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    CanJni.LexusLzCarsSet(23, (Id - 2) + 1);
                    return;
                case 9:
                    CanJni.LexusLzCarsSet(22, 0);
                    return;
                case 10:
                    CanJni.LexusLzCarsSet(20, 0);
                    return;
                case 11:
                    CanJni.LexusLzCarsSet(17, 0);
                    return;
                case 12:
                    CanJni.LexusLzCarsSet(26, 0);
                    return;
                case 13:
                    CanJni.LexusLzCarsSet(16, 0);
                    return;
                case 14:
                    CanJni.LexusLzCarsSet(19, 0);
                    return;
                case 15:
                    CanJni.LexusLzCarsSet(21, 0);
                    return;
                default:
                    return;
            }
        } else if (this.mMediaData.Mode == 1) {
            Log.d("lq3", "PAGE_RADIO" + Id);
            switch (Id) {
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    CanJni.LexusLzCarsSet(32, (Id - 18) + 1);
                    return;
                case 24:
                    CanJni.LexusLzCarsSet(38, 0);
                    return;
                case 25:
                    CanJni.LexusLzCarsSet(36, 0);
                    return;
                case 26:
                    CanJni.LexusLzCarsSet(37, 0);
                    return;
                default:
                    return;
            }
        }
    }

    public boolean onLongClick(View v) {
        int Id = ((Integer) v.getTag()).intValue();
        if (this.mMediaData.Mode != 1) {
            return false;
        }
        Log.d("lq3", "ID:onLongClick" + Id);
        switch (Id) {
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                CanJni.LexusLzCarsSet(33, (Id - 18) + 1);
                break;
            case 24:
                CanJni.LexusLzCarsSet(35, 0);
                break;
            case 26:
                CanJni.LexusLzCarsSet(34, 0);
                break;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_lkss_bg);
        this.mDiscData = new CanDataInfo.LexusLz_DiscInfo();
        this.mMediaData = new CanDataInfo.LexusLz_MediaInfo();
        InitData();
        InitView();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    private void InitView() {
        this.mImgBk = getRelativeManager().AddImage(95, 474, R.drawable.can_lkss_bg02);
        this.mImgBandDW = getRelativeManager().AddImage(688, 194);
        this.mImgBandDW.setStateDrawable(R.drawable.can_yg_radio_mhz, R.drawable.can_yg_radio_khz);
        this.mImgMainFreq = getRelativeManager().AddImage(KeyDef.RKEY_POWER, 125, 407, 197);
        this.mImgMainFreq.setDrawDt(-314, 0);
        this.mImgMainFreq.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                if (CanLexusLZIs250CarDevView.this.mMediaData.UpdateOnce == 0) {
                    return false;
                }
                if (CanLexusLZIs250CarDevView.this.mMediaData.Mode == 1 && CanLexusLZIs250CarDevView.this.mMediaData.CurFeq != 0) {
                    CanLexusLZIs250CarDevView.this.ShowPage(1);
                    int radioBand = CanLexusLZIs250CarDevView.this.getRadioBand(CanLexusLZIs250CarDevView.this.mMediaData.Band);
                    CanLexusLZIs250CarDevView.this.mImgBandDW.SetSel(radioBand > 1 ? 1 : 0);
                    CanLexusLZIs250CarDevView.this.mTextV1.setText(CanLexusLZIs250CarDevView.this.RadioBand[radioBand]);
                    if (radioBand == 0) {
                        for (int i = 0; i < CanLexusLZIs250CarDevView.this.mRadioFreqs.length; i++) {
                            CanLexusLZIs250CarDevView.this.mRadioFreqs[i].setText(String.format("%.2f", new Object[]{Float.valueOf(((float) CanLexusLZIs250CarDevView.this.mMediaData.Fm1_MenFeq[i]) * 0.01f)}));
                        }
                    } else if (radioBand == 1) {
                        for (int i2 = 0; i2 < CanLexusLZIs250CarDevView.this.mRadioFreqs.length; i2++) {
                            CanLexusLZIs250CarDevView.this.mRadioFreqs[i2].setText(String.format("%.2f", new Object[]{Float.valueOf(((float) CanLexusLZIs250CarDevView.this.mMediaData.Fm2_MenFeq[i2]) * 0.01f)}));
                        }
                    }
                    if (radioBand == 2) {
                        for (int i3 = 0; i3 < CanLexusLZIs250CarDevView.this.mRadioFreqs.length; i3++) {
                            CanLexusLZIs250CarDevView.this.mRadioFreqs[i3].setText(new StringBuilder(String.valueOf(CanLexusLZIs250CarDevView.this.mMediaData.Am_MenFeq[i3])).toString());
                        }
                    }
                    for (int i4 = 0; i4 < CanLexusLZIs250CarDevView.this.mRadioFreqs.length; i4++) {
                        CanLexusLZIs250CarDevView.this.mRadioFreqs[i4].setTextColor(-1);
                    }
                    if (CanLexusLZIs250CarDevView.this.mMediaData.Men == 0) {
                        CanLexusLZIs250CarDevView.this.mTextV2.setText(" ");
                    } else {
                        CanLexusLZIs250CarDevView.this.mRadioFreqs[CanLexusLZIs250CarDevView.this.mMediaData.Men - 1].setTextColor(SupportMenu.CATEGORY_MASK);
                        CanLexusLZIs250CarDevView.this.mTextV2.setText(String.format("CH%d", new Object[]{Integer.valueOf(CanLexusLZIs250CarDevView.this.mMediaData.Men)}));
                    }
                    CanLexusLZIs250CarDevView.this.mTextST.setVisibility(CanLexusLZIs250CarDevView.this.mMediaData.St == 0 ? 4 : 0);
                    CanLexusLZIs250CarDevView.this.mTextSCAN.setVisibility(CanLexusLZIs250CarDevView.this.mMediaData.Scan == 0 ? 4 : 0);
                    int Qian = (CanLexusLZIs250CarDevView.this.mMediaData.CurFeq / MediaDef.PROGRESS_MAX) % 10;
                    int Bai = (CanLexusLZIs250CarDevView.this.mMediaData.CurFeq / 100) % 10;
                    int Shi = (CanLexusLZIs250CarDevView.this.mMediaData.CurFeq / 10) % 10;
                    int Ge = CanLexusLZIs250CarDevView.this.mMediaData.CurFeq % 10;
                    if (CanLexusLZIs250CarDevView.this.mMediaData.Band >= 3) {
                        if (CanLexusLZIs250CarDevView.this.mMediaData.CurFeq >= 1000) {
                            view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[1], 444, 0);
                        }
                        view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[Bai], 492, 0);
                        view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[Shi], CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST1, 0);
                        view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[Ge], 588, 0);
                    } else {
                        if (CanLexusLZIs250CarDevView.this.mMediaData.CurFeq >= 10000) {
                            view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[1], 386, 0);
                        }
                        view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[Qian], 434, 0);
                        view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[Bai], 482, 0);
                        view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[Shi], 551, 0);
                        view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[Ge], 599, 0);
                        view.drawImage(R.drawable.can_yg_radio_point, 518, 0);
                    }
                } else if (CanLexusLZIs250CarDevView.this.mMediaData.Mode == 2) {
                    CanLexusLZIs250CarDevView.this.ShowPage(2);
                    CanLexusLZIs250CarDevView.this.mTextCDTR.setText(String.format("%d/%d", new Object[]{Integer.valueOf(CanLexusLZIs250CarDevView.this.mMediaData.CurTrack), Integer.valueOf(CanLexusLZIs250CarDevView.this.mMediaData.TotalTrack)}));
                    int HShi = (CanLexusLZIs250CarDevView.this.mMediaData.CurTime_h / 10) % 10;
                    int HGe = CanLexusLZIs250CarDevView.this.mMediaData.CurTime_h % 10;
                    int MShi = (CanLexusLZIs250CarDevView.this.mMediaData.CurTime_m / 10) % 10;
                    int MGe = CanLexusLZIs250CarDevView.this.mMediaData.CurTime_m % 10;
                    int SShi = (CanLexusLZIs250CarDevView.this.mMediaData.CurTime_s / 10) % 10;
                    int SGe = CanLexusLZIs250CarDevView.this.mMediaData.CurTime_s % 10;
                    view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[HShi], KeyDef.RKEY_POWER, 58);
                    view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[HGe], 362, 58);
                    view.drawImage(R.drawable.can_yg_radio_colon, 410, 58);
                    view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[MShi], 458, 58);
                    view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[MGe], 506, 58);
                    view.drawImage(R.drawable.can_yg_radio_colon, 554, 58);
                    view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[SShi], CanCameraUI.BTN_GOLF_WC_MODE3, 58);
                    view.drawImage(CanLexusLZIs250CarDevView.mFreqNumArr[SGe], CanCameraUI.BTN_LANDWIND_3D_LEFT_UP, 58);
                    CanLexusLZIs250CarDevView.this.mTextCDTime.setText(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(CanLexusLZIs250CarDevView.this.mMediaData.TotalTime_h), Integer.valueOf(CanLexusLZIs250CarDevView.this.mMediaData.TotalTime_m), Integer.valueOf(CanLexusLZIs250CarDevView.this.mMediaData.TotalTime_s)}));
                    CanLexusLZIs250CarDevView.this.mTextPlayDisc.setText("CD: " + CanLexusLZIs250CarDevView.this.mMediaData.CurNum);
                    CanLexusLZIs250CarDevView.this.mTextRandom.setText(CanLexusLZIs250CarDevView.this.mMediaData.Random == 0 ? "Rpt" : "Rdm");
                    String cycleText = "";
                    if (CanLexusLZIs250CarDevView.this.mMediaData.Cycle == 0) {
                        cycleText = "";
                    } else if (CanLexusLZIs250CarDevView.this.mMediaData.Cycle == 1) {
                        cycleText = "Cycle Disc";
                    } else if (CanLexusLZIs250CarDevView.this.mMediaData.Cycle == 2) {
                        cycleText = "Cycle One";
                    }
                    CanLexusLZIs250CarDevView.this.mTextCycle.setText(cycleText);
                } else if (CanLexusLZIs250CarDevView.this.mMediaData.Mode == 3) {
                    CanLexusLZIs250CarDevView.this.ShowPage(3);
                }
                return false;
            }
        });
        for (int i = 0; i < this.BtnRadioName.length; i++) {
            if (i < 6) {
                this.mBtnRadio[i] = getRelativeManager().AddButton((i * 128) + 1 + 128, 414, 126, 60);
                this.mBtnRadio[i].setStateUpDn(R.drawable.can_lkss_short_up, R.drawable.can_lkss_long_dn);
            } else {
                this.mBtnRadio[i] = getRelativeManager().AddButton(((i - 6) * 170) + 260, 480, 168, 60);
                this.mBtnRadio[i].setStateUpDn(R.drawable.can_lkss_long_up, R.drawable.can_lkss_long_dn);
            }
            this.mBtnRadio[i].setTag(Integer.valueOf(i + 18));
            this.mBtnRadio[i].setOnLongClickListener(this);
            this.mBtnRadio[i].setOnClickListener(this);
            this.mBtnRadio[i].setText(this.BtnRadioName[i]);
            this.mBtnRadio[i].setTextSize(0, 24.0f);
            this.mBtnRadio[i].setTextColor(-1);
            this.mBtnRadio[i].setVisibility(4);
        }
        for (int i2 = 0; i2 < this.BtnCDName.length; i2++) {
            if (i2 < 8) {
                this.mBtnCD[i2] = getRelativeManager().AddButton((i2 * 128) + 1, 414, 126, 60);
                this.mBtnCD[i2].setStateUpDn(R.drawable.can_lkss_short_up, R.drawable.can_lkss_short_dn);
            } else {
                this.mBtnCD[i2] = getRelativeManager().AddButton(((i2 - 8) * 128) + 71, 480, 126, 60);
                this.mBtnCD[i2].setStateUpDn(R.drawable.can_lkss_short_up, R.drawable.can_lkss_short_dn);
            }
            this.mBtnCD[i2].setTag(Integer.valueOf(i2 + 1));
            this.mBtnCD[i2].setOnLongClickListener(this);
            this.mBtnCD[i2].setOnClickListener(this);
            this.mBtnCD[i2].setText(this.BtnCDName[i2]);
            this.mBtnCD[i2].setTextSize(0, 24.0f);
            this.mBtnCD[i2].setTextColor(-1);
            this.mBtnCD[i2].setVisibility(4);
        }
        this.mBtnCD[0].setOnTouchListener(this);
        this.mBtnCD[7].setOnTouchListener(this);
        for (int i3 = 0; i3 < 6; i3++) {
            if (i3 < 3) {
                this.mRadioFreqs[i3] = getRelativeManager().AddButton((i3 * KeyDef.RKEY_POWER_OFF) + 89, Can.CAN_FLAT_RZC, Can.CAN_HYUNDAI_WC, 60);
                this.mRadioFreqs[i3].setStateUpDn(R.drawable.can_yg_radio_rect03_dn, R.drawable.can_yg_radio_rect03_dn);
            } else {
                this.mRadioFreqs[i3] = getRelativeManager().AddButton(((i3 - 3) * KeyDef.RKEY_POWER_OFF) + 89, KeyDef.RKEY_RDS_TA, Can.CAN_HYUNDAI_WC, 60);
                this.mRadioFreqs[i3].setStateUpDn(R.drawable.can_yg_radio_rect03_dn, R.drawable.can_yg_radio_rect03_dn);
            }
            this.mRadioFreqs[i3].setTextColor(-1);
            this.mRadioFreqs[i3].setTextSize(0, 24.0f);
            this.mRadioFreqs[i3].setVisibility(4);
        }
        this.mTextV1 = AddText1(260, 142, 100, 40);
        this.mTextV2 = AddText1(Can.CAN_CHANA_CS75_WC, 182, 200, 40);
        this.mTextV1.setGravity(21);
        this.mTextV2.setGravity(21);
        this.mTextST = AddText("ST", 40, 100, 20);
        this.mTextSCAN = AddText("SCAN", 120, 100, 20);
        this.mTextST.setVisibility(4);
        this.mTextSCAN.setVisibility(4);
        for (int i4 = 0; i4 < 6; i4++) {
            this.m_CdSta[i4] = getRelativeManager().AddImage((i4 * 35) + 42, 29, mDsicNumArr[i4]);
            this.m_CdSta[i4].Show(false);
        }
        this.mTextRandom = AddText("Rdm", 40, 80, 25);
        this.mTextCycle = AddText("Rpt", 120, 80, 25);
        this.mTextRandom.setVisibility(4);
        this.mTextCycle.setVisibility(4);
        this.mTextCDTime = AddText((int) CanCameraUI.BTN_CC_WC_DIRECTION1, (int) Can.CAN_SE_DX7_RZC, 300, 50);
        this.mTextCDTime.setTextSize(0, 45.0f);
        this.mTextCDTime.setMaxLines(1);
        this.mTextPlaySta = AddText(660, 90, 300, 50);
        this.mTextPlaySta.setGravity(21);
        this.mTextPlayDisc = AddText(40, (int) KeyDef.RKEY_res3, 300, 50);
        this.mTextCDTR = AddText((int) KeyDef.RKEY_POWER, 288, 300, 50);
        this.mTextCDTR.setMaxLines(1);
        this.mTextAux = AddMenuText(433, 119, 200, 104);
        this.mTextAux.setText("AUX");
    }

    /* access modifiers changed from: protected */
    public int getRadioBand(int band) {
        switch (band) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 16:
                return 2;
            default:
                return 0;
        }
    }

    private void InitData() {
        this.mRadioFreqs = new ParamButton[6];
        this.m_CdSta = new CustomImgView[6];
        this.RadioBand = new String[]{"FM1", "FM2", "AM"};
        this.CdSta = new String[]{"FREE", "LOAD", "WAIT", "DISC READING", "PLAY", "EJECT", "ERROR"};
        this.BtnCDName = new String[]{"<<", MainSet.SP_XPH5, MainSet.SP_RLF_KORON, MainSet.SP_XH_DMAX, MainSet.SP_KS_QOROS, MainSet.SP_LM_WR, MainSet.SP_YSJ_QP, ">>", "|<<Disc", "|<<", "RPT", "SCAN", "RDM", ">>|", "Disc>>|"};
        this.BtnRadioName = new String[]{MainSet.SP_XPH5, MainSet.SP_RLF_KORON, MainSet.SP_XH_DMAX, MainSet.SP_KS_QOROS, MainSet.SP_LM_WR, MainSet.SP_YSJ_QP, "|<<", "Search", ">>|"};
        this.mBtnCD = new ParamButton[this.BtnCDName.length];
        this.mBtnRadio = new ParamButton[this.BtnRadioName.length];
    }

    public void ResetData(boolean check) {
        CanJni.LexusLzGetDiscInfo(this.mDiscData);
        CanJni.LexusLzGetMediaInfo(this.mMediaData);
        UpdateMainFreq(check);
    }

    public void QueryData() {
        CanJni.LexusLzQuery(49, 0);
        Sleep(5);
        CanJni.LexusLzQuery(97, 0);
        Sleep(5);
        CanJni.LexusLzQuery(98, 0);
        Sleep(5);
    }

    private long getTickCount() {
        return SystemClock.uptimeMillis();
    }

    private void UpdateDiscFlag() {
        if (this.mMediaData.Mode == 2 && this.mDiscData.UpdateOnce != 0) {
            if (this.mDiscData.Sta <= 6) {
                if (!this.CdSta[this.mDiscData.Sta].equals(this.preCDSta)) {
                    this.preCDSta = this.CdSta[this.mDiscData.Sta];
                    if (this.mDiscData.Sta == 0) {
                        this.mTextPlaySta.setText("");
                    } else {
                        this.mTextPlaySta.setText(this.CdSta[this.mDiscData.Sta]);
                    }
                }
            } else if (this.mDiscData.Sta <= 15) {
                this.preCDSta = this.CdSta[6];
                this.mTextPlaySta.setText(this.CdSta[6]);
            }
            long curTick = getTickCount();
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
                if (i2b(this.mDiscData.Vail & 1) && this.mDiscData.Num != 1) {
                    this.m_CdSta[0].Show(true);
                }
                if (i2b(this.mDiscData.Vail & 2) && this.mDiscData.Num != 2) {
                    this.m_CdSta[1].Show(true);
                }
                if (i2b(this.mDiscData.Vail & 4) && this.mDiscData.Num != 3) {
                    this.m_CdSta[2].Show(true);
                }
                if (i2b(this.mDiscData.Vail & 8) && this.mDiscData.Num != 4) {
                    this.m_CdSta[3].Show(true);
                }
                if (i2b(this.mDiscData.Vail & 16) && this.mDiscData.Num != 5) {
                    this.m_CdSta[4].Show(true);
                }
                if (i2b(this.mDiscData.Vail & 32) && this.mDiscData.Num != 6) {
                    this.m_CdSta[5].Show(true);
                }
                if (this.mDiscData.Num > 0 && this.mDiscData.Num < 7) {
                    if (mLastDiscSta != 0) {
                        this.m_CdSta[this.mDiscData.Num - 1].Show(true);
                    } else {
                        this.m_CdSta[this.mDiscData.Num - 1].Show(false);
                    }
                }
            }
        }
    }

    private void UpdateMainFreq(boolean check) {
        if (this.mMediaData.UpdateOnce != 0) {
            if (!check || this.mMediaData.Update != 0) {
                this.mMediaData.Update = 0;
                this.mImgMainFreq.invalidate();
            }
        } else if (!check) {
            CanJni.LexusLzQuery(97, 0);
            CanJni.LexusLzQuery(98, 0);
        }
        UpdateDiscFlag();
    }

    /* access modifiers changed from: private */
    public void ShowPage(int page) {
        if (page == 1) {
            this.mTextPlaySta.setVisibility(4);
            this.mTextPlayDisc.setVisibility(4);
            this.mTextRandom.setVisibility(4);
            this.mTextCycle.setVisibility(4);
            this.mTextST.setVisibility(0);
            this.mTextSCAN.setVisibility(0);
            for (int i = 0; i < 6; i++) {
                this.m_CdSta[i].Show(false);
                this.mRadioFreqs[i].setVisibility(0);
            }
            this.mTextAux.setVisibility(4);
            this.mTextCDTime.setVisibility(4);
            this.mTextCDTR.setVisibility(4);
            this.mTextV1.setVisibility(0);
            this.mTextV2.setVisibility(0);
            this.mImgBandDW.Show(1);
            for (ParamButton visibility : this.mBtnCD) {
                visibility.setVisibility(4);
            }
            for (ParamButton visibility2 : this.mBtnRadio) {
                visibility2.setVisibility(0);
            }
        } else if (page == 2) {
            this.mImgBandDW.Show(0);
            this.mTextV1.setVisibility(4);
            this.mTextAux.setVisibility(4);
            this.mTextCDTime.setVisibility(0);
            this.mTextCDTR.setVisibility(0);
            for (int i2 = 0; i2 < 6; i2++) {
                this.mRadioFreqs[i2].setVisibility(4);
            }
            this.mTextV2.setVisibility(4);
            this.mTextPlaySta.setVisibility(0);
            this.mTextPlayDisc.setVisibility(0);
            this.mTextRandom.setVisibility(0);
            this.mTextCycle.setVisibility(0);
            this.mTextST.setVisibility(4);
            this.mTextSCAN.setVisibility(4);
            for (ParamButton visibility3 : this.mBtnCD) {
                visibility3.setVisibility(0);
            }
            for (ParamButton visibility4 : this.mBtnRadio) {
                visibility4.setVisibility(4);
            }
        } else if (page == 3) {
            this.mImgBandDW.Show(0);
            this.mTextV1.setVisibility(4);
            this.mTextV2.setVisibility(4);
            this.mTextPlaySta.setVisibility(4);
            this.mTextPlayDisc.setVisibility(4);
            this.mTextRandom.setVisibility(4);
            this.mTextCycle.setVisibility(4);
            this.mTextST.setVisibility(4);
            this.mTextSCAN.setVisibility(4);
            for (int i3 = 0; i3 < 6; i3++) {
                this.m_CdSta[i3].Show(false);
                this.mRadioFreqs[i3].setVisibility(4);
            }
            this.mTextAux.setVisibility(0);
            this.mTextCDTR.setVisibility(4);
            for (ParamButton visibility5 : this.mBtnCD) {
                visibility5.setVisibility(4);
            }
            for (ParamButton visibility6 : this.mBtnRadio) {
                visibility6.setVisibility(4);
            }
        }
    }

    private TextView AddText(String text, int x, int y, int size) {
        TextView view = AddText(x, y, -2, -2);
        view.setVisibility(0);
        view.setText(text);
        view.setTextSize(0, (float) size);
        return view;
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

    private TextView AddText1(int x, int y, int w, int h) {
        new TextView(getActivity());
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextSize(0, 23.0f);
        text.setTextColor(-1);
        text.setGravity(17);
        text.setVisibility(4);
        return text;
    }

    private TextView AddMenuText(int x, int y, int w, int h) {
        new TextView(getActivity());
        TextView text = getRelativeManager().AddText(x, y, w, h);
        text.setTextSize(0, 97.0f);
        text.setTextColor(-1);
        text.setGravity(17);
        text.setVisibility(4);
        return text;
    }
}
