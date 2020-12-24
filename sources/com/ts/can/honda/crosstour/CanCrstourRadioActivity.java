package com.ts.can.honda.crosstour;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.toyota.dj.CanToyotaDJCarDeviceView;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanCrstourRadioActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, View.OnLongClickListener {
    public static final int ITEM_AM = 6;
    public static final int ITEM_BAND = 16;
    public static final int ITEM_FM = 5;
    public static final int ITEM_LIST = 8;
    public static final int ITEM_LIST_MAX = 62;
    public static final int ITEM_LIST_MIN = 32;
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
    public static final String TAG = "CanCrstourRadioActivity";
    public static final int[] mFreqNumArr = {R.drawable.can_yg_radio_num0, R.drawable.can_yg_radio_num1, R.drawable.can_yg_radio_num2, R.drawable.can_yg_radio_num3, R.drawable.can_yg_radio_num4, R.drawable.can_yg_radio_num5, R.drawable.can_yg_radio_num6, R.drawable.can_yg_radio_num7, R.drawable.can_yg_radio_num8, R.drawable.can_yg_radio_num9};
    private ParamButton mBtnAm;
    private ParamButton mBtnBand;
    private ParamButton mBtnFm;
    private ParamButton[] mBtnFreqList;
    private ParamButton[] mBtnFreqPset;
    private ParamButton mBtnList;
    private ParamButton mBtnPreset;
    private ParamButton mBtnScan;
    private ParamButton mBtnSeekDec;
    private ParamButton mBtnSeekInc;
    private ParamButton mBtnStepDec;
    private ParamButton mBtnStepInc;
    private ParamButton mBtnUpdate;
    private int mCurBand = -1;
    /* access modifiers changed from: private */
    public CanDataInfo.AccordRadCurFreq mCurFreqData = new CanDataInfo.AccordRadCurFreq();
    private int mCurPage;
    private CustomImgView mImgBandDW;
    private CustomImgView mImgBandName;
    private CustomImgView mImgFreqBk;
    private CustomImgView mImgMainFreq;
    private CustomImgView mImgST;
    public long mLastTick = 0;
    private CanDataInfo.AccordRadList mListAmData = new CanDataInfo.AccordRadList();
    private CanDataInfo.AccordRadList mListFmData = new CanDataInfo.AccordRadList();
    protected RelativeLayoutManager mManager;
    private CanDataInfo.AccordRadPreset mPsetAmData = new CanDataInfo.AccordRadPreset();
    private CanDataInfo.AccordRadPreset mPsetFmData = new CanDataInfo.AccordRadPreset();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mImgFreqBk = this.mManager.AddImage(138, Can.CAN_MZD_TXB, R.drawable.can_yg_radio_bg);
        this.mImgST = this.mManager.AddImage(490, 56, R.drawable.can_yg_radio_st);
        this.mImgBandName = this.mManager.AddImage(301, 194);
        this.mImgBandName.setStateDrawable(R.drawable.can_yg_radio_fm, R.drawable.can_yg_radio_am);
        this.mImgBandDW = this.mManager.AddImage(688, 194);
        this.mImgBandDW.setStateDrawable(R.drawable.can_yg_radio_mhz, R.drawable.can_yg_radio_khz);
        this.mImgMainFreq = this.mManager.AddImage(386, 125, 263, 97);
        this.mImgMainFreq.setDrawDt(-386, 0);
        this.mImgMainFreq.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                if (!(CanCrstourRadioActivity.this.mCurFreqData.UpdateOnce == 0 || CanCrstourRadioActivity.this.mCurFreqData.CurFreq == 0)) {
                    int Bai = (CanCrstourRadioActivity.this.mCurFreqData.CurFreq / 100) % 10;
                    int Shi = (CanCrstourRadioActivity.this.mCurFreqData.CurFreq / 10) % 10;
                    int Ge = CanCrstourRadioActivity.this.mCurFreqData.CurFreq % 10;
                    if (CanCrstourRadioActivity.this.mCurFreqData.Band != 0) {
                        if (CanCrstourRadioActivity.this.mCurFreqData.CurFreq >= 1000) {
                            view.drawImage(CanCrstourRadioActivity.mFreqNumArr[1], 396, 0);
                        }
                        view.drawImage(CanCrstourRadioActivity.mFreqNumArr[Bai], 444, 0);
                        view.drawImage(CanCrstourRadioActivity.mFreqNumArr[Shi], 492, 0);
                        view.drawImage(CanCrstourRadioActivity.mFreqNumArr[Ge], CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST1, 0);
                    } else {
                        if (CanCrstourRadioActivity.this.mCurFreqData.CurFreq >= 1000) {
                            view.drawImage(CanCrstourRadioActivity.mFreqNumArr[1], 386, 0);
                        }
                        view.drawImage(CanCrstourRadioActivity.mFreqNumArr[Bai], 434, 0);
                        view.drawImage(CanCrstourRadioActivity.mFreqNumArr[Shi], 482, 0);
                        view.drawImage(CanCrstourRadioActivity.mFreqNumArr[Ge], 551, 0);
                        view.drawImage(R.drawable.can_yg_radio_point, 518, 0);
                    }
                }
                return false;
            }
        });
        this.mBtnStepDec = this.mManager.AddButton(168, 135);
        this.mBtnStepDec.setStateUpDn(R.drawable.can_yg_radio_prev_up, R.drawable.can_yg_radio_prev_dn);
        this.mBtnStepDec.setTag(1);
        this.mBtnStepDec.setOnClickListener(this);
        this.mBtnStepInc = this.mManager.AddButton(CanToyotaDJCarDeviceView.ITEM_PLAY, 135);
        this.mBtnStepInc.setStateUpDn(R.drawable.can_yg_radio_down_up, R.drawable.can_yg_radio_down_dn);
        this.mBtnStepInc.setTag(2);
        this.mBtnStepInc.setOnClickListener(this);
        this.mBtnSeekDec = this.mManager.AddButton(52, 135);
        this.mBtnSeekDec.setStateUpDn(R.drawable.can_yg_radio_vup_up, R.drawable.can_yg_radio_vup_dn);
        this.mBtnSeekDec.setTag(3);
        this.mBtnSeekDec.setOnClickListener(this);
        this.mBtnSeekInc = this.mManager.AddButton(861, 135);
        this.mBtnSeekInc.setStateUpDn(R.drawable.can_yg_radio_vdn_up, R.drawable.can_yg_radio_vdn_dn);
        this.mBtnSeekInc.setTag(4);
        this.mBtnSeekInc.setOnClickListener(this);
        this.mBtnBand = this.mManager.AddButton(45, 353);
        this.mBtnBand.setStateUpDn(R.drawable.can_yg_radio_rect02_up, R.drawable.can_yg_radio_rect02_dn);
        this.mBtnBand.setTag(16);
        this.mBtnBand.setOnClickListener(this);
        SetCtrlTxtFmt(this.mBtnBand, 0);
        this.mBtnBand.setText("BAND");
        this.mBtnPreset = this.mManager.AddButton(287, 353);
        this.mBtnPreset.setStateUpDn(R.drawable.can_yg_radio_rect02_up, R.drawable.can_yg_radio_rect02_dn);
        this.mBtnPreset.setTag(7);
        this.mBtnPreset.setOnClickListener(this);
        SetCtrlTxtFmt(this.mBtnPreset, R.string.can_rad_pset);
        this.mBtnList = this.mManager.AddButton(529, 353);
        this.mBtnList.setStateUpDn(R.drawable.can_yg_radio_rect02_up, R.drawable.can_yg_radio_rect02_dn);
        this.mBtnList.setTag(8);
        this.mBtnList.setOnClickListener(this);
        SetCtrlTxtFmt(this.mBtnList, R.string.can_rad_list);
        this.mBtnScan = this.mManager.AddButton(KeyDef.SKEY_POWEWR_3, 353);
        this.mBtnScan.setStateUpDn(R.drawable.can_yg_radio_rect02_up, R.drawable.can_yg_radio_rect02_dn);
        this.mBtnScan.setTag(9);
        this.mBtnScan.setOnClickListener(this);
        SetCtrlTxtFmt(this.mBtnScan, R.string.can_rad_scan);
        this.mBtnFreqPset = new ParamButton[12];
        for (int i = 0; i < this.mBtnFreqPset.length; i++) {
            this.mBtnFreqPset[i] = this.mManager.AddButton(((i % 6) * 165) + 24, ((i / 6) * 89) + 101);
            this.mBtnFreqPset[i].setDrawable(R.drawable.can_yg_radio_rect03_up, R.drawable.can_yg_radio_rect03_dn);
            this.mBtnFreqPset[i].setTag(Integer.valueOf(i + 16));
            this.mBtnFreqPset[i].setOnClickListener(this);
            this.mBtnFreqPset[i].Show(false);
            this.mBtnFreqPset[i].setOnLongClickListener(this);
            SetFreqTxtFmt(this.mBtnFreqPset[i]);
        }
        this.mBtnFreqList = new ParamButton[30];
        for (int i2 = 0; i2 < this.mBtnFreqList.length; i2++) {
            this.mBtnFreqList[i2] = this.mManager.AddButton(((i2 % 6) * 165) + 24, ((i2 / 6) * 89) + 12);
            this.mBtnFreqList[i2].setStateUpDn(R.drawable.can_yg_radio_rect03_up, R.drawable.can_yg_radio_rect03_dn);
            this.mBtnFreqList[i2].setTag(Integer.valueOf(i2 + 32));
            this.mBtnFreqList[i2].setOnClickListener(this);
            this.mBtnFreqList[i2].Show(false);
            SetFreqTxtFmt(this.mBtnFreqList[i2]);
        }
        this.mBtnUpdate = this.mManager.AddButton(348, 454);
        this.mBtnUpdate.setStateUpDn(R.drawable.can_yg_radio_rect04_up, R.drawable.can_yg_radio_rect04_dn);
        this.mBtnUpdate.setTag(10);
        this.mBtnUpdate.setOnClickListener(this);
        SetCtrlTxtFmt(this.mBtnUpdate, R.string.can_rad_update);
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

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        Evc.GetInstance().evol_workmode_set(12);
        ShowPage(0);
        Log.d(TAG, "********************onResume**********************");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void UpdateMainFreq(boolean check) {
        if (this.mCurFreqData.UpdateOnce != 0) {
            if (!check || this.mCurFreqData.Update != 0) {
                Log.d(TAG, "UpdateMainFreq");
                this.mCurFreqData.Update = 0;
                this.mImgBandName.SetSel(this.mCurFreqData.Band);
                this.mImgBandDW.SetSel(this.mCurFreqData.Band);
                this.mImgST.Show(this.mCurFreqData.STSta);
                this.mImgMainFreq.invalidate();
            }
        } else if (!check) {
            CanJni.CrstourQuery(3, 0);
        }
        this.mCurBand = this.mCurFreqData.Band;
    }

    private void UpdatePreset(boolean check) {
        if (!check) {
            CanJni.CrstourQuery(4, 0);
        }
        if (this.mCurFreqData.UpdateOnce != 0) {
            if (this.mCurFreqData.Band == 0) {
                CanJni.AccordGetRadPresetFm(this.mPsetFmData);
                if (this.mPsetFmData.UpdateOnce != 0) {
                    if (!(check && this.mPsetFmData.Update == 0 && this.mCurBand == this.mCurFreqData.Band && this.mCurFreqData.Update == 0)) {
                        this.mCurFreqData.Update = 0;
                        this.mPsetFmData.Update = 0;
                        for (int i = 0; i < 12; i++) {
                            this.mBtnFreqPset[i].Show(true);
                            this.mBtnFreqPset[i].setText(String.format("%.1f", new Object[]{Double.valueOf(((double) this.mPsetFmData.Freq[i]) / 10.0d)}));
                            this.mBtnFreqPset[i].setSelected(false);
                        }
                        if (this.mCurFreqData.MemNo <= 12 && this.mCurFreqData.MemNo > 0) {
                            this.mBtnFreqPset[this.mCurFreqData.MemNo - 1].setSelected(true);
                        }
                    }
                } else if (!check) {
                    for (ParamButton Show : this.mBtnFreqPset) {
                        Show.Show(false);
                    }
                }
            } else {
                CanJni.AccordGetRadPresetAm(this.mPsetAmData);
                if (this.mPsetAmData.UpdateOnce != 0) {
                    if (!(check && this.mPsetAmData.Update == 0 && this.mCurBand == this.mCurFreqData.Band && this.mCurFreqData.Update == 0)) {
                        this.mCurFreqData.Update = 0;
                        this.mPsetAmData.Update = 0;
                        for (int i2 = 0; i2 < 6; i2++) {
                            this.mBtnFreqPset[i2].Show(true);
                            this.mBtnFreqPset[i2].setText(new StringBuilder(String.valueOf(this.mPsetAmData.Freq[i2])).toString());
                            this.mBtnFreqPset[i2].setSelected(false);
                        }
                        if (this.mCurFreqData.MemNo <= 6 && this.mCurFreqData.MemNo > 0) {
                            this.mBtnFreqPset[this.mCurFreqData.MemNo - 1].setSelected(true);
                        }
                        for (int i3 = 6; i3 < 12; i3++) {
                            this.mBtnFreqPset[i3].Show(false);
                        }
                    }
                } else if (!check) {
                    for (ParamButton Show2 : this.mBtnFreqPset) {
                        Show2.Show(false);
                    }
                }
            }
        } else if (!check) {
            CanJni.CrstourQuery(4, 0);
        }
        this.mCurBand = this.mCurFreqData.Band;
    }

    private void UpdateList(boolean check) {
        if (this.mCurFreqData.UpdateOnce != 0) {
            if (this.mCurFreqData.Band == 0) {
                CanJni.AccordGetRadListFm(this.mListFmData);
                if (this.mListFmData.UpdateOnce != 0) {
                    if (!(check && this.mListFmData.Update == 0 && this.mCurBand == this.mCurFreqData.Band)) {
                        for (int i = 0; i < this.mListFmData.Num; i++) {
                            this.mBtnFreqList[i].Show(true);
                            this.mBtnFreqList[i].setText(String.format("%.1f", new Object[]{Double.valueOf(((double) this.mListFmData.Freq[i]) / 10.0d)}));
                        }
                        for (int i2 = this.mListFmData.Num; i2 < 30; i2++) {
                            this.mBtnFreqList[i2].Show(false);
                        }
                    }
                } else if (!check) {
                    for (ParamButton Show : this.mBtnFreqList) {
                        Show.Show(false);
                    }
                }
            } else {
                CanJni.AccordGetRadListAm(this.mListAmData);
                if (this.mListAmData.UpdateOnce != 0) {
                    if (!(check && this.mListAmData.Update == 0 && this.mCurBand == this.mCurFreqData.Band)) {
                        for (int i3 = 0; i3 < this.mListAmData.Num; i3++) {
                            this.mBtnFreqList[i3].Show(true);
                            this.mBtnFreqList[i3].setText(new StringBuilder(String.valueOf(this.mListAmData.Freq[i3])).toString());
                        }
                        for (int i4 = this.mListAmData.Num; i4 < 30; i4++) {
                            this.mBtnFreqList[i4].Show(false);
                        }
                    }
                } else if (!check) {
                    for (int i5 = 0; i5 < this.mBtnFreqPset.length; i5++) {
                        this.mBtnFreqList[i5].Show(false);
                    }
                }
            }
        }
        this.mCurBand = this.mCurFreqData.Band;
    }

    private void ResetData(boolean check) {
        CanJni.AccordGetRadCurFreq(this.mCurFreqData);
        switch (this.mCurPage) {
            case 0:
                UpdateMainFreq(check);
                return;
            case 1:
                UpdatePreset(check);
                return;
            case 2:
                UpdateList(check);
                QueryList();
                return;
            default:
                return;
        }
    }

    public void RadioCtrl(int cmd, int para) {
        CanJni.CrstourRadioCtrl(cmd, para);
    }

    public void QueryList() {
        long curTick = GetTickCount();
        if (curTick > this.mLastTick + 5000) {
            this.mLastTick = curTick;
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 1:
                RadioCtrl(9, 0);
                return;
            case 2:
                RadioCtrl(8, 0);
                return;
            case 3:
                RadioCtrl(5, 0);
                return;
            case 4:
                RadioCtrl(4, 0);
                return;
            case 5:
                RadioCtrl(1, 0);
                return;
            case 6:
                RadioCtrl(1, 0);
                return;
            case 7:
                ShowPage(1);
                return;
            case 8:
                ShowPage(2);
                return;
            case 9:
                RadioCtrl(10, 1);
                return;
            case 10:
                RadioCtrl(12, 1);
                return;
            case 16:
                RadioCtrl(1, 0);
                return;
            default:
                if (id >= 16 && id <= 28) {
                    RadioCtrl(6, (id - 16) + 1);
                    return;
                } else if (id >= 32 && id <= 62) {
                    RadioCtrl(11, (id - 32) + 1);
                    ShowPage(0);
                    return;
                } else {
                    return;
                }
        }
    }

    public boolean onLongClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id < 16 || id > 28) {
            return true;
        }
        RadioCtrl(7, (id - 16) + 1);
        return true;
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
        this.mImgFreqBk.Show(bmain);
        this.mImgMainFreq.Show(bmain);
        this.mImgBandName.Show(bmain);
        this.mImgBandDW.Show(bmain);
        this.mImgST.Show(bmain);
        this.mBtnStepDec.Show(bmain);
        this.mBtnStepInc.Show(bmain);
        this.mBtnSeekDec.Show(bmain);
        this.mBtnSeekInc.Show(bmain);
        this.mBtnBand.Show(bmain);
        this.mBtnPreset.Show(bmain);
        this.mBtnList.Show(bmain);
        this.mBtnScan.Show(bmain);
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

    public void UserAll() {
        ResetData(true);
    }

    public void onBackPressed() {
        switch (this.mCurPage) {
            case 0:
                super.onBackPressed();
                return;
            case 1:
                ShowPage(0);
                return;
            case 2:
                ShowPage(0);
                return;
            default:
                return;
        }
    }
}
