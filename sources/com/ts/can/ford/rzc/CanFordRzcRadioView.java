package com.ts.can.ford.rzc;

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
import com.ts.can.CanBaseCarDeviceActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZPoiSearchManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;
import java.io.UnsupportedEncodingException;

public class CanFordRzcRadioView extends CanRelativeCarInfoView implements View.OnLongClickListener {
    public static final String TAG = "RadioMainActivity";
    private static final int[] mBandNum = {R.drawable.can_radio_band_fm1, R.drawable.can_radio_band_fm2, R.drawable.can_radio_band_fm3, R.drawable.can_radio_band_ot, R.drawable.can_radio_band_am1, R.drawable.can_radio_band_am2};
    /* access modifiers changed from: private */
    public static final int[] mFreqNum = {R.drawable.can_radio_num00_up, R.drawable.can_radio_num01_up, R.drawable.can_radio_num02_up, R.drawable.can_radio_num03_up, R.drawable.can_radio_num04_up, R.drawable.can_radio_num05_up, R.drawable.can_radio_num06_up, R.drawable.can_radio_num07_up, R.drawable.can_radio_num08_up, R.drawable.can_radio_num09_up};
    /* access modifiers changed from: private */
    public int amXStart;
    /* access modifiers changed from: private */
    public int amXdt;
    private ParamButton[] mBtnFreq;
    /* access modifiers changed from: private */
    public CanDataInfo.FordRzcHostInfo mHostInfo;
    private ImageView mIvBand;
    private ImageView mIvDW;
    private CustomImgView mIvMainFreq;
    private CanDataInfo.FordRzcHostListText mRadioList;
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

    public CanFordRzcRadioView(Activity activity) {
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
        this.mHostInfo = new CanDataInfo.FordRzcHostInfo();
        this.mRadioList = new CanDataInfo.FordRzcHostListText();
        initCommonScreen();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void doOnPause() {
        super.doOnPause();
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
            this.mBtnFreq[i].setTag(Integer.valueOf(i));
            this.mBtnFreq[i].setColor(-1, -1);
            this.mBtnFreq[i].setStateDrawable(R.drawable.can_radio_rect_up, R.drawable.can_radio_rect_dn, R.drawable.can_radio_rect_dn);
            this.mBtnFreq[i].setTextSize(0, 35.0f);
            this.mBtnFreq[i].setPadding(0, 0, 0, 0);
            this.mBtnFreq[i].setOnClickListener(this);
            this.mBtnFreq[i].setOnLongClickListener(this);
        }
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
                if (!(CanFordRzcRadioView.this.mHostInfo.UpdateOnce == 0 || CanFordRzcRadioView.this.mHostInfo.Freq == 0)) {
                    Log.d("RadioMainActivity", "userPaint");
                    int curFreq = CanFordRzcRadioView.this.mHostInfo.Freq;
                    if (CanFordRzcRadioView.this.mHostInfo.Band == 16 || CanFordRzcRadioView.this.mHostInfo.Band == 17) {
                        if (curFreq > 999) {
                            view.drawImage(CanFordRzcRadioView.mFreqNum[1], CanFordRzcRadioView.this.amXStart + (CanFordRzcRadioView.this.amXdt * 0), CanFordRzcRadioView.this.yMFNum);
                        }
                        view.drawImage(CanFordRzcRadioView.mFreqNum[(curFreq % 1000) / 100], CanFordRzcRadioView.this.amXStart + (CanFordRzcRadioView.this.amXdt * 1), CanFordRzcRadioView.this.yMFNum);
                        view.drawImage(CanFordRzcRadioView.mFreqNum[(curFreq % 100) / 10], CanFordRzcRadioView.this.amXStart + (CanFordRzcRadioView.this.amXdt * 2), CanFordRzcRadioView.this.yMFNum);
                        view.drawImage(CanFordRzcRadioView.mFreqNum[curFreq % 10], CanFordRzcRadioView.this.amXStart + (CanFordRzcRadioView.this.amXdt * 3), CanFordRzcRadioView.this.yMFNum);
                    } else if (CanFordRzcRadioView.this.mHostInfo.Band == 1 || CanFordRzcRadioView.this.mHostInfo.Band == 2 || CanFordRzcRadioView.this.mHostInfo.Band == 3) {
                        if (curFreq > 9999) {
                            view.drawImage(CanFordRzcRadioView.mFreqNum[1], CanFordRzcRadioView.this.ptMFNums[0], CanFordRzcRadioView.this.yMFNum);
                        }
                        view.drawImage(CanFordRzcRadioView.mFreqNum[(curFreq % TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT) / 1000], CanFordRzcRadioView.this.ptMFNums[1], CanFordRzcRadioView.this.yMFNum);
                        view.drawImage(CanFordRzcRadioView.mFreqNum[(curFreq % 1000) / 100], CanFordRzcRadioView.this.ptMFNums[2], CanFordRzcRadioView.this.yMFNum);
                        view.drawImage(R.drawable.can_radio_point_up, CanFordRzcRadioView.this.xMFDot, CanFordRzcRadioView.this.yMFDot);
                        view.drawImage(CanFordRzcRadioView.mFreqNum[(curFreq % 100) / 10], CanFordRzcRadioView.this.ptMFNums[3], CanFordRzcRadioView.this.yMFNum);
                        view.drawImage(CanFordRzcRadioView.mFreqNum[curFreq % 10], CanFordRzcRadioView.this.ptMFNums[4], CanFordRzcRadioView.this.yMFNum);
                    }
                }
                return false;
            }
        });
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetHostInfo(this.mHostInfo);
        for (int i = 0; i < this.mBtnFreq.length; i++) {
            CanJni.FordRzcGetRadioListInfo(this.mRadioList, i);
            if (i2b(this.mRadioList.UpdateOnce) && (!check || i2b(this.mRadioList.Update))) {
                this.mRadioList.Update = 0;
                this.mBtnFreq[i].setText(byte2String(this.mRadioList.Type, this.mRadioList.Text, this.mRadioList.Text.length));
            }
        }
        if (i2b(this.mHostInfo.RadioUpdateOnce)) {
            if (i2b(this.mHostInfo.Update)) {
                this.mHostInfo.Update = 0;
                if (this.mHostInfo.Src == 2) {
                    enterSubWin(CanBaseCarDeviceActivity.class, -2);
                }
            }
            if (!check || i2b(this.mHostInfo.RadioUpdate)) {
                this.mHostInfo.RadioUpdate = 0;
                if (this.mHostInfo.Band == 16) {
                    this.mIvBand.setImageResource(R.drawable.can_radio_band_am1);
                    this.mIvDW.setImageResource(R.drawable.can_radio_band_khz);
                } else if (this.mHostInfo.Band == 17) {
                    this.mIvBand.setImageResource(R.drawable.can_radio_band_am2);
                    this.mIvDW.setImageResource(R.drawable.can_radio_band_khz);
                } else if (this.mHostInfo.Band == 1) {
                    this.mIvBand.setImageResource(R.drawable.can_radio_band_fm1);
                    this.mIvDW.setImageResource(R.drawable.can_radio_band_mhz);
                } else if (this.mHostInfo.Band == 2) {
                    this.mIvBand.setImageResource(R.drawable.can_radio_band_fm2);
                    this.mIvDW.setImageResource(R.drawable.can_radio_band_mhz);
                } else if (this.mHostInfo.Band == 3) {
                    this.mIvBand.setImageResource(R.drawable.can_radio_band_fm3);
                    this.mIvDW.setImageResource(R.drawable.can_radio_band_mhz);
                }
                this.mIvMainFreq.invalidate();
                if (this.mHostInfo.Men > 0 && this.mHostInfo.Men <= 6) {
                    for (ParamButton selected : this.mBtnFreq) {
                        selected.setSelected(false);
                    }
                    this.mBtnFreq[this.mHostInfo.Men - 1].setSelected(true);
                } else if (this.mHostInfo.Men == 31) {
                    for (ParamButton selected2 : this.mBtnFreq) {
                        selected2.setSelected(false);
                    }
                }
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

    public void QueryData() {
        CanJni.FordQuery(101, 0);
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

    private String byte2ASCIIString(byte[] data, int len) {
        try {
            return new String(data, 0, len, "GBK");
        } catch (UnsupportedEncodingException e) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    private String byte2String(int encode, byte[] data, int len) {
        if (encode == 0) {
            return CanIF.byte2UnicodeStr(data);
        }
        return byte2ASCIIString(data, len);
    }
}
