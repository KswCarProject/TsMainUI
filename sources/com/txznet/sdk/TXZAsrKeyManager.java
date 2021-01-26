package com.txznet.sdk;

import android.text.TextUtils;
import com.android.SdkConstants;
import com.ts.bt.ContactInfo;
import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: Proguard */
public class TXZAsrKeyManager {
    private static TXZAsrKeyManager T9 = new TXZAsrKeyManager();

    /* renamed from: T  reason: collision with root package name */
    AsrKeySource[] f693T;
    /* access modifiers changed from: private */
    public CommCmdsTool TE;
    private boolean TZ = false;
    private String[] Tk;
    Map<String, Set<String>> Tn;
    String[] Tr;
    AsrKeySource[] Ty;

    /* compiled from: Proguard */
    public static class AsrKeyType {
        public static final String ASK_REMAIN = "ASK_REMAIN";
        public static final String AUTO_MODE = "AUTO_MODE";
        public static final String BACK_HOME = "BACK_HOME";
        public static final String BACK_NAVI = "BACK_NAVI";
        public static final String BUZOUGAOSU = "BUZOUGAOSU";
        public static final String CANCEL_NAV = "CANCEL_NAV";
        public static final String CAR_DIRECT = "CAR_DIRECT";
        public static final String CLOSE_DOG = "CLOSE_DOG";
        public static final String CLOSE_MAP = "CLOSE_MAP";
        public static final String CLOSE_TRAFFIC = "CLOSE_TRAFFIC";
        public static final String DONGBEIHUA = "DONGBEIHUA";
        public static final String DUOBIYONGDU = "DUOBIYONGDU";
        public static final String EXIT_NAV = "EXIT_NAV";
        public static final String EXPORT_MODE = "EXPERT_MODE";
        public static final String GAOSUYOUXIAN = "GAOSUYOUXIAN";
        public static final String GO_COMPANY = "GO_COMPANY";
        public static final String GUANGDONGHUA = "GUANGDONGHUA";
        public static final String GUODEGANG = "GUODEGANG";
        public static final String GUOYU_GG = "GUOYU_GG";
        public static final String GUOYU_MM = "GUOYU_MM";
        public static final String HENANHUA = "HENANHUA";
        public static final String HOW_NAVI = "HOW_NAVI";
        public static final String HUNANHUA = "HUNANHUA";
        public static final String JINSHA = "JINSHA";
        public static final String LESS_DISTANCE = "LESS_DISTANCE";
        public static final String LESS_MONEY = "LESS_MONEY";
        public static final String LIGHT_MODE = "LIGHT_MODE";
        public static final String LIMIT_SPEED = "LIMIT_SPEED";
        public static final String LINZHILIN = "LINZHILIN";
        public static final String MEADWAR_MODE = "MEADWAR_MODE";
        public static final String MENGMENGDA = "MENGMENGDA";
        public static final String MUTE_MODE = "MUTE_MODE";
        public static final String NAV_RES_PREFIX = "RS_NAV_CMD_";
        public static final String NAV_WAY_POI_CMD_BANK = "NAV_WAY_POI_CMD_BANK";
        public static final String NAV_WAY_POI_CMD_GAS = "NAV_WAY_POI_CMD_GAS";
        public static final String NAV_WAY_POI_CMD_GO_ATM = "NAV_WAY_POI_CMD_ATM";
        public static final String NAV_WAY_POI_CMD_GO_GASTATION = "NAV_WAY_POI_CMD_GASTATION";
        public static final String NAV_WAY_POI_CMD_GO_REPAIR = "NAV_WAY_POI_CMD_REPAIR";
        public static final String NAV_WAY_POI_CMD_GO_TOILET = "NAV_WAY_POI_CMD_TOILET";
        public static final String NAV_WAY_POI_CMD_HOTEL = "NAV_WAY_POI_CMD_HOTEL";
        public static final String NAV_WAY_POI_CMD_PARK = "NAV_WAY_POI_CMD_PARK";
        public static final String NAV_WAY_POI_CMD_RESTAURANT = "NAV_WAY_POI_CMD_RESTAURANT";
        public static final String NAV_WAY_POI_CMD_SERVICE = "NAV_WAY_POI_CMD_SERVICE";
        public static final String NAV_WAY_POI_CMD_SPOTS = "NAV_WAY_POI_CMD_SPOTS";
        public static final String NAV_WAY_POI_CMD_TOILET = "NAV_WAY_POI_CMD_TOILET";
        public static final String NIGHT_MODE = "NIGHT_MODE";
        public static final String NORTH_DIRECT = "NORTH_DIRECT";
        public static final String OPEN_DOG = "OPEN_DOG";
        public static final String OPEN_TRAFFIC = "OPEN_TRAFFIC";
        public static final String SICHUANHUA = "SICHUANHUA";
        public static final String START_NAVI = "START_NAVI";
        public static final String SWITCH_ROLE = "SWITCH_ROLE";
        public static final String TAIWANHUA = "TAIWANHUA";
        public static final String THREE_MODE = "THREE_MODE";
        public static final String TUIJIANLUXIAN = "TUIJIANLUXIAN";
        public static final String TWO_MODE = "TWO_MODE";
        public static final String VIEW_ALL = "VIEW_ALL";
        public static final String ZHOUXINGXING = "ZHOUXINGXING";
        public static final String ZOOM_IN = "ZOOM_IN";
        public static final String ZOOM_OUT = "ZOOM_OUT";
    }

    private TXZAsrKeyManager() {
    }

    public static TXZAsrKeyManager getInstance() {
        return T9;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.f693T != null) {
            T(this.f693T, (Tn.Tr) null);
        }
        if (this.Tr != null) {
            forbidAsrKeys(this.Tr, (Tn.Tr) null);
        }
        if (this.Tk != null) {
            unForbidKeys(this.Tk);
        }
        if (this.Ty != null) {
            modifyAsrKeyCmds(this.Ty, (Tn.Tr) null);
        }
        if (this.Tn != null) {
            T(this.Tn);
        }
        if (this.TZ) {
            setCommCmdsTool(this.TE);
        }
    }

    /* compiled from: Proguard */
    public static class AsrKeySource {

        /* renamed from: T  reason: collision with root package name */
        private String f695T;
        private String[] Tr;

        public AsrKeySource(String type) {
            this.f695T = type;
        }

        public AsrKeySource(String type, List<String> cmds) {
            this.f695T = type;
            int size = cmds != null ? cmds.size() : 0;
            if (this.Tr == null) {
                this.Tr = new String[size];
            }
            this.Tr = (String[]) cmds.toArray(new String[size]);
        }

        public AsrKeySource(String type, String[] cmds) {
            this.f695T = type;
            this.Tr = cmds;
        }

        public String getKeyType() {
            return this.f695T;
        }

        public String[] getKeyCmds() {
            return this.Tr;
        }

        public void setKeyCmds(String[] cmds) {
            this.Tr = cmds;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f695T);
            sb.append(ContactInfo.COMBINATION_SEPERATOR);
            if (this.Tr != null) {
                for (String keyword : this.Tr) {
                    sb.append(keyword);
                    sb.append(ContactInfo.COMBINATION_SEPERATOR);
                }
            }
            return sb.toString();
        }

        public static AsrKeySource assign(String json) {
            String[] arrays;
            if (TextUtils.isEmpty(json) || (arrays = json.split(ContactInfo.COMBINATION_SEPERATOR)) == null) {
                return null;
            }
            int len = arrays.length;
            String type = arrays[0];
            String[] cmds = new String[(len - 1)];
            System.arraycopy(arrays, 1, cmds, 0, len - 1);
            return new AsrKeySource(type, cmds);
        }

        public AsrKeySource copy() {
            AsrKeySource aks = new AsrKeySource(this.f695T);
            if (this.Tr != null) {
                String[] cmds = new String[this.Tr.length];
                System.arraycopy(this.Tr, 0, cmds, 0, this.Tr.length);
                aks.setKeyCmds(cmds);
            }
            return aks;
        }
    }

    /* compiled from: Proguard */
    public static class AsrSources {

        /* renamed from: T  reason: collision with root package name */
        List<AsrKeySource> f696T;

        public List<AsrKeySource> getAsrKeySources() {
            return this.f696T;
        }

        public void setAsrKeySources(List<AsrKeySource> akss) {
            this.f696T = akss;
        }

        public void addAsrKeySource(AsrKeySource aks) {
            if (this.f696T == null) {
                this.f696T = new ArrayList();
            }
            this.f696T.add(aks);
        }

        public void addAsrKeySourceByTypeKeywords(String type, List<String> keywords) {
            addAsrKeySource(new AsrKeySource(type, keywords));
        }

        public void addAsrKeySourceByTypeKeywords(String type, String... keywords) {
            addAsrKeySource(new AsrKeySource(type, keywords));
        }

        public void modifyAsrKeyCmds(String type, String... cmds) {
            if (this.f696T != null) {
                for (AsrKeySource aks : this.f696T) {
                    if (aks.getKeyType().equals(type)) {
                        aks.setKeyCmds(cmds);
                        return;
                    }
                }
            }
        }

        public void removeAsrKeySourceByType(String type) {
            if (this.f696T != null) {
                for (AsrKeySource aks : this.f696T) {
                    if (aks.getKeyType().equals(type)) {
                        this.f696T.remove(aks);
                        return;
                    }
                }
            }
        }

        public void removeAsrKeySourceByType(Collection<String> types) {
            if (types != null) {
                for (String type : types) {
                    removeAsrKeySourceByType(type);
                }
            }
        }

        public void removeAsrKeySourceByType(String... types) {
            if (types != null) {
                for (String type : types) {
                    removeAsrKeySourceByType(type);
                }
            }
        }

        public byte[] toBytes() {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (this.f696T != null && i < this.f696T.size()) {
                sb.append(this.f696T.get(i).toString());
                sb.append(";");
                i++;
            }
            String json = sb.toString();
            if (!TextUtils.isEmpty(json)) {
                return json.getBytes();
            }
            return null;
        }

        public static AsrSources assign(byte[] data) {
            String[] asrJson;
            if (data != null) {
                String json = new String(data);
                if (!TextUtils.isEmpty(json) && (asrJson = json.split(";")) != null) {
                    AsrSources as = new AsrSources();
                    for (String j : asrJson) {
                        as.addAsrKeySource(AsrKeySource.assign(j));
                    }
                    return as;
                }
            }
            return null;
        }

        public AsrSources copy() {
            AsrSources asPos = new AsrSources();
            List<AsrKeySource> akss = getAsrKeySources();
            if (akss != null) {
                List<AsrKeySource> aksList = new ArrayList<>();
                for (AsrKeySource aks : akss) {
                    aksList.add(aks.copy());
                }
                asPos.setAsrKeySources(aksList);
            }
            return asPos;
        }
    }

    /* access modifiers changed from: package-private */
    public void T(AsrKeySource[] akss, Tn.Tr gdc) {
        this.f693T = akss;
        if (this.f693T != null) {
            AsrSources as = new AsrSources();
            for (AsrKeySource aks : akss) {
                as.addAsrKeySource(aks);
            }
            Tn.Tr().T("com.txznet.txz", "txz.nav.asr.key.syncKeySources", as.toBytes(), gdc);
            return;
        }
        Tn.Tr().T("com.txznet.txz", "txz.nav.asr.key.syncKeySources", (byte[]) null, gdc);
    }

    public void forbidAsrKeys(String[] types, Tn.Tr gdc) {
        this.Tr = types;
        if (this.Tr != null) {
            StringBuilder sb = new StringBuilder();
            for (String type : types) {
                sb.append(type);
                sb.append(ContactInfo.COMBINATION_SEPERATOR);
            }
            Tn.Tr().T("com.txznet.txz", "txz.nav.asr.key.forbidKeys", sb.toString().getBytes(), gdc);
        }
    }

    public void unForbidKeys(String[] types) {
        this.Tk = types;
        if (this.Tk != null) {
            StringBuilder sb = new StringBuilder();
            for (String type : types) {
                sb.append(type);
                sb.append(ContactInfo.COMBINATION_SEPERATOR);
            }
            Tn.Tr().T("com.txznet.txz", "txz.nav.asr.key.unForbidKeys", sb.toString().getBytes(), (Tn.Tr) null);
        }
    }

    @Deprecated
    public void modifyAsrKeyCmds(AsrKeySource[] modifyArrays, Tn.Tr gdc) {
        this.Ty = modifyArrays;
        if (this.Ty != null) {
            AsrSources as = new AsrSources();
            for (AsrKeySource aks : this.Ty) {
                as.addAsrKeySource(aks);
            }
            Tn.Tr().T("com.txznet.txz", "txz.nav.asr.key.modify", as.toBytes(), gdc);
        }
    }

    public void modifyAsrKeyCmds(String type, String... cmds) {
        if (this.Tn == null) {
            this.Tn = new HashMap();
        }
        Set<String> cds = this.Tn.get(type);
        if (cds == null) {
            cds = new HashSet<>();
        }
        cds.clear();
        if (cmds != null) {
            for (String cmd : cmds) {
                cds.add(cmd);
            }
        }
        this.Tn.put(type, cds);
        T(this.Tn);
    }

    private void T(Map<String, Set<String>> keyMaps) {
        AsrSources as = new AsrSources();
        for (String key : keyMaps.keySet()) {
            as.addAsrKeySourceByTypeKeywords(key, (String[]) keyMaps.get(key).toArray(new String[keyMaps.get(key).size()]));
        }
        Tn.Tr().T("com.txznet.txz", "txz.nav.asr.key.modify", as.toBytes(), (Tn.Tr) null);
    }

    public void setCommCmdsTool(CommCmdsTool tool) {
        this.TZ = true;
        this.TE = tool;
        if (this.TE == null) {
            Tn.Tr().T("com.txznet.txz", "txz.sys.commcmds.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("tool.ccw.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (TXZAsrKeyManager.this.TE == null) {
                    return SdkConstants.VALUE_FALSE.getBytes();
                }
                if (command.equals("handle_screen")) {
                    return (TXZAsrKeyManager.this.TE.handleScreen(Boolean.parseBoolean(new String(data))) + TXZResourceManager.STYLE_DEFAULT).getBytes();
                }
                if (command.equals("handle_front_camera")) {
                    return (TXZAsrKeyManager.this.TE.handleFrontCamera(Boolean.parseBoolean(new String(data))) + TXZResourceManager.STYLE_DEFAULT).getBytes();
                }
                if (command.equals("handle_back_camera")) {
                    return (TXZAsrKeyManager.this.TE.handleBackCamera(Boolean.parseBoolean(new String(data))) + TXZResourceManager.STYLE_DEFAULT).getBytes();
                }
                if (command.equals("backHome")) {
                    return (TXZAsrKeyManager.this.TE.backHome() + TXZResourceManager.STYLE_DEFAULT).getBytes();
                }
                if (command.equals("backNavi")) {
                    return (TXZAsrKeyManager.this.TE.backNavi() + TXZResourceManager.STYLE_DEFAULT).getBytes();
                }
                return (TXZAsrKeyManager.this.TE.procCmd(new String(data)) + TXZResourceManager.STYLE_DEFAULT).getBytes();
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.sys.commcmds.settool", (byte[]) null, (Tn.Tr) null);
    }

    /* compiled from: Proguard */
    public static abstract class CommCmdsTool {
        public abstract boolean handleBackCamera(boolean z);

        public abstract boolean handleFrontCamera(boolean z);

        public abstract boolean handleScreen(boolean z);

        public boolean backHome() {
            return false;
        }

        public boolean backNavi() {
            return false;
        }

        public boolean procCmd(String cmd) {
            return false;
        }
    }
}
