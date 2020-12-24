package com.ts.MainUI;

import android.util.Log;

public class MainTask {
    private static String TAG = "MainTask";
    private static MainTask mMainTask = new MainTask();
    protected TaskCallBack mTaskCallBack;
    protected UserCallBack mUserCallBack;

    public static MainTask GetInstance() {
        return mMainTask;
    }

    public void SetUserCallBack(UserCallBack cb) {
        Log.i(TAG, "cb==" + cb);
        this.mUserCallBack = cb;
    }

    public void SetTaskCallBack(TaskCallBack cb) {
        this.mTaskCallBack = cb;
    }
}
