package com.txznet.sdk.bean;

import android.content.Intent;
import android.util.Log;
import com.ts.main.txz.AmapAuto;
import com.txznet.comm.Tr.Tr.T;
import com.txznet.sdk.TXZAsrKeyManager;
import com.txznet.sdk.TXZNavManager;
import com.txznet.sdk.TXZResourceManager;

/* compiled from: Proguard */
public class NavVoicePlugin {
    public static final int ROLE_DONGBEIHUA = 6;
    public static final int ROLE_GUANGDONGHUA = 3;
    public static final int ROLE_GUODEGANG = 5;
    public static final int ROLE_GUOYU_GG = 1;
    public static final int ROLE_GUOYU_MM = 0;
    public static final int ROLE_HENANHUA = 7;
    public static final int ROLE_HUNANHUA = 8;
    public static final int ROLE_LINZHILIN = 4;
    public static final int ROLE_SICHUANHUA = 9;
    public static final int ROLE_TAIWANHUA = 10;
    public static final int ROLE_ZHOUXINGXING = 2;
    public static final String RS_MAP_HINT_DONGBEIHUA = "将为您切换东北话";
    public static final String RS_MAP_HINT_GUANGDONGHUA = "将为您切换广东话";
    public static final String RS_MAP_HINT_GUODEGANG = "将为您切换郭德纲的声音";
    public static final String RS_MAP_HINT_GUOYU_GG = "将为您切换国语男声";
    public static final String RS_MAP_HINT_GUOYU_MM = "将为您切换国语女声";
    public static final String RS_MAP_HINT_HENANHUA = "将为您切换河南话";
    public static final String RS_MAP_HINT_HUNANHUA = "将为您切换湖南话";
    public static final String RS_MAP_HINT_IS_DONGBEIHUA = "现在已是东北话在为您播报";
    public static final String RS_MAP_HINT_IS_GUANGDONGHUA = "现在已是广东话在为您播报";
    public static final String RS_MAP_HINT_IS_GUODEGANG = "现在已是郭德纲在为您播报";
    public static final String RS_MAP_HINT_IS_GUOYU_GG = "现在已是国语男声在为您播报";
    public static final String RS_MAP_HINT_IS_GUOYU_MM = "现在已是国语女声在为您播报";
    public static final String RS_MAP_HINT_IS_HENANHUA = "现在已是河南话在为您播报";
    public static final String RS_MAP_HINT_IS_HUNANHUA = "现在已是湖南话在为您播报";
    public static final String RS_MAP_HINT_IS_LINZHILIN = "现在已是林志玲在为您播报";
    public static final String RS_MAP_HINT_IS_SICHUANHUA = "现在已是四川话在为您播报";
    public static final String RS_MAP_HINT_IS_TAIWANHUA = "现在已是台湾话在为您播报";
    public static final String RS_MAP_HINT_IS_ZHOUXINGXING = "现在已是周星驰在为您播报";
    public static final String RS_MAP_HINT_LINZHILIN = "将为您切换林志玲的声音";
    public static final String RS_MAP_HINT_SICHUANHUA = "将为您切换四川话";
    public static final String RS_MAP_HINT_TAIWANHUA = "将为您切换台湾话";
    public static final String RS_MAP_HINT_ZHOUXINGXING = "将为您切换周星星的声音";

    /* renamed from: T  reason: collision with root package name */
    TXZNavManager.CallBack f860T;
    private String[] T9 = {TXZAsrKeyManager.AsrKeyType.SWITCH_ROLE, TXZAsrKeyManager.AsrKeyType.GUOYU_MM, TXZAsrKeyManager.AsrKeyType.GUOYU_GG, TXZAsrKeyManager.AsrKeyType.ZHOUXINGXING, TXZAsrKeyManager.AsrKeyType.GUANGDONGHUA, TXZAsrKeyManager.AsrKeyType.LINZHILIN, TXZAsrKeyManager.AsrKeyType.GUODEGANG, TXZAsrKeyManager.AsrKeyType.DONGBEIHUA, TXZAsrKeyManager.AsrKeyType.HENANHUA, TXZAsrKeyManager.AsrKeyType.HUNANHUA, TXZAsrKeyManager.AsrKeyType.SICHUANHUA, TXZAsrKeyManager.AsrKeyType.TAIWANHUA};
    private String[][] Tk = {new String[]{"切换导航声音"}, new String[]{"切换为国语女声", "国语女声来播"}, new String[]{"切换为国语男声", "国语男声来播"}, new String[]{"切换为周星驰", "周星星来播", "周星驰来播", "周星驰出来", "周星星出来", "我想听周星星来播", "我想听周星驰来播"}, new String[]{"切换为广东话", "广东话来播"}, new String[]{"切换为林志玲", "林志玲来播", "林志玲出来", "我想听林志玲来播"}, new String[]{"切换为郭德纲", "郭德纲来播", "郭德纲出来", "我想听郭德纲来播"}, new String[]{"切换为东北话", "东北话来播"}, new String[]{"切换为河南话", "河南话来播"}, new String[]{"切换为湖南话", "湖南话来播"}, new String[]{"切换为四川话", "四川话来播"}, new String[]{"切换为台湾话", "台湾话来播"}};
    int Tn;
    boolean Tr;
    T.C0015T Ty;

    public void setNavVoiceCmdCallback(TXZNavManager.CallBack callBack) {
        this.f860T = callBack;
    }

    public void registerVoiceCmds(String packageName) {
        if (!this.Tr && packageName.startsWith("com.autonavi.")) {
            this.Ty = new T.C0015T() {
                public boolean needAsrState() {
                    return false;
                }

                public String getTaskId() {
                    return "TASK_VOICE_CMD";
                }

                public void onCommandSelected(String type, String command) {
                    if (TXZAsrKeyManager.AsrKeyType.GUOYU_MM.equals(type)) {
                        NavVoicePlugin.this.T(isWakeupResult(), 0);
                    } else if (TXZAsrKeyManager.AsrKeyType.GUOYU_GG.equals(type)) {
                        NavVoicePlugin.this.T(isWakeupResult(), 1);
                    } else if (TXZAsrKeyManager.AsrKeyType.ZHOUXINGXING.equals(type)) {
                        NavVoicePlugin.this.T(isWakeupResult(), 2);
                    } else if (TXZAsrKeyManager.AsrKeyType.GUANGDONGHUA.equals(type)) {
                        NavVoicePlugin.this.T(isWakeupResult(), 3);
                    } else if (TXZAsrKeyManager.AsrKeyType.LINZHILIN.equals(type)) {
                        NavVoicePlugin.this.T(isWakeupResult(), 4);
                    } else if (TXZAsrKeyManager.AsrKeyType.GUODEGANG.equals(type)) {
                        NavVoicePlugin.this.T(isWakeupResult(), 5);
                    } else if (TXZAsrKeyManager.AsrKeyType.DONGBEIHUA.equals(type)) {
                        NavVoicePlugin.this.T(isWakeupResult(), 6);
                    } else if (TXZAsrKeyManager.AsrKeyType.HENANHUA.equals(type)) {
                        NavVoicePlugin.this.T(isWakeupResult(), 7);
                    } else if (TXZAsrKeyManager.AsrKeyType.HUNANHUA.equals(type)) {
                        NavVoicePlugin.this.T(isWakeupResult(), 8);
                    } else if (TXZAsrKeyManager.AsrKeyType.SICHUANHUA.equals(type)) {
                        NavVoicePlugin.this.T(isWakeupResult(), 9);
                    } else if (TXZAsrKeyManager.AsrKeyType.TAIWANHUA.equals(type)) {
                        NavVoicePlugin.this.T(isWakeupResult(), 10);
                    } else if (TXZAsrKeyManager.AsrKeyType.SWITCH_ROLE.equals(type)) {
                        int role = NavVoicePlugin.this.Tn + 1;
                        if (role > 10) {
                            role = 0;
                        }
                        NavVoicePlugin.this.T(isWakeupResult(), role);
                    }
                }
            };
            for (int i = 0; i < this.T9.length; i++) {
                String[] cmds = this.f860T.getTypeCmds(this.T9[i]);
                if (cmds == null || cmds.length == 0) {
                    this.Ty.addCommand(this.T9[i], this.Tk[i]);
                } else {
                    this.Ty.addCommand(this.T9[i], cmds);
                }
            }
            this.Tr = true;
            T.T((T.Tk) this.Ty);
        }
    }

    /* access modifiers changed from: private */
    public void T(boolean isWakeup, int role) {
        if (this.Tn == role) {
            TXZResourceManager.getInstance().speakTextOnRecordWin(getTTS(role), true, (Runnable) null);
            return;
        }
        this.Tn = role;
        if (!isWakeup) {
            TXZResourceManager.getInstance().speakTextOnRecordWin(getSetTTS(role), true, new Runnable() {
                public void run() {
                    NavVoicePlugin.this.setTtsRole(NavVoicePlugin.this.Tn);
                }
            });
        } else {
            setTtsRole(this.Tn);
        }
    }

    /* access modifiers changed from: private */
    public void setTtsRole(int role) {
        Intent intent = new Intent(AmapAuto.BROADCAST_AMAP_REV);
        intent.putExtra("KEY_TYPE", 10044);
        intent.putExtra("VOICE_ROLE", role);
        Log.d("TtsRole", "TtsRole:" + role);
        com.txznet.comm.Tr.T.Tr().sendBroadcast(intent);
    }

    public void registerAgain() {
        if (this.Ty != null && !this.Tr) {
            this.Tr = true;
            T.T((T.Tk) this.Ty);
        }
    }

    public void unRegisterVoiceCmds() {
        if (this.Tr) {
            this.Tr = false;
            T.TZ("TASK_VOICE_CMD");
        }
    }

    public void resetAsrTask() {
        this.Ty = null;
        this.Tr = false;
    }

    public String getTTS(int role) {
        switch (role) {
            case 0:
                return RS_MAP_HINT_IS_GUOYU_MM;
            case 1:
                return RS_MAP_HINT_IS_GUOYU_GG;
            case 2:
                return RS_MAP_HINT_IS_ZHOUXINGXING;
            case 3:
                return RS_MAP_HINT_IS_GUANGDONGHUA;
            case 4:
                return RS_MAP_HINT_IS_LINZHILIN;
            case 5:
                return RS_MAP_HINT_IS_GUODEGANG;
            case 6:
                return RS_MAP_HINT_IS_DONGBEIHUA;
            case 7:
                return RS_MAP_HINT_IS_HENANHUA;
            case 8:
                return RS_MAP_HINT_IS_HUNANHUA;
            case 9:
                return RS_MAP_HINT_IS_SICHUANHUA;
            case 10:
                return RS_MAP_HINT_IS_TAIWANHUA;
            default:
                return "";
        }
    }

    public String getSetTTS(int role) {
        switch (role) {
            case 0:
                return RS_MAP_HINT_GUOYU_MM;
            case 1:
                return RS_MAP_HINT_GUOYU_GG;
            case 2:
                return RS_MAP_HINT_ZHOUXINGXING;
            case 3:
                return RS_MAP_HINT_GUANGDONGHUA;
            case 4:
                return RS_MAP_HINT_LINZHILIN;
            case 5:
                return RS_MAP_HINT_GUODEGANG;
            case 6:
                return RS_MAP_HINT_DONGBEIHUA;
            case 7:
                return RS_MAP_HINT_HENANHUA;
            case 8:
                return RS_MAP_HINT_HUNANHUA;
            case 9:
                return RS_MAP_HINT_SICHUANHUA;
            case 10:
                return RS_MAP_HINT_TAIWANHUA;
            default:
                return "";
        }
    }
}
