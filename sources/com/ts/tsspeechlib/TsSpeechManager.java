package com.ts.tsspeechlib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.tsspeechlib.ITsSpeech;
import com.ts.tsspeechlib.bt.ITsSpeechBtListener;
import com.ts.tsspeechlib.bt.TsBtManager;
import com.ts.tsspeechlib.car.TsCarManager;
import com.ts.tsspeechlib.function.TsFunctionManager;
import com.ts.tsspeechlib.music.TsMusicManager;
import com.ts.tsspeechlib.radio.TsRadioManager;
import java.util.List;

public class TsSpeechManager {
    public static final String TAG = "TsSpeechManager";
    public static TsSpeechManager speechManager;
    /* access modifiers changed from: private */
    public InitCallback initCallback;
    /* access modifiers changed from: private */
    public boolean initSuccessful = false;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public ITsSpeech mSpeechService;
    ServiceConnection sconn = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TsSpeechManager.TAG, "鍒濆鍖栧け璐ワ紒");
            TsSpeechManager.this.mSpeechService = null;
            TsSpeechManager.this.initSuccessful = false;
            if (TsSpeechManager.this.initCallback != null) {
                TsSpeechManager.this.initCallback.onInitState(TsSpeechManager.this.initSuccessful);
            }
        }

        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            Log.d(TsSpeechManager.TAG, "鍒濆鍖栨垚鍔燂紒");
            TsSpeechManager.this.mSpeechService = ITsSpeech.Stub.asInterface(binder);
            TsBtManager.getInstance().initManager(TsSpeechManager.this.mContext);
            TsFunctionManager.getInstance().initManager(TsSpeechManager.this.mContext);
            TsMusicManager.getInstance().initManager(TsSpeechManager.this.mContext);
            TsCarManager.getInstance().initManager(TsSpeechManager.this.mContext);
            TsRadioManager.getInstance().initManager(TsSpeechManager.this.mContext);
            TsSpeechManager.this.initSuccessful = true;
            if (TsSpeechManager.this.initCallback != null) {
                TsSpeechManager.this.initCallback.onInitState(TsSpeechManager.this.initSuccessful);
            }
        }
    };

    public interface InitCallback {
        void onInitState(boolean z);
    }

    public static TsSpeechManager getInstance() {
        if (speechManager == null) {
            speechManager = new TsSpeechManager();
        }
        return speechManager;
    }

    public void initManager(Context context, InitCallback callback) {
        this.mContext = context;
        this.initCallback = callback;
        bindSpeechService();
    }

    public void setBtListener(ITsSpeechBtListener btListener) {
        try {
            if (this.mSpeechService != null) {
                this.mSpeechService.setBtListener(btListener);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isInitSuccess() {
        return this.initSuccessful;
    }

    private void bindSpeechService() {
        Intent intent = new Intent();
        intent.setAction("com.ts.tsspeechlib.TsSpeechService");
        Intent intent2 = createExplicitFromImplicitIntent(this.mContext, intent);
        if (intent2 != null) {
            this.mContext.bindService(intent2, this.sconn, 1);
        } else {
            Log.d(TAG, "bindSpeechService failed");
        }
    }

    public Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        List<ResolveInfo> resolveInfo = context.getPackageManager().queryIntentServices(implicitIntent, 0);
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfo.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}
