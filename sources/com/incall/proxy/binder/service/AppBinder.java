package com.incall.proxy.binder.service;

import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.incall.proxy.binder.service.IAppInterface;
import com.ts.bt.BtExe;
import com.ts.main.common.ShellUtils;

public class AppBinder extends IAppInterface.Stub {
    public static final String TAG = "AppBinder";
    public static IOnActionCallback mIOnActionCallback;
    private static IOnCallStateCallback mIOnCallStateCallback;
    IAppCallbackInterface mIAppCallbackInterface;

    public interface IOnCallStateCallback {
        void onCallStateChanged(int i);
    }

    public void installApp(String appFilePath) throws RemoteException {
        Log.v(TAG, "installApp:" + appFilePath);
        if (this.mIAppCallbackInterface != null) {
            this.mIAppCallbackInterface.installStart(appFilePath);
        }
        String[] commands = {"pm install -r "};
        commands[0] = String.valueOf(commands[0]) + "\"" + appFilePath + "\"";
        ShellUtils.CommandResult result = ShellUtils.execCommand(commands, true);
        if (!TextUtils.isEmpty(result.successMsg)) {
            Log.v(TAG, result.successMsg);
            if (this.mIAppCallbackInterface != null) {
                this.mIAppCallbackInterface.installEnd(appFilePath);
            }
        }
        if (!TextUtils.isEmpty(result.errorMsg)) {
            Log.v(TAG, result.errorMsg);
            if (this.mIAppCallbackInterface != null) {
                this.mIAppCallbackInterface.exception(appFilePath, result.result);
            }
        }
    }

    public void addCallBack(IAppCallbackInterface mIAppCallbackInterface2) throws RemoteException {
        this.mIAppCallbackInterface = mIAppCallbackInterface2;
    }

    public void onCallStateChanged(int state) throws RemoteException {
        Log.d(TAG, "state:" + state);
        if (mIOnCallStateCallback != null) {
            mIOnCallStateCallback.onCallStateChanged(state);
        }
    }

    public static void setOnCallStateCallBack(IOnCallStateCallback iOnCallStateCallback) {
        Log.d(TAG, "setOnCallStateCallBack");
        mIOnCallStateCallback = iOnCallStateCallback;
    }

    public void dial(String number) throws RemoteException {
        Log.d(TAG, "dial:" + number);
        if (BtExe.getCurrentCalls().size() != 0) {
            Log.d(TAG, "dial active");
        } else if (!TextUtils.isEmpty(number)) {
            BtExe.getBtInstance().dial(number);
        }
    }

    public void hangup() throws RemoteException {
        Log.d(TAG, "hangup");
        BtExe.getBtInstance().hangup();
    }

    public void answer() throws RemoteException {
        Log.d(TAG, "answer");
        BtExe.getBtInstance().answer();
    }

    public void setActionCallChanged(IOnActionCallback callback) throws RemoteException {
        Log.d(TAG, "setActionCallChanged");
        mIOnActionCallback = callback;
    }
}
