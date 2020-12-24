package com.ts.can.psa.wc;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanPSAWCCarSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2 {
    public static final int ITEM_DDHBXG = 29;
    public static final int ITEM_HBXZDKQ = 30;
    public static final int ITEM_ZTYSSZ = 20;
    CanDataInfo.PeugAdt mAdtData = new CanDataInfo.PeugAdt();
    private CanDataInfo.PsaWcLang mPsaWcLang = new CanDataInfo.PsaWcLang();
    private CanDataInfo.PsaWcSet1 mPsaWcSet1 = new CanDataInfo.PsaWcSet1();
    private CanDataInfo.PsaWcSet1Adt mPsaWcSet1Adt = new CanDataInfo.PsaWcSet1Adt();
    private CanDataInfo.PsaWcSet2 mPsaWcSet2 = new CanDataInfo.PsaWcSet2();
    private CanDataInfo.PsaWcSet2Adt mPsaWcSet2Adt = new CanDataInfo.PsaWcSet2Adt();
    CanDataInfo.PeugSet mSetData = new CanDataInfo.PeugSet();

    public CanPSAWCCarSetView(Activity activity) {
        super(activity, 31);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.PsaWcCarSet1(9, item);
                return;
            case 9:
                CanJni.PsaWcCarSet1(4, item);
                return;
            case 10:
                CanJni.PsaWcCarSet1(6, item);
                return;
            case 20:
                if (CanJni.GetSubType() == 29) {
                    CanJni.PsaWcCarSet2(19, item);
                    return;
                } else {
                    CanJni.PsaWcCarSet2(9, item);
                    return;
                }
            case 21:
                CanJni.PsaWcCarSet2(10, item);
                return;
            case 22:
                CanJni.PsaWcCarSet2(11, item);
                return;
            case 23:
                CanJni.PsaWcCarSet2(12, item);
                return;
            case 24:
                CanJni.PsaWcCarSet2(13, item);
                return;
            case 25:
                CanJni.PsaWcCarSet2(14, item);
                return;
            case 28:
                int para1 = 0;
                int para2 = 0;
                if (item == 1) {
                    para1 = 1;
                    para2 = 2;
                } else if (item == 2) {
                    para1 = 1;
                    para2 = 3;
                } else if (item == 3) {
                    para1 = 1;
                    para2 = 4;
                } else if (item == 4) {
                    para1 = 1;
                    para2 = 6;
                } else if (item == 5) {
                    para1 = 1;
                    para2 = 7;
                } else if (item == 6) {
                    para1 = 2;
                    para2 = 3;
                } else if (item == 7) {
                    para1 = 2;
                    para2 = 4;
                } else if (item == 8) {
                    para1 = 2;
                    para2 = 6;
                } else if (item == 9) {
                    para1 = 2;
                    para2 = 7;
                } else if (item == 10) {
                    para1 = 3;
                    para2 = 4;
                } else if (item == 11) {
                    para1 = 3;
                    para2 = 6;
                } else if (item == 12) {
                    para1 = 3;
                    para2 = 7;
                } else if (item == 13) {
                    para1 = 4;
                    para2 = 6;
                } else if (item == 14) {
                    para1 = 4;
                    para2 = 7;
                } else if (item == 15) {
                    para1 = 6;
                    para2 = 7;
                }
                CanJni.PsaWcCarSet2(17, para1);
                Sleep(20);
                CanJni.PsaWcCarSet2(18, para2);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 4) {
            CanJni.PsaWcCarSet1(10, pos | 128);
        } else if (id == 27) {
            CanJni.PsaWcCarSet2(16, pos);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.PsaWcCarSet1(8, Neg(this.mPsaWcSet1.Zdzc));
                return;
            case 1:
                CanJni.PsaWcCarSet1(11, Neg(this.mPsaWcSet1.Dcldkg));
                return;
            case 3:
                CanJni.PsaWcCarSet1(10, (Neg(this.mPsaWcSet1.Fwzm) << 7) | this.mPsaWcSet1.FwzmVal);
                return;
            case 5:
                CanJni.PsaWcCarSet1(1, Neg(this.mPsaWcSet1.Dczdhys));
                return;
            case 6:
                CanJni.PsaWcCarSet1(2, Neg(this.mPsaWcSet1.Zcfz));
                return;
            case 7:
                CanJni.PsaWcCarSet1(12, Neg(this.mPsaWcSet1.Cmzdsd));
                return;
            case 8:
                CanJni.PsaWcCarSet1(13, Neg(this.mPsaWcSet1.Cmss));
                return;
            case 11:
                CanJni.PsaWcCarSet1(5, Neg(this.mPsaWcSet1.Rjxcd));
                return;
            case 12:
                CanJni.PsaWcCarSet2(1, Neg(this.mPsaWcSet2.Jxlxjsszml));
                return;
            case 13:
                CanJni.PsaWcCarSet2(2, Neg(this.mPsaWcSet2.Sdzxddszml));
                return;
            case 14:
                new CanItemMsgBox(14, getActivity(), R.string.can_sure_tybd, this, this);
                return;
            case 15:
                CanJni.PsaWcCarSet2(4, Neg(this.mPsaWcSet2.Bdfz));
                return;
            case 16:
                CanJni.PsaWcCarSet2(5, Neg(this.mPsaWcSet2.Ybgn));
                return;
            case 17:
                CanJni.PsaWcCarSet2(6, Neg(this.mPsaWcSet2.Cdbcfz));
                return;
            case 18:
                CanJni.PsaWcCarSet2(7, Neg(this.mPsaWcSet2.Pljcxt));
                return;
            case 19:
                CanJni.PsaWcCarSet2(8, Neg(this.mPsaWcSet2.Xsts));
                return;
            case 26:
                new CanItemMsgBox(26, getActivity(), R.string.can_teramont_reset_notice, this, this);
                return;
            case 29:
                CanJni.PsaWcCarSet2(20, Neg(this.mPsaWcSet2.Ddhbxg));
                return;
            case 30:
                CanJni.PsaWcCarSet2(21, Neg(this.mPsaWcSet2.Hbxzdkq));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        Log.d("__lh__", "InitData");
        this.mItemTitleIds = new int[]{R.string.can_psa_wc_zdzc, R.string.can_psa_wc_dcld, R.string.can_ybzm, R.string.can_env_light, R.string.can_fwdlddj, R.string.can_hys, R.string.can_car_zcfz, R.string.can_psa_wc_cmzdsd, R.string.can_psa_wc_cmss, R.string.can_psa_wc_cmjs, R.string.can_bwhj_light, R.string.can_rjxcd, R.string.can_psa_wc_xlxjssz, R.string.can_psa_wc_sdzxddsz, R.string.can_tybd, R.string.can_psa_wc_bdfz, R.string.can_yb_func, R.string.can_psa_wc_cdbcfz, R.string.can_psa_wc_pljcxt, R.string.can_psa_wc_xsts, R.string.can_psa_wc_ztyssz, R.string.can_psa_wc_fwmsxz, R.string.can_psa_wc_jsms, R.string.can_psa_wc_lzjhq, R.string.can_psa_wc_xxlx, R.string.can_psa_wc_xxnd, R.string.can_psa_wc_cshms, R.string.can_psa_wc_pmldsz, R.string.can_psa_2017_4008_gxhybsz, R.string.can_ddhbxg, R.string.can_hbxzdkq};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[2] = new int[]{R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_60s};
        this.mPopValueIds[9] = new int[]{R.string.can_psa_wc_symjs, R.string.can_psa_wc_jsmjs};
        this.mPopValueIds[10] = new int[]{R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_60s};
        if (CanJni.GetSubType() == 29) {
            this.mPopValueIds[20] = new int[]{R.string.can_psa_2017_4008_ybbjys_3, R.string.can_psa_2017_4008_ybbjys_4, R.string.can_psa_2017_4008_ybbjys_5};
        } else {
            this.mPopValueIds[20] = new int[]{R.array.can_psa_wc_ztyssz_array};
        }
        this.mPopValueIds[21] = new int[]{R.array.can_psa_wc_fwmsxz_array};
        this.mPopValueIds[22] = new int[]{R.array.can_psa_wc_jsms_array};
        this.mPopValueIds[23] = new int[]{R.array.can_psa_wc_lzjhq_array};
        this.mPopValueIds[24] = new int[]{R.array.can_psa_wc_xxlx_array};
        this.mPopValueIds[25] = new int[]{R.array.can_psa_wc_xxnd_array};
        this.mPopValueIds[28] = new int[]{R.array.can_psa_wc_ybxssz_array};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 6;
        iArr2[2] = 1;
        iArr2[3] = 1;
        iArr[4] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 15;
        iArr4[2] = 1;
        iArr4[3] = 1;
        iArr3[27] = iArr4;
    }

    public void ResetData(boolean check) {
        CanJni.PsaWcGetSet1(this.mPsaWcSet1);
        CanJni.PsaWcGetSet2(this.mPsaWcSet2);
        CanJni.PsaWcGetLang(this.mPsaWcLang);
        if (i2b(this.mPsaWcSet1.UpdateOnce) && (!check || i2b(this.mPsaWcSet1.Update))) {
            this.mPsaWcSet1.Update = 0;
            updateItem(0, this.mPsaWcSet1.Zdzc);
            updateItem(1, this.mPsaWcSet1.Dcldkg);
            updateItem(2, this.mPsaWcSet1.Ybzm);
            updateItem(5, this.mPsaWcSet1.Dczdhys);
            updateItem(6, this.mPsaWcSet1.Zcfz);
            updateItem(7, this.mPsaWcSet1.Cmzdsd);
            updateItem(8, this.mPsaWcSet1.Cmss);
            updateItem(9, this.mPsaWcSet1.Cmjs);
            updateItem(11, this.mPsaWcSet1.Rjxcd);
            updateItem(10, this.mPsaWcSet1.Bwhjzm);
            updateItem(3, this.mPsaWcSet1.Fwzm);
            if (this.mPsaWcSet1.Fwzm == 0) {
                showItem(4, 0);
            } else {
                showItem(4, 1);
                updateItem(4, this.mPsaWcSet1.FwzmVal, new StringBuilder(String.valueOf(this.mPsaWcSet1.FwzmVal)).toString());
            }
        }
        if (i2b(this.mPsaWcSet2.UpdateOnce) && (!check || i2b(this.mPsaWcSet2.Update))) {
            updateItem(12, this.mPsaWcSet2.Jxlxjsszml);
            updateItem(13, this.mPsaWcSet2.Sdzxddszml);
            updateItem(15, this.mPsaWcSet2.Bdfz);
            updateItem(16, this.mPsaWcSet2.Ybgn);
            updateItem(17, this.mPsaWcSet2.Cdbcfz);
            updateItem(18, this.mPsaWcSet2.Pljcxt);
            updateItem(19, this.mPsaWcSet2.Xsts);
            updateItem(20, this.mPsaWcSet2.Ztyssz);
            updateItem(21, this.mPsaWcSet2.Fwms);
            updateItem(22, this.mPsaWcSet2.Jsms);
            updateItem(23, this.mPsaWcSet2.Lzjhq);
            updateItem(24, this.mPsaWcSet2.Xxlx);
            updateItem(25, this.mPsaWcSet2.XxVal);
            updateItem(27, this.mPsaWcSet2.Pmldsz, new StringBuilder(String.valueOf(this.mPsaWcSet2.Pmldsz)).toString());
            updateItem(29, this.mPsaWcSet2.Ddhbxg);
            updateItem(30, this.mPsaWcSet2.Hbxzdkq);
        }
        CanJni.PsaWcGetSet1Adt(this.mPsaWcSet1Adt);
        CanJni.PsaWcGetSet2Adt(this.mPsaWcSet2Adt);
        if (!i2b(this.mPsaWcSet1Adt.UpdateOnce) && !i2b(this.mPsaWcSet2Adt.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPsaWcSet1Adt.Update) || i2b(this.mPsaWcSet2Adt.Update)) {
            Log.d("__lh__", "update = " + this.mPsaWcSet2Adt.Update);
            this.mPsaWcSet1Adt.Update = 0;
            this.mPsaWcSet2Adt.Update = 0;
            showItems();
        }
    }

    /* access modifiers changed from: package-private */
    public void showItems() {
        for (int i = 0; i < this.mItemTitleIds.length; i++) {
            showItem(i, IsHaveItem(i));
        }
    }

    /* access modifiers changed from: protected */
    public int IsHaveItem(int item) {
        int GetSubType = CanJni.GetSubType();
        switch (item) {
            case 0:
                if (this.mPsaWcSet1Adt.Zdzc == 1) {
                    return 1;
                }
                return 0;
            case 1:
                if (this.mPsaWcSet1Adt.Dcldkg == 1) {
                    return 1;
                }
                return 0;
            case 2:
                if (this.mPsaWcSet1Adt.Ybzm == 1) {
                    return 1;
                }
                return 0;
            case 3:
                if (this.mPsaWcSet1Adt.Fwzm == 1) {
                    return 1;
                }
                return 0;
            case 4:
                if (this.mPsaWcSet1Adt.Fwzm != 1 || this.mPsaWcSet1.Fwzm <= 0) {
                    return 0;
                }
                return 1;
            case 5:
                if (this.mPsaWcSet1Adt.Dczdhys == 1) {
                    return 1;
                }
                return 0;
            case 6:
                if (this.mPsaWcSet1Adt.Zcfz == 1) {
                    return 1;
                }
                return 0;
            case 7:
                if (this.mPsaWcSet1Adt.Cmzdsd == 1) {
                    return 1;
                }
                return 0;
            case 8:
                if (this.mPsaWcSet1Adt.Cmss == 1) {
                    return 1;
                }
                return 0;
            case 9:
                if (this.mPsaWcSet1Adt.Cmjs == 1) {
                    return 1;
                }
                return 0;
            case 10:
                if (this.mPsaWcSet1Adt.Bwhjzm == 1) {
                    return 1;
                }
                return 0;
            case 11:
                if (this.mPsaWcSet1Adt.Rjxcd == 1) {
                    return 1;
                }
                return 0;
            case 12:
                if (this.mPsaWcSet2Adt.Jxlxjsszml == 1) {
                    return 1;
                }
                return 0;
            case 13:
                if (this.mPsaWcSet2Adt.Sdzxddszml == 1) {
                    return 1;
                }
                return 0;
            case 14:
                return 1;
            case 15:
                if (this.mPsaWcSet2Adt.Bdfz == 1) {
                    return 1;
                }
                return 0;
            case 16:
                if (this.mPsaWcSet2Adt.Ybgn == 1) {
                    return 1;
                }
                return 0;
            case 17:
                if (this.mPsaWcSet2Adt.Cdbcfz == 1) {
                    return 1;
                }
                return 0;
            case 18:
                if (this.mPsaWcSet2Adt.Pljcxt == 1) {
                    return 1;
                }
                return 0;
            case 19:
                if (this.mPsaWcSet2Adt.Xsts == 1) {
                    return 1;
                }
                return 0;
            case 20:
                if (this.mPsaWcSet2Adt.Ztyssz == 1) {
                    return 1;
                }
                return 0;
            case 21:
                if (this.mPsaWcSet2Adt.Fwdmsxz == 1) {
                    return 1;
                }
                return 0;
            case 22:
                if (this.mPsaWcSet2Adt.Jsms == 1) {
                    return 1;
                }
                return 0;
            case 23:
                if (this.mPsaWcSet2Adt.Lzjhq == 1) {
                    return 1;
                }
                return 0;
            case 24:
                if (this.mPsaWcSet2Adt.Xxsz == 1) {
                    return 1;
                }
                return 0;
            case 25:
                if (this.mPsaWcSet2Adt.Xxsz == 1) {
                    return 1;
                }
                return 0;
            case 26:
                return 1;
            case 27:
                if (this.mPsaWcSet2Adt.Pmldsz == 1) {
                    return 1;
                }
                return 0;
            case 28:
                return 1;
            case 29:
                if (this.mPsaWcSet2Adt.Ddhbxg == 1) {
                    return 1;
                }
                return 0;
            case 30:
                if (this.mPsaWcSet2Adt.Hbxzdkq == 1) {
                    return 1;
                }
                return 0;
            default:
                return 0;
        }
    }

    public void QueryData() {
    }

    public void onCancel(int param) {
        if (param == 14) {
            CanJni.PSACarSet(3, 0);
        } else if (param == 26) {
            CanJni.PsaWcCarSet2(15, 0);
        }
    }

    public void onOK(int param) {
        if (param == 14) {
            CanJni.PsaWcCarSet2(3, 1);
        } else if (param == 26) {
            CanJni.PsaWcCarSet2(15, 1);
        }
    }
}
