package com.ts.tsspeechlib.function;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.main.common.ShellUtils;
import com.ts.tsspeechlib.ManagerInitListener;
import com.ts.tsspeechlib.function.ITsSpeechFunction;
import com.txznet.sdk.TXZResourceManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class TsFunctionManager {
    public static final String BROADCAST_SET_PRODUCTID = "forfan.intent.action.set_productid";
    static final String DEVICEID_FILE = "/mnt/sdcard/EasyConnected/License.ini";
    static final String PROID_FILE = "/mnt/sdcard/Iconfig/Proid.ini";
    public static final String SET_PRODUCTID = "set_proid";
    public static final String TAG = "TsFunctionManager";
    public static TsFunctionManager functionManager;
    /* access modifiers changed from: private */
    public ManagerInitListener functionListener;
    private Context mContext;
    /* access modifiers changed from: private */
    public ITsSpeechFunction mSpeechFunctionService;
    ServiceConnection sconn = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TsFunctionManager.TAG, "鍒濆鍖栧け璐ワ紒");
            TsFunctionManager.this.mSpeechFunctionService = null;
            if (TsFunctionManager.this.functionListener != null) {
                TsFunctionManager.this.functionListener.initResult(0, false);
            }
        }

        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            Log.d(TsFunctionManager.TAG, "鍒濆鍖栨垚鍔燂紒");
            TsFunctionManager.this.mSpeechFunctionService = ITsSpeechFunction.Stub.asInterface(binder);
            if (TsFunctionManager.this.functionListener != null) {
                TsFunctionManager.this.functionListener.initResult(0, true);
            }
        }
    };

    public static TsFunctionManager getInstance() {
        if (functionManager == null) {
            functionManager = new TsFunctionManager();
        }
        return functionManager;
    }

    public void initManager(Context context, ManagerInitListener listener) {
        this.mContext = context;
        this.functionListener = listener;
        bindFunctionService();
    }

    public boolean isScreenOpen() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.isScreenOpen();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void onOpenScreen() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onOpenScreen();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onCloseScreen() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onCloseScreen();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentBrightness() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.getCurrentBrightness();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getMinBrightnes() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.getMinBrightnes();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getMaxBrightnes() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.getMaxBrightnes();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void onBrightenScreen() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onBrightenScreen();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onDimmingScreen() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onDimmingScreen();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onScreenBrightest() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onScreenBrightest();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onScreenDarkest() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onScreenDarkest();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentVolume() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.getCurrentVolume();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getMinVolume() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.getMinVolume();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getMaxVolume() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.getMaxVolume();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void onVolumeUp() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onVolumeUp();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onVolumeDown() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onVolumeDown();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onVolumeMax() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onVolumeMax();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onVolumeMin() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onVolumeMin();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isVolumeMute() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.isVolumeMute();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void onVolumeMute() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onVolumeMute();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onVolumeUnmute() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.onVolumeUnmute();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void showLauncher() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.showLauncher();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getWorkMode() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.getWorkMode();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void openSetting() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.openSetting();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void openAvin() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.openAvin();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void openCarInfo() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.openCarInfo();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void speechStartTTS() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.speechStartTTS();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void speechStopTTS() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.speechStopTTS();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void speechStartRecognition() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.speechStartRecognition();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setVolume(int number) {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.setVolume(number);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setScreenBright(int number) {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.setScreenBrightness(number);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void supportFastCharging(int state) {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.supportFastCharging(state);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setFunctionCallback(ITsFunctionCallback callback) {
        if (this.mSpeechFunctionService != null) {
            try {
                this.mSpeechFunctionService.setFunctionCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void speechStopRecognition() {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.speechStopRecognition();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String readUUID() {
        return readFile(PROID_FILE);
    }

    public String readDeviceID() {
        return readFile(DEVICEID_FILE);
    }

    public String readFile(String path) {
        String content = TXZResourceManager.STYLE_DEFAULT;
        File file = new File(path);
        if (file.isDirectory()) {
            Log.d(TAG, "The File doesn't not exist.");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    BufferedReader buffreader = new BufferedReader(new InputStreamReader(instream));
                    while (true) {
                        String line = buffreader.readLine();
                        if (line == null) {
                            break;
                        }
                        content = String.valueOf(content) + line + ShellUtils.COMMAND_LINE_END;
                    }
                    instream.close();
                }
            } catch (FileNotFoundException e) {
                Log.d(TAG, "The File doesn't not exist.");
            } catch (IOException e2) {
                Log.d(TAG, e2.getMessage());
            }
        }
        return content;
    }

    public void writeUUID(String id) {
        if (id.length() > 32) {
            id = id.substring(0, 32);
        }
        Intent intent = new Intent("forfan.intent.action.set_productid");
        intent.putExtra("set_proid", id);
        if (this.mContext != null) {
            this.mContext.sendBroadcast(intent);
        }
    }

    public int isIllOn() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.isIllOn();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int isBackCar() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.isBackCar();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int withBrakes() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.withBrakes();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getDeviceType() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.getDeviceType();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public String getHMI() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.getHMI();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public String getBranch() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.getBranch();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public int GetRadioIC() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.GetRadioIC();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int GetCanType() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.GetCanType();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int GetCameraType() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.GetCameraType();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int SendKey(int KeyCode) {
        try {
            if (this.mSpeechFunctionService == null) {
                return 0;
            }
            this.mSpeechFunctionService.SendKey(KeyCode);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void KillProcess(String pname) {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.KillProcess(pname);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void OpenMainUIApp(int nWin, int parat) {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.OpenMainUIApp(nWin, parat);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void CloseMainUIApp(int nWin) {
        try {
            if (this.mSpeechFunctionService != null) {
                this.mSpeechFunctionService.CloseMainUIApp(nWin);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String GetDeviceID() {
        try {
            if (this.mSpeechFunctionService != null) {
                return this.mSpeechFunctionService.GetDeviceID();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void bindFunctionService() {
        Intent intent = new Intent();
        intent.setAction("com.ts.tsspeechlib.function.TsFunctionService");
        Intent intent2 = createExplicitFromImplicitIntent(this.mContext, intent);
        if (intent2 != null) {
            this.mContext.bindService(intent2, this.sconn, 1);
        } else {
            Log.d(TAG, "bindFunctionService failed");
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
