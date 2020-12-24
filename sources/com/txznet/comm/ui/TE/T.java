package com.txznet.comm.ui.TE;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import com.txznet.comm.Tr.Tr.Tn;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class T {

    /* renamed from: T  reason: collision with root package name */
    public static int f533T = 2;
    private static String T5 = null;
    private static int T6 = 1;
    private static String T9;
    private static Boolean TB = null;
    private static String TE;
    private static Integer TF = null;
    private static Integer TG = null;
    private static Boolean TK = null;
    private static Integer TN = null;
    private static Integer TO = null;
    private static String TZ;
    private static Integer Te = null;
    private static Integer Th = null;
    private static String Tj = null;
    private static String Tk;
    private static int Tn = 1;
    private static int Tq = 3;
    static boolean Tr = true;
    private static Integer Ts = null;
    private static Boolean Tv = null;
    static int Ty = 120;

    public static void T(View view) {
        Integer mItemCount = Ty("max_visible_count");
        if (mItemCount != null && mItemCount.intValue() > 0) {
            Tn.Te = mItemCount;
        }
        Integer winHeight = Ty("win_height");
        if (winHeight != null && winHeight.intValue() > 0) {
            Tn.Tq = winHeight;
        }
        Integer itemHeight = Ty("list_item_height");
        if (itemHeight != null && itemHeight.intValue() > 0) {
            Tn.Tj = itemHeight;
        }
        Integer mItemHeight = Ty("max_item_height");
        if (mItemHeight != null && mItemHeight.intValue() > 0) {
            Tn.Ts = mItemHeight.intValue();
            Tn.TG = mItemHeight.intValue();
        }
        Integer mCinemaItemCount = Ty("cinema_visible_count");
        if (mCinemaItemCount != null && mCinemaItemCount.intValue() > 0) {
            Tn.T0 = mCinemaItemCount;
        }
        String mAutoItemHeight = Tr.T9("config_list_full_screen");
        if (mAutoItemHeight != null) {
            if (TextUtils.equals(mAutoItemHeight, "true")) {
                Tn.TK = true;
            } else if (TextUtils.equals(mAutoItemHeight, "false")) {
                Tn.TK = false;
            }
        }
        String mOrientation = Tr.T9("orientation");
        if (TextUtils.equals(mOrientation, "horizontal")) {
            Tn.Ty(view);
        } else if (TextUtils.equals(mOrientation, "vertical")) {
            Tn.Tr(view);
        } else {
            Tn.T(view);
        }
    }

    private static Integer Ty(String key) {
        String strData = Tr.T9(key);
        if (TextUtils.isEmpty(strData)) {
            return null;
        }
        try {
            return Integer.valueOf(strData);
        } catch (Exception e) {
            return null;
        }
    }

    public static int T() {
        return Tn.TO;
    }

    public static int Tr() {
        return Tn.Tu;
    }

    public static void T(int type) {
        switch (type) {
            case 1:
            case 2:
            case 3:
                Tn = type;
                break;
            default:
                Tn = 1;
                break;
        }
        Tn.TB = Integer.valueOf(Tn);
    }

    public static String Ty() {
        if (TextUtils.isEmpty(T5)) {
            T5 = Tr.T9("theme_package");
            Tn.T("mThemePackage:" + T5);
        }
        if (!TextUtils.isEmpty(T5)) {
            return T5 + ".winrecord.WinRecordImpl";
        }
        return null;
    }

    public static String Tn() {
        if (T9 == null) {
            if (TextUtils.isEmpty(T5)) {
                T5 = Tr.T9("theme_package");
                Tn.T("mThemePackage:" + T5);
            }
            if (!TextUtils.isEmpty(T5)) {
                T9 = T5 + ".view.";
            }
        }
        return T9;
    }

    public static String T9() {
        if (TZ == null) {
            if (TextUtils.isEmpty(T5)) {
                T5 = Tr.T9("theme_package");
            }
            if (!TextUtils.isEmpty(T5)) {
                TZ = T5 + ".winlayout.";
            }
        }
        return TZ;
    }

    public static String Tk() {
        if (Tk == null) {
            if (TextUtils.isEmpty(T5)) {
                T5 = Tr.T9("theme_package");
            }
            if (!TextUtils.isEmpty(T5)) {
                Tk = T5 + ".config.";
            }
        }
        return Tk;
    }

    public static String TZ() {
        if (TE == null) {
            if (TextUtils.isEmpty(T5)) {
                T5 = Tr.T9("theme_package");
            }
            if (!TextUtils.isEmpty(T5)) {
                TE = T5 + ".keyevent.KeyEventDispatcher";
            }
        }
        return TE;
    }

    public static void Tr(View contentView) {
        Tn.Tn(contentView);
    }

    public static int T(boolean needReset) {
        return Tn.T(needReset);
    }

    public static int TE() {
        return Tn.Ty();
    }

    public static Integer T5() {
        if (Th == null) {
            try {
                String str = Tr.T9("layout_record_weight");
                if (!TextUtils.isEmpty(str)) {
                    Th = Integer.valueOf(Integer.parseInt(str));
                    Tn.TA = Th.intValue();
                }
            } catch (Exception e) {
                Tn.T("getRecordWeight error!", (Throwable) e);
            }
        }
        if (Th == null) {
            return Integer.valueOf(T6);
        }
        return Th;
    }

    public static Integer Tv() {
        if (Te == null) {
            try {
                String str = Tr.T9("layout_content_weight");
                if (!TextUtils.isEmpty(str)) {
                    Te = Integer.valueOf(Integer.parseInt(str));
                    Tn.Tf = Te.intValue();
                }
            } catch (Exception e) {
                Tn.T("getContentWeight error!", (Throwable) e);
            }
        }
        if (Te == null) {
            return Integer.valueOf(Tq);
        }
        return Te;
    }

    public static Integer Th() {
        if (TF == null) {
            try {
                String str = Tr.T9("layout_record_height");
                if (!TextUtils.isEmpty(str)) {
                    TF = Integer.valueOf(Integer.parseInt(str));
                }
            } catch (Exception e) {
                Tn.T("getRecordHeight error!", (Throwable) e);
            }
        }
        return TF;
    }

    public static HashMap<String, Object> T(HashMap<String, Object> config, String path) {
        HashMap<String, Object> configs = config;
        File file = new File(path);
        if (path == null || !file.exists()) {
            return configs;
        }
        try {
            JSONArray attrArray = new JSONObject(T(file)).getJSONArray("attrs");
            if (configs == null) {
                configs = new HashMap<>();
            }
            for (int i = 0; i < attrArray.length(); i++) {
                JSONObject attrItem = attrArray.getJSONObject(i);
                Iterator iterator = attrItem.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    switch (Tr(key)) {
                        case -2:
                        case -1:
                            break;
                        case 1:
                            Integer colorValue = T("" + attrItem.get(key));
                            if (colorValue == null) {
                                break;
                            } else {
                                configs.put(key, colorValue);
                                break;
                            }
                        case 2:
                            configs.put(key, Integer.valueOf(attrItem.getInt(key)));
                            break;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return configs;
    }

    public static Integer T(String color) {
        try {
            return Integer.valueOf(Color.parseColor(color));
        } catch (Exception e) {
            Tn.Tn("get color value error:" + color);
            return null;
        }
    }

    public static int Tr(String attrName) {
        String attrName2 = attrName.toLowerCase(Locale.CHINA);
        if (TextUtils.isEmpty(attrName2)) {
            return -1;
        }
        if ("comment".equals(attrName2)) {
            return -2;
        }
        if (attrName2.contains("color")) {
            return 1;
        }
        if (attrName2.contains("size")) {
            return 2;
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034 A[SYNTHETIC, Splitter:B:17:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0039 A[Catch:{ IOException -> 0x0054 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x005c A[SYNTHETIC, Splitter:B:36:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0061 A[Catch:{ IOException -> 0x0065 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String T(java.io.File r9) {
        /*
            r3 = 0
            r6 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r8 = ""
            r1.<init>(r8)
            boolean r8 = r9.exists()
            if (r8 != 0) goto L_0x0012
            java.lang.String r8 = ""
        L_0x0011:
            return r8
        L_0x0012:
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0071 }
            r4.<init>(r9)     // Catch:{ Exception -> 0x0071 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0073, all -> 0x006a }
            r7.<init>(r4)     // Catch:{ Exception -> 0x0073, all -> 0x006a }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Exception -> 0x002c, all -> 0x006d }
            r0.<init>(r7)     // Catch:{ Exception -> 0x002c, all -> 0x006d }
            r5 = 0
        L_0x0022:
            java.lang.String r5 = r0.readLine()     // Catch:{ Exception -> 0x002c, all -> 0x006d }
            if (r5 == 0) goto L_0x003e
            r1.append(r5)     // Catch:{ Exception -> 0x002c, all -> 0x006d }
            goto L_0x0022
        L_0x002c:
            r2 = move-exception
            r6 = r7
            r3 = r4
        L_0x002f:
            r2.printStackTrace()     // Catch:{ all -> 0x0059 }
            if (r3 == 0) goto L_0x0037
            r3.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0037:
            if (r6 == 0) goto L_0x003c
            r6.close()     // Catch:{ IOException -> 0x0054 }
        L_0x003c:
            r8 = 0
            goto L_0x0011
        L_0x003e:
            java.lang.String r8 = r1.toString()     // Catch:{ Exception -> 0x002c, all -> 0x006d }
            if (r4 == 0) goto L_0x0047
            r4.close()     // Catch:{ IOException -> 0x004f }
        L_0x0047:
            if (r7 == 0) goto L_0x004c
            r7.close()     // Catch:{ IOException -> 0x004f }
        L_0x004c:
            r6 = r7
            r3 = r4
            goto L_0x0011
        L_0x004f:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x004c
        L_0x0054:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x003c
        L_0x0059:
            r8 = move-exception
        L_0x005a:
            if (r3 == 0) goto L_0x005f
            r3.close()     // Catch:{ IOException -> 0x0065 }
        L_0x005f:
            if (r6 == 0) goto L_0x0064
            r6.close()     // Catch:{ IOException -> 0x0065 }
        L_0x0064:
            throw r8
        L_0x0065:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0064
        L_0x006a:
            r8 = move-exception
            r3 = r4
            goto L_0x005a
        L_0x006d:
            r8 = move-exception
            r6 = r7
            r3 = r4
            goto L_0x005a
        L_0x0071:
            r2 = move-exception
            goto L_0x002f
        L_0x0073:
            r2 = move-exception
            r3 = r4
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.ui.TE.T.T(java.io.File):java.lang.String");
    }

    public static String T6() {
        if (Tj == null) {
            try {
                Tj = Tr.T9("dialog_background");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Tj;
    }

    public static boolean Tr(boolean defvalue) {
        if (TB == null) {
            try {
                TB = Boolean.valueOf(defvalue);
                String animation = Tr.T9("chat_list_animation");
                if ("true".equals(animation)) {
                    TB = true;
                } else if ("false".equals(animation)) {
                    TB = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TB.booleanValue();
    }

    public static boolean Ty(boolean defvalue) {
        if (TK == null) {
            try {
                TK = Boolean.valueOf(defvalue);
                String animation = Tr.T9("chat_list_animation_at_first");
                if ("true".equals(animation)) {
                    TK = true;
                } else if ("false".equals(animation)) {
                    TK = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TK.booleanValue();
    }

    public static Integer Te() {
        if (TO == null) {
            TO = 0;
            try {
                String str = Tr.T9("layout_padding_left");
                if (!TextUtils.isEmpty(str)) {
                    TO = Integer.valueOf(Integer.parseInt(str));
                }
            } catch (Exception e) {
                Tn.Tn("getPaddingLeft error!");
                e.printStackTrace();
            }
        }
        return TO;
    }

    public static Integer Tq() {
        if (TN == null) {
            TN = 0;
            try {
                String str = Tr.T9("layout_padding_top");
                if (!TextUtils.isEmpty(str)) {
                    TN = Integer.valueOf(Integer.parseInt(str));
                }
            } catch (Exception e) {
                Tn.Tn("getPaddingTop error!");
                e.printStackTrace();
            }
        }
        return TN;
    }

    public static Integer TF() {
        if (Ts == null) {
            Ts = 0;
            try {
                String str = Tr.T9("layout_padding_right");
                if (!TextUtils.isEmpty(str)) {
                    Ts = Integer.valueOf(Integer.parseInt(str));
                }
            } catch (Exception e) {
                Tn.Tn("getPaddingRight error!");
                e.printStackTrace();
            }
        }
        return Ts;
    }

    public static Integer Tj() {
        if (TG == null) {
            TG = 0;
            try {
                String str = Tr.T9("layout_padding_bottom");
                if (!TextUtils.isEmpty(str)) {
                    TG = Integer.valueOf(Integer.parseInt(str));
                }
            } catch (Exception e) {
                Tn.Tn("getPaddingBottom error!");
                e.printStackTrace();
            }
        }
        return TG;
    }
}
