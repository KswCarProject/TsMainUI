package com.T.T.Tr;

import com.android.SdkConstants;
import com.autochips.camera.util.DVRConst;
import com.ts.bt.ContactInfo;

/* compiled from: Proguard */
public class TZ {
    public static String T(int value) {
        switch (value) {
            case 1:
                return "error";
            case 2:
                return "int";
            case 3:
                return "float";
            case 4:
                return SdkConstants.TAG_STRING;
            case 5:
                return "iso8601";
            case 6:
                return SdkConstants.VALUE_TRUE;
            case 7:
                return SdkConstants.VALUE_FALSE;
            case 8:
                return DVRConst.UNKOWN_CAMERA_ID;
            case 9:
                return "new";
            case 10:
                return "(";
            case 11:
                return ")";
            case 12:
                return "{";
            case 13:
                return "}";
            case 14:
                return "[";
            case 15:
                return "]";
            case 16:
                return ContactInfo.COMBINATION_SEPERATOR;
            case 17:
                return ":";
            case 18:
                return "ident";
            case 19:
                return "fieldName";
            case 20:
                return "EOF";
            case 21:
                return "Set";
            case 22:
                return "TreeSet";
            default:
                return "Unkown";
        }
    }
}
