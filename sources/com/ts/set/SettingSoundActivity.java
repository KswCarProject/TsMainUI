package com.ts.set;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.android.SdkConstants;
import com.lgb.canmodule.Can;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.ts.set.setview.SetItemVerticalSeekBar;
import com.ts.set.setview.SettingBalanceView;
import com.ts.set.setview.SettingNumInuptDlg;
import com.ts.set.setview.SettingSeekBar;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingSoundActivity extends Activity implements UserCallBack, View.OnClickListener, SettingSeekBar.onTouchPosChanged, CompoundButton.OnCheckedChangeListener, SettingBalanceView.onTouchBalanceChanged {
    private static final int SB_ID_BASE = 1000;
    private static final String TAG = "SettingSoundActivity";
    public static final int id_balance = 1290;
    public static final int id_class = 1285;
    public static final int id_eq = 1291;
    public static final int id_flate = 1281;
    public static final int id_jazz = 1284;
    public static final int id_pop = 1282;
    public static final int id_rock = 1283;
    public static final int id_user = 1280;
    SettingNumInuptDlg ExAmpDialog;
    private int mBal;
    private int mBalOld;
    private RelativeLayout mBalanceLayout;
    private int mBkBot;
    private int mBkRt;
    private int mBkX;
    private int mBkY;
    private Button mBtnBalance;
    private Button mBtnCenter;
    private Button mBtnDn;
    private ParamButton[] mBtnEQ = new ParamButton[6];
    private Button mBtnLt;
    private Button mBtnRt;
    private Button mBtnSoundEQ;
    private Button mBtnUp;
    private int[] mEQAdjVal = new int[10];
    private RelativeLayout mEQLayout;
    private SetItemVerticalSeekBar[] mEQSb;
    private int[] mEQStrId = {R.string.set_sound_user, R.string.set_sound_flate, R.string.set_sound_pop, R.string.set_sound_rock, R.string.set_sound_jazz, R.string.set_sound_class};
    private int[] mEQTitleId = {R.string.set_sound_64hz, R.string.set_sound_128hz, R.string.set_sound_256hz, R.string.set_sound_512hz, R.string.set_sound_1khz, R.string.set_sound_2khz, R.string.set_sound_8khz, R.string.set_sound_12khz, R.string.set_sound_15khz, R.string.set_sound_16khz};
    /* access modifiers changed from: private */
    public int[] mEQVal = new int[10];
    private Evc mEvc = Evc.GetInstance();
    private int mFad;
    private int mFadOld;
    private SettingBalanceView mFbView;
    private CustomImgView mIvEQ;
    private int mLastX;
    private int mLastY;
    private RelativeLayoutManager mManager;
    private int mOldEqMode = -1;
    private int mOldLoud = -1;
    /* access modifiers changed from: private */
    public List<Point> mPtList = new ArrayList();
    /* access modifiers changed from: private */
    public boolean mSbDrag = false;
    /* access modifiers changed from: private */
    public Switch mSwExamp;
    private Switch mSwLoud;
    private SeekBar.OnSeekBarChangeListener sbChangeLsn = new SeekBar.OnSeekBarChangeListener() {
        public void onStopTrackingTouch(SeekBar seekBar) {
            SettingSoundActivity.this.mSbDrag = false;
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            SettingSoundActivity.this.mSbDrag = true;
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            SeekBar curSb = seekBar;
            Log.e("Drag", "Drag = " + SettingSoundActivity.this.mSbDrag + ", fromUser =" + fromUser + ", progress = " + curSb.getId() + ", " + curSb.getProgress());
            SettingSoundActivity.this.updateEQVal();
            int nNawBand = curSb.getId() + Can.OUT_TMP_INVALID_VAL;
            if (SettingSoundActivity.this.mEQVal[curSb.getId() + Can.OUT_TMP_INVALID_VAL] != curSb.getProgress()) {
                SettingSoundActivity.this.mEQVal[curSb.getId() + Can.OUT_TMP_INVALID_VAL] = curSb.getProgress();
                Log.e("Drag", "progress = " + curSb.getId() + ", " + curSb.getProgress());
                Iop.SetEqBand(nNawBand, curSb.getProgress());
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int dspState = Iop.DspSupport();
        Log.d("lh", "dspSupport = " + dspState);
        if (dspState == 1) {
            try {
                ComponentName componetName = new ComponentName("com.ts.MainUI", "com.ts.set.dsp.SetDspMainActivity");
                Intent intent = new Intent();
                intent.setComponent(componetName);
                intent.addFlags(337641472);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                Log.d("set", "settting exception = " + e.toString());
            }
        } else {
            if (dspState == -1) {
                finish();
                return;
            }
            setContentView(R.layout.set_relative_layout);
            InitUI();
            InitBalanceUI();
            this.mBtnBalance = (Button) findViewById(R.id.btn_balance);
            this.mBtnBalance.setId(id_balance);
            this.mBtnBalance.setOnClickListener(this);
            this.mBtnSoundEQ = (Button) findViewById(R.id.btn_eq);
            this.mBtnSoundEQ.setId(id_eq);
            this.mBtnSoundEQ.setOnClickListener(this);
        }
    }

    private void InitBalanceUI() {
        this.mBalanceLayout = (RelativeLayout) findViewById(R.id.layout_set_balance);
        this.mBtnCenter = (Button) findViewById(R.id.set_balance_btn_center);
        this.mBtnUp = (Button) findViewById(R.id.setting_balance_btn_up);
        this.mBtnDn = (Button) findViewById(R.id.setting_balance_btn_down);
        this.mBtnLt = (Button) findViewById(R.id.setting_balance_btn_left);
        this.mBtnRt = (Button) findViewById(R.id.setting_balance_btn_right);
        this.mSwExamp = (Switch) findViewById(R.id.switch_ctrl2);
        this.mSwExamp.setOnCheckedChangeListener(this);
        this.mSwLoud = (Switch) findViewById(R.id.switch_ctrl);
        this.mSwLoud.setOnCheckedChangeListener(this);
        ((TextView) findViewById(R.id.switch_value)).setText(R.string.set_balance_loud);
        TextView tv2 = (TextView) findViewById(R.id.switch_value2);
        tv2.setText(R.string.set_balance_examp);
        if (MainSet.GetInstance().IsXuhuiDmax()) {
            tv2.setVisibility(4);
        }
        if (getResources().getIdentifier("antenna_power", SdkConstants.TAG_STRING, getPackageName()) != 0 || getResources().getString(R.string.custom_ver_).equals("RU01")) {
            this.mSwExamp.setVisibility(8);
            tv2.setVisibility(8);
        }
        this.mFbView = (SettingBalanceView) findViewById(R.id.setting_balance_img_seat);
        this.mFbView.setBalanceChangedListener(this);
        this.mBtnUp.setOnClickListener(this);
        this.mBtnDn.setOnClickListener(this);
        this.mBtnLt.setOnClickListener(this);
        this.mBtnRt.setOnClickListener(this);
        this.mBtnCenter.setOnClickListener(this);
        this.mBkX = ((int) getResources().getDimension(R.dimen.x_setting_balance_seat)) + 3;
        this.mBkY = ((int) getResources().getDimension(R.dimen.y_setting_balance_seat)) + 2;
        this.mBkRt = this.mBkX + Can.CAN_TOYOTA_SP_XP;
        this.mBkBot = this.mBkY + Can.CAN_TOYOTA_SP_XP;
    }

    private void ResetData() {
        int mode = Iop.GetEqm();
        Log.d("hdd", "ResetData mode = " + mode);
        updateEQVal();
        if (mode < 6) {
            for (int i = 0; i < 6; i++) {
                this.mBtnEQ[i].setSelected(false);
                this.mBtnEQ[i].setTextColor(-1);
            }
            this.mBtnEQ[mode].setSelected(true);
            this.mBtnEQ[mode].setTextColor(Color.parseColor("#F58840"));
        }
        for (int i2 = 0; i2 < this.mEQSb.length; i2++) {
            Log.d("hdd", "i  = " + i2 + "---mEQVal = " + this.mEQVal[i2]);
            this.mEQSb[i2].GetSeekBar().setCurPos(this.mEQVal[i2]);
            this.mEQSb[i2].SetVal(new StringBuilder(String.valueOf(this.mEQVal[i2] - 9)).toString());
        }
        this.mIvEQ.invalidate();
        this.mOldEqMode = Iop.GetEqm();
        this.mOldLoud = Iop.GetLud();
        SetCheck(this.mSwLoud, Iop.GetLud());
        SetCheck(this.mSwExamp, FtSet.GetExAmp());
        resetFb();
    }

    /* access modifiers changed from: package-private */
    public void resetFb() {
        this.mFad = Iop.GetFad();
        this.mBal = Iop.GetBal();
        this.mFbView.setBalance(this.mFad, this.mBal);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData();
        if (MainSet.GetInstance().IsXuhuiDmax()) {
            this.mSwExamp.setVisibility(4);
        } else {
            SetCheck(this.mSwExamp, FtSet.GetExAmp());
        }
        SetCheck(this.mSwLoud, Iop.GetLud());
        this.mBalanceLayout.setVisibility(8);
        this.mEQLayout.setVisibility(0);
        this.mBtnSoundEQ.setSelected(true);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private int stringToInteger(int id) {
        try {
            return Integer.parseInt(getString(id));
        } catch (NumberFormatException e) {
            Log.d("com.ts.MainUI", "SettingSoundActivity reason:" + e.toString());
            e.printStackTrace();
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mEQLayout = (RelativeLayout) findViewById(R.id.set_relative_layout);
        this.mManager = new RelativeLayoutManager(this, R.id.set_relative_layout);
        this.mEQSb = new SetItemVerticalSeekBar[9];
        for (int i = 0; i < this.mEQSb.length; i++) {
            this.mEQSb[i] = new SetItemVerticalSeekBar(this, this.mEQTitleId[i]);
            this.mManager.AddView(this.mEQSb[i].GetView(), stringToInteger(R.string.mEQSb_marginleft) + ((stringToInteger(R.string.mEQSb_width) + stringToInteger(R.string.mEQSb_width_gap)) * i), stringToInteger(R.string.mEQSb_margintop) - stringToInteger(R.string.mEQSb_text_height), stringToInteger(R.string.mEQSb_width), stringToInteger(R.string.mEQSb_height) + (stringToInteger(R.string.mEQSb_text_height) * 2));
            this.mEQSb[i].GetSeekBar().setId(i + 1000);
            this.mEQSb[i].GetSeekBar().setMaxPos(18);
            this.mEQSb[i].GetSeekBar().setOnTouchChangedListener(this);
        }
        for (int i2 = 0; i2 < this.mBtnEQ.length; i2++) {
            this.mBtnEQ[i2] = this.mManager.AddButton(stringToInteger(R.string.mBtnEQ_marginleft) + ((stringToInteger(R.string.mBtnEQ_width) + stringToInteger(R.string.mBtnEQ_width_gap)) * i2), stringToInteger(R.string.mBtnEQ_margintop) + ((stringToInteger(R.string.mBtnEQ_height) + stringToInteger(R.string.mBtnEQ_height_gap)) * i2), stringToInteger(R.string.mBtnEQ_width), stringToInteger(R.string.mBtnEQ_height));
            this.mBtnEQ[i2].setStateDrawable(R.drawable.setup_eq_mode_up, R.drawable.setup_eq_mode_dn, R.drawable.setup_eq_mode_dn);
            this.mBtnEQ[i2].setId(i2 + 1280);
            this.mBtnEQ[i2].setText(this.mEQStrId[i2]);
            this.mBtnEQ[i2].setOnClickListener(this);
            this.mBtnEQ[i2].setTextSize(0, (float) stringToInteger(R.string.mBtnEQ_textsize));
            this.mBtnEQ[i2].setSingleLine(true);
            this.mBtnEQ[i2].setEllipsize(TextUtils.TruncateAt.END);
        }
        this.mBtnEQ[0].setTextColor(-1);
        this.mBtnEQ[1].setTextColor(-1);
        this.mBtnEQ[2].setTextColor(-1);
        this.mBtnEQ[3].setTextColor(-1);
        this.mBtnEQ[4].setTextColor(-1);
        this.mBtnEQ[5].setTextColor(-1);
        this.mIvEQ = this.mManager.AddImage(stringToInteger(R.string.mPtList_marginleft), stringToInteger(R.string.mPtList_margintop));
        this.mIvEQ.setBackgroundResource(R.drawable.set_eq_bg01);
        this.mIvEQ.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(3.0f);
                paint.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f, 5.0f, 5.0f}, 1.0f));
                paint.setColor(Color.parseColor("#F58840"));
                canvas.drawPath(SettingSoundActivity.this.getPointCurvePath(SettingSoundActivity.this.mPtList), paint);
                return false;
            }
        });
    }

    private int numApproach(int des, int cal) {
        int src = cal;
        if (des == src) {
            return src;
        }
        if (src < des) {
            return src + 1;
        }
        return src - 1;
    }

    public void UserAll() {
        if (!(this.mOldEqMode == Iop.GetEqm() && this.mOldLoud == Iop.GetLud())) {
            ResetData();
        }
        if (!Arrays.equals(this.mEQVal, this.mEQAdjVal) || this.mEQVal == null) {
            this.mPtList.clear();
            for (int i = 0; i < 10; i++) {
                this.mEQAdjVal[i] = numApproach(this.mEQVal[i], this.mEQAdjVal[i]);
                this.mPtList.add(new Point((float) (stringToInteger(R.string.mPtList_width_gap) * i), (float) ((stringToInteger(R.string.mPtList_height) - (stringToInteger(R.string.mPtList_margintop) / 2)) - ((stringToInteger(R.string.mPtList_height) / 18) * this.mEQAdjVal[i]))));
            }
            Log.e(TAG, "line calc");
            this.mIvEQ.invalidate();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case 1280:
                Iop.EqmSet(0);
                break;
            case id_flate /*1281*/:
                Iop.EqmSet(1);
                break;
            case id_pop /*1282*/:
                Iop.EqmSet(2);
                break;
            case id_rock /*1283*/:
                Iop.EqmSet(3);
                break;
            case id_jazz /*1284*/:
                Iop.EqmSet(4);
                break;
            case id_class /*1285*/:
                Iop.EqmSet(5);
                break;
            case id_balance /*1290*/:
                this.mBalanceLayout.setVisibility(0);
                this.mEQLayout.setVisibility(8);
                this.mBtnSoundEQ.setSelected(false);
                this.mBtnBalance.setSelected(true);
                break;
            case id_eq /*1291*/:
                this.mBalanceLayout.setVisibility(8);
                this.mEQLayout.setVisibility(0);
                this.mBtnSoundEQ.setSelected(true);
                this.mBtnBalance.setSelected(false);
                break;
        }
        ResetData();
        int id = v.getId();
        if (id == R.id.set_balance_btn_center) {
            this.mEvc.evol_bal_set(7);
            this.mEvc.evol_fad_set(7);
        } else if (id == R.id.setting_balance_btn_up) {
            this.mEvc.evol_fad_tune(0);
        } else if (id == R.id.setting_balance_btn_down) {
            this.mEvc.evol_fad_tune(1);
        } else if (id == R.id.setting_balance_btn_left) {
            this.mEvc.evol_bal_tune(0);
        } else if (id == R.id.setting_balance_btn_right) {
            this.mEvc.evol_bal_tune(1);
        }
        resetFb();
    }

    /* access modifiers changed from: package-private */
    public void enterSubWin(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public Path getPointCurvePath(List<Point> points) {
        Point p3 = new Point();
        Point p4 = new Point();
        Path path = new Path();
        if (!(points == null || points.size() == 0)) {
            Point startp = points.get(0);
            path.moveTo(startp.x, startp.y);
            for (int i = 0; i < points.size() - 1; i++) {
                Point startp2 = points.get(i);
                Point endp = points.get(i + 1);
                int xCenter = ((int) (startp2.getX() + endp.getX())) / 2;
                int y = ((int) (startp2.getY() + endp.getY())) / 2;
                p3.y = startp2.y;
                p3.x = (float) xCenter;
                p4.y = endp.y;
                p4.x = (float) xCenter;
                path.cubicTo(p3.x, p3.y, p4.x, p4.y, endp.x, endp.y);
            }
        }
        return path;
    }

    public void onChanged(View view, int pos) {
        SettingSeekBar curSb = (SettingSeekBar) view;
        updateEQVal();
        int nNawBand = curSb.getId() + Can.OUT_TMP_INVALID_VAL;
        this.mEQVal[curSb.getId() + Can.OUT_TMP_INVALID_VAL] = pos;
        Log.e("Drag", "progress = " + curSb.getId() + ", " + pos);
        Iop.SetEqBand(nNawBand, pos);
        ResetData();
    }

    public void updateEQVal() {
        for (int i = 0; i < this.mEQVal.length; i++) {
            this.mEQVal[i] = Iop.GetEqBand(i);
        }
    }

    public void onChanged(View view, int fad, int bal) {
        this.mEvc.evol_fad_set(fad);
        this.mEvc.evol_bal_set(bal);
    }

    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        int id = v.getId();
        if (id == R.id.switch_ctrl) {
            if (isChecked) {
                Iop.LudSet(1);
            } else {
                Iop.LudSet(0);
            }
            SetCheck(this.mSwLoud, Iop.GetLud());
        } else if (id == R.id.switch_ctrl2) {
            if (!isChecked || FtSet.GetExAmp() != 0) {
                if (!isChecked && FtSet.GetExAmp() != 0) {
                    if (MainSet.GetInstance().IsTwcjw()) {
                        this.ExAmpDialog = new SettingNumInuptDlg();
                        this.ExAmpDialog.createDlg(this, new SettingNumInuptDlg.onInputOK() {
                            public void onOK(String val) {
                                if (val != null && val.equals("771018")) {
                                    FtSet.SetExAmp(0);
                                    SettingSoundActivity.this.SetCheck(SettingSoundActivity.this.mSwExamp, FtSet.GetExAmp());
                                }
                            }
                        }, 6);
                    } else {
                        FtSet.SetExAmp(0);
                    }
                }
            } else if (MainSet.GetInstance().IsTwcjw()) {
                this.ExAmpDialog = new SettingNumInuptDlg();
                this.ExAmpDialog.createDlg(this, new SettingNumInuptDlg.onInputOK() {
                    public void onOK(String val) {
                        if (val != null && val.equals("771018")) {
                            FtSet.SetExAmp(1);
                            SettingSoundActivity.this.SetCheck(SettingSoundActivity.this.mSwExamp, FtSet.GetExAmp());
                        }
                    }
                }, 6);
            } else {
                FtSet.SetExAmp(1);
            }
            SetCheck(this.mSwExamp, FtSet.GetExAmp());
        }
    }

    /* access modifiers changed from: private */
    public void SetCheck(Switch sw, int val) {
        if (val != 0) {
            sw.setSelected(true);
            sw.setChecked(true);
            sw.setThumbResource(R.drawable.set_eq_dn);
            sw.setTrackResource(R.drawable.set_eq_track_dn);
            return;
        }
        sw.setSelected(false);
        sw.setChecked(false);
        sw.setThumbResource(R.drawable.set_eq_up);
        sw.setTrackResource(R.drawable.set_eq_track_up);
    }
}
