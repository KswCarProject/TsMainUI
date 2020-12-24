package com.txznet.comm.ui.T9;

import com.txznet.comm.ui.Tn.T.Tk;
import com.txznet.comm.ui.Tn.Ty;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: Proguard */
public class T9 implements T {
    private static T9 Tr = new T9();

    /* renamed from: T  reason: collision with root package name */
    boolean f502T = false;
    private Method T9;
    private Method Tn;
    private Class Ty;

    private T9() {
    }

    public static T9 Ty() {
        return Tr;
    }

    public void T() {
        Tk.Tk().T9();
    }

    public boolean isShowing() {
        return this.f502T;
    }

    public void T(boolean isFullScreen) {
    }

    public void T(Ty winLayout) {
    }

    public void show() {
        try {
            this.f502T = true;
            if (this.Ty == null) {
                this.Ty = Class.forName("com.txznet.reserve.activity.ReserveSingleInstanceActivity0");
            }
            if (this.Tn == null) {
                this.Tn = this.Ty.getDeclaredMethod("show", new Class[0]);
            }
            this.Tn.invoke(this.Ty, new Object[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
    }

    public void dismiss() {
        try {
            this.f502T = false;
            if (this.Ty == null) {
                this.Ty = Class.forName("com.txznet.reserve.activity.ReserveSingleInstanceActivity0");
            }
            if (this.T9 == null) {
                this.T9 = this.Ty.getDeclaredMethod("dismiss", new Class[0]);
            }
            this.T9.invoke(this.Ty, new Object[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
    }

    public void Tr() {
    }
}
