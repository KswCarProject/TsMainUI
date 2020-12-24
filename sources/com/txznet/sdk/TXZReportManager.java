package com.txznet.sdk;

import android.text.TextUtils;
import com.txznet.comm.Tr.Tr.TE;
import com.txznet.comm.Tr.Tr.Tn;

/* compiled from: Proguard */
public class TXZReportManager {

    /* renamed from: T  reason: collision with root package name */
    private static TXZReportManager f792T = new TXZReportManager();

    private TXZReportManager() {
    }

    public static TXZReportManager getInstance() {
        return f792T;
    }

    public void doReport(String data) {
        if (!TextUtils.isEmpty(data)) {
            TE.T(9, data);
        } else {
            Tn.Ty("report data empty!");
        }
    }

    public void doReportImmediately(String data) {
        if (!TextUtils.isEmpty(data)) {
            TE.Tr(9, data.getBytes());
        } else {
            Tn.Ty("report data empty!");
        }
    }
}
