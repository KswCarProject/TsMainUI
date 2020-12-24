package com.txznet.sdk.bean;

import java.io.Serializable;

/* compiled from: Proguard */
public class FlowInfo implements Serializable {
    public static final int DATA_PLAN_TYPE_ACTIVE = 3;
    public static final int DATA_PLAN_TYPE_INFI_EX_VIDEO = 4;
    public static final int DATA_PLAN_TYPE_INFI_INC_VIDEO = 5;
    public static final int DATA_PLAN_TYPE_MONTH = 0;
    public static final int DATA_PLAN_TYPE_NORMAL = 2;
    public static final int DATA_PLAN_TYPE_REFUL = 6;
    public static final int EC_FLOW_NOT_AGENT = 7303;
    public static final int EC_FLOW_NOT_PARTNER = 7302;
    public static final int EC_FLOW_NO_CURRENT_PLAN = 7304;
    public static final int EC_FLOW_OK = 7301;
    public String iccid;
    public long outtime;
    public String planName;
    public int planType;
    public int remainData;
    public int remainDay;
    public int totalFlow;
    public int useData;
}
