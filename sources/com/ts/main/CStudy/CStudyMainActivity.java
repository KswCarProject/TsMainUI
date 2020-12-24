package com.ts.main.CStudy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainCScreen;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.FtSet;

public class CStudyMainActivity extends Activity implements UserCallBack, SeekBar.OnSeekBarChangeListener {
    static int Step = 0;
    private static final String TAG = "MainCScreen";
    static int nTickNum = 0;
    ParamButton[] BtnCstudy = new ParamButton[23];
    RelativeLayout MyRelativeLayout = null;
    TextView ShowTouchSize;
    TextView[] TextShow = new TextView[23];
    SeekBar TouSizeSeek;
    private String[] setOptions;

    /* access modifiers changed from: protected */
    public void setViewPos(View view, int x, int y, int w, int h) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.MyRelativeLayout.addView(view);
    }

    /* access modifiers changed from: protected */
    public TextView TvCreateRelative(int x, int y, int w, int h) {
        TextView tv = new TextView(this);
        setViewPos(tv, x, y, w, h);
        return tv;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MainSet.GetScreenType() == 3 || MainSet.GetScreenType() == 6 || MainSet.GetScreenType() == 5) {
            setContentView(R.layout.activity_cstudy_main_v);
        } else {
            setContentView(R.layout.activity_cstudy_main);
        }
        this.setOptions = getResources().getStringArray(R.array.ctouch_general);
        this.MyRelativeLayout = (RelativeLayout) findViewById(R.id.activity_cstudy_mainid);
        this.TouSizeSeek = (SeekBar) findViewById(R.id.Touch_seekBar1);
        this.TouSizeSeek.setOnSeekBarChangeListener(this);
        this.ShowTouchSize = (TextView) findViewById(R.id.Touch_seekBarsize);
        this.ShowTouchSize.setTextColor(-1);
        ParamButton.initFactory(this, this.MyRelativeLayout, 0);
        for (int i = 0; i < 23; i++) {
            if (MainSet.GetScreenType() == 3 || MainSet.GetScreenType() == 6) {
                int njiage = (((MainUI.mScrH - 520) - 100) / 3) + 130;
                if (i == 22) {
                    this.TextShow[i] = TvCreateRelative(MainUI.mScrH - 150, 588, 130, 76);
                    this.BtnCstudy[i] = ParamButton.fsCreateRelative((njiage * 3) + 50, ((i / 4) * 110) + 72, 130, 76);
                } else {
                    this.TextShow[i] = TvCreateRelative(((i % 4) * njiage) + 50, ((i / 4) * 110) + 148, 130, 76);
                    this.BtnCstudy[i] = ParamButton.fsCreateRelative(((i % 4) * njiage) + 50, ((i / 4) * 110) + 72, 130, 76);
                }
            } else if (i == 22) {
                this.TextShow[i] = TvCreateRelative(800, 478, 130, 76);
                this.BtnCstudy[i] = ParamButton.fsCreateRelative(800, ((i / 6) * 110) + 10, 130, 76);
            } else {
                this.TextShow[i] = TvCreateRelative(((i % 6) * Can.CAN_JAC_REFINE_OD) + 50, ((i / 6) * 110) + 86, 130, 76);
                this.BtnCstudy[i] = ParamButton.fsCreateRelative(((i % 6) * Can.CAN_JAC_REFINE_OD) + 50, ((i / 6) * 110) + 10, 130, 76);
            }
            this.TextShow[i].setTextSize(12.0f);
            this.BtnCstudy[i].setStateDrawable(R.drawable.cstudy_public_up, R.drawable.cstudy_public_dn, R.drawable.cstudy_public_dn);
            this.BtnCstudy[i].setIntParam(i + 1);
            this.BtnCstudy[i].setTextColor(-16777216);
            this.BtnCstudy[i].setTextSize(15.0f);
            this.BtnCstudy[i].setText(this.setOptions[i]);
            this.BtnCstudy[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int id = ((ParamButton) v).getIntParam();
                    if (id <= 22) {
                        MainCScreen.GetInstance().SetNawStudyID(id);
                    } else {
                        MainCScreen.GetInstance().ClearAllKey();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        this.TouSizeSeek.setProgress(MainCScreen.GetInstance().nTouchLen);
        this.ShowTouchSize.setText(new StringBuilder().append(MainCScreen.GetInstance().nTouchLen).toString());
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void UserAll() {
        for (int i = 0; i < 22; i++) {
            if (MainCScreen.GetInstance().IsStudy(i) == 1) {
                this.BtnCstudy[i].setSelected(true);
                this.TextShow[i].setText(MainCScreen.GetInstance().GetShowString(i));
            } else {
                this.TextShow[i].setText("");
                if (MainCScreen.GetInstance().GetStudyID() == this.BtnCstudy[i].getIntParam()) {
                    nTickNum++;
                    if (nTickNum % 15 == 0) {
                        if (Step == 0) {
                            this.BtnCstudy[i].setSelected(true);
                            Step = 1;
                        } else {
                            this.BtnCstudy[i].setSelected(false);
                            Step = 0;
                        }
                    }
                } else {
                    this.BtnCstudy[i].setSelected(false);
                }
            }
        }
    }

    public void onProgressChanged(SeekBar seekbar, int progress, boolean frpmTouch) {
        if (progress >= 5) {
            MainCScreen.GetInstance().nTouchLen = progress;
            this.ShowTouchSize.setText(new StringBuilder().append(MainCScreen.GetInstance().nTouchLen).toString());
        }
    }

    public void onStartTrackingTouch(SeekBar arg0) {
    }

    public void onStopTrackingTouch(SeekBar arg0) {
        FtSet.SetCtErr(MainCScreen.GetInstance().nTouchLen);
        FtSet.Save(0);
        Log.e(TAG, "onStopTrackingTouch nTouchLen ==" + MainCScreen.GetInstance().nTouchLen);
    }
}
