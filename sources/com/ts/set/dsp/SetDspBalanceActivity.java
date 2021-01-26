package com.ts.set.dsp;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.SdkConstants;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.ts.set.dsp.SetDspBalanceView;
import com.ts.set.dsp.Switch;
import com.ts.set.setview.SettingNumInuptDlg;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;

public class SetDspBalanceActivity extends BaseActivity implements SetDspBalanceView.onTouchBalanceChanged, Switch.OnCheckedChangeListener {
    SettingNumInuptDlg ExAmpDialog;
    private int mBal;
    Button mBtnDspBalDown;
    Button mBtnDspBalLeft;
    Button mBtnDspBalReset;
    Button mBtnDspBalRight;
    Button mBtnDspBalUpward;
    private int mFad;
    SetDspBalanceView mSetDspBalanceView;
    Switch mSwitchDspBalExamp;
    TextView mTvDspBalExamp;

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dsp_bal_upward) {
            Iop.DspFadAdj(0);
        } else if (id == R.id.btn_dsp_bal_down) {
            Iop.DspFadAdj(1);
        } else if (id == R.id.btn_dsp_bal_left) {
            Iop.DspBalAdj(0);
        } else if (id == R.id.btn_dsp_bal_right) {
            Iop.DspBalAdj(1);
        } else if (id == R.id.btn_dsp_bal_reset) {
            Iop.DspFadSet(7);
            Iop.DspBalSet(7);
        }
        resetFb();
    }

    /* access modifiers changed from: package-private */
    public void resetFb() {
        this.mFad = Iop.GetFad();
        this.mBal = Iop.GetBal();
        this.mSetDspBalanceView.setBalance(this.mFad, this.mBal);
    }

    public boolean onLongClick(View v) {
        return false;
    }

    public int getLayout() {
        return R.layout.activity_dsp_balance;
    }

    public void initView() {
        this.mBtnDspBalUpward = (Button) findViewById(R.id.btn_dsp_bal_upward);
        this.mBtnDspBalDown = (Button) findViewById(R.id.btn_dsp_bal_down);
        this.mBtnDspBalLeft = (Button) findViewById(R.id.btn_dsp_bal_left);
        this.mBtnDspBalRight = (Button) findViewById(R.id.btn_dsp_bal_right);
        this.mBtnDspBalReset = (Button) findViewById(R.id.btn_dsp_bal_reset);
        this.mSetDspBalanceView = (SetDspBalanceView) findViewById(R.id.iv_dsp_bal_seat);
        this.mTvDspBalExamp = (TextView) findViewById(R.id.tv_dsp_bal_examp);
        this.mSetDspBalanceView.setBalanceChangedListener(this);
        this.mBtnDspBalUpward.setOnClickListener(this);
        this.mBtnDspBalDown.setOnClickListener(this);
        this.mBtnDspBalLeft.setOnClickListener(this);
        this.mBtnDspBalRight.setOnClickListener(this);
        this.mBtnDspBalReset.setOnClickListener(this);
        this.mSwitchDspBalExamp = (Switch) findViewById(R.id.sw_dsp_bal_examp);
        this.mSwitchDspBalExamp.setOnCheckedChangeListener(this);
    }

    public void initData() {
        resetFb();
        SetCheck(this.mSwitchDspBalExamp, FtSet.GetExAmp());
        this.mTvDspBalExamp.setText(R.string.set_balance_examp);
        if (MainSet.GetInstance().IsXuhuiDmax()) {
            this.mTvDspBalExamp.setVisibility(4);
        }
        if (getResources().getIdentifier("antenna_power", SdkConstants.TAG_STRING, getPackageName()) != 0 || getResources().getString(R.string.custom_ver_).equals("RU01")) {
            this.mSwitchDspBalExamp.setVisibility(8);
            this.mTvDspBalExamp.setVisibility(8);
        }
        if (getResources().getString(R.string.custom_ver_).equals("RU01") && FtSet.GetExAmp() == 0) {
            FtSet.SetExAmp(1);
        }
    }

    public void onChanged(View view, int fad, int bal) {
        Iop.DspFadSet(fad);
        Iop.DspBalSet(bal);
    }

    public void onCheckedChanged(View v, boolean isChecked) {
        if (v.getId() == R.id.sw_dsp_bal_examp) {
            if (!isChecked || FtSet.GetExAmp() != 0) {
                if (!isChecked && FtSet.GetExAmp() != 0) {
                    Log.d(SetDspAdvancedActivity.TAG, "isCheked1 = " + isChecked);
                    if (MainSet.GetInstance().IsTwcjw()) {
                        this.ExAmpDialog = new SettingNumInuptDlg();
                        this.ExAmpDialog.createDlg(this, new SettingNumInuptDlg.onInputOK() {
                            public void onOK(String val) {
                                if (val != null && val.equals("771018")) {
                                    FtSet.SetExAmp(0);
                                    SetDspBalanceActivity.this.SetCheck(SetDspBalanceActivity.this.mSwitchDspBalExamp, FtSet.GetExAmp());
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
                            SetDspBalanceActivity.this.SetCheck(SetDspBalanceActivity.this.mSwitchDspBalExamp, FtSet.GetExAmp());
                        }
                    }
                }, 6);
            } else {
                FtSet.SetExAmp(1);
            }
            SetCheck(this.mSwitchDspBalExamp, FtSet.GetExAmp());
        }
    }

    /* access modifiers changed from: private */
    public void SetCheck(Switch sw, int val) {
        if (val != 0) {
            sw.setChecked(true);
        } else {
            sw.setChecked(false);
        }
    }
}
