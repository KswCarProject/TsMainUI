package com.txznet.sdk;

import android.location.Location;
import android.os.Bundle;
import com.Tn.Tr.T9.T;
import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.bean.LocationData;

/* compiled from: Proguard */
public class TXZLocationManager {

    /* renamed from: T  reason: collision with root package name */
    private static TXZLocationManager f743T = new TXZLocationManager();
    private boolean T9;
    /* access modifiers changed from: private */
    public LocationData Tk;
    /* access modifiers changed from: private */
    public OnLocationListener Tn;
    private boolean Tr = false;
    private Object Ty = null;

    /* compiled from: Proguard */
    public enum GpsToolType {
        TXZ,
        QIHOO,
        AMAP
    }

    /* compiled from: Proguard */
    public interface OnLocationListener {
        void onLocationUpdate(LocationData locationData);
    }

    /* compiled from: Proguard */
    interface T {
        Location T();
    }

    private TXZLocationManager() {
    }

    public static TXZLocationManager getInstance() {
        return f743T;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (!this.Tr) {
            return;
        }
        if (this.Ty == null) {
            setGpsTool((GpsToolType) null);
        } else if (this.Ty instanceof T) {
            T((T) this.Ty);
        } else if (this.Ty instanceof GpsToolType) {
            setGpsTool((GpsToolType) this.Ty);
        }
    }

    /* access modifiers changed from: package-private */
    public void T(T tool) {
        this.Tr = true;
        this.Ty = tool;
    }

    public void setLocationListener(OnLocationListener listener) {
        this.Tn = listener;
        if (!this.T9) {
            this.T9 = true;
            TXZService.T("tool.loc.", new TXZService.T() {
                public byte[] T(String packageName, String command, byte[] data) {
                    if (!command.equals("updateLoc") || TXZLocationManager.this.Tn == null) {
                        return null;
                    }
                    try {
                        TXZLocationManager.this.T(T.Ty.T(data));
                        TXZLocationManager.this.Tn.onLocationUpdate(TXZLocationManager.this.Tk);
                        return null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
        }
    }

    public void setGpsTool(GpsToolType type) {
        this.Tr = true;
        this.Ty = type;
        if (type == null) {
            Tn.Tr().T("com.txznet.txz", "txz.loc.cleartool", (byte[]) null, (Tn.Tr) null);
        } else {
            Tn.Tr().T("com.txznet.txz", "txz.loc.setInnerTool", type.name().getBytes(), (Tn.Tr) null);
        }
    }

    public LocationData getCurrentLocationInfo() {
        if (this.Ty == null || !(this.Ty instanceof T)) {
            byte[] locInfo = Tn.Tr().T("txz.loc.getLocation", (byte[]) null);
            if (locInfo == null) {
                return null;
            }
            try {
                T(T.Ty.T(locInfo));
                com.txznet.comm.Tr.Tr.Tn.T("mGpsLocation:" + this.Tk.toString());
                return this.Tk;
            } catch (Exception e) {
                return null;
            }
        } else {
            T(((T) this.Ty).T());
            return this.Tk;
        }
    }

    public LocationData convertLocationData(T.Ty info) {
        if (info == null) {
            return null;
        }
        LocationData data = new LocationData();
        T.C0002T geoInfo = info.Tn;
        T.Tr gpsInfo = info.Ty;
        if (geoInfo == null && gpsInfo == null) {
            return null;
        }
        if (gpsInfo != null) {
            data.gps_type = gpsInfo.Tr;
            data.dbl_lat = gpsInfo.Ty;
            data.dbl_lng = gpsInfo.Tn;
            data.flt_direction = gpsInfo.T9;
            data.flt_speed = gpsInfo.Tk;
            data.dbl_altitude = gpsInfo.TZ;
            data.flt_radius = gpsInfo.TE;
        }
        if (geoInfo != null) {
            data.str_addr = geoInfo.Tr;
            data.str_provice = geoInfo.Ty;
            data.str_city = geoInfo.Tn;
            data.str_city_code = geoInfo.T9;
            data.str_district = geoInfo.Tk;
            data.str_street = geoInfo.TZ;
            data.str_street_num = geoInfo.TE;
        }
        data.accuracy = null;
        data.extra_bundle = null;
        return data;
    }

    /* access modifiers changed from: private */
    public void T(T.Ty info) {
        if (info != null) {
            T.C0002T geoInfo = info.Tn;
            T.Tr gpsInfo = info.Ty;
            if (geoInfo != null || gpsInfo != null) {
                if (this.Tk == null) {
                    this.Tk = new LocationData();
                }
                if (gpsInfo != null) {
                    this.Tk.gps_type = gpsInfo.Tr;
                    this.Tk.dbl_lat = gpsInfo.Ty;
                    this.Tk.dbl_lng = gpsInfo.Tn;
                    this.Tk.flt_direction = gpsInfo.T9;
                    this.Tk.flt_speed = gpsInfo.Tk;
                    this.Tk.dbl_altitude = gpsInfo.TZ;
                    this.Tk.flt_radius = gpsInfo.TE;
                }
                if (geoInfo != null) {
                    this.Tk.str_addr = geoInfo.Tr;
                    this.Tk.str_provice = geoInfo.Ty;
                    this.Tk.str_city = geoInfo.Tn;
                    this.Tk.str_city_code = geoInfo.T9;
                    this.Tk.str_district = geoInfo.Tk;
                    this.Tk.str_street = geoInfo.TZ;
                    this.Tk.str_street_num = geoInfo.TE;
                }
                this.Tk.accuracy = null;
                this.Tk.extra_bundle = null;
            }
        }
    }

    private void T(Location location) {
        if (location != null) {
            Float accu = Float.valueOf(location.getAccuracy());
            Double alt = Double.valueOf(location.getAltitude());
            Float bear = Float.valueOf(location.getBearing());
            Bundle bundle = location.getExtras();
            Double lat = Double.valueOf(location.getLatitude());
            Double lng = Double.valueOf(location.getLongitude());
            Float speed = Float.valueOf(location.getSpeed());
            if (this.Tk == null) {
                this.Tk = new LocationData();
            }
            this.Tk.gps_type = null;
            this.Tk.dbl_lat = lat;
            this.Tk.dbl_lng = lng;
            this.Tk.flt_direction = bear;
            this.Tk.flt_speed = speed;
            this.Tk.dbl_altitude = alt;
            this.Tk.flt_radius = null;
            this.Tk.accuracy = accu;
            this.Tk.extra_bundle = bundle;
            this.Tk.str_addr = null;
            this.Tk.str_provice = null;
            this.Tk.str_city = null;
            this.Tk.str_city_code = null;
            this.Tk.str_district = null;
            this.Tk.str_street = null;
            this.Tk.str_street_num = null;
        } else if (this.Tk != null) {
            this.Tk.reset();
        }
    }
}
