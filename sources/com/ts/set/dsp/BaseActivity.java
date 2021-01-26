package com.ts.set.dsp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.ts.MainUI.R;

public abstract class BaseActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {
    View.OnClickListener btnOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_dsp_subwoofer) {
                if (!(BaseActivity.this instanceof SetDspSubwooferActivity)) {
                    BaseActivity.this.openOtherActivity(SetDspSubwooferActivity.class);
                }
            } else if (id == R.id.btn_dsp_eq) {
                if (!(BaseActivity.this instanceof SetDspEqActivity)) {
                    BaseActivity.this.openOtherActivity(SetDspEqActivity.class);
                }
            } else if (id == R.id.btn_dsp_balance) {
                if (!(BaseActivity.this instanceof SetDspBalanceActivity)) {
                    BaseActivity.this.openOtherActivity(SetDspBalanceActivity.class);
                }
            } else if (id == R.id.btn_dsp_sound_field) {
                if (!(BaseActivity.this instanceof SetDspSoundFieldActivity)) {
                    BaseActivity.this.openOtherActivity(SetDspSoundFieldActivity.class);
                }
            } else if (id == R.id.btn_dsp_emperor) {
                if (!(BaseActivity.this instanceof SetDspEmperorActivity)) {
                    BaseActivity.this.openOtherActivity(SetDspEmperorActivity.class);
                }
            } else if (id == R.id.btn_dsp_advanced && !(BaseActivity.this instanceof SetDspAdvancedActivity)) {
                BaseActivity.this.openOtherActivity(SetDspAdvancedActivity.class);
            }
        }
    };
    RelativeLayout mBtnAdvanced;
    RelativeLayout mBtnBalance;
    RelativeLayout mBtnEmperor;
    RelativeLayout mBtnEq;
    RelativeLayout mBtnSoundField;
    RelativeLayout mBtnSubwoofer;

    public abstract int getLayout();

    public abstract void initData();

    public abstract void initView();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayout() != 0) {
            setContentView(getLayout());
            initButton();
            initView();
            initData();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: package-private */
    public void initButton() {
        this.mBtnSubwoofer = (RelativeLayout) findViewById(R.id.btn_dsp_subwoofer);
        this.mBtnEq = (RelativeLayout) findViewById(R.id.btn_dsp_eq);
        this.mBtnBalance = (RelativeLayout) findViewById(R.id.btn_dsp_balance);
        this.mBtnSoundField = (RelativeLayout) findViewById(R.id.btn_dsp_sound_field);
        this.mBtnEmperor = (RelativeLayout) findViewById(R.id.btn_dsp_emperor);
        this.mBtnAdvanced = (RelativeLayout) findViewById(R.id.btn_dsp_advanced);
        this.mBtnSubwoofer.setOnClickListener(this.btnOnClickListener);
        this.mBtnEq.setOnClickListener(this.btnOnClickListener);
        this.mBtnBalance.setOnClickListener(this.btnOnClickListener);
        this.mBtnSoundField.setOnClickListener(this.btnOnClickListener);
        this.mBtnEmperor.setOnClickListener(this.btnOnClickListener);
        this.mBtnAdvanced.setOnClickListener(this.btnOnClickListener);
        if (this instanceof SetDspEqActivity) {
            this.mBtnEq.setSelected(true);
        } else if (this instanceof SetDspSubwooferActivity) {
            this.mBtnSubwoofer.setSelected(true);
        } else if (this instanceof SetDspBalanceActivity) {
            this.mBtnBalance.setSelected(true);
        } else if (this instanceof SetDspSoundFieldActivity) {
            this.mBtnSoundField.setSelected(true);
        } else if (this instanceof SetDspEmperorActivity) {
            this.mBtnEmperor.setSelected(true);
        } else if (this instanceof SetDspAdvancedActivity) {
            this.mBtnAdvanced.setSelected(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void openOtherActivity(Class<?> clazz) {
        Log.d("lh", "openOtherActivity");
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        intent.setFlags(268468224);
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
