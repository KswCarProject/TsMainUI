package com.ts.main.ecar;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.ViewGroup;
import com.ts.bt.BtExe;
import com.ts.main.common.WinShow;
import com.ts.main.txz.AmapAuto;
import com.txznet.sdk.tongting.IConstantData;
import com.yyw.ts70xhw.Iop;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ECarCommunication {
    public static final int BT_CALL_IDLE = 3;
    public static final int BT_CALL_OFFHOOK = 5;
    public static final int BT_CALL_RINGING = 4;
    public static final int BT_CONNECTED = 2;
    public static final int BT_DISCONNECT = 1;
    public static final int BT_OFF = 0;
    public static String CAR_RECIVER_ACTION = "com.android.ecar.send";
    public static String CAR_RECIVER_CALLING_ACTION = "com.ecar.call.offhook";
    public static String CAR_RECIVER_CALL_IDLE_ACTION = "com.ecar.call.idle";
    public static String CAR_RECIVER_CALL_RING_ACTION = "com.ecar.call.incoming";
    public static String _Action_ = "com.android.ecar.recv";
    public static String _CMD_ = "ecarSendKey";
    public static String _KEYS_ = "keySet";
    public static String _TYPE_ = "cmdType";
    public static String _TYPE_STANDCMD_ = "standCMD";
    private static boolean bInit = false;
    private static ECarCommunication mInstance = null;
    Object BtCallMsgBox;
    Field BtCallMsgBoxLayout;
    Method BtCallMsgBoxShow;
    private boolean bShowCallBox = true;
    private boolean mBTConn = false;
    private BtExe mBTExe = null;
    private int mCallState = -1;
    private Context mContext = null;
    private BroadcastReceiver mReciver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            if (action.equals(ECarCommunication.CAR_RECIVER_ACTION)) {
                ECarCommunication.this.dealECarAction(context, intent);
            } else if (action.equals(ECarCommunication.CAR_RECIVER_CALL_IDLE_ACTION)) {
                Iop.BlueSpeaking(0);
            } else if (action.equals(ECarCommunication.CAR_RECIVER_CALLING_ACTION)) {
                Iop.BlueSpeaking(1);
            } else if (action.equals(ECarCommunication.CAR_RECIVER_CALL_RING_ACTION)) {
                Iop.BlueSpeaking(1);
            }
        }
    };
    private int mReportState = -1;

    /* access modifiers changed from: protected */
    public void dealECarAction(Context context, Intent intent) {
        String cmd = intent.getStringExtra("ecarSendKey");
        String stringExtra = intent.getStringExtra("cmdType");
        String stringExtra2 = intent.getStringExtra("keySet");
        if (cmd != null && cmd.length() > 0) {
            if (cmd.equals("StartMap")) {
                String poiName = intent.getStringExtra("gaode_poiName");
                String poiLatitude = intent.getStringExtra("gaode_latitude");
                String poiLongitude = intent.getStringExtra("gaode_longitude");
                startAutoMap(context, poiName, poiLatitude, poiLongitude);
                if (poiName != null && poiName.length() > 0 && poiLatitude != null && poiLatitude.length() > 0 && poiLongitude != null && poiLongitude.length() > 0) {
                    System.out.println(">>>>>>>>>> Recv Poi:" + poiName + ";" + poiLatitude + ";" + poiLongitude + " <<<<<<<<<<");
                }
            } else if (cmd.equals("HideCallUI")) {
                String tmpOper = intent.getStringExtra("oper");
                if (tmpOper != null && tmpOper.length() > 0) {
                    if (tmpOper.equals("hide")) {
                        showBTCallBox(false);
                        System.out.println(">>>>>>>>>> Recv msg: hide Call UI <<<<<<<<<<");
                    } else if (tmpOper.equals("show")) {
                        showBTCallBox(true);
                        System.out.println(">>>>>>>>>> Recv msg: show Call UI  <<<<<<<<<<");
                    }
                }
            } else if (cmd.equals("TTSSpeak")) {
                String text = intent.getStringExtra("text");
                if (text != null && text.length() > 0) {
                    System.out.println(">>>>>>>>>> TTS Message:" + text + "  <<<<<<<<<<");
                }
            } else if (cmd.equals("BluetoothQueryState")) {
                System.out.println(">>>>>>>>>> Recv msg: BluetoothQueryState  <<<<<<<<<<");
                sendBoradCast2ECar(context, this.mReportState);
            } else if (cmd.equals("BluetoothMakeCall")) {
                String name = intent.getStringExtra(IConstantData.KEY_NAME);
                String number = intent.getStringExtra("number");
                if (!this.mBTConn) {
                    WinShow.GotoWin(7, 0);
                    return;
                }
                if (!(this.mBTExe == null || number == null || number.length() <= 0)) {
                    this.mBTExe.dial(number);
                }
                if (name != null && name.length() > 0 && number != null && number.length() > 0) {
                    System.out.println(">>>>>>>>>> MakeCall:name=" + name + ";number:" + number + "  <<<<<<<<<<");
                }
            } else if (cmd.equals("BluetoothEndCall")) {
                if (this.mBTExe != null) {
                    this.mBTExe.hangup();
                }
                System.out.println(">>>>>>>>>> Recv msg: BluetoothEndCall  <<<<<<<<<<");
            } else if (cmd.equals("BluetoothConnect")) {
                if (this.mContext != null) {
                    WinShow.GotoWin(7, 0);
                }
                System.out.println(">>>>>>>>>> Recv msg: BluetoothEndCall  <<<<<<<<<<");
            }
        }
    }

    private void initECar() {
        if (this.mBTExe == null) {
            this.mBTExe = BtExe.getBtInstance();
        }
        initBTCallBox();
        this.mContext = BtExe.getContext();
        registerECarBroadCast();
        startUpdateService();
        bInit = true;
    }

    private void startUpdateService() {
        ComponentName cn = new ComponentName("com.ecar.AppManager", "com.ecar.AppManager.AppManagerService");
        Intent intent = new Intent();
        intent.setComponent(cn);
        intent.setPackage("com.ecar.AppManager");
        this.mContext.startService(intent);
    }

    private void registerECarBroadCast() {
        IntentFilter filter = new IntentFilter(CAR_RECIVER_ACTION);
        filter.addAction(CAR_RECIVER_CALLING_ACTION);
        filter.addAction(CAR_RECIVER_CALL_RING_ACTION);
        filter.addAction(CAR_RECIVER_CALL_IDLE_ACTION);
        this.mContext.registerReceiver(this.mReciver, filter);
    }

    public static ECarCommunication getInstance() {
        if (mInstance == null) {
            mInstance = new ECarCommunication();
        }
        return mInstance;
    }

    private void checkStatus() {
        if (this.mBTExe == null) {
            initECar();
        }
        if (this.mBTConn != this.mBTExe.isConnected()) {
            this.mBTConn = this.mBTExe.isConnected();
            if (this.mBTConn) {
                sendBoradCast2ECar(BtExe.getContext(), 2);
                this.mReportState = 2;
            } else {
                sendBoradCast2ECar(BtExe.getContext(), 1);
                this.mReportState = 1;
            }
        }
        if (this.BtCallMsgBoxLayout != null) {
            try {
                ViewGroup layout = (ViewGroup) this.BtCallMsgBoxLayout.get(this.BtCallMsgBox);
                if (layout != null) {
                    if (layout.getVisibility() == 0 && !this.bShowCallBox) {
                        showBTCallBox(false);
                    } else if (layout.getVisibility() == 8 && !this.bShowCallBox) {
                        showBTCallBox(false);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            }
        }
        if (this.mCallState != this.mBTExe.getSta()) {
            this.mCallState = this.mBTExe.getSta();
            switch (this.mCallState) {
                case 0:
                    sendBoradCast2ECar(BtExe.getContext(), 1);
                    this.mReportState = 1;
                    return;
                case 1:
                    sendBoradCast2ECar(BtExe.getContext(), 3);
                    this.mReportState = 3;
                    return;
                case 2:
                    sendBoradCast2ECar(BtExe.getContext(), 4);
                    this.mReportState = 4;
                    return;
                case 3:
                    sendBoradCast2ECar(BtExe.getContext(), 4);
                    this.mReportState = 4;
                    return;
                case 4:
                    sendBoradCast2ECar(BtExe.getContext(), 5);
                    this.mReportState = 5;
                    return;
                default:
                    return;
            }
        }
    }

    public int ECarTask(int nPowerMode) {
        if (!bInit) {
            initECar();
        }
        switch (nPowerMode) {
            case 2:
                return 255;
            default:
                checkStatus();
                return 255;
        }
    }

    public static void startAutoMap(Context context, String poiName, String lat, String lon) {
        double dlon = Double.parseDouble(lon);
        double dlat = Double.parseDouble(lat);
        Intent intent = new Intent();
        Log.d("Ecard", "paseEcardSendAction POINAME:" + poiName + " LON:" + dlon + " LAT:" + dlat);
        intent.setAction(AmapAuto.BROADCAST_AMAP_REV);
        intent.putExtra("KEY_TYPE", 10038);
        intent.putExtra("POINAME", poiName);
        intent.putExtra("LON", dlon);
        intent.putExtra("LAT", dlat);
        intent.putExtra("DEV", 0);
        intent.putExtra("STYLE", 0);
        context.sendBroadcast(intent);
    }

    private void sendBoradCast2ECar(Context context, int value) {
        if (context != null) {
            Intent tmpIntent = new Intent();
            tmpIntent.setAction(_Action_);
            tmpIntent.putExtra(_CMD_, "BluetoothState");
            tmpIntent.putExtra(_TYPE_, _TYPE_STANDCMD_);
            tmpIntent.putExtra(_KEYS_, IConstantData.KEY_STATE);
            tmpIntent.putExtra(IConstantData.KEY_STATE, String.valueOf(value));
            context.sendBroadcast(tmpIntent);
        }
    }

    public void startOneKeyActivity(Context context) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.coagent.ecar", "com.coagent.ecarnet.car.activity.OneKeyActivity"));
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }

    private void showBTCallBox(boolean bShow) {
        if (this.BtCallMsgBox != null && this.BtCallMsgBoxShow != null) {
            if (bShow) {
                try {
                    this.BtCallMsgBoxShow.invoke(this.BtCallMsgBox, new Object[]{1});
                    this.bShowCallBox = true;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                } catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                }
            } else {
                this.BtCallMsgBoxShow.invoke(this.BtCallMsgBox, new Object[]{0});
                this.bShowCallBox = false;
            }
        }
    }

    private void initBTCallBox() {
        try {
            Class<?> cls = Class.forName("com.ts.bt.BtCallMsgbox");
            Method method = cls.getDeclaredMethod("GetInstance", (Class[]) null);
            this.BtCallMsgBoxShow = cls.getDeclaredMethod("Show", new Class[]{Integer.TYPE});
            this.BtCallMsgBox = method.invoke((Object) null, (Object[]) null);
            this.BtCallMsgBoxLayout = cls.getDeclaredField("mFloatLayout");
            this.BtCallMsgBoxLayout.setAccessible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        } catch (NoSuchFieldException e6) {
            e6.printStackTrace();
        }
    }
}
