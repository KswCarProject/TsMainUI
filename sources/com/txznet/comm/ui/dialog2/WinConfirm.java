package com.txznet.comm.ui.dialog2;

import com.txznet.comm.ui.dialog2.WinDialog;
import com.txznet.comm.ui.dialog2.WinMessageBox;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Proguard */
public abstract class WinConfirm extends WinMessageBox {
    public abstract void onClickOk();

    /* compiled from: Proguard */
    public static class T extends WinMessageBox.Tr {
        public void T() {
            if (this.TN == null) {
                Tr("确定");
            }
            if (this.Ts == null) {
                T("取消");
            }
            super.T();
        }

        public T T(String text) {
            super.Th(text);
            return this;
        }

        public T Tr(String text) {
            super.Tv(text);
            return this;
        }
    }

    public WinConfirm(T data) {
        this(data, true);
    }

    protected WinConfirm(T data, boolean init) {
        super(data, false);
        if (init) {
            T9();
        }
    }

    /* access modifiers changed from: protected */
    public void T() {
        List<WinDialog.T9> focusViews = new ArrayList<>(2);
        focusViews.add(0, new WinDialog.T9(this.Tq.TE, "ButtonOk"));
        focusViews.add(1, new WinDialog.T9(this.Tq.Tv, "ButtonCancel"));
        setFocusViews(focusViews, -1);
    }

    @Deprecated
    public WinConfirm setCancelText(String s) {
        super.Tr(s);
        return this;
    }

    @Deprecated
    public WinConfirm setSureText(String s) {
        super.T(s);
        return this;
    }

    public void onClickCancel() {
    }

    public void onClickLeft() {
        onClickOk();
        Tv();
    }

    public void onClickRight() {
        onClickCancel();
        Tv();
    }

    public void onClickBlank() {
        onClickRight();
    }

    public void onBackPressed() {
        onClickCancel();
        Tv();
    }

    public void clickOkCountDown(int time) {
        super.T(this.TF.TN + " (%TIME%)", time);
    }

    public void clickOkCountDown(String text, int time) {
        super.T(text, time);
    }

    public void clickCancelCountDown(int time) {
        super.Ty(this.TF.Ts + " (%TIME%)", time);
    }

    public void clickCancelCountDown(String text, int time) {
        super.Ty(text, time);
    }
}
