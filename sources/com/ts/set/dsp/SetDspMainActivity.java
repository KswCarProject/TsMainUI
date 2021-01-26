package com.ts.set.dsp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.ts.MainUI.R;

public class SetDspMainActivity extends BaseActivity {
    private static final String TAG = "dsp";
    Button mBtnAdvanced;
    Button mBtnBalance;
    Button mBtnEmperor;
    Button mBtnEq;
    Button mBtnSoundField;
    Button mBtnSubwoofer;

    public int getLayout() {
        return 0;
    }

    public void initView() {
    }

    public void initData() {
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dsp_subwoofer) {
            openOtherActivity(SetDspSubwooferActivity.class);
        } else if (id == R.id.btn_dsp_eq) {
            openOtherActivity(SetDspEqActivity.class);
        } else if (id == R.id.btn_dsp_balance) {
            openOtherActivity(SetDspBalanceActivity.class);
        } else if (id == R.id.btn_dsp_sound_field) {
            openOtherActivity(SetDspSoundFieldActivity.class);
        } else if (id == R.id.btn_dsp_emperor) {
            openOtherActivity(SetDspEmperorActivity.class);
        } else if (id == R.id.btn_dsp_advanced) {
            openOtherActivity(SetDspAdvancedActivity.class);
        }
    }

    /* access modifiers changed from: package-private */
    public void openOtherActivity(Class<?> clazz) {
        Log.d("lh", "openOtherActivity");
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }

    public boolean onLongClick(View v) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openOtherActivity(SetDspEqActivity.class);
        finish();
    }
}
