package com.ts.set.dsp;

import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.set.dsp.Switch;
import com.ts.set.dsp.VerticalProgressBar;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.Iop;

public class SetDspSubwooferActivity extends BaseActivity {
    public static final int DSP_VBass_FREQ = 3;
    public static final int DSP_VBass_GAIN = 1;
    public static final int DSP_VBass_LEVEL = 2;
    public static final int DSP_VBass_ONOFF = 0;
    Button mBtnVirtualBassReset;
    RelativeLayout mRvDspSubwooferLayout;
    VerticalProgressBar[] mSbDspSubwoofers;
    int[] mSubwooferChangeValues = {12, 130, 15};
    String[] mSubwooferFlagStrs;
    int[] mSubwooferFlagValues;
    int[] mSubwooferMaxValues = {CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 150};
    String[] mSubwooferStrs;
    Switch mSwitchDspSubwooferVirtualBass;
    TextView[] mTvDspSubwooferFlags;
    TextView[] mTvDspSubwooferValues;

    public SetDspSubwooferActivity() {
        int[] iArr = new int[3];
        iArr[1] = 20;
        this.mSubwooferFlagValues = iArr;
        this.mSubwooferFlagStrs = new String[]{"dB", "Hz", TXZResourceManager.STYLE_DEFAULT};
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_dsp_virtual_bass_reset) {
            resetVBass();
        }
    }

    public boolean onLongClick(View v) {
        return false;
    }

    public int getLayout() {
        return R.layout.activity_dsp_subwoofer;
    }

    public void initView() {
        this.mTvDspSubwooferValues = new TextView[3];
        this.mTvDspSubwooferFlags = new TextView[3];
        this.mSbDspSubwoofers = new VerticalProgressBar[3];
        this.mSubwooferStrs = new String[7];
        this.mSubwooferStrs = getResources().getStringArray(R.array.str_dsp_subwoofer_arrays);
        this.mRvDspSubwooferLayout = (RelativeLayout) findViewById(R.id.rv_dsp_subwoofer_layout);
        this.mSwitchDspSubwooferVirtualBass = (Switch) findViewById(R.id.switch_dsp_subwoofer_virtual_bass);
        this.mBtnVirtualBassReset = (Button) findViewById(R.id.btn_dsp_virtual_bass_reset);
        this.mBtnVirtualBassReset.setOnClickListener(this);
        addSubwooferView();
        this.mSwitchDspSubwooferVirtualBass.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            public void onCheckedChanged(View v, boolean isChecked) {
                if (isChecked) {
                    Iop.DspSetVBass(0, 1);
                } else {
                    Iop.DspSetVBass(0, 0);
                }
                SetDspSubwooferActivity.this.checkVBassState(true);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void addSubwooferView() {
        Resources res = getResources();
        int subwooferLayoutWidth = res.getDimensionPixelSize(R.dimen.dsp_subwoofer_layout_width);
        int subwooferLayoutLeftMargin = res.getDimensionPixelSize(R.dimen.dsp_subwoofer_layout_left_margin);
        int subwooferLayoutTopMargin = res.getDimensionPixelSize(R.dimen.dsp_subwoofer_layout_top_margin);
        int subwooferLayoutGap = res.getDimensionPixelSize(R.dimen.dsp_subwoofer_layout_gap);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        int len = this.mTvDspSubwooferFlags.length;
        for (int i = 0; i < len; i++) {
            View view = layoutInflater.inflate(R.layout.dsp_subwoofert_seekbar_layout, (ViewGroup) null);
            this.mSbDspSubwoofers[i] = (VerticalProgressBar) view.findViewById(R.id.sb_dsp_subwoofer);
            this.mTvDspSubwooferFlags[i] = (TextView) view.findViewById(R.id.tv_dsp_flag);
            this.mTvDspSubwooferValues[i] = (TextView) view.findViewById(R.id.tv_dsp_value);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(subwooferLayoutWidth, -2);
            layoutParams.topMargin = subwooferLayoutTopMargin;
            layoutParams.leftMargin = (i * subwooferLayoutGap) + subwooferLayoutLeftMargin;
            this.mSbDspSubwoofers[i].setChange(this.mSubwooferChangeValues[i]);
            this.mSbDspSubwoofers[i].setFlag(this.mSubwooferFlagValues[i]);
            this.mSbDspSubwoofers[i].setMax(this.mSubwooferMaxValues[i]);
            this.mSbDspSubwoofers[i].setTag(Integer.valueOf(i));
            this.mSbDspSubwoofers[i].setOnSeekBarChangeListener(new VerticalProgressBar.OnSeekBarChangeListener() {
                public void onProgressChanged(VerticalProgressBar view, int progress, boolean fromUser) {
                    SetDspSubwooferActivity.this.updateSeekbarValues(fromUser, ((Integer) view.getTag()).intValue(), progress);
                }
            });
            this.mTvDspSubwooferFlags[i].setText(this.mSubwooferStrs[i]);
            this.mRvDspSubwooferLayout.addView(view, layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateSeekbarValues(boolean fromUser, int flag, int progress) {
        int value;
        Log.d(SetDspAdvancedActivity.TAG, "updateSeekbarValues falg = " + flag + ",progress = " + progress + ",fromUser = " + fromUser);
        int value2 = 0;
        switch (flag) {
            case 0:
                value2 = progress;
                if (fromUser) {
                    Iop.DspSetVBass(1, value2);
                    break;
                }
                break;
            case 1:
                value2 = progress;
                if (fromUser) {
                    Iop.DspSetVBass(2, value2);
                    break;
                }
                break;
            case 2:
                value2 = progress;
                if (fromUser) {
                    Iop.DspSetVBass(3, value2);
                    break;
                }
                break;
        }
        if (flag == 1) {
            value = ((this.mSubwooferChangeValues[flag] * value2) / this.mSubwooferMaxValues[flag]) + 20;
        } else {
            value = (this.mSubwooferChangeValues[flag] * value2) / this.mSubwooferMaxValues[flag];
        }
        this.mTvDspSubwooferValues[flag].setText(String.valueOf(value) + this.mSubwooferFlagStrs[flag]);
    }

    /* access modifiers changed from: package-private */
    public void checkVBassState(boolean isFromUser) {
        Log.d(SetDspAdvancedActivity.TAG, "vbass = " + Iop.DspGetVBass(0));
        if (Iop.DspGetVBass(0) == 1) {
            if (!isFromUser) {
                this.mSwitchDspSubwooferVirtualBass.setChecked(true);
            }
            for (VerticalProgressBar enabled : this.mSbDspSubwoofers) {
                enabled.setEnabled(true);
            }
            return;
        }
        if (!isFromUser) {
            this.mSwitchDspSubwooferVirtualBass.setChecked(false);
        }
        for (VerticalProgressBar enabled2 : this.mSbDspSubwoofers) {
            enabled2.setEnabled(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateVBassValues() {
        int value;
        int len = this.mSbDspSubwoofers.length;
        for (int i = 0; i < len; i++) {
            int value2 = Iop.DspGetVBass(i + 1);
            this.mSbDspSubwoofers[i].setProgress(value2);
            if (i == 1) {
                value = ((this.mSubwooferChangeValues[i] * value2) / this.mSubwooferMaxValues[i]) + 20;
            } else {
                value = (this.mSubwooferChangeValues[i] * value2) / this.mSubwooferMaxValues[i];
            }
            this.mTvDspSubwooferValues[i].setText(String.valueOf(value) + this.mSubwooferFlagStrs[i]);
        }
    }

    /* access modifiers changed from: package-private */
    public void resetVBass() {
        Iop.DspSetVBass(0, 1);
        Iop.DspSetVBass(1, 200);
        Iop.DspSetVBass(2, 300);
        Iop.DspSetVBass(3, 150);
        checkVBassState(false);
        updateVBassValues();
    }

    public void initData() {
        checkVBassState(false);
        updateVBassValues();
    }
}
