package com.txznet.comm.Tr;

import android.text.TextUtils;
import com.txznet.T.T;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Tr.Tr.T5;
import com.txznet.comm.Tr.Tr.TZ;
import com.txznet.comm.Tr.Tr.Th;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.Tr.Tr.Tv;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZConfigManager;
import com.txznet.sdk.TXZNetDataProvider;
import com.txznet.sdk.TXZWheelControlManager;

/* compiled from: Proguard */
public class Ty {
    public static byte[] T(String packageName, String command, final byte[] data) {
        if (!TextUtils.equals("comm.log", command) && !TextUtils.equals("tool.loc.updateLoc", command)) {
            try {
                Tn.T("music:receive:handler:AIDL:" + T.Tr().getPackageName() + ",from:" + packageName + "/" + command);
            } catch (Exception e) {
                Tn.T("music:receive:handler:AIDL:null,from:" + packageName + "/" + command);
            }
        }
        if (command.startsWith("comm.tts.event.")) {
            return Th.T(packageName, command.substring("comm.tts.event.".length()), data);
        }
        if (command.startsWith("comm.asr.event")) {
            return TZ(packageName, command, data);
        }
        if (command.startsWith("comm.status.")) {
            return T5.T(command.substring("comm.status.".length()));
        }
        if (command.startsWith("comm.record.event")) {
            return TE(packageName, command, data);
        }
        if (command.startsWith("comm.subscribe.broadcast")) {
            Tn.Tr().T(packageName, "", (byte[]) null, (Tn.Tr) null);
            return null;
        } else if (command.startsWith("comm.config.")) {
            return Tn(packageName, command.substring("comm.config.".length()), data);
        } else {
            if (command.equals("comm.log.setConsoleLogLevel")) {
                try {
                    T.T((Runnable) new Runnable() {
                        public void run() {
                            com.txznet.comm.Tr.Tr.Tn.T(Integer.parseInt(new String(data)));
                        }
                    });
                    return null;
                } catch (Exception e2) {
                    return null;
                }
            } else if (command.equals("comm.log.setFileLogLevel")) {
                try {
                    com.txznet.comm.Tr.Tr.Tn.Tr(Integer.parseInt(new String(data)));
                    return null;
                } catch (Exception e3) {
                    return null;
                }
            } else if (command.startsWith("comm.text.event")) {
                return T9(packageName, command, data);
            } else {
                if (command.startsWith("comm.plugin.")) {
                    return com.txznet.txz.T.Ty.Tr(packageName, command.substring("comm.plugin.".length()), data);
                }
                if (command.startsWith("comm.update.")) {
                }
                if (command.equals("comm.PackageInfo")) {
                    Tr json = new Tr();
                    json.T("versionCode", (Object) Integer.valueOf(com.txznet.comm.Tn.T.f353T));
                    json.T("versionName", (Object) com.txznet.comm.Tn.T.Tr);
                    json.T("sourceDir", (Object) T.Tr().getApplicationInfo().sourceDir);
                    json.T("versionCompile", (Object) TXZConfigManager.VERSION);
                    return json.Ty();
                } else if (command.startsWith("comm.netdata.resp.")) {
                    return Tk(packageName, command, data);
                } else {
                    if (command.startsWith("txz.wheelcontrol.notify.")) {
                        return Ty(packageName, command, data);
                    }
                    if (command.startsWith("comm.configer.")) {
                        return Tr(packageName, command.substring("comm.configer.".length()), data);
                    }
                    return null;
                }
            }
        }
    }

    private static byte[] Tr(String packageName, String command, byte[] data) {
        com.txznet.comm.Tr.Tr.Tn.T("preInvokeCommConfigEvent  cmd" + command + ",from:" + packageName);
        if (command.startsWith("navControl.")) {
            return com.txznet.comm.T.Tr.T().T(packageName, command.substring("navControl.".length()), data);
        }
        return null;
    }

    private static byte[] Ty(String packageName, String command, byte[] data) {
        return TXZWheelControlManager.getInstance().notifyCallback(command, data);
    }

    private static byte[] Tn(String packageName, String command, final byte[] data) {
        if (command.equals("showHelpInfos")) {
            com.txznet.comm.Tr.Tr.Tr.T(Boolean.parseBoolean(new String(data)));
        } else if (command.equals("showSettings")) {
            com.txznet.comm.Tr.Tr.Tr.Tr(Boolean.parseBoolean(new String(data)));
        } else if (command.equals("showCloseIcon")) {
            com.txznet.comm.Tr.Tr.Tr.Ty(Boolean.parseBoolean(new String(data)));
        } else if (command.equals("syncData")) {
            new Thread() {
                public void run() {
                    com.txznet.comm.Tr.Tr.Tr.T(new String(data));
                }
            }.start();
        } else if (command.equals("tts.setDefaultAudioStream")) {
            Th.f390T = Integer.parseInt(new String(data));
        } else if (command.equals("restore")) {
            com.txznet.comm.Tr.Tr.Tr.TE();
        }
        return null;
    }

    private static byte[] T9(String packageName, String command, byte[] data) {
        if (command.equals("comm.text.event.result")) {
            Tv.T("result", data);
        } else if (command.equals("comm.text.event.cancel")) {
            Tv.T("cancel", data);
        } else if (command.equals("comm.text.event.error")) {
            Tv.T("error", data);
        }
        return null;
    }

    private static byte[] Tk(String packageName, String command, byte[] data) {
        return TXZNetDataProvider.getInstance().notifyCallback(command.substring("comm.netdata.resp.".length()), data);
    }

    private static byte[] TZ(String packageName, String command, byte[] data) {
        if (command.equals("comm.asr.event.success")) {
            com.txznet.comm.Tr.Tr.T.T("success", data);
            return null;
        } else if (command.equals("comm.asr.event.cancel")) {
            com.txznet.comm.Tr.Tr.T.T("cancel", data);
            return null;
        } else if (command.equals("comm.asr.event.error")) {
            com.txznet.comm.Tr.Tr.T.T("error", data);
            return null;
        } else if (command.equals("comm.asr.event.end")) {
            com.txznet.comm.Tr.Tr.T.T("end", data);
            return null;
        } else if (command.equals("comm.asr.event.start")) {
            com.txznet.comm.Tr.Tr.T.T("start", data);
            return null;
        } else if (command.equals("comm.asr.event.abort")) {
            com.txznet.comm.Tr.Tr.T.T("abort", data);
            return null;
        } else if (command.equals("comm.asr.event.volume")) {
            com.txznet.comm.Tr.Tr.T.T("volume", data);
            return null;
        } else if (command.equals("comm.asr.event.regcmdnotify")) {
            com.txznet.comm.Tr.Tr.T.T("regnotify", data);
            return null;
        } else if (command.equals("comm.asr.event.onWakeupAsrResult")) {
            com.txznet.comm.Tr.Tr.T.Tr(new String(data));
            return null;
        } else if (command.equals("comm.asr.event.onTtsEnd")) {
            com.txznet.comm.Tr.Tr.T.Tn(new String(data));
            return null;
        } else if (command.equals("comm.asr.event.onTtsBegin")) {
            com.txznet.comm.Tr.Tr.T.Ty(new String(data));
            return null;
        } else if (command.equals("comm.asr.event.onSpeechEnd")) {
            com.txznet.comm.Tr.Tr.T.Tk(new String(data));
            return null;
        } else if (!command.equals("comm.asr.event.onSpeechBegin")) {
            return null;
        } else {
            com.txznet.comm.Tr.Tr.T.T9(new String(data));
            return null;
        }
    }

    private static byte[] TE(String packageName, String command, byte[] data) {
        if (command.equals("comm.record.event.begin")) {
            TZ.T("begin", data);
            return null;
        } else if (command.equals("comm.record.event.end")) {
            TZ.T("end", data);
            return null;
        } else if (command.equals("comm.record.event.parse")) {
            TZ.T("parse", data);
            return null;
        } else if (command.equals("comm.record.event.cancel")) {
            TZ.T("cancel", data);
            return null;
        } else if (command.equals("comm.record.event.error")) {
            TZ.T("error", data);
            return null;
        } else if (command.equals("comm.record.event.mp3buf")) {
            TZ.T("mp3buf", data);
            return null;
        } else if (command.equals("comm.record.event.mute")) {
            TZ.T("mute", data);
            return null;
        } else if (command.equals("comm.record.event.mutetimeout")) {
            TZ.T("mutetimeout", data);
            return null;
        } else if (command.equals("comm.record.event.speechtimeout")) {
            TZ.T("speechtimeout", data);
            return null;
        } else if (!command.equals("comm.record.event.volume")) {
            return null;
        } else {
            TZ.T("volume", data);
            return null;
        }
    }
}
