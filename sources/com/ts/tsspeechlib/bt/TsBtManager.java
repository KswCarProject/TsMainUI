package com.ts.tsspeechlib.bt;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import com.ts.tsspeechlib.bt.ITsSpeechBt;
import java.util.List;

public class TsBtManager {
    public static final String TAG = "TsBtManager";
    public static TsBtManager btManager;
    private Context mContext;
    /* access modifiers changed from: private */
    public ITsSpeechBt mSpeechBtService;
    ServiceConnection sconn = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TsBtManager.TAG, "鍒濆鍖栧け璐ワ紒");
            TsBtManager.this.mSpeechBtService = null;
        }

        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            Log.d(TsBtManager.TAG, "鍒濆鍖栨垚鍔燂紒");
            TsBtManager.this.mSpeechBtService = ITsSpeechBt.Stub.asInterface(binder);
        }
    };

    public static TsBtManager getInstance() {
        if (btManager == null) {
            btManager = new TsBtManager();
        }
        return btManager;
    }

    public void initManager(Context context) {
        this.mContext = context;
        bindBtService();
    }

    public void onCallPhone(String number) {
        try {
            if (this.mSpeechBtService != null) {
                this.mSpeechBtService.onCallPhone(number);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onAnswerPhone() {
        try {
            if (this.mSpeechBtService != null) {
                this.mSpeechBtService.onAnswerPhone();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onHangupPhone() {
        try {
            if (this.mSpeechBtService != null) {
                this.mSpeechBtService.onHangupPhone();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getBtConnectState() {
        try {
            if (this.mSpeechBtService != null) {
                return this.mSpeechBtService.getBtConnectState();
            }
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getBtPhoneState() {
        try {
            if (this.mSpeechBtService != null) {
                return this.mSpeechBtService.getBtPhoneState();
            }
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getBtDEV() {
        try {
            if (this.mSpeechBtService != null) {
                return this.mSpeechBtService.getBtDEV();
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getBtMusicState() {
        try {
            if (this.mSpeechBtService != null) {
                return this.mSpeechBtService.getBtMusicState();
            }
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void onBtMusicPlay() {
        try {
            if (this.mSpeechBtService != null) {
                this.mSpeechBtService.onBtMusicPlay();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onBtMusicPause() {
        try {
            if (this.mSpeechBtService != null) {
                this.mSpeechBtService.onBtMusicPause();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onBtMusicPrev() {
        try {
            if (this.mSpeechBtService != null) {
                this.mSpeechBtService.onBtMusicPrev();
                Toast.makeText(this.mContext, "钃濈墮闊充箰涓婁竴鏇�", 0).show();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onBtMusicNext() {
        try {
            if (this.mSpeechBtService != null) {
                this.mSpeechBtService.onBtMusicNext();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<ITsSpeechBtPbInfo> onBtPbListChange() {
        try {
            if (this.mSpeechBtService != null) {
                return this.mSpeechBtService.onBtPbListChange();
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void openBt() {
        try {
            if (this.mSpeechBtService != null) {
                this.mSpeechBtService.openBt();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void closeBt() {
        try {
            if (this.mSpeechBtService != null) {
                this.mSpeechBtService.closeBt();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void openBtMusic() {
        try {
            if (this.mSpeechBtService != null) {
                this.mSpeechBtService.openBtMusic();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void closeBtMusic() {
        try {
            if (this.mSpeechBtService != null) {
                this.mSpeechBtService.closeBtMusic();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bindBtService() {
        Intent intent = new Intent();
        intent.setAction("com.ts.tsspeechlib.bt.TsBtService");
        Intent intent2 = createExplicitFromImplicitIntent(this.mContext, intent);
        if (intent2 != null) {
            this.mContext.bindService(intent2, this.sconn, 1);
        } else {
            Log.d(TAG, "bindBtService failed");
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
