package com.txznet.sdk.bean;

import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.tongting.IConstantData;

/* compiled from: Proguard */
public class WechatContact {

    /* renamed from: T  reason: collision with root package name */
    protected String f868T;
    protected boolean T9;
    protected boolean Tn;
    protected String Tr;
    protected String Ty;

    public WechatContact() {
    }

    public WechatContact(byte[] json) {
        Tr builder = new Tr(json);
        setId((String) builder.T(IConstantData.KEY_ID, String.class));
        setIcon((String) builder.T("icon", String.class));
        setNick((String) builder.T("nick", String.class));
        setBlocked(((Boolean) builder.T("blocked", Boolean.class)).booleanValue());
        setIsGroup(((Boolean) builder.T("isgroup", Boolean.class)).booleanValue());
    }

    public String getId() {
        return this.f868T;
    }

    public void setId(String id) {
        this.f868T = id;
    }

    public String getIcon() {
        return this.Tr;
    }

    public void setIcon(String icon) {
        this.Tr = icon;
    }

    public String getNick() {
        return this.Ty;
    }

    public void setNick(String nick) {
        this.Ty = nick;
    }

    public boolean isBlocked() {
        return this.Tn;
    }

    public void setBlocked(boolean blocked) {
        this.Tn = blocked;
    }

    public boolean isIsGroup() {
        return this.T9;
    }

    public void setIsGroup(boolean isGroup) {
        this.T9 = isGroup;
    }
}
