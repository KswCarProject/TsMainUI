package com.txznet.txz.T;

import com.txznet.comm.Tr.Tr.T9;
import com.txznet.comm.Tr.Tr.Th;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: Proguard */
public class Tn {

    /* renamed from: T  reason: collision with root package name */
    static final ReadWriteLock f891T = new ReentrantReadWriteLock(false);
    static Map<String, T> Tr = new HashMap();
    public static int Ty = 1;

    /* compiled from: Proguard */
    public interface T {
    }

    static {
        com.txznet.comm.Tr.Tr.Tn.Tr();
        T9.T();
        Th.T();
        com.txznet.comm.Tr.Tr.T.Tk();
    }

    public static void T(String prefix, T proc) {
        f891T.writeLock().lock();
        Tr.put(prefix, proc);
        f891T.writeLock().unlock();
    }
}
