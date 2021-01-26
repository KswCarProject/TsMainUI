package com.txznet.sdk.bean;

import com.android.SdkConstants;
import com.txznet.comm.Ty.Tr;

/* compiled from: Proguard */
public class WechatContact {

    /* renamed from: T  reason: collision with root package name */
    protected String f872T;
    protected boolean T9;
    protected boolean Tn;
    protected String Tr;
    protected String Ty;

    public WechatContact() {
    }

    public WechatContact(byte[] json) {
        Tr builder = new Tr(json);
        setId((String) builder.T("id", String.class));
        setIcon((String) builder.T(SdkConstants.ATTR_ICON, String.class));
        setNick((String) builder.T("nick", String.class));
        setBlocked(((Boolean) builder.T("blocked", Boolean.class)).booleanValue());
        setIsGroup(((Boolean) builder.T("isgroup", Boolean.class)).booleanValue());
    }

    public String getId() {
        return this.f872T;
    }

    public void setId(String id) {
        this.f872T = id;
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
