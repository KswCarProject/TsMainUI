package com.ts.tsspeechlib.radio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.tsspeechlib.ManagerInitListener;
import com.ts.tsspeechlib.radio.ITsSpeechRadio;
import java.util.List;

public class TsRadioManager {
    private static final String ACTION_MIX_VOLUME_SIZE = "tsradiomanager_action_setmixvolumesize";
    private static final String ACTION_SOUND_COEXISTENCE = "tsradiomanager_action_setsoundcoexistence";
    public static final String TAG = "TsRadioManager";
    public static TsRadioManager radioManager;
    private Context mContext;
    /* access modifiers changed from: private */
    public ITsSpeechRadio mSpeechRadioService;
    /* access modifiers changed from: private */
    public ManagerInitListener radioListener;
    ServiceConnection sconn = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TsRadioManager.TAG, "radio init fail锛�");
            TsRadioManager.this.mSpeechRadioService = null;
            if (TsRadioManager.this.radioListener != null) {
                TsRadioManager.this.radioListener.initResult(3, false);
            }
        }

        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            Log.d(TsRadioManager.TAG, "radio init ok锛�");
            TsRadioManager.this.mSpeechRadioService = ITsSpeechRadio.Stub.asInterface(binder);
            if (TsRadioManager.this.radioListener != null) {
                TsRadioManager.this.radioListener.initResult(3, true);
            }
        }
    };

    public static TsRadioManager getInstance() {
        if (radioManager == null) {
            radioManager = new TsRadioManager();
        }
        return radioManager;
    }

    public void initManager(Context context, ManagerInitListener listener) {
        this.mContext = context;
        this.radioListener = listener;
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

    public void TurnBandAndFq(int nBand, int fq) {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.TurnBandAndFq(nBand, fq);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setMixVolumeSize(int size) {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.setMixVolumeSize(size);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setSoundCoexistence(int state) {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.setSoundCoexistence(state);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getRadioBand() {
        try {
            if (this.mSpeechRadioService != null) {
                return this.mSpeechRadioService.getRadioBand();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void SeekUp() {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.SeekUp();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void SeekDn() {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.SeekDn();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void OpenRadioCh() {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.OpenRadioCh();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void CloseRadioCh() {
        try {
            if (this.mSpeechRadioService != null) {
                this.mSpeechRadioService.CloseRadioCh();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bindRadioService() {
        Intent intent = new Intent();
        intent.setAction("com.ts.tsspeechlib.radio.TsRadioService");
        Intent intent2 = createExplicitFromImplicitIntent(this.mContext, intent);
        if (intent2 != null) {
            this.mContext.bindService(intent2, this.sconn, 1);
        } else {
            Log.d(TAG, "bindRadioService failed");
        }
    }

    private Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
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
