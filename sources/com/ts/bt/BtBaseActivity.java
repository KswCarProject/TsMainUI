package com.ts.bt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.SdkConstants;
import com.ts.MainUI.R;
import com.txznet.sdk.TXZResourceManager;

@SuppressLint({"NewApi"})
public class BtBaseActivity extends Activity {
    public static final boolean DEBUG = false;
    private static final String TAG = "BtBaseActivity";
    private static ActivityManager activityManager;
    public static BtBaseActivity baseActivity = null;
    private static Activity curActivity;
    private static int curActivityId;
    static boolean curHaveCall = false;
    public static String mBaseStrDialNo = new String();
    static boolean mIsHaveCall = false;
    public static Toast mToast = null;
    int[] a = new int[100];
    public BtExe bt = BtExe.getBtInstance();
    View.OnClickListener cl = new View.OnClickListener() {
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.btBtnSubConnect) {
                BtBaseActivity.this.showActivity(1);
            } else if (id == R.id.btBtnSubDial) {
                BtBaseActivity.this.showActivity(2);
            } else if (id == R.id.btBtnSubPb) {
                BtBaseActivity.this.showActivity(4);
            } else if (id == R.id.btBtnSubLog) {
                BtBaseActivity.this.showActivity(6);
            } else if (id == R.id.btBtnSubMusic) {
                BtBaseActivity.this.showActivity(7);
            }
        }
    };
    public boolean isShowActivity = false;
    private Button mBtnSubConnect;
    private Button mBtnSubDial;
    private Button mBtnSubLog;
    private Button mBtnSubMusic;
    private Button mBtnSubPb;
    View[] mFocusView;
    protected int mIcoSel = 0;
    public boolean mIsInMultiWindowMode = false;
    public View[] mLtView = new View[5];
    protected View mOldFocusView = null;
    public int mOrientation = 0;
    protected int mbSubFocus = 3;
    protected boolean mfgDialDlg = false;
    Resources res;
    public int showId = 1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager = (ActivityManager) getSystemService(SdkConstants.TAG_ACTIVITY);
        this.res = getResources();
        Intent intent = getIntent();
        if (intent != null) {
            this.isShowActivity = intent.getBooleanExtra("isShowActivity", false);
        }
    }

    public String getRunningActivityName() {
        if (activityManager != null) {
            return ((ActivityManager) getSystemService(SdkConstants.TAG_ACTIVITY)).getRunningTasks(1).get(0).topActivity.getClassName();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void showActivity(int id) {
        Context context;
        overridePendingTransition(0, 0);
        Intent intent = new Intent();
        if (id == 1 || BtExe.getBtInstance().isConnected()) {
            if (curActivity == null) {
                context = getApplicationContext();
            } else {
                context = curActivity;
            }
            Class cls = null;
            switch (id) {
                case 1:
                    cls = BtConnectActivity.class;
                    break;
                case 2:
                    cls = BtDialActivity.class;
                    break;
                case 3:
                    cls = BtCallingActivity.class;
                    break;
                case 4:
                    cls = BtPhoneBookActivity.class;
                    break;
                case 6:
                    cls = BtLogActivity.class;
                    break;
                case 7:
                    cls = BtMusicActivity.class;
                    break;
                case 8:
                    cls = BtSearchActivity.class;
                    break;
            }
            intent.setClass(context, cls);
            if (1 != 0) {
                this.mbSubFocus = 2;
                intent.putExtra("isShowActivity", true);
                startActivity(intent);
                if (curActivity != null && !cls.equals(curActivity.getClass())) {
                    curActivity.finish();
                    curActivity.overridePendingTransition(0, 0);
                    Log.e("finish", "123");
                }
                updateLeftIco(id);
                return;
            }
            return;
        }
        showToast(getApplicationContext(), R.string.bt_connect_first_msg);
    }

    /* access modifiers changed from: package-private */
    public void SubItemsInit(Activity Cur, int id) {
        curActivity = Cur;
        curActivityId = id;
        this.mBtnSubConnect = (Button) curActivity.findViewById(R.id.btBtnSubConnect);
        this.mBtnSubDial = (Button) curActivity.findViewById(R.id.btBtnSubDial);
        this.mBtnSubPb = (Button) curActivity.findViewById(R.id.btBtnSubPb);
        this.mBtnSubLog = (Button) curActivity.findViewById(R.id.btBtnSubLog);
        this.mBtnSubMusic = (Button) curActivity.findViewById(R.id.btBtnSubMusic);
        this.mBtnSubConnect.setOnClickListener(this.cl);
        this.mBtnSubDial.setOnClickListener(this.cl);
        this.mBtnSubPb.setOnClickListener(this.cl);
        this.mBtnSubLog.setOnClickListener(this.cl);
        this.mBtnSubMusic.setOnClickListener(this.cl);
        baseActivity = (BtBaseActivity) Cur;
        this.mLtView[0] = this.mBtnSubConnect;
        this.mLtView[1] = this.mBtnSubDial;
        this.mLtView[2] = this.mBtnSubPb;
        this.mLtView[3] = this.mBtnSubLog;
        this.mLtView[4] = this.mBtnSubMusic;
        updateLeftIco(id);
    }

    /* access modifiers changed from: package-private */
    public void updateLeftIco(int id) {
        if (this.mBtnSubConnect != null) {
            boolean bConnectSel = false;
            boolean bDialtSel = false;
            boolean bPbSel = false;
            boolean bLogSel = false;
            boolean bMusicSel = false;
            switch (id) {
                case 1:
                    bConnectSel = true;
                    this.mIcoSel = 0;
                    break;
                case 2:
                    bDialtSel = true;
                    this.mIcoSel = 1;
                    break;
                case 3:
                    bDialtSel = true;
                    break;
                case 4:
                    bPbSel = true;
                    this.mIcoSel = 2;
                    break;
                case 6:
                    bLogSel = true;
                    this.mIcoSel = 3;
                    break;
                case 7:
                    bMusicSel = true;
                    this.mIcoSel = 4;
                    break;
                case 8:
                    bPbSel = true;
                    this.mIcoSel = 2;
                    break;
            }
            this.showId = id;
            updateFocus(this.mLtView[this.mIcoSel]);
            if (bConnectSel) {
                this.mBtnSubConnect.setSelected(true);
            } else {
                this.mBtnSubConnect.setSelected(false);
            }
            if (bDialtSel) {
                this.mBtnSubDial.setSelected(true);
            } else {
                this.mBtnSubDial.setSelected(false);
            }
            if (bPbSel) {
                this.mBtnSubPb.setSelected(true);
            } else {
                this.mBtnSubPb.setSelected(false);
            }
            if (bLogSel) {
                this.mBtnSubLog.setSelected(true);
            } else {
                this.mBtnSubLog.setSelected(false);
            }
            if (bMusicSel) {
                this.mBtnSubMusic.setSelected(true);
            } else {
                this.mBtnSubMusic.setSelected(false);
            }
        }
    }

    static boolean isCurrentBTConnectedActivity(String strCurActivity) {
        if (strCurActivity.contains("BtDialActivity") || strCurActivity.contains("BtCallingActivity") || strCurActivity.contains("BtPhoneBookActivity") || strCurActivity.contains("BtLogActivity") || strCurActivity.contains("BtMusicActivity")) {
            return true;
        }
        return false;
    }

    public void onTimer() {
        Log.e(TAG, "onTimer");
    }

    public static String getRunningActivityName(Context context) {
        return ((ActivityManager) context.getSystemService(SdkConstants.TAG_ACTIVITY)).getRunningTasks(1).get(0).topActivity.getClassName();
    }

    public static boolean isCurOtherBT(Context context) {
        return isCurrentBTConnectedActivity(getRunningActivityName(context));
    }

    public static void gotoConnectActivity(Context context) {
        if (isCurOtherBT(context) && !BtExe.getBtInstance().isConnected()) {
            Log.d(TAG, "gotoConnectActivity FLAG_ACTIVITY_CLEAR_TASK");
            Intent intent = new Intent();
            intent.setClass(context, BtConnectActivity.class);
            intent.addFlags(268468224);
            context.startActivity(intent);
        }
    }

    public static void updateBtInfo() {
        curHaveCall = BtExe.getBtInstance().isHaveCall();
        if (mIsHaveCall != curHaveCall) {
            mIsHaveCall = curHaveCall;
            if (mIsHaveCall) {
                Log.e(TAG, "mIsHaveCall, Goto BtCallingActivity");
            } else if (!curHaveCall) {
                mBaseStrDialNo = TXZResourceManager.STYLE_DEFAULT;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resetBaseActivity() {
        baseActivity = null;
    }

    /* access modifiers changed from: protected */
    public void testJump() {
        BtExe.getBtInstance();
        Context context = BtExe.getContext();
        Intent intent = new Intent();
        intent.setClass(context, BtCallingActivity.class);
        intent.addFlags(268500992);
        context.startActivity(intent);
        Log.e(TAG, "null == baseActivity, Goto BtCallingActivity");
    }

    public static void showToast(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, 0);
        } else {
            mToast.setText(resId);
        }
        mToast.show();
    }

    /* access modifiers changed from: protected */
    public int GetLtFocusIndex() {
        if (this.mOldFocusView == null) {
            return 0;
        }
        for (int j = 0; j < this.mLtView.length; j++) {
            if (this.mOldFocusView == this.mLtView[j]) {
                return j;
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void LtPrev() {
        int index = GetLtFocusIndex() - 1;
        if (index < 0) {
            index = this.mLtView.length - 1;
        }
        Log.d(TAG, "GetLtFocusIndex LtPrev = " + index);
        updateFocus(this.mLtView[index]);
    }

    /* access modifiers changed from: protected */
    public void LtNext() {
        int index = GetLtFocusIndex() + 1;
        if (index > this.mLtView.length - 1) {
            index = 0;
        }
        Log.d(TAG, "GetLtFocusIndex LtNext = " + index);
        updateFocus(this.mLtView[index]);
    }

    /* access modifiers changed from: protected */
    public void EnterSubFocus() {
    }

    /* access modifiers changed from: protected */
    public void ClearFocus() {
        if (this.mOldFocusView != null) {
            this.mOldFocusView.setFocusableInTouchMode(false);
            this.mOldFocusView.clearFocus();
            this.mOldFocusView = null;
        }
    }

    /* access modifiers changed from: protected */
    public void SetSubFocus() {
    }

    /* access modifiers changed from: protected */
    public void LtCenter() {
        int index = GetLtFocusIndex();
        if (index == this.mIcoSel) {
            EnterSubFocus();
            Log.d(TAG, "LtCenter EnterSubFocus");
            return;
        }
        this.mLtView[index].performClick();
    }

    /* access modifiers changed from: protected */
    public boolean DealLtSel(int key) {
        switch (key) {
            case 19:
                LtPrev();
                return true;
            case 20:
                LtNext();
                return true;
            case 23:
                LtCenter();
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean DealSubKey(int key) {
        switch (key) {
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean DealMySubKey(int key) {
        Log.d(TAG, "DealMySubKey");
        switch (key) {
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                DealSubKey(key);
                return true;
            default:
                return false;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (isInMultiWindowMode()) {
            return true;
        }
        int action = event.getAction();
        int key = event.getKeyCode();
        Log.d(TAG, "action = " + action + ", key = " + key);
        if (action == 0) {
            Log.d(TAG, "mbSubFocus = " + this.mbSubFocus);
            Log.d(TAG, "showId = " + this.showId);
            if (key == 22) {
                if (this.mIcoSel == 0 || this.mIcoSel == 1 || this.mIcoSel == 4 || this.showId == 8) {
                    if (this.mbSubFocus == 2) {
                        return true;
                    }
                    if (this.mbSubFocus == 3) {
                        this.mbSubFocus = 2;
                        updateFocus(this.mLtView[this.mIcoSel]);
                        DealMySubKey(key);
                        return true;
                    }
                } else if (this.mbSubFocus == 1) {
                    return true;
                } else {
                    if (this.mbSubFocus == 2) {
                        this.mbSubFocus = 1;
                        updateFocus(this.mLtView[this.mIcoSel]);
                        DealMySubKey(key);
                        return true;
                    } else if (this.mbSubFocus == 3) {
                        this.mbSubFocus = 2;
                        updateFocus(this.mLtView[this.mIcoSel]);
                        DealMySubKey(key);
                        return true;
                    }
                }
            } else if (key == 21) {
                if (this.showId == 8 || this.mIcoSel == 0 || this.mIcoSel == 1 || this.mIcoSel == 4) {
                    if (this.mbSubFocus == 2) {
                        this.mbSubFocus = 3;
                        updateFocus(this.mLtView[this.mIcoSel]);
                        DealMySubKey(key);
                        return true;
                    } else if (this.mbSubFocus == 3) {
                        return true;
                    }
                } else if (this.mbSubFocus == 1) {
                    this.mbSubFocus = 2;
                    updateFocus(this.mLtView[this.mIcoSel]);
                    DealMySubKey(key);
                    return true;
                } else if (this.mbSubFocus == 2) {
                    this.mbSubFocus = 3;
                    updateFocus(this.mLtView[this.mIcoSel]);
                    DealMySubKey(key);
                    return true;
                } else if (this.mbSubFocus == 3) {
                    return true;
                }
            }
            if (this.mbSubFocus == 2 || this.mbSubFocus == 1) {
                if (DealMySubKey(key)) {
                    return true;
                }
            } else if (DealLtSel(key)) {
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public void updateFocus(View v) {
        if (this.mOldFocusView != null) {
            this.mOldFocusView.setFocusableInTouchMode(false);
        }
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        this.mOldFocusView = v;
    }
}
