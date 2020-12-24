package com.ts.can.honda.wc.crown;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.dvdplayer.definition.MediaDef;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZPoiSearchManager;
import com.yyw.ts70xhw.KeyDef;

public class CanCrownWcRadioView extends CanRelativeCarInfoView implements View.OnLongClickListener {
    public static final String TAG = "RadioMainActivity";
    public static final int btn_af = 13;
    public static final int btn_am = 4;
    public static final int btn_ams = 6;
    public static final int btn_band = 8;
    public static final int btn_close = 22;
    public static final int btn_fm = 3;
    public static final int btn_mode = 7;
    public static final int btn_mute = 1;
    public static final int btn_pty = 15;
    public static final int btn_rds_set = 23;
    public static final int btn_scan = 5;
    public static final int btn_seek_dec = 9;
    public static final int btn_seek_inc = 10;
    public static final int btn_step_dec = 11;
    public static final int btn_step_inc = 12;
    public static final int btn_t1 = 16;
    public static final int btn_t2 = 17;
    public static final int btn_t3 = 18;
    public static final int btn_t4 = 19;
    public static final int btn_t5 = 20;
    public static final int btn_t6 = 21;
    public static final int btn_ta = 14;
    private static final int[] mBandNum = {R.drawable.can_radio_band_fm1, R.drawable.can_radio_band_fm2, R.drawable.can_radio_band_fm3, R.drawable.can_radio_band_ot, R.drawable.can_radio_band_am1, R.drawable.can_radio_band_am2};
    /* access modifiers changed from: private */
    public static final int[] mFreqNum = {R.drawable.can_radio_num00_up, R.drawable.can_radio_num01_up, R.drawable.can_radio_num02_up, R.drawable.can_radio_num03_up, R.drawable.can_radio_num04_up, R.drawable.can_radio_num05_up, R.drawable.can_radio_num06_up, R.drawable.can_radio_num07_up, R.drawable.can_radio_num08_up, R.drawable.can_radio_num09_up};
    /* access modifiers changed from: private */
    public int amXStart;
    /* access modifiers changed from: private */
    public int amXdt;
    private ParamButton mBtnAm;
    private ParamButton mBtnAms;
    private ParamButton mBtnFm;
    private ParamButton[] mBtnFreq;
    private ParamButton mBtnMode;
    private ParamButton mBtnMute;
    private ParamButton mBtnScan;
    private ParamButton mBtnSeekDec;
    private ParamButton mBtnSeekInc;
    private ImageView mIvAMStS;
    private ImageView mIvBand;
    private ImageView mIvDW;
    private CustomImgView mIvMainFreq;
    private ImageView mIvStS;
    private TextView mTvStatus;
    /* access modifiers changed from: private */
    public int[] ptMFNums;
    /* access modifiers changed from: private */
    public CanDataInfo.CrownWcTunerInfo tunerInfo;
    private CanDataInfo.CrownWcTunerPreset tunerPreset;
    /* access modifiers changed from: private */
    public int xMFDot;
    private int xMFDt;
    /* access modifiers changed from: private */
    public int yMFDot;
    private int yMFDt;
    /* access modifiers changed from: private */
    public int yMFNum;

    public CanCrownWcRadioView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 1:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
                return;
            case 3:
                if (!i2b(this.tunerInfo.UpdateOnce)) {
                    return;
                }
                if (this.tunerInfo.Band == 2) {
                    RadioCtrl(3, 2);
                    return;
                } else {
                    RadioCtrl(3, 1);
                    return;
                }
            case 4:
                RadioCtrl(3, 0);
                return;
            case 5:
                RadioCtrl(17, 1);
                return;
            case 6:
                RadioCtrl(19, 1);
                return;
            case 7:
                CanJni.CrownWcSourceSet(1, 4);
                return;
            case 9:
                RadioCtrl(1, 1);
                return;
            case 10:
                RadioCtrl(1, 0);
                return;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                RadioCtrl(8, (id - 16) + 1);
                return;
            default:
                return;
        }
    }

    public boolean onLongClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 5:
                RadioCtrl(18, 1);
                break;
            case 9:
                RadioCtrl(2, 1);
                break;
            case 10:
                RadioCtrl(2, 0);
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                RadioCtrl(4, (id - 16) + 1);
                break;
        }
        return true;
    }

    private void RadioCtrl(int cmd, int para) {
        CanJni.CrownWcTunerSet(cmd, para);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_radio_bg);
        this.tunerInfo = new CanDataInfo.CrownWcTunerInfo();
        this.tunerPreset = new CanDataInfo.CrownWcTunerPreset();
        initCommonScreen();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    private void initCommonScreen() {
        this.mBtnMute = AddBtn(1, 0, 427, 172, 128, R.drawable.can_radio_button_sound_up, R.drawable.can_radio_button_sound_dn);
        this.mBtnScan = AddBtn(5, 172, 427, 172, 128, R.drawable.can_radio_button_horn_up, R.drawable.can_radio_button_horn_dn);
        this.mBtnScan.setOnLongClickListener(this);
        this.mBtnFm = AddBtn(3, 343, 427, 172, 128, R.drawable.can_radio_button_fm_up, R.drawable.can_radio_button_fm_dn);
        this.mBtnAm = AddBtn(4, 513, 427, 172, 128, R.drawable.can_radio_button_am_up, R.drawable.can_radio_button_am_dn);
        this.mBtnAms = AddBtn(6, 683, 427, 172, 128, R.drawable.can_radio_button_store_up, R.drawable.can_radio_button_store_dn);
        this.mBtnMode = AddBtn(7, 854, 427, 172, 128, R.drawable.can_radio_button_mode_up, R.drawable.can_radio_button_mode_dn);
        this.mBtnSeekDec = AddBtn(9, 148, 102, 83, 82, R.drawable.can_radio_button_vup_up, R.drawable.can_radio_button_vup_dn);
        this.mBtnSeekDec.setOnLongClickListener(this);
        this.mBtnSeekInc = AddBtn(10, KeyDef.SKEY_CHUP_1, 102, 83, 82, R.drawable.can_radio_button_vdn_up, R.drawable.can_radio_button_vdn_dn);
        this.mBtnSeekInc.setOnLongClickListener(this);
        this.mBtnFreq = new ParamButton[6];
        this.mBtnFreq[0] = addButton(74, Can.CAN_BYD_M6_DJ, Can.CAN_MG_ZS_WC, 74);
        this.mBtnFreq[1] = addButton(384, Can.CAN_BYD_M6_DJ, Can.CAN_MG_ZS_WC, 74);
        this.mBtnFreq[2] = addButton(699, Can.CAN_BYD_M6_DJ, Can.CAN_MG_ZS_WC, 74);
        this.mBtnFreq[3] = addButton(74, KeyDef.RKEY_RDS_TA, Can.CAN_MG_ZS_WC, 74);
        this.mBtnFreq[4] = addButton(384, KeyDef.RKEY_RDS_TA, Can.CAN_MG_ZS_WC, 74);
        this.mBtnFreq[5] = addButton(699, KeyDef.RKEY_RDS_TA, Can.CAN_MG_ZS_WC, 74);
        for (int i = 0; i < 6; i++) {
            this.mBtnFreq[i].setTag(Integer.valueOf(i + 16));
            this.mBtnFreq[i].setColor(-1, -1);
            this.mBtnFreq[i].setStateDrawable(R.drawable.can_radio_rect_up, R.drawable.can_radio_rect_dn, R.drawable.can_radio_rect_dn);
            this.mBtnFreq[i].setTextSize(0, 35.0f);
            this.mBtnFreq[i].setPadding(0, 0, 0, 0);
            this.mBtnFreq[i].setOnClickListener(this);
            this.mBtnFreq[i].setOnLongClickListener(this);
        }
        this.mIvStS = addImage(450, 32, 45, 20, R.drawable.can_radio_st);
        this.mIvAMStS = addImage(379, 32, 66, 20, R.drawable.can_radio_amst);
        this.mIvStS.setVisibility(4);
        this.mIvAMStS.setVisibility(4);
        this.mIvBand = addImage(286, 172, 46, 15, R.drawable.can_radio_band_fm1);
        this.mIvDW = addImage(704, 172, 46, 15, R.drawable.can_radio_band_mhz);
        this.ptMFNums = new int[]{358, 416, 474, CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, 620};
        this.xMFDt = -358;
        this.yMFDt = -79;
        this.yMFNum = 79;
        this.xMFDot = 517;
        this.yMFDot = 79;
        this.amXStart = this.ptMFNums[1] - ((this.ptMFNums[1] - this.ptMFNums[0]) / 2);
        this.amXdt = this.ptMFNums[1] - this.ptMFNums[0];
        this.mIvMainFreq = getRelativeManager().AddImage(358, 79, KeyDef.RKEY_MEDIA_OSD, 111);
        this.mIvMainFreq.setDrawDt(this.xMFDt, this.yMFDt);
        this.mIvMainFreq.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                if (!(CanCrownWcRadioView.this.tunerInfo.UpdateOnce == 0 || CanCrownWcRadioView.this.tunerInfo.Freq == 0)) {
                    Log.d("RadioMainActivity", "userPaint");
                    int curFreq = CanCrownWcRadioView.this.tunerInfo.Freq;
                    if (CanCrownWcRadioView.this.tunerInfo.Band == 1) {
                        if (curFreq > 999) {
                            view.drawImage(CanCrownWcRadioView.mFreqNum[1], CanCrownWcRadioView.this.amXStart + (CanCrownWcRadioView.this.amXdt * 0), CanCrownWcRadioView.this.yMFNum);
                        }
                        view.drawImage(CanCrownWcRadioView.mFreqNum[(curFreq % MediaDef.PROGRESS_MAX) / 100], CanCrownWcRadioView.this.amXStart + (CanCrownWcRadioView.this.amXdt * 1), CanCrownWcRadioView.this.yMFNum);
                        view.drawImage(CanCrownWcRadioView.mFreqNum[(curFreq % 100) / 10], CanCrownWcRadioView.this.amXStart + (CanCrownWcRadioView.this.amXdt * 2), CanCrownWcRadioView.this.yMFNum);
                        view.drawImage(CanCrownWcRadioView.mFreqNum[curFreq % 10], CanCrownWcRadioView.this.amXStart + (CanCrownWcRadioView.this.amXdt * 3), CanCrownWcRadioView.this.yMFNum);
                    } else if (CanCrownWcRadioView.this.tunerInfo.Band == 2 || CanCrownWcRadioView.this.tunerInfo.Band == 3 || CanCrownWcRadioView.this.tunerInfo.Band == 0) {
                        if (curFreq > 9999) {
                            view.drawImage(CanCrownWcRadioView.mFreqNum[1], CanCrownWcRadioView.this.ptMFNums[0], CanCrownWcRadioView.this.yMFNum);
                        }
                        view.drawImage(CanCrownWcRadioView.mFreqNum[(curFreq % TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT) / MediaDef.PROGRESS_MAX], CanCrownWcRadioView.this.ptMFNums[1], CanCrownWcRadioView.this.yMFNum);
                        view.drawImage(CanCrownWcRadioView.mFreqNum[(curFreq % MediaDef.PROGRESS_MAX) / 100], CanCrownWcRadioView.this.ptMFNums[2], CanCrownWcRadioView.this.yMFNum);
                        view.drawImage(R.drawable.can_radio_point_up, CanCrownWcRadioView.this.xMFDot, CanCrownWcRadioView.this.yMFDot);
                        view.drawImage(CanCrownWcRadioView.mFreqNum[(curFreq % 100) / 10], CanCrownWcRadioView.this.ptMFNums[3], CanCrownWcRadioView.this.yMFNum);
                        view.drawImage(CanCrownWcRadioView.mFreqNum[curFreq % 10], CanCrownWcRadioView.this.ptMFNums[4], CanCrownWcRadioView.this.yMFNum);
                    }
                }
                return false;
            }
        });
        this.mTvStatus = addText(60, 190, 178, 36);
        this.mTvStatus.setTextSize(0, 30.0f);
        this.mTvStatus.setGravity(17);
        this.mTvStatus.setTextColor(-1);
    }

    public void ResetData(boolean check) {
        CanJni.CrownWcGetTunerInfo(this.tunerInfo);
        CanJni.CrownWcGetTunerPreset(this.tunerPreset);
        if (i2b(this.tunerInfo.UpdateOnce) && (!check || i2b(this.tunerInfo.Update))) {
            this.tunerInfo.Update = 0;
            if (this.tunerInfo.Band == 1) {
                this.mIvBand.setImageResource(R.drawable.can_radio_band_am1);
                this.mIvDW.setImageResource(R.drawable.can_radio_band_khz);
                if (i2b(this.tunerPreset.AmUpdateOnce)) {
                    for (int i = 0; i < this.tunerPreset.AmPre.length; i++) {
                        this.mBtnFreq[i].setText(new StringBuilder(String.valueOf(this.tunerPreset.AmPre[i])).toString());
                    }
                }
            } else if (this.tunerInfo.Band == 2) {
                this.mIvBand.setImageResource(R.drawable.can_radio_band_fm1);
                this.mIvDW.setImageResource(R.drawable.can_radio_band_mhz);
                if (i2b(this.tunerPreset.Fm1UpdateOnce)) {
                    for (int i2 = 0; i2 < this.tunerPreset.Fm1Pre.length; i2++) {
                        this.mBtnFreq[i2].setText(tranalateIntoString(this.tunerPreset.Fm1Pre[i2]));
                    }
                }
            } else if (this.tunerInfo.Band == 3) {
                this.mIvBand.setImageResource(R.drawable.can_radio_band_fm2);
                this.mIvDW.setImageResource(R.drawable.can_radio_band_mhz);
                if (i2b(this.tunerPreset.Fm2UpdateOnce)) {
                    for (int i3 = 0; i3 < this.tunerPreset.Fm2Pre.length; i3++) {
                        this.mBtnFreq[i3].setText(tranalateIntoString(this.tunerPreset.Fm2Pre[i3]));
                    }
                }
            } else if (this.tunerInfo.Band == 0) {
                this.mIvBand.setImageResource(R.drawable.can_radio_band_fm);
                this.mIvDW.setImageResource(R.drawable.can_radio_band_mhz);
            }
            Show(this.mIvStS, this.tunerInfo.St);
            Show(this.mIvAMStS, this.tunerInfo.AmSt);
            this.mIvMainFreq.invalidate();
            if (this.tunerInfo.PreSet > 0 && this.tunerInfo.PreSet <= 6) {
                for (ParamButton selected : this.mBtnFreq) {
                    selected.setSelected(false);
                }
                this.mBtnFreq[this.tunerInfo.PreSet - 1].setSelected(true);
            }
            this.mTvStatus.setText(getStatusText(this.tunerInfo.Status));
        }
        if (!i2b(this.tunerPreset.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.tunerPreset.Update)) {
            this.tunerPreset.Update = 0;
            if (i2b(this.tunerPreset.AmUpdateOnce) && (!check || i2b(this.tunerPreset.AmUpdate))) {
                this.tunerPreset.AmUpdate = 0;
                if (this.tunerInfo.Band == 1) {
                    for (int i4 = 0; i4 < this.tunerPreset.AmPre.length; i4++) {
                        this.mBtnFreq[i4].setText(new StringBuilder(String.valueOf(this.tunerPreset.AmPre[i4])).toString());
                    }
                }
            }
            if (i2b(this.tunerPreset.Fm1UpdateOnce) && (!check || i2b(this.tunerPreset.Fm1Update))) {
                this.tunerPreset.Fm1Update = 0;
                if (this.tunerInfo.Band == 2 || this.tunerInfo.Band == 0) {
                    for (int i5 = 0; i5 < this.tunerPreset.Fm1Pre.length; i5++) {
                        this.mBtnFreq[i5].setText(tranalateIntoString(this.tunerPreset.Fm1Pre[i5]));
                    }
                }
            }
            if (!i2b(this.tunerPreset.Fm2UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.tunerPreset.Fm2Update)) {
                this.tunerPreset.Fm2Update = 0;
                if (this.tunerInfo.Band == 3) {
                    for (int i6 = 0; i6 < this.tunerPreset.Fm2Pre.length; i6++) {
                        this.mBtnFreq[i6].setText(tranalateIntoString(this.tunerPreset.Fm2Pre[i6]));
                    }
                }
            }
        }
    }

    private String getStatusText(int status) {
        switch (status) {
            case 1:
                return "SEEK+";
            case 2:
                return "SEEK-";
            case 3:
                return "Auto seek";
            case 4:
                return "Tune+";
            case 5:
                return "Tune-";
            case 6:
                return "SCAN";
            default:
                return "";
        }
    }

    /* access modifiers changed from: protected */
    public void UpdateInfo() {
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtLt(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(19);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtCenter(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void CdCtrl(int cmd, int part1, int part2) {
    }

    public void QueryData() {
        CanJni.CrownWcQuery(133, 255);
        Sleep(10);
        CanJni.CrownWcQuery(132, 255);
    }

    public String tranalateIntoString(int freq) {
        String text = new StringBuilder(String.valueOf(freq)).toString();
        StringBuilder sb = new StringBuilder(text);
        if (text.length() < 3) {
            return "";
        }
        sb.insert(text.length() - 2, ".");
        return sb.toString();
    }

    public void Show(View view, int show) {
        if (show != 0) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }
}
