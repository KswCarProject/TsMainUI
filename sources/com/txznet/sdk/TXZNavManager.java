package com.txznet.sdk;

import android.text.TextUtils;
import com.Tn.T.T;
import com.android.SdkConstants;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.bean.NavVoicePlugin;
import com.txznet.sdk.bean.NaviInfo;
import com.txznet.sdk.bean.Poi;
import com.txznet.sdk.media.InvokeConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TXZNavManager {
    private static TXZNavManager TB = new TXZNavManager();

    /* renamed from: T  reason: collision with root package name */
    Boolean f760T;
    Boolean T5;
    String T6;
    boolean T9 = true;
    private Integer TA;
    private Boolean TD;
    Boolean TE;
    Boolean TF;
    private NavToolType TG = null;
    private boolean TK = false;
    private boolean TN = false;
    /* access modifiers changed from: private */
    public Object TO = null;
    Boolean TZ;
    Boolean Te;
    private Integer Tf = null;
    Boolean Th = null;
    NavVoicePlugin Tj;
    boolean Tk = true;
    boolean Tn = false;
    Boolean Tq;
    boolean Tr;
    /* access modifiers changed from: private */
    public List<NavStatusListener> Ts;
    private Boolean Tt;
    private Boolean Tu;
    Boolean Tv = null;
    /* access modifiers changed from: private */
    public TmcTool Tx = null;
    boolean Ty = true;

    /* compiled from: Proguard */
    public interface CallBack {
        String[] getTypeCmds(String str);
    }

    /* compiled from: Proguard */
    public interface GetTxzNaviInfoListener {
        void onUpdateNaviInfo(NaviInfo naviInfo);
    }

    /* compiled from: Proguard */
    public interface NavStatusListener {
        void onBeginNav(String str, Poi poi);

        void onDefaultNavHasSeted(String str);

        void onEnd(String str);

        void onEnter(String str);

        void onExit(String str);

        void onForeground(String str, boolean z);

        void onStart(String str);

        void onStatusUpdate(String str);
    }

    /* compiled from: Proguard */
    public interface NavTool {
        void enterNav();

        void exitNav();

        boolean isInNav();

        @Deprecated
        void navCompany();

        @Deprecated
        void navHome();

        void navToLoc(Poi poi);

        void setCompanyLoc(Poi poi);

        void setHomeLoc(Poi poi);

        void setStatusListener(NavToolStatusHighListener navToolStatusHighListener);

        @Deprecated
        void setStatusListener(NavToolStatusListener navToolStatusListener);

        void speakLimitSpeed();
    }

    /* compiled from: Proguard */
    public interface NavToolStatusListener {
        void enableExitAllNavTool(boolean z);

        void onEnd();

        void onExitApp();

        void onGetFocus();

        void onLoseFocus();

        void onPathUpdate(PathInfo pathInfo);

        void onStart();
    }

    /* compiled from: Proguard */
    public enum NavToolType {
        NAV_TOOL_TXZ,
        NAV_TOOL_BAIDU_MAP,
        NAV_TOOL_BAIDU_NAV,
        NAV_TOOL_BAIDU_NAV_HD,
        NAV_TOOL_GAODE_MAP,
        NAV_TOOL_GAODE_MAP_CAR,
        NAV_TOOL_GAODE_NAV,
        NAV_TOOL_KAILIDE_NAV,
        NAV_TOOL_MX_NAV,
        NAV_TOOL_QIHOO,
        NAV_TOOL_TX,
        NAV_TOOL_KGO,
        NAV_TOOL_TXZ_COMM
    }

    /* compiled from: Proguard */
    public interface TmcTool {

        /* compiled from: Proguard */
        public interface OperateListener {
            void dismiss();

            void startNotifyTraffic();
        }

        boolean isIgnore();

        boolean needWait();

        void onDismissDialog();

        boolean onSmartTraffic(T.C0000T t);

        boolean onViewDataUpdate(String str, String str2);

        void setOperateListener(OperateListener operateListener);
    }

    private TXZNavManager() {
    }

    public static TXZNavManager getInstance() {
        return TB;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.TK) {
            if (this.TO == null) {
                setNavTool((NavToolType) null);
            } else if (this.TO instanceof NavTool) {
                setNavTool((NavTool) this.TO);
            } else if (this.TO instanceof NavToolType) {
                setNavTool((NavToolType) this.TO);
            }
        }
        if (this.TG != null) {
            setNavDefaultTool(this.TG);
        }
        if (this.Tr) {
            enableAutoAMapCmd(this.Tk, this.Ty, this.Tn, this.T9);
        }
        if (this.TZ != null) {
            enableWakeupNavCmds(this.TZ.booleanValue());
        }
        if (this.T5 != null) {
            enableWakeupExitNav(this.T5.booleanValue());
        }
        if (this.TE != null) {
            exitInteractiveWhenBackPoi(this.TE.booleanValue());
        }
        if (this.Tv != null) {
            forceRegsiterMapOrder(this.Tv.booleanValue());
        }
        if (this.T6 != null && !TextUtils.isEmpty(this.T6)) {
            setNavCldPackageName(this.T6);
        }
        if (this.Tq != null) {
            enableSavePlanAfterPlan(this.Tq.booleanValue());
        }
        if (this.TN) {
            Tr();
        }
        if (this.f760T != null) {
            setUseActiveNav(this.f760T.booleanValue());
        }
        if (this.Tf != null) {
            setPlanAutoNaviDelay(this.Tf.intValue());
        }
        if (this.Te != null) {
            setRemoveNavConfirmDialog(this.Te.booleanValue());
        }
        if (this.Tu != null) {
            banNavAbility(this.Tu.booleanValue());
        }
        if (this.Tt != null) {
            setAlwayAskNav(this.Tt.booleanValue());
        }
        if (this.TD != null) {
            enableMultiNavigation(this.TD.booleanValue());
        }
        if (this.Th != null) {
            enableNavCmd(this.Th.booleanValue());
        }
        if (this.TF != null) {
            setIsCloseWhenSetHcAddr(this.TF.booleanValue());
        }
        if (this.Tx != null) {
            setTmcTool(this.Tx);
        }
        Ty();
    }

    /* compiled from: Proguard */
    public static abstract class NavToolStatusHighListener implements NavToolStatusListener {
        public static final int MARK_LIMITSPEED = 2;
        public static final int MARK_WAYPOI = 1;

        /* renamed from: T  reason: collision with root package name */
        private volatile int f769T;

        public void addFlag(int flag) {
            this.f769T |= flag & 1;
            this.f769T |= flag & 2;
            T();
        }

        public void clearFlag(int flag) {
            this.f769T &= ((flag & 1) | (flag & 2)) ^ -1;
            T();
        }

        private void T() {
            Tn.Tr().T("com.txznet.txz", "txz.nav.setRemoteFlag", (this.f769T + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
        }
    }

    /* compiled from: Proguard */
    public static class PathInfo {

        /* renamed from: T  reason: collision with root package name */
        String f771T;
        public int _id;
        public String fromPoiAddr;
        public double fromPoiLat;
        public double fromPoiLng;
        public String fromPoiName;
        public String toCity;
        public String toPoiAddr;
        public double toPoiLat;
        public double toPoiLng;
        public String toPoiName;
        public Integer totalDistance = 0;
        public Integer totalTime = 0;
        public List<WayInfo> wayInfos;

        /* compiled from: Proguard */
        public static class WayInfo {
            public String addr;
            public double lat;
            public double lng;
            public String name;

            public String toString() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("lat", this.lat);
                    jsonObject.put("lng", this.lng);
                    jsonObject.put("name", this.name);
                    jsonObject.put("addr", this.addr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jsonObject.toString();
            }

            public static WayInfo fromJson(String json) {
                WayInfo pathInfo = new WayInfo();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    pathInfo.lat = jsonObject.optDouble("lat");
                    pathInfo.lng = jsonObject.optDouble("lng");
                    pathInfo.name = jsonObject.optString("name");
                    pathInfo.addr = jsonObject.optString("addr");
                    return pathInfo;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        public void clear() {
            this.fromPoiAddr = null;
            this.fromPoiLat = 0.0d;
            this.fromPoiLng = 0.0d;
            this.fromPoiName = null;
            this.toCity = null;
            this.toPoiAddr = null;
            this.toPoiLat = 0.0d;
            this.toPoiLng = 0.0d;
            this.toPoiName = null;
            this.totalDistance = 0;
            this.totalTime = 0;
            this.f771T = null;
            this.wayInfos = null;
        }

        public String toString() {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("fromPoiLat", this.fromPoiLat);
                jsonObject.put("fromPoiLng", this.fromPoiLng);
                jsonObject.put("toPoiLat", this.toPoiLat);
                jsonObject.put("toPoiLng", this.toPoiLng);
                jsonObject.put("fromPoiAddr", this.fromPoiAddr);
                jsonObject.put("fromPoiName", this.fromPoiName);
                jsonObject.put("toPoiAddr", this.toPoiAddr);
                jsonObject.put("toPoiName", this.toPoiName);
                jsonObject.put("toCity", this.toCity);
                jsonObject.put("totalDistance", this.totalDistance);
                jsonObject.put("totalTime", this.totalTime);
                if (this.wayInfos != null) {
                    JSONArray jsonArray = new JSONArray();
                    for (WayInfo info : this.wayInfos) {
                        jsonArray.put(info.toString());
                    }
                    this.f771T = jsonArray.toString();
                    jsonObject.put("wayInfoStr", this.f771T);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonObject.toString();
        }

        public static PathInfo fromString(String json) {
            PathInfo pathInfo = new PathInfo();
            try {
                JSONObject jsonObject = new JSONObject(json);
                pathInfo.fromPoiLat = jsonObject.optDouble("fromPoiLat");
                pathInfo.fromPoiLng = jsonObject.optDouble("fromPoiLng");
                pathInfo.toPoiLat = jsonObject.optDouble("toPoiLat");
                pathInfo.toPoiLng = jsonObject.optDouble("toPoiLng");
                pathInfo.fromPoiAddr = jsonObject.optString("fromPoiAddr");
                pathInfo.fromPoiName = jsonObject.optString("fromPoiName");
                pathInfo.toPoiAddr = jsonObject.optString("toPoiAddr");
                pathInfo.toPoiName = jsonObject.optString("toPoiName");
                pathInfo.toCity = jsonObject.optString("toCity");
                pathInfo.totalDistance = Integer.valueOf(jsonObject.optInt("totalDistance"));
                pathInfo.totalTime = Integer.valueOf(jsonObject.optInt("totalTime"));
                pathInfo.f771T = jsonObject.optString("wayInfoStr");
                pathInfo.wayInfos = new ArrayList();
                if (TextUtils.isEmpty(pathInfo.f771T)) {
                    return pathInfo;
                }
                JSONArray jsonArray = new JSONArray(pathInfo.f771T);
                for (int i = 0; i < jsonArray.length(); i++) {
                    pathInfo.wayInfos.add(WayInfo.fromJson(jsonArray.getString(i)));
                }
                return pathInfo;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void setNavTool(NavTool tool) {
        this.TK = true;
        this.TO = tool;
        if (tool == null) {
            setNavTool((NavToolType) null);
            return;
        }
        tool.setStatusListener((NavToolStatusListener) new NavToolStatusListener() {
            public void onStart() {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyNavStatus", SdkConstants.VALUE_TRUE.getBytes(), (Tn.Tr) null);
            }

            public void onEnd() {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyNavStatus", SdkConstants.VALUE_FALSE.getBytes(), (Tn.Tr) null);
            }

            public void enableExitAllNavTool(boolean enable) {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyExitAllNav", (enable + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
            }

            public void onExitApp() {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyExitApp", SdkConstants.VALUE_TRUE.getBytes(), (Tn.Tr) null);
            }

            public void onGetFocus() {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyIsFocus", SdkConstants.VALUE_TRUE.getBytes(), (Tn.Tr) null);
            }

            public void onLoseFocus() {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyIsFocus", SdkConstants.VALUE_FALSE.getBytes(), (Tn.Tr) null);
            }

            public void onPathUpdate(PathInfo pathInfo) {
                byte[] bArr;
                Tn Tr = Tn.Tr();
                if (pathInfo != null) {
                    bArr = pathInfo.toString().getBytes();
                } else {
                    bArr = null;
                }
                Tr.T("com.txznet.txz", "txz.nav.notifyPathInfo", bArr, (Tn.Tr) null);
            }
        });
        tool.setStatusListener((NavToolStatusHighListener) new NavToolStatusHighListener() {
            public void onStart() {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyNavStatus", SdkConstants.VALUE_TRUE.getBytes(), (Tn.Tr) null);
            }

            public void onEnd() {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyNavStatus", SdkConstants.VALUE_FALSE.getBytes(), (Tn.Tr) null);
            }

            public void enableExitAllNavTool(boolean enable) {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyExitAllNav", (enable + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
            }

            public void onExitApp() {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyExitApp", SdkConstants.VALUE_TRUE.getBytes(), (Tn.Tr) null);
            }

            public void onGetFocus() {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyIsFocus", SdkConstants.VALUE_TRUE.getBytes(), (Tn.Tr) null);
            }

            public void onLoseFocus() {
                Tn.Tr().T("com.txznet.txz", "txz.nav.notifyIsFocus", SdkConstants.VALUE_FALSE.getBytes(), (Tn.Tr) null);
            }

            public void onPathUpdate(PathInfo pathInfo) {
                byte[] bArr;
                Tn Tr = Tn.Tr();
                if (pathInfo != null) {
                    bArr = pathInfo.toString().getBytes();
                } else {
                    bArr = null;
                }
                Tr.T("com.txznet.txz", "txz.nav.notifyPathInfo", bArr, (Tn.Tr) null);
            }
        });
        TXZService.T("tool.nav.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (TXZNavManager.this.TO != null && (TXZNavManager.this.TO instanceof NavTool)) {
                    NavTool tool = (NavTool) TXZNavManager.this.TO;
                    if (command.equals("isInNav")) {
                        return (TXZResourceManager.STYLE_DEFAULT + tool.isInNav()).getBytes();
                    }
                    if (command.equals("navHome")) {
                        tool.navHome();
                        return null;
                    } else if (command.equals("navCompany")) {
                        tool.navCompany();
                        return null;
                    } else if (command.equals("navTo")) {
                        tool.navToLoc(Poi.fromString(new String(data)));
                        return null;
                    } else if (command.equals("speakLimitSpeed")) {
                        tool.speakLimitSpeed();
                        return null;
                    } else if (command.equals("setHomeLoc")) {
                        tool.setHomeLoc(Poi.fromString(new String(data)));
                        return null;
                    } else if (command.equals("setCompanyLoc")) {
                        tool.setCompanyLoc(Poi.fromString(new String(data)));
                        return null;
                    } else if (command.equals("exitNav")) {
                        tool.exitNav();
                        return null;
                    } else if (!command.equals("enterNav")) {
                        return null;
                    } else {
                        tool.enterNav();
                        return null;
                    }
                } else if (command.equals("isInNav")) {
                    return SdkConstants.VALUE_FALSE.getBytes();
                } else {
                    return null;
                }
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.nav.settool", (byte[]) null, (Tn.Tr) null);
    }

    public void setNavStatusListener(NavStatusListener listener) {
        if (listener != null) {
            if (this.Ts == null) {
                this.Ts = new ArrayList();
            }
            this.TN = true;
            this.Ts.add(listener);
            Tr();
        }
    }

    private void Tr() {
        TXZService.T("status.nav.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                String pkn;
                String pkn2;
                String pkn3;
                String pkn4;
                String pkn5;
                String pkn6;
                String pkn7;
                if (TXZNavManager.this.Ts != null && TXZNavManager.this.Ts.size() > 0) {
                    if (command.equals("enter")) {
                        if (data != null) {
                            pkn7 = new String(data);
                        } else {
                            pkn7 = null;
                        }
                        for (NavStatusListener listener : TXZNavManager.this.Ts) {
                            listener.onEnter(pkn7);
                        }
                    } else if (command.equals(InvokeConstants.INVOKE_EXIT)) {
                        if (data != null) {
                            pkn6 = new String(data);
                        } else {
                            pkn6 = null;
                        }
                        for (NavStatusListener listener2 : TXZNavManager.this.Ts) {
                            listener2.onExit(pkn6);
                        }
                    } else if (command.equals("beginNav")) {
                        Tr jsonBuilder = new Tr(data);
                        Poi poi = Poi.fromString((String) jsonBuilder.T("poi", String.class));
                        String pkn8 = (String) jsonBuilder.T("packageName", String.class, null);
                        for (NavStatusListener listener3 : TXZNavManager.this.Ts) {
                            listener3.onBeginNav(pkn8, poi);
                        }
                    } else if (command.equals(SdkConstants.ATTR_FOREGROUND)) {
                        if (data != null) {
                            pkn5 = new String(data);
                        } else {
                            pkn5 = null;
                        }
                        for (NavStatusListener listener4 : TXZNavManager.this.Ts) {
                            listener4.onForeground(pkn5, true);
                        }
                    } else if (command.equals(SdkConstants.ATTR_BACKGROUND)) {
                        if (data != null) {
                            pkn4 = new String(data);
                        } else {
                            pkn4 = null;
                        }
                        for (NavStatusListener listener5 : TXZNavManager.this.Ts) {
                            listener5.onForeground(pkn4, false);
                        }
                    } else if (command.equals("start")) {
                        if (data != null) {
                            pkn3 = new String(data);
                        } else {
                            pkn3 = null;
                        }
                        for (NavStatusListener listener6 : TXZNavManager.this.Ts) {
                            listener6.onStart(pkn3);
                        }
                    } else if (command.equals("end")) {
                        if (data != null) {
                            pkn2 = new String(data);
                        } else {
                            pkn2 = null;
                        }
                        for (NavStatusListener listener7 : TXZNavManager.this.Ts) {
                            listener7.onEnd(pkn2);
                        }
                    } else if (command.equals("defaultNav")) {
                        if (data != null) {
                            pkn = new String(data);
                        } else {
                            pkn = null;
                        }
                        for (NavStatusListener listener8 : TXZNavManager.this.Ts) {
                            listener8.onDefaultNavHasSeted(pkn);
                        }
                    }
                }
                return null;
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.nav.setStatusListener", (byte[]) null, (Tn.Tr) null);
    }

    public void setNavTool(NavToolType type) {
        this.TK = true;
        this.TO = type;
        if (type == null) {
            Tn.Tr().T("com.txznet.txz", "txz.nav.settool", TXZResourceManager.STYLE_DEFAULT.getBytes(), (Tn.Tr) null);
        } else {
            Tn.Tr().T("com.txznet.txz", "txz.nav.settool", T(type).getBytes(), (Tn.Tr) null);
        }
    }

    public void setNavDefaultTool(NavToolType type) {
        this.TG = type;
        if (type == null) {
            Tn.Tr().T("com.txznet.txz", "txz.nav.clearDefaultNav", (byte[]) null, (Tn.Tr) null);
        } else {
            Tn.Tr().T("com.txznet.txz", "txz.nav.setDefaultNav", T(type).getBytes(), (Tn.Tr) null);
        }
    }

    static String T(NavToolType type) {
        switch (type) {
            case NAV_TOOL_BAIDU_MAP:
                return "BAIDU_MAP";
            case NAV_TOOL_BAIDU_NAV:
                return "BAIDU_NAV";
            case NAV_TOOL_BAIDU_NAV_HD:
                return "BAIDU_NAV_HD";
            case NAV_TOOL_GAODE_MAP:
                return "GAODE_MAP";
            case NAV_TOOL_GAODE_MAP_CAR:
                return "GAODE_MAP_CAR";
            case NAV_TOOL_GAODE_NAV:
                return "GAODE_NAV";
            case NAV_TOOL_KAILIDE_NAV:
                return "KAILIDE_NAV";
            case NAV_TOOL_TXZ:
                return "TXZ";
            case NAV_TOOL_MX_NAV:
                return "MX_NAV";
            case NAV_TOOL_QIHOO:
                return "QIHOO_NAV";
            case NAV_TOOL_TX:
                return "TX_NAV";
            case NAV_TOOL_KGO:
                return "KGO_NAV";
            case NAV_TOOL_TXZ_COMM:
                return "TXZ_COMM";
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    public boolean setNavTool(String pkg) {
        if (pkg == null) {
            return false;
        }
        if ("com.baidu.BaiduMap".equals(pkg)) {
            setNavTool(NavToolType.NAV_TOOL_BAIDU_MAP);
            return true;
        } else if ("com.baidu.navi".equals(pkg)) {
            setNavTool(NavToolType.NAV_TOOL_BAIDU_NAV);
            return true;
        } else if ("com.baidu.navi.hd".equals(pkg)) {
            setNavTool(NavToolType.NAV_TOOL_BAIDU_NAV_HD);
            return true;
        } else if ("com.autonavi.minimap".equals(pkg)) {
            setNavTool(NavToolType.NAV_TOOL_GAODE_MAP);
            return true;
        } else if ("com.autonavi.amapauto".equals(pkg)) {
            setNavTool(NavToolType.NAV_TOOL_GAODE_MAP_CAR);
            return true;
        } else if ("com.autonavi.xmgd.navigator".equals(pkg)) {
            setNavTool(NavToolType.NAV_TOOL_GAODE_NAV);
            return true;
        } else if ("com.txznet.nav".equals(pkg)) {
            setNavTool(NavToolType.NAV_TOOL_TXZ);
            return true;
        } else if ("com.mxnavi.mxnavi".equals(pkg)) {
            setNavTool(NavToolType.NAV_TOOL_MX_NAV);
            return true;
        } else if ("cld.navi.kgomap".equals(pkg)) {
            setNavTool(NavToolType.NAV_TOOL_KGO);
            return false;
        } else if (!pkg.matches("^cld\\.navi\\.\\S+\\.mainframe$")) {
            return false;
        } else {
            setNavTool(NavToolType.NAV_TOOL_KAILIDE_NAV);
            return true;
        }
    }

    public void setUseActiveNav(boolean useActive) {
        this.f760T = Boolean.valueOf(useActive);
        Tn.Tr().T("com.txznet.txz", "txz.nav.useActiveNav", (useActive + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void banNavAbility(boolean isBan) {
        this.Tu = Boolean.valueOf(isBan);
        Tn.Tr().T("com.txznet.txz", "txz.nav.banNavTool", (isBan + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setAlwayAskNav(boolean isAlwayAsk) {
        this.Tt = Boolean.valueOf(isAlwayAsk);
        Tn.Tr().T("com.txznet.txz", "txz.nav.alwayAsk", (isAlwayAsk + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void enableMultiNavigation(boolean enable) {
        this.TD = Boolean.valueOf(enable);
        Tn.Tr().T("com.txznet.txz", "txz.nav.multinav", (enable + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setPlanAutoNaviDelay(int delay) {
        this.Tf = Integer.valueOf(delay);
        Tn.Tr().T("com.txznet.txz", "txz.nav.autoNaviDelay", (delay + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void navToLoc(Poi point) {
        try {
            JSONObject json = new JSONObject();
            json.put("lat", point.getLat());
            json.put("lng", point.getLng());
            json.put("city", point.getCity());
            json.put("name", point.getName());
            json.put("geo", point.getGeoinfo());
            Tn.Tr().T("com.txznet.txz", "txz.nav.navTo", json.toString().getBytes(), (Tn.Tr) null);
        } catch (Exception e) {
        }
    }

    public void navToLocWithHint(String text, String tts, Poi point) {
        try {
            JSONObject json = new JSONObject();
            json.put(SdkConstants.ATTR_TEXT, text);
            json.put("tts", tts);
            json.put("lat", point.getLat());
            json.put("lng", point.getLng());
            json.put("city", point.getCity());
            json.put("name", point.getName());
            json.put("geo", point.getGeoinfo());
            Tn.Tr().T("com.txznet.txz", "txz.nav.navToLocWithHint", json.toString().getBytes(), (Tn.Tr) null);
        } catch (Exception e) {
        }
    }

    public void navHome() {
        Tn.Tr().T("com.txznet.txz", "txz.nav.navHome", (byte[]) null, (Tn.Tr) null);
    }

    public void navCompany() {
        Tn.Tr().T("com.txznet.txz", "txz.nav.navCompany", (byte[]) null, (Tn.Tr) null);
    }

    public boolean isInNav() {
        byte[] data = Tn.Tr().T("txz.nav.isInNav", (byte[]) null);
        if (data == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(data));
    }

    public Poi getHomeLocation() {
        byte[] data = Tn.Tr().T("txz.nav.getHomeLocation", (byte[]) null);
        if (data == null) {
            return null;
        }
        return Poi.fromString(new String(data));
    }

    public void setHomeLocation(Poi poi) {
        Tn.Tr().T("com.txznet.txz", "txz.nav.setHomeLocation", poi.toString().getBytes(), (Tn.Tr) null);
    }

    public void clearHomeLocation() {
        Tn.Tr().T("com.txznet.txz", "txz.nav.clearHomeLocation", (byte[]) null, (Tn.Tr) null);
    }

    public Poi getCompanyLocation() {
        byte[] data = Tn.Tr().T("txz.nav.getCompanyLocation", (byte[]) null);
        if (data == null) {
            return null;
        }
        return Poi.fromString(new String(data));
    }

    public void setCompanyLocation(Poi poi) {
        Tn.Tr().T("com.txznet.txz", "txz.nav.setCompanyLocation", poi.toString().getBytes(), (Tn.Tr) null);
    }

    public void clearCompanyLocation() {
        Tn.Tr().T("com.txznet.txz", "txz.nav.clearCompanyLocation", (byte[]) null, (Tn.Tr) null);
    }

    public void enterNav() {
        Tn.Tr().T("com.txznet.txz", "txz.nav.enterNav", (byte[]) null, (Tn.Tr) null);
    }

    public void exitNav() {
        Tn.Tr().T("com.txznet.txz", "txz.nav.exitNav", (byte[]) null, (Tn.Tr) null);
    }

    @Deprecated
    public void enableAutoAMapCmd(boolean enableCmd, boolean enableTraffic, boolean enable3D, boolean enableDirect) {
        this.Tr = true;
        this.Tk = enableCmd;
        this.Ty = enableTraffic;
        this.Tn = enable3D;
        this.T9 = enableDirect;
        try {
            Tr jb = new Tr();
            jb.T("enableCmd", (Object) Boolean.valueOf(enableCmd));
            jb.T("enableTraffic", (Object) Boolean.valueOf(enableTraffic));
            jb.T("enable3D", (Object) Boolean.valueOf(enable3D));
            jb.T("enableDirect", (Object) Boolean.valueOf(enableDirect));
            Tn.Tr().T("com.txznet.txz", "txz.nav.app.enablecmd", jb.Ty(), (Tn.Tr) null);
        } catch (Exception e) {
        }
    }

    public void enableWakeupNavCmds(boolean enableWakeup) {
        this.TZ = Boolean.valueOf(enableWakeup);
        Tn.Tr().T("com.txznet.txz", "txz.nav.app.enableWakeupNav", (TXZResourceManager.STYLE_DEFAULT + enableWakeup).getBytes(), (Tn.Tr) null);
    }

    @Deprecated
    public void flingPager(int pos) {
        Tn.Tr().T("com.txznet.txz", "txz.selector.selection", (pos + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void exitInteractiveWhenBackPoi(boolean isExit) {
        this.TE = Boolean.valueOf(isExit);
        Tn.Tr().T("com.txznet.txz", "txz.selector.exitBack", (isExit + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void enableWakeupExitNav(boolean enable) {
        this.T5 = Boolean.valueOf(enable);
        Tn.Tr().T("com.txznet.txz", "txz.nav.app.enableWakeupExit", (enable + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void forceRegsiterMapOrder(boolean isForce) {
        this.Tv = Boolean.valueOf(isForce);
        Tn.Tr().T("com.txznet.txz", "txz.nav.app.forceRegister", (isForce + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void enableNavCmd(boolean enable) {
        this.Th = Boolean.valueOf(enable);
        Tn.Tr().T("com.txznet.txz", "txz.nav.app.enableNavCmd", (enable + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setNavCldPackageName(String pkn) {
        this.T6 = pkn;
        Tn.Tr().T("com.txznet.txz", "txz.nav.app.cldpkn", this.T6.getBytes(), (Tn.Tr) null);
    }

    public void setRemoveNavConfirmDialog(boolean isRemove) {
        this.Te = Boolean.valueOf(isRemove);
        Tn.Tr().T("com.txznet.txz", "txz.nav.cutNavDialog", (isRemove + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void enableSavePlanAfterPlan(boolean enable) {
        this.Tq = Boolean.valueOf(enable);
        Tn.Tr().T("com.txznet.txz", "txz.nav.app.savePlan", (this.Tq + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setIsCloseWhenSetHcAddr(boolean isCloseWhenSetHcAddr) {
        this.TF = Boolean.valueOf(isCloseWhenSetHcAddr);
        Tn.Tr().T("com.txznet.txz", "txz.nav.isCloseWhenSetHcAddr", (isCloseWhenSetHcAddr + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public String getNavHistoryJson(int size) {
        Tn.Ty data = Tn.Tr().T("com.txznet.txz", "txz.nav.getNavHistory", (size + TXZResourceManager.STYLE_DEFAULT).getBytes());
        if (data != null) {
            return data.T();
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public void removeNavHistory(String dataStr) {
        Tn.Tr().T("com.txznet.txz", "txz.nav.removeNavHistory", dataStr.getBytes(), (Tn.Tr) null);
    }

    public String getDefaultNavTool() {
        Tn.Ty data = Tn.Tr().T("com.txznet.txz", "txz.nav.getDefaultNavTool", (byte[]) null);
        if (data != null) {
            return data.T();
        }
        return null;
    }

    public String getNavAppPkns() {
        Tn.Ty data = Tn.Tr().T("com.txznet.txz", "txz.nav.getNavApps", (byte[]) null);
        if (data != null) {
            return data.T();
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public void setNavPlanType(int type) {
        this.TA = Integer.valueOf(type);
        Tn.Tr().T("com.txznet.txz", "txz.nav.setNavPlanType", (type + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public int getNavPlanType() {
        Tn.Ty data = Tn.Tr().T("com.txznet.txz", "txz.nav.getNavPlanType", (byte[]) null);
        if (data != null) {
            return data.Ty().intValue();
        }
        return 0;
    }

    public void setNavVoiceCmdCallback(CallBack callBack) {
        if (this.Tj == null) {
            this.Tj = new NavVoicePlugin();
        }
        if (callBack == null) {
            this.Tj.unRegisterVoiceCmds();
            return;
        }
        this.Tj.setNavVoiceCmdCallback(callBack);
        Ty();
    }

    private void Ty() {
        if (this.Tj != null) {
            setNavStatusListener(new NavStatusListener() {
                public void onStart(String navPkg) {
                    TXZNavManager.this.Tj.registerVoiceCmds(navPkg);
                }

                public void onForeground(String pkn, boolean isForeground) {
                    if (isForeground) {
                        TXZNavManager.this.Tj.registerAgain();
                    } else {
                        TXZNavManager.this.Tj.unRegisterVoiceCmds();
                    }
                }

                public void onExit(String pkn) {
                    TXZNavManager.this.Tj.unRegisterVoiceCmds();
                    TXZNavManager.this.Tj.resetAsrTask();
                }

                public void onEnter(String pkn) {
                }

                public void onEnd(String navPkg) {
                    TXZNavManager.this.Tj.unRegisterVoiceCmds();
                    TXZNavManager.this.Tj.resetAsrTask();
                }

                public void onBeginNav(String pkn, Poi poi) {
                }

                public void onStatusUpdate(String pkn) {
                }

                public void onDefaultNavHasSeted(String pkn) {
                }
            });
        }
    }

    public void setTmcTool(TmcTool tmcTool) {
        this.Tx = tmcTool;
        if (tmcTool != null) {
            tmcTool.setOperateListener(new TmcTool.OperateListener() {
                public void startNotifyTraffic() {
                    Tn.Tr().T("com.txznet.txz", "txz.nav.tmc.operate", (byte[]) null, (Tn.Tr) null);
                }

                public void dismiss() {
                    Tn.Tr().T("com.txznet.txz", "txz.nav.tmc.dismiss", (byte[]) null, (Tn.Tr) null);
                }
            });
            TXZService.T("tool.tmc.", new TXZService.T() {
                public byte[] T(String packageName, String command, byte[] data) {
                    boolean ret = false;
                    if (command.equals("needWait")) {
                        ret = TXZNavManager.this.Tx.needWait();
                    } else if (command.equals("isIgnore")) {
                        ret = TXZNavManager.this.Tx.isIgnore();
                    } else if (command.equals("onSmartTraffic")) {
                        try {
                            ret = TXZNavManager.this.Tx.onSmartTraffic(T.C0000T.T(data));
                        } catch (com.Tr.T.T.Tn e) {
                            e.printStackTrace();
                        }
                    } else if (command.equals("onViewDataUpdate")) {
                        Tr jsonBuilder = new Tr(data);
                        ret = TXZNavManager.this.Tx.onViewDataUpdate((String) jsonBuilder.T("title", String.class), (String) jsonBuilder.T("data", String.class));
                    } else if (command.equals("onDismissDialog")) {
                        TXZNavManager.this.Tx.onDismissDialog();
                    }
                    return (ret + TXZResourceManager.STYLE_DEFAULT).getBytes();
                }
            });
            Tn.Tr().T("com.txznet.txz", "txz.nav.tmc.settool", (byte[]) null, (Tn.Tr) null);
        }
    }
}
