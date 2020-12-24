package com.ts.can.bmw.withcd;

import android.app.StatusBarManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanFunc;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;

public class CanBmwWithCDExdActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener, View.OnClickListener {
    public static final String TAG = "CanBmwWithCDExdActivity";
    public static int mOldBtSta = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private RelativeLayoutManager mManager;
    private StatusBarManager mStatusBarManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setOnTouchListener(this);
        this.mManager.GetLayout().setClickable(true);
        this.mStatusBarManager = (StatusBarManager) getSystemService("statusbar");
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
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        if (!mfgAutoEnt) {
            Log.d(TAG, "BmwWithCDModeChange Exd Enter");
            CanJni.BmwWithCDCarSet(162, 1, CanBmwWithCDCarInfoActivity.IsBmwType(), 0);
        }
        mfgShow = true;
        mfgFinish = false;
        Log.d(TAG, "onResume");
        this.mStatusBarManager.disable(65536);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        mfgShow = false;
        mfgAutoEnt = false;
        Log.d(TAG, "onPause");
        Log.d(TAG, "BmwWithCDModeChange Exd Exit");
        CanJni.BmwWithCDCarSet(144, CanBmwWithCDCarInfoActivity.IsBmwAuxType(), 0, 0);
        this.mStatusBarManager.disable(0);
    }

    public static void showBmwWithCDWin() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            CanFunc.showCanActivity(CanBmwWithCDExdActivity.class);
        }
    }

    public static void entBmwWithCDMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanBmwWithCDExdActivity.class);
        }
    }

    public static void finishBmwWithCDWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public static boolean IsBmwWithCDWin() {
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
            Log.d(TAG, "onTouch ACTION_DOWN event.getX() = " + event.getX());
            Log.d(TAG, "onTouch ACTION_DOWN event.getY() = " + event.getY());
            CanJni.BmwWithCDSetTouchKey(1, (int) event.getX(), (int) event.getY());
        } else if (event.getAction() == 1) {
            Log.d(TAG, "onTouch ACTION_UP event.getX() = " + event.getX());
            Log.d(TAG, "onTouch ACTION_UP event.getY() = " + event.getY());
            CanJni.BmwWithCDSetTouchKey(0, (int) event.getX(), (int) event.getY());
        }
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public static void Init() {
        int temp;
        if (FtSet.Getlgb5() != 165) {
            FtSet.Setlgb1(1);
            FtSet.Setlgb4(0);
            FtSet.Setlgb5(165);
        }
        int temp2 = FtSet.Getlgb1();
        if (FtSet.GetCamTrack() > 0) {
            temp = temp2 & 191;
        } else {
            temp = temp2 | 64;
        }
        CanJni.BmwWithCDCarSet(149, temp, 0, 0);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CanJni.BmwWithCDCarSet(162, 0, CanBmwWithCDCarInfoActivity.IsBmwType(), 0);
        if (FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(0);
        }
    }

    public static void DealDevEvent() {
    }
}
