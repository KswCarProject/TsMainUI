package com.txznet.comm.ui.TE;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import com.txznet.txz.comm.R;
import com.txznet.txz.util.TE;
import java.util.HashMap;

/* compiled from: Proguard */
public class Tn {

    /* renamed from: T  reason: collision with root package name */
    public static int f539T;
    static Integer T0 = null;
    static int T5;
    static int T6;
    static int T9;
    public static int TA = 1;
    static Integer TB = null;
    static int TD;
    static int TE;
    static Integer TF = null;
    static int TG = 180;
    static Boolean TK = null;
    static boolean TN = true;
    static int TO = 3;
    private static T TV;
    static int TZ;
    static Integer Te = null;
    public static int Tf = 3;
    static int Th;
    static Integer Tj = null;
    static int Tk;
    static int Tn;
    static Integer Tq = null;
    public static int Tr;
    static int Ts = 120;
    static int Tt;
    static int Tu = 1;
    static int Tv;
    static int Tx = 4;
    public static int Ty;

    static {
        if (com.txznet.comm.Tr.T.T()) {
            TZ = (int) com.txznet.comm.Tr.T.Tr().getResources().getDimension(R.dimen.y64);
            TE = (int) com.txznet.comm.Tr.T.Tr().getResources().getDimension(R.dimen.y66);
            T5 = (int) com.txznet.comm.Tr.T.Tr().getResources().getDimension(R.dimen.y80);
            Tv = (int) com.txznet.comm.Tr.T.Tr().getResources().getDimension(R.dimen.y100);
        } else {
            TZ = (int) Tr.Tn("y64");
            TE = (int) Tr.Tn("y66");
            T5 = (int) Tr.Tn("y80");
            Tv = (int) Tr.Tn("y100");
        }
    }

    private static void Tn() {
        Tt = 0;
        TD = 0;
        TZ = (int) Tr.Tn("y64");
        TE = (int) Tr.Tn("y66");
        T5 = (int) Tr.Tn("y80");
        Tv = (int) Tr.Tn("y100");
        Tx = 4;
    }

    public static void T(View view) {
        Tn();
        Rect outRect = new Rect();
        view.getWindowVisibleDisplayFrame(outRect);
        int width = outRect.width();
        int height = outRect.height();
        HashMap<String, String> cfg = TE.T("screenWidth", "screenHeight");
        String strWidth = cfg.get("screenWidth");
        String strHeight = cfg.get("screenHeight");
        if (!TextUtils.isEmpty(strWidth) && !TextUtils.isEmpty(strHeight)) {
            try {
                int iWidth = Integer.parseInt(strWidth);
                int iHeight = Integer.parseInt(strHeight);
                if (iWidth > 0 && iHeight > 0) {
                    TF = Integer.valueOf(iWidth);
                    Tq = Integer.valueOf(iHeight);
                    com.txznet.comm.Tr.Tr.Tn.T((Object) "load width : " + iWidth + " ; height : " + iHeight);
                }
            } catch (Exception e) {
                com.txznet.comm.Tr.Tr.Tn.Tr((Object) "parse width/height error");
            }
        }
        if (Tq != null) {
            height = Tq.intValue();
        }
        if (TF != null) {
            width = TF.intValue();
        }
        float mAspectRatio = ((float) width) / ((float) height);
        if (width < 480 && height < 480) {
            TO = 1;
            Th = T5;
            T6 = 4;
            TN = true;
        } else if ((width >= 790 && mAspectRatio > 1.0f) || mAspectRatio > 1.64f) {
            if (height >= 400 && height < 480) {
                TO = 1;
                Th = T5;
                TN = true;
            } else if (height >= 480 && height < 600) {
                TO = 3;
                Th = T5;
                TN = true;
            } else if (height >= 600) {
                TO = 4;
                Th = T5;
                TN = false;
            } else {
                Th = T5;
                TN = true;
            }
            Tu = 1;
            T6 = 4;
        } else if ((width < 480 || width >= 800) && mAspectRatio > 1.64f) {
            Tu = 1;
            Th = T5;
            T6 = 4;
            TN = true;
        } else {
            TO = 3;
            Th = T5;
            Tu = 2;
            T6 = 4;
            TN = true;
            Tx = 3;
            if (width > 1024) {
                TO = 3;
            } else if (width > 790) {
                TO = 1;
            } else if (width > 480) {
                TO = 1;
            } else {
                TO = 4;
            }
            if (height >= 1024) {
                Th = Tv;
                TN = false;
            } else if (height >= 800) {
                Th = T5;
                TN = false;
            }
        }
        com.txznet.comm.Tr.Tr.Tn.T("mRealItemHeight:" + Th);
        com.txznet.comm.Tr.Tr.Tn.T(" bottom: " + outRect.bottom + " ; right: " + outRect.right + " ; mScreenType:" + TO + " ; mListViewRectHeight:" + f539T + " ；sVisibleCount:" + T9);
        if (Te != null) {
            T6 = Te.intValue();
        }
        if (TK != null) {
            TN = TK.booleanValue();
        }
        Tn(height);
        if (Tu == 2 && Te == null) {
            if (f539T <= 720) {
                T6 = 4;
            } else if (f539T <= 900) {
                T6 = 5;
            } else if (f539T <= 1080) {
                T6 = 6;
            } else if (f539T <= 1260) {
                T6 = 7;
            } else {
                T6 = 8;
            }
        }
        Ty(width);
        T(true);
    }

    public static void Tr(View view) {
        Tn();
        Rect outRect = new Rect();
        view.getWindowVisibleDisplayFrame(outRect);
        int width = outRect.width();
        int height = outRect.height();
        com.txznet.comm.Tr.Tr.Tn.T("width:" + width + ",height:" + height);
        HashMap<String, String> cfg = TE.T("screenWidth", "screenHeight");
        String strWidth = cfg.get("screenWidth");
        String strHeight = cfg.get("screenHeight");
        if (!TextUtils.isEmpty(strWidth) && !TextUtils.isEmpty(strHeight)) {
            try {
                int iWidth = Integer.parseInt(strWidth);
                int iHeight = Integer.parseInt(strHeight);
                if (iWidth > 0 && iHeight > 0) {
                    width = iWidth;
                    height = iHeight;
                    com.txznet.comm.Tr.Tr.Tn.T((Object) "load width : " + iWidth + " ; height : " + iHeight);
                }
            } catch (Exception e) {
                com.txznet.comm.Tr.Tr.Tn.Tr((Object) "parse width/height error");
            }
        }
        float mAspectRatio = ((float) width) / ((float) height);
        Tu = 2;
        Tx = 3;
        if (width < 480 && height < 480) {
            TO = 1;
            Th = T5;
            T6 = 4;
            TN = true;
        } else if ((width >= 790 && mAspectRatio > 1.0f) || mAspectRatio > 1.64f) {
            if (height >= 400 && height < 480) {
                TO = 1;
                Th = T5;
                TN = true;
            } else if (height >= 480 && height < 600) {
                TO = 3;
                Th = T5;
                TN = true;
            } else if (height >= 600) {
                TO = 4;
                Th = T5;
                TN = false;
            } else {
                Th = T5;
                TN = true;
            }
            T6 = 4;
        } else if ((width < 480 || width >= 800) && mAspectRatio > 1.64f) {
            Th = T5;
            TN = true;
        } else {
            TO = 3;
            Th = T5;
            T6 = 4;
            TN = true;
            Tx = 3;
            if (width > 1024) {
                TO = 3;
            } else if (width > 790) {
                TO = 1;
            } else if (width > 480) {
                TO = 1;
            } else {
                TO = 4;
            }
            if (height >= 1024) {
                Th = Tv;
                TN = false;
            } else if (height >= 800) {
                Th = T5;
                TN = false;
            }
        }
        if (Te != null) {
            T6 = Te.intValue();
        }
        if (TK != null) {
            TN = TK.booleanValue();
        }
        com.txznet.comm.Tr.Tr.Tn.T("mRealItemHeight:" + Th);
        com.txznet.comm.Tr.Tr.Tn.T(" bottom: " + outRect.bottom + " ; right: " + outRect.right + " ; mScreenType:" + TO + " ; mListViewRectHeight:" + f539T + " ；sVisibleCount:" + T9);
        Tn(height);
        if (Tu == 2 && Te == null) {
            if (f539T <= 720) {
                T6 = 4;
            } else if (f539T <= 900) {
                T6 = 5;
            } else if (f539T <= 1080) {
                T6 = 6;
            } else if (f539T <= 1260) {
                T6 = 7;
            } else {
                T6 = 8;
            }
        }
        Ty(width);
        T(true);
    }

    public static void Ty(View view) {
        Tn();
        Rect outRect = new Rect();
        view.getWindowVisibleDisplayFrame(outRect);
        int width = outRect.width();
        int height = outRect.height();
        com.txznet.comm.Tr.Tr.Tn.T("width:" + width + ",height:" + height);
        HashMap<String, String> cfg = TE.T("screenWidth", "screenHeight");
        String strWidth = cfg.get("screenWidth");
        String strHeight = cfg.get("screenHeight");
        if (!TextUtils.isEmpty(strWidth) && !TextUtils.isEmpty(strHeight)) {
            try {
                int iWidth = Integer.parseInt(strWidth);
                int iHeight = Integer.parseInt(strHeight);
                if (iWidth > 0 && iHeight > 0) {
                    width = iWidth;
                    height = iHeight;
                    com.txznet.comm.Tr.Tr.Tn.T((Object) "load width : " + iWidth + " ; height : " + iHeight);
                }
            } catch (Exception e) {
                com.txznet.comm.Tr.Tr.Tn.Tr((Object) "parse width/height error");
            }
        }
        float mAspectRatio = ((float) width) / ((float) height);
        Tu = 1;
        if (width < 480 && height < 480) {
            TO = 1;
            Th = T5;
            T6 = 4;
            TN = true;
        } else if ((width >= 790 && mAspectRatio > 1.0f) || mAspectRatio > 1.64f) {
            if (height >= 400 && height < 480) {
                TO = 1;
                Th = T5;
                TN = true;
            } else if (height >= 480 && height < 600) {
                TO = 3;
                Th = T5;
                TN = true;
            } else if (height >= 600) {
                TO = 4;
                Th = T5;
                TN = false;
            } else {
                Th = T5;
                TN = true;
            }
            T6 = 4;
        } else if ((width < 480 || width >= 800) && mAspectRatio > 1.64f) {
            Th = T5;
            TN = true;
        } else {
            TO = 3;
            Th = T5;
            T6 = 4;
            TN = false;
        }
        if (Te != null) {
            T6 = Te.intValue();
        }
        if (TK != null) {
            TN = TK.booleanValue();
        }
        com.txznet.comm.Tr.Tr.Tn.T("mRealItemHeight:" + Th);
        com.txznet.comm.Tr.Tr.Tn.T(" bottom: " + outRect.bottom + " ; right: " + outRect.right + " ; mScreenType:" + TO + " ; mListViewRectHeight:" + f539T + " ；sVisibleCount:" + T9);
        Tn(height);
        Ty(width);
        T(true);
    }

    public static boolean T() {
        return f539T > Tr;
    }

    public static int Tr() {
        return Tu;
    }

    public static void Tn(final View contentView) {
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                contentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int tmpH = contentView.getHeight();
                int tmpW = contentView.getWidth();
                com.txznet.comm.Ty.Tn.T(contentView.getWidth(), contentView.getHeight(), false);
                if (tmpH != Tn.Tt && Tn.Tq == null && tmpH > 0) {
                    Tn.Tt = tmpH;
                    Tn.Tn(Tn.Tt);
                    Tn.T(true);
                }
                if (tmpW != Tn.TD && Tn.TF == null && tmpW > 0) {
                    Tn.TD = tmpW;
                    Tn.Ty(Tn.TD);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void Ty(int conWidth) {
        com.txznet.comm.Tr.Tr.Tn.T("ScreenUtil  checkListWidth start:" + Tr + ",conHeight:" + conWidth);
        if (conWidth > 0) {
            switch (Tr()) {
                case 2:
                    Tr = (int) (((float) conWidth) - Tr.Tn("x30"));
                    break;
                default:
                    Tr = (int) (((float) ((Tf * conWidth) / (Tf + TA))) - Tr.Tn("x30"));
                    break;
            }
            com.txznet.comm.Tr.Tr.Tn.T("ScreenUtil  checkListWidth end:" + Tr);
        }
    }

    /* access modifiers changed from: private */
    public static void Tn(int conHeight) {
        if (conHeight > 0) {
            switch (Tr()) {
                case 1:
                    if (TB != null) {
                        switch (TB.intValue()) {
                            case 1:
                            case 3:
                                Ty = conHeight;
                                f539T = conHeight - TZ;
                                return;
                            case 2:
                                switch (TO) {
                                    case 1:
                                        Ty = conHeight;
                                        f539T = (conHeight - TZ) - Tv;
                                        return;
                                    default:
                                        Ty = conHeight;
                                        f539T = (conHeight - TZ) - T5;
                                        return;
                                }
                            default:
                                return;
                        }
                    } else {
                        Ty = conHeight;
                        f539T = conHeight - TZ;
                        return;
                    }
                case 2:
                    if (TB != null) {
                        switch (TB.intValue()) {
                            case 1:
                            case 3:
                                Ty = conHeight - Tv;
                                f539T = (conHeight - TZ) - Tv;
                                return;
                            case 2:
                                Ty = (conHeight - Tv) - T5;
                                f539T = ((conHeight - TZ) - Tv) - T5;
                                return;
                            default:
                                return;
                        }
                    } else {
                        Ty = conHeight - Tv;
                        f539T = (conHeight - TZ) - Tv;
                        return;
                    }
                default:
                    return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        if (r1 > 0) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized int T(boolean r8) {
        /*
            r4 = 1
            java.lang.Class<com.txznet.comm.ui.TE.Tn> r3 = com.txznet.comm.ui.TE.Tn.class
            monitor-enter(r3)
            java.lang.Integer r2 = Tj     // Catch:{ all -> 0x00c6 }
            if (r2 == 0) goto L_0x0010
            java.lang.Integer r2 = Tj     // Catch:{ all -> 0x00c6 }
            int r1 = r2.intValue()     // Catch:{ all -> 0x00c6 }
        L_0x000e:
            monitor-exit(r3)
            return r1
        L_0x0010:
            if (r8 != 0) goto L_0x001e
            com.txznet.comm.ui.TE.Tn$T r2 = TV     // Catch:{ all -> 0x00c6 }
            if (r2 == 0) goto L_0x001e
            com.txznet.comm.ui.TE.Tn$T r2 = TV     // Catch:{ all -> 0x00c6 }
            int r1 = r2.T()     // Catch:{ all -> 0x00c6 }
            if (r1 > 0) goto L_0x000e
        L_0x001e:
            int r2 = Tn     // Catch:{ all -> 0x00c6 }
            if (r2 <= 0) goto L_0x0027
            if (r8 != 0) goto L_0x0027
            int r1 = Tn     // Catch:{ all -> 0x00c6 }
            goto L_0x000e
        L_0x0027:
            int r2 = Tu     // Catch:{ all -> 0x00c6 }
            if (r2 != r4) goto L_0x00ba
            int r2 = Th     // Catch:{ all -> 0x00c6 }
            int r4 = Ts     // Catch:{ all -> 0x00c6 }
            if (r2 <= r4) goto L_0x0035
            int r2 = Ts     // Catch:{ all -> 0x00c6 }
            Th = r2     // Catch:{ all -> 0x00c6 }
        L_0x0035:
            int r2 = f539T     // Catch:{ all -> 0x00c6 }
            if (r2 <= 0) goto L_0x008a
            int r2 = Th     // Catch:{ all -> 0x00c6 }
            if (r2 != 0) goto L_0x0040
            r2 = 1
            Th = r2     // Catch:{ all -> 0x00c6 }
        L_0x0040:
            int r2 = f539T     // Catch:{ all -> 0x00c6 }
            int r4 = Th     // Catch:{ all -> 0x00c6 }
            int r0 = r2 / r4
            int r2 = T6     // Catch:{ all -> 0x00c6 }
            if (r0 <= r2) goto L_0x00ce
            int r0 = T6     // Catch:{ all -> 0x00c6 }
            boolean r2 = TN     // Catch:{ all -> 0x00c6 }
            if (r2 == 0) goto L_0x00c9
            int r2 = f539T     // Catch:{ all -> 0x00c6 }
            int r2 = r2 / r0
            double r4 = (double) r2     // Catch:{ all -> 0x00c6 }
            double r4 = java.lang.Math.ceil(r4)     // Catch:{ all -> 0x00c6 }
            int r2 = (int) r4     // Catch:{ all -> 0x00c6 }
            Tn = r2     // Catch:{ all -> 0x00c6 }
        L_0x005b:
            T9 = r0     // Catch:{ all -> 0x00c6 }
            int r2 = T9     // Catch:{ all -> 0x00c6 }
            int r4 = Tn     // Catch:{ all -> 0x00c6 }
            int r2 = r2 * r4
            Tk = r2     // Catch:{ all -> 0x00c6 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c6 }
            r2.<init>()     // Catch:{ all -> 0x00c6 }
            java.lang.String r4 = "RectHeight:"
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x00c6 }
            int r4 = f539T     // Catch:{ all -> 0x00c6 }
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x00c6 }
            java.lang.String r4 = ",ItemH:"
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x00c6 }
            int r4 = Tn     // Catch:{ all -> 0x00c6 }
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00c6 }
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r2)     // Catch:{ all -> 0x00c6 }
        L_0x008a:
            int r2 = T9     // Catch:{ all -> 0x00c6 }
            if (r2 <= 0) goto L_0x00b6
            com.txznet.comm.Tr.Tn r2 = com.txznet.comm.Tr.Tn.Tr()     // Catch:{ all -> 0x00c6 }
            java.lang.String r4 = "com.txznet.txz"
            java.lang.String r5 = "txz.record.ui.event.display.count"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c6 }
            r6.<init>()     // Catch:{ all -> 0x00c6 }
            int r7 = T9     // Catch:{ all -> 0x00c6 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x00c6 }
            java.lang.String r7 = ""
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x00c6 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00c6 }
            byte[] r6 = r6.getBytes()     // Catch:{ all -> 0x00c6 }
            r7 = 0
            r2.T((java.lang.String) r4, (java.lang.String) r5, (byte[]) r6, (com.txznet.comm.Tr.Tn.Tr) r7)     // Catch:{ all -> 0x00c6 }
        L_0x00b6:
            int r1 = Tn     // Catch:{ all -> 0x00c6 }
            goto L_0x000e
        L_0x00ba:
            int r2 = Th     // Catch:{ all -> 0x00c6 }
            int r4 = TG     // Catch:{ all -> 0x00c6 }
            if (r2 <= r4) goto L_0x0035
            int r2 = TG     // Catch:{ all -> 0x00c6 }
            Th = r2     // Catch:{ all -> 0x00c6 }
            goto L_0x0035
        L_0x00c6:
            r2 = move-exception
            monitor-exit(r3)
            throw r2
        L_0x00c9:
            int r2 = Th     // Catch:{ all -> 0x00c6 }
            Tn = r2     // Catch:{ all -> 0x00c6 }
            goto L_0x005b
        L_0x00ce:
            if (r0 != 0) goto L_0x00db
            int r2 = Tn     // Catch:{ all -> 0x00c6 }
            if (r2 <= 0) goto L_0x00d8
            int r1 = Tn     // Catch:{ all -> 0x00c6 }
            goto L_0x000e
        L_0x00d8:
            r2 = 1
            Tn = r2     // Catch:{ all -> 0x00c6 }
        L_0x00db:
            int r2 = f539T     // Catch:{ all -> 0x00c6 }
            int r2 = r2 / r0
            double r4 = (double) r2     // Catch:{ all -> 0x00c6 }
            double r4 = java.lang.Math.ceil(r4)     // Catch:{ all -> 0x00c6 }
            int r2 = (int) r4     // Catch:{ all -> 0x00c6 }
            Tn = r2     // Catch:{ all -> 0x00c6 }
            goto L_0x005b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.ui.TE.Tn.T(boolean):int");
    }

    public static synchronized int Ty() {
        int i;
        synchronized (Tn.class) {
            if (TV == null || TV.Tr() <= 0) {
                i = T9;
            } else {
                i = TV.Tr();
            }
        }
        return i;
    }

    /* compiled from: Proguard */
    public static class T<T> {
        public int T() {
            return -1;
        }

        public int Tr() {
            return Tn.T9;
        }
    }
}
