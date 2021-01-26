package com.ts.can.hyundai.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanNumInuptDlg;
import java.util.ArrayList;

public class CanHyundaiRzcYyszSetView extends CanScrollCarInfoView {
    private static final int ITEM_CDDL = 23;
    private static final int ITEM_CDDL_SZZCCDDLBXS = 27;
    private static final int ITEM_CDDL_SZZCCDDLCDZ = 26;
    private static final int ITEM_CDDL_YHSJCD = 25;
    private static final int ITEM_CDDL_YYCDSJ = 24;
    private static final int ITEM_CDDL_YYKTQCC = 29;
    private static final int ITEM_CDDL_YYKTWD = 28;
    private static final int ITEM_CDDL_YYKTWDZ = 30;
    private static final int ITEM_CDSZ1 = 8;
    private static final int ITEM_CDSZ1_KG = 9;
    private static final int ITEM_CDSZ1_SJSZH = 10;
    private static final int ITEM_CDSZ1_SJSZM = 11;
    private static final int ITEM_CDSZ1_XHRQ = 12;
    private static final int ITEM_CDSZ2 = 13;
    private static final int ITEM_CDSZ2_KG = 14;
    private static final int ITEM_CDSZ2_SJSZH = 15;
    private static final int ITEM_CDSZ2_SJSZM = 16;
    private static final int ITEM_CDSZ2_XHRQ = 17;
    private static final int ITEM_CDSZ_JSSJH = 21;
    private static final int ITEM_CDSZ_JSSJM = 22;
    private static final int ITEM_CDSZ_KSSJH = 19;
    private static final int ITEM_CDSZ_KSSJM = 20;
    private static final int ITEM_CDSZ_YYCDSJ = 18;
    private static final int ITEM_INIT = 0;
    private static final int ITEM_YYSJ = 2;
    private static final int ITEM_YYSJ_JSSJH = 5;
    private static final int ITEM_YYSJ_JSSJM = 6;
    private static final int ITEM_YYSJ_KSSJH = 3;
    private static final int ITEM_YYSJ_KSSJM = 4;
    private static final int ITEM_YYSJ_XHRQ = 7;
    private static final int ITEM_YYSZFS = 1;
    /* access modifiers changed from: private */
    public CanDataInfo.HyRzcXnySet mSetData;

    public CanHyundaiRzcYyszSetView(Activity activity) {
        super(activity, 31);
    }

    public void onPositiveItem(int id, int[] item) {
        switch (id) {
            case 7:
                int itemNum = 0;
                for (int xhrq : item) {
                    itemNum += getXhrq(xhrq);
                }
                CanJni.HyundaiRzcXnySet(2, (this.mSetData.Yyss_kssjM << 5) + this.mSetData.Yyss_kssjH, (this.mSetData.Yyss_jssjM << 5) + this.mSetData.Yyss_jssjH, itemNum);
                return;
            case 12:
                int itemNum2 = 0;
                for (int xhrq2 : item) {
                    itemNum2 += getXhrq(xhrq2);
                }
                CanJni.HyundaiRzcXnySet(5, (this.mSetData.Xcclcfsjsz_sjszM << 5) + this.mSetData.Xcclcfsjsz_sjszH, itemNum2 + (this.mSetData.Xcclcfsjsz_cf & 128), 0);
                return;
            case 17:
                int itemNum3 = 0;
                for (int xhrq3 : item) {
                    itemNum3 += getXhrq(xhrq3);
                }
                CanJni.HyundaiRzcXnySet(6, (this.mSetData.Xcclcfsjsz_sjszM2 << 5) + this.mSetData.Xcclcfsjsz_sjszH2, itemNum3 + (this.mSetData.Xcclcfsjsz_cf2 & 128), 0);
                return;
            default:
                return;
        }
    }

    private int getXhrq(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            default:
                return 0;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.HyundaiRzcXnySet(1, item + 1, 0, 0);
                return;
            case 25:
                CanJni.HyundaiRzcXnySet(4, (this.mSetData.Cddl_yycdsj << 1) + item, (this.mSetData.Yycdsj_kssjM << 5) + this.mSetData.Yycdsj_kssjH, (this.mSetData.Yycdsj_jssjM << 5) + this.mSetData.Yycdsj_jssjH);
                return;
            case 26:
                CanJni.HyundaiRzcXnySet(3, (item << 2) + this.mSetData.Cddl_Szzccddl_Bxs, 0, 0);
                return;
            case 27:
                CanJni.HyundaiRzcXnySet(3, (this.mSetData.Cddl_szzccddl_Cdz << 2) + item, 0, 0);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 30:
                CanJni.HyundaiRzcXnySet(7, (this.mSetData.YyKtwd << 7) + (this.mSetData.YyKtQcs << 6) + pos, 0, 0);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 0:
                CanJni.HyundaiRzcXnySet(0, 1, 0, 0);
                return;
            case 3:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            CanJni.HyundaiRzcXnySet(2, (CanHyundaiRzcYyszSetView.this.mSetData.Yyss_kssjM << 5) + num, (CanHyundaiRzcYyszSetView.this.mSetData.Yyss_jssjM << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yyss_jssjH, CanHyundaiRzcYyszSetView.this.mSetData.Yyss_cf);
                        }
                    }
                }, 2, id);
                return;
            case 4:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 5) {
                            CanJni.HyundaiRzcXnySet(2, (num << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yyss_kssjH, (CanHyundaiRzcYyszSetView.this.mSetData.Yyss_jssjM << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yyss_jssjH, CanHyundaiRzcYyszSetView.this.mSetData.Yyss_cf);
                        }
                    }
                }, 1, id);
                return;
            case 5:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            CanJni.HyundaiRzcXnySet(2, (CanHyundaiRzcYyszSetView.this.mSetData.Yyss_kssjM << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yyss_kssjH, (CanHyundaiRzcYyszSetView.this.mSetData.Yyss_jssjM << 5) + num, CanHyundaiRzcYyszSetView.this.mSetData.Yyss_cf);
                        }
                    }
                }, 2, id);
                return;
            case 6:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 5) {
                            CanJni.HyundaiRzcXnySet(2, (CanHyundaiRzcYyszSetView.this.mSetData.Yyss_kssjM << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yyss_kssjH, (num << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yyss_jssjH, CanHyundaiRzcYyszSetView.this.mSetData.Yyss_cf);
                        }
                    }
                }, 1, id);
                return;
            case 9:
                CanJni.HyundaiRzcXnySet(5, (this.mSetData.Xcclcfsjsz_sjszM << 5) + this.mSetData.Xcclcfsjsz_sjszH, (Neg(this.mSetData.Xcclcfsjsz_cf & 128) << 7) + (this.mSetData.Xcclcfsjsz_cf & 127), 0);
                return;
            case 10:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            CanJni.HyundaiRzcXnySet(5, (CanHyundaiRzcYyszSetView.this.mSetData.Xcclcfsjsz_sjszM << 5) + num, CanHyundaiRzcYyszSetView.this.mSetData.Xcclcfsjsz_cf, 0);
                        }
                    }
                }, 2, id);
                return;
            case 11:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 5) {
                            CanJni.HyundaiRzcXnySet(5, (num << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Xcclcfsjsz_sjszH, CanHyundaiRzcYyszSetView.this.mSetData.Xcclcfsjsz_cf, 0);
                        }
                    }
                }, 1, id);
                return;
            case 14:
                CanJni.HyundaiRzcXnySet(6, (this.mSetData.Xcclcfsjsz_sjszM2 << 5) + this.mSetData.Xcclcfsjsz_sjszH2, (Neg(this.mSetData.Xcclcfsjsz_cf2 & 128) << 7) + (this.mSetData.Xcclcfsjsz_cf2 & 127), 0);
                return;
            case 15:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            CanJni.HyundaiRzcXnySet(6, (CanHyundaiRzcYyszSetView.this.mSetData.Xcclcfsjsz_sjszM2 << 5) + num, CanHyundaiRzcYyszSetView.this.mSetData.Xcclcfsjsz_cf2, 0);
                        }
                    }
                }, 2, id);
                return;
            case 16:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 5) {
                            CanJni.HyundaiRzcXnySet(6, (num << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Xcclcfsjsz_sjszH2, CanHyundaiRzcYyszSetView.this.mSetData.Xcclcfsjsz_cf2, 0);
                        }
                    }
                }, 1, id);
                return;
            case 19:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            CanJni.HyundaiRzcXnySet(4, (CanHyundaiRzcYyszSetView.this.mSetData.Cddl_yycdsj << 1) + CanHyundaiRzcYyszSetView.this.mSetData.Cddl_Yhsjcd, (CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_kssjM << 5) + num, (CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_jssjM << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_jssjH);
                        }
                    }
                }, 2, id);
                return;
            case 20:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 5) {
                            CanJni.HyundaiRzcXnySet(4, (CanHyundaiRzcYyszSetView.this.mSetData.Cddl_yycdsj << 1) + CanHyundaiRzcYyszSetView.this.mSetData.Cddl_Yhsjcd, (num << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_kssjH, (CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_jssjM << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_jssjH);
                        }
                    }
                }, 1, id);
                return;
            case 21:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            CanJni.HyundaiRzcXnySet(4, (CanHyundaiRzcYyszSetView.this.mSetData.Cddl_yycdsj << 1) + CanHyundaiRzcYyszSetView.this.mSetData.Cddl_Yhsjcd, (CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_kssjM << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_kssjH, (CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_jssjM << 5) + num);
                        }
                    }
                }, 2, id);
                return;
            case 22:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 5) {
                            CanJni.HyundaiRzcXnySet(4, (CanHyundaiRzcYyszSetView.this.mSetData.Cddl_yycdsj << 1) + CanHyundaiRzcYyszSetView.this.mSetData.Cddl_Yhsjcd, (CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_kssjM << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_kssjH, (num << 5) + CanHyundaiRzcYyszSetView.this.mSetData.Yycdsj_jssjH);
                        }
                    }
                }, 1, id);
                return;
            case 24:
                CanJni.HyundaiRzcXnySet(4, (Neg(this.mSetData.Cddl_yycdsj) << 1) + this.mSetData.Cddl_Yhsjcd, (this.mSetData.Yycdsj_kssjM << 5) + this.mSetData.Yycdsj_kssjH, (this.mSetData.Yycdsj_jssjM << 5) + this.mSetData.Yycdsj_jssjH);
                return;
            case 28:
                CanJni.HyundaiRzcXnySet(7, (Neg(this.mSetData.YyKtwd) << 7) + (this.mSetData.YyKtQcs << 6) + this.mSetData.Yyktwd, 0, 0);
                return;
            case 29:
                CanJni.HyundaiRzcXnySet(7, (this.mSetData.YyKtwd << 7) + (Neg(this.mSetData.YyKtQcs) << 6) + this.mSetData.Yyktwd, 0, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_energy_init, R.string.can_gc_yysz, R.string.can_yysj, R.string.can_kssj, R.string.can_kssj, R.string.can_jssj, R.string.can_jssj, R.string.can_gc_xhrq, R.string.can_cdsz, R.string.can_cdsz, R.string.can_car_time_set, R.string.can_car_time_set, R.string.can_gc_xhrq, R.string.can_cdsz, R.string.can_cdsz, R.string.can_car_time_set, R.string.can_car_time_set, R.string.can_gc_xhrq, R.string.can_gc_yycd, R.string.can_kssj, R.string.can_kssj, R.string.can_jssj, R.string.can_jssj, R.string.can_cddl, R.string.can_gc_yycd, R.string.can_yhsjcd, R.string.can_szzccddl, R.string.can_szzccddl, R.string.can_yyktwd, R.string.can_yyktqcc, R.string.can_yyktwdz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP_CHECK, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP_CHECK, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP_CHECK, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS};
        this.mPopCheckValueIds[12] = new int[]{R.array.can_gc_week_arrays};
        this.mPopCheckValueIds[17] = new int[]{R.array.can_gc_week_arrays};
        this.mPopCheckValueIds[7] = new int[]{R.array.can_gc_week_arrays};
        this.mPopValueIds[25] = new int[]{R.string.can_yhsjdyxcd, R.string.can_jyhsjdcd};
        this.mPopValueIds[26] = new int[]{R.string.can_max_a, R.string.can_reduce, R.string.can_min_value};
        this.mPopValueIds[27] = new int[]{R.string.can_max_a, R.string.can_reduce, R.string.can_min_value};
        this.mPopValueIds[1] = new int[]{R.string.can_cch9_seatmemory_key1, R.string.can_cdfs1, R.string.can_cdfs2};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 6;
        iArr2[1] = 26;
        iArr2[2] = 1;
        iArr[30] = iArr2;
        this.mSetData = new CanDataInfo.HyRzcXnySet();
        if (CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 15) {
            this.mItemVisibles[8] = 0;
            this.mItemVisibles[9] = 0;
            this.mItemVisibles[10] = 0;
            this.mItemVisibles[11] = 0;
            this.mItemVisibles[12] = 0;
            this.mItemVisibles[13] = 0;
            this.mItemVisibles[14] = 0;
            this.mItemVisibles[15] = 0;
            this.mItemVisibles[16] = 0;
            this.mItemVisibles[17] = 0;
            this.mItemVisibles[18] = 0;
            this.mItemVisibles[19] = 0;
            this.mItemVisibles[20] = 0;
            this.mItemVisibles[21] = 0;
            this.mItemVisibles[22] = 0;
            this.mItemVisibles[23] = 0;
            this.mItemVisibles[24] = 0;
            this.mItemVisibles[25] = 0;
            this.mItemVisibles[26] = 0;
            this.mItemVisibles[27] = 0;
            this.mItemVisibles[28] = 0;
            this.mItemVisibles[29] = 0;
            this.mItemVisibles[30] = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        ((CanItemTitleValList) this.mItemObjects[3]).GetBtn().setText(String.valueOf(getString(R.string.can_kssj)) + "_" + getString(R.string.can_hour));
        ((CanItemTitleValList) this.mItemObjects[4]).GetBtn().setText(String.valueOf(getString(R.string.can_kssj)) + "_" + getString(R.string.can_min));
        ((CanItemTitleValList) this.mItemObjects[5]).GetBtn().setText(String.valueOf(getString(R.string.can_jssj)) + "_" + getString(R.string.can_hour));
        ((CanItemTitleValList) this.mItemObjects[6]).GetBtn().setText(String.valueOf(getString(R.string.can_jssj)) + "_" + getString(R.string.can_min));
        ((CanItemBlankTextList) this.mItemObjects[8]).SetVal(String.valueOf(getString(R.string.can_cdsz)) + "_" + getString(R.string.can_xcclcfsjsz) + "1");
        ((CanItemTitleValList) this.mItemObjects[10]).GetBtn().setText(String.valueOf(getString(R.string.can_car_time_set)) + "_" + getString(R.string.can_hour));
        ((CanItemTitleValList) this.mItemObjects[11]).GetBtn().setText(String.valueOf(getString(R.string.can_car_time_set)) + "_" + getString(R.string.can_min));
        ((CanItemBlankTextList) this.mItemObjects[13]).SetVal(String.valueOf(getString(R.string.can_cdsz)) + "_" + getString(R.string.can_xcclcfsjsz) + "2");
        ((CanItemTitleValList) this.mItemObjects[15]).GetBtn().setText(String.valueOf(getString(R.string.can_car_time_set)) + "_" + getString(R.string.can_hour));
        ((CanItemTitleValList) this.mItemObjects[16]).GetBtn().setText(String.valueOf(getString(R.string.can_car_time_set)) + "_" + getString(R.string.can_min));
        ((CanItemBlankTextList) this.mItemObjects[18]).SetVal(String.valueOf(getString(R.string.can_cdsz)) + "_" + getString(R.string.can_gc_yycd));
        ((CanItemTitleValList) this.mItemObjects[19]).GetBtn().setText(String.valueOf(getString(R.string.can_kssj)) + "_" + getString(R.string.can_hour));
        ((CanItemTitleValList) this.mItemObjects[20]).GetBtn().setText(String.valueOf(getString(R.string.can_kssj)) + "_" + getString(R.string.can_min));
        ((CanItemTitleValList) this.mItemObjects[21]).GetBtn().setText(String.valueOf(getString(R.string.can_jssj)) + "_" + getString(R.string.can_hour));
        ((CanItemTitleValList) this.mItemObjects[22]).GetBtn().setText(String.valueOf(getString(R.string.can_jssj)) + "_" + getString(R.string.can_min));
        ((CanItemPopupList) this.mItemObjects[26]).GetBtn().setText(String.valueOf(getString(R.string.can_szzccddl)) + "_" + getString(R.string.can_cdz));
        ((CanItemPopupList) this.mItemObjects[27]).GetBtn().setText(String.valueOf(getString(R.string.can_szzccddl)) + "_" + getString(R.string.can_bxs));
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiRzcGetXnySet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(1, this.mSetData.Yyszfs - 1);
            if (this.mSetData.Yyszfs == 1) {
                showItem(2, 0);
                showItem(3, 0);
                showItem(4, 0);
                showItem(5, 0);
                showItem(6, 0);
                showItem(7, 0);
            } else if (this.mSetData.Yyszfs == 2) {
                showItem(2, 1);
                showItem(3, 1);
                showItem(4, 1);
                showItem(5, 0);
                showItem(6, 0);
                showItem(7, 1);
            } else {
                showItem(2, 1);
                showItem(3, 1);
                showItem(4, 1);
                showItem(5, 1);
                showItem(6, 1);
                showItem(7, 1);
            }
            updateItem(3, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Yyss_kssjH)}));
            updateItem(4, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Yyss_kssjM * 10)}));
            updateItem(5, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Yyss_jssjH)}));
            updateItem(6, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Yyss_jssjM * 10)}));
            updateItem(7, getXhrqSta(this.mSetData.Yyss_cf));
            updateItem(9, this.mSetData.Xcclcfsjsz_cf & 128);
            updateItem(10, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Xcclcfsjsz_sjszH)}));
            updateItem(11, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Xcclcfsjsz_sjszM * 10)}));
            updateItem(12, getXhrqSta2(this.mSetData.Xcclcfsjsz_cf));
            updateItem(14, this.mSetData.Xcclcfsjsz_cf2 & 128);
            updateItem(15, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Xcclcfsjsz_sjszH2)}));
            updateItem(16, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Xcclcfsjsz_sjszM2 * 10)}));
            updateItem(17, getXhrqSta2(this.mSetData.Xcclcfsjsz_cf2));
            updateItem(19, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Yycdsj_kssjH)}));
            updateItem(20, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Yycdsj_kssjM * 10)}));
            updateItem(21, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Yycdsj_jssjH)}));
            updateItem(22, 0, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Yycdsj_jssjM * 10)}));
            updateItem(24, this.mSetData.Cddl_yycdsj);
            updateItem(25, this.mSetData.Cddl_Yhsjcd);
            updateItem(26, this.mSetData.Cddl_szzccddl_Cdz);
            updateItem(27, this.mSetData.Cddl_Szzccddl_Bxs);
            updateItem(28, this.mSetData.YyKtwd);
            updateItem(29, this.mSetData.YyKtQcs);
            if (this.mSetData.Yyktwd > 0) {
                updateItem(30, this.mSetData.Yyktwd, String.format("%.1f ℃", new Object[]{Double.valueOf((((double) (this.mSetData.Yyktwd - 6)) * 0.5d) + 17.5d)}));
                return;
            }
            updateItem(30, this.mSetData.Yyktwd, String.format("%d", new Object[]{0}));
        }
    }

    private int[] getXhrqSta(int cycSta) {
        ArrayList<Integer> mList = new ArrayList<>();
        if ((cycSta & 32) > 0) {
            mList.add(0);
        }
        if ((cycSta & 16) > 0) {
            mList.add(1);
        }
        if ((cycSta & 8) > 0) {
            mList.add(2);
        }
        if ((cycSta & 4) > 0) {
            mList.add(3);
        }
        if ((cycSta & 2) > 0) {
            mList.add(4);
        }
        if ((cycSta & 1) > 0) {
            mList.add(5);
        }
        if ((cycSta & 64) > 0) {
            mList.add(6);
        }
        int[] cycStaInt = new int[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            cycStaInt[i] = mList.get(i).intValue();
        }
        return cycStaInt;
    }

    private int[] getXhrqSta2(int XhrqSta) {
        ArrayList<Integer> mList = new ArrayList<>();
        if ((XhrqSta & 1) > 0) {
            mList.add(0);
        }
        if ((XhrqSta & 2) > 0) {
            mList.add(1);
        }
        if ((XhrqSta & 4) > 0) {
            mList.add(2);
        }
        if ((XhrqSta & 8) > 0) {
            mList.add(3);
        }
        if ((XhrqSta & 16) > 0) {
            mList.add(4);
        }
        if ((XhrqSta & 32) > 0) {
            mList.add(5);
        }
        if ((XhrqSta & 64) > 0) {
            mList.add(6);
        }
        int[] cycStaInt = new int[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            cycStaInt[i] = mList.get(i).intValue();
        }
        return cycStaInt;
    }

    public void QueryData() {
        CanJni.HyundaiRzcXnySet(255, 1, 0, 0);
        Sleep(10);
        CanJni.HyundaiRzcQuery(83, 0);
    }
}
