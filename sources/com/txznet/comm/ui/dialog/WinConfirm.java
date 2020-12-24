package com.txznet.comm.ui.dialog;

import android.view.View;

/* compiled from: Proguard */
public abstract class WinConfirm extends WinMessageBox {
    public abstract void onClickOk();

    public WinConfirm() {
        T("确定");
        Ty("取消");
        this.TG = new View[2];
        this.TG[0] = this.Te.TE;
        this.TG[1] = this.Te.Tv;
    }

    public WinConfirm(boolean isSystem) {
        super(isSystem);
        T("确定");
        Ty("取消");
        this.TG = new View[2];
        this.TG[0] = this.Te.TE;
        this.TG[1] = this.Te.Tv;
    }

    public WinConfirm setTitle(String s) {
        this.Te.Ty.setVisibility(0);
        this.Te.Ty.setText(s);
        return this;
    }

    public WinConfirm setMessage(String s) {
        super.setMessage(s);
        return this;
    }

    public WinConfirm setMessageData(Object data) {
        super.setMessageData(data);
        return this;
    }

    public WinConfirm setCancelText(String s) {
        super.Ty(s);
        return this;
    }

    public WinConfirm setSureText(String s) {
        super.T(s);
        return this;
    }

    public void onClickCancel() {
    }

    public void onClickLeft() {
        onClickOk();
        dismiss();
    }

    public void onClickRight() {
        onClickCancel();
        dismiss();
    }

    public void onClickBlank() {
        onClickRight();
    }

    public void onBackPressed() {
        onClickCancel();
        dismiss();
    }
}
