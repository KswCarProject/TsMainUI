package com.ts.tsspeechlib.car;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.tsspeechlib.ManagerInitListener;
import com.ts.tsspeechlib.car.ITsSpeechCar;
import java.util.List;

public class TsCarManager {
    public static final String TAG = "TsCarManager";
    public static TsCarManager carManager;
    /* access modifiers changed from: private */
    public ManagerInitListener carListener;
    private Context mContext;
    /* access modifiers changed from: private */
    public ITsSpeechCar mSpeechCarService;
    ServiceConnection sconn = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TsCarManager.TAG, "鍒濆鍖栧け璐ワ紒");
            TsCarManager.this.mSpeechCarService = null;
            if (TsCarManager.this.carListener != null) {
                TsCarManager.this.carListener.initResult(4, false);
            }
        }

        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            Log.d(TsCarManager.TAG, "鍒濆鍖栨垚鍔燂紒");
            TsCarManager.this.mSpeechCarService = ITsSpeechCar.Stub.asInterface(binder);
            if (TsCarManager.this.carListener != null) {
                TsCarManager.this.carListener.initResult(4, true);
            }
        }
    };

    public static TsCarManager getInstance() {
        if (carManager == null) {
            carManager = new TsCarManager();
        }
        return carManager;
    }

    public void initManager(Context context, ManagerInitListener listener) {
        this.mContext = context;
        this.carListener = listener;
        bindCarService();
    }

    public void onTurnOnAir() {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onTurnOnAir();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onTurnDownAir() {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onTurnDownAir();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onHighAir() {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onHighAir();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onLowAir() {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onLowAir();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onSetLoop(String number) {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onSetLoop(number);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onSetAir(String number) {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onSetAir(number);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onUpWindSpeed() {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onUpWindSpeed();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onDownWindSpeed() {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onDownWindSpeed();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onWindMode(String number) {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onWindMode(number);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onOpenSkylight() {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onOpenSkylight();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onCloseSkylight() {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onCloseSkylight();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onOpenAllCarwindow() {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onOpenAllCarwindow();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onCloseAllCarwindow() {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onCloseAllCarwindow();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onOpenCarwindow(String number) {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onOpenCarwindow(number);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onCloseCarwindow(String number) {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onCloseCarwindow(number);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onHeatRearwindow() {
        try {
            if (this.mSpeechCarService != null) {
                this.mSpeechCarService.onHeatRearwindow();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int GetTotalKilometers() {
        try {
            if (this.mSpeechCarService == null) {
                return 0;
            }
            this.mSpeechCarService.GetTotalKilometers();
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int GetOilLeftover() {
        try {
            if (this.mSpeechCarService == null) {
                return 0;
            }
            this.mSpeechCarService.GetOilLeftover();
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int GetLeftTurnSignal() {
        try {
            if (this.mSpeechCarService == null) {
                return 0;
            }
            this.mSpeechCarService.GetLeftTurnSignal();
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int GetRightTurnSignal() {
        try {
            if (this.mSpeechCarService == null) {
                return 0;
            }
            this.mSpeechCarService.GetRightTurnSignal();
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int GetEmergency() {
        try {
            if (this.mSpeechCarService == null) {
                return 0;
            }
            this.mSpeechCarService.GetEmergency();
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int GetSpeed() {
        try {
            if (this.mSpeechCarService == null) {
                return 0;
            }
            this.mSpeechCarService.GetSpeed();
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void bindCarService() {
        Intent intent = new Intent();
        intent.setAction("com.ts.tsspeechlib.car.TsCarService");
        Intent intent2 = createExplicitFromImplicitIntent(this.mContext, intent);
        if (intent2 != null) {
            this.mContext.bindService(intent2, this.sconn, 1);
        } else {
            Log.d(TAG, "bindCarService failed");
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
