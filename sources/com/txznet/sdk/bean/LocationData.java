package com.txznet.sdk.bean;

import android.os.Bundle;

/* compiled from: Proguard */
public class LocationData {
    public Float accuracy = null;
    public Double dbl_altitude = null;
    public Double dbl_lat = null;
    public Double dbl_lng = null;
    public Bundle extra_bundle = null;
    public Float flt_direction = null;
    public Float flt_radius = null;
    public Float flt_speed = null;
    public Integer gps_type = null;
    public String str_addr = null;
    public String str_city = null;
    public String str_city_code = null;
    public String str_district = null;
    public String str_provice = null;
    public String str_street = null;
    public String str_street_num = null;

    public void reset() {
        this.gps_type = null;
        this.dbl_lat = null;
        this.dbl_lng = null;
        this.flt_direction = null;
        this.flt_speed = null;
        this.dbl_altitude = null;
        this.flt_radius = null;
        this.str_addr = null;
        this.str_provice = null;
        this.str_city = null;
        this.str_city_code = null;
        this.str_district = null;
        this.str_street = null;
        this.str_street_num = null;
        this.accuracy = null;
    }

    public String toString() {
        return "LocationData [gps_type=" + this.gps_type + ", dbl_lat=" + this.dbl_lat + ", dbl_lng=" + this.dbl_lng + ", flt_direction=" + this.flt_direction + ", flt_speed=" + this.flt_speed + ", dbl_altitude=" + this.dbl_altitude + ", flt_radius=" + this.flt_radius + ", accuracy=" + this.accuracy + ", str_addr=" + this.str_addr + ", str_provice=" + this.str_provice + ", str_city=" + this.str_city + ", str_city_code=" + this.str_city_code + ", str_district=" + this.str_district + ", str_street=" + this.str_street + ", str_street_num=" + this.str_street_num + ", extra_bundle=" + this.extra_bundle + "]";
    }
}
