package com.ts.tsspeechlib.radio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.tsspeechlib.radio.ITsSpeechRadio;
import java.util.List;

public class TsRadioManager {
    public static final String TAG = "TsRadioManager";
    public static TsRadioManager radioManager;
    private Context mContext;
    /* access modifiers changed from: private */
    public ITsSpeechRadio mSpeechRadioService;
    ServiceConnection sconn = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TsRadioManager.TAG, "鍒濆鍖栧け璐ワ紒");
            TsRadioManager.this.mSpeechRadioService = null;
        }

        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            Log.d(TsRadioManager.TAG, "鍒濆鍖栨垚鍔燂紒");
            TsRadioManager.this.mSpeechRadioService = ITsSpeechRadio.Stub.asInterface(binder);
        }
    };

    public static TsRadioManager getInstance() {
        if (radioManager == null) {
            radioManager = new TsRadioManager();
        }
        return radioManager;
    }

    public void initManager(Context context) {
        this.mContext = context;
        bindRadioService();
    }

    public void onSelectedFreq(int number) {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.onSelectedFreq(number);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onPrevFreq() {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.onPrevFreq();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onNextFreq() {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.onNextFreq();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void openRadio() {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.openRadio();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void closeRadio() {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.closeRadio();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onRadioFM() {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.onRadioFM();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onRadioAM() {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.onRadioAM();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getRadioState() {
        try {
            if (this.mSpeechRadioService != null) {
                return this.mSpeechRadioService.getRadioState();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void bindRadioService() {
        Intent intent = new Intent();
        intent.setAction("com.ts.tsspeechlib.radio.TsRadioService");
        Intent intent2 = createExplicitFromImplicitIntent(this.mContext, intent);
        if (intent2 != null) {
            this.mContext.bindService(intent2, this.sconn, 1);
        } else {
            Log.d(TAG, "bindMusicService failed");
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
