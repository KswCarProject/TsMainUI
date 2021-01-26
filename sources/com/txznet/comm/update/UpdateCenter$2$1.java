package com.txznet.comm.update;

import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.dialog2.WinNotice;
import com.txznet.comm.update.T;

/* compiled from: Proguard */
class UpdateCenter$2$1 extends WinNotice {

    /* renamed from: T  reason: collision with root package name */
    final /* synthetic */ T.AnonymousClass2 f665T;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UpdateCenter$2$1(T.AnonymousClass2 this$0, WinNotice.T data) {
        super(data);
        this.f665T = this$0;
    }

    public void onClickOk() {
        Tn.Ty("upgrade silence restart");
        com.txznet.T.T.TE();
    }

    public String getReportDialogId() {
        return "upgrade_force_restart";
    }
}
