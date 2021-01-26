package com.txznet.comm.ui.T9;

import android.content.Intent;
import android.view.View;
import com.ts.can.bmw.mini.CanBMWMiniServiceDetailActivity;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.comm.ui.T5.T;
import com.txznet.comm.ui.T5.T.TM;
import com.txznet.comm.ui.T5.Tn;
import com.txznet.comm.ui.T5.Tr.TB;
import com.txznet.comm.ui.T5.Ty;
import com.txznet.comm.ui.Tn.Tn;
import com.txznet.sdk.TXZAsrManager;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.sdk.media.InvokeConstants;
import com.txznet.sdk.tongting.IConstantData;
import org.texustek.mirror.aidl.BinderName;

/* compiled from: Proguard */
public class Tk {

    /* renamed from: T  reason: collision with root package name */
    private static Tk f512T = new Tk();
    /* access modifiers changed from: private */
    public int T9 = 0;
    /* access modifiers changed from: private */
    public boolean TZ;
    private T Tk;
    private boolean Tn = false;
    /* access modifiers changed from: private */
    public int Tr = -1;
    private boolean Ty = false;

    private Tk() {
    }

    public void T() {
    }

    public static Tk Tr() {
        return f512T;
    }

    public void T(String data) {
        if (Tn.T().f558T) {
            try {
                final Tr jsonBuilder = new Tr(data);
                String action = (String) jsonBuilder.T("action", String.class);
                if ("addMsg".equals(action)) {
                    TM viewData = com.txznet.comm.ui.T5.Tn.T(data);
                    if (viewData != null) {
                        com.txznet.comm.ui.Tn.T((Runnable) new com.txznet.txz.util.T.T<TM>(viewData) {
                            public void run() {
                                int targetView;
                                com.txznet.comm.ui.T5.Tr msgViewBase;
                                if (Tr.T().Ty()) {
                                    boolean update = ((Boolean) jsonBuilder.T("update", Boolean.class, false)).booleanValue();
                                    boolean shouldUpdate = Tk.this.Tr(((TM) this.Ty).Ty());
                                    if ((update || shouldUpdate) && (msgViewBase = Tn.T().TZ()) != null && msgViewBase.Tn() != null && msgViewBase.T() == ((TM) this.Ty).Ty() && msgViewBase.T() != 0 && Tk.this.T9 == ((TM) this.Ty).Ty() && (msgViewBase.Tn().intValue() & 1) == 1) {
                                        com.txznet.comm.Tr.Tr.Tn.T("[UI2.0] target view support updateView,just update view");
                                        msgViewBase.Tr((TM) this.Ty);
                                        Tn.T().T(msgViewBase);
                                        return;
                                    }
                                    if (Tk.this.T9 == 23) {
                                        Tk.this.Th();
                                    } else if (Tk.this.T9 == 27) {
                                        Tk.this.Th();
                                    }
                                    Tn.T viewAdapter = com.txznet.comm.ui.T5.Tn.T((TM) this.Ty);
                                    if (viewAdapter != null) {
                                        if (viewAdapter.T9 != null) {
                                            targetView = viewAdapter.T9.booleanValue() ? 20 : 10;
                                        } else {
                                            targetView = Tk.this.Ty(viewAdapter.f466T);
                                        }
                                        if (viewAdapter.Ty && (viewAdapter.Tn instanceof TB)) {
                                            com.txznet.comm.ui.Tn.Tn.T().T((TB) viewAdapter.Tn);
                                        }
                                        if (viewAdapter.Tn instanceof com.txznet.comm.ui.T5.Tr) {
                                            com.txznet.comm.ui.Tn.Tn.T().T((com.txznet.comm.ui.T5.Tr) viewAdapter.Tn);
                                        }
                                        int unused = Tk.this.Tr = viewAdapter.f466T;
                                        Tk.this.Tr(targetView, viewAdapter.Tr);
                                    }
                                    int unused2 = Tk.this.T9 = ((TM) this.Ty).Ty();
                                }
                            }
                        }, 0);
                    }
                } else if ("updateVolume".equals(action)) {
                    com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                        public void run() {
                            if (Tr.T().Ty()) {
                                Tk.this.T9(((Integer) jsonBuilder.T("value", Integer.class)).intValue());
                            }
                        }
                    }, 0);
                } else if ("snapPage".equals(action)) {
                    com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                        public void run() {
                            if (Tr.T().Ty()) {
                                Tk.this.Tr(((Boolean) jsonBuilder.T(InvokeConstants.INVOKE_NEXT, Boolean.class)).booleanValue());
                            }
                        }
                    }, 0);
                } else if ("updateProgress".equals(action)) {
                    final Integer progress = (Integer) jsonBuilder.T("value", Integer.class);
                    final Integer selection = (Integer) jsonBuilder.T("selection", Integer.class);
                    if (progress != null && selection != null) {
                        com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                            public void run() {
                                if (Tr.T().Ty()) {
                                    Tk.this.T(progress.intValue(), selection.intValue());
                                }
                            }
                        }, 0);
                    }
                } else if ("updateState".equals(action)) {
                    final Integer state = (Integer) jsonBuilder.T(IConstantData.KEY_STATE, Integer.class);
                    if ("wheelControl".equals((String) jsonBuilder.T("type", String.class))) {
                        com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                            public void run() {
                                Tk.this.Tk(state.intValue());
                            }
                        }, 0);
                    } else if (state != null) {
                        com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                            public void run() {
                                if (Tr.T().Ty()) {
                                    Tk.this.Tn(state.intValue());
                                }
                            }
                        }, 0);
                    }
                } else if ("onKeyEvent".equals(action)) {
                    final Integer keyEvent = (Integer) jsonBuilder.T("keyEvent", Integer.class);
                    com.txznet.comm.Tr.Tr.Tn.T("receive keyEvent:" + keyEvent);
                    if (keyEvent != null) {
                        com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                            public void run() {
                                if (keyEvent.intValue() == 14) {
                                    Tk.this.Tk();
                                } else if (Tr.T().Ty()) {
                                    Tk.this.T(keyEvent.intValue());
                                }
                            }
                        }, 0);
                    }
                } else if ("sendInformation".equals(action) && ((Integer) jsonBuilder.T("type", Integer.class, -1)).intValue() == 0) {
                    com.txznet.comm.Tr.Tr.Tr.Tn(((Boolean) jsonBuilder.T("showHelpNewTag", Boolean.class, false)).booleanValue());
                }
            } catch (Exception e) {
                com.txznet.comm.Tr.Tr.Tn.Tn("[UI2.0] error :" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void Tr(String keyword) {
        Tr jb = new Tr();
        jb.T(BinderName.KEY, (Object) keyword);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.search.edit.result", jb.Ty(), (Tn.Tr) null);
    }

    public void Ty() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.search.edit.cancel", (byte[]) null, (Tn.Tr) null);
    }

    public void Ty(String city) {
        Tr jb = new Tr();
        jb.T("city", (Object) city);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.select.city.result", jb.Ty(), (Tn.Tr) null);
    }

    public void Tn() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.select.city.cancel", (byte[]) null, (Tn.Tr) null);
    }

    public void T(int type, String event, Object... objects) {
        switch (type) {
            case 1:
                if (this.Tk == null) {
                    try {
                        Class<?> clazzWinManager = Class.forName("com.txznet.txz.module.ui.WinManager");
                        this.Tk = (T) clazzWinManager.getField("mViewStateTransfer").get(clazzWinManager.getDeclaredMethod("getInstance", new Class[0]).invoke("getInstance", new Object[0]));
                    } catch (Exception e) {
                        com.txznet.comm.Tr.Tr.Tn.Tn("send ui event to core error!");
                    }
                }
                if (this.Tk != null && objects.length >= 2) {
                    this.Tk.T(objects[0], objects[1].intValue());
                    return;
                }
                return;
            case 2:
                if ("txz.record.ui.event.clearProgress".equals(event)) {
                    com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.clearProgress", (byte[]) null, (Tn.Tr) null);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void T(int type, Object... objects) {
        T(type, (String) null, objects);
    }

    public Object T(int actionType, int view, int listType, int listIndex) {
        return T(actionType, view, listType, listIndex, 1);
    }

    public Object T(int actionType, int view, int listType, int listIndex, int operateSource) {
        com.txznet.comm.Tr.Tr.Tn.T("operateView actionType:" + actionType + ",view:" + view + ",listType:" + listType + ",listIndex" + listIndex + ",operateSource:" + operateSource);
        if (!this.Ty) {
            byte[] data = null;
            if (operateSource != 0 || listIndex >= 0 || view == 1 || view == 2) {
                Tr jsonBuilder = new Tr();
                jsonBuilder.T("operateSource", (Object) Integer.valueOf(operateSource));
                jsonBuilder.T(CanBMWMiniServiceDetailActivity.KEY_INDEX, (Object) Integer.valueOf(listIndex));
                if (view == 1) {
                    jsonBuilder.T("type", (Object) 1);
                    jsonBuilder.T("clicktype", (Object) 2);
                }
                if (view == 2) {
                    jsonBuilder.T("type", (Object) 1);
                    jsonBuilder.T("clicktype", (Object) 1);
                }
                data = jsonBuilder.Ty();
            }
            if (actionType != 0) {
                if (actionType == 2) {
                    switch (view) {
                        case 3:
                            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.list.ontouch", (byte[]) null, (Tn.Tr) null);
                            break;
                    }
                }
            } else {
                switch (view) {
                    case 1:
                        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.display.page", data, (Tn.Tr) null);
                        break;
                    case 2:
                        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.display.page", data, (Tn.Tr) null);
                        break;
                    case 3:
                        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.item.selected", data, (Tn.Tr) null);
                        break;
                    case 10:
                        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.help.ui.detail.open", data, (Tn.Tr) null);
                        break;
                    case 11:
                        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.button.setting", data, (Tn.Tr) null);
                        break;
                    case 12:
                        Tv();
                        break;
                    case 13:
                        com.txznet.comm.Tr.T.Tr().sendBroadcast(new Intent("com.txznet.txz.record.dismiss.button"));
                        Tr.T().T9();
                        break;
                    case 14:
                        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.display.tip", data, (Tn.Tr) null);
                        break;
                    case 15:
                        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "wx.subscribe.qrcode", (byte[]) null, (Tn.Tr) null);
                        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.list.ontouch", "0".getBytes(), (Tn.Tr) null);
                        break;
                    case 16:
                        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.button.back", data, (Tn.Tr) null);
                        break;
                    case 17:
                        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.display.city", (byte[]) null, (Tn.Tr) null);
                        break;
                }
            }
        }
        return null;
    }

    private void Tv() {
        if (!this.TZ) {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.button.record", (byte[]) null, (Tn.Tr) null);
            this.TZ = true;
            com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                public void run() {
                    boolean unused = Tk.this.TZ = false;
                }
            }, 1000);
        }
    }

    public void T9() {
        com.txznet.comm.Tr.Tr.Tn.T("forceUseUI1");
        this.Ty = true;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.recordwin2.forceUI1", (byte[]) null, (Tn.Tr) null);
    }

    public void T(boolean disable) {
        if (this.Tn != disable) {
            this.Tn = disable;
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.recordwin2.disableThirdWin", (TXZResourceManager.STYLE_DEFAULT + disable).getBytes(), (Tn.Tr) null);
        }
    }

    public void Tk() {
        if (!Tr.T().Ty()) {
            TXZAsrManager.getInstance().triggerRecordButton();
            return;
        }
        Ty mCurChatView = com.txznet.comm.ui.Tn.Tn.T().TZ();
        if (mCurChatView == null || !(mCurChatView instanceof TB)) {
            Tr().T(0, 12, 0, 0);
        } else {
            TXZAsrManager.getInstance().restart(TXZResourceManager.STYLE_DEFAULT);
        }
    }

    public boolean TZ() {
        return this.Tn;
    }

    /* access modifiers changed from: private */
    public boolean Tr(int type) {
        switch (type) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 17:
            case 18:
            case 19:
            case 23:
            case 24:
            case 26:
            case 27:
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: private */
    public int Ty(int type) {
        switch (type) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 17:
            case 18:
            case 19:
            case 24:
            case 26:
                return 20;
            default:
                return 10;
        }
    }

    /* access modifiers changed from: private */
    public void Tr(int targetView, View view) {
        com.txznet.comm.ui.Tn.Tn.T().T(targetView, view);
    }

    /* access modifiers changed from: private */
    public void Th() {
        com.txznet.comm.ui.Tn.Tn.T().TE();
    }

    public Object T(final int targetView, final View view) {
        if (!this.Ty) {
            switch (targetView) {
                case 30:
                    com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                        public void run() {
                            com.txznet.comm.ui.Tn.Tn.T().T(view);
                        }
                    }, 0);
                    break;
                default:
                    com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                        public void run() {
                            Tk.this.Tr(targetView, view);
                        }
                    }, 0);
                    break;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void Tn(int state) {
        com.txznet.comm.ui.Tn.Tn.T().T(state);
    }

    /* access modifiers changed from: private */
    public void T9(int volume) {
        com.txznet.comm.ui.Tn.Tn.T().Tr(volume);
    }

    /* access modifiers changed from: private */
    public void T(int progress, int selection) {
        com.txznet.comm.ui.Tn.Tn.T().T(progress, selection);
    }

    /* access modifiers changed from: private */
    public void Tk(int state) {
        com.txznet.comm.ui.Ty.Tr.T().Tr(state);
    }

    /* access modifiers changed from: private */
    public void Tr(boolean next) {
        com.txznet.comm.ui.Tn.Tn.T().T(next);
    }

    public boolean T(int keyEvent) {
        return com.txznet.comm.ui.Ty.Tr.T().T(keyEvent);
    }

    public void TE() {
        if (com.txznet.comm.ui.Tn.Tn.T().f558T) {
            com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                public void run() {
                    Tr.T().Tn();
                }
            }, 0);
        }
    }

    public void T5() {
        if (com.txznet.comm.ui.Tn.Tn.T().f558T) {
            com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                public void run() {
                    Tr.T().T9();
                    com.txznet.comm.ui.Tn.Tn.T().T((com.txznet.comm.ui.T5.Tr) null);
                    com.txznet.comm.ui.Tn.Tn.T().T((TB) null);
                    com.txznet.comm.ui.Tn.Tn.T().T9();
                }
            }, 0);
        }
    }
}
