package com.ts.set;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.other.RelativeLayoutManager;
import com.ts.set.definition.Debug;
import com.ts.set.setview.SetMainItemTopName;
import com.yyw.ts70xhw.Mcu;

public class SettingWheelActivity extends Activity implements View.OnClickListener, UserCallBack {
    private static final String TAG = "SetSWCActivity";
    private static final int[][] mStudyId;
    private Button mBtnClear;
    private Button[] mBtnStudy;
    private int mCurKeyNow;
    private int mCurSta;
    private long mCurTick;
    private RelativeLayoutManager mManager;
    private int mOldKeyNow;
    private int mOldSta;
    private long mOldTick;
    private boolean mfgFlashSel;
    private boolean mfgStudyNow;
    SetMainItemTopName topname;

    static {
        int[] iArr = new int[2];
        iArr[0] = R.id.btn_swc_poweroff;
        mStudyId = new int[][]{iArr, new int[]{R.id.btn_swc_vol_inc, 1}, new int[]{R.id.btn_swc_vol_dec, 2}, new int[]{R.id.btn_swc_next, 3}, new int[]{R.id.btn_swc_prev, 4}, new int[]{R.id.btn_swc_ch_dec, 5}, new int[]{R.id.btn_swc_ch_inc, 6}, new int[]{R.id.btn_swc_mute, 7}, new int[]{R.id.btn_swc_mode, 8}, new int[]{R.id.btn_swc_dial, 9}, new int[]{R.id.btn_swc_hang, 10}, new int[]{R.id.btn_swc_pp, 11}, new int[]{R.id.btn_swc_gps, 12}, new int[]{R.id.btn_swc_home, 13}, new int[]{R.id.btn_swc_return, 14}, new int[]{R.id.btn_swc_yuyin, 15}};
    }

    /* access modifiers changed from: protected */
    public void setupViews() {
        this.mManager = new RelativeLayoutManager(this, R.id.wheel_layout);
        this.topname = new SetMainItemTopName(this, getResources().getStringArray(R.array.set_main_options)[7]);
        this.mManager.AddView(this.topname.GetView(), 0, 0, 1024, -2);
        this.topname.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingWheelActivity.this.finish();
            }
        });
        this.mBtnStudy = new Button[mStudyId.length];
        for (int i = 0; i < mStudyId.length; i++) {
            this.mBtnStudy[i] = (Button) findViewById(mStudyId[i][0]);
            this.mBtnStudy[i].setTag(Integer.valueOf(mStudyId[i][1]));
            this.mBtnStudy[i].setOnClickListener(this);
        }
        this.mBtnClear = (Button) findViewById(R.id.btn_swc_clear);
        this.mBtnClear.setOnClickListener(this);
        this.mBtnClear.setTextSize(0, 30.0f);
        this.mBtnClear.setTextColor(-1);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        this.mOldKeyNow = 255;
        this.mOldSta = 0;
        onTimer();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_swc);
        setupViews();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        if (this.mfgStudyNow) {
            if (this.mCurKeyNow < this.mBtnStudy.length) {
                this.mBtnStudy[this.mCurKeyNow].setSelected(false);
            }
            Mcu.SwKeyStudy(-1);
        }
    }

    public void onClick(View v) {
        if (v.equals(this.mBtnClear)) {
            Mcu.SwKeyDelete();
            Debug.d(TAG, "SwKeyDelete");
            return;
        }
        Mcu.SwKeyStudy(((Integer) v.getTag()).intValue());
    }

    public static long getTickCount() {
        return SystemClock.uptimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onTimer() {
        this.mCurSta = Mcu.GetSwKeyState();
        this.mCurKeyNow = Mcu.GetSwKeyNow();
        boolean fgUpdate = false;
        if (this.mCurKeyNow != this.mOldKeyNow) {
            fgUpdate = true;
            this.mOldKeyNow = this.mCurKeyNow;
            Log.e(TAG, "study  mCurKeyNow id = " + this.mCurKeyNow);
            if (255 != this.mCurKeyNow) {
                this.mfgStudyNow = true;
                this.mfgFlashSel = true;
                this.mOldTick = getTickCount();
            } else {
                this.mfgStudyNow = false;
            }
        }
        if (this.mfgStudyNow) {
            this.mCurTick = getTickCount();
            if (this.mCurTick > this.mOldTick + 300) {
                this.mOldTick = this.mCurTick;
                if (this.mCurKeyNow < this.mBtnStudy.length) {
                    this.mBtnStudy[this.mCurKeyNow].setSelected(this.mfgFlashSel);
                }
                this.mfgFlashSel = !this.mfgFlashSel;
            }
        }
        if (this.mCurSta != this.mOldSta) {
            fgUpdate = true;
            this.mOldSta = this.mCurSta;
            Log.e(TAG, "mCurSta = " + Integer.toBinaryString(this.mCurSta));
        }
        if (fgUpdate) {
            int keyCnt = 0;
            int i = 0;
            while (i < mStudyId.length && i < this.mBtnStudy.length) {
                if (1 == ((this.mCurSta >> i) & 1)) {
                    this.mBtnStudy[i].setSelected(true);
                    keyCnt++;
                } else if (i != this.mCurKeyNow) {
                    this.mBtnStudy[i].setSelected(false);
                }
                i++;
            }
            Log.e(TAG, "KeyCnd = " + keyCnt);
        }
    }

    public void UserAll() {
        onTimer();
    }
}
