package com.txznet.sdk;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import com.txznet.T.T;
import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZService;

/* compiled from: Proguard */
public class TXZUpgradeManager {

    /* renamed from: T  reason: collision with root package name */
    private static TXZUpgradeManager f845T = new TXZUpgradeManager();
    /* access modifiers changed from: private */
    public ApkInstaller T9;
    private TXZService.T TE = new TXZService.T() {
        public byte[] T(String packageName, String command, byte[] data) {
            if (TXZUpgradeManager.this.T9 != null) {
                Parcel p = Parcel.obtain();
                if (data != null) {
                    p.unmarshall(data, 0, data.length);
                    p.setDataPosition(0);
                }
                if ("install".equals(command)) {
                    final String pkgName = p.readString();
                    final String version = p.readString();
                    TXZUpgradeManager.this.T9.install(pkgName, version, p.readString(), new ApkInstaller.ApkInstallListener() {
                        public void onSuccess() {
                            Parcel p = Parcel.obtain();
                            p.writeString(pkgName);
                            p.writeString(version);
                            Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.installer.install_succ", p.marshall(), (Tn.Tr) null);
                            p.recycle();
                        }

                        public void onFailed(@Nullable String msg) {
                            Parcel p = Parcel.obtain();
                            p.writeString(pkgName);
                            p.writeString(version);
                            if (msg == null) {
                                msg = "";
                            }
                            p.writeString(msg);
                            Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.installer.install_failed", p.marshall(), (Tn.Tr) null);
                            p.recycle();
                        }
                    });
                }
                p.recycle();
            }
            return null;
        }
    };
    private TXZService.T TZ = new TXZService.T() {
        public byte[] T(String packageName, String command, byte[] data) {
            if (TXZUpgradeManager.this.Tn != null) {
                Parcel p = Parcel.obtain();
                if (data != null) {
                    p.unmarshall(data, 0, data.length);
                    p.setDataPosition(0);
                }
                if ("notify".equals(command)) {
                    final String pkgName = p.readString();
                    final String version = p.readString();
                    final String apkName = p.readString();
                    final int event = p.readInt();
                    final String d = p.readString();
                    T.Tr((Runnable) new Runnable() {
                        public void run() {
                            if (TXZUpgradeManager.this.Tn != null) {
                                TXZUpgradeManager.this.Tn.notify(pkgName, version, apkName, event, d);
                            }
                        }
                    });
                }
                p.recycle();
            }
            return null;
        }
    };
    private TXZService.T Tk = new TXZService.T() {
        public byte[] T(String packageName, String command, byte[] data) {
            if (TXZUpgradeManager.this.Ty != null) {
                Parcel p = Parcel.obtain();
                if (data != null) {
                    p.unmarshall(data, 0, data.length);
                    p.setDataPosition(0);
                }
                if ("show".equals(command)) {
                    final String title = p.readString();
                    final String content = p.readString();
                    final String detailInfo = p.readString();
                    T.Tr((Runnable) new Runnable() {
                        public void run() {
                            if (TXZUpgradeManager.this.Ty != null) {
                                TXZUpgradeManager.this.Ty.showConfirmDialog(title, content, detailInfo, new UpgradeDialogTool.DialogListener() {
                                    public void onClickOk() {
                                        Parcel p = Parcel.obtain();
                                        p.writeString(detailInfo);
                                        Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.dialog.dialog_confirm", p.marshall(), (Tn.Tr) null);
                                        p.recycle();
                                    }

                                    public void onClickCancel() {
                                        Parcel p = Parcel.obtain();
                                        p.writeString(detailInfo);
                                        Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.dialog.dialog_cancel", p.marshall(), (Tn.Tr) null);
                                        p.recycle();
                                    }

                                    public void onDismiss() {
                                        Parcel p = Parcel.obtain();
                                        p.writeString(detailInfo);
                                        Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.dialog.dialog_dismiss", p.marshall(), (Tn.Tr) null);
                                        p.recycle();
                                    }
                                });
                            }
                        }
                    });
                }
                p.recycle();
            }
            return null;
        }
    };
    /* access modifiers changed from: private */
    public NotificationTool Tn;
    private Boolean Tr;
    /* access modifiers changed from: private */
    public UpgradeDialogTool Ty;

    /* compiled from: Proguard */
    public interface ApkInstaller {

        /* compiled from: Proguard */
        public interface ApkInstallListener {
            void onFailed(@Nullable String str);

            void onSuccess();
        }

        @WorkerThread
        void install(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull ApkInstallListener apkInstallListener);
    }

    /* compiled from: Proguard */
    public interface NotificationTool {
        public static final int EVENT_BEGIN_DOWNLOAD = 0;
        public static final int EVENT_BEGIN_INSTALL = 4;
        public static final int EVENT_DOWNLOAD_FAILED = 3;
        public static final int EVENT_END_DOWNLOAD = 2;
        public static final int EVENT_INSTALL_FAILED = 6;
        public static final int EVENT_INSTALL_SUCCESS = 5;
        public static final int EVENT_PROGRESS_CHANGE = 1;

        @UiThread
        void cancelAll();

        @UiThread
        void notify(@NonNull String str, @NonNull String str2, @NonNull String str3, int i, @Nullable String str4);
    }

    /* compiled from: Proguard */
    public interface UpgradeDialogTool {

        /* compiled from: Proguard */
        public interface DialogListener {
            void onClickCancel();

            void onClickOk();

            void onDismiss();
        }

        @UiThread
        void showConfirmDialog(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull DialogListener dialogListener);
    }

    private TXZUpgradeManager() {
    }

    public static TXZUpgradeManager getInstance() {
        return f845T;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.Tr != null) {
            setDeleteApkAfterInstall(this.Tr.booleanValue());
        }
        if (this.Ty != null) {
            setUpgradeDialogTool(this.Ty);
        }
        if (this.Tn != null) {
            setNotificationTool(this.Tn);
            T.Tr((Runnable) new Runnable() {
                public void run() {
                    TXZUpgradeManager.this.Tn.cancelAll();
                }
            });
        }
        if (this.T9 != null) {
            setApkInstaller(this.T9);
        }
    }

    public void setDeleteApkAfterInstall(boolean bDelete) {
        this.Tr = Boolean.valueOf(bDelete);
        Parcel p = Parcel.obtain();
        p.writeByte((byte) (bDelete ? 1 : 0));
        Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.delete_apk_after_install", p.marshall(), (Tn.Tr) null);
        p.recycle();
    }

    public void setUpgradeDialogTool(UpgradeDialogTool dialogTool) {
        this.Ty = dialogTool;
        if (this.Ty == null) {
            Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.clear_upgrade_dialog", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("txz.upgrade.cmd.dialog.", this.Tk);
        Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.set_upgrade_dialog", (byte[]) null, (Tn.Tr) null);
    }

    public void setNotificationTool(NotificationTool notificationTool) {
        this.Tn = notificationTool;
        if (this.Tn == null) {
            Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.clear_notification_tool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("txz.upgrade.cmd.notification.", this.TZ);
        Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.set_notification_tool", (byte[]) null, (Tn.Tr) null);
    }

    public void setApkInstaller(ApkInstaller apkInstaller) {
        this.T9 = apkInstaller;
        if (this.T9 == null) {
            Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.clear_apk_installer", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("txz.upgrade.cmd.installer.", this.TE);
        Tn.Tr().T("com.txznet.txz", "txz.upgrade.invoke.set_apk_installer", (byte[]) null, (Tn.Tr) null);
    }
}
