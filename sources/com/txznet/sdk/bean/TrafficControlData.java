package com.txznet.sdk.bean;

import java.util.Date;

/* compiled from: Proguard */
public class TrafficControlData {
    public String city;
    public String local;
    public String nonlocal;
    public TrafficControlInfo[] trafficControlInfos;

    /* compiled from: Proguard */
    public static class TrafficControlInfo {
        public Date forbiddenDate;
        public String[] forbiddenTailNumber;
        public int week;
    }
}
