package com.ts.can.volvo.xfy;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanFunc;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.Mcu;

public class CanVolvoXfyCarDeviceActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener, View.OnClickListener {
    public static final String TAG = "CanVolvoXfyCarDeviceActivity";
    public static int mOldBtSta = 0;
    public static int mOldMode = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private AutoFitTextureView mCameraView;
    private RelativeLayoutManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        Log.d(TAG, "onCreate");
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setContentView(R.layout.activity_can_withcd_base);
        this.mManager = new RelativeLayoutManager(this, R.id.can_withcd_base_layout);
        this.mManager.GetLayout().setOnTouchListener(this);
        this.mManager.GetLayout().setClickable(true);
    }

    public ParamButton addButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    public ParamButton addButton(int x, int y, int w, int h, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        onCreate((Bundle) null);
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        if (!mfgAutoEnt) {
            Evc.GetInstance().evol_workmode_set(12);
            Log.d(TAG, "WORKMODE_EXD");
        }
        mfgShow = true;
        mfgFinish = false;
        Log.d(TAG, "onResume");
        TsDisplay.GetInstance().SetDispParat(-1);
        if (CanJni.GetSubType() == 0) {
            MainSet.GetInstance().SetVideoChannel(2);
        } else {
            MainSet.GetInstance().SetVideoChannel(0);
        }
        this.mCameraView = (AutoFitTextureView) findViewById(R.id.DevtextureView);
        BackcarService.getInstance().StartCamera(this.mCameraView, false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        mfgShow = false;
        mfgAutoEnt = false;
        Log.d(TAG, "onPause");
        BackcarService.getInstance().StopCamera();
    }

    public static void showVolvoXfyWin() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            CanFunc.showCanActivity(CanVolvoXfyCarDeviceActivity.class);
        }
    }

    public static void entVolvoXfyMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanVolvoXfyCarDeviceActivity.class);
        }
    }

    public static void finishVolvoXfyWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public static boolean IsVolvoXfyWin() {
        return mfgShow;
    }

    private void ResetData(boolean check) {
    }

    public void UserAll() {
        ResetData(true);
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            finish();
        }
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        if (event.getAction() == 0 || event.getAction() == 2) {
            Log.d(TAG, "onTouch ACTION_DOWN event.getX() = " + 0);
            Log.d(TAG, "onTouch ACTION_DOWN event.getY() = " + 0);
            return false;
        } else if (event.getAction() != 1) {
            return false;
        } else {
            Log.d(TAG, "onTouch ACTION_UP event.getX() = " + 0);
            Log.d(TAG, "onTouch ACTION_UP event.getY() = " + 0);
            Mcu.SetCkey(8);
            return false;
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public static void Init() {
    }

    public static void DealDevEvent() {
    }
}
