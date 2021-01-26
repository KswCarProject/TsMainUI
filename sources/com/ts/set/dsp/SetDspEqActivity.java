package com.ts.set.dsp;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.set.dsp.EQVerticalProgressBar;
import com.yyw.ts70xhw.Iop;
import java.util.ArrayList;
import java.util.List;

public class SetDspEqActivity extends BaseActivity {
    private static final int BTN_DSP_CLASSIC = 4;
    private static final int BTN_DSP_FLAT = 0;
    private static final int BTN_DSP_JAZZ = 3;
    private static final int BTN_DSP_NEXT = 7;
    private static final int BTN_DSP_POP = 1;
    private static final int BTN_DSP_PREV = 6;
    private static final int BTN_DSP_ROCK = 2;
    private static final int BTN_DSP_USER = 5;
    Button[] mBtnDspEqModes = new Button[6];
    Button mBtnDspNext;
    Button mBtnDspPrev;
    ChartView mChartView;
    String[] mFlagStrs;
    RelativeLayout mRvLeft;
    RelativeLayout mRvMid;
    RelativeLayout mRvRight;
    int[] mSbIds = {R.id.sb_dsp1, R.id.sb_dsp2, R.id.sb_dsp3, R.id.sb_dsp4, R.id.sb_dsp5, R.id.sb_dsp6, R.id.sb_dsp7, R.id.sb_dsp8, R.id.sb_dsp9, R.id.sb_dsp10, R.id.sb_dsp11, R.id.sb_dsp12, R.id.sb_dsp13, R.id.sb_dsp14, R.id.sb_dsp15, R.id.sb_dsp16, R.id.sb_dsp17, R.id.sb_dsp18, R.id.sb_dsp19, R.id.sb_dsp20, R.id.sb_dsp21, R.id.sb_dsp22, R.id.sb_dsp23, R.id.sb_dsp24, R.id.sb_dsp25, R.id.sb_dsp26, R.id.sb_dsp27, R.id.sb_dsp28, R.id.sb_dsp29, R.id.sb_dsp30};
    EQVerticalProgressBar[] mSeekbars = new EQVerticalProgressBar[30];
    int[] mTvFlagIds = {R.id.tv_dsp_flag1, R.id.tv_dsp_flag2, R.id.tv_dsp_flag3, R.id.tv_dsp_flag4, R.id.tv_dsp_flag5, R.id.tv_dsp_flag6, R.id.tv_dsp_flag7, R.id.tv_dsp_flag8, R.id.tv_dsp_flag9, R.id.tv_dsp_flag10, R.id.tv_dsp_flag11, R.id.tv_dsp_flag12, R.id.tv_dsp_flag13, R.id.tv_dsp_flag14, R.id.tv_dsp_flag15, R.id.tv_dsp_flag16, R.id.tv_dsp_flag17, R.id.tv_dsp_flag18, R.id.tv_dsp_flag19, R.id.tv_dsp_flag20, R.id.tv_dsp_flag21, R.id.tv_dsp_flag22, R.id.tv_dsp_flag23, R.id.tv_dsp_flag24, R.id.tv_dsp_flag25, R.id.tv_dsp_flag26, R.id.tv_dsp_flag27, R.id.tv_dsp_flag28, R.id.tv_dsp_flag29, R.id.tv_dsp_flag30};
    int[] mTvValueIds = {R.id.tv_dsp_value1, R.id.tv_dsp_value2, R.id.tv_dsp_value3, R.id.tv_dsp_value4, R.id.tv_dsp_value5, R.id.tv_dsp_value6, R.id.tv_dsp_value7, R.id.tv_dsp_value8, R.id.tv_dsp_value9, R.id.tv_dsp_value10, R.id.tv_dsp_value11, R.id.tv_dsp_value12, R.id.tv_dsp_value13, R.id.tv_dsp_value14, R.id.tv_dsp_value15, R.id.tv_dsp_value16, R.id.tv_dsp_value17, R.id.tv_dsp_value18, R.id.tv_dsp_value19, R.id.tv_dsp_value20, R.id.tv_dsp_value21, R.id.tv_dsp_value22, R.id.tv_dsp_value23, R.id.tv_dsp_value24, R.id.tv_dsp_value25, R.id.tv_dsp_value26, R.id.tv_dsp_value27, R.id.tv_dsp_value28, R.id.tv_dsp_value29, R.id.tv_dsp_value30};
    TextView[] mTvValues = new TextView[30];
    String[] mValueStrs;
    int[] mValues = new int[30];
    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;
    List<View> mViews = new ArrayList();

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                if (id == 5) {
                    checkDspModeSelected(id);
                    Iop.DspSetEqm(0);
                } else {
                    checkDspModeSelected(id);
                    Iop.DspSetEqm(id + 1);
                }
                updateDspEqValues(id);
                return;
            case 6:
                prev();
                return;
            case 7:
                next();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void prev() {
        int index;
        if (this.mViewPager != null && this.mViewPager.getCurrentItem() - 1 >= 0) {
            this.mViewPager.setCurrentItem(index);
        }
    }

    /* access modifiers changed from: package-private */
    public void next() {
        int index;
        if (this.mViewPager != null && (index = this.mViewPager.getCurrentItem() + 1) < 3) {
            this.mViewPager.setCurrentItem(index);
        }
    }

    /* access modifiers changed from: package-private */
    public void checkDspModeSelected(int id) {
        for (Button selected : this.mBtnDspEqModes) {
            selected.setSelected(false);
        }
        if (id < this.mBtnDspEqModes.length) {
            this.mBtnDspEqModes[id].setSelected(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateDspEqValues(int id) {
        for (int i = 0; i < this.mValues.length; i++) {
            this.mValues[i] = Iop.DspGetEqBand(i);
            this.mSeekbars[i].setProgress(this.mValues[i]);
        }
        this.mChartView.updatePoints(this.mValues);
    }

    public boolean onLongClick(View v) {
        return false;
    }

    public int getLayout() {
        return R.layout.activity_dsp_eq;
    }

    public void initView() {
        this.mFlagStrs = getResources().getStringArray(R.array.str_dsp_flags);
        this.mChartView = (ChartView) findViewById(R.id.cv_dsp);
        this.mViewPager = (ViewPager) findViewById(R.id.vp_dsp);
        this.mViewPager.setOffscreenPageLimit(2);
        this.mRvLeft = new RelativeLayout(this);
        this.mRvMid = new RelativeLayout(this);
        this.mRvRight = new RelativeLayout(this);
        this.mBtnDspNext = (Button) findViewById(R.id.btn_dsp_eq_next);
        this.mBtnDspNext.setTag(7);
        this.mBtnDspNext.setOnClickListener(this);
        this.mBtnDspPrev = (Button) findViewById(R.id.btn_dsp_eq_prev);
        this.mBtnDspPrev.setTag(6);
        this.mBtnDspPrev.setOnClickListener(this);
        long startTime = System.currentTimeMillis();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        this.mRvLeft = (RelativeLayout) layoutInflater.inflate(R.layout.dsp_eq_left_layout, (ViewGroup) null);
        this.mRvMid = (RelativeLayout) layoutInflater.inflate(R.layout.dsp_eq_left_layout, (ViewGroup) null);
        this.mRvRight = (RelativeLayout) layoutInflater.inflate(R.layout.dsp_eq_left_layout, (ViewGroup) null);
        for (int i = 0; i < 30; i++) {
            if (i < 10) {
                this.mTvValues[i] = (TextView) this.mRvLeft.findViewById(this.mTvValueIds[i % 10]);
                ((TextView) this.mRvLeft.findViewById(this.mTvFlagIds[i % 10])).setText(this.mFlagStrs[i]);
                this.mSeekbars[i] = (EQVerticalProgressBar) this.mRvLeft.findViewById(this.mSbIds[i % 10]);
            } else if (i < 20) {
                this.mTvValues[i] = (TextView) this.mRvMid.findViewById(this.mTvValueIds[i % 10]);
                ((TextView) this.mRvMid.findViewById(this.mTvFlagIds[i % 10])).setText(this.mFlagStrs[i]);
                this.mSeekbars[i] = (EQVerticalProgressBar) this.mRvMid.findViewById(this.mSbIds[i % 10]);
            } else {
                this.mTvValues[i] = (TextView) this.mRvRight.findViewById(this.mTvValueIds[i % 10]);
                ((TextView) this.mRvRight.findViewById(this.mTvFlagIds[i % 10])).setText(this.mFlagStrs[i]);
                this.mSeekbars[i] = (EQVerticalProgressBar) this.mRvRight.findViewById(this.mSbIds[i % 10]);
            }
            this.mSeekbars[i].setTag(Integer.valueOf(i));
            this.mSeekbars[i].setOnSeekBarChangeListener(new EQVerticalProgressBar.OnSeekBarChangeListener() {
                public void onProgressChanged(EQVerticalProgressBar view, int progress, boolean fromUser) {
                    Log.d("seekbar", "onProgressChanged");
                    int tag = ((Integer) view.getTag()).intValue();
                    SetDspEqActivity.this.mValues[tag] = progress;
                    SetDspEqActivity.this.mTvValues[tag].setText(new StringBuilder(String.valueOf(progress - 12)).toString());
                    if (fromUser) {
                        SetDspEqActivity.this.mChartView.updatePoints(SetDspEqActivity.this.mValues);
                        SetDspEqActivity.this.checkDspModeSelected(5);
                        if (Iop.DspGetEqm() == 0) {
                            Iop.DspSetEqBand(tag, SetDspEqActivity.this.mValues[tag]);
                            return;
                        }
                        Iop.DspSetEqm(0);
                        SetDspEqActivity.this.updateValues();
                    }
                }
            });
            this.mSeekbars[i].setFlag(-12);
            this.mSeekbars[i].setMax(24);
        }
        this.mViews.add(this.mRvLeft);
        this.mViews.add(this.mRvMid);
        this.mViews.add(this.mRvRight);
        Log.d("seekbar", "totalTime = " + (System.currentTimeMillis() - startTime));
        this.mViewPagerAdapter = new ViewPagerAdapter(this.mViews);
        this.mViewPager.setAdapter(this.mViewPagerAdapter);
        this.mBtnDspEqModes[0] = (Button) findViewById(R.id.btn_dsp_eq_flat);
        this.mBtnDspEqModes[1] = (Button) findViewById(R.id.btn_dsp_eq_pop);
        this.mBtnDspEqModes[2] = (Button) findViewById(R.id.btn_dsp_eq_rock);
        this.mBtnDspEqModes[3] = (Button) findViewById(R.id.btn_dsp_eq_jazz);
        this.mBtnDspEqModes[4] = (Button) findViewById(R.id.btn_dsp_eq_classic);
        this.mBtnDspEqModes[5] = (Button) findViewById(R.id.btn_dsp_eq_user);
        for (int i2 = 0; i2 < this.mBtnDspEqModes.length; i2++) {
            this.mBtnDspEqModes[i2].setOnClickListener(this);
            this.mBtnDspEqModes[i2].setTag(Integer.valueOf(i2));
        }
    }

    /* access modifiers changed from: package-private */
    public void updateValues() {
        for (int i = 0; i < this.mValues.length; i++) {
            Iop.DspSetEqBand(i, this.mValues[i]);
        }
    }

    public void initData() {
        int mode = Iop.DspGetEqm();
        if (mode == 0) {
            checkDspModeSelected(5);
        } else {
            checkDspModeSelected(mode - 1);
        }
        updateDspEqValues(mode);
    }
}
