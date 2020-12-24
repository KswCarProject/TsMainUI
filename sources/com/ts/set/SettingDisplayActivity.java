package com.ts.set;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.WinShow;
import com.ts.set.setview.SetDisplayItemProgressList;
import com.ts.set.setview.SetItemProgressList;
import com.ts.set.setview.SetMainItemNoIcon;
import com.ts.set.setview.SetMainItemSw;
import com.ts.set.setview.SetMainItemTopName;
import com.ts.set.setview.UISetSroView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.StSet;

public class SettingDisplayActivity extends Activity implements UserCallBack, SetItemProgressList.onPosChange, CompoundButton.OnCheckedChangeListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private static final String TAG = "SettingDisplayActivity";
    SetMainItemNoIcon KeyColor;
    SetMainItemSw autolight;
    SetDisplayItemProgressList daylight;
    int nDayLight;
    int nNightLight;
    SetDisplayItemProgressList nightlight;
    private String[] setDisplayOptions;
    SetMainItemTopName topname;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UISetSroView.Inint(this);
        initSetDisplayOptions();
        UISetSroView.Show(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.daylight.SetCurVal(StSet.GetBLDay());
        this.nightlight.SetCurVal(StSet.GetBLNight());
        this.nDayLight = StSet.GetBLDay();
        this.nNightLight = StSet.GetBLNight();
        this.autolight.SetSwitch(StSet.GetBLIll());
        MainTask.GetInstance().SetUserCallBack(this);
        super.onResume();
    }

    private boolean isHaveOption(int setOption) {
        if (setOption != 3 || FtSet.GetOptionIll() == 1) {
            return true;
        }
        return false;
    }

    private void initSetDisplayOptions() {
        this.topname = new SetMainItemTopName(this, getResources().getStringArray(R.array.set_main_options)[1]);
        this.topname.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingDisplayActivity.this.finish();
            }
        });
        UISetSroView.AddView(this.topname.GetView(), 1280, 80);
        this.setDisplayOptions = getResources().getStringArray(R.array.set_display_options);
        for (int setOpt = 0; setOpt < 4; setOpt++) {
            if (isHaveOption(setOpt)) {
                switch (setOpt) {
                    case 0:
                        this.daylight = new SetDisplayItemProgressList((Context) this, this.setDisplayOptions[setOpt]);
                        this.daylight.SetMinMax(0, 6);
                        this.daylight.SetIdCallBack(setOpt, this);
                        UISetSroView.AddView(this.daylight.GetView(), 1280, 87);
                        break;
                    case 1:
                        this.nightlight = new SetDisplayItemProgressList((Context) this, this.setDisplayOptions[setOpt]);
                        this.nightlight.SetMinMax(0, 6);
                        this.nightlight.SetIdCallBack(setOpt, this);
                        UISetSroView.AddView(this.nightlight.GetView(), 1280, 87);
                        break;
                    case 2:
                        this.autolight = new SetMainItemSw(this, this.setDisplayOptions[setOpt]);
                        this.autolight.SetUserCallback(setOpt, this);
                        UISetSroView.AddView(this.autolight.GetView(), 1280, 87);
                        break;
                    case 3:
                        this.KeyColor = new SetMainItemNoIcon(this, this.setDisplayOptions[setOpt]);
                        this.KeyColor.SetUserCallback(setOpt, this);
                        if (!isHaveOption(setOpt)) {
                            break;
                        } else {
                            UISetSroView.AddView(this.KeyColor.GetView(), 1280, 87);
                            break;
                        }
                }
            }
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                this.daylight.SetCurVal(pos);
                StSet.SetBLDay(pos);
                return;
            case 1:
                this.nightlight.SetCurVal(pos);
                StSet.SetBLNight(pos);
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(CompoundButton arg0, boolean bRet) {
        int i = 1;
        switch (((Integer) arg0.getTag()).intValue()) {
            case 2:
                Log.i(TAG, "bRet=" + bRet);
                StSet.SetBLIllSwitch(bRet ? 1 : 2);
                SetMainItemSw setMainItemSw = this.autolight;
                if (!bRet) {
                    i = 0;
                }
                setMainItemSw.SetSwitch(i);
                return;
            default:
                return;
        }
    }

    public void onClick(View arg0) {
        switch (((Integer) arg0.getTag()).intValue()) {
            case 3:
                Log.i(TAG, "onClick=");
                WinShow.show("com.ts.MainUI", "com.ts.set.SetColorKeyMainActivity");
                return;
            default:
                return;
        }
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (((Integer) seekBar.getTag()).intValue()) {
            case 0:
                this.daylight.SetCurVal(progress);
                Log.d("hdd", "progress = " + progress);
                StSet.SetBLDay(progress);
                return;
            case 1:
                this.nightlight.SetCurVal(progress);
                StSet.SetBLNight(progress);
                return;
            default:
                return;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void UserAll() {
        if (this.nDayLight != StSet.GetBLDay()) {
            this.nDayLight = StSet.GetBLDay();
            this.daylight.SetCurVal(this.nDayLight);
        }
        if (this.nNightLight != StSet.GetBLNight()) {
            this.nNightLight = StSet.GetBLNight();
            this.nightlight.SetCurVal(this.nNightLight);
        }
    }
}
