package com.hongfans.carmedia;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import com.hongfans.carmedia.processes.AndroidAppProcessLoader;
import com.hongfans.carmedia.processes.models.AndroidAppProcess;
import com.hongfans.rearview.services.api.IOnPlayChangedListener;
import com.hongfans.rearview.services.api.IPlayManager;
import com.hongfans.rearview.services.api.ProgramDigtalModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaAPI {
    private static final int NEXT = 4097;
    private static final int OTHER = 4;
    private static final int PAUSE = 2;
    private static final int PREVIOUS = 4096;
    private static final int RESUME = 3;
    /* access modifiers changed from: private */
    public static ServiceConnection conn = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            boolean unused = MediaAPI.mIsBind = true;
            Util.print("服务绑定成功");
            try {
                IPlayManager unused2 = MediaAPI.ipm = IPlayManager.Stub.asInterface(service);
                MediaAPI.ipm.registerPlayChangedListener(MediaAPI.mListener);
            } catch (RemoteException e) {
                e.printStackTrace();
                Util.printE("onServiceConnected 发生错误");
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            boolean unused = MediaAPI.mIsBind = false;
            Util.print("onServiceDisconnected 发送退出广播", true);
            MediaAPI.mContext.sendBroadcast(new Intent("com.hongfans.action.exit"));
            IPlayManager unused2 = MediaAPI.ipm = null;
        }
    };
    /* access modifiers changed from: private */
    public static IPlayManager ipm;
    /* access modifiers changed from: private */
    public static Context mContext;
    /* access modifiers changed from: private */
    public static volatile boolean mIsBind;
    /* access modifiers changed from: private */
    public static String mKey = "";
    /* access modifiers changed from: private */
    public static IOnPlayChangedListener.Stub mListener = new IOnPlayChangedListener.Stub() {
        public void OnPlayStateChanged(int state) throws RemoteException {
            Util.print("收到播放状态改变 mOnPlayChangedListener " + MediaAPI.mOnPlayChangedListener + ", state " + state + ", mIsBind " + MediaAPI.mIsBind);
            if (MediaAPI.mOnPlayChangedListener != null) {
                MediaAPI.mOnPlayChangedListener.OnPlayStateChanged(state);
            }
            if (MediaAPI.mPlayStateListener != null) {
                MediaAPI.mPlayStateListener.OnPlayStateChange(state);
            }
        }

        public void OnPlayListChanged(List<ProgramDigtalModel> list) throws RemoteException {
            Util.print("收到播放列表改变 mOnPlayChangedListener " + MediaAPI.mOnPlayChangedListener + ", list.size " + list.size());
            if (MediaAPI.mOnPlayChangedListener != null) {
                MediaAPI.mOnPlayChangedListener.OnPlayListChanged(list);
            }
        }

        public void OnPlayMusicChanged(ProgramDigtalModel model) throws RemoteException {
            Util.print("收到播放内容改变　mOnPlayChangedListener " + MediaAPI.mOnPlayChangedListener + ", model " + model.getTitle());
            if (MediaAPI.mOnPlayChangedListener != null) {
                MediaAPI.mOnPlayChangedListener.OnPlayMusicChanged(model);
            }
        }
    };
    private static MediaAPI mMediaAPI;
    /* access modifiers changed from: private */
    public static OnPlayChangedListener mOnPlayChangedListener;
    /* access modifiers changed from: private */
    public static PlayerStateListener mPlayStateListener;
    private static boolean sIsDebug;
    private static BroadcastReceiver sReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Util.print("收到退出广播 mIsBind " + MediaAPI.mIsBind);
            if (MediaAPI.mIsBind) {
                try {
                    MediaAPI.mListener.OnPlayStateChanged(0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Util.printE(e.getLocalizedMessage());
                }
                if (MediaAPI.mIsBind) {
                    MediaAPI.mContext.unbindService(MediaAPI.conn);
                    Util.print("已调用 unbindService");
                    boolean unused = MediaAPI.mIsBind = false;
                    IPlayManager unused2 = MediaAPI.ipm = null;
                }
            }
        }
    };

    private MediaAPI() {
    }

    public static MediaAPI createMediaAPI(Context context, String paramString) {
        if (context == null) {
            throw new IllegalStateException("param Context cannot be null");
        }
        if (mMediaAPI == null) {
            context.registerReceiver(sReceiver, new IntentFilter("com.hongfans.action.exit"));
            mContext = context;
            mMediaAPI = new MediaAPI();
        }
        return mMediaAPI;
    }

    public static boolean getIsDebug() {
        return sIsDebug;
    }

    public void setIsDebug(boolean isDebug) {
        sIsDebug = isDebug;
    }

    private static void initService() {
        Util.print("绑定服务");
        Intent intent = new Intent();
        intent.setAction(Constant.ACTION_MUSIC_SERVICE);
        intent.setPackage(Constant.PACKAGE_NAME);
        intent.putExtra(Constant.EXTRA_VERSION, "v4.0,40");
        if (mIsBind) {
            Util.print("需解绑服务上一次");
            mContext.unbindService(conn);
            mIsBind = false;
            ipm = null;
        }
        mContext.bindService(intent, conn, 1);
    }

    public void startAPP(final Context paramContext, final boolean switchScreen) {
        Util.print("startAPP switchScreen " + switchScreen);
        if (validate()) {
            new AndroidAppProcessLoader(paramContext, new AndroidAppProcessLoader.Listener() {
                public void onComplete(List<AndroidAppProcess> processes) {
                    if (processes == null || processes.size() <= 0) {
                        MediaAPI.this.toActivity(paramContext, Constant.CLASS_NAME_SPLASHACTIVITY, (Map<String, String>) null, switchScreen);
                        Util.print("startAPP 0or2--", true);
                        return;
                    }
                    MediaAPI.this.toActivity(paramContext, Constant.CLASS_NAME_MAINACTIVITY, (Map<String, String>) null, switchScreen);
                    Util.print("startAPP 0or1--", true);
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void startAPP(Context paramContext) {
        Util.print("startAPP");
        Util.print("version info v4.0, 40", true);
        if (validate()) {
            toActivityByAction(paramContext, Constant.ACTION_START_APP, (Map<String, String>) null);
            Util.print("startAPP 0or3--", true);
        }
    }

    public void playMusic(Context paramContext, String keyword, String semantics, boolean switchScreen) {
        Util.print("playMusic switchScreen " + switchScreen);
        if (validate()) {
            final String str = keyword;
            final String str2 = semantics;
            final Context context = paramContext;
            final boolean z = switchScreen;
            new AndroidAppProcessLoader(paramContext, new AndroidAppProcessLoader.Listener() {
                public void onComplete(List<AndroidAppProcess> list) {
                    Map<String, String> Bvalues = new HashMap<>();
                    Bvalues.put("keyword", str);
                    Bvalues.put("semantics", str2);
                    Bvalues.put("isswitchScreen", "isswitchScreen");
                    MediaAPI.this.toBroadcast(context, Constant.ACTION_SEARCH_MUSIC, Bvalues);
                    Map<String, String> values = new HashMap<>();
                    values.put("keyword", str);
                    values.put("semantics", str2);
                    MediaAPI.this.toActivity(context, Constant.CLASS_NAME_SPLASHACTIVITY, values, z);
                    Util.print("playMusic 0 or 1-- keyword " + str + ", switchScreen " + z, true);
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void playMusic(Context paramContext, String keyword, String semantics) {
        Util.print("playMusic");
        if (validate()) {
            Map<String, String> values = new HashMap<>();
            values.put("keyword", keyword);
            values.put("semantics", semantics);
            toActivityByAction(paramContext, Constant.ACTION_PLAY_MUSIC, values);
            Util.print("playMusic 3-- keyword " + keyword, true);
        }
    }

    public void switchProgramByBroadcast() {
        Util.print("switchProgramByBroadcast");
        if (validate()) {
            Intent intent = new Intent(Constant.ACTION_SWITCH_PROGRAM);
            intent.putExtra("hongfans_key", mKey);
            Bundle bundle = getDefBundle(mContext);
            bundle.putInt("next", 1);
            intent.putExtras(bundle);
            mContext.sendBroadcast(intent);
            Util.print("switchProgramByBroadcast", true);
        }
    }

    public void exitAPP(Context paramContext) {
        Util.print("exitAPP");
        if (validate()) {
            Intent localIntent = new Intent(Constant.ACTION_EXIT_APP);
            localIntent.putExtra("hongfans_key", mKey);
            localIntent.putExtras(getDefBundle(paramContext));
            paramContext.sendBroadcast(localIntent);
            Util.print("exitAPP 1--", true);
        }
    }

    public void setPlayMode(Context paramContext, int paramPlayMode) {
        Util.print("setPlayMode");
        if (validate()) {
            Intent localIntent = new Intent(Constant.ACTION_MEDIA_BUTTON);
            localIntent.putExtra("PlayMode", paramPlayMode);
            localIntent.putExtra("hongfans_key", mKey);
            localIntent.putExtras(getDefBundle(paramContext));
            paramContext.sendBroadcast(localIntent);
            Util.print("setPlayMode playMode " + paramPlayMode, true);
        }
    }

    public void PlayNext(Context paramContext) {
        Util.print("PlayNext");
        if (validate()) {
            Intent MusicIntent = new Intent();
            MusicIntent.setAction(Constant.MusicContro_Action);
            MusicIntent.putExtra("musiccommand", 4097);
            MusicIntent.putExtra("ttscommand", 0);
            MusicIntent.putExtras(getDefBundle(paramContext));
            paramContext.sendBroadcast(MusicIntent);
        }
    }

    public void PlayPrevious(Context paramContext) {
        Util.print("PlayPrevious");
        if (validate()) {
            Intent MusicIntent = new Intent();
            MusicIntent.setAction(Constant.MusicContro_Action);
            MusicIntent.putExtra("musiccommand", 4096);
            MusicIntent.putExtra("ttscommand", 0);
            MusicIntent.putExtras(getDefBundle(paramContext));
            paramContext.sendBroadcast(MusicIntent);
        }
    }

    public void PlayPause(Context paramContext) {
        Util.print("PlayPause");
        if (validate()) {
            Intent MusicIntent = new Intent();
            MusicIntent.setAction(Constant.MusicContro_Action);
            MusicIntent.putExtra("musiccommand", 2);
            MusicIntent.putExtra("ttscommand", 0);
            MusicIntent.putExtras(getDefBundle(paramContext));
            paramContext.sendBroadcast(MusicIntent);
        }
    }

    public void PlayResume(Context paramContext) {
        Util.print("PlayResume");
        if (validate()) {
            Intent MusicIntent = new Intent();
            MusicIntent.setAction(Constant.MusicContro_Action);
            MusicIntent.putExtra("musiccommand", 3);
            MusicIntent.putExtra("ttscommand", 0);
            MusicIntent.putExtras(getDefBundle(paramContext));
            paramContext.sendBroadcast(MusicIntent);
        }
    }

    public void opeanUrl(final Context paramContext, final String url) {
        Util.print("opeanUrl");
        if (validate()) {
            new AndroidAppProcessLoader(paramContext, new AndroidAppProcessLoader.Listener() {
                public void onComplete(List<AndroidAppProcess> processes) {
                    if (processes == null || processes.size() <= 0) {
                        Map<String, String> values = new HashMap<>();
                        values.put("url", url);
                        MediaAPI.this.toActivity(paramContext, Constant.CLASS_NAME_SPLASHACTIVITY, values);
                        Util.print("opeanUrl SplashActivity");
                        return;
                    }
                    Intent localIntent = new Intent("com.hongfans.action.opeanurl");
                    localIntent.putExtra("hongfans_key", MediaAPI.mKey);
                    Bundle bundle = MediaAPI.this.getDefBundle(paramContext);
                    bundle.putString("url", url);
                    localIntent.putExtras(bundle);
                    paramContext.sendBroadcast(localIntent);
                    Util.print("opeanUrl 发送广播");
                    Map<String, String> values2 = new HashMap<>();
                    values2.put("url", url);
                    MediaAPI.this.toActivity(paramContext, Constant.CLASS_NAME_MAINACTIVITY, values2);
                    Util.print("opeanUrl MainActivity");
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private boolean validate() {
        if (mContext == null) {
            throw new IllegalStateException("param Context cannot be null");
        } else if (Util.GetAppID(mContext).length() <= 0) {
            Util.show(mContext, "无效AppID");
            return false;
        } else if (Util.GetSecret(mContext).length() > 0) {
            return true;
        } else {
            Util.show(mContext, "无效Secret");
            return false;
        }
    }

    public int getPlayState() {
        Util.print("getPlayState");
        if (!validate()) {
            return -1;
        }
        if (ipm != null) {
            try {
                int state = ipm.IGetPlayState();
                Util.print("服务连接 修正前 state " + state);
                if (!(state == 0 || state == 1 || state == 2)) {
                    state = (state < 4101 || state > 4104) ? 0 : 2;
                }
                Util.print("服务连接 修正后 state " + state);
                return state;
            } catch (RemoteException e) {
                e.printStackTrace();
                Util.printE("err " + e);
                return -1;
            }
        } else {
            Util.print("服务未连接 not connect");
            return -1;
        }
    }

    @Deprecated
    public void OnPlayState(PlayerStateListener listener) {
        mPlayStateListener = listener;
    }

    public void setOnPlayChangedListener(OnPlayChangedListener listener) {
        Util.print("设置回调 listener " + listener + ", ipm " + ipm);
        if (validate()) {
            mOnPlayChangedListener = listener;
            if (listener != null) {
                if (ipm != null) {
                    try {
                        ipm.registerPlayChangedListener(mListener);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        Util.printE("设置回调失败，重连 " + e.getMessage());
                        initService();
                    }
                } else {
                    initService();
                }
            } else if (ipm != null) {
                try {
                    ipm.unRegisterPlayChangedListener(mListener);
                } catch (RemoteException e2) {
                    Util.printE("注销回调失败 " + e2.getLocalizedMessage());
                    e2.printStackTrace();
                }
            }
        }
    }

    private void toActivity(Context ctx, String action, String classname, Map<String, String> values, boolean isRightWindow) {
        Intent intent = new Intent(action);
        intent.setFlags(268435456);
        if (isRightWindow) {
            intent.addFlags(512);
        }
        if (classname != null) {
            intent.setClassName(Constant.PACKAGE_NAME, classname);
        }
        Bundle bundle = getDefBundle(ctx);
        if (values != null) {
            for (Map.Entry<String, String> next : values.entrySet()) {
                bundle.putString(next.getKey(), next.getValue());
            }
        }
        intent.putExtras(bundle);
        try {
            ctx.startActivity(intent);
        } catch (Exception e) {
            Util.printE("err " + e);
        }
    }

    /* access modifiers changed from: private */
    public Bundle getDefBundle(Context ctx) {
        Bundle bundle = new Bundle();
        bundle.putString("appid", Util.GetAppID(ctx));
        bundle.putString("appsecret", Util.GetSecret(ctx));
        bundle.putString("pid", Process.myPid() + "");
        bundle.putString(Constant.EXTRA_VERSION, "v4.0,40");
        return bundle;
    }

    /* access modifiers changed from: private */
    public void toActivity(Context ctx, String className, Map<String, String> values, boolean isRightWindow) {
        toActivity(ctx, "android.intent.action.VIEW", className, values, isRightWindow);
    }

    /* access modifiers changed from: private */
    public void toActivity(Context ctx, String classname, Map<String, String> values) {
        toActivity(ctx, classname, values, false);
    }

    private void toActivityByAction(Context ctx, String action, Map<String, String> values) {
        toActivity(ctx, action, (String) null, values, false);
    }

    /* access modifiers changed from: private */
    public void toBroadcast(Context ctx, String broadcast, Map<String, String> values) {
        Intent intent = new Intent();
        intent.setAction(broadcast);
        Bundle bundle = getDefBundle(ctx);
        if (values != null) {
            for (Map.Entry<String, String> next : values.entrySet()) {
                bundle.putString(next.getKey(), next.getValue());
            }
        }
        intent.putExtras(bundle);
        ctx.sendBroadcast(intent);
    }

    public void destroy() {
        Util.print("destroy");
        if (mIsBind) {
            try {
                mContext.unbindService(conn);
                mIsBind = false;
                ipm = null;
            } catch (Exception e) {
                Util.printE(e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        mContext.unregisterReceiver(sReceiver);
        mMediaAPI = null;
        mContext = null;
    }
}
