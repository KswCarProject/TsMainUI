package com.ts.can.byd.hcy.s6s7;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBydS6S7CarWinSetView extends CanScrollCarInfoView {
    private static final int BYDS6S7_CAWDKGBSSC = 2;
    private static final int BYDS6S7_CAWDKGJSJC = 3;
    private static final int BYDS6S7_YKJC = 1;
    private static final int BYDS6S7_YKSC = 0;
    private CanDataInfo.BYDS6S7CarWindowSetData mCarWinData;

    public CanBydS6S7CarWinSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.BYDS6S7CarWinSet(getYKSC(Neg(this.mCarWinData.YKSC)) + getYKJC(this.mCarWinData.YKJC) + getBSSC(this.mCarWinData.CAWDKGBSSC) + getJSJC(this.mCarWinData.CAWDKGJSJC));
                return;
            case 1:
                CanJni.BYDS6S7CarWinSet(getYKSC(this.mCarWinData.YKSC) + getYKJC(Neg(this.mCarWinData.YKJC)) + getBSSC(this.mCarWinData.CAWDKGBSSC) + getJSJC(this.mCarWinData.CAWDKGJSJC));
                return;
            case 2:
                CanJni.BYDS6S7CarWinSet(getYKSC(this.mCarWinData.YKSC) + getYKJC(this.mCarWinData.YKJC) + getBSSC(Neg(this.mCarWinData.CAWDKGBSSC)) + getJSJC(this.mCarWinData.CAWDKGJSJC));
                return;
            case 3:
                CanJni.BYDS6S7CarWinSet(getYKSC(this.mCarWinData.YKSC) + getYKJC(this.mCarWinData.YKJC) + getBSSC(this.mCarWinData.CAWDKGBSSC) + getJSJC(Neg(this.mCarWinData.CAWDKGJSJC)));
                return;
            default:
                return;
        }
    }

    private int getYKSC(int YKSC) {
        if (i2b(YKSC)) {
            return 128;
        }
        return 0;
    }

    private int getYKJC(int YKJC) {
        if (i2b(YKJC)) {
            return 64;
        }
        return 0;
    }

    private int getBSSC(int BSSC) {
        if (i2b(BSSC)) {
            return 8;
        }
        return 0;
    }

    private int getJSJC(int JSJC) {
        if (i2b(JSJC)) {
            return 4;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mCarWinData = new CanDataInfo.BYDS6S7CarWindowSetData();
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mItemTitleIds = new int[]{R.string.can_carset_ykss, R.string.can_carset_ykjc, R.string.can_cawdkgbssc, R.string.can_cawdkgjsjc};
    }

    public void ResetData(boolean check) {
        CanJni.BYDS6S7GetCarWindowSetData(this.mCarWinData);
        Log.d("lq", "YKSC:" + this.mCarWinData.YKSC);
        Log.d("lq", "YKJC:" + this.mCarWinData.YKJC);
        Log.d("lq", "CAWDKGBSSC:" + this.mCarWinData.CAWDKGBSSC);
        if (!i2b(this.mCarWinData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarWinData.Update)) {
            this.mCarWinData.Update = 0;
            Log.d("lq", "YKSC1:" + this.mCarWinData.YKSC);
            Log.d("lq", "YKJC1:" + this.mCarWinData.YKJC);
            Log.d("lq", "CAWDKGBSSC1:" + this.mCarWinData.CAWDKGBSSC);
            updateItem(new int[]{this.mCarWinData.YKSC, this.mCarWinData.YKJC, this.mCarWinData.CAWDKGBSSC, this.mCarWinData.CAWDKGJSJC});
        }
    }

    public void QueryData() {
    }
}
