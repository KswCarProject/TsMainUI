package com.ts.can.lexus.is250;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
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
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemSeekBar;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanLexusIs250CarDevActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener, View.OnClickListener, CanItemSeekBar.CallBack {
    public static final int ITEM_ASEL = 13;
    public static final int ITEM_ASL = 18;
    public static final int ITEM_AUIDO = 10;
    public static final int ITEM_BAL = 23;
    public static final int ITEM_BASS = 19;
    public static final int ITEM_DOWN = 11;
    public static final int ITEM_FAD = 22;
    public static final int ITEM_MID = 20;
    public static final int ITEM_MUTE = 14;
    public static final int ITEM_NUM1 = 2;
    public static final int ITEM_NUM2 = 3;
    public static final int ITEM_NUM3 = 4;
    public static final int ITEM_NUM4 = 5;
    public static final int ITEM_NUM5 = 6;
    public static final int ITEM_NUM6 = 7;
    public static final int ITEM_PSEL = 12;
    public static final int ITEM_SCAN = 8;
    public static final int ITEM_TOGGLE = 17;
    public static final int ITEM_TRAF = 1;
    public static final int ITEM_TRE = 21;
    public static final int ITEM_UP = 9;
    private static final int LONG_KEY_TICK = 6;
    public static final int PAGE_AUDIO = 4;
    public static final int PAGE_AUX = 3;
    public static final int PAGE_DISC = 2;
    public static final int PAGE_POW_OF = 0;
    public static final int PAGE_RADIO = 1;
    public static final String TAG = "CanLexusIs250CarDevActivity";
    public static final int[] mDsicNumArr = {R.drawable.can_klz_num01, R.drawable.can_klz_num02, R.drawable.can_klz_num03, R.drawable.can_klz_num04, R.drawable.can_klz_num05, R.drawable.can_klz_num06};
    public static final int[] mFreqNumArr = {R.drawable.can_yg_radio_num0, R.drawable.can_yg_radio_num1, R.drawable.can_yg_radio_num2, R.drawable.can_yg_radio_num3, R.drawable.can_yg_radio_num4, R.drawable.can_yg_radio_num5, R.drawable.can_yg_radio_num6, R.drawable.can_yg_radio_num7, R.drawable.can_yg_radio_num8, R.drawable.can_yg_radio_num9};
    private static int mLastDiscSta = 0;
    private static long mLastTick = 0;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private String[] BtnName = {"TRAF", "1", "2", "3", MainSet.SP_KS_QOROS, MainSet.SP_TW_CJW, MainSet.SP_XS_DZ, "SCAN", "UP", "AUDIO", "DN", "P.SEL", "ASL", "MUTE"};
    /* access modifiers changed from: private */
    public String[] CdSta = {"LOAD", "READ", "EJECT", "NO DISC", "CHECK DISC", "BUSY", "PLAY"};
    /* access modifiers changed from: private */
    public String[] RadioBand = {"FM1", "FM2", " ", "AM"};
    private boolean isAudioShowing;
    private CanDataInfo.Is250_Amp mAmpData = new CanDataInfo.Is250_Amp();
    private TextView[] mAudioTexts = new TextView[8];
    private CanItemSeekBar mBALItem;
    private CanItemSeekBar mBASSItem;
    private String[] mBalanceValues = {"驾驶员", "全部", "前", "后"};
    private ParamButton[] mBtn = new ParamButton[14];
    private ParamButton mBtnASL;
    private ParamButton mBtnToggle;
    /* access modifiers changed from: private */
    public CanDataInfo.Is250_Media mCurMedia = new CanDataInfo.Is250_Media();
    private CanItemSeekBar mFADItem;
    /* access modifiers changed from: private */
    public CustomImgView mImgBandDW;
    private CustomImgView mImgBk;
    private CustomImgView mImgMainFreq;
    private CanItemSeekBar mMIDItem;
    protected RelativeLayoutManager mManager;
    private CanItemSeekBar mTREItem;
    private TextView mTextAux;
    /* access modifiers changed from: private */
    public TextView mTextPlaySta;
    /* access modifiers changed from: private */
    public TextView mTextRandom;
    /* access modifiers changed from: private */
    public TextView mTextRepeat;
    /* access modifiers changed from: private */
    public TextView mTextV1;
    /* access modifiers changed from: private */
    public TextView mTextV2;
    private TextView mTvBalance;
    private CustomImgView[] m_CdSta = new CustomImgView[6];
    private long mlongKeyTick = 0;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_lkss_bg);
        this.mImgBk = this.mManager.AddImage(95, 474, R.drawable.can_lkss_bg02);
        this.mImgBandDW = this.mManager.AddImage(688, 194);
        this.mImgBandDW.setStateDrawable(R.drawable.can_yg_radio_mhz, R.drawable.can_yg_radio_khz);
        this.mImgMainFreq = this.mManager.AddImage(386, 125, 263, 97);
        this.mImgMainFreq.setDrawDt(-386, 0);
        this.mImgMainFreq.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                if (CanLexusIs250CarDevActivity.this.mCurMedia.UpdateOnce == 0) {
                    return false;
                }
                if (CanLexusIs250CarDevActivity.this.mCurMedia.Mode == 1 && CanLexusIs250CarDevActivity.this.mCurMedia.Frq != 0) {
                    CanLexusIs250CarDevActivity.this.ShowPage(1);
                    CanLexusIs250CarDevActivity.this.mImgBandDW.SetSel(CanLexusIs250CarDevActivity.this.mCurMedia.Band > 1 ? 1 : 0);
                    CanLexusIs250CarDevActivity.this.mTextV1.setText(CanLexusIs250CarDevActivity.this.RadioBand[CanLexusIs250CarDevActivity.this.mCurMedia.Band]);
                    if (CanLexusIs250CarDevActivity.this.mCurMedia.Pre == 0) {
                        CanLexusIs250CarDevActivity.this.mTextV2.setText(" ");
                    } else {
                        CanLexusIs250CarDevActivity.this.mTextV2.setText(String.format("CH%d", new Object[]{Integer.valueOf(CanLexusIs250CarDevActivity.this.mCurMedia.Pre)}));
                    }
                    int Qian = (CanLexusIs250CarDevActivity.this.mCurMedia.Frq / 1000) % 10;
                    int Bai = (CanLexusIs250CarDevActivity.this.mCurMedia.Frq / 100) % 10;
                    int Shi = (CanLexusIs250CarDevActivity.this.mCurMedia.Frq / 10) % 10;
                    int Ge = CanLexusIs250CarDevActivity.this.mCurMedia.Frq % 10;
                    if (CanLexusIs250CarDevActivity.this.mCurMedia.Band >= 3) {
                        if (CanLexusIs250CarDevActivity.this.mCurMedia.Frq >= 1000) {
                            view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[1], 444, 0);
                        }
                        view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[Bai], 492, 0);
                        view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[Shi], 540, 0);
                        view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[Ge], 588, 0);
                    } else {
                        if (CanLexusIs250CarDevActivity.this.mCurMedia.Frq >= 10000) {
                            view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[1], 386, 0);
                        }
                        view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[Qian], 434, 0);
                        view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[Bai], 482, 0);
                        view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[Shi], 551, 0);
                        view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[Ge], 599, 0);
                        view.drawImage(R.drawable.can_yg_radio_point, 518, 0);
                    }
                } else if (CanLexusIs250CarDevActivity.this.mCurMedia.Mode == 2) {
                    CanLexusIs250CarDevActivity.this.ShowPage(2);
                    CanLexusIs250CarDevActivity.this.mTextV2.setText(String.format("TR %d", new Object[]{Integer.valueOf(CanLexusIs250CarDevActivity.this.mCurMedia.Track)}));
                    int MShi = (CanLexusIs250CarDevActivity.this.mCurMedia.PlayMin / 10) % 10;
                    int MGe = CanLexusIs250CarDevActivity.this.mCurMedia.PlayMin % 10;
                    int SShi = (CanLexusIs250CarDevActivity.this.mCurMedia.PlaySec / 10) % 10;
                    int SGe = CanLexusIs250CarDevActivity.this.mCurMedia.PlaySec % 10;
                    view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[MShi], 386, 0);
                    view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[MGe], 434, 0);
                    view.drawImage(R.drawable.can_yg_radio_colon, 482, 0);
                    view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[SShi], CanCameraUI.BTN_CHANA_ALSVINV7_MODE1, 0);
                    view.drawImage(CanLexusIs250CarDevActivity.mFreqNumArr[SGe], 578, 0);
                    if (CanLexusIs250CarDevActivity.this.mCurMedia.PlaySta <= 6) {
                        CanLexusIs250CarDevActivity.this.mTextPlaySta.setText(CanLexusIs250CarDevActivity.this.CdSta[CanLexusIs250CarDevActivity.this.mCurMedia.PlaySta]);
                    }
                    CanLexusIs250CarDevActivity.this.mTextRandom.setVisibility(CanLexusIs250CarDevActivity.this.mCurMedia.Rdm == 0 ? 4 : 0);
                    CanLexusIs250CarDevActivity.this.mTextRepeat.setVisibility(CanLexusIs250CarDevActivity.this.mCurMedia.Rpt == 0 ? 4 : 0);
                } else if (CanLexusIs250CarDevActivity.this.mCurMedia.Mode == 3) {
                    CanLexusIs250CarDevActivity.this.ShowPage(3);
                }
                return false;
            }
        });
        for (int i = 0; i < 14; i++) {
            if (i < 8) {
                this.mBtn[i] = this.mManager.AddButton((i * 128) + 1, 414, 126, 60);
                this.mBtn[i].setStateUpDn(R.drawable.can_lkss_short_up, R.drawable.can_lkss_long_dn);
            } else {
                this.mBtn[i] = this.mManager.AddButton(((i - 8) * 170) + 3, 480, 168, 60);
                this.mBtn[i].setStateUpDn(R.drawable.can_lkss_long_up, R.drawable.can_lkss_long_dn);
            }
            this.mBtn[i].setTag(Integer.valueOf(i + 1));
            this.mBtn[i].setOnTouchListener(this);
            this.mBtn[i].setText(this.BtnName[i]);
            this.mBtn[i].setTextColor(-1);
        }
        this.mTextPlaySta = AddText(670, 172, 300, 50);
        this.mTextV1 = AddText1(260, 142, 100, 40);
        this.mTextV2 = AddText1(260, 182, 100, 40);
        this.mTextRandom = AddText("Rdm", 40, 100, 20);
        this.mTextRepeat = AddText("Rpt", 120, 100, 20);
        this.mTextRandom.setVisibility(4);
        this.mTextRepeat.setVisibility(4);
        for (int i2 = 0; i2 < 6; i2++) {
            this.m_CdSta[i2] = this.mManager.AddImage((i2 * 35) + 42, 29, mDsicNumArr[i2]);
            this.m_CdSta[i2].Show(false);
        }
        this.mTextAux = AddMenuText(433, 119, 200, 104);
        this.mTextAux.setText("AUX");
        AddAudioViews();
        showAudioViews(false);
    }

    private void AddAudioViews() {
        this.mBASSItem = AddSeekBar(60, 60, -5, 5, 19);
        this.mMIDItem = AddSeekBar(60, 125, -5, 5, 20);
        this.mTREItem = AddSeekBar(60, 190, -5, 5, 21);
        this.mFADItem = AddSeekBar(60, 255, -7, 7, 22);
        this.mBALItem = AddSeekBar(60, KeyDef.RKEY_MEDIA_SLOW, -7, 7, 23);
        this.mAudioTexts[0] = AddText("低音", 430, 95, 24);
        this.mAudioTexts[1] = AddText("中音", 430, 160, 24);
        this.mAudioTexts[2] = AddText("高音", 430, (int) Can.CAN_BENZ_SMART_OD, 24);
        this.mAudioTexts[3] = AddText("前后", 430, 290, 24);
        this.mAudioTexts[4] = AddText("左右", 430, 355, 24);
        this.mAudioTexts[5] = AddText("音响开关", (int) CanCameraUI.BTN_CC_WC_DIRECTION1, 100, 30);
        this.mAudioTexts[6] = AddText("ASL", (int) CanCameraUI.BTN_CC_WC_DIRECTION1, 200, 30);
        this.mAudioTexts[7] = AddText("平衡点", (int) CanCameraUI.BTN_CC_WC_DIRECTION1, 300, 30);
        this.mBtnToggle = AddButton(850, 100, 117, 46, R.drawable.can_lexus_is250_eq_off, R.drawable.can_lexus_is250_eq_on, 17);
        this.mBtnASL = AddButton(850, 200, 117, 46, R.drawable.can_lexus_is250_eq_off, R.drawable.can_lexus_is250_eq_on, 18);
        this.mTvBalance = AddText1(850, 300, 120, 40);
        this.mTvBalance.setVisibility(0);
        this.mTvBalance.setTextSize(0, 30.0f);
    }

    private void showAudioViews(boolean show) {
        this.mBASSItem.showGone(show);
        this.mMIDItem.showGone(show);
        this.mTREItem.showGone(show);
        this.mFADItem.showGone(show);
        this.mBALItem.showGone(show);
        int visible = show ? 0 : 8;
        for (View view : this.mAudioTexts) {
            view.setVisibility(visible);
        }
        this.mBtnToggle.setVisibility(visible);
        this.mBtnASL.setVisibility(visible);
        this.mTvBalance.setVisibility(visible);
    }

    private CanItemSeekBar AddSeekBar(int x, int y, int min, int max, int id) {
        CanItemSeekBar item = new CanItemSeekBar(this, min, max, id);
        item.setOnProgressChangedListener(this);
        this.mManager.AddView(item.getView(), x, y, -2, -2);
        return item;
    }

    private ParamButton AddButton(int x, int y, int w, int h, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y);
        button.setOnClickListener(this);
        button.setTag(Integer.valueOf(id));
        button.setStateUpSel(normal, selected);
        return button;
    }

    private TextView AddText(String text, int x, int y, int size) {
        TextView view = AddText(x, y, -2, -2);
        view.setVisibility(0);
        view.setText(text);
        view.setTextSize(0, (float) size);
        return view;
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

    private TextView AddText1(int x, int y, int w, int h) {
        new TextView(this);
        TextView text = this.mManager.AddText(x, y, w, h);
        text.setTextSize(0, 23.0f);
        text.setTextColor(-1);
        text.setGravity(17);
        text.setVisibility(4);
        return text;
    }

    private TextView AddMenuText(int x, int y, int w, int h) {
        new TextView(this);
        TextView text = this.mManager.AddText(x, y, w, h);
        text.setTextSize(0, 97.0f);
        text.setTextColor(-1);
        text.setGravity(17);
        text.setVisibility(4);
        return text;
    }

    public void onIncrease(int id) {
        if (id == 19) {
            AudioCtrl(4, 1);
        } else if (id == 20) {
            AudioCtrl(6, 1);
        } else if (id == 21) {
            AudioCtrl(5, 1);
        } else if (id == 22) {
            AudioCtrl(1, 1);
        } else if (id == 23) {
            AudioCtrl(2, 1);
        }
    }

    public void onDecrease(int id) {
        if (id == 19) {
            AudioCtrl(4, 0);
        } else if (id == 20) {
            AudioCtrl(6, 0);
        } else if (id == 21) {
            AudioCtrl(5, 0);
        } else if (id == 22) {
            AudioCtrl(1, 0);
        } else if (id == 23) {
            AudioCtrl(2, 0);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 18:
                AudioCtrl(3, Neg(this.mAmpData.Asl));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        Evc.GetInstance().evol_workmode_set(12);
        CanJni.LexusIs250Query(124, 0);
        CanJni.LexusIs250Query(49, 0);
        Log.d(TAG, "********************onResume**********************");
        mfgShow = true;
        mfgFinish = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        mfgShow = false;
    }

    private void UpdateDiscFlag() {
        if (this.mCurMedia.Mode == 2) {
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
                if (i2b(this.mCurMedia.DiscVail & 1) && this.mCurMedia.DiscNum != 1) {
                    this.m_CdSta[0].Show(true);
                }
                if (i2b(this.mCurMedia.DiscVail & 2) && this.mCurMedia.DiscNum != 2) {
                    this.m_CdSta[1].Show(true);
                }
                if (i2b(this.mCurMedia.DiscVail & 4) && this.mCurMedia.DiscNum != 3) {
                    this.m_CdSta[2].Show(true);
                }
                if (i2b(this.mCurMedia.DiscVail & 8) && this.mCurMedia.DiscNum != 4) {
                    this.m_CdSta[3].Show(true);
                }
                if (i2b(this.mCurMedia.DiscVail & 16) && this.mCurMedia.DiscNum != 5) {
                    this.m_CdSta[4].Show(true);
                }
                if (i2b(this.mCurMedia.DiscVail & 32) && this.mCurMedia.DiscNum != 6) {
                    this.m_CdSta[5].Show(true);
                }
                if (this.mCurMedia.DiscNum > 0 && this.mCurMedia.DiscNum < 7) {
                    if (mLastDiscSta != 0) {
                        this.m_CdSta[this.mCurMedia.DiscNum - 1].Show(true);
                    } else {
                        this.m_CdSta[this.mCurMedia.DiscNum - 1].Show(false);
                    }
                }
            }
        }
    }

    private void UpdateMainFreq(boolean check) {
        if (!this.isAudioShowing) {
            if (this.mCurMedia.UpdateOnce != 0) {
                if (!check || this.mCurMedia.Update != 0) {
                    Log.d(TAG, "UpdateMainFreq");
                    this.mCurMedia.Update = 0;
                    this.mImgMainFreq.invalidate();
                }
            } else if (!check) {
                CanJni.LexusIs250Query(124, 0);
            }
            UpdateDiscFlag();
        }
    }

    private void updateAudio(boolean check) {
        if (this.isAudioShowing) {
            if (this.mAmpData.UpdateOnce != 0) {
                if (!check || this.mAmpData.Update != 0) {
                    this.mBtnToggle.SetSel(this.mAmpData.Sound);
                    this.mBtnASL.SetSel(this.mAmpData.Asl);
                    if (this.mAmpData.Balance >= 0 && this.mAmpData.Balance < this.mBalanceValues.length) {
                        this.mTvBalance.setText(this.mBalanceValues[this.mAmpData.Balance]);
                    }
                    this.mBASSItem.setValue(this.mAmpData.Bass - 7);
                    this.mMIDItem.setValue(this.mAmpData.Mid - 7);
                    this.mTREItem.setValue(this.mAmpData.Tre - 7);
                    this.mFADItem.setValue(this.mAmpData.Fad - 7);
                    this.mBALItem.setValue(this.mAmpData.Bal - 7);
                }
            } else if (!check) {
                CanJni.LexusIs250Query(49, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void ShowPage(int page) {
        if (page == 1) {
            this.mTextPlaySta.setVisibility(4);
            this.mTextRandom.setVisibility(4);
            this.mTextRepeat.setVisibility(4);
            for (int i = 0; i < 6; i++) {
                this.m_CdSta[i].Show(false);
            }
            this.mTextAux.setVisibility(4);
            this.mTextV1.setVisibility(0);
            this.mTextV2.setVisibility(0);
            this.mImgBandDW.Show(1);
        } else if (page == 2) {
            this.mImgBandDW.Show(0);
            this.mTextV1.setVisibility(4);
            this.mTextAux.setVisibility(4);
            this.mTextV2.setVisibility(0);
            this.mTextPlaySta.setVisibility(0);
            this.mTextRandom.setVisibility(0);
            this.mTextRepeat.setVisibility(0);
        } else if (page == 3) {
            this.mImgBandDW.Show(0);
            this.mTextV1.setVisibility(4);
            this.mTextV2.setVisibility(4);
            this.mTextPlaySta.setVisibility(4);
            this.mTextRandom.setVisibility(4);
            this.mTextRepeat.setVisibility(4);
            for (int i2 = 0; i2 < 6; i2++) {
                this.m_CdSta[i2].Show(false);
            }
            this.mTextAux.setVisibility(0);
        } else if (page == 4) {
            this.mTextPlaySta.setVisibility(4);
            this.mTextRandom.setVisibility(4);
            this.mTextRepeat.setVisibility(4);
            for (int i3 = 0; i3 < 6; i3++) {
                this.m_CdSta[i3].Show(false);
            }
            this.mTextAux.setVisibility(4);
            this.mTextV1.setVisibility(4);
            this.mTextV2.setVisibility(4);
            this.mImgBandDW.Show(0);
            this.mImgMainFreq.Show(false);
        }
    }

    private void ResetData(boolean check) {
        CanJni.LexusIs250GetMedia(this.mCurMedia);
        CanJni.LexusIs250GetAmp(this.mAmpData);
        UpdateMainFreq(check);
        updateAudio(check);
    }

    public void RadioCtrl(int cmd, int para) {
        CanJni.LexusIs250SetTouchKey(cmd, para);
    }

    public void AudioCtrl(int cmd, int para) {
        CanJni.LexusIs250SetAmp(cmd, para);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int sta;
        boolean z;
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            sta = 1;
            this.mlongKeyTick = 6;
            this.mBtn[Id - 1].setTextColor(-256);
        } else if (1 == action) {
            this.mlongKeyTick = 0;
            this.mBtn[Id - 1].setTextColor(-1);
            CanJni.LexusIs250SetTouchKey(0, 0);
            return false;
        } else if (2 != action) {
            return false;
        } else {
            sta = 2;
            this.mBtn[Id - 1].setTextColor(-256);
            if (this.mlongKeyTick > 0) {
                return true;
            }
            this.mlongKeyTick = 6;
        }
        switch (Id) {
            case 1:
                CanJni.LexusIs250SetTouchKey(1, sta);
                return false;
            case 2:
                CanJni.LexusIs250SetTouchKey(5, sta);
                return false;
            case 3:
                CanJni.LexusIs250SetTouchKey(6, sta);
                return false;
            case 4:
                CanJni.LexusIs250SetTouchKey(7, sta);
                return false;
            case 5:
                CanJni.LexusIs250SetTouchKey(8, sta);
                return false;
            case 6:
                CanJni.LexusIs250SetTouchKey(9, sta);
                return false;
            case 7:
                CanJni.LexusIs250SetTouchKey(10, sta);
                return false;
            case 8:
                CanJni.LexusIs250SetTouchKey(15, sta);
                return false;
            case 9:
                CanJni.LexusIs250SetTouchKey(4, sta);
                return false;
            case 10:
                if (this.isAudioShowing) {
                    z = false;
                } else {
                    z = true;
                }
                this.isAudioShowing = z;
                if (this.isAudioShowing) {
                    ShowPage(4);
                    showAudioViews(true);
                    return false;
                }
                showAudioViews(false);
                ShowPage(this.mCurMedia.Mode);
                this.mImgMainFreq.Show(true);
                return false;
            case 11:
                CanJni.LexusIs250SetTouchKey(3, sta);
                return false;
            case 12:
                CanJni.LexusIs250SetTouchKey(12, sta);
                return false;
            case 13:
                CanJni.LexusIs250SetTouchKey(13, sta);
                return false;
            case 14:
                CanJni.LexusIs250SetTouchKey(14, sta);
                return false;
            default:
                return false;
        }
    }

    public static void entLexusIs250Mode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanLexusIs250CarDevActivity.class);
        }
    }

    public static void finishLexusIs250Win() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public void UserAll() {
        ResetData(true);
        if (this.mlongKeyTick > 0) {
            this.mlongKeyTick--;
        }
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            finish();
        }
    }
}
