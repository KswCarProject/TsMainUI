package com.ts.main.radio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.ts.main.common.MainUI;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Radio;

public class RadioFunc {
    public static final boolean DEBUG = false;
    private static OnAmsListener mAmsListener;
    private static SharedPreferences sp;

    public interface OnAmsListener {
        void onAms();
    }

    public static void registerOnAmsListener(OnAmsListener listener) {
        mAmsListener = listener;
    }

    public static void saveMemFreqName(String name, int freqId) {
        if (MainUI.GetInstance() != null) {
            if (sp == null) {
                sp = MainUI.GetInstance().getSharedPreferences("radio_meminfo", 0);
            }
            SharedPreferences.Editor edit = sp.edit();
            edit.putString(new StringBuilder().append(freqId).toString(), name);
            edit.commit();
        }
    }

    public static String getMemFreqName(int index) {
        if (MainUI.GetInstance() == null) {
            return null;
        }
        if (sp == null) {
            sp = MainUI.GetInstance().getSharedPreferences("radio_meminfo", 0);
        }
        return sp.getString(new StringBuilder().append(index).toString(), (String) null);
    }

    public static void clearMemFreqName() {
        if (sp == null) {
            sp = MainUI.GetInstance().getSharedPreferences("radio_meminfo", 0);
        }
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.commit();
    }

    public static int DealKey(int nkey) {
        switch (nkey) {
            case 12:
            case 50:
            case 55:
            case 298:
            case KeyDef.RKEY_RDS_PTY /*331*/:
                return 1;
            case 32:
            case 276:
                Radio.TuneMset(1);
                return 1;
            case 33:
            case 277:
                Radio.TuneMset(2);
                return 1;
            case 34:
            case 278:
                Radio.TuneMset(3);
                return 1;
            case 35:
            case 279:
                Radio.TuneMset(4);
                return 1;
            case 36:
            case 280:
                Radio.TuneMset(5);
                return 1;
            case 37:
            case 281:
                Radio.TuneMset(6);
                return 1;
            case 43:
            case 263:
                Radio.TuneBand(1);
                return 1;
            case 44:
            case 291:
                Radio.TuneMnext();
                return 1;
            case 45:
            case 292:
                Radio.TuneMprev();
                return 1;
            case 46:
            case 293:
                Radio.TuneSearch(1);
                return 1;
            case 47:
            case 294:
                Radio.TuneSearch(0);
                return 1;
            case 48:
            case KeyDef.RKEY_RDS_AF /*329*/:
                Radio.RdsAf();
                return 1;
            case 49:
            case KeyDef.RKEY_RDS_TA /*330*/:
                Radio.RdsTa();
                return 1;
            case 51:
                if (Radio.GetDisp(2) < 4) {
                    Radio.TuneBandAm();
                    return 1;
                }
                Radio.TuneBandFm();
                return 1;
            case 52:
                Radio.TuneBandFm();
                return 1;
            case 53:
                Radio.TuneBandAm();
                return 1;
            case 54:
            case 297:
                Radio.TuneStset();
                return 1;
            case 56:
            case 516:
                Radio.TuneStep(1);
                return 1;
            case 57:
            case 515:
                Radio.TuneStep(0);
                return 1;
            case 58:
            case 295:
                Radio.TuneAms();
                clearMemFreqName();
                if (mAmsListener == null) {
                    return 1;
                }
                mAmsListener.onAms();
                return 1;
            case 59:
            case 63:
            case 296:
                Radio.TuneInt();
                return 1;
            case 66:
            case 289:
                Radio.TuneSearch(1);
                return 1;
            case 67:
            case 290:
                Radio.TuneSearch(0);
                return 1;
            case 68:
            case 288:
                Radio.TuneMprev();
                return 1;
            case 69:
            case 287:
                Radio.TuneMnext();
                return 1;
            case 75:
            case KeyDef.RKEY_RADIO_1S /*323*/:
                Radio.TuneMsave(1);
                return 1;
            case 76:
            case KeyDef.RKEY_RADIO_2S /*324*/:
                Radio.TuneMsave(2);
                return 1;
            case 77:
            case KeyDef.RKEY_RADIO_3S /*325*/:
                Radio.TuneMsave(3);
                return 1;
            case 78:
            case KeyDef.RKEY_RADIO_4S /*326*/:
                Radio.TuneMsave(4);
                return 1;
            case 79:
            case KeyDef.RKEY_RADIO_5S /*327*/:
                Radio.TuneMsave(5);
                return 1;
            case 80:
            case KeyDef.RKEY_RADIO_6S /*328*/:
                Radio.TuneMsave(6);
                return 1;
            case KeyDef.SKEY_SEEKUP_2 /*785*/:
            case KeyDef.SKEY_CHUP_1 /*794*/:
                Radio.TuneMnext();
                return 1;
            case KeyDef.SKEY_SEEKUP_3 /*786*/:
                Radio.TuneSearch(1);
                return 1;
            case KeyDef.SKEY_SEEKDN_2 /*790*/:
            case KeyDef.SKEY_CHDN_1 /*799*/:
                Radio.TuneMprev();
                return 1;
            case KeyDef.SKEY_SEEKDN_3 /*791*/:
                Radio.TuneSearch(0);
                return 1;
            default:
                return 0;
        }
    }

    public static void ShowRdsSet(Context context) {
        show("com.ts.MainUI", "com.ts.can.radio.CanRadioRdsSetActivity");
    }

    public static void show(String sPackage, String sActivity) {
        Context context;
        MainUI ui = MainUI.GetInstance();
        if (ui != null && (context = ui.getApplicationContext()) != null) {
            ComponentName componetName = new ComponentName(sPackage, sActivity);
            Intent intent = new Intent();
            intent.setComponent(componetName);
            intent.addFlags(337641472);
            context.startActivity(intent);
        }
    }
}
