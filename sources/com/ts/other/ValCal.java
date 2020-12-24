package com.ts.other;

public class ValCal {
    public static int dataCal(int cur, int step, int min, int max, boolean inc, boolean loop) {
        if (inc) {
            if (cur + step <= max) {
                return cur + step;
            }
            if (loop) {
                return min;
            }
            return max;
        } else if (cur - step >= min) {
            return cur - step;
        } else {
            if (loop) {
                return max;
            }
            return min;
        }
    }

    public static int dataInc(int cur, int min, int max) {
        return dataCal(cur, 1, min, max, true, false);
    }

    public static int dataDec(int cur, int min, int max) {
        return dataCal(cur, 1, min, max, false, false);
    }

    public static int dataStepNoLoop(int cur, int min, int max, boolean inc) {
        return dataCal(cur, 1, min, max, inc, false);
    }

    public static int dataStepLoop(int cur, int min, int max, boolean inc) {
        return dataCal(cur, 1, min, max, inc, true);
    }

    public static boolean int2Bool(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    public static int AbsSub(int a, int b) {
        if (a > b) {
            return a - b;
        }
        return b - a;
    }

    public static int NumApproach(int des, int src) {
        if (des == src) {
            return src;
        }
        if (AbsSub(des, src) > 3) {
            return src + ((des - src) / 3);
        }
        if (src < des) {
            return src + 1;
        }
        return src - 1;
    }
}
