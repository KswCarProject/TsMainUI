package com.txznet.comm.update;

import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.dialog2.WinConfirm;
import com.txznet.comm.update.T;

/* compiled from: Proguard */
class UpdateCenter$2$2 extends WinConfirm {

    /* renamed from: T  reason: collision with root package name */
    final /* synthetic */ T.AnonymousClass2 f666T;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UpdateCenter$2$2(T.AnonymousClass2 this$0, WinConfirm.T data) {
        super(data);
        this.f666T = this$0;
    }

    public void onClickOk() {
        Tn.Ty("upgrade silence restart");
        T.T(this.f666T.Tr);
        com.txznet.T.T.TE();
    }

    public String getReportDialogId() {
        return "upgrade_normal_restart";
    }
}
