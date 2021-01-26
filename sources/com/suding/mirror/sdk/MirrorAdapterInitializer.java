package com.suding.mirror.sdk;

import android.content.Context;
import android.util.Log;

public class MirrorAdapterInitializer {
    private static final String TAG = "MirrorAdapterInitializer";
    private static MirrorAdapterInitializer instance;
    private static final Object mLock = new Object();
    private boolean debug;
    private boolean initTag = false;
    private Context mContext;
    private MirrorInitParams mMirrorInitParams;

    private MirrorAdapterInitializer() {
    }

    public static MirrorAdapterInitializer getInstance() {
        if (instance == null) {
            synchronized (mLock) {
                if (instance == null) {
                    instance = new MirrorAdapterInitializer();
                }
            }
        }
        return instance;
    }

    public boolean initMirrorSDK(Context context, MirrorInitParams mirrorInitParams) {
        if (context == null || mirrorInitParams == null) {
            throw new NullPointerException("Context or MirrorInitParams  can not be null,make sure you have init correctly");
        } else if (this.initTag) {
            Log.w(TAG, "MirrorAdapterSDK already initialized,can't be initizalize again ");
            return false;
        } else {
            this.mContext = context;
            this.mMirrorInitParams = mirrorInitParams;
            MirrorAdapterManager.getInstance().initializeMirror(context);
            return true;
        }
    }

    public MirrorInitParams getMirrorInitParams() {
        return this.mMirrorInitParams;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void setDebug(boolean debug2) {
        this.debug = debug2;
    }
}
