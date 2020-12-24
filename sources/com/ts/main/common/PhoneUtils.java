package com.ts.main.common;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import com.android.internal.telephony.ITelephony;
import com.txznet.sdk.tongting.IConstantData;
import java.lang.reflect.Method;

public class PhoneUtils {
    public static ITelephony getITelephony(TelephonyManager telephony) throws Exception {
        Method getITelephonyMethod = telephony.getClass().getDeclaredMethod("getITelephony", new Class[0]);
        getITelephonyMethod.setAccessible(true);
        return (ITelephony) getITelephonyMethod.invoke(telephony, new Object[0]);
    }

    public static void rejectCall(TelephonyManager telephony) {
        KeyTouch.GetInstance().sendKeyClick(6);
    }

    public void autoAnswerPhone(Context myContext, TelephonyManager mTelephonyManager) {
        try {
            getITelephony(mTelephonyManager).answerRingingCall();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                intent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, 79));
                myContext.sendOrderedBroadcast(intent, "android.permission.CALL_PRIVILEGED");
                Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
                intent2.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(1, 79));
                myContext.sendOrderedBroadcast(intent2, "android.permission.CALL_PRIVILEGED");
                Intent localIntent1 = new Intent("android.intent.action.HEADSET_PLUG");
                localIntent1.addFlags(1073741824);
                localIntent1.putExtra(IConstantData.KEY_STATE, 1);
                localIntent1.putExtra("microphone", 1);
                localIntent1.putExtra(IConstantData.KEY_NAME, "Headset");
                myContext.sendOrderedBroadcast(localIntent1, "android.permission.CALL_PRIVILEGED");
                Intent localIntent2 = new Intent("android.intent.action.MEDIA_BUTTON");
                localIntent2.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, 79));
                myContext.sendOrderedBroadcast(localIntent2, "android.permission.CALL_PRIVILEGED");
                Intent localIntent3 = new Intent("android.intent.action.MEDIA_BUTTON");
                localIntent3.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(1, 79));
                myContext.sendOrderedBroadcast(localIntent3, "android.permission.CALL_PRIVILEGED");
                Intent localIntent4 = new Intent("android.intent.action.HEADSET_PLUG");
                localIntent4.addFlags(1073741824);
                localIntent4.putExtra(IConstantData.KEY_STATE, 0);
                localIntent4.putExtra("microphone", 1);
                localIntent4.putExtra(IConstantData.KEY_NAME, "Headset");
                myContext.sendOrderedBroadcast(localIntent4, "android.permission.CALL_PRIVILEGED");
            } catch (Exception e2) {
                e2.printStackTrace();
                Intent meidaButtonIntent = new Intent("android.intent.action.MEDIA_BUTTON");
                meidaButtonIntent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(1, 79));
                myContext.sendOrderedBroadcast(meidaButtonIntent, (String) null);
            }
        }
    }

    public static void answerCall(TelephonyManager telephony) {
        KeyTouch.GetInstance().sendKeyClick(5);
    }
}
