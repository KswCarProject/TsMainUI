package com.ts.main.common;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import com.lgb.canmodule.Can;

public class VirTouchManager {
    public static int MUL_ACTION1 = 100;
    public static int MUL_ACTION2 = 101;
    public static int MyPointerAction = 888;
    public static int linelength = 100;
    /* access modifiers changed from: private */
    public static Instrumentation mInst = new Instrumentation();
    private static int nMaxTouch = 0;
    static final int nPoint = 10;
    private static int ncenterX = Can.CAN_JAC_REFINE_OD;
    private static int ncenterY = Can.CAN_NISSAN_XFY;
    private static PointMap[] pm = new PointMap[10];
    /* access modifiers changed from: private */
    public static MotionEvent.PointerCoords[] pointCoor = new MotionEvent.PointerCoords[10];
    /* access modifiers changed from: private */
    public static MotionEvent.PointerProperties[] pointProp = new MotionEvent.PointerProperties[10];
    private static syncthreadRun threadRun = new syncthreadRun();

    private static class PointMap {
        public int Action;
        public int ID;

        private PointMap() {
        }

        /* synthetic */ PointMap(PointMap pointMap) {
            this();
        }
    }

    public static class syncthreadRun implements Runnable {
        private int Action;
        private int nPointID;
        private int xpos;
        private int ypos;

        public void setParam(int x, int y, int nID, int Ac) {
            this.xpos = x;
            this.ypos = y;
            this.nPointID = nID;
            this.Action = Ac;
        }

        public void run() {
            synchronized (this) {
                MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
                pointerCoords.x = (float) this.xpos;
                pointerCoords.y = (float) this.ypos;
                pointerCoords.pressure = 1.0f;
                pointerCoords.size = 1.0f;
                VirTouchManager.pointCoor[this.nPointID] = pointerCoords;
                MotionEvent.PointerProperties pp = new MotionEvent.PointerProperties();
                pp.id = this.nPointID;
                pp.toolType = 1;
                VirTouchManager.pointProp[this.nPointID] = pp;
                VirTouchManager.mInst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), this.Action, this.nPointID + 1, VirTouchManager.pointProp, VirTouchManager.pointCoor, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0));
            }
        }
    }

    public static synchronized void clickedDown(int x, int y, int Action) {
        synchronized (VirTouchManager.class) {
            boolean nofigure = true;
            int i = 0;
            while (true) {
                if (i < pm.length) {
                    if (pm[i] != null && pm[i].Action != -1) {
                        nofigure = false;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            if (nofigure) {
                nMaxTouch = 0;
            }
            boolean existTouch = false;
            int ntouchid = nMaxTouch;
            PointMap pointmap = new PointMap((PointMap) null);
            int i2 = 0;
            while (true) {
                if (i2 >= pm.length) {
                    break;
                }
                if (pm[i2] != null) {
                    Log.i("Touch", "i = " + i2 + ",,ID = " + pm[i2].ID);
                    Log.i("Touch", "i = " + i2 + ",,Action = " + pm[i2].Action);
                    if (pm[i2].Action == Action) {
                        existTouch = true;
                        ntouchid = pm[i2].ID;
                        break;
                    }
                }
                i2++;
            }
            int ac = 0;
            if (ntouchid != 0) {
                ac = (ntouchid * 256) + 5;
            }
            clickedPoint(x, y, ac, ntouchid);
            pointmap.Action = Action;
            pointmap.ID = ntouchid;
            pm[ntouchid] = pointmap;
            if (!existTouch) {
                nMaxTouch++;
            }
        }
    }

    public static synchronized void clickedUp(int x, int y, int Action) {
        synchronized (VirTouchManager.class) {
            nMaxTouch--;
            if (nMaxTouch < 0) {
                nMaxTouch = 0;
            }
            int ac = 1;
            int nID = 0;
            for (int i = 0; i < pm.length; i++) {
                if (pm[i] != null && pm[i].Action == Action) {
                    nID = pm[i].ID;
                }
            }
            if (nID != 0) {
                ac = (nID * 256) + 6;
            }
            clickedPoint(x, y, ac, nID);
            pm[nID].Action = -1;
            Log.i("Touch", "clickedUp nMaxTouch = " + nMaxTouch);
        }
    }

    public static synchronized void clickedline(float startx, float starty) {
        int xpos;
        int ypos;
        synchronized (VirTouchManager.class) {
            int xpos2 = (int) ((((double) Math.abs(startx)) / 1.0d) * ((double) linelength));
            int ypos2 = (int) ((((double) Math.abs(starty)) / 1.0d) * ((double) linelength));
            if (((double) startx) < 0.0d) {
                xpos = ncenterX - xpos2;
            } else {
                xpos = xpos2 + ncenterX;
            }
            if (((double) starty) < 0.0d) {
                ypos = ncenterY - ypos2;
            } else {
                ypos = ypos2 + ncenterY;
            }
            if (ncenterX == xpos && ncenterY == ypos) {
                Log.i("Touch", "clickedUp");
                clickedUp(xpos, ypos, MyPointerAction);
            } else if (movepoint(xpos, ypos)) {
                Log.i("Touch", "movepoint == true,,, xpos = " + xpos + ", ypos" + ypos + "nMaxTouch = " + nMaxTouch);
            } else {
                Log.i("Touch", "movepoint == false,,, xpos = " + xpos + ", ypos" + ypos + "nMaxTouch = " + nMaxTouch);
                clickedDown(xpos, ypos, MyPointerAction);
            }
        }
    }

    public static boolean movepoint(int x, int y, int nAc) {
        boolean bresult = false;
        int ntouchid = -1;
        int i = 0;
        while (true) {
            if (i < pm.length) {
                if (pm[i] != null && pm[i].Action == nAc) {
                    ntouchid = pm[i].ID;
                    bresult = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (-1 != ntouchid) {
            clickedPoint(x, y, 2, ntouchid);
        }
        return bresult;
    }

    public static boolean movepoint(int x, int y) {
        boolean bresult = false;
        int ntouchid = -1;
        int i = 0;
        while (true) {
            if (i < pm.length) {
                if (pm[i] != null && pm[i].Action == MyPointerAction) {
                    ntouchid = pm[i].ID;
                    bresult = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (-1 != ntouchid) {
            clickedPoint(x, y, 2, ntouchid);
        }
        return bresult;
    }

    private static void clickedPoint(int x, int y, int Action, int nPointID) {
        threadRun.setParam(x, y, nPointID, Action);
        new Thread(threadRun).start();
    }
}
