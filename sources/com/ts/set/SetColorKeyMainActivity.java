package com.ts.set;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;

public class SetColorKeyMainActivity extends Activity implements UserCallBack, SeekBar.OnSeekBarChangeListener {
    private static final String TAG = "SetColorKeyMainActivity";
    private int[] ColorDnIcon;
    ParamButton[] ColorKeyBtn = new ParamButton[9];
    private int[] ColorUpIcon;
    RelativeLayoutManager ColorkeyManage;
    int[] SetColor;
    SeekBar[] mSeekBar = new SeekBar[3];
    TextView[] mShowInfo = new TextView[3];
    TextView[] mShowInfo2 = new TextView[3];
    int[] nColor;

    public SetColorKeyMainActivity() {
        int[] iArr = new int[27];
        iArr[0] = 255;
        iArr[4] = 255;
        iArr[8] = 255;
        iArr[9] = 255;
        iArr[10] = 153;
        iArr[11] = 204;
        iArr[12] = 204;
        iArr[13] = 255;
        iArr[14] = 153;
        iArr[15] = 107;
        iArr[16] = 204;
        iArr[17] = 255;
        iArr[18] = 255;
        iArr[19] = 255;
        iArr[21] = 255;
        iArr[22] = 255;
        iArr[23] = 255;
        iArr[24] = 255;
        iArr[25] = 102;
        this.SetColor = iArr;
        this.ColorUpIcon = new int[]{R.drawable.set_qcd_01_up, R.drawable.set_qcd_02_up, R.drawable.set_qcd_03_up, R.drawable.set_qcd_04_up, R.drawable.set_qcd_05_up, R.drawable.set_qcd_06_up, R.drawable.set_qcd_07_up, R.drawable.set_qcd_08_up, R.drawable.set_qcd_09_up};
        this.ColorDnIcon = new int[]{R.drawable.set_qcd_01_dn, R.drawable.set_qcd_02_dn, R.drawable.set_qcd_03_dn, R.drawable.set_qcd_04_dn, R.drawable.set_qcd_05_dn, R.drawable.set_qcd_06_dn, R.drawable.set_qcd_07_dn, R.drawable.set_qcd_08_dn, R.drawable.set_qcd_09_dn};
        this.nColor = new int[3];
    }

    /* access modifiers changed from: package-private */
    public void ShowSelectBtn(int nNum) {
        for (int i = 0; i < 9; i++) {
            this.ColorKeyBtn[i].setSelected(false);
        }
        this.ColorKeyBtn[nNum].setSelected(true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_color_key_main);
        this.ColorkeyManage = new RelativeLayoutManager(this, R.id.set_color_mainid);
        this.mSeekBar[0] = (SeekBar) findViewById(R.id.seekBarR);
        this.mSeekBar[1] = (SeekBar) findViewById(R.id.seekBarG);
        this.mSeekBar[2] = (SeekBar) findViewById(R.id.seekBarB);
        for (int i = 0; i < 3; i++) {
            this.mShowInfo[i] = this.ColorkeyManage.AddText(120, (i * 71) + 170);
            this.mShowInfo[i].setTextColor(-1);
            this.mShowInfo[i].setTextSize(20.0f);
            this.mSeekBar[i].setTag(Integer.valueOf(i));
            this.mSeekBar[i].setOnSeekBarChangeListener(this);
            this.mShowInfo2[i] = this.ColorkeyManage.AddText(470, (i * 71) + 170);
            this.mShowInfo2[i].setTextColor(-1);
            this.mShowInfo2[i].setTextSize(20.0f);
        }
        this.mShowInfo[0].setText("R:");
        this.mShowInfo[1].setText("G:");
        this.mShowInfo[2].setText("B:");
        for (int i2 = 0; i2 < 3; i2++) {
            for (int j = 0; j < 3; j++) {
                int k = (i2 * 3) + j;
                this.ColorKeyBtn[k] = this.ColorkeyManage.AddButton((j * 125) + CanCameraUI.BTN_TRUMPCHI_GS7_MODE1, (i2 * 72) + Can.CAN_HONDA_WC, 115, 62);
                this.ColorKeyBtn[k].setDrawable(this.ColorUpIcon[k], this.ColorDnIcon[k]);
                this.ColorKeyBtn[k].setTag(Integer.valueOf(k));
                this.ColorKeyBtn[k].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        int nTag = ((Integer) arg0.getTag()).intValue();
                        SetColorKeyMainActivity.this.ShowSelectBtn(nTag);
                        if (nTag < 9 && nTag >= 0) {
                            for (int i = 0; i < 3; i++) {
                                SetColorKeyMainActivity.this.nColor[i] = SetColorKeyMainActivity.this.SetColor[(nTag * 3) + i];
                                SetColorKeyMainActivity.this.SetColor(i, SetColorKeyMainActivity.this.nColor[i]);
                                SetColorKeyMainActivity.this.SetColor(i, 256);
                                SetColorKeyMainActivity.this.mSeekBar[i].setProgress(SetColorKeyMainActivity.this.nColor[i]);
                                SetColorKeyMainActivity.this.mShowInfo2[i].setText(" " + SetColorKeyMainActivity.this.nColor[i]);
                            }
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void SetColor(int nMode, int nColor2) {
        switch (nMode) {
            case 0:
                FtSet.SetIllR(nColor2);
                return;
            case 1:
                FtSet.SetIllG(nColor2);
                return;
            case 2:
                FtSet.SetIllB(nColor2);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public int GetColor(int nColor2) {
        switch (nColor2) {
            case 0:
                return FtSet.GetIllR();
            case 1:
                return FtSet.GetIllG();
            case 2:
                return FtSet.GetIllB();
            default:
                return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void UpdateSeekBar() {
        for (int i = 0; i < 3; i++) {
            if (this.nColor[i] != GetColor(i)) {
                this.nColor[i] = GetColor(i);
                this.mSeekBar[i].setProgress(this.nColor[i]);
                this.mShowInfo2[i].setText(" " + this.nColor[i]);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        for (int i = 0; i < 3; i++) {
            this.nColor[i] = 65535;
        }
        UpdateSeekBar();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void UserAll() {
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int nTag = ((Integer) seekBar.getTag()).intValue();
        Log.i(TAG, "nTag=" + nTag + "progress==" + progress);
        if (progress != this.nColor[nTag]) {
            this.nColor[nTag] = progress;
            SetColor(nTag, progress);
            this.mShowInfo2[nTag].setText(" " + this.nColor[nTag]);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        int nTag = ((Integer) seekBar.getTag()).intValue();
        Log.i(TAG, "onStopTrackingTouch nTag=" + nTag);
        SetColor(nTag, 256);
    }
}
