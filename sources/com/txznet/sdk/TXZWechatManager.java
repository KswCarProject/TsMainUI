package com.txznet.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.util.Log;
import com.android.SdkConstants;
import com.ts.main.common.MainUI;
import com.txznet.comm.Tr.T;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.T9;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.bean.WechatMessage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.texustek.mirror.aidl.BinderName;

/* compiled from: Proguard */
public class TXZWechatManager {

    /* renamed from: T  reason: collision with root package name */
    private static TXZWechatManager f858T = new TXZWechatManager();
    /* access modifiers changed from: private */
    public Map<String, ImageListener> T5 = new HashMap();
    private boolean T9 = false;
    private Boolean TE = null;
    private BroadcastReceiver TZ = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String id = intent.getStringExtra("id");
            String img = intent.getStringExtra("img");
            if (!T9.T(id) && TXZWechatManager.this.T5.containsKey(id)) {
                ((ImageListener) TXZWechatManager.this.T5.get(id)).onImageReady(id, img);
                TXZWechatManager.this.T5.remove(id);
            }
        }
    };
    private NotificationTool Tk;
    private WechatTool Tn;
    private boolean Tr = false;
    private boolean Ty = false;

    /* compiled from: Proguard */
    public interface ImageListener {
        void onImageReady(String str, String str2);
    }

    /* compiled from: Proguard */
    public interface NotificationTool {
        void dismissNotify();

        void updateNotify(String str, String str2, String str3, boolean z, boolean z2);
    }

    /* compiled from: Proguard */
    public interface WechatTool {
        void QRScanned(String str);

        void dismissNotify();

        void dismissRecordWin(boolean z);

        void launch();

        void login();

        void logout();

        void showChat(String str, String str2, List<WechatMessage> list);

        void showQR(String str);

        void updateNotify(String str, String str2, String str3, boolean z, boolean z2);

        void updateNotifyStatus(boolean z);

        void updateQR(String str);

        void updateRecordWin(int i, String str, String str2);

        void updateSelf(String str, String str2);
    }

    private TXZWechatManager() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.txznet.webchat.action.SDK_DOWNLOAD_IMG_COMPLETE");
        T.Tr().registerReceiver(this.TZ, filter);
    }

    public static TXZWechatManager getInstance() {
        return f858T;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.Tr) {
            setWechatTool(this.Tn, this.Ty);
        }
        if (this.T9) {
            setNotificationTool(this.Tk);
        }
        if (this.TE != null) {
            enableAutoSpeak(this.TE.booleanValue());
        }
    }

    public void skipCurrentMessage() {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.skipCurrentMessage", (byte[]) null, (Tn.Tr) null);
    }

    public void repeatLastMessage() {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.repeatLastMessage", (byte[]) null, (Tn.Tr) null);
    }

    public void repeatSendMessage(String tip) {
        Tr builder = new Tr();
        builder.T("tip", (Object) tip);
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.repeat.send", builder.Ty(), (Tn.Tr) null);
    }

    public void sendToRecentContact(String tip) {
        Tr builder = new Tr();
        builder.T("tip", (Object) tip);
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.repeat.recent", builder.Ty(), (Tn.Tr) null);
    }

    public void replyToRecentContact(String tip) {
        Tr builder = new Tr();
        builder.T("tip", (Object) tip);
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.reply.recent", builder.Ty(), (Tn.Tr) null);
    }

    public void replyToCurrentContact(String tip) {
        Tr builder = new Tr();
        builder.T("tip", (Object) tip);
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.reply.current", builder.Ty(), (Tn.Tr) null);
    }

    public void blockCurrentContact() {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.blockCurrentContact", (byte[]) null, (Tn.Tr) null);
    }

    public void enableAutoSpeak(boolean auto) {
        this.TE = Boolean.valueOf(auto);
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.enableAutoSpeak", (TXZResourceManager.STYLE_DEFAULT + auto).getBytes(), (Tn.Tr) null);
    }

    public void exit(boolean doLogout) {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.exit", (TXZResourceManager.STYLE_DEFAULT + doLogout).getBytes(), (Tn.Tr) null);
    }

    public void cancelRecord() {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.cancelRecord", (byte[]) null, (Tn.Tr) null);
    }

    public void finishRecord() {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.finishRecord", (byte[]) null, (Tn.Tr) null);
    }

    public void revokeLastMessage() {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.revokeLastMsg", (byte[]) null, (Tn.Tr) null);
    }

    public void enableWakeupLogin(boolean enable) {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.wakeupLogin", (TXZResourceManager.STYLE_DEFAULT + enable).getBytes(), (Tn.Tr) null);
    }

    public void enableWakupAsrCmd(boolean enable) {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.enableWakupAsrCmd", (TXZResourceManager.STYLE_DEFAULT + enable).getBytes(), (Tn.Tr) null);
    }

    public void setLocMsgEnabled(boolean enable) {
        Tn.Tr().T("com.txznet.webchat", "wechat.setting.enableLocMsg", (TXZResourceManager.STYLE_DEFAULT + enable).getBytes(), (Tn.Tr) null);
    }

    public void setLocShareEnabled(boolean enable) {
        Tn.Tr().T("com.txznet.webchat", "wechat.setting.enableLocShare", (TXZResourceManager.STYLE_DEFAULT + enable).getBytes(), (Tn.Tr) null);
    }

    @Deprecated
    public void enableUIFullScreen(boolean enable) {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.set.ui_fullscreen", (TXZResourceManager.STYLE_DEFAULT + enable).getBytes(), (Tn.Tr) null);
    }

    public void exit() {
        exit(true);
    }

    public void refreshQR() {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.qr.refresh", (byte[]) null, (Tn.Tr) null);
    }

    public boolean isLogin() {
        try {
            return Tn.Tr().T("com.txznet.webchat", "wechat.status.isLogin", (byte[]) null).T9().booleanValue();
        } catch (Exception e) {
            com.txznet.comm.Tr.Tr.Tn.T("wechat sdk invoke failed, cause=" + e.getMessage());
            return false;
        }
    }

    public void getUsericon(String id, ImageListener listener) {
        File head = new File(Environment.getExternalStorageDirectory() + "/txz/webchat/cache/Head/" + id);
        if (head.exists()) {
            listener.onImageReady(id, head.getPath());
            return;
        }
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.loadImage", id.getBytes(), (Tn.Tr) null);
        this.T5.put(id, listener);
    }

    public void setFilterGroupMessage(boolean enable) {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.filter.groupmsg", (TXZResourceManager.STYLE_DEFAULT + enable).getBytes(), (Tn.Tr) null);
    }

    public void setFilterGroupContact(boolean enable) {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.filter.groupcon", (TXZResourceManager.STYLE_DEFAULT + enable).getBytes(), (Tn.Tr) null);
    }

    @Deprecated
    public void setLoginTipText(String text) {
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.tip.login", text.getBytes(), (Tn.Tr) null);
    }

    public void setVoiceText(String key, String value) {
        Tr builder = new Tr();
        builder.T(BinderName.KEY, (Object) key);
        builder.T("value", (Object) value);
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.set.voice_tip", builder.Ty(), (Tn.Tr) null);
    }

    public void setWechatTipText(String key, String value) {
        Tr builder = new Tr();
        builder.T(BinderName.KEY, (Object) key);
        builder.T("value", (Object) value);
        Tn.Tr().T("com.txznet.webchat", "wechat.ctrl.set.tip", builder.Ty(), (Tn.Tr) null);
    }

    public void setNotificationTool(final NotificationTool tool) {
        this.Tk = tool;
        if (tool == null) {
            this.T9 = false;
            Tn.Tr().T("com.txznet.webchat", "txz.webchat.ntool.clear", (byte[]) null, (Tn.Tr) null);
            return;
        }
        this.T9 = true;
        setWechatTool((WechatTool) null);
        TXZService.T("tool.wechat.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("notify.show")) {
                    Tr builder = new Tr(data);
                    boolean casting = ((Boolean) builder.T("hasSpeak", Boolean.TYPE)).booleanValue();
                    boolean isGroup = ((Boolean) builder.T("isGroup", Boolean.TYPE)).booleanValue();
                    tool.updateNotify((String) builder.T("msgId", String.class), (String) builder.T("id", String.class), (String) builder.T("nick", String.class), isGroup, casting);
                    return null;
                } else if (!command.equals("notify.cancel")) {
                    return null;
                } else {
                    tool.dismissNotify();
                    return null;
                }
            }
        });
        Tn.Tr().T("com.txznet.webchat", "txz.webchat.ntool.set", (byte[]) null, (Tn.Tr) null);
    }

    public void setWechatTool(WechatTool tool) {
        setWechatTool(tool, true);
    }

    public void setWechatTool(final WechatTool tool, boolean blockUI) {
        this.Tn = tool;
        if (tool == null) {
            this.Tr = false;
            this.Ty = false;
            Tn.Tr().T("com.txznet.webchat", "txz.webchat.tool.clear", (byte[]) null, (Tn.Tr) null);
            return;
        }
        this.Tr = true;
        this.Ty = blockUI;
        setNotificationTool((NotificationTool) null);
        TXZService.T("tool.wechat.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                com.txznet.comm.Tr.Tr.Tn.T("wxsdk::on cmd: " + command);
                if (command.equals("launch")) {
                    tool.launch();
                    return null;
                } else if (command.equals("qr.show")) {
                    tool.showQR((String) new Tr(data).T("qrcode", String.class));
                    return null;
                } else if (command.equals("qr.update")) {
                    tool.updateQR((String) new Tr(data).T("qrcode", String.class));
                    return null;
                } else if (command.equals("qr.scanned")) {
                    tool.QRScanned((String) new Tr(data).T(SdkConstants.ATTR_ICON, String.class));
                    return null;
                } else if (command.equals("login")) {
                    tool.login();
                    return null;
                } else if (command.equals("update.self")) {
                    Tr builder = new Tr(data);
                    tool.updateSelf((String) builder.T("id", String.class), (String) builder.T("nick", String.class));
                    return null;
                } else if (command.equals("logout")) {
                    tool.logout();
                    return null;
                } else if (command.equals("record.update")) {
                    Tr builder2 = new Tr(data);
                    int timeRemain = ((Integer) builder2.T(MainUI.NET_TIME_, Integer.class)).intValue();
                    tool.updateRecordWin(timeRemain, (String) builder2.T("id", String.class), (String) builder2.T("nick", String.class));
                    return null;
                } else if (command.equals("record.dismiss")) {
                    tool.dismissRecordWin(Boolean.valueOf(Boolean.parseBoolean(new String(data))).booleanValue());
                    return null;
                } else if (command.equals("notify.status")) {
                    tool.updateNotifyStatus(((Boolean) new Tr(data).T(SdkConstants.ATTR_ENABLED, Boolean.class)).booleanValue());
                    return null;
                } else if (command.equals("chat.show")) {
                    Tr builder3 = new Tr(data);
                    Log.d("demo", builder3.toString());
                    List<WechatMessage> msgList = com.T.T.T.Tr((String) builder3.T("message", String.class), WechatMessage.class);
                    tool.showChat((String) builder3.T("contactId", String.class), (String) builder3.T("contactNick", String.class), msgList);
                    return null;
                } else if (command.equals("notify.show")) {
                    Tr builder4 = new Tr(data);
                    boolean casting = ((Boolean) builder4.T("hasSpeak", Boolean.TYPE)).booleanValue();
                    boolean isGroup = ((Boolean) builder4.T("isGroup", Boolean.TYPE)).booleanValue();
                    tool.updateNotify((String) builder4.T("msgId", String.class), (String) builder4.T("id", String.class), (String) builder4.T("nick", String.class), isGroup, casting);
                    return null;
                } else if (!command.equals("notify.cancel")) {
                    return null;
                } else {
                    tool.dismissNotify();
                    return null;
                }
            }
        });
        Tr builder = new Tr();
        builder.T("blockUI", (Object) Boolean.valueOf(blockUI));
        Tn.Tr().T("com.txznet.webchat", "txz.webchat.tool.set", builder.Ty(), (Tn.Tr) null);
    }
}
