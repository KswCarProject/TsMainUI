package com.ts.can.byd.rsw;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.txznet.sdk.TXZResourceManager;

public class CanBydRswPm25SetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    protected static final int ITEM_CNJC = 1;
    protected static final int ITEM_CWJC = 2;
    protected static final int ITEM_DSJC = 5;
    protected static final int ITEM_INLEV = 13;
    protected static final int ITEM_INVAL = 10;
    protected static final int ITEM_JSZT = 14;
    protected static final int ITEM_KCJC = 7;
    protected static final int ITEM_KGMJC = 4;
    private static final int ITEM_MAX = 15;
    private static final int ITEM_MIN = 1;
    protected static final int ITEM_OUTLEV = 12;
    protected static final int ITEM_OUTVAL = 11;
    protected static final int ITEM_SDJC = 3;
    protected static final int ITEM_SET_TITLE = 0;
    protected static final int ITEM_STA_TITLE = 6;
    protected static final int ITEM_SZCGBJ = 8;
    protected static final int ITEM_ZXSBZW = 9;
    private static Toast mToast;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private CanDataInfo.BydRsw_Pm25 m_SetData;

    public CanBydRswPm25SetView(Activity activity) {
        super(activity, 15);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.BydRswPm25Set(1, Neg(this.m_SetData.Cnjc));
                return;
            case 2:
                CanJni.BydRswPm25Set(2, Neg(this.m_SetData.Cwjc));
                return;
            case 3:
                CanJni.BydRswPm25Set(3, Neg(this.m_SetData.Sdjc));
                return;
            case 4:
                CanJni.BydRswPm25Set(4, Neg(this.m_SetData.Kmjc));
                return;
            case 5:
                CanJni.BydRswPm25Set(5, Neg(this.m_SetData.Dsjc));
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_base_setup, R.string.can_pm25_cnjc, R.string.can_pm25_cwjc, R.string.can_pm25_sdjc, R.string.can_pm25_kmjc, R.string.can_pm25_dsjc, R.string.can_status, R.string.can_pm25_kcjc, R.string.can_pm25_szcgbj, R.string.can_pm25_zxsbzw, R.string.can_pm25_cnsj, R.string.can_pm25_cwsj, R.string.can_pm25_cwdj, R.string.can_pm25_cndj, R.string.can_pm25_jszt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mPopValueIds[7] = new int[]{R.string.can_off, R.string.can_on};
        this.mPopValueIds[8] = new int[]{R.string.can_oil_status_normal, R.string.can_baojing};
        this.mPopValueIds[9] = new int[]{R.string.can_tripbresettiming_wc_1, R.string.can_pm25_carin, R.string.can_pm25_carout};
        this.mPopValueIds[14] = new int[]{R.string.can_cch9_headlight_mode_key2, R.string.can_pm25_carincb, R.string.can_pm25_caroutcb};
        this.m_SetData = new CanDataInfo.BydRsw_Pm25();
    }

    public void ResetData(boolean check) {
        CanJni.BydRswGetPm25Data(this.m_SetData);
        if (i2b(this.m_SetData.UpdateOnce) && (!check || i2b(this.m_SetData.Update))) {
            this.m_SetData.Update = 0;
            updateItem(1, this.m_SetData.Cnjc);
            updateItem(2, this.m_SetData.Cwjc);
            updateItem(3, this.m_SetData.Sdjc);
            updateItem(4, this.m_SetData.Kmjc);
            updateItem(5, this.m_SetData.Dsjc);
            updateItem(7, 0, getString(this.mPopValueIds[7][this.m_SetData.Kcjc]));
            updateItem(8, 0, getString(this.mPopValueIds[8][this.m_SetData.Szcgbj]));
            updateItem(9, 0, getString(this.mPopValueIds[9][this.m_SetData.Zxsbzw]));
            if (this.m_SetData.InVal == 4095) {
                updateItem(10, 0, "--");
            } else {
                updateItem(10, 0, String.format("%d ug/m3", new Object[]{Integer.valueOf(this.m_SetData.InVal)}));
            }
            if (this.m_SetData.OutVal == 4095) {
                updateItem(11, 0, "--");
            } else {
                updateItem(11, 0, String.format("%d ug/m3", new Object[]{Integer.valueOf(this.m_SetData.OutVal)}));
            }
            updateItem(12, 0, getStringArray(R.array.can_s6s7_pm25_arrays)[this.m_SetData.OutLev]);
            updateItem(13, 0, getStringArray(R.array.can_s6s7_pm25_arrays)[this.m_SetData.InLev]);
            updateItem(14, 0, getString(this.mPopValueIds[14][this.m_SetData.Jszt]));
        }
        if (mfgFinish) {
            getActivity().finish();
        }
    }

    public static void DealDevEvent() {
        if (mfgShow) {
            mfgFinish = true;
        } else {
            CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
        }
    }

    public static void ShowWarnToast(Context context) {
        CanDataInfo.BydRsw_Pm25 setData = new CanDataInfo.BydRsw_Pm25();
        CanJni.BydRswGetPm25Data(setData);
        if (setData.Jszt != 0) {
            String text = context.getString(R.string.can_pm25_jhts);
            if (mToast == null) {
                mToast = Toast.makeText(context, TXZResourceManager.STYLE_DEFAULT, 1);
            }
            mToast.setText(text);
            mToast.show();
        }
    }

    public void doOnPause() {
        super.doOnPause();
        mfgShow = false;
        mfgFinish = false;
    }

    public void doOnResume() {
        super.doOnResume();
        mfgShow = true;
        mfgFinish = false;
    }

    public void QueryData() {
        CanJni.BydRswQuery(42, 0, 0, 0);
    }
}
