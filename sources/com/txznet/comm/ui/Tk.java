package com.txznet.comm.ui;

import android.database.Observable;

/* compiled from: Proguard */
public class Tk extends Observable<T> {

    /* compiled from: Proguard */
    public interface T {
        void T();

        void Tr();

        void Ty();
    }

    public void T() {
        synchronized (this.mObservers) {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((T) this.mObservers.get(i)).T();
            }
        }
    }

    public void Tr() {
        synchronized (this.mObservers) {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((T) this.mObservers.get(i)).Tr();
            }
        }
    }

    public void Ty() {
        synchronized (this.mObservers) {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((T) this.mObservers.get(i)).Ty();
            }
        }
    }
}
