package com.txznet.comm.ui.T5;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.android.SdkConstants;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.comm.ui.T5.T.T0;
import com.txznet.comm.ui.T5.T.T5;
import com.txznet.comm.ui.T5.T.T6;
import com.txznet.comm.ui.T5.T.T9;
import com.txznet.comm.ui.T5.T.TB;
import com.txznet.comm.ui.T5.T.TD;
import com.txznet.comm.ui.T5.T.TE;
import com.txznet.comm.ui.T5.T.TF;
import com.txznet.comm.ui.T5.T.TG;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.T.TO;
import com.txznet.comm.ui.T5.T.TP;
import com.txznet.comm.ui.T5.T.TZ;
import com.txznet.comm.ui.T5.T.Th;
import com.txznet.comm.ui.T5.T.Tj;
import com.txznet.comm.ui.T5.T.Tk;
import com.txznet.comm.ui.T5.T.Tq;
import com.txznet.comm.ui.T5.T.Ts;
import com.txznet.comm.ui.T5.T.Tt;
import com.txznet.comm.ui.T5.T.Tu;
import com.txznet.comm.ui.T5.T.Tv;
import com.txznet.comm.ui.T5.T.Tx;
import com.txznet.comm.ui.T5.T.Ty;
import com.txznet.sdk.TXZResourceManager;
import org.texustek.mirror.aidl.BinderName;

/* compiled from: Proguard */
public class Tn {

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public int f466T;
        public Boolean T9;
        public Integer Tk = null;
        public Object Tn = null;
        public View Tr;
        public boolean Ty = false;
    }

    public static TM T(String data) {
        com.txznet.comm.Tr.Tr.Tn.T(data);
        Tr jsonBuilder = new Tr(data);
        String type = (String) jsonBuilder.T("type", String.class);
        if (TextUtils.equals(type, "toSys")) {
            T5 viewData = new T5();
            viewData.T((String) jsonBuilder.T("msg", String.class));
            return viewData;
        } else if (TextUtils.equals(type, "fromSys")) {
            Ty viewData2 = new Ty();
            viewData2.T((String) jsonBuilder.T("msg", String.class));
            return viewData2;
        } else if (TextUtils.equals(type, "toSysPart")) {
            TE viewData3 = new TE();
            viewData3.T((String) jsonBuilder.T("msg", String.class));
            return viewData3;
        } else if (TextUtils.equals(type, "weather")) {
            Tv viewData4 = new Tv();
            viewData4.T(data);
            return viewData4;
        } else if (TextUtils.equals(type, "shock")) {
            T9 viewData5 = new T9();
            viewData5.T(data);
            return viewData5;
        } else {
            if (TextUtils.equals(type, "list")) {
                String listData = (String) jsonBuilder.T("data", String.class);
                Tr jsonBuilder2 = new Tr(listData);
                int listType = ((Integer) jsonBuilder2.T("type", Integer.class)).intValue();
                TB viewData6 = null;
                switch (listType) {
                    case 0:
                        viewData6 = new com.txznet.comm.ui.T5.T.Tr();
                        break;
                    case 1:
                        viewData6 = new TP();
                        break;
                    case 2:
                        Boolean isList = (Boolean) jsonBuilder2.T("listmodel", Boolean.class);
                        if (isList != null && !isList.booleanValue()) {
                            viewData6 = new TO();
                            break;
                        } else {
                            viewData6 = new Tu();
                            break;
                        }
                        break;
                    case 4:
                        viewData6 = new com.txznet.comm.ui.T5.T.T();
                        break;
                    case 5:
                        viewData6 = new Tx();
                        break;
                    case 6:
                        viewData6 = new T0();
                        break;
                    case 7:
                        viewData6 = new Th();
                        break;
                    case 8:
                        viewData6 = new TF();
                        break;
                    case 9:
                        viewData6 = new Tq();
                        break;
                    case 10:
                        viewData6 = new T6();
                        break;
                    case 11:
                        viewData6 = new Ts();
                        break;
                    case 12:
                        viewData6 = new TD();
                        break;
                }
                if (viewData6 != null) {
                    viewData6.T(listData);
                    if (viewData6.T9 > 0) {
                        return viewData6;
                    }
                    Ty chatFromSysViewData = new Ty();
                    switch (listType) {
                        case 1:
                            chatFromSysViewData.T("未找到微信联系人");
                            break;
                        case 2:
                            chatFromSysViewData.T(com.txznet.txz.util.Tr.T(Tr(viewData6.TE).toString()));
                            chatFromSysViewData.T((View.OnClickListener) new View.OnClickListener() {
                                public void onClick(View v) {
                                    com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.display.tip", (byte[]) null, (Tn.Tr) null);
                                }
                            });
                            break;
                        case 4:
                            String keywords = viewData6.TE;
                            if (TextUtils.isEmpty(keywords)) {
                                keywords = "没有找到相关结果";
                            }
                            chatFromSysViewData.T(com.txznet.txz.util.Tr.T(keywords));
                            break;
                    }
                    return chatFromSysViewData;
                }
            } else if (TextUtils.equals("data", type)) {
                switch (((Integer) jsonBuilder.T("dataType", Integer.class, null)).intValue()) {
                    case 2:
                        TG viewData7 = new TG();
                        viewData7.T(((Integer) jsonBuilder.T(BinderName.KEY, Integer.class)).intValue());
                        Tr tr = new Tr((String) jsonBuilder.T("value", String.class));
                        viewData7.Tr((String) tr.T("title", String.class));
                        viewData7.T((String) tr.T("qrCode", String.class));
                        return viewData7;
                    case 3:
                        Tt qrViewData = new Tt();
                        qrViewData.T((String) jsonBuilder.T("qrCode", String.class, TXZResourceManager.STYLE_DEFAULT));
                        return qrViewData;
                    case 4:
                        Tk highlightViewData = new Tk();
                        highlightViewData.T((String) jsonBuilder.T("rawText", String.class, TXZResourceManager.STYLE_DEFAULT));
                        return highlightViewData;
                    case 5:
                        TZ interruptViewData = new TZ();
                        interruptViewData.T((String) jsonBuilder.T(SdkConstants.ATTR_TEXT, String.class));
                        interruptViewData.Tr((String) jsonBuilder.T("tips", String.class, TXZResourceManager.STYLE_DEFAULT));
                        return interruptViewData;
                    case 6:
                        Tj helpTipsViewData = new Tj();
                        helpTipsViewData.T(jsonBuilder);
                        return helpTipsViewData;
                }
            } else if (TextUtils.equals(type, "map")) {
                com.txznet.comm.ui.T5.T.Tn viewData8 = new com.txznet.comm.ui.T5.T.Tn();
                viewData8.T((String) jsonBuilder.T("data", String.class));
                return viewData8;
            }
            return null;
        }
    }

    public static T T(TM viewData) {
        switch (viewData.Ty()) {
            case 1:
                return com.txznet.comm.ui.Tn.Tn.T().Te().T(viewData);
            case 2:
                return com.txznet.comm.ui.Tn.Tn.T().Tq().T(viewData);
            case 3:
                return com.txznet.comm.ui.Tn.Tn.T().TG().T(viewData);
            case 4:
                return com.txznet.comm.ui.Tn.Tn.T().Ts().T(viewData);
            case 5:
                return com.txznet.comm.ui.Tn.Tn.T().Tv().T(viewData);
            case 6:
                return com.txznet.comm.ui.Tn.Tn.T().TB().T(viewData);
            case 7:
                return com.txznet.comm.ui.Tn.Tn.T().T6().T(viewData);
            case 8:
                return com.txznet.comm.ui.Tn.Tn.T().Th().T(viewData);
            case 9:
                return com.txznet.comm.ui.Tn.Tn.T().Tj().T(viewData);
            case 10:
                return com.txznet.comm.ui.Tn.Tn.T().TF().T(viewData);
            case 11:
                return com.txznet.comm.ui.Tn.Tn.T().TK().T(viewData);
            case 12:
                return com.txznet.comm.ui.Tn.Tn.T().Tu().T(viewData);
            case 13:
                return com.txznet.comm.ui.Tn.Tn.T().TN().T(viewData);
            case 17:
                return com.txznet.comm.ui.Tn.Tn.T().TO().T(viewData);
            case 19:
                return com.txznet.comm.ui.Tn.Tn.T().Tt().T(viewData);
            case 20:
                return com.txznet.comm.ui.Tn.Tn.T().TD().T(viewData);
            case 21:
                return com.txznet.comm.ui.Tn.Tn.T().Tf().T(viewData);
            case 22:
                return com.txznet.comm.ui.Tn.Tn.T().TA().T(viewData);
            case 23:
                return com.txznet.comm.ui.Tn.Tn.T().Tx().T(viewData);
            case 24:
                return com.txznet.comm.ui.Tn.Tn.T().TV().T(viewData);
            case 25:
                return com.txznet.comm.ui.Tn.Tn.T().T0().T(viewData);
            case 26:
                return com.txznet.comm.ui.Tn.Tn.T().Tb().T(viewData);
            case 27:
                return com.txznet.comm.ui.Tn.Tn.T().TM().T(viewData);
            default:
                return null;
        }
    }

    private static SpannableStringBuilder Tr(String keywords) {
        int length = keywords.length();
        String keywords2 = "没有找到" + keywords + "相关结果，请再说一次或点击本消息手动修改";
        SpannableStringBuilder ssb = new SpannableStringBuilder(keywords2);
        ForegroundColorSpan norfcs = new ForegroundColorSpan(-1);
        ForegroundColorSpan keyfcs = new ForegroundColorSpan(Color.parseColor("#27d7fd"));
        ssb.setSpan(norfcs, 0, 4, 33);
        ssb.setSpan(keyfcs, 4, length + 4, 33);
        ssb.setSpan(norfcs, length + 4, keywords2.length(), 33);
        return ssb;
    }
}
