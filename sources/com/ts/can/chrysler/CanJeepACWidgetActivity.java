package com.ts.can.chrysler;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;

public class CanJeepACWidgetActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    private static final int[] mValues = {R.string.can_off, R.string.can_cdjd, R.string.can_cdjg, R.string.can_cdzj};
    public static boolean mfgShow = false;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private Button mBtnLtHot;
    private Button mBtnLtWind;
    private Button mBtnRearMirror;
    private Button mBtnRtHot;
    private Button mBtnRtWind;
    private Button mBtnSwHot;
    private CanDataInfo.ChrOthSetData mSetData = new CanDataInfo.ChrOthSetData();
    private TextView mTvLtChairHot;
    private TextView mTvLtChairWind;
    private TextView mTvLtHot;
    private TextView mTvLtWind;
    private TextView mTvMirrorDim;
    private TextView mTvRtChairHot;
    private TextView mTvRtChairWind;
    private TextView mTvRtHot;
    private TextView mTvRtWind;
    private TextView mTvSetUp;
    private TextView mTvSwHot;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeep_ac_widget);
        initViews();
    }

    private void initViews() {
        this.mTvLtHot = (TextView) findViewById(R.id.tv_lt_hot);
        this.mTvLtWind = (TextView) findViewById(R.id.tv_lt_wind);
        this.mTvRtHot = (TextView) findViewById(R.id.tv_rt_hot);
        this.mTvRtWind = (TextView) findViewById(R.id.tv_rt_wind);
        this.mTvLtChairHot = (TextView) findViewById(R.id.tv_lt_chairhot);
        this.mTvRtChairHot = (TextView) findViewById(R.id.tv_rt_chairhot);
        this.mTvLtChairWind = (TextView) findViewById(R.id.tv_lt_chairwind);
        this.mTvRtChairWind = (TextView) findViewById(R.id.tv_rt_chairwind);
        this.mTvSwHot = (TextView) findViewById(R.id.tv_sw_hot);
        this.mTvSetUp = (TextView) findViewById(R.id.tv_setup);
        this.mTvMirrorDim = (TextView) findViewById(R.id.tv_mirror_dimmer);
        this.mBtnLtHot = (Button) findViewById(R.id.btn_lt_hot);
        this.mBtnLtHot.setOnClickListener(this);
        this.mBtnLtHot.setBackground(getStateListDrawable(R.drawable.jeep_kj_chair01_up, R.drawable.jeep_kj_chair01_dn));
        this.mBtnLtWind = (Button) findViewById(R.id.btn_lt_wind);
        this.mBtnLtWind.setOnClickListener(this);
        this.mBtnLtWind.setBackground(getStateListDrawable(R.drawable.jeep_kj_chair02_up, R.drawable.jeep_kj_chair02_dn));
        this.mBtnSwHot = (Button) findViewById(R.id.btn_sw_hot);
        this.mBtnSwHot.setOnClickListener(this);
        this.mBtnSwHot.setBackground(getStateListDrawable(R.drawable.jeep_kj_fxp_up, R.drawable.jeep_kj_fxp_dn));
        this.mBtnRtHot = (Button) findViewById(R.id.btn_rt_hot);
        this.mBtnRtHot.setOnClickListener(this);
        this.mBtnRtHot.setBackground(getStateListDrawable(R.drawable.jeep_kj_chair03_up, R.drawable.jeep_kj_chair03_dn));
        this.mBtnRtWind = (Button) findViewById(R.id.btn_rt_wind);
        this.mBtnRtWind.setOnClickListener(this);
        this.mBtnRtWind.setBackground(getStateListDrawable(R.drawable.jeep_kj_chair04_up, R.drawable.jeep_kj_chair04_dn));
        this.mBtnRearMirror = (Button) findViewById(R.id.btn_rear_mirror);
        this.mBtnRearMirror.setOnClickListener(this);
        this.mBtnRearMirror.setBackground(getStateListDrawable(R.drawable.jeep_kj_hsj_up, R.drawable.jeep_kj_hsj_dn));
        Button btnSettings = (Button) findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(this);
        btnSettings.setBackground(getStateListDrawable(R.drawable.jeep_kj_set_up, R.drawable.jeep_kj_set_dn));
        Button btnClose = (Button) findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        btnClose.setBackground(getStateListDrawable(R.drawable.jeep_kj_del_up, R.drawable.jeep_kj_del_dn));
        this.mACInfo = Can.mACInfo;
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_lt_hot) {
            CanJni.ChrOthACCtrl(1, 17);
        } else if (id == R.id.btn_rt_hot) {
            CanJni.ChrOthACCtrl(1, 18);
        } else if (id == R.id.btn_lt_wind) {
            CanJni.ChrOthACCtrl(1, 22);
        } else if (id == R.id.btn_rt_wind) {
            CanJni.ChrOthACCtrl(1, 23);
        } else if (id == R.id.btn_sw_hot) {
            CanJni.ChrOthACCtrl(1, 24);
        } else if (id == R.id.btn_rear_mirror) {
            CanJni.ChrOthCarSet(39, Neg(this.mSetData.Hsjtgj));
        } else if (id == R.id.btn_settings) {
            enterSubWin(CanChrOthCarInfoActivity.class);
        } else if (id == R.id.btn_close) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        QueryData();
        ResetData(false);
        mfgShow = true;
    }

    private void ResetData(boolean check) {
        Can.updateAC();
        if (!check || Can.mACInfo.Update != 0) {
            updateACUI();
        }
        CanJni.ChrOthGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.LightsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.LightsUpdate)) {
            this.mSetData.LightsUpdate = 0;
            this.mBtnRearMirror.setSelected(i2b(this.mSetData.Hsjtgj));
        }
    }

    private void updateACUI() {
        this.mACInfo = Can.mACInfo;
        updateHot(this.mACInfo.nLtChairHot, true);
        updateHot(this.mACInfo.nRtChairHot, false);
        updateWind(this.mACInfo.nLtChairWind, true);
        updateWind(this.mACInfo.nRtChairWind, false);
        this.mBtnSwHot.setSelected(i2b(this.mACInfo.fgWheelHot));
    }

    private void QueryData() {
        CanJni.ChrOthQuery(33, 0, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        mfgShow = false;
    }

    public void UserAll() {
        ResetData(true);
    }

    private void updateHot(int value, boolean isLeft) {
        boolean z = true;
        if (value <= mValues.length) {
            int textId = mValues[value];
            if (isLeft) {
                this.mTvLtHot.setText(textId);
                Button button = this.mBtnLtHot;
                if (value == 0) {
                    z = false;
                }
                button.setSelected(z);
                return;
            }
            this.mTvRtHot.setText(textId);
            Button button2 = this.mBtnRtHot;
            if (value == 0) {
                z = false;
            }
            button2.setSelected(z);
        }
    }

    private void updateWind(int value, boolean isLeft) {
        boolean z = true;
        if (value <= mValues.length) {
            int textId = mValues[value];
            if (isLeft) {
                this.mTvLtWind.setText(textId);
                Button button = this.mBtnLtWind;
                if (value == 0) {
                    z = false;
                }
                button.setSelected(z);
                return;
            }
            this.mTvRtWind.setText(textId);
            Button button2 = this.mBtnRtWind;
            if (value == 0) {
                z = false;
            }
            button2.setSelected(z);
        }
    }

    public static boolean IsCanJeepACWidgetWin() {
        return mfgShow;
    }

    private void setTextAttrs(Button v, int str, int paddingRight) {
        v.setText(str);
        v.setTextColor(Color.parseColor("#dddddd"));
        v.setTextSize(0, 30.0f);
        v.setGravity(21);
        v.setPadding(0, 0, paddingRight, 0);
        v.setLineSpacing(2.0f, 1.0f);
    }

    private StateListDrawable getStateListDrawable(int normal, int pressed) {
        StateListDrawable stateDrawable = new StateListDrawable();
        Resources res = getResources();
        Drawable selectedDrawable = res.getDrawable(pressed);
        Drawable pressedDrawable = res.getDrawable(pressed);
        Drawable normalDrawable = res.getDrawable(normal);
        stateDrawable.addState(new int[]{16842913}, selectedDrawable);
        stateDrawable.addState(new int[]{16842919}, pressedDrawable);
        stateDrawable.addState(new int[0], normalDrawable);
        return stateDrawable;
    }
}
