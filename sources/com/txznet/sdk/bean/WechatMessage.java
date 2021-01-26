package com.txznet.sdk.bean;

/* compiled from: Proguard */
public class WechatMessage {
    public static final int MSG_TYPE_HTML = 2;
    public static final int MSG_TYPE_IMG = 3;
    public static final int MSG_TYPE_LOCATION = 5;
    public static final int MSG_TYPE_TEXT = 1;
    public static final int MSG_TYPE_VOICE = 4;

    /* renamed from: T  reason: collision with root package name */
    private String f873T;
    private String T9;
    private int Tn;
    private String Tr;
    private String Ty;

    public WechatMessage() {
    }

    public WechatMessage(String id, String sessionId, String senderId, int type, String content) {
        setId(id);
        setSessionId(sessionId);
        setSenderId(senderId);
        setContent(content);
    }

    public String getId() {
        return this.f873T;
    }

    public void setId(String id) {
        this.f873T = id;
    }

    public String getSenderId() {
        return this.Tr;
    }

    public void setSenderId(String id) {
        this.Tr = id;
    }

    public String getSessionId() {
        return this.Ty;
    }

    public void setSessionId(String id) {
        this.Ty = id;
    }

    public int getType() {
        return this.Tn;
    }

    public void setType(int type) {
        this.Tn = type;
    }

    public String getContent() {
        return this.T9;
    }

    public void setContent(String content) {
        this.T9 = content;
    }
}
