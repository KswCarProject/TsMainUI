package com.txznet.sdk;

import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import com.txznet.comm.Tr.T;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.comm.base.Ty;
import com.txznet.comm.ui.T9.Tk;
import com.txznet.comm.ui.Tn;
import com.txznet.sdk.TXZService;

/* compiled from: Proguard */
public class TXZRecordWinManager {
    private static TXZRecordWinManager Tn = new TXZRecordWinManager();

    /* renamed from: T  reason: collision with root package name */
    Boolean f787T;
    private Integer T9 = null;
    /* access modifiers changed from: private */
    public RecordWin2 TZ;
    private Boolean Tk;
    Boolean Tr;
    Boolean Ty;

    /* compiled from: Proguard */
    public interface RecordWin2 {

        /* compiled from: Proguard */
        public interface RecordWinController {
            public static final int OPERATE_CLICK = 0;
            public static final int OPERATE_LONG_CLICK = 1;
            public static final int OPERATE_SOURCE_NAVCONTROL = 2;
            public static final int OPERATE_SOURCE_TOUCH = 1;
            public static final int OPERATE_TOUCH = 2;
            public static final int TARGET_CONTENT_CHAT = 10;
            public static final int TARGET_CONTENT_FULL = 20;
            public static final int TARGET_VIEW_MIC = 30;
            public static final int VIEW_BACK = 16;
            public static final int VIEW_CITY = 17;
            public static final int VIEW_CLOSE = 13;
            public static final int VIEW_HELP = 10;
            public static final int VIEW_LIST_ITEM = 3;
            public static final int VIEW_LIST_NEXTPAGE = 1;
            public static final int VIEW_LIST_PREPAGE = 2;
            public static final int VIEW_RECORD = 12;
            public static final int VIEW_SETTING = 11;
            public static final int VIEW_TIPS = 14;
            public static final int VIEW_TTS_QRCODE = 15;

            Object addView(int i, View view);

            void dismiss();

            Object operateView(int i, int i2, int i3, int i4);

            void show();
        }

        void setWinController(RecordWinController recordWinController);

        boolean showData(String str);
    }

    private TXZRecordWinManager() {
    }

    public static TXZRecordWinManager getInstance() {
        return Tn;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.f787T != null) {
            enableFullScreen(this.f787T.booleanValue());
        }
        if (this.Ty != null) {
            setWinRecordCloseWhenProcCmd(this.Ty.booleanValue());
        }
        if (this.Tk != null) {
            setRecordWin2(this.TZ);
        }
        if (this.T9 != null) {
            setWinContentWidth(this.T9.intValue());
        }
        if (this.Tr != null) {
            enableMsgEntryAnimation(this.Tr.booleanValue());
        }
    }

    public void updateScreenSize(int widthPx, int heightPx) {
        Tr jsonBuilder = new Tr();
        jsonBuilder.T("width", (Object) Integer.valueOf(widthPx));
        jsonBuilder.T("height", (Object) Integer.valueOf(heightPx));
        Tn.Tr().T("com.txznet.txz", "txz.record.win.updateScreenSize", jsonBuilder.Ty(), (Tn.Tr) null);
    }

    public void setWinContentWidth(int width) {
        this.T9 = Integer.valueOf(width);
        Tn.Tr().T("com.txznet.txz", "txz.record.win.contentWidth", ("" + this.T9).getBytes(), (Tn.Tr) null);
    }

    public boolean isOpened() {
        byte[] data = Tn.Tr().T("txz.record.win.isOpened", (byte[]) null);
        if (data == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(data));
    }

    public void openAndShowText(String text) {
        if (!TextUtils.isEmpty(text)) {
            Tn.Tr().T("com.txznet.txz", "txz.record.win.openShowText", text.getBytes(), (Tn.Tr) null);
        }
    }

    public void enableFullScreen(boolean isFullScreen) {
        this.f787T = Boolean.valueOf(isFullScreen);
        Tn.Tr().T("com.txznet.txz", "txz.config.winRecord.fullScreen", ("" + isFullScreen).getBytes(), (Tn.Tr) null);
    }

    public void enableMsgEntryAnimation(boolean enable) {
        this.Tr = Boolean.valueOf(enable);
        Tn.Tr().T("com.txznet.txz", "txz.record.win.enableMsgEntryAnim", ("" + this.Tr).getBytes(), (Tn.Tr) null);
    }

    public void setWinRecordCloseWhenProcCmd(boolean isClose) {
        this.Ty = Boolean.valueOf(isClose);
        Tn.Tr().T("com.txznet.txz", "txz.config.winRecord.close", (this.Ty + "").getBytes(), (Tn.Tr) null);
    }

    public void setRecordWin2(final RecordWin2 recordWin) {
        this.Tk = true;
        this.TZ = recordWin;
        if (this.TZ == null) {
            Tn.Tr().T("com.txznet.txz", "txz.recordwin2.clear", (byte[]) null, (Tn.Tr) null);
        } else if (T.Tr() != null) {
            try {
                Ty.T(T.Tr(), new Ty.T(Environment.getExternalStorageDirectory().getPath() + "/txz/report/"));
            } catch (Exception e) {
            }
            com.txznet.comm.ui.Tn.T().Tr(new Tn.T() {
                public void T() {
                    recordWin.setWinController(new RecordWin2.RecordWinController() {
                        public void show() {
                            Tk.Tr().TE();
                        }

                        public void dismiss() {
                            Tk.Tr().T5();
                        }

                        public Object addView(int targetView, View view) {
                            return Tk.Tr().T(targetView, view);
                        }

                        public Object operateView(int actionType, int view, int listType, int listIndex) {
                            return Tk.Tr().T(actionType, view, listType, listIndex);
                        }
                    });
                }

                public void Tr() {
                    Tk.Tr().T9();
                }
            });
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.recordwin2.set", (byte[]) null, (Tn.Tr) null);
            TXZService.T("win.record2.", new TXZService.T() {
                public byte[] T(String packageName, String command, byte[] data) {
                    Boolean isFull;
                    if (command.equals("show")) {
                        Tk.Tr().TE();
                    } else if (command.equals("dismiss")) {
                        Tk.Tr().T5();
                    } else if (command.equals("showData")) {
                        String json = new String(data);
                        if (!TXZRecordWinManager.this.TZ.showData(json)) {
                            Tk.Tr().T(json);
                        }
                    } else if (command.equals("fullScreen") && (isFull = Boolean.valueOf(Boolean.parseBoolean(new String(data)))) != null) {
                        com.txznet.comm.ui.Tn.T((Runnable) new com.txznet.txz.util.T.T<Boolean>(isFull) {
                            public void run() {
                                com.txznet.comm.ui.T9.Tr.T().T(((Boolean) this.Ty).booleanValue());
                            }
                        }, 0);
                    }
                    return null;
                }
            });
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.recordwin2.set", "false".getBytes(), (Tn.Tr) null);
        }
    }
}
