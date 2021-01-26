package com.ts.set.dsp;

import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.set.dsp.Switch;
import com.ts.set.dsp.VerticalProgressBar;
import com.yyw.ts70xhw.Iop;

public class SetDspSoundFieldActivity extends BaseActivity implements UserCallBack {
    public static final int DSP_LOUD_HIGHFREQ = 2;
    public static final int DSP_LOUD_HIGHGAIN = 4;
    public static final int DSP_LOUD_LOWFREQ = 1;
    public static final int DSP_LOUD_LOWGAIN = 3;
    public static final int DSP_LOUD_ONOFF = 0;
    Button mBtnLoudnessReset;
    RelativeLayout mRvDspSubwooferLayout;
    VerticalProgressBar[] mSbDspSubwoofers;
    String[] mSubwooferFlagStrs;
    int[] mSubwooferFlagValues;
    int[] mSubwooferMaxValues = {950, 12, 8000, 12};
    String[] mSubwooferStrs;
    Switch mSwitchDspSubwooferLoudness;
    TextView[] mTvDspSubwooferFlags;
    TextView[] mTvDspSubwooferValues;
    int mValue;

    public SetDspSoundFieldActivity() {
        int[] iArr = new int[4];
        iArr[0] = 50;
        iArr[2] = 2000;
        this.mSubwooferFlagValues = iArr;
        this.mSubwooferFlagStrs = new String[]{"Hz", "dB", "Hz", "dB"};
        this.mValue = -1;
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_dsp_loudness_reset) {
            resetLoud();
        }
    }

    public boolean onLongClick(View v) {
        return false;
    }

    public int getLayout() {
        return R.layout.activity_dsp_sound_field;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        this.mValue = -1;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    public void initView() {
        this.mTvDspSubwooferValues = new TextView[4];
        this.mTvDspSubwooferFlags = new TextView[4];
        this.mSbDspSubwoofers = new VerticalProgressBar[4];
        this.mSubwooferStrs = new String[4];
        this.mSubwooferStrs = getResources().getStringArray(R.array.str_dsp_loudness_arrays);
        this.mRvDspSubwooferLayout = (RelativeLayout) findViewById(R.id.rv_dsp_sound_field_layout);
        this.mSwitchDspSubwooferLoudness = (Switch) findViewById(R.id.switch_dsp_subwoofer_loudness);
        this.mBtnLoudnessReset = (Button) findViewById(R.id.btn_dsp_loudness_reset);
        this.mBtnLoudnessReset.setOnClickListener(this);
        addSoundFieldView();
        this.mSwitchDspSubwooferLoudness.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            public void onCheckedChanged(View v, boolean isChecked) {
                if (isChecked) {
                    Iop.DspSetLoud(0, 1);
                } else {
                    Iop.DspSetLoud(0, 0);
                }
                SetDspSoundFieldActivity.this.checkLoudState(true);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void addSoundFieldView() {
        Resources res = getResources();
        int soundfieldLayoutWidth = res.getDimensionPixelSize(R.dimen.dsp_soundfield_layout_width);
        int soundfieldLayoutLeftMargin = res.getDimensionPixelSize(R.dimen.dsp_soundfield_layout_left_margin);
        int soundfieldLayoutTopMargin = res.getDimensionPixelSize(R.dimen.dsp_soundfield_layout_top_margin);
        int soundfieldLayoutGap = res.getDimensionPixelSize(R.dimen.dsp_soundfield_layout_gap);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        int len = this.mTvDspSubwooferFlags.length;
        for (int i = 0; i < len; i++) {
            View view = layoutInflater.inflate(R.layout.dsp_subwoofert_seekbar_layout, (ViewGroup) null);
            this.mSbDspSubwoofers[i] = (VerticalProgressBar) view.findViewById(R.id.sb_dsp_subwoofer);
            this.mTvDspSubwooferFlags[i] = (TextView) view.findViewById(R.id.tv_dsp_flag);
            this.mTvDspSubwooferValues[i] = (TextView) view.findViewById(R.id.tv_dsp_value);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(soundfieldLayoutWidth, -2);
            layoutParams.topMargin = soundfieldLayoutTopMargin;
            layoutParams.leftMargin = (i * soundfieldLayoutGap) + soundfieldLayoutLeftMargin;
            this.mSbDspSubwoofers[i].setFlag(this.mSubwooferFlagValues[i]);
            this.mSbDspSubwoofers[i].setMax(this.mSubwooferMaxValues[i]);
            this.mSbDspSubwoofers[i].setTag(Integer.valueOf(i));
            this.mSbDspSubwoofers[i].setOnSeekBarChangeListener(new VerticalProgressBar.OnSeekBarChangeListener() {
                public void onProgressChanged(VerticalProgressBar view, int progress, boolean fromUser) {
                    SetDspSoundFieldActivity.this.updateSeekbarValues(fromUser, ((Integer) view.getTag()).intValue(), progress);
                }
            });
            this.mTvDspSubwooferFlags[i].setPadding(30, 0, 30, 0);
            this.mTvDspSubwooferFlags[i].setText(this.mSubwooferStrs[i]);
            this.mRvDspSubwooferLayout.addView(view, layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateSeekbarValues(boolean fromUser, int flag, int progress) {
        Log.d(SetDspAdvancedActivity.TAG, "updateSeekbarValues falg = " + flag + ",progress = " + progress + ",fromUser = " + fromUser);
        int value = 0;
        switch (flag) {
            case 0:
                value = progress + 50;
                if (fromUser) {
                    Iop.DspSetLoud(1, value);
                    break;
                }
                break;
            case 1:
                value = progress;
                if (fromUser) {
                    Iop.DspSetLoud(3, value);
                    break;
                }
                break;
            case 2:
                value = progress + 2000;
                if (fromUser) {
                    Iop.DspSetLoud(2, value);
                    break;
                }
                break;
            case 3:
                value = progress;
                if (fromUser) {
                    Iop.DspSetLoud(4, value);
                    break;
                }
                break;
        }
        this.mTvDspSubwooferValues[flag].setText(String.valueOf(value) + this.mSubwooferFlagStrs[flag]);
    }

    /* access modifiers changed from: package-private */
    public void checkLoudState(boolean isFromUser) {
        int value = Iop.DspGetLoud(0);
        Log.d(SetDspAdvancedActivity.TAG, "loud = " + value);
        if (value == 1) {
            if (!isFromUser) {
                this.mSwitchDspSubwooferLoudness.setChecked(true);
            }
            for (VerticalProgressBar enabled : this.mSbDspSubwoofers) {
                enabled.setEnabled(true);
            }
            return;
        }
        if (!isFromUser) {
            this.mSwitchDspSubwooferLoudness.setChecked(false);
        }
        for (VerticalProgressBar enabled2 : this.mSbDspSubwoofers) {
            enabled2.setEnabled(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateLoudValues() {
        int value;
        int len = this.mSbDspSubwoofers.length;
        for (int i = 0; i < len; i++) {
            if (i == 1) {
                value = Iop.DspGetLoud(3);
            } else if (i == 2) {
                value = Iop.DspGetLoud(2);
            } else {
                value = Iop.DspGetLoud(i + 1);
            }
            if (i == 0) {
                this.mSbDspSubwoofers[i].setProgress(value - 50);
            } else if (i == 2) {
                this.mSbDspSubwoofers[i].setProgress(value - 2000);
            } else {
                this.mSbDspSubwoofers[i].setProgress(value);
            }
            this.mTvDspSubwooferValues[i].setText(String.valueOf(value) + this.mSubwooferFlagStrs[i]);
        }
    }

    /* access modifiers changed from: package-private */
    public void resetLoud() {
        Iop.DspSetLoud(0, 1);
        Iop.DspSetLoud(1, 600);
        Iop.DspSetLoud(2, 2000);
        Iop.DspSetLoud(3, 4);
        Iop.DspSetLoud(4, 4);
        checkLoudState(false);
        updateLoudValues();
    }

    public void initData() {
        checkLoudState(false);
        updateLoudValues();
    }

    public void UserAll() {
        int value = Iop.DspGetLoud(0);
        if (this.mValue != value) {
            Log.d(SetDspAdvancedActivity.TAG, "value:" + value);
            this.mValue = value;
            checkLoudState(false);
        }
    }
}
