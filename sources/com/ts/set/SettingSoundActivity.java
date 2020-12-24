package com.ts.set;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.ts.set.setview.SetMainItemTopName;
import com.ts.set.setview.SetSoundProgressList;
import com.ts.set.setview.SettingBalanceView;
import com.ts.set.setview.SettingSeekBar;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;

public class SettingSoundActivity extends Activity implements UserCallBack, View.OnClickListener, SettingBalanceView.onTouchBalanceChanged, SettingSeekBar.onTouchPosChanged, SetSoundProgressList.onPosChange, CompoundButton.OnCheckedChangeListener {
    private static final int SB_ID_BASE = 1000;
    public static final int SOUND_BAS = 0;
    public static final int SOUND_MID = 1;
    public static final int SOUND_TRE = 2;
    private static final String TAG = "SettingSoundActivity";
    private static final String TAG2 = "SettingBalanceActivity";
    public static final int id_balance = 1290;
    public static final int id_class = 1285;
    public static final int id_flate = 1281;
    public static final int id_jazz = 1284;
    public static final int id_pop = 1282;
    public static final int id_rock = 1283;
    public static final int id_user = 1280;
    private int fadLockedValue = -1;
    private int mBal;
    private int mBalOld;
    private int mBas;
    private int mBkBot;
    private int mBkRt;
    private int mBkX;
    private int mBkY;
    private Button mBtnCenter;
    private Button mBtnDn;
    private ParamButton[] mBtnEQ = new ParamButton[6];
    private Button mBtnLt;
    private Button mBtnRt;
    private Button mBtnUp;
    private SetSoundProgressList mEQSbBas;
    private SetSoundProgressList mEQSbMid;
    private SetSoundProgressList mEQSbTre;
    private int[] mEQStrId = {R.string.set_sound_user, R.string.set_sound_flate, R.string.set_sound_pop, R.string.set_sound_rock, R.string.set_sound_jazz, R.string.set_sound_class};
    private int[] mEQTitleId = {R.string.set_general_sound_min, R.string.set_general_sound_mid, R.string.set_general_sound_high};
    private Evc mEvc = Evc.GetInstance();
    private int mFad;
    private int mFadOld;
    private SettingBalanceView mFbView;
    private int mLastX;
    private int mLastY;
    public LayoutInflater mLayoutInflater;
    private int mLud;
    private RelativeLayoutManager mManager;
    private int mMid;
    private int mOldEqMode = -1;
    private RelativeLayout mRlLayout;
    private int mSub;
    /* access modifiers changed from: private */
    public Switch mSwExamp;
    private int mTre;
    private TextView mTvLud;
    private TextView mTvSub;
    private TextView mTvSw;
    SetMainItemTopName topname;

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
        }
    }

    private void ResetData() {
        if (this.mOldEqMode != Iop.GetEqm()) {
            this.mOldEqMode = Iop.GetEqm();
            int mode = Iop.GetEqm();
            if (mode < 6) {
                for (int i = 0; i < 6; i++) {
                    this.mBtnEQ[i].setSelected(false);
                }
                this.mBtnEQ[mode].setSelected(true);
            }
        }
        int curBas = Iop.GetBas();
        int curMid = Iop.GetMid();
        int curTre = Iop.GetTre();
        if (!(this.mBas == curBas && this.mMid == curMid && this.mTre == curTre)) {
            this.mBas = curBas;
            this.mMid = curMid;
            this.mTre = curTre;
            this.mEQSbBas.SetCurVal(this.mBas);
            this.mEQSbMid.SetCurVal(this.mMid);
            this.mEQSbTre.SetCurVal(this.mTre);
        }
        resetFb();
        resetLud();
        resetSub();
    }

    private void SetCheck(Switch sw, int val) {
        if (val != 0) {
            sw.setSelected(true);
            sw.setChecked(true);
            sw.setThumbResource(R.drawable.setup_switch_dn);
            sw.setTrackResource(R.drawable.setup_switch_track_dn);
            this.mTvSw.setText(getResources().getString(R.string.set_common_switch_on));
            return;
        }
        sw.setSelected(false);
        sw.setChecked(false);
        sw.setThumbResource(R.drawable.setup_switch_up);
        sw.setTrackResource(R.drawable.setup_switch_track_up);
        this.mTvSw.setText(getResources().getString(R.string.set_common_switch_off));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData();
        SetCheck(this.mSwExamp, FtSet.GetExAmp());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new RelativeLayoutManager(this, R.id.set_relative_layout);
        this.topname = new SetMainItemTopName(this, getResources().getStringArray(R.array.set_main_options)[3]);
        this.mManager.AddView(this.topname.GetView(), 0, 0, 1280, 80);
        this.topname.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingSoundActivity.this.finish();
            }
        });
        String mEQSbBasName = getResources().getString(R.string.set_general_sound_min);
        String mEQSbMidName = getResources().getString(R.string.set_general_sound_mid);
        String mEQSbTreName = getResources().getString(R.string.set_general_sound_high);
        this.mEQSbBas = new SetSoundProgressList((Context) this, mEQSbBasName);
        this.mEQSbBas.SetMinMax(0, 18);
        this.mEQSbBas.SetIdCallBack(0, this);
        this.mEQSbMid = new SetSoundProgressList((Context) this, mEQSbMidName);
        this.mEQSbMid.SetMinMax(0, 18);
        this.mEQSbMid.SetIdCallBack(1, this);
        this.mEQSbTre = new SetSoundProgressList((Context) this, mEQSbTreName);
        this.mEQSbTre.SetMinMax(0, 18);
        this.mEQSbTre.SetIdCallBack(2, this);
        this.mBas = Iop.GetBas();
        this.mMid = Iop.GetMid();
        this.mTre = Iop.GetTre();
        this.mEQSbBas.SetCurVal(this.mBas);
        this.mEQSbMid.SetCurVal(this.mMid);
        this.mEQSbTre.SetCurVal(this.mTre);
        this.mManager.AddView(this.mEQSbBas.GetView(), CanCameraUI.BTN_CC_WC_DIRECTION4, 100, 550, 51);
        this.mManager.AddView(this.mEQSbMid.GetView(), CanCameraUI.BTN_CC_WC_DIRECTION4, 179, 550, 51);
        this.mManager.AddView(this.mEQSbTre.GetView(), CanCameraUI.BTN_CC_WC_DIRECTION4, 258, 550, 51);
        this.mBtnCenter = (Button) findViewById(R.id.set_balance_btn_center1);
        this.mBtnUp = (Button) findViewById(R.id.setting_balance_btn_up);
        this.mBtnDn = (Button) findViewById(R.id.setting_balance_btn_down);
        this.mBtnLt = (Button) findViewById(R.id.setting_balance_btn_left);
        this.mBtnRt = (Button) findViewById(R.id.setting_balance_btn_right);
        this.mSwExamp = (Switch) findViewById(R.id.switch_ctrl2);
        this.mSwExamp.setOnCheckedChangeListener(this);
        this.mSwExamp.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() != 1) {
                    return false;
                }
                Log.d(SettingSoundActivity.TAG, String.valueOf(event.getAction()) + "   ");
                SettingSoundActivity.this.mSwExamp.setChecked(SettingSoundActivity.this.mSwExamp.isChecked());
                return false;
            }
        });
        this.mTvSw = (TextView) findViewById(R.id.set_tv_switch2);
        ((TextView) findViewById(R.id.switch_value2)).setText(R.string.set_balance_examp);
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
        for (int i = 0; i < this.mBtnEQ.length; i++) {
            this.mBtnEQ[i] = this.mManager.AddButton(25, (i * 57) + 78, 176, 57);
            this.mBtnEQ[i].setStateDrawable(R.drawable.setup_eq_mode_up, R.drawable.setup_eq_mode_dn, R.drawable.setup_eq_mode_dn);
            this.mBtnEQ[i].setId(i + 1280);
            this.mBtnEQ[i].setText(this.mEQStrId[i]);
            this.mBtnEQ[i].setOnClickListener(this);
            this.mBtnEQ[i].setTextColor(-16777216);
            this.mBtnEQ[i].setTextSize(0, 23.0f);
            this.mBtnEQ[i].setSingleLine(true);
            this.mBtnEQ[i].setEllipsize(TextUtils.TruncateAt.END);
        }
        dealFadLock();
    }

    private void dealFadLock() {
        int id = getResources().getIdentifier("eq_fad_locked", "string", getPackageName());
        if (id != 0) {
            try {
                this.fadLockedValue = Integer.parseInt(getResources().getString(id));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (this.fadLockedValue >= 0) {
            this.mBtnUp.setVisibility(8);
            this.mBtnDn.setVisibility(8);
            Iop.FadSet(this.fadLockedValue);
        }
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
        ResetData();
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.set_balance_btn_center1) {
            Iop.BalSet(7);
            Iop.FadSet(7);
        } else if (id == R.id.setting_balance_btn_up) {
            Iop.FadAdj(0);
        } else if (id == R.id.setting_balance_btn_down) {
            Iop.FadAdj(1);
        } else if (id == R.id.setting_balance_btn_left) {
            Iop.BalAdj(0);
        } else if (id == R.id.setting_balance_btn_right) {
            Iop.BalAdj(1);
        }
        resetFb();
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
        }
        ResetData();
    }

    /* access modifiers changed from: package-private */
    public void enterSubWin(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    /* access modifiers changed from: package-private */
    public void resetLud() {
    }

    /* access modifiers changed from: package-private */
    public void resetSub() {
    }

    private void resetData() {
        SetCheck(this.mSwExamp, FtSet.GetExAmp());
        resetFb();
        resetLud();
        resetSub();
    }

    /* access modifiers changed from: package-private */
    public void resetFb() {
        if (this.mFad != Iop.GetFad() || this.mBal != Iop.GetBal()) {
            this.mFad = Iop.GetFad();
            this.mBal = Iop.GetBal();
            this.mFbView.setBalance(this.mFad, this.mBal);
        }
    }

    public void onChanged(View view, int fad, int bal) {
        Iop.FadSet(fad);
        Iop.BalSet(bal);
    }

    public void onChanged(View view, int pos) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                Iop.BasSet(pos);
                return;
            case 1:
                Iop.MidSet(pos);
                return;
            case 2:
                Iop.TreSet(pos);
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.switch_ctrl2) {
            if (isChecked) {
                FtSet.SetExAmp(1);
            } else {
                FtSet.SetExAmp(0);
            }
        }
        SetCheck(this.mSwExamp, FtSet.GetExAmp());
    }
}
