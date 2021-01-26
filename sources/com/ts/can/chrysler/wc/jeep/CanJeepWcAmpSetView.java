package com.ts.can.chrysler.wc.jeep;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanFunc;
import com.ts.can.CanScrollCarInfoView;

public class CanJeepWcAmpSetView extends CanScrollCarInfoView {
    public static final int ITEM_BAL = 1;
    public static final int ITEM_BAS = 3;
    public static final int ITEM_FAD = 2;
    public static final int ITEM_MAX = 6;
    public static final int ITEM_MID = 4;
    public static final int ITEM_TRE = 5;
    public static final int ITEM_VOL = 0;
    protected static final String TAG = "CanJeepWcAmpSetView";
    private CanDataInfo.JeepWcAudioInfo mAmpData;
    private CanDataInfo.JeepWcAudioInfo mAmpDatab;

    public CanJeepWcAmpSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.JeepWcAmpSet(1, pos);
                return;
            case 1:
                CanJni.JeepWcAmpSet(2, pos);
                return;
            case 2:
                CanJni.JeepWcAmpSet(3, pos);
                return;
            case 3:
                CanJni.JeepWcAmpSet(4, pos);
                return;
            case 4:
                CanJni.JeepWcAmpSet(5, pos);
                return;
            case 5:
                CanJni.JeepWcAmpSet(6, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS};
        int[] attr = new int[4];
        attr[0] = 1;
        attr[1] = 19;
        attr[2] = 1;
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 38;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        this.mProgressAttrs[2] = attr;
        this.mProgressAttrs[1] = attr;
        this.mProgressAttrs[3] = attr;
        this.mProgressAttrs[4] = attr;
        this.mProgressAttrs[5] = attr;
        this.mAmpData = new CanDataInfo.JeepWcAudioInfo();
        this.mAmpDatab = new CanDataInfo.JeepWcAudioInfo();
    }

    public void ResetData(boolean check) {
        CanJni.JeepWcGetAmpInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vol);
            updateItem(2, this.mAmpData.Fad, setValText(this.mAmpData.Fad, 2));
            updateItem(1, this.mAmpData.Bal, setValText(this.mAmpData.Bal, 1));
            updateItem(3, this.mAmpData.Bas, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bas - 10)}));
            updateItem(4, this.mAmpData.Mid, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Mid - 10)}));
            updateItem(5, this.mAmpData.Tre, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Tre - 10)}));
            if (this.mAmpData.Vol != this.mAmpDatab.Vol || this.mAmpData.Fad != this.mAmpDatab.Fad || this.mAmpData.Bal != this.mAmpDatab.Bal || this.mAmpData.Bas != this.mAmpDatab.Bas || this.mAmpData.Mid != this.mAmpDatab.Mid || this.mAmpData.Tre != this.mAmpDatab.Tre) {
                this.mAmpDatab.Vol = this.mAmpData.Vol;
                this.mAmpDatab.Fad = this.mAmpData.Fad;
                this.mAmpDatab.Bal = this.mAmpData.Bal;
                this.mAmpDatab.Bas = this.mAmpData.Bas;
                this.mAmpDatab.Mid = this.mAmpData.Mid;
                this.mAmpDatab.Tre = this.mAmpData.Tre;
                Log.d(TAG, "mAmpData UPT");
                byte[] fileMsg = new byte[8];
                fileMsg[0] = 85;
                fileMsg[1] = (byte) this.mAmpData.Vol;
                fileMsg[2] = (byte) this.mAmpData.Fad;
                fileMsg[3] = (byte) this.mAmpData.Bal;
                fileMsg[4] = (byte) this.mAmpData.Bas;
                fileMsg[5] = (byte) this.mAmpData.Mid;
                fileMsg[6] = (byte) this.mAmpData.Tre;
                CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Amp_Set_fileName);
            }
        }
    }

    private String setValText(int val, int sta) {
        switch (sta) {
            case 1:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "L" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "R" + String.valueOf(val - 10);
                }
                break;
            case 2:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "F" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "R" + String.valueOf(val - 10);
                }
                break;
        }
        return "0";
    }

    public void QueryData() {
    }

    public void doOnResume() {
        super.doOnResume();
        ResetData(false);
        Log.d(TAG, "doOnResume");
    }

    public static void Init() {
        byte[] fileMsg = new byte[8];
        CanFunc.GetFileData(CanFunc.Can_Amp_Set_fileName, fileMsg);
        if (fileMsg[0] == 85) {
            Log.d(TAG, "mAmpData Init");
            CanJni.JeepWcAmpSet(1, fileMsg[1]);
            CanJni.JeepWcAmpSet(2, fileMsg[2]);
            CanJni.JeepWcAmpSet(3, fileMsg[3]);
            CanJni.JeepWcAmpSet(4, fileMsg[4]);
            CanJni.JeepWcAmpSet(5, fileMsg[5]);
            CanJni.JeepWcAmpSet(6, fileMsg[6]);
        }
    }
}
