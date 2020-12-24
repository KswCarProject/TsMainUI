package com.ts.set;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import com.ts.MainUI.R;
import com.ts.main.common.WinShow;
import com.ts.set.setview.SetItemProgressList;
import com.ts.set.setview.SetMainItemNoIcon;
import com.ts.set.setview.SetMainItemSw;
import com.ts.set.setview.SetMainItemTopName;
import com.ts.set.setview.UISetSroView;
import com.yyw.ts70xhw.StSet;

public class SettingGpsActivity extends Activity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private static final String TAG = "SettingGpsActivity";
    SetMainItemSw AudoNavi;
    byte[] Name = new byte[128];
    SetItemProgressList NaviMix;
    SetMainItemNoIcon NaviPathChoose;
    private String[] setNaviOptions;
    SetMainItemTopName topname;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UISetSroView.Inint(this);
        initGpsOptions();
        UISetSroView.Show(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.AudoNavi.SetSwitch(StSet.GetAutoNavi());
        StSet.GetNaviName(this.Name);
        this.NaviMix.SetCurVal(StSet.Getmvwns());
        this.NaviPathChoose.ShowEndInfo(new String(this.Name));
        super.onResume();
    }

    private void initGpsOptions() {
        this.topname = new SetMainItemTopName(this, getResources().getStringArray(R.array.set_main_options)[5]);
        this.topname.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingGpsActivity.this.finish();
            }
        });
        UISetSroView.AddView(this.topname.GetView(), 1280, 80);
        this.setNaviOptions = getResources().getStringArray(R.array.set_navi_options);
        for (int setOpt = 0; setOpt < 3; setOpt++) {
            switch (setOpt) {
                case 0:
                    this.AudoNavi = new SetMainItemSw(this, this.setNaviOptions[setOpt]);
                    this.AudoNavi.SetUserCallback(setOpt, this);
                    UISetSroView.AddView(this.AudoNavi.GetView(), 1280, 87);
                    break;
                case 1:
                    this.NaviPathChoose = new SetMainItemNoIcon(this, this.setNaviOptions[setOpt]);
                    this.NaviPathChoose.SetUserCallback(setOpt, this);
                    UISetSroView.AddView(this.NaviPathChoose.GetView(), 1280, 87);
                    break;
                case 2:
                    this.NaviMix = new SetItemProgressList((Context) this, this.setNaviOptions[2]);
                    this.NaviMix.SetMinMax(0, 100);
                    this.NaviMix.SetIdCallBack(setOpt, this);
                    UISetSroView.AddView(this.NaviMix.GetView(), 1280, 87);
                    break;
            }
        }
    }

    public void onCheckedChanged(CompoundButton arg0, boolean bRet) {
        int i;
        int i2 = 1;
        switch (((Integer) arg0.getTag()).intValue()) {
            case 0:
                SetMainItemSw setMainItemSw = this.AudoNavi;
                if (bRet) {
                    i = 1;
                } else {
                    i = 0;
                }
                setMainItemSw.SetSwitch(i);
                if (!bRet) {
                    i2 = 0;
                }
                StSet.SetAutoNaviSwitch(i2);
                return;
            default:
                return;
        }
    }

    public void onClick(View arg0) {
        switch (((Integer) arg0.getTag()).intValue()) {
            case 1:
                WinShow.GotoWin(11, 99);
                return;
            default:
                return;
        }
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (((Integer) seekBar.getTag()).intValue()) {
            case 2:
                StSet.Setmvwns(progress);
                this.NaviMix.SetCurVal(progress);
                return;
            default:
                return;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
