package com.ts.can.bmw.lz.bmw2;

import com.yyw.ts70xhw.Iop;

public class CanBmw2LzCarDevView {
    public static int mOldBtSta = 0;

    public static void DealDevEvent() {
        if (mOldBtSta != Iop.GetMediaOrBlue()) {
            mOldBtSta = Iop.GetMediaOrBlue();
            if (mOldBtSta > 0) {
                Iop.RstPort(1);
            } else {
                Iop.RstPort(0);
            }
        }
    }
}
