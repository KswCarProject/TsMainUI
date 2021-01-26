package com.ts.set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.ts.other.RelativeLayoutManager;
import com.ts.set.setview.SetItemVerticalSeekBar;
import com.ts.set.setview.SettingBalanceView;
import com.ts.set.setview.SettingSeekBar;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;

public class SettingBalanceActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SettingBalanceView.onTouchBalanceChanged, SettingSeekBar.onTouchPosChanged, UserCallBack {
    private static final int SB_ID_BASE = 1000;
    private static final String TAG = "SettingBalanceActivity";
    private int mBal;
    private int mBkX;
    private int mBkY;
    private Button mBtnCenter;
    private Button mBtnDn;
    private Button mBtnEQ;
    private Button mBtnLt;
    private Button mBtnRt;
    private Button mBtnUp;
    private Evc mEvc = Evc.GetInstance();
    private int mFad;
    private SetItemVerticalSeekBar[] mFbSb = new SetItemVerticalSeekBar[2];
    private SettingBalanceView mFbView;
    public LayoutInflater mLayoutInflater;
    private int mLud;
    private RelativeLayoutManager mManager;
    private int mSub;
    private Switch mSwExamp;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_balance);
        this.mManager = new RelativeLayoutManager(this, R.id.layout_set_balance);
        this.mBtnEQ = (Button) findViewById(R.id.set_balance_btn_sound);
        this.mBtnCenter = (Button) findViewById(R.id.set_balance_btn_center);
        this.mBtnUp = (Button) findViewById(R.id.setting_balance_btn_up);
        this.mBtnDn = (Button) findViewById(R.id.setting_balance_btn_down);
        this.mBtnLt = (Button) findViewById(R.id.setting_balance_btn_left);
        this.mBtnRt = (Button) findViewById(R.id.setting_balance_btn_right);
        this.mSwExamp = (Switch) findViewById(R.id.switch_ctrl2);
        this.mSwExamp.setOnCheckedChangeListener(this);
        TextView tv2 = (TextView) findViewById(R.id.switch_value2);
        tv2.setText(R.string.set_balance_examp);
        if (MainSet.GetInstance().IsXuhuiDmax()) {
            tv2.setVisibility(4);
        }
        this.mFbView = (SettingBalanceView) findViewById(R.id.setting_balance_img_seat);
        this.mFbView.setBalanceChangedListener(this);
        this.mBtnUp.setOnClickListener(this);
        this.mBtnDn.setOnClickListener(this);
        this.mBtnLt.setOnClickListener(this);
        this.mBtnRt.setOnClickListener(this);
        this.mBtnEQ.setOnClickListener(this);
        this.mBtnCenter.setOnClickListener(this);
        this.mBkX = ((int) getResources().getDimension(R.dimen.x_setting_balance_seat)) + 3;
        this.mBkY = ((int) getResources().getDimension(R.dimen.y_setting_balance_seat)) + 2;
        for (int i = 0; i < this.mFbSb.length; i++) {
            this.mFbSb[i] = new SetItemVerticalSeekBar(this, 0);
            this.mManager.AddView(this.mFbSb[i].GetView(), (i * 110) + 81, 110, 0, 0);
            this.mFbSb[i].GetSeekBar().setId(i + 1000);
            this.mFbSb[i].GetSeekBar().setMaxPos(20);
            this.mFbSb[i].GetSeekBar().setOnTouchChangedListener(this);
        }
        this.mFbSb[0].SetTitle(getResources().getString(R.string.set_balance_loud));
        this.mFbSb[1].SetTitle(getResources().getString(R.string.set_balance_subwoof));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        resetData();
        if (MainSet.GetInstance().IsXuhuiDmax()) {
            this.mSwExamp.setVisibility(4);
        } else {
            SetCheck(this.mSwExamp, FtSet.GetExAmp());
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    protected static boolean i2b(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void resetLud() {
        this.mFbSb[0].GetSeekBar().setCurPos(this.mLud);
        this.mFbSb[0].SetVal(Integer.toString(this.mLud));
    }

    /* access modifiers changed from: package-private */
    public void resetSub() {
        this.mFbSb[1].GetSeekBar().setCurPos(this.mSub);
        this.mFbSb[1].SetVal(Integer.toString(this.mSub));
    }

    private void resetData() {
        SetCheck(this.mSwExamp, FtSet.GetExAmp());
        resetFb();
        resetLud();
        resetSub();
    }

    private void SetCheck(Switch sw, int val) {
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

    /* access modifiers changed from: package-private */
    public void resetFb() {
        this.mFad = Iop.GetFad();
        this.mBal = Iop.GetBal();
        this.mFbView.setBalance(this.mFad, this.mBal);
    }

    public void onClick(View v) {
        if (R.id.set_balance_btn_sound == v.getId()) {
            enterSubWin(SettingSoundActivity.class);
            return;
        }
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

    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        int id = v.getId();
        if (id == R.id.switch_ctrl) {
            if (isChecked) {
                this.mEvc.evol_ch51_set(1);
            } else {
                this.mEvc.evol_ch51_set(0);
            }
        } else if (id == R.id.switch_ctrl2) {
            if (isChecked) {
                FtSet.SetExAmp(1);
            } else {
                FtSet.SetExAmp(0);
            }
        }
        SetCheck(this.mSwExamp, FtSet.GetExAmp());
    }

    public void onChanged(View view, int fad, int bal) {
        this.mEvc.evol_fad_set(fad);
        this.mEvc.evol_bal_set(bal);
    }

    public void onChanged(View view, int pos) {
        Log.e(TAG, "pos = " + pos);
        if (1000 == ((SettingSeekBar) view).getId()) {
            this.mEvc.evol_lud_set(pos);
            resetLud();
            return;
        }
        this.mEvc.evol_sub_set(pos);
        resetSub();
    }

    public void UserAll() {
    }
}
