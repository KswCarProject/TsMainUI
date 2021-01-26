package com.ts.main.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.Radio;

public class FactoryRadioTestActivity extends Activity implements UserCallBack {
    private static final String TAG = "[scj]Test";
    public static final int TEST_FK = 2;
    public static final int TEST_IO = 1;
    public static final int TEST_NULL = 18;
    public static final int TEST_RIDIO = 0;
    Button BtnStartT;
    int IoMode = 0;
    private int mCurKeyNow;
    private int mCurSta;
    private int mOldKeyNow;
    private int mOldSta;
    private String[] mStrIc = {"ST7786", "NX6686", "Si4702", "QN8035", "474X", "7708"};
    int nStep = 0;
    int nTestCam = 0;
    int nTestIll = 0;
    int nTestKey = 0;
    TextView txeMyTextView;
    TextView txeMyTextView2;
    TextView txeMyTextView3;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factoryradiotest);
        this.txeMyTextView = (TextView) findViewById(R.id.factory_radiotest_item);
        this.txeMyTextView2 = (TextView) findViewById(R.id.factory_radiotest_item2);
        this.txeMyTextView3 = (TextView) findViewById(R.id.factory_radiotest_item3);
        this.BtnStartT = (Button) findViewById(R.id.btn_factory_radiotest);
        this.BtnStartT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switch (FactoryRadioTestActivity.this.nStep) {
                    case 0:
                        FactoryRadioTestActivity.this.nStep = 1;
                        FactoryRadioTestActivity.this.UpdateDisp(1);
                        return;
                    case 1:
                        FactoryRadioTestActivity.this.nStep = 2;
                        FactoryRadioTestActivity.this.UpdateDisp(2);
                        Mcu.SwKeyDelete();
                        return;
                    case 2:
                        if (FactoryRadioTestActivity.this.nTestKey == 0) {
                            Mcu.SwKeyStudy(1);
                            FactoryRadioTestActivity.this.UpdateDisp(2);
                            return;
                        }
                        Mcu.SwKeyStudy(2);
                        FactoryRadioTestActivity.this.UpdateDisp(2);
                        return;
                    case 18:
                        Mcu.SwKeyDelete();
                        FactoryRadioTestActivity.this.enterSubWin(FactorytestvideoActivity.class);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != 0) {
            event.getAction();
            return true;
        } else if (this.nStep != 2) {
            return true;
        } else {
            Mcu.SwKeyDelete();
            enterSubWin(FactorytestvideoActivity.class);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void enterSubWin(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    /* access modifiers changed from: package-private */
    public void UpdateDisp(int nMode) {
        switch (nMode) {
            case 0:
                if (FtSet.GetRadioIc() < 6) {
                    this.txeMyTextView.setText("收音类型" + this.mStrIc[FtSet.GetRadioIc()] + ":信号发生器设定频率");
                } else {
                    this.txeMyTextView.setText("收音测试:信号发生器设定频率");
                }
                this.txeMyTextView2.setText("必须四个通道均能看到稳定波形");
                factory_test.AddToSort("收音测试OK");
                this.BtnStartT.setText("下一步");
                return;
            case 1:
                this.txeMyTextView.setText("外部IO口测试");
                this.txeMyTextView2.setText("请拨动相应的开关");
                this.BtnStartT.setVisibility(4);
                return;
            case 2:
                this.txeMyTextView.setText("方控学习测试");
                this.txeMyTextView2.setText("音量加按键学习中，请按下按键学习");
                this.BtnStartT.setText("开始学习");
                this.BtnStartT.setVisibility(0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        if (Iop.DspSupport() == 1) {
            Evc.GetInstance().evol_vol_set(12);
        } else {
            Evc.GetInstance().evol_vol_set(8);
        }
        this.nStep = 0;
        UpdateDisp(0);
        Radio.TuneSearch(1);
        Evc.GetInstance().evol_workmode_set(1);
        this.IoMode = 0;
        this.nTestCam = Mcu.GetReverse();
        this.nTestIll = Mcu.GetIll();
        this.mOldSta = 0;
        this.nTestKey = 0;
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void UserAll() {
        int val;
        boolean fgUpdate = false;
        switch (this.nStep) {
            case 0:
                this.txeMyTextView3.setText("当前频率=" + Radio.GetDisp(1));
                return;
            case 1:
                switch (this.IoMode) {
                    case 0:
                        this.txeMyTextView3.setText("当前倒车状态==" + this.nTestCam + "请拨动倒车开关");
                        Log.i(TAG, "Mcu.GetReverse() = " + Mcu.GetReverse());
                        if (this.nTestCam != Mcu.GetReverse()) {
                            factory_test.AddToSort("倒车测试OK");
                            this.IoMode = 1;
                            return;
                        }
                        return;
                    case 1:
                        this.txeMyTextView3.setText("当前大灯状态==" + this.nTestIll + "请拨动大灯开关");
                        if (this.nTestIll != Mcu.GetIll()) {
                            factory_test.AddToSort("大灯测试OK");
                            this.nStep = 2;
                            UpdateDisp(2);
                            Mcu.SwKeyDelete();
                            Mcu.SwKeyStudy(1);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            case 2:
                this.mCurSta = Mcu.GetSwKeyState();
                this.mCurKeyNow = Mcu.GetSwKeyNow();
                Log.e(TAG, "mCurSta = " + this.mCurSta);
                Log.e(TAG, "mCurKeyNow = " + this.mCurKeyNow);
                if (this.mCurSta != this.mOldSta) {
                    fgUpdate = true;
                    this.mOldSta = this.mCurSta;
                    Log.e(TAG, "mCurSta = " + Integer.toBinaryString(this.mCurSta));
                }
                if (fgUpdate) {
                    if (this.nTestKey == 0) {
                        val = (this.mCurSta >> 1) & 1;
                    } else {
                        val = (this.mCurSta >> 2) & 1;
                    }
                    if (1 == val) {
                        this.txeMyTextView3.setText("第" + (this.nTestKey + 1) + "个按键学习成功");
                        if (this.nTestKey == 1) {
                            this.nStep = 18;
                            this.BtnStartT.setText("下一步");
                            factory_test.AddToSort("方控测试OK");
                            this.BtnStartT.setVisibility(0);
                            Mcu.SwKeyDelete();
                            enterSubWin(FactorytestvideoActivity.class);
                            return;
                        }
                        this.BtnStartT.setVisibility(0);
                        Mcu.SwKeyStudy(2);
                        this.nTestKey = 1;
                        return;
                    }
                    return;
                }
                this.txeMyTextView3.setText("第" + (this.nTestKey + 1) + "个按键学习中,请按下相应按钮");
                return;
            default:
                return;
        }
    }
}
