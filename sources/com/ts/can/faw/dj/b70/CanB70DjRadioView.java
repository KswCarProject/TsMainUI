package com.ts.can.faw.dj.b70;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZPoiSearchManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanB70DjRadioView extends CanRelativeCarInfoView implements View.OnLongClickListener {
    public static final String TAG = "RadioMainActivity";
    public static final int btn_t1 = 16;
    public static final int btn_t2 = 17;
    public static final int btn_t3 = 18;
    public static final int btn_t4 = 19;
    public static final int btn_t5 = 20;
    public static final int btn_t6 = 21;
    private static final int[] mBandNum = {R.drawable.can_radio_band_fm1, R.drawable.can_radio_band_fm2, R.drawable.can_radio_band_fm3, R.drawable.can_radio_band_ot, R.drawable.can_radio_band_am1, R.drawable.can_radio_band_am2};
    /* access modifiers changed from: private */
    public static final int[] mFreqNum = {R.drawable.can_radio_num00_up, R.drawable.can_radio_num01_up, R.drawable.can_radio_num02_up, R.drawable.can_radio_num03_up, R.drawable.can_radio_num04_up, R.drawable.can_radio_num05_up, R.drawable.can_radio_num06_up, R.drawable.can_radio_num07_up, R.drawable.can_radio_num08_up, R.drawable.can_radio_num09_up};
    /* access modifiers changed from: private */
    public int amXStart;
    /* access modifiers changed from: private */
    public int amXdt;
    private ParamButton[] mBtnFreq;
    private ImageView mIvBand;
    private ImageView mIvDW;
    private CustomImgView mIvMainFreq;
    /* access modifiers changed from: private */
    public CanDataInfo.B70_Dj_RadioInfo mRadioInfo;
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

    public CanB70DjRadioView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onLongClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
        return true;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_radio_bg);
        this.mRadioInfo = new CanDataInfo.B70_Dj_RadioInfo();
        initCommonScreen();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    private void initCommonScreen() {
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
        this.mIvBand = addImage(286, 172, 46, 15, R.drawable.can_radio_band_fm);
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
                if (!(CanB70DjRadioView.this.mRadioInfo.UpdateOnce == 0 || CanB70DjRadioView.this.mRadioInfo.Frq == 0)) {
                    Log.d("RadioMainActivity", "userPaint");
                    int curFreq = CanB70DjRadioView.this.mRadioInfo.Frq;
                    if (CanB70DjRadioView.this.mRadioInfo.Band == 17 || CanB70DjRadioView.this.mRadioInfo.Band == 18) {
                        if (curFreq > 999) {
                            view.drawImage(CanB70DjRadioView.mFreqNum[1], CanB70DjRadioView.this.amXStart + (CanB70DjRadioView.this.amXdt * 0), CanB70DjRadioView.this.yMFNum);
                        }
                        view.drawImage(CanB70DjRadioView.mFreqNum[(curFreq % 1000) / 100], CanB70DjRadioView.this.amXStart + (CanB70DjRadioView.this.amXdt * 1), CanB70DjRadioView.this.yMFNum);
                        view.drawImage(CanB70DjRadioView.mFreqNum[(curFreq % 100) / 10], CanB70DjRadioView.this.amXStart + (CanB70DjRadioView.this.amXdt * 2), CanB70DjRadioView.this.yMFNum);
                        view.drawImage(CanB70DjRadioView.mFreqNum[curFreq % 10], CanB70DjRadioView.this.amXStart + (CanB70DjRadioView.this.amXdt * 3), CanB70DjRadioView.this.yMFNum);
                    } else if (CanB70DjRadioView.this.mRadioInfo.Band == 1 || CanB70DjRadioView.this.mRadioInfo.Band == 2 || CanB70DjRadioView.this.mRadioInfo.Band == 3) {
                        if (curFreq > 9999) {
                            view.drawImage(CanB70DjRadioView.mFreqNum[1], CanB70DjRadioView.this.ptMFNums[0], CanB70DjRadioView.this.yMFNum);
                        }
                        view.drawImage(CanB70DjRadioView.mFreqNum[(curFreq % TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT) / 1000], CanB70DjRadioView.this.ptMFNums[1], CanB70DjRadioView.this.yMFNum);
                        view.drawImage(CanB70DjRadioView.mFreqNum[(curFreq % 1000) / 100], CanB70DjRadioView.this.ptMFNums[2], CanB70DjRadioView.this.yMFNum);
                        view.drawImage(R.drawable.can_radio_point_up, CanB70DjRadioView.this.xMFDot, CanB70DjRadioView.this.yMFDot);
                        view.drawImage(CanB70DjRadioView.mFreqNum[(curFreq % 100) / 10], CanB70DjRadioView.this.ptMFNums[3], CanB70DjRadioView.this.yMFNum);
                        view.drawImage(CanB70DjRadioView.mFreqNum[curFreq % 10], CanB70DjRadioView.this.ptMFNums[4], CanB70DjRadioView.this.yMFNum);
                    }
                }
                return false;
            }
        });
    }

    public void ResetData(boolean check) {
        CanJni.B70DjGetRadioInfo(this.mRadioInfo);
        if (!i2b(this.mRadioInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mRadioInfo.Update)) {
            this.mRadioInfo.Update = 0;
            switch (this.mRadioInfo.Band) {
                case 1:
                    this.mIvBand.setImageResource(R.drawable.can_radio_band_fm);
                    this.mIvDW.setImageResource(R.drawable.can_radio_band_mhz);
                    break;
                case 2:
                    this.mIvBand.setImageResource(R.drawable.can_radio_band_fm2);
                    this.mIvDW.setImageResource(R.drawable.can_radio_band_mhz);
                    break;
                case 3:
                    this.mIvBand.setImageResource(R.drawable.can_radio_band_fm3);
                    this.mIvDW.setImageResource(R.drawable.can_radio_band_mhz);
                    break;
                case 17:
                    this.mIvBand.setImageResource(R.drawable.can_radio_band_am1);
                    this.mIvDW.setImageResource(R.drawable.can_radio_band_khz);
                    break;
                case 18:
                    this.mIvBand.setImageResource(R.drawable.can_radio_band_am2);
                    this.mIvDW.setImageResource(R.drawable.can_radio_band_khz);
                    break;
            }
            this.mIvMainFreq.invalidate();
            for (int i = 0; i < this.mBtnFreq.length; i++) {
                if (this.mRadioInfo.Band == 17 || this.mRadioInfo.Band == 18) {
                    this.mBtnFreq[i].setText(new StringBuilder(String.valueOf(this.mRadioInfo.MenFrq[i])).toString());
                } else if (this.mRadioInfo.Band == 1 || this.mRadioInfo.Band == 2 || this.mRadioInfo.Band == 3) {
                    this.mBtnFreq[i].setText(tranalateIntoString(this.mRadioInfo.MenFrq[i]));
                }
            }
            if (this.mRadioInfo.Men > 0 && this.mRadioInfo.Men <= 6) {
                for (ParamButton selected : this.mBtnFreq) {
                    selected.setSelected(false);
                }
                this.mBtnFreq[this.mRadioInfo.Men - 1].setSelected(true);
            }
        }
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
        CanJni.B70DjQueryInfo(1, 0);
    }

    public String tranalateIntoString(int freq) {
        String text = new StringBuilder(String.valueOf(freq)).toString();
        StringBuilder sb = new StringBuilder(text);
        if (text.length() < 3) {
            return TXZResourceManager.STYLE_DEFAULT;
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
