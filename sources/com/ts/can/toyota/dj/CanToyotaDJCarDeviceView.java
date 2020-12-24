package com.ts.can.toyota.dj;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarDeviceActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.dvdplayer.definition.MediaDef;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZPoiSearchManager;
import com.yyw.ts70xhw.KeyDef;
import java.util.ArrayList;

public class CanToyotaDJCarDeviceView extends CanRelativeCarInfoView implements View.OnLongClickListener {
    public static final int ITEM_DISC = 2560;
    public static final int ITEM_FF = 1536;
    public static final int ITEM_FR = 256;
    public static final int ITEM_NEXT = 1280;
    public static final int ITEM_PAUSE = 1024;
    public static final int ITEM_PLAY = 768;
    public static final int ITEM_PREV = 512;
    public static final int ITEM_RDM = 2048;
    public static final int ITEM_RPT = 1792;
    public static final int ITEM_SEARCH = 2304;
    public static final int btn_am = 4;
    public static final int btn_fm = 3;
    public static final int btn_seek_dec = 9;
    public static final int btn_seek_inc = 10;
    public static final int btn_t1 = 16;
    public static final int btn_t2 = 17;
    public static final int btn_t3 = 18;
    public static final int btn_t4 = 19;
    public static final int btn_t5 = 20;
    public static final int btn_t6 = 21;
    private static final int[] mBandNum = {R.drawable.can_radio_band_fm1, R.drawable.can_radio_band_fm2, R.drawable.can_radio_band_fm3, R.drawable.can_radio_band_ot, R.drawable.can_radio_band_am1, R.drawable.can_radio_band_am2};
    /* access modifiers changed from: private */
    public static final int[] mFreqNum = {R.drawable.can_radio_num00_up, R.drawable.can_radio_num01_up, R.drawable.can_radio_num02_up, R.drawable.can_radio_num03_up, R.drawable.can_radio_num04_up, R.drawable.can_radio_num05_up, R.drawable.can_radio_num06_up, R.drawable.can_radio_num07_up, R.drawable.can_radio_num08_up, R.drawable.can_radio_num09_up};
    public static boolean mfgShow = false;
    protected ArrayList<CustomImgView> CDList;
    private ArrayList<CustomTextView> CDTXTList;
    /* access modifiers changed from: private */
    public int amXStart;
    /* access modifiers changed from: private */
    public int amXdt;
    private ParamButton[] mBtnFreq;
    protected ParamButton mBtnRdm;
    protected ParamButton mBtnRpt;
    protected ParamButton mBtnSEARCH;
    protected CustomImgView[] mCDArrays;
    /* access modifiers changed from: private */
    public int mCurrentId;
    /* access modifiers changed from: private */
    public View mCurrentView;
    private CanDataInfo.ToyotaDj_DiscInfo mDiscInfos;
    private RelativeLayoutManager mDiscManager;
    private ImageView mIvBand;
    private ImageView mIvDW;
    private CustomImgView mIvMainFreq;
    private CanDataInfo.ToyotaDj_RadioListInfo mRadioInfos;
    private RelativeLayoutManager mRadioManager;
    private Runnable mSendKey = new Runnable() {
        public void run() {
            CanToyotaDJCarDeviceView.this.SendKey(CanToyotaDJCarDeviceView.this.mCurrentId, 2);
            CanToyotaDJCarDeviceView.this.mCurrentView.postDelayed(this, 100);
        }
    };
    /* access modifiers changed from: private */
    public CanDataInfo.ToyotaDj_SourcrInfo mSourceInfo;
    private CustomTextView mTime;
    private CustomTextView mTrack;
    private TextView mTvAudioOff;
    protected CustomTextView mTxtCD1;
    protected CustomTextView mTxtCD2;
    protected CustomTextView mTxtCD3;
    protected CustomTextView mTxtCD4;
    protected CustomTextView mTxtCD5;
    protected CustomTextView mTxtCD6;
    /* access modifiers changed from: private */
    public int[] ptMFNums;
    /* access modifiers changed from: private */
    public int xMFDot;
    private int xMFDt;
    /* access modifiers changed from: private */
    public int yMFDot;
    private int yMFDt;
    /* access modifiers changed from: private */
    public int yMFNum;

    public CanToyotaDJCarDeviceView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        this.mCurrentId = id;
        this.mCurrentView = v;
        if (action == 0) {
            SendKey(id, 1);
            v.postDelayed(this.mSendKey, 100);
        } else if (1 == action) {
            v.removeCallbacks(this.mSendKey);
            SendKey(id, 0);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void SendKey(int id, int keycode) {
        switch (id) {
            case 3:
                CanJni.ToyotaDjMKeySet(5, keycode);
                return;
            case 4:
                CanJni.ToyotaDjMKeySet(4, keycode);
                return;
            case 9:
                CanJni.ToyotaDjMKeySet(9, keycode);
                return;
            case 10:
                CanJni.ToyotaDjMKeySet(8, keycode);
                return;
            case 256:
                CanJni.ToyotaDjMKeySet(17, keycode);
                return;
            case 512:
                if (keycode != 2) {
                    CdCtrl(15, keycode);
                    return;
                }
                return;
            case ITEM_PLAY /*768*/:
                if (keycode != 2) {
                    CdCtrl(17, keycode);
                    return;
                }
                return;
            case 1024:
                if (keycode != 2) {
                    CdCtrl(16, keycode);
                    return;
                }
                return;
            case 1280:
                if (keycode != 2) {
                    CdCtrl(14, keycode);
                    return;
                }
                return;
            case ITEM_FF /*1536*/:
                CanJni.ToyotaDjMKeySet(16, keycode);
                return;
            case ITEM_RPT /*1792*/:
                if (keycode != 2) {
                    CdCtrl(11, keycode);
                    return;
                }
                return;
            case 2048:
                if (keycode != 2) {
                    CdCtrl(12, keycode);
                    return;
                }
                return;
            case ITEM_SEARCH /*2304*/:
                if (keycode != 2) {
                    CdCtrl(13, keycode);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                if (this.mSourceInfo.Band == 17) {
                    CanJni.ToyotaDjMediaCmd(4, (id - 16) + 1);
                    return;
                } else if (this.mSourceInfo.Band == 1 || this.mSourceInfo.Band == 2) {
                    CanJni.ToyotaDjMediaCmd(5, (id - 16) + 1);
                    return;
                } else {
                    return;
                }
            case ITEM_DISC /*2560*/:
                CdCtrl(6, this.CDList.indexOf(v) + 1);
                return;
            default:
                return;
        }
    }

    private void CdCtrl(int cmd, int part1) {
        CanJni.ToyotaDjMediaCmd(cmd, part1);
    }

    public boolean onLongClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                CanJni.ToyotaDjMediaCmd(3, (id - 16) + 1);
                return true;
            default:
                return true;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mSourceInfo = new CanDataInfo.ToyotaDj_SourcrInfo();
        this.mRadioInfos = new CanDataInfo.ToyotaDj_RadioListInfo();
        this.mDiscInfos = new CanDataInfo.ToyotaDj_DiscInfo();
        getRelativeManager().GetLayout().setBackgroundResource(R.drawable.can_jeep_ycsb_bg02);
        this.mTvAudioOff = addText(0, 0, "Audio System OFF");
        ((RelativeLayout.LayoutParams) this.mTvAudioOff.getLayoutParams()).addRule(13);
        this.mTvAudioOff.setTextColor(-1);
        this.mTvAudioOff.setTextSize(30.0f);
        RelativeLayout radioLayout = new RelativeLayout(getActivity());
        this.mRadioManager = new RelativeLayoutManager(radioLayout);
        getRelativeManager().AddView(radioLayout, 0, 0, -1, -1);
        RelativeLayout discLayout = new RelativeLayout(getActivity());
        this.mDiscManager = new RelativeLayoutManager(discLayout);
        getRelativeManager().AddView(discLayout, 0, 0, -1, -1);
        addRadioViews();
        addDiscViews();
        Show(this.mRadioManager.GetLayout(), false);
        Show(this.mDiscManager.GetLayout(), false);
    }

    private void addRadioViews() {
        this.mRadioManager.GetLayout().setBackgroundResource(R.drawable.can_radio_bg);
        AddBtn(this.mRadioManager, 3, 343, 427, 172, 128, R.drawable.can_radio_button_fm_up, R.drawable.can_radio_button_fm_dn);
        AddBtn(this.mRadioManager, 4, 513, 427, 172, 128, R.drawable.can_radio_button_am_up, R.drawable.can_radio_button_am_dn);
        AddBtn(this.mRadioManager, 9, 148, 102, 83, 82, R.drawable.can_radio_button_vup_up, R.drawable.can_radio_button_vup_dn);
        AddBtn(this.mRadioManager, 10, KeyDef.SKEY_CHUP_1, 102, 83, 82, R.drawable.can_radio_button_vdn_up, R.drawable.can_radio_button_vdn_dn);
        this.mBtnFreq = new ParamButton[6];
        this.mBtnFreq[0] = this.mRadioManager.AddButton(74, Can.CAN_BYD_M6_DJ, Can.CAN_MG_ZS_WC, 74);
        this.mBtnFreq[1] = this.mRadioManager.AddButton(384, Can.CAN_BYD_M6_DJ, Can.CAN_MG_ZS_WC, 74);
        this.mBtnFreq[2] = this.mRadioManager.AddButton(699, Can.CAN_BYD_M6_DJ, Can.CAN_MG_ZS_WC, 74);
        this.mBtnFreq[3] = this.mRadioManager.AddButton(74, KeyDef.RKEY_RDS_TA, Can.CAN_MG_ZS_WC, 74);
        this.mBtnFreq[4] = this.mRadioManager.AddButton(384, KeyDef.RKEY_RDS_TA, Can.CAN_MG_ZS_WC, 74);
        this.mBtnFreq[5] = this.mRadioManager.AddButton(699, KeyDef.RKEY_RDS_TA, Can.CAN_MG_ZS_WC, 74);
        for (int i = 0; i < 6; i++) {
            this.mBtnFreq[i].setTag(Integer.valueOf(i + 16));
            this.mBtnFreq[i].setColor(-1, -1);
            this.mBtnFreq[i].setStateDrawable(R.drawable.can_radio_rect_up, R.drawable.can_radio_rect_dn, R.drawable.can_radio_rect_dn);
            this.mBtnFreq[i].setTextSize(0, 35.0f);
            this.mBtnFreq[i].setPadding(0, 0, 0, 0);
            this.mBtnFreq[i].setOnClickListener(this);
            this.mBtnFreq[i].setOnLongClickListener(this);
        }
        this.mIvBand = AddImage(this.mRadioManager, 286, 172, 46, 15, R.drawable.can_radio_band_fm1);
        this.mIvDW = AddImage(this.mRadioManager, 704, 172, 46, 15, R.drawable.can_radio_band_mhz);
        this.ptMFNums = new int[]{358, 416, 474, CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, 620};
        this.xMFDt = -358;
        this.yMFDt = -79;
        this.yMFNum = 79;
        this.xMFDot = 517;
        this.yMFDot = 79;
        this.amXStart = this.ptMFNums[1] - ((this.ptMFNums[1] - this.ptMFNums[0]) / 2);
        this.amXdt = this.ptMFNums[1] - this.ptMFNums[0];
        this.mIvMainFreq = this.mRadioManager.AddImage(358, 79, KeyDef.RKEY_MEDIA_OSD, 111);
        this.mIvMainFreq.setDrawDt(this.xMFDt, this.yMFDt);
        this.mIvMainFreq.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                if (!(CanToyotaDJCarDeviceView.this.mSourceInfo.UpdateOnce == 0 || CanToyotaDJCarDeviceView.this.mSourceInfo.Frq == 0)) {
                    int curFreq = CanToyotaDJCarDeviceView.this.mSourceInfo.Frq;
                    if (CanToyotaDJCarDeviceView.this.mSourceInfo.Band == 17) {
                        if (curFreq > 999) {
                            view.drawImage(CanToyotaDJCarDeviceView.mFreqNum[1], CanToyotaDJCarDeviceView.this.amXStart + (CanToyotaDJCarDeviceView.this.amXdt * 0), CanToyotaDJCarDeviceView.this.yMFNum);
                        }
                        view.drawImage(CanToyotaDJCarDeviceView.mFreqNum[(curFreq % MediaDef.PROGRESS_MAX) / 100], CanToyotaDJCarDeviceView.this.amXStart + (CanToyotaDJCarDeviceView.this.amXdt * 1), CanToyotaDJCarDeviceView.this.yMFNum);
                        view.drawImage(CanToyotaDJCarDeviceView.mFreqNum[(curFreq % 100) / 10], CanToyotaDJCarDeviceView.this.amXStart + (CanToyotaDJCarDeviceView.this.amXdt * 2), CanToyotaDJCarDeviceView.this.yMFNum);
                        view.drawImage(CanToyotaDJCarDeviceView.mFreqNum[curFreq % 10], CanToyotaDJCarDeviceView.this.amXStart + (CanToyotaDJCarDeviceView.this.amXdt * 3), CanToyotaDJCarDeviceView.this.yMFNum);
                    } else if (CanToyotaDJCarDeviceView.this.mSourceInfo.Band == 1 || CanToyotaDJCarDeviceView.this.mSourceInfo.Band == 2) {
                        int curFreq2 = curFreq * 10;
                        if (curFreq2 > 9999) {
                            view.drawImage(CanToyotaDJCarDeviceView.mFreqNum[1], CanToyotaDJCarDeviceView.this.ptMFNums[0], CanToyotaDJCarDeviceView.this.yMFNum);
                        }
                        view.drawImage(CanToyotaDJCarDeviceView.mFreqNum[(curFreq2 % TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT) / MediaDef.PROGRESS_MAX], CanToyotaDJCarDeviceView.this.ptMFNums[1], CanToyotaDJCarDeviceView.this.yMFNum);
                        view.drawImage(CanToyotaDJCarDeviceView.mFreqNum[(curFreq2 % MediaDef.PROGRESS_MAX) / 100], CanToyotaDJCarDeviceView.this.ptMFNums[2], CanToyotaDJCarDeviceView.this.yMFNum);
                        view.drawImage(R.drawable.can_radio_point_up, CanToyotaDJCarDeviceView.this.xMFDot, CanToyotaDJCarDeviceView.this.yMFDot);
                        view.drawImage(CanToyotaDJCarDeviceView.mFreqNum[(curFreq2 % 100) / 10], CanToyotaDJCarDeviceView.this.ptMFNums[3], CanToyotaDJCarDeviceView.this.yMFNum);
                        view.drawImage(CanToyotaDJCarDeviceView.mFreqNum[curFreq2 % 10], CanToyotaDJCarDeviceView.this.ptMFNums[4], CanToyotaDJCarDeviceView.this.yMFNum);
                    }
                }
                return false;
            }
        });
    }

    private void addDiscViews() {
        this.mDiscManager.GetLayout().setBackgroundResource(R.drawable.can_jeep_ycsb_bg02);
        AddBtn(this.mDiscManager, 256, 123, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_down_up, R.drawable.can_jeep_ycsb_down_dn);
        AddBtn(this.mDiscManager, 512, 257, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        AddBtn(this.mDiscManager, ITEM_PLAY, 391, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        AddBtn(this.mDiscManager, 1024, CanCameraUI.BTN_GEELY_YJX6_GJ, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        AddBtn(this.mDiscManager, 1280, 660, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        AddBtn(this.mDiscManager, ITEM_FF, KeyDef.SKEY_CHUP_1, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_speed_up, R.drawable.can_jeep_ycsb_speed_dn);
        this.mBtnRpt = AddBtn(this.mDiscManager, ITEM_RPT, 46, 25, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(this.mDiscManager, 2048, 180, 25, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        this.mBtnSEARCH = AddBtn(this.mDiscManager, ITEM_SEARCH, KeyDef.RKEY_POWER, 25, R.drawable.can_jeep_ycsb_search_up, R.drawable.can_jeep_ycsb_search_dn);
        this.mTxtCD1 = AddTxtCenter(this.mDiscManager, 604, 200, 81, 30);
        this.mTxtCD2 = AddTxtCenter(this.mDiscManager, 748, 200, 81, 30);
        this.mTxtCD3 = AddTxtCenter(this.mDiscManager, 892, 200, 81, 30);
        this.mTxtCD4 = AddTxtCenter(this.mDiscManager, 604, KeyDef.RKEY_RDS_TA, 81, 30);
        this.mTxtCD5 = AddTxtCenter(this.mDiscManager, 748, KeyDef.RKEY_RDS_TA, 81, 30);
        this.mTxtCD6 = AddTxtCenter(this.mDiscManager, 892, KeyDef.RKEY_RDS_TA, 81, 30);
        this.CDTXTList = new ArrayList<>();
        this.CDTXTList.add(this.mTxtCD1);
        this.CDTXTList.add(this.mTxtCD2);
        this.CDTXTList.add(this.mTxtCD3);
        this.CDTXTList.add(this.mTxtCD4);
        this.CDTXTList.add(this.mTxtCD5);
        this.CDTXTList.add(this.mTxtCD6);
        this.mCDArrays = new CustomImgView[6];
        this.mCDArrays[0] = this.mDiscManager.AddImage(604, 113, R.drawable.can_jeep_ycsb_cd03);
        this.mCDArrays[1] = this.mDiscManager.AddImage(748, 113, R.drawable.can_jeep_ycsb_cd03);
        this.mCDArrays[2] = this.mDiscManager.AddImage(892, 113, R.drawable.can_jeep_ycsb_cd03);
        this.mCDArrays[3] = this.mDiscManager.AddImage(604, Can.CAN_BYD_M6_DJ, R.drawable.can_jeep_ycsb_cd03);
        this.mCDArrays[4] = this.mDiscManager.AddImage(748, Can.CAN_BYD_M6_DJ, R.drawable.can_jeep_ycsb_cd03);
        this.mCDArrays[5] = this.mDiscManager.AddImage(892, Can.CAN_BYD_M6_DJ, R.drawable.can_jeep_ycsb_cd03);
        this.CDList = new ArrayList<>();
        for (CustomImgView img : this.mCDArrays) {
            img.setOnClickListener(this);
            img.setTag(Integer.valueOf(ITEM_DISC));
            this.CDList.add(img);
        }
        this.mTrack = AddTxtLt(this.mDiscManager, 44, 133, 300, 36);
        this.mTime = AddTxtCenter(this.mDiscManager, 362, KeyDef.RKEY_res4, 300, 35);
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
        mfgShow = true;
    }

    public void doOnPause() {
        super.doOnPause();
        mfgShow = false;
    }

    public void ResetData(boolean check) {
        CanJni.ToyotaDjGetSourcrInfo(this.mSourceInfo);
        CanJni.ToyotaDjGetRadioListInfo(this.mRadioInfos);
        CanJni.ToyotaDjGetDiscInfo(this.mDiscInfos);
        if (this.mSourceInfo.Mode == 1) {
            Show(this.mRadioManager.GetLayout(), true);
            Show(this.mDiscManager.GetLayout(), false);
            Show(this.mTvAudioOff, false);
        } else if (this.mSourceInfo.Mode == 2) {
            Show(this.mRadioManager.GetLayout(), false);
            Show(this.mDiscManager.GetLayout(), true);
            Show(this.mTvAudioOff, false);
        } else {
            Show(this.mRadioManager.GetLayout(), false);
            Show(this.mDiscManager.GetLayout(), false);
            Show(this.mTvAudioOff, true);
        }
        if (this.mRadioManager.GetLayout().getVisibility() == 0) {
            if (this.mSourceInfo.Band == 17) {
                this.mIvBand.setImageResource(R.drawable.can_radio_band_am1);
                this.mIvDW.setImageResource(R.drawable.can_radio_band_khz);
                for (int i = 0; i < this.mRadioInfos.AmMen.length; i++) {
                    this.mBtnFreq[i].setText(new StringBuilder(String.valueOf(this.mRadioInfos.AmMen[i])).toString());
                }
            } else if (this.mSourceInfo.Band == 1) {
                this.mIvBand.setImageResource(R.drawable.can_radio_band_fm);
                this.mIvDW.setImageResource(R.drawable.can_radio_band_mhz);
                for (int i2 = 0; i2 < this.mRadioInfos.FmMen.length; i2++) {
                    this.mBtnFreq[i2].setText(tranalateIntoString(this.mRadioInfos.FmMen[i2]));
                }
            } else if (this.mSourceInfo.Band == 2) {
                this.mIvBand.setImageResource(R.drawable.can_radio_band_fm2);
                this.mIvDW.setImageResource(R.drawable.can_radio_band_mhz);
                for (int i3 = 0; i3 < this.mRadioInfos.Fm2Men.length; i3++) {
                    this.mBtnFreq[i3].setText(tranalateIntoString(this.mRadioInfos.Fm2Men[i3]));
                }
            }
            this.mIvMainFreq.invalidate();
            if (this.mSourceInfo.Men > 0 && this.mSourceInfo.Men <= 6) {
                for (ParamButton selected : this.mBtnFreq) {
                    selected.setSelected(false);
                }
                this.mBtnFreq[this.mSourceInfo.Men - 1].setSelected(true);
            }
        }
        if (i2b(this.mSourceInfo.DiscUpdateOnce) && this.mDiscManager.GetLayout().getVisibility() == 0 && (!check || i2b(this.mSourceInfo.DiscUpdate))) {
            this.mSourceInfo.DiscUpdate = 0;
            this.mBtnRpt.SetSel(this.mSourceInfo.Rpt);
            this.mBtnRdm.SetSel(this.mSourceInfo.Rand);
            this.mBtnSEARCH.SetSel(this.mSourceInfo.Scan);
            for (int i4 = 0; i4 < this.CDTXTList.size(); i4++) {
                this.CDTXTList.get(i4).setText("CD" + (i4 + 1));
            }
            int cdNum = this.mSourceInfo.Num;
            if (cdNum <= this.CDList.size() && cdNum > 0) {
                this.CDList.get(cdNum - 1).setImageResource(R.drawable.can_jeep_ycsb_cd02);
                this.CDTXTList.get(cdNum - 1).setText("播放");
            }
            if (this.mSourceInfo.TotalTrack == 0) {
                this.mTrack.setText("Track:" + String.format("%d", new Object[]{Integer.valueOf(this.mSourceInfo.Track)}));
            } else {
                this.mTrack.setText(String.format("%d / %d", new Object[]{Integer.valueOf(this.mSourceInfo.Track), Integer.valueOf(this.mSourceInfo.TotalTrack)}));
            }
            this.mTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mSourceInfo.PlayMin), Integer.valueOf(this.mSourceInfo.PlaySec)}));
        }
        if (!i2b(this.mDiscInfos.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDiscInfos.Update)) {
            this.mDiscInfos.Update = 0;
            for (int i5 = 0; i5 < this.mDiscInfos.Sta.length; i5++) {
                if (this.mDiscInfos.Sta[i5] == 0) {
                    this.CDList.get(i5).setImageResource(R.drawable.can_jeep_ycsb_cd03);
                } else {
                    this.CDList.get(i5).setImageResource(R.drawable.can_jeep_ycsb_cd01);
                }
            }
        }
    }

    public void QueryData() {
        CanJni.ToyotaDjQuery(28, 0);
        Sleep(10);
        CanJni.ToyotaDjQuery(16, 0);
        Sleep(10);
        CanJni.ToyotaDjQuery(18, 0);
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtLt(RelativeLayoutManager manager, int x, int y, int w, int h) {
        CustomTextView temp = manager.AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(19);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtCenter(RelativeLayoutManager manager, int x, int y, int w, int h) {
        CustomTextView temp = manager.AddCusText(x, y, w, h);
        temp.SetPixelSize(26);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(RelativeLayoutManager manager, int id, int x, int y, int up, int dn) {
        ParamButton btn = manager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(RelativeLayoutManager manager, int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = manager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    private CustomImgView AddImage(RelativeLayoutManager manager, int x, int y, int w, int h, int resId) {
        CustomImgView v = manager.AddImage(x, y, w, h);
        if (resId != 0) {
            v.setImageResource(resId);
        }
        return v;
    }

    public String tranalateIntoString(int freq) {
        String text = new StringBuilder(String.valueOf(freq)).toString();
        StringBuilder sb = new StringBuilder(text);
        if (text.length() < 3) {
            return "";
        }
        sb.insert(text.length() - 1, ".");
        return sb.toString();
    }

    public void Show(View view, boolean show) {
        if (show) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    public static void entMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanCarDeviceActivity.class);
        }
    }
}
