package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanMzdRzcRadioView extends CanRelativeCarInfoView implements View.OnLongClickListener {
    public static final int ITEM_AM = 6;
    public static final int ITEM_FM = 5;
    public static final int ITEM_LIST = 8;
    public static final int ITEM_LIST_MAX = 62;
    public static final int ITEM_LIST_MIN = 32;
    public static final int ITEM_MODE = 11;
    public static final int ITEM_PSET = 7;
    public static final int ITEM_PSET_MAX = 28;
    public static final int ITEM_PSET_MIN = 16;
    public static final int ITEM_SCAN = 9;
    public static final int ITEM_SEEK_DEC = 3;
    public static final int ITEM_SEEK_INC = 4;
    public static final int ITEM_STEP_DEC = 1;
    public static final int ITEM_STEP_INC = 2;
    public static final int ITEM_UPDATE = 10;
    public static final int PAGE_LIST = 2;
    public static final int PAGE_MAIN = 0;
    public static final int PAGE_PSET = 1;
    public static final String TAG = "CanMzdRzcRadioView";
    public static final int[] mFreqNumArr = {R.drawable.can_yg_radio_num0, R.drawable.can_yg_radio_num1, R.drawable.can_yg_radio_num2, R.drawable.can_yg_radio_num3, R.drawable.can_yg_radio_num4, R.drawable.can_yg_radio_num5, R.drawable.can_yg_radio_num6, R.drawable.can_yg_radio_num7, R.drawable.can_yg_radio_num8, R.drawable.can_yg_radio_num9};
    private ParamButton mBtnAm;
    private ParamButton mBtnFm;
    private ParamButton[] mBtnFreqList;
    private ParamButton[] mBtnFreqPset;
    private ParamButton mBtnList;
    private ParamButton mBtnMode;
    private ParamButton mBtnScan;
    private ParamButton mBtnSeekDec;
    private ParamButton mBtnSeekInc;
    private ParamButton mBtnStepDec;
    private ParamButton mBtnStepInc;
    private ParamButton mBtnUpdate;
    private int mCurBand = -1;
    private int mCurPage;
    private CustomImgView mImgBandDW;
    private CustomImgView mImgBandName;
    private CustomImgView mImgFreqBk;
    private CustomImgView mImgMainFreq;
    protected RelativeLayoutManager mManager;
    /* access modifiers changed from: private */
    public CanDataInfo.Mzd_Rzc_RadioInfo mRadioData;
    private CanDataInfo.Mzd_Rzc_RadioList mRadioListData;
    private String[] mRadioSta;
    private TextView mRadioStaTxt;

    public CanMzdRzcRadioView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 1:
                MzdRzcRadioCtrl(8, 0);
                return;
            case 2:
                MzdRzcRadioCtrl(9, 0);
                return;
            case 3:
                if (this.mRadioData.Sta == 5) {
                    MzdRzcRadioCtrl(5, 0);
                    return;
                } else {
                    MzdRzcRadioCtrl(4, 0);
                    return;
                }
            case 4:
                if (this.mRadioData.Sta == 6) {
                    MzdRzcRadioCtrl(7, 0);
                    return;
                } else {
                    MzdRzcRadioCtrl(6, 0);
                    return;
                }
            case 5:
                CanJni.MzdRzcCarAudioSet(4, 2);
                return;
            case 6:
                CanJni.MzdRzcCarAudioSet(4, 1);
                return;
            case 7:
                ShowPage(1);
                return;
            case 8:
                ShowPage(2);
                CanJni.MzdCx4Query(115, 0);
                return;
            case 9:
                if (this.mRadioData.Sta == 3) {
                    MzdRzcRadioCtrl(3, 0);
                    return;
                } else {
                    MzdRzcRadioCtrl(2, 0);
                    return;
                }
            case 10:
                MzdRzcRadioCtrl(1, 0);
                return;
            case 11:
                CanJni.MzdRzcCarAudioSet(4, 3);
                return;
            default:
                if ((id < 16 || id > 28) && id >= 32 && id <= 62) {
                    if (this.mRadioData.Band == 0) {
                        if (this.mRadioListData.FmFreq[id - 32] != 0) {
                            MzdRzcRadioCtrl(10, this.mRadioListData.FmFreq[id - 32]);
                        }
                    } else if (this.mRadioListData.AmFreq[id - 32] != 0) {
                        MzdRzcRadioCtrl(10, this.mRadioListData.AmFreq[id - 32]);
                    }
                    ShowPage(0);
                    return;
                }
                return;
        }
    }

    public boolean onLongClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
        return true;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        if (MainSet.GetScreenType() == 5) {
            initLayoutParams();
        } else if (mDisplayMetrics.heightPixels == 400 && mDisplayMetrics.widthPixels == 1024) {
            initLayoutParams();
        }
        initBaseUI();
        if (MainSet.GetScreenType() == 5) {
            this.mManager.GetLayout().setScaleX(1.25f);
            this.mManager.GetLayout().setScaleY(0.78f);
        } else if (mDisplayMetrics.heightPixels == 400 && mDisplayMetrics.widthPixels == 1024) {
            this.mManager.GetLayout().setScaleY(0.63f);
        }
    }

    private void initLayoutParams() {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
        lp.width = 1024;
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        lp.gravity = 17;
        this.mManager.GetLayout().setLayoutParams(lp);
    }

    private void initBaseUI() {
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg02);
        this.mRadioData = new CanDataInfo.Mzd_Rzc_RadioInfo();
        this.mRadioListData = new CanDataInfo.Mzd_Rzc_RadioList();
        this.mImgFreqBk = addImage(138, Can.CAN_MZD_TXB, R.drawable.can_yg_radio_bg);
        this.mRadioStaTxt = addText(50, 50, KeyDef.RKEY_RADIO_2S, 30);
        this.mRadioStaTxt.setGravity(19);
        this.mRadioStaTxt.setTextSize(0, 25.0f);
        this.mRadioStaTxt.setTextColor(-1);
        this.mImgBandName = addImage(301, 194);
        this.mImgBandName.setStateDrawable(R.drawable.can_yg_radio_fm, R.drawable.can_yg_radio_am);
        this.mImgBandDW = addImage(688, 194);
        this.mImgBandDW.setStateDrawable(R.drawable.can_yg_radio_mhz, R.drawable.can_yg_radio_khz);
        this.mImgMainFreq = addImage(386, 125, 263, 97);
        this.mImgMainFreq.setDrawDt(-386, 0);
        this.mImgMainFreq.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                if (CanMzdRzcRadioView.this.mRadioData.UpdateOnce == 0 || CanMzdRzcRadioView.this.mRadioData.Freq == 0) {
                    return false;
                }
                if (CanMzdRzcRadioView.this.mRadioData.Band != 0 && CanMzdRzcRadioView.this.mRadioData.Freq > 124) {
                    return false;
                }
                if (CanMzdRzcRadioView.this.mRadioData.Band == 0 && CanMzdRzcRadioView.this.mRadioData.Freq > 411) {
                    return false;
                }
                if (CanMzdRzcRadioView.this.mRadioData.Band != 0) {
                    CanMzdRzcRadioView.this.mRadioData.Freq = ((CanMzdRzcRadioView.this.mRadioData.Freq - 1) * 9) + CanCameraUI.BTN_GEELY_YJX6_MODE3;
                } else {
                    CanMzdRzcRadioView.this.mRadioData.Freq = (int) (((((double) (CanMzdRzcRadioView.this.mRadioData.Freq - 1)) * 0.05d) + 87.5d) * 10.0d);
                }
                int Bai = (CanMzdRzcRadioView.this.mRadioData.Freq / 100) % 10;
                int Shi = (CanMzdRzcRadioView.this.mRadioData.Freq / 10) % 10;
                int Ge = CanMzdRzcRadioView.this.mRadioData.Freq % 10;
                if (CanMzdRzcRadioView.this.mRadioData.Band != 0) {
                    if (CanMzdRzcRadioView.this.mRadioData.Freq >= 1000) {
                        view.drawImage(CanMzdRzcRadioView.mFreqNumArr[1], 396, 0);
                    }
                    view.drawImage(CanMzdRzcRadioView.mFreqNumArr[Bai], 444, 0);
                    view.drawImage(CanMzdRzcRadioView.mFreqNumArr[Shi], 492, 0);
                    view.drawImage(CanMzdRzcRadioView.mFreqNumArr[Ge], 540, 0);
                } else {
                    if (CanMzdRzcRadioView.this.mRadioData.Freq >= 1000) {
                        view.drawImage(CanMzdRzcRadioView.mFreqNumArr[1], 386, 0);
                    }
                    view.drawImage(CanMzdRzcRadioView.mFreqNumArr[Bai], 434, 0);
                    view.drawImage(CanMzdRzcRadioView.mFreqNumArr[Shi], 482, 0);
                    view.drawImage(CanMzdRzcRadioView.mFreqNumArr[Ge], 551, 0);
                    view.drawImage(R.drawable.can_yg_radio_point, 518, 0);
                }
                return false;
            }
        });
        this.mBtnStepDec = addButton(168, 135);
        this.mBtnStepDec.setStateUpDn(R.drawable.can_yg_radio_prev_up, R.drawable.can_yg_radio_prev_dn);
        this.mBtnStepDec.setTag(1);
        this.mBtnStepDec.setOnClickListener(this);
        this.mBtnStepInc = addButton(768, 135);
        this.mBtnStepInc.setStateUpDn(R.drawable.can_yg_radio_down_up, R.drawable.can_yg_radio_down_dn);
        this.mBtnStepInc.setTag(2);
        this.mBtnStepInc.setOnClickListener(this);
        this.mBtnSeekDec = addButton(52, 135);
        this.mBtnSeekDec.setStateUpDn(R.drawable.can_yg_radio_vup_up, R.drawable.can_yg_radio_vup_dn);
        this.mBtnSeekDec.setTag(3);
        this.mBtnSeekDec.setOnClickListener(this);
        this.mBtnSeekInc = addButton(861, 135);
        this.mBtnSeekInc.setStateUpDn(R.drawable.can_yg_radio_vdn_up, R.drawable.can_yg_radio_vdn_dn);
        this.mBtnSeekInc.setTag(4);
        this.mBtnSeekInc.setOnClickListener(this);
        this.mBtnFm = addButton(71, 353);
        this.mBtnFm.setStateUpDn(R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnFm.setTag(5);
        this.mBtnFm.setOnClickListener(this);
        SetCtrlTxtFmt(this.mBtnFm, 0);
        this.mBtnFm.setText("FM");
        this.mBtnAm = addButton(Can.CAN_MG_ZS_WC, 353);
        this.mBtnAm.setStateUpDn(R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnAm.setTag(6);
        this.mBtnAm.setOnClickListener(this);
        SetCtrlTxtFmt(this.mBtnAm, 0);
        this.mBtnAm.setText("AM");
        this.mBtnList = addButton(KeyDef.SKEY_SEEKDN_3, 353);
        this.mBtnList.setStateUpDn(R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnList.setTag(8);
        this.mBtnList.setOnClickListener(this);
        SetCtrlTxtFmt(this.mBtnList, R.string.can_rad_list);
        this.mBtnScan = addButton(CanCameraUI.BTN_CCH9_MODE2, 353);
        this.mBtnScan.setStateUpDn(R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnScan.setTag(9);
        this.mBtnScan.setOnClickListener(this);
        SetCtrlTxtFmt(this.mBtnScan, R.string.can_rad_scan);
        this.mBtnMode = addButton(431, 353);
        this.mBtnMode.setStateUpDn(R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnMode.setTag(11);
        this.mBtnMode.setOnClickListener(this);
        SetCtrlTxtFmt(this.mBtnMode, 0);
        this.mBtnMode.setText("MODE");
        this.mBtnFreqPset = new ParamButton[12];
        for (int i = 0; i < this.mBtnFreqPset.length; i++) {
            this.mBtnFreqPset[i] = addButton(((i % 6) * 165) + 24, ((i / 6) * 89) + 101);
            this.mBtnFreqPset[i].setDrawable(R.drawable.can_yg_radio_rect03_up, R.drawable.can_yg_radio_rect03_dn);
            this.mBtnFreqPset[i].setTag(Integer.valueOf(i + 16));
            this.mBtnFreqPset[i].setOnClickListener(this);
            this.mBtnFreqPset[i].Show(false);
            this.mBtnFreqPset[i].setOnLongClickListener(this);
            SetFreqTxtFmt(this.mBtnFreqPset[i]);
        }
        this.mBtnFreqList = new ParamButton[30];
        for (int i2 = 0; i2 < this.mBtnFreqList.length; i2++) {
            this.mBtnFreqList[i2] = addButton(((i2 % 6) * 165) + 24, ((i2 / 6) * 89) + 12);
            this.mBtnFreqList[i2].setStateUpDn(R.drawable.can_yg_radio_rect03_up, R.drawable.can_yg_radio_rect03_dn);
            this.mBtnFreqList[i2].setTag(Integer.valueOf(i2 + 32));
            this.mBtnFreqList[i2].setOnClickListener(this);
            this.mBtnFreqList[i2].Show(false);
            SetFreqTxtFmt(this.mBtnFreqList[i2]);
        }
        this.mBtnUpdate = addButton(348, 454);
        this.mBtnUpdate.setStateUpDn(R.drawable.can_yg_radio_rect04_up, R.drawable.can_yg_radio_rect04_dn);
        this.mBtnUpdate.setTag(10);
        this.mBtnUpdate.setOnClickListener(this);
        SetCtrlTxtFmt(this.mBtnUpdate, R.string.can_rad_update);
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
        ShowPage(0);
    }

    public boolean setPage() {
        switch (this.mCurPage) {
            case 1:
                ShowPage(0);
                return true;
            case 2:
                ShowPage(0);
                return true;
            default:
                return false;
        }
    }

    private void ShowPage(int page) {
        boolean bmain;
        boolean bpset;
        boolean blist = true;
        if (page == 0) {
            bmain = true;
        } else {
            bmain = false;
        }
        if (page == 1) {
            bpset = true;
        } else {
            bpset = false;
        }
        if (page != 2) {
            blist = false;
        }
        this.mRadioStaTxt.setVisibility(bmain ? 0 : 4);
        this.mImgFreqBk.Show(bmain);
        this.mImgMainFreq.Show(bmain);
        this.mImgBandName.Show(bmain);
        this.mImgBandDW.Show(bmain);
        this.mBtnStepDec.Show(bmain);
        this.mBtnStepInc.Show(bmain);
        this.mBtnSeekDec.Show(bmain);
        this.mBtnSeekInc.Show(bmain);
        this.mBtnFm.Show(bmain);
        this.mBtnAm.Show(bmain);
        this.mBtnList.Show(bmain);
        this.mBtnScan.Show(bmain);
        this.mBtnMode.Show(bmain);
        for (ParamButton Show : this.mBtnFreqPset) {
            Show.Show(bpset);
        }
        for (ParamButton Show2 : this.mBtnFreqList) {
            Show2.Show(blist);
        }
        this.mBtnUpdate.Show(blist);
        this.mCurPage = page;
        ResetData(false);
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarRadioInfo(this.mRadioData);
        CanJni.MzdRzcGetCarRadioList(this.mRadioListData);
        switch (this.mCurPage) {
            case 0:
                UpdateMainFreq(check);
                return;
            case 1:
                UpdatePreset(check);
                return;
            case 2:
                UpdateList(check);
                return;
            default:
                return;
        }
    }

    public void MzdRzcRadioCtrl(int cmd, int para) {
        CanJni.MzdRzcCarRadioCmd(cmd, para);
    }

    public void QueryData() {
    }

    private void SetCtrlTxtFmt(ParamButton btn, int resId) {
        btn.setTextSize(0, 36.0f);
        btn.setTextColor(-1);
        if (resId != 0) {
            btn.setText(resId);
        }
        btn.setPadding(0, 3, 0, 0);
    }

    private void SetFreqTxtFmt(ParamButton btn) {
        btn.setTextSize(0, 40.0f);
        btn.setPadding(0, 3, 0, 0);
        btn.setTextColor(-1);
    }

    private void UpdateMainFreq(boolean check) {
        if (this.mRadioData.UpdateOnce != 0 && (!check || this.mRadioData.Update != 0)) {
            this.mRadioData.Update = 0;
            this.mImgBandName.SetSel(this.mRadioData.Band);
            this.mImgBandDW.SetSel(this.mRadioData.Band);
            this.mImgMainFreq.invalidate();
            this.mRadioSta = getStringArray(R.array.can_mzd_car_radio_sta);
            if (this.mRadioData.Sta < this.mRadioSta.length) {
            }
            this.mRadioStaTxt.setText(this.mRadioSta[this.mRadioData.Sta]);
        }
        this.mCurBand = this.mRadioData.Band;
    }

    private void UpdatePreset(boolean check) {
    }

    private void UpdateList(boolean check) {
        if (this.mRadioData.UpdateOnce != 0) {
            if (this.mRadioData.Band == 0) {
                if (this.mRadioListData.UpdateOnce != 0) {
                    if (!(check && this.mRadioListData.Update == 0 && this.mCurBand == this.mRadioData.Band)) {
                        for (int i = 0; i < this.mRadioListData.FmFreq.length; i++) {
                            this.mBtnFreqList[i].Show(true);
                            if (this.mRadioListData.FmFreq[i] == 0) {
                                this.mBtnFreqList[i].setText("--");
                            } else {
                                this.mBtnFreqList[i].setText(String.format("%.1f", new Object[]{Double.valueOf((((double) (this.mRadioListData.FmFreq[i] - 1)) * 0.05d) + 87.5d)}));
                            }
                        }
                        for (int i2 = this.mRadioListData.FmFreq.length; i2 < 30; i2++) {
                            this.mBtnFreqList[i2].Show(false);
                        }
                    }
                } else if (!check) {
                    for (ParamButton Show : this.mBtnFreqList) {
                        Show.Show(false);
                    }
                }
            } else if (this.mRadioListData.UpdateOnce != 0) {
                if (!(check && this.mRadioListData.Update == 0 && this.mCurBand == this.mRadioData.Band)) {
                    for (int i3 = 0; i3 < this.mRadioListData.AmFreq.length; i3++) {
                        this.mBtnFreqList[i3].Show(true);
                        if (this.mRadioListData.AmFreq[i3] == 0) {
                            this.mBtnFreqList[i3].setText("--");
                        } else {
                            this.mBtnFreqList[i3].setText(new StringBuilder(String.valueOf(((this.mRadioListData.AmFreq[i3] - 1) * 9) + CanCameraUI.BTN_GEELY_YJX6_MODE3)).toString());
                        }
                    }
                    for (int i4 = this.mRadioListData.AmFreq.length; i4 < 30; i4++) {
                        this.mBtnFreqList[i4].Show(false);
                    }
                }
            } else if (!check) {
                for (int i5 = 0; i5 < this.mBtnFreqPset.length; i5++) {
                    this.mBtnFreqList[i5].Show(false);
                }
            }
        } else if (!check) {
            CanJni.MzdCx4Query(114, 0);
        }
        this.mCurBand = this.mRadioData.Band;
    }
}
