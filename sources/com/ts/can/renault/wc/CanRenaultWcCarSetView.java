package com.ts.can.renault.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;

public class CanRenaultWcCarSetView extends CanScrollCarInfoView {
    protected static final int ITEM_BCSYKG = 24;
    protected static final int ITEM_BCSYLX = 25;
    protected static final int ITEM_BCYL = 26;
    protected static final int ITEM_CLIMATE = 10;
    protected static final int ITEM_DISSTYLE = 7;
    protected static final int ITEM_FWDHB = 4;
    protected static final int ITEM_FWDKG = 5;
    protected static final int ITEM_FWDQB = 3;
    protected static final int ITEM_FWDQD = 1;
    protected static final int ITEM_FWDYB = 2;
    protected static final int ITEM_FWDYS = 0;
    protected static final int ITEM_HBCCGQ = 27;
    protected static final int ITEM_HSYXKG = 28;
    protected static final int ITEM_JSFZPLJC = 15;
    protected static final int ITEM_JSYSTS = 22;
    protected static final int ITEM_MAX = 30;
    protected static final int ITEM_MS = 6;
    protected static final int ITEM_MTAJGN = 18;
    protected static final int ITEM_NBHYSY = 16;
    protected static final int ITEM_POWERTRAIN = 9;
    protected static final int ITEM_STEERING = 8;
    protected static final int ITEM_TSYL = 19;
    protected static final int ITEM_TYFW = 29;
    protected static final int ITEM_WBHY = 20;
    protected static final int ITEM_XSZDCMSD = 17;
    protected static final int ITEM_YBPLD = 23;
    protected static final int ITEM_ZDNBD = 21;
    protected static final int ITEM_ZYAMKG = 14;
    protected static final int ITEM_ZYAMMS = 11;
    protected static final int ITEM_ZYAMQD = 12;
    protected static final int ITEM_ZYAMSD = 13;
    private static String[] mBcsylx;
    private static String[] mClimate;
    private static String[] mDisplayStyle;
    private static String[] mMultiSense;
    private static String[] mPowertrain;
    private static String[] mSteering;
    private static String[] mTsyl;
    private static String[] mZyamms;
    private CanDataInfo.RenaulWcCarSetData mSetData;

    public CanRenaultWcCarSetView(Activity activity) {
        super(activity, 30);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                carSet(1, item);
                return;
            case 6:
                carSet(7, item);
                return;
            case 7:
                carSet(8, item);
                return;
            case 8:
                carSet(9, item);
                return;
            case 9:
                carSet(10, item);
                return;
            case 10:
                carSet(11, item + 2);
                return;
            case 11:
                carSet(13, item);
                return;
            case 19:
                carSet(20, item);
                return;
            case 25:
                carSet(27, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                carSet(2, pos);
                return;
            case 12:
                carSet(14, pos);
                return;
            case 13:
                carSet(15, pos);
                return;
            case 23:
                carSet(24, pos);
                return;
            case 26:
                carSet(28, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 2:
                carSet(3, Neg(this.mSetData.Fwdyb));
                return;
            case 3:
                carSet(4, Neg(this.mSetData.Fwdqb));
                return;
            case 4:
                carSet(5, Neg(this.mSetData.Fwdhb));
                return;
            case 5:
                carSet(6, Neg(this.mSetData.Fwdkg));
                return;
            case 14:
                carSet(12, Neg(this.mSetData.Zyamkg));
                return;
            case 15:
                carSet(16, Neg(this.mSetData.Jsfzpljc));
                return;
            case 16:
                carSet(17, Neg(this.mSetData.Nbhysy));
                return;
            case 17:
                carSet(18, Neg(this.mSetData.Xszdcmsd));
                return;
            case 18:
                carSet(19, Neg(this.mSetData.Mtajgn));
                return;
            case 20:
                carSet(21, Neg(this.mSetData.Wbhy));
                return;
            case 21:
                carSet(22, Neg(this.mSetData.Zdnbd));
                return;
            case 22:
                carSet(23, Neg(this.mSetData.Jsysts));
                return;
            case 24:
                carSet(26, Neg(this.mSetData.Bcsykg));
                return;
            case 27:
                carSet(29, Neg(this.mSetData.Hbccgq));
                return;
            case 28:
                carSet(30, Neg(this.mSetData.Hsyxkg));
                return;
            case 29:
                new CanItemMsgBox(id, getActivity(), R.string.can_rw_rx5_hfccsz, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanRenaultWcCarSetView.this.carSet(25, 1);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void carSet(int cmd, int para) {
        CanJni.RenaultWcCarSet(cmd, para, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_fwd_color, R.string.can_fwd_qd, R.string.can_fwd_yb, R.string.can_fwd_qb, R.string.can_fwd_hb, R.string.can_fwd_kg, R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name, R.string.app_name, R.string.can_zyamms, R.string.can_zyamqd, R.string.can_zyamsd, R.string.can_zyamkg, R.string.can_psa_wc_pljcxt, R.string.can_nbhysy, R.string.can_zdcmsd, R.string.can_mtajgn, R.string.can_tsysy, R.string.can_wbhy, R.string.can_zdnbd, R.string.can_jsysts, R.string.can_ybpld, R.string.can_bcsykg, R.string.can_bcsylx, R.string.can_bcyl, R.string.can_hbccgq, R.string.can_hsyxkg, R.string.can_rw_rx5_tyfw};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.string.can_qingse, R.string.can_color_red, R.string.can_color_blue, R.string.can_purple, R.string.can_brown, R.string.can_color_light_blue, R.string.can_color_yellow, R.string.can_orange_color};
        this.mPopValueIds[6] = new int[]{R.string.app_name, R.string.app_name};
        this.mPopValueIds[7] = new int[]{R.string.app_name, R.string.app_name};
        this.mPopValueIds[8] = new int[]{R.string.app_name, R.string.app_name};
        this.mPopValueIds[9] = new int[]{R.string.app_name, R.string.app_name};
        this.mPopValueIds[10] = new int[]{R.string.app_name, R.string.app_name};
        this.mPopValueIds[11] = new int[]{R.string.app_name, R.string.app_name};
        this.mPopValueIds[19] = new int[]{R.string.app_name, R.string.app_name};
        this.mPopValueIds[25] = new int[]{R.string.app_name, R.string.app_name};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 20;
        iArr2[2] = 1;
        iArr[1] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 5;
        iArr4[2] = 1;
        iArr3[12] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 5;
        iArr6[2] = 1;
        iArr5[13] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[1] = 20;
        iArr8[2] = 1;
        iArr7[23] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[1] = 31;
        iArr10[2] = 1;
        iArr9[26] = iArr10;
        this.mSetData = new CanDataInfo.RenaulWcCarSetData();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        mMultiSense = new String[]{"Sport", "Comfort", "Neutral", "Eco", "Perso"};
        mDisplayStyle = new String[]{"Style1", "Style2", "Style3", "Style4"};
        mSteering = new String[]{"Sport", "Comfort", "Neutral"};
        mPowertrain = new String[]{"Sport", "Neutral", "Eco"};
        mClimate = new String[]{"Neutral", "Eco"};
        mZyamms = new String[]{"Relaxing", "Lumbar", "Tonic"};
        mTsyl = new String[]{String.valueOf(getString(R.string.can_vol)) + "1", String.valueOf(getString(R.string.can_vol)) + "2", String.valueOf(getString(R.string.can_vol)) + "3"};
        mBcsylx = new String[]{"Type1", "Type2", "Type3"};
        getScrollManager().RemoveView(6);
        this.mItemObjects[6] = getScrollManager().addItemPopupList(this.mItemTitleIds[6], mMultiSense, 6, this, 6);
        ((CanItemPopupList) this.mItemObjects[6]).GetBtn().setText("Multi-Sense");
        getScrollManager().RemoveView(7);
        this.mItemObjects[7] = getScrollManager().addItemPopupList(this.mItemTitleIds[7], mDisplayStyle, 7, this, 7);
        ((CanItemPopupList) this.mItemObjects[7]).GetBtn().setText("Display Style");
        getScrollManager().RemoveView(8);
        this.mItemObjects[8] = getScrollManager().addItemPopupList(this.mItemTitleIds[8], mSteering, 8, this, 8);
        ((CanItemPopupList) this.mItemObjects[8]).GetBtn().setText("Steering");
        getScrollManager().RemoveView(9);
        this.mItemObjects[9] = getScrollManager().addItemPopupList(this.mItemTitleIds[9], mPowertrain, 9, this, 9);
        ((CanItemPopupList) this.mItemObjects[9]).GetBtn().setText("Powertrain");
        getScrollManager().RemoveView(10);
        this.mItemObjects[10] = getScrollManager().addItemPopupList(this.mItemTitleIds[10], mClimate, 10, this, 10);
        ((CanItemPopupList) this.mItemObjects[10]).GetBtn().setText("Climate");
        getScrollManager().RemoveView(11);
        this.mItemObjects[11] = getScrollManager().addItemPopupList(this.mItemTitleIds[11], mZyamms, 11, this, 11);
        getScrollManager().RemoveView(19);
        this.mItemObjects[19] = getScrollManager().addItemPopupList(this.mItemTitleIds[19], mTsyl, 19, this, 19);
        getScrollManager().RemoveView(25);
        this.mItemObjects[25] = getScrollManager().addItemPopupList(this.mItemTitleIds[25], mBcsylx, 25, this, 25);
    }

    public void ResetData(boolean check) {
        CanJni.RenaultWcGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Fwdys);
            updateItem(1, this.mSetData.Fwdqd, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.Fwdqd * 5)}));
            updateItem(2, this.mSetData.Fwdyb);
            updateItem(3, this.mSetData.Fwdqb);
            updateItem(4, this.mSetData.Fwdhb);
            updateItem(5, this.mSetData.Fwdkg);
            updateItem(6, this.mSetData.Multi_Sense);
            updateItem(7, this.mSetData.Display_Style);
            updateItem(8, this.mSetData.Steering);
            updateItem(9, this.mSetData.Powertrain);
            updateItem(10, this.mSetData.Climate - 2);
            updateItem(11, this.mSetData.Zyamms);
            updateItem(12, this.mSetData.Zyamqd, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.Zyamqd)}));
            updateItem(13, this.mSetData.Zyamsd, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.Zyamsd)}));
            updateItem(14, this.mSetData.Zyamkg);
            updateItem(15, this.mSetData.Jsfzpljc);
            updateItem(16, this.mSetData.Nbhysy);
            updateItem(17, this.mSetData.Xszdcmsd);
            updateItem(18, this.mSetData.Mtajgn);
            updateItem(19, this.mSetData.Tsyl);
            updateItem(20, this.mSetData.Wbhy);
            updateItem(21, this.mSetData.Zdnbd);
            updateItem(22, this.mSetData.Jsysts);
            updateItem(23, this.mSetData.Ybpld, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.Ybpld * 5)}));
            updateItem(24, this.mSetData.Bcsykg);
            updateItem(25, this.mSetData.Bcsylx);
            updateItem(26, this.mSetData.Bcyl, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.Bcyl)}));
            updateItem(27, this.mSetData.Hbccgq);
            updateItem(28, this.mSetData.Hsyxkg);
        }
    }

    public void QueryData() {
    }
}
